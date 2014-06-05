/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PlanValidateUtilsTest.java
 * @package com.deppon.crm.module.marketing.utilstest 
 * @author ZhuPJ
 * @version 0.1 2012-12-5
 */
package com.deppon.crm.module.marketing.utilstest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.manager.impl.ReturnVisitManager;
import com.deppon.crm.module.marketing.server.utils.PlanValidateUtils;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.PlanDevelopCondition;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.shared.exception.PlanException;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.BusinessException;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PlanValidateUtilsTest.java
 * @package com.deppon.crm.module.marketing.utilstest 
 * @author ZhuPJ
 * @version 0.1 2012-12-5
 */

public class PlanValidateUtilsTest {
	private Employee emp;
	@Test
	public void testCanSearchMemberList(){
		CustomerVo vo = new CustomerVo();
		try {
			vo.setBeginTime(new Date());
			vo.setOverTime(new Date());
			PlanValidateUtils.canSearchMemberList(vo);
		} catch (BusinessException e1) {
			// TODO: handle exception
		}
		try {
			vo.setBeginTime(new Date());
			vo.setOverTime(DateUtils.addMonths(new Date(), -20));
			PlanValidateUtils.canSearchMemberList(vo);
		} catch (BusinessException e1) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testIsCustomerListNull(){
		String[] custormerList = null;
		PlanValidateUtils.isCustomerListNull(custormerList);
		custormerList = new String[3];
		System.out.print(custormerList.length);
		custormerList[0] = "xiao";
		custormerList[1] = "hong";
		custormerList[2] = "ye";
		PlanValidateUtils.isCustomerListNull(custormerList);
	}
	
	@Test
	public void testIsScheduleStatusOfPlan(){
		List<Schedule> scheList = new ArrayList<Schedule>();
		Schedule s = new Schedule();
		s.setStatus(MarketingConstance.SCHEDULE_ASSIGNED);
		scheList.add(s);
		PlanValidateUtils.isScheduleStatusOfPlan(scheList);

		s.setStatus(MarketingConstance.SCHEDULE_FORMULATE);
		scheList.removeAll(scheList);
		scheList.add(s);
		PlanValidateUtils.isScheduleStatusOfPlan(scheList);
	}
	
	@Test
	public void testCanDeletePlan(){
		String userId = "105944";
		List<Plan> plans = new ArrayList<Plan>();
		for(int i = 1;i <= 5;i++){
			Plan plan = new Plan();
			plan.setCreateUserId(userId);
			plan.setStatus(MarketingConstance.FINISHED);
			plans.add(plan);
		}
		try{
			PlanValidateUtils.canDeletePlan(plans, userId);
		}
		catch(PlanException e){
			//System.out.println("计划状态不符合要求");
		}
		
		for(Plan p:plans){
			p.setStatus(MarketingConstance.OVERDUE);
		}
		try{
			PlanValidateUtils.canDeletePlan(plans, userId);
		}
		catch(PlanException e){
			//System.out.println("计划状态不符合要求");
		}
		
		for(Plan p:plans){
			p.setStatus(MarketingConstance.EXECUTING);
		}
		try{
			PlanValidateUtils.canDeletePlan(plans, userId);
		}
		catch(PlanException e){
			//System.out.println("计划状态不符合要求");
		}
		for(Plan p:plans){
			p.setStatus(MarketingConstance.NOTEXECUTE);
		}
		try{
			PlanValidateUtils.canDeletePlan(plans, userId);
		}
		catch(PlanException e){
			//System.out.println("计划状态不符合要求");
		}
		
		for(Plan p:plans){
			List<Schedule> scheduleList = null;
			p.setScheduleList(scheduleList);
		}
		try{
			PlanValidateUtils.canDeletePlan(plans, userId);
		}
		catch(PlanException e){

		}
		for(Plan p:plans){
			List<Schedule> scheduleList = new ArrayList<Schedule>();
			for(int i = 1;i<=2;i++){
				Schedule schedule = new Schedule();
				schedule.setStatus("10");
				scheduleList.add(schedule);
				
			}
			p.setScheduleList(scheduleList);
		}
		try{
			PlanValidateUtils.canDeletePlan(plans, userId);
		}
		catch(PlanException e){

		}
		for(Plan p:plans){
			p.getScheduleList().get(0).setStatus("20");
		}
		try{
			PlanValidateUtils.canDeletePlan(plans, userId);
		}
		catch(PlanException e){

		}
	}
	
	@Test
	public void testIsPassedValidate(){
		Plan plan = new Plan();
		String[] customerList = null;
		List<Plan> planList = new ArrayList<Plan>();
		String deptId = "11469";
		try{
			PlanValidateUtils.isPassedValidate(plan, customerList, planList, deptId);
		}
		catch(PlanException e){
			
		}
		
		plan.setExecdeptid(deptId);
		try{
			PlanValidateUtils.isPassedValidate(plan, customerList, planList, deptId);
		}
		catch(PlanException e){
			
		}
		Date date = new Date();
		plan.setBeginTime(DateUtils.addDays(date,2));
		try{
			PlanValidateUtils.isPassedValidate(plan, customerList, planList, deptId);
		}
		catch(PlanException e){
			
		}
		plan.setEndTime(DateUtils.addMonths(date, -2));
		try{
			PlanValidateUtils.isPassedValidate(plan, customerList, planList, deptId);
		}
		catch(PlanException e){
			
		}
		plan.setEndTime(DateUtils.addMonths(date, 3));
		try{
			PlanValidateUtils.isPassedValidate(plan, customerList, planList, deptId);
		}
		catch(PlanException e){
			
		}
		
		plan.setEndTime(DateUtils.addDays(date, 20));
		try{
			PlanValidateUtils.isPassedValidate(plan, customerList, planList, deptId);
		}
		catch(PlanException e){
			
		}
		customerList = new String[2];
		plan.setPlanType(MarketingConstance.DEVELOP_TYPE);
		try{
			PlanValidateUtils.isPassedValidate(plan, customerList, planList, deptId);
		}
		catch(PlanException e){
			
		}
		plan.setPlanType(MarketingConstance.FIXEDPLAN_MONTHLY_TYPE);
		try{
			PlanValidateUtils.isPassedValidate(plan, customerList, planList, deptId);
		}
		catch(PlanException e){
			
		}
//		plan.setPlanType(MarketingConstance.FIXEDPLAN_DAILY_TYPE);
		plan.setBeginTime(date);
		try{
			PlanValidateUtils.isPassedValidate(plan, customerList, planList, deptId);
		}
		catch(PlanException e){
			
		}
	}
	
	@Test
	public void testCanUpdatePlan(){
		Plan plan = new Plan();
		Plan oldPlan = new Plan();
		List<Plan> planList = new ArrayList<Plan>();
		List<Schedule> scheList = new ArrayList<Schedule>();
		String userId = null;
		String managerId = null;
		String[] contactIds = null;
		oldPlan.setPlanType(MarketingConstance.FIXEDPLAN_MONTHLY_TYPE);
		oldPlan.setStatus(MarketingConstance.OVERDUE);
		String[] outOrLeave = {"105944","111111"};
		String[] custList = {"105944","111111"};
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		
//		oldPlan.setPlanType(MarketingConstance.FIXEDPLAN_DAILY_TYPE);
		oldPlan.setStatus(MarketingConstance.NOTEXECUTE);
		for(int i = 0;i<=2;i++){
			Schedule sche = new Schedule();
			sche.setStatus(MarketingConstance.SCHEDULE_FORMULATE);
			scheList.add(sche);
		}
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		for(Schedule sche:scheList){
			sche.setStatus(MarketingConstance.SCHEDULE_ASSIGNED);
		}
		contactIds = new String[2];
//		try{
//			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
//					outOrLeave, contactIds,emp,null, outOrLeave);
//		}
//		catch(PlanException e){
//			
//		}
		contactIds = new String[3];
		contactIds[0] = "105944";
		contactIds[1] = "111111";
		contactIds[2] = "008002";
		scheList.get(0).setContactId("000000");
//		try{
//			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
//					outOrLeave, contactIds,emp,null, outOrLeave);
//		}
//		catch(PlanException e){
//			
//		}
		
		scheList.get(0).setContactId(contactIds[0]);
		scheList.get(1).setContactId(contactIds[1]);
		scheList.get(2).setContactId(contactIds[2]);
		Date date = new Date();
		plan.setBeginTime(date);
		oldPlan.setBeginTime(DateUtils.addDays(date, 1));
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		oldPlan.setBeginTime(date);
		plan.setEndTime(DateUtils.addDays(date, 10));
		oldPlan.setEndTime(DateUtils.addDays(date, 11));
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		oldPlan.setEndTime(DateUtils.addDays(date, 10));
		plan.setTopic("客户回访");
		oldPlan.setTopic("客户意见");
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		oldPlan.setTopic("客户回访");
		plan.setActivedesc("满意");
		oldPlan.setActivedesc("不满意");
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		oldPlan.setActivedesc("满意");
		userId = "105944";
		managerId = "111111";
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		managerId = "105944";
		plan.setExecuserid("000000");
		oldPlan.setExecuserid("000000");
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		
		oldPlan.setExecuserid("111111");
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		
		oldPlan.setPlanType(MarketingConstance.DEVELOP_TYPE);
		oldPlan.setStatus(MarketingConstance.FINISHED);
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		oldPlan.setStatus(MarketingConstance.OVERDUE);
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		
		oldPlan.setStatus(MarketingConstance.EXECUTING);
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		oldPlan.setStatus(MarketingConstance.NOTEXECUTE);
		oldPlan.setCreateUserId("000000");
		userId = "111111";
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		userId = "000000";
		plan.setBeginTime(null);
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		plan.setBeginTime(DateUtils.addDays(date, -2));
		plan.setEndTime(null);
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		plan.setEndTime(DateUtils.addDays(date, -10));
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		
		plan.setEndTime(DateUtils.addDays(date, 80));
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		
		plan.setEndTime(DateUtils.addDays(date, 22));
		plan.setPlanType(MarketingConstance.DEVELOP_TYPE);
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		
		plan.setBeginTime(DateUtils.addDays(date, 2));
		planList = null;
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		plan.setPlanType(MarketingConstance.FIXEDPLAN_MONTHLY_TYPE);
		planList = new ArrayList<Plan>();
		Plan newPlan = new Plan();
		newPlan.setTopic("客户");
		newPlan.setId("001");
		planList.add(newPlan);
		plan.setTopic("客户");
		plan.setId("001");
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
//		plan.setPlanType(MarketingConstance.FIXEDPLAN_DAILY_TYPE);
		plan.setId("003");
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
		
		scheList = new ArrayList<Schedule>();
		Schedule schedule1 = new Schedule();
		schedule1.setStatus(MarketingConstance.SCHEDULE_EXECUTING);
		Schedule schedule2 = new Schedule();
		schedule2.setStatus(MarketingConstance.SCHEDULE_ASSIGNED);
		scheList.add(schedule1);
		scheList.add(schedule2);
		planList = null;
		System.out.println(scheList.get(0).getStatus()+"\n"+scheList.get(1).getStatus());
		try{
			PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList, scheList,
					outOrLeave, contactIds,emp,null, outOrLeave);
		}
		catch(PlanException e){
			
		}
	}
	
	@Test
	public void testIsSelfPlan(){
		Plan p = new Plan();
		p.setCreateUserId("2");
		PlanValidateUtils.isSelfPlan(p, "1");
		PlanValidateUtils.isSelfPlan(p, "2");
	}
	
	@Test
	public void testIsPassTimeLimit(){
		PlanDevelopCondition condition = new PlanDevelopCondition();
		// 时间不为空、结束不能大于开始，相差不能超过3个月
		Date d1 = new Date();
		Date d2 = new Date();
		
		condition.setPlanStartDate(d1);
		condition.setPlanOverDate(d2);
		PlanValidateUtils.validateDevelopPlanSearch(condition);

		condition.setPlanStartDate(null);
		condition.setPlanOverDate(null);
		PlanValidateUtils.validateDevelopPlanSearch(condition);

		condition.setPlanStartDate(d1);
		condition.setPlanOverDate(null);
		PlanValidateUtils.validateDevelopPlanSearch(condition);

		condition.setPlanStartDate(null);
		condition.setPlanOverDate(d2);
		PlanValidateUtils.validateDevelopPlanSearch(condition);
		// start大于end
		condition.setPlanStartDate(d1);
		condition.setPlanOverDate(DateUtils.addMonths(d2, -1));
		PlanValidateUtils.validateDevelopPlanSearch(condition);

		// start小于end
		condition.setPlanStartDate(d1);
		condition.setPlanOverDate(DateUtils.addMonths(d2, 1));
		PlanValidateUtils.validateDevelopPlanSearch(condition);

		// 相差3月以上
		condition.setPlanStartDate(d1);
		condition.setPlanOverDate(DateUtils.addMonths(d2, 4));
		try{
			PlanValidateUtils.validateDevelopPlanSearch(condition);
		}catch(Exception e){
			
		}
	}
	
	@Test
	public void testCheckDeleteIdsIsNotNull(){
		String[] ids = null;
		try {
			PlanValidateUtils.checkDeleteIdsIsNotNull(ids);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			ids = new String[]{};
			PlanValidateUtils.checkDeleteIdsIsNotNull(ids);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			ids = new String[]{"1"};
			PlanValidateUtils.checkDeleteIdsIsNotNull(ids);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testDateCompareTo(){
		PlanValidateUtils.dateCompareTo(new Date(), new Date());
	}
	
	@Test
	public void testCheckContactIds(){
		boolean rs = false;
		String[] contactIds = new String[]{"1","2","3"};
		List<Schedule> schList = new ArrayList<Schedule>();
		for(int i=1;i<4;i++){
			Schedule s = new Schedule();
			s.setContactId(i+"");
			schList.add(s);
		}
		// 无变更
		rs = PlanValidateUtils.checkContactIdsNotChange(contactIds, schList);
		Assert.assertTrue(rs);
		// 删除个别
		contactIds = new String[]{"1","2"};
		rs = PlanValidateUtils.checkContactIdsNotChange(contactIds, schList);
		Assert.assertFalse(rs);
		// 增加个别
		contactIds = new String[]{"1","2","3","4"};
		rs = PlanValidateUtils.checkContactIdsNotChange(contactIds, schList);
		Assert.assertFalse(rs);
		// 修改个别
		contactIds = new String[]{"1","3","4"};
		rs = PlanValidateUtils.checkContactIdsNotChange(contactIds, schList);
		Assert.assertFalse(rs);
	}
	@Test
	public void testCheckDemission(){
		Employee emp = new Employee();
		// 校验计划执行人是否已经离职
		PlanValidateUtils.checkDemission(null);
		PlanValidateUtils.checkDemission(emp);
		emp.setStatus(true);
		PlanValidateUtils.checkDemission(emp);
		emp.setStatus(false);
		PlanValidateUtils.checkDemission(emp);
	}
	@Test
	public void testCheckExecuteEmpMove(){
		// 校验计划执行人是否已经异动
		Employee emp = new Employee();
		Department dept = new Department();
		PlanValidateUtils.checkExecuteEmpMove(null, null);
		PlanValidateUtils.checkExecuteEmpMove(emp, null);
		emp.setDeptId(dept);
		PlanValidateUtils.checkExecuteEmpMove(emp, null);
		dept.setId("106139");
		Plan plan = new Plan();
		plan.setExecdeptid("110");
		boolean isFalse = PlanValidateUtils.checkExecuteEmpMove(emp, plan);
		plan.setExecdeptid("106139");
		boolean isRight = PlanValidateUtils.checkExecuteEmpMove(emp, plan);
	}
	@Test
	public void testIsOutOrLeave(){
		Employee emp = new Employee();
		Plan plan = new Plan();
		PlanValidateUtils.isOutOrLeave(emp, plan);
	}
	
	@Test
	public void testGetCoopString(){
		ReturnVisitManager manager = new ReturnVisitManager();
		List<String> cs = new ArrayList<String>();
		cs.add("11");
		cs.add("11");
		cs.add("22");
		cs.add("33");
		cs.add("44");
		cs.add("44");
		cs.add("44");
		//Expected "11,22,33,44;"
		System.out.println(manager.getCoopString(cs));
		Assert.assertEquals("44,22,33,11", manager.getCoopString(cs));
	}
}
