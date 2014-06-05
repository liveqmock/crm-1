package com.deppon.foss.framework.server.components.jobgrid.domain;

import java.io.Serializable;

public class JobWarnning implements Serializable {

	private static final long serialVersionUID = 2336397017791591577L;

	private String id;
	//任务组
	private String jobGroup;
	//任务名
	private String jobName;
	//预警时效
	private String warnType;

	//失败时间  分钟 整数
	private int failTime;
	//失败次数
	private int failCount;
	//电子邮件
	private String email;
    //手机号码
	private String mobile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getWarnType() {
		return warnType;
	}

	public void setWarnType(String warnType) {
		this.warnType = warnType;
	}

	public int getFailTime() {
		return failTime;
	}

	public void setFailTime(int failTime) {
		this.failTime = failTime;
	}

	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

}