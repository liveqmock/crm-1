package com.deppon.crm.test.client.map;



import java.util.Date;
import java.util.UUID;

import javax.xml.ws.Holder;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.foss.crm.CancelOrderRequest;
import com.deppon.foss.crm.CancelOrderResponse;
import com.deppon.foss.crm.CommonException;
import com.deppon.foss.crm.CustomerService;
import com.deppon.foss.crm.ESBHeader;

/**
 * @作者：罗典
 * @时间：2012-2-25
 * @描述：工作流
 * */
public class Test1 {

	private CustomerService customer;

	@Before
	public void setUp() {
		JaxWsProxyFactoryBean jax = new JaxWsProxyFactoryBean();
		//http://192.168.10.92:8180/esb2/ws/crm2foss/customerService
		//http://192.168.67.12:8180/esb2/ws/crm2foss/customerService
		jax.setAddress("http://192.168.67.12:8180/esb2/ws/crm2foss/customerService");
		jax.setServiceClass(CustomerService.class);
		customer = (CustomerService) jax.create();
	}

	/**
	 * @throws Exception 
	 * @作者：罗典
	 * @时间：2012-2-25
	 * @描述：合同审批数据结果返回
	 * */
	@Test
	public void test() throws Exception { 
		CancelOrderRequest request=new CancelOrderRequest();
		request.setOrderNumber("4444");
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode(Constant.FOSS_CANCEL_ORDER);
		esbHeader.setBusinessId("444");
		esbHeader.setSourceSystem("CRM");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		CancelOrderResponse response=customer.cancelOrder(request, holder); 
		System.out.println(response.getResult());
		
	}

}