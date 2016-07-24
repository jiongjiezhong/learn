package com.wanma.common;

import java.util.List;
import java.util.Map;

import com.product.model.RoleModel;
/**
 * @Description: 万马公共CODE定义
 * @author songjf
 * @createTime：2015-3-15 下午08:36:31
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public class WanmaConstants {

	/** 图片分类定义 */
	public static String MULTI_TYPE_USER_AVATAR = "userAvatar";// 用户头像
	public static String MULTI_TYPE_PRODUCT_IMAGE = "proImage";// 新能源商品列表图片
	public static String MULTI_TYPE_PRODUCT_DETAIL_IMAGE = "proDetailImage";// 新能源商品详细图片
	public static String MULTI_TYPE_PRODUCT_COMMENT_IMAGE = "proCommentImage";// 评论图片
	public static String MULTI_TYPE_TOTAL_Home_IMAGE = "homeImage";// 首页广告栏图片
	public static String MULTI_TYPE_ID_CARD_IMAGE = "IdCardImage";// 身份证附件
	public static String MULTI_UNITTYPE_ID_CARD_IMAGE = "IdUnitCardImage";// 纯商家身份证附件
	public static String MULTI_TYPE_UNIT_IMAGE = "UnitImage";// 个体商家图片
	public static String MULTI_TYPE_LICENSE_IMAGE = "LicenseImage";// 营业执照
	public static String MULTI_TYPE_AFFAIRS_IMAGE = "AffairsImage";// 税务登记证
	public static String MULTI_TYPE_ACCREDIT_IMAGE = "AccreditImage";// 授权证明
	
	public static String MULTI_TYPE_ELECTRICT_LIST_IMAGE = "electricListImage";// 电桩列表图片
	public static String MULTI_TYPE_ELECTRICT_DETAIL_IMAGE = "electricDetailImage";// 电桩详情图片
   public static String MULTI_TYPE_ELECTRICT_LIST_SUBGRAPH = "electricListSubgraph";// 电桩列表子图片
	public static String MULTI_TYPE_ELECTRICT_DETAIL_SUBGRAPH = "electricDetailSubgraph";// 电桩详情子图片
	
	public static String MULTI_TYPE_ELECTRICT_PUBLISH_IMG = "epPublishImage"; //电桩发布
	public static String MULTI_TYPE_PARTNER_APPLY_IMG = "partnerApplyImage"; //合作伙伴申请页面图片
	
	public static String MULTI_TYPE_CAR_FIX_IMAGE = "carfix";//车辆维修图片
	public static String MULTI_TYPE_POWER_LIST_IMAGE = "powerListImage";// 电站列表图片
	public static String MULTI_TYPE_POWER_DETAIL_IMAGE = "powerDetailImage";// 电站详情图片

	public static String MULTI_LIST_IMAGE = "ListImage";// 商品管理的列表图片
	public static String MULTI_DETAIL_IMAGE = "DetailImage";// 商品管理的列表图片
	public static String IMAGE_TEMP = "temp";// 图片临时区
	public static String USER_NORMALUSE = "userAvatar";// 普通用户头像
	public static String DYNAMIC = "dynamic";// 动态首图
	public static String SENSITIVE_WORD_LIST="sensitiveWordList";//敏感词列表
	/* 安卓ios安装包 */
	public static String MULTI_TYPE_APK = "apk";// 安卓获取ios安卓包

	/** 评论类型 */
	public static String PRODUCT_COMMENT_MALL = "2";// 用户头像

	/** 枪头状态 */
	public static int ELECTRICPILEHEAD_FREE = 0;// 空闲中
	public static int ELECTRICPILEHEAD_APPOINTMENT = 3;// 预约中
	public static int ELECTRICPILEHEAD_BATTERY = 6;// 充电中
	public static int ELECTRICPILEHEAD_STOP = 9;// 停用

	/** 预约状态 */
	public static int BESPOKE_CANCEL = 1;// 取消预约
	public static int BESPOKE_END = 2;// 结束预约
	public static int BESPOKE_CONTRACT = 3;// 续预约
	public static int BESPOKE_ING = 4;// 预约中
	public static int BESPOKE_AFFIRM_ING = 5;// 预约确认中
	public static int BESPOKE_AFFIRM_FAIL = 6;// 预约失败

	/** 订单状态 */
	public static int ORDER_STATUS_WAIT = 1;// 待支付
	public static int ORDER_STATUS_SUCCESS = 2;// 支付成功
	public static int ORDER_STATUS_DONE = 3;// 操作完成
	public static int ORDER_STATUS_DELETE = 0;// 订单删除
	public static int ORDER_STATUS_TOBE_SEND_GOODS = 4;// 待发货
	public static int ORDER_STATUS_SEND_GOODS = 5;// 已发货

	/** 订单跟踪状态 */
	public static int ORDER_TRACK_BUY = 1;// 购买
	public static int ORDER_TRACK_PAY = 2;// 付款
	public static int ORDER_TRACK_APPEMENT = 3;// 预约安装
	public static int ORDER_TRACK_RECEIPT = 4;// 收货
	public static int ORDER_TRACK_INSTALL = 5;// 安装中
	public static int ORDER_TRACK_FINISH = 6;// 完成

	/**
	 * 电桩配置信息 配置类型 1-电桩状态 2-电桩枪口状态 3-电桩充电方式 4-电桩类型 5-电桩枪口连接方式 6-电桩额定功率 7-最大电流
	 * 8-桩体用途 9-搜索半径 10-支持预约
	 */
	public static int ELCTRC_CONFGURTN_STATE = 1;// 电桩状态
	public static int ELCTRC_CONFGURTN_MUZZLE = 2;// 电桩枪口状态
	public static int ELCTRC_CONFGURTN_CHARGE = 3;// 电桩充电方式
	public static int ELCTRC_CONFGURTN_TYPE = 4;// 电桩类型
	public static int ELCTRC_CONFGURTN_CONNECTOR = 5;// 电桩枪口连接方式
	public static int ELCTRC_CONFGURTN_POWER = 6;// 电桩额定功率
	public static int ELCTRC_CONFGURTN_ELECTRICITY = 7;// 最大电流
	public static int ELCTRC_CONFGURTN_USE = 8;// 桩体用途
	public static int ELCTRC_CONFGURTN_RADIO = 9;// 搜索半径
	public static int ELCTRC_CONFGURTN_MAKE = 10;// 支持预约

	public static int CONFIG_PARAMETER_EFFECTIVE = 0;// 启用
	public static int CONFIG_PARAMETER_INVALID = 1;// 禁用

	/**
	 * 充电交易类型*
	 */
	public static int CHARGE_TRANSTYPE_BROKEN = 1;// 断网上传
	public static int CHARGE_TRANSTYPE_ONLINE = 2;// 断网上传
	
	/**
	 * 充电时信息 工作状态*
	 */
	public static int CHARGEING_WORK_OFFLINE = 0;// 离线
	public static int CHARGEING_WORK_FAULT = 1;// 故障
	public static int CHARGEING_WORK_STANDBY = 2;// 待机
	public static int CHARGEING_WORKING = 3;// 工作
	public static int CHARGEING_WORK_UNDERVOLTAGE = 4;// 欠压故障
	public static int CHARGEING_WORK_OVERVOLTAGE = 5;// 过压故障
	public static int CHARGEING_WORK_FLOW = 6;// 过电流故障
	
	/**
	 * 充电时信息 连接确认开关状态*
	 */
	public static int CHARGEING_CONNECT_OPEN = 0;// 关
	public static int CHARGEING_CONNECT_CLOSE = 1;// 开
	
	/**
	 * 万马用户级别
	 *  备注：1：超级管理员 2：系统管理员 3:商家用户 5：个体商家用户
	 */
	/** 超级管理员**/
	public static int USER_LEVEL_SUPER = 1;
	/**系统管理员**/
	public static int USER_LEVEL_ADMIN = 2;
	/**商家用户 **/
	public static int USER_LEVEL_BUSINESS = 3;
	/** 个体商家用户**/
	public static int USER_LEVEL_BUSINESS_NORMAL = 5;
	/** 普通用户**/
	public static int USER_LEVEL_NORMAL = 6;
	
	public static int USER_STATUS_NORMAL=1;//正常
	public static int USER_STATUS_FROZEN=2;//冻结
	public static int USER_STATUS_DELETE=3;//删除
	/**
	 * 图片压缩 宽 高 130,100
	 */
	public static int PICTRUE_W =130; 
	public static int PICTRUE_H = 100;
	/**
	 * 用户图片压缩 宽 高 130,100
	 */
	public static int USER_HEAD_PICTRUE_W =200; 
	public static int USER_HEAD_PICTRUE_H = 200;
	
	public static int PICTRUE_W_1280=1280;
	public static int PICTRUE_H_720=720;
	
	/**
	 * 生成电桩编号时电桩类型
	 */
	public static String ELECTRIC_TYPE_DIRECT = "0";// 直流
	public static String ELECTRIC_TYPE_COMMUNICATION = "1";// 交流
	
	public static Map<String,Object> provinceMap;
	public static Map<String,Object> cityMap;
	public static Map<String,Object> areaMap;
	public static Map<String,List<String>> provinceCityMap;
	public static Map<String,List<String>> cityAreaMap;
	
	public static RoleModel businessNormalRole;//个人商家角色
	
	public static String WANMA_PHONE="400-085-0006";//万马联系电话
	public static String ERROR_PAGE="admin/error_auth";
	public static StringBuilder areaSb;
	
	public static int CONFIG_PILE_TYPE=1;
	public static int CONFIG_PILE_POWERUSE=2;
	public static int CONFIG_PILE_CHARGING_MODE=3;
	public static int CONFIG_PILE_POWER_SIZE=4;
	public static int CONFIG_PILE_POWER_INTERFACE=5;
	public static int CONFIG_PILE_POWER_STATUS=6;
	public static int CONFIG_SEARCH_RADIUS=7;
	public static int CONFIG_HEAD_STATUS=8;
	public static int CONFIG_PILE_MAKER=11;
	public static int CONFIG_PILE_STATE=12;
	public static int CONFIG_PILE_BINDING=13;
	public static int CONFIG_PILE_ISAPPOINT=14;
	public static int CONFIG_PILE_PAYSTYLE=15;
	public static int CONFIG_ERROR_TYPE=29;
	
	
	
}
