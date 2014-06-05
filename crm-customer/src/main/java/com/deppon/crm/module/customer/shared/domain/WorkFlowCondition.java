package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;
import java.util.Set;

/**
 * @作者：罗典
 * @时间：2012-4-11
 * @描述：工作流查询条件
 * */
public class WorkFlowCondition {
	// 流程序号
	private Long workflowId;
	// 流程名称
	private String workflowName;
	// 流程状态
	private String applicationStatus;
	// 所属部门
	private Integer deptId;
	// 申请人
	private String createUser;
	// 申请开始时间
	private Date startDate;
	// 申请结束时间
	private Date endDate;
	// 所属人角色
	private Set<String> ownerRoleIds;
	// 所属人部门
	private String ownerDeptId;
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
	 * Description:deptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Integer getDeptId() {
		return deptId;
	}
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	/**
	 * <p>
	 * Description:createUser<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * <p>
	 * Description:createUser<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * <p>
	 * Description:startDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * <p>
	 * Description:startDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * <p>
	 * Description:endDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * <p>
	 * Description:endDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * <p>
	 * Description:ownerRoleIds<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Set<String> getOwnerRoleIds() {
		return ownerRoleIds;
	}
	/**
	 * <p>
	 * Description:ownerRoleIds<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOwnerRoleIds(Set<String> ownerRoleIds) {
		this.ownerRoleIds = ownerRoleIds;
	}
	/**
	 * <p>
	 * Description:ownerDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getOwnerDeptId() {
		return ownerDeptId;
	}
	/**
	 * <p>
	 * Description:ownerDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOwnerDeptId(String ownerDeptId) {
		this.ownerDeptId = ownerDeptId;
	}
}
