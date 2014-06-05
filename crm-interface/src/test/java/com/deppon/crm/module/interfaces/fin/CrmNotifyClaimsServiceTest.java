package com.deppon.crm.module.interfaces.fin;

import java.math.BigDecimal;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import com.deppon.crm.module.interfaces.fin.domain.NotifyClaimsStateRequest;
import com.deppon.crm.module.interfaces.fin.domain.NotifyClaimsStateResponse;

public class CrmNotifyClaimsServiceTest {

	ICrmNotifyClaimsService serviceImpl;

//	@Before
//	public void setUp() {
//		JaxWsProxyFactoryBean jcf = new JaxWsProxyFactoryBean();
//		jcf.setServiceClass(ICrmNotifyClaimsService.class);
//		jcf.setAddress("http://localhost:8088/crm/ws/crmNotifyClaimsService");
//		jcf.getOutInterceptors()
//				.add(new com.deppon.crm.module.client.esb.trans.SoapMessageOutInterceptor());
//		serviceImpl = (ICrmNotifyClaimsService) jcf.create();
//	}
	
	@Test
	public void testCrmNotifyClaimsService(){ 
		JaxWsProxyFactoryBean jcf = new JaxWsProxyFactoryBean();
		jcf.setServiceClass(ICrmNotifyClaimsService.class); 
		jcf.setAddress("http://localhost:8088/crm/ws/crmNotifyClaimsService");
//		jcf.getOutInterceptors()
//				.add(new com.deppon.crm.module.client.esb.trans.SoapMessageOutInterceptor());
		serviceImpl = (ICrmNotifyClaimsService) jcf.create();
		NotifyClaimsStateRequest request = new NotifyClaimsStateRequest(); 
		request.setVoucherNumber("12345");
		request.setDepartmentCode("DP25"); 
		request.setPaymentMoney(new BigDecimal("56"));
		request.setPaymentResults("1"); 
		NotifyClaimsStateResponse response=serviceImpl.notifyClaimState(request);
		System.out.println(response.getFailedReason());
		System.out.println(response.isIsSuccess()); 
	}   

}
