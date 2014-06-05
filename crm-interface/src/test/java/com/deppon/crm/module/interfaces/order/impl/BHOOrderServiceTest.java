/*package com.deppon.crm.module.interfaces.order.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.ws.Holder;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.order.IBHOOrderService;
import com.deppon.crm.module.interfaces.order.domain.OrderQueryCondition;
import com.deppon.crm.module.order.shared.domain.Order;

*//**
 * @作者：罗典
 * @描述：接口-官网订单模块测试
 * @时间：2012-4-25
 * *//*
public class BHOOrderServiceTest {
	IBHOOrderService bhoService =null;

	@Before
	public void setUp() {
		JaxWsProxyFactoryBean jcf = new JaxWsProxyFactoryBean();
		jcf.setServiceClass(IBHOOrderService.class);
		jcf.setAddress("http://localhost:8088/crm/ws/bhoOrderService");
//		jcf.setAddress("http://192.168.17.172:8088/crm/ws/bhoOrderService");
		bhoService = (IBHOOrderService) jcf.create();
	}

	*//**
	 * @作者：罗典
	 * @描述：撤销订单测试
	 * @时间：2012-4-25
	 * *//*
	@Test
	public void testCancelOrder() {
		try {
			bhoService.cancelOrder("W0000000001", "ALIBABA", "已更换其它物流！");
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	*//**
	 * @作者：罗典
	 * @描述：创建订单测试
	 * @时间：2012-4-25
	 * *//*
	@Test
	public void testSearchOrder() {
		try {
			bhoService.searchOrder("6858185");
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	*//**
	 * @作者：罗典
	 * @描述：根据条件查询订单测试
	 * @时间：2012-4-25
	 * *//*
	@Test
	public void testQueryOrders() {
		OrderQueryCondition orderQueryCondition = new OrderQueryCondition();
		List<String> list = new ArrayList<String>();
		list.add("IN_TRANSIT");
		list.add("ACCEPT");
		orderQueryCondition.setOrderStatus(list);
		orderQueryCondition.setCustomerCode("中山市越海电器有限公司");
		// orderQueryCondition.setPaymentType("1");
		// orderQueryCondition.setOrderNumber("1");
		// orderQueryCondition.setWaybillNumber("1");
		// orderQueryCondition.setReceiveContact("1");
		// orderQueryCondition.setGoodsName("1");
		orderQueryCondition.setStartLine(1);
		orderQueryCondition.setEndLine(8);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse("2011-01-01");
			orderQueryCondition.setStartDate(date);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		orderQueryCondition.setEndDate(new Date());
		try {
			Holder<OrderQueryCondition> orderQueryConditionHolder = new Holder<OrderQueryCondition>(
					orderQueryCondition);
			List<Order> orderList = bhoService
					.queryOrders(orderQueryConditionHolder);
			System.out.println(orderList.size() + "----"
					+ orderQueryConditionHolder.value.getCount());
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	*//**
	 * @作者：罗典
	 * @描述：修改订单测试
	 * @时间：2012-4-25
	 * *//*
	@Test
	public void testUpdateOrder() {
		Order order = new Order();
		order.setHastenCount(Integer.valueOf("1"));
		order.setLastHastenTime(new Date());
//		order.setContactManId(Integer.valueOf("1"));
		order.setOrderNumber("1");
		order.setChannelNumber("W11111111111111");
		order.setHastenCount(Integer.valueOf("1"));
		order.setLastHastenTime(new Date());
//		order.setContactManId(Integer.valueOf("1"));
		order.setOrderNumber("1");
		order.setChannelNumber("6858185");
		order.setShipperId("1");
		order.setShipperNumber("1");
		order.setShipperName("1");
		order.setContactName("1");
		order.setContactMobile("1");
		order.setContactPhone("1");
		order.setContactProvince("1");
		order.setContactCity("1");
		order.setContactArea("1");
		order.setContactAddress("1");
		order.setIsReceiveGoods(true);
		order.setBeginAcceptTime(new Date());
		order.setEndAcceptTime(new Date());
		order.setReceiverCustId("1");
		order.setReceiverCustNumber("1");
		order.setReceiverCustcompany("1");
		order.setReceiverCustName("1");
		order.setReceiverCustMobile("1");
		order.setReceiverCustPhone("1");
		order.setReceiverCustProvince("1");
		order.setReceiverCustCity("1");
		order.setReceiverCustArea("1");
		order.setReceiverCustAddress("1");
		order.setIsSendmessage(true);
		order.setReceivingToPoint("1");
		order.setTransportMode("1");
		order.setGoodsName("1");
		order.setPacking("1");
		order.setGoodsType("1");
		order.setGoodsNumber(Integer.valueOf("1"));
		order.setTotalWeight(Double.valueOf("1"));
		order.setTotalVolume(Double.valueOf("1"));
		order.setPaymentType("1");
		order.setChannelType("1");
		order.setChannelCustId("1");
		order.setDeliveryMode("1");
		order.setReciveLoanType("1");
		order.setReviceMoneyAmount(Double.valueOf("1"));
		order.setInsuredAmount(Double.valueOf("1"));
		order.setOrderTime(new Date());
		order.setStartStation("DP02117");
		order.setStartStationName("1");
		order.setAcceptDept("1");
		order.setAcceptDeptName("1");
		order.setOrderStatus("1");
		order.setDealPerson("1");
		order.setOrderAcceptTime(new Date());
		order.setReceiver("1");
		order.setAccepterPersonMobile("1");
//		order.setWaybillNumber("1");
		order.setResource("ALIBABA");
		order.setTransNote("1");
		order.setReturnBillType("1");
		order.setOrderPerson("1");
		order.setFeedbackInfo("1");
		try {
			bhoService.updateOrder(order);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
}
*/