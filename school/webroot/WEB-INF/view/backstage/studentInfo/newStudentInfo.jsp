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
<form method="post" action="userManager/addStudent.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
	<div class="pageFormContent nowrap" layoutH="97">
	<input type="hidden" value="1" name="normRegisteType" />
	<input type="hidden" value="6" name="userLevel" />
		<dl>
			<dt><bmtag:message messageKey="姓名"/></dt>
			<dd>
				<input name="studentName" class="required" class="textInput" maxlength="20" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt>
				<bmtag:message messageKey="性别"/>
			</dt>
			<dd>
				<input type="radio" value="男" class="required" name="studentSex" />男 
				<input type="radio" value="女" class="required" name="studentSex" />女 
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
				<input name="studentAge"   class="required" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="班级"/></dt>
			<dd>
				<input name="studentGrade" class="required" maxlength="48" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="监控卡号"/></dt>
			<dd>
				<select name="monitorCode"  class="select_Style" onchange="getCarTypeName(this,'usinAddCarinfo')" style="width: 130px">
					<option value="0">--请选择--</option>
					<c:forEach items="${MonitorList}" var="Monitor">
						<option value="${Monitor.mCode }">${Monitor.mCode}</option>
					</c:forEach>
				</select>
			</dd>
		</dl>
	
		<dl>
			<dt><bmtag:message messageKey="班主任"/></dt>
			<dd>
				<select name="teacherId"  class="select_Style" onchange="getCarTypeName(this,'usinAddCarinfo')" style="width: 130px">
					<option value="0">--请选择--</option>
					<c:forEach items="${teacherList}" var="teacher">
						<option value="${teacher.teacherId }">${teacher.teacherName}</option>
					</c:forEach>
				</select>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="家长名字"/></dt>
			<dd>
				<input name="parentName" class="required" maxlength="48" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="家长电话"/></dt>
			<dd>
				<input name="parentPhone" class="required"  minlength="11" maxlength="11" style="width:130px;"/>
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