package com.deppon.crm.module.complaint.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

public class DutyBusinessType extends BaseEntity {
	//业务范围ID
	private String busScopeId;
	//业务类型名称
	private String busTypeName;
	//处理语言
	private String dealLanguage;
	//奖罚类型
	private String rewOrPusType;
	//反馈期限
	private int feedDeadline;
	public String getBusScopeId() {
		return busScopeId;
	}
	public void setBusScopeId(String busScopeId) {
		this.busScopeId = busScopeId;
	}
	public String getBusTypeName() {
		return busTypeName;
	}
	public void setBusTypeName(String busTypeName) {
		this.busTypeName = busTypeName;
	}
	public String getDealLanguage() {
		return dealLanguage;
	}
	public void setDealLanguage(String dealLanguage) {
		this.dealLanguage = dealLanguage;
	}
	public String getRewOrPusType() {
		return rewOrPusType;
	}
	public void setRewOrPusType(String rewOrPusType) {
		this.rewOrPusType = rewOrPusType;
	}
	public int getFeedDeadline() {
		return feedDeadline;
	}
	public void setFeedDeadline(int feedDeadline) {
		this.feedDeadline = feedDeadline;
	}

}
