package com.deppon.crm.module.complaint.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

public class DutyResult extends BaseEntity{
	//工单责任ID
	private String dutyId;
	//划分类型
	private String divideType;
	//责任人ID
	private String dutyPerId;
	//责任人名称
	private String DutyPerName;
	//奖罚类型
	private String rewOrPusType;
	//反馈时限
	private Date dutyDeadline;
	//业务环节
	private String BusAspect;
	//业务范围
	private String BusScope;
	//业务类型
	private String BusType;
	//处理语言
	private String dealLanguage;
	//责任状态
	private String DutyStatus;
	//调查结果
	private String surveyResult;
	//工单详情状态
	private String status;
	//修改人
	private String modifyUserId;
	//修改时间
	private Date modifyTime;
	//修改前ID
	private String befModDetId;
	public String getDutyId() {
		return dutyId;
	}
	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}
	public String getDivideType() {
		return divideType;
	}
	public void setDivideType(String divideType) {
		this.divideType = divideType;
	}
	public String getDutyPerId() {
		return dutyPerId;
	}
	public void setDutyPerId(String dutyPerId) {
		this.dutyPerId = dutyPerId;
	}
	public String getDutyPerName() {
		return DutyPerName;
	}
	public void setDutyPerName(String dutyPerName) {
		DutyPerName = dutyPerName;
	}
	public String getRewOrPusType() {
		return rewOrPusType;
	}
	public void setRewOrPusType(String rewOrPusType) {
		this.rewOrPusType = rewOrPusType;
	}
	public Date getDutyDeadline() {
		return dutyDeadline;
	}
	public void setDutyDeadline(Date dutyDeadline) {
		this.dutyDeadline = dutyDeadline;
	}
	public String getBusAspect() {
		return BusAspect;
	}
	public void setBusAspect(String busAspect) {
		BusAspect = busAspect;
	}
	public String getBusScope() {
		return BusScope;
	}
	public void setBusScope(String busScope) {
		BusScope = busScope;
	}
	public String getBusType() {
		return BusType;
	}
	public void setBusType(String busType) {
		BusType = busType;
	}
	public String getDealLanguage() {
		return dealLanguage;
	}
	public void setDealLanguage(String dealLanguage) {
		this.dealLanguage = dealLanguage;
	}
	public String getDutyStatus() {
		return DutyStatus;
	}
	public void setDutyStatus(String dutyStatus) {
		DutyStatus = dutyStatus;
	}
	public String getSurveyResult() {
		return surveyResult;
	}
	public void setSurveyResult(String surveyResult) {
		this.surveyResult = surveyResult;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getBefModDetId() {
		return befModDetId;
	}
	public void setBefModDetId(String befModDetId) {
		this.befModDetId = befModDetId;
	}
	

}
