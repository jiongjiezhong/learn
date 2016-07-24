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
	<form method="post" action="userManager/modifyUser.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<input type="hidden" name="studentId" value="${student.studentId}" />
			<dl>
				<dt>
					<bmtag:message messageKey="名字" />
				</dt>
				<dd>
					<input name="studentName" class="textInput"
						value="${student.studentName}" maxlength="20" style="width: 130px;" />
					<span class="info"></span>
				</dd>
			</dl>		
			<dl>
				<dt>
					<bmtag:message messageKey="年纪" />
				</dt>
				<dd>
					<input name="studentAge" class="textInput"
						value="${student.studentAge}" maxlength="20" style="width: 130px;" />
					<span class="info"></span>
				</dd>
			</dl>		
			<dl>
				<dt>
					<bmtag:message messageKey="班级" />
				</dt>
				<dd>
					<input name="studentGrade" value="${student.studentGrade}"
						maxlength="80" style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="性别" />
				</dt>
				<dd>
					<input type="radio" value="男" name="studentSex" checked="checked" ${student.studentSex==
					"男"?'checked="checked" ':''}/>男 <input type="radio" value="女" name="studentSex"
					${student.studentSex=="女"?'checked="checked" ':''}/>女 <span class="info"></span>
				</dd>
			</dl>
			
			<dl>
				<dt>
					<bmtag:message messageKey="绑定的监控卡号" />
				</dt>
				<dd>
					<input name="monitorCode" value="${student.monitorCode}" disabled="disabled" 
						maxlength="80" style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			
			<dl>
				<dt><bmtag:message messageKey="班主任"/></dt>
				<dd>
					<select name="teacherId"  class="select_Style" onchange="getCarTypeName(this,'usinAddCarinfo')" style="width: 130px">
						<option value="0">--请选择--</option>
						<c:forEach items="${teacherList}" var="teacher">
							<option value="${teacher.teacherId }" ${teacher.teacherId==
								student.teacherId ? 'selected="selected"' : ''}>${teacher.teacherName}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="家长名字" />
				</dt>
				<dd>
					<input name="parentName" value="${student.parentName}"
						maxlength="80" style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="家长电话" />
				</dt>
				<dd>
					<input name="parentPhone" value="${student.parentPhone}"
				class="textInput required"  style="width: 130px;"  minlength="11" maxlength="11" /> <span class="info"></span>
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