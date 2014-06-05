/**   
 * @title ListItemDaoJdbcImplTest.java
 * @package com.deppon.crm.recompense.dao
 * @description what to do
 * @author 潘光均
 * @update 2012-2-16 下午5:01:47
 * @version V1.0   
 */
package com.deppon.crm.module.recompense.server.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.server.dao.impl.ListItemDaoImpl;
import com.deppon.crm.module.recompense.server.dao.impl.RecompenseDaoImpl;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.Balance;
import com.deppon.crm.module.recompense.shared.domain.BankAccount;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.GoodsTrans;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.Message;
import com.deppon.crm.module.recompense.shared.domain.MessageReminder;
import com.deppon.crm.module.recompense.shared.domain.OAWorkflow;
import com.deppon.crm.module.recompense.shared.domain.OnlineApply;
import com.deppon.crm.module.recompense.shared.domain.Overpay;
import com.deppon.crm.module.recompense.shared.domain.Payment;
import com.deppon.crm.module.recompense.shared.domain.PaymentBill;
import com.deppon.crm.module.recompense.shared.domain.RecalledCompensation;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.RecompenseAttachment;
import com.deppon.crm.module.recompense.shared.domain.RecompenseSearchCondition;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;

/**
 * @description 实现列表项的单元测试
 * @author 潘光均
 * @version 0.1 2012-2-16
 * @date 2012-2-16
 */

public class ListItemDaoImplTest extends TestCase {
	static ClassPathXmlApplicationContext factory;
	private static ListItemDaoImpl listItemDao = null;
	private static RecompenseDaoImpl recompenseDao;
	private AwardItem aItem = null;
	private DeptCharge charge = null;
	private GoodsTrans trans = null;
	private IssueItem iItem = null;
	private Message msg = null;
	private MessageReminder remainder = null;
	private OAWorkflow flow = null;
	private PaymentBill bill = null;
	private RecalledCompensation comp = null;
	private RecompenseAttachment attachment = null;
	private Payment payment = null;

	private ResponsibleDept dept = null;
	String recompenseId = "1";

	static {
		// AppContext.initAppContext("application", "");
		// String resource =
		// "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml";
		// factory = new ClassPathXmlApplicationContext(resource);
		// listItemDao = (ListItemDaoImpl) factory.getBean("listItemDao");
		// recompenseDao = (RecompenseDaoImpl) factory.getBean("recompenseDao");
		listItemDao = TestUtil.listItemDao;
		recompenseDao = TestUtil.recompenseDao;
	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		// String resource =
		// "com/deppon/crm/module/recompense/server/META-INF/spring.xml";

		RecompenseSearchCondition condition = new RecompenseSearchCondition();
		condition.setReportStartTime(new Date(2011, 3, 1));
		condition.setReportEndTime(new Date(2012, 3, 31));

		condition.setStart(0);
		condition.setLimit(10);
		List<RecompenseApplication> recList = recompenseDao
				.searchRecompenseByCondition(condition);
		if (recList != null && recList.size() > 0) {
			recompenseId = recList.get(0).getId();
		}

		charge = new DeptCharge();
		charge.setDeptId("1");
		charge.setDeptName("烧腊店");
		charge.setAmount(3232.2);
		charge.setRecompenseId("1");
		// dao.insertDeptCharge(charge);

		trans = new GoodsTrans();
		trans.setGoodsName("倚天剑");
		trans.setPrice(10283.3);
		trans.setQuality(2);
		trans.setRealPrice(2234.4);
		trans.setRecompenseId(recompenseId);
		// dao.insertGoodsTrans(trans);

		iItem = new IssueItem();
		iItem.setInsurType("1");
		iItem.setQuality(34);
		iItem.setDescription("打湿完了,没法放啊");
		iItem.setRecompenseId(recompenseId);
		// dao.insertIssueItem(iItem);

		msg = new Message();
		msg.setUserId("1");
		msg.setUserName("张三丰");
		msg.setCreateTime(new Date());
		msg.setContent("你的火炮儿全湿来，没法放了");
		msg.setRecompenseId(recompenseId);
		// dao.insertMessage(msg);

		remainder = new MessageReminder();
		// remainder.setReminderMethod("3");
		remainder.setRecompenseId(recompenseId);
		// dao.insertMessageReminder(remainder);

		Date date = new Date();
		flow = new OAWorkflow();
		flow.setAuditDate(new Date());
		flow.setAuditopinion("张三丰明明是自己搞湿的，非要赖我们，不管他，不赔");
		flow.setCommitDate(new Date(System.currentTimeMillis() - 2342435));
		flow.setRecompenseId(recompenseId);
		flow.setWorkflowType(2);
		flow.setWorkflowStatus(4);
		// flow.setWorkflowNum(date.getTime());
		// dao.insertWorkflow(flow);

		bill = new PaymentBill();
		bill.setFreightCollectAmount(24324.3);
		bill.setReceivableAmount(43534.43);
		bill.setRecompenseId(recompenseId);
		bill.setPaymentType("2");
		Department dept1 = new Department();
		dept1.setId("1");

		BankAccount ba = new BankAccount();
		ba.setOpenName("1");
		ba.setBankName("1");
		ba.setAccount("1");
		ba.setProvince("1");
		ba.setCity("1");
		ba.setBranchName("1");
		bill.setBankAccount(ba);

		// bill.getBelongfinance();
		// bill.setBelongfinance(dept1);
		// dao.insertPaymentBill(bill);

		comp = new RecalledCompensation();
		comp.setCompensateBackAmount(43534.22);
		comp.setCompensateBackTime(new Date(
				System.currentTimeMillis() - 8342435));
		comp.setDeptName("灭绝师太门");
		comp.setDeptId("2");
		comp.setRecoveryTime(new Date(System.currentTimeMillis() - 6342435));
		comp.setReturnDeductions(32.33);
		comp.setSuspendedAmount(343.44);
		comp.setRecompenseId(recompenseId);
		// dao.insertRecalledCompensation(comp);

		// rItem = new RecompenseItem();
		// rItem.setAmount(43534.22);
		// rItem.setDescription("别人的是倚天剑，是在价值连城，不得不赔这么多啊");
		// rItem.setGoodsName("倚天剑");
		// rItem.setInsurType("1");
		// rItem.setId(trans.getId());
		// rItem.setRecompenseId(recompenseId);
		// rItem.setQuality(2);
		// dao.insertRecompenseItem(rItem);

		dept = new ResponsibleDept();
		dept.setDeptId("1");
		dept.setDeptName("东方求败派");
		dept.setRecompenseId(recompenseId);
		aItem = new AwardItem(recompenseId, "awardType", dept.getDeptId(),
				dept.getDeptName(), "1", "1", 0D, "1");

		attachment = new RecompenseAttachment();
		attachment.setAttachAddress("e:es/e/es");
		attachment.setAttachName("倚天剑秘籍");
		attachment.setRecompenseId(recompenseId);
		// dao.insertRecompenseAttachment(attachment);

		// 付款信息
		payment = new Payment();
		payment.setCreateUserId("106138");
		payment.setCreateTime(new Date());
		payment.setRecompenseId("135648974");
		payment.setAccount("6222021001110778888");
		payment.setAccountProp("1");
		payment.setApplyTime(new Date());
		// payment.setBankName("交通银行");
		payment.setOpenName("许华龙");
		payment.setPaymentMode("2");
		// payment.setCity("厦门");
		// payment.setProvince("福建");
		payment.setApplyTime(new Date());
		super.setUp();
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		listItemDao.deleteAwardItemById(aItem.getId());
		// listItemDao.deleteRecompenseItemById(rItem.getId());
		listItemDao.deleteDeptChargeById(charge.getId());
		listItemDao.deleteGoodsTransById(trans.getId());
		listItemDao.deleteIssueItemById(iItem.getId());
		listItemDao.deleteMessageById(msg.getId());
		// dao.deleteMessageRemainderById(remainder.getId());
		listItemDao.deleteOAWorkflowById(flow.getId());
		listItemDao.deleteOAWorkflowById("121245462");
		listItemDao.deletePaymentBillById(bill.getId());
		listItemDao.deleteRecallCompById(comp.getId());
		listItemDao.deleteRecallCompById("678486434");
		listItemDao.deleteRecompAttById(attachment.getId());
		listItemDao.deleteResponsibleDeptById(dept.getId());
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#insertGoodsTrans(String, com.deppon.crm.recompense.domain.GoodsTrans)}
	 * .
	 */
	@Test
	public void testInsertGoodsTransGoodsTrans() {
		GoodsTrans gt = listItemDao.insertGoodsTrans(trans);
		assertNotNull(gt);
		assertEquals("倚天剑", gt.getGoodsName());

		// 1.存在RecompenseId与goodsTrans列表
		List<GoodsTrans> goodsTrans = new ArrayList<GoodsTrans>();
		goodsTrans.add(trans);
		boolean bool = listItemDao.insertGoodsTrans(iItem.getRecompenseId(),
				goodsTrans);
		assertTrue(bool);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.recompense.dao.impl.ListItemDaoJdbcImpl#insertRecompenseItem(com.deppon.crm.recompense.domain.RecompenseItem)}
	 * .
	 */
	/*
	 * @Test public void testInsertRecompenseItemRecompenseItem() {
	 * dao.insertGoodsTrans(trans); rItem.setId(trans.getId());
	 * dao.insertRecompenseItem(rItem); RecompenseItem ri =
	 * dao.insertRecompenseItem(rItem); assertNotNull(ri);
	 * assertEquals("别人的是倚天剑，是在价值连城，不得不赔这么多啊", ri.getDescription()); }
	 */

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getGoodsTransById(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetGoodsTransById() {
		listItemDao.insertGoodsTrans(trans);
		GoodsTrans gt = listItemDao.getGoodsTransById(trans.getId());
		assertNotNull(gt);
		assertEquals("倚天剑", gt.getGoodsName());

		gt = listItemDao.getGoodsTransById("");
		assertNull(gt);
		gt = listItemDao.getGoodsTransById(null);
		assertNull(gt);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.recompense.dao.impl.ListItemDaoJdbcImpl#getRecompenseItemById(java.lang.String)}
	 * .
	 */
	/*
	 * @Test public void testGetRecompenseItemById() {
	 * dao.insertGoodsTrans(trans); rItem.setId(trans.getId());
	 * dao.insertRecompenseItem(rItem); RecompenseItem ri =
	 * dao.getRecompenseItemById(rItem.getId()); assertNotNull(ri);
	 * assertEquals("别人的是倚天剑，是在价值连城，不得不赔这么多啊", ri.getDescription());
	 * 
	 * ri =dao.getRecompenseItemById(""); assertNull(ri); ri =
	 * dao.getRecompenseItemById(null); assertNull(ri); }
	 */

	/**
	 * Test method for
	 * {@link com.deppon.crm.recompense.dao.impl.ListItemDaoJdbcImpl#getRecompenseItemByRecompenseId(java.lang.String)}
	 * .
	 */
	/*
	 * @Test public void testGetRecompenseItemByRecompenseId() {
	 * dao.insertGoodsTrans(trans); rItem.setId(trans.getId());
	 * dao.insertRecompenseItem(rItem); List<RecompenseItem> ris =
	 * dao.getRecompenseItemByRecompenseId("1"); assertEquals(1, ris.size());
	 * assertNotNull(ris); assertEquals("别人的是倚天剑，是在价值连城，不得不赔这么多啊",
	 * ris.get(0).getDescription());
	 * 
	 * ris =dao.getRecompenseItemByRecompenseId(""); assertNull(ris); ris =
	 * dao.getRecompenseItemByRecompenseId(null); assertNull(ris); }
	 */

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getAwardItemByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetAwardItemByRecompenseId() {
		listItemDao.insertAwardItem(aItem);
		List<AwardItem> list = listItemDao
				.getAwardItemByRecompenseId(recompenseId);
		assertNotNull(list);

		list = listItemDao.getAwardItemByRecompenseId(null);
		assertEquals(0, list.size());
		list = listItemDao.getAwardItemByRecompenseId("");
		assertEquals(0, list.size());

	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#insertAwardItem(String, com.deppon.crm.recompense.domain.AwardItem)}
	 * .
	 */
	@Test
	public void testInsertAwardItem() {
		AwardItem ai = listItemDao.insertAwardItem(aItem);
		assertNotNull(ai);
		// assertEquals("1", ai.getAwardType());
		try {
			listItemDao.insertAwardItem(aItem);
			assertTrue(true);
		} catch (Exception e) {
			// fail();
		}
		// 1.String appId, List<AwardItem> awardItems
		List<AwardItem> list = listItemDao.getAwardItemByRecompenseId(iItem
				.getRecompenseId());
		try {
			assertTrue(listItemDao.insertAwardItem(recompenseId, list));
		} catch (Exception e) {
			// fail("大人，失败了");
		}
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getAwardItemById(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetAwardItemById() {
		listItemDao.insertAwardItem(aItem);
		AwardItem ai = listItemDao.getAwardItemById(aItem.getId());
		assertNotNull(ai);

		ai = listItemDao.getAwardItemById("");
		assertNull(ai);
		ai = listItemDao.getAwardItemById(null);
		assertNull(ai);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getDeptChargeById(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetDeptChargeById() {
		listItemDao.insertDeptCharge(charge);
		DeptCharge dc = listItemDao.getDeptChargeById(charge.getId());
		assertNotNull(dc);
		assertEquals("烧腊店", dc.getDeptName());

		dc = listItemDao.getDeptChargeById(null);
		assertNull(dc);
		dc = listItemDao.getDeptChargeById("");
		assertNull(dc);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#insertDeptCharge(String, com.deppon.crm.recompense.domain.DeptCharge)}
	 * .
	 */
	@Test
	public void testInsertDeptChargeDeptCharge() {
		DeptCharge dc = listItemDao.insertDeptCharge(charge);
		assertNotNull(dc);
		assertEquals("烧腊店", dc.getDeptName());

		try {
			listItemDao.insertDeptCharge(charge);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getDeptChargeByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetDeptChargeByRecompenseId() {
		listItemDao.insertDeptCharge(charge);
		List<DeptCharge> list = listItemDao
				.getDeptChargeByRecompenseId(recompenseId);
		assertNotNull(list);

		list = listItemDao.getDeptChargeByRecompenseId("");
		assertEquals(0, list.size());

		list = listItemDao.getDeptChargeByRecompenseId(null);
		assertEquals(0, list.size());

	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getIssueItemByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetIssueItemByRecompenseId() {
		listItemDao.insertIssueItem(iItem);
		IssueItem ii = listItemDao.getIssueItemById(iItem.getId());
		assertNotNull(ii);
		assertEquals("打湿完了,没法放啊", ii.getDescription());

		ii = listItemDao.getIssueItemById("");
		assertNull(ii);
		ii = listItemDao.getIssueItemById(null);
		assertNull(ii);

		// 1.RecompenseId不为空
		List<IssueItem> issueItems = new ArrayList<IssueItem>();
		issueItems = listItemDao.getIssueItemByRecompenseId(iItem
				.getRecompenseId());
		assertNotNull(issueItems);

		// 2.RecompenseId为空
		issueItems = listItemDao.getIssueItemByRecompenseId(null);
		assertNotNull(issueItems);

		// 2.RecompenseId为空
		issueItems = listItemDao.getIssueItemByRecompenseId("");
		assertNotNull(issueItems);

	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#insertIssueItem(String, com.deppon.crm.recompense.domain.IssueItem)}
	 * .
	 */
	@Test
	public void testInsertIssueItemIssueItem() {
		IssueItem ii = listItemDao.insertIssueItem(iItem);
		assertNotNull(ii);
		assertEquals("打湿完了,没法放啊", ii.getDescription());
		try {
			listItemDao.insertIssueItem(iItem);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getIssueItemById(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetIssueItemById() {
		listItemDao.insertIssueItem(iItem);
		IssueItem ii = listItemDao.getIssueItemById(iItem.getId());
		assertNotNull(ii);
		assertEquals("打湿完了,没法放啊", ii.getDescription());

		ii = listItemDao.getIssueItemById("");
		assertNull(ii);
		ii = listItemDao.getIssueItemById(null);
		assertNull(ii);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#insertMessage(com.deppon.crm.recompense.domain.Message)}
	 * .
	 */
	@Test
	public void testInsertMessageMessage() {
		Message ms = listItemDao.insertMessage(msg);
		assertNotNull(ms);
		assertEquals("你的火炮儿全湿来，没法放了", ms.getContent());
		try {
			listItemDao.insertMessage(msg);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getMessageByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetMessageByRecompenseId() {
		listItemDao.insertMessage(msg);
		List<Message> mss = listItemDao.getMessageByRecompenseId("1");
		assertNotNull(mss);

		mss = listItemDao.getMessageByRecompenseId("");
		assertEquals(0, mss.size());

	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#insertMessageReminder(String, com.deppon.crm.recompense.domain.MessageReminder)}
	 * .
	 */
	@Test
	public void testInsertMessageReminderMessageReminder() {
		MessageReminder mr = listItemDao.insertMessageReminder(remainder);
		assertNotNull(mr);
		try {
			listItemDao.insertMessageReminder(remainder);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getMessageReminderById(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetMessageReminderById() {
		listItemDao.insertMessageReminder(remainder);
		MessageReminder mr = listItemDao.getMessageReminderById(remainder
				.getId());
		assertNotNull(mr);

		mr = listItemDao.getMessageReminderById(null);
		assertNull(mr);
		mr = listItemDao.getMessageReminderById("");
		assertNull(mr);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getMessageReminderByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetMessageReminderByRecompenseId() {
		listItemDao.insertMessageReminder(remainder);
		List<MessageReminder> mrs = listItemDao
				.getMessageReminderByRecompenseId(recompenseId);
		assertNotNull(mrs);

		mrs = listItemDao.getMessageReminderByRecompenseId("");
		assertEquals(0, mrs.size());

		mrs = listItemDao.getMessageReminderByRecompenseId(null);
		assertEquals(0, mrs.size());
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#insertPaymentBill(com.deppon.crm.recompense.domain.PaymentBill)}
	 * .
	 */
	@Test
	public void testInsertPaymentBill() {
		PaymentBill pb = listItemDao.insertPaymentBill(bill);
		assertNotNull(pb);
		assertEquals(24324.3, pb.getFreightCollectAmount());
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#updatePaymentBill(com.deppon.crm.recompense.domain.PaymentBill)}
	 * .
	 * 
	 * @author zouming
	 */
	@Test
	public void testUpdatePaymentBill() {
		listItemDao.insertPaymentBill(bill);

		PaymentBill paymentBill = listItemDao.getPaymentBillByRecompenseId(
				bill.getRecompenseId()).get(0);

		assertTrue(this.listItemDao.updatePaymentBill(paymentBill));

		paymentBill.setId("1212");
		assertFalse(this.listItemDao.updatePaymentBill(paymentBill));

		paymentBill.setId("");
		assertFalse(this.listItemDao.updatePaymentBill(paymentBill));

		paymentBill.setId(null);
		assertFalse(this.listItemDao.updatePaymentBill(paymentBill));

		paymentBill = null;
		assertFalse(this.listItemDao.updatePaymentBill(paymentBill));
		// listItemDao.deletePaymentBillById(null);
		listItemDao.deletePaymentBillById("3132112354");
		// listItemDao.deletePaymentBillById(paymentBill.getId());

	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getPaymentBillById(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetPaymentBillById() {
		listItemDao.insertPaymentBill(bill);
		// 1.Id为空
		PaymentBill pb = listItemDao.getPaymentBillById("");
		assertNull(pb);
		pb = listItemDao.getPaymentBillById(null);
		assertNull(pb);
		// 2.Id不为空
		pb = listItemDao.getPaymentBillById(bill.getId());
		assertNotNull(pb);
		assertEquals(24324.3, pb.getFreightCollectAmount());
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getPaymentBillByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetPaymentBillByRecompenseId() {
		listItemDao.insertPaymentBill(bill);
		// 1.为空
		List<PaymentBill> pb = listItemDao.getPaymentBillByRecompenseId("");
		assertNotNull(pb);
		pb = listItemDao.getPaymentBillByRecompenseId(null);
		assertNotNull(pb);
		// 2.不为空
		pb = listItemDao.getPaymentBillByRecompenseId(recompenseId);
		assertNotNull(pb);
		assertEquals(24324.3, pb.get(0).getFreightCollectAmount());
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#insertRecompenseAttachment(String, com.deppon.crm.recompense.domain.RecompenseAttachment)}
	 * .
	 */
	@Test
	public void testInsertRecompenseAttachmentRecompenseAttachment() {
		RecompenseAttachment ra = listItemDao
				.insertRecompenseAttachment(attachment);
		assertNotNull(ra);
		assertEquals("e:es/e/es", ra.getAttachAddress());
		try {
			listItemDao.insertRecompenseAttachment(attachment);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getRecompenseAttachmentById(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetRecompenseAttachmentById() {
		listItemDao.insertRecompenseAttachment(attachment);
		RecompenseAttachment ra = listItemDao
				.getRecompenseAttachmentById(attachment.getId());
		assertNotNull(ra);
		assertEquals("倚天剑秘籍", ra.getAttachName());

		ra = listItemDao.getRecompenseAttachmentById("");
		assertNull(ra);
		ra = listItemDao.getRecompenseAttachmentById(null);
		assertNull(ra);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#insertRecalledCompensation(com.deppon.crm.recompense.domain.RecalledCompensation)}
	 * .
	 */
	@Test
	public void testInsertRecalledCompensation() {
		RecalledCompensation rc = listItemDao.insertRecalledCompensation(comp);
		assertNotNull(rc);
		assertEquals("灭绝师太门", rc.getDeptName());
		try {
			listItemDao.insertRecalledCompensation(comp);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getRecalledCompensationById(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetRecalledCompensationById() {
		listItemDao.insertRecalledCompensation(comp);
		RecalledCompensation rc = listItemDao.getRecalledCompensationById(comp
				.getId());
		assertNotNull(rc);
		assertEquals(43534.22, rc.getCompensateBackAmount());

		rc = listItemDao.getRecalledCompensationById(null);

		assertNull(rc);
		rc = listItemDao.getRecalledCompensationById("");
		assertNull(rc);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getRecalledCompensationByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetRecalledCompensationByRecompenseId() {
		listItemDao.insertRecalledCompensation(comp);
		List<RecalledCompensation> rc = listItemDao
				.getRecalledCompensationByRecompenseId(recompenseId);
		assertNotNull(rc);

		rc = listItemDao.getRecalledCompensationByRecompenseId(null);
		assertEquals(0, rc.size());
		rc = listItemDao.getRecalledCompensationByRecompenseId("");
		assertEquals(0, rc.size());
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getGoodsTransByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetGoodsTransByRecompenseId() {
		listItemDao.insertGoodsTrans(trans);
		GoodsTrans gt = listItemDao.getGoodsTransById(trans.getId());
		assertNotNull(gt);
		assertEquals("倚天剑", gt.getGoodsName());

		gt = listItemDao.getGoodsTransById("");
		assertNull(gt);
		gt = listItemDao.getGoodsTransById(null);
		assertNull(gt);

		// 1.RecompenseId为空
		List<GoodsTrans> goodsTrans = new ArrayList<GoodsTrans>();
		goodsTrans = listItemDao.getGoodsTransByRecompenseId(null);
		assertNotNull(goodsTrans);
		goodsTrans = listItemDao.getGoodsTransByRecompenseId("");
		assertNotNull(goodsTrans);

		// 2.RecompenseId不为空
		goodsTrans = listItemDao.getGoodsTransByRecompenseId(iItem
				.getRecompenseId());
		assertNotNull(goodsTrans);
	}

	// /**
	// * Test method for {@link
	// com.deppon.crm.recompense.dao.impl.ListItemDaoJdbcImpl#insertGoodsTrans(java.util.List)}.
	// */
	// @Test
	// public void testInsertGoodsTransListOfGoodsTrans() {
	// fail("Not yet implemented");
	// }

	// /**
	// * Test method for {@link
	// com.deppon.crm.recompense.dao.impl.ListItemDaoJdbcImpl#insertRecompenseItem(java.util.List)}.
	// */
	// @Test
	// public void testInsertRecompenseItemListOfRecompenseItem() {
	// fail("Not yet implemented");
	// }

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#deleteGoodsTransByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testDeleteGoodsTransByRecompenseId() {
		listItemDao.insertGoodsTrans(trans);
		assertNotNull(trans);
		listItemDao.deleteGoodsTransById(null);
		assertNotNull(trans);
		listItemDao.deleteGoodsTransById("");
		assertNotNull(trans);
		listItemDao.deleteGoodsTransById(trans.getId());
		assertNull(listItemDao.getGoodsTransById(trans.getId()));

		// 1.RecompenseId为空
		assertFalse(listItemDao.deleteGoodsTransByRecompenseId(null));
		assertFalse(listItemDao.deleteGoodsTransByRecompenseId(""));

		// 2.RecompenseId不为空
		listItemDao.deleteGoodsTransByRecompenseId(iItem.getRecompenseId());
		assertFalse(listItemDao.deleteGoodsTransByRecompenseId("02154512"));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#deleteAwardItemByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testDeleteAwardItemByRecompenseId() {
		listItemDao.insertAwardItem(aItem);
		assertNotNull(aItem);
		listItemDao.deleteAwardItemById(null);
		assertNotNull(aItem);
		listItemDao.deleteAwardItemById("");
		assertNotNull(aItem);

		listItemDao.deleteAwardItemById(aItem.getId());
		assertNull(listItemDao.getAwardItemById(aItem.getId()));

		// 1.RecompenseId为空
		assertFalse(listItemDao.deleteAwardItemByRecompenseId(null));
		assertFalse(listItemDao.deleteAwardItemByRecompenseId(""));
		// 2.RecompenseId不为空
		assertFalse(listItemDao.deleteAwardItemByRecompenseId(iItem
				.getRecompenseId()));
		assertFalse(listItemDao.deleteAwardItemByRecompenseId("54545121"));

	}

	// /**
	// * Test method for {@link
	// com.deppon.crm.recompense.dao.impl.ListItemDaoJdbcImpl#insertAwardItem(java.util.List)}.
	// */
	// @Test
	// public void testInsertAwardItemListOfAwardItem() {
	// fail("Not yet implemented");
	// }

	// /**
	// * Test method for {@link
	// com.deppon.crm.recompense.dao.impl.ListItemDaoJdbcImpl#insertDeptCharge(java.util.List)}.
	// */
	// @Test
	// public void testInsertDeptChargeListOfDeptCharge() {
	// fail("Not yet implemented");
	// }

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#deleteDeptChargeByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testDeleteDeptChargeByRecompenseId() {
		listItemDao.insertDeptCharge(charge);
		assertNotNull(charge);
		listItemDao.deleteDeptChargeById(null);
		assertNotNull(charge);
		listItemDao.deleteDeptChargeById("");
		assertNotNull(charge);

		listItemDao.deleteDeptChargeById(charge.getId());
		assertNull(listItemDao.getDeptChargeById(charge.getId()));

		// 1.RecompenseId为空
		assertFalse(listItemDao.deleteDeptChargeByRecompenseId(null));
		// 2.RecompenseId不为空
		assertTrue(listItemDao.deleteDeptChargeByRecompenseId(iItem
				.getRecompenseId()));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#deleteIssueItemByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testDeleteIssueItemByRecompenseId() {
		listItemDao.insertIssueItem(iItem);
		assertNotNull(iItem);
		// 1.
		assertFalse(listItemDao.deleteIssueItemByRecompenseId(""));
		assertFalse(listItemDao.deleteIssueItemByRecompenseId(null));
		// 2.
		assertTrue(listItemDao.deleteIssueItemByRecompenseId(recompenseId));
		assertFalse(listItemDao.deleteIssueItemByRecompenseId("1254878556"));

	}

	// /**
	// * Test method for {@link
	// com.deppon.crm.recompense.dao.impl.ListItemDaoJdbcImpl#insertIssueItem(java.util.List)}.
	// */
	// @Test
	// public void testInsertIssueItemListOfIssueItem() {
	// fail("Not yet implemented");
	// }

	/**
	 * Test method for
	 * {@link com.deppon.crm.recompense.dao.impl.ListItemDaoJdbcImpl#insertMessage(java.util.List)}
	 * .
	 */
	@Test
	public void testInsertMessageListOfMessage() {
		List<Message> list = new ArrayList<Message>();
		list.add(msg);
		assertTrue(listItemDao.insertMessage(list));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#deleteMessageByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testDeleteMessageByRecompenseId() {
		listItemDao.insertMessage(msg);
		assertNotNull(msg);
		assertFalse(listItemDao.deleteMessageByRecompenseId(null));
		assertFalse(listItemDao.deleteMessageByRecompenseId(""));
		assertTrue(listItemDao.deleteMessageByRecompenseId(msg
				.getRecompenseId()));
		assertEquals(listItemDao
				.getMessageByRecompenseId(msg.getRecompenseId()).size(), 0);
	}

	// /**
	// * Test method for {@link
	// com.deppon.crm.recompense.dao.impl.ListItemDaoJdbcImpl#insertMessageReminder(java.util.List)}.
	// */
	// @Test
	// public void testInsertMessageReminderListOfMessageReminder() {
	// fail("Not yet implemented");
	// }

	// /**
	// * Test method for {@link
	// com.deppon.crm.recompense.dao.impl.ListItemDaoJdbcImpl#insertRecompenseAttachment(java.util.List)}.
	// */
	// @Test
	// public void testInsertRecompenseAttachmentListOfRecompenseAttachment() {
	// fail("Not yet implemented");
	// }

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#deleteAttachmentByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testDeleteAttachmentByRecompenseId() {
		listItemDao.insertRecompenseAttachment(attachment);
		assertNotNull(attachment);
		assertFalse(listItemDao.deleteAttachmentByRecompenseId(null));
		assertFalse(listItemDao.deleteAttachmentByRecompenseId(""));
		assertTrue(listItemDao.deleteAttachmentByRecompenseId(attachment
				.getRecompenseId()));
		assertEquals(
				listItemDao.getAttachmentByRecompenseId(
						attachment.getRecompenseId()).size(), 0);
		assertNotNull(listItemDao.getAttachmentByRecompenseId(null));
	}

	// /**
	// * Test method for {@link
	// com.deppon.crm.recompense.dao.impl.ListItemDaoJdbcImpl#insertResponsibleDept(com.deppon.crm.recompense.domain.ResponsibleDept)}.
	// */
	// @Test
	// public void testInsertResponsibleDeptResponsibleDept() {
	// fail("Not yet implemented");
	// }
	//

	/**
	 * Test method for {@link
	 * com.deppon.crm.recompense.dao.impl.ListItemDaoJdbcImpl#
	 * insertResponsibleDept(String appId, List<ResponsibleDept> depts)}.
	 */
	@Test
	public void testInsertResponsibleDept() {
		List<ResponsibleDept> depts = new ArrayList<ResponsibleDept>();
		assertFalse(listItemDao.insertResponsibleDept(recompenseId, depts));
		depts = null;
		assertFalse(listItemDao.insertResponsibleDept(recompenseId, depts));
	}

	// /**
	// * Test method for {@link
	// com.deppon.crm.recompense.dao.impl.ListItemDaoJdbcImpl#insertResponsibleDept(java.util.List)}.
	// */
	// @Test
	// public void testInsertResponsibleDeptListOfResponsibleDept() {
	// fail("Not yet implemented");
	// }

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getResponsibleDeptByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetResponsibleDeptByRecompenseId() {
		listItemDao.insertResponsibleDept(dept);
		assertNotNull(dept);
		assertFalse(listItemDao.deleteResponsibleDeptByRecompenseId(""));
		assertTrue(listItemDao
				.deleteResponsibleDeptByRecompenseId(recompenseId));

		// 1.
		assertNotNull(listItemDao.getResponsibleDeptByRecompenseId(""));
		// 2.
		assertNotNull(listItemDao.getResponsibleDeptByRecompenseId(iItem
				.getRecompenseId()));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#deleteResponsibleDeptByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testDeleteResponsibleDeptByRecompenseId() {
		listItemDao.insertResponsibleDept(dept);
		assertNotNull(dept);
		assertFalse(listItemDao.deleteResponsibleDeptByRecompenseId(""));
		assertFalse(listItemDao.deleteResponsibleDeptByRecompenseId(null));
		assertTrue(listItemDao
				.deleteResponsibleDeptByRecompenseId(recompenseId));

		// 1.
		assertFalse(listItemDao
				.deleteResponsibleDeptByRecompenseId("123456742"));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.recompense.dao.impl.ListItemDaoJdbcImpl#deleteRecompenseItemByRecompenId(java.lang.String)}
	 * .
	 */
	/*
	 * @Test public void testDeleteRecompenseItemByRecompenId() {
	 * dao.insertGoodsTrans(trans); rItem.setId(trans.getId());
	 * dao.insertRecompenseItem(rItem); assertNotNull(rItem);
	 * assertFalse(dao.deleteRecompenseItemByRecompenId(""));
	 * assertFalse(dao.deleteRecompenseItemByRecompenId(null));
	 * assertTrue(dao.deleteRecompenseItemByRecompenId("1"));
	 * assertEquals(dao.getRecompenseItemByRecompenseId("1").size(),0); }
	 */

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#deleteMessageReminderByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testDeleteMessageRemainderByRecompenseId() {
		listItemDao.insertMessageReminder(remainder);
		assertFalse(listItemDao.deleteMessageReminderByRecompenseId(""));
		assertFalse(listItemDao.deleteMessageReminderByRecompenseId(null));

		assertTrue(listItemDao
				.deleteMessageReminderByRecompenseId(recompenseId));

		// 1.
		assertFalse(listItemDao
				.deleteMessageReminderByRecompenseId(recompenseId));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#updateDeptChargeById(com.deppon.crm.recompense.domain.DeptCharge)}
	 * .
	 */
	@Test
	public void testUpdateDeptChargeById() {
		listItemDao.insertDeptCharge(charge);
		assertEquals("烧腊店", charge.getDeptName());
		assertFalse(listItemDao.updateDeptChargeById(null));
		String id = charge.getId();
		charge.setId(null);
		assertFalse(listItemDao.updateDeptChargeById(charge));
		charge.setId(id);

		charge.setDeptName("馒头店");
		assertTrue(listItemDao.updateDeptChargeById(charge));
		assertEquals("馒头店", charge.getDeptName());

		// 1.
		charge.setId("12457861232");
		charge.setDeptName("豆腐店");
		assertFalse(listItemDao.updateDeptChargeById(charge));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.recompense.dao.impl.ListItemDaoJdbcImpl#updateDeptChargesById(java.util.List)}
	 * .
	 */
	@Test
	public void testUpdateDeptChargesById() {
		// 1.
		DeptCharge deptCharge = new DeptCharge();
		deptCharge.setAmount(100d);
		List<DeptCharge> deptCharges = null;
		assertFalse(listItemDao.updateDeptChargesById(deptCharges));
		// 2.
		deptCharges = new ArrayList<DeptCharge>();
		assertTrue(listItemDao.updateDeptChargesById(deptCharges));
		// 3.
		deptCharges.add(deptCharge);
		assertTrue(listItemDao.updateDeptChargesById(deptCharges));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#insertWorkflow(com.deppon.crm.recompense.domain.OAWorkflow)}
	 * .
	 */
	@Test
	public void testInsertWorkflow() {
		OAWorkflow wf = listItemDao.insertWorkflow(flow);
		assertNotNull(wf);
		assertEquals("张三丰明明是自己搞湿的，非要赖我们，不管他，不赔", wf.getAuditopinion());
		try {
			wf = listItemDao.insertWorkflow(flow);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
		try {
			wf = listItemDao.insertWorkflow(null);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testUpdateWorkflow() {
		OAWorkflow wf = listItemDao.insertWorkflow(flow);
		listItemDao.updateWorkflow(flow);
	}

	@Test
	public void testGetWorkflowByWorkflowNum() {
		OAWorkflow wf = listItemDao.insertWorkflow(flow);
		listItemDao.getWorkflowByWorkflowNum("1111112222211111");
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getWorkflowByRecompenseId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetWorkflowByRecompenseId() {
		listItemDao.insertWorkflow(flow);
		List<OAWorkflow> list = listItemDao.getWorkflowByRecompenseId("");
		assertEquals(0, list.size());
		list = listItemDao.getWorkflowByRecompenseId(null);
		assertEquals(0, list.size());
		list = listItemDao.getWorkflowByRecompenseId(recompenseId);
		assertEquals(1, list.size());
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#insertOverpay(com.deppon.crm.module.recompense.shared.domain.Overpay)}
	 * .
	 * 
	 * @author zouming
	 */
	@Test
	public void testInsertOverpay() {
		RecompenseApplication ra = new RecompenseApplication();
		ra.setId("2395");
		Overpay overpay = new Overpay(ra.getId(), 1000d, 5000d, true, null,
				null, "货损坏", null, "", "", "");
		boolean result = listItemDao.insertOverpay(overpay);
		assertEquals(result, true);

		assertTrue(listItemDao.deleteOverpayByRecompenseId(overpay
				.getRecompenseId()));
		assertFalse(listItemDao.deleteOverpayByRecompenseId("0002212"));
		assertFalse(listItemDao.deleteOverpayByRecompenseId(null));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getOverpayByRecompenseId(String)}
	 * .
	 * 
	 * @author zouming
	 * 
	 */
	@Test
	public void testGetOverpayByRecompenseId() {
		List<Overpay> oList = this.listItemDao.getOverpayByRecompenseId("2395");
		if (oList != null && oList.size() > 0) {
			System.out.println(oList.size());
		}
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getOverpayByWorkflowNum(String)}
	 * .
	 * 
	 * @author zouming
	 * 
	 */
	@Test
	public void testGetOverpayByWorkflowNum() {
		listItemDao.getOverpayByWorkflowNum("1254711", recompenseId);
	}

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
		onlineApply.setApplyPart("onlineApply");
		onlineApply.setRecompenseReason("异常签收");
		onlineApply.setStatus(Constants.STATUS_SUBMITED);
		assertTrue(this.listItemDao.insertOnlineApply(onlineApply));
	}

	public void testSearchOnlineApplyByCondition() {
		List<String> statusList = new ArrayList<String>();
		statusList.add(Constants.STATUS_SUBMITED);// 待处理
		statusList.add(Constants.STATUS_ONLINE_REFUSED);// 已退回

		List<OnlineApply> list = this.listItemDao.searchOnlineApplyByCondition(
				null, statusList, 0, 10);
		if (list != null && list.size() > 0) {
			System.out.println(list.size());
		}
	}

	@Test
	public void testInsertBalance() {
		Balance balance = new Balance("1", "", 3D, 2D, 1D);
		boolean result = this.listItemDao.insertBalance(balance);
		assertEquals(result, true);

		List<Balance> list = new ArrayList<Balance>();
		list.add(balance);
		assertTrue(listItemDao.insertBalance(list));
	}

	@Test
	public void testSearchBalanceByCondition() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("start", "0");
		map.put("limit", "10");
		List<Balance> list = this.listItemDao.searchBalanceByCondition(map);
		if (list != null && list.size() > 0) {
			System.out.println(list.size());
		}
	}

	public void testGetBalanceAmountCountByRecompenseId() {
		Double d = this.listItemDao.getBalanceAmountCountByRecompenseId("1");
		System.out.println(d);
	}

	@Test
	public void testGetOnlineApplyCountByCondition() {
		Long resutl = this.listItemDao.getOnlineApplyCountByCondition(null,
				null);
		System.out.println(resutl);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#updateOnlineApplyStatusByRecompenseId(String,String,String)}
	 * .
	 * 
	 * @author zouming
	 * 
	 */
	@Test
	public void testUpdateOnlineApplyStatusByRecompenseId() {
		listItemDao.updateOnlineApplyStatusByRecompenseId("3207", "Rejected",
				null);
		listItemDao.updateOnlineApplyStatusByRecompenseId("3207", "Rejected",
				"123");

		listItemDao.updateOnlineApplyStatusByRecompenseId("3207", "Rejected",
				"");
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#updateOnlineApply(com.deppon.crm.module.recompense.shared.domain.OnlineApply)}
	 * .
	 * 
	 * @author zouming
	 * 
	 */
	@Test
	public void testUpdateOnlineApply() {
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
		onlineApply.setApplyPart("onlineApply");
		onlineApply.setRecompenseReason("异常签收");
		onlineApply.setStatus(Constants.STATUS_SUBMITED);
		listItemDao.insertOnlineApply(onlineApply);
		try {
			assertTrue(listItemDao.updateOnlineApply(onlineApply));
		} catch (Exception e) {
			e.printStackTrace();
		}
		OnlineApply onlineApply01 = onlineApply;
		onlineApply01.setId("1245451");
		try {
			assertFalse(listItemDao.updateOnlineApply(onlineApply01));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetOnlineApplyByOnlineUser() {
		this.listItemDao.getOnlineApplyByOnlineUser("wsyyt", "");
		System.out.println("");
	}

	@Test
	public void testGetOnlineApplyByInterCondition() {
		this.listItemDao.getOnlineApplyByInterCondition("wsyyt", "", null,
				new Date(), 0, 123);
		System.out.println("");
	}

	@Test
	public void testGetOnlineApplyByInterConditionCount() {
		this.listItemDao.getOnlineApplyByInterConditionCount("wsyyt", "", null,
				new Date());
		System.out.println("");
	}

	/**
	 * 
	 * <p>
	 * Description:测试保存账号信息<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-4 void
	 */
	@Test
	public void testSavePayment() {
		this.listItemDao.savePayment(payment);

	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#getOnlineApplyByRecompenseId(String)}
	 * .
	 * 
	 * @author zouming
	 * 
	 */
	@Test
	public void testGetOnlineApplyByRecompenseId() {
		listItemDao.getOnlineApplyByRecompenseId(recompenseId);
	}

	@Test
	public void testDeleteMessageRemainderById() {
		String id = null;
		assertFalse(listItemDao.deleteMessageRemainderById(id));
		id = "1213478545";
		assertFalse(listItemDao.deleteMessageRemainderById(id));
	}

	@Test
	public void testDeleteOAWorkflowById() {
		String id = null;
		assertFalse(listItemDao.deleteOAWorkflowById(id));
		assertFalse(listItemDao.deleteOAWorkflowById(""));

		id = "454878124545";
		assertFalse(listItemDao.deleteOAWorkflowById(id));

		listItemDao.insertWorkflow(flow);
		id = listItemDao.getWorkflowByRecompenseId(flow.getRecompenseId())
				.get(0).getId();
		assertTrue(listItemDao.deleteOAWorkflowById(id));
	}

	@Test
	public void testDeleteOAWorkflowByRecompenseId() {
		String id = null;
		assertFalse(listItemDao.deleteOAWorkflowByRecompenseId(id));
		assertFalse(listItemDao.deleteOAWorkflowByRecompenseId(""));

		id = "454878124545";
		assertFalse(listItemDao.deleteOAWorkflowByRecompenseId(id));

		listItemDao.insertWorkflow(flow);
		OAWorkflow oaWorkflow = listItemDao.getWorkflowByRecompenseId(
				flow.getRecompenseId()).get(0);
		assertTrue(listItemDao.deleteOAWorkflowByRecompenseId(oaWorkflow
				.getRecompenseId()));
	}

	@Test
	public void testGetMessageById() {
		// 1.id为空
		String id = null;
		assertNull(listItemDao.getMessageById(id));
		assertNull(listItemDao.getMessageById(""));

		// 2.id不为空
		listItemDao.insertMessage(msg);
		id = listItemDao.getMessageByRecompenseId(msg.getRecompenseId()).get(0)
				.getId();
		assertNotNull(listItemDao.getMessageById(id));
	}

	@Test
	public void testUpdateGoodsTransById() {
		GoodsTrans goodsTrans = new GoodsTrans();

		goodsTrans.setId("");
		assertFalse(listItemDao.updateGoodsTransById(goodsTrans));

		goodsTrans = trans;
		goodsTrans.setId("647045648");
		assertFalse(listItemDao.updateGoodsTransById(goodsTrans));

	}

	@Test
	public void testUpdateAwardItemById() {
		listItemDao.insertAwardItem(aItem);

		AwardItem item = listItemDao.getAwardItemByRecompenseId(
				aItem.getRecompenseId()).get(0);
		assertTrue(listItemDao.updateAwardItemById(item));

		item.setId("54546546");
		assertFalse(listItemDao.updateAwardItemById(item));

		item.setId("");
		assertFalse(listItemDao.updateAwardItemById(item));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#updateIssueItemById(com.deppon.crm.module.recompense.shared.domain.IssueItem)}
	 * .
	 */
	@Test
	public void testUpdateIssueItemById() {

		// IssueItem issueItem =
		listItemDao.insertIssueItem(iItem);

		IssueItem issueItem = listItemDao.getIssueItemByRecompenseId(
				iItem.getRecompenseId()).get(0);
		// issueItem.setId("121212");
		assertTrue(listItemDao.updateIssueItemById(issueItem));

		issueItem.setId("121212");
		assertFalse(listItemDao.updateIssueItemById(issueItem));

		issueItem.setId("");
		assertFalse(listItemDao.updateIssueItemById(issueItem));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#updateMessage(com.deppon.crm.module.recompense.shared.domain.Message)}
	 * .
	 */
	@Test
	public void testUpdateMessage() {

		assertFalse(listItemDao.updateMessage(null));

		// 即时插入一条数据
		listItemDao.insertMessage(msg);

		Message message = listItemDao.getMessageByRecompenseId(
				msg.getRecompenseId()).get(0);
		assertTrue(listItemDao.updateMessage(message));

		message.setId("12121");
		assertFalse(listItemDao.updateMessage(message));

		message.setId("");
		assertFalse(listItemDao.updateMessage(message));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#updateMessageReminder(com.deppon.crm.module.recompense.shared.domain.MessageReminder)}
	 * .
	 */
	@Test
	public void testUpdateMessageReminder() {
		listItemDao.insertMessageReminder(remainder);

		MessageReminder reminder1 = listItemDao
				.getMessageReminderByRecompenseId(remainder.getRecompenseId())
				.get(0);
		assertTrue(listItemDao.updateMessageReminder(reminder1));

		reminder1.setId("");
		assertFalse(listItemDao.updateMessageReminder(reminder1));

		reminder1.setId("1212");
		assertFalse(listItemDao.updateMessageReminder(reminder1));

		reminder1 = null;
		assertFalse(listItemDao.updateMessageReminder(reminder1));

	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#deleteRecallCompByRecompenseId(String)}
	 * .
	 * 
	 * @author zouming
	 */
	public void testDeleteRecallCompByRecompenseId() {

		listItemDao.insertRecalledCompensation(comp);
		RecalledCompensation r = listItemDao
				.getRecalledCompensationByRecompenseId(comp.getRecompenseId())
				.get(0);
		assertFalse(listItemDao.deleteRecallCompByRecompenseId(""));

		assertFalse(listItemDao.deleteRecallCompByRecompenseId("11212454"));

		listItemDao.updateRecalledCompensation(r);

		assertTrue(listItemDao.deleteRecallCompByRecompenseId(r
				.getRecompenseId()));

	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#deletePaymentByRecompenseId(String)}
	 * .
	 * 
	 * @author zouming
	 */
	@Test
	public void testDeletePaymentByRecompenseId() {

		assertFalse(listItemDao.deletePaymentByRecompenseId(null));

		listItemDao.insertPaymentBill(bill);
		PaymentBill paymentBill = listItemDao.getPaymentBillByRecompenseId(
				bill.getRecompenseId()).get(0);

		assertTrue(listItemDao.deletePaymentByRecompenseId(paymentBill
				.getRecompenseId()));

		assertFalse(listItemDao.deletePaymentByRecompenseId("1245186522"));
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.ListItemDaoImpl.dao.impl.ListItemDaoJdbcImpl#updateRecompenseAttachment(com.deppon.crm.module.recompense.shared.domain.RecompenseAttachment)}
	 * .
	 * 
	 * @author zouming
	 */
	// TODO 源代码错误
	@Test
	public void testUpdateRecompenseAttachment() {

		RecompenseAttachment rattachment = null;
		// assertFalse(listItemDao.updateRecompenseAttachment(rattachment));

		listItemDao.insertRecompenseAttachment(attachment);
		// rattachment = listItemDao.getRecompenseAttachmentById(id);
		rattachment = attachment;
		assertTrue(listItemDao.updateRecompenseAttachment(rattachment));

		rattachment.setId("");
		assertFalse(listItemDao.updateRecompenseAttachment(rattachment));

		// rattachment.setId(null);
		// assertFalse(listItemDao.updateRecompenseAttachment(rattachment));

	}

	// 源代码错误 TODO
	@Test
	public void testUpdateResponsibleDept() {
		ResponsibleDept responsibleDept = null;
		listItemDao.insertResponsibleDept(dept);

		responsibleDept = listItemDao.getResponsibleDeptByRecompenseId(
				dept.getRecompenseId()).get(0);
		assertTrue(listItemDao.updateResponsibleDept(responsibleDept));

		responsibleDept.setId("1245633353");
		assertFalse(listItemDao.updateResponsibleDept(responsibleDept));

		// responsibleDept.setId(null);
		// assertTrue(listItemDao.updateResponsibleDept(responsibleDept));

	}
}
