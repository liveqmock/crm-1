/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PlanDaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author Administrator
 * @version 0.1 2012-3-27
 */
package com.deppon.crm.module.marketing.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.manager.DataUtilTest;
import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.dao.IPlanDao;
import com.deppon.crm.module.marketing.server.dao.impl.PlanDao;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.NeedMaintainCustomer;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.PlanDevelopCondition;
import com.deppon.crm.module.marketing.shared.domain.PlanList;
import com.deppon.crm.module.marketing.shared.domain.ReduceCustomer;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PlanDaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author 苏玉军
 * @version 0.1 2012-3-27
 */

public class PlanDaoTest {
	private static IPlanDao planDao;
	
	private static Plan staticPlan;
	
	static {
		planDao = (IPlanDao) SpringTestHelper.get().getBean(PlanDao.class);
	}
	
	@Before
	public void setUp() throws Exception {
//		planDao = (IPlanDao) SpringTestHelper.get().getBean(PlanDao.class);
//		scheduleDao=(IScheduleDao)SpringTestHelper.get().getBean(ScheduleDao.class);
	}
	
	@Test
	public void testCreatePlan(){
		staticPlan = DataUtilTest.getPlanData();
		String id=planDao.createPlan(staticPlan);
		
		System.out.println(id);
	}

	
	@Test
	public void testUpdatePlan(){
		Plan plan= planDao.searchPlanById("40026238");
		plan.setTopic("测试修改主题");
		boolean ret=planDao.updatePlan(plan);
		Assert.assertTrue(ret);
	}

	
	@Test
	public void testSearchScheduleIdByPlanId(){
		String planId = "59";
		List<String> scheduleIds = planDao.searchScheduleIdByPlanId(planId);
		Assert.assertNotNull(scheduleIds);
		System.out.println(scheduleIds);
	}
	
	@Test
	public void testSearchPlanByTheme(){
		String theme = "%发货送积分%";
		List<Plan> plans = planDao.searchPlanByTheme(theme, "1", MarketingConstance.DEVELOP_TYPE);
		//Assert.assertNotNull(plans);
		System.out.println(plans.size());
	}
	
	@Test
	public void testSearchDepartmentPlanList(){
		String execDeptId="13123333";
		List list = planDao.searchDepartmentPlanList(execDeptId, "", MarketingConstance.DEVELOP_TYPE) ;
		String topic = "2012-4-5";
		list = planDao.searchDepartmentPlanList(execDeptId, topic, MarketingConstance.DEVELOP_TYPE) ;
		Assert.assertNotNull(list);
		System.out.println(list.size());		
	}
	
	@Test
	public void testSearchPlanDevelop(){
		Date startd = DateUtils.setDays(new Date(), 5);
		Date endd = DateUtils.setDays(new Date(), 30);
		
		PlanDevelopCondition condition = new PlanDevelopCondition();
//		condition.setDevelopMode("1");
		condition.setExecuteDept("49708");
//		condition.setExecutor("76993");
//		condition.setPlanMaker("386405");
//		condition.setPlanName("测试修");
		condition.setPlanOverDate(endd);
		condition.setPlanStartDate(startd);
		condition.setType(MarketingConstance.DEVELOP_TYPE);
		int start = 0;
		int limit = 10;
		List<?> list = planDao.searchPlan(condition, start, limit);
		System.out.println(list.size());
		Assert.assertNotNull(list);
		
		int count = planDao.getPlanCount(condition);
		System.out.println(count);
		
	}
	
	@Test
	public void testSearchPlanById(){
		Plan plan =  planDao.searchPlanById("395");
//		Assert.assertNotNull(plan);
	}
	@Test
	public void testSearchPlanDetail(){
		String id = "397";
		PlanList pd = planDao.searchPlanDetail(id);
//		Assert.assertNotNull(pd);
	}

//	@Test
//	public void testDelPlanById(){
//		String[] id = {"192","1","2","3","4"}; // 仅提供1次删除
//		boolean rs = planDao.deletePlanDevelop(id);
//		System.out.println(rs);
//		Assert.assertTrue(true);
//	}
	
	@After
	public void tearDown() throws Exception {
		// 清理DAO层测试数据
		//DataTestUtil.cleanDaoData();
	}

	
	@Test
	public void testSearchPlansByStatusList() {
		List<String> notInStatusList = new ArrayList<String>();
		notInStatusList.add(MarketingConstance.FINISHED);
		notInStatusList.add(MarketingConstance.OVERDUE);
		List<Plan> plans = planDao.searchPlansByStatusList(notInStatusList,0,10);
		Assert.assertNotNull(plans);
		Assert.assertTrue(plans.size() > 0);
	}
	
//	@Test
//	public void testUpdatePlanStatusForTimer() {
//		String planId = "192";
//			
//		Plan plan = new Plan();
//		plan.setId(planId);
//		plan.setStatus(MarketingConstance.FINISHED);
//		plan.setModifyUser(MarketingConstance.TIMERUSERID);
//		plan.setModifyDate(new Date());
//		boolean result = planDao.updatePlanStatusForTimer(plan);
//		Assert.assertFalse(result);
//		
//		/*Plan response = planDao.searchPlanById(planId);
//		Assert.assertEquals(MarketingConstance.FINISHED, response.getStatus());
//		Assert.assertEquals(MarketingConstance.TIMERUSERID, response.getModifyUser());*/
//	}
	
	@Test
	public void TestSearchMemberList(){
		try {
			CustomerVo vo = DataUtilTest.getCustomerVO4Dao();
			List<CustomerVo> list=planDao.searchMemberList(vo, 0, 20);
			Assert.assertNotNull(list);
			

			CustomerVo vo2 = new CustomerVo();
			vo2.setDeptId("1");
			vo2.setCustNumbers(new String[]{"000924"});
			planDao.searchMemberCount(vo2);
			list=planDao.searchMemberList(vo2, 0, 20);
			Assert.assertNotNull(list);

			
			CustomerVo vo3 = new CustomerVo();
			vo3.setDeptId("1");
			vo3.setContactIds(new String[]{"1074","111114"});
			list=planDao.searchMemberList(vo3, 0, 20);
			Assert.assertNotNull(list);
			
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
	@Test
	public void testGetPlanContactIdList(){
		List<String> strList=planDao.getPlanContactIdList("618");
	}

	
	@Test
	public void testGetCustomerList(){
		String matid = "1023";
		List<CustomerVo> list2 = planDao.getMaintainMemberList(matid);
		System.out.println(list2.size());
	}
	
	@Test
	public void testQueryDeliverTop50Customer(){
		String queryDate = "201204";
		List<NeedMaintainCustomer> list = planDao.queryDeliverTop50Customer(queryDate);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() >= 0);
//		for (NeedMaintainCustomer c : list) {
//			Assert.assertNotNull("部门ID不能为空，表内的数据有错误", c.getDeptId());
//			Assert.assertNotNull("客户编码不能为空，表内的数据有错误", c.getCustNumber());
//		}
	}
//	
//	@Before
//	public void setUp() throws Exception {
////		planDao = (IPlanDao) SpringTestHelper.get().getBean(PlanDao.class);
////		scheduleDao=(IScheduleDao)SpringTestHelper.get().getBean(ScheduleDao.class);
//	}
//	
//	@Test
//	public void testCreatePlan(){
//		staticPlan = DataUtilTest.getPlanData();
//		String id=planDao.createPlan(staticPlan);
//		
//		System.out.println(id);
//	}
//
//	
//	@Test
//	public void testUpdatePlan(){
//		Plan plan= planDao.searchPlanById(staticPlan.getId());
//		plan.setSchedule(new Date());
//		plan.setTopic("测试修改主题");
//		boolean ret=planDao.updatePlan(plan);
//		Assert.assertTrue(ret);
//	}
//
//	
//	@Test
//	public void testSearchScheduleIdByPlanId(){
//		String planId = "59";
//		List<String> scheduleIds = planDao.searchScheduleIdByPlanId(planId);
//		Assert.assertNotNull(scheduleIds);
//		System.out.println(scheduleIds);
//	}
//	
//	@Test
//	public void testSearchPlanByTheme(){
//		String theme = "%发货送积分%";
//		List<Plan> plans = planDao.searchPlanByTheme(theme, null, MarketingConstance.DEVELOP_TYPE);
//		Assert.assertNotNull(plans);
//		System.out.println(plans.size());
//	}
//	
//	@Test
//	public void testSearchDepartmentPlanList(){
//		String execDeptId="13123333";
//		List list = planDao.searchDepartmentPlanList(execDeptId, "", MarketingConstance.DEVELOP_TYPE) ;
//		String topic = "2012-4-5";
//		list = planDao.searchDepartmentPlanList(execDeptId, topic, MarketingConstance.DEVELOP_TYPE) ;
//		Assert.assertNotNull(list);
//		System.out.println(list.size());		
//	}
//	
//	@Test
//	public void testSearchPlanDevelop(){
//		Date startd = DateUtils.setDays(new Date(), 5);
//		Date endd = DateUtils.setDays(new Date(), 30);
//		
//		PlanDevelopCondition condition = new PlanDevelopCondition();
////		condition.setDevelopMode("1");
//		condition.setExecuteDept("49708");
////		condition.setExecutor("76993");
////		condition.setPlanMaker("386405");
////		condition.setPlanName("测试修");
//		condition.setPlanOverDate(endd);
//		condition.setPlanStartDate(startd);
//		condition.setType(MarketingConstance.DEVELOP_TYPE);
//		int start = 0;
//		int limit = 10;
//		List<?> list = planDao.searchPlan(condition, start, limit);
//		System.out.println(list.size());
//		Assert.assertNotNull(list);
//		
//		int count = planDao.getPlanCount(condition);
//		System.out.println(count);
//		
//	}
//	
//	@Test
//	public void testSearchPlanById(){
//		Plan plan =  planDao.searchPlanById("395");
////		Assert.assertNotNull(plan);
//	}
//	@Test
//	public void testSearchPlanDetail(){
//		String id = "397";
//		PlanList pd = planDao.searchPlanDetail(id);
////		Assert.assertNotNull(pd);
//	}
//
//	@Test
//	public void testDelPlanById(){
//		String[] id = {"192","1","2","3","4"}; // 仅提供1次删除
//		boolean rs = planDao.deletePlanDevelop(id);
//		System.out.println(rs);
//		Assert.assertTrue(true);
//	}
//	
//	@After
//	public void tearDown() throws Exception {
//		// 清理DAO层测试数据
//		//DataTestUtil.cleanDaoData();
//	}
//
//	
//	@Test
//	public void testSearchPlansByStatusList() {
//		List<String> notInStatusList = new ArrayList<String>();
//		notInStatusList.add(MarketingConstance.FINISHED);
//		notInStatusList.add(MarketingConstance.OVERDUE);
//		List<Plan> plans = planDao.searchPlansByStatusList(notInStatusList);
//		Assert.assertNotNull(plans);
//		Assert.assertTrue(plans.size() > 0);
//	}
//	
//	@Test
//	public void testUpdatePlanStatusForTimer() {
//		String planId = "192";
//			
//		Plan plan = new Plan();
//		plan.setId(planId);
//		plan.setStatus(MarketingConstance.FINISHED);
//		plan.setModifyUser(MarketingConstance.TIMERUSERID);
//		plan.setModifyDate(new Date());
//		boolean result = planDao.updatePlanStatusForTimer(plan);
//		Assert.assertFalse(result);
//		
//		/*Plan response = planDao.searchPlanById(planId);
//		Assert.assertEquals(MarketingConstance.FINISHED, response.getStatus());
//		Assert.assertEquals(MarketingConstance.TIMERUSERID, response.getModifyUser());*/
//	}
//	
//	@Test
//	public void TestSearchMemberList(){
//		try {
//			CustomerVo vo = DataUtilTest.getCustomerVO4Dao();
//			List<CustomerVo> list=planDao.searchMemberList(vo, 0, 20);
//			Assert.assertNotNull(list);
//			
//
//			CustomerVo vo2 = new CustomerVo();
//			vo2.setDeptId("1");
//			vo2.setCustNumbers(new String[]{"000924"});
//			planDao.searchMemberCount(vo2);
//			list=planDao.searchMemberList(vo2, 0, 20);
//			Assert.assertNotNull(list);
//
//			
//			CustomerVo vo3 = new CustomerVo();
//			vo3.setDeptId("1");
//			vo3.setContactIds(new String[]{"1074","111114"});
//			list=planDao.searchMemberList(vo3, 0, 20);
//			Assert.assertNotNull(list);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}		
//	}
//	@Test
//	public void testGetPlanContactIdList(){
//		List<String> strList=planDao.getPlanContactIdList("618");
//	}
//
//	
//	@Test
//	public void testGetCustomerList(){
//		String matid = "1023";
//		List<CustomerVo> list2 = planDao.getMaintainMemberList(matid);
//		System.out.println(list2.size());
//	}
//	
//	@Test
//	public void testQueryDeliverTop50Customer(){
//		String queryDate = "201204";
//		List<NeedMaintainCustomer> list = planDao.queryDeliverTop50Customer(queryDate);
//		Assert.assertNotNull(list);
//		Assert.assertTrue(list.size() >= 0);
////		for (NeedMaintainCustomer c : list) {
////			Assert.assertNotNull("部门ID不能为空，表内的数据有错误", c.getDeptId());
////			Assert.assertNotNull("客户编码不能为空，表内的数据有错误", c.getCustNumber());
////		}
//	}
//	
//	@Test
//	public void testQueryRecieveTop50Customer(){
//		String queryDate = "201204";
//		List<NeedMaintainCustomer> list = planDao.queryReceiveTop50Customer(queryDate);
//		Assert.assertNotNull(list);
//		Assert.assertTrue(list.size() >= 0);
//	}
//	
//	@Test
//	public void testQueryLostCustomer(){
//		String queryDate = "201204";
//		List<NeedMaintainCustomer> list = planDao.queryLostCustomer(queryDate);
////		Assert.assertNotNull(list);
////		Assert.assertTrue(list.size() >= 4);
//	}
//	
//	@Test
//	public void testQueryDelayCustomer() {
//		String queryDate = "20121018";
//		List<NeedMaintainCustomer> list = planDao.queryDelayCustomer(queryDate);
//		Assert.assertNotNull(list);
//		Assert.assertTrue(list.size() >= 0);
//	}
//	
//	@Test
//	public void testQueryReduceCustomer(){
//		Calendar c = Calendar.getInstance();
//		c.set(Calendar.YEAR, 2012);
//		c.set(Calendar.MONTH, Calendar.APRIL);
//		c.set(Calendar.DATE, 18);
//		List<ReduceCustomer> list = planDao.queryReduceCustomer(c.getTime());
//		Assert.assertNotNull(list);
//	}
//	
//	@Test
//	public void testQueryExistedCustomerInPlan() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("sdate", new Date());
//		map.put("edate", DateUtils.addDays(new Date(), 6));
//		map.put("planType", MarketingConstance.FIXEDPLAN_DAILY_TYPE);
//		List<String> list = planDao.queryExistedCustomerInPlan(map);
////		Assert.assertNotNull(list);
////		Assert.assertTrue(list.size() > 0);
//	}
//
//	@Test
//	public void testSearchPlan4Job() {
//		List<?> list = planDao.searchPlan4Job();
//		System.out.println(list.size());
//	}
//	
//	@Test
//	public void testGetPlanCreatorList(){
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("executeDept", "11469");
//		planDao.getPlanCreatorList(map);
//
//		map.remove("executeDept");
//		map.put("executeDepts", new String[]{"11469", "1"});
//		planDao.getPlanCreatorList(map);
//	}
//
//	@Test
//	public void testSearchSchedulesByPlanNameDim(){
//
//		Date startd = DateUtils.setDays(new Date(), 5);
//		Date endd = DateUtils.setDays(new Date(), 30);
//		
//		PlanDevelopCondition condition = new PlanDevelopCondition();
//		condition.setPlanName("");
//		condition.setExecuteDepts(new String[]{"11469", "1"});
//		condition.setPlanStartDate(startd);
//		condition.setPlanOverDate(endd);
//		List<String> list = planDao.searchSchedulesByPlanNameDim(condition);
//		
//		System.out.println(list.size());
//	}
//	
//	
//	@Test
//	public void testDestroy(){
//		boolean b = planDao.deletePlanDevelop(new String[]{staticPlan.getId()});
//		System.out.println(b);
//	}
//	
//	@Test
//	public void testQueryPlanCustIdList(){
//		planDao.queryPlanCustIdList("1");
//	}
//	@Test
//	public void testGetDevelopCustomerList(){
//		planDao.getDevelopCustomerList("1");
//	}
//	@Test
//	public void testGetUserByEmpId(){
//		planDao.getUserByEmpId("383579");
//	}
/*	@Test
	public void testCreateMonthlyPlanByJob(){
		planDao.createMonthlyPlanByJob();
	}*/
//	@Test
//	public void testSearchExecuteDep(){
//		Set<String> deptID = new HashSet<String>();
//		for(int i = 11001;i <= 11470;i++){			
//			deptID.add(Integer.toString(i));
//		}
//		System.out.println("xixi"+deptID.size());
//		planDao.searchExecuteDep(deptID, "", 0, 10);
//		planDao.searchExecuteDepCount(deptID, "");
//		for(int i = 11471;i <= 11900;i++){			
//			deptID.add(Integer.toString(i));
//		}
//		System.out.println("haha"+deptID.size());
//		planDao.searchExecuteDep(deptID, "", 0, 10);
//	}
//	@Test
//	public void testSearchPlanDetail(){
//		String id = "397";
//		PlanList pd = planDao.searchPlanDetail(id);
////		Assert.assertNotNull(pd);
//	}
//
//	@Test
//	public void testDelPlanById(){
//		String[] id = {"192","1","2","3","4"}; // 仅提供1次删除
//		boolean rs = planDao.deletePlanDevelop(id);
//		System.out.println(rs);
//		Assert.assertTrue(true);
//	}
//	
//	@After
//	public void tearDown() throws Exception {
//		// 清理DAO层测试数据
//		//DataTestUtil.cleanDaoData();
//	}
//
//	
//	@Test
//	public void testSearchPlansByStatusList() {
//		List<String> notInStatusList = new ArrayList<String>();
//		notInStatusList.add(MarketingConstance.FINISHED);
//		notInStatusList.add(MarketingConstance.OVERDUE);
//		List<Plan> plans = planDao.searchPlansByStatusList(notInStatusList);
//		Assert.assertNotNull(plans);
//		Assert.assertTrue(plans.size() > 0);
//	}
//	
//	@Test
//	public void testUpdatePlanStatusForTimer() {
//		String planId = "192";
//			
//		Plan plan = new Plan();
//		plan.setId(planId);
//		plan.setStatus(MarketingConstance.FINISHED);
//		plan.setModifyUser(MarketingConstance.TIMERUSERID);
//		plan.setModifyDate(new Date());
//		boolean result = planDao.updatePlanStatusForTimer(plan);
//		Assert.assertFalse(result);
//		
//		/*Plan response = planDao.searchPlanById(planId);
//		Assert.assertEquals(MarketingConstance.FINISHED, response.getStatus());
//		Assert.assertEquals(MarketingConstance.TIMERUSERID, response.getModifyUser());*/
//	}
//	
//	@Test
//	public void TestSearchMemberList(){
//		try {
//			CustomerVo vo = DataUtilTest.getCustomerVO4Dao();
//			List<CustomerVo> list=planDao.searchMemberList(vo, 0, 20);
//			Assert.assertNotNull(list);
//			
//
//			CustomerVo vo2 = new CustomerVo();
//			vo2.setDeptId("1");
//			vo2.setCustNumbers(new String[]{"000924"});
//			planDao.searchMemberCount(vo2);
//			list=planDao.searchMemberList(vo2, 0, 20);
//			Assert.assertNotNull(list);
//
//			
//			CustomerVo vo3 = new CustomerVo();
//			vo3.setDeptId("1");
//			vo3.setContactIds(new String[]{"1074","111114"});
//			list=planDao.searchMemberList(vo3, 0, 20);
//			Assert.assertNotNull(list);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}		
//	}
//	@Test
//	public void testGetPlanContactIdList(){
//		List<String> strList=planDao.getPlanContactIdList("618");
//	}
//
//	
//	@Test
//	public void testGetCustomerList(){
//		String matid = "1023";
//		List<CustomerVo> list2 = planDao.getMaintainMemberList(matid);
//		System.out.println(list2.size());
//	}
//	
//	@Test
//	public void testQueryDeliverTop50Customer(){
//		String queryDate = "201204";
//		List<NeedMaintainCustomer> list = planDao.queryDeliverTop50Customer(queryDate);
//		Assert.assertNotNull(list);
//		Assert.assertTrue(list.size() >= 0);
////		for (NeedMaintainCustomer c : list) {
////			Assert.assertNotNull("部门ID不能为空，表内的数据有错误", c.getDeptId());
////			Assert.assertNotNull("客户编码不能为空，表内的数据有错误", c.getCustNumber());
////		}
//	}
//	
//	@Test
//	public void testQueryRecieveTop50Customer(){
//		String queryDate = "201204";
//		List<NeedMaintainCustomer> list = planDao.queryReceiveTop50Customer(queryDate);
//		Assert.assertNotNull(list);
//		Assert.assertTrue(list.size() >= 0);
//	}
//	
//	@Test
//	public void testQueryLostCustomer(){
//		String queryDate = "201204";
//		List<NeedMaintainCustomer> list = planDao.queryLostCustomer(queryDate);
////		Assert.assertNotNull(list);
////		Assert.assertTrue(list.size() >= 4);
//	}
//	
//	@Test
//	public void testQueryDelayCustomer() {
//		String queryDate = "20121018";
//		List<NeedMaintainCustomer> list = planDao.queryDelayCustomer(queryDate);
//		Assert.assertNotNull(list);
//		Assert.assertTrue(list.size() >= 0);
//	}
//	
//	@Test
//	public void testQueryReduceCustomer(){
//		Calendar c = Calendar.getInstance();
//		c.set(Calendar.YEAR, 2012);
//		c.set(Calendar.MONTH, Calendar.APRIL);
//		c.set(Calendar.DATE, 18);
//		List<ReduceCustomer> list = planDao.queryReduceCustomer(c.getTime());
//		Assert.assertNotNull(list);
//	}
//	
//	@Test
//	public void testQueryExistedCustomerInPlan() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("sdate", new Date());
//		map.put("edate", DateUtils.addDays(new Date(), 6));
//		map.put("planType", MarketingConstance.FIXEDPLAN_DAILY_TYPE);
//		List<String> list = planDao.queryExistedCustomerInPlan(map);
////		Assert.assertNotNull(list);
////		Assert.assertTrue(list.size() > 0);
//	}
//
//	@Test
//	public void testSearchPlan4Job() {
//		List<?> list = planDao.searchPlan4Job();
//		System.out.println(list.size());
//	}
//	
//	@Test
//	public void testGetPlanCreatorList(){
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("executeDept", "11469");
//		planDao.getPlanCreatorList(map);
//
//		map.remove("executeDept");
//		map.put("executeDepts", new String[]{"11469", "1"});
//		planDao.getPlanCreatorList(map);
//	}
//	@Test
//	public void testSearchExecuteDep(){
//		Set<String> deptID = new HashSet<String>();
//		for(int i = 11001;i <= 11470;i++){			
//			deptID.add(Integer.toString(i));
//		}
//		System.out.println("xixi"+deptID.size());
//		planDao.searchExecuteDep(deptID, "", 0, 10);
//		planDao.searchExecuteDepCount(deptID, "");
//		for(int i = 11471;i <= 11900;i++){			
//			deptID.add(Integer.toString(i));
//		}
//		System.out.println("haha"+deptID.size());
//		planDao.searchExecuteDep(deptID, "", 0, 10);
//	}
//
//	@Test
//	public void testSearchSchedulesByPlanNameDim(){
//
//		Date startd = DateUtils.setDays(new Date(), 5);
//		Date endd = DateUtils.setDays(new Date(), 30);
//		
//		PlanDevelopCondition condition = new PlanDevelopCondition();
//		condition.setPlanName("");
//		condition.setExecuteDepts(new String[]{"11469", "1"});
//		condition.setPlanStartDate(startd);
//		condition.setPlanOverDate(endd);
//		List<String> list = planDao.searchSchedulesByPlanNameDim(condition);
//		
//		System.out.println(list.size());
//	}
//	
	
	@Test
	public void testDestroy(){
		boolean b = planDao.deletePlanDevelop(new String[]{staticPlan.getId()});
		System.out.println(b);
	}
	
	@Test
	public void testQueryPlanCustIdList(){
		planDao.queryPlanCustIdList("1");
	}
//	@Test
//	public void testGetDevelopCustomerList(){
//		planDao.getDevelopCustomerList("1");
//	}
	@Test
	public void testGetUserByEmpId(){
		planDao.getUserByEmpId("383579");
	}
////	@Test
////	public void testCreateMonthlyPlanByJob(){
////		planDao.createMonthlyPlanByJob();
////	}
	@Test
	public void testSearchExecuteDep(){
		Set<String> deptID = new HashSet<String>();
		for(int i = 11001;i <= 11470;i++){			
			deptID.add(Integer.toString(i));
		}
		System.out.println("xixi"+deptID.size());
		planDao.searchExecuteDep(deptID, "", 0, 10);
		planDao.searchExecuteDepCount(deptID, "");
		for(int i = 11471;i <= 11900;i++){			
			deptID.add(Integer.toString(i));
		}
		System.out.println("haha"+deptID.size());
		planDao.searchExecuteDep(deptID, "", 0, 10);
	}
	@Test
	public void testsearchEmployeeByCode(){
		Employee emp = planDao.searchEmployeeByCode("531093");
		System.out.println(emp);
	}
}
