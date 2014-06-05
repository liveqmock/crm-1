package com.deppon.crm.test.client.workflow;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.exception.ErrorMessageCode;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.client.common.util.NullOrEmptyValidator;
import com.deppon.crm.module.client.workflow.IContractApplyOperate;
import com.deppon.crm.module.client.workflow.domain.ContractApplyType;
import com.deppon.crm.module.client.workflow.domain.ContractInfo;
import com.deppon.crm.module.client.workflow.domain.WorkFlowApplyInfo;
import com.deppon.esb.ws.ESBService;
import com.deppon.esb.ws.EsbJsonRequest;
import com.deppon.esb.ws.EsbJsonResponse;
import com.deppon.esb.ws.Exception_Exception;
import com.deppon.util.EntityCreateUtil;

public class ContractApplyOperateTest{
	
	private ESBService esbService;

	@Before
	public void setUp() {
		JaxWsProxyFactoryBean jax = new JaxWsProxyFactoryBean();
		jax.setAddress("http://192.168.17.141:8580/esb/webservice/workflow/apply");
		jax.setServiceClass(ESBService.class);
		esbService = (ESBService) jax.create();
	}
	
	@Test
	public void createContractTest() throws CrmBusinessException{
		String applyType=ContractApplyType.NEW.toString();
		ContractInfo contractInfo=EntityCreateUtil.createContractInfo();
		NullOrEmptyValidator.checkNull(contractInfo,"contractInfo");
		EsbJsonRequest ejr = new EsbJsonRequest();
		ejr.setServiceCode(Constant.ESB2OA_WORKFLOWAPPLY);
		WorkFlowApplyInfo wf = new WorkFlowApplyInfo();
		contractInfo.setApplyType(Constant.CONTRACT_NEW_CONTENT);
		wf.setBizType(Constant.CONTRACT_NEW);
		ejr.setSystemName(Constant.SYSTEM_NAME);
		wf.setBizInfo(JsonMapperUtil.writeValue(contractInfo));
		ejr.setBody(JsonMapperUtil.writeValue(wf));
		try {
			System.out.println(JsonMapperUtil.writeValue(ejr));
			EsbJsonResponse response = esbService.process(ejr);
			if ("SUCCESS".equalsIgnoreCase(response.getStatus())) {
				System.out.println(response.getResponse());
			} else {
				throw new CrmBusinessException(
						ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,response.getMessage());
			}

		} catch (Exception_Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(
					ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e, e
							.getFaultInfo().getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e.getMessage());
		}
	}
	
}
