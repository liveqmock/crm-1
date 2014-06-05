/**
 * @description
 * @author 赵斌
 * @2012-4-14
 * @return
 */
package com.deppon.crm.module.customer.shared.domain;

import java.util.List;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @author 潘光均
 *@description 合同操作记录表
 *@version 1.0.4
 *@date 2012-06-28
 */
public class ContractOperatorLog extends BaseEntity{

	private static final long serialVersionUID = -5184759154498885328L;
	//合同信息
	private Contract contract = new Contract();
	//附件信息
	private List<FileInfo> fileInfos;
	//操作类型
	private String operatorType;
	//工作流号
	private String workflowId;
	//变更前部门
	private Department previousDept = new Department();
	//变更后部门
	private Department changedDept = new Department();
	//操作部门
	private Department operatorDept = new Department();
	// 审批人
	private String approvalMan;
	// OA审批状态 1审批中 2同意 3拒绝
	private String approvalState;
	/**
	 * <p>
	 * Description:contract<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Contract getContract() {
		return contract;
	}
	/**
	 * <p>
	 * Description:contract<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	/**
	 * <p>
	 * Description:fileInfos<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}
	/**
	 * <p>
	 * Description:fileInfos<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}
	/**
	 * <p>
	 * Description:operatorType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getOperatorType() {
		return operatorType;
	}
	/**
	 * <p>
	 * Description:operatorType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
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
	 * Description:previousDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Department getPreviousDept() {
		return previousDept;
	}
	/**
	 * <p>
	 * Description:previousDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPreviousDept(Department previousDept) {
		this.previousDept = previousDept;
	}
	/**
	 * <p>
	 * Description:changedDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Department getChangedDept() {
		return changedDept;
	}
	/**
	 * <p>
	 * Description:changedDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setChangedDept(Department changedDept) {
		this.changedDept = changedDept;
	}
	/**
	 * <p>
	 * Description:operatorDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Department getOperatorDept() {
		return operatorDept;
	}
	/**
	 * <p>
	 * Description:operatorDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOperatorDept(Department operatorDept) {
		this.operatorDept = operatorDept;
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
	 * Description:serialversionuid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
