package com.deppon.crm.module.common.server.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.manager.impl.ProblemManager;
import com.deppon.crm.module.common.server.manager.impl.TodoWorkflowManager;
import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.common.shared.domain.TodoWorkflowMap;

public class ProblemManagerTest extends TestCase{
	private static ProblemManager problemManager;
	private static TodoWorkflowManager  todoWorkflowManager ;
	private static ApplicationContext ctx = null;
	static String[] xmls = new String[]{"CommonBeanTest.xml"};

	static{
		try{
			if(ctx == null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			problemManager = ctx.getBean(ProblemManager.class);
			todoWorkflowManager = ctx.getBean(TodoWorkflowManager .class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void testQueryAllFeedBack(){
		problemManager.queryAllFeedBack();
		problemManager.getpService();
	}
	public void testGenerateTodoWorkflow(){
		long workflowId = 1;
		String workflow = "0";
		Map deptMap = new HashMap();
		List<TodoWorkflowMap> todoMapList = new ArrayList<TodoWorkflowMap>();
		todoMapList.add(null);
		todoWorkflowManager.generateTodoWorkflow(
				workflowId, workflow, workflow, workflow, workflow, workflow, deptMap);
		todoWorkflowManager.generateTodoWorkflow(
				workflowId, workflow, workflow, workflow, workflow, workflow, deptMap);
	}
	public void testGetTodoWorkflowByWorkflowId(){
		String workflowName = "workflowName";
		String workflowId = "workflowId";
		String roleId = "110";
		String[] deptIds = {"110"};
		todoWorkflowManager.getTodoWorkflowByWorkflowId(workflowName, workflowId, roleId, deptIds);
	}
	public void testGetTodoWorkflowByWorkflowName(){
		String workflowName = "workflowName";
		String roleId = "110";
		String[] deptIds = {"110"};
		todoWorkflowManager.getTodoWorkflowByWorkflowName(workflowName, roleId, deptIds);
	}
	public void testUpdateTodoflagByWorkflowId(){
		long workflowId = 1;
		String todoFlag = "todoFlag";
		String id = "110";
		todoWorkflowManager.updateTodoflagByWorkflowId(workflowId, todoFlag);
		todoWorkflowManager.updateTodoflagById(id, todoFlag);
	}
	public void testaddTodoWorkflow(){
		TodoWorkflow todoWorkflow = new TodoWorkflow();
		long workflowId = 1;
		todoWorkflow.setWorkflowId(workflowId);
		todoWorkflowManager.addTodoWorkflow(todoWorkflow);
		List<TodoWorkflow> todoWorkflowList = new ArrayList<TodoWorkflow>();
		todoWorkflowList.add(todoWorkflow);
		todoWorkflowManager.addTodoWorkflowList(todoWorkflowList);
		String workflowName = "workflowName";
		String fromStatus = "fromStatus";
		String toStatus ="toStatus";
		todoWorkflowManager.searchTodoWorkflowMap(workflowName, fromStatus, toStatus);
	}
}
