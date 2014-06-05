package com.deppon.crm.module.marketing.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.IMarketAssessManager;
import com.deppon.crm.module.marketing.server.manager.impl.MarketAssessManagerImpl;
import com.deppon.crm.module.marketing.shared.domain.AssessAuthority;
import com.deppon.crm.module.marketing.shared.domain.CustDevAssess;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;

public class MarketAssessManagerTest {
	private IMarketAssessManager marketAssess;
	private User user;
	@Before
	public void setUp(){
		marketAssess = (IMarketAssessManager) SpringTestHelper.get().getBean(MarketAssessManagerImpl.class);
		user = new User();
		
		Set<String> depts = new HashSet<String>();
		//103,104,265,622,48939,194778,49084
		depts.add("103");
		depts.add("104");
		depts.add("265");
		depts.add("622");
		depts.add("48939");
		depts.add("194778");
		depts.add("49084");
		
		Set<String> auths = new HashSet<String>();
		auths.add("2001");
		auths.add("2002");
		auths.add("2003");
		auths.add("2004");
		auths.add("2005");
		auths.add("0");
		auths.add("123");
		Employee e = new Employee();
		e.setEmpCode("003998");
		Department d = new Department();
		d.setDeptSeq(".103.104.265.475.50502.11500.11469.");
		e.setDeptId(d);
		user.setEmpCode(e);
		user.setRoleids(auths);
		user.setDeptids(depts);
	}
	@Test
	public void testCreateAssessAuth(){
		AssessAuthority assAuth = marketAssess.createAssessAuth(user);
		

	}
	@Test
	public void testSearchCustDevByAuth(){
		List<String> deptids = new ArrayList<String>();
		deptids.add("194778");
		//deptids.add("622");
		marketAssess.searchCustDevByAuth( deptids,new Date(System.currentTimeMillis()-1000*60*60*24*2), user,null,"ALL", 0, 10);
	}
	@Test
	public void testSearchCustMatByAuth(){
		List<String> deptids = new ArrayList<String>();
		deptids.add("194778");

		marketAssess.searchCustMatByAuth( deptids,new Date(System.currentTimeMillis()-1000*60*60*24*2), user, 0, 10);
	}
//	@Test
//	public void testOutputDevAssessExcelByPage(){
//		List<String> deptids = new ArrayList<String>();
//		deptids.add("265");
//		String fileName = "aaaa";
//		String filePath ="E:";
//		Date date = new Date(System.currentTimeMillis()-1000*60*60*24*3);
//		marketAssess.outputDevAssessExcelByPage(fileName, filePath,deptids, date, user);
//	}
//	@Test
//	public void testOutputMatAssessExcelByAuth(){
//		String fileName = "bbaa";
//		String filePath ="E:";
//		Date date = new Date(System.currentTimeMillis()-1000*60*60*24*3);
//		marketAssess.outputMatAssessExcelByAuth(fileName, filePath, date, user);
//	}
//	@Test
//	public void testOutputDevAssessExcelByAuth(){
//		String fileName = "bbaa";
//		String filePath ="E:";
//		Date date = new Date(System.currentTimeMillis()-1000*60*60*24*2);
//		marketAssess.outputDevAssessExcelByAuth(fileName, filePath, date, user);
//	}
	@Test
	public void testAutoCreateMarketReport(){
		marketAssess.autoCreateMarketReport();
	}
//	
}
