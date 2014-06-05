package com.deppon.crm.module.order.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.order.server.dao.IOrderReminderDao;
import com.deppon.crm.module.order.server.util.Constant;
import com.deppon.crm.module.order.shared.domain.OrderAcceptConfig;
import com.deppon.crm.module.order.shared.domain.OrderAcceptInfo;
import com.deppon.crm.module.order.shared.domain.OrderReminder;
import com.deppon.crm.module.order.util.TestUtil;

public class OrderReminderDaoTest extends TestCase {
	static IOrderReminderDao orderReminderDao;
	OrderReminder orderReminder = null;

	static {
		orderReminderDao = TestUtil.orderReminderDao;
	}

	@Before
	public void setUp() throws Exception {

		// 变量
		Integer int_ = 1;
		String str_ = "2";
		boolean boo_ = true;
		Date date_ = new Date(2012, 3, 9);
		Double double_ = 1D;
		// 构造订单对象
		orderReminder = new OrderReminder();
		orderReminder.setOrderQty(int_);
		orderReminder.setRemindType(str_);
		orderReminder.setRemindTime(date_);
		orderReminder.setDeptId(str_);
		orderReminder.setReadFlag(str_);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testSaveOrderReminder() {
		orderReminderDao.saveOrderReminder(orderReminder);
	}

	@Test
	public void testSaveOrderReminderList() {
		List<OrderReminder> list = new ArrayList<OrderReminder>();
		list.add(orderReminder);
		list.add(orderReminder);
		orderReminderDao.saveOrderReminderList(list);
	}

	@Test
	public void testGetOrderReminderAll() {
		orderReminderDao.getOrderReminderAll();
	}

	@Test
	public void testClearOrderReminder() {
		orderReminderDao.clearOrderReminder();
	}
	@Test
	public  void testGetUnassignOrderReminder(){
		List<String> statuses=new ArrayList<String>();
		statuses.add(Constant.ORDER_STATUS_WAIT_ALLOT);
		orderReminderDao.getUnassignOrderReminder(statuses);
		
	}
	@Test
	public  void testGetUnacceptOrderReminder(){
		List<String> statuses=new ArrayList<String>();
		statuses.add(Constant.ORDER_STATUS_WAIT_ACCEPT);
		orderReminderDao.getUnacceptOrderReminder(statuses);
	}
	@Test
	public  void testGetAcceptedOrderReminder(){
		List<String> statuses=new ArrayList<String>();
		statuses.add(Constant.ORDER_STATUS_ACCEPT);
		orderReminderDao.getAcceptedOrderReminder(statuses);
		
	}

	@Test
	public void testAddOrderAcceptDept() {
		orderReminderDao.addOrderAcceptDept();
	}

	@Test
	public void testGenerateOrderAcceptInfo() {
		OrderAcceptConfig oac = new OrderAcceptConfig();
		oac.setStandardCode("DP09456");
		oac.setWarnTime(5);
		oac.setLockTime(15);
		List<OrderAcceptInfo> oaiList = orderReminderDao
				.generateOrderAcceptInfo(oac);
		System.out.println("testGenerateOrderAcceptInfo");

	}

	@Test
	public void testGetOrderAcceptInfoByDept() {
		OrderAcceptConfig oac = new OrderAcceptConfig();
		oac.setWarnTime(5);
		oac.setLockTime(15);
		OrderAcceptInfo oai = orderReminderDao.getOrderAcceptInfoByDept(oac,
				"DP09456");
		System.out.println("testGetOrderAcceptInfoByDept");

	}

}
