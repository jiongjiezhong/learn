<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="pageContent">
	<form method="post" action="dish/modifyDish.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<input type="hidden" name="pkDish" value="${dish.pkDish}" />
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>
					<bmtag:message messageKey="菜名" />
				</dt>
				<dd>
					<input name="dishName" value="${dish.dishName}" maxlength="32" class="textInput  required" style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="价格" />
				</dt>
				<dd>
					<input name="dishPrice" value="${dish.dishPrice}" maxlength="20" class="textInput required number" style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="类别" />
				</dt>
				<dd>
					<select name="dishLeibie" value="${dish.dishLeibie}" maxlength="20" style="width: 130px;">
						<option value="0" >—请选择—</option>
						<option value="1" <c:if test="${dish.dishLeibie==1}">selected="selected"</c:if>>川菜</option>
						<option value="2" <c:if test="${dish.dishLeibie==2}">selected="selected"</c:if>>浙菜</option>
						<option value="3" <c:if test="${dish.dishLeibie==3}">selected="selected"</c:if>>湘菜</option>
						<option value="4" <c:if test="${dish.dishLeibie==4}">selected="selected"</c:if>>粤菜</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="季节" />
				</dt>
				<dd>
					<select name="dishSeason" value="${dish.dishSeason}" maxlength="20" style="width: 130px;">
						<option value="0" >—请选择—</option>
						<option value="1" <c:if test="${dish.dishSeason==1}">selected="selected"</c:if>>春季</option>
						<option value="2" <c:if test="${dish.dishSeason==2}">selected="selected"</c:if>>夏季</option>
						<option value="3" <c:if test="${dish.dishSeason==3}">selected="selected"</c:if>>秋季</option>
						<option value="4" <c:if test="${dish.dishSeason==4}">selected="selected"</c:if>>冬季</option>
					</select>
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