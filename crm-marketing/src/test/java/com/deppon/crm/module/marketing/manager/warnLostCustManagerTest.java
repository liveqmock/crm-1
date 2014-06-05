package com.deppon.crm.module.marketing.manager;

import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.deppon.crm.module.marketing.server.manager.IWarnLostCustManager;
import com.deppon.crm.module.marketing.server.manager.impl.WarnLostCustManagerImp;
import com.deppon.crm.module.marketing.shared.domain.WarnLostCust;
import com.deppon.crm.module.marketing.shared.domain.WarnLostMailAccount;
import com.deppon.crm.module.marketing.shared.domain.WarnStatusUpdateInfo;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;

public class warnLostCustManagerTest {
	public IWarnLostCustManager warnLostCustManager;

	@Before
	public void setUp() throws Exception {
		warnLostCustManager = SpringTestHelper.get().getBean(
				WarnLostCustManagerImp.class);
	}

	/*
	 * @Test public void createWarnLostTest(){
	 * warnLostCustManager.createWarnList(); }
	 */
	@Test
	public void searchWarnLostTest() throws Exception {
		String deptStandardCode = "DP10272";
		String level = WarnLostCust.CAREEA;
		String[] para = new String[] { WarnLostCust.DIAMOND };
		// Date beginTime=new Date();
		// Date endTime=new Date();
		warnLostCustManager.searchWarnLostCustInfo(156, "");
		warnLostCustManager.searchWarnLostCust(deptStandardCode, level, para,
				new Date(), new Date());
		List<WarnLostCust> s = warnLostCustManager.searchWarnLostCust(
				deptStandardCode, level, para, null, null);
		// System.out.println(s.get(0).getCadreStandardName());
		// ExportExcel.resultSetToExcel(WarnLostCust.HEAD, WarnLostCust.KEYS, s,
		// "RTT", "SHEET1");
	}

	@Test
	public void getDetpGroupFromWarnLostTest() {
		String level = WarnLostCust.AREA;
		String[] para = new String[] { WarnLostCust.GOLD, WarnLostCust.DIAMOND,
				WarnLostCust.PLATINUM };
		List<WarnLostMailAccount> s = warnLostCustManager
				.getDetpGroupFromWarnLost(level, para, "");
		System.out.print(s);
	}

	@Test
	public void updateWarnLostTest() {
		WarnStatusUpdateInfo updateInfo = new WarnStatusUpdateInfo(634563,
				"AAAA", new Date(), new Date(), "123", "123M", 12, "436311", 0);
		try {
			warnLostCustManager.updateWarnInfo(updateInfo);
		} catch (Exception e) {

		}
	}

	/*
	 * @Test public void sendEmailTest(){ try {
	 * ((WarnLostCustManagerImp)warnLostCustManager).sendMial(); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */
	@Test
	public void getWarnInfoTest() {
		warnLostCustManager.searchWarnLostCustInfo(1667, "");
	}

	@Test
	public void getDetpFrom() {
		warnLostCustManager.searchWarnLostCustInfo(1667, "");
	}

	@Test
	public void testSearchWarnLostCustFor360() {
		warnLostCustManager.searchWarnLostCustFor360View("", "001192", "",
				new Date(), new Date());
	}

}
