package com.deppon.crm.module.recompense.server.manager;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.recompense.server.util.TestDataCreator;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.Waybill;

public class RecompenseInterfaceTest extends TestCase {
	static ClassPathXmlApplicationContext factory;
	private static RecompenseManager recompenseManager;
	private static RecompenseService recompenseService;

	static {
//		AppContext.initAppContext("application", "");
		// String resource =
		// "classpath*:com/deppon/crm/module/crm-recompense/server/META-INF/spring.xml";
//		String[] resource = { "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml" };
//		factory = new ClassPathXmlApplicationContext(resource);
//		recompenseManager = (RecompenseManager) factory
//				.getBean("recompenseManager");
//		recompenseService = (RecompenseService) factory
//				.getBean("recompenseService");
		recompenseManager = TestUtil.recompenseManager;
		recompenseService = TestUtil.recompenseService;
	}

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void xtestGetWaybillInfoByNo_AbnormalSign() {
		String voucherNo = "12650003";
		Waybill waybill;
		waybill = recompenseManager.getWaybillInfoByNo(voucherNo,
				Constants.ABNORMAL_SIGN);
		assertNotNull(waybill);
	}

	@Test
	public void xtestGetWaybillInfoByNo_LostGoods() {
		String voucherNo = "37762012";
		Waybill waybill;
		waybill = recompenseManager.getWaybillInfoByNo(voucherNo,
				Constants.LOST_GOODS);
		assertNotNull(waybill);
	}

	@Test
	public void xtestGetWaybillInfoByNo_Unbilled() {
		String voucherNo = "201112957159";
		Waybill waybill;
		waybill = recompenseManager.getWaybillInfoByNo(voucherNo,
				Constants.UNBILLED);
		assertNotNull(waybill);
	}

	@Test
	public void xtestSubmitRecompenseOaApproval() {
		RecompenseApplication app = TestDataCreator.createReportApp();
		boolean r = recompenseService.submitRecompenseOaApproval(app, null,
				null, null, "");
		assertTrue(r);
	}

	@Test
	public void xtestSubmitRecompenseOverpayApproval() {
		RecompenseApplication app = TestDataCreator.createReportApp();
		app.setId("123456");
		boolean r = recompenseService.submitRecompenseOverpayApproval(app,
				null, null, null);
		assertTrue(r);
	}

	@Test
	public void xtestSubmitRecompensePaymentApproval() {
		RecompenseApplication app = TestDataCreator.createReportApp();
		boolean r = recompenseService.submitRecompensePaymentApproval(app,
				null, null);
		assertTrue(r);
	}

	@Test
	public void xtestSendSms() {
		recompenseService.sendSms("13472528129", "CRM Testing");
	}

	@Test
	public void testSendSmsInfo() {
		//recompenseService.sendSmsInfo("13472528129", "CRM Testing", "005072", "DP01563");
	}

	@Test
	public void tester() {
	}

}
