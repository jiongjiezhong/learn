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
<form method="post" action="userManager/addTeacher.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
	<div class="pageFormContent nowrap" layoutH="97">
	<input type="hidden" value="1" name="normRegisteType" />
	<input type="hidden" value="6" name="userLevel" />
		<dl>
			<dt><bmtag:message messageKey="姓名"/></dt>
			<dd>
				<input name="teacherName" class="required" class="textInput" maxlength="20" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt>
				<bmtag:message messageKey="性别"/>
			</dt>
			<dd>
				<input type="radio" value="男" class="required" name="teacherSex" />男 
				<input type="radio" value="女" class="required" name="teacherSex" />女 
				<span class="info"></span>
			</dd>
		</dl>
 
	<!--  <dl>
			<dd>
				<label>出生日期</label>
				  <input id="newUser_createDate" name="normBirthday"  class="date"  style="width:130px"
					        type="text"  readonly="true" 
						 onClick="WdatePicker({el:'newUser_createDate',minDate:'1970-01-01'})" >
				<span class="info"></span>
			</dd>
		</dl>
		-->	
		
	
		
		<dl>
			<dt><bmtag:message messageKey="年纪"/></dt>
			<dd>
				<input name="teacherAge"   class="required" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="班级"/></dt>
			<dd>
				<input name="teacherGrade" class="required" maxlength="48" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="监控卡号"/></dt>
			<dd>
				<select name="monitorCode"  class="select_Style" onchange="getCarTypeName(this,'usinAddCarinfo')" style="width: 130px">
					<option value="0">--请选择--</option>
					<c:forEach items="${monitorList}" var="Monitor">
						<option value="${Monitor.mCode }">${Monitor.mCode}</option>
					</c:forEach>
				</select>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="电话"/></dt>
			<dd>
				<input name="teacherPhone" class="required" maxlength="48" style="width:130px;" maxlength="11"/>
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