/** 
 * FileName MenuServiceImpl.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001  All Rights Reserved.
 */
package com.product.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.common.CommonConsts;
import com.product.dao.MenuMapper;
import com.product.dao.MenuRoleMapper;
import com.product.dao.RoleMapper;
import com.product.model.MenuModel;
import com.product.model.MenuRoleModel;
import com.product.model.RoleModel;
import com.product.service.MenuService;
import com.product.utils.HttpServletRequestUtil;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblUser;

/**
 * FileName MenuServiceImpl.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 菜单业务处理类
 */
@Service
public class MenuServiceImpl implements MenuService {

	/** 菜单表操作用DAO */
	@Autowired
	private MenuMapper menuMapper;


	/** 菜单角色权限表操作用DAO */
	@Autowired
	MenuRoleMapper menuRoleMapper;



	/** 角色表操作用DAO */
	@Autowired
	RoleMapper roleMapper;

	private String contextPath;

	/**
	 * 取得菜单信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuId
	 *            菜单ID
	 * @return MenuModel 菜单信息
	 * @throws 无
	 */
	public MenuModel findMenu(String menuId,TblUser loginUser) {

		// 菜单信息
		MenuModel menuModel;

		// 取得菜单信息
		menuModel = menuMapper.findMenu(menuId);

		// 设置菜单权限相关信息
		setMenuAuthInfo(menuModel,loginUser);

		// 返回菜单信息
		return menuModel;
	}

	/**
	 * 添加菜单
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuModel
	 *            菜单信息
	 * @return Menu 菜单信息
	 * @throws 无
	 */
	public void addMenu(MenuModel menuModel) {
		// 调用DAO执行菜单添加处理
		menuMapper.addMenu(menuModel);

		// 菜单权限相关处理
		processMenuAuthInfo(menuModel);
	}

	/**
	 * 编辑菜单
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuModel
	 *            菜单信息
	 * @return 无
	 * @throws 无
	 */
	public void modifyMenu(MenuModel menuModel) {

		// 调用DAO执行菜单更新处理
		menuMapper.modifyMenu(menuModel);

		// 菜单权限相关处理
		processMenuAuthInfo(menuModel);
	}

	/**
	 * 删除菜单
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuId
	 *            菜单ID
	 * @return 无
	 * @throws 无
	 */
	public void removeMenu(String menuId) {

		// 调用DAO执行菜单更新处理
		menuMapper.removeMenu(menuId);
		//
		removeAllMenuAuthInfo(menuId);
	}

	/**
	 * 取得菜单一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<MenuModel> 菜单一览
	 * @throws 无
	 */
	public List<MenuModel> getMenuList() {
		// 菜单一览
		List<MenuModel> listMenu;

		// 取得菜单一览
		listMenu = menuMapper.getMenuList();

		// 返回菜单一览
		return listMenu;
	}

	/**
	 * 查询菜单一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<MenuModel> 菜单一览
	 * @throws 无
	 */
	public List<MenuModel> searchMenuList(MenuModel menu) {
		// 菜单一览
		List<MenuModel> listMenu;

		// 取得菜单一览
		listMenu = menuMapper.searchMenuList(menu);

		// 返回菜单一览
		return listMenu;

	}

	/**
	 * 菜单唯一性检查
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuModel
	 *            菜单信息
	 * @return String 菜单唯一性检查结果 "true":通过 "false":不通过
	 * @throws 无
	 */
	public String checkMenuUnique(String menuId) {

		// 处理结果
		String processResult = CommonConsts.CHECK_RESULT_OK;
		// 菜单数
		int menuCount = 0;

		// 根据菜单登录ID取得菜单数
		menuCount = menuMapper.getMenuCountById(menuId);

		// 如果取得的菜单数大于0个，返回错误标识
		if (menuCount > 0) {
			return CommonConsts.CHECK_RESULT_NG;
		}

		// 返回处理结果标识
		return processResult;

	}

	/**
	 * 设置菜单权限相关信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuModel
	 *            菜单信息
	 * @return 无
	 * @throws 无
	 */
	private void setMenuAuthInfo(MenuModel menuModel,TblUser loginUser) {
		// 菜单ID
		String menuId = "";
		// 菜单角色权限列表
		List<RoleModel> roleList = null;

		if (menuModel == null) {
			return;
		}

		// 取得菜单ID
		menuId = menuModel.getMenuId();

		// 取得拥有菜单权限相关列表
		RoleModel roleModel=new RoleModel();
		roleModel.setMenuId(menuId);
		if (loginUser!=null){
			if(loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS) {// 纯商家
				roleModel.setCreateUser(loginUser.getUserId().toString());
			}else if(loginUser.getUserLevel()<=WanmaConstants.USER_LEVEL_ADMIN){
				roleModel.setUserLevel(loginUser.getUserLevel()+"");
			}
		}	
		roleList = roleMapper.getMenuRoleList1(roleModel);
		menuModel.setRoleList(roleList);
	}

	/**
	 * 菜单权限相关处理
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuModel
	 *            菜单信息
	 * @return 无
	 * @throws 无
	 */
	private void processMenuAuthInfo(MenuModel menuModel) {
		// 菜单角色权限列表
		List<MenuRoleModel> menuRoleList = null;

		menuRoleList = menuModel.getMenuRoleList();


		if (menuRoleList != null && menuRoleList.size() > 0) {

			//
			// 菜单部门权限追加/删除处理
			//
			for (MenuRoleModel menuRoleModel : menuRoleList) {

				// 处理分类
				String processType = menuRoleModel.getPrcessFlg();

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_ADD,
						processType)) {
					// 调用DAO执行菜单部门权限添加处理
					menuRoleMapper.addMenuRole(menuRoleModel);
				}

				if (StringUtils.equals(CommonConsts.PROCESS_FLG_DELETE,
						processType)) {
					// 调用DAO执行菜单部门权限添加处理
					menuRoleMapper.removeMenuRole(menuRoleModel);
				}
			}
		}

	}

	/**
	 * 删除菜单权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param menuId
	 *            菜单ID
	 * @return 无
	 * @throws 无
	 */
	private void removeAllMenuAuthInfo(String menuId) {


		// 生成菜单角色权限删除对象
		MenuRoleModel menuRoleModel = new MenuRoleModel();
		menuRoleModel.setMenuId(menuId);
		// 删除菜单下所有角色权限
		menuRoleMapper.removeMenuRole(menuRoleModel);


	}

	@Override
	public String getMenuListByRoleIdAndMenuIds(String roleId,String menuIds) {
		List<MenuModel> menuList=menuMapper.getCheckedMenuListByRoleIdAndMenuIds(roleId,menuIds);
		StringBuilder sb=new StringBuilder();
		String iconSkin="";
		String pId="";
		for(MenuModel menu:menuList){
			iconSkin=menu.getMenuType().equals("2")?"icon02":"icon01";
			if(StringUtils.isBlank(menu.getUrl())){
				iconSkin="";
			}
			pId=(StringUtils.isBlank(menu.getParentMenuId())?"0":menu.getParentMenuId());
			sb.append("{id:'"+menu.getMenuId()+"', pId:'"+pId+"',iconSkin:'"+iconSkin
					+"', name:'"+menu.getContents()+"', checked:"+(menu.getIsSelected()==1?"true":"false")+"},");
		}
		return "["+sb.substring(0,sb.length()-1)+"]";
	}

	@Override
	public List<MenuModel> getMenuListByRoleList(List<RoleModel> roleList) {
		String roleIds="";
		for(RoleModel role:roleList){
			roleIds+=role.getRoleId()+",";
		}
		List<MenuModel> menuList=menuMapper.getMenuListByRoleIds(StringUtils.removeEnd(roleIds, ","));
		return menuList;
	}

	@Override
	public String getMenuTree(List<MenuModel> menuList,boolean isLookUp) {
		String openType=isLookUp?"collapse":"collapse";
		StringBuilder strHtml=new StringBuilder("<ul class='tree treeFolder "+openType+"'>");
		if(isLookUp){
			strHtml.append("<li><a href='javascript:;' onclick=\"$.bringBack({id:'', name:'根菜单'})\">根菜单</a><ul>");
		}
		for(MenuModel menu:menuList){
			//递归无父节点的根节点
			if(StringUtils.isBlank(menu.getParentMenuId())){
				strHtml.append("<li>"+getMenuLinkStr(menu,isLookUp));
				writeMenuTree(menu,menuList, strHtml,isLookUp);
				strHtml.append("</li>");
			}
		}
		if(isLookUp){
			strHtml.append("</ul></li>");
		}
		strHtml.append("</ul>");
		return strHtml.toString();
	}

	private void writeMenuTree(MenuModel menu, List<MenuModel> menuList,
			StringBuilder strHtml,boolean isLookUp) {
		strHtml.append("<ul>");
		for(MenuModel child:menuList){
			if(child.getMenuType().equals("1")){//菜单权限构成树
				if(child.getParentMenuId().equals(menu.getMenuId())){
					strHtml.append("<li>"+getMenuLinkStr(child,isLookUp));
					//对无URL的菜单权限，进行递归
					if(child.getHasChild()>0){
						writeMenuTree(child,menuList,strHtml,isLookUp);
					}
					strHtml.append("</li>");	
					
				}
			}
			
		}
		strHtml.append("</ul>");
	}
	
	
	private String getMenuLinkStr(MenuModel menu,boolean isLookUp){
		String tempStr="";
		if(isLookUp){
			tempStr="href='javascript:;' onclick=\"$.bringBack({id:'"+menu.getMenuId()+"',"
					+ " name:'"+menu.getContents()+"'})\"";
		}else{
			if(StringUtils.isNotBlank(menu.getUrl())){
			tempStr=" href='"+getContextPath()+menu.getUrl()+"' target='navTab' rel='"+menu.getRel()+"'";
			}
		}
		return "<a "+tempStr+">"+menu.getContents()+"</a>";
	}

	
	public String getContextPath() {
		if(contextPath==null){
			contextPath=HttpServletRequestUtil.getHttpRequest().getContextPath();
		}
		return contextPath;
	}

	@Override
	public List<MenuModel> getMenuList(MenuModel menu) {
		return menuMapper.searchMenuList(menu);
	}

	@Override
	public Long getMenuListCount(MenuModel menu) {
		return menuMapper.searchMenuListCount(menu);
	}

	@Override
	public MenuModel getMenu(String menuId) {
		return menuMapper.findMenu(menuId);
	}
}
