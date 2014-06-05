package com.deppon.crm.module.recompense.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * Description:索赔信息<br />
 * </p>
 * 
 * @title Claim.java
 * @package com.deppon.crm.module.recompense.shared.domain
 * @author roy
 * @version 0.1 2013-3-1
 */
public class Claim extends BaseEntity {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;
	// 理赔类型
	private String recompenseMethod;
	// 运单号
	private String waybillNumber;
	// 索赔方
	private String claimer;
	// 联系人
	private String linkMan;
	// 联系方式
	private String linkMobile;
	// 保价金额
	private Double insuranceValue;
	// 索赔人
	private String claimPerson;
	// 索赔金额
	private Double claimAmount;
	// 客户索赔要求
	private String claimRequire;
	// 流转次数
	private int tranferCount;
	// 第一次拒绝时间
	private Date firstRefuseTime;
	// 索赔状态
	private String claimStatus;
	// 报案部门
	private String reportDept;
	// 报案部门名字
	private String reportDeptName;
	// 报案人
	private String reporter;
	// 报案人名字
	private String reporterName;
	// 当前处理部门
	private String processDept;
	// 当前处理部门名字
	private String processDeptName;
	//当前处理人
	private String processor;
	//当前处理人名字
	private String processorName;
	//发货部们id
	private String receiveDept;
	//到达部们id
	private String arrivedDept;
	//发货人
	private String shipper;
	//收货人 CONSIGNEE
	private String consignee;
	//发货联系方式
	private String shipperPhone;
	//收货联系方式
	private String consigneePhone;
	//索赔时间
	private Date claimTime;
	//运输类型
	private String transType;
	//显示时间
	private Date showTime;



	/**
	 * <p>
	 * Description:shipper<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public String getShipper() {
		return shipper;
	}

	/**
	 * <p>
	 * Description:shipper<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	/**
	 * <p>
	 * Description:consignee<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public String getConsignee() {
		return consignee;
	}

	/**
	 * <p>
	 * Description:consignee<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	/**
	 * <p>
	 * Description:shipperPhone<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public String getShipperPhone() {
		return shipperPhone;
	}

	/**
	 * <p>
	 * Description:shipperPhone<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public void setShipperPhone(String shipperPhone) {
		this.shipperPhone = shipperPhone;
	}

	/**
	 * <p>
	 * Description:consigneePhone<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public String getConsigneePhone() {
		return consigneePhone;
	}

	/**
	 * <p>
	 * Description:consigneePhone<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}

	/**
	 * <p>
	 * Description:reportDeptName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 */
	public String getReportDeptName() {
		return reportDeptName;
	}

	/**
	 * <p>
	 * Description:reportDeptName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 */
	public void setReportDeptName(String reportDeptName) {
		this.reportDeptName = reportDeptName;
	}

	/**
	 * <p>
	 * Description:processDeptName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 */
	public String getProcessDeptName() {
		return processDeptName;
	}

	/**
	 * <p>
	 * Description:processDeptName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 */
	public void setProcessDeptName(String processDeptName) {
		this.processDeptName = processDeptName;
	}

	// 状态顺序
	private String statusSeq;

	/**
	 * <p>
	 * Description:waybillNumber<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getWaybillNumber() {
		return waybillNumber;
	}

	/**
	 * <p>
	 * Description:waybillNumber<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	/**
	 * <p>
	 * Description:claimer<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getClaimer() {
		return claimer;
	}

	/**
	 * <p>
	 * Description:claimer<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setClaimer(String claimer) {
		this.claimer = claimer;
	}

	/**
	 * <p>
	 * Description:linkMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getLinkMan() {
		return linkMan;
	}

	/**
	 * <p>
	 * Description:linkMan<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	/**
	 * <p>
	 * Description:linkMobile<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getLinkMobile() {
		return linkMobile;
	}

	/**
	 * <p>
	 * Description:linkMobile<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}

	/**
	 * <p>
	 * Description:insuranceValue<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public Double getInsuranceValue() {
		return insuranceValue;
	}

	/**
	 * <p>
	 * Description:insuranceValue<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setInsuranceValue(Double insuranceValue) {
		this.insuranceValue = insuranceValue;
	}

	/**
	 * <p>
	 * Description:claimPerson<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getClaimPerson() {
		return claimPerson;
	}

	/**
	 * <p>
	 * Description:claimPerson<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setClaimPerson(String claimPerson) {
		this.claimPerson = claimPerson;
	}

	/**
	 * <p>
	 * Description:claimAmount<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public Double getClaimAmount() {
		return claimAmount;
	}

	/**
	 * <p>
	 * Description:claimAmount<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setClaimAmount(Double claimAmount) {
		this.claimAmount = claimAmount;
	}

	/**
	 * <p>
	 * Description:claimRequire<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getClaimRequire() {
		return claimRequire;
	}

	/**
	 * <p>
	 * Description:claimRequire<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setClaimRequire(String claimRequire) {
		this.claimRequire = claimRequire;
	}


	/**
	 * <p>
	 * Description:tranferCount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-5
	 */
	public int getTranferCount() {
		return tranferCount;
	}

	/**
	 * <p>
	 * Description:tranferCount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-5
	 */
	public void setTranferCount(int tranferCount) {
		this.tranferCount = tranferCount;
	}

	/**
	 * <p>
	 * Description:firstRefuseTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public Date getFirstRefuseTime() {
		return firstRefuseTime;
	}

	/**
	 * <p>
	 * Description:firstRefuseTime<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setFirstRefuseTime(Date firstRefuseTime) {
		this.firstRefuseTime = firstRefuseTime;
	}

	/**
	 * <p>
	 * Description:claimStatus<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getClaimStatus() {
		return claimStatus;
	}

	/**
	 * <p>
	 * Description:claimStatus<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	/**
	 * <p>
	 * Description:reportDept<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getReportDept() {
		return reportDept;
	}

	/**
	 * <p>
	 * Description:reportDept<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setReportDept(String reportDept) {
		this.reportDept = reportDept;
	}

	/**
	 * <p>
	 * Description:reporter<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getReporter() {
		return reporter;
	}

	/**
	 * <p>
	 * Description:reporter<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	/**
	 * <p>
	 * Description:processDept<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public String getProcessDept() {
		return processDept;
	}

	/**
	 * <p>
	 * Description:processDept<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-1
	 */
	public void setProcessDept(String processDept) {
		this.processDept = processDept;
	}

	/**
	 * <p>
	 * Description:statusSeq<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 */
	public String getStatusSeq() {
		return statusSeq;
	}

	/**
	 * <p>
	 * Description:statusSeq<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 */
	public void setStatusSeq(String statusSeq) {
		this.statusSeq = statusSeq;
	}

	/**
	 * <p>
	 * Description:reporterName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 */
	public String getReporterName() {
		return reporterName;
	}

	/**
	 * <p>
	 * Description:reporterName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-4
	 */
	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}


	/**
	 * <p>
	 * Description:recompenseMethod<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-5
	 */
	public String getRecompenseMethod() {
		return recompenseMethod;
	}

	/**
	 * <p>
	 * Description:recompenseMethod<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-5
	 */
	public void setRecompenseMethod(String recompenseMethod) {
		this.recompenseMethod = recompenseMethod;
	}

	/**
	 * <p>
	 * Description:processor<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-5
	 */
	public String getProcessor() {
		return processor;
	}

	/**
	 * <p>
	 * Description:processor<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-5
	 */
	public void setProcessor(String processor) {
		this.processor = processor;
	}

	/**
	 * <p>
	 * Description:processorName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-5
	 */
	public String getProcessorName() {
		return processorName;
	}

	/**
	 * <p>
	 * Description:processorName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-5
	 */
	public void setProcessorName(String processorName) {
		this.processorName = processorName;
	}

	

	/**
	 * <p>
	 * Description:receiveDept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-11
	 */
	public String getReceiveDept() {
		return receiveDept;
	}

	/**
	 * <p>
	 * Description:receiveDept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-11
	 */
	public void setReceiveDept(String receiveDept) {
		this.receiveDept = receiveDept;
	}

	/**
	 * <p>
	 * Description:arrivedDept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public String getArrivedDept() {
		return arrivedDept;
	}

	/**
	 * <p>
	 * Description:arrivedDept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public void setArrivedDept(String arrivedDept) {
		this.arrivedDept = arrivedDept;
	}

	/**
	 * <p>
	 * Description:claimTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-21
	 */
	public Date getClaimTime() {
		return claimTime;
	}

	/**
	 * <p>
	 * Description:claimTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-21
	 */
	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}

	/**
	 * <p>
	 * Description:transType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-21
	 */
	public String getTransType() {
		return transType;
	}

	/**
	 * <p>
	 * Description:transType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-21
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}

	/**
	 * <p>
	 * Description:showTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-25
	 */
	public Date getShowTime() {
		return showTime;
	}

	/**
	 * <p>
	 * Description:showTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-25
	 */
	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}




}
