<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>爱充网后台管理系统</title>
<link href="${webroot }/res/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">	
	alert(" ${erMessage}");
	var servicHost="${servicHost}";
	if(servicHost==null && servicHost==""){
		servicHost="http://localhost:8080/wanma";
	}
	window.location.href = ""+servicHost+"/login.do"; 
</script>
</head>

<body>
</body>
</html>