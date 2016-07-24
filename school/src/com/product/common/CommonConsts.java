/** 
 * FileName CommonConsts.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001  All Rights Reserved.
 */

package com.product.common;

/**
 * FileName CommonConsts.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 共有变量定义
 */

public class CommonConsts {

	/** 超级用户区分 */
	public static String SUPPER_USER_LEVEL_ID = "1";

	/** 系统设置文件 */
	public static String PRO_FILE_SYSTEM_SETTING = "system.properties";

	/** 数据库连接设置文件 */
	public static String PRO_FILE_JDBC_SETTING = "jdbc.properties";

	/** 信息配置文件 */
	public static String PRO_FILE_MESSAGE_PROPERTIES = "message.properties";

	/** 第三方登录设置文件 */
	public static String REMOTE_OAUTH_PROPERTIES = "remote-oauth.properties";

	/** 系统设置文件:提前预定天数 */
	public static String PRO_KEY_BOOK_AHEAD_DAYS = "book.ahead.days";

	/** MAP KEY:提前预定天数 */
	public static String MAP_KEY_BOOK_AHEAD_DAYS = "bookAheadDays";

	/** 系统设置文件:资源路径 */
	public static String PRO_KEY_STORAGE_PATH_RES = "storage.path.res";

	/** 系统设置文件:正式文件存放路径 */
	public static String PRO_KEY_STORAGE_REL_PATH = "storage.path.real.file";

	/** 系统设置文件:图片服务器上传地址*/
	public static String PICTURE_SERVICE_UPLOADURL = "picture.service.uploadUrl";
	/** 系统设置文件:图片服务器浏览地址*/
	public static String PICTURE_SERVICE_SCANURL = "picture.service.scanUrl";
	/** 系统设置文件:临时文件存放路径 */
	public static String PRO_KEY_STORAGE_TEMP_FILE = "storage.path.temp.file";

	/** 系统设置文件:用户图片存放路径 */
	public static String PRO_KEY_STORAGE_USER_IMAGE = "storage.path.user.image";

	/** 权限设置文件 */
	public static String DATA_AUTH_SETTING_XML = "data-auth-setting.xml";
	/** JSON返回内容限制设置文件 */
	public static String JSON_PUBLISH_SETTING_XML = "json-publish-setting.xml";

	/** 数据库连接设置文件:URL */
	public static String PRO_KEY_DB_URL = "jdbc.url";
	/** 数据库连接设置文件:驱动 */
	public static String PRO_KEY_DB_DRIVER = "jdbc.driver";
	/** 数据库连接设置文件:用户名 */
	public static String PRO_KEY_DB_USERNAME = "jdbc.username";
	/** 数据库连接设置文件:密码 */
	public static String PRO_KEY_DB_PASSWORD = "jdbc.password";
	/** 数据库连接设置文件:URL */
	public static String MAP_KEY_DB_URL = "jdbcUrl";
	/** 数据库连接设置文件:驱动 */
	public static String MAP_KEY_DB_DRIVER = "jdbcDriver";
	/** 数据库连接设置文件:用户名 */
	public static String MAP_KEY_DB_USERNAME = "jdbcUsername";
	/** 数据库连接设置文件:密码 */
	public static String MAP_KEY_DB_PASSWORD = "jdbcPassword";

	/** 系统设置文件:用户默认密码 */
	public static String MAP_KEY_DEFAULT_USER_PASSWORD = "defaultPassword";
	/** MAP KEY:用户默认密码 */
	public static String PRO_KEY_DEFAULT_USER_PASSWORD = "user.default.password";

	/** 用户检查状态区分：登录正确 */
	public static String USER_STATUS_CHECK_OK = "001";
	/** 用户检查状态区分：不存在用户 */
	public static String USER_STATUS_CHECK_NO_USER = "002";
	/** 用户检查状态区分：不存在用户 */
	public static String USER_STATUS_CHECK_ERROR_PASSWORD = "003";

	/** 检查结果：通过 */
	public static String CHECK_RESULT_OK = "true";
	/** 检查结果：不通过 */
	public static String CHECK_RESULT_NG = "false";

	/** 处理结果标识：处理成功 */
	public static String PROCESS_STATUS_OK = "001";
	/** 处理结果标识：处理失败 */
	public static String PROCESS_STATUS_NG = "002";

	/** 商品分类：商品 */
	public static String PRODUCT_TYPE_PRODUCT = "001";
	/** 商品分类：券 */
	public static String PRODUCT_TYPE_ETICKET = "002";

	/** 权限查询用MAP关键字：用户ID */
	public static String AUTH_MAP_KEY_USER_ID = "userId";

	/** 权限查询用MAP关键字：功能ID */
	public static String AUTH_MAP_KEY_ACTION_ID = "actionId";

	/** 默认密码：系统配置文件未设置时，"123456" */
	public static String DEFAULT_PASSWORD_STRING = "123456";

	/** 追加删除标识：追加 */
	public static String PROCESS_FLG_ADD = "add";
	/** 追加删除标识：删除 */
	public static String PROCESS_FLG_DELETE = "delete";

	/** 处理标识：部门用户 */
	public static String USER_PROCESS_TYPE_DEPARTMENT = "department";
	/** 处理标识：角色用户 */
	public static String USER_PROCESS_TYPE_ROLE = "role";
	/** 处理标识：职位用户 */
	public static String USER_PROCESS_TYPE_POST = "post";

	/** 处理标识：菜单部门权限 */
	public static String ROLE_PROCESS_TYPE_MENU = "menuRole";
	/** 处理标识：菜单部门权限 */
	public static String POST_PROCESS_TYPE_MENU = "menuPost";
	/** 处理标识：菜单部门权限 */
	public static String DEPARTMENT_PROCESS_TYPE_MENU = "menuDept";

	/** 处理标识：画面功能部门权限 */
	public static String ROLE_PROCESS_TYPE_ACTION = "actionRole";
	/** 处理标识：画面功能部门权限 */
	public static String POST_PROCESS_TYPE_ACTION = "actionPost";
	/** 处理标识：画面功能部门权限 */
	public static String DEPARTMENT_PROCESS_TYPE_ACTION = "actionDept";

	/** 处理标识：App接口部门权限 */
	public static String ROLE_PROCESS_TYPE_APP = "appApiRole";
	/** 处理标识：App接口部门权限 */
	public static String POST_PROCESS_TYPE_APP = "appApiPost";
	/** 处理标识：App接口部门权限 */
	public static String DEPARTMENT_PROCESS_TYPE_APP = "appApiDept";

	/** 多媒体文件分类定义:用户头像 */
	public static String MULTI_TYPE_USER = "userAvatar";
	/** 多媒体文件分类定义:用户二维码 */
	public static String MULTI_TYPE_USER_QR_CODE = "userQRCode";
	/** 多媒体文件分类定义:公司 */
	public static String MULTI_TYPE_COMPANY = "company";
	

	/** 用户分类:系统用户 */
	public static String USER_TYPE_SYSTEM = "SYS";
	/** 用户分类:APP注册用户 */
	public static String USER_TYPE_PRE_APP = "APP";
	/** 用户分类:商家用户 */
	public static String USER_TYPE_PRE_POS = "POS";
	/** 用户分类:物业管理用户 */
	public static String USER_TYPE_PRE_CPM = "CPM";

	/** 编号生成:图片ID */
	public static String SEQUENCE_MUTI = "SEQUENCE_MUTI";
	/** 编号生成:公司ID */
	public static String SEQUENCE_COMP = "SEQUENCE_COMP";

	/** 删除标识:未删除 */
	public static String DELETE_FLAG_NO = "0";
	/** 删除标识:删除 */
	public static String DELETE_FLAG_YES = "1";

	/** 启用标识:未启用 */
	public static String ACTIVE_FLAG_NO = "0";
	/** 启用标识:启用 */
	public static String ACTIVE_FLAG_YES = "1";

	/** 发布环境URL */
	public static String DEPLOY_URL = "deploy.url";

	/** 真实路径 */
	public static String REAL_FILE = "storage.path.real.file";

	/** 公司分类:系统公司 */
	public static String COMPANY_TYPE_SYSTEM = "001";
	/** 公司分类:物业公司 */
	public static String COMPANY_TYPE_ESTATE = "002";

	/** 验证码有效时间 */
	public static Long AUTHCODE_EFFECTIVE_TIME = 60l;

}
