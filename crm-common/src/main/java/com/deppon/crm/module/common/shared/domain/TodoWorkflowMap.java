package com.deppon.crm.module.common.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

public class TodoWorkflowMap extends BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 4985450113818304301L;
	private String fromStatus;
	private String toStatus;
	private String roleId;
	private String workflowName;
	private String applicationName;
	private String applicationStatus;
	private String applicationStatusDesc;
	private String url;

	public String getFromStatus() {
		return fromStatus;
	}

	public void setFromStatus(String fromStatus) {
		this.fromStatus = fromStatus;
	}

	public String getToStatus() {
		return toStatus;
	}

	public void setToStatus(String toStatus) {
		this.toStatus = toStatus;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
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

}
