package com.product.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class BaseFilter
 */
public class AppAcessFilter implements Filter {
	Logger log = Logger.getLogger(this.getClass());
	private String[] security_uri_array;

	/**
	 * Default constructor.
	 */
	public AppAcessFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 设置编码
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		boolean needSecurity = false;
		request.setAttribute("webroot", req.getContextPath());
		// 判断请求URI是否在配置的需要登录验证的URI列表中
		for (int i = 0; i < security_uri_array.length; i++) {
			String sUri = security_uri_array[i];
			if ("".equals(sUri))
				continue;
			if (req.getRequestURI().startsWith(
					req.getContextPath() + sUri.replace("*", ""))) {
				needSecurity = true;
				break;
			}
		}
		if (!needSecurity) {
			// 不需要验证的请求
			chain.doFilter(request, response);
		} else {

			try {
				// 需要监视的请求
				chain.doFilter(request, response);
			} catch (Exception e) {
				log.error(e.getLocalizedMessage());
				// 出错时跳转
				chain.doFilter(request, response);
				resp.sendRedirect(req.getContextPath()
						+ "/error/app_request_error.jsp");
			}
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// 获取需要登录验证的URI
		String security_uri_conf = fConfig.getInitParameter("SECURITY_URI");
		// URI以逗号分隔
		security_uri_array = security_uri_conf.split(",");
	}

}
