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
	<form method="post" action="userManager/modifyTeacher.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<input type="hidden" name="teacherId" value="${teacher.teacherId}" />
			<dl>
				<dt>
					<bmtag:message messageKey="名字" />
				</dt>
				<dd>
					<input name="teacherName" class="textInput"
						value="${teacher.teacherName}" maxlength="20" style="width: 130px;" />
					<span class="info"></span>
				</dd>
			</dl>		
			<dl>
				<dt>
					<bmtag:message messageKey="年纪" />
				</dt>
				<dd>
					<input name="teacherAge" class="textInput"
						value="${teacher.teacherAge}" maxlength="20" style="width: 130px;" />
					<span class="info"></span>
				</dd>
			</dl>		
			<dl>
				<dt>
					<bmtag:message messageKey="班级" />
				</dt>
				<dd>
					<input name="teacherGrade" value="${teacher.teacherGrade}"
						maxlength="80" style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="性别" />
				</dt>
				<dd>
					<input type="radio" value="男" name="teacherSex" checked="checked" ${teacher.teacherSex==
					"男"?'checked="checked" ':''}/>男 <input type="radio" value="女" name="teacherSex"
					${teacher.teacherSex=="女"?'checked="checked" ':''}/>女 <span class="info"></span>
				</dd>
			</dl>
			
			<dl>
				<dt>
					<bmtag:message messageKey="绑定的监控卡号" />
				</dt>
				<dd>
					<input name="monitorCode" value="${teacher.monitorCode}" disabled="disabled"
						maxlength="80" style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>

			<dl>
				<dt>
					<bmtag:message messageKey="电话" />
				</dt>
				<dd>
					<input name="teacherPhone" value="${teacher.teacherPhone}"
					class=" required"	 style="width: 130px;" minlength="11" maxlength="11"/> <span class="info"></span>
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