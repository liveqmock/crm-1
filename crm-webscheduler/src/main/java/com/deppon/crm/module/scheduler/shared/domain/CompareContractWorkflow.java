package com.deppon.crm.module.scheduler.shared.domain;

/**   
 * <p>
 * Description:合同工作流比较结果集 OA-CRM<br />
 * </p>
 * @title CompareContractWorkflow.java
 * @package com.deppon.crm.module.scheduler.shared.domain 
 * @author 李国文
 * @version 0.1 2013-2-1
 */
public class CompareContractWorkflow {
	//工作流号
	private String workflowId;
	
	//合同序号
	private String contractNum;
	
	//crm工作流状态 
	private String crmStatus;
	
	//OA工作流状态
	private String oAStatus;
	
	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public String getCrmStatus() {
		return crmStatus;
	}

	public void setCrmStatus(String crmStatus) {
		this.crmStatus = crmStatus;
	}

	public String getoAStatus() {
		return oAStatus;
	}

	public void setoAStatus(String oAStatus) {
		this.oAStatus = oAStatus;
	}

}
