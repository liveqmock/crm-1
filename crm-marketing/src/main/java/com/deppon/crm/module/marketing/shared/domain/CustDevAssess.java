package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;
/**   
 * <p>
 * Description:营销效果评估客户开发表<br />
 * </p>
 * @title CustDevAssess.java
 * @package com.deppon.crm.module.marketing.shared.domain
 * @author ZhouYuan
 * @version 2013-01-17
 */
public class CustDevAssess extends BaseEntity {
	//部门名称
	private String deptName;
	//部门ID
	private String deptId;
	//上级部门ID
	private String parentId;
	//上级部门名称
	private String parentName;
	//经营本部名称
	private String managerDeptName;
	//事业部名称
	private String orgDeptName;
	//大区名称
	private String bigDeptName;
	//小区名称
	private String smaDeptName;
	//营业部名字
	private String salDeptName;
	//新增潜客数
	private String newCreateNum;
	//转化为散客数
	private String turnToScNum;
	//转化为固定客户数
	private String turnToRcNum;
	//计划回访数
	private String planVisitNum;
	//实际回访数
	private String actualVisitNum;
	//计划回访率
	private String planVisitRate;
	//当前需求确认阶段客户数
	private String nowIdentifyNeedsCustNum;
	//需求确认阶段转化客户数
	private String identifyNeedsOutCustNum;
	//需求确认阶段转化率
	private String identifyNeedsCustRate;
	//当前意向洽谈阶段客户数
	private String nowIntentionDiscussCustNum;
	//意向洽谈阶段转化客户数
	private String intentionDiscussOutCustNum;
	//意向洽谈阶段转化率
	private String intentionDiscussCustRate;
	//当前持续培养阶段客户数
	private String nowContinuingTrainingCustNum;
	//持续培养阶段转化客户数
	private String continuingTrainingOutCustNum;
	//持续培养阶段转化率
	private String continuingTrainingCustRate;
	//近三个月潜客数
	private String beforePotentialNum;
	//潜客本月发货数
	private String nowPotentialDeleviryNum;
	// 潜客开发成功率
	private String potentialDevSuccessRate;
	//零担发货金额
	private String lttDelCustAmount;
	//快递发货金额
	private String expDelCustAmount;
	//经营本部id
	private String managerDeptId;
	//事业部id
	private String orgDeptId;
	//大区id
	private String bigDeptId;
	//小区id
	private String smaDeptId;
	//营业部id
	private String salDeptId;
	//创建时间
	private Date createTime;
	//查询截止时间
	private Date queryTime;
	//查询截止日期
	private String queryTimeStr;
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getManagerDeptName() {
		return managerDeptName;
	}
	public void setManagerDeptName(String managerDeptName) {
		this.managerDeptName = managerDeptName;
	}
	public String getOrgDeptName() {
		return orgDeptName;
	}
	public void setOrgDeptName(String orgDeptName) {
		this.orgDeptName = orgDeptName;
	}
	public String getBigDeptName() {
		return bigDeptName;
	}
	public void setBigDeptName(String bigDeptName) {
		this.bigDeptName = bigDeptName;
	}
	public String getSmaDeptName() {
		return smaDeptName;
	}
	public void setSmaDeptName(String smaDeptName) {
		this.smaDeptName = smaDeptName;
	}
	public String getSalDeptName() {
		return salDeptName;
	}
	public void setSalDeptName(String salDeptName) {
		this.salDeptName = salDeptName;
	}
	public String getNewCreateNum() {
		return newCreateNum;
	}
	public void setNewCreateNum(String newCreateNum) {
		this.newCreateNum = newCreateNum;
	}
	public String getTurnToScNum() {
		return turnToScNum;
	}
	public void setTurnToScNum(String turnToScNum) {
		this.turnToScNum = turnToScNum;
	}
	public String getTurnToRcNum() {
		return turnToRcNum;
	}
	public void setTurnToRcNum(String turnToRcNum) {
		this.turnToRcNum = turnToRcNum;
	}
	public String getPlanVisitNum() {
		return planVisitNum;
	}
	public void setPlanVisitNum(String planVisitNum) {
		this.planVisitNum = planVisitNum;
	}
	
	public String getActualVisitNum() {
		return actualVisitNum;
	}
	public void setActualVisitNum(String actualVisitNum) {
		this.actualVisitNum = actualVisitNum;
	}
	public String getPlanVisitRate() {
		return planVisitRate;
	}
	public void setPlanVisitRate(String planVisitRate) {
		this.planVisitRate = planVisitRate;
	}
	public String getNowIdentifyNeedsCustNum() {
		return nowIdentifyNeedsCustNum;
	}
	public void setNowIdentifyNeedsCustNum(String nowIdentifyNeedsCustNum) {
		this.nowIdentifyNeedsCustNum = nowIdentifyNeedsCustNum;
	}
	public String getIdentifyNeedsOutCustNum() {
		return identifyNeedsOutCustNum;
	}
	public void setIdentifyNeedsOutCustNum(String identifyNeedsOutCustNum) {
		this.identifyNeedsOutCustNum = identifyNeedsOutCustNum;
	}
	public String getIdentifyNeedsCustRate() {
		return identifyNeedsCustRate;
	}
	public void setIdentifyNeedsCustRate(String identifyNeedsCustRate) {
		this.identifyNeedsCustRate = identifyNeedsCustRate;
	}
	public String getNowIntentionDiscussCustNum() {
		return nowIntentionDiscussCustNum;
	}
	public void setNowIntentionDiscussCustNum(String nowIntentionDiscussCustNum) {
		this.nowIntentionDiscussCustNum = nowIntentionDiscussCustNum;
	}
	public String getIntentionDiscussOutCustNum() {
		return intentionDiscussOutCustNum;
	}
	public void setIntentionDiscussOutCustNum(String intentionDiscussOutCustNum) {
		this.intentionDiscussOutCustNum = intentionDiscussOutCustNum;
	}
	public String getIntentionDiscussCustRate() {
		return intentionDiscussCustRate;
	}
	public void setIntentionDiscussCustRate(String intentionDiscussCustRate) {
		this.intentionDiscussCustRate = intentionDiscussCustRate;
	}
	public String getNowContinuingTrainingCustNum() {
		return nowContinuingTrainingCustNum;
	}
	public void setNowContinuingTrainingCustNum(String nowContinuingTrainingCustNum) {
		this.nowContinuingTrainingCustNum = nowContinuingTrainingCustNum;
	}
	public String getContinuingTrainingOutCustNum() {
		return continuingTrainingOutCustNum;
	}
	public void setContinuingTrainingOutCustNum(String continuingTrainingOutCustNum) {
		this.continuingTrainingOutCustNum = continuingTrainingOutCustNum;
	}
	public String getContinuingTrainingCustRate() {
		return continuingTrainingCustRate;
	}
	public void setContinuingTrainingCustRate(String continuingTrainingCustRate) {
		this.continuingTrainingCustRate = continuingTrainingCustRate;
	}
	public String getBeforePotentialNum() {
		return beforePotentialNum;
	}
	public void setBeforePotentialNum(String beforePotentialNum) {
		this.beforePotentialNum = beforePotentialNum;
	}
	public String getNowPotentialDeleviryNum() {
		return nowPotentialDeleviryNum;
	}
	public void setNowPotentialDeleviryNum(String nowPotentialDeleviryNum) {
		this.nowPotentialDeleviryNum = nowPotentialDeleviryNum;
	}
	public String getPotentialDevSuccessRate() {
		return potentialDevSuccessRate;
	}
	public void setPotentialDevSuccessRate(String potentialDevSuccessRate) {
		this.potentialDevSuccessRate = potentialDevSuccessRate;
	}
	public String getLttDelCustAmount() {
		return lttDelCustAmount;
	}
	public void setLttDelCustAmount(String lttDelCustAmount) {
		this.lttDelCustAmount = lttDelCustAmount;
	}
	public String getExpDelCustAmount() {
		return expDelCustAmount;
	}
	public void setExpDelCustAmount(String expDelCustAmount) {
		this.expDelCustAmount = expDelCustAmount;
	}
	public String getManagerDeptId() {
		return managerDeptId;
	}
	public void setManagerDeptId(String managerDeptId) {
		this.managerDeptId = managerDeptId;
	}
	public String getOrgDeptId() {
		return orgDeptId;
	}
	public void setOrgDeptId(String orgDeptId) {
		this.orgDeptId = orgDeptId;
	}
	public String getBigDeptId() {
		return bigDeptId;
	}
	public void setBigDeptId(String bigDeptId) {
		this.bigDeptId = bigDeptId;
	}
	public String getSmaDeptId() {
		return smaDeptId;
	}
	public void setSmaDeptId(String smaDeptId) {
		this.smaDeptId = smaDeptId;
	}
	public String getSalDeptId() {
		return salDeptId;
	}
	public void setSalDeptId(String salDeptId) {
		this.salDeptId = salDeptId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(Date queryTime) {
		this.queryTime = queryTime;
	}
	public String getQueryTimeStr() {
		return queryTimeStr;
	}
	public void setQueryTimeStr(String queryTimeStr) {
		this.queryTimeStr = queryTimeStr;
	}
}
