<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<div class="pageContent">
	<form method="post" action="book/saveBook.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<input type="hidden" value="1" name="normRegisteType" /> <input
				type="hidden" value="6" name="userLevel" />
			<dl>
				<dt>
					<bmtag:message messageKey="书名" />
				</dt>
				<dd>
					<input name="bookName" class="required textInput" maxlength="20"
						style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="价格" />
				</dt>
				<dd>
					<input name="bookPrice" class="required textInput number"
						maxlength="20" style="width: 130px;" /> <span class="info"></span>
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