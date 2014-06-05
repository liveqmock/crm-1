/**
 * @description
 * @author 赵斌
 * @2012-4-14
 * @return 
 */

package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @author 赵斌
 *
 */
public class ContractWorkflow extends BaseEntity{

	private static final long serialVersionUID = -5184759154498885328L;
	//部门名称
	private String deptName;
	//工作流号
	private String workflowId;
	//OA审批状态  1审批中  2同意  3拒绝
	private String approvalState;
    //创建时间
	private Date createDate;
	//最后审批时间
	private Date modifyDate;
	//审批人
	private String approvalMan;
	//合同部门ID
	private String contractDeptId;
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
	 * Description:workflowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getWorkflowId() {
		return workflowId;
	}
	/**
	 * <p>
	 * Description:workflowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	/**
	 * <p>
	 * Description:approvalState<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getApprovalState() {
		return approvalState;
	}
	/**
	 * <p>
	 * Description:approvalState<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}
	/**
	 * <p>
	 * Description:createDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * <p>
	 * Description:createDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * <p>
	 * Description:modifyDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * <p>
	 * Description:modifyDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * <p>
	 * Description:approvalMan<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getApprovalMan() {
		return approvalMan;
	}
	/**
	 * <p>
	 * Description:approvalMan<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setApprovalMan(String approvalMan) {
		this.approvalMan = approvalMan;
	}
	/**
	 * <p>
	 * Description:contractDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContractDeptId() {
		return contractDeptId;
	}
	/**
	 * <p>
	 * Description:contractDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractDeptId(String contractDeptId) {
		this.contractDeptId = contractDeptId;
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
