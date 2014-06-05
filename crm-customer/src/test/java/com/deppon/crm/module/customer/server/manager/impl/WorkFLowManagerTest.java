package com.deppon.crm.module.customer.server.manager.impl;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.WorkFlow;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

public class WorkFLowManagerTest extends BeanUtil {
	WorkFlow workFlow = new WorkFlow();

	@Before
	public void setUp(){
		User user = new User();
		Department depart = new Department();
		depart.setId("432658");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("98564");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
	}

	@After
	public void tearDown(){
		UserContext.setCurrentUser(null);
	}
	
	@Test
	public void test_updateWorkFlowStatusByWorkFLowId(){
		
		workFlowManager.updateWorkFlowStatusByWorkFLowId("12", 987876675);
		
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:test 分页查询我的工作流信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-31
	 * void
	 */
	@Test
	public void test_searchWorkFLow(){
		
		WorkFlowCondition condition = new WorkFlowCondition();
		
		int count = workFlowManager.countSearchWorkFlow(condition);
		
		List<WorkFlow> result_list = workFlowManager.searchWorkFLow(condition, 0, 20);
		Assert.assertTrue(count>=result_list.size());
		
		
	      condition = new WorkFlowCondition();
	      condition.setWorkflowId(0l);
		 count = workFlowManager.countSearchWorkFlow(condition);
		
		result_list = workFlowManager.searchWorkFLow(condition, 0, 20);
		Assert.assertTrue(count>=result_list.size());
		
		  condition = new WorkFlowCondition();
	      condition.setWorkflowId(400231293l);
		 count = workFlowManager.countSearchWorkFlow(condition);
		
		result_list = workFlowManager.searchWorkFLow(condition, 0, 20);
		Assert.assertTrue(count>=result_list.size());
		
		
		
	}
	
	@Test
	public void test_searchMydisposeWorkFlow(){
		WorkFlowCondition condition = new WorkFlowCondition();
		int count = workFlowManager.countSearchMydisposeWorkFlow(condition);
		List<WorkFlow> result_list = workFlowManager.searchMydisposeWorkFlow(condition, 0, 20);
		Assert.assertTrue(count>=result_list.size());
		
		 condition = new WorkFlowCondition();
		 condition.setWorkflowId(0l);
		 count = workFlowManager.countSearchMydisposeWorkFlow(condition);
		result_list = workFlowManager.searchMydisposeWorkFlow(condition, 0, 20);
		Assert.assertTrue(count>=result_list.size());
		
		
		 condition = new WorkFlowCondition();
		 condition.setWorkflowId(400231293l);
		 count = workFlowManager.countSearchMydisposeWorkFlow(condition);
		result_list = workFlowManager.searchMydisposeWorkFlow(condition, 0, 20);
		Assert.assertTrue(count>=result_list.size());
		
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2013-1-12
	 @return void
	 */
	@Test
	public void test_saveWorkFlow(){
		workFlow.setAppId("5201314");
		workFlow.setCreateDate(new Date());
		workFlow.setCreateUser("死不死");
		workFlow.setDeptName("桑不起，有木有");
		workFlow.setId("110");
		workFlowManager.saveWorkFlow(workFlow);
	}
}
