package com.deppon.crm.module.customer.server.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.testutils.DateCreateUtil;
import com.deppon.crm.module.customer.server.testutils.DateInitUtil;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.integral.WaybillIntegral;
import com.opensymphony.xwork2.interceptor.annotations.After;

public class WaybillIntegralDaoTest extends BeanUtil{
	
	@Before
	public void setUp(){
	}
	@After
	public void shutDown() throws Exception{
		DateInitUtil.executeCleanSql();
	}
	@Test
	public void testCountSearchWaybillIntegrals(){
		WaybillIntegral integral = DateCreateUtil.createWaybillIntegral();
		
		integral.setCreateDate(null);
		integral.setCreateUser(null);
		integral.setModifyDate(null);
		integral.setModifyUser(null);
		
		long cout = waybillIntegralDao.countSearchWaybillIntegrals(integral);
	}
	@Test
	public void testSearchWaybillIntegrals(){
		WaybillIntegral integral = DateCreateUtil.createWaybillIntegral();
		
		integral.setCreateDate(null);
		integral.setCreateUser(null);
		integral.setModifyDate(null);
		integral.setModifyUser(null);
		
		List list = waybillIntegralDao.searchWaybillIntegrals(integral, 0,100);
	}
	@Test
	public void testGetHandRewardIntegralById(){
		WaybillIntegral waybillIntegral = DateCreateUtil.createWaybillIntegral();
		WaybillIntegral waybillIntegral2 = waybillIntegralDao.getWaybillIntegralById(waybillIntegral.getId());
	}
	@Test
	public void testSearchWaybillIntegralForContactId(){
		List<String> list = new ArrayList<String>();
		list.add("123123");
		list.add("23123");
		list.add("123123123");
		list.add("2131231231");
		waybillIntegralDao.searchWaybillIntegralForContactId(list,0,100);
	}
	@Test
	public void testCountSearchWaybillIntegralForContactId(){
		List<String> list = new ArrayList<String>();
		list.add("123123");
		list.add("23123");
		list.add("123123123");
		list.add("2131231231");
		waybillIntegralDao.countSearchWaybillIntegralForContactId(list);
	}
	@Test
	public void testGetWaybillIntegralById() {
		String id = "";
		Assert.assertNull(waybillIntegralDao.getWaybillIntegralById(id));
	}
}
