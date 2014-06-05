package com.deppon.crm.module.client.workflow.domain;

import java.util.Date;

/**
 * 理赔付款工作流，包括常规理赔付款、多陪付款及在线理赔付款
 * 其中，常规理赔和多赔参数一致，在线理赔
 * @author davidcun @2012-4-10
 */
public class PaymentInfo {
	
	//申請人工号(如果是在线理赔需要的是经理工号)
	private String applyPersonNumber;
	//收款人姓名
	private String payee;
	//收款人手机号
	private String payeeMobilePhone;
	//省份名称或标准编号(有官网传入的值确定)
	private String province;
	//城市名称或标准编号(有官网传入的值确定)
	private String city;
	//开户银行名称或标准编号(有官网传入的值确定)
	private String bank;
	//支行名称或标准编号(有官网传入的值确定)
	private String subbranch;
	//账号
	private String accountNumber;
	//总金金额
	private double amountMoney;
	//支付(领款)方式
	private String payWay;
	//单号
	private String waybillNumber;
	//差错编号
	private String errorNumber;
	
	//-----------------在线理赔付款多加的信息
	//甲方
	private String partA;
	//乙方
	private String partB;
	//发货日期
	private Date shipmentsDate;
	//出发站(名称)
	private String startStation;
	//目的站(名称)
	private String destination;
	//赔款金额
	private double recompenseMoney;
	//赔款金额大写
	private String recompenseMoneyText;
	//运单号
	//账号
	//开户银行
	//开户支行
	//开户名
	private String accountName;
	//甲方签字
	private String partAsign;
	//甲方签字日期
	private Date partAsignDate;
	//乙方签字
	private String partBAsign;
	//乙方签字日期
	private Date partBAsignDate;
	//身份证
	private String identityCard;
	
	public String getApplyPersonNumber() {
		return applyPersonNumber;
	}
	public void setApplyPersonNumber(String applyPersonNumber) {
		this.applyPersonNumber = applyPersonNumber;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getPayeeMobilePhone() {
		return payeeMobilePhone;
	}
	public void setPayeeMobilePhone(String payeeMobilePhone) {
		this.payeeMobilePhone = payeeMobilePhone;
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
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getSubbranch() {
		return subbranch;
	}
	public void setSubbranch(String subbranch) {
		this.subbranch = subbranch;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getAmountMoney() {
		return amountMoney;
	}
	public void setAmountMoney(double amountMoney) {
		this.amountMoney = amountMoney;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getWaybillNumber() {
		return waybillNumber;
	}
	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}
	public String getErrorNumber() {
		return errorNumber;
	}
	public void setErrorNumber(String errorNumber) {
		this.errorNumber = errorNumber;
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
	public Date getShipmentsDate() {
		return shipmentsDate;
	}
	public void setShipmentsDate(Date shipmentsDate) {
		this.shipmentsDate = shipmentsDate;
	}
	public String getStartStation() {
		return startStation;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public double getRecompenseMoney() {
		return recompenseMoney;
	}
	public void setRecompenseMoney(double recompenseMoney) {
		this.recompenseMoney = recompenseMoney;
	}
	public String getRecompenseMoneyText() {
		return recompenseMoneyText;
	}
	public void setRecompenseMoneyText(String recompenseMoneyText) {
		this.recompenseMoneyText = recompenseMoneyText;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
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
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	
	
	
	
	
}
