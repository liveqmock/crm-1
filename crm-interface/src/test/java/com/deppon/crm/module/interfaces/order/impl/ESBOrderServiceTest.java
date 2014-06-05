package com.deppon.crm.module.interfaces.order.impl;

import java.util.Date;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.order.IESBOrderService;
import com.deppon.crm.module.interfaces.order.domain.OrderStatusInfo;
import com.deppon.crm.module.interfaces.order.domain.Order;

public class ESBOrderServiceTest {
	IESBOrderService erpService;

	@Before
	public void setUp() {
		JaxWsProxyFactoryBean jcf = new JaxWsProxyFactoryBean();
		jcf.setServiceClass(IESBOrderService.class);
		jcf.setAddress("http://localhost:8088/crm/ws/esbOrderService");
		erpService = (IESBOrderService) jcf.create();
	}

	/**
	 * @作者：罗典
	 * @描述：更新订单状态
	 * @时间：2012-4-25
	 * */
	@Test
	public void updateOrderStatus() {
		OrderStatusInfo orderStatusInfo = new OrderStatusInfo();
		orderStatusInfo.setCrmOrderState(5);
		orderStatusInfo.setProcessPerson("075586");
		orderStatusInfo.setProcessDept("DP00497");
		orderStatusInfo.setProcessDate(new Date());
		orderStatusInfo.setOrderNumber("A1924511");
		orderStatusInfo.setWaybillNumber("12345678");
		orderStatusInfo.setFailReason("时效太低");
		try {
			erpService.updateOrderStatus(orderStatusInfo);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @作者：罗典
	 * @描述：新增订单
	 * @时间：2012-4-25
	 * */
	@Test
	public void createOrder() throws CrmBusinessException {
		Order order = new Order();
		order.setResource("ONLINE");
		order.setContactName("z");
		order.setTotalWeight(null);
		order.setContactPhone("");
		order.setReceiverCustNumber(null);
		order.setShipperNumber("087539");
		order.setContactMobile("12121213");
		order.setContactProvince("北京");
		order.setContactCity("北京市");
		order.setContactAddress("debang-peisongshangmen");
		order.setStartStation("DP09725");
		order.setGoodsName("电视");
		order.setTransportMode("JZCY");
		order.setDeliveryMode("PICKSELF");
		order.setGoodsNumber(1);
		order.setTotalVolume(null);
		order.setTransNote("");
		order.setBeginAcceptTime(null);
		order.setEndAcceptTime(null);
		order.setOrderStatus(null);
		order.setAcceptDept(null);
		order.setLastHastenTime(null);
		order.setContactArea("昌平区");
		order.setOrderNumber("W1309113037920");
		order.setCouponNumber(null);
		order.setWaybillNumber("52653562");
		order.setChannelCustId("yonghuming");
		order.setChannelNumber("W1309113037920");
		order.setReceivingToPoint("DP09725");
		order.setShipperId("87539");
		order.setReceiverCustAddress("s");
		order.setReceiverCustName("杜天龙");
	    order.setContactManId("217067");
	    order.setFeedbackInfo(null);
	    order.setPacking("1纸");
	    order.setReceiver(null);
	    order.setReceiverCustMobile("13965245141");
	    order.setReceiverCustPhone("");
	    order.setReceiverCustArea("昆山市");
	    order.setInsuredAmount(new Double(10.0));
	    order.setReviceMoneyAmount(new Double(10.0));
	    order.setReciveLoanType("INTRADAY");
	    order.setReturnBillType("NO_RETURN_SIGNED");
	    order.setPaymentType("PAY_ARIIVE");
	    order.setDealPerson(null);
	    order.setReceiverCustcompany(null);
	    order.setReceiverCustId(null);
	    order.setAcceptDeptName(null);
	    order.setOrderAcceptTime(null);
	    order.setAccepterPersonMobile(null);
	    order.setOrderPerson(null);
	    order.setReceivingToPointName("昆山周市营业部");
	    order.setOnlineName("");
	    order.setMemberType(null);
	    order.setIsReceiveGoods(false);
	    order.setIsSendmessage(true);
	    order.setId(null);
	    order.setModifyUser(null);
	    order.setCreateDate(null);
	    order.setCreateUser(null);
	    order.setModifyDate(null);
	    erpService.createOrder(order);
	}
}
