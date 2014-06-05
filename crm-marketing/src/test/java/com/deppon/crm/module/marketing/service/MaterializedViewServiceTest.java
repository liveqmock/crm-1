/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PlanServiceTest.java
 * @package com.deppon.crm.module.marketing.service 
 * @author Administrator
 * @version 0.1 2012-3-29
 */
package com.deppon.crm.module.marketing.service;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.service.IMaterviewService;
import com.deppon.crm.module.marketing.server.service.impl.MaterviewServiceImpl;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
public class MaterializedViewServiceTest {
	private IMaterviewService vmservice;
	
	@Before
	public void setUp() throws Exception {
		vmservice = (MaterviewServiceImpl) SpringTestHelper.get().getBean(
				MaterviewServiceImpl.class);
	}
//	@Test
//	public void reFreshMaterView(){
//		vmservice.reFreshMVCuststomerMonthly();
//		vmservice.reFreshMVCuststomerDaily();
//		vmservice.reFreshMVPSCustomerDaily();
//	}
//	/**
//	 * <p>
//	 * Description:这里写描述<br />
//	 * </p>
//	 * @author 苏玉军
//	 * @version 0.1 2012-3-29
//	 * @throws java.lang.Exception
//	 * void
//	 */
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	/**
//	 * Test method for {@link com.deppon.crm.module.marketing.server.service.impl.PlanService#searchDepartmentPlanList(java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	public void testSearchDepartmentPlanList() {
//		String execDeptId="1313888";
//		String planType="dev";
//		List<Plan> list =  planService.searchDepartmentPlanList(execDeptId, null, planType);
//		Assert.assertNotNull(list);
//	}
//
//	/**
//	 * Test method for {@link com.deppon.crm.module.marketing.server.service.impl.PlanService#createPlan(com.deppon.crm.module.marketing.shared.domain.Plan)}.
//	 */
//	@Test
//	public void testCreatePlan() {
//		Plan plan = DataUtilTest.getPlanData();
//		String s =planService.createPlan(plan);
//		Assert.assertNotNull(s);
//		
//	}
//
//	/**
//	 * Test method for {@link com.deppon.crm.module.marketing.server.service.impl.PlanService#searchScheduleIdByPlanId(java.lang.String)}.
//	 */
//	@Test
//	public void testSearchScheduleIdByPlanId() {
//		List<String> list = planService.searchScheduleIdByPlanId("408");
//		for(String str:list){
//			System.out.println(str);
//		}
//		Assert.assertEquals(0, list.size());
//	}
//
//	/**
//	 * Test method for {@link com.deppon.crm.module.marketing.server.service.impl.PlanService#searchPlanByTheme(java.lang.String, java.util.Set)}.
//	 */
//	@Test
//	public void testSearchPlanByTheme() {
//		//fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link com.deppon.crm.module.marketing.server.service.impl.PlanService#updatePlan(com.deppon.crm.module.marketing.shared.domain.Plan)}.
//	 */
//	@Test
//	public void testUpdatePlan() {
//
//		try {
//			Plan plan = planService.getPlanById("400000712");
//			plan.setTopic("测试更新Plan");
//			boolean b=  planService.updatePlan(plan);
//			Assert.assertTrue(b);
//			plan= planService.getPlanById("400000712");
//			Assert.assertEquals("测试更新Plan", plan.getTopic());
//		} catch (GeneralException e) {
//			System.out.println(e.getMessage());
//		}
//	}
//
//	@Test
//	public void testGetMemberList(){
//		List<?> l1 = planService.getMemberList("1061", MarketingConstance.DEVELOP_TYPE);
//		Assert.assertNotNull(l1);
//		
//		List<?> l2 = planService.getMemberList("1092", MarketingConstance.MAINTAIN_TYPE);
//		Assert.assertNotNull(l2);
//		
//	}
//	
//	@Test
//	public void testSearchPlan(){
//		PlanDevelopCondition condition = new PlanDevelopCondition();
//		try {
//			Date s1 = DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 3);
//			Date s2 = DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 5);
//			condition.setPlanStartDate(s1);
//			condition.setPlanOverDate(s2);
//			condition.setType(MarketingConstance.DEVELOP_TYPE);
//			condition.setDevelopMode("1");
//			planService.searchPlan(condition, 0, 10);
//		} catch (GeneralException e) {
//			// TODO: handle exception
//		}
//		try {
//			Date s1 = DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 2);
//			Date s2 = DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 2);
//			condition.setPlanStartDate(s1);
//			condition.setPlanOverDate(s2);
//			condition.setPlanName("1");
//			condition.setType(MarketingConstance.DEVELOP_TYPE);
//			condition.setDevelopMode("1");
//			planService.searchPlan(condition, 0, 10);
//		} catch (GeneralException e) {
//			// TODO: handle exception
//		}
//	}
}
