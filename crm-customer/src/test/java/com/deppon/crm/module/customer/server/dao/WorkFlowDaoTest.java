package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.testutils.DateInitUtil;
import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.WorkFlow;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;

public class WorkFlowDaoTest extends BeanUtil{
	
	/*
	static{
		try {
			DateInitUtil.executeCleanSql();
			Runtime.getRuntime().addShutdownHook(new Thread(){
				@Override
				public void run() {
					try {
						DateInitUtil.executeInitSql();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	@Before
	public void setUp() throws Exception{
		DateInitUtil.executeInitSql();
	}
	
	@After
	public void shutDown() throws Exception{
		DateInitUtil.executeCleanSql();
	}
	
	@Test
	public void testInsertWorkFlow(){
		WorkFlow workFLow = TestUtils.createBean(WorkFlow.class);
		workFLow.setId(null);
		workFLow.setCreateUser("7");
		workFLow.setUserId("1");
		workFLow.setDeptId("1");
		workFlowDao.insertWorkFlow(workFLow);
		Assert.assertNotNull(workFLow.getId());
	}
	
	@Test
	public void testSearchWorkFLow(){
		WorkFlowCondition condition =TestUtils.createBean(WorkFlowCondition.class);
		workFlowDao.searchWorkFLow(condition,0,100);
	}
	
	@Test
	public void testUpdateWorkFlowStatusByWorkFLowId(){
		WorkFlow workFLow = TestUtils.createBean(WorkFlow.class);
		workFLow.setCreateUser("7");
		workFLow.setWorkFlowId(12345);
		workFLow.setUserId("1");
		workFLow.setDeptId("1");
		workFLow.setId(null);
		workFlowDao.insertWorkFlow(workFLow);
		workFlowDao.updateWorkFlowStatusByWorkFLowId(Constant.AGREED,12345,"1234");
		
		WorkFlowCondition condition = new WorkFlowCondition();
		condition.setWorkflowId(12345L);
		
		List<WorkFlow> list = workFlowDao.searchWorkFLow(condition,0,100);
		
		Assert.assertEquals(list.get(0).getStatus(), Constant.AGREED);
	}
	
	
	@Test
	public void testSearchMydisposeWorkFlow(){
		WorkFlowCondition condition =TestUtils.createBean(WorkFlowCondition.class);
		workFlowDao.searchMydisposeWorkFlow(condition,0,100);
	}
	
	@Test
	public void testCountSearchMydisposeWorkFlow(){
		WorkFlowCondition condition =TestUtils.createBean(WorkFlowCondition.class);
		workFlowDao.countSearchMydisposeWorkFlow(condition);
	}
	@Test
	public void testCountSearchWorkFlow(){
		WorkFlowCondition condition  = TestUtils.createBean(WorkFlowCondition.class);
		workFlowDao.countSearchWorkFlow(condition);
	}
	
}
