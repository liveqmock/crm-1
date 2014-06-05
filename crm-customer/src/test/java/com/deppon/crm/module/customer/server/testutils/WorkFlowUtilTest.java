package com.deppon.crm.module.customer.server.testutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.utils.WorkFlowUtil;
import com.opensymphony.workflow.StoreException;
import com.opensymphony.workflow.spi.Step;
import com.opensymphony.workflow.spi.WorkflowEntry;
import com.opensymphony.workflow.spi.WorkflowStore;

public class WorkFlowUtilTest {
	private Mockery context = new Mockery();
	private WorkflowEntry entry = null;
	private WorkflowStore store = null;
	private Step step = null;
	
	@Before
	public void setUp(){
		entry = context.mock(WorkflowEntry.class);
		store = context.mock(WorkflowStore.class);
		 step = context.mock(Step.class);
	}
	@After
	public void tearDown(){
		 context.assertIsSatisfied();
	}
	

	
	@Test
	public void test_getOwner_scane1() throws StoreException{
		
		context.checking(new Expectations(){
			{
			exactly(1).of(entry).getId();
			will(returnValue(1224345l));
			}
		});
		context.checking(new Expectations(){
			{
			exactly(1).of(store).findCurrentSteps(1224345l);
			
			List<Step> currentSteps = new ArrayList<Step>();
			context.checking(new Expectations(){
				{
				exactly(2).of(step).getOwner();
				will(returnValue("ower"));
				}
			});
			context.checking(new Expectations(){
				{
				exactly(1).of(step).getStepId();
				will(returnValue(1));
				}
			});
			currentSteps.add(step);
			will(returnValue(currentSteps));
			}
		});
		

		Map<String,Object> transientVars = new HashMap<String,Object>();
		transientVars.put("entry", entry);
		transientVars.put("store", store);
		Map<String,Object> args = new HashMap<String,Object>();
		args.put("stepId", "1");
		
		String result = WorkFlowUtil.getOwner(transientVars, args);
		Assert.assertEquals("ower", result);
		
	  
	}

	
	@Test
	public void test_getOwner2() throws StoreException{
		
		context.checking(new Expectations(){
			{
			exactly(1).of(entry).getId();
			will(returnValue(1224345l));
			}
		});
		context.checking(new Expectations(){
			{
			exactly(1).of(store).findCurrentSteps(1224345l);
			
			List<Step> currentSteps = new ArrayList<Step>();
			context.checking(new Expectations(){
				{
				exactly(2).of(step).getOwner();
				will(returnValue("ower"));
				}
			});
			currentSteps.add(step);
			will(returnValue(currentSteps));
			}
		});
		

		Map<String,Object> transientVars = new HashMap<String,Object>();
		transientVars.put("entry", entry);
		transientVars.put("store", store);
		Map<String,Object> args = new HashMap<String,Object>();
		args.put("stepId", null);
		
		
		
		String result = WorkFlowUtil.getOwner(transientVars, args);
		Assert.assertEquals("ower", result);
		
	  
	}
	
	

	@Test
	public void test_getOwner_scane3() throws StoreException{
		
		context.checking(new Expectations(){
			{
			exactly(1).of(entry).getId();
			will(returnValue(1224345l));
			}
		});
		context.checking(new Expectations(){
			{
			exactly(1).of(store).findCurrentSteps(1224345l);
			
			List<Step> currentSteps = new ArrayList<Step>();
		
			context.checking(new Expectations(){
				{
				exactly(1).of(step).getStepId();
				will(returnValue(1));
				}
			});
			currentSteps.add(step);
			will(returnValue(currentSteps));
			}
		});
		

		Map<String,Object> transientVars = new HashMap<String,Object>();
		transientVars.put("entry", entry);
		transientVars.put("store", store);
		Map<String,Object> args = new HashMap<String,Object>();
		args.put("stepId", "1234");
		
		String result = WorkFlowUtil.getOwner(transientVars, args);
		Assert.assertNull(result);

	}
	

	@Test
	public void test_getOwner_scane4() throws StoreException{
		
		context.checking(new Expectations(){
			{
			exactly(1).of(entry).getId();
			will(returnValue(1224345l));
			}
		});
		context.checking(new Expectations(){
			{
			exactly(1).of(store).findCurrentSteps(1224345l);
			
			List<Step> currentSteps = new ArrayList<Step>();
		
			context.checking(new Expectations(){
				{
				exactly(1).of(step).getStepId();
				will(throwException(new Exception("测试跑出异常")));
				}
			});
			currentSteps.add(step);
			will(returnValue(currentSteps));
			}
		});
		

		Map<String,Object> transientVars = new HashMap<String,Object>();
		transientVars.put("entry", entry);
		transientVars.put("store", store);
		Map<String,Object> args = new HashMap<String,Object>();
		args.put("stepId", "1");
		
		String result = WorkFlowUtil.getOwner(transientVars, args);
		Assert.assertNull(result);

	}
}
