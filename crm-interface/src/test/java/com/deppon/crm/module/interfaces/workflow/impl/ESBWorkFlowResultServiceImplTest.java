package com.deppon.crm.module.interfaces.workflow.impl;

import java.util.Date;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.workflow.IESBWorkFlowResultService;
import com.deppon.crm.module.interfaces.workflow.domain.WorkflowApplyResultInfo;

/**
 * @作者：罗典
 * @时间：2012-2-25
 * @描述：工作流
 * */
public class ESBWorkFlowResultServiceImplTest {

	private IESBWorkFlowResultService ESBWorkFlowResultService;

	@Before
	public void setUp() {
		JaxWsProxyFactoryBean jax = new JaxWsProxyFactoryBean();
		jax.setAddress("http://localhost:8088/crm/ws/esbWorkFlowResultService");
		jax.setServiceClass(IESBWorkFlowResultService.class);
		ESBWorkFlowResultService = (IESBWorkFlowResultService) jax.create();
	}

	/**
	 * @作者：罗典
	 * @时间：2012-2-25
	 * @描述：合同审批数据结果返回
	 * */
	@Test
	public void test() {
		WorkflowApplyResultInfo workflowApplyResultInfo = new WorkflowApplyResultInfo();
		workflowApplyResultInfo.setWorkflowNumber("3607136");
		workflowApplyResultInfo.setExamineDate(new Date());
		workflowApplyResultInfo.setExminePerson("089464");
		workflowApplyResultInfo.setExmineResult(true);
		workflowApplyResultInfo.setExmaineSuggestion(null);
		try {
			
			//{"exminePerson":"089464","workflowNumber":"3607136","examineDate":1347966078000,"exmineResult":true,"exmaineSuggestion":null}
			ESBWorkFlowResultService
					.returnContractApplyResult(workflowApplyResultInfo);
//			ESBWorkFlowResultService
//					.returnMuchRecompenseResult(workflowApplyResultInfo);
//			ESBWorkFlowResultService
//					.returnNormalRecompenseResult(workflowApplyResultInfo);
		} catch (CrmBusinessException e) {
			System.out.println(e.getErrorCode()
					+ "-----------------------------");
			e.printStackTrace();
		}
	}

}
