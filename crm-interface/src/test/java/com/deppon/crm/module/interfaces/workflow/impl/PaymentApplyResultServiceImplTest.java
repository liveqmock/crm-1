package com.deppon.crm.module.interfaces.workflow.impl;

import java.util.Date;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.workflow.IPaymentApplyResultService;
import com.deppon.crm.module.interfaces.workflow.domain.WorkflowApplyResultInfo;

/**
 * @作者：罗典
 * @时间：2012-4-26
 * @描述：再次付款申请测试
 * */
public class PaymentApplyResultServiceImplTest {
	IPaymentApplyResultService paymentApplyResultService;
	@Before
	public void setUp() throws Exception {
		JaxWsProxyFactoryBean jax = new JaxWsProxyFactoryBean();
		jax.setAddress("http://localhost:8088/crm/ws/paymentApplyResultService");
//		jax.setAddress("http://192.168.11.28:8088/crm/ws/paymentApplyResultService");
		jax.setServiceClass(IPaymentApplyResultService.class);
		paymentApplyResultService = (IPaymentApplyResultService)jax.create();
	}

	@Test
	public void testReturnVoucherPaymentResult(){
		try {
			paymentApplyResultService.returnVoucherPaymentResult("2013011853002", 0);
		} catch (CrmBusinessException e) { 
			e.printStackTrace();
		}
	} 
	
	/**
	 * @作者：罗典
	 * @时间：2012-4-26
	 * @描述：再次付款申请
	 * */
	@Test
	public void testReturnPaymentApplyResult() {
		WorkflowApplyResultInfo workflowApplyResultInfo = new WorkflowApplyResultInfo();
		workflowApplyResultInfo.setWorkflowNumber("1");
		workflowApplyResultInfo.setExamineDate(new Date());
		workflowApplyResultInfo.setExminePerson("1");
		workflowApplyResultInfo.setExmineResult(true);
		workflowApplyResultInfo.setExmaineSuggestion("1");
		try {
			paymentApplyResultService.returnPaymentApplyResult(workflowApplyResultInfo);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

}
