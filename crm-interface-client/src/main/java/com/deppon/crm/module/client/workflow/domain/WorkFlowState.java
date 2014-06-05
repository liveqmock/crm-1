package com.deppon.crm.module.client.workflow.domain;

import java.util.Date;

/**
 * 工作流状态信息
 * */
public class WorkFlowState {
	
	public WorkFlowState() {

	}
	
	/**工作流号*/
	private String processInstId;
	/**工作流状态 4－同意   5－不同意*/
	private String condition;
	/**最后审批时间*/
	private Date approveDate;
	/**编码*/
	private String bizCode;
	
	public String getBizCode() {
		return bizCode;
	}
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getProcessInstId() {
		return processInstId;
	}
	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}
	public Date getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
}
