var page={
		pageSize:10,
		pageNum:1,
		totalPage:10
}
var actBtnDisableClass="fanyeOFF";
var actBtnClass="fanyeOFF fanyeON";
var numBtnActiveClass="fanyeNav";
var numBtnClass="fanyeNav fanyeNavLink";

function initPageParams(pageNum,pageSize){
	var pageParams= {
			pageSize:pageSize,
			pageNum:pageNum
		}
	return pageParams;
}

function initPage(data,pageFun,pageElement){
	pageElement=pageElement||'pageEle';
	var pageNum=data.currentPage;
	var pageSize=data.count;
	var totalPage=data.totalPage;
	if(totalPage<=1){//数据小时不显示
		$("#"+pageElement).html("");
		return;
	}
	var html=initPageParam(pageNum,totalPage,pageSize,pageFun)
			+initBeforePage(pageNum,totalPage)
			+initNumPage(pageNum,totalPage)
			+initNextPage(pageNum,totalPage);
	$("#"+pageElement).html(html).show();
}

function initPageParam(pageNum,totalPage,pageSize,pageFun){
	var html='<ul class="fanyeMu" pageNum="'+pageNum+'" totalPage="'+totalPage+'" pageSize="'+pageSize+'" pageFun="'+pageFun+'">';
	return html;
}

function initBeforePage(pageNum,totalPage){
	var tempClass=actBtnClass;
	if(pageNum==1){
		tempClass=actBtnDisableClass;
	}
	var html='<li class="'+tempClass+'" data-type="first">首页</li>';
	html+='<li class="'+tempClass+'" data-type="previous">上一页</li>';
	return html; 
}

function initNumPage(pageNum,totalPage){
	var tempNum=0;
	var numLimit=5;
	var divideNum=(numLimit+1)/2;
	//alert(divideNum+"__"+pageNum+"__"+totalPage)
	var html='';
	if(totalPage<=numLimit+2){//总页数过小
		for(var i=1;i<=totalPage;i++){
			html+='<li class="'+(i==pageNum?numBtnActiveClass:numBtnClass)+'" data-page-num="'+i+'">'+i+'</li>';
		}
	}else{//总页数标准
		if(pageNum<=divideNum){//页目小于下限划分数
			tempNum=numLimit;
			for(var i=1;i<=numLimit;i++){
				html+='<li class="'+(i==pageNum?numBtnActiveClass:numBtnClass)+'" data-page-num="'+i+'">'+i+'</li>';
			}
			html+='<li class="'+numBtnClass+'">...</li>';
			html+='<li class="'+numBtnClass+'" data-page-num="'+totalPage+'" >'+totalPage+'</li>';
		}else if(totalPage<=pageNum+divideNum-1){//页目大于上限划分数
			html+='<li class="'+numBtnClass+'" data-page-num="1">1</li>';
			html+='<li class="'+numBtnClass+'" >...</li>';
			for(var i=totalPage-numLimit+1;i<=totalPage;i++){
				html+='<li class="'+(i==pageNum?numBtnActiveClass:numBtnClass)+'" data-page-num="'+i+'">'+i+'</li>';
			}
		}else{//正常分页
			html+='<li class="'+numBtnClass+'" data-page-num="1">1</li>';
			if(pageNum-divideNum>1){
				html+='<li class="'+numBtnClass+'" >...</li>';
			}
			for(var i=pageNum-divideNum+1;i<=pageNum+divideNum-1;i++){
				html+='<li class="'+(i==pageNum?numBtnActiveClass:numBtnClass)+'" data-page-num="'+i+'">'+i+'</li>';
			}
			if(pageNum+divideNum<totalPage){
				html+='<li class="'+numBtnClass+'" >...</li>';
			}
			html+='<li class="'+numBtnClass+'" data-page-num="'+totalPage+'">'+totalPage+'</li>';
		}
	}
	return html;
}

function initNextPage(pageNum,totalPage){
	var tempClass=actBtnClass;
	if(pageNum==totalPage){
		tempClass=actBtnDisableClass;
	}
	var html='<li class="'+tempClass+'" data-type="next">下一页</li><li class="'+tempClass+'" data-type="last">末页</li></ul>';
	return html;
}

//分页页数点击
$('body').on('click', '.fanyeMu li.'+numBtnActiveClass+'[data-page-num]', function (e) {
    var pageNum=parseInt($(this).attr("data-page-num"));
    var pageSize=parseInt($(this).parent().attr("pageSize"));
    var pageFun=$(this).parent().attr("pageFun");
    eval(pageFun+'('+pageNum+','+pageSize+')');
});

//上下页首尾页点击
$('body').on('click', '.fanyeMu li.'+actBtnDisableClass+'[data-type]', function (e) {
	var dataType=$(this).attr("data-type");
    var pageNum=parseInt($(this).parent().attr("pageNum"));
    var totalPage=parseInt($(this).parent().attr("totalPage"));
    if(dataType=="previous"){
    	if(pageNum==1){
    		return;
    	}
    	pageNum=pageNum-1;
    }else if(dataType=="next"){
    	if(pageNum==totalPage){
    		return;
    	}
    	pageNum=pageNum+1;
    }else if(dataType=="first"){
    	if(pageNum==1){
    		return;
    	}
    	pageNum=1;
    }else if(dataType=="last"){
    	if(pageNum==totalPage){
    		return;
    	}
    	pageNum=totalPage;
    }
    var pageSize=$(this).parent().attr("pageSize");
    var pageFun=$(this).parent().attr("pageFun");
    eval(pageFun+'('+pageNum+','+pageSize+')');
});
