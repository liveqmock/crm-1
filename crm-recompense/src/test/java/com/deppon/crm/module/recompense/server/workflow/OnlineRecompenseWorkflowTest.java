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
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.recompense.server.util.TestDataCreator;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.OnlineApply;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.foss.framework.server.context.AppContext;
import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.loader.WorkflowDescriptor;
import com.opensymphony.workflow.spi.Step;

public class OnlineRecompenseWorkflowTest extends TestCase {
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

	public OnlineRecompenseWorkflowTest(String s) {
		super(s);
		log = LogFactory.getLog(getClass());
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

	public void xtestOnlineRecompenseWorkflow() throws Exception {//
		printCurrentUsedTime("testOnlineRecompenseWorkflow: begin");
		String onlineApplyId = "85";
		RecompenseApplication app = TestDataCreator.createBase();
		app.setOperator(MANAGER);
		app.setRealAmount(Double.valueOf(500));
		app.setRecompenseMethod(Constants.ONLINE_TYPE);
		app.setOnlineApplyId(onlineApplyId);
		app.setStatus(Constants.STATUS_SUBMITED);
		app.setReportMan(manager.getId());

		Map appMap = new HashMap();
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, manager);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_SUBMIT_ACTION, MANAGER);
		doGenernalAssert(app, 2, "Submited", 1, 1, admin);
		doGenernalAssert(app, 2, "Submited", 1, 1, manager);
		doTodoAssert(app, manager, 1, "3", "Submited");
		doTodoAssert(app, admin, 1, "4", "Submited");

		// 專員在線理賠拒絕
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, admin);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_ONLINE_REJECT_ACTION, ADMIN);
		doGenernalAssert(app, 0, "OnlineRefused", 1, 2, admin);
		doGenernalAssert(app, 2, "OnlineRefused", 1, 2, manager);
		doTodoAssert(app, manager, 1, "3", "OnlineRefused");
		doTodoAssert(app, admin, 0, "4", "OnlineRefused");
		// 修改
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, manager);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_REPORT_SAVE_ACTION, MANAGER);
		doGenernalAssert(app, 2, "Submited", 1, 3, admin);
		doGenernalAssert(app, 2, "Submited", 1, 3, manager);
		doTodoAssert(app, manager, 1, "3", "Submited");
		doTodoAssert(app, admin, 1, "4", "Submited");
		// 專員在線理賠同意
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, admin);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_ONLINE_CONFIRM_ACTION, ADMIN);
		doGenernalAssert(app, 2, "InPayment", 1, 4, payClient);

		// 付款拒絕
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, payClient);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_PAYMENT_REJECT_ACTION, PAY_CLIENT);
		doGenernalAssert(app, 1, "OnlinePayFailed", 1, 5, manager);
		// doTodoAssert(app, manager, 1, "3", "OnlinePayFailed");
		// 重新付款提交
		// appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		// appMap.put(Constants.RECOMPENSE_CURRENT_USER, manager);
		// recompenseManager.performAction(appMap,
		// Constants.RECOMPENSE_ONLINE_REPAY_ACTION, MANAGER);
		OnlineApply onlineApply = recompenseManager
				.getOnlineApplyById(onlineApplyId);
		recompenseManager.repayOnlineApply(onlineApply);
		doGenernalAssert(app, 2, "InPayment", 1, 6, payClient);
		// 付款同意
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, payClient);
		recompenseManager.performAction(appMap,
				Constants.RECOMPENSE_PAYMENT_CONFIRM_ACTION, PAY_CLIENT);
		doGenernalAssert(app, 1, "Paid", 1, 7, payClient);
		// 付款失敗
		// appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		// appMap.put(Constants.RECOMPENSE_CURRENT_USER, payClient);
		// recompenseManager.performAction(appMap,
		// Constants.RECOMPENSE_PAID_PAYFAILED_ACTION, PAY_CLIENT);
		// doGenernalAssert(app, 1, "OnlinePayFailed", 1, 8, manager);
		// doTodoAssert(app, manager, 1, "3", "OnlinePayFailed");

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
