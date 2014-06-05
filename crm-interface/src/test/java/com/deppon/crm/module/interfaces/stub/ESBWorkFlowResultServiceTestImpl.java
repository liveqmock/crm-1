package com.deppon.crm.module.interfaces.stub;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.workflow.IESBWorkFlowResultService;
import com.deppon.crm.module.interfaces.workflow.domain.WorkflowApplyResultInfo;

public class ESBWorkFlowResultServiceTestImpl implements IESBWorkFlowResultService {

	@Override
	public boolean returnContractApplyResult(WorkflowApplyResultInfo resultInfo)
			throws CrmBusinessException {
		return true;
	}

	@Override
	public boolean returnNormalRecompenseResult(
			WorkflowApplyResultInfo resultInfo) throws CrmBusinessException {
		return true;
	}

	@Override
	public boolean returnMuchRecompenseResult(WorkflowApplyResultInfo resultInfo)
			throws CrmBusinessException {
		return true;
	}

}
