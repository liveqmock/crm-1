/*package com.deppon.crm.module.interfaces.order;

import java.util.Date;
import java.util.List;

import javax.xml.ws.Holder;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.order.domain.OrderQueryCondition;
import com.deppon.crm.module.order.shared.domain.Order;

public class OrderTest {

	@Test
	public void test1(){
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setAddress("http://192.168.17.172:8088/crm/ws/bhoOrderService");
		factoryBean.setServiceClass(IBHOOrderService.class);
		IBHOOrderService service = (IBHOOrderService) factoryBean.create();
		OrderQueryCondition orderQueryCondition = new OrderQueryCondition();
		orderQueryCondition.setCustomerCode("1");
		orderQueryCondition.setPaymentType("1");
		orderQueryCondition.setOrderStatus("1");
		orderQueryCondition.setCustomerCode("1");
		orderQueryCondition.setPaymentType("1");
		orderQueryCondition.setOrderStatus("1");
		orderQueryCondition.setOrderNumber("1");
		orderQueryCondition.setWaybillNumber("1");
		orderQueryCondition.setReceiveContact("1");
		orderQueryCondition.setGoodsName("1");
		orderQueryCondition.setStartLine(1);
		orderQueryCondition.setEndLine(3);
		orderQueryCondition.setStartDate(new Date());
		orderQueryCondition.setEndDate(new Date());
		Holder<OrderQueryCondition> holder = new Holder<OrderQueryCondition>(orderQueryCondition);
		try {
			List<Order> orders = service.queryOrders(holder);
			Order order = service.searchOrder("Y12041020535");
			System.out.println(orders);
			System.out.println(order);
//			System.out.println(order.getChannelNumber());
//			System.out.println(orders.size());
		} catch (CrmBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
*/