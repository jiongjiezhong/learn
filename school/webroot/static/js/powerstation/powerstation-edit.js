$("body").on("input propertychange",".epNum",function(){
	var value=$(this).val();
	var count=0;
	$(".epNum").each(function(){
    	if($(this).val()==value){
    		count++;
    	}
  	});
	if(count>1){
		alertMsg.error("此电桩的编号重复，请修改。");
		$(this).val("");
	}
 });
 function selectPile(args){
	var content="";
	var length=args["pkElectricpile"].split(",").length;
	var pkElectricpileArr=args["pkElectricpile"].split(",");
	var elpiElectricPileCodeArr=args["elpiElectricPileCode"].split(",");
	var elpiElectricpilenameArr=args["elpiElectricpilename"].split(",");
	var elpiStateArr=args["elpiState"].split(",");
	var chargingModeNameArr=args["chargingModeName"].split(",");
	var powerNameArr=args["powerName"].split(",");
	var typeNameArr=args["typeName"].split(",");
	var powerSizeNameArr=args["powerSizeName"].split(",");
	var content="";
	$(".newTr").remove();
	var oldTrLength=$("#selectPileTable tr").length;
	var epNum=getMaxNum();
	for (var i=0;i<length;i++){
			content+='<tr class="newTr border-left" rel='+pkElectricpileArr[i]+' align="center">';
			content+='<td>'+(oldTrLength+i+1)+'</td>';	 
			content+='<td>'+(elpiElectricPileCodeArr[i]?elpiElectricPileCodeArr[i]:"")+'</td>';	 
			content+='<td><input type="hidden" name="pkElectricpile" value="'+pkElectricpileArr[i]+'" />'
			content+='<input type="hidden" name="oldEpNum" value="-1" />'
			content+='<input class="epNum" type="text" name="epNum" value="'+(epNum+i+1)+'" size="1" style="float:none;" />号桩</td>';	 
			content+='<td>'+elpiElectricpilenameArr[i]+'</td>';	 
			content+='<td>'+elpiStateArr[i]+'</td>';	 
			content+='<td>'+chargingModeNameArr[i]+'</td>';	 
			content+='<td>'+powerNameArr[i]+'</td>';	 
			content+='<td>'+typeNameArr[i]+'</td>';	 
			content+='<td>'+powerSizeNameArr[i]+'</td>';
			content+='<td></td>';
			content+='</tr>';
	}
	$("#selectPileTable").append(content);
 }
 
 function getMaxNum(){
	 var epNum=0;
	 $(".epNum").each(function(){
		var value=parseInt($(this).val());
		 if(value>epNum){
			epNum=value;
		}
	});
	 return epNum;
 }