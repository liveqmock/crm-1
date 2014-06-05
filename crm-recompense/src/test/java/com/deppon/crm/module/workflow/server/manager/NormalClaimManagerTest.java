package com.deppon.crm.module.workflow.server.manager;

import junit.framework.TestCase;

import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.workflow.server.util.NormalClaimUtil;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * 
 * <p>
 * Description:理赔工作流管理层测试<br />
 * </p>
 * @title INormalClaimManagerTest.java
 * @package com.deppon.crm.module.workflow.server.manager 
 * @author andy
 * @version 0.1 2013-8-10
 */
public class NormalClaimManagerTest extends TestCase {
	
	private static INormalClaimManager normalClaimManager;
	 
	static {
		normalClaimManager= TestUtil.normalClaimManager;
		
		User user = new User();
		user.setId("31967");
		Employee employee = new Employee();
		Department deparment = new Department();
		deparment.setId("316218");
		deparment.setDeptName("上海事业部营销管理组");
		employee.setId("31967");
		employee.setDeptId(deparment);
		employee.setEmpCode("002902");
		employee.setEmpName("包俊英");
		user.setEmpCode(employee);
		UserContext.setCurrentUser(user);
	}
	
	
	//起草流程
	@Test
	public void testCreateWorkflow() {
		try {
			normalClaimManager.createWorkflow("1234567", "", "", "", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//工作流查询
	@Test 
	public void testQueryMyWorkFlow() {
		try {
			normalClaimManager.queryMyWorkFlow(1, 10, NormalClaimUtil.PROCESS_DEFINITION_NAME, "3", null, null, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testQueryActivityByProcessDefID(){
		try {
			normalClaimManager.queryActivityByProcessDefID(NormalClaimUtil.PROCESS_DEFINITION_NAME);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testWorkflowApprove() {
		try {
			normalClaimManager.workflowApprove(35067, 22923, "0", "我是阿杜，我审批", null, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
