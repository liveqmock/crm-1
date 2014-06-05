package com.deppon.crm.module.customer.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

public class WorkFlow extends BaseEntity{
	//工作流申请人id
	private String userId;
	//工作流申请人name
	private String userName;
	//工作流状态
	private String status;
	//工作流id
	private long workFlowId;
	//工作流名称
	private String workFLowName;
	//所属部门id
	private String deptId;
	//所属部门
	private String deptName;
	//业务id
	private String appId;
	/**
	 * <p>
	 * Description:userId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * <p>
	 * Description:userId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * <p>
	 * Description:userName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * <p>
	 * Description:userName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * <p>
	 * Description:workFlowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public long getWorkFlowId() {
		return workFlowId;
	}
	/**
	 * <p>
	 * Description:workFlowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWorkFlowId(long workFlowId) {
		this.workFlowId = workFlowId;
	}
	/**
	 * <p>
	 * Description:workFLowName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getWorkFLowName() {
		return workFLowName;
	}
	/**
	 * <p>
	 * Description:workFLowName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWorkFLowName(String workFLowName) {
		this.workFLowName = workFLowName;
	}
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * <p>
	 * Description:appId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getAppId() {
		return appId;
	}
	/**
	 * <p>
	 * Description:appId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}
}
