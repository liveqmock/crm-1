//package com.deppon.crm.module.customer.workflow;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.deppon.crm.module.customer.shared.domain.Constant;
//
//public class MemberModifyTest {
//	
//	private WorkFlowTestUtil wfUtil;
//	
//	
//	@Before
//	public void setUp() {
//		wfUtil = new WorkFlowTestUtil("memberModify"); 
//	}
//	
//
//	private void testTwoSet(String accountStepUser,String baseDateStepUser) {
//		// assertNotNull("Could not get UserManager", um);
//		//三个审批角色
//		//有合同--客户关系部--crDept
//		//没有合同--营销专员--marketing
//		//银行账户--营业部经理--bizManager
//		
//		Map map = null;
//		long workflowId = -1;
//		boolean isBaseDateType = true;
//		boolean isAccountType = true;
//		
//		
//		
//		map = createInitDate(isBaseDateType,isAccountType);
//		workflowId = wfUtil.inintWorkFlow(map);
//		wfUtil.doAction(accountStepUser, workflowId, Constant.MemberModifyWK_AccountPass, new HashMap());
//		wfUtil.doAction(baseDateStepUser, workflowId, Constant.MemberModifyWK_BaseNoPass, new HashMap());
//		wfUtil.assertWorkFlowEnd(workflowId);
//		
//		map = createInitDate(isBaseDateType,isAccountType);
//		workflowId = wfUtil.inintWorkFlow(map);
//		wfUtil.doAction(accountStepUser, workflowId, Constant.MemberModifyWK_AccountNoPass, new HashMap());
//		wfUtil.assertWorkFlowEnd(workflowId);
//	
//		map = createInitDate(isBaseDateType,isAccountType);
//		workflowId = wfUtil.inintWorkFlow(map);
//		wfUtil.doAction(accountStepUser, workflowId, Constant.MemberModifyWK_AccountPass, new HashMap());
//		wfUtil.doAction(baseDateStepUser, workflowId, Constant.MemberModifyWK_BasePass, new HashMap());
//		wfUtil.assertWorkFlowEnd(workflowId);
//	
//	}
//	
//	
//	
//	@Test
//	public void testMemberModifyWorkflow(){
//		//testNoContractMember
//		//RoleTest.isContract = false;
//		
//		String accountStepUser = Constant.BizManager;
////		String baseDateStepUser = Constant.CrDept;
//		
////		testBaseDateStep(baseDateStepUser);
//		testAccountDateStep(accountStepUser);
////		testTwoSet(accountStepUser,baseDateStepUser);
//		
//		//testContractMember
//		//RoleTest.isContract = true;
//		
//		//修改模拟对象的行为
//		wfUtil.createChangeMocker();
//		
//		accountStepUser = Constant.BizManager;
////		baseDateStepUser = Constant.Marketing;
//		
////		testBaseDateStep(baseDateStepUser);
//		testAccountDateStep(accountStepUser);
////		testTwoSet(accountStepUser,baseDateStepUser);
//	}
//	
//	
//	private void testBaseDateStep(String userId){
//		Map map = null;
//		long workflowId = -1;
//		boolean isBaseDateType = true;
//		boolean isAccountType = false;
//		map = createInitDate(isBaseDateType,isAccountType);
//		workflowId = wfUtil.inintWorkFlow(map);
//		wfUtil.doAction(userId, workflowId, Constant.MemberModifyWK_BaseNoPass, new HashMap());
//		wfUtil.assertWorkFlowEnd(workflowId);
//		
//		map = createInitDate(isBaseDateType,isAccountType);
//		workflowId = wfUtil.inintWorkFlow(map);
//		wfUtil.doAction(userId, workflowId, Constant.MemberModifyWK_BasePass, new HashMap());
//		wfUtil.assertWorkFlowEnd(workflowId);
//	}
//	
//	
//	private void testAccountDateStep(String userId){
//		Map map = null;
//		long workflowId = -1;
//		
//		boolean isBaseDateType = false;
//		boolean isAccountType = true;
//		map = createInitDate(isBaseDateType,isAccountType);
//		workflowId = wfUtil.inintWorkFlow(map);
//		wfUtil.doAction(Constant.BizManager, workflowId, Constant.MemberModifyWK_AccountPass, new HashMap());
//		wfUtil.assertWorkFlowEnd(workflowId);
//		
//		map = createInitDate(isBaseDateType,isAccountType);
//		workflowId = wfUtil.inintWorkFlow(map);
//		wfUtil.doAction(Constant.BizManager, workflowId, Constant.MemberModifyWK_AccountNoPass, new HashMap());
//		wfUtil.assertWorkFlowEnd(workflowId);
//	}
//	
//	private Map createInitDate(boolean isBaseDateType,boolean isAccountType){
//		Map map = new HashMap();
//		Set<String> dateTypes = new HashSet<String>();
//		if(isBaseDateType){
//			dateTypes.add("baseDateType");
//		}
//		if(isAccountType){
//			dateTypes.add("accountDateType");
//		}
//		map.put("dataTypes",dateTypes);
//		map.put("memberId", "123123123");
//		return map;
//	}
//	
//	
//	
//	public void aasertContionsAction(int[] actions ,int action){
//		for (int i : actions) {
//			if( i == action){
//				return;
//			}
//		}
//		Assert.fail();
//	}
//	
//	
//	
//}
