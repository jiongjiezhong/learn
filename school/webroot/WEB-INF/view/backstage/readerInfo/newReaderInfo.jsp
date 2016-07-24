<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/user/user-list.js" />
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json){
	}
	
	
	
</script>
<h2 class="contentTitle"><bmtag:message messageKey="user.title.user.new"/></h2>
<div class="pageContent">
<form method="post" action="equipment/addReader.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
	<div class="pageFormContent nowrap" layoutH="97">
	<input type="hidden" value="1" name="normRegisteType" />
	<input type="hidden" value="6" name="userLevel" />
		<dl>
			<dt><bmtag:message messageKey="名称"/></dt>
			<dd>
				<input name="readerName" class="required" class="textInput" maxlength="20" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>

		<dl>
			<dt><bmtag:message messageKey="经度"/></dt>
			<dd>
				<input name="longitude" class="required" maxlength="48" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="纬度"/></dt>
			<dd>
				<input name="latitude" class="required" maxlength="48" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="创建时间"/></dt>
			<dd>
				<input name="creatTime" class="required"  minlength="11" maxlength="111" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
	
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" type="submit" id="formSubmitter"/></li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>