package com.deppon.crm.module.marketing.server.action;

import java.util.Date;
/**
 * Description:封装开发日程查询view<br />
 * @title DevelopScheduleVO.java
 * @package com.deppon.crm.module.marketing.server.action 
 * @author 毛建强
 * @version 0.1 2012-4-1
 */
public class DevelopScheduleVO {
	//id
	private String id;
	//计划Id
	private String planId; 
	//计划类别
	private String planType;
	//合作意向
	private String cooperatePurpose;
	//客户姓名
	private String custName;
	//已制定计划
	private String planeDraft;
	//联系人
	private String linkManName;
	//联系人手机
	private String linkManMobeilPhone;
	//联系人电话
	private String linkManTelePhone;
	//发货潜力
	private String sendGoodsPontential;
	//日程时间
	private Date scheduleDate;
	//开发状态
	private String planeState;
	//日程状态	
	private String scheduleStatus;
	//计划名称
	private String planeName;
	//执行人
	private String executePersion;
	//开始时间
	private Date createStartTime;
	//结束时间
	private Date createEndTime;
	//日程类型
	private String scheduleType;
	//未完成计划数量
	private int unfinishedPlanNum;
	//未完成计划名称
	private String unfinishedPlanName;
	//商机状态
	private String businessStatus;
	//最后回访时间
	private Date lastVisitDate;
	//最后修改人
	private String lastUpdateUser;
	//最后修改时间
	private Date lastUpdateTime;
	// 客户类型
	private String custType;
	//营销备注
	private String marketRemark ;
	//会员等级
	private String memberLevel;
	//回访次数
	private int visitNum;
	//职位
	private String position;
	//本部门Id
	private String executeDeptId;
	//开发/维护时限
	private String dateLimit;
	// 联系人ID
	private String linkManId;
	// 会员ID
	private String memberId;
	// 潜散客ID
	private String psCustomerId;
	//配合开发简版360页面，增加固定客户编码返回字段
	private String custNumber;
	//日程来源ID
	private String comeFromId;
	//日程来源
	private String comeFrom;
	//问卷ID
	private String surveyId;
	// 计划类别  auth：kuang
	private String projectType;
	//问卷名称
	private String questionnaireName;
	//问卷编码
	private String questionnaireCode;
	//问卷id
	private String questionnaireId;
	
	/**
	 * @param questionnaireName the questionnaireName to get
	 */
	public String getQuestionnaireName() {
		return questionnaireName;
	}
	/**
	 * @param questionnaireName the questionnaireName to set
	 */
	public void setQuestionnaireName(String questionnaireName) {
		this.questionnaireName = questionnaireName;
	}
	/**
	 * @param questionnaireCode the questionnaireCode to get
	 */
	public String getQuestionnaireCode() {
		return questionnaireCode;
	}
	/**
	 * @param questionnaireCode the questionnaireCode to set
	 */
	public void setQuestionnaireCode(String questionnaireCode) {
		this.questionnaireCode = questionnaireCode;
	}
	/**
	 * @param questionnaireId the questionnaireId to get
	 */
	public String getQuestionnaireId() {
		return questionnaireId;
	}
	/**
	 * @param questionnaireId the questionnaireId to set
	 */
	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	/**
	 * @param custNumber the custNumber to get
	 */
	public String getCustNumber() {
		return custNumber;
	}
	/**
	 * @param custNumber the custNumber to set
	 */
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	/**
	 * @return id : return the property id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id : set the property id.
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return cooperatePurpose : return the property cooperatePurpose.
	 */
	public String getCooperatePurpose() {
		return cooperatePurpose;
	}
	/**
	 * @param cooperatePurpose : set the property cooperatePurpose.
	 */
	public void setCooperatePurpose(String cooperatePurpose) {
		this.cooperatePurpose = cooperatePurpose;
	}
	/**
	 * @return custName : return the property custName.
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * @param custName : set the property custName.
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * @return planeDraft : return the property planeDraft.
	 */
	public String getPlaneDraft() {
		return planeDraft;
	}
	/**
	 * @param planeDraft : set the property planeDraft.
	 */
	public void setPlaneDraft(String planeDraft) {
		this.planeDraft = planeDraft;
	}
	/**
	 * @return linkManName : return the property linkManName.
	 */
	public String getLinkManName() {
		return linkManName;
	}
	/**
	 * @param linkManName : set the property linkManName.
	 */
	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}
	/**
	 * @return linkManMobeilPhone : return the property linkManMobeilPhone.
	 */
	public String getLinkManMobeilPhone() {
		return linkManMobeilPhone;
	}
	/**
	 * @param linkManMobeilPhone : set the property linkManMobeilPhone.
	 */
	public void setLinkManMobeilPhone(String linkManMobeilPhone) {
		this.linkManMobeilPhone = linkManMobeilPhone;
	}
	/**
	 * @return linkManTelePhone : return the property linkManTelePhone.
	 */
	public String getLinkManTelePhone() {
		return linkManTelePhone;
	}
	/**
	 * @param linkManTelePhone : set the property linkManTelePhone.
	 */
	public void setLinkManTelePhone(String linkManTelePhone) {
		this.linkManTelePhone = linkManTelePhone;
	}
	/**
	 * @return sendGoodsPontential : return the property sendGoodsPontential.
	 */
	public String getSendGoodsPontential() {
		return sendGoodsPontential;
	}
	/**
	 * @param sendGoodsPontential : set the property sendGoodsPontential.
	 */
	public void setSendGoodsPontential(String sendGoodsPontential) {
		this.sendGoodsPontential = sendGoodsPontential;
	}
	/**
	 * @return scheduleDate : return the property scheduleDate.
	 */
	public Date getScheduleDate() {
		return scheduleDate;
	}
	/**
	 * @param scheduleDate : set the property scheduleDate.
	 */
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	/**
	 * @return planeState : return the property planeState.
	 */
	public String getPlaneState() {
		return planeState;
	}
	/**
	 * @param planeState : set the property planeState.
	 */
	public void setPlaneState(String planeState) {
		this.planeState = planeState;
	}
	/**
	 * @return scheduleStatus : return the property scheduleStatus.
	 */
	public String getScheduleStatus() {
		return scheduleStatus;
	}
	/**
	 * @param scheduleStatus : set the property scheduleStatus.
	 */
	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}
	/**
	 * @return planeName : return the property planeName.
	 */
	public String getPlaneName() {
		return planeName;
	}
	/**
	 * @param planeName : set the property planeName.
	 */
	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}
	/**
	 * @return executePersion : return the property executePersion.
	 */
	public String getExecutePersion() {
		return executePersion;
	}
	/**
	 * @param executePersion : set the property executePersion.
	 */
	public void setExecutePersion(String executePersion) {
		this.executePersion = executePersion;
	}
	/**
	 * @return createStartTime : return the property createStartTime.
	 */
	public Date getCreateStartTime() {
		return createStartTime;
	}
	/**
	 * @param createStartTime : set the property createStartTime.
	 */
	public void setCreateStartTime(Date createStartTime) {
		this.createStartTime = createStartTime;
	}
	/**
	 * @return createEndTime : return the property createEndTime.
	 */
	public Date getCreateEndTime() {
		return createEndTime;
	}
	/**
	 * @param createEndTime : set the property createEndTime.
	 */
	public void setCreateEndTime(Date createEndTime) {
		this.createEndTime = createEndTime;
	}
	/**
	 * @return scheduleType : return the property scheduleType.
	 */
	public String getScheduleType() {
		return scheduleType;
	}
	/**
	 * @param scheduleType : set the property scheduleType.
	 */
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	/**
	 * @return unfinishedPlanNum : return the property unfinishedPlanNum.
	 */
	public int getUnfinishedPlanNum() {
		return unfinishedPlanNum;
	}
	/**
	 * @param unfinishedPlanNum : set the property unfinishedPlanNum.
	 */
	public void setUnfinishedPlanNum(int unfinishedPlanNum) {
		this.unfinishedPlanNum = unfinishedPlanNum;
	}
	/**
	 * @return unfinishedPlanName : return the property unfinishedPlanName.
	 */
	public String getUnfinishedPlanName() {
		return unfinishedPlanName;
	}
	/**
	 * @param unfinishedPlanName : set the property unfinishedPlanName.
	 */
	public void setUnfinishedPlanName(String unfinishedPlanName) {
		this.unfinishedPlanName = unfinishedPlanName;
	}
	/**
	 * @return businessStatus : return the property businessStatus.
	 */
	public String getBusinessStatus() {
		return businessStatus;
	}
	/**
	 * @param businessStatus : set the property businessStatus.
	 */
	public void setBusinessStatus(String businessStatus) {
		this.businessStatus = businessStatus;
	}
	/**
	 * @return lastVisitDate : return the property lastVisitDate.
	 */
	public Date getLastVisitDate() {
		return lastVisitDate;
	}
	/**
	 * @param lastVisitDate : set the property lastVisitDate.
	 */
	public void setLastVisitDate(Date lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}
	/**
	 * @return lastUpdateUser : return the property lastUpdateUser.
	 */
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}
	/**
	 * @param lastUpdateUser : set the property lastUpdateUser.
	 */
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	/**
	 * @return lastUpdateTime : return the property lastUpdateTime.
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	/**
	 * @param lastUpdateTime : set the property lastUpdateTime.
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 * @return custType : return the property custType.
	 */
	public String getCustType() {
		return custType;
	}
	/**
	 * @param custType : set the property custType.
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}
	/**
	 * @return marketRemark : return the property marketRemark.
	 */
	public String getMarketRemark() {
		return marketRemark;
	}
	/**
	 * @param marketRemark : set the property marketRemark.
	 */
	public void setMarketRemark(String marketRemark) {
		this.marketRemark = marketRemark;
	}
	/**
	 * @return memberLevel : return the property memberLevel.
	 */
	public String getMemberLevel() {
		return memberLevel;
	}
	/**
	 * @param memberLevel : set the property memberLevel.
	 */
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	/**
	 * @return visitNum : return the property visitNum.
	 */
	public int getVisitNum() {
		return visitNum;
	}
	/**
	 * @param visitNum : set the property visitNum.
	 */
	public void setVisitNum(int visitNum) {
		this.visitNum = visitNum;
	}
	/**
	 * @return position : return the property position.
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position : set the property position.
	 */
	public void setPosition(String position) {
		this.position = position;
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
	 * @return dateLimit : return the property dateLimit.
	 */
	public String getDateLimit() {
		return dateLimit;
	}
	/**
	 * @param dateLimit : set the property dateLimit.
	 */
	public void setDateLimit(String dateLimit) {
		this.dateLimit = dateLimit;
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
	 * @return psCustomerId : return the property psCustomerId.
	 */
	public String getPsCustomerId() {
		return psCustomerId;
	}
	/**
	 * @param psCustomerId : set the property psCustomerId.
	 */
	public void setPsCustomerId(String psCustomerId) {
		this.psCustomerId = psCustomerId;
	}
	public String getComeFromId() {
		return comeFromId;
	}
	public void setComeFromId(String comeFromId) {
		this.comeFromId = comeFromId;
	}
	public String getComeFrom() {
		return comeFrom;
	}
	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}
	public String getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
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
}
