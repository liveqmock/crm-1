package com.deppon.crm.module.complaint.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

public class DutyHoliday extends BaseEntity{
	//日期
	private Date date;
	//日期格式
	private String dateFormat;
	//星期
	private String weekday;
	//工作日标识
	private String status;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public String getWeekday() {
		return weekday;
	}
	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
