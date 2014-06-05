package com.deppon.crm.module.marketing.dao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.dao.impl.MarketAssessDaoImpl;
import com.deppon.crm.module.marketing.shared.domain.AssDeptPriCondition;
import com.deppon.crm.module.marketing.shared.domain.AssessDept;
import com.deppon.crm.module.marketing.shared.domain.AssessDeptPrincipal;
import com.deppon.crm.module.marketing.shared.domain.AuthAllSalCondition;
import com.deppon.crm.module.marketing.shared.domain.CustDevAssess;
import com.deppon.crm.module.marketing.shared.domain.CustMatAssess;
import com.deppon.crm.module.marketing.shared.domain.MarketAssessConstance;
import com.deppon.crm.module.marketing.shared.domain.SearchAssessCondition;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Department;

/**   
 * <p>
 * Description:营销评估DAO测试类<br />
 * </p>
 * @title MarketAssessDaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author ZhouYuan
 * @version 2013-01-21
 */

public class MarketAssessDaoTest {
	private MarketAssessDaoImpl marketAssessDaoImpl;
	@Before
	public void setUp(){	
		marketAssessDaoImpl = (MarketAssessDaoImpl) SpringTestHelper.get().getBean(MarketAssessDaoImpl.class);
	}
	@Test
	public void testSearchDepts(){
		String ss = ".103.104.265.754.626.10945.49082.";
		ss = ss.substring(1);
		String[] s = ss.split("\\.");
		System.out.println(Arrays.toString(s));
		List<AssessDept> depts= marketAssessDaoImpl.searchDepts(Arrays.asList(s));
		System.out.println(depts);
	}
//	@Test
//	public void testCreateDevMarketReport() throws ParseException{
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
////		Date beginDate = sdf.parse("2013-01-01");
////		Date endDate = sdf.parse("2013-01-21");
//		Date beginDate = sdf.parse("2013-01-01");
//		Date endDate = sdf.parse("2013-01-01");
//		marketAssessDaoImpl.createDevMarketReport(beginDate, endDate);
//	}

	@Test
	public void testSearchCustDevByAuth(){
		
		SearchAssessCondition devCondition = new SearchAssessCondition();
		devCondition.setGroupDept(new AssessDept());
		devCondition.getGroupDept().setAuthority("Y");
		devCondition.getGroupDept().setHierarchy(MarketAssessConstance.AUTHOR_BUDEPT);
		devCondition.setSearchDept(new AssessDept());
		devCondition.getSearchDept().setAuthority("Y");
		devCondition.getSearchDept().setHierarchy(MarketAssessConstance.AUTHOR_SERCENTER);
		devCondition.getSearchDept().setId("265");
		devCondition.setDateStr("20130121");
		List<CustDevAssess> list =marketAssessDaoImpl.searchCustDevByAuth(devCondition, 0, 10);
		
		devCondition = new SearchAssessCondition();
		devCondition.setGroupDept(new AssessDept());
		devCondition.getGroupDept().setAuthority("Y");
		devCondition.getGroupDept().setHierarchy(MarketAssessConstance.AUTHOR_REGION);
		devCondition.setSearchDept(new AssessDept());
		devCondition.getSearchDept().setAuthority("Y");
		devCondition.getSearchDept().setHierarchy(MarketAssessConstance.AUTHOR_BUDEPT);
		devCondition.getSearchDept().setId("463");
		devCondition.setDateStr("20130121");
		list =marketAssessDaoImpl.searchCustDevByAuth(devCondition, 0, 10);
		
		devCondition = new SearchAssessCondition();
		devCondition.setGroupDept(new AssessDept());
		devCondition.getGroupDept().setAuthority("Y");
		devCondition.getGroupDept().setHierarchy(MarketAssessConstance.AUTHOR_BUQUARTER);
		devCondition.setSearchDept(new AssessDept());
		devCondition.getSearchDept().setAuthority("Y");
		devCondition.getSearchDept().setHierarchy(MarketAssessConstance.AUTHOR_REGION);
		devCondition.getSearchDept().setId("274225");
		devCondition.setDateStr("20130121");
		list =marketAssessDaoImpl.searchCustDevByAuth(devCondition, 0, 10);
		
		devCondition = new SearchAssessCondition();
		devCondition.setGroupDept(new AssessDept());
		devCondition.getGroupDept().setAuthority("Y");
		devCondition.getGroupDept().setHierarchy(MarketAssessConstance.AUTHOR_SALDEPT);
		devCondition.setSearchDept(new AssessDept());
		devCondition.getSearchDept().setAuthority("Y");
		devCondition.getSearchDept().setHierarchy(MarketAssessConstance.AUTHOR_BUQUARTER);
		devCondition.getSearchDept().setId("11442");
		devCondition.setDateStr("20130121");
		list =marketAssessDaoImpl.searchCustDevByAuth(devCondition, 0, 10);
	}
	@Test
	public void testSearchAmountCustDevByAuth(){
		SearchAssessCondition devCondition = new SearchAssessCondition();
		devCondition.setGroupDept(new AssessDept());
		devCondition.getGroupDept().setAuthority("Y");
		devCondition.getGroupDept().setHierarchy(MarketAssessConstance.AUTHOR_BUDEPT);
		devCondition.setSearchDept(new AssessDept());
		devCondition.getSearchDept().setAuthority("Y");
		devCondition.getSearchDept().setHierarchy(MarketAssessConstance.AUTHOR_SERCENTER);
		devCondition.getSearchDept().setId("265");
		List<CustDevAssess> list =marketAssessDaoImpl.searchAmountCustDevByAuth(devCondition);
	}
	@Test
	public void testSearchCustMatByAuth(){
		SearchAssessCondition devCondition = new SearchAssessCondition();
		devCondition.setGroupDept(new AssessDept());
		devCondition.getGroupDept().setAuthority("Y");
		devCondition.getGroupDept().setHierarchy(MarketAssessConstance.AUTHOR_BUDEPT);
		devCondition.setSearchDept(new AssessDept());
		devCondition.getSearchDept().setAuthority("Y");
		devCondition.getSearchDept().setHierarchy(MarketAssessConstance.AUTHOR_SERCENTER);
		devCondition.getSearchDept().setId("265");
		devCondition.setDateStr("20130121");
		List<CustMatAssess> list =marketAssessDaoImpl.searchCustMatByAuth(devCondition, 0, 10);
		
		devCondition = new SearchAssessCondition();
		devCondition.setGroupDept(new AssessDept());
		devCondition.getGroupDept().setAuthority("Y");
		devCondition.getGroupDept().setHierarchy(MarketAssessConstance.AUTHOR_REGION);
		devCondition.setSearchDept(new AssessDept());
		devCondition.getSearchDept().setAuthority("Y");
		devCondition.getSearchDept().setHierarchy(MarketAssessConstance.AUTHOR_BUDEPT);
		devCondition.getSearchDept().setId("463");
		devCondition.setDateStr("20130121");
		list =marketAssessDaoImpl.searchCustMatByAuth(devCondition, 0, 10);
		
		devCondition = new SearchAssessCondition();
		devCondition.setGroupDept(new AssessDept());
		devCondition.getGroupDept().setAuthority("Y");
		devCondition.getGroupDept().setHierarchy(MarketAssessConstance.AUTHOR_BUQUARTER);
		devCondition.setSearchDept(new AssessDept());
		devCondition.getSearchDept().setAuthority("Y");
		devCondition.getSearchDept().setHierarchy(MarketAssessConstance.AUTHOR_REGION);
		devCondition.getSearchDept().setId("274225");
		devCondition.setDateStr("20130121");
		list =marketAssessDaoImpl.searchCustMatByAuth(devCondition, 0, 10);
		
		devCondition = new SearchAssessCondition();
		devCondition.setGroupDept(new AssessDept());
		devCondition.getGroupDept().setAuthority("Y");
		devCondition.getGroupDept().setHierarchy(MarketAssessConstance.AUTHOR_SALDEPT);
		devCondition.setSearchDept(new AssessDept());
		devCondition.getSearchDept().setAuthority("Y");
		devCondition.getSearchDept().setHierarchy(MarketAssessConstance.AUTHOR_BUQUARTER);
		devCondition.getSearchDept().setId("11442");
		devCondition.setDateStr("20130121");
		list =marketAssessDaoImpl.searchCustMatByAuth(devCondition, 0, 10);
	}
	@Test
	public void testCountCustDevByAuth(){
		SearchAssessCondition devCondition = new SearchAssessCondition();
		devCondition.setGroupDept(new AssessDept());
		devCondition.getGroupDept().setAuthority("Y");
		devCondition.getGroupDept().setHierarchy(MarketAssessConstance.AUTHOR_BUDEPT);
		devCondition.setSearchDept(new AssessDept());
		devCondition.getSearchDept().setAuthority("Y");
		devCondition.getSearchDept().setHierarchy(MarketAssessConstance.AUTHOR_SERCENTER);
		devCondition.getSearchDept().setId("265");
		devCondition.setDateStr("20130121");
		int count =marketAssessDaoImpl.countCustDevByAuth(devCondition);
		System.out.println(count);
	}
	@Test
	public void testSearchCustDevAllSalDept(){
		AuthAllSalCondition allCondition = new AuthAllSalCondition();
		allCondition.setDeptId("265");
		allCondition.setAuthority("5");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(new Date());
		allCondition.setDateStr(dateStr);
		List<CustDevAssess> list = marketAssessDaoImpl.searchCustDevAllSalDept(allCondition);
		System.out.println(list);
	}
	@Test
	public void testCountCustMatByAuth(){
		SearchAssessCondition devCondition = new SearchAssessCondition();
		devCondition.setGroupDept(new AssessDept());
		devCondition.getGroupDept().setAuthority("Y");
		devCondition.getGroupDept().setHierarchy(MarketAssessConstance.AUTHOR_BUDEPT);
		devCondition.setSearchDept(new AssessDept());
		devCondition.getSearchDept().setAuthority("Y");
		devCondition.getSearchDept().setHierarchy(MarketAssessConstance.AUTHOR_SERCENTER);
		devCondition.getSearchDept().setId("265");
		devCondition.setDateStr("20130121");
		int count =marketAssessDaoImpl.countCustMatByAuth(devCondition);
		System.out.println(count);
	}
//	@Test
//	public void testSearchCustMatAllSalDept(){
//		AuthAllSalCondition allCondition = new AuthAllSalCondition();
//		allCondition.setDeptId("265");
//		allCondition.setAuthority("2005");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String dateStr = sdf.format(new Date(System.currentTimeMillis()-1000*60*60*24*2));
//		allCondition.setDateStr(dateStr);
//		List<CustMatAssess> list = marketAssessDaoImpl.searchCustMatAllSalDept(allCondition);
//		System.out.println(list);
//	}

//	@Test
//	public void testCreateMatMarketReport() throws ParseException{
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
////		Date beginDate = sdf.parse("2012-11-01");
////		Date endDate = sdf.parse("2012-11-31");
//		Date beginDate = sdf.parse("2013-01-01");
//		Date endDate = sdf.parse("2013-01-01");
//		marketAssessDaoImpl.createMatMarketReport(beginDate, endDate);
//	}
	@Test
	public void testSearchmanagerDept(){
		String deptName = "%本部";
		List<Department> dept = marketAssessDaoImpl.searchmanagerDept(deptName);
		Assert.assertNotNull(dept);
	}
	@Test
	public void testSearchDeptsByParentId(){
		String parentId = "13669"; // 华东经营事业部
		List<Department> dept = marketAssessDaoImpl.searchDeptsByParentId(parentId);
		Assert.assertNotNull(dept);
	}
	@Test
	public void testSearchDeptPrincipal(){
		AssDeptPriCondition adpc = new AssDeptPriCondition();
		adpc.setEmpCode("003998");
		List<AssessDeptPrincipal> dept= marketAssessDaoImpl.searchDeptPrincipal(adpc);
		Assert.assertNotNull(dept);
	}
}
