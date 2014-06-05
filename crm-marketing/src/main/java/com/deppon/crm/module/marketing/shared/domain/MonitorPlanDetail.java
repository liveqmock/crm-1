/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanDetail.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-27
 */
package com.deppon.crm.module.marketing.shared.domain;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanDetail.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-27
 */

public class MonitorPlanDetail {
	// 部门名称
	private String departName;
	
	// 开发主题
	private String topic;
	
	// 计划制定者
	private String creatorName;	
	
	// 计划时限
	private String planeTimeLimit;
	
	// 客户名称
	private String customerName;
	
	// 客户类型
	private String customerType;
			
	// 客户属性
	private String customerProperty;
	
	// 执行人员
	private String execuserName;
	
	// 计划状态
	private String planStatus;
	
	// 日程状态
	private String scheStatus;
	
	// 开发详情
	private String planDesc;
	//联系人姓名
	private String linkManName;
	//会员等级
	private String memberLevel;
	/**
	 * 计划类别
	 * auth:李春雨
	 * date:2014-01-09
	 */
	private String projectType;
	/**
	 * @return scheStatus : return the property scheStatus.
	 */
	public String getScheStatus() {
		return scheStatus;
	}

	/**
	 * @param scheStatus : set the property scheStatus.
	 */
	public void setScheStatus(String scheStatus) {
		this.scheStatus = scheStatus;
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
	 * @return creatorName : return the property creatorName.
	 */
	public String getCreatorName() {
		return creatorName;
	}

	/**
	 * @param creatorName : set the property creatorName.
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	/**
	 * @return planeTimeLimit : return the property planeTimeLimit.
	 */
	public String getPlaneTimeLimit() {
		return planeTimeLimit;
	}

	/**
	 * @param planeTimeLimit : set the property planeTimeLimit.
	 */
	public void setPlaneTimeLimit(String planeTimeLimit) {
		this.planeTimeLimit = planeTimeLimit;
	}

	/**
	 * @return customerName : return the property customerName.
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName : set the property customerName.
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return customerType : return the property customerType.
	 */
	public String getCustomerType() {
		return customerType;
	}

	/**
	 * @param customerType : set the property customerType.
	 */
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	/**
	 * @return customerProperty : return the property customerProperty.
	 */
	public String getCustomerProperty() {
		return customerProperty;
	}

	/**
	 * @param customerProperty : set the property customerProperty.
	 */
	public void setCustomerProperty(String customerProperty) {
		this.customerProperty = customerProperty;
	}

	/**
	 * @return execuserName : return the property execuserName.
	 */
	public String getExecuserName() {
		return execuserName;
	}

	/**
	 * @param execuserName : set the property execuserName.
	 */
	public void setExecuserName(String execuserName) {
		this.execuserName = execuserName;
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
	 * @return plan : return the property plan.
	 */
	public String getPlanDesc() {
		return planDesc;
	}

	/**
	 * @param plan : set the property plan.
	 */
	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
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
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-1-9
	 */
	public String getProjectType() {
		return projectType;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-1-9
	 * @param projectType the projectType to set
	 */
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	
}
