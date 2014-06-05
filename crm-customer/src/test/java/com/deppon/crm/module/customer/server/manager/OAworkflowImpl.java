/**   
 * @title OAworkflowImpl.java
 * @package com.deppon.crm.module.customer.server.manager
 * @description what to do
 * @author 潘光均
 * @update 2012-8-7 下午2:12:18
 * @version V1.0   
 */
package com.deppon.crm.module.customer.server.manager;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.workflow.IContractApplyOperate;
import com.deppon.crm.module.client.workflow.domain.ContractApplyType;
import com.deppon.crm.module.client.workflow.domain.ContractInfo;
import com.deppon.crm.module.client.workflow.domain.WorkFlowState;

/**
 * @description fuction  
 * @author 潘光均
 * @version 0.1 2012-8-7
 *@date 2012-8-7
 */

public class OAworkflowImpl implements IContractApplyOperate{

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.workflow.IContractApplyOperate#contractApply(com.deppon.crm.module.client.workflow.domain.ContractInfo, com.deppon.crm.module.client.workflow.domain.ContractApplyType)
	 */
	@Override
	public String contractApply(ContractInfo contractInfo,
			ContractApplyType applyType) throws CrmBusinessException {
		return  (int) (Math.random() * 1000000) + "";
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.workflow.IContractApplyOperate#queryWorkFlowApproveState(java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	public List<WorkFlowState> queryWorkFlowApproveState(Date beginDate,
			Date endDate, String workflowType) throws CrmBusinessException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.workflow.IContractApplyOperate#deleteWorkflow(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteWorkflow(String workflowNum, String workflowType)
			throws CrmBusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.workflow.IContractApplyOperate#contractApply(com.deppon.crm.module.client.workflow.domain.ContractInfo, com.deppon.crm.module.client.workflow.domain.ContractApplyType, java.lang.String)
	 */
	public String contractApply(ContractInfo contractInfo,
			ContractApplyType applyType, String bizCode)
			throws CrmBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
