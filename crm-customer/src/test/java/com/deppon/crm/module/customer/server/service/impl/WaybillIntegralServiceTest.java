package com.deppon.crm.module.customer.server.service.impl;

import java.util.List;

import org.junit.Before;

import com.deppon.crm.module.customer.server.util.BeanUtil;
public class WaybillIntegralServiceTest extends BeanUtil{ 

	@Before
	public void setUp(){
	}

	//@Test
	public void testGetNeedDisPoseWaybills(){
		waybillIntegralService.getNeedDisPoseWaybills();
	}
	//@Test
	public void testSearchWaybillIntegralForContactId(){
		List list = null;
		waybillIntegralService.searchWaybillIntegralForContactId(list,1,1000);
	}
	//@Test
	public void testCountSearchWaybillIntegralForContactId(){
		List list = null;
		waybillIntegralService.countSearchWaybillIntegralForContactId(list);
	}
}

