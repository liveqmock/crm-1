package com.deppon.crm.module.duty.shared.domain;

import java.util.Date;

/**   
 * <p>
 * 工单责任审批查询条件封装<br />
 * </p>
 * @title DutyApprovalQueryCondition.java
 * @package com.deppon.crm.module.duty.shared.domain 
 * @author 苏玉军
 * @version 0.1 2013-3-9
 */

public class DutyApprovalQueryCondition {
	//反馈人
	private String feedBackPerson;
	//查询编码类型
	private String codeType;
	//编码
	private String code;
	//责任状态
	private String dutyStatus;
	//反馈部门
	private String feedBackDept;
	//反馈查询开始时间
	private Date feedBackStartTime;
	//反馈查询结束时间
	private Date feedBackEndTime;
	//调查结果
	private String surveyResult;
	/**
	 * @return feedBackPerson : return the property feedBackPerson.
	 */
	public String getFeedBackPerson() {
		return feedBackPerson;
	}
	/**
	 * @param feedBackPerson : set the property feedBackPerson.
	 */
	public void setFeedBackPerson(String feedBackPerson) {
		this.feedBackPerson = feedBackPerson;
	}
	/**
	 * @return codeType : return the property codeType.
	 */
	public String getCodeType() {
		return codeType;
	}
	/**
	 * @param codeType : set the property codeType.
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	/**
	 * @return code : return the property code.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code : set the property code.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return dutyStatus : return the property dutyStatus.
	 */
	public String getDutyStatus() {
		return dutyStatus;
	}
	/**
	 * @param dutyStatus : set the property dutyStatus.
	 */
	public void setDutyStatus(String dutyStatus) {
		this.dutyStatus = dutyStatus;
	}
	/**
	 * @return feedBackDept : return the property feedBackDept.
	 */
	public String getFeedBackDept() {
		return feedBackDept;
	}
	/**
	 * @param feedBackDept : set the property feedBackDept.
	 */
	public void setFeedBackDept(String feedBackDept) {
		this.feedBackDept = feedBackDept;
	}
	/**
	 * @return feedBackStartTime : return the property feedBackStartTime.
	 */
	public Date getFeedBackStartTime() {
		return feedBackStartTime;
	}
	/**
	 * @param feedBackStartTime : set the property feedBackStartTime.
	 */
	public void setFeedBackStartTime(Date feedBackStartTime) {
		this.feedBackStartTime = feedBackStartTime;
	}
	/**
	 * @return feedBackEndTime : return the property feedBackEndTime.
	 */
	public Date getFeedBackEndTime() {
		return feedBackEndTime;
	}
	/**
	 * @param feedBackEndTime : set the property feedBackEndTime.
	 */
	public void setFeedBackEndTime(Date feedBackEndTime) {
		this.feedBackEndTime = feedBackEndTime;
	}
	/**
	 * @return surveyResult : return the property surveyResult.
	 */
	public String getSurveyResult() {
		return surveyResult;
	}
	/**
	 * @param surveyResult : set the property surveyResult.
	 */
	public void setSurveyResult(String surveyResult) {
		this.surveyResult = surveyResult;
	}
}
