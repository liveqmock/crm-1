package com.deppon.crm.module.complaint.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

public class Duty extends BaseEntity{
	//工单编号
	private String complaintid;
	//凭证号
	private String voucherNumber;
	//来电人
	private String caller;
	//处理编号
	private String dealNumber;
	//联系电话
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
	//创建时间
	private Date createTime;
	//创建人ID
	private String createUserId;
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
	//接入锁定标识
	private String lock;

	public String getComplaintid() {
		return complaintid;
	}
	public void setComplaintid(String complaintid) {
		this.complaintid = complaintid;
	}
	public String getVoucherNumber() {
		return voucherNumber;
	}
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	public String getCaller() {
		return caller;
	}
	public void setCaller(String caller) {
		this.caller = caller;
	}
	public String getDealNumber() {
		return dealNumber;
	}
	public void setDealNumber(String dealNumber) {
		this.dealNumber = dealNumber;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCompManId() {
		return compManId;
	}
	public void setCompManId(String compManId) {
		this.compManId = compManId;
	}
	public String getComplaintCust() {
		return complaintCust;
	}
	public void setComplaintCust(String complaintCust) {
		this.complaintCust = complaintCust;
	}
	public String getSendOrReceive() {
		return sendOrReceive;
	}
	public void setSendOrReceive(String sendOrReceive) {
		this.sendOrReceive = sendOrReceive;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getCustRequire() {
		return custRequire;
	}
	public void setCustRequire(String custRequire) {
		this.custRequire = custRequire;
	}
	public String getComplaintSource() {
		return complaintSource;
	}
	public void setComplaintSource(String complaintSource) {
		this.complaintSource = complaintSource;
	}
	public String getRelatCustId() {
		return relatCustId;
	}
	public void setRelatCustId(String relatCustId) {
		this.relatCustId = relatCustId;
	}
	public String getRelatCus() {
		return relatCus;
	}
	public void setRelatCus(String relatCus) {
		this.relatCus = relatCus;
	}
	public String getRelatCusLevel() {
		return relatCusLevel;
	}
	public void setRelatCusLevel(String relatCusLevel) {
		this.relatCusLevel = relatCusLevel;
	}
	public String getRelatCusType() {
		return relatCusType;
	}
	public void setRelatCusType(String relatCusType) {
		this.relatCusType = relatCusType;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getTiLimitCycle() {
		return tiLimitCycle;
	}
	public void setTiLimitCycle(String tiLimitCycle) {
		this.tiLimitCycle = tiLimitCycle;
	}
	public String getPririty() {
		return pririty;
	}
	public void setPririty(String pririty) {
		this.pririty = pririty;
	}
	public String getRebindNo() {
		return rebindNo;
	}
	public void setRebindNo(String rebindNo) {
		this.rebindNo = rebindNo;
	}
	public String getReporterId() {
		return reporterId;
	}
	public void setReporterId(String reporterId) {
		this.reporterId = reporterId;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public String getProStatus() {
		return proStatus;
	}
	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getReceiveUserId() {
		return receiveUserId;
	}
	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}
	public String getReceiveUser() {
		return receiveUser;
	}
	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getAppDutyUserId() {
		return appDutyUserId;
	}
	public void setAppDutyUserId(String appDutyUserId) {
		this.appDutyUserId = appDutyUserId;
	}
	public String getAppDutyUser() {
		return appDutyUser;
	}
	public void setAppDutyUser(String appDutyUser) {
		this.appDutyUser = appDutyUser;
	}
	public Date getAppDutyTime() {
		return appDutyTime;
	}
	public void setAppDutyTime(Date appDutyTime) {
		this.appDutyTime = appDutyTime;
	}
	public String getSurveyResult() {
		return surveyResult;
	}
	public void setSurveyResult(String surveyResult) {
		this.surveyResult = surveyResult;
	}
	public String getLock() {
		return lock;
	}
	public void setLock(String lock) {
		this.lock = lock;
	}

}
