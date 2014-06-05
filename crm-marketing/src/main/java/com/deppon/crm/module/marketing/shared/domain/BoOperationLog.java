package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

public class BoOperationLog extends BaseEntity {

	/** 描述 (@author: huangzhanming) */

	private static final long serialVersionUID = 6170799490954396151L;
	// 商机日志Id number SEQ_ID_BoOperationLog
	// 商机id number
	private String boId;
	// 前一个阶段 varchar2(20)
	private String prevStep;
	// 当前阶段 varchar2(20)
	private String currentStep;
	// 操作人 number
	private String operator;
	// 操作时间 timestamp
	private Date operationTime;

	public String getBoId() {
		return boId;
	}

	public void setBoId(String boId) {
		this.boId = boId;
	}

	public String getPrevStep() {
		return prevStep;
	}

	public void setPrevStep(String prevStep) {
		this.prevStep = prevStep;
	}

	public String getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(String currentStep) {
		this.currentStep = currentStep;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

}
