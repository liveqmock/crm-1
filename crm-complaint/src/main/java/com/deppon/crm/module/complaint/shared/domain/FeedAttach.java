package com.deppon.crm.module.complaint.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

public class FeedAttach extends BaseEntity{
	//责任反馈ID
	private String feedId;
	//附件路径
	private String attachPath;
	//附件名称
	private String attachName;
	//附件描述
	private String describe;
	public String getFeedId() {
		return feedId;
	}
	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
}
