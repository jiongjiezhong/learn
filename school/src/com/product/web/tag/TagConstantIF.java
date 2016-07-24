/**
 * FileName:TagConstantIF.java
 * Author: Administrator
 * Create: 2014年6月30日
 * Last Modified: 2014年6月30日
 * Version: V1.0 
 */
package com.product.web.tag;

/**
 * 自定义标签公共接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月30日
 */
public interface TagConstantIF {

	/** 是否接受权限控制：接受 */
	public static boolean IS_UNDER_CONTROL_YES = true;

	/** 是否接受权限控制：不接受 */
	public static boolean IS_UNDER_CONTROL_NO = false;

	/** 按钮class：不可用 */
	public static String BUTTON_DISABLE_CLASS = "buttonDisabled";

	/** 按钮class：可用 */
	public static String BUTTON_ACTIVE_CLASS = "buttonActive";

	/** 按钮class：正常 */
	public static String BUTTON_NORMAL_CLASS = "button";

	/** 按钮class */
	public static String BUTTON_CLASS = "buttonContent";
}
