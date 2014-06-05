package com.deppon.crm.module.scheduler.shared.domain;

import java.util.Date;

/**
 * @description 定时任务调度控制.
 * @author 安小虎
 * @version 0.1
 * @date 2012-5-31
 */

public class SchedulingControl {
	private Integer id;
	private String tableName;// 表名
	private Date reportTime;// 报表完成时间
	private Date buzDate;// 业务时间
	private Date crmTime;// CRM执行开始时间
	private Date crmEndTime;// CRM执行结束时间
	private String state;// 状态：0 or null crm未处理，1crm执行成功，2crm执行异常

	public SchedulingControl() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public Date getBuzDate() {
		return buzDate;
	}

	public void setBuzDate(Date buzDate) {
		this.buzDate = buzDate;
	}

	public Date getCrmTime() {
		return crmTime;
	}

	public void setCrmTime(Date crmTime) {
		this.crmTime = crmTime;
	}

	public Date getCrmEndTime() {
		return crmEndTime;
	}

	public void setCrmEndTime(Date crmEndTime) {
		this.crmEndTime = crmEndTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "SchedulingControl [id=" + id + ", tableName=" + tableName
				+ ", reportTime=" + reportTime + ", crmTime=" + crmTime
				+ ", crmServiceName=" + ", state=" + state + "]";
	}

}
