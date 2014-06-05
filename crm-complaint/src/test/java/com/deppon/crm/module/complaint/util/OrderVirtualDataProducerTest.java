package com.deppon.crm.module.complaint.util;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.complaint.server.util.OrderVirtualDataProducer;

public class OrderVirtualDataProducerTest {
	private OrderVirtualDataProducer orderVirtualDataProducer;
	@Before
	public void initUtil(){
		orderVirtualDataProducer = new OrderVirtualDataProducer();
	}
	
	@Test
	public void testGetOrderWaybillNum(){
		orderVirtualDataProducer.getOrderWaybillNum("001");
	}
}
