/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanQueryCondition.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-27
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;


/**   
 * <p>
 * Description: 监控计划-查询<br />
 * </p>
 * @title MonitorPlanQueryCondition.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-27
 */

public class MonitorPlanQueryCondition {
	// 部门ID
	private String execdeptId;
	
	// 计划ID
	private String planId;

	// 计划主题
	private String topic;
	
	// 开发状态
	private String planStatus;
	
	// 开发时间（起始）
	private Date planStartDate;
	private Date planOverDate;
	
	// 执行人员ID
	private String execuserId;

	// 计划类型表示开发还是维护计划
	private String planType;

	// 执行人员ID
	private String[] execdeptIds;
	
	//计划类别表示零担还是快递计划
	private String projectType;
	/**
	 * @return execdeptIds : return the property execdeptIds.
	 */
	public String[] getExecdeptIds() {
		return execdeptIds;
	}

	/**
	 * @param execdeptIds : set the property execdeptIds.
	 */
	public void setExecdeptIds(String[] execdeptIds) {
		this.execdeptIds = execdeptIds;
	}

	/**
	 * @return planStartDate : return the property planStartDate.
	 */
	public Date getPlanStartDate() {
		return planStartDate;
	}

	/**
	 * @param planStartDate : set the property planStartDate.
	 */
	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	/**
	 * @return planOverDate : return the property planOverDate.
	 */
	public Date getPlanOverDate() {
		return planOverDate;
	}

	/**
	 * @param planOverDate : set the property planOverDate.
	 */
	public void setPlanOverDate(Date planOverDate) {
		this.planOverDate = planOverDate;
	}

	/**
	 * @return topic : return the property topic.
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic : set the property topic.
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * @return planType : return the property planType.
	 */
	public String getPlanType() {
		return planType;
	}

	/**
	 * @param planType : set the property planType.
	 */
	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getExecdeptId() {
		return execdeptId;
	}

	public void setExecdeptId(String execdeptId) {
		this.execdeptId = execdeptId;
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
	 * @return planStatus : return the property planStatus.
	 */
	public String getPlanStatus() {
		return planStatus;
	}

	/**
	 * @param planStatus : set the property planStatus.
	 */
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}


	/**
	 * @return execuserId : return the property execuserId.
	 */
	public String getExecuserId() {
		return execuserId;
	}

	/**
	 * @param execuserId : set the property execuserId.
	 */
	public void setExecuserId(String execuserId) {
		this.execuserId = execuserId;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
}
