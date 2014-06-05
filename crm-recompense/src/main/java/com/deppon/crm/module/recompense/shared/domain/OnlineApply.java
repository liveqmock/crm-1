package com.deppon.crm.module.recompense.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>
 * Description:在线理赔<br />
 * </p>
 * @title OnlineApply.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class OnlineApply extends BaseEntity {

	private static final long serialVersionUID = -4529947812296214959L;

	// 理赔方式
	private String recompenseMethod = "online";
	// 理赔类型
	private String recompenseType;
	// 运单号
	private String waybillNumber;
	// 客户编码
	private String customerNum;
	// 索赔人
	private String customerId;
	// 索赔金额
	private Double recompenseAmount;
	// 索赔金额大写
	private String chineseAmount;
	// 保价金额
	private Double insurAmount;
	// 索赔原由
	private String recompenseReason;
	// 退回原因
	private String rejectReason;
	// 受理时间
	private Date applyTime;
	// 退回时间
	private Date rejectTime;
	// 身份证号 ;
	private String idCard;
	// 银行
	private String bankName;
	// 支银行
	private String branchName;
	// 开户名
	private String openName;
	// 账号
	private String account;
	// 手机号
	private String mobile;
	// 电话
	private String telphone;
	// 省
	private String province;
	// 市
	private String city;
	// 区县
	private String county;
	// 登录名
	private String onlineUser;
	// 运输性质、
	private String transType;
	// 出发站、
	private String startStation;
	// 目的站、
	private String endStation;
	// 发货时间、
	private Date sendDate;
	// 部门id、
	private String applyDeptId;
	// 理赔状态（待受理、审批退回）
	private String Status;
	// 理赔id
	private String recompenseId;

	// 甲方
	private String partA;
	// 甲方签字
	private String partAsign;
	// 甲方签字日期
	private Date partAsignDate;
	// 乙方
	private String partB;
	// 乙方签字
	private String partBAsign;
	// 乙方签字日期
	private Date partBAsignDate;
	// 理賠方
	private String applyPart;
	// 出发部门子公司
	private String leaveCompany;
	// 到达部门子公司
	private String arriveCompany;
	// 正面的图片
	private String frontImage;
	// 背面的图片
	private String backImage;
	//银行编码
	private String bankCode;
	//支行编码
	private String branchCode;
	//城市编码
	private String cityCode;
	//省份编码
	private String provinceCode;
	//所属区域ID
	private String belongsAreaName;
	//所属区域名字
	private String belongsArea;
	//持续时间差(单位:天)在线理赔监控使用
	private Integer acceptDays;
	//受理时间 ==理赔监控使用
	private Date acceptDate;
	//受理部门名字
	private String acceptDeptName;
	
	/*
	 * 监控添加 20131113
	 */
	// 催办次数
	private Integer hastenCount;
	// 最后催办时间
	private Date lastHastenTime;
	
	
	/**
	 *@return  hastenCount;
	 */
	public Integer getHastenCount() {
		return hastenCount;
	}
	/**
	 * @param hastenCount : set the property hastenCount.
	 */
	public void setHastenCount(Integer hastenCount) {
		this.hastenCount = hastenCount;
	}
	/**
	 *@return  lastHastenTime;
	 */
	public Date getLastHastenTime() {
		return lastHastenTime;
	}
	/**
	 * @param lastHastenTime : set the property lastHastenTime.
	 */
	public void setLastHastenTime(Date lastHastenTime) {
		this.lastHastenTime = lastHastenTime;
	}
	public String getAcceptDeptName() {
		return acceptDeptName;
	}
	public void setAcceptDeptName(String acceptDeptName) {
		this.acceptDeptName = acceptDeptName;
	}
	public Date getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}
	public Integer getAcceptDays() {
		return acceptDays;
	}
	public void setAcceptDays(Integer acceptDays) {
		this.acceptDays = acceptDays;
	}
	public String getBelongsAreaName() {
		return belongsAreaName;
	}
	public void setBelongsAreaName(String belongsAreaName) {
		this.belongsAreaName = belongsAreaName;
	}
	public String getBelongsArea() {
		return belongsArea;
	}
	public void setBelongsArea(String belongsArea) {
		this.belongsArea = belongsArea;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	/**
	 * <p>
	 * Description:recompenseMethod<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseMethod() {
		return recompenseMethod;
	}
	/**
	 * <p>
	 * Description:recompenseMethod<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseMethod(String recompenseMethod) {
		this.recompenseMethod = recompenseMethod;
	}
	/**
	 * <p>
	 * Description:recompenseType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseType() {
		return recompenseType;
	}
	/**
	 * <p>
	 * Description:recompenseType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseType(String recompenseType) {
		this.recompenseType = recompenseType;
	}
	/**
	 * <p>
	 * Description:waybillNumber<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getWaybillNumber() {
		return waybillNumber;
	}
	/**
	 * <p>
	 * Description:waybillNumber<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}
	/**
	 * <p>
	 * Description:customerNum<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCustomerNum() {
		return customerNum;
	}
	/**
	 * <p>
	 * Description:customerNum<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	/**
	 * <p>
	 * Description:customerId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * <p>
	 * Description:customerId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * <p>
	 * Description:recompenseAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getRecompenseAmount() {
		return recompenseAmount;
	}
	/**
	 * <p>
	 * Description:recompenseAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseAmount(Double recompenseAmount) {
		this.recompenseAmount = recompenseAmount;
	}
	/**
	 * <p>
	 * Description:chineseAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getChineseAmount() {
		return chineseAmount;
	}
	/**
	 * <p>
	 * Description:chineseAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setChineseAmount(String chineseAmount) {
		this.chineseAmount = chineseAmount;
	}
	/**
	 * <p>
	 * Description:insurAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getInsurAmount() {
		return insurAmount;
	}
	/**
	 * <p>
	 * Description:insurAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setInsurAmount(Double insurAmount) {
		this.insurAmount = insurAmount;
	}
	/**
	 * <p>
	 * Description:recompenseReason<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseReason() {
		return recompenseReason;
	}
	/**
	 * <p>
	 * Description:recompenseReason<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseReason(String recompenseReason) {
		this.recompenseReason = recompenseReason;
	}
	/**
	 * <p>
	 * Description:rejectReason<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRejectReason() {
		return rejectReason;
	}
	/**
	 * <p>
	 * Description:rejectReason<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	/**
	 * <p>
	 * Description:applyTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getApplyTime() {
		return applyTime;
	}
	/**
	 * <p>
	 * Description:applyTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	/**
	 * <p>
	 * Description:rejectTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getRejectTime() {
		return rejectTime;
	}
	/**
	 * <p>
	 * Description:rejectTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRejectTime(Date rejectTime) {
		this.rejectTime = rejectTime;
	}
	/**
	 * <p>
	 * Description:idCard<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * <p>
	 * Description:idCard<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	/**
	 * <p>
	 * Description:bankName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * <p>
	 * Description:bankName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * <p>
	 * Description:branchName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * <p>
	 * Description:branchName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * <p>
	 * Description:openName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getOpenName() {
		return openName;
	}
	/**
	 * <p>
	 * Description:openName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setOpenName(String openName) {
		this.openName = openName;
	}
	/**
	 * <p>
	 * Description:account<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * <p>
	 * Description:account<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * <p>
	 * Description:mobile<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * <p>
	 * Description:mobile<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * <p>
	 * Description:telphone<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getTelphone() {
		return telphone;
	}
	/**
	 * <p>
	 * Description:telphone<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	/**
	 * <p>
	 * Description:province<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * <p>
	 * Description:province<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * <p>
	 * Description:city<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCity() {
		return city;
	}
	/**
	 * <p>
	 * Description:city<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * <p>
	 * Description:county<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCounty() {
		return county;
	}
	/**
	 * <p>
	 * Description:county<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	/**
	 * <p>
	 * Description:onlineUser<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getOnlineUser() {
		return onlineUser;
	}
	/**
	 * <p>
	 * Description:onlineUser<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setOnlineUser(String onlineUser) {
		this.onlineUser = onlineUser;
	}
	/**
	 * <p>
	 * Description:transType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getTransType() {
		return transType;
	}
	/**
	 * <p>
	 * Description:transType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}
	/**
	 * <p>
	 * Description:startStation<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getStartStation() {
		return startStation;
	}
	/**
	 * <p>
	 * Description:startStation<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	/**
	 * <p>
	 * Description:endStation<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getEndStation() {
		return endStation;
	}
	/**
	 * <p>
	 * Description:endStation<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}
	/**
	 * <p>
	 * Description:sendDate<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getSendDate() {
		return sendDate;
	}
	/**
	 * <p>
	 * Description:sendDate<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	/**
	 * <p>
	 * Description:applyDeptId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getApplyDeptId() {
		return applyDeptId;
	}
	/**
	 * <p>
	 * Description:applyDeptId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setApplyDeptId(String applyDeptId) {
		this.applyDeptId = applyDeptId;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getStatus() {
		return Status;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setStatus(String status) {
		Status = status;
	}
	/**
	 * <p>
	 * Description:recompenseId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseId() {
		return recompenseId;
	}
	/**
	 * <p>
	 * Description:recompenseId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseId(String recompenseId) {
		this.recompenseId = recompenseId;
	}
	/**
	 * <p>
	 * Description:partA<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getPartA() {
		return partA;
	}
	/**
	 * <p>
	 * Description:partA<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setPartA(String partA) {
		this.partA = partA;
	}
	/**
	 * <p>
	 * Description:partAsign<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getPartAsign() {
		return partAsign;
	}
	/**
	 * <p>
	 * Description:partAsign<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setPartAsign(String partAsign) {
		this.partAsign = partAsign;
	}
	/**
	 * <p>
	 * Description:partAsignDate<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getPartAsignDate() {
		return partAsignDate;
	}
	/**
	 * <p>
	 * Description:partAsignDate<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setPartAsignDate(Date partAsignDate) {
		this.partAsignDate = partAsignDate;
	}
	/**
	 * <p>
	 * Description:partB<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getPartB() {
		return partB;
	}
	/**
	 * <p>
	 * Description:partB<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setPartB(String partB) {
		this.partB = partB;
	}
	/**
	 * <p>
	 * Description:partBAsign<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getPartBAsign() {
		return partBAsign;
	}
	/**
	 * <p>
	 * Description:partBAsign<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setPartBAsign(String partBAsign) {
		this.partBAsign = partBAsign;
	}
	/**
	 * <p>
	 * Description:partBAsignDate<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getPartBAsignDate() {
		return partBAsignDate;
	}
	/**
	 * <p>
	 * Description:partBAsignDate<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setPartBAsignDate(Date partBAsignDate) {
		this.partBAsignDate = partBAsignDate;
	}
	/**
	 * <p>
	 * Description:applyPart<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getApplyPart() {
		return applyPart;
	}
	/**
	 * <p>
	 * Description:applyPart<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setApplyPart(String applyPart) {
		this.applyPart = applyPart;
	}
	/**
	 * <p>
	 * Description:leaveCompany<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getLeaveCompany() {
		return leaveCompany;
	}
	/**
	 * <p>
	 * Description:leaveCompany<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setLeaveCompany(String leaveCompany) {
		this.leaveCompany = leaveCompany;
	}
	/**
	 * <p>
	 * Description:arriveCompany<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getArriveCompany() {
		return arriveCompany;
	}
	/**
	 * <p>
	 * Description:arriveCompany<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setArriveCompany(String arriveCompany) {
		this.arriveCompany = arriveCompany;
	}
	/**
	 * <p>
	 * Description:frontImage<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getFrontImage() {
		return frontImage;
	}
	/**
	 * <p>
	 * Description:frontImage<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setFrontImage(String frontImage) {
		this.frontImage = frontImage;
	}
	/**
	 * <p>
	 * Description:backImage<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getBackImage() {
		return backImage;
	}
	/**
	 * <p>
	 * Description:backImage<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setBackImage(String backImage) {
		this.backImage = backImage;
	}


}
