/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitManagerTest.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author ZhuPJ
 * @version 0.1 2012-3-28
 */
package com.deppon.crm.module.marketing.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.action.ReturnVisitVO;
import com.deppon.crm.module.marketing.server.manager.IReturnVisitManager;
import com.deppon.crm.module.marketing.server.manager.impl.ReturnVisitManager;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotential;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title TestReturnVisitManager.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author ZhuPJ
 * @version 0.1 2012-3-28
 */

public class ReturnVisitManagerTest {
	private IReturnVisitManager rvManager;
	private User user;
	private JdbcTemplate jdbc;
	
	@Before
	public void setUp() throws Exception {
		rvManager=SpringTestHelper.get().getBean(ReturnVisitManager.class);
		user = DataUtilTest.getUser();
		// init UserContext
		User user=new User();
		user.setId("304896");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("238217", null);
		map.put("1", null);
		map.put("250218", null);
		map.put("22241", null);
		map.put("26238", null);
		Set<String> ids = map.keySet();
		user.setDeptids(ids);
		Employee emp = new Employee();
		emp.setId("1");
		Department dept=new Department();
		dept.setId("1");
		emp.setDeptId(dept);
		user.setEmpCode(emp);
		UserContext.setCurrentUser(user);
	
		jdbc = (JdbcTemplate) SpringTestHelper.get().getBean(JdbcTemplate.class);

		jdbc.execute("delete from t_cust_returnvisit where fid = 192");
		jdbc.execute("insert into t_cust_returnvisit (fid, flinkmanname, flinkmanmobile, flinkmanphone, fscheduleid, fway, fdate, fcreatetime, fcreateuserid, fexecdeptid) values(192, '王二麻子', '13918882222', '0755-9876451', 192, 'VISIT', sysdate, sysdate, 192, 1)");

		jdbc.execute("delete from t_cust_schedule where fid = 192");
		jdbc.execute("insert into t_cust_schedule (fid, fplanid, fcustid, fstatus, ftype, fcreatetime, fcreateuserid) values(192, 192, 192, '进行中', 'dev', sysdate, 192)");

		jdbc.execute("delete from t_cust_developmaintainlist where fid = 192");
		jdbc.execute("insert into t_cust_developmaintainlist (fid, fbegintime, fendtime, fplantype, ftopic) values(192, sysdate, sysdate, 'dev', '测试用开发主题')");

		jdbc.execute("delete from t_cust_returnvisitdetail where fid = 1192");
		jdbc.execute("delete from t_cust_returnvisitdetail where fid = 1193");
		jdbc.execute("insert into t_cust_returnvisitdetail (fid, fparentid, fleacityid, farrcityid, fvolumpoten, fcoopcompany) values(1192, 192, '上海', '南京', '600', 'deppon')");
		jdbc.execute("insert into t_cust_returnvisitdetail (fid, fparentid, fleacityid, farrcityid, fvolumpoten, fcoopcompany) values(1193, 192, '上海', '北京', '500', 'deppon')");

		jdbc.execute("delete from t_cust_revisitopiniondetail where fid = 1192");
		jdbc.execute("delete from t_cust_revisitopiniondetail where fid = 1193");
		jdbc.execute("insert into t_cust_revisitopiniondetail(fid, fparentid, fneedtype, fquestion, fsolve) values(1192, 192, '时效', '及时吗', '及时')");
		jdbc.execute("insert into t_cust_revisitopiniondetail(fid, fparentid, fneedtype, fquestion, fsolve) values(1193, 192, '安全', '安全吗', '安全')");
	}
	

	@Test 
	public void testCreateReturnVisitSchedule(){ 
		user = DataUtilTest.getUser();
		// init UserContext
		User user=new User();
		user.setId("1");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("238217", null);
		map.put("1", null);
		map.put("250218", null);
		map.put("22241", null);
		map.put("26238", null);
		Set<String> ids = map.keySet();
		user.setDeptids(ids);
		Employee emp = new Employee();
		emp.setId("1");
		Department dept=new Department();
		dept.setId("1");
		emp.setDeptId(dept);
		user.setEmpCode(emp);
		UserContext.setCurrentUser(user);
	
		/*
		 * 无法创建schedule因为在创建日程的时候CustId肯定为空!!!!
		 */
		ReturnVisitVO rv = new ReturnVisitVO();
		/********************** 回访基本信息 start ********************************/
		rv.setScheType(MarketingConstance.DEVELOP_TYPE);
		rv.setPlanId("666");
		rv.setScheduleId("1815");
		rv.setMemberId("");		// 会员
		rv.setLinkManId("888888888");
		rv.setLinkName("Zpj");
		rv.setLinkManMobile("138111111");
		rv.setLinkManPhone("021123456");
		rv.setAccording("10");
		rv.setWay("");
		/********************** 回访基本信息  end ********************************/

		/********************** 回访意见信息 start ********************************/
		ReturnVisitOpinion rvo = new ReturnVisitOpinion();
		rvo.setOpinionType("时效类");
		rvo.setProblem("有意见有意见有意见");
		rvo.setSolution("解决解决解决");
		rvo.setReturnVisitId("6"); 
		
		List<ReturnVisitOpinion> rvoList = new ArrayList<ReturnVisitOpinion>();
		/********************** 回访意见信息 end ********************************/


		/********************** 回访走货信息 start ********************************/
		ReturnVisitVolumePotential rvvp = new ReturnVisitVolumePotential();
		rvvp.setComeFromCity("SH");
		rvvp.setComeToCity("BJ");
		rvvp.setVolumePotential("500");
		rvvp.setCompanyId("SF");
		rvvp.setReturnVisitId("6");
		rvvp.setRemark("备注123");

		List<ReturnVisitVolumePotential> rvvpList = new ArrayList<ReturnVisitVolumePotential>();		
		rvvpList.add(rvvp);
		rv.setVolumePotentialList(rvvpList);
		/********************** 回访走货信息 end ********************************/

		rv.setExecuteDeptId("49708");
		rv.setExecutorTime(new Date());
		rv.setExecutorId("76993");
		rv.setCreateDate(new Date());
		rv.setModifyDate(new Date());

		/********************** 跟踪日期 start ********************************/
		rv.setSchedule(new Date());
		rv.setTraceWay("1");
		rv.setExecuteDeptId("49708");
		rv.setExecutorId("76993");
		/********************** 跟踪日期 end ********************************/
		boolean rs = false;
		
		try {
			rs = rvManager.createReturnvisit(null,rvoList, rvvpList,null,null,user);
			//Assert.assertFalse(rs);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			rs = rvManager.createReturnvisit(rv, null, rvvpList,null,null,user);
			//Assert.assertFalse(rs);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		try {
			rvoList.add(rvo);
			rv.setOpinionList(rvoList);
			rvvpList.add(rvvp);
			rv.setVolumePotentialList(rvvpList);
			rs = rvManager.createReturnvisit(rv, rvoList, rvvpList,null,null,user);
			//Assert.assertTrue(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//1.全为空
				rv.setReturnVisitType("");
				rv.setScheType("");
				try {
					rs = rvManager.createReturnvisit(rv, rvoList, rvvpList,null,null,user);
					//Assert.assertFalse(rs);		
				} catch (Exception e) {
					e.printStackTrace();
				}
				rv.setReturnVisitType("");
				rv.setScheType(MarketingConstance.DEVELOP_TYPE);
				try {
					rs = rvManager.createReturnvisit(rv, rvoList, rvvpList,null,null,user);
					//Assert.assertFalse(rs);		
				} catch (Exception e) {
					e.printStackTrace();;
				}
				rv.setReturnVisitType(MarketingConstance.DEVELOP_TYPE);
				rv.setScheType("");
				try {
					rs = rvManager.createReturnvisit(rv, rvoList, rvvpList,null,null,user);
					//Assert.assertFalse(rs);		
				} catch (Exception e) {
					e.printStackTrace();
				}
				rv.setReturnVisitType(MarketingConstance.DEVELOP_TYPE);
				rv.setScheType(MarketingConstance.DEVELOP_TYPE);
				try {
					rs = rvManager.createReturnvisit(rv, rvoList, rvvpList,null,null,user);
					//Assert.assertFalse(rs);		
				} catch (Exception e) {
					e.printStackTrace();
				}
				 rv = new ReturnVisitVO();
				 rvoList.add(rvo);
				 rv.setWay("aaa");
				 rv.setScheduleId("192");
				 rv.setScheType(MarketingConstance.DEVELOP_TYPE);
				 rv.setMemberId("747629");
				try {
					//rv.setLinkManId(rv.getPsCustomerId());
					rs = rvManager.createReturnvisit(rv, rvoList, rvvpList,null,null,user);
	
					//Assert.assertFalse(rs);		
				} catch (Exception e) {
					e.printStackTrace();
				}
				 rv.setScheduleId("400001042");
				 rv.setScheType(MarketingConstance.MAINTAIN_TYPE);
				 
				 rv.setMemberId("222");
				
				try {
					
					rs = rvManager.createReturnvisit(rv, rvoList, rvvpList,null,null,user);
					//Assert.assertFalse(rs);		
				} catch (Exception e) {
					e.printStackTrace();
				}
				rv.setReturnVisitType(rv.getScheduleId());
				rv.setLinkManId(rv.getMemberId());
				try {
					
					rs = rvManager.createReturnvisit(rv, rvoList, rvvpList,null,null,user);
					//Assert.assertFalse(rs);		
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				rv.setScheType(MarketingConstance.MAINTAIN_TYPE);
				rvoList.add(rvo);
				 rv.setWay("aaa");
				try {
					
					rs = rvManager.createReturnvisit(rv, rvoList, rvvpList,null,null,user);
					//Assert.assertFalse(rs);		
				} catch (Exception e) {
					e.printStackTrace();
				}
				//rv.setPsCustomerId(null);
				try {
					
					rs = rvManager.createReturnvisit(rv, rvoList, rvvpList,null,null,user);
					//Assert.assertFalse(rs);		
				} catch (Exception e) {
					e.printStackTrace();
				}
				rv.setScheduleId("400001074");
				try {
					
					rs = rvManager.createReturnvisit(rv, rvoList, rvvpList,null,null,user);
					//Assert.assertFalse(rs);		
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				rv = new ReturnVisitVO();
				rv.setScheduleId(400001257+new Random().nextInt(10000000)+"");
				rv.setSchedule(new Date());
	            
				try {
					
					rs = rvManager.createReturnvisit(rv, rvoList, rvvpList,null,null,user);
					//Assert.assertFalse(rs);		
				} catch (Exception e) {
					e.printStackTrace();
				}
				rv.setScheduleId(400001257+new Random().nextInt(10000000)+"");
				//rv.setScheduleId("400001240");
				rvoList.add(rvo);
				rv.setOpinionList(rvoList);
				rvvpList.add(rvvp);
				rv.setVolumePotentialList(rvvpList);
				 rv.setWay("aaa");
				 rvoList.add(rvo);
				 rv.setScheType(MarketingConstance.MAINTAIN_TYPE);
				try {
					
					rs = rvManager.createReturnvisit(rv, rvoList, rvvpList,null,null,user);
					//Assert.assertFalse(rs);		
				} catch (Exception e) {
					e.printStackTrace();
				}
				rv.setScheType(MarketingConstance.DEVELOP_TYPE);
				rv.setScheduleId(400001257+new Random().nextInt(10000000)+"");
				try {
					
					rs = rvManager.createReturnvisit(rv, rvoList, rvvpList,null,null,user);
					//Assert.assertFalse(rs);		
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetReturnVisitList() {
		try{
		User user=(User)UserContext.getCurrentUser();
		int start = 0;
		int limit = 20;
		ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.DAY_OF_MONTH, 1);
		begin.set(Calendar.HOUR_OF_DAY, 0);
		condition.setBeginTime(begin.getTime());
		Calendar end = Calendar.getInstance();
		end.add(Calendar.MONTH, 1);
		condition.setEndTime(end.getTime());
		condition.setLinkName("王二");
		condition.setPlanId("192");
		Map<String, Object> map = rvManager.getReturnVisitList(condition, start, limit, user);
		List<ReturnVisit> list = (List<ReturnVisit>) map.get("data");
		int count = (Integer) map.get("count");
//		Assert.assertTrue(list.size() >= 1);
//		Assert.assertTrue(count >= 1);
		
		condition.setPlanId(null);
		map = rvManager.getReturnVisitList(condition, start, limit, user);
		list = (List<ReturnVisit>) map.get("data");
		count = (Integer) map.get("count");
//		Assert.assertTrue(list.size() >= 1);
//		Assert.assertTrue(count >= 1);
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			User user=(User)UserContext.getCurrentUser();
			int start = 0;
			int limit = 20;
			ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
			Calendar begin = Calendar.getInstance();
			begin.set(Calendar.DAY_OF_MONTH, 1);
			begin.set(Calendar.HOUR_OF_DAY, 0);
			condition.setBeginTime(begin.getTime());
			Calendar end = Calendar.getInstance();
			end.add(Calendar.MONTH, 1);
			condition.setEndTime(end.getTime());
			condition.setLinkName("王二");
			condition.setPlanId("192");
			condition.setDeptId("111111");
			Map<String, Object> map = rvManager.getReturnVisitList(condition, start, limit, user);
			List<ReturnVisit> list = (List<ReturnVisit>) map.get("data");
			int count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			
			condition.setPlanId(null);
			map = rvManager.getReturnVisitList(condition, start, limit, user);
			list = (List<ReturnVisit>) map.get("data");
			count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			}catch(Exception e){
				e.printStackTrace();
			}
		try{
			User user=(User)UserContext.getCurrentUser();
			int start = 0;
			int limit = 20;
			ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
			Calendar begin = Calendar.getInstance();
			begin.set(Calendar.DAY_OF_MONTH, 1);
			begin.set(Calendar.HOUR_OF_DAY, 0);
			condition.setBeginTime(begin.getTime());
			Calendar end = Calendar.getInstance();
			end.add(Calendar.MONTH, 1);
			condition.setEndTime(end.getTime());
			condition.setLinkName("王二");
			condition.setPlanId("192");
			condition.setDeptId("1234");
			condition.setCustomerType("mm");
			Map<String, Object> map = rvManager.getReturnVisitList(condition, start, limit, user);
			List<ReturnVisit> list = (List<ReturnVisit>) map.get("data");
			int count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			
			condition.setPlanId(null);
			map = rvManager.getReturnVisitList(condition, start, limit, user);
			list = (List<ReturnVisit>) map.get("data");
			count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			}catch(Exception e){
				e.printStackTrace();
			}
		try{
			User user=(User)UserContext.getCurrentUser();
			int start = 0;
			int limit = 20;
			ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
			Calendar begin = Calendar.getInstance();
			begin.set(Calendar.DAY_OF_MONTH, 1);
			begin.set(Calendar.HOUR_OF_DAY, 0);
			condition.setBeginTime(begin.getTime());
			Calendar end = Calendar.getInstance();
			end.add(Calendar.MONTH, 1);
			condition.setEndTime(end.getTime());
			condition.setLinkName("王二");
			condition.setPlanId("192");
			condition.setDeptId(null);
			condition.setCustomerType(MarketingConstance.PC_SC);
			Map<String, Object> map = rvManager.getReturnVisitList(condition, start, limit, user);
			List<ReturnVisit> list = (List<ReturnVisit>) map.get("data");
			int count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			
			condition.setPlanId(null);
			map = rvManager.getReturnVisitList(condition, start, limit, user);
			list = (List<ReturnVisit>) map.get("data");
			count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			}catch(Exception e){
				e.printStackTrace();
			}
		try{
			User user=(User)UserContext.getCurrentUser();
			int start = 0;
			int limit = 20;
			ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
			Calendar begin = Calendar.getInstance();
			begin.set(Calendar.DAY_OF_MONTH, 1);
			begin.set(Calendar.HOUR_OF_DAY, 0);
			condition.setBeginTime(begin.getTime());
			Calendar end = Calendar.getInstance();
			end.add(Calendar.MONTH, 1);
			condition.setEndTime(end.getTime());
			condition.setLinkName("王二");
			condition.setPlanId("192");
			condition.setDeptId("");
			condition.setCustomerType(MarketingConstance.PC_SC);
			Map<String, Object> map = rvManager.getReturnVisitList(condition, start, limit, user);
			List<ReturnVisit> list = (List<ReturnVisit>) map.get("data");
			int count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			
			condition.setPlanId(null);
			map = rvManager.getReturnVisitList(condition, start, limit, user);
			list = (List<ReturnVisit>) map.get("data");
			count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			}catch(Exception e){
				e.printStackTrace();
			}
		try{
			User user=(User)UserContext.getCurrentUser();
			int start = 0;
			int limit = 20;
			ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
			Calendar begin = Calendar.getInstance();
			begin.set(Calendar.DAY_OF_MONTH, 1);
			begin.set(Calendar.HOUR_OF_DAY, 0);
			condition.setBeginTime(begin.getTime());
			Calendar end = Calendar.getInstance();
			end.add(Calendar.MONTH, 1);
			condition.setEndTime(end.getTime());
			condition.setLinkName("王二");
			condition.setPlanId("192");
			condition.setDeptId("111111");
			condition.setCustomerType(MarketingConstance.PC_SC);
			Map<String, Object> map = rvManager.getReturnVisitList(condition, start, limit, user);
			List<ReturnVisit> list = (List<ReturnVisit>) map.get("data");
			int count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			
			condition.setPlanId(null);
			map = rvManager.getReturnVisitList(condition, start, limit, user);
			list = (List<ReturnVisit>) map.get("data");
			count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			}catch(Exception e){
				e.printStackTrace();
			}
		try{
			User user=(User)UserContext.getCurrentUser();
			int start = 0;
			int limit = 20;
			ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
			Calendar begin = Calendar.getInstance();
			begin.set(Calendar.DAY_OF_MONTH, 1);
			begin.set(Calendar.HOUR_OF_DAY, 0);
			condition.setBeginTime(begin.getTime());
			Calendar end = Calendar.getInstance();
			end.add(Calendar.MONTH, 1);
			condition.setEndTime(end.getTime());
			condition.setLinkName("王二");
			condition.setPlanId("192");
			condition.setDeptId("111111");
			condition.setCustomerType(MarketingConstance.POTENTIAL_CUSTOMER);
			Map<String, Object> map = rvManager.getReturnVisitList(condition, start, limit, user);
			List<ReturnVisit> list = (List<ReturnVisit>) map.get("data");
			int count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			
			condition.setPlanId(null);
			map = rvManager.getReturnVisitList(condition, start, limit, user);
			list = (List<ReturnVisit>) map.get("data");
			count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			}catch(Exception e){
				e.printStackTrace();
			}
		try{
			User user=(User)UserContext.getCurrentUser();
			int start = 0;
			int limit = 20;
			ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
			Calendar begin = Calendar.getInstance();
			begin.set(Calendar.DAY_OF_MONTH, 1);
			begin.set(Calendar.HOUR_OF_DAY, 0);
			condition.setBeginTime(begin.getTime());
			Calendar end = Calendar.getInstance();
			end.add(Calendar.MONTH, 1);
			condition.setEndTime(end.getTime());
			condition.setLinkName("王二");
			condition.setPlanId("192");
			condition.setDeptId("111111");
			condition.setCustomerType(MarketingConstance.SCATTER_CUSTOMER);
			Map<String, Object> map = rvManager.getReturnVisitList(condition, start, limit, user);
			List<ReturnVisit> list = (List<ReturnVisit>) map.get("data");
			int count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			
			condition.setPlanId(null);
			map = rvManager.getReturnVisitList(condition, start, limit, user);
			list = (List<ReturnVisit>) map.get("data");
			count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			}catch(Exception e){
				e.printStackTrace();
			}
		try{
			User user=(User)UserContext.getCurrentUser();
			int start = 0;
			int limit = 20;
			ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
			Calendar begin = Calendar.getInstance();
			begin.set(Calendar.DAY_OF_MONTH, 1);
			begin.set(Calendar.HOUR_OF_DAY, 0);
			condition.setBeginTime(begin.getTime());
			Calendar end = Calendar.getInstance();
			end.add(Calendar.MONTH, 1);
			condition.setEndTime(end.getTime());
			condition.setLinkName("王二");
			condition.setPlanId("192");
			condition.setDeptId("111111");
			condition.setPlanName("aaa");
			condition.setCustomerType(MarketingConstance.REGULAR_CUSTOMER);
			Map<String, Object> map = rvManager.getReturnVisitList(condition, start, limit, user);
			List<ReturnVisit> list = (List<ReturnVisit>) map.get("data");
			int count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			
			condition.setPlanId(null);
			map = rvManager.getReturnVisitList(condition, start, limit, user);
			list = (List<ReturnVisit>) map.get("data");
			count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			}catch(Exception e){
				e.printStackTrace();
			}
		try{
			User user=(User)UserContext.getCurrentUser();
			int start = 0;
			int limit = 20;
			ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
			Calendar begin = Calendar.getInstance();
			begin.set(Calendar.DAY_OF_MONTH, 1);
			begin.set(Calendar.HOUR_OF_DAY, 0);
			condition.setBeginTime(begin.getTime());
			Calendar end = Calendar.getInstance();
			end.add(Calendar.MONTH, 1);
			condition.setEndTime(end.getTime());
			condition.setLinkName("");
			condition.setPlanId("192");
			condition.setDeptId("111111");
			condition.setPlanName("aaa");
			condition.setCustomerType(MarketingConstance.REGULAR_CUSTOMER);
			Map<String, Object> map = rvManager.getReturnVisitList(condition, start, limit, user);
			List<ReturnVisit> list = (List<ReturnVisit>) map.get("data");
			int count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			
			condition.setPlanId(null);
			map = rvManager.getReturnVisitList(condition, start, limit, user);
			list = (List<ReturnVisit>) map.get("data");
			count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			}catch(Exception e){
				e.printStackTrace();
			}
		try{
			User user=(User)UserContext.getCurrentUser();
			int start = 0;
			int limit = 20;
			ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
//			Calendar begin = Calendar.getInstance();
//			begin.set(Calendar.DAY_OF_MONTH, 1);
//			begin.set(Calendar.HOUR_OF_DAY, 0);
			condition.setBeginTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-07-01"));
			Calendar end = Calendar.getInstance();
			
			condition.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-01"));
			condition.setLinkName("");
			condition.setPlanId("192");
			condition.setDeptId("76743");
			condition.setLinkManId("125312");
			condition.setPlanName("月饼营销2");
			condition.setCustomerType(MarketingConstance.REGULAR_CUSTOMER);
			Map<String, Object> map = rvManager.getReturnVisitList(condition, start, limit, user);
			List<ReturnVisit> list = (List<ReturnVisit>) map.get("data");
			int count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			
			condition.setPlanId(null);
			map = rvManager.getReturnVisitList(condition, start, limit, user);
			list = (List<ReturnVisit>) map.get("data");
			count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			}catch(Exception e){
				e.printStackTrace();
			}
		try{
			User user=(User)UserContext.getCurrentUser();
			int start = 0;
			int limit = 20;
			ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
//			Calendar begin = Calendar.getInstance();
//			begin.set(Calendar.DAY_OF_MONTH, 1);
//			begin.set(Calendar.HOUR_OF_DAY, 0);
			Calendar begin = Calendar.getInstance();
			begin.set(Calendar.DAY_OF_MONTH, 1);
			begin.set(Calendar.HOUR_OF_DAY, 0);
			condition.setBeginTime(begin.getTime());
			Calendar end = Calendar.getInstance();
			end.add(Calendar.MONTH, 1);
			condition.setEndTime(end.getTime());
			
			condition.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-01"));
			condition.setLinkName("");
			condition.setPlanId("192");
			condition.setDeptId("76743");
			condition.setLinkManId("125312");
			condition.setPlanName("月饼营销2");
			condition.setCustomerType(MarketingConstance.REGULAR_CUSTOMER);
			Map<String, Object> map = rvManager.getReturnVisitList(condition, start, limit, user);
			List<ReturnVisit> list = (List<ReturnVisit>) map.get("data");
			int count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			
			condition.setPlanId(null);
			map = rvManager.getReturnVisitList(condition, start, limit, user);
			list = (List<ReturnVisit>) map.get("data");
			count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			}catch(Exception e){
				e.printStackTrace();
			}
		try{
			User user=(User)UserContext.getCurrentUser();
			int start = 0;
			int limit = 20;
			ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
//			Calendar begin = Calendar.getInstance();
//			begin.set(Calendar.DAY_OF_MONTH, 1);
//			begin.set(Calendar.HOUR_OF_DAY, 0);
//			Calendar begin = Calendar.getInstance();
//			begin.set(Calendar.DAY_OF_MONTH, 1);
//			begin.set(Calendar.HOUR_OF_DAY, 0);
			condition.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-07-01"));
			Calendar end = Calendar.getInstance();
			end.add(Calendar.MONTH, 1);
			condition.setEndTime(end.getTime());
			
			condition.setBeginTime(new Date());
			condition.setLinkName("");
			condition.setPlanId("192");
			condition.setDeptId("76743");
			condition.setLinkManId("400116086");
			condition.setPlanName("月饼营销2");
			condition.setCustomerType(MarketingConstance.REGULAR_CUSTOMER);
			Map<String, Object> map = rvManager.getReturnVisitList(condition, start, limit, user);
			List<ReturnVisit> list = (List<ReturnVisit>) map.get("data");
			int count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			
			condition.setPlanId(null);
			map = rvManager.getReturnVisitList(condition, start, limit, user);
			list = (List<ReturnVisit>) map.get("data");
			count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			}catch(Exception e){
				e.printStackTrace();
			}
		try{
			User user=(User)UserContext.getCurrentUser();
			int start = 0;
			int limit = 20;
			ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
//			Calendar begin = Calendar.getInstance();
//			begin.set(Calendar.DAY_OF_MONTH, 1);
//			begin.set(Calendar.HOUR_OF_DAY, 0);
//			Calendar begin = Calendar.getInstance();
//			begin.set(Calendar.DAY_OF_MONTH, 1);
//			begin.set(Calendar.HOUR_OF_DAY, 0);
			condition.setBeginTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-07-01"));
			Calendar end = Calendar.getInstance();
			end.add(Calendar.MONTH, 1);
			condition.setEndTime(end.getTime());
			
			condition.setEndTime(new Date());
			condition.setLinkName("");
			condition.setPlanId("192");
			condition.setDeptId("76743");
			condition.setLinkManId("400116086");
			condition.setPlanName("月饼营销2");
			condition.setCustomerType(MarketingConstance.REGULAR_CUSTOMER);
			Map<String, Object> map = rvManager.getReturnVisitList(condition, start, limit, user);
			List<ReturnVisit> list = (List<ReturnVisit>) map.get("data");
			int count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			
			condition.setPlanId(null);
			map = rvManager.getReturnVisitList(condition, start, limit, user);
			list = (List<ReturnVisit>) map.get("data");
			count = (Integer) map.get("count");
//			Assert.assertTrue(list.size() >= 1);
//			Assert.assertTrue(count >= 1);
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	@SuppressWarnings("unchecked")
/*	@Test
	public void testGetReturnVlisit() { 
		int id = 192;
		Map<String, Object> result = rvManager.getReturnVisit(id);
		ReturnVisit rv = (ReturnVisit)result.get("returnVisit");
		Assert.assertNotNull(rv);
		Assert.assertEquals("王二麻子", rv.getLinkName());
		Assert.assertEquals("13918882222", rv.getLinkManMobile());
		Assert.assertEquals("0755-9876451", rv.getLinkManPhone());
		List<ReturnVisitOpinion> opinions = (List<ReturnVisitOpinion>) result.get("returnVisitOpinion");
		Assert.assertTrue(opinions.size() >= 2);
		List<ReturnVisitVolumePotential> volumes = (List<ReturnVisitVolumePotential>) result.get("returnVisitVolumePotential");
		Assert.assertTrue(volumes.size() >= 2);		
	}*/

	@Test
	public void testInitCreateReturnvisitPage(){
		ReturnVisit rv = new ReturnVisit();	
		
		try {
			rv.setScheduleId("400092811");
			rvManager.initCreateReturnvisitPage(rv, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			rv.setScheduleId("400140170");
//			rv.setScheType(MarketingConstance.DEVELOP_TYPE);
			ReturnVisit r = rvManager.initCreateReturnvisitPage(rv, user);
//			Assert.assertNotNull(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			rv.setScheduleId("1845");
			rv.setScheType(MarketingConstance.MAINTAIN_TYPE);
			ReturnVisit r = rvManager.initCreateReturnvisitPage(rv, user);
//			Assert.assertNotNull(r);
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
		try {
			rv.setScheduleId("400092811");
			rvManager.initCreateReturnvisitPage(rv, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			rv.setScheduleId("400140170");
			rv.setScheType(MarketingConstance.DEVELOP_TYPE);
			ReturnVisit r = rvManager.initCreateReturnvisitPage(rv, user);
//			Assert.assertNotNull(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			rv.setScheduleId("400092811");
			rv.setScheType("mat");
			rv.setExecutorId("389904");
			rv.setExecuteDeptId("11387");
			ReturnVisit r = rvManager.initCreateReturnvisitPage(rv, user);
//			Assert.assertNotNull(r);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		user = new User();
		user.setId("1");
		emp = new Employee();
		user.setEmpCode(emp);
		dept = new Department();
		dept.setId("1");
		emp.setId("1");
		emp.setDeptId(dept);
		try {
			rv.setScheduleId("403308445");
			rv.setScheType("dev");
			rv.setExecutorId("1");
			rv.setExecuteDeptId("1");
			ReturnVisit r = rvManager.initCreateReturnvisitPage(rv, user);
//			Assert.assertNotNull(r);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		try {
			rv.setScheduleId("1845");
			rv.setScheType(MarketingConstance.MAINTAIN_TYPE);
			ReturnVisit r = rvManager.initCreateReturnvisitPage(rv, user);
//			Assert.assertNotNull(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInitCreateReturnvisitPageByCust(){
		ReturnVisit rv = null;
		try {
			rvManager.initCreateReturnvisitPageByCust(rv, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv = new ReturnVisit();	
		try {		
			rvManager.initCreateReturnvisitPageByCust(rv, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			rv.setScheduleId("3098");
			rvManager.initCreateReturnvisitPageByCust(rv, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			rv.setScheduleId("3098"); 
			rv.setScheType(MarketingConstance.DEVELOP_TYPE);
			rvManager.initCreateReturnvisitPageByCust(rv, null);
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
		try {
			rv.setScheduleId("3098"); 
			rv.setScheType(MarketingConstance.DEVELOP_TYPE);
			ReturnVisit r = rvManager.initCreateReturnvisitPageByCust(rv, user);
//			Assert.assertNotNull(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			rv.setScheduleId("3098");
			
			rv.setScheType(MarketingConstance.MAINTAIN_TYPE);
			ReturnVisit r = rvManager.initCreateReturnvisitPageByCust(rv, user);
//			Assert.assertNotNull(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			rv.setScheduleId("3098");
			rv.setLinkManId("2488");
			rv.setScheType(MarketingConstance.MAINTAIN_TYPE);
			ReturnVisit r = rvManager.initCreateReturnvisitPageByCust(rv, user);
//			Assert.assertNotNull(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/*@Test
	public void testGetReturnVisitLog(){
		ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
		condition.setScheType("dev");
		condition.setPsCustomerId("3098");
		List<?> list = rvManager.getReturnVisitLog(condition);
		Assert.assertNotNull(list);
		
		condition.setScheType("mat");
		condition.setPsCustomerId("999999999999999");
		condition.setMemberId("999999999999999");
		condition.setLinkManId("111111");
		list = rvManager.getReturnVisitLog(condition);
		Assert.assertNotNull(list);
		
	}*/
//	@Test
//	public void testQueryRvListByCustId(){
//		rvManager.queryRvListByCustId(new HashMap());
//	}
	@Test
	public void testQueryRvOptionByMarketingIds(){
		List list = new ArrayList();
		list.add("11234");
		rvManager.queryRvOptionByMarketingIds(list, 0, 10);
	}
	@Test
	public void testQueryRvPotentialByMarketingIds(){
		List marketingIds = new ArrayList();
		marketingIds.add("12345");
		rvManager.queryRvPotentialByMarketingIds(marketingIds, 0, 10);
	}
	@Test
	public void testCountRvOptionByMarketingIds(){
		List marketingIds = new ArrayList();
		marketingIds.add("12345"); 
		rvManager.countRvOptionByMarketingIds(marketingIds);
	}
	@Test
	public void testCountRvPotentialByMarketingIds(){
		List marketingIds = new ArrayList();
		marketingIds.add("12345"); 
		rvManager.countRvPotentialByMarketingIds(marketingIds);
	}
}
