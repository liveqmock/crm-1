package com.deppon.foss.framework.server.components.jobgrid.domain;

/**作者：zouming
 *创建时间：2012-12-31
 *最后修改时间：2012-12-31
 *描述：
 */
public class JobScheduleCondition {
	private String id;
	private String jobName;
	private String jobGroup;
	private String triggerName;
	/**
	 * 实例名称
	 */
	private String jobInstance;
	private int start;
	private int limit;
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getJobInstance() {
		return jobInstance;
	}
	public void setJobInstance(String jobInstance) {
		this.jobInstance = jobInstance;
	}
	
	
}
