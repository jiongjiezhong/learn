/**
 * FileName:MenuUtil.java
 * Author: Administrator
 * Create: 2014年7月10日
 * Last Modified: 2014年7月10日
 * Version: V1.0 
 */
package com.product.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.util.StringUtils;
import com.product.common.CommonConsts;
import com.product.common.ProcessInfoCommon;
import com.product.model.MenuModel;
import com.product.model.MenuRoleModel;
import com.product.model.RoleModel;

/**
 * 部门工具类
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月10日
 */
public class MenuUtil {

	

	

	

	/**
	 * 判断角色列表中是否已存在角色信息
	 * 
	 * @param targetList
	 *            对象角色列表
	 * @param targetRoleModel
	 *            对象角色
	 * @return 存在标识
	 */
	public static boolean containsRole(List<RoleModel> targetList,
			RoleModel targetRoleModel) {

		// 角色ID
		String targetRoleId = "";

		//
		// 对象角色为空，返回不存在标识
		//
		if (targetRoleModel == null) {
			// 返回不存在标识
			return false;
		}

		// 取得角色ID
		targetRoleId = targetRoleModel.getRoleId();

		// 返回存在标识
		return containsRole(targetList, targetRoleId);

	}

	/**
	 * 判断角色列表中是否已存在角色信息
	 * 
	 * @param targetList
	 *            对象角色列表
	 * @param targetRoleId
	 *            对象角色Id
	 * @return 存在标识
	 */
	public static boolean containsRole(List<RoleModel> targetList,
			String targetRoleId) {
		// 存在标识
		boolean containRole = false;

		//
		// 角色列表或者对象角色为空，返回不存在标识
		//
		if (targetList == null || targetList.size() < 1
				|| StringUtil.isEmpty(targetRoleId)) {
			// 返回不存在标识
			return false;
		}

		for (RoleModel roleModel : targetList) {
			// 取得角色ID
			String roleId = roleModel.getRoleId();

			if (StringUtils.equals(targetRoleId, roleId)) {
				containRole = true;
				break;
			}

		}

		// 返回存在标识
		return containRole;

	}

	/**
	 * 合并处理对象角色列表
	 * 
	 * @param addRoleList
	 *            追加对象角色列表
	 * @param deleteRoleList
	 *            删除对象角色列表
	 * @return List<RoleModel> 合并处理对象角色列表
	 */
	public static List<MenuRoleModel> mergeProcessRole(
			List<RoleModel> addRoleList, List<RoleModel> deleteRoleList,
			MenuModel menuModel) {
		// 菜单ID
		String menuId = "";

		// 处理对象角色列表
		List<MenuRoleModel> processRole = new ArrayList<MenuRoleModel>();

		if ((addRoleList == null || addRoleList.size() == 0)
				&& (deleteRoleList == null || deleteRoleList.size() == 0)
				|| ObjectUtil.isEmpty(menuModel)) {
			return null;
		}

		// 取得菜单ID
		menuId = menuModel.getMenuId();

		//
		// 处理追加对象角色
		//
		if (addRoleList != null && addRoleList.size() > 0) {
			for (RoleModel roleAdd : addRoleList) {

				if (!containsRole(deleteRoleList, roleAdd)) {
					// 角色角色对象
					MenuRoleModel menuRoleModel = new MenuRoleModel();

					// 处理分类：追加
					menuRoleModel.setPrcessFlg(CommonConsts.PROCESS_FLG_ADD);
					// 菜单ID设置
					menuRoleModel.setMenuId(menuId);
					// 角色ID设置
					menuRoleModel.setRoleId(roleAdd.getRoleId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(menuRoleModel);

					// 加入到列表
					processRole.add(menuRoleModel);
				}
			}
		}

		//
		// 处理追加对象角色
		//
		if (deleteRoleList != null && deleteRoleList.size() > 0) {
			for (RoleModel roleDelete : deleteRoleList) {

				if (!containsRole(addRoleList, roleDelete)) {
					// 角色角色对象
					MenuRoleModel menuRoleModel = new MenuRoleModel();

					// 处理分类：删除
					menuRoleModel.setPrcessFlg(CommonConsts.PROCESS_FLG_DELETE);
					// 菜单ID设置
					menuRoleModel.setMenuId(menuId);
					// 角色ID设置
					menuRoleModel.setRoleId(roleDelete.getRoleId());
					// 设置创建者用户信息
					ProcessInfoCommon.setCreateUserInfo(menuRoleModel);

					// 加入到列表
					processRole.add(menuRoleModel);
				}
			}
		}

		// 返回处理对象角色列表
		return processRole;
	}

	

	

	
}
