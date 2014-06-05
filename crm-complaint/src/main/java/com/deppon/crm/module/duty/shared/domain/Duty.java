package com.deppon.crm.module.duty.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

public class Duty extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 工单编号
	private String complaintid;
	// 凭证号
	private String voucherNumber;
	//来电人
	private String caller;
	//处理编号
	private String dealNumber;
	// 业务模式 快递 EXPRESS 零担UNEXPRESS
	private String businessModel;
	// 联系电话
	private String tel;
	//来电客户ID
	private String compManId;
	//来电客户
	private String complaintCust;
	//来电人类型
	private String sendOrReceive;
	//客户等级
	private String custLevel;
	//客户类型
	private String custType;
	//客户要求
	private String custRequire;
	//工单来源
	private String complaintSource;
	//相关客户ID
	private String relatCustId;
	//相关客户
	private String relatCus;
	//相关客户等级
	private String relatCusLevel;
	//相关客户类型
	private String relatCusType;
	//期望时限
	private int timeLimit;
	//期望时限周期
	private String tiLimitCycle;
	//优先级别
	private String pririty;
	//重复工单码
	private String rebindNo;
	//上报人ID
	private String reporterId;
	//上报类型
	private String reportType;
	//上报人
	private String reporter;
	//上报时间
	private Date reportTime;
	//上报内容
	private String reportContent;
	//处理状态
	private String proStatus;
	//保存状态
	private String status;
	//接入人ID
	private String receiveUserId;
	//接入人
	private String receiveUser;
	//接入时间
	private Date receiveTime;
	//责任划分人ID
	private String appDutyUserId;
	//责任划分人
	private String appDutyUser;
	//责任划分时间
	private Date appDutyTime;
	//调查结果
	private String surveyResult;
	//处理经过
	private String processPass;
	//未被接入状态 DutyConstants.UNRECEIVE
	private String unReceiveStatus;
	/**
	 * @return complaintid : return the property complaintid.
	 */
	public String getComplaintid() {
		return complaintid;
	}
	/**
	 * @param complaintid : set the property complaintid.
	 */
	public void setComplaintid(String complaintid) {
		this.complaintid = complaintid;
	}
	/**
	 * @return voucherNumber : return the property voucherNumber.
	 */
	public String getVoucherNumber() {
		return voucherNumber;
	}
	/**
	 * @param voucherNumber : set the property voucherNumber.
	 */
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	/**
	 * @return caller : return the property caller.
	 */
	public String getCaller() {
		return caller;
	}
	/**
	 * @param caller : set the property caller.
	 */
	public void setCaller(String caller) {
		this.caller = caller;
	}
	/**
	 * @return dealNumber : return the property dealNumber.
	 */
	public String getDealNumber() {
		return dealNumber;
	}
	/**
	 * @param dealNumber : set the property dealNumber.
	 */
	public void setDealNumber(String dealNumber) {
		this.dealNumber = dealNumber;
	}
	/**
	 * @return tel : return the property tel.
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel : set the property tel.
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return compManId : return the property compManId.
	 */
	public String getCompManId() {
		return compManId;
	}
	/**
	 * @param compManId : set the property compManId.
	 */
	public void setCompManId(String compManId) {
		this.compManId = compManId;
	}
	/**
	 * @return complaintCust : return the property complaintCust.
	 */
	public String getComplaintCust() {
		return complaintCust;
	}
	/**
	 * @param complaintCust : set the property complaintCust.
	 */
	public void setComplaintCust(String complaintCust) {
		this.complaintCust = complaintCust;
	}
	/**
	 * @return sendOrReceive : return the property sendOrReceive.
	 */
	public String getSendOrReceive() {
		return sendOrReceive;
	}
	/**
	 * @param sendOrReceive : set the property sendOrReceive.
	 */
	public void setSendOrReceive(String sendOrReceive) {
		this.sendOrReceive = sendOrReceive;
	}
	/**
	 * @return custLevel : return the property custLevel.
	 */
	public String getCustLevel() {
		return custLevel;
	}
	/**
	 * @param custLevel : set the property custLevel.
	 */
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	/**
	 * @return custType : return the property custType.
	 */
	public String getCustType() {
		return custType;
	}
	/**
	 * @param custType : set the property custType.
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}
	/**
	 * @return custRequire : return the property custRequire.
	 */
	public String getCustRequire() {
		return custRequire;
	}
	/**
	 * @param custRequire : set the property custRequire.
	 */
	public void setCustRequire(String custRequire) {
		this.custRequire = custRequire;
	}
	/**
	 * @return complaintSource : return the property complaintSource.
	 */
	public String getComplaintSource() {
		return complaintSource;
	}
	/**
	 * @param complaintSource : set the property complaintSource.
	 */
	public void setComplaintSource(String complaintSource) {
		this.complaintSource = complaintSource;
	}
	/**
	 * @return relatCustId : return the property relatCustId.
	 */
	public String getRelatCustId() {
		return relatCustId;
	}
	/**
	 * @param relatCustId : set the property relatCustId.
	 */
	public void setRelatCustId(String relatCustId) {
		this.relatCustId = relatCustId;
	}
	/**
	 * @return relatCus : return the property relatCus.
	 */
	public String getRelatCus() {
		return relatCus;
	}
	/**
	 * @param relatCus : set the property relatCus.
	 */
	public void setRelatCus(String relatCus) {
		this.relatCus = relatCus;
	}
	/**
	 * @return relatCusLevel : return the property relatCusLevel.
	 */
	public String getRelatCusLevel() {
		return relatCusLevel;
	}
	/**
	 * @param relatCusLevel : set the property relatCusLevel.
	 */
	public void setRelatCusLevel(String relatCusLevel) {
		this.relatCusLevel = relatCusLevel;
	}
	/**
	 * @return relatCusType : return the property relatCusType.
	 */
	public String getRelatCusType() {
		return relatCusType;
	}
	/**
	 * @param relatCusType : set the property relatCusType.
	 */
	public void setRelatCusType(String relatCusType) {
		this.relatCusType = relatCusType;
	}
	/**
	 * @return timeLimit : return the property timeLimit.
	 */
	public int getTimeLimit() {
		return timeLimit;
	}
	/**
	 * @param timeLimit : set the property timeLimit.
	 */
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	/**
	 * @return tiLimitCycle : return the property tiLimitCycle.
	 */
	public String getTiLimitCycle() {
		return tiLimitCycle;
	}
	/**
	 * @param tiLimitCycle : set the property tiLimitCycle.
	 */
	public void setTiLimitCycle(String tiLimitCycle) {
		this.tiLimitCycle = tiLimitCycle;
	}
	/**
	 * @return pririty : return the property pririty.
	 */
	public String getPririty() {
		return pririty;
	}
	/**
	 * @param pririty : set the property pririty.
	 */
	public void setPririty(String pririty) {
		this.pririty = pririty;
	}
	/**
	 * @return rebindNo : return the property rebindNo.
	 */
	public String getRebindNo() {
		return rebindNo;
	}
	/**
	 * @param rebindNo : set the property rebindNo.
	 */
	public void setRebindNo(String rebindNo) {
		this.rebindNo = rebindNo;
	}
	/**
	 * @return reporterId : return the property reporterId.
	 */
	public String getReporterId() {
		return reporterId;
	}
	/**
	 * @param reporterId : set the property reporterId.
	 */
	public void setReporterId(String reporterId) {
		this.reporterId = reporterId;
	}
	/**
	 * @return reportType : return the property reportType.
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType : set the property reportType.
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	/**
	 * @return reporter : return the property reporter.
	 */
	public String getReporter() {
		return reporter;
	}
	/**
	 * @param reporter : set the property reporter.
	 */
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	/**
	 * @return reportTime : return the property reportTime.
	 */
	public Date getReportTime() {
		return reportTime;
	}
	/**
	 * @param reportTime : set the property reportTime.
	 */
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	/**
	 * @return reportContent : return the property reportContent.
	 */
	public String getReportContent() {
		return reportContent;
	}
	/**
	 * @param reportContent : set the property reportContent.
	 */
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	/**
	 * @return proStatus : return the property proStatus.
	 */
	public String getProStatus() {
		return proStatus;
	}
	/**
	 * @param proStatus : set the property proStatus.
	 */
	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}
	/**
	 * @return status : return the property status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status : set the property status.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return receiveUserId : return the property receiveUserId.
	 */
	public String getReceiveUserId() {
		return receiveUserId;
	}
	/**
	 * @param receiveUserId : set the property receiveUserId.
	 */
	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}
	/**
	 * @return receiveUser : return the property receiveUser.
	 */
	public String getReceiveUser() {
		return receiveUser;
	}
	/**
	 * @param receiveUser : set the property receiveUser.
	 */
	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}
	/**
	 * @return receiveTime : return the property receiveTime.
	 */
	public Date getReceiveTime() {
		return receiveTime;
	}
	/**
	 * @param receiveTime : set the property receiveTime.
	 */
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	/**
	 * @return appDutyUserId : return the property appDutyUserId.
	 */
	public String getAppDutyUserId() {
		return appDutyUserId;
	}
	/**
	 * @param appDutyUserId : set the property appDutyUserId.
	 */
	public void setAppDutyUserId(String appDutyUserId) {
		this.appDutyUserId = appDutyUserId;
	}
	/**
	 * @return appDutyUser : return the property appDutyUser.
	 */
	public String getAppDutyUser() {
		return appDutyUser;
	}
	/**
	 * @param appDutyUser : set the property appDutyUser.
	 */
	public void setAppDutyUser(String appDutyUser) {
		this.appDutyUser = appDutyUser;
	}
	/**
	 * @return appDutyTime : return the property appDutyTime.
	 */
	public Date getAppDutyTime() {
		return appDutyTime;
	}
	/**
	 * @param appDutyTime : set the property appDutyTime.
	 */
	public void setAppDutyTime(Date appDutyTime) {
		this.appDutyTime = appDutyTime;
	}
	/**
	 * @return surveyResult : return the property surveyResult.
	 */
	public String getSurveyResult() {
		return surveyResult;
	}
	/**
	 * @param surveyResult : set the property surveyResult.
	 */
	public void setSurveyResult(String surveyResult) {
		this.surveyResult = surveyResult;
	}
	/**
	 * @return processPass : return the property processPass.
	 */
	public String getProcessPass() {
		return processPass;
	}
	/**
	 * @param processPass : set the property processPass.
	 */
	public void setProcessPass(String processPass) {
		this.processPass = processPass;
	}

	public String getBusinessModel() {
		return businessModel;
	}

	public void setBusinessModel(String businessModel) {
		this.businessModel = businessModel;
	}
	/**
	 * @return the unReceiveStatus
	 */
	public String getUnReceiveStatus() {
		return unReceiveStatus;
	}
	/**
	 * @param unReceiveStatus the unReceiveStatus to set
	 */
	public void setUnReceiveStatus(String unReceiveStatus) {
		this.unReceiveStatus = unReceiveStatus;
	}
	
}
