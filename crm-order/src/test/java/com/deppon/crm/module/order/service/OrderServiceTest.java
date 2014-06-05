package com.deppon.crm.module.order.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.exception.ErrorMessageCode;
import com.deppon.crm.module.client.order.domain.AppointmentCarInfo;
import com.deppon.crm.module.order.server.service.IOrderService;
import com.deppon.crm.module.order.server.util.Constant;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderOperationLog;
import com.deppon.crm.module.order.shared.exception.OrderExceptionType;
import com.deppon.crm.module.order.util.TestUtil;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * 
 * <p>
 * Description:订单 service 测试类<br />
 * </p>
 * @title OrderServiceTest.java
 * @package com.deppon.crm.module.order.service 
 * @author 侯斌飞
 * @version 0.1 2012-12-18
 */
public class OrderServiceTest extends TestCase {

	static IOrderService orderService;
	Order order = null;
	//订单操作日志
	Set<OrderOperationLog> operationLogs = new HashSet<OrderOperationLog>();
	List<String> ids = new ArrayList<String>();
	static {
		orderService = TestUtil.orderService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		order = new Order();
		order.setId("1");
		order.setContactManId("0");
		order.setLastHastenTime(null);
		order.setHastenCount(0);
		order.setShipperId("1233332");
		order.setShipperNumber("dp123");
		order.setShipperName("德邦物流");
		order.setContactName("李艳芳");
		order.setContactMobile("111111111111");
		order.setContactProvince("天津");
		order.setContactCity("天津市");
		order.setContactArea("宁河县");
		order.setContactAddress("明珠路1018号");
		order.setIsReceiveGoods(true);
		order.setBeginAcceptTime(null);
		order.setEndAcceptTime(null);
		order.setReceiverCustId(null);
		order.setReceiver("德邦物流");
		order.setReceiverCustName("李艳芳");
		order.setReceiverCustNumber("dp123");
		order.setReceiverCustProvince("安徽省");
		order.setReceiverCustCity("马鞍市市");
		order.setReceiverCustArea("马化腾");
		order.setReceiverCustAddress("金家庄区");
		order.setContactAddress("明珠路1018号");
		order.setIsSendmessage(false);
		order.setTransportMode("JZCY");
		order.setGoodsName("炸药");
		order.setPacking("发个人");
		order.setGoodsType("A");
		order.setTotalWeight(12434.0);
		order.setTotalVolume(1243423.00);
		order.setPaymentType("REMIT");
		order.setChannelType(System.currentTimeMillis() + "");
		order.setChannelCustId(null);
		order.setDeliveryMode("PICKSELF");
		order.setReciveLoanType("0");
		order.setReviceMoneyAmount(546546.0);
		order.setInsuredAmount(546546.0);
		order.setOrderTime(null);
		order.setAcceptDept("2");
		order.setAcceptDeptName("德邦物流===");
		order.setCreateDate(new Date());
		order.setModifyDate(null);
		order.setResource(Constant.ORDER_SOURCE_ONLINE);
		order.setOrderNumber("dp" + System.currentTimeMillis());
		order.setTransNote("水电费特瑞");
		order.setOrderPerson("上海营");
		order.setStartStation("2");
		order.setWaybillNumber(System.currentTimeMillis() + "");
		order.setReceivingToPoint("4");
		order.setReturnBillType(" CUST_ORIGINAL");
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ALLOT);
		order.setOnlineName("depponOnline");
		order.setMemberType("alibborder");
		
		//订单 操作日志
		OrderOperationLog orderLog = new OrderOperationLog();
		orderLog.setId("1");
		orderLog.setContactName(order.getContactName());
		orderLog.setCreateDate(new Date());
		orderLog.setCreateUser(order.getCreateUser());
		orderLog.setOperatorContent(System.currentTimeMillis()+"");
		operationLogs.add(orderLog);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		//删除集合中不存在的 order 记录
		if(!ids.contains(order.getId())){
			orderService.deleteOrder(order.getId());
		}
		for (String id : ids) {
			orderService.deleteOrder(id);
		}
		
	}
	
	@Test
	public void testSaveOrder() {
		orderService.saveOrder(order);
	}
	@Test
	public void testUpdateOrder() {
		orderService.saveOrder(order);
		order.setChannelNumber(System.currentTimeMillis()+"");
		orderService.updateOrder(order);
		
		order.setChannelNumber(System.currentTimeMillis()+"");
		order.setOperationLogs(null);
		orderService.updateOrder(order);
		
		order.setOperationLogs(operationLogs);
		order.setChannelNumber(System.currentTimeMillis()+"");
		orderService.updateOrder(order);
	}

	@Test
	public void testUpdateOrderAssignBatch() {
		List<String> odrArray = new ArrayList<String>();
		orderService.saveOrder(order);
		ids.add(order.getId());
		odrArray.add(order.getId());
		
		order.setId(System.currentTimeMillis()+"");
		order.setOrderNumber(System.currentTimeMillis()+"");
		orderService.saveOrder(order);
		ids.add(order.getId());
		odrArray.add(order.getId());
		
		List<OrderOperationLog> orderOperationLogList = new ArrayList<OrderOperationLog>();
		orderOperationLogList.add(operationLogs.iterator().next());
		orderService.updateOrderAssignBatch(odrArray, "2", "德邦物流","2", "2",orderOperationLogList);	
	}
	
	@Test
	public void testUpdateOrderWithChannel(){
		orderService.saveOrder(order);
		order.setOperationLogs(operationLogs);
		orderService.updateOrderWithChannel(null, order);
	}
	
	@Test
	public void testGetOrderAndRegInfoByOrderNum(){
		
		//source == null || "".equals(source) || list.contains(source) == true
		order.setResource(Constant.ORDER_SOURCE_BUSINESS_DEPT);
		orderService.saveOrder(order);
		try {
			orderService.getOrderAndRegInfoByOrderNum(order.getOrderNumber());
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.BOUNDCONTACT_ORDERSOURCEERROR.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		
		//source == null || "".equals(source) || list.contains(source) == false
		order.setResource(Constant.ORDER_SOURCE_ONLINE);
		order.setChannelCustId(null);
		orderService.updateOrder(order);
		orderService.getOrderAndRegInfoByOrderNum(order.getOrderNumber());
		
		
		order.setChannelCustId(System.currentTimeMillis()+"");
		orderService.updateOrder(order);
		try {
			orderService.getOrderAndRegInfoByOrderNum(order.getOrderNumber());
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.REGISTERUSERNOTEXIST.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		
		try {
			orderService.getOrderAndRegInfoByOrderNum(System.currentTimeMillis()+"");
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.ORDER_NULL.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
	}
	
	
	@Test
	public void testUpdateOrderToErp() {
/*		orderService.saveOrder(order);
		order.setOperationLogs(operationLogs);
		AppointmentCarInfo erpOrder = new AppointmentCarInfo();
		try {
			orderService.updateOrderToErp(erpOrder, order);
		} catch (CrmBusinessException e) {
			if(e.getErrorCode().equals(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE)){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		order.setResource(Constant.ORDER_SOURCE_BUSINESS_DEPT);
		orderService.updateOrder(order);
		try {
			orderService.updateOrderToErp(erpOrder, order);
		} catch (CrmBusinessException e) {
			if(e.getErrorCode().equals(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE)){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		order.setResource(Constant.ORDER_SOURCE_CALLCENTER);
		orderService.updateOrder(order);
		try {
			orderService.updateOrderToErp(erpOrder, order);
		} catch (CrmBusinessException e) {
			if(e.getErrorCode().equals(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE)){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}
		
		order.setOrderStatus(Constant.ORDER_SATUTS_SHOUTCAR);
		orderService.updateOrder(order);
		try {
			orderService.updateOrderToErp(erpOrder, order);
		} catch (CrmBusinessException e) {
			if(e.getErrorCode().equals(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE)){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		} catch (GeneralException e) {
			if(e.getErrorCode().equals(OrderExceptionType.ORDER_HADBOOKVIHECLE.getErrorCode())){
				assertTrue(true);
			}else{
				fail("异常异常");
			}
		}*/
	}
}
