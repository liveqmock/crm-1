package com.deppon.crm.module.interfaces.workflow.impl;

import java.util.Date;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.workflow.IESBWorkFlowResultService;
import com.deppon.crm.module.interfaces.workflow.IGiftApplyResultService;
import com.deppon.crm.module.interfaces.workflow.domain.OtherBill;
import com.deppon.crm.module.interfaces.workflow.domain.WorkflowApplyResultInfo;

public class GiftApplyResultServiceImplTest {
	private IGiftApplyResultService giftApplyResultService;

	@Before
	public void setUp() {
		JaxWsProxyFactoryBean jax = new JaxWsProxyFactoryBean();
		jax.setAddress("http://localhost:8088/crm/ws/giftApplyResultService");
		jax.setServiceClass(IGiftApplyResultService.class);
		giftApplyResultService = (IGiftApplyResultService) jax.create();
	}

	@Test
	public void testReturnGiftApplyResult() {
		WorkflowApplyResultInfo workflowApplyResultInfo = new WorkflowApplyResultInfo();
		workflowApplyResultInfo.setWorkflowNumber("1869328");
		workflowApplyResultInfo.setExamineDate(new Date());
		workflowApplyResultInfo.setExminePerson("005072");
		workflowApplyResultInfo.setExmineResult(false);
		workflowApplyResultInfo.setExmaineSuggestion("1");

		try {
			giftApplyResultService
					.returnGiftApplyResult(workflowApplyResultInfo);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReturnGiftResult() {
		StringBuffer sb = new StringBuffer("");
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<otherBill>\n");
		sb.append("<billHead>\n");
		sb.append("<id>" + "11111" + "</id>\n");
		sb.append("<status>" + "false" + "</status>\n");
		sb.append("<common>" + "没什么可说的" + "</common>\n");
		sb.append("</billHead>\n");
		sb.append("</otherBill>\n");
		try {
			giftApplyResultService
					.returnGiftResult(sb.toString());
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
}
