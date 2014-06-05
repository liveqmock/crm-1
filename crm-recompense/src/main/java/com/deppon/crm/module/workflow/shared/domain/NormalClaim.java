package com.deppon.crm.module.workflow.shared.domain;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * Description:理赔工作流<br />
 * </p>
 * @title NomalClaim.java
 * @package com.deppon.crm.module.workflow.shared.domain 
 * @author andy
 * @version 0.1 2013-7-29
 */
public class NormalClaim extends BaseEntity{
	
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 1L;

	//工作流编号
	private String processinstId;
	
	//申请人工号
	private String applyPersonCode;
	
	//申请人所在部门标杆编码
	private String standardCode;
	
	//线索工号,报案人经理工号
	private String clueUserId;
	
	//运单号/差错编号
	private String transportOrErrorCode;
	
	//保价人/发货联系人
	private String insuredUnits;
	
	//联系电话
	private String contactPhone;
	
	//运输类型
	private String haulType;
	
	//收货部门（名称）
	private String receivingDept;
	
	//始发站
	private String startingStation;
	
	//货物名称
	private String goodsName;
	
	//货物属性：件/重/体
	private String goodsAttribute;
	
	//保险金额
	private double insuredAmount;
	
	//目标部门，到达部门
	private String targetDept;
	
	//发货日期
	private Date sendingDate;
	
	//出险日期
	private Date dangerDate;
	
	//所属区域Id
	private String areaId;
	
	//所属区域(名称)
	private String area;
	
	//理赔类型(名称)
	private String claimsType;
	
	//冲账方式
	private String offsetType;
	
	//报案人(工号)
	private String caseReporter;
	
	//报案人(名称)
	private String caseReporterName;
	
	//报案部门Id
	private String reportDept;
	
	//报案部门(名称)
	private String reportDeptName;
	
	//报案日期
	private Date reportDate;
	
	//处理人(名称)
	private String handler;
	
	//处理日期
	private Date handleDate;
	
	//其他费用说明
	private String otherCost;
	
	//索赔金额
	private double claimAmount;
	
	//责任部门/大区（如果有多个则进行组装：重庆大区,江门大区）
	private String responsibileDept;
	
	//正常理赔金额
	private double normalAmount;
	
	//实际理赔金额
	private double actualClaimsAmount;
	
	//入公司费用
	private double toCompanyCost;
	
	//大区标杆编码
	private String areaCode;
	
	//流程编号 格式：ICRM年月日+6位数字
	private String workflowNo;
	
	//附件信息
	private List<FileInfo> fileInfoList;

	public String getProcessinstId() {
		return processinstId;
	}

	public void setProcessinstId(String processinstId) {
		this.processinstId = processinstId;
	}

	public String getApplyPersonCode() {
		return applyPersonCode;
	}

	public void setApplyPersonCode(String applyPersonCode) {
		this.applyPersonCode = applyPersonCode;
	}

	public String getClueUserId() {
		return clueUserId;
	}

	public void setClueUserId(String clueUserId) {
		this.clueUserId = clueUserId;
	}

	public String getTransportOrErrorCode() {
		return transportOrErrorCode;
	}

	public void setTransportOrErrorCode(String transportOrErrorCode) {
		this.transportOrErrorCode = transportOrErrorCode;
	}

	public String getInsuredUnits() {
		return insuredUnits;
	}

	public void setInsuredUnits(String insuredUnits) {
		this.insuredUnits = insuredUnits;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getHaulType() {
		return haulType;
	}

	public void setHaulType(String haulType) {
		this.haulType = haulType;
	}

	public String getReceivingDept() {
		return receivingDept;
	}

	public void setReceivingDept(String receivingDept) {
		this.receivingDept = receivingDept;
	}

	public String getStartingStation() {
		return startingStation;
	}

	public void setStartingStation(String startingStation) {
		this.startingStation = startingStation;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsAttribute() {
		return goodsAttribute;
	}

	public void setGoodsAttribute(String goodsAttribute) {
		this.goodsAttribute = goodsAttribute;
	}

	public double getInsuredAmount() {
		return insuredAmount;
	}

	public void setInsuredAmount(double insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	public String getTargetDept() {
		return targetDept;
	}

	public void setTargetDept(String targetDept) {
		this.targetDept = targetDept;
	}

	public Date getSendingDate() {
		return sendingDate;
	}

	public void setSendingDate(Date sendingDate) {
		this.sendingDate = sendingDate;
	}

	public Date getDangerDate() {
		return dangerDate;
	}

	public void setDangerDate(Date dangerDate) {
		this.dangerDate = dangerDate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getClaimsType() {
		return claimsType;
	}

	public void setClaimsType(String claimsType) {
		this.claimsType = claimsType;
	}

	public String getOffsetType() {
		return offsetType;
	}

	public void setOffsetType(String offsetType) {
		this.offsetType = offsetType;
	}

	public String getCaseReporter() {
		return caseReporter;
	}

	public void setCaseReporter(String caseReporter) {
		this.caseReporter = caseReporter;
	}

	public String getReportDept() {
		return reportDept;
	}

	public void setReportDept(String reportDept) {
		this.reportDept = reportDept;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	public String getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(String otherCost) {
		this.otherCost = otherCost;
	}

	public double getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getResponsibileDept() {
		return responsibileDept;
	}

	public void setResponsibileDept(String responsibileDept) {
		this.responsibileDept = responsibileDept;
	}

	public double getNormalAmount() {
		return normalAmount;
	}

	public void setNormalAmount(double normalAmount) {
		this.normalAmount = normalAmount;
	}

	public double getActualClaimsAmount() {
		return actualClaimsAmount;
	}

	public void setActualClaimsAmount(double actualClaimsAmount) {
		this.actualClaimsAmount = actualClaimsAmount;
	}

	public double getToCompanyCost() {
		return toCompanyCost;
	}

	public void setToCompanyCost(double toCompanyCost) {
		this.toCompanyCost = toCompanyCost;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getStandardCode() {
		return standardCode;
	}

	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}

	public String getWorkflowNo() {
		return workflowNo;
	}

	public void setWorkflowNo(String workflowNo) {
		this.workflowNo = workflowNo;
	}

	
	public List<FileInfo> getFileInfoList() {
		return fileInfoList;
	}

	public void setFileInfoList(List<FileInfo> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}

	public String getCaseReporterName() {
		return caseReporterName;
	}

	public void setCaseReporterName(String caseReporterName) {
		this.caseReporterName = caseReporterName;
	}

	public String getReportDeptName() {
		return reportDeptName;
	}

	public void setReportDeptName(String reportDeptName) {
		this.reportDeptName = reportDeptName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	
}
