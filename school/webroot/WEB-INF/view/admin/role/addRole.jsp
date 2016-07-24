<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<h2 class="contentTitle">角色新增</h2>
<div class="pageContent">
<form id="addRoleForm" method="post" action="role/saveRole.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
	<input type="hidden" name="roleId" value="${role.roleId}" />
	<input type="hidden" id="menuIds" name="menuIds" value="${role.roleId}" />
	<div class="pageFormContent nowrap" layoutH="97" >
			<dl>
				<dt>角色名称：</dt>
				<dd>
					<input name="roleName" value="${role.roleName}" type="text" class="required normal" size="25"
						maxlength="20"  remote="role/checkUnique.do"/>
				</dd>
			</dl>
			<dl>
				<dt>角色类别：</dt>
				<dd>
					<select name="category" class="required">
						<option value="">---请选择---</option>
						<c:if test="${userLevel<=2}">
							<option value="2" }>普通管理员</option>
							<option value="3" }>纯商家</option>
						</c:if>
						<c:if test="${userLevel==3}">
							<option value="4" }>商家子角色</option>
						</c:if>
						<c:if test="${userLevel==5||userLevel<=2}">
							<option value="5" }>个体商家</option>
						</c:if>
					</select>
				</dd>
			</dl>
			<!-- <dl>
				<dt>权限分配：</dt>
				<dd>
					<ul id="treeDemo" class="ztree"></ul> 
				</dd>
			</dl> -->
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" type="submit" /></li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul> 
	</div>
</form>
</div>
<script type="text/javascript">
var setting = {
		check: {
			enable: true,
			chkboxType: { "Y" : "s", "N" : "s" }
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};

	var zNodes =${menuData};
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});

</script>