/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title TestPlanManager.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author Administrator
 * @version 0.1 2012-3-28
 */
package com.deppon.crm.module.marketing.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.service.IMemberService;
import com.deppon.crm.module.customer.server.service.impl.MemberService;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.manager.IPlanManager;
import com.deppon.crm.module.marketing.server.manager.IScheduleManager;
import com.deppon.crm.module.marketing.server.manager.impl.PlanManager;
import com.deppon.crm.module.marketing.server.manager.impl.ScheduleManager;
import com.deppon.crm.module.marketing.shared.domain.CustomerList;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.PlanDevelopCondition;
import com.deppon.crm.module.marketing.shared.domain.PlanList;
import com.deppon.crm.module.marketing.shared.domain.ReduceCustomer;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;


public class PlanManagerTest {
	private static IPlanManager planManager;
	private static IScheduleManager scheduleManager;
	private static JdbcTemplate jdbc;
	private static Plan plan=new Plan();
	private static Plan maintainPlan=new Plan();
	private static String[] custList;
	private static String[] contactIds;
	private  String planType=MarketingConstance.DEVELOP_TYPE;
	private static IMemberService memberService;
	
	static User user;
	

	private static void createTestMember(int n) {
		int customerNumber = 223230;
		int deptId = 14731;
		int custId = 192;
		
		for (int i = 0; i < n; i++) {
			Member m = new Member();
			m.setId(String.valueOf(custId));
			m.setCustId(m.getId());
			m.setCustName("Five Kilometre Test Customer");
			m.setCustNumber(String.valueOf(customerNumber));
			m.setDegree("Golden");
			m.setCustType("MEMBER");
			m.setIsSpecial(true);
			m.setBussType("busstype");
			m.setIsTranGoods(true);
			m.setIsRedeempoints(true);
			m.setDeptId(String.valueOf(deptId));
			m.setCustStatus("1");

			ShuttleAddress sa = new ShuttleAddress();
			sa.setAddress("上海市明珠路1018号");
			List<ShuttleAddress> sal = new ArrayList<ShuttleAddress>();
			sal.add(sa);
			m.setShuttleAddressList(sal);
			
			Contact c = new Contact();
			c.setCustId(m.getCustId());
			c.setNumber(String.valueOf(customerNumber * 2));
			c.setLinkmanType("1");
			c.setIsMainLinkMan(true);
			List<Contact> cl = new ArrayList<Contact> ();
			cl.add(c);
			m.setContactList(cl);
			
			Account a = new Account();
			a.setBank("工商银行");
			a.setAccountUse("收款");
			List<Account> al = new ArrayList<Account> ();
			al.add(a);
			m.setAccountList(al);
			try {
				memberService.deleteMemberById(m.getId());
				
				memberService.createMember(m);		
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			customerNumber++;
			custId++;
			deptId++;
		}
	}
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		user = new User();
		user.setId("1");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("238217", null);
		map.put("1", null);
		map.put("250218", null);
		map.put("22241", null);
		map.put("49708", null);
		Set<String> ids = map.keySet();
		user.setDeptids(ids);
		Employee emp = new Employee();
		emp.setId("1");
		Department dept=new Department();
		dept.setId("1");
		emp.setDeptId(dept);
		user.setEmpCode(emp);
		UserContext.setCurrentUser(user);
		
		planManager = (IPlanManager) SpringTestHelper.get().getBean(PlanManager.class);
		scheduleManager = (IScheduleManager) SpringTestHelper.get().getBean(ScheduleManager.class);
		plan=DataUtilTest.getPlanData();
		maintainPlan = DataUtilTest.getMaintainPlanData();
		custList=DataUtilTest.getCustList();
		contactIds = DataUtilTest.getContactIds();
		memberService = (IMemberService) SpringTestHelper.get().getBean(MemberService.class);
				

		// test for timer
//		jdbc = (JdbcTemplate) SpringTestHelper.get().getBean(JdbcTemplate.class);
//		jdbc.update("delete from t_cust_developmaintainlist where fid between 100 and 200");
//		jdbc.update("delete from t_cust_schedule where fid between 100 and 200");
//		jdbc.update("delete from T_CRM_DELIVERTOP50CUSTMER where fid between 100 and 200");
//		jdbc.update("delete from T_CRM_RECEIVETOP50CUSTMER where fid between 100 and 200");
//		jdbc.update("delete from T_CRM_DELIVEROVERDUE where fid between 100 and 200");
		
		// 创建测试客户
		createTestMember(5);		
		
		Calendar yesterday = Calendar.getInstance();
		Calendar tomorrow   = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		yesterday.add(Calendar.DATE, -1);
		tomorrow.add(Calendar.DATE, 1);
		
//		String insertPlan = "insert into t_cust_developmaintainlist (fid, fbegintime, fendtime, fplantype, fstatus, FCREATEUSERID) values(?, ?, ?, ?, ?, 1)";
//		jdbc.update(insertPlan, new Object[]{ 100, yesterday, tomorrow, MarketingConstance.DEVELOP_TYPE, MarketingConstance.NOTEXECUTE});
//		jdbc.update(insertPlan, new Object[]{ 101, yesterday, tomorrow, MarketingConstance.DEVELOP_TYPE, MarketingConstance.EXECUTING});
//		jdbc.update(insertPlan, new Object[]{ 102, yesterday, tomorrow, MarketingConstance.DEVELOP_TYPE, MarketingConstance.FINISHED});
//		jdbc.update(insertPlan, new Object[]{ 103, yesterday, tomorrow, MarketingConstance.DEVELOP_TYPE, MarketingConstance.OVERDUE});
//		jdbc.update(insertPlan, new Object[]{ 140, yesterday, today, MarketingConstance.DEVELOP_TYPE, MarketingConstance.NOTEXECUTE});
//		jdbc.update(insertPlan, new Object[]{ 141, yesterday, today, MarketingConstance.DEVELOP_TYPE, MarketingConstance.EXECUTING});
//		jdbc.update(insertPlan, new Object[]{ 142, yesterday, today, MarketingConstance.DEVELOP_TYPE, MarketingConstance.FINISHED});
//		jdbc.update(insertPlan, new Object[]{ 143, yesterday, today, MarketingConstance.DEVELOP_TYPE, MarketingConstance.OVERDUE});
//		jdbc.update(insertPlan, new Object[]{ 170, today, tomorrow, MarketingConstance.DEVELOP_TYPE, MarketingConstance.NOTEXECUTE});
//		jdbc.update(insertPlan, new Object[]{ 171, today, tomorrow, MarketingConstance.DEVELOP_TYPE, MarketingConstance.EXECUTING});
//		jdbc.update(insertPlan, new Object[]{ 172, today, tomorrow, MarketingConstance.DEVELOP_TYPE, MarketingConstance.FINISHED});
//		jdbc.update(insertPlan, new Object[]{ 173, today, tomorrow, MarketingConstance.DEVELOP_TYPE, MarketingConstance.OVERDUE});
//		jdbc.update(insertPlan, new Object[]{ 192, today, tomorrow, MarketingConstance.DEVELOP_TYPE, MarketingConstance.OVERDUE});
	}
	
	@Test
	public void testSearchPlansByStatusListAndProcessPlansStatusByDate() {
		List<String> notInStatusList = new ArrayList<String>();
		notInStatusList.add(MarketingConstance.FINISHED);
		notInStatusList.add(MarketingConstance.OVERDUE);
//		List<Plan> plans = planManager.searchPlansByStatusList(notInStatusList, 0, 20);
		List<Plan> plans2 = planManager.searchPlansByStatusList(notInStatusList, 20, 20);
		List<Plan> plans3 = planManager.searchPlansByStatusList(notInStatusList, 40, 20);
//		Plan plan1 = planManager.getPlanById("40026232");
//		
//		List<Schedule> schedules = scheduleManager.getAllSchedule(plan1);
//		plan1.setScheduleList(schedules);
//		
//		List<Plan> plans = new ArrayList<Plan>();
//		plans.add(plan1);
//		Assert.assertNotNull(plans);
//		Assert.assertTrue(plans.size() > 0);
//		
//		Calendar testDate = Calendar.getInstance();
//		
////		planManager.processPlansStatusByDate(null, testDate.getTime());
////		planManager.processPlansStatusByDate(plans, null);
//
//		List<Plan> resultPlan = planManager.processPlansStatusByDate(plans, testDate.getTime());
//
//		Date date = new Date();
//		boolean b;
//		if (plans != null && plans.size() > 0){
//			System.out.println("update operate");
//			// 根据日程更新计划状态
//			resultPlan = planManager.processPlansStatusByDate(plans, date);
//			b = planManager.updatePlanList(resultPlan);
//			if (b == false) {
//				System.out.println("未找到需要进行状态更改的计划日程");
//			} else {
//				System.out.println("计划日程状态更改成功");
//			}
//		}			
		
		
		
//		Assert.assertTrue(result.size() >= 4);
//		int testSum = 0;
//		for (Plan plan : result) {
//			if (StringUtils.equals(plan.getId(), "100")) {
//				Assert.assertTrue(plan.getScheduleList().size() >= 1);
//				testSum++;
//			}
//			if (StringUtils.equals(plan.getId(), "101")) {
//				Assert.assertTrue(plan.getScheduleList().size() >= 1);
//				testSum++;
//			}
//			if (StringUtils.equals(plan.getId(), "140")) {
//				Assert.assertTrue(plan.getScheduleList().size() >= 2);
//				testSum++;
//			}
//			if (StringUtils.equals(plan.getId(), "170")) {
//				Assert.assertTrue(plan.getScheduleList().size() >= 1);
//				testSum++;
//			}
////			System.out.println(plan);
//		}
////		Assert.assertEquals(4, testSum);
		
	}
	
	@Test
	public void testUpdatePlanList() {
		Date now = new Date();
		
		List<Plan> plans = new ArrayList<Plan>();
		Plan p1 = new Plan();
		p1.setId("100");
		p1.setStatus(MarketingConstance.FINISHED);
		p1.setModifyUser(MarketingConstance.TIMERUSERID);
		p1.setModifyDate(now);

		Plan p2 = new Plan();
		p2.setId("101");
		p2.setStatus(MarketingConstance.EXECUTING);
		p2.setModifyUser(MarketingConstance.TIMERUSERID);
		p2.setModifyDate(now);
		p2.setScheduleList(new ArrayList<Schedule>());

		Schedule s1 = new Schedule();
		s1.setId("110");
		s1.setStatus(MarketingConstance.SCHEDULE_ASSIGNED);
		s1.setModifyUser(MarketingConstance.TIMERUSERID);
		s1.setModifyDate(now);

		Schedule s2 = new Schedule();
		s2.setId("111");
		s2.setStatus(MarketingConstance.SCHEDULE_FINISH);
		s2.setModifyUser(MarketingConstance.TIMERUSERID);
		s2.setModifyDate(now);
		
		plans.add(p1);
		plans.add(p2);
		p2.getScheduleList().add(s1);
		p2.getScheduleList().add(s2);
		
		boolean result = planManager.updatePlanList(plans);
//		Assert.assertTrue(result);
		
		Plan plan1 = planManager.getPlanById("400012456");
//		Assert.assertEquals(MarketingConstance.FINISHED, plan1.getStatus());
//		Assert.assertEquals(now, plan1.getModifyDate());
//		Assert.assertEquals(MarketingConstance.TIMERUSERID, plan1.getModifyUser());

		Plan plan2 = planManager.getPlanById("400012456");
//		Assert.assertEquals(MarketingConstance.EXECUTING, plan2.getStatus());
//		Assert.assertEquals(now, plan2.getModifyDate());
//		Assert.assertEquals(MarketingConstance.TIMERUSERID, plan2.getModifyUser());
		List<Schedule> schedules = scheduleManager.getAllSchedule(plan2);

//		Assert.assertEquals(2, testSum);
	}
	
	
	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testUpdatePlan() {
		Plan plan = planManager.getPlanById("400000712");
		String[] newCustIds=new String[]{"111111","333333","666666","777777","888888"};
		try {
			plan.setTopic("测试啦啦啦啦11");
			plan.setId("12345777");
			plan.setStatus(MarketingConstance.OVERDUE);
			String b = planManager.updatePlan(plan, newCustIds,null, user,null);
			Assert.assertNotNull(b);
		} catch (Exception e) {}
		try {
			plan = planManager.getPlanById("724");
			String b = planManager.updatePlan(plan, newCustIds, null, user,null);
			Assert.assertNotNull(b);
		} catch (Exception e1) {}
		try {
			plan = planManager.getPlanById("400000694");
			plan.setCreateUserId("000000");
			String b = planManager.updatePlan(plan, newCustIds, null, user,null);
			Assert.assertNotNull(b);
		} catch (Exception e2) {}
		try { 
			plan = planManager.getPlanById("619");
			String b = planManager.updatePlan(plan, newCustIds, null, user,null);
			Assert.assertNotNull(b);
		} catch (Exception e) {}
	}

	
	public void testUpdatePlan2() {
		Plan plan = planManager.getPlanById("138");
		String[] newCustIds=new String[]{"138","139","150","151","152"};
		try {
			plan.setTopic("测试啦啦啦啦11");
			plan.setId("12345777");
			String b = planManager.updatePlan(plan, newCustIds,null, user,null);
			Assert.assertNotNull(b);
//		} catch (GeneralException e) {
		} catch (Exception e) {
			try {
				plan = planManager.getPlanById("724");
				String b = planManager.updatePlan(plan, newCustIds, null, user,null);
				Assert.assertNotNull(b);
			} catch (Exception e1) {
				try {
					plan = planManager.getPlanById("619");
					plan.setCreateUserId("000000");
					String b = planManager.updatePlan(plan, newCustIds, null, user,null);
					Assert.assertNotNull(b);
				} catch (Exception e2) {
					plan = planManager.getPlanById("619");
					newCustIds=new String[]{"238","239","250","251","253"};
					plan.setBeginTime(new Date());
					plan.setEndTime(DateUtils.addDays(new Date(), 5));
					String b = planManager.updatePlan(plan, newCustIds, null, user,null);
					Assert.assertNotNull(b);
				}
			}
		}
	}

	
	public void testUpdatePlan3() {
		Plan plan = planManager.getPlanById("138");
		String[] newCustIds=new String[]{"166","188","222","333","888"};
		try {
			plan.setTopic("测试啦啦啦啦11");
			plan.setId("12345777");
			String b = planManager.updatePlan(plan, newCustIds,null, user,null);
			Assert.assertNotNull(b);
		} catch (Exception e) {
			try {
				plan = planManager.getPlanById("724");
				String b = planManager.updatePlan(plan, newCustIds, null, user,null);
				Assert.assertNotNull(b);
			} catch (Exception e1) {
				try {
					plan = planManager.getPlanById("619");
					plan.setCreateUserId("000000");
					String b = planManager.updatePlan(plan, newCustIds, null, user,null);
					Assert.assertNotNull(b);
				} catch (Exception e2) {
					plan = planManager.getPlanById("619");
					plan.setBeginTime(new Date());
					plan.setEndTime(DateUtils.addDays(new Date(), 5));
					newCustIds=new String[]{"1166","1188","2122","3133","888"};
					String b = planManager.updatePlan(plan, newCustIds, null, user,null);
					Assert.assertNotNull(b);
				}
			}
		}
	}
	@Test
	public void testUpdatePlanContact() {
		Plan plan = planManager.getPlanById("400000642");
		String[] newCustIds=new String[]{"111111","333333","666666","777777","888888"};
		String[] contactIds=new String[]{"111","333","666","777","888"};
		try {
			//plan.setTopic("苏玉军苏玉军苏玉军苏玉军");
			String b = planManager.updatePlan(plan, newCustIds,contactIds, user,null);
			Assert.assertNotNull(b);
		} catch (GeneralException e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void testCreatePlan() {
		Date start = DateUtils.addDays(new Date(), 1);
		plan=DataUtilTest.getPlanData();
		try {
			plan.setBeginTime(new Date());
			plan.setEndTime(new Date());
			String b = planManager.createPlan(plan, null,null, user);
		} catch (GeneralException e) {
			// 时间检查失败
		}
//		
//		try {
//			plan.setBeginTime(start);
//			plan.setEndTime(DateUtils.addDays(start, 5));
//			String b = planManager.createPlan(plan, null,null, user);
//		} catch (GeneralException e) {
//			//未选择客户，不能创建
//		}

		String[] newCustIds=new String[]{"400116085","400116086","400116087"};
		try {
			plan.setBeginTime(start);
			plan.setEndTime(DateUtils.addDays(start, 5));
			String b = planManager.createPlan(plan, newCustIds,null, user);
		} catch (Exception e) {}
		try {
			plan.setBeginTime(new Date());
			plan.setEndTime(DateUtils.addDays(start, 5));
			plan.setPlanType(MarketingConstance.MAINTAIN_TYPE);
			String b = planManager.createPlan(plan, newCustIds,null, user);
		} catch (Exception e) {}
		
		try {
			plan=DataUtilTest.getPlanData();
			custList=null;
			String b = planManager.createPlan(plan, custList, null, user);
			Assert.assertNotNull(b);
		} catch (Exception e1) {}
		try {
			plan=DataUtilTest.getPlanData();
			custList=DataUtilTest.getCustList();
			String b = planManager.createPlan(plan, newCustIds, null, user);
			Assert.assertNotNull(b);
		} catch (GeneralException e2) {}
		try {
			plan = DataUtilTest.getPlanData();
			custList=DataUtilTest.getCustList();
			plan.setTopic("发货送积分2012-4-6 18:58:20");
			String b = planManager.createPlan(plan, newCustIds, null, user);
			Assert.assertNotNull(b);
		} catch (GeneralException e3) {}
		try{
				plan=DataUtilTest.getPlanData();
				custList=DataUtilTest.getCustList();
				String b = planManager.createPlan(plan, newCustIds, null, user);
				Assert.assertNotNull(b);	
		}catch(Exception e4){System.out.println(e4.getMessage());}
		try {
			plan.setBeginTime(start);
			plan.setEndTime(DateUtils.addDays(start, 5));
			plan.setTopic("发货送积分2012-4-6 18:58:20");
			String b = planManager.createPlan(plan, newCustIds,null, user);
		} catch (Exception e) {}
		
		try {
			plan=DataUtilTest.getPlanData();
			custList=DataUtilTest.getCustList();
			String b = planManager.createPlan(plan, newCustIds, null, user);
		} catch (GeneralException e) {}
	}
	
	@Test
	public void testCreateMaintainPlan() {
		try {
			//maintainPlan.setTopic("发货送积分2012-4-10 9:13:51");
			String b = planManager.createPlan(maintainPlan, custList,contactIds, user);
			Assert.assertNotNull(b);
		} catch (GeneralException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testCreateMaintainPlan1() {
		try {
			String[] custs = new String[]{"99999","99999"};
			String[] contacts = new String[]{"111111","111113"};
			String b = planManager.createPlan(maintainPlan, custs,contacts, user);
			Assert.assertNotNull(b);
		} catch (GeneralException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testGetPlanById() {

		try {
			planManager.getPlanById(null);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			Plan plan = planManager.getPlanById("389");
//			assertEquals("测试Ok","389", plan.getId());
		} catch (GeneralException e) {
			// TODO: handle exception
		}
	}

//	@Test
//	public void testDelPlanById(){
//		String[] id1 = null;
//		try {
//			boolean rs = planManager.deletePlan(id1, user);
//		} catch (GeneralException e) {
//		}
//		try {
//			id1 = new String[]{};
//			planManager.deletePlan(id1, user);
//		} catch (GeneralException e) {
//		}
//
//		try {
//			String[] id2 = {"192"};
////			planManager.deletePlan(id2, user);
//			Assert.assertTrue(true);// 删除数据仅能
//		} catch (GeneralException e) {
//		}
//		String[] newCustIds=new String[]{"400116085","400116086","400116087"};
//		try {
//			plan.setBeginTime(new Date());
//			plan.setEndTime(DateUtils.addDays(new Date(), 5));
//			String b = planManager.createPlan(plan, newCustIds,null, user);
//		} catch (Exception e) {}
//		Plan planDelete = planManager.searchDepartmentDevelopPlanList(user, planType).get(0);
//		try {
//			String[] id3 = {planDelete.getId()};
//			planManager.deletePlan(id3, user);
//			Assert.assertTrue(true);// 删除数据仅能
//		} catch (GeneralException e) {
//		}
//	}
	@Test
	public void testUpdatePlan1() {
		try {
			Plan plan = planManager.getPlanById("400000642");
			plan.setTopic("测试啦啦啦啦11000");
			String[] newCustIds=new String[]{"111111","333333","666666","777777","888888"};
			String[] newContactIds=new String[]{"111","333","666","777","888"};
			String b = planManager.updatePlan(plan, newCustIds,newContactIds, user,null);
			Assert.assertNotNull(b);
		} catch (GeneralException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testSearchPlan(){
		PlanDevelopCondition condition = new PlanDevelopCondition();
		try {
			planManager.searchPlan(condition, 0, 10, user);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			Date s1 = DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 3);;
			Date s2 = DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 4);;
			condition.setPlanStartDate(s1);
			condition.setPlanOverDate(s2);
			condition.setType(MarketingConstance.DEVELOP_TYPE);
			condition.setDevelopMode("VISIT");
			planManager.searchPlan(condition, 0, 10, user);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			user.setDeptids(null);
			planManager.searchPlan(condition, 0, 10, user);
		} catch (Exception e) {}
		try {
			user.setDeptids(null);
			planManager.searchPlan(condition, 0, 10, null);
		} catch (Exception e) {}
	}
	
	@Test
	public void testSearchPlanDetail(){

		try {
			planManager.searchPlanDetail(null, MarketingConstance.DEVELOP_TYPE);
			String planid = "1061";
			Map<String, Object> map = planManager.searchPlanDetail(planid, MarketingConstance.DEVELOP_TYPE);
			PlanList pl = (PlanList) map.get("plandetail");
			List<CustomerList> list = (List<CustomerList>) map.get("custlist");
			
//			Assert.assertNotNull(pl);
			Assert.assertNotNull(list);
		} catch (GeneralException e) {
			System.out.println(e.getMessage());
		}


		try {
			planManager.searchPlanDetail(null, MarketingConstance.MAINTAIN_TYPE);
			String planid = "1092";
			Map<String, Object> map = planManager.searchPlanDetail(planid, MarketingConstance.MAINTAIN_TYPE);
			PlanList pl = (PlanList) map.get("plandetail");
			List<CustomerList> list = (List<CustomerList>) map.get("custlist");
			
//			Assert.assertNotNull(pl);
			Assert.assertNotNull(list);
		} catch (GeneralException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testSearchMemberList1(){
		Map<String, Object> map= new HashMap<String, Object>();
		try{
			CustomerVo vo =new CustomerVo();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			vo.setBeginTime(sdf.parse("2011-09-12"));
			vo.setOverTime(sdf.parse("2012-12-12"));
			planManager.searchMemberList(vo, 0, 20, user);
		}catch(Exception e){System.out.println(e.getMessage());}
		try{
			CustomerVo vo =new CustomerVo();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			vo.setBeginTime(sdf.parse("2011-09-12"));
			vo.setOverTime(sdf.parse("2012-06-12"));
			vo.setDeptId("");
			planManager.searchMemberList(vo, 0, 20, user);
		}catch(Exception e){}
		try{
			CustomerVo vo =new CustomerVo();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			vo.setBeginTime(sdf.parse("2011-09-12"));
			vo.setOverTime(sdf.parse("2012-06-12"));
			vo.setDeptId("1");
			planManager.searchMemberList(vo, 0, 20, user);
		}catch(Exception e){}
		try {
			List<CustomerVo> voList=new ArrayList<CustomerVo>();
			CustomerVo vo =null;
			map = planManager.searchMemberList(vo, 0, 20, user);
			voList = (List<CustomerVo>) map.get("list");
			Assert.assertNotNull(voList);
		} catch (GeneralException e) {
			List<CustomerVo> voList=new ArrayList<CustomerVo>();
			CustomerVo vo = DataUtilTest.getCustomerVO();
			vo.setDeptId("11469");
			map = planManager.searchMemberList(vo, 0, 20, user);
			voList = (List<CustomerVo>) map.get("list");
			Assert.assertNotNull(voList);
		}
	}
	
	@Test
	public void testSavePlan(){
		try {
			Plan plan = new Plan();
			plan.setId(null);
			plan.setPlanType(MarketingConstance.DEVELOP_TYPE);
			planManager.savePlan(plan, null, null, user,null);
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			Plan plan = planManager.getPlanById("400000642");
//			plan.setId("1234577");
			plan.setPlanType(MarketingConstance.DEVELOP_TYPE);
			planManager.savePlan(plan, null, null, user,null);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testSearchPlanByTheme(){
		try{
			String theme = "";
			planManager.searchDepartmentDevelopPlanList(theme, "1", MarketingConstance.DEVELOP_TYPE);
		}catch(GeneralException e){}
		try{
			String theme = "发货送";
			planManager.searchDepartmentDevelopPlanList(theme, "1", MarketingConstance.DEVELOP_TYPE);
		}catch(GeneralException e){}
		String theme = "发货送积分";
		List<Plan> plans = planManager.searchDepartmentDevelopPlanList(theme, "1", MarketingConstance.DEVELOP_TYPE);
		Assert.assertNotNull(plans);
	}
	@Test
	public void testSearchDepartmentDevelopPlanList(){
		planManager.searchDepartmentDevelopPlanList("1", planType);
	}
	@Test
	public void testSearchDevelopPlan(){
		try{
			planManager.searchDevelopPlan("110", user);
		}catch(Exception e){}
	}
//	@Test
//	public void testGetCustomerbyPlanId(){
//		String planId = "400000694";
//		planManager.getCustomerbyPlanId(planId);
//	}
	@Test
	public void testProcessPlansStatusByDate(){
		List<Plan> planList = new ArrayList<Plan>();
		Plan p1 = new Plan();
		planList.add(p1);
		Date testDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		List<Schedule> ScheduleList = new ArrayList<Schedule>();
		Schedule s1 = new Schedule();
		ScheduleList.add(s1);
		try{
			// 两个参数都为空
			planManager.processPlansStatusByDate(null, null);
		}catch(Exception e){}
		try{
			// 时间参数为空
			planManager.processPlansStatusByDate(planList, null);
		}catch(Exception e){}
		try{
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
		try{
			// 开始时间 小于现在时间 ， 现在时间小于结束时间
			p1.setBeginTime(sdf.parse("2012-09"));
			testDate = sdf.parse("2012-11");
			p1.setEndTime(sdf.parse("2012-12"));
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
		try{
			// 开始时间小于现在时间 ， 现在时间大于 结束时间
			p1.setBeginTime(sdf.parse("2012-09"));
			testDate = sdf.parse("2012-11");
			p1.setEndTime(sdf.parse("2012-10"));
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
		try{
			// 开始时间大于现在时间 ， 现在时间小于结束时间
			p1.setBeginTime(sdf.parse("2012-12"));
			testDate = sdf.parse("2012-11");
			p1.setEndTime(sdf.parse("2012-10"));
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
		try{
			// 计划状态 不满足条件
			p1.setBeginTime(sdf.parse("2012-09"));
			testDate = sdf.parse("2012-11");
			p1.setEndTime(sdf.parse("2012-12"));
			p1.setStatus(MarketingConstance.EXECUTING);
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
		try{
			// 计划下日程 为空 不满足
			p1.setBeginTime(sdf.parse("2012-09"));
			testDate = sdf.parse("2012-11");
			p1.setEndTime(sdf.parse("2012-12"));
			p1.setScheduleList(null);
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
		try{
			// 计划下日程 为空 不满足
			p1.setBeginTime(sdf.parse("2012-09"));
			testDate = sdf.parse("2012-11");
			p1.setEndTime(sdf.parse("2012-12"));
			p1.setScheduleList(ScheduleList);
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
		try{
			// 日程开始时间不为空 满足
			p1.setBeginTime(sdf.parse("2012-09"));
			testDate = sdf.parse("2012-11");
			p1.setEndTime(sdf.parse("2012-12"));
			s1.setTime(new Date());
			p1.setScheduleList(ScheduleList);
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
		try{
			// 日程开始时间等于现在时间  且状态不为 已制定 不满足
			testDate = sdf.parse("2012-11");
			s1.setTime(testDate);
			s1.setStatus(MarketingConstance.SCHEDULE_ASSIGNED);
			p1.setScheduleList(ScheduleList);
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
		try{
			// 日程开始时间等于现在时间  且状态为 已制定 满足
			testDate = sdf.parse("2012-11");
			s1.setTime(testDate);
			s1.setStatus(MarketingConstance.SCHEDULE_FORMULATE);
			p1.setScheduleList(ScheduleList);
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
		try{
			// 日程开始时间小于现在时间  且状态不为 已制定 满足
			testDate = sdf.parse("2012-11");
			s1.setTime(sdf.parse("2012-10"));
			s1.setStatus(MarketingConstance.SCHEDULE_ASSIGNED);
			p1.setScheduleList(ScheduleList);
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
		try{
			// 日程开始时间小于现在时间  且状态为 已制定 满足
			testDate = sdf.parse("2012-11");
			s1.setTime(sdf.parse("2012-10"));
			s1.setStatus(MarketingConstance.SCHEDULE_FORMULATE);
			p1.setScheduleList(ScheduleList);
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
		try{
			// 日程状态为 已完成  满足
			s1.setStatus(MarketingConstance.SCHEDULE_FINISH);
			p1.setScheduleList(ScheduleList);
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
		try{
			// 开始时间小于现在时间 ， 现在时间大于 结束时间
			p1.setBeginTime(sdf.parse("2012-09"));
			testDate = sdf.parse("2012-11");
			p1.setEndTime(sdf.parse("2012-10"));
			s1.setStatus(MarketingConstance.SCHEDULE_FORMULATE);
			p1.setScheduleList(ScheduleList);
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
		try{
			// 开始时间小于现在时间 ， 现在时间大于 结束时间
			p1.setBeginTime(sdf.parse("2012-09"));
			testDate = sdf.parse("2012-11");
			p1.setEndTime(sdf.parse("2012-10"));
			s1.setStatus(MarketingConstance.SCHEDULE_FINISH);
			p1.setScheduleList(ScheduleList);
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
		try{
			// 现在时间小于 结束时间 且 日程状态为 已结束
			p1.setBeginTime(sdf.parse("2012-12"));
			testDate = sdf.parse("2012-11");
			p1.setEndTime(sdf.parse("2012-12"));
			s1.setStatus(MarketingConstance.SCHEDULE_FINISH);
			p1.setScheduleList(ScheduleList);
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
		try{
			// 现在时间小于 结束时间 
			p1.setBeginTime(sdf.parse("2012-12"));
			testDate = sdf.parse("2012-11");
			p1.setEndTime(sdf.parse("2012-12"));
			s1.setStatus(MarketingConstance.SCHEDULE_FORMULATE);
			p1.setScheduleList(ScheduleList);
			planManager.processPlansStatusByDate(planList, testDate);
		}catch(Exception e){}
	}
	
	@Test
	public void testQueryMember(){
		CustomerVo vo = new CustomerVo();
		vo.setDeptId("11469");
		vo.setCustType("RC_CUSTOMER");
		vo.setCustNature("ARRIVE_CUSTOMER");
		vo.setCustbase("ABC");
		vo.setCustName("苏");
		vo.setLinkManName("su");
		vo.setLinkManMobile("13917097761");
		vo.setLinkManPhone("02131350806");
		vo.setVolumePotential("400");
		vo.setDevelopmentPhase("开始发货");
		vo.setDegree("黄金客户");
		vo.setContinueMark("1");
		vo.setBeginTime(new Date());
		vo.setOverTime(new Date());
		vo.setTrade("外贸");
		vo.setCoopIntention("开始发货") ;
		vo.setBusinessType("EL");
		planManager.searchMemberList(vo,0,100,new User());
	}
}
