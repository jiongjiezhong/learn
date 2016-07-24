package com.wanma.model;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

import com.product.model.common.BasicListAndMutiFile;

public class TblUser extends BasicListAndMutiFile {
	private static final long serialVersionUID = 7452092510137346486L;
	private Long userId;// 用户ID
	private String userAccount;// 用户帐号(普通用户帐号为手机号)
	private String userPassword;// 用户密码
	private Integer userLevel;// 用户等级1：超级管理员 2：系统管理员 3:纯商家用户 5:个体商家用户 6:普通用户
	private Integer userStatus;// 用户状态 1:正常 2:冻结 3:删除
	private String normName;// 普通用户昵称
	private String normRealName;// 普通用户真实姓名
	private Integer normStatus;// 普通用户状态(1：初始化 2 ：个体商家待审批 3：已审批通过)
	private Integer normRegisteType;// 普通用户注册来源(1：管理后台 2：web 3：android 4：ios)
	private BigDecimal normAccountBalance;// 普通用户帐户余额
	private String normPayPassword;// 普通用户支付密码
	private String normDeviceId;// 普通用户登录设备ID
	private String normEmail;// 普通用户邮箱
	private String normAddress;// 普通用户地址
	private String normIdCard;// 普通用户身份证号
	private Integer normSex;// 普通用户性别 (1:男 2：女)
	private String normBirthday;// 普通用户生日
	private Integer normIntegrate;// 普通用户积分
	private Integer normCarCompanyId;// 普通用户汽车品牌ID
	private Integer normCarTypeId;// 普通用户汽车车型ID
	private String normVehicleNumber;// 普通用户车架号
	private String normDrivingLicence;// 普通用户驾驶证号
	private Integer busiCompanyId;//公司ID
	private Integer busiParentId;//纯商家用户父ID
	private String busiName;//纯商家姓名
	private String busiPhone;//纯商家手机号
	private String adminName;//管理员姓名
	private String adminPhone;//管理员手机号
	private String applyCard;//管理员手机号
	private MultipartFile[] idCardPic; // 身份附件
	
	

	//填充字段
	private String userImage;//用户头像
	private String idCardimage;//授权证件附件
	private String rePassword;//密码确认
	private String normCarCompany;//普通用户汽车品牌
	private String normCarType;// 普通用户汽车车型
	private String busiCompany;//公司名称

	private int pileCount;//纯商家拥有的电桩数量
	private int childCount;//纯商家下的子商家数量
	private String roleNames;//角色名称
	private String cardId;//充电卡id

	private String phone;//手机号
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getNormName() {
		return normName;
	}

	public void setNormName(String normName) {
		this.normName = normName;
	}

	public String getNormRealName() {
		return normRealName;
	}

	public void setNormRealName(String normRealName) {
		this.normRealName = normRealName;
	}

	public Integer getNormStatus() {
		return normStatus;
	}

	public void setNormStatus(Integer normStatus) {
		this.normStatus = normStatus;
	}

	public Integer getNormRegisteType() {
		return normRegisteType;
	}

	public void setNormRegisteType(Integer normRegisteType) {
		this.normRegisteType = normRegisteType;
	}

	public BigDecimal getNormAccountBalance() {
		return normAccountBalance;
	}

	public void setNormAccountBalance(BigDecimal normAccountBalance) {
		this.normAccountBalance = normAccountBalance;
	}

	public String getNormPayPassword() {
		return normPayPassword;
	}

	public void setNormPayPassword(String normPayPassword) {
		this.normPayPassword = normPayPassword;
	}

	public String getNormDeviceId() {
		return normDeviceId;
	}

	public void setNormDeviceId(String normDeviceId) {
		this.normDeviceId = normDeviceId;
	}

	public String getNormEmail() {
		return normEmail;
	}

	public void setNormEmail(String normEmail) {
		this.normEmail = normEmail;
	}

	public String getNormAddress() {
		return normAddress;
	}

	public void setNormAddress(String normAddress) {
		this.normAddress = normAddress;
	}

	public String getNormIdCard() {
		return normIdCard;
	}

	public void setNormIdCard(String normIdCard) {
		this.normIdCard = normIdCard;
	}

	public Integer getNormSex() {
		return normSex;
	}

	public void setNormSex(Integer normSex) {
		this.normSex = normSex;
	}

	public String getNormBirthday() {
		return normBirthday;
	}

	public void setNormBirthday(String normBirthday) {
		this.normBirthday = normBirthday;
	}

	public Integer getNormIntegrate() {
		return normIntegrate;
	}

	public void setNormIntegrate(Integer normIntegrate) {
		this.normIntegrate = normIntegrate;
	}

	public Integer getNormCarCompanyId() {
		return normCarCompanyId;
	}

	public void setNormCarCompanyId(Integer normCarCompanyId) {
		this.normCarCompanyId = normCarCompanyId;
	}

	public Integer getNormCarTypeId() {
		return normCarTypeId;
	}

	public void setNormCarTypeId(Integer normCarTypeId) {
		this.normCarTypeId = normCarTypeId;
	}

	public String getNormVehicleNumber() {
		return normVehicleNumber;
	}

	public void setNormVehicleNumber(String normVehicleNumber) {
		this.normVehicleNumber = normVehicleNumber;
	}

	public String getNormDrivingLicence() {
		return normDrivingLicence;
	}

	public void setNormDrivingLicence(String normDrivingLicence) {
		this.normDrivingLicence = normDrivingLicence;
	}
	

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}


	public Integer getBusiCompanyId() {
		return busiCompanyId;
	}

	public void setBusiCompanyId(Integer busiCompanyId) {
		this.busiCompanyId = busiCompanyId;
	}

	public Integer getBusiParentId() {
		return busiParentId;
	}

	public void setBusiParentId(Integer busiParentId) {
		this.busiParentId = busiParentId;
	}

	public MultipartFile[] getIdCardPic() {
		return idCardPic;
	}

	public void setIdCardPic(MultipartFile[] idCardPic) {
		this.idCardPic = idCardPic;
	}

	public String getIdCardimage() {
		return idCardimage;
	}

	public void setIdCardimage(String idCardimage) {
		this.idCardimage = idCardimage;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	

	public String getNormCarCompany() {
		return normCarCompany;
	}

	public void setNormCarCompany(String normCarCompany) {
		this.normCarCompany = normCarCompany;
	}

	public String getNormCarType() {
		return normCarType;
	}

	public void setNormCarType(String normCarType) {
		this.normCarType = normCarType;
	}

	

	public String getBusiCompany() {
		return busiCompany;
	}

	public void setBusiCompany(String busiCompany) {
		this.busiCompany = busiCompany;
	}

	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

	public String getBusiPhone() {
		return busiPhone;
	}

	public void setBusiPhone(String busiPhone) {
		this.busiPhone = busiPhone;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}
	

	public int getPileCount() {
		return pileCount;
	}

	public void setPileCount(int pileCount) {
		this.pileCount = pileCount;
	}

	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}
	

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getApplyCard() {
		return applyCard;
	}

	public void setApplyCard(String applyCard) {
		this.applyCard = applyCard;
	}

	@Override
	public String toString() {
		return "TblUser [userId=" + userId + ", userAccount=" + userAccount
				+ ", userPassword=" + userPassword + ", userLevel=" + userLevel
				+ ", userStatus=" + userStatus + ", normName=" + normName
				+ ", normRealName=" + normRealName + ", normStatus="
				+ normStatus + ", normRegisteType=" + normRegisteType
				+ ", normAccountBalance=" + normAccountBalance
				+ ", normPayPassword=" + normPayPassword + ", normDeviceId="
				+ normDeviceId + ", normEmail=" + normEmail + ", normAddress="
				+ normAddress + ", normIdCard=" + normIdCard + ", normSex="
				+ normSex + ", normBirthday=" + normBirthday
				+ ", normIntegrate=" + normIntegrate + ", normCarCompanyId="
				+ normCarCompanyId + ", normCarTypeId=" + normCarTypeId
				+ ", normVehicleNumber=" + normVehicleNumber
				+ ", normDrivingLicence=" + normDrivingLicence
				+ ", busiCompanyId=" + busiCompanyId + ", busiParentId="
				+ busiParentId + ", idCardPic=" + Arrays.toString(idCardPic)
				+ ", userImage=" + userImage + ", idCardimage=" + idCardimage
				+ ", rePassword=" + rePassword + ", normCarCompany="
				+ normCarCompany + ", normCarType=" + normCarType
				+ ", busiCompany=" + busiCompany + "]";
	}

	

	

	

}
