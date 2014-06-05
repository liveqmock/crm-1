package com.deppon.crm.module.workflow.server.manager;

import junit.framework.TestCase;

import org.junit.Test;

import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.workflow.server.util.NormalClaimUtil;

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
public class INormalClaimManagerTest extends TestCase {
	
	private static INormalClaimManager normalClaimManager;
	
	static {
		normalClaimManager= TestUtil.normalClaimManager;
	}
	
	
	//起草流程
	@Test
	public void testCreateWorkflow() {
		normalClaimManager.createWorkflow("1234567", "", "", "", "");
	}
	
	//工作流查询
	@Test
	public void testQueryMyWorkFlow() {
		normalClaimManager.queryMyWorkFlow(1, 10, NormalClaimUtil.PROCESS_DEFINITION_NAME, "3", null, null, "");
	}
	
	public void testQueryActivityByProcessDefID(){
		normalClaimManager.queryActivityByProcessDefID(NormalClaimUtil.PROCESS_DEFINITION_NAME);
	}
	
	public void testWorkflowApprove() {
		normalClaimManager.workflowApprove(35067, 22923, "0", "我是阿杜，我审批", null, "");
	}
}
