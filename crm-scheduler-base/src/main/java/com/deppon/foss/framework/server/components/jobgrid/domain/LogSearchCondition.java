package com.deppon.foss.framework.server.components.jobgrid.domain;

import java.util.Date;

/**
 * 
 * <p>
 * Description:日志查询条件<br />
 * </p>
 * 
 * @title LogSearchCondition.java
 * @package com.deppon.crm.module.scheduler.shared.domain
 * @author 华龙
 * @version 0.1 2012-12-28
 */
public class LogSearchCondition {
	//触发器名称
	private String triggerName;
	//任务分组
	private String jobGroup;
	//任务名称
	private String jobName;
	//开始时间
	private Date startDate;
	//结束时间
	private Date endDate;

	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	

}
