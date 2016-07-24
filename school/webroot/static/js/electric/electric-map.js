/**
 * Created by Kael on 2015/3/20.
 */
$(function() {
	$("#search").click(function() {
		if ($("#address").val() != "") {
			loadContent();
		}
	})

	$(".searchCondition").click(function() {
		loadContent();
	});

	$(':checkbox[name=chargingMode]').each(function() {
		$(this).click(function() {
			if ($(this).is(':checked')) {
				$(this).siblings().removeAttr('checked');
			}
			loadContent();
		});
	});

	$('#suitable').click(function() {
		var userid = UserService.getUserId();		
		if (!userid) {
			$("#suitable").attr("checked",false);
			$("#login").click();
			return;
		}
	});
	/**
	 * 电桩筛选条件
	 */
	config.data = function() {
		var cur_li = [];
		var lb = 3;
		var chargingMode = "";
		$(':checkbox[name=chargingMode]').each(function() {
			if ($(this).is(':checked')) {
				chargingMode = $(this).val();
			}
		});
		var powerInterface = "";
		$(':checkbox[name=powerInterface]').each(function() {
			if ($(this).is(':checked')) {
				powerInterface = $(this).val();
			}
		});
		return {
			screenType : lb,
			chargingMode : chargingMode,
			powerInterface : powerInterface,
			headState : $("#headState").is(':checked') ? $("#headState").val()
					: null,
			commStatus : $("#commStatus").is(':checked') ? $("#commStatus")
					.val() : null,
			suitable : $("#suitable").is(':checked') ? $("#suitable").val()
					: null,
			address : $("#address").val()
		};
	}

	/**
	 * 获取用户当前地区
	 */
	config.point = function() {
		var data_pot;
		$.ajax({
			type : 'get',
			url : config.IGetPoint,
			dataType : "json",
			async : false,
			success : function(data) {
				data_pot = data.data.point;
			}
		});
		return data_pot;
	}

	/**
	 * 获取图片地址
	 */
	config.absImg = function(imgUrl) {
		if (!imgUrl) {
			return config.defaultImg;
		}
		if (imgUrl && imgUrl.indexOf('http://') == 0) {
			return imgUrl;
		}
		return config.imageServer + imgUrl;
	};

	/**
	 * 电桩查找 地图模式
	 */
	var map;
	var mapDatas;
	config.mapRequest = function(paramStr) {
		pileErrorCount(paramStr);//获取故障电桩数量
		getPileCount(paramStr);//获取电站电桩数量
		getChargingCount(paramStr);//获取充电中的电桩数量
		getBespokeCount(paramStr);//获取预约中的电桩数量
		getOnlineCount(paramStr);//获取分享点数量
		getOfflineCount(paramStr);//获取专属点数量
		
     	if (!map) {
			map = new AMap.Map('allMap', {
				view : new AMap.View2D({
					zoom : 13,
				}),
				scrollWheel : true
			});

			// 不设置城市，默认加载到当前城市
			// 在地图中添加ToolBar插件
			map.plugin([ "AMap.ToolBar" ], function() {
				var toolBar = new AMap.ToolBar();
				map.addControl(toolBar);
			});
			map.on('click', function(e) {
			});
			map.on('moveend', function(e) {
				drawMarkers(mapDatas);
			});
			map.on('zoomend', function(e) {
				drawMarkers(mapDatas);
			});
		} else {
			map.clearMap();
		}
     	
     	
		$.ajax({
			type : 'post',
			url : basepath+"/admin/electricPileMonitor/getElectricPileForMap.do"+paramStr,
			dataType : "json",
			data : config.data(),
			success : function(datas) {
			var msgInfo = JSON3.stringify(datas.electricPileMonitorMapList);
				if (msgInfo.indexOf("needSelectValue") >= 0) {
					 $("input[name=suitable]").attr("checked",false);			
					return;
				}
				mapDatas=datas.electricPileMonitorMapList;
				if(mapDatas.length==0){
					alert("没有电桩");
					return;
				}
				if(mapDatas[0] && paramStr != '?1=1'){
					map.setCenter([mapDatas[0].electricLongitude,mapDatas[0].electricLatitude]);
				}
				drawMarkers(mapDatas);
				
				// ie8以下浏览器，地图label样式无法修改，此处弥补
				var browser = getBrowserInfo();
				if (browser.browser = "ie" && browser.version <= 9) {
					setTimeout(setLabelStyle, 1500);
				}

			}
		});
		
		function drawMarkers(datas){
			var data;
			var bounds=map.getBounds();
			var point;
			var containsFlag=false;
			for (var i = 0; i < datas.length; i++) {
				data = datas[i];
				if (data.electricLongitude && data.electricLatitude) {
					var point=new AMap.LngLat(data.electricLongitude,data.electricLatitude);	
					containsFlag=bounds.contains(point);
					if(containsFlag){				
						addMarker(data);
						//删除全局数组中的该点
						mapDatas.splice(i,1); 
					}
				}
			}
		}

		function setLabelStyle() {
			$(".amap-marker-label").addClass("marker-label").removeClass(
					"amap-marker-label");
		}
		function addMarker(data) {
			var offset = new AMap.Pixel(15, 10);
			var imgIcon = "map-ok.png"
			if(data.isError == 1){
				imgIcon = "map-error.png"
			}
			var marker = new AMap.Marker({
				icon : basepath + "/static/images/map/"+imgIcon,
				position : new AMap.LngLat(data.electricLongitude,
						data.electricLatitude),
				map : map
			});
			marker.setLabel({
				style : "border:0px",
				offset : offset,
				//content : data.headNum
			});
			// 鼠标点击marker弹出自定义的信息窗体
			AMap.event
					.addListener(
							marker,
							'click',
							function() {

								// 判断访问地址
								var  pileId = data.electricId;
								if(data.electricType == 1){
									selectHeadList(pileId);
								}else if(data.electricType ==2){
									hideChartDiv();
									$("#headDetail").hide();
									$.ajax({
										type : 'post',
										url : basepath+"/admin/electricPileMonitor/getElectricPileListByStationId.do",
										dataType : "json",
										data : {
											electricId:data.electricId
										},
										success : function(datas) {
											var content="";
											for (var i = 0; i < datas.length; i++) {		
												var electricName= datas[i].electricName;
												var electricAddress = datas[i].electricAddress;
												var headState = "";
												if(datas[i].isFree == 1){
													headState += '<span class="zt_color_a">空闲</span>、'
												}
												if(datas[i].isBespeak == 1){
													headState += '<span class="zt_color_a">预约</span>、'
												}
												if(datas[i].isCharge == 1){
													headState += '<span class="zt_color_b">充电</span>、'
												}
												if(datas[i].isError == 1){
													headState += '<span class="zt_color_c">故障</span>、'
												}
												headState = headState.substr(0,headState.length-1);	
												content += '<dl class="lie_box">'
								                    +'<dt><a onclick="selectHeadList('+datas[i].electricId+')">'+electricName+'</a></dt>'
								                    +'<dd>地址：'+electricAddress+'</dd>'
								                    +'<dd>状态：'+headState+'</dd>'
								                    +'<dd style=" border-bottom:1px #CCCCCC solid; height:20px;"></dd></dl>'+			
													'</div>';
												
												
											}
											$("#listDetail").hide();
											$("#pileDetailList").show();
											$("#pileDetailList").html('<div><span id="back">返回</span>'+content);
											
										}
									});
								}
								
								var electricName = data.electricName.length > 12 ? data.electricName
										.substring(0, 12)
										+ "..."
										: data.electricName;
								var electricAddress = data.electricAddress.length > 40 ? data.electricAddress
										.substring(0, 40)
										+ "..."
										: data.electricAddress;
								// 拼接弹窗内容
								var content = ' <div class="floatL list2"><dl style="margin-top:0px;" >'
										+ '<dt><img src="'
										+ basepath
										+ '/static/images/img/ic-1.png" width="43" height="43" alt="  " /></a></dt>'
										+ '<br><dd class="P-Name"><h3 class="zhuang_title" title="'
										+ data.electricName
										+ '">'
										+ electricName
										+ '</h3></dd>'
										+ ' <br><dd class="add"><span class="zhuang_roud" title="'
										+ data.electricAddress
										+ '">地址：'
										+ electricAddress
										+ '</span></dd></dl><div>';
								var obj = {
									lng : data.electricLongitude,
									lat : data.electricLatitude,
									type : "",
									name : data.electricName,
									addr : data.electricAddress,
									service : 1.0
								}
								// 构建信息窗体中显示的内容
								var infoWindow = new AMap.InfoWindow({
									content : content,
									offset : new AMap.Pixel(8, -25), // 相对于基点的位置
									size : new AMap.Size(480, 140)
								});
								infoWindow.open(map, marker.getPosition());
							});
		}
	}
	
	// 内容加载
	var dataType = "map";
	loadContent();
	function loadContent() {
		if (dataType == "map") {
			$("#allMap").show();
			$("#table").hide();
			searchAll();
		} else {
			$("#allMap").hide();
			$("#table").show();
			config.begin = 1;
			listRequest(1, 10);
		}
	}
});

function getBrowserInfo() {
	var userAgent = navigator.userAgent, rMsie = /(msie\s|trident.*rv:)([\w.]+)/, rFirefox = /(firefox)\/([\w.]+)/, rOpera = /(opera).+version\/([\w.]+)/, rChrome = /(chrome)\/([\w.]+)/, rSafari = /version\/([\w.]+).*(safari)/;
	var browser;
	var version;
	var ua = userAgent.toLowerCase();
	var match = rMsie.exec(ua);
	if (match != null) {
		return {
			browser : "ie",
			version : match[2] || "0"
		};
	}
	var match = rFirefox.exec(ua);
	if (match != null) {
		return {
			browser : match[1] || "",
			version : match[2] || "0"
		};
	}
	var match = rOpera.exec(ua);
	if (match != null) {
		return {
			browser : match[1] || "",
			version : match[2] || "0"
		};
	}
	var match = rChrome.exec(ua);
	if (match != null) {
		return {
			browser : match[1] || "",
			version : match[2] || "0"
		};
	}
	var match = rSafari.exec(ua);
	if (match != null) {
		return {
			browser : match[2] || "",
			version : match[1] || "0"
		};
	}
	if (match != null) {
		return {
			browser : "",
			version : "0"
		};
	}
	return null;
}

function searchAll(pageNum, pageSize){
	var electricSelProvince = $("#electricSelProvince").val()
	var electricSelCity = $("#electricSelCity").val()
	var electricSelDistrict = $("#electricSelDistrict").val()
	var pileName = $("#serach").val();
	var paramStr = "?1=1"
	if(electricSelProvince){
		paramStr += "&electricSelProvince="+electricSelProvince;
	}
	if(electricSelCity){
		paramStr += "&electricSelCity="+electricSelCity;
	}
	if(electricSelDistrict){
		paramStr += "&electricSelDistrict="+electricSelDistrict;
	}
	if(pileName){
		paramStr += "&pileName="+pileName;
	}
	config.mapRequest(paramStr);
	serachList(1, 10,paramStr);
//	hideChartDiv();
}

	/**
	 * 获取故障电桩数量
	 */
	function pileErrorCount(paramStr){
		$.ajax({
			type : 'POST',
			url : "/wanma/admin/electricPileMonitor/pileErrorCount.do"+paramStr,
			dataType : "json",
			cache : false,
			success : function(json) {
				$("#pileErrorCount").html(json.count);
				$("#pileErrorDiv").click(function(){
					queryErrorPile(paramStr);
				});
			}
		});
	}

	/**
	 * 获取故障电桩列表
	 */
	function queryErrorPile(paramStr){
		hideChartDiv();
		var paramStrTemp = paramStr;
		$.ajax({
			type : 'post',
			url : basepath+"/admin/electricPileMonitor/queryErrorPile.do"+paramStrTemp,
			dataType : "json",
			cache : false,
			success : function(datas) {
				var content="";
				for (var i = 0; i < datas.length; i++) {		
					var electricName= datas[i].electricName;
					var electricAddress = datas[i].electricAddress;		
					 content +='<div><a href="#" onclick="returnList()">返回</a><p id="name" >'+electricName+
						'</p> <p >地址 ：<span id="address">'+electricAddress+
						'</span> </p> <p>状态 : <span id="appoint">预约</span></p>'+
						'<p class="line"></p>'+			
						'</div>';
					
				}
				$("mainList").hide();
				$("#headDetail").show();
				$("#headDetail").html(content);
			}
		});
	}
	/**
	 * 获取电站电桩数量
	 */
	function getPileCount(paramStr){
		$.ajax({
			type : 'POST',
			url : "/wanma/admin/electricPileMonitor/getAllPileCount.do"+paramStr,
			dataType : "json",
			cache : false,
			success : function(json) {
				$("#psCount").html(json.pile_count);
			}
		});
	}
	/**
	 * 获取充电中的电桩数量
	 */
	function getChargingCount(paramStr){
		$.ajax({
			type : 'POST',
			url : "/wanma/admin/electricPileMonitor/getChargingCount.do"+paramStr,
			dataType : "json",
			cache : false,
			success : function(json) {
				$("#chargingCount").html(json.pile_count);
			}
		});
	}
	/**
	 * 获取预约中电桩数量
	 */
	function getBespokeCount(paramStr){
		$.ajax({
			type : 'POST',
			url : "/wanma/admin/electricPileMonitor/getBespokeCount.do"+paramStr,
			dataType : "json",
			cache : false,
			success : function(json) {
				$("#bespokeCount").html(json.pile_count);
			}
		});
	}
	
	/**
	 * 获取分享点数量
	 */
	function getOnlineCount(paramStr){
		$.ajax({
			type : 'POST',
			url : "/wanma/admin/electricPileMonitor/getOnlineCount.do"+paramStr,
			dataType : "json",
			cache : false,
			success : function(json) {
				$("#onlineCount").html(json.pile_count);
			}
		});
	}
	
	/**
	 * 获取专属点数量
	 */
	function getOfflineCount(paramStr){
		$.ajax({
			type : 'POST',
			url : "/wanma/admin/electricPileMonitor/getOfflineCount.do"+paramStr,
			dataType : "json",
			cache : false,
			success : function(json) {
				$("#offlineCount").html(json.pile_count);
			}
		});
	}
	
	function serachList(pageNum, pageSize,paramStr){
		if(!paramStr){
			var electricSelProvince = $("#electricSelProvince").val()
			var electricSelCity = $("#electricSelCity").val()
			var electricSelDistrict = $("#electricSelDistrict").val()
			var pileName = $("#serach").val();
			var paramStr = "?1=1"
			if(electricSelProvince){
				paramStr += "&electricSelProvince="+electricSelProvince;
			}
			if(electricSelCity){
				paramStr += "&electricSelCity="+electricSelCity;
			}
			if(electricSelDistrict){
				paramStr += "&electricSelDistrict="+electricSelDistrict;
			}
			if(pileName){
				paramStr += "&pileName="+pileName;
			}
		}
		$.ajax({
			type : 'post',
			url : basepath+"/admin/electricPileMonitor/getElectricPileMapList.do"+paramStr,
			dataType : "json",
			data :initPageParams(pageNum,pageSize),
			success : function(datas) {
				var msgInfo = datas.data;	
				var content="";
				for (var i = 0; i < msgInfo.length; i++) {	
					var headState = "";
					var headColor = "";
					var electricName= msgInfo[i].electricName;
					var electricAddress = msgInfo[i].electricAddress;
					if(msgInfo[i].isFree == 1){
						headState += '<span class="zt_color_a">空闲</span>、'
					}
					if(msgInfo[i].isBespeak == 1){
						headState += '<span class="zt_color_a">预约</span>、'
					}
					if(msgInfo[i].isCharge == 1){
						headState += '<span class="zt_color_b">充电</span>、'
					}
					if(msgInfo[i].isError == 1){
						headState += '<span class="zt_color_c">故障</span>、'
					}
					headState = headState.substr(0,headState.length-1);
					
                    
					 content += '<dl class="lie_box">'
		                    +'<dt>'+electricName+'</dt>'
		                    +'<dd>地址：'+electricAddress+'</dd>'
		                    +'<dd>状态：'+headState+'</dd>'
		                    +'<dd style=" border-bottom:1px #CCCCCC solid; height:20px;"></dd></dl>'
					
				}
				$("#listDetail").show();
				$("#listDetail").html(content);
				initPage(datas, "serachList");
				$("#pageEle").show();
			}

		});
		
	}
	
	
	
	$("#back").live("click",function(){
		$("#pileDetailList").hide();
		$("#listDetail").show();
	})
	
	function selectHeadList(pileId){	
		var chargingMode ="";
		$.ajax({
			type : 'post',
			url : basepath+"/admin/electricPileMonitor/getElectricPileDetail.do",
			dataType : "json",
			data : {
				electricId:pileId
			},
			success : function(datas) {
			
				var pileDetail = datas.pileDetail;
				var headList = datas.headList;
				var electricPileName = pileDetail.electricPileName;
				var electricPileAdress = pileDetail.electricPileAdress;
				var electricPowerSize = pileDetail.electricPowerSize;
				var serviceCharge = pileDetail.raInServiceCharge;
				var raInServiceCharge = pileDetail.raInServiceCharge;
				var totalChargeDl = headList[0]==null?0.00:headList[0].totalChargeDl;
				var totalChargeTime = headList[0]==null?0.00:headList[0].totalChargeTime;
				var totalChargeAmt = headList[0]==null?0.00:headList[0].totalChargeAmt;
				var state = "";
				if(pileDetail.electricPileChargingMode==5){
					chargingMode ="快充";
				}else if(pileDetail.electricPileChargingMode==14){
					chargingMode ="慢充";
				}
				$("mainList").hide();
				var headDetail = "<tr>"
					for(var i=0;i<headList.length;i++){
						if(i<2){
							headDetail += '<td width="20"></td><td width="45%" class="qiangtou_NAV_left"><a data-totalChargeDl='+headList[i].totalChargeDl+' data-totalChargeTime='
							+headList[i].totalChargeTime+' data-totalChargeAmt='+headList[i].totalChargeAmt+' data-epheElectricpileheadstate='+headList[i].epheElectricpileheadstate+' onclick="selectHead(this)">'+(i+1)+'号枪头'+'</a></td><td width="10"></td>'
						}else{
							headDetail += '<tr><td width="20"></td><td width="45%" class="qiangtou_NAV_left"><a data-totalChargeDl='+headList[i].totalChargeDl+' data-totalChargeTime='
							+headList[i].totalChargeTime+' data-totalChargeAmt='+headList[i].totalChargeAmt+' data-epheElectricpileheadstate='+headList[i].epheElectricpileheadstate+' onclick="selectHead(this)">'+(i+1)+'号枪头'+'</a></td><td width="10"></td>'
						}
						  
					}
				headDetail += '</tr>'
				var detailContent = '<table width="100%" border="0">'
				+'<tr><td width="10">&nbsp;</td><td width="100%"><table width="100%" border="0">'
				+'<tr><td class="xiangqing_top">'+electricPileName+'</td><td class="xiangqing_top"><a href="#" onclick="returnList()">返回</a></td>'
				+'</tr><tr><td colspan="2" class="xiangqing_add">地址：'+electricPileAdress+'</td></tr></table></td><td width="10">&nbsp;</td></tr>'
				+'</table><br><div style="clear: both;"></div>'
				+'<table width="100%" border="0">'
				+'<tr><td width="10">&nbsp;</td><td width="100%"><table width="100%" border="0"cellpadding="0" cellspacing="0" class="lie_box_xq_biaoge">'
				+'<tr><td>编号</td><td><strong>01</strong></td></tr>'
				+'<tr><td>服务费</td><td><strong><span class="zt_color_c">'+raInServiceCharge+'</span>元/度</strong></td></tr>'
				+'<tr><td>电费</td><td><strong><a href="#">查看费率</a></strong></td></tr>'
				+'<tr><td>充电方式</td><td><strong>'+chargingMode+'</strong></td></tr>'
				+'<tr><td>额定功率</td><td><strong>'+electricPowerSize+'kw</strong></td></tr>'
				+'<tr><td>操作</td><td class="/static/images/img/biaoge_right_open"><a href="#"><strong>开启</strong></a></td></tr>'
				+'</table></td><td width="10">&nbsp;</td></tr>'
				+'</table>'
				+'<div style="clear: both;"></div>'
				+'<table width="100%" border="0" style="margin-top: 10px;">'
				+headDetail+'</table>'
				+'<div style="clear: both;"></div>'
				+'<table width="100%" border="0">'
				+'<tr><td width="10">&nbsp;</td><td width="100%"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="lie_box_xq_biaoge">'
				+'<tr><td>状态</td><td><strong id="headStataSpan">空闲</strong></td></tr>'
				+'<tr><td>总充电电量</td><td><strong><span class="zt_color_c" id="totalChargeDlSpan">'+totalChargeDl+'</span>度</strong></td></tr>'
				+'<tr><td>总充小时</td><td><strong id="totalChargeTimeSpan">'+totalChargeTime+'小时</strong></td></tr>'
				+'<tr><td>总充电费用</td><td><strong id="totalChargeAmtSpan">'+totalChargeAmt+'元</strong></td></tr></table></td>'
				+'<td width="10">&nbsp;</td></tr></table>'
				+'<div style="clear: both; height: 20px;"></div>'

				$("mainList").hide();
				$("#headDetail").show();
				$("#headDetail").html(detailContent);
				showChartDiv();
				drowVoltageChart(datas);
				drowDcDiv(datas);
				temperature();
			}
		});
	}
	
	function selectHead(this_e){
		$("#chargeDl").html($(this_e).attr("data-totalChargeDl")+"<span>元/度</span>");
		$("#chargeTime").html($(this_e).attr("data-totalChargeTime")+"<span>小时</span>");
		$("#chargeAmt").html($(this_e).attr("data-totalChargeAmt")+"<span>元/度</span>");
		
	}
	
	function hideChartDiv(){
		$("#pileCountDiv").css({'bottom':'0'});
		$("#chartDiv").hide();
	}
	
	function showChartDiv(){
		$("#pileCountDiv").css({'bottom':'28%'});
		$("#chartDiv").show();
	}
	
	function chartDivSlideUp(){
		$('#pileCountDiv').animate({bottom:'28%'},1000)
		$("#chartDiv").animate({height: 'toggle'}, 1000);
	}
	
	function chartDivSlideDown(){
		$('#pileCountDiv').animate({bottom:'0'},1000)
		$("#chartDiv").animate({height: 'toggle'}, 1000);
	}
	
	$(document).ready(function() {
		$(".xialaNAV_close").click(function(){
			hideChartDiv();
		});
	});
	
	function selectHead(this_e){
//		$(".qiangtou_NAV_left").css({'background': 'none'})
//		$(this_e).css({'background': 'url(/wanma/static/images/img/electric/qiangtouBG.png) center 0'})
		$("#totalChargeDlSpan").html($(this_e).attr("data-totalChargeDl"));
		$("#totalChargeTimeSpan").html($(this_e).attr("data-totalChargeTime")+"小时");
		$("#totalChargeAmtSpan").html($(this_e).attr("data-totalChargeAmt")+"元");
		var stata = $(this_e).attr("data-epheElectricpileheadstate")
		if(stata == '0'){
			$("#headStataSpan").html("空闲");
		}
		if(stata == '3'){
			$("#headStataSpan").html("预约");
		}
		if(stata == '6'){
			$("#headStataSpan").html("充电");
		}
		if(stata == '9'){
			$("#headStataSpan").html("故障");
		}
	}
	
	function returnList(){
		$("#mainList").show();
		$("#headDetail").hide();
	}
