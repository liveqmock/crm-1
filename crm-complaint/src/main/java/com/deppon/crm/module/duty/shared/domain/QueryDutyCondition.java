package com.deppon.crm.module.duty.shared.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * Description:工单责任查询条件<br />
 * </p>
 * 
 * @title QueryDutyCondition.java
 * @package com.deppon.crm.module.duty.shared.domain
 * @author 钟琼
 * @version 0.1 2013-3-7
 */
public class QueryDutyCondition extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String appDutyUser; // 责任划分人
	private Date reportTimeBegin; // 上报开始时间
	private Date reportTimeEnd; // 上报结束时间
	private String typeId; // 类型编号
	private String typeName; // 类型名称
	private Date feedBackTimeBegin; // 反馈开始时间
	private Date feedBackTimeEnd; // 反馈结束时间
	private String surveyResult; // 调查结果
	private Date appDutyTimeBegin; // 责任划分开始时间
	private Date appDutyTimeEnd; // 责任划分结束时间
	private String dutyStates; // 责任状态
	private String dutyDept; // 责任部门
	private String feedbackDept; // 反馈部门
	private String callCenterUser;// 呼叫中心审批人
	private boolean dutyInspector; //是否400人员，有质检员角色则是
	// 反馈人
	private String feedBackPerson;
	//统计员所具有的的权限部门
	private List<String> deptIds = new ArrayList<String>();
	//角色ID
	private String roleId;
	
	// 业务模式 快递 EXPRESS 零担UNEXPRESS
	private String businessModel;
	
	//任务部门所在事业部
	private String bussDeptCode;
	
	/**
	 * @return roleId : return the property roleId.
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId : set the property roleId.
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return deptIds : return the property deptIds.
	 */
	public List<String> getDeptIds() {
		return deptIds;
	}

	/**
	 * @return appDutyUser : return the property appDutyUser.
	 */
	public String getAppDutyUser() {
		return appDutyUser;
	}

	/**
	 * @param appDutyUser
	 *            : set the property appDutyUser.
	 */
	public void setAppDutyUser(String appDutyUser) {
		this.appDutyUser = appDutyUser;
	}

	/**
	 * @return reportTimeBegin : return the property reportTimeBegin.
	 */
	public Date getReportTimeBegin() {
		return reportTimeBegin;
	}

	/**
	 * @param reportTimeBegin
	 *            : set the property reportTimeBegin.
	 */
	public void setReportTimeBegin(Date reportTimeBegin) {
		this.reportTimeBegin = reportTimeBegin;
	}

	/**
	 * @return reportTimeEnd : return the property reportTimeEnd.
	 */
	public Date getReportTimeEnd() {
		return reportTimeEnd;
	}

	/**
	 * @param reportTimeEnd
	 *            : set the property reportTimeEnd.
	 */
	public void setReportTimeEnd(Date reportTimeEnd) {
		this.reportTimeEnd = reportTimeEnd;
	}

	/**
	 * @return typeId : return the property typeId.
	 */
	public String getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId
	 *            : set the property typeId.
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return typeName : return the property typeName.
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName
	 *            : set the property typeName.
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return feedBackTimeBegin : return the property feedBackTimeBegin.
	 */
	public Date getFeedBackTimeBegin() {
		return feedBackTimeBegin;
	}

	/**
	 * @param feedBackTimeBegin
	 *            : set the property feedBackTimeBegin.
	 */
	public void setFeedBackTimeBegin(Date feedBackTimeBegin) {
		this.feedBackTimeBegin = feedBackTimeBegin;
	}

	/**
	 * @return feedBackTimeEnd : return the property feedBackTimeEnd.
	 */
	public Date getFeedBackTimeEnd() {
		return feedBackTimeEnd;
	}

	/**
	 * @param feedBackTimeEnd
	 *            : set the property feedBackTimeEnd.
	 */
	public void setFeedBackTimeEnd(Date feedBackTimeEnd) {
		this.feedBackTimeEnd = feedBackTimeEnd;
	}

	/**
	 * @return surveyResult : return the property surveyResult.
	 */
	public String getSurveyResult() {
		return surveyResult;
	}

	/**
	 * @param surveyResult
	 *            : set the property surveyResult.
	 */
	public void setSurveyResult(String surveyResult) {
		this.surveyResult = surveyResult;
	}

	/**
	 * @return appDutyTimeBegin : return the property appDutyTimeBegin.
	 */
	public Date getAppDutyTimeBegin() {
		return appDutyTimeBegin;
	}

	/**
	 * @param appDutyTimeBegin
	 *            : set the property appDutyTimeBegin.
	 */
	public void setAppDutyTimeBegin(Date appDutyTimeBegin) {
		this.appDutyTimeBegin = appDutyTimeBegin;
	}

	/**
	 * @return appDutyTimeEnd : return the property appDutyTimeEnd.
	 */
	public Date getAppDutyTimeEnd() {
		return appDutyTimeEnd;
	}

	/**
	 * @param appDutyTimeEnd
	 *            : set the property appDutyTimeEnd.
	 */
	public void setAppDutyTimeEnd(Date appDutyTimeEnd) {
		this.appDutyTimeEnd = appDutyTimeEnd;
	}

	/**
	 * @return dutyStates : return the property dutyStates.
	 */
	public String getDutyStates() {
		return dutyStates;
	}

	/**
	 * @param dutyStates
	 *            : set the property dutyStates.
	 */
	public void setDutyStates(String dutyStates) {
		this.dutyStates = dutyStates;
	}

	/**
	 * @return dutyDept : return the property dutyDept.
	 */
	public String getDutyDept() {
		return dutyDept;
	}

	/**
	 * @param dutyDept
	 *            : set the property dutyDept.
	 */
	public void setDutyDept(String dutyDept) {
		this.dutyDept = dutyDept;
	}

	/**
	 * @return feedbackDept : return the property feedbackDept.
	 */
	public String getFeedbackDept() {
		return feedbackDept;
	}

	/**
	 * @param feedbackDept
	 *            : set the property feedbackDept.
	 */
	public void setFeedbackDept(String feedbackDept) {
		this.feedbackDept = feedbackDept;
	}

	/**
	 * @return callCenterUser : return the property callCenterUser.
	 */
	public String getCallCenterUser() {
		return callCenterUser;
	}

	/**
	 * @param callCenterUser
	 *            : set the property callCenterUser.
	 */
	public void setCallCenterUser(String callCenterUser) {
		this.callCenterUser = callCenterUser;
	}

	/**
	 * @return feedBackPerson : return the property feedBackPerson.
	 */
	public String getFeedBackPerson() {
		return feedBackPerson;
	}

	/**
	 * @param feedBackPerson
	 *            : set the property feedBackPerson.
	 */
	public void setFeedBackPerson(String feedBackPerson) {
		this.feedBackPerson = feedBackPerson;
	}

	public String getBusinessModel() {
		return businessModel;
	}

	public void setBusinessModel(String businessModel) {
		this.businessModel = businessModel;
	}

	public boolean getDutyInspector() {
		return dutyInspector;
	}

	public void setDutyInspector(boolean dutyInspector) {
		this.dutyInspector = dutyInspector;
	}

	public String getBussDeptCode() {
		return bussDeptCode;
	}

	public void setBussDeptCode(String bussDeptCode) {
		this.bussDeptCode = bussDeptCode;
	}
	
}
