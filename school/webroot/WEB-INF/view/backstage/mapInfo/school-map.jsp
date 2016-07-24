<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>地图</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>

</head>

<body>
	<div id="wrapper">

		<!--------地图--------->

		<div class="map" id="allMap" style="position: reletive; height: 930px">

		</div>
		<div class="pileList" id="tableList"></div>
		<div id="mainList"
			style="position: absolute; right: 6px; top: 8px; height: 790px; width: 233px; z-index: 5000;">
			<div style="float: left">
				<input type="text" id="serach" placeholder="直接搜索">
			</div>
			<div style="float: right">
					<span id="serachInput" style=" display:block; float:right; width:35px; height:25px;margin-right:17px; background:#ff8800;"> 
					<img style="margin:1.5px 0px 0px 10px;" src="/school_html/static/images/map/Magnifying-glass-silhouette.png" width="20px" height="19">
					</span>
			</div>
		
			<div>
		
				<br>
				<br>
			</div>
			<select class="provinceCode required" id="electricSelProvince"
				next="electricSelCity" name="elPiOwnProvinceCode"
				style="float: none; width: 130px;">
				<option value="">--选择省--</option>
						<c:forEach var="item" items="${proviceMap}">
							<option value="${item.provinceId}"
								${item.provinceId== tblElectricpile.elPiOwnProvinceCode ? 'selected="selected"' : ''} >
								${item.provinceName}
							</option>
						</c:forEach>
			</select> 
			<select class="cityCode required" id="electricSelCity" next="electricSelDistrict" data-val="${tblElectricpile.elPiOwnCityCode}" name="elPiOwnCityCode" 
			 style="float: none; width:130px; margin-top:10px;">
						 <option value="">--选择市--</option>
			</select>
			<select id="electricSelDistrict" data-val="${tblElectricpile.elPiOwnCountyCode}" name="elPiOwnCountyCode" class="required" 
			 style="float: none; width:130px; margin-top:10px;">
						<option value="">--选择区--</option>
			</select>	
		</div>





	</div>
	<!-- end content -->
	<!--//footer-->
	<script type="text/javascript">
	var appShareUrl="${appShareUrl}";
	this_tag="electricDistribution";

	
</script>
	<link href="<%=basePath%>/static/css/map.css" rel="stylesheet"
		type="text/css" />
	<script type="text/javascript"
		src="http://webapi.amap.com/maps?v=1.3&key=5482098780018a97976fbb4f52252595"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/config.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/common/json3.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/map/map.js"></script>




</body>
</html>
