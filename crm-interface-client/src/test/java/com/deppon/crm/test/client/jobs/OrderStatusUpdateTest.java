/*package com.deppon.crm.test.client.jobs;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.util.Assert;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.jobs.impl.LogSenderImpl;
import com.deppon.crm.module.client.order.domain.ChannelOrderStatusInfo;
import com.deppon.crm.module.client.order.domain.OrderStatusLog;
import com.deppon.crm.test.client.common.OperaterTest;

public class OrderStatusUpdateTest extends OperaterTest  {
	
	@Test
	public void test(){
		String orderNum = "A120510333966";
		String orderStatus = "ACCEPT";
		String orderSource = "ALIBABA";
		
		List<ChannelOrderStatusInfo> orders = new ArrayList<ChannelOrderStatusInfo>();
		
		ChannelOrderStatusInfo info = new ChannelOrderStatusInfo();
		info.setChannelOrderNumber(orderNum);
		info.setOrderStatus(orderStatus);
		info.setOrderSource(orderSource);
		
		ChannelOrderStatusInfo info2 = new ChannelOrderStatusInfo();
		info2.setChannelOrderNumber("LP88889366");
		info2.setOrderStatus("GOT");
		info2.setOrderSource("TAOBAO");
		
		orders.add(info);
		orders.add(info2);
		try {
			List<String> result = 
			orderOperate2.updateOrderStatus(orders);
			Assert.notNull(result);
			
			System.out.println(result.size());
			
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2(){
		OrderStatusLog log = new OrderStatusLog();
		log.setOrderNumber("LP88889366");
		log.setOrderStatus("GOT");
		log.setOrderSource("TAOBAO");
		OrderStatusLog log2 = new OrderStatusLog();
		log2.setOrderNumber("A120510333966");
		log2.setOrderStatus("ACCEPT");
		log2.setOrderSource("ALIBABA");
		
		LogSenderImpl senderImpl = new LogSenderImpl();
		senderImpl.setOrderOperate(orderOperate2);
		
		List<OrderStatusLog> logs = new ArrayList<OrderStatusLog>();
		logs.add(log2);
		logs.add(log);
		int[] result = senderImpl.send(logs);
		
		Assert.notNull(result);
		System.out.println(result[0]);
	}
	
	@Test
	public void test3(){
		OrderStatusLog log = new OrderStatusLog();
		log.setOrderNumber("LP88889366");
		log.setOrderStatus("GOT");
		log.setOrderSource("TAOBAO");
		OrderStatusLog log2 = new OrderStatusLog();
		log2.setOrderNumber("A120510333966");
		log2.setOrderStatus("ACCEPT");
		log2.setOrderSource("ALIBABA");
		
		LogSenderImpl senderImpl = new LogSenderImpl();
		senderImpl.setOrderOperate(orderOperate2);
		
		List<OrderStatusLog> logs = new ArrayList<OrderStatusLog>();
		logs.add(log2);
//		logs.add(log);
		int[] result = senderImpl.send(logs);
		
		Assert.isNull(result);
//		System.out.println(result);
//		System.out.println(result[0]);
	}
	
	
	
}
*/