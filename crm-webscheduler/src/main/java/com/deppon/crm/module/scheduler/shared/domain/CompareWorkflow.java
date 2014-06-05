package com.deppon.crm.module.scheduler.shared.domain;

public class CompareWorkflow {
	//月结 
	public static final String CONTRACT_TYPE ="WFS_HT_001";
	//常规理陪
	public static final String NOMALRECOM_TYPE ="WFS_LP_001";
	//多赔
	public static final String FASTRECOM_TYPE ="WFS_LP_002";
	
	
	// WFS_HT_001 月结 WFS_LP_001 常规理陪 WFS_LP_002 多赔
	private String workflowType;
	// 包含合同，多赔，常规理赔编码
	private String bizNum;
	// 工作流号
	private String workflowNum;
	// 状态
	private String state;
	// 审批时间
	private String approveTime;

	public String getWorkflowType() {
		return workflowType;
	}

	public void setWorkflowType(String workflowType) {
		this.workflowType = workflowType;
	}

	public String getBizNum() {
		return bizNum;
	}

	public void setBizNum(String bizNum) {
		this.bizNum = bizNum;
	}

	public String getWorkflowNum() {
		return workflowNum;
	}

	public void setWorkflowNum(String workflowNum) {
		this.workflowNum = workflowNum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}

}
