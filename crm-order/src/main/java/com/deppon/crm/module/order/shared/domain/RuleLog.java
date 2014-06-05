package com.deppon.crm.module.order.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

public class RuleLog extends BaseEntity {
	private static final long serialVersionUID = 7282339944821076356L;
	private String id;
	private String event;
	private String content;
	private String operatorUserId;
	private String operatorEmpName;
	private String operatorDeptName;
	private Date operateTime;
	private String orderNumberRuleId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperatorUserId() {
		return operatorUserId;
	}

	public void setOperatorUserId(String operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	public String getOperatorEmpName() {
		return operatorEmpName;
	}

	public void setOperatorEmpName(String operatorEmpName) {
		this.operatorEmpName = operatorEmpName;
	}

	public String getOperatorDeptName() {
		return operatorDeptName;
	}

	public void setOperatorDeptName(String operatorDeptName) {
		this.operatorDeptName = operatorDeptName;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOrderNumberRuleId() {
		return orderNumberRuleId;
	}

	public void setOrderNumberRuleId(String orderNumberRuleId) {
		this.orderNumberRuleId = orderNumberRuleId;
	}
}
