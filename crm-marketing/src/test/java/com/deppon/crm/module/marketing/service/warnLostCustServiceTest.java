package com.deppon.crm.module.marketing.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import com.deppon.crm.module.marketing.server.service.IWarnLostCustSerivce;
import com.deppon.crm.module.marketing.server.service.impl.WarnLostCustSerivceImp;
import com.deppon.crm.module.marketing.server.utils.ExportExcel;
import com.deppon.crm.module.marketing.shared.domain.WarnLostCust;
import com.deppon.crm.module.marketing.shared.domain.WarnLostMailAccount;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;

public class warnLostCustServiceTest {
	public IWarnLostCustSerivce warnLostCustSerivce;

	@Before
	public void setUp() throws Exception {
		warnLostCustSerivce = SpringTestHelper.get().getBean(
				WarnLostCustSerivceImp.class);
	}

//	@Test
//	public void createWarnLostTest() {
//		warnLostCustSerivce.createWarnList();
//	}

	@Test
	public void searchWarnLostTest() throws Exception {
//		String deptStandardCode = "DP10272";
//		String level = WarnLostCust.CAREEA;
//		String empCode = "";
//		String[] para = new String[] { WarnLostCust.DIAMOND };
//		// Date beginTime=new Date();
//		// Date endTime=new Date();
//		List<WarnLostCust> s = warnLostCustSerivce.searchWarnLostCust(
//				deptStandardCode, empCode, level, para, null, null);
//		// System.out.println(s.get(0).getCadreStandardName());
//		ExportExcel.resultSetToExcel(WarnLostCust.HEAD, WarnLostCust.KEYS, s,
//				"RTT", "SHEET1");
	}

	@Test
	public void getDetpGroupFromWarnLostTest() {
//		String level = WarnLostCust.AREA;
//		String[] para = new String[] { WarnLostCust.GOLD, WarnLostCust.DIAMOND,
//				WarnLostCust.PLATINUM };
//		List<WarnLostMailAccount> s = warnLostCustSerivce
//				.getDetpGroupFromWarnLost(level, para);
//		System.out.print(s);
	}
}
