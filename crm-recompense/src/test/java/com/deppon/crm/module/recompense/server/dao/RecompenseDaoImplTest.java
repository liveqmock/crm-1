/**   
 * @title RecompenseDaoJdbcImplTest.java
 * @package com.deppon.crm.recompense.dao.impl
 * @description what to do
 * @author 潘光均
 * @update 2012-2-14 下午6:10:10
 * @version V1.0   
 */
package com.deppon.crm.module.recompense.server.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.recompense.server.dao.impl.RecompenseDaoImpl;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.RecompenseForCC;
import com.deppon.crm.module.recompense.shared.domain.RecompenseSearchCondition;
import com.deppon.crm.module.recompense.shared.domain.TodoReminder;
import com.deppon.crm.module.recompense.shared.domain.Waybill;

/**
 * @description 实现xxx
 * @author 潘光均
 * @version 0.1 2012-2-14
 * @date 2012-2-14
 */

public class RecompenseDaoImplTest extends TestCase {
	static ClassPathXmlApplicationContext factory;

	private RecompenseApplication app = null;
	RecompenseApplication app2 = null;
	RecompenseApplication app3 = null;
	private static RecompenseDaoImpl recompenseDao = null;
	static Connection conn = null;
	private List<String> ids = new ArrayList<String>();

	static {
//		AppContext.initAppContext("application", "");
//		String resource = "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml";
//		// String resource =
//		// "com/deppon/crm/module/recompense/server/META-INF/spring.xml";
//		factory = new ClassPathXmlApplicationContext(resource);
//		recompenseDao = (RecompenseDaoImpl) factory.getBean("recompenseDao");
		recompenseDao = TestUtil.recompenseDao;

		// // 这里的代码会在类初始化时执行，所以相当于BeforeClass
		// // 加载spring容器,初始化sqlSession
		// Properties prop = new Properties();
		// dao = (RecompenseDaoJdbcImpl) SpringHelper.get().getRecompenseDao();
		// // 初始化基础表数据
		// try {
		// prop.load(RecompenseDaoJdbcImplTest.class
		// .getResourceAsStream("../../../../../../classes/spring/production/db.properties"));
		// Class.forName("oracle.jdbc.driver.OracleDriver");
		// conn=DriverManager.getConnection(prop.getProperty("jdbc.url"),prop.getProperty("jdbc.username"),prop.getProperty("jdbc.password"));
		// System.out.println(conn.isClosed());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// ScriptRunner runner = new ScriptRunner(conn);
		// runner.setAutoCommit(true);
		// runner.setErrorLogWriter(null);
		// runner.setLogWriter(null);
		// BufferedReader br = new BufferedReader(
		// new InputStreamReader(RecompenseDaoJdbcImplTest.class
		// .getResourceAsStream("../../../../../sql/initData.sql")));
		// runner.runScript(br);
		// // 将一个匿名方法写到这里，就相当于AfterClass
		// Runtime.getRuntime().addShutdownHook(new Thread() {
		// public void run() {
		// // 清除基础表数据
		// ScriptRunner runner = new ScriptRunner(conn);
		// BufferedReader br = new BufferedReader(
		// new InputStreamReader(
		// RecompenseDaoJdbcImplTest.class
		// .getResourceAsStream("../../../../../sql/deleteInitData.sql")));
		// runner.runScript(br);
		// }
		// });
	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {

		app = new RecompenseApplication();
		app.setWorkflowId(11111111l);
		app.setRecompenseMethod("1");
		app.setRecompenseType("2");
		Member ct = new Member();
		ct.setId("1");
		// ct.setContactPhone("1399090950");
		// ct.setContactTel("0512-25487511");
		ct.setCustNumber("cu10000");
		ct.setCustName("疯经神1");
		ct.setDegree("普通会员");
		app.setCustomer(ct);
		Waybill waybill = new Waybill();
		waybill.setWaybillNumber(Calendar.getInstance().getTimeInMillis() + "");
		waybill.setId(Calendar.getInstance().getTimeInMillis() + "");
		waybill.setPackaging("纸盒");
		waybill.setTransType("3");
		waybill.setInsured("王木子");
		waybill.setTelephone("139909950");
		waybill.setStartStation("北京");
		waybill.setEndStation("上海");
		waybill.setGoodsName("炸药");
		waybill.setPwv("45/234/22");
		waybill.setSendDate(new Date(2011, 9, 24, 8, 25, 32));
		waybill.setInsurAmount(34453.12);
		waybill.setReceiveDept("北京朝天门营业部");
		app.setWaybill(waybill);
		app.setRecompenseNum(Calendar.getInstance().getTimeInMillis() + "");
		app.setInsurType("2");
		app.setInsurQuantity(43);
		app.setReportDept("5");
		app.setInsurDate(new Date(2011, 9, 25, 8, 25, 32));
		app.setReportMan("78852");
		app.setReportDate(new Date(2011, 9, 26, 8, 25, 32));
		app.setDeptId("1");
		app.setDeptName("北京大区");
		app.setRecompenseAmount(5464564.23);
		// app.setAgreeRecompense(false);
		app.setClaimParty("1");
		app.setCreateUser("123");
		app.setCompanyFax("AX-9989-9090950");
		app.setCompanyPhone("9090950");
		app.setCompanyName("人民大会堂");
		app.setStatus("0");

		app2 = new RecompenseApplication();
		app2.setWorkflowId(11111111l);
		app2.setRecompenseMethod("1");
		app2.setRecompenseType("2");
		Member ct2 = new Member();
		ct2.setId("2");
		// ct2.setContactPhone("1395854950");
		// ct2.setContactTel("0987-25498751");
		ct2.setCustNumber("cu10001");
		ct2.setCustName("疯病1");
		ct2.setDegree("黄金会员");
		app2.setCustomer(ct2);
		Waybill waybill2 = new Waybill();
		waybill2.setWaybillNumber(Calendar.getInstance().getTimeInMillis() + "");
		waybill2.setId(Calendar.getInstance().getTimeInMillis() + "");
		waybill2.setPackaging("纸盒");
		waybill2.setTransType("3");
		waybill2.setInsured("王木子");
		waybill2.setTelephone("139909950");
		waybill2.setStartStation("北京");
		waybill2.setEndStation("上海");
		waybill2.setGoodsName("炸药");
		waybill2.setPwv("45/234/22");
		waybill2.setSendDate(new Date(2011, 9, 24, 8, 25, 32));
		waybill2.setInsurAmount(34453.12);
		waybill2.setReceiveDept("北京朝天门营业部");
		app2.setWaybill(waybill2);
		app2.setRecompenseNum(Calendar.getInstance().getTimeInMillis() + "");
		app2.setInsurType("2");
		app2.setInsurQuantity(43);
		app2.setReportDept("5");
		app2.setInsurDate(new Date(2011, 9, 25, 8, 25, 32));
		app2.setReportMan("78852");
		app2.setReportDate(new Date(2011, 9, 26, 8, 25, 32));
		app2.setDeptId("1");
		app2.setDeptName("北京大区");
		app2.setRecompenseAmount(5464564.23);
		// app.setAgreeRecompense(false);
		app2.setClaimParty("1");
		app2.setCreateUser("123");
		app2.setCompanyFax("AX-9989-9090950");
		app2.setCompanyPhone("9090950");
		app2.setCompanyName("人民大会堂");
		app2.setStatus("1");

		app3 = new RecompenseApplication();
		app3.setWorkflowId(11111111l);
		app3.setRecompenseMethod("1");
		app3.setRecompenseType("2");
		Member ct3 = new Member();
		ct2.setId("3");
		// ct2.setContactPhone("13555874980");
		// ct2.setContactTel("021-25486581");
		ct2.setCustNumber("cu10002");
		ct2.setCustName("疯经病3");
		ct2.setDegree("钻石会员");
		app3.setCustomer(ct3);
		Waybill waybill3 = new Waybill();
		waybill3.setWaybillNumber(Calendar.getInstance().getTimeInMillis() + "");
		waybill3.setId(Calendar.getInstance().getTimeInMillis() + "");
		waybill3.setPackaging("纸盒");
		waybill3.setTransType("3");
		waybill3.setInsured("王木子");
		waybill3.setTelephone("139909950");
		waybill3.setStartStation("北京");
		waybill3.setEndStation("上海");
		waybill3.setGoodsName("炸药");
		waybill3.setPwv("45/234/22");
		waybill3.setSendDate(new Date(2011, 9, 24, 8, 25, 32));
		waybill3.setInsurAmount(34453.12);
		waybill3.setReceiveDept("北京朝天门营业部");
		app3.setWaybill(waybill3);
		app3.setRecompenseNum(Calendar.getInstance().getTimeInMillis() + "");
		app3.setInsurType("2");
		app3.setInsurQuantity(43);
		app3.setReportDept("5");
		app3.setInsurDate(new Date(2011, 9, 25, 8, 25, 32));
		app3.setReportMan("78852");
		app3.setReportDate(new Date(2011, 9, 26, 8, 25, 32));
		app3.setDeptId("1");
		app3.setDeptName("北京大区");
		app3.setRecompenseAmount(5464564.23);
		// app.setAgreeRecompense(false);
		app3.setClaimParty("1");
		app3.setCreateUser("123");
		app3.setCompanyFax("AX-9989-9090950");
		app3.setCompanyPhone("9090950");
		app3.setCompanyName("人民大会堂");
		app3.setStatus("0");
		// super.setUp();
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		for (int i = 0; i < ids.size(); i++) {
			recompenseDao.deleteRecompenseApplicationById(ids.get(i));
		}
		super.tearDown();
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.RecompenseDaoImpl.dao.impl.RecompenseDaoJdbcImpl#getRecompenseApplicationById(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetRecompenseApplicationById() {
		assertNotNull(recompenseDao);
		recompenseDao.insertRecompenseApplication(null);
		recompenseDao.insertRecompenseApplication(app);
		ids.add(app.getId());
		RecompenseApplication ra = recompenseDao
				.getRecompenseApplicationById(app.getId());
		assertEquals("人民大会堂", ra.getCompanyName());

		ra = recompenseDao.getRecompenseApplicationById(null);
		assertNull(ra);

		ra = recompenseDao.getRecompenseApplicationById("");
		assertNull(ra);

		ra = recompenseDao.getRecompenseApplicationById("9999999999");
		assertNull(ra);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.RecompenseDaoImpl.dao.impl.RecompenseDaoJdbcImpl#insertRecompenseApplication(com.deppon.crm.recompense.domain.RecompenseApplication)}
	 * .
	 */
	@Test
	public void testInsertRecompenseApplication() {
		RecompenseApplication ra = recompenseDao
				.insertRecompenseApplication(app);
		ids.add(app.getId());
		assertEquals("人民大会堂", ra.getCompanyName());
		// ra = dao.getRecompenseApplicationById(app.getId());
		// assertEquals("78852", ra.getReportMan());
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.RecompenseDaoImpl.dao.impl.RecompenseDaoJdbcImpl#updateRecompenseApplication(com.deppon.crm.recompense.domain.RecompenseApplication)}
	 * .
	 */
	@Test
	public void testUpdateRecompenseApplication() {
		String costExplain = "";
		for (int i = 0; i < 666; i++) {
			costExplain = costExplain + "中";
		}
		System.out.println(costExplain + "::" + costExplain.length());
		app.setCostExplain(costExplain);
		RecompenseApplication ra = recompenseDao
				.insertRecompenseApplication(app);
		ids.add(app.getId());
		assertNotNull(ra);
		assertEquals("人民大会堂", ra.getCompanyName());

		app.setCompanyName("狗不理包子店");
		assertTrue(recompenseDao.updateRecompenseApplication(app));

		ra = recompenseDao.getRecompenseApplicationById(app.getId());
		assertNotNull(ra);
		assertEquals("狗不理包子店", ra.getCompanyName());

		assertFalse(recompenseDao.updateRecompenseApplication(null));

		String id = app.getId();
		app.setId(null);

		assertFalse(recompenseDao.updateRecompenseApplication(app));
		app.setId(id);
	}

	@Test
	public void testUpdateRecompenseForReport() {
		RecompenseApplication ra = recompenseDao
				.insertRecompenseApplication(app);
		ids.add(app.getId());
		assertNotNull(ra);
		assertEquals("人民大会堂", ra.getCompanyName());

		RecompenseApplication app0 = null;
		recompenseDao.updateRecompenseStatusInfo(app0);
		RecompenseApplication app = new RecompenseApplication();
		app.setId(null);
		recompenseDao.updateRecompenseStatusInfo(app);
		app.setId(ra.getId());
		// app.setAmountConfirmedTime(new Date());
		recompenseDao.updateRecompenseStatusInfo(app);
		app.setAmountConfirmedTime(new Date());
		// 初步处理时间：
		app.setHandledTime(new Date());
		// 初步处理人：
		app.setHandledMan("1");
		// 资料确认时间：
		app.setDocConfirmedTime(new Date());
		// 资料确认人：
		app.setDocConfirmedMan("1");
		// 金额确认时间：
		app.setAmountConfirmedTime(new Date());
		// 金额确认人：
		app.setAmountConfirmedMan("1");
		// 提交审批时间：
		app.setNormalApproveSubmitTime(new Date());
		// 提交审批人：
		app.setNormalApproveSubmitMan("1");
		// 最后审批时间：
		app.setLastApprovedTime(new Date());
		// 最后审批人：
		app.setLastApprovedMan("1");
		// 提交付款时间：
		app.setPaymentSubmitTime(new Date());
		// 提交付款人：
		app.setPaymentSubmitMan("1");
		// 出纳付款时间：
		app.setAmountPaidTime(new Date());
		// 付款审批人：
		app.setAmountPaidMan("1");
		app.setModifyUser("121");
		app.setStatus("121");
		recompenseDao.updateRecompenseStatusInfo(app);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.RecompenseDaoImpl.dao.impl.RecompenseDaoJdbcImpl#deleteRecompenseApplicationById(java.lang.String)}
	 * .
	 */
	@Test
	public void testDeleteRecompenseApplicationById() {
		RecompenseApplication ra = recompenseDao
				.insertRecompenseApplication(app);
		ids.add(app.getId());
		assertNotNull(ra);

		ra = recompenseDao.getRecompenseApplicationById(app.getId());
		assertNotNull(ra);

		assertTrue(recompenseDao.deleteRecompenseApplicationById(app.getId()));
		ra = recompenseDao.getRecompenseApplicationById(app.getId());
		assertNull(ra);
		// 重复删
		assertFalse(recompenseDao.deleteRecompenseApplicationById(app.getId()));
		assertFalse(recompenseDao.deleteRecompenseApplicationById(null));
		assertFalse(recompenseDao.deleteRecompenseApplicationById(""));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.recompense.dao.impl.RecompenseDaoJdbcImpl#getAllRecompenseApplication()}
	 * .
	 */
	// @Test
	// public void testGetAllRecompenseApplication() {
	// List<RecompenseApplication> raList = dao.getAllRecompenseApplication();
	// System.out.println(raList != null ? raList.size() : 0);
	// }

	// /**
	// * Test method for
	// * {@link
	// com.deppon.crm.recompense.dao.impl.RecompenseDaoJdbcImpl#getRecompenseApplicationByVoucherNo(java.lang.String)}
	// * .
	// */
	// @Test
	// public void testGetRecompenseApplicationByRecompenseNum() {
	// RecompenseApplication ra = dao.insertRecompenseApplication(app);
	// ids.add(app.getId());
	// assertNotNull(ra);
	//
	// ra = dao.getRecompenseApplicationByVoucherNo(app.getRecompenseNum());
	// assertNotNull(ra);
	//
	// assertEquals("人民大会堂", ra.getCompanyName());
	//
	// ra = dao.getRecompenseApplicationByVoucherNo(null);
	// assertNull(ra);
	// ra = dao.getRecompenseApplicationByVoucherNo("");
	// assertNull(ra);
	// }

	/**
	 * Test method for
	 * {@link com.deppon.crm.RecompenseDaoImpl.dao.impl.RecompenseDaoJdbcImpl#getRecompenseApplicationByCustomerId(java.lang.String,java.lang.String)}
	 * .
	 */
	@Test
	public void testGetRecompenseApplicationByCustomerId() {
		RecompenseApplication ra = recompenseDao
				.insertRecompenseApplication(app);
		ids.add(app.getId());
		assertNotNull(ra);
		int limit = 10;
		int start = 0;
		recompenseDao.getRecompenseApplicationByCustomerId(null,
				"23", start, limit);
		recompenseDao.getRecompenseApplicationByCustomerId("525",
				null, start, limit);
		recompenseDao.getRecompenseApplicationByCustomerId("525",
				"23", start, limit);
		
		List list0 = recompenseDao.getRecompenseApplicationByCustomerId("525",
				"23", new Date(), new Date());

		assertEquals("人民大会堂", ra.getCompanyName());

		List<RecompenseApplication> list = recompenseDao
				.getRecompenseApplicationByCustomerId(null, "23", null, null);
		assertEquals(0, list.size());
		list = recompenseDao.getRecompenseApplicationByCustomerId("32", null,
				null, null);
		assertEquals(0, list.size());
		list = recompenseDao.getRecompenseApplicationByCustomerId(null, null,
				null, null);
		assertEquals(0, list.size());
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.RecompenseDaoImpl.dao.impl.RecompenseDaoJdbcImpl#getRecompenseApplicationByMultiCondition(java.lang.String, java.util.List, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.util.Date, java.util.Date, java.lang.String, java.lang.String, java.util.List)}
	 * .
	 */
	// @Test
	// public void testGetRecompenseApplicationByMultiCondition() {
	// RecompenseApplication ra = dao.insertRecompenseApplication(app);
	// ids.add(app.getId());
	// assertNotNull(ra);
	// dao.insertRecompenseApplication(app2);
	// ids.add(app2.getId());
	// dao.insertRecompenseApplication(app3);
	// ids.add(app3.getId());

	// List<RecompenseApplication> list = dao
	// .getRecompenseApplicationByMultiCondition(null, null, null,
	// null, "普通会员", null, null, null, null, null, null, null,
	// null, 0, Integer.MAX_VALUE);
	// assertNotNull(list);
	// assertEquals(list.get(0).getCustomer().getCustomerLevel(), "普通会员");
	//
	// list = dao.getRecompenseApplicationByMultiCondition(null, null, null,
	// "疯经神1", null, null, null, null, null, null, null, null, null,
	// 0, Integer.MAX_VALUE);
	// assertEquals(list.get(0).getCustomer().getCustomerName(), "疯经神1");
	//
	// list = dao.getRecompenseApplicationByMultiCondition(null, null,
	// "cu10000", null, null, null, null, null, null, null, null,
	// null, null, 0, Integer.MAX_VALUE);
	// assertEquals(1, list.size());
	// assertEquals(list.get(0).getCustomer().getCustomerNum(), "cu10000");
	//
	// List depts = new ArrayList();
	// depts.add("1");
	// list = dao.getRecompenseApplicationByMultiCondition(null, depts,
	// "cu10000", null, null, null, null, null, null, null, null,
	// null, null, 0, Integer.MAX_VALUE);
	// assertEquals(list.get(0).getCustomer().getCustomerNum(), "cu10000");
	// assertEquals(list.get(0).getDeptId(), "1");
	//
	// list = dao.getRecompenseApplicationByMultiCondition(null, depts,
	// null,
	// null, null, null, null, null, null, null, null, null, null, 0,
	// Integer.MAX_VALUE);
	// assertEquals(3, list.size());
	//
	// List status = new ArrayList();
	// status.add("0");
	// list = dao.getRecompenseApplicationByMultiCondition(null, null, null,
	// null, null, null, null, null, null, null, null, null, status,
	// 0, Integer.MAX_VALUE);
	// assertEquals(2, list.size());
	//
	// list = dao.getRecompenseApplicationByMultiCondition(null, null,
	// "cu10000", null, null, null, null, null, null, null, null,
	// null, status, 0, Integer.MAX_VALUE);
	// assertEquals(list.get(0).getCustomer().getCustomerNum(), "cu10000");
	// assertEquals(list.get(0).getDeptId(), "1");
	// assertEquals(app, list.get(0));
	//
	// list = dao.getRecompenseApplicationByMultiCondition(null, null, null,
	// null, null, null, null, null, null, null, null, null, null, 0,
	// Integer.MAX_VALUE);
	// assertEquals(0, list.size());
	//
	// list = dao.getRecompenseApplicationByMultiCondition("nsdf", depts,
	// "nsdf", "nsdf", "nsdf", "nsdf", new Date(), new Date(),
	// new Date(), new Date(), "nsdf", "nsdf", status, 0,
	// Integer.MAX_VALUE);
	// assertEquals(0, list.size());
	// }

	@Test
	public void testSearchRecompenseByCondition() {
		RecompenseApplication ra = recompenseDao
				.insertRecompenseApplication(app);
		ids.add(app.getId());
		assertNotNull(ra);

		RecompenseSearchCondition condition = new RecompenseSearchCondition();
		condition.setReportStartTime(new Date(2011, 3, 1));
		condition.setReportEndTime(new Date(2012, 3, 31));

		condition.setStart(0);
		condition.setLimit(10);
		List list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		condition.setReportDeptIdList(list);
		condition.setDeptIdList(list);
		condition.setRecompenseStateList(list);
		List<RecompenseApplication> raList = recompenseDao
				.searchRecompenseByCondition(condition);
		if (raList != null && raList.size() > 0) {
			int s = raList.size();
			System.out.println(s);
		}
	}

	@Test
	public void testGetRecompenseCountByCondition() {
		RecompenseSearchCondition condition = new RecompenseSearchCondition();
		condition.setReportStartTime(new Date(2011, 3, 1));
		condition.setReportEndTime(new Date(2012, 3, 31));
		int i = this.recompenseDao.getRecompenseCountByCondition(condition);
		System.out.println(i);
	}

	public void testGetRecompenseByWaybillNum() {
		List<RecompenseApplication> list = this.recompenseDao
				.searchRecompenseByWaybillNum("1333162372343");
		if (list != null && list.size() > 0) {
			System.out.println("~~~~~~size=" + list.size());
		}
	}

	public void testGetRecompenseApplicationByCustomerNum() {
		List<RecompenseApplication> list = this.recompenseDao
				.searchRecompenseByCustNum("%");
		if (list != null && list.size() > 0) {
			System.out.println(list.size());
		}
	}

	public void testGenerateTodoReminder() {
		List<TodoReminder> list = this.recompenseDao
				.generateTodoReminder("Submited");
	}
	
	@Test
	public void testSearchRecompensePageByCustNum(){
		 RecompenseSearchCondition condition = new RecompenseSearchCondition();
		 condition.setStart(0);
		 condition.setLimit(10);
		 System.err.println(
				 recompenseDao.searchRecompensePageByCustNum(condition));
	}
	
	@Test
	public void testGetRecompenseCountByCustNum(){
		RecompenseSearchCondition condition = new RecompenseSearchCondition();
		condition.setCustNum(app.getCustomer().getCustNumber());
		System.err.println(
				recompenseDao.getRecompenseCountByCustNum(condition));
	}
	
	@Test
	public void testUpdateRecompenseOverpayById(){
		String recompenseId;
		String custId = app.getCustomer().getId();
		//recompenseId = recompenseDao.getRecompenseListByCustId(custId).get(0).getId();
		recompenseId = "4564654654";
		String overPayId="00152452140";
		assertFalse(		
				recompenseDao.updateRecompenseOverpayById(recompenseId, overPayId));
	}
	
	@Test
	public void testFindInvalidCustRecompenseList(){
		try {
			List<RecompenseApplication>	r =recompenseDao.findInvalidCustRecompenseList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testupdateCustomerInfo(){
		
		recompenseDao.updateCustomerInfo("400159551", "1", "2");
	}
	
	@Test
	public void testSearchRecompenseHistoryList() {
		List<RecompenseForCC> list=	recompenseDao.searchRecompenseHistoryList("12", 20, 1);
		System.out.println(list);
	}
	
}
