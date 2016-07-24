<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json) {
	}
</script>

<div class="pageContent"
	style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<li><bmtag:link href="common/departmentSelectList.do?backRel=actionDepartmentList&processType=actionDept" target="dialog"
					rel="selActionDepartment" messageKey="common.icon.new"
					dwzClass="add" id="addAcDeptLink" /></li>
			<li><bmtag:link
					href="action/removeActionDept.do?departmentIds={departmentIds}"
					target="ajax" rel="actionDepartmentList"
					altKey="common.msg.delete.confirm" id="delAcDeptLink" 
					messageKey="common.icon.delete" dwzClass="delete" /></li>
		</ul>
	</div>
	<div  id="actionDepartmentList">
	<table class="table" width="100%"  layoutH="310" >
		<thead>
			<tr>
				<th><bmtag:message messageKey="common.label.index" /></th>
				<th><bmtag:message messageKey="company.label.company_name" /></th>
				<th><bmtag:message messageKey="department.label.department_id" /></th>
				<th><bmtag:message messageKey="department.label.department_name" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${actionModel.deptList}" var="department" varStatus="status">
				<tr target="departmentIds" rel="${department.companyId},${department.departmentId}" align="center">
					<td>${ status.index + 1}</td>
					<td>${department.companyName }</td>
					<td>${department.departmentId }</td>
					<td>${department.departmentName }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="panelBar">
	</div>
</div>
