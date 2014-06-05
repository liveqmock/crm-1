package com.deppon.crm.module.interfaces.workflow.impl;

import java.util.Date;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.interfaces.workflow.IWorkflowApplyResultService;
import com.deppon.crm.module.interfaces.workflow.domain.WorkflowApplyResultInfo;

public class IWorkflowApplyResultServiceTest {
	IWorkflowApplyResultService workflowApplyResultService;

	@Before
	public void setUp() throws Exception {
		JaxWsProxyFactoryBean jax = new JaxWsProxyFactoryBean();
		jax.setAddress("http://localhost:8088/crm/ws/workflowApplyResultService");
		// jax.setAddress("http://192.168.11.28:8088/crm/ws/paymentApplyResultService");
		jax.setServiceClass(IWorkflowApplyResultService.class);
		workflowApplyResultService = (IWorkflowApplyResultService) jax.create();
	}

	@Test
	public void returnBackFreightResultTest() {
		try {
			WorkflowApplyResultInfo resultInfo = new WorkflowApplyResultInfo();
			resultInfo.setExamineDate(new Date());
			resultInfo.setExmaineSuggestion("043546"); 
			resultInfo.setExminePerson("075586");
			resultInfo.setExmineResult(true); 
			resultInfo.setWorkflowNumber("6133794");
			workflowApplyResultService.returnBackFreightResult(JsonMapperUtil
					.writeValue(resultInfo));
			// workflowApplyResultService
			// .returnBackFreightResult("{\"exmineResult\":true,\"workflowNumber\":6110253,\"exminePerson\":\"043546\",\"examineDate\":1354331258000,\"exmaineSuggestion\":null} ");
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
	@Test 
	public void returnServiceRecoveryResultTest() {  
		try { 
			WorkflowApplyResultInfo resultInfo = new WorkflowApplyResultInfo();
			resultInfo.setExamineDate(new Date()); 
			resultInfo.setExmaineSuggestion("043546"); 
			resultInfo.setExminePerson("075586");
			resultInfo.setExmineResult(true);
			resultInfo.setWorkflowNumber("6113385");
			workflowApplyResultService.returnServiceRecoveryResult(JsonMapperUtil
					.writeValue(resultInfo));
			// workflowApplyResultService
			// .returnBackFreightResult("{\"exmineResult\":true,\"workflowNumber\":6110253,\"exminePerson\":\"043546\",\"examineDate\":1354331258000,\"exmaineSuggestion\":null} ");
		} catch (CrmBusinessException e) {
			e.printStackTrace(); 
		}
	}
}
