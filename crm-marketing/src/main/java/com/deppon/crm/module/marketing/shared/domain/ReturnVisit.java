/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisit.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-24
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:回访信息实体<br />
 * </p>
 * @title ReturnVisit.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-24
 */
public class ReturnVisit extends BaseEntity{
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -3990571235751242440L;
	//计划制定人
	/**
	 * 苏玉军添加 
	 */
	//回访问题
	private String problem;
	//解决办法
	private String solution;
	
	private String formulater;
	// 跟踪时间
	private Date schedule;
	
	// 跟踪方式
	private String traceWay;
	
	// 计划ID
	private String planId;
	
	// 日程ID
	private String scheduleId;

	// 会员ID
	private String memberId;
	
	// 联系人ID
	private String linkManId;
	
	// 联系人姓名
	private String linkName;
	
	// 开发维护依据
	private String according;
	
	// 开发维护方式
	private String way;
	
	// 联系人手机
	private String linkManMobile;
	
	// 联系人固话
	private String linkManPhone;	
	
	// 执行时间
	private Date executorTime;
	
	// 执行部门
	private String executeDeptId;
	
	// 执行人员
	private String executorId;
	
	// 回访查询-客户意见（第一条）
	private String needType;
	
	// 回访查询-开发主题
	private String theme;

	// 回访类型
	private String returnVisitType;

	// 开发维护依据描述
	private String accordingDesc;
	
	// 开发维护方式描述
	private String wayDesc;
	
	// 潜散客名称
	private String psCustomerName;
	
	// 会员名称
	private String memberName;
	
	// 日程类型
	private String scheType;
	
	// 执行部门名字
	private String departName;
	
	// 执行人员名字
	private String userName;
	//是否继续营销
	private String continueMarket;
	//问卷id (add by xiaohongye)
	private String surveyId;
	//
	private String type;	
	//客户编码
	private String custNumber;
	//计划类别
	private String projectType;
	
	/**
	 * @return projectType : return the property projectType.  
	 */
	public String getProjectType() {
		return projectType;
	}

	/**
	 * @param projectType : set the property projectType. 
	 */
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	/**
	 * @param surveyId the surveyId to get
	 */
	public String getSurveyId() {
		return surveyId;
	}

	/**
	 * @param surveyId the surveyId to set
	 */
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	/**
	 * @return problem : return the property problem.
	 */
	public String getProblem() {
		return problem;
	}

	/**
	 * @param problem : set the property problem.
	 */
	public void setProblem(String problem) {
		this.problem = problem;
	}

	/**
	 * @return solution : return the property solution.
	 */
	public String getSolution() {
		return solution;
	}

	/**
	 * @param solution : set the property solution.
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}

	/**
	 * @return formulater : return the property formulater.
	 */
	public String getFormulater() {
		return formulater;
	}

	/**
	 * @param formulater : set the property formulater.
	 */
	public void setFormulater(String formulater) {
		this.formulater = formulater;
	}

	/**
	 * @return schedule : return the property schedule.
	 */
	public Date getSchedule() {
		return schedule;
	}

	/**
	 * @param schedule : set the property schedule.
	 */
	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}

	/**
	 * @return traceWay : return the property traceWay.
	 */
	public String getTraceWay() {
		return traceWay;
	}

	/**
	 * @param traceWay : set the property traceWay.
	 */
	public void setTraceWay(String traceWay) {
		this.traceWay = traceWay;
	}

	/**
	 * @return planId : return the property planId.
	 */
	public String getPlanId() {
		return planId;
	}

	/**
	 * @param planId : set the property planId.
	 */
	public void setPlanId(String planId) {
		this.planId = planId;
	}

	/**
	 * @return scheduleId : return the property scheduleId.
	 */
	public String getScheduleId() {
		return scheduleId;
	}

	/**
	 * @param scheduleId : set the property scheduleId.
	 */
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	/**
	 * @return memberId : return the property memberId.
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId : set the property memberId.
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return linkManId : return the property linkManId.
	 */
	public String getLinkManId() {
		return linkManId;
	}

	/**
	 * @param linkManId : set the property linkManId.
	 */
	public void setLinkManId(String linkManId) {
		this.linkManId = linkManId;
	}

	/**
	 * @return linkName : return the property linkName.
	 */
	public String getLinkName() {
		return linkName;
	}

	/**
	 * @param linkName : set the property linkName.
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	/**
	 * @return according : return the property according.
	 */
	public String getAccording() {
		return according;
	}

	/**
	 * @param according : set the property according.
	 */
	public void setAccording(String according) {
		this.according = according;
	}

	/**
	 * @return way : return the property way.
	 */
	public String getWay() {
		return way;
	}

	/**
	 * @param way : set the property way.
	 */
	public void setWay(String way) {
		this.way = way;
	}

	/**
	 * @return linkManMobile : return the property linkManMobile.
	 */
	public String getLinkManMobile() {
		return linkManMobile;
	}

	/**
	 * @param linkManMobile : set the property linkManMobile.
	 */
	public void setLinkManMobile(String linkManMobile) {
		this.linkManMobile = linkManMobile;
	}

	/**
	 * @return linkManPhone : return the property linkManPhone.
	 */
	public String getLinkManPhone() {
		return linkManPhone;
	}

	/**
	 * @param linkManPhone : set the property linkManPhone.
	 */
	public void setLinkManPhone(String linkManPhone) {
		this.linkManPhone = linkManPhone;
	}

	/**
	 * @return executorTime : return the property executorTime.
	 */
	public Date getExecutorTime() {
		return executorTime;
	}

	/**
	 * @param executorTime : set the property executorTime.
	 */
	public void setExecutorTime(Date executorTime) {
		this.executorTime = executorTime;
	}

	/**
	 * @return executeDeptId : return the property executeDeptId.
	 */
	public String getExecuteDeptId() {
		return executeDeptId;
	}

	/**
	 * @param executeDeptId : set the property executeDeptId.
	 */
	public void setExecuteDeptId(String executeDeptId) {
		this.executeDeptId = executeDeptId;
	}

	/**
	 * @return executorId : return the property executorId.
	 */
	public String getExecutorId() {
		return executorId;
	}

	/**
	 * @param executorId : set the property executorId.
	 */
	public void setExecutorId(String executorId) {
		this.executorId = executorId;
	}

	/**
	 * @return needType : return the property needType.
	 */
	public String getNeedType() {
		return needType;
	}

	/**
	 * @param needType : set the property needType.
	 */
	public void setNeedType(String needType) {
		this.needType = needType;
	}

	/**
	 * @return theme : return the property theme.
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param theme : set the property theme.
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * @return returnVisitType : return the property returnVisitType.
	 */
	public String getReturnVisitType() {
		return returnVisitType;
	}

	/**
	 * @param returnVisitType : set the property returnVisitType.
	 */
	public void setReturnVisitType(String returnVisitType) {
		this.returnVisitType = returnVisitType;
	}

	/**
	 * @return accordingDesc : return the property accordingDesc.
	 */
	public String getAccordingDesc() {
		return accordingDesc;
	}

	/**
	 * @param accordingDesc : set the property accordingDesc.
	 */
	public void setAccordingDesc(String accordingDesc) {
		this.accordingDesc = accordingDesc;
	}

	/**
	 * @return wayDesc : return the property wayDesc.
	 */
	public String getWayDesc() {
		return wayDesc;
	}

	/**
	 * @param wayDesc : set the property wayDesc.
	 */
	public void setWayDesc(String wayDesc) {
		this.wayDesc = wayDesc;
	}

	/**
	 * @return psCustomerName : return the property psCustomerName.
	 */
	public String getPsCustomerName() {
		return psCustomerName;
	}

	/**
	 * @param psCustomerName : set the property psCustomerName.
	 */
	public void setPsCustomerName(String psCustomerName) {
		this.psCustomerName = psCustomerName;
	}

	/**
	 * @return memberName : return the property memberName.
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName : set the property memberName.
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * @return scheType : return the property scheType.
	 */
	public String getScheType() {
		return scheType;
	}

	/**
	 * @param scheType : set the property scheType.
	 */
	public void setScheType(String scheType) {
		this.scheType = scheType;
	}

	/**
	 * @return departName : return the property departName.
	 */
	public String getDepartName() {
		return departName;
	}

	/**
	 * @param departName : set the property departName.
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	/**
	 * @return userName : return the property userName.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName : set the property userName.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContinueMarket() {
		return continueMarket;
	}

	public void setContinueMarket(String continueMarket) {
		this.continueMarket = continueMarket;
	}

	/**
	*@return  type;
	*/
	public String getType() {
		return type;
	}

	/**
	 * @param type : set the property type.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return custNumber : return the property custNumber.  
	 */
	public String getCustNumber() {
		return custNumber;
	}

	/**
	 * @param custNumber : set the property custNumber. 
	 */
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	
}
