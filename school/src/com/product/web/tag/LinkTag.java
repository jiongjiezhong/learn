/**
 * FileName:LinkTag.java
 * Author: Administrator
 * Create: 2014年6月30日
 * Last Modified: 2014年6月30日
 * Version: V1.0 
 */
package com.product.web.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;

import com.product.common.MessageManager;
import com.product.model.MenuModel;
import com.product.utils.ObjectUtil;
import com.wanma.common.SessionMgr;

/**
 * 链接标签
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月30日
 */
public class LinkTag extends CommonTagSupport {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 3304989777218544309L;

	/** 链接 */
	private String href;
	private String authUrl;

	/** 显示目标标签 */
	private String target;

	/** 关联组件 */
	private String rel;

	/** 信息内容关键字 */
	private String messageKey;

	/** 按钮按下处理 */
	private String onclick;

	/** 查找组 */
	private String lookupGroup;

	/** 查找主键 */
	private String lookupPk;

	/** 只读 */
	private String disabled = "";

	/** 提示信息key */
	private String altKey = "";

	/** 样式 */
	private String style = "";

	/** 提示信息控制URL */
	private String titleCondition;

	/** 目标分类 */
	private String targetType = "";
	private String postType = "";

	public int doEndTag() throws JspException {

		String jsFunction = null;
		try {

			String label = "";
			String visible = TagConstantIF.BUTTON_NORMAL_CLASS;
			String userId = this.getLoginUserId();

			// 实例化 MessageManager
			MessageManager messageManager = MessageManager.getMessageManager();

			//
			// 如果设定了message key
			if (ObjectUtil.isNotEmpty(this.getMessageKey())) {
				// 取得key
				String key = this.getMessageKey();
				// key对应的值取得
				label = messageManager.getMessage(key);

			}

			if (this.getIsAuth()) {
				String authUrl=StringUtils.isNotBlank(this.getHref())?this.getHref():this.getAuthUrl();
				// 如果当前用户没有该按钮的操作权限
				if (!checkAuthByUrl(authUrl)) {
					// 当前按钮设置成不可用
					visible = TagConstantIF.BUTTON_DISABLE_CLASS;
				}
			}

			// readonly
			if ("true".equals(this.getDisabled())) {
				visible = TagConstantIF.BUTTON_DISABLE_CLASS;
			}

			// 按下时显示的信息
			String msg = "";
			if (ObjectUtil.isNotEmpty(this.getAltKey())) {
				// 按下时显示的信息
				msg = messageManager.getMessage(this.getAltKey());
			}

			if (ObjectUtil.isNotEmpty(this.getOnclick())) {
				jsFunction = this.getOnclick();
			}

			JspWriter out = pageContext.getOut();

			StringBuffer sb = new StringBuffer();

			sb.append("<a ");
			if ("button".equals(this.getDwzClass())
					&& TagConstantIF.BUTTON_DISABLE_CLASS.equals(visible)) {
				this.setDwzClass(TagConstantIF.BUTTON_DISABLE_CLASS);
			}

			if (TagConstantIF.BUTTON_DISABLE_CLASS.equals(visible)) {
				sb.append("style='display:none' ");
				this.setHref("javascript:;");
			}

			if (ObjectUtil.isNotEmpty(this.getHref())) {
				sb.append("href=\"" + this.getHref() + "\" ");
			}
			if (!TagConstantIF.BUTTON_DISABLE_CLASS.equals(visible)
					&& ObjectUtil.isNotEmpty(this.getTarget())) {
				sb.append("target=\"" + this.getTarget() + "\" ");
			}
			if (!TagConstantIF.BUTTON_DISABLE_CLASS.equals(visible)
					&& ObjectUtil.isNotEmpty(this.getTargetType())) {
				sb.append("targetType=\"" + this.getTargetType() + "\" ");
			}
			if (!TagConstantIF.BUTTON_DISABLE_CLASS.equals(visible)
					&& ObjectUtil.isNotEmpty(this.getRel())) {
				sb.append("rel=\"" + this.getRel() + "\" ");
			}
			if (ObjectUtil.isNotEmpty(this.getId())) {
				sb.append("id=\"" + this.getId() + "\" ");
			}
			if (ObjectUtil.isNotEmpty(this.getDwzClass())) {
				sb.append("class=\"" + this.getDwzClass() + "\" ");
			}
			if (ObjectUtil.isNotEmpty(this.getPostType())) {
				sb.append("postType=\"" + this.getPostType() + "\" ");
			}
			if (ObjectUtil.isNotEmpty(jsFunction)) {
				sb.append("onclick=\"" + jsFunction + "\"  ");
			}
			if (ObjectUtil.isNotEmpty(msg)) {
				sb.append("title=\"" + msg + "\" ");
			}
			if (ObjectUtil.isNotEmpty(getLookupGroup())) {
				sb.append("lookupGroup=" + getLookupGroup() + " ");
			}
			if (ObjectUtil.isNotEmpty(getLookupPk())) {
				sb.append("lookupPk=\"" + getLookupPk() + "\" ");
			}
			if (ObjectUtil.isNotEmpty(getTitleCondition())) {
				sb.append("titleCondition=\"" + getTitleCondition() + "\" ");
			}
			if (ObjectUtil.isNotEmpty(this.getStyle())) {
				sb.append("style=\"" + this.getStyle() + "\" ");
			}
			sb.append(">");
			sb.append("<span>" + label + "</span>");
			sb.append("</a>");

			out.println(sb.toString());

		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException("Failed to insert Button");

		}

		return EVAL_PAGE;
	}

	/**
	 * 链接的取得。
	 * 
	 * @return 链接
	 */
	public String getHref() {
		return href;
	}

	/**
	 * 链接的设定。
	 * 
	 * @param pHref
	 *            链接
	 */
	public void setHref(String pHref) {
		this.href = pHref;
	}

	/**
	 * 显示目标标签的取得。
	 * 
	 * @return 显示目标标签
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * 显示目标标签的设定。
	 * 
	 * @param pTarget
	 *            显示目标标签
	 */
	public void setTarget(String pTarget) {
		this.target = pTarget;
	}

	/**
	 * 关联组件的取得。
	 * 
	 * @return 关联组件
	 */
	public String getRel() {
		return rel;
	}

	/**
	 * 关联组件的设定。
	 * 
	 * @param pRel
	 *            关联组件
	 */
	public void setRel(String pRel) {
		this.rel = pRel;
	}

	/**
	 * 信息内容关键字的取得。
	 * 
	 * @return 信息内容关键字
	 */
	public String getMessageKey() {
		return messageKey;
	}

	/**
	 * 信息内容关键字的设定。
	 * 
	 * @param pMessageKey
	 *            信息内容关键字
	 */
	public void setMessageKey(String pMessageKey) {
		this.messageKey = pMessageKey;
	}

	/**
	 * ID的取得。
	 * 
	 * @return ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * ID的设定。
	 * 
	 * @param pId
	 *            ID
	 */
	public void setId(String pId) {
		this.id = pId;
	}

	/**
	 * 按钮按下处理的取得。
	 * 
	 * @return 按钮按下处理
	 */
	public String getOnclick() {
		return onclick;
	}

	/**
	 * 按钮按下处理的设定。
	 * 
	 * @param pOnclick
	 *            按钮按下处理
	 */
	public void setOnclick(String pOnclick) {
		this.onclick = pOnclick;
	}

	/**
	 * 只读的取得。
	 * 
	 * @return 只读
	 */
	public String getDisabled() {
		return disabled;
	}

	/**
	 * 只读的设定。
	 * 
	 * @param pDisabled
	 *            只读
	 */
	public void setDisabled(String pDisabled) {
		this.disabled = pDisabled;
	}

	/**
	 * 提示信息的取得。
	 * 
	 * @return 提示信息key
	 */
	public String getAltKey() {
		return altKey;
	}

	/**
	 * 提示信息的设定。
	 * 
	 * @param pAltKey
	 *            提示信息 key
	 */
	public void setAltKey(String pAltKey) {
		this.altKey = pAltKey;
	}

	/**
	 * 查找组 的取得。
	 * 
	 * @return 查找组
	 */
	public String getLookupGroup() {
		return lookupGroup;
	}

	/**
	 * 查找组的设定。
	 * 
	 * @param pLookupGroup
	 *            查找组
	 */
	public void setLookupGroup(String pLookupGroup) {
		this.lookupGroup = pLookupGroup;
	}

	/**
	 * 查找主键 的取得。
	 * 
	 * @return 查找主键
	 */
	public String getLookupPk() {
		return lookupPk;
	}

	/**
	 * 查找主键的设定。
	 * 
	 * @param pLookupPk
	 *            查找主键
	 */
	public void setLookupPk(String pLookupPk) {
		this.lookupPk = pLookupPk;
	}

	/**
	 * 样式的取得。
	 * 
	 * @return 样式
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * 样式的设定。
	 * 
	 * @param pStyle
	 *            样式
	 */
	public void setStyle(String pStyle) {
		this.style = pStyle;
	}

	/**
	 * 提示信息控制URL的取得。
	 * 
	 * @return 提示信息控制URL
	 */
	public String getTitleCondition() {
		return titleCondition;
	}

	/**
	 * 提示信息控制URL的设定。
	 * 
	 * @param pTitleCondition
	 *            提示信息控制URL
	 */
	public void setTitleCondition(String pTitleCondition) {
		this.titleCondition = pTitleCondition;
	}

	/**
	 * 目标分类的取得。
	 * 
	 * @return 目标分类
	 */
	public String getTargetType() {
		return targetType;
	}

	/**
	 * 目标分类的设定。
	 * 
	 * @param pTargetType
	 *            目标分类
	 */
	public void setTargetType(String pTargetType) {
		this.targetType = pTargetType;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	

	
}
