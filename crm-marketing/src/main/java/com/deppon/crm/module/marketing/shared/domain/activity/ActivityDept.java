package com.deppon.crm.module.marketing.shared.domain.activity;

import com.deppon.foss.framework.entity.BaseEntity;

public class ActivityDept extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5729658962127264079L;
	//市场推广活动ID
	private String activityId;
	//部门标杆编码
	private String deptStandardCode;
	//部门名称
	private String deptName;
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
	public String getDeptStandardCode() {
		return deptStandardCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param deptStandardCode the deptStandardCode to set
	 */
	public void setDeptStandardCode(String deptStandardCode) {
		this.deptStandardCode = deptStandardCode;
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
}
