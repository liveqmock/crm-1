//package com.deppon.crm.module.customer.workflow;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import com.deppon.crm.module.customer.shared.domain.Constant;
//
//public class SepMemberCreateTest {
//	
//	private WorkFlowTestUtil wfUtil;
//	
//	
//	@Before
//	public void setUp() {
//		wfUtil = new WorkFlowTestUtil("createSpecialMember"); 
//	}
//	
//	@Test
//	public void testSepMemberCreateWork(){
//		Map map = new HashMap();
//		map.put("memberId", "1234");
//		long workflowId = -1;
//		
//		String userId = Constant.Marketing;
//		
//		workflowId = wfUtil.inintWorkFlow(map);
//		wfUtil.doAction(userId, workflowId, Constant.CreateSepMemberWK_Pass, new HashMap());
//		wfUtil.assertWorkFlowEnd(workflowId);
//		
//		workflowId = wfUtil.inintWorkFlow(map);
//		wfUtil.doAction(userId, workflowId, Constant.CreateSepMemberWK_NoPass, new HashMap());
//		wfUtil.assertWorkFlowEnd(workflowId);
//	}
//	
//}
