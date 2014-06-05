package com.deppon.crm.module.customer.server.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.manager.IContractWorkflowManager;
import com.deppon.crm.module.customer.server.service.IAlterMemberService;
import com.deppon.crm.module.customer.server.service.IContractService;
import com.deppon.crm.module.customer.server.service.IContractWorkflowService;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;
import com.deppon.crm.module.customer.shared.domain.ContractWorkflowInfo;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.Member4All;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

import junit.framework.TestCase;

/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title ContractWorkflowManagerTest.java
 * @package com.deppon.crm.module.customer.server.manager.impl
 * @author 106138
 * @version 0.1 2014年4月14日
 */
public class ContractWorkflowManagerTest extends TestCase {
	public static ContractWorkflowManager contractWorkflowManager = new ContractWorkflowManager();
	@Before
	protected void setUp() throws Exception {
		Mockery contractServiceMockery = new Mockery();
		final IContractService contractService = contractServiceMockery.mock(IContractService.class);
		contractServiceMockery.checking(new Expectations() {
			{
				allowing(contractService).searchOperaLogByWorkflowId("1");
				List<ContractOperatorLog> contractLogList=new ArrayList<ContractOperatorLog>();
				ContractOperatorLog c=new ContractOperatorLog();
				c.setCreateUser("1");
				contractLogList.add(c);
				will(returnValue(contractLogList));
			}
		});
		contractWorkflowManager.setContractService(contractService);
		Mockery contractWorkflowServiceMockery = new Mockery();
		final IContractWorkflowService contractWorkflowService = contractWorkflowServiceMockery.mock(IContractWorkflowService.class);
		contractWorkflowServiceMockery.checking(new Expectations() {
			{
				allowing(contractWorkflowService).queryContractWorkflowInfoByBusino(with(any(String.class)));
				will(returnValue(new ContractWorkflowInfo()));
			}
		});
		contractWorkflowManager.setContractWorkflowService(contractWorkflowService);
		Mockery employeeServiceMockery = new Mockery();
		final IEmployeeService employeeService = employeeServiceMockery.mock(IEmployeeService.class);
		employeeServiceMockery.checking(new Expectations() { 
			{	allowing(employeeService).getEmpById("1");
				Employee employee =new Employee();
				employee.setEmpCode("1");
				will(returnValue(employee));
			}
		});
		contractWorkflowManager.setEmployeeService(employeeService);
		Mockery userServiceMockery = new Mockery();
		final IUserService userService = userServiceMockery.mock(IUserService.class);
		userServiceMockery.checking(new Expectations() {
			{	allowing(userService).findByLoginName("1");
			User user=new User();
			user.setId("1");
			will(returnValue(user));
			allowing(userService).getUserRoleFunDeptById("1");
			will(returnValue(user));
			}
		});
		contractWorkflowManager.setUserService(userService);
		
	}


	@After
	protected void tearDown() throws Exception {
		
	}
	@Test
	public void testFindContractWorkflowInfoByWorkNo() {
		contractWorkflowManager.findContractWorkflowInfoByWorkNo(null, "1");
		contractWorkflowManager.findContractWorkflowInfoByWorkNo("1", "1");
		contractWorkflowManager.getContractService();
		contractWorkflowManager.getContractWorkflowService();
		contractWorkflowManager.getUserService();
		contractWorkflowManager.getEmployeeService();
		
	}

	public void testSearchOperaDeptByWorkflowId() {
		contractWorkflowManager.searchOperaDeptByWorkflowId("1");
	}
}
