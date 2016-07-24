package com.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.product.model.MenuModel;
import com.product.model.common.TreeModel;
import com.product.service.MenuService;
import com.product.service.RoleService;
import com.product.utils.MenuTreeUtil;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblUser;

@Controller
public class MainController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/admin/index")
	public String index(HttpServletRequest request, Model model) {
		TblUser user=SessionMgr.getWebUser(request);
		if(user==null)return "login";
		// 登录用户信息
		TblUser loginUser = null;

		// 将用户信息放到会话中
		loginUser =SessionMgr.getWebUser(request);
		//加载刷新用户角色和菜单，感觉有点凶~~
		roleService.initRolesAndMenus(request);
		List<MenuModel> menuList=SessionMgr.getUserMenus(request);
		/*if(ObjectUtil.isEmpty(menuList)){
			roleService.initRolesAndMenus(request);
		}*/
		String strHtml =menuService.getMenuTree(menuList,false);

		// 将用户信息设置到画面显示对象
		model.addAttribute("menuTreeModel", strHtml);

		// 将登录用户信息设置到画面显示对象
		model.addAttribute("loginUser", loginUser);

		List<TblUser> userList = new ArrayList<TblUser>();
		userList.add(loginUser);

		return "admin/index";
	}

	private String getMenuTree(boolean isMenu) {
		String strHtml = "";
		MenuTreeUtil treeUtil = new MenuTreeUtil();
		List<TreeModel> treeList = null;

		treeList = treeUtil.getMenuTreeData(false);
		treeUtil.setHref("menu/editMenu.do");
		treeUtil.setRel("menuBox");

		treeUtil.setIsPutParameter(true);
		treeUtil.setTarget("navTab");
		strHtml = treeUtil.writeMenuTree(treeList);

		return strHtml;
	}

	@RequestMapping("/login")
	public String homePage() {
		return "login";
	}

	@RequestMapping("/reset")
	public String reset() {
		return "reset";
	}

	@RequestMapping("/simpleLogin")
	public String simpleLogin() {
		return "/simple_login";
	}
}
