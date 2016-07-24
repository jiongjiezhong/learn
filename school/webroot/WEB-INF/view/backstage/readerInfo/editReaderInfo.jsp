<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/user/user-list.js" />
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json) {
	}	
	  
	
</script>

<h2 class="contentTitle">
	<bmtag:message messageKey="user.title.user.edit" />
</h2>
<div class="pageContent">
	<form method="post" action="equipment/modifyReader.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<input type="hidden" name="readerId" value="${reader.readerId}" />
			<dl>
				<dt>
					<bmtag:message messageKey="名字" />
				</dt>
				<dd>
					<input name="readerName" class="textInput"
						value="${reader.readerName}" maxlength="20" style="width: 130px;" />
					<span class="info"></span>
				</dd>
			</dl>		
			
			<dl>
				<dt>
					<bmtag:message messageKey="经度" />
				</dt>
				<dd>
					<input name="longitude" value="${reader.longitude}"
						maxlength="80" style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="纬度" />
				</dt>
				<dd>
					<input name="latitude" value="${reader.latitude}"
						maxlength="80" style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
	
		
		</div>
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit"
						type="submit" id="formSubmitter" /></li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" /></li>
			</ul>
		</div>
	</form>
</div>