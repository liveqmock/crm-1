/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ValidateTest.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author Administrator
 * @version 0.1 2012-3-28
 */
package com.deppon.crm.module.marketing.utilstest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.manager.DataUtilTest;
import com.deppon.crm.module.marketing.server.utils.CycleValidateUtils;
import com.deppon.crm.module.marketing.server.utils.MonitorPlanValidateUtils;
import com.deppon.crm.module.marketing.server.utils.PlanValidateUtils;
import com.deppon.crm.module.marketing.server.utils.ReturnVisitValidateUtils;
import com.deppon.crm.module.marketing.server.utils.ScheduleValiateUtils;
import com.deppon.crm.module.marketing.server.utils.UserInfoUtils;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.BusinessException;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ValidateTest.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author 苏玉军
 * @version 0.1 2012-3-28
 */

public class ValidateTest {

//	@Test
//	public void testIsSameDay(){
//		Date date1=new Date();
//		Date date2=new Date();
//		boolean b=PlanValidateUtils.isSameDay(date1, date2);
//		Assert.assertTrue(b);
//		
//		date2.setMonth(8);
//		Assert.assertFalse(PlanValidateUtils.isSameDay(date1, date2));
//	}
//	
//	@Test
//	public void testIsCustomerListNull(){
//		String[] list=DataUtilTest.getCustList();
//		boolean b=PlanValidateUtils.isCustomerListNull(list);
//		
//		PlanValidateUtils.isCustomerListNull(null);
//		
//	}
//	@Test
//	public void testIsSameDate(){
//		Date date1=new Date();
//		Date date2=new Date();
//		
//		int i=PlanValidateUtils.isSameDate(date1, date2);
//		Assert.assertEquals(0, i);
//		
//		
//		/*date2.setDate(10);
//		i=PlanValidateUtils.isSameDate(date1, date2);
//		Assert.assertEquals(1, i);
//		
//		date2.setDate(29);
//		i=PlanValidateUtils.isSameDate(date1, date2);
//		Assert.assertEquals(0, i);*/
//		
//	} 
//
//	@Test
//	public void testCanCreateReturnvisit() {
//		List<ReturnVisitOpinion> rvoList = new ArrayList<ReturnVisitOpinion>();
//		ReturnVisit rv = null;
//		try {
//			Assert.assertFalse(ReturnVisitValidateUtils.canCreateReturnVisit(rv, null, rvoList));
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//		rv = new ReturnVisit();
//		try {
//			Assert.assertFalse(ReturnVisitValidateUtils.canCreateReturnVisit(rv, null, rvoList));
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//		ReturnVisitOpinion rvo = new ReturnVisitOpinion();
//		rvo.setOpinionType("时效类");
//		rvo.setProblem("有意见有意见有意见");
//		rvo.setSolution("解决解决解决");
//		rvo.setReturnVisitId("6");
//		rvoList.add(rvo);
//		try {
//			Assert.assertTrue(ReturnVisitValidateUtils.canCreateReturnVisit(rv, null, rvoList));
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//		try {
//			rv.setSchedule(null);
//			Assert.assertFalse(ReturnVisitValidateUtils.canCreateSchedule(rv));
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//		try {
//			rv.setSchedule(new Date());
//			Assert.assertTrue(ReturnVisitValidateUtils.canCreateSchedule(rv));
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//
//		try {
//			rv.setSchedule(DateUtils.setDays(new Date(), 1));
//			Assert.assertFalse(ReturnVisitValidateUtils.canCreateSchedule(rv));
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//	}
//
//	@Test
//	public void testInitCreatePageData() {
//		ReturnVisit rv = null;
//		try {
//			ReturnVisitValidateUtils.canInitCreatePageData(rv, null, null);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//		rv = new ReturnVisit();	
//		try {		
//			ReturnVisitValidateUtils.canInitCreatePageData(rv, null, null);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//		try {
//			rv.setScheduleId("1845");
//			ReturnVisitValidateUtils.canInitCreatePageData(rv, null, null);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//		try {
//			rv.setScheduleId("1845");
//			rv.setScheType(MarketingConstance.DEVELOP_TYPE);
//			Assert.assertNotNull(ReturnVisitValidateUtils.canInitCreatePageData(rv, null, null));
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//		try {
//			rv.setScheduleId("1845");
//			rv.setScheType(MarketingConstance.MAINTAIN_TYPE);
//			Assert.assertNotNull(ReturnVisitValidateUtils.canInitCreatePageData(rv, null, null));
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//	}
//	
//	@Test
//	public void testPlanValidateNewPlan(){
//		boolean rs = false;
//		Plan newPlan = new Plan();
//		List<Plan> planList = null;
//		try {
//			rs = PlanValidateUtils.validateNewPlan(newPlan, planList);
//			Assert.assertTrue(rs);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//
//		try {
//			planList = new ArrayList<Plan>();
//			rs = PlanValidateUtils.validateNewPlan(newPlan, planList);
//			Assert.assertTrue(rs);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//		try {
//			newPlan.setId("1");
//			newPlan.setTopic("abc");
//			planList = new ArrayList<Plan>();
//			planList.add(newPlan);
//			rs = PlanValidateUtils.validateNewPlan(newPlan, planList);
//			System.out.println(rs);
////			Assert.assertTrue(rs);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//
//		try {
//			newPlan.setId("1");
//			newPlan.setTopic("abc");
//
//			Plan oldPlan = new Plan();
//			oldPlan.setId("2");
//			oldPlan.setTopic("abc");
//			planList = new ArrayList<Plan>();
//			planList.add(oldPlan);
//			rs = PlanValidateUtils.validateNewPlan(newPlan, planList);
//			System.out.println(rs);
////			Assert.assertFalse(rs);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//
//	}
//
//	
//	@Test
//	public void testCanSearchMonitorPlan(){
//		MonitorPlanQueryCondition condition = new MonitorPlanQueryCondition();
//		Date start = DateUtils.setMonths(DateUtils.setDays(new Date(), 1), 2); // 3/1
//		Date end = DateUtils.setMonths(DateUtils.setDays(new Date(), 15), 2); // 3/15
//		try {
//			condition.setPlanStartDate(start);
//			condition.setPlanOverDate(end);
//			MonitorPlanValidateUtils.canSearchMonitorPlan(condition);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//		
//		try {
//			condition.setPlanStartDate(start);
//			condition.setPlanOverDate(end);
//			
//			condition.setExecdeptId("");
//			condition.setExecuserId("");
//			condition.setTopic("");
//			condition.setPlanStatus("");
//			
//			MonitorPlanValidateUtils.canSearchMonitorPlan(condition);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//
//		try {
//			condition.setPlanStartDate(start);
//			condition.setPlanOverDate(end);
//			
//			condition.setExecdeptId("1");
//			condition.setExecuserId("");
//			condition.setTopic("");
//			condition.setPlanStatus("");
//			
//			boolean rs = MonitorPlanValidateUtils.canSearchMonitorPlan(condition);
//			Assert.assertTrue(rs);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//	}
//
//	@Test
//	public void testComparisonDateByField(){
//		Date d1 = new Date();
//		Date d2 = new Date();
//		boolean rs = MonitorPlanValidateUtils.comparisonDateByField(d1, d2, Calendar.DATE, 1);
//		Assert.assertTrue(rs);
//
//		d1 = DateUtils.setDays(new Date(), 1);
//		d2 = DateUtils.setDays(new Date(), 3);
//		rs = MonitorPlanValidateUtils.comparisonDateByField(d1, d2, Calendar.DATE, 1);
//		Assert.assertFalse(rs);
//		
//
//		d1 = DateUtils.setMonths(DateUtils.setDays(new Date(), 28), 3);
//		d2 = DateUtils.setMonths(DateUtils.setDays(new Date(), 27), 4);
//		rs = MonitorPlanValidateUtils.comparisonDateByField(d1, d2, Calendar.MONTH, 1);
//		Assert.assertTrue(rs);
//
//		d1 = DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 3);
//		d2 = DateUtils.setMonths(DateUtils.setDays(new Date(), 4), 5);
//		rs = MonitorPlanValidateUtils.comparisonDateByField(d1, d2, Calendar.MONTH, 1);
//		Assert.assertFalse(rs);
//		
//
//		d1 = DateUtils.setYears(DateUtils.setDays(new Date(), 5), 2012);
//		d2 = DateUtils.setYears(DateUtils.setDays(new Date(), 4), 2013);
//		rs = MonitorPlanValidateUtils.comparisonDateByField(d1, d2, Calendar.YEAR, 1);
//		Assert.assertTrue(rs);
//
//		d1 = DateUtils.setYears(DateUtils.setDays(new Date(), 5), 2012);
//		d2 = DateUtils.setYears(DateUtils.setDays(new Date(), 5), 2013);
//		rs = MonitorPlanValidateUtils.comparisonDateByField(d1, d2, Calendar.YEAR, 1);
//		Assert.assertFalse(rs);
//	}
//	
//	@Test
//	public void testCanDeletePlan(){
//		try {
//			Plan p = new Plan();
//			p.setCreateUserId("1");
//			Plan p2 = new Plan();
//			p2.setCreateUserId("0");
//			String userId = "1";
//			List<Plan> plans = new ArrayList<Plan>();
//			PlanValidateUtils.canDeletePlan(plans, userId);
//			plans.add(p);
//			PlanValidateUtils.canDeletePlan(plans, userId);
//			plans.add(p2);
//			PlanValidateUtils.canDeletePlan(plans, userId);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//	
//	@Test
//	public void testCanGetReturnVisitList(){
//		ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
//		try {
//			ReturnVisitValidateUtils .canGetReturnVisitList(null);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//		
//		try {
//			ReturnVisitValidateUtils .canGetReturnVisitList(condition);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//
//		try {
//			condition.setLinkName("1");
//			condition.setBeginTime(DateUtils.setDays(new Date(), 10));
//			condition.setEndTime(DateUtils.setDays(new Date(), 1));
//			ReturnVisitValidateUtils.canGetReturnVisitList(condition);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//		try {
//			condition.setLinkName("1");
//			condition.setBeginTime(new Date());
//			condition.setEndTime(new Date());
//			boolean rs = ReturnVisitValidateUtils .canGetReturnVisitList(condition);
//			Assert.assertTrue(rs);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//
//	}
//	
//	@Test
//	public void testCycleIsConditonNull(){
//		CustomerGroupDetail detail = new CustomerGroupDetail();
//		Assert.assertTrue(CycleValidateUtils.isConditonNull(null));
//
//		detail.setDeptId(null);
//		Assert.assertTrue(CycleValidateUtils.isConditonNull(detail));
//		
////		detail.setDeptId("1");
////		Assert.assertFalse(CycleValidateUtils.isConditonNull(detail));
//
//		detail.setDeptId(null);
//		detail.setQueryDate(null);
//		Assert.assertTrue(CycleValidateUtils.isConditonNull(detail));
//
//		detail.setDeptId(null);
//		detail.setQueryDate(new Date());
////		Assert.assertFalse(CycleValidateUtils.isConditonNull(detail));
//
//		detail.setDeptId(null);
//		detail.setQueryDate(null);
//		detail.setCustNumber(null);
//		Assert.assertTrue(CycleValidateUtils.isConditonNull(detail));
//
//		detail.setDeptId(null);
//		detail.setQueryDate(null);
//		detail.setCustNumber("1");
////		Assert.assertFalse(CycleValidateUtils.isConditonNull(detail));
//
//		detail.setDeptId(null);
//		detail.setQueryDate(null);
//		detail.setCustNumber(null);
//		detail.setGroupId(null);
////		Assert.assertTrue(CycleValidateUtils.isConditonNull(detail));
//
//		detail.setDeptId(null);
//		detail.setQueryDate(null);
//		detail.setCustNumber(null);
//		detail.setGroupId("1");
////		Assert.assertFalse(CycleValidateUtils.isConditonNull(detail));
//
//		detail.setDeptId(null);
//		detail.setQueryDate(null);
//		detail.setCustNumber(null);
//		detail.setGroupId(null);
//		detail.setEmpName(null);
//		Assert.assertTrue(CycleValidateUtils.isConditonNull(detail));
//
//		detail.setDeptId(null);
//		detail.setQueryDate(null);
//		detail.setCustNumber(null);
//		detail.setGroupId(null);
//		detail.setEmpName("1");
//		Assert.assertTrue(CycleValidateUtils.isConditonNull(detail));
//		
//	}
//	
//	@Test
//	public void testIsScheduleCannDelete(){
//		Plan plan = new Plan();
//		try {
//			plan.setStatus(MarketingConstance.FINISHED);
//			ScheduleValiateUtils.isScheduleCannDelete(plan, null, null);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//
//		try {
//			plan.setStatus(MarketingConstance.OVERDUE);
//			ScheduleValiateUtils.isScheduleCannDelete(plan, null, null);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//
//		try {
//			plan.setStatus(MarketingConstance.EXECUTING);
//			Schedule sche = new Schedule ();
//			sche.setExeManId("11");
//			sche.setStatus(MarketingConstance.SCHEDULE_FINISH);
//			User user = new User();
//			Employee e = new Employee();
//			e.setId("1");
//			user.setEmpCode(e);
//			user.setId("1");
//			ScheduleValiateUtils.isScheduleCannDelete(plan, sche, user);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//		}
//
//		try {
//			plan.setStatus(MarketingConstance.EXECUTING);
//			Schedule sche = new Schedule ();
//			sche.setExeManId("1");
//			User user = new User();
//			user.setId("1");
//			Employee e = new Employee();
//			e.setId("1");
//			user.setEmpCode(e);
//			sche.setStatus(MarketingConstance.SCHEDULE_FINISH);
//			ScheduleValiateUtils.isScheduleCannDelete(plan, sche, user);
//		} catch (BusinessException e) {
//			// TODO: handle exception
//			System.out.println(11);
//		}
//
//		try {
//			plan.setStatus(MarketingConstance.EXECUTING);
//			Schedule sche = new Schedule ();
//			sche.setExeManId("1");
//			sche.setStatus(MarketingConstance.SCHEDULE_EXECUTING);
//			User user = new User();
//			user.setId("1");
//			Employee e = new Employee();
//			e.setId("1");
//			user.setEmpCode(e);
//			boolean rs = ScheduleValiateUtils.isScheduleCannDelete(plan, sche, user);
//			assertTrue(rs);
//		} catch (BusinessException e) {
//			System.out.println(22);
//		}
//	}
//	
//	@Test
//	public void testCanUpdateSchedule(){
//		Plan plan = new Plan();
//		Schedule osche = new Schedule ();
//		Schedule nsche = new Schedule ();
//		User user = new User();
//		Employee e = new Employee();
//		e.setId("1");
//		user.setEmpCode(e);
//		try {
//			ScheduleValiateUtils.canUpdateSchedule(plan, osche, nsche, user);
//		} catch (BusinessException e1) {
//			System.out.println(e1);
//		}
//		try {
//			nsche.setTime(new Date());
//			osche.setExeManId("1");
//			user.setId("1");
//			
//			plan.setStatus(MarketingConstance.OVERDUE);
//			ScheduleValiateUtils.canUpdateSchedule(plan, osche, nsche, user);
//		} catch (BusinessException e1) {		}
//
//		try {
//			nsche.setTime(new Date());
//			osche.setExeManId("1");
//			user.setId("1");
//			plan.setStatus(MarketingConstance.FINISHED);
//			ScheduleValiateUtils.canUpdateSchedule(plan, osche, nsche, user);
//		} catch (BusinessException e1) {		}
//		try {
//			nsche.setTime(new Date());
//			osche.setExeManId("1");
//			user.setId("2");
//			ScheduleValiateUtils.canUpdateSchedule(plan, osche, nsche, user);
//		} catch (BusinessException e1) {		}
//
//		try {
//			nsche.setTime(new Date());
//			osche.setExeManId("1");
//			osche.setStatus(MarketingConstance.EXECUTING);
//			user.setId("2");
//			try {
//				plan = null;
//				ScheduleValiateUtils.canUpdateSchedule(plan, osche, nsche, user);
//			} catch (BusinessException e1) {
//				// TODO: handle exception
//			}
//			try {
//				plan = new Plan();
//				plan.setStatus(MarketingConstance.FINISHED);
//				ScheduleValiateUtils.canUpdateSchedule(plan, osche, nsche, user);
//			} catch (BusinessException e1) {
//				// TODO: handle exception
//			}
//			try {
//				plan = new Plan();
//				plan.setStatus(MarketingConstance.OVERDUE);
//				ScheduleValiateUtils.canUpdateSchedule(plan, osche, nsche, user);
//			} catch (BusinessException e1) {
//				// TODO: handle exception
//			}
//			try {
//				plan = new Plan();
//				plan.setStatus(MarketingConstance.EXECUTING);
//				plan.setBeginTime(DateUtils.setDays(new Date(), 1));
//				plan.setEndTime(DateUtils.setDays(new Date(), 10));
//				nsche.setTime(new Date());
//				ScheduleValiateUtils.canUpdateSchedule(plan, osche, nsche, user);
//			} catch (BusinessException e1) {
//				// TODO: handle exception
//			}
//
//
//			try {
//				plan = new Plan();
//				plan.setStatus(MarketingConstance.EXECUTING);
//				plan.setBeginTime(DateUtils.setDays(new Date(), 1));
//				plan.setEndTime(DateUtils.setDays(new Date(), 10));
//				nsche.setTime(new Date());
//				
//				osche.setStatus(MarketingConstance.SCHEDULE_FINISH);
//				ScheduleValiateUtils.canUpdateSchedule(plan, osche, nsche, user);
//			} catch (BusinessException e1) {
//				// TODO: handle exception
//			}
//			try {
//				plan = new Plan();
//				plan.setStatus(MarketingConstance.EXECUTING);
//				plan.setBeginTime(DateUtils.setDays(new Date(), 1));
//				plan.setEndTime(DateUtils.setDays(new Date(), 10));
//				nsche.setTime(new Date());
//				
//				osche.setStatus(MarketingConstance.SCHEDULE_EXECUTING);
//				ScheduleValiateUtils.canUpdateSchedule(plan, osche, nsche, user);
//			} catch (BusinessException e1) {
//				// TODO: handle exception
//			}
//		} catch (BusinessException e1) {		}
//		
//	}
//	
//	@Test
//	public void testUserInfoUtils(){
//		User user = new User();
//		Employee emp = new Employee();
//		emp.setId("18706");
//		Department dept = new Department();
//		dept.setId("11469");
//		emp.setDeptId(dept);
//		user.setEmpCode(emp);
//		UserInfoUtils.getCurrentDeptId(user);
//		UserInfoUtils.getCurrentUserId(user);
//		
//	}
}
