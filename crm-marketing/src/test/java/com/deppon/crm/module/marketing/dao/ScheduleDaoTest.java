package com.deppon.crm.module.marketing.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.action.DevelopScheduleVO;
import com.deppon.crm.module.marketing.server.action.ToDoPojo;
import com.deppon.crm.module.marketing.server.dao.IScheduleDao;
import com.deppon.crm.module.marketing.server.dao.impl.ScheduleDao;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;


public class ScheduleDaoTest {

	private IScheduleDao scheduleDao;
	private JdbcTemplate jdbc;
	@Before
	public void setUp() throws Exception {
		jdbc = (JdbcTemplate) SpringTestHelper.get().getBean(JdbcTemplate.class);
//		jdbc.execute("delete from t_cust_schedule where fid = 192");
//		jdbc.execute("insert into t_cust_schedule (fid, fcustid, fstatus, ftype) values(192, 192, '20', 'dev')");
		
		scheduleDao = (IScheduleDao) SpringTestHelper.get().getBean(ScheduleDao.class);
	}

	@Test
	public void testUpdateScheduleStatusForTimer() {
		String id = "192";
		Schedule schedule = new Schedule();
		schedule.setId(id);
		schedule.setStatus(MarketingConstance.SCHEDULE_FINISH);
		schedule.setModifyUser(MarketingConstance.TIMERUSERID);
		boolean result = scheduleDao.updateScheduleStatusForTimer(schedule);
		Assert.assertTrue(result);
		
		Schedule response = scheduleDao.searchScheduleById(id);
		Assert.assertNotNull(response);
		Assert.assertEquals(MarketingConstance.SCHEDULE_FINISH, response.getStatus());
		Assert.assertEquals(MarketingConstance.TIMERUSERID, response.getModifyUser());
		
	}
	@Test
	public void testSearchCustomerList(){
		CustomerVo v = new CustomerVo();
		v.setBeginTime(DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 4));
		v.setOverTime(DateUtils.setDays(new Date(), 28));
		v.setDeptId("10800");
		v.setCustType("PC_CUSTOMER");
		List<?> i = new ArrayList();
//		List<?> i = scheduleDao.searchCustomerList(v, 0, 20);
//		Assert.assertNotNull(i);
		
		v = new CustomerVo();
		v.setCustName("李英");
		v.setLinkManName("李英");
		v.setLinkManMobile("13096332260");
//		i = scheduleDao.searchCustomerList(v, 0, 20);
//		Assert.assertNotNull(i);
	}
	
	
	@Test
	public void testCountSearchCustomerList(){
		CustomerVo v = new CustomerVo();
		v.setBeginTime(DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 4));
		v.setOverTime(DateUtils.setDays(new Date(), 28));
		v.setDeptId("11469");
		v.setCustType("PC_CUSTOMER");
//		int i = scheduleDao.countSearchCustomerList(v);
//		Assert.assertNotNull(i);
	}
	
	@Test
	public void testSearchMaintainCustList(){
		try {
			CustomerVo v = new CustomerVo();
			v.setDeptId("464756");
			v.setCustNumbers(new String[]{"006274","006275"});
			List<ScheduleQueryResultObject> list = scheduleDao.searchMaintainCustList(v, 0, 10);
			scheduleDao.countSearchMaintainCustList(v);
			Assert.assertNotNull(list);
			v.setCustName("工厂");
			v.setLinkManName("明");
			scheduleDao.searchMaintainCustList(v, 0, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchScheduleSelf(){
		int start = 0 ;
		int limit = 10;
//		List<DevelopScheduleVO> list1 = scheduleDao.searchDEVScheduleSelf("18706", start, limit);
//		System.out.println(list1.size());
//		int count1 = scheduleDao.getDEVScheduleSelfCount("18706");
//		System.out.println(count1);

		List<DevelopScheduleVO> list2 = scheduleDao.searchMATScheduleSelf("176179", start, limit);
		System.out.println(list2.size());
		int count2 = scheduleDao.getMATScheduleSelfCount("176179");
		System.out.println(count2);
	}

	@Test	
	public void testSearchScheduleStatus(){
		List<Schedule> list1 = scheduleDao.searchScheduleByStatus("15",0,10);
		System.out.println(list1.size());
		List<Schedule> list2 = scheduleDao.searchScheduleByStatus("20",0,10);
		System.out.println(list2.size());
	}

	@Test	
	public void testSearchScheduleStatus4Job(){
		Schedule sche = new Schedule();

		sche.setStatus(MarketingConstance.SCHEDULE_OVERDUE);
		// 统计每日过期日程
		List<ToDoPojo> overdueList = scheduleDao.searchSchedule4Job(sche);

		sche.setStatus(MarketingConstance.SCHEDULE_ASSIGNED);
		// 统计每日已指派日程
		List<ToDoPojo> assignedList = scheduleDao.searchSchedule4Job(sche);

		sche.setStatus(MarketingConstance.SCHEDULE_EXECUTING);
		// 统计每日待进行日程
		List<ToDoPojo> executingList = scheduleDao.searchSchedule4Job(sche);
		
		System.out.println(overdueList.size());
		System.out.println(assignedList.size());
		System.out.println(executingList.size());
	}
	
	@Test
	public void testDeleteDevelopSchedule(){
		Integer planId = 663;
		Integer custId = 555555;
		boolean b = scheduleDao.deleteDevelopSchedule(planId.toString(), custId.toString());
	}
	
	@Test
	public void testCreateSchedules(){
		Schedule sche = new Schedule();
		sche.setCreateUserId("20117");
		sche.setCreateDate(new Date());
		sche.setLastModifyUserId("20117");
		sche.setLastUpdateTime(new Date());
		
		sche.setCustId("444329");
		sche.setCustType(null);
		sche.setTime(new Date());
		sche.setStatus("40");
		sche.setType("mat");
		sche.setExeDeptId("76743");
		sche.setExeManId("125312");
		sche.setContactId(null);
		
		scheduleDao.createSchedules(sche);
	}
	
	@Test
	public void testUpdateSchedule(){
		List<Schedule> sches = scheduleDao.searchScheduleByStatus("15",0,10);
		if(sches.size()>0){
			Schedule sche = sches.get(0);
			sche.setCreateUserId("20117");
			sche.setCreateDate(new Date());
			sche.setLastModifyUserId("20117");
			sche.setLastUpdateTime(new Date());
			sche.setCustId("444329");
			sche.setCustType(null);
			sche.setTime(new Date());
			sche.setType("dev");
			scheduleDao.updateSchedule(sche);
		}				
	}
	
	@Test
	public void testGetAllSchedule(){
		Plan plan = new Plan();
		plan.setId("400000642");
		scheduleDao.getAllSchedule(plan);
	}
	
	@Test
	public void testDelete(){
		Schedule sche = new Schedule();
		sche.setCreateUserId("20117");
		sche.setCreateDate(new Date());
		sche.setLastModifyUserId("20117");
		sche.setLastUpdateTime(new Date());
		
		sche.setCustId("444329");
		sche.setCustType(null);
		sche.setTime(new Date());
		sche.setStatus("40");
		sche.setType("mat");
		sche.setExeDeptId("76743");
		sche.setExeManId("125312");
		sche.setContactId(null);
		
		scheduleDao.createSchedules(sche);
		// 先创建，再删除
		scheduleDao.delete(sche);
	}
	
	@Test
	public void testDeleteScheduleById(){

		Schedule sche = new Schedule();
		sche.setCreateUserId("20117");
		sche.setCreateDate(new Date());
		sche.setLastModifyUserId("20117");
		sche.setLastUpdateTime(new Date());
		
		sche.setCustId("444329");
		sche.setCustType(null);
		sche.setTime(new Date());
		sche.setStatus("40");
		sche.setType("mat");
		sche.setExeDeptId("76743");
		sche.setExeManId("125312");
		sche.setContactId(null);
		
		scheduleDao.createSchedules(sche);
		
		scheduleDao.deleteScheduleById(sche.getId());
	}
	
	@Test
	public void testSearchSchedule(){
		DevelopScheduleVO dsVo = new DevelopScheduleVO();
		dsVo.setPlanId("400000694");
		dsVo.setExecuteDeptId("11469");
		Date date1 = DateUtils.setDays(new Date(), 1) ;
		Date date2 = DateUtils.setDays(new Date(), 28);
		dsVo.setCreateStartTime(date1);
		dsVo.setCreateEndTime(date2);
//		scheduleDao.searchSchedule(dsVo, 0, 10);
		dsVo.setPlaneName("客户");
//		scheduleDao.searchSchedule(dsVo, 0, 10);
//		int i = scheduleDao.countForSearchSchedule(dsVo);
//		System.out.println(i);
	}
	
	@Test
	public void testDeleteScheduleByContact(){
		scheduleDao.deleteScheduleByContact("400000694","686441");
	}
	
	@Test
	public void testSearchMaintainSchedule(){
		DevelopScheduleVO vo = new DevelopScheduleVO();
		vo.setCreateStartTime(new Date());
		vo.setCreateEndTime(new Date());
		vo.setExecuteDeptId("11469");
		vo.setId("400000694");
		scheduleDao.searchMaintainSchedule(vo, 0, 10);
		vo.setPlaneName("客户");
		scheduleDao.searchMaintainSchedule(vo, 0, 10);
		int count = scheduleDao.countForSearchMaintainSchedule(vo);
		System.out.println(count);
	}
	
	@Test
	public void testUpdOrginalSchdule(){
		scheduleDao.updOrginalSchdule("400000694", "000122");
	}
	
	@Test
	public void testSearchScheduleByReturnVisit(){
		scheduleDao.searchScheduleByReturnVisit(0,10);
	}
	
	@Test
	public void testUpdAllScheduleToCompleteByCustId(){
		String custId = "405384415";
		scheduleDao.updAllScheduleToCompleteByCustId(custId);
	}
	
	@Test
	public void testSearchTodoInfo(){
		String custId = "405384415";
		scheduleDao.searchTodoInfo(custId);
	}
}
