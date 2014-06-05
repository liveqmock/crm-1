package com.deppon.crm.module.recompense.server.workflow;

import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.recompense.server.util.TestDataCreator;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.loader.WorkflowDescriptor;
import com.opensymphony.workflow.spi.Step;

public class RecompenseWorkflowOldTest extends TestCase {
	// ~ Constructors
	// ///////////////////////////////////////////////////////////
	private static final String USER_TEST = "crm";
	// private static final String BUSINESS_MANAGER = "229492";
	protected Log log;
	protected Workflow workflow;
	protected WorkflowDescriptor workflowDescriptor;
	// protected RecompenseManager recompenseManager;
	private Long lastTime = System.currentTimeMillis();

	// ClassPathXmlApplicationContext factory;

	// UserCacheProvider userCacheProvider;

	public RecompenseWorkflowOldTest(String s) {
		super(s);
		log = LogFactory.getLog(getClass());
	}

	protected void setUp() throws Exception {
		// AppContext.initAppContext("application", "");
		// String resource =
		// "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml";
		// factory = new ClassPathXmlApplicationContext(resource);
		// userCacheProvider = (UserCacheProvider) factory
		// .getBean("userCacheProvider");
		// String resource =
		// "com/deppon/crm/module/recompense/server/META-INF/spring.xml";
		// factory = new ClassPathXmlApplicationContext(resource);
		// recompenseManager = (RecompenseManager) factory
		// .getBean("recompenseManager");
		// workflow = (Workflow) factory.getBean("basicWorkflow");
		// WorkflowManager workManager = (WorkflowManager) factory
		// .getBean("workflowManager");
		// workflow = workManager.getWorkflow();
		// System.out.println();
		// workflow = new BasicWorkflow(USER_TEST);
		//
		// UserManager um = UserManager.getInstance();
		// assertNotNull("Could not get UserManager", um);
		//
		// try {
		// um.getUser(USER_TEST);
		// } catch (EntityNotFoundException enfe) {
		// User test = um.createUser(USER_TEST);
		// test.setPassword("crm");
		//
		// Group bm = um.createGroup("BusinessManager");
		// Group casher = um.createGroup("Casher");
		// Group admin = um.createGroup("RecompenseAdmin");
		// Group rMgr = um.createGroup("RegionalManager");
		// Group rDir = um.createGroup("RegionalDirector");
		// Group bDir = um.createGroup("BusinessDirector");
		// Group oVp = um.createGroup("OperationVP");
		// Group ceo = um.createGroup("CEO");
		// Group accountant = um.createGroup("Accountant");
		// Group rAccountant = um.createGroup("RegionalAccountant");
		// Group aReceivable = um.createGroup("AccountsReceivable");
		//
		// test.addToGroup(bm);
		// test.addToGroup(casher);
		// test.addToGroup(admin);
		// test.addToGroup(rMgr);
		// test.addToGroup(rDir);
		// test.addToGroup(bDir);
		// test.addToGroup(oVp);
		// test.addToGroup(ceo);
		// test.addToGroup(accountant);
		// test.addToGroup(rAccountant);
		// test.addToGroup(aReceivable);
		// }
	}

	protected String getWorkflowName() {
		return getClass().getResource("/recompense.xml").toString();
	}

	protected void logActions(int[] actions) {
		for (int i = 0; i < actions.length; i++) {
			String name = workflowDescriptor.getAction(actions[i]).getName();
			int actionId = workflowDescriptor.getAction(actions[i]).getId();

			if (log.isDebugEnabled()) {
				log.debug("Actions Available: " + name + " id:" + actionId);
			}
		}
	}

	public void testFastRecompenseWorkflow() throws Exception {
		// MemoryStore.get().clearRecompenseApplication();
		// printCurrentUsedTime("testFastRecompenseWorkflow: begin");
		// // long workflowId = createWorkFlow();
		// RecompenseApplication app = TestUtils.createBase();//new
		// RecompenseApplication();
		// // app.setWorkflowId(workflowId);
		// app.setRealAmount(Double.valueOf(500));
		// // app.setId("1000000001");
		// // app.setWaybill(new Waybill());
		// app.setOperator(USER_TEST);
		// app.setRecompenseMethod(RecompenseApplication.FAST_TYPE);
		//
		// User user = (User) userCacheProvider.get(BUSINESS_MANAGER);
		// // User user = (User)userCache.get("229492");
		// UserContext.setCurrentUser(user);
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_SUBMIT_ACTION, BUSINESS_MANAGER);
		// doGenernalAssert(app, 3, "Underway", 1, 1, BUSINESS_MANAGER);
		//
		// // recompenseManager.performAction(app,
		// // Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
		// // doGenernalAssert(app, 3, "Underway", 1, 2, USER_TEST);
		//
		// // 收回
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_FAST_CANCEL_ACTION, USER_TEST);
		// doGenernalAssert(app, 4, "Underway", 1, 3, USER_TEST);
		// // 理赔提交
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION, USER_TEST);
		// doGenernalAssert(app, 3, "Underway", 1, 4, USER_TEST);
		// // 收银提交
		// PaymentBill paymentBill = new PaymentBill();
		// paymentBill.setPaymentType("other");
		// app.setPaymentBill(paymentBill);
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_CASHER_ACTION, USER_TEST);
		// doGenernalAssert(app, 2, "Underway", 1, 5, USER_TEST);
		// printCurrentUsedTime("finished");
	}

	// public void testCancelRecompenseWorkflow() throws Exception {
	// MemoryStore.get().clearRecompenseApplication();
	// printCurrentUsedTime("testCancelRecompenseWorkflow: begin");
	//
	// RecompenseApplication app = new RecompenseApplication();
	// app.setRealAmount(Double.valueOf(1500));
	// app.setWaybill(new Waybill());
	// app.setOperator(USER_TEST);
	// app.setRecompenseMethod(RecompenseApplication.NORMAL_TYPE);
	// RecompenseManager recompenseManager = RecompenseManager.get();
	// // 理赔创建
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CREATE_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 1, USER_TEST);
	// // 理赔提交
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Ready", 1, 2, USER_TEST);
	// // 退回
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_NORMAL_CANCEL_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 3, USER_TEST);
	// // 理赔资料确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Ready", 1, 4, USER_TEST);
	// printCurrentUsedTime("finished");
	// }
	//
	public void testNormalPaymentRecompenseWorkflow() throws Exception {
		// MemoryStore.get().clearRecompenseApplication();
		printCurrentUsedTime("testNormalPaymentRecompenseWorkflow: begin");
		// long workflowId = createWorkFlow();
		RecompenseApplication app = TestDataCreator.createBase();
		app.setRealAmount(Double.valueOf(150000));
		app.setOperator(USER_TEST);
		app.setRecompenseMethod(Constants.NORMAL_TYPE);

		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
		// doGenernalAssert(app, 3, "Underway", 1, 1, USER_TEST);
		//
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_REPORT_SAVE_ACTION, USER_TEST);
		// doGenernalAssert(app, 3, "Underway", 1, 2, USER_TEST);

		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
		// doGenernalAssert(app, 4, "Ready", 1, 3, USER_TEST);
		//
		// //
		// // 理赔资料确认
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_DOCUMENT_CONFIRM_ACTION, USER_TEST);
		// doGenernalAssert(app, 4, "Underway", 1, 4, USER_TEST);
		//
		// // 理赔处理保存
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_ADMIN_SAVE_ACTION, USER_TEST);
		// doGenernalAssert(app, 4, "Underway", 1, 5, USER_TEST);
		// // 理赔金额分配
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION, USER_TEST);
		// doGenernalAssert(app, 3, "Confirm", 1, 6, USER_TEST);
		// // 经理金额确认
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_AMOUNT_CONFIRM_ACTION, USER_TEST);
		// doGenernalAssert(app, 2, "Confirm", 1, 7, USER_TEST);
		// // 理赔确认金额
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_RECONFIRM_ACTION, USER_TEST);
		// doGenernalAssert(app, 2, "Underway", 1, 8, USER_TEST);
		// // 区长审批
		// recompenseManager
		// .performAction(app,
		// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
		// USER_TEST);
		// doGenernalAssert(app, 2, "Underway", 1, 9, USER_TEST);
		// // 大区长审批
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_REGIONAL_DIRECTOR_CONFIRM_ACTION,
		// USER_TEST);
		// doGenernalAssert(app, 2, "Underway", 1, 10, USER_TEST);
		// // 总裁审批
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_BUSINESS_DIRECTOR_CONFIRM_ACTION,
		// USER_TEST);
		// doGenernalAssert(app, 2, "Underway", 1, 11, USER_TEST);
		// // 副总审批
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_OPERATION_VP_CONFIRM_ACTION, USER_TEST);
		// doGenernalAssert(app, 2, "Underway", 1, 12, USER_TEST);
		// // CEO审批
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_CEO_CONFIRM_ACTION, USER_TEST);
		// doGenernalAssert(app, 2, "Underway", 1, 13, USER_TEST);
		// //
		// PaymentBill paymentBill = new PaymentBill();
		// paymentBill.setPaymentType("other");
		// app.setPaymentBill(paymentBill);
		// // 收银单保存
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_PAYMENT_SAVE_ACTION, USER_TEST);
		// doGenernalAssert(app, 2, "Underway", 1, 14, USER_TEST);
		// // 收银提交
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_CASHER_ACTION, USER_TEST);
		// doGenernalAssert(app, 2, "Underway", 1, 15, USER_TEST);
		// // 部门会计
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_ACCOUNTANT_REJECT_ACTION, USER_TEST);
		// doGenernalAssert(app, 2, "Rejected", 1, 16, USER_TEST);
		// // 收银提交
		// paymentBill.setPaymentType("other");
		// app.setPaymentBill(paymentBill);
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_CASHER_ACTION, USER_TEST);
		// doGenernalAssert(app, 2, "Underway", 1, 17, USER_TEST);
		// // 部门会计
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_ACCOUNTANT_CONFIRM_ACTION, USER_TEST);
		// doGenernalAssert(app, 2, "Underway", 1, 18, USER_TEST);
		// // 大区会计
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_REGIONAL_ACCOUNTANT_REJECT_ACTION,
		// USER_TEST);
		// doGenernalAssert(app, 2, "Rejected", 1, 19, USER_TEST);
		// // 收银提交
		// paymentBill.setPaymentType("other");
		// app.setPaymentBill(paymentBill);
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_CASHER_ACTION, USER_TEST);
		// doGenernalAssert(app, 2, "Underway", 1, 20, USER_TEST);
		// // 部门会计
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_ACCOUNTANT_CONFIRM_ACTION, USER_TEST);
		// doGenernalAssert(app, 2, "Underway", 1, 21, USER_TEST);
		// // 大区会计
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_REGIONAL_ACCOUNTANT_CONFIRM_ACTION,
		// USER_TEST);
		// doGenernalAssert(app, 1, "Paid", 1, 22, USER_TEST);
		// // 重新付款
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_FAILEDPAY_ACTION, USER_TEST);
		// doGenernalAssert(app, 2, "Rejected", 1, 23, USER_TEST);
		// // 收银提交
		// paymentBill.setPaymentType("cash");
		// app.setPaymentBill(paymentBill);
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_CASHER_ACTION, USER_TEST);
		// doGenernalAssert(app, 2, "Underway", 1, 24, USER_TEST);
		// // 部门会计
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_ACCOUNTANT_CONFIRM_ACTION, USER_TEST);
		// doGenernalAssert(app, 1, "Paid", 1, 25, USER_TEST);
		// // 重新付款
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_FAILEDPAY_ACTION, USER_TEST);
		// doGenernalAssert(app, 2, "Rejected", 1, 26, USER_TEST);
		// // 收银提交
		// paymentBill.setPaymentType("account");
		// app.setPaymentBill(paymentBill);
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_CASHER_ACTION, USER_TEST);
		// doGenernalAssert(app, 1, "Paid", 1, 27, USER_TEST);
		// // 重新付款
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_FAILEDPAY_ACTION, USER_TEST);
		// doGenernalAssert(app, 2, "Rejected", 1, 28, USER_TEST);
		// printCurrentUsedTime("finished");
	}

	//
	// public void testNormalApproveRecompenseWorkflow() throws Exception {
	// MemoryStore.get().clearRecompenseApplication();
	// printCurrentUsedTime("testNormalApproveRecompenseWorkflow: begin");
	// // long workflowId = createWorkFlow();
	//
	// RecompenseApplication app = new RecompenseApplication();
	// // app.setWorkflowId(workflowId);
	// app.setRealAmount(Double.valueOf(150000));
	// // app.setId("1000000001");
	// app.setWaybill(new Waybill());
	// app.setOperator(USER_TEST);
	// app.setRecompenseMethod(RecompenseApplication.NORMAL_TYPE);
	// RecompenseManager recompenseManager = RecompenseManager.get();
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CREATE_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 1, USER_TEST);
	//
	// // 理赔提交
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Ready", 1, 2, USER_TEST);
	// // 理赔资料确认===退回
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_DOCUMENT_REJECT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Rejected", 1, 3, USER_TEST);
	// // 理赔提交
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Ready", 1, 4, USER_TEST);
	// // 理赔资料确认===确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_DOCUMENT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Underway", 1, 5, USER_TEST);
	// // 理赔金额分配
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_DOCUMENT_CANCEL_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Ready", 1, 6, USER_TEST);
	// // 理赔资料确认===确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_DOCUMENT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Underway", 1, 7, USER_TEST);
	// // 理赔金额分配
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 8, USER_TEST);
	// // // 经理金额确认
	// //
	// recompenseManager.performAction(app,Constants.RECOMPENSE_AMOUNT_CONFIRM_ACTION,USER_TEST);
	// // doGenernalAssert(app, 2, "Rejected", 1, 9, USER_TEST);
	// // // ==================================
	// // // 理赔金额分配
	// //
	// recompenseManager.performAction(app,Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION,USER_TEST);
	// // doGenernalAssert(app, 2, "Confirm", 1, 10, USER_TEST);
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Confirm", 1, 9, USER_TEST);
	// // 理赔确认金额
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_RECONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 10, USER_TEST);
	// // 区长审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_REJECT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Rejected", 1, 11, USER_TEST);
	// // ==================================
	// // 理赔金额分配
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 12, USER_TEST);
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Confirm", 1, 13, USER_TEST);
	// // 理赔确认金额
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_RECONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 14, USER_TEST);
	// // 区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 15, USER_TEST);
	// // 大区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_DIRECTOR_REJECT_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 4, "Rejected", 1, 16, USER_TEST);
	// // ==================================
	// // 理赔金额分配
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 17, USER_TEST);
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Confirm", 1, 18, USER_TEST);
	// // 理赔确认金额
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_RECONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 19, USER_TEST);
	// // 区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 20, USER_TEST);
	// // 大区长审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_DIRECTOR_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 21, USER_TEST);
	// // 总裁审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_BUSINESS_DIRECTOR_REJECT_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 4, "Rejected", 1, 22, USER_TEST);
	// // ==================================
	// // 理赔金额分配
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 23, USER_TEST);
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Confirm", 1, 24, USER_TEST);
	// // 理赔确认金额
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_RECONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 25, USER_TEST);
	// // 区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 26, USER_TEST);
	// // 大区长审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_DIRECTOR_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 27, USER_TEST);
	// // 总裁审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_BUSINESS_DIRECTOR_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 28, USER_TEST);
	// // 副总审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OPERATION_VP_REJECT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Rejected", 1, 29, USER_TEST);
	// // ==================================
	// // 理赔金额分配
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 30, USER_TEST);
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Confirm", 1, 31, USER_TEST);
	// // 理赔确认金额
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_RECONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 32, USER_TEST);
	// // 区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 33, USER_TEST);
	// // 大区长审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_DIRECTOR_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 34, USER_TEST);
	// // 总裁审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_BUSINESS_DIRECTOR_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 35, USER_TEST);
	// // 副总审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OPERATION_VP_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 36, USER_TEST);
	// // CEO审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CEO_REJECT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Rejected", 1, 37, USER_TEST);
	// // =================================
	// // 理赔金额分配
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 38, USER_TEST);
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Confirm", 1, 39, USER_TEST);
	// // 理赔确认金额
	// // performAction(app,
	// // Constants.RECOMPENSE_REGIONAL_DIRECTOR_CONFIRM_ACTION,USER_TEST)
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_RECONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 40, USER_TEST);
	// // 区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 41, USER_TEST);
	// // 大区长审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_DIRECTOR_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 42, USER_TEST);
	// // 总裁审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_BUSINESS_DIRECTOR_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 43, USER_TEST);
	// // 副总审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OPERATION_VP_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 44, USER_TEST);
	// // CEO审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CEO_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 45, USER_TEST);
	// // 收银提交
	// PaymentBill paymentBill = new PaymentBill();
	// paymentBill.setPaymentType("other");
	// app.setPaymentBill(paymentBill);
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CASHER_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 46, USER_TEST);
	// printCurrentUsedTime("finished");
	// }
	//
	// public void testNormal4999ApproveRecompenseWorkflow() throws Exception {
	// MemoryStore.get().clearRecompenseApplication();
	// printCurrentUsedTime("testNormal4999ApproveRecompenseWorkflow: begin");
	// // long workflowId = createWorkFlow();
	//
	// RecompenseApplication app = new RecompenseApplication();
	// // app.setWorkflowId(workflowId);
	// app.setRealAmount(Double.valueOf(4999));
	// // app.setId("1000000001");
	// app.setWaybill(new Waybill());
	// app.setOperator(USER_TEST);
	// app.setRecompenseMethod(RecompenseApplication.NORMAL_TYPE);
	// RecompenseManager recompenseManager = RecompenseManager.get();
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CREATE_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 1, USER_TEST);
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Ready", 1, 2, USER_TEST);
	//
	// // 理赔资料确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_DOCUMENT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Underway", 1, 3, USER_TEST);
	// // 理赔金额分配
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 4, USER_TEST);
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Confirm", 1, 5, USER_TEST);
	// // 理赔确认金额
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_RECONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 6, USER_TEST);
	// // 区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 7, USER_TEST);
	// // 收银提交
	// PaymentBill paymentBill = new PaymentBill();
	// paymentBill.setPaymentType("other");
	// app.setPaymentBill(paymentBill);
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CASHER_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 8, USER_TEST);
	// // 部门会计
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_ACCOUNTANT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 9, USER_TEST);
	// // 大区会计
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_ACCOUNTANT_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 1, "Paid", 1, 10, USER_TEST);
	// // 重新付款
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_FAILEDPAY_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Rejected", 1, 11, USER_TEST);
	// printCurrentUsedTime("finished");
	// }
	//
	// public void testNormal9999ApproveRecompenseWorkflow() throws Exception {
	// MemoryStore.get().clearRecompenseApplication();
	// printCurrentUsedTime("testNormal9999ApproveRecompenseWorkflow: begin");
	// // long workflowId = createWorkFlow();
	//
	// RecompenseApplication app = new RecompenseApplication();
	// // app.setWorkflowId(workflowId);
	// app.setRealAmount(Double.valueOf(9999));
	// // app.setId("1000000001");
	// app.setWaybill(new Waybill());
	// app.setOperator(USER_TEST);
	// app.setRecompenseMethod(RecompenseApplication.NORMAL_TYPE);
	// RecompenseManager recompenseManager = RecompenseManager.get();
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CREATE_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 1, USER_TEST);
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Ready", 1, 2, USER_TEST);
	//
	// // 理赔资料确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_DOCUMENT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Underway", 1, 3, USER_TEST);
	// // 理赔金额分配
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 4, USER_TEST);
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Confirm", 1, 5, USER_TEST);
	// // 理赔确认金额
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_RECONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 6, USER_TEST);
	// // 区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 7, USER_TEST);
	// // 大区长审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_DIRECTOR_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 8, USER_TEST);
	// // 收银提交
	// PaymentBill paymentBill = new PaymentBill();
	// paymentBill.setPaymentType("other");
	// app.setPaymentBill(paymentBill);
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CASHER_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 9, USER_TEST);
	// // 部门会计
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_ACCOUNTANT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 10, USER_TEST);
	// // 大区会计
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_ACCOUNTANT_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 1, "Paid", 1, 11, USER_TEST);
	// // 重新付款
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_FAILEDPAY_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Rejected", 1, 12, USER_TEST);
	// printCurrentUsedTime("finished");
	// }
	//
	// public void testOverpayApproveRecompenseWorkflow() throws Exception {
	// MemoryStore.get().clearRecompenseApplication();
	// printCurrentUsedTime("testOverpayApproveRecompenseWorkflow: begin");
	// // long workflowId = createWorkFlow();
	//
	// RecompenseApplication app = new RecompenseApplication();
	// // app.setWorkflowId(workflowId);
	// app.setRealAmount(Double.valueOf(150000));
	// // app.setId("1000000001");
	// app.setWaybill(new Waybill());
	// app.setOperator(USER_TEST);
	// app.setRecompenseMethod(RecompenseApplication.NORMAL_TYPE);
	// RecompenseManager recompenseManager = RecompenseManager.get();
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CREATE_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 1, USER_TEST);
	// // 理赔提交
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Ready", 1, 2, USER_TEST);
	// // 理赔资料确认===确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_DOCUMENT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Underway", 1, 3, USER_TEST);
	// // 理赔金额分配
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 4, USER_TEST);
	// // 经理金额确认
	// app.setOverpayAmount(Double.valueOf(100));
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OVERPAY_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 5, USER_TEST);
	// // 区长审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_REJECT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 6, USER_TEST);
	// // ==================================
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OVERPAY_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 7, USER_TEST);
	// // 区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 8, USER_TEST);
	// // 大区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_DIRECTOR_REJECT_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 9, USER_TEST);
	// // ==================================
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OVERPAY_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 10, USER_TEST);
	// // 区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 11, USER_TEST);
	// // 大区长审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_DIRECTOR_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 12, USER_TEST);
	// // 总裁审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_BUSINESS_DIRECTOR_REJECT_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 13, USER_TEST);
	// // ==================================
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OVERPAY_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 14, USER_TEST);
	// // 区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 15, USER_TEST);
	// // 大区长审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_DIRECTOR_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 16, USER_TEST);
	// // 总裁审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_BUSINESS_DIRECTOR_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 17, USER_TEST);
	// // 副总审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OPERATION_VP_REJECT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 18, USER_TEST);
	// // ==================================
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OVERPAY_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 19, USER_TEST);
	// // 区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 20, USER_TEST);
	// // 大区长审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_DIRECTOR_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 21, USER_TEST);
	// // 总裁审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_BUSINESS_DIRECTOR_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 22, USER_TEST);
	// // 副总审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OPERATION_VP_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 23, USER_TEST);
	// // CEO审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CEO_REJECT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 24, USER_TEST);
	// // ==================================
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OVERPAY_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 25, USER_TEST);
	// // 区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 26, USER_TEST);
	// // 大区长审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_DIRECTOR_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 27, USER_TEST);
	// // 总裁审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_BUSINESS_DIRECTOR_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 28, USER_TEST);
	// // 副总审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OPERATION_VP_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 29, USER_TEST);
	// // CEO审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CEO_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Overpay", 1, 30, USER_TEST);
	// // 确认多赔
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OVERPAY_RECONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 31, USER_TEST);
	// // 收银提交
	// PaymentBill paymentBill = new PaymentBill();
	// paymentBill.setPaymentType("other");
	// app.setPaymentBill(paymentBill);
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CASHER_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 32, USER_TEST);
	// printCurrentUsedTime("finished");
	// }
	//
	// public void testOverpay4999ApproveRecompenseWorkflow() throws Exception {
	// MemoryStore.get().clearRecompenseApplication();
	// printCurrentUsedTime("testOverpay4999ApproveRecompenseWorkflow: begin");
	// // long workflowId = createWorkFlow();
	//
	// RecompenseApplication app = new RecompenseApplication();
	// // app.setWorkflowId(workflowId);
	// app.setRealAmount(Double.valueOf(4999));
	// // app.setId("1000000001");
	// app.setWaybill(new Waybill());
	// app.setOperator(USER_TEST);
	// app.setRecompenseMethod(RecompenseApplication.NORMAL_TYPE);
	// RecompenseManager recompenseManager = RecompenseManager.get();
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CREATE_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 1, USER_TEST);
	//
	// // 理赔提交
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Ready", 1, 2, USER_TEST);
	//
	// // 理赔资料确认===确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_DOCUMENT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Underway", 1, 3, USER_TEST);
	// // 理赔金额分配
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 4, USER_TEST);
	// // 经理金额确认
	// app.setOverpayAmount(Double.valueOf(100));
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OVERPAY_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 5, USER_TEST);
	// // 区长审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_REJECT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 6, USER_TEST);
	// // =================================
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OVERPAY_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 7, USER_TEST);
	// // 区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Overpay", 1, 8, USER_TEST);
	// // 确认多赔
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OVERPAY_RECONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 9, USER_TEST);
	// // 收银提交
	// PaymentBill paymentBill = new PaymentBill();
	// paymentBill.setPaymentType("other");
	// app.setPaymentBill(paymentBill);
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CASHER_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 10, USER_TEST);
	// printCurrentUsedTime("finished");
	// }
	//
	// public void testOverpay9999ApproveRecompenseWorkflow() throws Exception {
	// MemoryStore.get().clearRecompenseApplication();
	// printCurrentUsedTime("testOverpay9999ApproveRecompenseWorkflow: begin");
	// // long workflowId = createWorkFlow();
	//
	// RecompenseApplication app = new RecompenseApplication();
	// // app.setWorkflowId(workflowId);
	// app.setRealAmount(Double.valueOf(9999));
	// // app.setId("1000000001");
	// app.setWaybill(new Waybill());
	// app.setOperator(USER_TEST);
	// app.setRecompenseMethod(RecompenseApplication.NORMAL_TYPE);
	// RecompenseManager recompenseManager = RecompenseManager.get();
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CREATE_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 1, USER_TEST);
	//
	// // 理赔提交
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Ready", 1, 2, USER_TEST);
	// // 理赔资料确认===确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_DOCUMENT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Underway", 1, 3, USER_TEST);
	// // 理赔金额分配
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 4, USER_TEST);
	// // 经理金额确认
	// app.setOverpayAmount(Double.valueOf(100));
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OVERPAY_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 5, USER_TEST);
	// // 区长审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_REJECT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 6, USER_TEST);
	// // ==================================
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OVERPAY_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 7, USER_TEST);
	// // 区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 8, USER_TEST);
	// // 大区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_DIRECTOR_REJECT_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 9, USER_TEST);
	// // ==================================
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OVERPAY_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 10, USER_TEST);
	// // 区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 11, USER_TEST);
	// // 大区长审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_DIRECTOR_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Overpay", 1, 12, USER_TEST);
	// // 确认多赔
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OVERPAY_RECONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 13, USER_TEST);
	// // 收银提交
	// PaymentBill paymentBill = new PaymentBill();
	// paymentBill.setPaymentType("other");
	// app.setPaymentBill(paymentBill);
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CASHER_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 14, USER_TEST);
	// printCurrentUsedTime("finished");
	// }
	//
	// public void testExemptReconfirmRecompenseWorkflow() throws Exception {
	// MemoryStore.get().clearRecompenseApplication();
	// printCurrentUsedTime("testExemptReconfirmRecompenseWorkflow: begin");
	// // long workflowId = createWorkFlow();
	//
	// RecompenseApplication app = new RecompenseApplication();
	// // app.setWorkflow(workflow);
	// // app.setWorkflowId(workflowId);
	// app.setRealAmount(Double.valueOf(150000));
	// // app.setId("1000000001");
	// app.setWaybill(new Waybill());
	// app.setOperator(USER_TEST);
	// app.setRecompenseMethod(RecompenseApplication.NORMAL_TYPE);
	// RecompenseManager recompenseManager = RecompenseManager.get();
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CREATE_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 1, USER_TEST);
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Ready", 1, 2, USER_TEST);
	//
	// // 理赔资料确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_DOCUMENT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Underway", 1, 3, USER_TEST);
	// // 理赔金额分配
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 4, USER_TEST);
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Confirm", 1, 5, USER_TEST);
	// // 理赔确认金额
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_EXEMPT_ACTION, USER_TEST);
	// doGenernalAssert(app, 0, "Exempted", 1, 6, USER_TEST);
	// printCurrentUsedTime("finished");
	// }
	//
	// public void testExemptDocConfirmRecompenseWorkflow() throws Exception {
	// MemoryStore.get().clearRecompenseApplication();
	// printCurrentUsedTime("testExemptDocConfirmRecompenseWorkflow: begin");
	// // long workflowId = createWorkFlow();
	//
	// RecompenseApplication app = new RecompenseApplication();
	// // app.setWorkflowId(workflowId);
	// app.setRealAmount(Double.valueOf(150000));
	// // app.setId("1000000001");
	// app.setWaybill(new Waybill());
	// app.setOperator(USER_TEST);
	// app.setRecompenseMethod(RecompenseApplication.NORMAL_TYPE);
	// RecompenseManager recompenseManager = RecompenseManager.get();
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CREATE_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 1, USER_TEST);
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Ready", 1, 2, USER_TEST);
	//
	// // 理赔资料确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_DOCUMENT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Underway", 1, 3, USER_TEST);
	// // 理赔确认金额
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_EXEMPT_ACTION, USER_TEST);
	// doGenernalAssert(app, 0, "Exempted", 1, 4, USER_TEST);
	// printCurrentUsedTime("finished");
	// }
	//
	// public void testExemptSubmitRecompenseWorkflow() throws Exception {
	// MemoryStore.get().clearRecompenseApplication();
	// printCurrentUsedTime("testExemptSubmitRecompenseWorkflow: begin");
	// // long workflowId = createWorkFlow();
	//
	// RecompenseApplication app = new RecompenseApplication();
	// // app.setWorkflow(workflow);
	// // app.setWorkflowId(workflowId);
	// app.setRealAmount(Double.valueOf(150000));
	// // app.setId("1000000001");
	// app.setWaybill(new Waybill());
	// app.setOperator(USER_TEST);
	// app.setRecompenseMethod(RecompenseApplication.NORMAL_TYPE);
	// RecompenseManager recompenseManager = RecompenseManager.get();
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CREATE_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 1, USER_TEST);
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Ready", 1, 2, USER_TEST);
	//
	// // 理赔确认金额
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_EXEMPT_ACTION, USER_TEST);
	// doGenernalAssert(app, 0, "Exempted", 1, 3, USER_TEST);
	// printCurrentUsedTime("finished");
	// }
	//
	// public void testExemptOverpayRecompenseWorkflow() throws Exception {
	// MemoryStore.get().clearRecompenseApplication();
	// printCurrentUsedTime("testExemptOverpayRecompenseWorkflow: begin");
	// // long workflowId = createWorkFlow();
	//
	// RecompenseApplication app = new RecompenseApplication();
	// // app.setWorkflow(workflow);
	// // app.setWorkflowId(workflowId);
	// app.setRealAmount(Double.valueOf(4999));
	// // app.setId("1000000001");
	// app.setWaybill(new Waybill());
	// app.setOperator(USER_TEST);
	// app.setRecompenseMethod(RecompenseApplication.NORMAL_TYPE);
	//
	// RecompenseManager recompenseManager = RecompenseManager.get();
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CREATE_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 1, USER_TEST);
	//
	// // 理赔提交
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Ready", 1, 2, USER_TEST);
	// // 理赔资料确认===确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_DOCUMENT_CONFIRM_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Underway", 1, 3, USER_TEST);
	// // 理赔金额分配
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 4, USER_TEST);
	// // 经理金额确认
	// // performAction(app, Constants.RECOMPENSE_OVERPAY_ACTION,
	// // USER_TEST)
	// app.setOverpayAmount(Double.valueOf(100));
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OVERPAY_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 5, USER_TEST);
	// // 区长审批
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_REJECT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Confirm", 1, 6, USER_TEST);
	// // ==================================
	// // 经理金额确认
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_OVERPAY_ACTION, USER_TEST);
	// doGenernalAssert(app, 2, "Underway", 1, 7, USER_TEST);
	// // 区长审批
	// recompenseManager
	// .performAction(app,
	// Constants.RECOMPENSE_REGIONAL_MANAGER_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 2, "Overpay", 1, 8, USER_TEST);
	// // 确认多赔
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_EXEMPT_ACTION, USER_TEST);
	// doGenernalAssert(app, 0, "Exempted", 1, 9, USER_TEST);
	// printCurrentUsedTime("finished");
	// }
	//
	// public void testDeleteRecompenseWorkflow() throws Exception {
	// // MemoryStore.get().clearRecompenseApplication();
	// printCurrentUsedTime("testDeleteRecompenseWorkflow: begin");
	// RecompenseApplication app = TestUtils.createBase();
	// app.setRealAmount(Double.valueOf(150000));
	// app.setRecompenseMethod(RecompenseApplication.NORMAL_TYPE);
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 1, USER_TEST);
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REPORT_SAVE_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 2, USER_TEST);
	//
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_REPORT_DELETE_ACTION, USER_TEST);
	// doGenernalAssert(app, 0, "Deleted", 1, 3, USER_TEST);
	// printCurrentUsedTime("finished");
	// }

	// public void testQueryRecompenseWorkflow() throws Exception {
	// MemoryStore.get().clearRecompenseApplication();
	// printCurrentUsedTime("testQueryRecompenseWorkflow: begin");
	// RecompenseManager recompenseManager = RecompenseManager.get();
	// RecompenseApplication app = null;
	// List<RecompenseApplication> raList = null;
	// List wfList = null;
	//
	// // long workflowId = createWorkFlow();
	// app = new RecompenseApplication();
	// // app.setWorkflowId(workflowId);
	// app.setRealAmount(Double.valueOf(4999));
	// // app.setId(Long.toString(workflowId));
	// app.setWaybill(new Waybill());
	// app.setOperator(USER_TEST);
	// app.setRecompenseMethod(RecompenseApplication.NORMAL_TYPE);
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CREATE_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 1, USER_TEST);
	// // 理赔提交
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Ready", 1, 2, USER_TEST);
	// // raList = recompenseManager.getRecompenseApplicationByUser(USER_TEST);
	// // assertEquals(raList.size(), 1);
	// wfList = recompenseManager.getWorkFlowByOwner(USER_TEST);
	// // assertEquals(wfList.size(), 1);
	//
	// // workflowId = createWorkFlow();
	// app = new RecompenseApplication();
	// // app.setWorkflowId(workflowId);
	// app.setRealAmount(Double.valueOf(9999));
	// // app.setId(Long.toString(workflowId));
	// app.setWaybill(new Waybill());
	// app.setOperator(USER_TEST);
	// app.setRecompenseMethod(RecompenseApplication.NORMAL_TYPE);
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_CREATE_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 1, USER_TEST);
	// // 理赔提交
	// recompenseManager.performAction(app,
	// Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 4, "Ready", 1, 2, USER_TEST);
	// // 理赔资料确认
	// //
	// recompenseManager.performAction(app,Constants.RECOMPENSE_DOCUMENT_CONFIRM_ACTION,USER_TEST);
	// // doGenernalAssert(app, 3, "Underway", 1, 3, USER_TEST);
	// // raList = recompenseManager.getRecompenseApplicationByUser(USER_TEST);
	// // assertEquals(raList.size(), 2);
	// wfList = recompenseManager.getWorkFlowByOwner(USER_TEST);
	// // assertEquals(wfList.size(), 2);
	//
	// printCurrentUsedTime("finished");
	// }
	//
	// public void testOnlineRecompenseWorkflow() throws Exception {
	// MemoryStore.get().clearRecompenseApplication();
	// printCurrentUsedTime("testOnlineRecompenseWorkflow: begin");
	// // long workflowId = createWorkFlow();
	// RecompenseApplication app = new RecompenseApplication();
	// // app.setWorkflowId(workflowId);
	// app.setRealAmount(Double.valueOf(500));
	// // app.setId("1000000001");
	// app.setWaybill(new Waybill());
	// app.setOperator(USER_TEST);
	// app.setRecompenseMethod(RecompenseApplication.ONLINE_TYPE);
	// PaymentBill paymentBill = new PaymentBill();
	// paymentBill.setPaymentType("account");
	// app.setPaymentBill(paymentBill);
	// RecompenseManager rm = RecompenseManager.get();
	//
	// rm.performAction(app, Constants.RECOMPENSE_CREATE_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 1, USER_TEST);
	// rm.performAction(app, Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 2, USER_TEST);
	//
	// // 退回审批
	// rm.performAction(app, Constants.RECOMPENSE_ONLINE_REJECT_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 3, "Rejected", 1, 3, USER_TEST);
	// // 拒绝理赔
	// rm.performAction(app, Constants.RECOMPENSE_ONLINE_CANCEL_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 1, "Canceled", 1, 4, USER_TEST);
	// // 重启理赔
	// rm.performAction(app, Constants.RECOMPENSE_ONLINE_RELOAD_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 5, USER_TEST);
	// // 提交
	// rm.performAction(app, Constants.RECOMPENSE_SUBMIT_ACTION, USER_TEST);
	// doGenernalAssert(app, 3, "Underway", 1, 6, USER_TEST);
	// // 理赔提交
	// rm.performAction(app, Constants.RECOMPENSE_AMOUNT_SUBMIT_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 1, "Underway", 1, 7, USER_TEST);
	// // 付款
	// rm.performAction(app,
	// Constants.RECOMPENSE_REGIONAL_ACCOUNTANT_CONFIRM_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 1, "Paid", 1, 8, USER_TEST);
	// // 付款失败
	// rm.performAction(app, Constants.RECOMPENSE_FAILEDPAY_ACTION, USER_TEST);
	// doGenernalAssert(app, 1, "PayFailed", 1, 9, USER_TEST);
	// // 重新付款
	// rm.performAction(app, Constants.RECOMPENSE_ONLINE_REPAY_ACTION,
	// USER_TEST);
	// doGenernalAssert(app, 1, "Underway", 1, 10, USER_TEST);
	// printCurrentUsedTime("finished");
	// }
	//
	// // public void testCreateRecompenseWorkflow() throws Exception {
	// // MemoryStore.get().clearRecompenseApplication();
	// // printCurrentUsedTime("testCreateRecompenseWorkflow: begin");
	// // RecompenseManager recompenseManager = RecompenseManager.get();
	// // WorkflowManager workflowManager = WorkflowManager.get();
	// // long id=workflowManager.initRecompenseWorkFlow(USER_TEST);
	// //
	// // printCurrentUsedTime("finished");
	// // }
	//
	// @SuppressWarnings("rawtypes")
	// public long createWorkFlow() throws Exception {
	// String workflowName = getWorkflowName();
	// assertTrue("canInitialize for workflow " + workflowName + " is false",
	// workflow.canInitialize(workflowName, 100));
	//
	// long workflowId = workflow.initialize(workflowName, 100, new HashMap());
	// String workorderName = workflow.getWorkflowName(workflowId);
	// workflowDescriptor = workflow.getWorkflowDescriptor(workorderName);
	//
	// if (log.isDebugEnabled()) {
	// log.debug("Name of workorder:" + workorderName);
	// }
	//
	// List currentSteps = workflow.getCurrentSteps(workflowId);
	// assertEquals("Unexpected number of current steps", 1,
	// currentSteps.size());
	// assertEquals("Unexpected current step", 200,
	// ((Step) currentSteps.get(0)).getStepId());
	//
	// List historySteps = workflow.getHistorySteps(workflowId);
	// assertEquals("Unexpected number of history steps", 0,
	// historySteps.size());
	//
	// if (log.isDebugEnabled()) {
	// log.debug("Perform Finish First Draft");
	// }
	// printCurrentUsedTime("0");
	// return workflowId;
	// }

	@SuppressWarnings("rawtypes")
	public void doGenernalAssert(RecompenseApplication app, int actionsLength,
			String currentStatus, int currentStepsSize, int historyStepsSize,
			String caller) {
		Long workflowId = app.getWorkflowId();
		String workorderName = workflow.getWorkflowName(workflowId);
		workflowDescriptor = workflow.getWorkflowDescriptor(workorderName);
		List currentSteps = workflow.getCurrentSteps(workflowId);
		List historySteps = workflow.getHistorySteps(workflowId);
		int[] actions = workflow.getAvailableActions(workflowId,
				Collections.EMPTY_MAP);
		assertEquals(actionsLength, actions.length);
		assertEquals("Unexpected number of current steps", currentStepsSize,
				currentSteps.size());
		if (currentStepsSize > 0) {
			Step step = (Step) currentSteps.get(0);
			assertEquals(currentStatus, step.getStatus());
		}
		assertEquals("Unexpected number of history steps", historyStepsSize,
				historySteps.size());

		Step historyStep = (Step) historySteps.get(0);
		assertEquals(caller, historyStep.getCaller());
		assertNull(historyStep.getDueDate());

		// check system date, add in a 1 second fudgefactor.
		assertTrue("history step finish date " + historyStep.getFinishDate()
				+ " is in the future!",
				(historyStep.getFinishDate().getTime() - 1000) < System
						.currentTimeMillis());
		logActions(actions);

		if (log.isDebugEnabled()) {
			log.debug("Perform Finish Foo");
		}
		printCurrentUsedTime(Integer.toString(historyStepsSize));

	}

	public void printCurrentUsedTime(String step) {
		Date now = new Date();
		Long used = System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		DateFormat df = DateFormat.getDateTimeInstance();
		System.out.println(df.format(now) + " <<" + step + ">> Used:" + used);
	}
}
