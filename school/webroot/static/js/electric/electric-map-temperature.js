
function temperature(data){
	$("#hot").fadeTo(0,0);//初始透明度为0；
	$('#num').click(function(){this.select();})
	
	$('#Risk').click(function(){ 
		var inputvalue=null;
	    inputvalue=(parseFloat(data.lowTemperature)+parseFloat(data.highTemperature))/2;
		inputvalue = 20;
	    if(inputvalue=="NaN"){
	    	inputvalue = "0";
	    }
	    var inputnum=parseInt(inputvalue);  
	    
	        $("#Hgheader").html(inputvalue+"℃");
	        if(inputnum>=100){
	        inputnum=100;
	        $('#num').val(100)
	        $("#Hgheader").html(100+"℃");
	        } 
	    var Columnhe=inputnum/100;
	    $("#Hg").animate({height:inputnum},'show');
	    $("#hot").fadeTo('slow',Columnhe);
	//在这里把html换成text效果也是一样的;
	});
	
}