package com.deppon.crm.module.duty.shared.domain;

import java.util.Date;
/**
 * 
 * <p>
 * Description:工单责任划分结果<br />
 * </p>
 * @title ProcResult.java
 * @package com.deppon.crm.module.duty.shared.domain 
 * @author ouy
 * @version 0.1 2013-3-5
 */
public class DutyResult {
	
	private String id;//id
	private Date modifyTime;//修改时间
	private String modifyUser;//修改人
	private String dutyId;//工单责任ID
	private String divideType;//划分类型
	private String old_dealType;//划分类型(辅助字段，旧的divideType)
	private String dutyPerId;//责任人ID
	private String old_dutyPerId;//划分类型(辅助字段，旧的dutyPerId)
	private String dutyPerName;//责任人名称
	private String reworpusType;//奖罚类型
	private Date dutyDeadLine;//反馈时限
	private String busAspect;//业务环节
	private String busScope;//业务范围
	private String busType;//业务类型
	private String busAspectId;//业务环节
	private String busScopeId;//业务范围
	private String busTypeId;//业务类型
	private String dealLanguage;//处理语言
	private String dutyStatus;//责任状态
	private String surveyResult;//调查结果
	private String status;//工单详情状态
	private String befmoddeId;//修改前ID
	private String businessLink;//业务环节
	private String virtualCode;//虚拟编码
	
	private String dutyDeptId;//责任部门编号/个人所在责任部门编号
	//是否为 400 责任部门，0 普通部门，1特殊部门。-1  三个经营本部外的部门
	private String dutyDepartmentCC;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}
	/**
	 * @param modifyTime the modifyTime to set
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * @return the modifyUser
	 */
	public String getModifyUser() {
		return modifyUser;
	}
	/**
	 * @param modifyUser the modifyUser to set
	 */
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	/**
	 * @return the divideType
	 */
	public String getDivideType() {
		return divideType;
	}
	/**
	 * @param divideType the divideType to set
	 */
	public void setDivideType(String divideType) {
		this.divideType = divideType;
	}
	/**
	 * @return the dutyPerId
	 */
	public String getDutyPerId() {
		return dutyPerId;
	}
	/**
	 * @param dutyPerId the dutyPerId to set
	 */
	public void setDutyPerId(String dutyPerId) {
		this.dutyPerId = dutyPerId;
	}
	/**
	 * @return the dutyPerName
	 */
	public String getDutyPerName() {
		return dutyPerName;
	}
	/**
	 * @param dutyPerName the dutyPerName to set
	 */
	public void setDutyPerName(String dutyPerName) {
		this.dutyPerName = dutyPerName;
	}
	/**
	 * @return the reworpusType
	 */
	public String getReworpusType() {
		return reworpusType;
	}
	/**
	 * @param reworpusType the reworpusType to set
	 */
	public void setReworpusType(String reworpusType) {
		this.reworpusType = reworpusType;
	}
	/**
	 * @return the dutyDeadLine
	 */
	public Date getDutyDeadLine() {
		return dutyDeadLine;
	}
	/**
	 * @param dutyDeadLine the dutyDeadLine to set
	 */
	public void setDutyDeadLine(Date dutyDeadLine) {
		this.dutyDeadLine = dutyDeadLine;
	}
	/**
	 * @return the busAspect
	 */
	public String getBusAspect() {
		return busAspect;
	}
	/**
	 * @param busAspect the busAspect to set
	 */
	public void setBusAspect(String busAspect) {
		this.busAspect = busAspect;
	}
	/**
	 * @return the busScope
	 */
	public String getBusScope() {
		return busScope;
	}
	/**
	 * @param busScope the busScope to set
	 */
	public void setBusScope(String busScope) {
		this.busScope = busScope;
	}
	/**
	 * @return the busType
	 */
	public String getBusType() {
		return busType;
	}
	/**
	 * @param busType the busType to set
	 */
	public void setBusType(String busType) {
		this.busType = busType;
	}
	/**
	 * @return the dealLanguage
	 */
	public String getDealLanguage() {
		return dealLanguage;
	}
	/**
	 * @param dealLanguage the dealLanguage to set
	 */
	public void setDealLanguage(String dealLanguage) {
		this.dealLanguage = dealLanguage;
	}
	/**
	 * @return the dutyStatus
	 */
	public String getDutyStatus() {
		return dutyStatus;
	}
	/**
	 * @param dutyStatus the dutyStatus to set
	 */
	public void setDutyStatus(String dutyStatus) {
		this.dutyStatus = dutyStatus;
	}
	/**
	 * @return the surveyResult
	 */
	public String getSurveyResult() {
		return surveyResult;
	}
	/**
	 * @param surveyResult the surveyResult to set
	 */
	public void setSurveyResult(String surveyResult) {
		this.surveyResult = surveyResult;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the dutyId
	 */
	public String getDutyId() {
		return dutyId;
	}
	/**
	 * @param dutyId the dutyId to set
	 */
	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}
	/**
	 * @return the befmoddeId
	 */
	public String getBefmoddeId() {
		return befmoddeId;
	}
	/**
	 * @param befmoddeId the befmoddeId to set
	 */
	public void setBefmoddeId(String befmoddeId) {
		this.befmoddeId = befmoddeId;
	}
	/**
	 * @return the busAspectId
	 */
	public String getBusAspectId() {
		return busAspectId;
	}
	/**
	 * @param busAspectId the busAspectId to set
	 */
	public void setBusAspectId(String busAspectId) {
		this.busAspectId = busAspectId;
	}
	/**
	 * @return the busScopeId
	 */
	public String getBusScopeId() {
		return busScopeId;
	}
	/**
	 * @param busScopeId the busScopeId to set
	 */
	public void setBusScopeId(String busScopeId) {
		this.busScopeId = busScopeId;
	}
	/**
	 * @return the busTypeId
	 */
	public String getBusTypeId() {
		return busTypeId;
	}
	/**
	 * @param busTypeId the busTypeId to set
	 */
	public void setBusTypeId(String busTypeId) {
		this.busTypeId = busTypeId;
	}
	/**
	 * @return businessLink : return the property businessLink.
	 */
	public String getBusinessLink() {
		return businessLink;
	}
	/**
	 * @param businessLink : set the property businessLink.
	 */
	public void setBusinessLink(String businessLink) {
		this.businessLink = businessLink;
	}
	/**
	 * @return the virtualCode
	 */
	public String getVirtualCode() {
		return virtualCode;
	}
	/**
	 * @param virtualCode the virtualCode to set
	 */
	public void setVirtualCode(String virtualCode) {
		this.virtualCode = virtualCode;
	}
	/**
	 * @return the old_dealType
	 */
	public String getOld_dealType() {
		return old_dealType;
	}
	/**
	 * @param old_dealType the old_dealType to set
	 */
	public void setOld_dealType(String old_dealType) {
		this.old_dealType = old_dealType;
	}
	/**
	 * @return the old_dutyPerId
	 */
	public String getOld_dutyPerId() {
		return old_dutyPerId;
	}
	/**
	 * @param old_dutyPerId the old_dutyPerId to set
	 */
	public void setOld_dutyPerId(String old_dutyPerId) {
		this.old_dutyPerId = old_dutyPerId;
	}
	/**
	 * @return the dutyDepartmentCC
	 */
	public String getDutyDepartmentCC() {
		return dutyDepartmentCC;
	}
	/**
	 * @param dutyDepartmentCC the dutyDepartmentCC to set
	 */
	public void setDutyDepartmentCC(String dutyDepartmentCC) {
		this.dutyDepartmentCC = dutyDepartmentCC;
	}
	/**
	 * @return the dutyDeptId
	 */
	public String getDutyDeptId() {
		return dutyDeptId;
	}
	/**
	 * @param dutyDeptId the dutyDeptId to set
	 */
	public void setDutyDeptId(String dutyDeptId) {
		this.dutyDeptId = dutyDeptId;
	}
	
}
