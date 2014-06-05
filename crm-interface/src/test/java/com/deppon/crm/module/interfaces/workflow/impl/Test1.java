package com.deppon.crm.module.interfaces.workflow.impl;

import java.util.UUID;

import javax.xml.ws.Holder;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.interfaces.cc.ICallcenterToCrmService;
import com.deppon.crm.module.interfaces.cc.domain.CommException;
import com.deppon.crm.module.interfaces.cc.domain.CreateCallCenterCustomerRequest;
import com.deppon.crm.module.interfaces.cc.domain.CreateCallCenterCustomerResponse;
import com.deppon.crm.module.interfaces.cc.domain.MarketingInfoAddRequest;
import com.deppon.crm.module.interfaces.cc.domain.MarketingInfoAddResponse;
import com.deppon.crm.module.interfaces.cc.domain.ESBHeader;



public class Test1 {

	private ICallcenterToCrmService service;

	@Before
	public void setUp() {
		JaxWsProxyFactoryBean jax = new JaxWsProxyFactoryBean();
		//jax.setAddress("http://10.224.64.19:8088/crm/ws/callcenterToCrmService?wsdl");
		jax.setAddress("http://192.168.67.12:8580/esb2/ws/ccs2crm/ccs2CrmService?wsdl");
		jax.setServiceClass(ICallcenterToCrmService.class);
		service = (ICallcenterToCrmService) jax.create();

	}

	/**
	 * @throws CommException
	 * @throws TestMethodFault
	 * @作者：罗典
	 * @时间：2012-2-25
	 * @描述：合同审批数据结果返回
	 * */
	@org.junit.Test
	public void test() throws CrmBusinessException, CommException {
		MarketingInfoAddRequest request=new MarketingInfoAddRequest();
		request.setCellphone("13524455216");
		request.setCustName("wu");
		request.setCustNumber("215454");
		request.setMarketDept("DP23562");
		request.setMarketPerson("150256");
		request.setNeedQuestion("dkf");
		request.setNeedType("dkl");
		request.setSolution("dkfkld");
		request.setTelephone("559405");
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode("ESB_CCS2ESB_SEND_MARKETINGINFO");
		esbHeader.setBusinessId("dfdfad");
		esbHeader.setSourceSystem("CCS");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		MarketingInfoAddResponse response=	service.sendMarketingInfo(holder, request);
        System.out.println("dfdklfjkdl");
	}

}
