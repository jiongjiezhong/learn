<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="book/findBookList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td style="align: left"><span>书名</span> <input
							name="bookName" value="${book.bookName }" /></td>
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
			<li><bmtag:link target="navTab" href="book/newBook.do"
					rel="bookAddPage" messageKey="common.icon.new" dwzClass="add" /></li>
			<li><bmtag:link target="navTab"
					href="book/editBook.do?pkBook={pkBook}" rel="bookEditPage"
					messageKey="common.icon.edit" dwzClass="edit" /></li>
			<li><bmtag:link target="ajaxTodo"
					href="book/removeBook.do?pkBook={pkBook}"
					altKey="common.msg.delete.confirm" messageKey="删除"
					dwzClass="delete" /></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
			<tr align="center">
				<th width="10"><input type="checkbox" group="pkBooks"
					class="checkboxCtrl" /></th>
				<th>序号</th>
				<th>书本ID</th>
				<th>书名</th>
				<th>价格</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${bookList}" var="book" varStatus="status">
				<tr target="pkBook" rel="${book.pkBook }" align="center">
					<td><input name="pkBooks" value="${book.pkBook}"
						type="checkbox" /></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${book.pkBook }</td>
					<td>${book.bookName }</td>
					<td>${book.bookPrice }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span> <!-- <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option> 
				<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option> 
				<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option> 
				<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option> 
			</select> --> 共${pager.total }条, 共${pager.pageTotal}页
			</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
