package com.deppon.crm.module.customer.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.server.manager.ITodoWorkflowManager;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.server.manager.IIntegralManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.server.manager.IWorkFLowManager;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.ExamineRecord;
import com.deppon.crm.module.customer.shared.domain.WorkFlow;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;
import com.deppon.crm.module.customer.workflow.function.RoleTest;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;
import com.opensymphony.workflow.InvalidActionException;
import com.opensymphony.workflow.InvalidEntryStateException;
import com.opensymphony.workflow.InvalidInputException;
import com.opensymphony.workflow.InvalidRoleException;
import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.WorkflowException;
import com.opensymphony.workflow.basic.BasicWorkflow;
import com.opensymphony.workflow.loader.WorkflowDescriptor;

public class WorkFlowTestUtil {
	
	private String workFlowName ;
	
	private Workflow workflow = null;
	private WorkflowDescriptor wd;

	protected WorkflowDescriptor workflowDescriptor;
	
	
	public WorkFlowTestUtil(String workFlowName) {
		this.workFlowName = workFlowName;

		workflow = SpringTestHelper.getBean(BasicWorkflow.class);
		userJMock();
		createChangeMocker();
	}
	
	public void doAction(String userId, long workflowId, int actionId, Map map) {
		if(map == null) map = new HashMap();
		initUser(userId);

		try {
			System.out.println("当前执行ActionID=="+actionId+wd.getAction(actionId).getName());
			wd = workflow.getWorkflowDescriptor(workflow
					.getWorkflowName(workflowId));
			
			List currentSteps = workflow.getCurrentSteps(workflowId);// 当前步骤
			for (Object object : currentSteps) {
				System.out.println("currentSteps==" + object.toString());
			}
			List historySteps = workflow.getHistorySteps(workflowId);//
			for (Object object : historySteps) {
				System.out.println("historySteps==" + historySteps.toString());
			}
			int[] actions = workflow.getAvailableActions(workflowId);
			for (int i : actions) {
				System.out.println("actions==" + i);
			}
			workflow.doAction(workflowId, actionId, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static void initUser(String userId) {
		// TODO Auto-generated method stub
		String dept = RoleTest.getDepartment(userId);
		Set<String> roles = RoleTest.getRules(userId);
		
		User user = new User();
		Employee empCode = new Employee();
		Department deptId = new Department();
		deptId.setId(dept);
		empCode.setDeptId(deptId);
		user.setRoleids(roles);
		user.setEmpCode(empCode);
		UserContext.setCurrentUser(user);

	}

	public String getWorkflowName() {
		return getClass().getResource("/"+workFlowName+".xml").toString();
	}
	
	public void assertWorkFlowEnd(long workflowId){
		Assert.assertEquals(workflow.getCurrentSteps(workflowId).size(), 0);
	}
	
	public long inintWorkFlow(Map map){
		initUser("test");
		long workflowId = -1;
		try {
			workflowId = workflow.initialize(workFlowName, 0, map);
		} catch (InvalidActionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRoleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidEntryStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WorkflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wd = workflow.getWorkflowDescriptor(workflow
				.getWorkflowName(workflowId));
		return workflowId;
	}
	
	//**************************mock 对象
	
	public static void jMockAlterManager(){
		Mockery mock = new Mockery();
		
		final IAlterMemberManager alterMemberManager = mock.mock(IAlterMemberManager.class);
		mock.checking(new Expectations(){{

			allowing(alterMemberManager).disposeWorkflow(with(any(String.class)),with(any(Boolean.class)));
		}});
		
		ExamineFinishFuntion efFun = SpringTestHelper.getBean(ExamineFinishFuntion.class);
		efFun.setAlterMemberManager(alterMemberManager);
	}
	
	public static void userJMock(){
		Mockery mock = new Mockery();
		//测试部门
		final Department department = new Department();
		department.setId("test");
		final IBaseDataManager baseDataManager = mock.mock(IBaseDataManager.class);
		final IAlterMemberManager alterMemberManager = mock.mock(IAlterMemberManager.class);
		final ITodoWorkflowManager todoWorkflowManager = mock.mock(ITodoWorkflowManager.class);
		final IWorkFLowManager workFlowManager = mock.mock(IWorkFLowManager.class);
		final IIntegralManager integralManager = mock.mock(IIntegralManager.class);
		final IUserService userService = mock.mock(IUserService.class);
		final IMessageManager messageManager = mock.mock(IMessageManager.class);
		final List list = new ArrayList();
		
		mock.checking(new Expectations(){{
			allowing(messageManager).addMessage(with(any(Message.class)));
			
			allowing(userService).queryUsersByDeptAndRole(with(any(String.class)), with(any(String.class)));
			will(returnValue(list));
			
			allowing(baseDataManager).getBusinessDivesionOfficeMarketingGroup(with(any(Department.class)));
			will(returnValue(department));
			
			allowing(baseDataManager).getCustRelationsDepartment();
			will(returnValue(department));
			
			allowing(baseDataManager).getCauseDepartment(with(any(String.class)));
			will(returnValue(department));
			
			allowing(baseDataManager).getNeighborhoodDepartment(with(any(String.class)));
			will(returnValue(department));
			
			allowing(baseDataManager).getRegionDepartment(with(any(String.class)));
			will(returnValue(department));
			
			allowing(alterMemberManager).disposeWorkflow(with(any(String.class)),with(any(Boolean.class)));

			allowing(todoWorkflowManager).updateTodoflagByWorkflowId(with(any(Long.class)),with(any(String.class)));
			
			allowing(todoWorkflowManager).addTodoWorkflow(with(any(TodoWorkflow.class)));
			
			allowing(workFlowManager).searchWorkFLow(with(any(WorkFlowCondition.class)),with(any(Integer.class)),with(any(Integer.class)));
			
			allowing(workFlowManager).saveWorkFlow(with(any(WorkFlow.class)));

			allowing(workFlowManager).updateWorkFlowStatusByWorkFLowId(with(any(String.class)),with(any(Long.class)));
			
			allowing(integralManager).disposeContactVaryByExamineResult(with(any(String.class)),with(any(Boolean.class)));

		}});
		
		CreateWorkFlowFunction fun = SpringTestHelper.getBean(CreateWorkFlowFunction.class);
		fun.setWorkFLowManager(workFlowManager);
		
		UpdateWorkFLowFunction upfun = SpringTestHelper.getBean(UpdateWorkFLowFunction.class);
		upfun.setWorkFLowManager(workFlowManager);
		
		InitUseOwner initUseOwner =  SpringTestHelper.getBean(InitUseOwner.class);
		initUseOwner.setBaseDataManager(baseDataManager);
		
		InitContactVaryOnwer initContactVaryOnwer = SpringTestHelper.getBean(InitContactVaryOnwer.class);
		initContactVaryOnwer.setBaseDataManager(baseDataManager);
		
		ExamineFinishFuntion efFun = SpringTestHelper.getBean(ExamineFinishFuntion.class);
		efFun.setAlterMemberManager(alterMemberManager);
		
		ToDoWorkFunction todo = SpringTestHelper.getBean(ToDoWorkFunction.class);
		todo.setTodoWorkflowManager(todoWorkflowManager);
		todo.setUserService(userService);
		todo.setMessageManager(messageManager);
		
		ExamineContactVaryFinishFunction examineContactVaryFinishFunction = SpringTestHelper.getBean(ExamineContactVaryFinishFunction.class);
		examineContactVaryFinishFunction.setIntegralManager(integralManager);
		
	}
	
	public static void createChangeMocker(){
		Mockery mock = new Mockery();

		final IMemberManager memberManager = mock.mock(IMemberManager.class);
		
		mock.checking(new Expectations(){{
			
			allowing(memberManager).saveExamineRecord(with(any(ExamineRecord.class)));
			
			allowing(memberManager).isContractMember(with(any(String.class)));
			will(returnValue(RoleTest.isContract()));
			
			allowing(memberManager).disposeSpecialMemberByExamineResult(with(any(String.class)),with(any(Boolean.class)));

		}});
		
		InitStepUser initStepOwner =  SpringTestHelper.getBean(InitStepUser.class);
		initStepOwner.setMemberManager(memberManager);
		
		WriteExaminRecordFunction weFun = SpringTestHelper.getBean(WriteExaminRecordFunction.class);
		weFun.setMemberManager(memberManager);
		
		ExamineCreateSMFinishFuntion exFun = SpringTestHelper.getBean(ExamineCreateSMFinishFuntion.class);
		exFun.setMemberManager(memberManager);
	}
}
