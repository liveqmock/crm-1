package com.deppon.crm.module.common.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

public class TodoWorkflow extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -3159484329333284513L;
	private Long workflowId;
	private String workflowName;
	private String applicationId;
	private String applicationName;
	private String applicationDesc;
	private String applicationStatus;
	private String applicationStatusDesc;
	private String url;
	private String roleId;
	private String roleName;
	private String deptId;
	private String deptName;
	private String todoFlag;

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApplicationDesc() {
		return applicationDesc;
	}

	public void setApplicationDesc(String applicationDesc) {
		this.applicationDesc = applicationDesc;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getApplicationStatusDesc() {
		return applicationStatusDesc;
	}

	public void setApplicationStatusDesc(String applicationStatusDesc) {
		this.applicationStatusDesc = applicationStatusDesc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getTodoFlag() {
		return todoFlag;
	}

	public void setTodoFlag(String todoFlag) {
		this.todoFlag = todoFlag;
	}

	// public void setTodoFlag(String todoFlag) {
	// this.todoFlag = todoFlag;
	// if ("1".equals(todoFlag)) {
	// this.todoFlag = true;
	// } else if ("0".equals(todoFlag)) {
	// this.todoFlag = false;
	// }
	// }

}
