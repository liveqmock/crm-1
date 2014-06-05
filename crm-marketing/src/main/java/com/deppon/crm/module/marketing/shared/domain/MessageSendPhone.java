package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

public class MessageSendPhone extends BaseEntity {
	//任务ID
	private String taskId;
	//电话号码
	private String phone;
	//是否有效 1有效 0无效
	private String valid;
	//状态 0未发送 1失败 2成功
	private String status;
	//发送时间
	private Date sendDate;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	
}
