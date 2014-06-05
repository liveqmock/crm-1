package com.deppon.crm.module.complaint.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

public class BasicBusType extends BaseEntity{
	//创建时间
	private Date createTime;
	//创建人ID
	private String createUserId;
	//最后修改时间
	private Date lastUpdateTime;
	//最后修改人
	private String lastModifyUserId;
	//基础资料层级id
	private String levelId;
	//处理语言
	private String dealLanguage;
	//反馈时限
	private String feedbackLimit;
	//处理时限
	private String procLimit;
	//业务类型
	private String busType;
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getLastModifyUserId() {
		return lastModifyUserId;
	}
	public void setLastModifyUserId(String lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	public String getDealLanguage() {
		return dealLanguage;
	}
	public void setDealLanguage(String dealLanguage) {
		this.dealLanguage = dealLanguage;
	}
	public String getFeedbackLimit() {
		return feedbackLimit;
	}
	public void setFeedbackLimit(String feedbackKLimit) {
		this.feedbackLimit = feedbackKLimit;
	}
	public String getProcLimit() {
		return procLimit;
	}
	public void setProcLimit(String procLimit) {
		this.procLimit = procLimit;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}

}
