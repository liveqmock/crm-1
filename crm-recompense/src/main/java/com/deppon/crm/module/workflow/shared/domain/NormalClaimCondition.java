package com.deppon.crm.module.workflow.shared.domain;

import java.util.Date;

/**
 * 
 * <p>
 * Description:理赔工作流查询条件<br />
 * </p>
 * @title NormalClaimCondition.java
 * @package com.deppon.crm.module.workflow.shared.domain 
 * @author liuHuan
 * @version 0.1 2013-8-8
 */
public class NormalClaimCondition {
	
	private int start;
	
	private int limit;
	
	//申请人工号
	private String applerId ;
	
	//申请人所在部门id
	private String deptId; 
	
	//申请人所在部门编码
	private String fstandardcode; 
	
	//工作流类型
	private String type;
	
	//1 未提交 2 未审批 3 审批中 4 已同意 5 未同意 6 已退回 7 汇款失败 8 汇款成功
	private String state ;
	
	//申请日期
	private Date startTime; 
	
	//结束日期
	private Date endTime;

	//工作流号
	private String processinstId;
	
	//crm工作流号
	private String workflowNo;
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getApplerId() {
		return applerId;
	}

	public void setApplerId(String applerId) {
		this.applerId = applerId;
	}

	public String getFstandardcode() {
		return fstandardcode;
	}

	public void setFstandardcode(String fstandardcode) {
		this.fstandardcode = fstandardcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProcessinstId() {
		return processinstId;
	}

	public void setProcessinstId(String processinstId) {
		this.processinstId = processinstId;
	}

	public String getWorkflowNo() {
		return workflowNo;
	}

	public void setWorkflowNo(String workflowNo) {
		this.workflowNo = workflowNo;
	} 


	
}
