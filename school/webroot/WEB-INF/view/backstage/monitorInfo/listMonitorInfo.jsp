<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/user/user-list.js" />
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader"> 


<form id="pagerForm" method="post" action="equipment/addReader.do?" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<!--  <td>
					<span>姓名</span>
					<input name="studentName" placeholder="请输入" value="${student.studentName}"/>
				</td>
				
				<td align="right">
					<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
				</td>-->
			</tr>
			<tr>
				
				
			</tr>
		</tbody>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<bmtag:link isAuth="true" href="equipment/newMonitor.do"  
					target="navTab" rel="userAddPage" messageKey="common.icon.new" dwzClass="add" id="addLink"/>
			</li>
			
		<!-- <li>
				<bmtag:link isAuth="true" target="navTab" href="equipment/delectMonitor.do?pkUserinfo={pkUserinfo}" rel="pkUserinfo"
					 messageKey="删除" dwzClass="delete" />
			</li> -->	
		</ul>
	</div>
	<table class="table" width="100%" layoutH="139">
		<thead>
		<tr align="center" >
			<th width="10"><input type="checkbox" group="pkUserinfos"
					class="checkboxCtrl" /></th>
			<th><bmtag:message messageKey="序号"/></th>
		    <th><bmtag:message messageKey="监控卡号"/></th>
		    <th><bmtag:message messageKey="经度"/></th>
		    <th><bmtag:message messageKey="纬度"/></th>
		    <th><bmtag:message messageKey="监控卡状态"/></th>
		    <th><bmtag:message messageKey="绑定的学生名字"/></th>
		    <th><bmtag:message messageKey="绑定的老师名字"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${monitorList}" var="monitor" >
			<tr target="pkUserinfo" rel="${monitor.monitorId }" align="center">
				<td>
					<input name="pkUserinfos"  value="${monitor.monitorId}" type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${monitor.mCode }</td>
				<td>${monitor.longitude }</td>
				<td>${monitor.latitude }</td>
				<td><c:choose>
						<c:when test="${monitor.status=='1'}">
							已绑定
						</c:when>
						<c:when test="${monitor.status=='0'}">
							未绑定
						</c:when>
				</c:choose></td>
				<td>${monitor.studentName }</td>
				<td>${monitor.teacherName }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
