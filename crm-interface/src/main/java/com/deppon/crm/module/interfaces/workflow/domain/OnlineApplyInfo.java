package com.deppon.crm.module.interfaces.workflow.domain;

import java.util.Date;

public class OnlineApplyInfo {

	//理赔编号(第二次提交的时候一定要有此字段，对应开发组的recompenseId)
	private String recompenseNumber;
	
	//理赔人(名称，网厅输入的名字)。
	private String claimant;
	
	//理赔时间，是否对应createDate字段
	private Date recompenseDate;
	//最后更新时间 ,是否对应modifyDate字段
	private Date lastModifyTime;

	//------这些字段是需要开发组添加的，建表的---
	//甲方
	private String partA;
	//乙方
	private String partB;
	
	//甲方签字
	private String partAsign;
	//甲方签字日期
	private Date partAsignDate;
	//乙方签字
	private String partBAsign;
	//乙方签字日期
	private Date partBAsignDate;
	
	//理赔方：发货方、收货方
	private String recompensePerson;
	
    //--------开发组在线理赔字段
	// 理赔id
	private String recompenseId;
	// 运单号
	private String waybillNumber;
	// 客户编码
	private String customerNum;
	// 索赔人(官网注册用户名)
	private String customerId;
	// 索赔金额
	private Double recompenseAmount;
	// 保价金额
	private Double insurAmount;
	// 索赔原由（理赔原因）
	private String recompenseReason;
	// 退回原因
	private String rejectReason;
	// 受理时间
	private Date applyTime;
	// 退回时间
	private Date rejectTime;
	// 身份证号 
	private String idCard;
	// 银行(第二次提交)
	private String bankName;
	//银行编码
	private String bankCode;
	// 支银行(第二提交)
	private String branchName;
	//支行编码
	private String branchCode;
	// 开户名(第二次提交)
	private String openName;
	// 账号(第二次提交)
	private String account;
	// 手机号
	private String mobile;
	// 电话
	private String telphone;
	// 省（此字段需要传入费控，官网过来的数据需要费控识别）
	private String province;
	//省编码
	private String provinceCode;
	// 市
	private String city;
	//市编码
	private String cityCode;
	// 区县
	private String county;
	// 登录名
	private String onlineUser;
	// 运输性质(名称)
	/**
	 * ERP，
	 * 精准空运0/1,精准汽运长途2,精准卡航3,精准城运4,汽运偏线5,精准汽运短途6
	 */
	private String transType;
	// 出发站
	private String startStation;
	// 目的站
	private String endStation;
	// 发货时间
	private Date sendDate;
	// 部门编码(发货或者到货部门)
	private String applyDeptId;
	// 理赔状态（待受理、审批退回）
	private String Status;
	// 身份证正面照片路径
	private String overPICsrc;
	//身份证正面照片路径
	private String backPICsrc;
	
	public String getOverPICsrc() {
		return overPICsrc;
	}
	public void setOverPICsrc(String overPICsrc) {
		this.overPICsrc = overPICsrc;
	}
	public String getBackPICsrc() {
		return backPICsrc;
	}
	public void setBackPICsrc(String backPICsrc) {
		this.backPICsrc = backPICsrc;
	}
	public String getRecompenseId() {
		return recompenseId;
	}
	public void setRecompenseId(String recompenseId) {
		this.recompenseId = recompenseId;
	}
	public String getWaybillNumber() {
		return waybillNumber;
	}
	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Double getRecompenseAmount() {
		return recompenseAmount;
	}
	public void setRecompenseAmount(Double recompenseAmount) {
		this.recompenseAmount = recompenseAmount;
	}
	public Double getInsurAmount() {
		return insurAmount;
	}
	public void setInsurAmount(Double insurAmount) {
		this.insurAmount = insurAmount;
	}
	public String getRecompenseReason() {
		return recompenseReason;
	}
	public void setRecompenseReason(String recompenseReason) {
		this.recompenseReason = recompenseReason;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Date getRejectTime() {
		return rejectTime;
	}
	public void setRejectTime(Date rejectTime) {
		this.rejectTime = rejectTime;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getOpenName() {
		return openName;
	}
	public void setOpenName(String openName) {
		this.openName = openName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getOnlineUser() {
		return onlineUser;
	}
	public void setOnlineUser(String onlineUser) {
		this.onlineUser = onlineUser;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getStartStation() {
		return startStation;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public String getEndStation() {
		return endStation;
	}
	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getApplyDeptId() {
		return applyDeptId;
	}
	public void setApplyDeptId(String applyDeptId) {
		this.applyDeptId = applyDeptId;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getRecompenseNumber() {
		return recompenseNumber;
	}
	public void setRecompenseNumber(String recompenseNumber) {
		this.recompenseNumber = recompenseNumber;
	}
	public String getClaimant() {
		return claimant;
	}
	public void setClaimant(String claimant) {
		this.claimant = claimant;
	}
	public Date getRecompenseDate() {
		return recompenseDate;
	}
	public void setRecompenseDate(Date recompenseDate) {
		this.recompenseDate = recompenseDate;
	}
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public String getPartA() {
		return partA;
	}
	public void setPartA(String partA) {
		this.partA = partA;
	}
	public String getPartB() {
		return partB;
	}
	public void setPartB(String partB) {
		this.partB = partB;
	}
	public String getPartAsign() {
		return partAsign;
	}
	public void setPartAsign(String partAsign) {
		this.partAsign = partAsign;
	}
	public Date getPartAsignDate() {
		return partAsignDate;
	}
	public void setPartAsignDate(Date partAsignDate) {
		this.partAsignDate = partAsignDate;
	}
	public String getPartBAsign() {
		return partBAsign;
	}
	public void setPartBAsign(String partBAsign) {
		this.partBAsign = partBAsign;
	}
	public Date getPartBAsignDate() {
		return partBAsignDate;
	}
	public void setPartBAsignDate(Date partBAsignDate) {
		this.partBAsignDate = partBAsignDate;
	}
	public String getRecompensePerson() {
		return recompensePerson;
	}
	public void setRecompensePerson(String recompensePerson) {
		this.recompensePerson = recompensePerson;
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
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
}
