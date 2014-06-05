package com.deppon.crm.module.gis.shared.domain;

import com.deppon.crm.module.interfaces.order.BaseEntity;

public class TaskEntity extends BaseEntity{
	private String id;
	private String taskType;
	private String taskParameter;
	private int taskStatus;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getTaskParameter() {
		return taskParameter;
	}
	public void setTaskParameter(String taskParameter) {
		this.taskParameter = taskParameter;
	}
	public int getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}

}
