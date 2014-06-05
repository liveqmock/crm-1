//package com.deppon.crm.module.recompense.server.workflow;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import junit.framework.TestCase;
//
//import org.junit.Test;
//
//import com.deppon.crm.module.authorization.shared.domain.User;
//import com.deppon.crm.module.frameworkimpl.server.cache.UserCacheProvider;
//import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
//import com.deppon.crm.module.recompense.server.service.RecompenseService;
//import com.deppon.crm.module.recompense.server.util.TestDataCreator;
//import com.deppon.crm.module.recompense.server.util.TestUtil;
//import com.deppon.crm.module.recompense.server.utils.Constants;
//import com.deppon.crm.module.recompense.shared.domain.AwardItem;
//import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
//import com.deppon.crm.module.recompense.shared.domain.GoodsTrans;
//import com.deppon.crm.module.recompense.shared.domain.IssueItem;
//import com.deppon.crm.module.recompense.shared.domain.MessageReminder;
//import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
//import com.deppon.crm.module.recompense.shared.domain.RecompenseAttachment;
//import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
//import com.deppon.crm.module.recompense.shared.domain.Waybill;
//import com.opensymphony.module.propertyset.PropertySet;
//import com.opensymphony.module.propertyset.memory.MemoryPropertySet;
//import com.opensymphony.workflow.FunctionProvider;
//import com.opensymphony.workflow.WorkflowContext;
//import com.opensymphony.workflow.WorkflowException;
//import com.opensymphony.workflow.basic.BasicWorkflowContext;
//import com.opensymphony.workflow.spi.SimpleWorkflowEntry;
//import com.opensymphony.workflow.spi.WorkflowEntry;
//
//public class RecompenseFunctionTest extends TestCase {
//	protected static FunctionProvider createRecompenseFunction;
//	protected static FunctionProvider saveRecompenseReportFunction;
//	protected static FunctionProvider saveRecompenseProcessFunction;
//	protected static FunctionProvider exemptRecompenseFunction;
//	protected static FunctionProvider updateRecompenseStatusFunction;
//	protected static FunctionProvider deleteRecompenseFunction;
//
//	protected static RecompenseManager recompenseManager;
//	protected static RecompenseService recompenseService;
//	private static UserCacheProvider userCacheProvider;
//
//	private static User user;
//	private static RecompenseApplication app;
//	private static PropertySet ps;
//	static {
//		createRecompenseFunction = TestUtil.createRecompenseFunction;
//		saveRecompenseReportFunction = TestUtil.saveRecompenseReportFunction;
//		saveRecompenseProcessFunction = TestUtil.saveRecompenseProcessFunction;
//		exemptRecompenseFunction = TestUtil.exemptRecompenseFunction;
//		updateRecompenseStatusFunction = TestUtil.updateRecompenseStatusFunction;
//		deleteRecompenseFunction = TestUtil.deleteRecompenseFunction;
//		recompenseManager = TestUtil.recompenseManager;
//		recompenseService = TestUtil.recompenseService;
//		userCacheProvider = TestUtil.userCacheProvider;
//		user = (User) userCacheProvider.get("21935");
//
//	}
//
//	protected void setUp() throws Exception {
//		app = TestDataCreator.createReportApp();
//		Date date = new Date();
//		Long voucherNo = date.getTime();
//		Waybill waybill = app.getWaybill();
//		waybill.setWaybillNumber(voucherNo.toString());
//		app.setWorkflowId(voucherNo);
//		app.setWaybill(waybill);
//		ps = new MemoryPropertySet();
//		ps.init(null, null);
//	}
//
//	protected void tearDown() throws Exception {
//
//	}
//
//	@Test
//	public void testFailure() {
//		// fail("testFailure");
//	}
//
//	@Test
//	public void testSuccess() {
//		System.out.println("testSuccess");
//	}
//
//	@Test
//	public void testCreateRecompenseFunctionExecute() throws WorkflowException {
//		createRecompenseFunctionExecute();
//		System.out.println("testCreateRecompenseFunctionExecute");
//	}
//
//	public void createRecompenseFunctionExecute() throws WorkflowException {
//		Map map = new HashMap();
//		map.put(Constants.RECOMPENSE_APPLICATION, app);
//		map.put(Constants.RECOMPENSE_CURRENT_USER, user);
//		PropertySet ps = new MemoryPropertySet();
//		ps.init(null, null);
//		WorkflowContext context = new BasicWorkflowContext("crm");
//		// 无数据异常
//		try {
//			createRecompenseFunction.execute(null, null, ps);
//			fail("末抛出异常");
//		} catch (Exception e) {
//			assertTrue(true);
//		}
//
//		// 有数据操作
//		createRecompenseFunction.execute(map, null, ps);
//		assertTrue(true);
//		// 查询校验
//		app = recompenseService.getRecompenseApplicationByVoucherNo(app
//				.getWaybill().getWaybillNumber());
//		app = recompenseService.getRecompenseApplicationById(app.getId());
//		assertNotNull(app.getId());
//	}
//
//	@Test
//	public void testSaveRecompenseReportFunctionExecute()
//			throws WorkflowException {
//		createRecompenseFunctionExecute();
//		saveRecompenseReportFunctionExecute();
//		System.out.println("testSaveRecompenseReportFunctionExecute");
//	}
//
//	public void saveRecompenseReportFunctionExecute() throws WorkflowException {
//		Map map = new HashMap();
//		map.put(Constants.RECOMPENSE_APPLICATION, app);
//		map.put(Constants.RECOMPENSE_CURRENT_USER, user);
//
//		Map attachmentMap = new HashMap();
//		attachmentMap.put(Constants.LIST_TYPE_ADD,
//				TestDataCreator.createRecompenseAttachmentList());
//		List<RecompenseAttachment> ad = new ArrayList<RecompenseAttachment>();
//		ad.add(app.getAttachmentList().get(0));
//		app.getAttachmentList().remove(0);
//		attachmentMap.put(Constants.LIST_TYPE_DELETE, ad);
//		attachmentMap.put(Constants.LIST_TYPE_UPDATE, app.getAttachmentList());
//		Map issueItemMap = new HashMap();
//		issueItemMap.put(Constants.LIST_TYPE_ADD,
//				TestDataCreator.createIssueItemList());
//		List<IssueItem> id = new ArrayList<IssueItem>();
//		id.add(app.getIssueItemList().get(0));
//		app.getIssueItemList().remove(0);
//		issueItemMap.put(Constants.LIST_TYPE_DELETE, id);
//		issueItemMap.put(Constants.LIST_TYPE_UPDATE, app.getIssueItemList());
//		Map goodsTransMap = new HashMap();
//		goodsTransMap.put(Constants.LIST_TYPE_ADD,
//				TestDataCreator.createGoodsTransList());
//		List<GoodsTrans> gd = new ArrayList<GoodsTrans>();
//		gd.add(app.getGoodsTransList().get(0));
//		app.getGoodsTransList().remove(0);
//		goodsTransMap.put(Constants.LIST_TYPE_DELETE, gd);
//		goodsTransMap.put(Constants.LIST_TYPE_UPDATE, app.getGoodsTransList());
//		map.put(Constants.RECOMPENSE_ATTACHMENT_MAP, attachmentMap);
//		map.put(Constants.RECOMPENSE_ISSUEITEM_MAP, issueItemMap);
//		map.put(Constants.RECOMPENSE_GOODSTRANS_MAP, goodsTransMap);
//		saveRecompenseReportFunction.execute(map, null, ps);
//		app = recompenseService.getRecompenseApplicationById(app.getId());
//	}
//
//	@Test
//	public void testSaveRecompenseProcessFunctionExecute()
//			throws WorkflowException {
//		createRecompenseFunctionExecute();
//		saveRecompenseReportFunctionExecute();
//		saveRecompenseProcessFunctionExecute();
//		System.out.println("testSaveRecompenseProcessFunctionExecute");
//	}
//
//	public void saveRecompenseProcessFunctionExecute() throws WorkflowException {
//		Map map = new HashMap();
//		map.put(Constants.RECOMPENSE_APPLICATION, app);
//		map.put(Constants.RECOMPENSE_CURRENT_USER, user);
//
//		Map deptChargeMap = new HashMap();
//		deptChargeMap.put(Constants.LIST_TYPE_ADD,
//				TestDataCreator.createDeptChargeList());
//		deptChargeMap.put(Constants.LIST_TYPE_DELETE,
//				new ArrayList<DeptCharge>());
//		deptChargeMap.put(Constants.LIST_TYPE_UPDATE,
//				new ArrayList<DeptCharge>());
//		Map responsibleDeptMap = new HashMap();
//		responsibleDeptMap.put(Constants.LIST_TYPE_ADD,
//				TestDataCreator.createResponsibleDeptList());
//		responsibleDeptMap.put(Constants.LIST_TYPE_DELETE,
//				new ArrayList<ResponsibleDept>());
//		responsibleDeptMap.put(Constants.LIST_TYPE_UPDATE,
//				new ArrayList<ResponsibleDept>());
//		Map messageReminderMap = new HashMap();
//		messageReminderMap.put(Constants.LIST_TYPE_ADD,
//				TestDataCreator.createMessageReminderList());
//		messageReminderMap.put(Constants.LIST_TYPE_DELETE,
//				new ArrayList<MessageReminder>());
//		messageReminderMap.put(Constants.LIST_TYPE_UPDATE,
//				new ArrayList<MessageReminder>());
//		Map awardItemMap = new HashMap();
//		awardItemMap.put(Constants.LIST_TYPE_ADD,
//				TestDataCreator.createAwardItemList());
//		awardItemMap
//				.put(Constants.LIST_TYPE_DELETE, new ArrayList<AwardItem>());
//		awardItemMap
//				.put(Constants.LIST_TYPE_UPDATE, new ArrayList<AwardItem>());
//		map.put(Constants.RECOMPENSE_DEPTCHARGE_MAP, deptChargeMap);
//		map.put(Constants.RECOMPENSE_RESPONSIBLEDEPT_MAP, responsibleDeptMap);
//		map.put(Constants.RECOMPENSE_MESSAGEREMINDER_MAP, messageReminderMap);
//		map.put(Constants.RECOMPENSE_AWARDITEM_MAP, awardItemMap);
//		saveRecompenseProcessFunction.execute(map, null, ps);
//		app = recompenseService.getRecompenseApplicationById(app.getId());
//
//		deptChargeMap = new HashMap();
//		List<DeptCharge> dcl = new ArrayList<DeptCharge>();
//		dcl.add(app.getDeptChargeList().get(0));
//		app.getDeptChargeList().remove(0);
//		deptChargeMap.put(Constants.LIST_TYPE_ADD,
//				TestDataCreator.createDeptChargeList());
//		deptChargeMap.put(Constants.LIST_TYPE_DELETE, dcl);
//		deptChargeMap.put(Constants.LIST_TYPE_UPDATE, app.getDeptChargeList());
//		responsibleDeptMap = new HashMap();
//		List<ResponsibleDept> rdl = new ArrayList<ResponsibleDept>();
//		rdl.add(app.getResponsibleDeptList().get(0));
//		app.getResponsibleDeptList().remove(0);
//		responsibleDeptMap.put(Constants.LIST_TYPE_ADD,
//				TestDataCreator.createResponsibleDeptList());
//		responsibleDeptMap.put(Constants.LIST_TYPE_DELETE, rdl);
//		responsibleDeptMap.put(Constants.LIST_TYPE_UPDATE,
//				app.getResponsibleDeptList());
//		messageReminderMap = new HashMap();
//		List<MessageReminder> mrl = new ArrayList<MessageReminder>();
//		mrl.add(app.getMessageReminderList().get(0));
//		app.getMessageReminderList().remove(0);
//		messageReminderMap.put(Constants.LIST_TYPE_ADD,
//				TestDataCreator.createMessageReminderList());
//		messageReminderMap.put(Constants.LIST_TYPE_DELETE, mrl);
//		messageReminderMap.put(Constants.LIST_TYPE_UPDATE,
//				app.getMessageReminderList());
//		awardItemMap = new HashMap();
//		List<AwardItem> dil = new ArrayList<AwardItem>();
//		dil.add(app.getAwardItemList().get(0));
//		app.getAwardItemList().remove(0);
//		awardItemMap.put(Constants.LIST_TYPE_ADD,
//				TestDataCreator.createAwardItemList());
//		awardItemMap.put(Constants.LIST_TYPE_DELETE, dil);
//		awardItemMap.put(Constants.LIST_TYPE_UPDATE, app.getAwardItemList());
//		map.put(Constants.RECOMPENSE_DEPTCHARGE_MAP, deptChargeMap);
//		map.put(Constants.RECOMPENSE_RESPONSIBLEDEPT_MAP, responsibleDeptMap);
//		map.put(Constants.RECOMPENSE_MESSAGEREMINDER_MAP, messageReminderMap);
//		map.put(Constants.RECOMPENSE_AWARDITEM_MAP, awardItemMap);
//		saveRecompenseProcessFunction.execute(map, null, ps);
//		app = recompenseService.getRecompenseApplicationById(app.getId());
//	}
//
//	@Test
//	public void testExemptRecompenseFunctionExecute() throws WorkflowException {
//		createRecompenseFunctionExecute();
//		saveRecompenseReportFunctionExecute();
//		saveRecompenseProcessFunctionExecute();
//		exemptRecompenseFunctionExecute();
//		System.out.println("testExemptRecompenseFunctionExecute");
//	}
//
//	public void exemptRecompenseFunctionExecute() throws WorkflowException {
//		Map map = new HashMap();
//		map.put(Constants.RECOMPENSE_APPLICATION, app);
//		map.put(Constants.RECOMPENSE_CURRENT_USER, user);
//		exemptRecompenseFunction.execute(map, null, ps);
//		app = recompenseService.getRecompenseApplicationById(app.getId());
//	}
//
//	@Test
//	public void testUpdateRecompenseStatusFunctionExecute()
//			throws WorkflowException {
//		createRecompenseFunctionExecute();
//		saveRecompenseReportFunctionExecute();
//		saveRecompenseProcessFunctionExecute();
//		exemptRecompenseFunctionExecute();
//		updateRecompenseStatusFunctionExecute();
//		System.out.println("testUpdateRecompenseStatusFunctionExecute");
//	}
//
//	public void updateRecompenseStatusFunctionExecute()
//			throws WorkflowException {
//		Map map = new HashMap();
//		map.put(Constants.RECOMPENSE_APPLICATION, app);
//		map.put(Constants.RECOMPENSE_CURRENT_USER, user);
//		WorkflowEntry entry = new SimpleWorkflowEntry((app.getWorkflowId()),
//				"recompense", 1);
//		map.put("entry", entry);
//		Map args = new HashMap<String, String>();
//		args.put("recompenseStatus", "recompenseStatus");
//		updateRecompenseStatusFunction.execute(map, args, ps);
//		app = recompenseService.getRecompenseApplicationById(app.getId());
//	}
//
//	@Test
//	public void testDeleteRecompenseFunctionExecute() throws WorkflowException {
//		createRecompenseFunctionExecute();
//		saveRecompenseReportFunctionExecute();
//		saveRecompenseProcessFunctionExecute();
//		exemptRecompenseFunctionExecute();
//		updateRecompenseStatusFunctionExecute();
//		deleteRecompenseFunctionExecute();
//		System.out.println("testDeleteRecompenseFunctionExecute");
//	}
//
//	public void deleteRecompenseFunctionExecute() throws WorkflowException {
//		Map map = new HashMap();
//		map.put(Constants.RECOMPENSE_APPLICATION, app);
//		map.put(Constants.RECOMPENSE_CURRENT_USER, user);
//		deleteRecompenseFunction.execute(map, null, ps);
//		app = recompenseService.getRecompenseApplicationById(app.getId());
//	}
//
//}
