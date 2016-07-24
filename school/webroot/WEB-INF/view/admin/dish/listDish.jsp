<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="dish/findDishList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td style="align: left"><span>菜名</span> <input
							name="dishName" value="${dish.dishName }" /> <span>价格</span> <input
							name="dishPrice" value="${dish.dishPrice }" /> <span>类别</span> <select
							name="dishLeibie" 
							style="width: 150px; height: 20px">
								<option value="0">—请选择—</option>
								<option value="1"
									<c:if test="${dish.dishLeibie==1}">selected="selected"</c:if>>川菜</option>
								<option value="2"
									<c:if test="${dish.dishLeibie==2}">selected="selected"</c:if>>浙菜</option>
								<option value="3"
									<c:if test="${dish.dishLeibie==3}">selected="selected"</c:if>>湘菜</option>
								<option value="4"
									<c:if test="${dish.dishLeibie==4}">selected="selected"</c:if>>粤菜</option>
						</select> <span>季节</span> <select name="dishSeason"
							 style="width: 150px; height: 20px">
								<option value="0">—请选择—</option>
								<option value="1"
									<c:if test="${dish.dishSeason==1}">selected="selected"</c:if>>春季</option>
								<option value="2"
									<c:if test="${dish.dishSeason==2}">selected="selected"</c:if>>夏季</option>
								<option value="3"
									<c:if test="${dish.dishSeason==3}">selected="selected"</c:if>>秋季</option>
								<option value="4"
									<c:if test="${dish.dishSeason==4}">selected="selected"</c:if>>冬季</option>
						</select></td>
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><bmtag:link target="navTab" href="dish/newDish.do"
					rel="dishAddPage" messageKey="common.icon.new" dwzClass="add" /></li>
			<li><bmtag:link target="navTab"
					href="dish/editDish.do?pkDish={pkDish}" rel="dishEditPage"
					messageKey="common.icon.edit" dwzClass="edit" /></li>
			<li><bmtag:link target="ajaxTodo"
					href="dish/removeDish.do?pkDish={pkDish}"
					altKey="common.msg.delete.confirm" messageKey="删除"
					dwzClass="delete" /></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
			<tr align="center">
				<th width="10"><input type="checkbox" group="pkDishs"
					class="checkboxCtrl" /></th>
				<th>序号</th>
				<th>菜品ID</th>
				<th>菜名</th>
				<th>价格</th>
				<th>类别</th>
				<th>季节</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${dishList}" var="dish" varStatus="status">
				<tr target="pkDish" rel="${dish.pkDish }" align="center">
					<td><input name="pkDishs" value="${dish.pkDish}"
						type="checkbox" /></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${dish.pkDish }</td>
					<td>${dish.dishName }</td>
					<td>${dish.dishPrice }</td>
					<td><c:if test="${dish.dishLeibie==0}">家常菜</c:if> <c:if
							test="${dish.dishLeibie==1}">川菜</c:if> <c:if
							test="${dish.dishLeibie==2}">浙菜</c:if> <c:if
							test="${dish.dishLeibie==3}">湘菜</c:if> <c:if
							test="${dish.dishLeibie==4}">粤菜</c:if></td>
					<td><c:if test="${dish.dishSeason==0}">不限</c:if> <c:if
							test="${dish.dishSeason==1}">春季</c:if> <c:if
							test="${dish.dishSeason==2}">夏季</c:if> <c:if
							test="${dish.dishSeason==3}">秋季</c:if> <c:if
							test="${dish.dishSeason==4}">冬季</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>