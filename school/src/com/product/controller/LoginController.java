package com.product.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.product.common.MessageManager;
import com.product.model.common.DwzAjaxResult;
import com.product.service.MenuService;
import com.product.service.RoleService;
import com.product.service.TblUserService;
import com.product.utils.JsonObject;
import com.product.utils.MD5Util;
import com.product.utils.StringUtil;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblUser;

@Controller
public class LoginController {

	/** 用户业务处理对象 *//*
	@Autowired
	private UserService userService;*/
	@Autowired
	private TblUserService userService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;
	
	/** 日志文件生成器 */
	private static Logger log = Logger .getLogger(LoginController.class);
	
	/**
     * 跳转到登陆页面
     */
    @RequestMapping( value = "/tologin", method = RequestMethod.GET )
    public String toLogin()
    {
        return "login";
    }
	
	
	/**
	 * 用户登录处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param username
	 *            用户名
	 * @param passwd
	 *            密码
	 * @param session
	 *            会话
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/tologin", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username,
			@RequestParam("passwd") String passwd, HttpSession session,HttpServletRequest request,
			Model model) {
		// 登录用户信息
		TblUser loginUser = null;

		MessageManager messageManger = MessageManager.getMessageManager();
		// 错误信息
		String errorMessage = "";

		// 用户名设置
		model.addAttribute("username", username);
		
		// 未输入用户名
		if (StringUtil.isEmpty(username)) {
			errorMessage = messageManger.getMessage("error.msg.empty.user");
			model.addAttribute("erMessage", errorMessage);
			
			// 跳转至登录页面
			return "login";
			// 未输入密码
		} else if (StringUtil.isEmpty(passwd)) {
			errorMessage = messageManger.getMessage("error.msg.empty.password");
			model.addAttribute("erMessage", errorMessage);
			return "login";
		}
		
		TblUser tempUser=new TblUser();
		tempUser.setUserAccount(username);
		tempUser.setUserPassword(passwd);
		try {
			loginUser = userService.findLoginUser(tempUser);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if (loginUser==null) {
			errorMessage = messageManger.getMessage("error.msg.invalid.login");
			model.addAttribute("erMessage", errorMessage);
			return "login";
		}else if(!tempUser.getUserAccount().equals(loginUser.getUserAccount())){
			model.addAttribute("erMessage", "用户名错误，请区分大小写");
			return "login";
		} else if(!loginUser.getUserPassword().equals(tempUser.getUserPassword())){
			errorMessage = messageManger.getMessage("error.msg.invalid.password");
			model.addAttribute("erMessage", errorMessage);
			return "login";
		}	
		

		// 将用户信息放到会话中
		SessionMgr.addWebUser(request, loginUser);
		roleService.initRolesAndMenus(request);
		/******* 以下操作为实现唯一登录-begin *********/
		// 保存用户登录
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sessionId", session.getId());
		map.put("ip", getIpAddr(request));
		map.put("date", new Date());
		// 将用户信息添加到application对象中
		ServletContext application = session.getServletContext();
		application.setAttribute(loginUser.getUserId()+"", map);

		/******* 以下操作为实现唯一登录-end *********/
//		CommitLog commitLog=new CommitLog();
//		commitLog.setLogName(JudgeNullUtils.nvlStr(loginUser.getUserName()));
//		commitLog.setIpAddress(getIpAddr(request));
//		commitLog.setLogContent("登录成功");
//		commitLog.setCreatedate(new Date());
//		commitLog.setUpdatedate(new Date());
//		commitLogDao.insert(commitLog);
		// 跳转至管理员主页面
		return "redirect:/admin/index.do";
	}

	/**
	 * 用户登录处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param username
	 *            用户名
	 * @param passwd
	 *            密码
	 * @param session
	 *            会话
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/toSimplelogin", method = RequestMethod.POST)
	@ResponseBody
	public String toSimplelogin(@RequestParam("username") String username,
			@RequestParam("passwd") String passwd, HttpServletRequest request,
			Model model) {

		// 登录用户信息
		TblUser loginUser = null;
		// 处理结果信息
		DwzAjaxResult dwzResult;

		MessageManager messageManger = MessageManager.getMessageManager();
		// 错误信息
		String errorMessage = "";

		// 用户名设置
		model.addAttribute("username", username);

		// 未输入用户名
		if (StringUtil.isEmpty(username)) {
			errorMessage = messageManger.getMessage("error.msg.empty.user");
			// 设置处理结果信息
			dwzResult = new DwzAjaxResult("300", errorMessage, "", "", "");
			// 返回错误信息
			return new JsonObject(dwzResult).toString();
			// 未输入密码
		} else if (StringUtil.isEmpty(passwd)) {
			errorMessage = messageManger.getMessage("error.msg.empty.password");
			// 设置处理结果信息
			dwzResult = new DwzAjaxResult("300", errorMessage, "", "", "");
			// 返回错误信息
			return new JsonObject(dwzResult).toString();
		}
		
		// 验证登录用户
		TblUser tempUser=new TblUser();
		tempUser.setUserAccount(username);
		tempUser.setUserPassword(passwd);
		try {
			loginUser = userService.findLoginUser(tempUser);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		if (loginUser==null) {
			errorMessage = messageManger.getMessage("error.msg.invalid.login");
			// 设置处理结果信息
			dwzResult = new DwzAjaxResult("300", errorMessage, "", "", "");
			// 返回错误信息
			return new JsonObject(dwzResult).toString();
		}else if(!loginUser.getUserPassword().equals(MD5Util.MD5(tempUser.getUserPassword()))){
			errorMessage = messageManger.getMessage("error.msg.invalid.password");
			// 设置处理结果信息
			dwzResult = new DwzAjaxResult("300", errorMessage, "", "", "");
			// 返回错误信息
			return new JsonObject(dwzResult).toString();
		}	
		
		// 设置成功并返回用户一览画面信息
		dwzResult = new DwzAjaxResult("200", "重新登录成功", "", "closeCurrent", "");
		
		// 将用户信息放到会话中
		SessionMgr.addWebUser(request,loginUser);
		roleService.initRolesAndMenus(request);

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 用户登出处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param session
	 *            会话
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		// 清空会话信息
		SessionMgr.removeWebUser(request);
		// 跳转至管理员登录页面
		return "redirect:/login.do";
	}
	
	/**
     * 获取IP地址
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    
}
