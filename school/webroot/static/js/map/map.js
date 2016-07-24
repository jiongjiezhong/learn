var ponitMarker={};
    
 var map = new AMap.Map('mapDiv', {
                resizeEnable: false,
				zooms:[1,20],
                zoom:16,
                center:[120.353038,30.31436]
          });
         
         
		  var marker = new AMap.Marker({
    		icon : "http://vdata.amap.com/icons/b18/1/2.png",
            position: [120.353038,30.31436],
            map:map
    	});

		  	
		//添加多边形覆盖物  
//function addPolygon(){  
//   var polygonArr=new Array();//多边形覆盖物节点坐标数组   
//   polygonArr.push(new AMap.LngLat("120.357951","30.310544"));   
//   polygonArr.push(new AMap.LngLat("120.357973","30.318639"));   
//   polygonArr.push(new AMap.LngLat("120.348424","30.318602")); 
//   polygonArr.push(new AMap.LngLat("120.348467","30.309933")); 
//   polygon=new AMap.Polygon({     
//   path:polygonArr,//设置多边形边界路径  
//   strokeColor:"#0000ff", //线颜色  
//   strokeOpacity:0.2, //线透明度   
//   strokeWeight:3,    //线宽   
//   fillColor: "#f5deb3", //填充色  
//   fillOpacity: 0.95//填充透明度  
//  });   
//   polygon.setMap(map);  
// } 
// addPolygon();
//添加学生轨迹折线  
function addPolyline(data){  
   var polylineArr=new Array();//节点坐标数组   
   for(var i=0;i<data.length;i++){
	   polylineArr.push(new AMap.LngLat(data[i].longitude,data[i].latitude));  
   }
//   polylineArr.push(new AMap.LngLat("120.357951","30.310544"));   
//   polylineArr.push(new AMap.LngLat("120.357973","30.318639"));   
//   polylineArr.push(new AMap.LngLat("120.348424","30.318602")); 
//   polylineArr.push(new AMap.LngLat("120.348467","30.309933")); 


   alert(polylineArr)
   polyline=new AMap.Polyline({     
	   zIndex:50,
   path:polylineArr,//设置多边形边界路径  
   strokeColor:"#006600", //线颜色  
   strokeOpacity:0.5, //线透明度   
   strokeWeight:3,    //线宽   
  });   
   polyline.setMap(map);  
 } 

function getMapData(){
	//获取学生和老师
	 $.ajax({
			type : 'post',
			url : "/school/web/map/getMonitorForMap.do",
			dataType : "json",
			success : function(datas) {
				//map.clearMap();
				//polygon.setMap(map);  
				drawMarkers(datas);
			}
		});
}
getMapData();
//setInterval("getMapData()",1000);
 function drawMarkers(datas){
		var data;
		var bounds=map.getBounds();
		var point;
		var containsFlag=false;
		for (var i = 0; i < datas.length; i++) {
			data = datas[i];
			if (data.longitude && data.latitude) {
				var point=new AMap.LngLat(data.longitude,data.latitude);	
				containsFlag=bounds.contains(point);
				if(containsFlag){	
					addMarker(data);
					//删除全局数组中的该点
//					data.splice(i,1); 
				}
			}
		}
	}
 
 function addMarker(data) {	
	if(!ponitMarker[data.monitorId]){
		var offset = new AMap.Pixel(15, 10);
		var mapNumber="map_b_1.png";
		if(data.studentName){
			mapNumber="map_a_5.png";
		}
		var marker = new AMap.Marker({
			icon :  "/school/static/images/map/"+mapNumber,
			position : new AMap.LngLat(data.longitude,
					data.latitude),
			map : map
		});
		// 鼠标点击marker弹出自定义的信息窗体
		AMap.event.addListener(marker,'click',function() {
			// 判断访问地址
//			var name = data.studentName == "" ? data.teacherName: data.studentName;
//			// 拼接弹窗内容
//			var content = ' <div ><h3>'
//					+ name
//					+ '</h3></dd>';
			// 构建信息窗体中显示的内容
//			var infoWindow = new AMap.InfoWindow({
//				content : content,
//				offset : new AMap.Pixel(8, -25), // 相对于基点的位置
//				size : new AMap.Size(480, 140)
//			});
//			infoWindow.open(map, marker.getPosition());
			$.ajax({
				type : 'post',
				dataType : "json",
				url : "/school/web/map/getMonitorHistoryById.do",
				data :{
					monitorId : data.monitorId
				} ,
				success : function(dataHistorys) {
					 addPolyline(dataHistorys);
				}
			});
			
		});
		ponitMarker[data.monitorId]=marker;
	}else{
		var marker=ponitMarker[data.monitorId];
		 marker.setPosition([data.longitude,data.latitude]); //更新点标记位置
	}
	
}

 

 $(function(){
	   $("#loginButton").click(function(){//保存用户
		   var form = document.forms[0];
			var v=$.md5($("#password").val());
			$("#password1").val(v);
		    $("#password").attr("disabled", "true");
			form.action = "${pageContext.request.contextPath}/tologin.do";
			form.submit();
		   });
	});


function newMonitor(data){
	$.ajax({
		type : 'post',
		url : "/school/web/map/newMonitorForMap.do",
		data :{
			lng : data.longitude,
			lat : data.latitude
		} ,
		success : function(datas) {
			
		var msgInfo = JSON3.stringify(datas);
//			if (msgInfo.indexOf("needSelectValue") >= 0) {
//				 $("input[name=suitable]").attr("checked",false);			
//				return;
//			}
//			mapDatas=datas.electricPileMonitorMapList;
//			if(mapDatas[0] && paramStr != '?1=1'){
//				map.setCenter([mapDatas[0].electricLongitude,mapDatas[0].electricLatitude]);
//			}


		}
	});
}













//$(function() {
//	var map;
//	var mapDatas;
//	config.mapRequest = function(paramStr) {
//     	if (!map) {
//			map = new AMap.Map('allMap', {
//				view : new AMap.View2D({
//					zoom : 13,
//				}),
//				scrollWheel : true
//			});
//			// 在地图中添加ToolBar插件
//			map.plugin([ "AMap.ToolBar" ], function() {
//				var toolBar = new AMap.ToolBar();
//				map.addControl(toolBar);
//			});
//			map.on('click', function(e) {
//			});
//			map.on('moveend', function(e) {
//				drawMarkers(mapDatas);
//			});
//			map.on('zoomend', function(e) {
//				drawMarkers(mapDatas);
//			});
//		} else {
//			map.clearMap();
//		}
//     	
//		$.ajax({
//			type : 'post',
//			url : "/school_html/itf/map/getSchoolForMap.do",
//			dataType : "json",
//			success : function(datas) {
//
//			var msgInfo = JSON3.stringify(datas);
//				if (msgInfo.indexOf("needSelectValue") >= 0) {
//					 $("input[name=suitable]").attr("checked",false);			
//					return;
//				}
//				mapDatas=datas.electricPileMonitorMapList;
//				if(mapDatas[0] && paramStr != '?1=1'){
//					map.setCenter([mapDatas[0].electricLongitude,mapDatas[0].electricLatitude]);
//				}
//				drawMarkers(mapDatas);
//				
//				// ie8以下浏览器，地图label样式无法修改，此处弥补
//				var browser = getBrowserInfo();
//				if (browser.browser = "ie" && browser.version <= 9) {
//					setTimeout(setLabelStyle, 1500);
//				}
//
//			}
//		});
//		
//		function drawMarkers(datas){
//			var data;
//			var bounds=map.getBounds();
//			var point;
//			var containsFlag=false;
//			for (var i = 0; i < datas.length; i++) {
//				data = datas[i];
//				if (data.electricLongitude && data.electricLatitude) {
//					var point=new AMap.LngLat(data.electricLongitude,data.electricLatitude);	
//					containsFlag=bounds.contains(point);
//					if(containsFlag){				
//						addMarker(data);
//						//删除全局数组中的该点
//						mapDatas.splice(i,1); 
//					}
//				}
//			}
//		}
//
//		function setLabelStyle() {
//			$(".amap-marker-label").addClass("marker-label").removeClass(
//					"amap-marker-label");
//		}
//		function addMarker(data) {	
//			var offset = new AMap.Pixel(15, 10);
//			var marker = new AMap.Marker({
//				icon :  "/school_html/static/images/map/map_a_5.png",
//				position : new AMap.LngLat(data.electricLongitude,
//						data.electricLatitude),
//				map : map
//			});
//			// 鼠标点击marker弹出自定义的信息窗体
//			AMap.event
//					.addListener(
//							marker,
//							'click',
//							function() {
//								// 判断访问地址
//								var url = (data.electricType == 2 ? config.IGetElectricPlant
//										: config.IGetElectricPile)
//										+ "?eid=" + data.electricId;
//
////								var pileId = data.electricId;
////								if(data.electricType ==1){
////									selectHeadList(pileId);
////								}else if(data.electricType ==2){
////									$.ajax({
////										type : 'post',
////										url : basepath+"/admin/electricPileMonitor/getElectricPileListByStationId.do",
////										dataType : "json",
////										data : {
////											electricId:data.electricId
////										},
////										success : function(datas) {
////											var content="";
////											for (var i = 0; i < datas.length; i++) {		
////												var electricName= datas[i].electricName;
////												var electricAddress = datas[i].electricAddress;		
////												 content +='<div><p id="name" ><a onclick="selectHeadList('+datas[i].electricId+')">'+electricName+
////													'</a></p> <p >地址 ：<span id="address">'+electricAddress+
////													'</span> </p> <p>状态 : <span id="appoint">	预约</span></p>'+
////													'<p id="line"></p>'+			
////													'</div>';
////												
////											}
////											$("#pileDetailList").hide();
////											$("mainList").show();
////											$("#listDetail").html(content);
////										}
////									});
////								}	
//								// 拼接弹窗内容
//								var content = ' <div ><dl style="margin-top:0px;" >'
//										+ '<dd class="P-Name"><h3 class="zhuang_title" >'
//										+ data.electricName
//										+ '</h3></dd>'
//										+ ' <dd class="add"><span class="zhuang_roud" >地址：'
//										+ data.electricAddress
//										+ '</span></dd></dl><div>';
//								var obj = {
//									lng : data.electricLongitude,
//									lat : data.electricLatitude,
//									type : "",
//									name : data.electricName,
//									addr : data.electricAddress,
//									service : 1.0
//								}
//								// 构建信息窗体中显示的内容
//								var infoWindow = new AMap.InfoWindow({
//									content : content,
//									offset : new AMap.Pixel(8, -25), // 相对于基点的位置
//									size : new AMap.Size(480, 140)
//								});
//								infoWindow.open(map, marker.getPosition());
//							});
//		}
//	}
	
