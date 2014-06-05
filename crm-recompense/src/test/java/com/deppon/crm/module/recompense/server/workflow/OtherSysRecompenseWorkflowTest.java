package com.deppon.crm.module.recompense.server.workflow;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.authorization.server.dao.impl.UserDao;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.impl.TodoWorkflowManager;
import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.loader.WorkflowDescriptor;
import com.opensymphony.workflow.spi.Step;

public class OtherSysRecompenseWorkflowTest extends TestCase {
	// ~ Constructors
	// ///////////////////////////////////////////////////////////
	// private static final String USER_TEST = "crm";
	private static final String MANAGER = "229492";
	private static final String ADMIN = "21935";
	private static final String CASH = "273139";
	private static final String OA_CLIENT = "26700";
	private static final String BALANCE = "221791";
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

	public OtherSysRecompenseWorkflowTest(String s) {
		super(s);
		log = LogFactory.getLog(getClass());
	}

	public void tester() {
		System.out.println("OK");
	}

	public void xtestOaNormalApprove() throws Exception {
		// 正常审批同意
		Long workflowId = 3010L;
		String workflowNum = "OA_TEST_" + workflowId.toString();
		recompenseManager.oaNormalApprove(workflowNum, oaClient, "123",
				new Date());
		doTodoAssert(workflowId, manager, 1, "3", "Handled");
		doTodoAssert(workflowId, admin, 1, "4", "Handled");
	}

	public void xtestOaNormalRefuse() throws Exception {
		// 正常审批拒绝
		Long workflowId = 3010L;
		String workflowNum = "OA_TEST_" + workflowId.toString();
		recompenseManager.oaNormalRefuse(workflowNum, oaClient, "123",
				new Date());
		doTodoAssert(workflowId, manager, 1, "3", "Handled");
		doTodoAssert(workflowId, admin, 1, "4", "Handled");
	}

	public void xtestOaOverpayApprove() throws Exception {
		// 多赔审批同意
		Long workflowId = 65160L;
		String workflowNum = "OVERPAY_TEST_" + workflowId.toString();
		recompenseManager.oaOverpayApprove(workflowNum, oaClient, "123",
				new Date());
		doTodoAssert(workflowId, manager, 1, "3", "Handled");
		doTodoAssert(workflowId, admin, 1, "4", "Handled");
	}

	public void xtestOaOverpayRefuse() throws Exception {
		// 多赔审批拒绝
		Long workflowId = 3010L;
		String workflowNum = "OVERPAY_TEST_" + workflowId.toString();
		recompenseManager.oaOverpayRefuse(workflowNum, oaClient, "123",
				new Date());
		doTodoAssert(workflowId, manager, 1, "3", "Handled");
		doTodoAssert(workflowId, admin, 1, "4", "Handled");
	}

	public void testErpPaymentRefuse() {
		// recompenseManager.erpPaymentRefuse("28663947");
	}

	public void testErpPaymentApprove() {
		// recompenseManager.erpPaymentApprove("28663947");
	}

	public void testRepayOnlineApply() {
		// RecompenseApplication app = recompenseManager
		// .getRecompenseApplicationByVoucherNo("28663947");
		// OnlineApply onlineApply = recompenseManager
		// .getOnlineApplyByRecompenseId(app.getId());
		// recompenseManager.repayOnlineApply(onlineApply);
	}

	// public void xtestPayPaymentRefuse() throws Exception {
	// // 付款拒絕
	// Long workflowId = 5240L;
	// String workflowNum = "PAYMENT_TEST_" + workflowId.toString();
	// recompenseManager.payPaymentRefuse(workflowNum, payClient, "1234",
	// new Date());
	// doTodoAssert(workflowId, admin, 1, "4", "PayFailed");
	// doTodoAssert(workflowId, cash, 1, "5", "PayFailed");
	// }
	//
	// public void xtestPayPaymentApprove() throws Exception {
	// // 付款同意
	// Long workflowId = 11060L;
	// String workflowNum = "PAYMENT_TEST_" + workflowId.toString();
	// recompenseManager.payPaymentApprove(workflowNum, payClient, "1234",
	// new Date());
	// doTodoAssert(workflowId, admin, 1, "4", "PayFailed");
	// doTodoAssert(workflowId, cash, 1, "5", "PayFailed");
	// }
	//
	// public void xtestPayPaymentFailed() throws Exception {
	// // 汇款失败
	// Long workflowId = 5240L;
	// String workflowNum = "PAYMENT_TEST_" + workflowId.toString();
	// recompenseManager.payPaymentFailed(workflowNum, payClient, "1234",
	// new Date());
	// doTodoAssert(workflowId, admin, 1, "4", "PayFailed");
	// doTodoAssert(workflowId, cash, 1, "5", "PayFailed");
	// }
	//
	// public void xtestOnlinePayPaymentFailed() throws Exception {
	// // 汇款失败
	// Long workflowId = 11060L;
	// String workflowNum = "PAYMENT_TEST_" + workflowId.toString();
	// recompenseManager.payPaymentFailed(workflowNum, payClient, "1234",
	// new Date());
	// doTodoAssert(workflowId, admin, 0, "4", "OnlinePayFailed");
	// doTodoAssert(workflowId, cash, 0, "5", "OnlinePayFailed");
	// }

	public void xtestGetOverpayByWorkflowNum() {
		recompenseService.getOverpayByWorkflowNum("OVERPAY_TEST_7860", "3177");
	}

	@SuppressWarnings("rawtypes")
	public void doGenernalAssert(Long workflowId, int actionsLength,
			String currentStatus, int currentStepsSize, int historyStepsSize,
			User user) {
		// User user = userDao.getById(caller);
		Map appMap = new HashMap();
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, user);
		// Long workflowId = app.getWorkflowId();
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

		// Step historyStep = (Step) historySteps.get(0);
		// assertEquals(caller, historyStep.getCaller());
		// assertNull(historyStep.getDueDate());

		// check system date, add in a 1 second fudgefactor.
		// assertTrue("history step finish date " + historyStep.getFinishDate()
		// + " is in the future!",
		// (historyStep.getFinishDate().getTime() - 1000) < System
		// .currentTimeMillis());
		logActions(actions);

		if (log.isDebugEnabled()) {
			log.debug("Perform Finish Foo");
		}
		printCurrentUsedTime(Integer.toString(historyStepsSize));

	}

	@SuppressWarnings("rawtypes")
	public void doTodoAssert(Long workflowId, User user, int qty,
			String roleId, String status) {
		String workflowName = "recompense";
		List<TodoWorkflow> todo = todoWorkflowManager
				.getTodoWorkflowByWorkflowId(workflowName,
						workflowId.toString(),
						recompenseManager.getUserRoleId(user),
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

	protected void logActions(int[] actions) {
		for (int i = 0; i < actions.length; i++) {
			String name = workflowDescriptor.getAction(actions[i]).getName();
			int actionId = workflowDescriptor.getAction(actions[i]).getId();

			if (log.isDebugEnabled()) {
				log.debug("Actions Available: " + name + " id:" + actionId);
			}
		}
	}

}
