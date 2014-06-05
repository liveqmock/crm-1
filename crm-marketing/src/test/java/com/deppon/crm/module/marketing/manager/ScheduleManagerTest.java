/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title TestScheduleManager.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author Administrator
 * @version 0.1 2012-3-28
 */
package com.deppon.crm.module.marketing.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;
import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.action.DevelopScheduleVO;
import com.deppon.crm.module.marketing.server.manager.IPlanManager;
import com.deppon.crm.module.marketing.server.manager.IScheduleManager;
import com.deppon.crm.module.marketing.server.manager.impl.PlanManager;
import com.deppon.crm.module.marketing.server.manager.impl.ScheduleManager;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;
import com.deppon.crm.module.marketing.shared.domain.SearchCustomerCondition;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title TestScheduleManager.java
 * @package com.deppon.crm.module.marketing.manager
 * @author 苏玉军
 * @version 0.1 2012-3-28
 */

public class ScheduleManagerTest {
	private IScheduleManager scheduleManager;
	private IPlanManager planManager;
	private JdbcTemplate jdbc;
	User user = null;
	private final int START = 1;
	private final int Limit = 20;

	@Before
	public void setUp() throws Exception {
		scheduleManager = SpringTestHelper.get().getBean(ScheduleManager.class);
		user= DataUtilTest.getUser();
		planManager = SpringTestHelper.get().getBean(PlanManager.class);

//		jdbc = (JdbcTemplate) SpringTestHelper.get().getBean(JdbcTemplate.class);
//
//		jdbc.execute("delete from t_cust_developmaintainlist where fid between 1 and 100");
//		jdbc.execute("delete from t_cust_schedule where fid between 1 and 100");
//		
//		Calendar yesterday = Calendar.getInstance();
//		Calendar tomorrow   = Calendar.getInstance();
//		Calendar today = Calendar.getInstance();
//		yesterday.add(Calendar.DATE, -1);
//		tomorrow.add(Calendar.DATE, 1);
//		
//		String insertPlan = "insert into t_cust_developmaintainlist (fid, fbegintime, fendtime, fplantype, fstatus, FCREATEUSERID,FEXECUSERID) values(?, ?, ?, ?, ?,1,1)";
//		jdbc.update(insertPlan, new Object[]{ 1, yesterday, tomorrow, MarketingConstance.DEVELOP_TYPE, MarketingConstance.NOTEXECUTE});
//		jdbc.update(insertPlan, new Object[]{ 2, yesterday, tomorrow, MarketingConstance.DEVELOP_TYPE, MarketingConstance.EXECUTING});
//		jdbc.update(insertPlan, new Object[]{ 3, yesterday, tomorrow, MarketingConstance.DEVELOP_TYPE, MarketingConstance.FINISHED});
//		jdbc.update(insertPlan, new Object[]{ 4, yesterday, tomorrow, MarketingConstance.DEVELOP_TYPE, MarketingConstance.OVERDUE});
//		
//		String insertSchedule = "insert into t_cust_schedule (fplanid, fid, fcustid, fstatus, ftype, ftime, FCREATEUSERID,FEXECUSERID) values (?, ?, 1, ?, ?, ?,1,1)";
//		jdbc.update(insertSchedule, new Object[]{1, 1, MarketingConstance.SCHEDULE_ASSIGNED, MarketingConstance.DEVELOP_TYPE, yesterday});
//		jdbc.update(insertSchedule, new Object[]{1, 2, MarketingConstance.SCHEDULE_FORMULATE, MarketingConstance.DEVELOP_TYPE, yesterday});
//		jdbc.update(insertSchedule, new Object[]{1, 3, MarketingConstance.SCHEDULE_EXECUTING, MarketingConstance.DEVELOP_TYPE, yesterday});
//		jdbc.update(insertSchedule, new Object[]{1, 4, MarketingConstance.SCHEDULE_FINISH, MarketingConstance.DEVELOP_TYPE, yesterday});
//		jdbc.update(insertSchedule, new Object[]{1, 5, MarketingConstance.SCHEDULE_OVERDUE, MarketingConstance.DEVELOP_TYPE, yesterday});
//		
//
//		jdbc.update(insertSchedule, new Object[]{3, 11, MarketingConstance.SCHEDULE_ASSIGNED, MarketingConstance.DEVELOP_TYPE, yesterday});
//		jdbc.update(insertSchedule, new Object[]{3, 12, MarketingConstance.SCHEDULE_FORMULATE, MarketingConstance.DEVELOP_TYPE, yesterday});
//		jdbc.update(insertSchedule, new Object[]{3, 13, MarketingConstance.SCHEDULE_EXECUTING, MarketingConstance.DEVELOP_TYPE, yesterday});
//		jdbc.update(insertSchedule, new Object[]{3, 14, MarketingConstance.SCHEDULE_FINISH, MarketingConstance.DEVELOP_TYPE, yesterday});
//		jdbc.update(insertSchedule, new Object[]{3, 15, MarketingConstance.SCHEDULE_OVERDUE, MarketingConstance.DEVELOP_TYPE, yesterday});
//
//		jdbc.update(insertSchedule, new Object[]{null, 99, MarketingConstance.SCHEDULE_OVERDUE, MarketingConstance.DEVELOP_TYPE, yesterday});

	}
	/*
	 *insert into T_CUST_SCHEDULE sql语句有误,需要插入15个字段,传参只有14个!!!
	 */
	@Test
	public void testCreateSchedule() {
		// 创建日程测试
		Schedule s = new Schedule(); 
	
		try {
			boolean b = scheduleManager.createSchedule(s,user);
		} catch (GeneralException e) {
			e.printStackTrace();
		}
		// 有计划 
		try {
			s.setCreateDate(new Date());
			s.setCreateTime(new Date());
			s.setCreateUser("1");
			s.setCustId("9999");
			s.setType("10");
			s.setPlanId("1");
			boolean b = scheduleManager.createSchedule(s,user); 
			//Assert.assertTrue(b);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		try {
			s.setCreateDate(new Date());
			s.setCreateTime(new Date());
			s.setCreateUser("1");
			s.setCustId("9999");
			s.setType("10");
			s.setPlanId("1");
			s.setTime(new Date());
			boolean b = scheduleManager.createSchedule(s,user);
			//Assert.assertTrue(b);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		try {
			// 根据计划ID未找到计划
			s.setCreateDate(new Date());
			s.setCreateTime(new Date());
			s.setCreateUser("1");
			s.setCustId("9999");
			s.setType(MarketingConstance.DEVELOP_TYPE);
			s.setPlanId("77");
			s.setTime(new Date());
			boolean b = scheduleManager.createSchedule(s,user);
			//Assert.assertTrue(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 根据计划ID找到计划
			s.setCreateDate(new Date());
			s.setCreateTime(new Date());
			s.setCreateUser("1");
			s.setCustId("9999");
			s.setType(MarketingConstance.DEVELOP_TYPE);
			s.setPlanId("1");
			s.setTime(new Date());
			boolean b = scheduleManager.createSchedule(s,user);
			//Assert.assertTrue(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			s.setCreateDate(new Date());
			s.setCreateTime(new Date());
			s.setCreateUser("1");
			s.setCustId("9999");
			s.setType(MarketingConstance.DEVELOP_TYPE);
			s.setPlanId("1");
			// 日程日期不满足
			s.setTime(DateUtils.setDays(new Date(), 1));
			boolean b = scheduleManager.createSchedule(s,user);
			//Assert.assertTrue(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// 无计划
		try {
			s.setCreateDate(new Date());
			s.setCreateTime(new Date());
			s.setCreateUser("1");
			s.setCustId("9999");
			s.setType("10");
			s.setPlanId(null);
			boolean b = scheduleManager.createSchedule(s,user);
			Assert.assertTrue(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		User user = new User();
		user.setId("389904");
		Employee emp = new Employee();
		user.setEmpCode(emp);
		Department dept = new Department();
		dept.setId("11387");
		emp.setId("389904");
		emp.setDeptId(dept);
		UserContext.setCurrentUser(user);
		s = new Schedule();
		s.setCreateDate(new Date());
		s.setCreateTime(new Date());
		s.setCreateUser("1");
		s.setCustId("9999");
		s.setType(MarketingConstance.DEVELOP_TYPE);
		s.setPlanId("1");
		try {
			boolean b = scheduleManager.createSchedule(s,user);
		} catch (GeneralException e) {
			e.printStackTrace();
		}
		// 有计划
		try {
			s.setCreateDate(new Date());
			s.setCreateTime(new Date());
			s.setCreateUser("1");
			s.setCustId("9999");
			s.setType(MarketingConstance.DEVELOP_TYPE);
			s.setPlanId("1");
			boolean b = scheduleManager.createSchedule(s,user); 
			//Assert.assertTrue(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			UserContext.setCurrentUser(user);
			s.setCreateDate(new Date());
			s.setCreateTime(new Date());
			s.setCreateUser("1");
			s.setCustId("401591269");
			s.setType(MarketingConstance.DEVELOP_TYPE);
			s.setPlanId("1111111");
			s.setTime(new Date()); 
			boolean b = scheduleManager.createSchedule(s,user); 
			//Assert.assertTrue(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			UserContext.setCurrentUser(user);
			s.setCreateDate(new Date());
			s.setCreateTime(new Date());
			s.setCreateUser("1");
			s.setCustId("401584253");
			s.setType(MarketingConstance.DEVELOP_TYPE);
			s.setPlanId("1111111");
			s.setTime(new Date()); 
			boolean b = scheduleManager.createSchedule(s,user); 
			//Assert.assertTrue(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			s.setCreateDate(new Date());
			s.setCreateTime(new Date());
			s.setCreateUser("1");
			s.setCustId("9999");
			s.setType(MarketingConstance.DEVELOP_TYPE);
			s.setPlanId("1");
			s.setTime(new Date());
			boolean b = scheduleManager.createSchedule(s,user);
			//Assert.assertTrue(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 根据计划ID未找到计划
			s.setCreateDate(new Date());
			s.setCreateTime(new Date());
			s.setCreateUser("1");
			s.setCustId("9999");
			s.setType(MarketingConstance.DEVELOP_TYPE);
			s.setPlanId("77");
			s.setTime(new Date());
			boolean b = scheduleManager.createSchedule(s,user);
			//Assert.assertTrue(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 根据计划ID找到计划
			s.setCreateDate(new Date());
			s.setCreateTime(new Date());
			s.setCreateUser("1");
			s.setCustId("9999");
			s.setType(MarketingConstance.DEVELOP_TYPE);
			s.setPlanId("1");
			s.setTime(new Date());
			boolean b = scheduleManager.createSchedule(s,user);
			//Assert.assertTrue(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			s.setCreateDate(new Date());
			s.setCreateTime(new Date());
			s.setCreateUser("1");
			s.setCustId("9999");
			s.setType(MarketingConstance.DEVELOP_TYPE);
			s.setPlanId("1");
			// 日程日期不满足
			s.setTime(DateUtils.setDays(new Date(), 1));
			boolean b = scheduleManager.createSchedule(s,user);
			//Assert.assertTrue(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			
			UserContext.setCurrentUser(user);
			s.setCreateDate(new Date());
			s.setCreateTime(new Date());
			s.setCreateUser("1");
			s.setCustId("");
			s.setType(MarketingConstance.DEVELOP_TYPE);
			s.setPlanId("1111111");
			s.setTime(new Date()); 
			boolean b = scheduleManager.createSchedule(s,user); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	/*	try {
			Plan plan = new Plan();
			s.setCreateDate(new Date());
			s.setCreateTime(new Date());
			s.setCreateUser("1");
			s.setCustId("9999");
			s.setType("10");
			s.setStatus("1111");
			s.setPlanId(null);
			boolean b = scheduleManager.createSchedule(s);
			Assert.assertTrue(b);
		} catch (GeneralException e) {
			try {
				e.printStackTrace();
				Plan plan = new Plan();
				Schedule s = new Schedule();
				s.setCreateDate(new Date());
				s.setCreateTime(new Date());
				s.setCreateUser("1");
				s.setCustId("9999");
				s.setType("10");
				s.setStatus("1111");
				s.setPlanId("0000");
				boolean b = scheduleManager.createSchedule(s);
				Assert.assertTrue(b);
			} catch (GeneralException e1) {
				try {
					e1.printStackTrace();
					Plan plan = new Plan();
					Schedule s = new Schedule();
					s.setCreateDate(new Date());
					s.setCreateTime(new Date());
					s.setCreateUser("1");
					s.setCustId(null);
					s.setType("10");
					s.setStatus("1111");
					s.setPlanId("999");
					boolean b = scheduleManager.createSchedule(s);
					Assert.assertTrue(b);
				} catch (GeneralException e2) {
					e2.printStackTrace();
					try {
						Plan plan = new Plan();
						Schedule s = new Schedule();
						s.setCreateDate(new Date());
						s.setCreateTime(new Date());
						s.setCreateUser("1");
						s.setCustId("9999");
						s.setType("10");
						s.setStatus("1111");
						// s.setPlanId("999");
						boolean b = scheduleManager.createSchedule(s);
						Assert.assertTrue(b);
					} catch (GeneralException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
						Plan plan = new Plan();
						Schedule s = new Schedule();
						s.setCreateDate(new Date());
						s.setCreateTime(new Date());
						s.setCreateUser("1");
						s.setCustId("9999");
						s.setType("10");
						s.setStatus("1111");
						s.setPlanId("999");
						boolean b = scheduleManager.createSchedule(s);
						Assert.assertTrue(b);
					}
				}
			}
		}*/

	}

	@Test
	public void testSearchCustomerList() {
		try {
			//System.out.println("测试条件为空验证不通过");
			User user = new User();
			user.setId("386405");
			Employee emp = new Employee();
			user.setEmpCode(emp);
			Department dept = new Department();
			dept.setId("111111");
			emp.setId("1212");
			emp.setDeptId(dept);
			UserContext.setCurrentUser(user);
			CustomerVo conditon =null;
			List<ScheduleQueryResultObject> list = scheduleManager
					.searchCustomerList(conditon, START, Limit,user);
//			for (ScheduleQueryResultObject result : list) {
//				System.out.println(result);
//			}
		} catch (Exception e) {
			//System.out.println("测试日期范围超过三个？？？");
			try {
				User user = new User();
				user.setId("386405");
				Employee emp = new Employee();
				user.setEmpCode(emp);
				Department dept = new Department();
				dept.setId("111111");
				emp.setId("1212");
				emp.setDeptId(dept);
				UserContext.setCurrentUser(user);
				CustomerVo conditon = DataUtilTest.getPlanScheduleQueryCondition();
				Date end = new Date();
				end.setMonth(9);
				conditon.setOverTime(end);
				List<ScheduleQueryResultObject> list = scheduleManager
						.searchCustomerList(conditon, START, Limit,user);
//				for (ScheduleQueryResultObject result : list) {
//					System.out.println(result);
//				}
			} catch (Exception e1) {
				//System.out.println("测试通过！！");
				User user = new User();
				user.setId("386405");
				Employee emp = new Employee();
				user.setEmpCode(emp);
				Department dept = new Department();
				dept.setId("111111");
				emp.setId("1212");
				emp.setDeptId(dept);
				UserContext.setCurrentUser(user);
				CustomerVo conditon =null ;
				try{
					scheduleManager.searchCustomerList(conditon, START, Limit,user);
				}catch(Exception ex1){
					
				}
				 conditon =DataUtilTest.getCustomerVO();
				
				try{
					List<ScheduleQueryResultObject> list = scheduleManager
							.searchCustomerList(conditon, START, Limit,user);
				}catch (Exception ex1) {
					
				}

				try{
					
				//1
				conditon.setDeptId(null);
				conditon.setScheduleType(MarketingConstance.DEVELOP_TYPE);
				scheduleManager.searchCustomerList(conditon, START, Limit,user);
				}catch(Exception ex1){
					
				}
				//2
				try{
				conditon.setDeptId("");
				conditon.setScheduleType(MarketingConstance.DEVELOP_TYPE);
				scheduleManager.searchCustomerList(conditon, START, Limit,user);
				}catch(Exception ex1){
					
				}
				//3
				try{
				conditon.setDeptId("1111");
				conditon.setScheduleType(null);
				scheduleManager.searchCustomerList(conditon, START, Limit,user);
				}catch(Exception ex1){
					
				}
			//4
				try{
				conditon.setDeptId("");
				conditon.setScheduleType("");
				scheduleManager.searchCustomerList(conditon, START, Limit,user);
				
				}catch(Exception ex1){
					
				}
				//5
				try{
				conditon.setDeptId("1111");
				conditon.setScheduleType(MarketingConstance.DEVELOP_TYPE);
				scheduleManager.searchCustomerList(conditon, START, Limit,user);

				}catch(Exception ex1){
					
				}
			}finally{
				System.out.println("测试日程类型为维护。");
				User user = new User();
				user.setId("386405");
				Employee emp = new Employee();
				user.setEmpCode(emp);
				Department dept = new Department();
				dept.setId("111111");
				emp.setId("1212");
				emp.setDeptId(dept);
				UserContext.setCurrentUser(user);
				CustomerVo conditon =DataUtilTest.getCustomerVO();
				conditon.setScheduleType(MarketingConstance.MAINTAIN_TYPE);
				try{
				List<ScheduleQueryResultObject> list = scheduleManager
						.searchCustomerList(conditon, START, Limit,user);
				for (ScheduleQueryResultObject result : list) {
					System.out.println(result);
				}
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
		}
	}

	@Test
	public void testDeleteSchedule() {
		try{
		CustomerVo con = new CustomerVo();
		con.setPlanId("1111111");
		con.setBeginTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-06-01"));
		con.setOverTime(new Date());
		con.setScheduleType(MarketingConstance.DEVELOP_TYPE);
		con.setDeptId("11387");
		List<ScheduleQueryResultObject> list= scheduleManager.searchCustomerList(con, START, Limit,user);
//		
//			Schedule s = new Schedule(); 
//			s.setCreateDate(list.get(0).getCreateDate());
//			s.setCreateTime(list.get(0).getCreateDate());
//			s.setCreateUser(list.get(0).getCreateUser());
//			s.setCustId(list.get(0).getCustId());
//			s.setType("dev");
//			s.setPlanId("1");
//			s.setTime(new Date());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		Schedule s = new Schedule(); 
		try {
			s.setCreateDate(new Date(System.currentTimeMillis()-1000*60*60*24*2));
			s.setCreateTime(new Date(System.currentTimeMillis()+1000*60*60*24*2));
			s.setCreateUser("1");
			s.setCustId("400116085");
			s.setType(MarketingConstance.DEVELOP_TYPE);
			s.setPlanId("1");
			s.setTime(new Date());
			boolean b = scheduleManager.createSchedule(s,user);
		
			//Assert.assertTrue(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Schedule s1 = new Schedule();
		
		try {
			s1.setCreateDate(new Date(System.currentTimeMillis()-1000*60*60*24*2));
			s1.setCreateTime(new Date(System.currentTimeMillis()+1000*60*60*24*2));
			s1.setCreateUser("1");
			s1.setCustId("400116085");
			s1.setType(MarketingConstance.DEVELOP_TYPE);
			s1.setTime(new Date());
			s1.setPlanId("");
			 scheduleManager.createSchedule(s1,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Schedule s2 = new Schedule();
		
		try {
			s2.setCreateDate(new Date(System.currentTimeMillis()-1000*60*60*24*2));
			s2.setCreateTime(new Date(System.currentTimeMillis()+1000*60*60*24*2));
			s2.setCreateUser("1");
			s2.setCustId("400116085");
			s2.setType(MarketingConstance.DEVELOP_TYPE);
			s2.setPlanId("2");
			s2.setTime(new Date());
			 scheduleManager.createSchedule(s2,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Schedule s3 = new Schedule();
		
		try {
			s3.setCreateDate(new Date(System.currentTimeMillis()-1000*60*60*24*2));
			s3.setCreateTime(new Date(System.currentTimeMillis()+1000*60*60*24*2));
			s3.setCreateUser("1");
			s3.setCustId("400116085");
			s3.setType(MarketingConstance.DEVELOP_TYPE);
			s3.setTime(new Date());
			s3.setPlanId("3");
			 scheduleManager.createSchedule(s3,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	Schedule s4 = new Schedule();
		
		try {
			s4.setCreateDate(new Date(System.currentTimeMillis()-1000*60*60*24*2));
			s4.setCreateTime(new Date(System.currentTimeMillis()+1000*60*60*24*2));
			s4.setCreateUser("1");
			s4.setCustId("400116085");
			s4.setType(MarketingConstance.DEVELOP_TYPE);
			s4.setTime(new Date());
			s4.setPlanId("4");
			 scheduleManager.createSchedule(s4,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
Schedule s5 = new Schedule();
		
		try {
			s5.setCreateDate(new Date(System.currentTimeMillis()-1000*60*60*24*2));
			s5.setCreateTime(new Date(System.currentTimeMillis()+1000*60*60*24*2));
			s5.setCreateUser("1");
			s5.setCustId("400116085");
			s5.setType(MarketingConstance.DEVELOP_TYPE);
			s5.setTime(new Date());
			s5.setPlanId("5");
			 scheduleManager.createSchedule(s5,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		User user = new User();
		user.setId("1");
		Employee emp = new Employee();
		user.setEmpCode(emp);
		Department dept = new Department();
		dept.setId("1");
		emp.setId("1");
		emp.setDeptId(dept);
		UserContext.setCurrentUser(user);
		try {
			
			
			String[] ids = new String[] { s.getId() };
			boolean b = scheduleManager.deleteSchedule(ids,user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			String[] ids = new String[] { s1.getId() };
			boolean b = scheduleManager.deleteSchedule(ids,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String[] ids = new String[] { s2.getId() };
			boolean b = scheduleManager.deleteSchedule(ids,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String[] ids = new String[] { s3.getId() };
			boolean b = scheduleManager.deleteSchedule(ids,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String[] ids = new String[] { s4.getId() };
			boolean b = scheduleManager.deleteSchedule(ids,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String[] ids = new String[] { s5.getId() };
			boolean b = scheduleManager.deleteSchedule(ids,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Test
//	public void testGetCustomerDetail() {
//		PotentialCustomer pc = scheduleManager.getCustomerDetail("21740",
//				MarketingConstance.POTENTIAL_CUSTOMER);
//
//		PotentialCustomer pc1 =  scheduleManager
//				.getCustomerDetail("3082", MarketingConstance.SCATTER_CUSTOMER);
//
//	}
	@Test
	public void testUpdateSchedule(){
		Schedule schedule = null;
		try {
			scheduleManager.updateSchedule(schedule,user);
		} catch (GeneralException e) {
			e.printStackTrace();
		}
		// 有计划的日程
		schedule = new Schedule();
		try {
			// 日程为空
			schedule.setId("1");
			schedule.setTime(null);
			scheduleManager.updateSchedule(schedule,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 计划状态-已完成
			schedule.setId("16221");
			schedule.setTime(new Date());
			scheduleManager.updateSchedule(schedule,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 计划状态-已完成
			schedule.setId("16221");
			schedule.setTime(new Date());
			scheduleManager.updateSchedule(schedule,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 日程时间超出计划时间
			schedule.setId("3");
			schedule.setTime(DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 3));
			scheduleManager.updateSchedule(schedule,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 日程时间超出计划时间
			schedule.setId("16241");
			schedule.setTime(DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 11));
			scheduleManager.updateSchedule(schedule,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			schedule.setId("16241");
			schedule.setTime(new Date());
			scheduleManager.updateSchedule(schedule,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			schedule.setId("16241");
			schedule.setTime(new Date());
			boolean rs = scheduleManager.updateSchedule(schedule,user);
			Assert.assertTrue(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			schedule = new Schedule();
			schedule.setId("403309114");
			schedule.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-21"));
			boolean rs = scheduleManager.updateSchedule(schedule,user);
			Assert.assertTrue(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		// 无计划的日程
//		try {
//			schedule.setId("99");
//			schedule.setTime(new Date());
//			boolean rs = scheduleManager.updateSchedule(schedule,user);
//			Assert.assertTrue(rs);
//		} catch (GeneralException e) {
//			// TODO: handle exception
//		}
	}
	

	@Test
	public void testSaveSchedule(){
		Schedule schedule = null;
		try {
			// 创建日程
			schedule = new Schedule();
			schedule.setId("403309114");
			schedule.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-21"));
			//boolean rs = scheduleManager.updateSchedule(schedule,user);
			boolean rs = scheduleManager.saveSchedule(schedule,user);
			//Assert.assertTrue(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
			try {
				Schedule s3 = new Schedule();
				s3.setId("");
				s3.setCreateDate(new Date(System.currentTimeMillis()-1000*60*60*24*2));
				s3.setCreateTime(new Date(System.currentTimeMillis()+1000*60*60*24*2));
				s3.setCreateUser("1");
				s3.setCustId("400116085");
				s3.setType(MarketingConstance.DEVELOP_TYPE);
				s3.setTime(new Date());
				s3.setPlanId("3");
				scheduleManager.saveSchedule(s3,user);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		
	}
	@Test
	public void testGetCustomerIdByPlanId(){
		String planId  = "2";
		List<String> customerIds = scheduleManager.getCustomerIdByPlanId(planId);
		//Assert.assertNotNull(customerIds);
		//Assert.assertEquals(9,customerIds.size());
//		for(String s : customerIds){
//			System.out.println(s);
//		}
		
	}
//	@Test
//	public void testCountSearchCustomerList(){
//		CustomerVo condition = DataUtilTest.getCustomerVO();
//		condition.setDeptId("1");
//		int i = -1;
//		condition.setScheduleType(MarketingConstance.DEVELOP_TYPE);
//		i = scheduleManager.countSearchCustomerList(condition);
//		Assert.assertEquals(0, i);
//		System.out.println(i);
//		
//		System.out.println("-----------------------------------");
//		condition.setScheduleType(MarketingConstance.MAINTAIN_TYPE);
//		i = scheduleManager.countSearchCustomerList(condition);
//		Assert.assertEquals(0, i);
//		System.out.println(i);
//	}
//	
	@Test
	public void testDeleteSchedule1() {
//		String[] scheduleIds = {"5533","5594","5593","5592","5591","5590"};
//		boolean b = scheduleManager.deleteSchedule(scheduleIds,user);
//		Assert.assertTrue(b);
	}
	
//	@Test
//	public void testGetPlanById(){
//		String id = "5595";
//		Plan p = planManager.getPlanById(id);
//		//Assert.assertNull(p);
//		
//		id = "1425";
//		p = planManager.getPlanById(id);
//		try {
//			id = "";
//			p = planManager.getPlanById(id);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		//Assert.assertNotNull(p);
//	}
	
	@Test
	public void testSearchSchedule(){
		DevelopScheduleVO vo = new DevelopScheduleVO();
		Date start = null;
		Date end =null ;
		try {
			start = new SimpleDateFormat("yyyyMMdd").parse("20121101");
			end  = new SimpleDateFormat("yyyyMMdd").parse("20121221");
		} catch (ParseException e1) {
			
			e1.printStackTrace();
		}
		
		List<DevelopScheduleVO> list=null;
		try {
			vo.setExecuteDeptId("");
			//start.setMonth(2);
			//end.setMonth(6);
			vo.setCreateStartTime(start);
			vo.setCreateEndTime(end);
			vo.setScheduleType(MarketingConstance.DEVELOP_TYPE);
			list  = scheduleManager.searchSchedule(vo, 0, 10,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			vo.setExecuteDeptId(null);
			//start.setMonth(2);
			//end.setMonth(6);
			vo.setCreateStartTime(start);
			vo.setCreateEndTime(end);
			vo.setScheduleType(MarketingConstance.DEVELOP_TYPE);
			list  = scheduleManager.searchSchedule(vo, 0, 10,user);
		} catch (Exception e) {
			e.printStackTrace();
		}

			start = new Date();
			end  = new Date();
	
		try{
			vo.setExecuteDeptId("1");
			start.setMonth(2);
			end.setMonth(6);
			vo.setCreateStartTime(start);
			vo.setCreateEndTime(end);
			vo.setScheduleType(MarketingConstance.DEVELOP_TYPE);
			list  = scheduleManager.searchSchedule(vo, 0, 10,user);
		}catch(GeneralException e){
			e.printStackTrace();
			vo = new DevelopScheduleVO();
			vo.setExecuteDeptId("1");
			start.setMonth(2);
			end.setMonth(3);
			vo.setCreateStartTime(start);
			vo.setCreateEndTime(end);
			vo.setScheduleType(MarketingConstance.DEVELOP_TYPE);
			list = scheduleManager.searchSchedule(vo, 0, 10,user);
			//Assert.assertNotNull(list);
			
			vo.setScheduleType(MarketingConstance.MAINTAIN_TYPE);
			list=scheduleManager.searchSchedule(vo, 0, 10,user);
			//Assert.assertNotNull(list);
				
		}
	}
	@Test
	public void countForSearchSchedule(){
		DevelopScheduleVO vo =null;
		try {
			scheduleManager.countForSearchSchedule(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo = new DevelopScheduleVO();
		Date start = new Date();
		Date end  = new Date();
		vo.setExecuteDeptId("11469");
		start.setMonth(5);
		end.setMonth(7);
		vo.setCreateStartTime(start);
		vo.setCreateEndTime(end);
		vo.setScheduleType(MarketingConstance.DEVELOP_TYPE);
		vo.setPlanId("40000302");
		
		
		int i = scheduleManager.countForSearchSchedule(vo);
		//vo.setScheduleType(MarketingConstance.MAINTAIN_TYPE);
////		Assert.assertSame(0, i);
//		
		vo.setScheduleType(MarketingConstance.MAINTAIN_TYPE);
	    i = scheduleManager.countForSearchSchedule(vo);
//		Assert.assertSame(0, i);
	}

	@Test
	public void testSearchScheduleStatus(){
		List<Schedule> list1 = scheduleManager.searchScheduleByStatus("15",0,20);
		System.out.println(list1.size());
		List<Schedule> list2 = scheduleManager.searchScheduleByStatus("20",0,20);
		System.out.println(list2.size());
	}
	@Test
	public void testDeleteSchedule2(){
		scheduleManager.deleteSchedule("1","2");
	}
	@Test
	public void testCreateScheduleByPlan(){
		Schedule s =null;
		try {
			scheduleManager.createScheduleByPlan(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			s.setCreateDate(new Date(System.currentTimeMillis()-1000*60*60*24*2));
			s.setCreateTime(new Date(System.currentTimeMillis()+1000*60*60*24*2));
			s.setCreateUser("1");
			s.setCustId("400116085");
			s.setType(MarketingConstance.DEVELOP_TYPE);
			s.setPlanId("1");
			s.setTime(new Date());
			scheduleManager.createScheduleByPlan(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetAllSchedule(){
		Plan p = new Plan();
		p.setId("111");
		scheduleManager.getAllSchedule(p);
	}
	@Test
	public void testUpdateScheduleStatus(){
		Schedule s = new Schedule();
		s.setId("403309107");
		s.setStatus("20");
		scheduleManager.updateScheduleStatus(s);
	}
	@Test
	public void testDeleteScheduleById(){
		scheduleManager.deleteScheduleById("444444");
	}
	@Test
	public void testDeleteScheduleByContact(){
		scheduleManager.deleteScheduleByContact("4444", "3333");
	}
	@Test
	public void testUpdOrginalSchdule(){
		scheduleManager.updOrginalSchdule("1", "1");
	}
	@Test
	public void testSearchCustomerList4Plan(){
		SearchCustomerCondition scc =new SearchCustomerCondition();
		scheduleManager.searchCustomerList4Plan(scc, 0, 10, user);
	}
//	@Test
//	public void testSearchScheduleSelf(){
//		scheduleManager.searchScheduleSelf("1", "dev", 0, 10);
//	}
	@Test
	public void testProcessScheduleStatus(){
		List<Schedule> list = new ArrayList<Schedule>();
		Schedule s = new Schedule();
		s.setId("403309107");
		s.setStatus("20");
		list.add(s);
		scheduleManager.processScheduleStatus(list, "20");
	}
//	@Test
//	public void testGetPcScDetail(){
//		scheduleManager.getPcScDetail("1232");
//	}
	@Test
	public void testSearchScheduleByReturnVisit(){
		scheduleManager.searchScheduleByReturnVisit(0,10);
	}
	@Test
	public void testProcessScheduleTODO(){
//		scheduleManager.processScheduleTODO();
	}
	@Test	
	public void testSearchScheduleStatus4Job(){
//		scheduleManager.processScheduleTODO();
	}
	
	@Test
	public void testChangeStatusToCompleteForBO(){
		String custId = "54646464";
		scheduleManager.changeStatusToCompleteForBO(custId);
	}
}
