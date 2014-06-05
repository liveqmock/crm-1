/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BpsContractApplyOerate.java
 * @package com.deppon.crm.module.customer.bpsworkflow 
 * @author pgj
 * @version 0.1 2013-11-15
 */
package com.deppon.crm.module.customer.bpsworkflow;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.bps.server.manager.IBpsWorkflowManager;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.workflow.IContractApplyOperate;
import com.deppon.crm.module.client.workflow.domain.ContractApplyType;
import com.deppon.crm.module.client.workflow.domain.ContractInfo;
import com.deppon.crm.module.client.workflow.domain.WorkFlowState;
import com.deppon.crm.module.customer.bpsworkflow.util.BpsConstant;

/**
 * <p>
 * Description:合同工作流申请，BPS实现方式<br />
 * </p>
 * 
 * @title BpsContractApplyOerate.java
 * @package com.deppon.crm.module.customer.bpsworkflow
 * @author pgj
 * @version 0.1 2013-11-15
 */

public class ContractApplyOperate implements IContractApplyOperate {
	// bps工作流
	private IBpsWorkflowManager bpsWorkflowManager;
	// oa工作流
	private IContractApplyOperate contractApplyOperate;

	/**
	 * @param contractApplyOperate
	 *            : set the property contractApplyOperate.
	 */
	public void setContractApplyOperate(
			IContractApplyOperate contractApplyOperate) {
		this.contractApplyOperate = contractApplyOperate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.client.workflow.IContractApplyOperate#deleteWorkflow
	 * (java.lang.String, java.lang.String)
	 */
	public boolean deleteWorkflow(String workflowNum, String workflowType)
			throws CrmBusinessException {
		return getBpsWorkflowManager().removeMyProcessInstExt(Long
				.parseLong(workflowNum));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.client.workflow.IContractApplyOperate#contractApply
	 * (com.deppon.crm.module.client.workflow.domain.ContractInfo,
	 * com.deppon.crm.module.client.workflow.domain.ContractApplyType)
	 */
	@Override
	public String contractApply(ContractInfo contractInfo,
			ContractApplyType applyType,String bizCode) throws CrmBusinessException {
		//工作流id
		String workflowId = null;
		Map<String, String> map = null;
		//新签
		if (ContractApplyType.NEW.equals(applyType)) {
			//生成工作流
			map = bpsWorkflowManager.createWorkflow(
					BpsConstant.LTT_NEW_PROCESSNAME,
					BpsConstant.PROCESS_INSTANCE_LTT_NEW+bizCode,
					BpsConstant.PROCESS_INSTANCE_LTT_NEW_DESCRIPTION,bizCode);
			//快递新签
		} else if (ContractApplyType.EX_NEW.equals(applyType)) {
			//生成工作流
			map = bpsWorkflowManager.createWorkflow(
					BpsConstant.EX_NEW_PROCESSNAME,
					BpsConstant.PROCESS_INSTANCE_EX_NEW+bizCode,
					BpsConstant.PROCESS_INSTANCE_EX_NEW_DESCRIPTION,bizCode);
			//修改
		} else if (ContractApplyType.UPDATE.equals(applyType)) {
			//生成工作流
			map = bpsWorkflowManager.createWorkflow(
					BpsConstant.LTT_UPDATE_PROCESSNAME,
					BpsConstant.PROCESS_INSTANCE_LTT_UPADTE+bizCode,
					BpsConstant.PROCESS_INSTANCE_LTT_UPDATE_DESCRIPTION,bizCode);
			//快递修改
		} else if (ContractApplyType.EX_UPDATE.equals(applyType)) {
			//生成工作流
			map = bpsWorkflowManager.createWorkflow(
					BpsConstant.EX_UPDATE_PROCESSNAME,
					BpsConstant.PROCESS_INSTANCE_EX_UPADTE+bizCode,
					BpsConstant.PROCESS_INSTANCE_LTT_UPADTE_DESCRIPTION,bizCode);
			//合同终止
		} else if (ContractApplyType.CANCEL.equals(applyType)||ContractApplyType.EX_CANCEL.equals(applyType)) {
			map = bpsWorkflowManager.createWorkflow(
					BpsConstant.CANCEL_PROCESSNAME,
					BpsConstant.PROCESS_INSTANCE_CANCEL+bizCode,
					BpsConstant.PROCESS_INSTANCE_CANCEL_DESCRIPTION,bizCode);
		//其他工作流走OA
		}else {
			workflowId = this.contractApply(contractInfo, applyType);
		}
		//工作流号为bizCode
		if(null!=map){
			workflowId=map.get("bizCode");
		}
		//返回工作流号
		return workflowId;
	}
	/**
	 * <p>
	 * Description:生产业务编码<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-11-30
	 * void
	 */
	public String bizCode() {
		return bpsWorkflowManager.bizCode();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.client.workflow.IContractApplyOperate#
	 * queryWorkFlowApproveState(java.util.Date, java.util.Date,
	 * java.lang.String)
	 */
	public List<WorkFlowState> queryWorkFlowApproveState(Date beginDate,
			Date endDate, String workflowType) throws CrmBusinessException {
		return null;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * IBpsWorkflowManager
	 */
	public IBpsWorkflowManager getBpsWorkflowManager() {
		return bpsWorkflowManager;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param bpsWorkflowManager
	 * void
	 */
	public void setBpsWorkflowManager(IBpsWorkflowManager bpsWorkflowManager) {
		this.bpsWorkflowManager = bpsWorkflowManager;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param contractInfo
	 * @param applyType
	 * @return
	 * @throws CrmBusinessException
	 *
	 */
	@Override
	public String contractApply(ContractInfo contractInfo,
			ContractApplyType applyType) throws CrmBusinessException {
		//合同申请
		return contractApplyOperate.contractApply(contractInfo, applyType);
	}
}
