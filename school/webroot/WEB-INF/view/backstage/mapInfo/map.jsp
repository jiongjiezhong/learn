<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!doctype html>
<html lang="en" >
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
<script type="text/javascript">
</script>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <style type="text/css">
      h3{
        font-size: 1.17em;
        line-height: 1.4;
        font-weight: bold;
        margin: 2px;
      }
      hr{
        margin: 1px;
      }
      html,body{
        width: 100%;
        height: 100%;
        margin: 0px;
      }
      .map{
        height: 100%;
        width: 100%;
        float: left;
      }
      .login{
      	height:160px;
      	width:200px;
		opacity:0.5;
		background:#000;
		color:#ff0;
		position:absolute;
		top:0;
		right:0;
		z-index:9999;
      }
      .loginButton{
      width:50px;
      height:30px;
      line-height:30px;
      border:1px solid #ccc;
      display:inline-block;
      text-align:center;
      background:#000 repeat-y;
      border-radius:5px;
      color:#fff;
      margin-left:80px;
      }
      
   #mapDiv .amap-indoormap-floorbar-control{bottom:10%}
   .panel-box{display:none};
    </style>
    <title>室内地图</title>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	%>
    
  </head>
  <body>
   <div class="login">
    	<form method="post" method="post">
    	账号:<input type="text" name="" value=""/><br/><br/>
    	密码:<input type="password" value=""/><br/><br/>
    	<!-- <div class="loginButton">登陆</div> -->
    	<input id="loginButton" type="submit" value="登录" />
    	<input type="reset" value="重置" />
    	</form>
    </div>
    
    <div id="mapDiv" class="map" tabindex="0" >
    
    </div>
    <script type="text/javascript" src='https://webapi.amap.com/maps?v=1.3&key=5482098780018a97976fbb4f52252595&plugin=AMap.ToolBar'></script>
  </body>
  	<link href="<%=basePath%>/static/css/map.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/config.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/common/json3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/map/map.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/map/websocket.js"></script>
</html>
