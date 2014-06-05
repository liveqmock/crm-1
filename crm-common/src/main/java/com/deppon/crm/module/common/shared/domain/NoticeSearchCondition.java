package com.deppon.crm.module.common.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * Description:
 * SearchCondition.java Create on 2012-10-9 上午11:17:54 
 * @author xhl
 * @version 1.0
 */
public class NoticeSearchCondition extends BaseEntity{
	private String title;
	private String type;
	private String moduleBelong;
	private Date startTime;
	private Date endTime;
	private String createUser;
	private Boolean active;
	private Boolean top;
	private int start;
	private int limit;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModuleBelong() {
		return moduleBelong;
	}
	public void setModuleBelong(String moduleBelong) {
		this.moduleBelong = moduleBelong;
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Boolean getTop() {
		return top;
	}
	public void setTop(Boolean top) {
		this.top = top;
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
	
	

}
