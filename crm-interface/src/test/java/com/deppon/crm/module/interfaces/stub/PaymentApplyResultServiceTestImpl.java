package com.deppon.crm.module.interfaces.stub;

import javax.jws.WebService;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.workflow.IPaymentApplyResultService;
import com.deppon.crm.module.interfaces.workflow.domain.WorkflowApplyResultInfo;

@WebService(portName="PaymentApplyResultServiceImplPort",serviceName="PaymentApplyResultServiceImplService",endpointInterface="com.deppon.crm.module.interfaces.workflow.IPaymentApplyResultService")
public class PaymentApplyResultServiceTestImpl implements IPaymentApplyResultService{

	@Override
	public boolean returnPaymentApplyResult(WorkflowApplyResultInfo resultInfo)
			throws CrmBusinessException {
		return true;
	}

	@Override
	public boolean returnVoucherPaymentResult(String voucherNum,
			int paymentResult) throws CrmBusinessException {
		// TODO Auto-generated method stub
		return false;
	}

}
