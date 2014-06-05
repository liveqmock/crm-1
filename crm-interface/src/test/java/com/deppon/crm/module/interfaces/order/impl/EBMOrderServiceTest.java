/*package com.deppon.crm.module.interfaces.order.impl;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.order.IEBMOrderService;
import com.deppon.crm.module.order.shared.domain.Order;

public class EBMOrderServiceTest {
	IEBMOrderService ebmservice;

	@Before
	public void setUp() {
		JaxWsProxyFactoryBean jcf = new JaxWsProxyFactoryBean();
		jcf.setServiceClass(IEBMOrderService.class);
		jcf.setAddress("http://localhost:8088/crm/ws/ebmOrderService");
		ebmservice = (IEBMOrderService) jcf.create();
	}

	*//**
	 * @作者：罗典
	 * @描述：根据渠道单号订单信息
	 * @时间：2012-4-25
	 * *//*
	@Test
	public void queryOrder() {
		try {
			ebmservice.queryOrderByChannelNumber("W000000001");
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
	
	*//**
	 * @作者：罗典
	 * @描述：根据渠道单号订单信息
	 * @时间：2012-4-25
	 * *//*
	@Test
	public void bindShangchengId() {
		try {
			Order order = new Order();
			order = new Order();
			order.setContactName("罗典");
			order.setContactPhone("021-12345678");
			order.setContactMobile("13512345678");
			order.setContactProvince("湖北");
			order.setContactCity("武汉");
			order.setContactArea("武昌");
			order.setContactAddress("武胜路1001号");
			order.setStartStation("3232323232");
			order.setChannelCustId("LP0000223232");
			order.setOrderPerson("luodian");
			ebmservice.bindShangchengId(order);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
}
*/