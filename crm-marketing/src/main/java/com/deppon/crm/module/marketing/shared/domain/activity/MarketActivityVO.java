package com.deppon.crm.module.marketing.shared.domain.activity;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;


/**   
 * <p>
 * Description:市场推广活动实体类<br />
 * </p>
 * @title MarketActivity.java
 * @package com.deppon.crm.module.marketing.shared.domain.activity 
 * @author ZhouYuan
 * @version 0.1 2014-03-05
 */
public class MarketActivityVO extends BaseEntity{
	//市场推广活动编码
	private String activityCode;
	//市场推广活动名称
	private String activityName;
	//活动类型
	private String activityType;
	//活动类别
	private String activityCategory;
	//活动开始时间
	private Date startTime;
	//活动结束时间
	private Date endTime;
	//活动状态
	private String activityStatus;
	//活动创建部门
	private String deptId;
	//活动创建部门名称
	private String deptName;
	//创建人姓名
	private String createUserName;
	//客户群列表
	private String clientBases;
	//部门性质
	private String deptCharacter;
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getActivityCode() {
		return activityCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param activityCode the activityCode to set
	 */
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getActivityName() {
		return activityName;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param activityName the activityName to set
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getActivityType() {
		return activityType;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param activityType the activityType to set
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getActivityCategory() {
		return activityCategory;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param activityCategory the activityCategory to set
	 */
	public void setActivityCategory(String activityCategory) {
		this.activityCategory = activityCategory;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getActivityStatus() {
		return activityStatus;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param activityStatus the activityStatus to set
	 */
	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
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
	public String getClientBases() {
		return clientBases;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientBases the clientBases to set
	 */
	public void setClientBases(String clientBases) {
		this.clientBases = clientBases;
	}
	public String getDeptCharacter() {
		return deptCharacter;
	}
	public void setDeptCharacter(String deptCharacter) {
		this.deptCharacter = deptCharacter;
	}
	
}
