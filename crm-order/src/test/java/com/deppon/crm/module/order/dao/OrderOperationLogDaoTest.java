/**   
 * @title OrderOperationLogDaoTest.java
 * @package com.deppon.crm.module.order.dao
 * @description what to do
 * @author 安小虎
 * @update 2012-3-13 上午9:54:59
 * @version V1.0   
 */
package com.deppon.crm.module.order.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.order.server.dao.IOrderOperationLogDao;
import com.deppon.crm.module.order.server.dao.ISequenceTool;
import com.deppon.crm.module.order.server.dao.impl.OrderOperationLogDao;
import com.deppon.crm.module.order.shared.domain.OrderOperationLog;
import com.deppon.crm.module.order.util.TestUtil;
import com.deppon.foss.framework.server.context.AppContext;

/**
 * @description 订单操作记录DAO测试用例
 * @author 安小虎
 * @version 0.1 2012-3-13
 * @date 2012-3-13
 */
public class OrderOperationLogDaoTest {
	static IOrderOperationLogDao orderOperationLogDao;
	OrderOperationLog orderOperationLog = null;
	
	static{
		orderOperationLogDao = TestUtil.orderOperationLogDao;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	public void setUp() throws Exception {
	
		// 变量
		Integer int_ = 1;
		String str_ = "2";
		boolean boo_ = true;
		Date date_ = new Date(2012, 3, 9);
		Double double_ = 1D;
		// 构造订单对象
		orderOperationLog = new OrderOperationLog();
		orderOperationLog.setContactName(str_);
		orderOperationLog.setOrderId(Calendar.getInstance().getTimeInMillis()
				+ "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	public void tearDown() throws Exception {

	}

	/**
	 * {@link com.deppon.crm.module.order.server.dao.impl.OrderOperationLogDao#saveOrderOperationLog(com.deppon.crm.module.order.shared.domain.OrderOperationLog)}
	 * 的测试方法。
	 */
	@Test
	public void testInsertOrderOperationLog() {
		orderOperationLogDao.saveOrderOperationLog(orderOperationLog);
	}

	/**
	 * {@link com.deppon.crm.module.order.server.dao.impl.OrderOperationLogDao#saveOrderOperationLog(com.deppon.crm.module.order.shared.domain.OrderOperationLog)}
	 * 的测试方法。
	 */
	@Test
	public void testBatchSaveOrderOperationLog() {
		List<OrderOperationLog> list = new ArrayList<OrderOperationLog>();
		list.add(orderOperationLog);
		list.add(orderOperationLog);
		orderOperationLogDao.batchSaveOrderOperationLog(list);
	}

	/**
	 * {@link com.deppon.crm.module.order.server.dao.impl.OrderOperationLogDao#getListByOrderId(java.lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testGetListByOrderId() {
		String orderID = "1";
		orderOperationLogDao.getListByOrderId(orderID);
	}
	/** 无意义*/
	@Test
	public void testgetSequenceTool() {
		OrderOperationLogDao order=new OrderOperationLogDao();
		ISequenceTool a=order.getSequenceTool();
	}

	/**
	 * {@link com.deppon.crm.module.order.server.dao.impl.OrderOperationLogDao#getListByCondion(java.lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testGetListByCondion() {
		orderOperationLogDao.getListByCondion(orderOperationLog);
	}

	/**
	 * {@link com.deppon.crm.module.order.server.dao.impl.OrderOperationLogDao#getListByCondion(java.lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testSearchCountByOrderId() {
		orderOperationLog = orderOperationLogDao
				.saveOrderOperationLog(orderOperationLog);
		Long c=orderOperationLogDao.searchCountByOrderId(orderOperationLog
				.getOrderId());
		System.out.println(c);
	}
}
