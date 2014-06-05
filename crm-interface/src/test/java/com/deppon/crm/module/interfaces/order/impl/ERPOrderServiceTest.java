/*package com.deppon.crm.module.interfaces.order.impl;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.order.IERPOrderService;
import com.deppon.crm.module.order.shared.domain.Order;

public class ERPOrderServiceTest {
	IERPOrderService erpService;

	@Before
	public void setUp() {
		JaxWsProxyFactoryBean jcf = new JaxWsProxyFactoryBean();
		jcf.setServiceClass(IERPOrderService.class);
		jcf.setAddress("http://192.168.17.172:8088/crm/ws/erpOrderService");
		erpService = (IERPOrderService) jcf.create();
	}

	*//**
	 * @作者：罗典
	 * @描述：根据单号订单信息
	 * @时间：2012-4-25
	 * *//*
	@Test
	public void searchOrder() {
		try {
		 Order order =	erpService.searchOrder("W120730310838");
		 order.getBeginAcceptTime();
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
}
*/