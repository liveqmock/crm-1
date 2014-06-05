/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ScheduleQueryResultObject.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author Administrator
 * @version 0.1 2012-4-1
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * 日程查询、维护结果实体<br />
 * </p>
 * @title ScheduleQueryResultObject.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author 苏玉军
 * @version 0.1 2012-4-1
 * @revision 盛诗庆 新增业务类别字段
 */

public class ScheduleQueryResultObject extends BaseEntity {
	private static final long serialVersionUID = -99660416228877603L;
	//未完成计划名称
	private String unfinishedPlanName;
	//会员等级
	private String memberLevel;
	//联系人Id
	private String contactId;
	//盛诗庆 业务类别
	private String businessType;
	//计划Id
	private String planId;
	//客户Id
	private String custId;
	//配合开发简版360页面，增加固定客户编码返回字段
	private String custNumber;
	//日程Id
	private String scheduleId;
	//计划名称
	private String planTopic;
	//合作意向
	private String coopIntetion;
	//客户名称
	private String custName;
	//联系人名称
	private String contactName;
	//联系人手机
	private String contactMobile;
	//联系人电话
	private String contactTel;
	//发货潜力
	private String goodsPotential;
	//日程时间
	private Date scheduleDate;
	//日程状态
	private String developStatus;
	//未完成计划数量
	private String unfinishedPlanNum;
	//回访次数
	private int visitNum;
	//最后回访时间
	private Date lastVisitDate;
	//执行人员
	private String executeUserName;
	// 客户类型
	private String custType;	
	//商机状态/开发阶段
	private String businessStatus;
	//是否是重复客户，0：否，1：是
	private String repeatCust;
	//疑似重复客户部门
	private String repeatCustDeptName;
	//商机状态
	private String businessOpportunityStatus;
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
	 * @return contactId : return the property contactId.
	 */
	public String getContactId() {
		return contactId;
	}

	/**
	 * @param contactId : set the property contactId.
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
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
	 * @return custId : return the property custId.
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * @param custId : set the property custId.
	 */
	public void setCustId(String custId) {
		this.custId = custId;
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
	 * @return planTopic : return the property planTopic.
	 */
	public String getPlanTopic() {
		return planTopic;
	}

	/**
	 * @param planTopic : set the property planTopic.
	 */
	public void setPlanTopic(String planTopic) {
		this.planTopic = planTopic;
	}

	/**
	 * @return coopIntetion : return the property coopIntetion.
	 */
	public String getCoopIntetion() {
		return coopIntetion;
	}

	/**
	 * @param coopIntetion : set the property coopIntetion.
	 */
	public void setCoopIntetion(String coopIntetion) {
		this.coopIntetion = coopIntetion;
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
	 * @return contactName : return the property contactName.
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName : set the property contactName.
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return contactMobile : return the property contactMobile.
	 */
	public String getContactMobile() {
		return contactMobile;
	}

	/**
	 * @param contactMobile : set the property contactMobile.
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	/**
	 * @return contactTel : return the property contactTel.
	 */
	public String getContactTel() {
		return contactTel;
	}

	/**
	 * @param contactTel : set the property contactTel.
	 */
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	/**
	 * @return goodsPotential : return the property goodsPotential.
	 */
	public String getGoodsPotential() {
		return goodsPotential;
	}

	/**
	 * @param goodsPotential : set the property goodsPotential.
	 */
	public void setGoodsPotential(String goodsPotential) {
		this.goodsPotential = goodsPotential;
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
	 * @return developStatus : return the property developStatus.
	 */
	public String getDevelopStatus() {
		return developStatus;
	}

	/**
	 * @param developStatus : set the property developStatus.
	 */
	public void setDevelopStatus(String developStatus) {
		this.developStatus = developStatus;
	}

	/**
	 * @return unfinishedPlanNum : return the property unfinishedPlanNum.
	 */
	public String getUnfinishedPlanNum() {
		return unfinishedPlanNum;
	}

	/**
	 * @param unfinishedPlanNum : set the property unfinishedPlanNum.
	 */
	public void setUnfinishedPlanNum(String unfinishedPlanNum) {
		this.unfinishedPlanNum = unfinishedPlanNum;
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
	 * @return executeUserName : return the property executeUserName.
	 */
	public String getExecuteUserName() {
		return executeUserName;
	}

	/**
	 * @param executeUserName : set the property executeUserName.
	 */
	public void setExecuteUserName(String executeUserName) {
		this.executeUserName = executeUserName;
	}

	/**
	 * @return businessType : return the property businessType.  
	 */
	public String getBusinessType() {
		return businessType;
	}

	/**
	 * @param businessType : set the property businessType. 
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
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
	 * @return repeatCust : return the property repeatCust.  
	 */
	public String getRepeatCust() {
		return repeatCust;
	}

	/**
	 * @param repeatCust : set the property repeatCust. 
	 */
	public void setRepeatCust(String repeatCust) {
		this.repeatCust = repeatCust;
	}

	/**
	 * @return repeatCustDeptName : return the property repeatCustDeptName.  
	 */
	public String getRepeatCustDeptName() {
		return repeatCustDeptName;
	}

	/**
	 * @param repeatCustDeptName : set the property repeatCustDeptName. 
	 */
	public void setRepeatCustDeptName(String repeatCustDeptName) {
		this.repeatCustDeptName = repeatCustDeptName;
	}

	/**
	 * @return businessOpportunityStatus : return the property businessOpportunityStatus.  
	 */
	public String getBusinessOpportunityStatus() {
		return businessOpportunityStatus;
	}

	/**
	 * @param businessOpportunityStatus : set the property businessOpportunityStatus. 
	 */
	public void setBusinessOpportunityStatus(String businessOpportunityStatus) {
		this.businessOpportunityStatus = businessOpportunityStatus;
	}


	
}
