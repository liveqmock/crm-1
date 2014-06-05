package com.deppon.crm.module.recompense.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>
 * Description:提供CC系统调用理赔历史的实体类<br />
 * </p>
 * @title RecompenseForCC.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-8-19
 */
public class RecompenseForCC extends BaseEntity {

	// 理赔类型
	private String recompenseType;
	// 报案部门
	private String reportDept;
	// 单号
	private String waybillNumber;
	// 理赔金额
	private String recompenseAmount;
	// 理赔方式
	private String recompenseMethod;
	// 上报时间
	private Date reportDate;
	// 处理状态
	private String status;

	public String getRecompenseType() {
		return recompenseType;
	}

	public void setRecompenseType(String recompenseType) {
		this.recompenseType = recompenseType;
	}

	public String getReportDept() {
		return reportDept;
	}

	public void setReportDept(String reportDept) {
		this.reportDept = reportDept;
	}

	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	public String getRecompenseAmount() {
		return recompenseAmount;
	}

	public void setRecompenseAmount(String recompenseAmount) {
		this.recompenseAmount = recompenseAmount;
	}

	public String getRecompenseMethod() {
		return recompenseMethod;
	}

	public void setRecompenseMethod(String recompenseMethod) {
		this.recompenseMethod = recompenseMethod;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

}
