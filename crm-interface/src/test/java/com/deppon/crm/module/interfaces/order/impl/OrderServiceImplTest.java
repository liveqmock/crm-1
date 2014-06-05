package com.deppon.crm.module.interfaces.order.impl;

import java.util.Date;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.order.IESBOrderService;
import com.deppon.crm.module.interfaces.order.domain.Order;
import com.deppon.crm.module.interfaces.order.domain.OrderStatusInfo;
import com.deppon.crm.module.order.server.service.IOrderService;

/**
 * @作者：罗典
 * @描述：接口-订单模块测试
 * @时间：2012-4-25
 * */
public class OrderServiceImplTest {

	IESBOrderService service = null;

	@Before
	public void setUp() {
		if (service == null) {
			JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
			factoryBean.setAddress("http://localhost:8088/crm/ws/esbOrderService");
			factoryBean.setServiceClass(IESBOrderService.class);
			service = (IESBOrderService) factoryBean.create();
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-20
	 * @描述：订单状态更新测试
	 * */
	@Test
	public void testUpdateOrderStatus() {
		OrderStatusInfo orderStatusInfo = new OrderStatusInfo();
		orderStatusInfo.setCrmOrderState(1);
		orderStatusInfo.setProcessPerson("075586");
		orderStatusInfo.setProcessDept("C12037");
		orderStatusInfo.setProcessDate(new Date());
		orderStatusInfo.setOrderNumber("Y130106536719");
		orderStatusInfo.setWaybillNumber("12345678");
		orderStatusInfo.setFailReason("时效太低");
		try {
			service.updateOrderStatus(orderStatusInfo);
		} catch (CrmBusinessException e) {

			e.printStackTrace();
		}

	}

	@Test
	public void testCreateOrder() throws CrmBusinessException{
		Order order=new Order();
		order.setResource("ONLINE");
		order.setDeliveryMode("PICKSELF");
		order.setOrderNumber("W140513732461");
		order.setContactName("宋");
		order.setContactMobile("13655555555");
		order.setContactProvince("广东省");
		order.setContactCity("广州市");
		order.setContactAddress("123");
		order.setStartStation("DP08273");
		order.setGoodsName("12");
		order.setTransportMode("JZCY");
		order.setContactArea("虹口区");
		order.setChannelCustId("zhangpei0009");
		order.setChannelNumber("W140513732117");
		order.setInsuredAmount(100d);
		order.setReviceMoneyAmount(0d);
		order.setReceiverCustCity("上海市");
		order.setReceiverCustProvince("上海市");
		order.setStartStationName("上海虹口区大连路营业部");
		order.setReceiverCustPhone("上海");
		order.setReceiverCustArea("崇明县");
		order.setReceiverCustAddress("123");
		order.setReceiverCustName("234");
		order.setReceiverCustMobile("13655444444");
		order.setPaymentType("PAY_ARIIVE");
		order.setReciveLoanType("INTRADAY");
		order.setReturnBillType("NO_RETURN_SIGNED");
		order.setIsReceiveGoods(false);
		order.setIsSendmessage(true);
		service.createOrder(order);
	}

}
