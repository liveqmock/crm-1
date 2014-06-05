package com.deppon.crm.module.recompense.server.workflow;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.server.dao.impl.UserDao;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.impl.TodoWorkflowManager;
import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.recompense.server.util.TestDataCreator;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.Overpay;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.foss.framework.server.context.AppContext;
import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.loader.WorkflowDescriptor;
import com.opensymphony.workflow.spi.Step;

public class NormalRecompenseWorkflowTest extends TestCase {
	// ~ Constructors
	// ///////////////////////////////////////////////////////////
	// private static final String USER_TEST = "crm";
	private static final String MANAGER = "229492";
	private static final String ADMIN = "21935";
	private static final String CASH = "273139";
	private static final String OA_CLIENT = "26700";
	private static final String ERP_CLIENT = "221791";
	private static final String PAY_CLIENT = "234443";

	private static Workflow workflow;
	private static RecompenseManager recompenseManager;
	private static RecompenseService recompenseService;
	private static TodoWorkflowManager todoWorkflowManager;
	private static User manager, admin, cash, oaClient, balance, payClient;

	private Log log;
	private WorkflowDescriptor workflowDescriptor;
	private Long lastTime = System.currentTimeMillis();

	static {
		recompenseManager = TestUtil.recompenseManager;
		recompenseService = TestUtil.recompenseService;
		todoWorkflowManager = TestUtil.todoWorkflowManager;
		workflow = TestUtil.workflow;
		manager = TestUtil.manager;
		admin = TestUtil.admin;
		cash = TestUtil.cash;
		oaClient = TestUtil.oaClient;
		balance = TestUtil.balance;
		payClient = TestUtil.payClient;
	}

	public NormalRecompenseWorkflowTest(String s) {
		super(s);
		log = LogFactory.getLog(getClass());
	}

	protected void setUp() throws Exception {
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

	@Test
	public void test() {
		System.out.println("No testing");
	}

	public void xtestNormalPaymentRecompenseWorkflow() throws Exception {
		// MemoryStore.get().clearRecompenseApplication();
		printCurrentUsedTime("testNormalPaymentRecompenseWorkflow: begin");
		List<TodoWorkflow> todo = null;
		RecompenseApplication app = TestDataCreator.createReportApp();
		app.setOperator(MANAGER);
		app.setRealAmount(Double.valueOf(150000));
		app.setRecompenseMethod(Constants.NORMAL_TYPE);
		app.setStatus(Constants.STATUS_SUBMITED);

		// 理赔上报
		Map appMap = new HashMap();
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, manager);
		appMap.put(Constants.RECOMPENSE_ISSUEITEM_MAP, new HashMap());
		appMap.put(Constants.RECOMPENSE_GOODSTRANS_MAP, new HashMap());
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_SUBMIT_ACTION, MANAGER);
		doGenernalAssert(app, 2, "Submited", 1, 1, manager);
		doGenernalAssert(app, 2, "Submited", 1, 1, admin);
		doTodoAssert(app, manager, 1, "3", "Submited");
		doTodoAssert(app, admin, 1, "4", "Submited");
		// getTodoList("recompense", manager);
		// getTodoList(manager);
		// assertNotNull(todo);
		// assertEquals(manager, todo.getRole());
		// assertEquals("type", todo.getType());
		// assertEquals("status", todo.getStatus());
		//
		// todo = getTodoList(admin);
		// assertNotNull(todo);

		// 理赔保存
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, manager);
		// modify app
		app.setStatus(null);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_REPORT_SAVE_ACTION, MANAGER);
		doGenernalAssert(app, 2, "Submited", 1, 2, manager);
		doGenernalAssert(app, 2, "Submited", 1, 2, admin);
		doTodoAssert(app, manager, 1, "3", "Submited");
		doTodoAssert(app, admin, 1, "4", "Submited");

		// 资料确认
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, admin);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_DOC_CONFIRM_ACTION, ADMIN);
		doGenernalAssert(app, 2, "DocConfirmed", 1, 3, admin);
		doTodoAssert(app, admin, 1, "4", "DocConfirmed");

		// 取消资料确认
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, admin);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_DOCUMENT_REJECT_ACTION, ADMIN);
		doGenernalAssert(app, 2, "Submited", 1, 4, manager);
		doGenernalAssert(app, 2, "Submited", 1, 4, admin);
		doTodoAssert(app, manager, 1, "3", "Submited");
		doTodoAssert(app, admin, 1, "4", "Submited");

		// 理赔上报修改
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, manager);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_REPORT_SAVE_ACTION, MANAGER);
		doGenernalAssert(app, 2, "Submited", 1, 5, manager);
		doGenernalAssert(app, 2, "Submited", 1, 5, admin);
		doTodoAssert(app, manager, 1, "3", "Submited");
		doTodoAssert(app, admin, 1, "4", "Submited");

		// 资料确认
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, admin);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_DOC_CONFIRM_ACTION, ADMIN);
		doGenernalAssert(app, 2, "DocConfirmed", 1, 6, admin);
		doTodoAssert(app, admin, 1, "4", "DocConfirmed");

		// 理赔处理
		app = TestDataCreator.createDisposeApp(app);
		Map messageReminderMap = new HashMap();
		messageReminderMap.put(Constants.LIST_TYPE_ADD,
				app.getMessageReminderList());
		appMap.put(Constants.RECOMPENSE_MESSAGEREMINDER_MAP, messageReminderMap);
		recompenseManager.saveRecompenseProcessInfo(appMap);
		appMap.remove(Constants.RECOMPENSE_MESSAGEREMINDER_MAP);
		doGenernalAssert(app, 1, "DocConfirmed", 1, 6, admin);
		doGenernalAssert(app, 1, "DocConfirmed", 1, 6, manager);
		doTodoAssert(app, manager, 1, "3", "Handled");
		doTodoAssert(app, admin, 1, "4", "Handled");
		// 金额确认
		recompenseManager.confirmRecompenseAmountInfo(app.getId(), null);

		doGenernalAssert(app, 3, "DocConfirmed", 1, 6, admin);
		doGenernalAssert(app, 0, "DocConfirmed", 1, 6, manager);
		doTodoAssert(app, manager, 0, "3", "AmountConfirmed");
		doTodoAssert(app, admin, 1, "4", "AmountConfirmed");

		// 提交審批
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, admin);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_AMOUNT_CONFIRMED_SUBMIT_ACTION, ADMIN);
		doGenernalAssert(app, 2, "InOaApprove", 1, 7, oaClient);

		// 審批拒絕
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, oaClient);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.oaNormalRefuse("OA_TEST_"
				+ app.getWorkflowId().toString(), oaClient, "123", new Date());
		doGenernalAssert(app, 1, "Handled", 1, 8, admin);
		doGenernalAssert(app, 1, "Handled", 1, 8, manager);
		doTodoAssert(app, manager, 1, "3", "Handled");
		doTodoAssert(app, admin, 1, "4", "Handled");

		// 保存理赔处理
		recompenseManager.saveRecompenseProcessInfo(appMap);
		doTodoAssert(app, manager, 1, "3", "Handled");
		doTodoAssert(app, admin, 1, "4", "Handled");

		// 多賠提交
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, manager);
		Overpay overpay = new Overpay();
		Department dept = new Department();
		dept.setId("1");
		User deptAccount = new User();
		deptAccount.setId("1");
		// overpay.setOverpayAccountDeptId(dept);
		overpay.setDeptAccount(deptAccount);
		overpay.setRecompenseId(app.getId());
		overpay.setOverpayAmount(123d);
		overpay.setRecoverYszk(true);
		overpay.setTotalAmount(123d);
		overpay.setOverpayReason("1231");
		app.setOverpay(overpay);

		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_OVERPAY_SUBMIT_ACTION, MANAGER);
		doGenernalAssert(app, 2, "InOverpayApprove", 1, 9, oaClient);
		// 多賠拒絕
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, oaClient);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.oaOverpayRefuse("OVERPAY_TEST_"
				+ app.getWorkflowId().toString(), oaClient, "123", new Date());
		doGenernalAssert(app, 1, "Handled", 1, 10, admin);
		doGenernalAssert(app, 1, "Handled", 1, 10, manager);
		doTodoAssert(app, manager, 1, "3", "Handled");
		doTodoAssert(app, admin, 1, "4", "Handled");

		// 理赔处理
		recompenseManager.saveRecompenseProcessInfo(appMap);

		doGenernalAssert(app, 1, "Handled", 1, 10, admin);
		doGenernalAssert(app, 1, "Handled", 1, 10, manager);
		doTodoAssert(app, manager, 1, "3", "Handled");
		doTodoAssert(app, admin, 1, "4", "Handled");

		// 金额确认
		recompenseManager.confirmRecompenseAmountInfo(app.getId(), null);

		doGenernalAssert(app, 3, "Handled", 1, 10, admin);
		doGenernalAssert(app, 0, "Handled", 1, 10, manager);
		doTodoAssert(app, manager, 0, "3", "AmountConfirmed");
		doTodoAssert(app, admin, 1, "4", "AmountConfirmed");

		// 取消金额确认
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, admin);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_AMOUNT_CONFIRMED_REJECT_ACTION, ADMIN);
		doGenernalAssert(app, 1, "Handled", 1, 11, admin);
		doGenernalAssert(app, 1, "Handled", 1, 11, manager);
		doTodoAssert(app, manager, 1, "3", "Handled");
		doTodoAssert(app, admin, 1, "4", "Handled");

		// 取消理赔处理
		recompenseManager.cancelRecompenseProcessInfo(app.getId(), null);
		doGenernalAssert(app, 2, "Handled", 1, 11, admin);
		doTodoAssert(app, manager, 0, "3", "DocConfirmed");
		doTodoAssert(app, admin, 1, "4", "DocConfirmed");

		// 保存理赔处理
		recompenseManager.saveRecompenseProcessInfo(appMap);
		doGenernalAssert(app, 1, "Handled", 1, 11, admin);
		doGenernalAssert(app, 1, "Handled", 1, 11, manager);
		doTodoAssert(app, manager, 1, "3", "Handled");
		doTodoAssert(app, admin, 1, "4", "Handled");

		// 多賠提交
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, manager);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_OVERPAY_SUBMIT_ACTION, MANAGER);
		doGenernalAssert(app, 2, "InOverpayApprove", 1, 12, oaClient);
		// 多賠同意
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, oaClient);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.oaOverpayApprove("OVERPAY_TEST_"
				+ app.getWorkflowId().toString(), oaClient, "123", new Date());
		doGenernalAssert(app, 3, "OverpayApproved", 1, 13, admin);
		doGenernalAssert(app, 0, "OverpayApproved", 1, 13, manager);
		doTodoAssert(app, manager, 0, "3", "OverpayApproved");
		doTodoAssert(app, admin, 1, "4", "OverpayApproved");
		// 專員同意多賠
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, admin);
		app.setStatus(null);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_OVERPAY_CONFIRM_ACTION, ADMIN);
		doGenernalAssert(app, 1, "Approved", 1, 14, cash);
		doGenernalAssert(app, 1, "Approved", 1, 14, admin);
		doGenernalAssert(app, 0, "Approved", 1, 14, manager);
		doTodoAssert(app, manager, 0, "3", "Approved");
		doTodoAssert(app, admin, 1, "4", "Approved");
		doTodoAssert(app, cash, 1, "5", "Approved");
		// 專員免賠處理
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, admin);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_EXEMPT_ACTION, ADMIN);
		doGenernalAssert(app, 0, "Exempted", 0, 16, cash);
		doGenernalAssert(app, 0, "Exempted", 0, 16, admin);
		doGenernalAssert(app, 0, "Exempted", 0, 16, manager);
		doTodoAssert(app, manager, 0, "3", "Exempted");
		doTodoAssert(app, admin, 0, "4", "Exempted");
		doTodoAssert(app, cash, 0, "5", "Exempted");

		// //提交理赔
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_AMOUNT_CONFIRMED_SUBMIT_ACTION, ADMIN);
		// doGenernalAssert(app, 2, "InOaApprove", 1, 12, OA_CLIENT);
		//
		// //理赔批准
		// recompenseManager.oaNormalApprove(workflowId, oaClient, "", new
		// Date());
		// doGenernalAssert(app, 2, "Approved", 1, 13, CASH);
		// doGenernalAssert(app, 1, "Approved", 1, 13, ADMIN);
		//
		// //冲账
		// //app + 冲账金额
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_BALANCE_ACTION, CASH);
		// doGenernalAssert(app, 2, "InBalance", 1, 14, ERP_CLIENT);
		//
		// int rest = 100;//<0 为不够 ==0 刚好，>0 为有余额，需打款付给客户
		//
		// recompenseManager.erpConfirmBalance(workflowId, erpClient, rest, "",
		// new Date());
		// doGenernalAssert(app, 1, "Balanced", 1, 15, CASH);
		//
		// //费控
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_PAYMENT_ACTION, CASH);
		// doGenernalAssert(app, 2, "InPayment", 1, 16, PAYMENT_CLIENT);
		//
		// recompenseManager.erpConfirmPayment(workflowId, paymentClient, rest,
		// "", new Date());
		// doGenernalAssert(app, 1, "Paied", 1, 17, PAYMENT_CLIENT);
		//
		// recompenseManager.erpPayFailed(workflowId, paymentClient, rest, "",
		// new Date());
		// doGenernalAssert(app, 1, "PayFailed", 1, 18, CASH);
		//
		// recompenseManager.performAction(app,
		// Constants.RECOMPENSE_PAYMENT_ACTION, CASH);
		// doGenernalAssert(app, 2, "InPayment", 1, 19, PAYMENT_CLIENT);
		//
		// recompenseManager.erpPaySuccess(workflowId, paymentClient, rest, "",
		// new Date());
		// assert workflow finished
	}

	public void xtestNormalSubmitedExemptRecompenseWorkflow() throws Exception {
		printCurrentUsedTime("testNormalSubmitedExemptRecompenseWorkflow: begin");
		List<TodoWorkflow> todo = null;
		RecompenseApplication app = TestDataCreator.createReportApp();
		app.setOperator(MANAGER);
		app.setRealAmount(Double.valueOf(150000));
		app.setRecompenseMethod(Constants.NORMAL_TYPE);
		app.setStatus(Constants.STATUS_SUBMITED);

		Map appMap = new HashMap();
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, manager);
		appMap.put(Constants.RECOMPENSE_ISSUEITEM_MAP, new HashMap());
		appMap.put(Constants.RECOMPENSE_GOODSTRANS_MAP, new HashMap());
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_SUBMIT_ACTION, MANAGER);
		doGenernalAssert(app, 2, "Submited", 1, 1, manager);
		doGenernalAssert(app, 2, "Submited", 1, 1, admin);
		doTodoAssert(app, manager, 1, "3", "Submited");
		doTodoAssert(app, admin, 1, "4", "Submited");
		// 專員免賠處理
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, admin);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_EXEMPT_ACTION, ADMIN);
		doGenernalAssert(app, 0, "Exempted", 0, 3, cash);
		doGenernalAssert(app, 0, "Exempted", 0, 3, admin);
		doGenernalAssert(app, 0, "Exempted", 0, 3, manager);
		doTodoAssert(app, manager, 0, "3", "Exempted");
		doTodoAssert(app, admin, 0, "4", "Exempted");
		doTodoAssert(app, cash, 0, "5", "Exempted");
	}

	public void xtestNormalSubmitedDeleteRecompenseWorkflow() throws Exception {
		printCurrentUsedTime("testNormalSubmitedDeleteRecompenseWorkflow: begin");
		List<TodoWorkflow> todo = null;
		RecompenseApplication app = TestDataCreator.createReportApp();
		app.setOperator(MANAGER);
		app.setRealAmount(Double.valueOf(150000));
		app.setRecompenseMethod(Constants.NORMAL_TYPE);
		app.setStatus(Constants.STATUS_SUBMITED);

		Map appMap = new HashMap();
		appMap.put(Constants.RECOMPENSE_ISSUEITEM_MAP, new HashMap());
		appMap.put(Constants.RECOMPENSE_GOODSTRANS_MAP, new HashMap());
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, manager);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_SUBMIT_ACTION, MANAGER);
		doGenernalAssert(app, 2, "Submited", 1, 1, manager);
		doGenernalAssert(app, 2, "Submited", 1, 1, admin);
		doTodoAssert(app, manager, 1, "3", "Submited");
		doTodoAssert(app, admin, 1, "4", "Submited");

		// 專員免賠處理
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, manager);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_REPORT_DELETE_ACTION, MANAGER);
		doGenernalAssert(app, 0, "Deleted", 0, 3, cash);
		doGenernalAssert(app, 0, "Deleted", 0, 3, admin);
		doGenernalAssert(app, 0, "Deleted", 0, 3, manager);
		doTodoAssert(app, manager, 0, "3", "Deleted");
		doTodoAssert(app, admin, 0, "4", "Deleted");
		doTodoAssert(app, cash, 0, "5", "Deleted");
	}

	public void xtestNormalDocConfirmedExemptRecompenseWorkflow()
			throws Exception {
		printCurrentUsedTime("testNormalDocConfirmedExemptRecompenseWorkflow: begin");
		List<TodoWorkflow> todo = null;
		RecompenseApplication app = TestDataCreator.createReportApp();
		app.setOperator(MANAGER);
		app.setRealAmount(Double.valueOf(150000));
		app.setRecompenseMethod(Constants.NORMAL_TYPE);
		app.setStatus(Constants.STATUS_SUBMITED);

		Map appMap = new HashMap();
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, manager);
		appMap.put(Constants.RECOMPENSE_ISSUEITEM_MAP, new HashMap());
		appMap.put(Constants.RECOMPENSE_GOODSTRANS_MAP, new HashMap());
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_SUBMIT_ACTION, MANAGER);
		doGenernalAssert(app, 2, "Submited", 1, 1, manager);
		doGenernalAssert(app, 2, "Submited", 1, 1, admin);
		doTodoAssert(app, manager, 1, "3", "Submited");
		doTodoAssert(app, admin, 1, "4", "Submited");

		appMap.put(Constants.RECOMPENSE_CURRENT_USER, manager);
		// modify app
		app.setStatus(null);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_REPORT_SAVE_ACTION, MANAGER);
		doGenernalAssert(app, 2, "Submited", 1, 2, manager);
		doGenernalAssert(app, 2, "Submited", 1, 2, admin);
		doTodoAssert(app, manager, 1, "3", "Submited");
		doTodoAssert(app, admin, 1, "4", "Submited");

		appMap.put(Constants.RECOMPENSE_CURRENT_USER, admin);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_DOC_CONFIRM_ACTION, ADMIN);
		doGenernalAssert(app, 2, "DocConfirmed", 1, 3, admin);
		doTodoAssert(app, manager, 0, "3", "DocConfirmed");
		doTodoAssert(app, admin, 1, "4", "DocConfirmed");
		// 專員免賠處理
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, admin);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_EXEMPT_ACTION, ADMIN);
		doGenernalAssert(app, 0, "Exempted", 0, 5, cash);
		doGenernalAssert(app, 0, "Exempted", 0, 5, admin);
		doGenernalAssert(app, 0, "Exempted", 0, 5, manager);
		doTodoAssert(app, manager, 0, "3", "Exempted");
		doTodoAssert(app, admin, 0, "4", "Exempted");
		doTodoAssert(app, cash, 0, "5", "Exempted");
	}

	public void xtestNormal1000PaymentRecompenseWorkflow() throws Exception {
		// MemoryStore.get().clearRecompenseApplication();
		printCurrentUsedTime("testNormalPaymentRecompenseWorkflow: begin");
		List<TodoWorkflow> todo = null;
		RecompenseApplication app = TestDataCreator.createReportApp();
		app.setOperator(MANAGER);
		app.setRealAmount(Double.valueOf(1000));
		app.setRecompenseMethod(Constants.NORMAL_TYPE);
		app.setStatus(Constants.STATUS_SUBMITED);

		// 理赔上报
		Map appMap = new HashMap();
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, manager);
		appMap.put(Constants.RECOMPENSE_ISSUEITEM_MAP, new HashMap());
		appMap.put(Constants.RECOMPENSE_GOODSTRANS_MAP, new HashMap());
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_SUBMIT_ACTION, MANAGER);
		doGenernalAssert(app, 2, "Submited", 1, 1, manager);
		doGenernalAssert(app, 2, "Submited", 1, 1, admin);
		doTodoAssert(app, manager, 1, "3", "Submited");
		doTodoAssert(app, admin, 1, "4", "Submited");

		// 理赔保存
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, manager);
		// modify app
		app.setStatus(null);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_REPORT_SAVE_ACTION, MANAGER);
		doGenernalAssert(app, 2, "Submited", 1, 2, manager);
		doGenernalAssert(app, 2, "Submited", 1, 2, admin);
		doTodoAssert(app, manager, 1, "3", "Submited");
		doTodoAssert(app, admin, 1, "4", "Submited");

		// 资料确认
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, admin);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_DOC_CONFIRM_ACTION, ADMIN);
		doGenernalAssert(app, 2, "DocConfirmed", 1, 3, admin);
		doTodoAssert(app, admin, 1, "4", "DocConfirmed");

		// 取消资料确认
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, admin);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_DOCUMENT_REJECT_ACTION, ADMIN);
		doGenernalAssert(app, 2, "Submited", 1, 4, manager);
		doGenernalAssert(app, 2, "Submited", 1, 4, admin);
		doTodoAssert(app, manager, 1, "3", "Submited");
		doTodoAssert(app, admin, 1, "4", "Submited");

		// 理赔上报修改
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, manager);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_REPORT_SAVE_ACTION, MANAGER);
		doGenernalAssert(app, 2, "Submited", 1, 5, manager);
		doGenernalAssert(app, 2, "Submited", 1, 5, admin);
		doTodoAssert(app, manager, 1, "3", "Submited");
		doTodoAssert(app, admin, 1, "4", "Submited");

		// 资料确认
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, admin);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_DOC_CONFIRM_ACTION, ADMIN);
		doGenernalAssert(app, 2, "DocConfirmed", 1, 6, admin);
		doTodoAssert(app, admin, 1, "4", "DocConfirmed");

		// 理赔处理
		app = TestDataCreator.createDisposeApp(app);
		Map messageReminderMap = new HashMap();
		messageReminderMap.put(Constants.LIST_TYPE_ADD,
				app.getMessageReminderList());
		appMap.put(Constants.RECOMPENSE_MESSAGEREMINDER_MAP, messageReminderMap);
		recompenseManager.saveRecompenseProcessInfo(appMap);
		appMap.remove(Constants.RECOMPENSE_MESSAGEREMINDER_MAP);
		doGenernalAssert(app, 1, "DocConfirmed", 1, 6, admin);
		doGenernalAssert(app, 1, "DocConfirmed", 1, 6, manager);
		doTodoAssert(app, manager, 1, "3", "Handled");
		doTodoAssert(app, admin, 1, "4", "Handled");
		// 金额确认
		recompenseManager.confirmRecompenseAmountInfo(app.getId(), null);

		doGenernalAssert(app, 3, "DocConfirmed", 1, 6, admin);
		doGenernalAssert(app, 0, "DocConfirmed", 1, 6, manager);
		doTodoAssert(app, manager, 0, "3", "AmountConfirmed");
		doTodoAssert(app, admin, 1, "4", "AmountConfirmed");

		// 提交審批
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, admin);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_AMOUNT_CONFIRMED_SUBMIT_ACTION, ADMIN);
		doGenernalAssert(app, 1, "Approved", 1, 7, cash);
		doGenernalAssert(app, 1, "Approved", 1, 7, admin);
		doGenernalAssert(app, 0, "Approved", 1, 7, manager);

	}

	@SuppressWarnings("rawtypes")
	public void doGenernalAssert(RecompenseApplication app, int actionsLength,
			String currentStatus, int currentStepsSize, int historyStepsSize,
			User user) {
		// User user = userDao.getById(caller);
		Map appMap = new HashMap();
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, user);
		Long workflowId = app.getWorkflowId();
		String workorderName = workflow.getWorkflowName(workflowId);
		workflowDescriptor = workflow.getWorkflowDescriptor(workorderName);
		List currentSteps = workflow.getCurrentSteps(workflowId);
		List historySteps = workflow.getHistorySteps(workflowId);
		int[] actions = workflow.getAvailableActions(workflowId, appMap);
		System.out.print("---------Action IDs: ");
		for (int a : actions) {
			System.out.print(a + ",");
		}
		System.out.println();
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
		// assertEquals(caller, historyStep.getCaller());
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

	@SuppressWarnings("rawtypes")
	public void doTodoAssert(RecompenseApplication app, User user, int qty,
			String roleId, String status) {
		String workflowName = "recompense";
		List<TodoWorkflow> todo = todoWorkflowManager
				.getTodoWorkflowByWorkflowId(workflowName, app.getWorkflowId()
						.toString(), recompenseManager.getUserRoleId(user),
						recompenseManager.getUserDeptIds(user));
		assertNotNull(todo);
		assertEquals(todo.size(), qty);
		if (qty > 0) {
			assertEquals(roleId, todo.get(0).getRoleId());
			assertEquals(workflowName, todo.get(0).getWorkflowName());
			assertEquals(status, todo.get(0).getApplicationStatus());
		}
	}

	public void printCurrentUsedTime(String step) {
		Date now = new Date();
		Long used = System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		DateFormat df = DateFormat.getDateTimeInstance();
		System.out.println("==================================>>> "
				+ df.format(now) + " <<" + step + ">> Used:" + used);
	}
}
