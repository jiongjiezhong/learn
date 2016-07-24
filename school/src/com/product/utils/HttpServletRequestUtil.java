/**
 * FileName:ApplicationContextUtil.java
 * Author: Administrator
 * Create: 2014年7月7日
 * Last Modified: 2014年7月7日
 * Version: V1.0 
 */
package com.product.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * HttpServletRequest工具类
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月7日
 */
public class HttpServletRequestUtil {

	public static HttpServletRequest getHttpRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}

	public static HttpSession getHttpSession() {
		HttpSession session = getHttpRequest().getSession();
		return session;
	}

	public static String getSessionId() {
		String sessionId = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getSessionId();
		return sessionId;
	}

}
