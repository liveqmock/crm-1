//package com.deppon.crm.module.customer.server.manager;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import com.deppon.crm.module.authorization.shared.domain.User;
//import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
//import com.deppon.crm.module.customer.server.testutils.DataTestUtil;
//import com.deppon.crm.module.customer.server.testutils.TestUtils;
//import com.deppon.crm.module.customer.server.util.BeanUtil;
//import com.deppon.crm.module.customer.server.util.UserUtil;
//import com.deppon.crm.module.customer.server.utils.ContextUtil;
//import com.deppon.crm.module.customer.shared.domain.ChangeMemberDept;
//import com.deppon.crm.module.customer.shared.domain.Constant;
//import com.deppon.crm.module.customer.shared.domain.ExamineRecord;
//import com.deppon.crm.module.customer.shared.domain.WorkFlow;
//import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;
//import com.deppon.crm.module.customer.shared.exception.WorkFlowException;
//import com.deppon.crm.module.customer.shared.exception.WorkFlowExceptionType;
//import com.deppon.crm.module.customer.workflow.WorkFlowTestUtil;
//import com.deppon.crm.module.organization.shared.domain.Department;
//import com.deppon.crm.module.organization.shared.domain.Employee;
//import com.deppon.foss.framework.server.context.UserContext;
//
//public class MemberWorkFlowManagerTest extends BeanUtil{
//	
//	private static boolean isMock = true; 
//	
//	@Override
//	public void setUp(){
//		WorkFlowTestUtil.userJMock();
//		UserUtil.setUserToAdmin();
//		WorkFlowTestUtil.createChangeMocker();
//		Assert.assertNotNull(memberWorkFlowManager);
//	}
//	
//	public void setUpI(){
//		Assert.assertNotNull(memberWorkFlowManager);
//		
//		crDept= userDao.getUserRoleFunDeptById("1002");
//		// employee deptId
//		//  048843  180218
//		bizManager = userDao.getUserRoleFunDeptById("1004");
//		marking = userDao.getUserRoleFunDeptById("1003");
//		create = userDao.getUserRoleFunDeptById("319093");
//		//deptId 246224 001514 
//		neighborhood = userDao.queryUsersByDeptAndRole("246224","1006").get(0);
//		//deptId 319 003203
//		region = userDao.getUserRoleFunDeptById("1005");
//		//deptId 270 000164 
//		cause =  userDao.getUserRoleFunDeptById("1007");
//		
//	}
//	
//	
//	
//	
//	public void testChangeMemberDept() throws Exception{
//		tearDown1();
//		cleanTodoWorkflow();
//		setUpI();
//		isMock = false;
//		long wkId = 0;
//		ChangeMemberDept dept = null;
//		initUser("test");
//		
//		dept = TestUtils.createBean(ChangeMemberDept.class);
//		dept.setFromDeptId("180218");
//		wkId = memberManager.changeMemberDept(dept);
//
////		assertTodo(wkId, 1);
//		assertWorkUpdate(wkId,Constant.UNAPPROVED);
//		
//		initUser(Constant.BizManager);
////		testCommitChangeMemberDept(false,wkId);
////		assertTodo(wkId, 1);
//		assertWorkUpdate(wkId,Constant.UNAPPROVED);
//		assertWorkFlowEnd(wkId);
//		
//		
//		dept = TestUtils.createBean(ChangeMemberDept.class);
//		dept.setFromDeptId("180218");
//		try {
//			wkId = memberManager.changeMemberDept(dept);
//		} catch (Exception e) {
//			Assert.assertTrue(true);
//		}
//		assertTodo(wkId, 1);
//		assertWorkUpdate(wkId,Constant.UNAPPROVED);
//		
//		initUser(Constant.BizManager);
//		testCommitChangeMemberDept(true,wkId);
//		assertTodo(wkId,1);
//		assertWorkUpdate(wkId,Constant.UNAPPROVED);
//		assertWorkFlowEnd(wkId);
//		tearDown();
//		cleanTodoWorkflow();
//		
//		
//		System.out.println("------------------->"+wkId);
//		try {
//			DataTestUtil.cleanWorkflow(wkId+"");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	//TODO 这个测试有问题， 半真实环境，问题很大。
//	public void testContactVaryWorkFlow(){
//		setUpI();
//		isMock = false;
//		long wkId = 0;
//		initUser("test");
//		
//		wkId = memberWorkFlowManager.createContactVaryWorkFlow("124","123","190219","173218");
//		assertTodo(wkId, 0);
//		assertWorkUpdate(wkId,Constant.UNAPPROVED);
//		
//
//		initUser(Constant.Neighborhood);
//		testCommitContactVary(false,wkId);
//			assertTodo(wkId, 0);
//				assertWorkUpdate(wkId,Constant.DISAGREE);
//			assertWorkFlowEnd(wkId);
//		
//		wkId = memberWorkFlowManager.createContactVaryWorkFlow("124","123","190219","173218");
//			assertTodo(wkId, 1);
//			assertWorkUpdate(wkId,Constant.UNAPPROVED);
//		
//		initUser(Constant.Neighborhood);
//		testCommitContactVary(true,wkId);
//			assertTodo(wkId, 0);
//			assertWorkUpdate(wkId,Constant.AGREED);
//			assertWorkFlowEnd(wkId);
//		
//		
//		
//		wkId = memberWorkFlowManager.createContactVaryWorkFlow("124","123","190219","162776");
//			assertTodo(wkId, 1);
//				assertWorkUpdate(wkId,Constant.UNAPPROVED);
//		
//		initUser(Constant.Region);
//		testCommitContactVary(true,wkId);
//				assertTodo(wkId, 0);
//				assertWorkUpdate(wkId,Constant.AGREED);
//				assertWorkFlowEnd(wkId);
//		
//		//数据问题
////		wkId = memberWorkFlowManager.createContactVaryWorkFlow("124","190219","161776", fName);
//		assertTodo(wkId, 1);
//		assertWorkUpdate(wkId,Constant.UNAPPROVED);
//		
//		initUser(Constant.Cause);
//		testCommitContactVary(true,wkId);
//		assertTodo(wkId, 0);
//		assertWorkUpdate(wkId,Constant.AGREED);
//		assertWorkFlowEnd(wkId);
//		
//		wkId = memberWorkFlowManager.createContactVaryWorkFlow("124","123","190219","61646");
//				assertTodo(wkId, 1);
//			assertWorkUpdate(wkId,Constant.UNAPPROVED);
//		
//		initUser(Constant.Marketing);
//		testCommitContactVary(true,wkId);
//			assertTodo(wkId, 0);
//			assertWorkUpdate(wkId,Constant.AGREED);
//			assertWorkFlowEnd(wkId);
//		
//	}
//	
//	private void testCommitChangeMemberDept(boolean result, long wkId) {
//		ExamineRecord record = TestUtils.createBean(ExamineRecord.class);
//		record.setResult(result);
//		record.setWorkFlowId(wkId);
//		memberWorkFlowManager.commitChangeMemberDeptExamin(record);
//		assertExamine(wkId);
//	}
//	
//	private void testCommitContactVary(boolean result, long wkId) {
//		ExamineRecord record = TestUtils.createBean(ExamineRecord.class);
//		record.setResult(result);
//		record.setWorkFlowId(wkId);
//		memberWorkFlowManager.commitContactVaryExamin(record);
//		assertExamine(wkId);
//	}
//
//	@Test
//	public void testCreateSepMemberWorkFlowInter(){
//		tearDown1();
//		System.out.println("wkId-----null------4--"+null);
//		cleanTodoWorkflow();
//		System.out.println("wkId-----null------5--"+null);
//		setUpI();		
//		System.out.println("wkId-----null------6--"+null);
//		isMock = false;
//		initUser("test");
//		System.out.println("wkId-----null-------"+null);
//		long wkId = memberWorkFlowManager.createSepCreateMemberWorkFlow("123");
//		System.out.println("wkId------------"+wkId);/*
////		assertTodo(wkId, 1);
//		assertWorkUpdate(wkId,Constant.UNAPPROVED);
//		
//		initUser(Constant.Marketing);
////		testCommitSepMember(false,wkId);
////		assertTodo(wkId, 0);
//		assertWorkUpdate(wkId,Constant.UNAPPROVED);
////		assertWorkFlowEnd(wkId);
//		
//		initUser("test");
//		wkId = memberWorkFlowManager.createSepCreateMemberWorkFlow("123");
////		assertTodo(wkId, 1);
//		assertWorkUpdate(wkId,Constant.UNAPPROVED);
//
//		try {
//			initUser("test");
//			testCommitSepMember(true,wkId);
//			Assert.fail();
//		} catch (WorkFlowException e) {
//			Assert.assertEquals(e.getErrorCode(), WorkFlowExceptionType.NoAuthor.getErrCode());
//		}
//		
//		initUser(Constant.Marketing);
////		testCommitSepMember(true,wkId);
////		assertWorkFlowEnd(wkId);
////		assertTodo(wkId, 0);
//		assertWorkUpdate(wkId,Constant.UNAPPROVED);*/
//		try {
//			DataTestUtil.cleanWorkflow(wkId+"");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private void assertWorkUpdate(long wkId, String status) {
//		if(!isMock){
//			WorkFlowCondition condition = new WorkFlowCondition();
//			condition.setWorkflowId(wkId);
//			List<WorkFlow> list = workFlowDao.searchWorkFLow(condition);
//			System.out.println("assertWorkUpdate---"+list.size());
//			for (WorkFlow workFlow : list) {
//				Assert.assertEquals(status, workFlow.getStatus());
//			}
//		}
//	}
//
//	public void testModifyMember(){
//		setUpI();
//		WorkFlowTestUtil.jMockAlterManager();
//		
//		isMock = false;
//		
//		String accountStepUser = Constant.BizManager;
//		String baseDateStepUser = Constant.CrDept;;
//		
//		testTwoSep(accountStepUser,baseDateStepUser);
//		testAccountSep(accountStepUser);
//		testBaseDateSep(baseDateStepUser);
//		
//		UserContext.setCurrentUser(create);
//		Set<String> dateTypes = new HashSet<String>();
//		dateTypes.add(Constant.AccountDateType);
//		
//		UserContext.setCurrentUser(create);
//		long wkId = memberWorkFlowManager.createNewModifyMemberWorkFlow(dateTypes, "123");
//		assertTodo(wkId,1);
//		UserContext.setCurrentUser(bizManager);
//		testCommitModify(true,wkId);
//		assertTodo(wkId,0);
//		assertWorkFlowEnd(wkId);
//
//		UserContext.setCurrentUser(create);
//		wkId = memberWorkFlowManager.createNewModifyMemberWorkFlow(dateTypes, "123");
//		assertTodo(wkId,1);
//		UserContext.setCurrentUser(bizManager);
//		testCommitModify(false,wkId);
//		assertTodo(wkId,0);
//		assertWorkFlowEnd(wkId);
//		
//	}
//	
//	private void initUser(String user){
//		if(isMock){
//			WorkFlowTestUtil.initUser(user);
//		}else{
//			if("test".equals(user)){
//				UserContext.setCurrentUser(create);
//			}
//			if(Constant.BizManager.equals(user)){
//				UserContext.setCurrentUser(bizManager);
//			}
//			if(Constant.CrDept.equals(user)){
//				UserContext.setCurrentUser(crDept);
//			}
//			if(Constant.Marketing.equals(user)){
//				UserContext.setCurrentUser(marking);
//			}
//			if(Constant.Neighborhood.equals(user)){
//				UserContext.setCurrentUser(neighborhood);
//			}
//			if(Constant.Region.equals(user)){
//				UserContext.setCurrentUser(region);
//			}
//			if(Constant.Cause.equals(user)){
//				UserContext.setCurrentUser(cause);
//			}
//		}
//		
//	}
//	
//	
//	private void assertExamine(long wkId){
//		if(!isMock){
//			List<ExamineRecord> list = examineRecordDao.getExamineRecordByWorkflowId(wkId);
//			for (ExamineRecord examineRecord : list) {
//				String userId = examineRecord.getDisposeUserId();
//				if(userId.equals(ContextUtil.getCurrentUserName())){
//					return;
//				}
//			}
//			Assert.fail();
//		}
//		
//	}
//	
//	private void assertTodo(long wkId,int length) {
//		if(!isMock){
//			Assert.assertTrue(wkId != -1);
//			List<TodoWorkflow> todoWorkList = todoWorkflowManager.getTodoWorkflowByWorkflowId(null, wkId+"", null, null);
//			Assert.assertNotNull(todoWorkList);
//			Assert.assertEquals(todoWorkList.size(),length);
//		}
//	}
//	
//	public void testModifyMemberWorkFlow(){
//		setUp();
//		String accountStepUser = Constant.BizManager;
//		String baseDateStepUser = Constant.CrDept;;
//		isMock = true;
//		
//		testTwoSep(accountStepUser,baseDateStepUser);
//		testAccountSep(accountStepUser);
//			testBaseDateSep(baseDateStepUser);
//	}
//	
//	private void testBaseDateSep(String baseDateStepUser) {
//		initUser("test");
//		Set<String> dateTypes = new HashSet<String>();
//		dateTypes.add(Constant.BaseDateType);
//		
//		initUser("test");
//		long wkId = memberWorkFlowManager.createNewModifyMemberWorkFlow(dateTypes, "123");
//		assertWorkUpdate(wkId,Constant.UNAPPROVED);
//		assertTodo(wkId,1);
//		initUser(baseDateStepUser);
//		testCommitModify(false,wkId);
//		assertWorkUpdate(wkId,Constant.DISAGREE);
//		assertTodo(wkId,0);
//		assertWorkFlowEnd(wkId);
//		
//		initUser("test");
//		wkId = memberWorkFlowManager.createNewModifyMemberWorkFlow(dateTypes, "123");
//		assertTodo(wkId,1);
//		initUser(baseDateStepUser);
//		testCommitModify(true,wkId);
//		assertWorkUpdate(wkId,Constant.AGREED);
//		assertTodo(wkId,0);
//		assertWorkFlowEnd(wkId);
//	}
//
//	private void testAccountSep(String accountStepUser) {
//		initUser("test");
//		Set<String> dateTypes = new HashSet<String>();
//		dateTypes.add(Constant.AccountDateType);
//		
//		initUser("test");
//		long wkId = memberWorkFlowManager.createNewModifyMemberWorkFlow(dateTypes, "123");
//		assertWorkUpdate(wkId,Constant.UNAPPROVED);
//		assertTodo(wkId,1);
//		initUser(accountStepUser);
//		testCommitModify(false,wkId);
//		assertWorkUpdate(wkId,Constant.DISAGREE);
//		assertTodo(wkId,0);
//		assertWorkFlowEnd(wkId);
//		
//		initUser("test");
//		wkId = memberWorkFlowManager.createNewModifyMemberWorkFlow(dateTypes, "123");
//		assertTodo(wkId,1);
//		initUser(accountStepUser);
//		testCommitModify(true,wkId);
//		assertWorkUpdate(wkId,Constant.AGREED);
//		assertTodo(wkId,0);
//		assertWorkFlowEnd(wkId);
//	}
//
//	public void testTwoSep(String accountStepUser,String baseDateStepUser){
//		Set<String> dateTypes = new HashSet<String>();
//		dateTypes.add(Constant.BaseDateType);
//		dateTypes.add(Constant.AccountDateType);
//		
//		long wkId = -1;
//		initUser("test");
//		wkId = memberWorkFlowManager.createNewModifyMemberWorkFlow(dateTypes, "123");
//		assertTodo(wkId,1);
//		assertWorkUpdate(wkId,Constant.UNAPPROVED);
//		try {
//			testCommitModify(false,wkId);
//			Assert.fail();
//		} catch (WorkFlowException e) {
//			Assert.assertEquals(e.getErrorCode(), WorkFlowExceptionType.NoAuthor.getErrCode());
//		}
//		
//		initUser(accountStepUser);
//		testCommitModify(false,wkId);
//		assertTodo(wkId,0);
//		assertWorkFlowEnd(wkId);
//		assertWorkUpdate(wkId,Constant.DISAGREE);
//
//		
//		initUser("test");
//		wkId = memberWorkFlowManager.createNewModifyMemberWorkFlow(dateTypes, "123");
//		assertTodo(wkId,1);
//		assertWorkUpdate(wkId,Constant.UNAPPROVED);
//		initUser(accountStepUser);
//		testCommitModify(true,wkId);
//		assertTodo(wkId,1);
//		assertWorkUpdate(wkId,Constant.APPROVING);
//		initUser(baseDateStepUser);
//		testCommitModify(false,wkId);
//		assertTodo(wkId,0);
//		assertWorkUpdate(wkId,Constant.DISAGREE);
//		assertWorkFlowEnd(wkId);
//		
//		initUser("test");
//		wkId = memberWorkFlowManager.createNewModifyMemberWorkFlow(dateTypes, "123");
//		assertTodo(wkId,1);
//		assertWorkUpdate(wkId,Constant.UNAPPROVED);
//		initUser(accountStepUser);
//		testCommitModify(true,wkId);
//		assertTodo(wkId,1);
//		assertWorkUpdate(wkId,Constant.APPROVING);
//		initUser(baseDateStepUser);
//		testCommitModify(true,wkId);
//		assertTodo(wkId,0);
//		assertWorkUpdate(wkId,Constant.AGREED);
//		assertWorkFlowEnd(wkId);
//
//	}
//	
//	private void testCommitModify(boolean result,long wkId){
//		ExamineRecord record = TestUtils.createBean(ExamineRecord.class);
//		record.setResult(result);
//		record.setWorkFlowId(wkId);
//		memberWorkFlowManager.commitModifyMemberExamin(record);
//		assertExamine(wkId);
//	}
//	
//	public void assertWorkFlowEnd(long workflowId){
//		Assert.assertEquals(memberWorkFlowManager.getWorkflowManage().getCurrentSteps("",workflowId).length, 1);
//	}
//	
//	/**
//	 * @description function.  
//	 * @author 潘光均
//	 * @version 0.1 2012-11-19
//	 * @param 
//	 *@date 2012-11-19
//	 * @return void
//	 * @update 2012-11-19 下午1:48:01
//	 */
//	private void tearDown1() {
//		try {
//			DataTestUtil.cleanTodo("180218");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * @description .  
//	 * @author 潘光均
//	 * @version 0.1 2012-11-19
//	 * @param 
//	 *@date 2012-11-19
//	 * @return void
//	 * @update 2012-11-19 下午2:01:04
//	 */
//	private void cleanTodoWorkflow() {
//		try {
//			DataTestUtil.cleanTodoWorkflow("123");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	//特殊会员创建工作流测试
//	public void testCreateSepMemberWorkFlow(){
//		setUp();
//		isMock = true;
//		initUser("test");
//		long wkId = memberWorkFlowManager.createSepCreateMemberWorkFlow("123");
//		
//		initUser(Constant.Marketing);
//		testCommitSepMember(false,wkId);
//		//	assertWorkFlowEnd(wkId);
//		
//		
//		initUser("test");
//		wkId = memberWorkFlowManager.createSepCreateMemberWorkFlow("123");
//		
//		try {
//			initUser("test");
//			testCommitSepMember(true,wkId);
//			Assert.fail();
//		} catch (WorkFlowException e) {
//			Assert.assertEquals(e.getErrorCode(), WorkFlowExceptionType.NoAuthor.getErrCode());
//		}
//		
//		initUser(Constant.Marketing);
//			testCommitSepMember(true,wkId);
//			assertWorkFlowEnd(wkId);
//	}
//
//	private void testCommitSepMember(boolean result,long wkId) {
//		ExamineRecord record = TestUtils.createBean(ExamineRecord.class);
//		record.setResult(result);
//		record.setWorkFlowId(wkId);
//		memberWorkFlowManager.commitSepCreateMemberExamin(record);
//		assertExamine(wkId);
//	}
//}
