/**
 * Filename:	TaskResult.java
 * Description:
 * Copyright:	Copyright (c)2011
 * Company:		IBM1
 * @author:		
 * @version:	
 * create at:	2012-4-23 下午2:33:55
 *
 * Modification History:
 * Date			Author			Version			Description
 * ------------------------------------------------------------------------
 * 2012-4-23	    
 */

package com.deppon.crm.module.complaint.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TaskResult {

	/**
	 * @Fields complaintId : 工单ID
	 */
	private BigDecimal complaintId;
	/**
	 * 单号，即 凭证号
	 */
	private String bill;
	/**
	 * 处理编号
	 */
	private String dealbill;

	/**
	 * 重复工单绑定码,即重复投拆绑定
	 */
	private String rebindno;
	// 重复工单码
	private String recomcode;

	/**
	 * r 为投诉 e 为异常
	 */
	private String reporttype;

	// 投诉人
	private String compman;

	// 处理状态
	private String prostatus;

	// 投诉级别
	private String complevel;

	// 上报时间
	private Date reporttime;

	/**
	 * @Fields resultId : 任务部门处理结果ID
	 */
	private BigDecimal resultId;
	//业务类型
	private String businessModel;
	// 反馈部门
	private String taskpartment;

	// 任务属性
	private String taskproperties;
	
	public String getBusinessModel() {
		return businessModel;
	}
	public void setBusinessModel(String businessModel) {
		this.businessModel = businessModel;
	}
	//是否有过退回
	private String isBack;
	private BigDecimal processtimelimit;
	//状态
	private String stat;
	
	//延时申请状态（0：申请延时 1：申请同意 2：申请拒绝）
	private String delay;
	// 反馈时限
	private BigDecimal feedtimelimit;
	// 部门类型（0 任务部门  1出发部门）
	private String deptType;
	/**
	 * set get 方法
	 */
	// processtimelimit get方法
	public BigDecimal getProcesstimelimit() {
		return processtimelimit;
	}
	// processtimelimit set方法
	public void setProcesstimelimit(BigDecimal processtimelimit) {
		this.processtimelimit = processtimelimit;
	}
	// taskproperties get方法
	public String getTaskproperties() {
		return taskproperties;
	}
	// taskproperties set方法
	public void setTaskproperties(String taskproperties) {
		this.taskproperties = taskproperties;
	}
	// complaintId get方法
	public BigDecimal getComplaintId() {
		return complaintId;
	}
	// complaintId set方法
	public void setComplaintId(BigDecimal complaintId) {
		this.complaintId = complaintId;
	}
	// dealbill get方法
	public String getDealbill() {
		return dealbill;
	}
	// dealbill set方法
	public void setDealbill(String dealbill) {
		this.dealbill = dealbill;
	}
	// rebindno get方法
	public String getRebindno() {
		return rebindno;
	}
	// rebindno set方法
	public void setRebindno(String rebindno) {
		this.rebindno = rebindno;
	}
	// reporttype get方法
	public String getReporttype() {
		return reporttype;
	}
	// reporttype set方法
	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}
	// compman get方法
	public String getCompman() {
		return compman;
	}
	// compman set方法
	public void setCompman(String compman) {
		this.compman = compman;
	}
	// prostatus get方法
	public String getProstatus() {
		return prostatus;
	}
	// prostatus set方法
	public void setProstatus(String prostatus) {
		this.prostatus = prostatus;
	}
	// complevel get方法
	public String getComplevel() {
		return complevel;
	}
	// complevel set方法
	public void setComplevel(String complevel) {
		this.complevel = complevel;
	}
	// reporttime get方法
	public Date getReporttime() {
		return reporttime;
	}
	// reporttime set方法
	public void setReporttime(Date reporttime) {
		this.reporttime = reporttime;
	}
	// reporttime get方法
	public BigDecimal getResultId() {
		return resultId;
	}
	// reporttime set方法
	public void setResultId(BigDecimal resultId) {
		this.resultId = resultId;
	}
	// taskpartment get方法
	public String getTaskpartment() {
		return taskpartment;
	}
	// taskpartment set方法
	public void setTaskpartment(String taskpartment) {
		this.taskpartment = taskpartment;
	}
	// feedtimelimit get方法
	public BigDecimal getFeedtimelimit() {
		return feedtimelimit;
	}
	// feedtimelimit set方法
	public void setFeedtimelimit(BigDecimal feedtimelimit) {
		this.feedtimelimit = feedtimelimit;
	}
	// recomcode get方法
	public String getRecomcode() {
		return recomcode;
	}
	// recomcode set方法
	public void setRecomcode(String recomcode) {
		this.recomcode = recomcode;
	}
	// delay get方法
	public String getDelay() {
		return delay;
	}
	// delay set方法
	public void setDelay(String delay) {
		this.delay = delay;
	}
	// delay get方法
	public String getIsBack() {
		return isBack;
	}
	// delay set方法
	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}
	// delay get方法
	public String getStat() {
		return stat;
	}
	// bill get方法
	public String getBill() {
		return bill;
	}
	// bill set方法
	public void setBill(String bill) {
		this.bill = bill;
	}
	// stat set方法
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getDeptType() {
		return deptType;
	}
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}
}
