<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
 <title>您找的页面不存在</title>

<style type="text/css">
<!--
BODY {	PADDING-RIGHT: 0px; PADDING-LEFT: 35px; BACKGROUND: url(/images/photoback.gif) repeat-x left top; PADDING-BOTTOM: 0px; MARGIN: 0px; FONT: 12px Arial, Helvetica, sans-serif; COLOR: #333; PADDING-TOP: 35px}
A {	COLOR: #007ab7; TEXT-DECORATION: none}
A:hover {COLOR: #007ab7; TEXT-DECORATION: none}
A:hover {COLOR: #de1d6a}
.hidehr {DISPLAY: none}
.show12 {PADDING-RIGHT: 0px; DISPLAY: block; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 5px 0px; PADDING-TOP: 0px}
.show13 {PADDING-RIGHT: 0px; DISPLAY: block; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 5px 0px; PADDING-TOP: 0px}
.show12 A {	BORDER-RIGHT: #bfdeed 1px solid; PADDING-RIGHT: 6px; BORDER-TOP: #bfdeed 1px solid; DISPLAY: inline-block; PADDING-LEFT: 6px; BACKGROUND: #d8ebf4; PADDING-BOTTOM: 2px; OVERFLOW: hidden; BORDER-LEFT: #bfdeed 1px solid; LINE-HEIGHT: 17px; PADDING-TOP: 2px; BORDER-BOTTOM: #bfdeed 1px solid; HEIGHT: 16px}
.show13 A {	BORDER-RIGHT: #bfdeed 1px solid; PADDING-RIGHT: 6px; BORDER-TOP: #bfdeed 1px solid; DISPLAY: inline-block; PADDING-LEFT: 6px; BACKGROUND: #d8ebf4; PADDING-BOTTOM: 2px; OVERFLOW: hidden; BORDER-LEFT: #bfdeed 1px solid; LINE-HEIGHT: 17px; PADDING-TOP: 2px; BORDER-BOTTOM: #bfdeed 1px solid; HEIGHT: 16px}
.show12 A:hover {	BORDER-RIGHT: #ea5e96 1px solid; BORDER-TOP: #ea5e96 1px solid; BACKGROUND: #fce8f0; BORDER-LEFT: #ea5e96 1px solid; COLOR: #de1d6a; BORDER-BOTTOM: #ea5e96 1px solid; TEXT-DECORATION: none}
.show13 A:hover {	BORDER-RIGHT: #ea5e96 1px solid; BORDER-TOP: #ea5e96 1px solid; BACKGROUND: #fce8f0; BORDER-LEFT: #ea5e96 1px solid; COLOR: #de1d6a; BORDER-BOTTOM: #ea5e96 1px solid; TEXT-DECORATION: none}
.show12 A {	FONT-WEIGHT: normal; FONT-SIZE: 12px}
.show13 A {	BORDER-RIGHT: #ed268c 1px solid; BORDER-TOP: #ed268c 1px solid; BACKGROUND: #dd137b; BORDER-LEFT: #ed268c 1px solid; COLOR: #fff; BORDER-BOTTOM: #ed268c 1px solid}
.img404 {PADDING-RIGHT: 0px; PADDING-LEFT: 0px; BACKGROUND: url(http://www.lanrentuku.com/down/js/images/12630051960.gif) no-repeat left top; FLOAT: left; PADDING-BOTTOM: 0px; MARGIN: 0px; WIDTH: 80px; PADDING-TOP: 0px; POSITION: relative; HEIGHT: 90px}
H2 {PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FONT-SIZE: 16px; FLOAT: left; PADDING-BOTTOM: 25px; MARGIN: 0px; WIDTH: 80%; LINE-HEIGHT: 0; PADDING-TOP: 25px; BORDER-BOTTOM: #ccc 1px solid; POSITION: relative}
H3.wearesorry {	PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FONT-WEIGHT: normal; FONT-SIZE: 10px; LEFT: 117px; PADDING-BOTTOM: 0px; MARGIN: 0px; COLOR: #ccc; LINE-HEIGHT: 10px; PADDING-TOP: 0px; POSITION: absolute; TOP: 70px}
.content {	CLEAR: both; PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FONT-SIZE: 13px; LEFT: 80px; FLOAT: left; PADDING-BOTTOM: 0px; MARGIN: 0px; WIDTH: 80%; LINE-HEIGHT: 19px; PADDING-TOP: 0px; POSITION: relative; TOP: -30px}
.content UL {PADDING-RIGHT: 35px; PADDING-LEFT: 35px; PADDING-BOTTOM: 20px; MARGIN: 0px; PADDING-TOP: 10px}
.show12 UL {PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px 0px 0px 20px; PADDING-TOP: 0px; LIST-STYLE-TYPE: none}
.show14 UL LI {	MARGIN-BOTTOM: 5px}

-->
</style>
</head>

<body>

<H1 class=hidehr>找不到你要的页面。</H1>
<DIV class=img404>　</DIV>
<H2>抱歉，找不到您要的页面……</H2>
<H3 class=wearesorry>We're sorry but the page your are looking for is Not 
Found...</H3>
<DIV class=content>仔细找过啦，没有发现你要找的页面。最可能的原因是： 
  <UL>
  <LI>在地址中可能存在键入错误。 </LI>
  <LI>当你点击某个链接时，它可能已过期。 
</LI></UL>
</DIV>
</body>
</html>
