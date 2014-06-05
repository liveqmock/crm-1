package com.deppon.crm.module.recompense.server.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.server.dao.impl.RoleDao;
import com.deppon.crm.module.authorization.server.dao.impl.UserDao;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.organization.server.dao.impl.DepartmentDao;
import com.deppon.crm.module.organization.server.dao.impl.EmployeeDao;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.server.dao.RecompenseDao;
import com.deppon.crm.module.recompense.server.dao.impl.BaseModelDaoImpl;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.server.utils.DateUtil;
import com.deppon.crm.module.recompense.shared.domain.CellphoneMessageInfo;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.GoodsTrans;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.Message;
import com.deppon.crm.module.recompense.shared.domain.GetRecompenseByWayBill;
import com.deppon.crm.module.recompense.shared.domain.OAWorkflow;
import com.deppon.crm.module.recompense.shared.domain.OnlineApply;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.RecompenseSearchCondition;
import com.deppon.crm.module.recompense.shared.domain.UserRoleDeptRelation;
import com.deppon.crm.module.recompense.shared.domain.Waybill;
import com.deppon.crm.module.recompense.shared.exception.RecompenseExceptionType;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;

public class RecompenseManagerTest extends TestCase {
	static ClassPathXmlApplicationContext factory;
	private static RecompenseManager recompenseManager;
	private static RecompenseService recompenseService;
	private static RecompenseDao recompenseDao;

	/*******/
	private static BaseModelDaoImpl baseModelDao = null;
	private static RoleDao roleDao = null;
	private static DepartmentDao departmentDao = null;
	private static UserDao userDao = null;
	private static EmployeeDao employeeDao = null;

	private UserRoleDeptRelation urd = null;

	private static Role role = null;
	private static Department dept = null;
	private static User user = null;
	/********/

	RecompenseApplication rApp = null;
	RecompenseApplication rApp01 = null;
	RecompenseApplication rApp02 = null;
	RecompenseSearchCondition condition = null;
	// User user = null;
	Map mapApp = null;
	Message message = null;
	IssueItem issueItem = null;
	List<IssueItem> listIssueItems = null;
	GoodsTrans goodsTrans = null;
	List<GoodsTrans> listGoodsTrans = null;
	DeptCharge deptCharge = null;
	List<DeptCharge> listDeptCharges = null;
	Member customer = null;
	String recompenseId = "400159551";
	static {
		recompenseManager = TestUtil.recompenseManager;
		recompenseService = TestUtil.recompenseService;
		recompenseDao = TestUtil.recompenseDao;

		/****/
		baseModelDao = TestUtil.baseModelDao;
		roleDao = TestUtil.roleDao;
		departmentDao = TestUtil.departmentDao;
		userDao = TestUtil.userDao;
		/*
		 * role = roleDao.getAll().get(0); dept =
		 * departmentDao.getAll(null).get(0); user =
		 * userDao.getAll(null).get(0); 原来代码需要把role dept user 全部查出来 数据量大
		 */
		Employee emp = new Employee();
		role = roleDao.getById("1004");
		dept = departmentDao.getById("24237");
		emp.setDeptId(dept);
		user = userDao.getById("26604");
		// userDao.up
		user.setEmpCode(emp);
		/******/

	}

	@Before
	public void setUp() throws Exception {
		condition = new RecompenseSearchCondition();
		urd = new UserRoleDeptRelation();
		urd.setRole(role);
		urd.setDept(dept);
		urd.setUser(user);

		message = new Message();
		// message.setId("101111");
		message.setUserId("1");
		message.setUserName("张三丰");
		message.setCreateTime(new Date());
		message.setContent("你的火炮儿全湿来，没法放了");
		message.setRecompenseId(recompenseId);

		Waybill waybill = new Waybill();
		waybill.setWaybillNumber("593523666");
		waybill.setInsurAmount(55.00);

		issueItem = new IssueItem();
		issueItem.setId("1");
		issueItem.setRecompenseId("400142699");
		issueItem.setQuality(1);
		issueItem.setInsurType("BREAKED");
		issueItem.setDescription("货物外包装破损，导致内部遥控器丢失");
		listIssueItems = new ArrayList<IssueItem>();
		listIssueItems.add(issueItem);

		goodsTrans = new GoodsTrans();
		goodsTrans.setId("400113686");
		goodsTrans.setRecompenseId("400153516");
		goodsTrans.setGoodsName("臭豆腐");
		goodsTrans.setPrice(1.00);
		goodsTrans.setNumber(111);
		listGoodsTrans = new ArrayList<GoodsTrans>();
		listGoodsTrans.add(goodsTrans);

		deptCharge = new DeptCharge();
		deptCharge.setId("400191200");
		deptCharge.setDeptId("10151");
		deptCharge.setAmount(100.00);
		listDeptCharges = new ArrayList<DeptCharge>();
		listDeptCharges.add(deptCharge);

		rApp = new RecompenseApplication();
		rApp.setId("400142699");
		rApp.setWorkflowId(5555554411112L);
		rApp.setRecompenseMethod("fast");
		rApp.setRecompenseType("NORMAL_TYPE");
		rApp.setStatus("Paid");
		rApp.setInsurType("BREAKED");
		rApp.setInsurQuantity(1);
		rApp.setReportDept("10772");
		rApp.setReportMan("32140");
		rApp.setReportDate(new Date());
		rApp.setInsurDate(new Date());
		rApp.setDeptId("424");
		rApp.setRecompenseAmount(200.00);
		rApp.setCompanyName("大师兄");
		rApp.setCompanyPhone("13843819438");
		rApp.setIssueItemList(listIssueItems);
		rApp.setWorkflowId(null);
		// rApp.setWaybill(waybill);

		rApp01 = new RecompenseApplication();
		rApp01.setId("400142703");
		rApp01.setWorkflowId(5555554411112L);
		rApp01.setRecompenseMethod("fast");
		rApp01.setRecompenseType("abnormal");
		rApp01.setStatus("Paid");
		rApp01.setInsurType("BREAKED");
		rApp01.setInsurQuantity(1);
		rApp01.setReportDept("10772");
		rApp01.setReportMan("32140");
		rApp01.setReportDate(new Date());
		rApp01.setInsurDate(new Date());
		rApp01.setDeptId("424");
		rApp01.setRecompenseAmount(200.00);
		rApp01.setRealAmount(100.00);
		rApp01.setNormalAmount(100.00);
		rApp01.setCompanyName("二师兄");
		rApp01.setCompanyPhone("13843819438");
		rApp01.setIssueItemList(listIssueItems);
		rApp01.setGoodsTransList(listGoodsTrans);
		customer = new Member();
		customer.setId("115220");
		rApp01.setCustomer(customer);
		rApp01.setWaybill(waybill);

		rApp02 = new RecompenseApplication();
		rApp02.setId("400142703");
		rApp02.setWorkflowId(5555554411112L);
		rApp02.setRecompenseMethod("fast");
		rApp02.setRecompenseType("abnormal");
		rApp02.setStatus("Paid");
		rApp02.setInsurType("BREAKED");
		rApp02.setInsurQuantity(1);
		rApp02.setReportDept("10772");
		rApp02.setReportMan("32140");
		rApp02.setReportDate(new Date());
		rApp02.setInsurDate(new Date());
		rApp02.setDeptId("424");
		// rApp02.setRecompenseAmount(200.00);
		rApp02.setRealAmount(100.00);
		rApp02.setNormalAmount(100.00);
		rApp02.setCompanyName("二师兄");
		rApp02.setCompanyPhone("13843819438");
		rApp02.setIssueItemList(listIssueItems);
		rApp02.setGoodsTransList(listGoodsTrans);
		customer = new Member();
		customer.setId("115220");
		rApp02.setCustomer(customer);
		rApp02.setWaybill(waybill);

		mapApp = new HashMap();
		mapApp.put("recompenseApplication", rApp);
		// mapApp.put("issueItemMap", rApp);
		// mapApp.put("goodsTransMap", rApp);
		// mapApp.put("deptChargeMap", rApp);
		// mapApp.put(null, rApp);
		// mapApp.put(null, rApp);
		// mapApp.put(null, rApp);
		// mapApp.put(null, rApp);

	}

	@Test
	public void testPerformAction() {
		int actionId = 210;
		recompenseManager.performAction(mapApp, actionId, user.getId());
		 actionId = 220;
//		 recompenseManager.performAction(mapApp, actionId, user.getId());
//		 actionId = 350;
//		 recompenseManager.performAction(mapApp, actionId, user.getId());
//		 actionId = 111;
//		 recompenseManager.performAction(mapApp, actionId, user.getId());
	}

	@Test
	public void testValidateRecompenseData() {
		// Map appMap, int actionId
		
	}
	
	@Test
	public void testAddTodoMessage() {
		com.deppon.crm.module.common.shared.domain.Message  todo=new  com.deppon.crm.module.common.shared.domain.Message();
		
		try{
			recompenseManager.addTodoMessage(todo);
		}catch (Exception e) {
		}
	}
	@Test
	public void testDeleteRecompenseApplication() {
		try{
		recompenseManager.deleteRecompenseApplication(user.getId(), recompenseId, 250000);
		}catch (Exception e) {
		}
	}
	@Test
	public void testGetWorkFlowByOwner() {
		try{
			recompenseManager.getWorkFlowByOwner(user.getId());
		}catch (Exception e) {
		}
	}
	
	@Test
	public void testGetMessageUserIds() {
		try{
			recompenseManager.getMessageUserIds(Constants.ROLE_ADMIN, "11111", "11103");
			recompenseManager.getMessageUserIds("1111", "11111", "11103");
		}catch (Exception e) {
		}
	}
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.recompense.server.manager.impl.RecompenseManagerImpl# getRecompenseApplicationByUser(String,com.deppon.crm.module.authorization.shared.domain.User)}
	 * .
	 */
	@Test
	public void testGetRecompenseApplicationByUser() {
		List list = recompenseManager.getRecompenseApplicationByUser(user
				.getId());
		assertNotNull(list);
	}

	// TODO
	@Test
	public void testGetRecompenseApplicationByCustomer() {
		List list = recompenseManager.getRecompenseApplicationByCustomer(rApp01
				.getCustomer().getAreaId(), rApp01.getId(),
				new Date(112, 12, 1), new Date(112, 12, 10));
		assertNotNull(list);
		RecompenseApplication app = rApp01;
		app.setNormalAmount(null);
		app.setRealAmount(null);
		List list2 = recompenseManager.getRecompenseApplicationByCustomer(
				rApp01.getCustomer().getAreaId(), rApp01.getId(), new Date(112,
						12, 1), new Date(112, 12, 10));
		assertNotNull(list2);
	}

	/**
	 * Test method for {@link
	 * com.deppon.crm.module.recompense.server.manager.impl.
	 * RecompenseManagerImpl#
	 * getRecompenseApplicationStatByCustomer(List<RecompenseApplication>list)}
	 * .
	 */
	@Test
	public void testGetRecompenseApplicationStatByCustomer() {
		List<RecompenseApplication> list = null;

		// 1.list为空
		assertNotNull(recompenseManager
				.getRecompenseApplicationStatByCustomer(list));
		// 2.list不为空，size为0
		list = new ArrayList<RecompenseApplication>();
		assertNotNull(recompenseManager
				.getRecompenseApplicationStatByCustomer(list));

		// 3.list不为空，size不为0
		list.add(rApp01);
		assertNotNull(recompenseManager
				.getRecompenseApplicationStatByCustomer(list));
		// //4.list不为空，size不为0,recompenseAmount为空
		RecompenseApplication app = null;
		app = rApp02;
		list.add(app);
		assertNotNull(recompenseManager
				.getRecompenseApplicationStatByCustomer(list));
		// 5.list不为空，size不为0,normalAmount为空
		app = rApp01;
		app.setNormalAmount(null);
		list.add(app);
		assertNotNull(recompenseManager
				.getRecompenseApplicationStatByCustomer(list));

		// 6.list不为空，size不为0,realAmount为空
		app = rApp01;
		app.setRealAmount(null);
		list.add(app);
		assertNotNull(recompenseManager
				.getRecompenseApplicationStatByCustomer(list));
		// 7.list不为空，size不为0,waybill为空
		app = rApp01;
		app.getWaybill().setInsurAmount(null);
		list.add(app);
		assertNotNull(recompenseManager
				.getRecompenseApplicationStatByCustomer(list));
		// 8.list不为空，size不为0,waybill.insurAmount为空
		app = rApp01;
		app.setWaybill(null);
		list.add(app);
		assertNotNull(recompenseManager
				.getRecompenseApplicationStatByCustomer(list));
	}

	@Test
	public void testGetRecompenseApplicationById() {
		// 1.
		assertNotNull(recompenseManager.getRecompenseApplicationById(
				recompenseId, user));
		// 2.
//		assertNotNull(recompenseManager.getRecompenseApplicationById(
//				rApp.getId(), user));
	}

	@Test
	public void testGetWaybillByWaybillNum() {
	}

	/**
	 * 
	 * <p>
	 * Description:在线理赔付款失败回调<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-1 void
	 */
	@Test
	public void testerpPaymentRefuse() {
		//recompenseManager.paymentRefuse("965333333");

	}

	@Test
	public void testRepayOnlineApply() {
		OnlineApply onlineApply = new OnlineApply();
		onlineApply.setWaybillNumber("08876023");
		onlineApply.setOnlineUser("039027");
		//recompenseManager.repayOnlineApply(onlineApply);

	}

	// TODO
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2012-12-13上午9:47:09 void
	 * @update 2012-12-13上午9:47:09
	 */
	@Test
	public void testSearchRecompenseByCondition() {

		condition.setReportStartTime(new Date(2011, 3, 1));
		condition.setReportEndTime(new Date(2012, 3, 31));
		condition.setStart(0);
		condition.setLimit(10);
		condition.setRecompenseMethod("ALL");
		condition.setInitSearch(true);

		String[] deptIds = { dept.getId() };

		Set<String> set = new HashSet<String>();
		set.add("3");
		user.setRoleids(set);
		// set.removeAll(set);
		List<RecompenseApplication> list = recompenseManager
				.searchRecompenseByCondition(condition, user);
		assertNotNull(list);

		condition.setRecompenseMethod("overpay");
		condition.setRecompenseType("ALL");
		set.removeAll(set);
		set.add("4");
		condition.setReportDept("1212");
		condition.setBelongArea("11211");
		user.setRoleids(set);
		list = recompenseManager.searchRecompenseByCondition(condition, user);
		assertNotNull(list);

		condition.setReportDept("");
		condition.setBelongArea("");
		list = recompenseManager.searchRecompenseByCondition(condition, user);
		assertNotNull(list);

		condition.setReportDept(null);
		condition.setBelongArea(null);
		list = recompenseManager.searchRecompenseByCondition(condition, user);
		assertNotNull(list);

		condition.setRecompenseMethod("xxxxxxxx");
		set.removeAll(set);
		set.add("5");
		user.setRoleids(set);
		list = recompenseManager.searchRecompenseByCondition(condition, user);
		assertNotNull(list);

		set.removeAll(set);
		set.add("6");
		user.setRoleids(set);
		list = recompenseManager.searchRecompenseByCondition(condition, user);
		assertNotNull(list);

		set.removeAll(set);
		set.add("7");
		user.setRoleids(set);
		list = recompenseManager.searchRecompenseByCondition(condition, user);
		assertNotNull(list);

		set.removeAll(set);
		set.add("8");
		user.setRoleids(set);
		list = recompenseManager.searchRecompenseByCondition(condition, user);
		assertNotNull(list);

		user.setRoleids(null);
		list = recompenseManager.searchRecompenseByCondition(condition, user);
		assertNotNull(list);

		set.removeAll(set);
		set.add("10001");
		user.setRoleids(set);
		condition.setReportDept("1212");
		condition.setBelongArea("11211");
		list = recompenseManager.searchRecompenseByCondition(condition, user);
		assertNotNull(list);

		condition.setReportDept("");
		condition.setBelongArea("");
		list = recompenseManager.searchRecompenseByCondition(condition, null);
		assertNotNull(list);

		condition.setReportDept(null);
		condition.setBelongArea(null);
		condition.setInitSearch(false);
		condition.setRecompenseState("xxxxx");
		list = recompenseManager.searchRecompenseByCondition(condition, null);
		assertNotNull(list);

		condition.setRecompenseState("ALL");
		list = recompenseManager.searchRecompenseByCondition(condition, null);
		assertNotNull(list);

		condition.setRecompenseState("");
		list = recompenseManager.searchRecompenseByCondition(condition, null);
		assertNotNull(list);

		condition.setRecompenseState(null);
		list = recompenseManager.searchRecompenseByCondition(condition, null);
		assertNotNull(list);

	}

	@Test
	public void testSearchRecompenseForMonitorOrGetRecompenseCountForMonitor() {
		condition.setRecompenseMethod("ALL");
		condition.setRecompenseType("ALL");
		condition.setBelongArea("ALL");
		condition.setCustomerLevel("ALL");
		condition.setRecompenseState("xxxx");
		recompenseManager.searchRecompenseForMonitor(condition);
		recompenseManager.getRecompenseCountForMonitor(condition);

		condition.setRecompenseMethod("");
		condition.setRecompenseType("");
		condition.setBelongArea("12121");
		condition.setCustomerLevel("");
		condition.setRecompenseState("ALL");
		recompenseManager.searchRecompenseForMonitor(condition);
		recompenseManager.getRecompenseCountForMonitor(condition);

		condition.setRecompenseMethod("");
		condition.setRecompenseType("");
		condition.setBelongArea("");
		condition.setCustomerLevel("");
		condition.setRecompenseState("");
		recompenseManager.searchRecompenseForMonitor(condition);
		recompenseManager.getRecompenseCountForMonitor(condition);

		condition.setRecompenseMethod("ALL");
		condition.setRecompenseType("ALL");
		condition.setBelongArea("ALL");
		condition.setCustomerLevel("ALL");
		condition.setRecompenseState("xxxx");
		recompenseManager.getRecompenseCountForMonitor(condition);
		recompenseManager.searchRecompenseForMonitor(condition);

		condition.setRecompenseMethod("");
		condition.setRecompenseType("");
		condition.setBelongArea(null);
		condition.setCustomerLevel("");
		condition.setRecompenseState(null);
		recompenseManager.getRecompenseCountForMonitor(condition);
		recompenseManager.searchRecompenseForMonitor(condition);

	}

	// TODO 覆盖ID不为空的条件时，有异常抛出
	@Test
	public void testAddMessageForRecompense() {
		// String reportDept,String deptId, Message message, User user
		String reportDept = rApp.getReportDept();
		String deptId = rApp.getDeptId();
		recompenseManager.addMessageForRecompense(reportDept, deptId, message,
				user);
		//
		// Set<String> set = new HashSet<String>();
		// set.add("4");
		// user.setRoleids(set);
		// try {
		// recompenseManager.
		// addMessageForRecompense(
		// reportDept, deptId, message, user);
		// } catch (GeneralException e) {

		// }
	}

	@Test
	public void testGetWaybillInfoByNo() {

		String voucherNo = "55121293";
		// String voucherNo = "123124125";
		String recompenseMethod = "abnormal";
		try {
			recompenseManager.getWaybillInfoByNo(voucherNo, recompenseMethod);
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_RECORD_EXIST_ERROR
							.getErrorCode())) {
				assertTrue(true);
			} else {
//				fail("异常异常");
			}
		}
	}

	@Test
	public void testGetRecompenseCountByCondition() {
		RecompenseSearchCondition condition = new RecompenseSearchCondition();
		condition.setReportStartTime(new Date(2011, 1, 1));
		condition.setReportEndTime(new Date(2012, 12, 31));
		condition.setStart(0);
		condition.setLimit(10);
		int i = recompenseManager
				.getRecompenseCountByCondition(condition, null);
		System.out.println(i);
	}

	@Test
	public void testInsertOnlineApply() {
		long l = Calendar.getInstance().getTimeInMillis();
		String str = String.valueOf(l);
		str = str.substring(3);

		OnlineApply onlineApply = new OnlineApply();
		onlineApply.setRecompenseMethod(Constants.ONLINE_TYPE);
		onlineApply.setRecompenseType(Constants.ABNORMAL_SIGN);
		onlineApply.setWaybillNumber(str);
		onlineApply.setCustomerNum(str);
		onlineApply.setInsurAmount(1000D);
		onlineApply.setCustomerId(str);
		onlineApply.setRecompenseAmount(1000D);
		onlineApply.setRecompenseReason("异常签收");
		onlineApply.setStatus(Constants.STATUS_SUBMITED);
		onlineApply.setApplyDeptId("1");
		recompenseManager.createOnlineApply(onlineApply);
	}

	@Test
	public void testSearchOnlineApplyByCondition() {
		List<OnlineApply> list = recompenseManager
				.searchOnlineApplyByCondition("4141649203", null, 0, 10);
		Long l = recompenseManager.searchOnlineApplyCountByCondition(
				"4141649203", null);
		assertEquals(Long.valueOf(list.size() + ""), l);

		list = recompenseManager.searchOnlineApplyByCondition(
				"4141649203111111", null, 0, 10);
		l = recompenseManager.searchOnlineApplyCountByCondition(
				"4141649203111111", null);
		assertEquals(Long.valueOf(list.size() + ""), l);

		list = recompenseManager
				.searchOnlineApplyByCondition(null, null, 0, 10);
		l = recompenseManager.searchOnlineApplyCountByCondition(null, null);
		if (l < 11) {
			assertEquals(Long.valueOf(list.size()), l);
		} else {
			System.out.println(l);
		}
	}

	@Test
	public void testInitOnlineApplyCount() {
		Long i = recompenseManager
				.searchOnlineApplyCountByCondition(null, null);
		System.out.println(i);
	}

	@Test
	public void xtestHandleOnlineApply() {
		List<OnlineApply> list = recompenseManager
				.searchOnlineApplyByCondition(null, null, 0, 10);
		if (list != null && list.size() > 0) {
			for (OnlineApply onlineApply : list) {
				String wbNum = onlineApply.getWaybillNumber();
				RecompenseApplication ra = this.recompenseService
						.getRecompenseApplicationByVoucherNo(wbNum);
				if (ra == null) {
					RecompenseApplication rec = recompenseManager
							.handleOnlineApply(onlineApply.getId(), user);
					assertNotNull(rec);
					break;
				}
			}
		}
	}

	@Test
	public void testRefuseOnlineApply() {
		List<OnlineApply> list = recompenseManager
				.searchOnlineApplyByCondition(null, null, 0, 10);
		if (list != null && list.size() > 0) {
			for (OnlineApply onlineApply : list) {
				if (onlineApply.getStatus()
						.equals(Constants.STATUS_WAIT_ACCEPT)) {
//					boolean boo = recompenseManager.refuseOnlineApply(
//							onlineApply.getId(), user);
//					assertEquals(boo, true);
					break;
				}
			}

		}
	}

	// @Test
	// public void testSendMobileMessage(){
	// recompenseManager.se
	// }
	@Test
	public void testGetAreaById() {
		Department dept = recompenseManager.getAreaById("1");
	}

	@SuppressWarnings("static-access")
	@Test
	public void testReminderForRecompenseMonitor() {
		RecompenseSearchCondition condition = new RecompenseSearchCondition();
		condition.setReportStartTime(new Date(111, 3, 1));
		condition.setReportEndTime(new Date(112, 3, 31));
		condition.setStart(0);
		condition.setLimit(10);

		List<RecompenseApplication> list = recompenseManager
				.searchRecompenseByCondition(condition, null);
		if (list != null && list.size() > 0) {
			this.recompenseManager.reminderForRecompenseMonitor(list.get(0)
					.getId());
		}
	}

	@Test
	public void testGetUserRoleDeptRelationByUserId() {
		recompenseManager.getUserRoleDeptRelationByUserId(recompenseId);
	}

	@Test
	public void testGetAllUserRoleDeptRelation() {
		recompenseManager.getAllUserRoleDeptRelation(0, 10);
	}

	@Test
	public void testDeleteUserRoleDeptRelationById() {
		baseModelDao.insertUserRoleDepartment(urd);
		UserRoleDeptRelation urd01 = baseModelDao
				.getUserRoleDeptRelationByDeptId(dept.getId()).get(0);
		recompenseManager.deleteUserRoleDeptRelationById(urd01.getId());
	}

	@Test
	public void testInsertUserRoleDepartment() {
		recompenseManager.insertUserRoleDepartment(user.getId(), dept.getId(),
				role.getId());
	}

	@Test
	public void testHandleOnlineApply() {
		try {
			recompenseManager.handleOnlineApply("400000120", user);
			fail("没有异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					RecompenseExceptionType.RECOMPENSE_STATUS_WAIT_ACCEPT
							.getErrorCode())) {
				assertTrue(true);

			}
		}
	}

	@Test
	public void testGetBizDeptByDeptId() {
		assertNotNull(recompenseManager.getBizDeptByDeptId(dept.getId()));
	}

	@Test
	public void testGetAllBizDeptList() {
		assertNotNull(recompenseManager.getAllBizDeptList());
	}

	@Test
	public void testGetBigAreaByDeptCode() {
		assertNotNull(recompenseManager
				.getBigAreaByDeptCode(dept.getDeptCode()));
	}

	@Test
	public void testGetDeptByStandardCode() {
		recompenseManager.getDeptByStandardCode(dept.getDeptCode());
	}

	@Test
	public void testGetBigAreaList() {
		assertNotNull(recompenseManager.getBigAreaList());
	}

	@Test
	public void testGetUserListByUserName() {
		assertNotNull(recompenseManager.getUserListByUserName("邹明", 10));
	}

	@Test
	public void testGetUserListByEmpCode() {
		assertNotNull(recompenseManager.getUserListByEmpCode("034543", 10));
	}

	@Test
	public void testGetAccidentByNo() {
		assertNotNull(this.recompenseManager.getAccidentByNo("1",
				Constants.ABNORMAL_SIGN));

		assertNotNull(this.recompenseManager.getAccidentByNo("1",
				Constants.UNBILLED));
	}

	@Test
	public void testGetRecompenseForMonitoring() {
		List<RecompenseApplication> list = recompenseManager
				.getRecompenseForMonitoring(condition);
		assertNotNull(list);
	}

	@Test
	public void testGetWorkflowByAppIdAndType() {
		OAWorkflow wf = this.recompenseService.getWorkflowByAppIdAndType(
				"3191", "lost_goods");
		if (wf != null) {
			System.out.println(wf.getId());
		}
	}

	@Test
	public void testUpdateRecompenseOverpayById() {
		RecompenseApplication ra = this.recompenseService
				.getRecompenseApplicationById("800001684");
		if (ra != null) {
			assertTrue(this.recompenseService.updateRecompenseOverpayById(
					ra.getId(), "8"));
			ra = this.recompenseService
					.getRecompenseApplicationById(ra.getId());
			assertNotNull(ra);

			RecompenseSearchCondition condition = new RecompenseSearchCondition();
			condition.setStart(0);
			condition.setLimit(10);
			List list = this.recompenseService
					.searchRecompenseByCondition(condition);
			if (list != null && list.size() > 0) {
				System.out.println(list.size());
			}
		}

	}

	@Test
	public void testGetDeptListByDeptName() {
		recompenseManager.getDeptListByDeptName("上海", 20);
	}

	@Test
	public void testBetBigAreaListByName() {
		recompenseManager.getBigAreaListByName("浦西");
	}

	@Test
	public void testGenerateTodoReminder() {
		recompenseManager.generateTodoReminder();
	}

//	@Test
//	public void testGetRecompenseInPayment() {
//		Date startDate = DateUtil.getDateMidnight(new Date(), -10);
//		Date endDate = DateUtil.getDateMidnight(new Date(), 0);
//		recompenseManager.getRecompenseInPayment(startDate, endDate);
//	}

	@Test
	public void testGetRecompensePaymented() {
		Date startDate = DateUtil.getDateMidnight(new Date(), -100);
		Date endDate = DateUtil.getDateMidnight(new Date(), 0);
		//recompenseManager.getRecompensePaymented(startDate, endDate);

	}

	@Test
	public void testUpdateDeptChargesById() {

	}
	@Test
	public void testGetRecompenseByWayBill() {
		GetRecompenseByWayBill rec = recompenseManager.getRecompenseByWayBill("54559549");
//		assertNotNull(rec);
//		assertEquals("023587", rec.getCustNumber());
//		assertEquals("惠州市精工弹簧有限公司", rec.getCustName());
//		assertEquals("59352997", rec.getWaybillNumber());
//		assertEquals("BREAKED", rec.getInsurType());
////		assertEquals("", rec.getInsurDate());
//		assertEquals(200d, rec.getRecompenseAmount());
//		assertEquals(200d, rec.getRealAmount());
//		assertEquals(null, rec.getReceiveDept());
//		assertEquals("木架全部散掉，2台机器全部挤压变形;", rec.getIssueDesc());
//		assertEquals("WaitAccept", rec.getStatus());
//		assertEquals("系统自动",rec.getHandledMan());
//		assertNotNull(rec.getHandledTime());
//		assertEquals("河南北部大区:140.0;苏州大区:60.0;", rec.getDeptCharge());
	}
	@Test
	public void testGetBigAreaByDeptId(){
		recompenseManager.getBigAreaByDeptId("11103");
		
		
	}

	public void testSearchRecompenseHistoryList() {
		recompenseManager.searchRecompenseHistoryList("135", 20, 1);
	}

	@Test
	public void testMessageReceiverMore() {
		List<String> recompenseIdList = new ArrayList<String>();
		recompenseIdList.add("800002020");
		recompenseIdList.add("99991001");
		recompenseManager.getMessageReceiver(recompenseIdList, "1,2,3,4,5,6");
	}
	
	@Test
	public void testMessageReceiver() {
		List<String> recompenseIdList = new ArrayList<String>();
		recompenseIdList.add("800002020");
		recompenseManager.getMessageReceiver(recompenseIdList, "1,2,6");
	}
	
	@Test
	public void testSMSTemplate() {
		recompenseManager.getSMSTemplate("Handled", "700400116", "上海闵行区浦江镇营业部", "online", "1,2,3");
	}

	@Test
	public void testSMSTemplate2() {
		recompenseManager.getSMSTemplate("InOaApprove", "99991001", "广州白云区东平营业部", "fast", "1,2,3,4,5,6");
	}
	
	@Test
	public void testSendSmsInfo() {
		List<CellphoneMessageInfo> smsInfos = new ArrayList<CellphoneMessageInfo>();
		CellphoneMessageInfo sms = new CellphoneMessageInfo();
		// 收信人电话
		sms.setMobile("13761465230");
		// 消息内容
		sms.setMsgContent("【未处理】您好，运单号700400116由上海闵行区浦江镇营业部上报理赔，截止目前55天仍处于未处理状态，请您协助跟进，尽快收齐并提交理赔资料至事业部理赔专员。【理赔研究小组】");
		// 业务类型
		sms.setMsgType(Constant.SMS_COMPlAINT_CODE);
		// 短信发送人
		sms.setSender("084544");
		// 短信发送部门(标杆编码)			
		sms.setSendDept("DP02076");
		smsInfos.add(sms);
		
		sms = new CellphoneMessageInfo();
		// 收信人电话
		sms.setMobile("13524304498");
		// 消息内容
		sms.setMsgContent("【初步处理】您好，运单号99991001由广州白云区东平营业部上报理赔，截止目前50天仍处于初步处理状态，由于协商时间较长，根据升级处理机制的内容，请领导协助营业部处理。【理赔研究小组】");
		// 业务类型
		sms.setMsgType(Constant.SMS_COMPlAINT_CODE);
		// 短信发送人
		sms.setSender("084544");
		// 短信发送部门(标杆编码)			
		sms.setSendDept("DP02076");
		smsInfos.add(sms);
		//  TODO recompenseManager.sendSmsInfo(smsInfos);
	}
	
	@Test
	public void testSendSmsInfoMore() {
		List<CellphoneMessageInfo> smsInfos = new ArrayList<CellphoneMessageInfo>();
		CellphoneMessageInfo sms = new CellphoneMessageInfo();
		// 收信人电话
		sms.setMobile("13761465230");
		// 消息内容
		sms.setMsgContent("【未处理】您好，运单号700400116由上海闵行区浦江镇营业部上报理赔，截止目前55天仍处于未处理状态，请您协助跟进，尽快收齐并提交理赔资料至事业部理赔专员。【理赔研究小组】");
		// 业务类型
		sms.setMsgType(Constant.SMS_COMPlAINT_CODE);
		// 短信发送人
		sms.setSender("084544");
		// 短信发送部门(标杆编码)			
		sms.setSendDept("DP02076");
		sms.setSendDeptName("光华营业部");
		sms.setWaybillNo("700400116");
		smsInfos.add(sms);
		
		sms = new CellphoneMessageInfo();
		// 收信人电话
		sms.setMobile("13524304498");
		// 消息内容
		sms.setMsgContent("【未处理】您好，运单号700400116由上海闵行区浦江镇营业部上报理赔，截止目前55天仍处于未处理状态，请您协助跟进，尽快收齐并提交理赔资料至事业部理赔专员。【理赔研究小组】");
		// 业务类型
		sms.setMsgType(Constant.SMS_COMPlAINT_CODE);
		// 短信发送人
		sms.setSender("084544");
		// 短信发送部门(标杆编码)			
		sms.setSendDept("DP02076");
		sms.setSendDeptName("武汉江汉营业部");
		sms.setWaybillNo("99991001");
		smsInfos.add(sms);
		//TODO recompenseManager.sendSmsInfoMore(smsInfos, "Submited", "1,2,3,5");
	}
}
