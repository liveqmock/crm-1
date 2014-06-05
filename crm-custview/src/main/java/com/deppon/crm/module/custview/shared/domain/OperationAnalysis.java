package com.deppon.crm.module.custview.shared.domain;

import java.util.Date;

/**
 * @description 营运分析.
 * @author 安小虎
 * @version 0.1 2012-4-25
 * @date 2012-4-25
 */

public class OperationAnalysis {
	// ID
	private String id;
	// 客户id
	private String custId;
	// 客户编码
	private String custNumber;
	// 日期
	private Date date;
	// 年月
	private String yearMonth;
	// 出发货量
	private String leaveWeight;
	// 到达货量
	private String arriveWeight;
	// 出发票数
	private String leaveBill;
	// 到达票数
	private String arriveBill;
	// 优势业务占比
	private String advantageBusiRate;
	// 汽运占比
	private String motorRate;
	// 发货总金额
	private String leaveMoney;
	// 保险费
	private String insurance;
	// 保营比
	private String insuranceRate;
	// 理赔金额
	private String claimsMoney;
	// 包装费
	private String fpackage;
	// 包营比
	private String packageRate;
	// 代收货款
	private String agentreceivePay;
	// 代收比
	private String agentreceivepayRate;
	// 网上营业厅票数占比
	private String wtvoteRate;
	// 呼叫中心票数占比
	private String callvoteRate;
	// 营业厅票数占比
	private String yytvoteRate;
	// 第三方渠道票数占比
	private String othervoteRate;
	// 到货金额
	private String arriveMoney;
	/**
	 * @return the id
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the custId
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * @return the custNumber
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getCustNumber() {
		return custNumber;
	}
	/**
	 * @param custNumber the custNumber to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	/**
	 * @return the date
	 * @author 潘光均
	 * @version v0.1
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the yearMonth
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getYearMonth() {
		return yearMonth;
	}
	/**
	 * @param yearMonth the yearMonth to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	/**
	 * @return the leaveWeight
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getLeaveWeight() {
		return leaveWeight;
	}
	/**
	 * @param leaveWeight the leaveWeight to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setLeaveWeight(String leaveWeight) {
		this.leaveWeight = leaveWeight;
	}
	/**
	 * @return the arriveWeight
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getArriveWeight() {
		return arriveWeight;
	}
	/**
	 * @param arriveWeight the arriveWeight to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setArriveWeight(String arriveWeight) {
		this.arriveWeight = arriveWeight;
	}
	/**
	 * @return the leaveBill
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getLeaveBill() {
		return leaveBill;
	}
	/**
	 * @param leaveBill the leaveBill to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setLeaveBill(String leaveBill) {
		this.leaveBill = leaveBill;
	}
	/**
	 * @return the arriveBill
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getArriveBill() {
		return arriveBill;
	}
	/**
	 * @param arriveBill the arriveBill to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setArriveBill(String arriveBill) {
		this.arriveBill = arriveBill;
	}
	/**
	 * @return the advantageBusiRate
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getAdvantageBusiRate() {
		return advantageBusiRate;
	}
	/**
	 * @param advantageBusiRate the advantageBusiRate to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setAdvantageBusiRate(String advantageBusiRate) {
		this.advantageBusiRate = advantageBusiRate;
	}
	/**
	 * @return the motorRate
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getMotorRate() {
		return motorRate;
	}
	/**
	 * @param motorRate the motorRate to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setMotorRate(String motorRate) {
		this.motorRate = motorRate;
	}
	/**
	 * @return the leaveMoney
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getLeaveMoney() {
		return leaveMoney;
	}
	/**
	 * @param leaveMoney the leaveMoney to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setLeaveMoney(String leaveMoney) {
		this.leaveMoney = leaveMoney;
	}
	/**
	 * @return the insurance
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getInsurance() {
		return insurance;
	}
	/**
	 * @param insurance the insurance to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	/**
	 * @return the insuranceRate
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getInsuranceRate() {
		return insuranceRate;
	}
	/**
	 * @param insuranceRate the insuranceRate to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setInsuranceRate(String insuranceRate) {
		this.insuranceRate = insuranceRate;
	}
	/**
	 * @return the claimsMoney
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getClaimsMoney() {
		return claimsMoney;
	}
	/**
	 * @param claimsMoney the claimsMoney to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setClaimsMoney(String claimsMoney) {
		this.claimsMoney = claimsMoney;
	}
	/**
	 * @return the fpackage
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getFpackage() {
		return fpackage;
	}
	/**
	 * @param fpackage the fpackage to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setFpackage(String fpackage) {
		this.fpackage = fpackage;
	}
	/**
	 * @return the packageRate
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getPackageRate() {
		return packageRate;
	}
	/**
	 * @param packageRate the packageRate to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setPackageRate(String packageRate) {
		this.packageRate = packageRate;
	}
	/**
	 * @return the agentreceivePay
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getAgentreceivePay() {
		return agentreceivePay;
	}
	/**
	 * @param agentreceivePay the agentreceivePay to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setAgentreceivePay(String agentreceivePay) {
		this.agentreceivePay = agentreceivePay;
	}
	/**
	 * @return the agentreceivepayRate
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getAgentreceivepayRate() {
		return agentreceivepayRate;
	}
	/**
	 * @param agentreceivepayRate the agentreceivepayRate to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setAgentreceivepayRate(String agentreceivepayRate) {
		this.agentreceivepayRate = agentreceivepayRate;
	}
	/**
	 * @return the wtvoteRate
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getWtvoteRate() {
		return wtvoteRate;
	}
	/**
	 * @param wtvoteRate the wtvoteRate to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setWtvoteRate(String wtvoteRate) {
		this.wtvoteRate = wtvoteRate;
	}
	/**
	 * @return the callvoteRate
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getCallvoteRate() {
		return callvoteRate;
	}
	/**
	 * @param callvoteRate the callvoteRate to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setCallvoteRate(String callvoteRate) {
		this.callvoteRate = callvoteRate;
	}
	/**
	 * @return the yytvoteRate
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getYytvoteRate() {
		return yytvoteRate;
	}
	/**
	 * @param yytvoteRate the yytvoteRate to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setYytvoteRate(String yytvoteRate) {
		this.yytvoteRate = yytvoteRate;
	}
	/**
	 * @return the othervoteRate
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getOthervoteRate() {
		return othervoteRate;
	}
	/**
	 * @param othervoteRate the othervoteRate to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setOthervoteRate(String othervoteRate) {
		this.othervoteRate = othervoteRate;
	}
	/**
	 * @return the arriveMoney
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getArriveMoney() {
		return arriveMoney;
	}
	/**
	 * @param arriveMoney the arriveMoney to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setArriveMoney(String arriveMoney) {
		this.arriveMoney = arriveMoney;
	}
}
