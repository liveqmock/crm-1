package com.deppon.crm.module.marketing.shared.domain.activity;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:市场推广活动关联客户群<br />
 * </p>
 * @title MarketActivity.java
 * @package com.deppon.crm.module.marketing.shared.domain.ActivityClientBase 
 * @author ZhouYuan
 * @version 0.1 2014-03-05
 */
public class ActivityClientBase extends BaseEntity{
	//客户群ID
	private String clientBaseId;
	//市场推广活动ID
	private String activityId;
	//客户群名称
	private String clientBaseName;
	//客户群状态
	private String clientBaseStatus;
	//客户数量
	private String clientNum;
	//客户群归属部门ID
	private String deptId;
	//客户群部门名称
	private String deptName;
	//客户群创建人
	private String createUserName;
	//回访计划开始时间
	private Date planStartTime;
	//回访计划结束时间
	private Date planEndTime;
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getClientBaseId() {
		return clientBaseId;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientBaseId the clientBaseId to set
	 */
	public void setClientBaseId(String clientBaseId) {
		this.clientBaseId = clientBaseId;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getActivityId() {
		return activityId;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param activityId the activityId to set
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getClientBaseName() {
		return clientBaseName;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientBaseName the clientBaseName to set
	 */
	public void setClientBaseName(String clientBaseName) {
		this.clientBaseName = clientBaseName;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getClientBaseStatus() {
		return clientBaseStatus;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientBaseStatus the clientBaseStatus to set
	 */
	public void setClientBaseStatus(String clientBaseStatus) {
		this.clientBaseStatus = clientBaseStatus;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getClientNum() {
		return clientNum;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientNum the clientNum to set
	 */
	public void setClientNum(String clientNum) {
		this.clientNum = clientNum;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getCreateUserName() {
		return createUserName;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param createUserName the createUserName to set
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public Date getPlanStartTime() {
		return planStartTime;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param planStartTime the planStartTime to set
	 */
	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public Date getPlanEndTime() {
		return planEndTime;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param planEndTime the planEndTime to set
	 */
	public void setPlanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}
}
