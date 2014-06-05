package com.deppon.crm.module.interfaces.stub;

import javax.jws.WebService;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.workflow.IGiftApplyResultService;
import com.deppon.crm.module.interfaces.workflow.domain.WorkflowApplyResultInfo;

@WebService(serviceName="GiftApplyResultServiceImplService",portName="GiftApplyResultServiceImplPort",endpointInterface="com.deppon.crm.module.interfaces.workflow.IGiftApplyResultService")
public class GiftApplyResultServiceTestImpl implements IGiftApplyResultService {

	@Override
	public boolean returnGiftApplyResult(WorkflowApplyResultInfo resultInfo)
			throws CrmBusinessException {
		return true;
	}

	@Override
	public boolean returnGiftResult(String xmlInfo) throws CrmBusinessException {
		// TODO Auto-generated method stub
		return false;
	}

/*	@Override
	public boolean returnGiftApplyResult(String xmlResultInfo)
			throws CrmBusinessException {
		// TODO Auto-generated method stub
		return false;
	}*/

}
