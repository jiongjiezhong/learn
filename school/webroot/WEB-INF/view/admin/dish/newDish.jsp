<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<div class="pageContent">
	<form method="post" action="dish/saveDish.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<input type="hidden" value="1" name="normRegisteType" /> <input
				type="hidden" value="6" name="userLevel" />
			<dl>
				<dt>
					<bmtag:message messageKey="菜名" />
				</dt>
				<dd>
					<input name="dishName" class="required textInput" maxlength="20"
						style="width: 150px; height:20px" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="价格" />
				</dt>
				<dd>
					<input name="dishPrice" class="required textInput number"
						maxlength="6" style="width: 150px; height:20px" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="类别" />
				</dt>
				<dd>
					<select name="dishLeibie" maxlength="20" style="width: 130px;">
						<option value="0" selected="selected">—请选择—</option>
						<option value="1" >川菜</option>
						<option value="2" >浙菜</option>
						<option value="3" >湘菜</option>
						<option value="4" >粤菜</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="季节" />
				</dt>
				<dd>
					<select name="dishSeason" maxlength="20" style="width: 130px;">
						<option value="0" selected="selected">—请选择—</option>
						<option value="1" >春季</option>
						<option value="2" >夏季</option>
						<option value="3" >秋季</option>
						<option value="4" >冬季</option>
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