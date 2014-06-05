package com.deppon.crm.module.interfaces.ows;

import java.util.UUID;

import javax.xml.ws.Holder;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.interfaces.ows.domain.CommonExceptionInfo_Exception;
import com.deppon.crm.module.interfaces.ows.domain.CreateNewCouponRequest;
import com.deppon.crm.module.interfaces.ows.domain.CreateNewCouponResponse;
import com.deppon.crm.module.interfaces.ows.domain.ESBHeader;

public class OwsToCrmServiceTest {

	private IOwsToCrmService owsToCrmService;
	
	@Before
	public void setUp() {
		JaxWsProxyFactoryBean jcf = new JaxWsProxyFactoryBean();
		jcf.setServiceClass(IOwsToCrmService.class);
		// jcf.setAddress("http://localhost:8080/jaxwebservice");
		jcf.setAddress("http://localhost:8088/crm/ws/owsToCrmService");
		jcf.getOutInterceptors()
				.add(new com.deppon.crm.module.client.esb.trans.SoapMessageOutInterceptor());
		
		owsToCrmService = (IOwsToCrmService) jcf.create();
	}
	
	
	@Test
	public void  createNewCouponTest() throws CommonExceptionInfo_Exception{
		
		CreateNewCouponRequest request = new CreateNewCouponRequest();
		request.setDeptCode("DP5897414");
		
		ESBHeader esbHeader1 = new ESBHeader();
		esbHeader1.setRequestId(UUID.randomUUID().toString());
		
		Holder<ESBHeader> esbHeader = new Holder<ESBHeader>(esbHeader1);
	
		CreateNewCouponResponse response = owsToCrmService.createNewCoupon(esbHeader,request);
		
		System.out.println("----"+response.getCouponCode());
		
	}
	
	
	
}
