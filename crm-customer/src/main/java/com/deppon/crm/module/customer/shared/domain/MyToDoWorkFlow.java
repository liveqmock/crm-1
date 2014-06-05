/**   
 * @title MyToDoWorkFlow.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @description what to do
 * @author 潘光均
 * @update 2012-7-14 上午11:21:43
 * @version V1.0   
 */
package com.deppon.crm.module.customer.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @description 我要处理的工作流  
 * @author 潘光均
 * @version 0.1 2012-7-14
 *@date 2012-7-14
 */

public class MyToDoWorkFlow extends BaseEntity{
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 5381762328343754084L;
	private Long workflowId;
	private String workflowName;
	private String applicationId;
	private String applicationName;
	private String applicationDesc;
	private String applicationStatus;
	private String applicationStatusDesc;
	private String url;
	private String roleId;
	private String roleName;
	private String todoFlag;
	
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
	 * Description:workflowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Long getWorkflowId() {
		return workflowId;
	}
	/**
	 * <p>
	 * Description:workflowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}
	/**
	 * <p>
	 * Description:workflowName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getWorkflowName() {
		return workflowName;
	}
	/**
	 * <p>
	 * Description:workflowName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}
	/**
	 * <p>
	 * Description:applicationId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getApplicationId() {
		return applicationId;
	}
	/**
	 * <p>
	 * Description:applicationId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	/**
	 * <p>
	 * Description:applicationName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getApplicationName() {
		return applicationName;
	}
	/**
	 * <p>
	 * Description:applicationName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	/**
	 * <p>
	 * Description:applicationDesc<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getApplicationDesc() {
		return applicationDesc;
	}
	/**
	 * <p>
	 * Description:applicationDesc<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setApplicationDesc(String applicationDesc) {
		this.applicationDesc = applicationDesc;
	}
	/**
	 * <p>
	 * Description:applicationStatus<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getApplicationStatus() {
		return applicationStatus;
	}
	/**
	 * <p>
	 * Description:applicationStatus<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	/**
	 * <p>
	 * Description:applicationStatusDesc<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getApplicationStatusDesc() {
		return applicationStatusDesc;
	}
	/**
	 * <p>
	 * Description:applicationStatusDesc<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setApplicationStatusDesc(String applicationStatusDesc) {
		this.applicationStatusDesc = applicationStatusDesc;
	}
	/**
	 * <p>
	 * Description:url<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * <p>
	 * Description:url<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * <p>
	 * Description:roleId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * <p>
	 * Description:roleId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * <p>
	 * Description:roleName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * <p>
	 * Description:roleName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * <p>
	 * Description:todoFlag<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTodoFlag() {
		return todoFlag;
	}
	/**
	 * <p>
	 * Description:todoFlag<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTodoFlag(String todoFlag) {
		this.todoFlag = todoFlag;
	}
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
	/**
	 * <p>
	 * Description:serialversionuid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
