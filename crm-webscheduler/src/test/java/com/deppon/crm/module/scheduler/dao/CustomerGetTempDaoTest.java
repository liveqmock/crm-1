package com.deppon.crm.module.scheduler.dao;


import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.scheduler.server.dao.ICustomerGetTempDao;
import com.deppon.crm.module.scheduler.server.dao.impl.CustomerGetTempDao;
import com.deppon.crm.module.scheduler.service.testUtil.SpringTestHelper;
public class CustomerGetTempDaoTest {
	private ICustomerGetTempDao customerGetTempDao;
	@Before
	public void setUp() throws Exception {
		customerGetTempDao = SpringTestHelper.get().getBean(CustomerGetTempDao.class);
		Assert.assertNotNull(customerGetTempDao);
	}
	@After
	public void shutDown() throws Exception{
	}
	/**
	 * @作者 李学兴
	 * @时间 2012-5-25
	 * @功能 测试 查询达到客户信息
	 * */
	@Test
	public void testGetArrivalCustomerList(){
		Assert.assertNotNull(customerGetTempDao.getArrivalCustomerList(new Date(),Integer.MAX_VALUE));
		
	}
	/**
	 * @作者 李学兴
	 * @时间 2012-5-25
	 * @功能 测试 根据时间查询达到客户信息
	 * */
	@Test
	public void testGetScatterCustomerListByDate(){
		Assert.assertNotNull(customerGetTempDao.getScatterCustomerListByDate(new Date()));
	}
}
