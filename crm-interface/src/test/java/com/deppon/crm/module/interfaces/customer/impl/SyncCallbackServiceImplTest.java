/*package com.deppon.crm.module.interfaces.customer.impl;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.interfaces.customer.IBHOCustomerService;
import com.deppon.crm.module.interfaces.customer.ISyncCallbackService;

public class SyncCallbackServiceImplTest {
	ISyncCallbackService syncCallbackService;
	@Before
	public void setUp(){
		JaxWsProxyFactoryBean jax = new JaxWsProxyFactoryBean();
//		jax.setAddress("http://192.168.17.172:8088/crm/ws/bhoCustomerService");
		jax.setAddress("http://localhost:8088/crm/ws/SyncCallbackService");
		jax.setServiceClass(ISyncCallbackService.class);
		syncCallbackService = (ISyncCallbackService)jax.create();
	}
	
	*//**
	 * @作者：罗典
	 * @时间：2012-4-24
	 * @功能描述：根据网单号和部门编码和联系人编码查询会员信息
	 *//*
	@Test
	public void testQueryCustomerByCode() {
		
		try {
			String nnn = "罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典罗典";
			syncCallbackService.handle("2012072800000402264556", "ERP", 100, nnn);
		} catch (CrmBusinessException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
*/