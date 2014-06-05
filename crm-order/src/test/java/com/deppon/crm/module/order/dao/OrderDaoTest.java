/**   
 * @title OrderDaoTest.java
 * @package com.deppon.crm.module.order.dao
 * @description what to do
 * @author 安小虎
 * @update 2012-3-9 上午11:45:43
 * @version V1.0   
 */
package com.deppon.crm.module.order.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.order.server.dao.IOrderDao;
import com.deppon.crm.module.order.server.util.Constant;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderSearchCondition;
import com.deppon.crm.module.order.util.TestUtil;
import com.deppon.foss.framework.server.context.AppContext;

/**
 * @description 订单DAO测试用例
 * @author 安小虎
 * @version 0.1 2012-3-9
 * @date 2012-3-9
 */

public class OrderDaoTest extends TestCase {

	static IOrderDao orderDao;
	Order order = null;

	static {
		
		orderDao = TestUtil.orderDao;
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
		order.setDeliveryMode("PICKFOOR");
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		orderDao.deleteOrderById(order.getId());
	}

	@Test
	public void testGetOrderByErpId() {
		List<Order> list = this.orderDao.getOrderByErpId("1");
		Assert.assertNotNull(list);
	}

	@Test
	public void testGetOrderListByCustId() {
		String custId = "525";
		Date startDate = new Date(2012, 7, 1);
		Date endDate = new Date();
		this.orderDao.getOrderListByCustId(custId, startDate, endDate, 0, 10);
		this.orderDao.getOrderCountByCustId(custId, startDate, endDate);
	}

	@Test
	public void testupdateOrderByOrderPerson() {
		Order order1 = new Order();
		order1.setChannelCustId("1111");
		order1.setOrderPerson("200712272");
		this.orderDao.updateOrderByOrderPerson(order1);
	}

	/**
	 * {@link com.deppon.crm.module.order.server.dao.impl.OrderDao#saveOrder(com.deppon.crm.module.order.shared.domain.Order)}
	 * 的测试方法。
	 */
	@Test
	public void testInsertOrder() {
		assertNotNull(orderDao);
		order.setResource(Constant.ORDER_SOURCE_ALIBABA);
		order.setStartStation("11174");
		order.setId(orderDao.getSequence());
		orderDao.saveOrder(order);
		System.out.println("~~~订单新增测试用例~~~id=" + order.getId() + "~num="
				+ order.getOrderNumber());
	}

	/**
	 * {@link com.deppon.crm.module.order.server.dao.impl.OrderDao#updateOrder(com.deppon.crm.module.order.shared.domain.Order)}
	 * 的测试方法。
	 */
	@Test
	public void testUpdateOrder() {
		assertNotNull(orderDao);
		orderDao.saveOrder(order);
		order.setIsReceiveGoods(false);
		Order ord = orderDao.updateOrder(order);
		assertEquals(order.getIsReceiveGoods(), ord.getIsReceiveGoods());

		orderDao.deleteOrderById(order.getId());
	}

	/**
	 * {@link com.deppon.crm.module.order.server.dao.impl.OrderDao#deleteOrder(java .lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testDeleteOrderById() {
		assertNotNull(orderDao);
		order.setId(orderDao.getSequence());
		order = orderDao.saveOrder(order);
		orderDao.deleteOrderById(order.getId());
	}

	/**
	 * {@link com.deppon.crm.module.order.server.dao.impl.OrderDao#selectOrderByID(java .lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testQueryOrderByID() {
		assertNotNull(orderDao);
		order.setId(orderDao.getSequence());
		order = orderDao.saveOrder(order);
		order = orderDao.getOrderById(order.getId());
	}

	/**
	 * {@link com.deppon.crm.module.order.server.dao.impl.OrderDao#getOrderByNum(java .lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testGetOrderByNum() {
		assertNotNull(orderDao);
		order.setId(orderDao.getSequence());
		order = orderDao.saveOrder(order);
		order = orderDao.getOrderByNum(order.getOrderNumber());
	}

	/**
	 * {@link com.deppon.crm.module.order.server.dao.impl.OrderDao#getOrderByWaybillNumber(java .lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testGetOrderByWaybillNumber() {
		assertNotNull(orderDao);
		order.setId(orderDao.getSequence());
		order = orderDao.saveOrder(order);
		order = orderDao.getOrderByWaybillNumber(order.getWaybillNumber());
	}

	/**
	 * {@link com.deppon.crm.module.order.server.dao.impl.OrderDao#getOrderByWaybillNumber(java .lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testSearchOrderCountByCondition() {
		assertNotNull(orderDao);
		order.setId(orderDao.getSequence());
		order = orderDao.saveOrder(order);

		OrderSearchCondition condition = new OrderSearchCondition();
		condition.setOrderStatus(order.getOrderStatus());
		Long count = orderDao.searchOrderCountByCondition(condition);
		System.out.println(count);
	}

	/**
	 * {@link com.deppon.crm.module.order.server.dao.impl.OrderDao#selectOrderByID(java .lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testQueryOrderListByOrderStatusList() {
		assertNotNull(orderDao);
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
	}

	/**
	 * {@link com.deppon.crm.module.order.server.dao.impl.OrderDao#queryOrderIsUpdateable(Map)}
	 * 的测试方法。
	 */
	@Test
	public void testQueryOrderIsUpdateable() {
		assertNotNull(orderDao);
		order.setId(orderDao.getSequence());
		Order ord = orderDao.saveOrder(order);
		order.setId(ord.getId());
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
	}

	@Test
	public void testQueryOrdersByIds() {
		List<String> orderIds = new ArrayList<String>();
		orderIds.add("10346");
		orderIds.add("10348");

		List<Order> list = orderDao.getOrdersByIds(orderIds);
		System.out.println(list);
	}

	@Test
	public void testQueryOrders() {
		//这个方法貌似没用到了。
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("onlineName", new String("deppononline"));
//		List<Order> list = orderDao.queryOrders(map);
//		System.out.println(list);
	}

	@Test
	public void testQueryOrderByChannelNumber() {
		Order order1 = orderDao.queryOrderByChannelNumber("AL01013516293");

	}

	@Test
	public void testUpdateOrderAssignBatch() {
		List<String> orderIds = new ArrayList<String>();
		orderIds.add("10346");
		orderIds.add("10348");

		boolean xx = orderDao.updateOrderAssignBatch(orderIds, "2", "德邦物流",
				"2", "2");
		System.out.println(xx);
	}

	@Test
	public void testGetOrderListByDeptIdsAndStatus() {
		order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
		order.setAcceptDept("121");
		List<String> ids = new ArrayList<String>();
		ids.add("121");
		order.setId(orderDao.getSequence());
		orderDao.saveOrder(order);
		List<Order> list = orderDao.getOrderListByDeptIdsAndStatus(ids,
				Constant.ORDER_STATUS_WAIT_ACCEPT);
		assertNotNull(list);
	}

	@Test
	public void testSearchOrders() {
		/** 空查询数据量太大,不进行测试 @author 华龙 */
		
	// = orderDao.searchOrders(con);
		//
		OrderSearchCondition  con = new OrderSearchCondition();
		// con.setStart(null);
		// orders = orderDao.searchOrders(con);
		// con.setStart(0);
		// con.setLimit(null);
		//orders = orderDao.searchOrders(con);
//		con.setLimit(3);
		
		con.setDeptIds(new ArrayList<String>());
		con.setStart(0);
		con.setStartDate(new Date(112, 6, 1));
		con.setEndDate(new Date(112, 7, 1));
		con.setStartStationId("11333");
		con.setCreateEmpName("张晓民");

		List<Order> orders = orderDao.searchOrders(con);
		
	}

	@Test
	public void testGetSequence() {
		String id = orderDao.getSequence();
		System.out.println(id + "==============");
		assertNotNull(id);
	}

	@Test
	public void testSearchAssignAndRefuseOrders() {
		OrderSearchCondition osc = new OrderSearchCondition();
		osc.setOrderStatus(Constant.ORDER_MESSAGE_ACCEPT);
		osc.setContactMobile("111111111");
		osc.setStart(1);
		osc.setLimit(10);
		List<Order> orders = orderDao.searchAssignAndRefuseOrders(osc);
		assertNotNull(osc);
	}

	@Test
	public void testQueryStartStationByHistory() {
		order.setId(orderDao.getSequence());
		orderDao.saveOrder(order);
		String startStation = orderDao.queryStartStationByHistory(
				order.getShipperId(), order.getContactProvince(),
				order.getContactCity(), order.getContactArea(),
				order.getContactAddress());
		assertEquals(startStation, order.getStartStation());
	}

	@Test
	public void testQueryStartStationByHistoryOne() {
		order.setId(orderDao.getSequence());
		orderDao.saveOrder(order);
		String startStation = orderDao.queryStartStationByHistory(order
				.getShipperId());
		assertEquals(startStation, order.getStartStation());
	}

	@Test
	public void testUpdateOrderStatus() {
		order.setId(orderDao.getSequence());
		orderDao.saveOrder(order);
		orderDao.updateOrderStatus(order.getId(), Constant.ORDER_STATUS_ACCEPT);
		orderDao.updateOrderStatus("11111111111111", Constant.ORDER_STATUS_ACCEPT);
	}

	@Test
	public void testUpdateOrderByWaybillNumber() {
		order.setId(orderDao.getSequence());
		orderDao.saveOrder(order);
		orderDao.updateOrderByWaybillNumber(order.getWaybillNumber());
	}

	/**
	 * @throws ParseException 
	 * @作者：罗典
	 * @描述：订单条件查询
	 * @时间：2012-11-8
	 * */
	@Test
	public void testQueryCondition() {
		Map<String, Object> map = new HashMap<String, Object>();
//		List<String> list = new ArrayList<String>();
//		list.add("ACCEPT");
//		list.add("FAILGOT");
//		map.put("orderStatus", list);
//		map.put("customerCode", "中山市越海电器有限公司");
		map.put("startLine", 1);
		map.put("endLine", 8);
//		map.put("orderType","ALIBABA");
//		map.put("acceptStatus","GOT");
		map.put("deptCode", "DP01225");
	/*	map.put("endDate",new Date());
		 SimpleDateFormat sd = new SimpleDateFormat("yy-MM-dd hh:mm:ss:SSS");
		map.put("startDate",sd.parse("2012-11-01 12:11:11:111"));
		System.out.println(sd.format(sd.parse("2012-11-01 12:11:11:111")));
		map.put("shipperCust","Cust");
		map.put("shipperLinkman","Linkman");
		map.put("shipperMobile","1351234");
		map.put("shipperPhone","1111111");*/
		orderDao.queryOrders(map);
		int ll = orderDao.countOrders(map);

		
		System.out.println(ll);
	}
	
	@Test
	public void testGetStartStationName() {
		order.setContactMobile("13816174681");
		order.setStartStation("11174");
		orderDao.saveOrder(order);
		String id1 = orderDao.getStartStationId(order.getContactMobile());
		Assert.assertEquals(order.getStartStation(), id1);

		String phone3 = "138000000000000";
		String id3 = orderDao.getStartStationId(phone3);
		Assert.assertNull(id3);
	}
}
