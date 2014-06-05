package com.deppon.crm.module.marketing.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.dao.IMarketActivityDao;
import com.deppon.crm.module.marketing.server.dao.impl.MarketActivityDao;
import com.deppon.crm.module.marketing.server.manager.IClientBaseManager;
import com.deppon.crm.module.marketing.server.manager.IMarketActivityManager;
import com.deppon.crm.module.marketing.server.manager.impl.ClientBaseManager;
import com.deppon.crm.module.marketing.server.manager.impl.CycleManager;
import com.deppon.crm.module.marketing.server.manager.impl.MarketActivityManager;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityDept;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityOption;
import com.deppon.crm.module.marketing.shared.domain.activity.ClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.DeptTree;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivity;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityConstance;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityVO;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;

public class MarketActivityManagerTest {
	IMarketActivityManager mam;
	IClientBaseManager cam;
	IMarketActivityDao mao;
	User user;
	User user1;
	User user2;
	User user3;
	MarketActivity ma = new MarketActivity();
	MarketActivity ma1 = new MarketActivity();
	@Before
	public void setUp(){
		ApplicationContext ap = null;
		SpringTestHelper.get().getBean(CycleManager.class);
		mam = (IMarketActivityManager)SpringTestHelper.get().getBean(MarketActivityManager.class);
		cam = (IClientBaseManager)SpringTestHelper.get().getBean(ClientBaseManager.class);
		mao = (IMarketActivityDao)SpringTestHelper.get().getBean(MarketActivityDao.class);
		user = new User();
		
		Employee e = new Employee();
		e.setId("22207");
		e.setEmpName("邢涛");
		e.setEmpCode("026538");
		Department d = new Department();
		d.setId("106782");
		d.setPrincipal("026538");
		e.setDeptId(d);
		user.setEmpCode(e);
		user.setUserName("邢涛");
		user.setId("22207");
		Set<String> r =new HashSet<String>();
		r.add(MarketActivityConstance.AUTH_LTT_SEARCHALL);
		r.add(MarketActivityConstance.LOGISTICSPRODUCTFUNCTION);
		user.setAccessUris(r);
		user1 = new User();
		
		Employee e1 = new Employee();
		e1.setId("29811");
		e1.setEmpName("郭飞飞");
		e1.setEmpCode("047042");
		Department d1 = new Department();
		d1.setId("465665");
		d1.setPrincipal("047042");
		e1.setDeptId(d1);
		user1.setEmpCode(e1);
		user1.setUserName("郭飞飞");
		user1.setId("29811");
		r =new HashSet<String>();
		r.add(MarketActivityConstance.AUTH_EXPRESS_SEARCHALL);
		user2 = new User();
		
		Employee e2 = new Employee();
		e2.setId("25761");
		e2.setEmpName("韩明明");
		e2.setEmpCode("001599");
		Department d2 = new Department();
		d2.setId("10361");
		d2.setPrincipal("001599");
		e2.setDeptId(d2);
		user2.setEmpCode(e2);
		user2.setUserName("韩明明");
		user2.setId("25761");
		
		
		user3 = new User();
		
		Employee e3 = new Employee();
		e3.setId("498060");
		e3.setEmpName("韩明明");
		e3.setEmpCode("102563");
		Department d3 = new Department();
		d3.setId("315229");
		d3.setPrincipal("102563");
		e3.setDeptId(d3);
		user3.setEmpCode(e3);
		user3.setUserName("韩明明");
		user3.setId("500886");
		ma.setId("22");
		ma1.setId("22");
	}
//	@Test
//	public void testInitMarketActivity(){
//		ma = mam.initMarketActivity(user);
//		//ma1 = mam.initMarketActivity(user1);
//	}
	@Test
	public void testInsertLineDept(){
		List<LineDept> l = new ArrayList<LineDept>();
		LineDept d = new LineDept();
		d.setLeaveDeptCode("DP00509");
		d.setLeavedDeptName("报表");
		l.add(d);
		mam.insertLineDept(l, MarketActivityConstance.LINE_TYPE_LEAVE, ma1.getId());
		List<LineDept> l1 = new ArrayList<LineDept>();
		LineDept d1 = new LineDept();
		d1.setArriveDeptCode("DP00509");
		d1.setArriveDeptCode("啊啊");
		l1.add(d1);
		mam.insertLineDept(l1, MarketActivityConstance.LINE_TYPE_ARRIVE, ma.getId());
		List<LineDept> l2 = new ArrayList<LineDept>();
		LineDept d2 = new LineDept();
		d2.setLeaveDeptCode("DP00509");
		d2.setLeavedDeptName("报表");
		d2.setArriveDeptCode("DP00509");
		d2.setArriveDeptCode("啊啊");
		l2.add(d2);
		mam.insertLineDept(l2, MarketActivityConstance.LINE_TYPE_LEAVE_ARRIVE, ma.getId());
	}
	@Test
	public void testSaveMarketActivity(){
		ClientBase ca=new ClientBase();
		ca.setId("29");
		ca.setClientBaseStatus("UNUSED");
		ca.setModifyUser("150982");
		ca.setModifyDate(new Date());
		cam.updateClientBaseStatus(ca);
		
		
		
		
		/*
		 * 快递市场推广活动
		 */
		ma1.setActivityType( MarketActivityConstance.ACTIVITY_TYPE_NATION);
		ma1.setActivityCategory(MarketActivityConstance.ACTIVITY_CATEGORY_EXPRESS);
		ma1.setProposerName("张三");
		ma1.setProposerCode("012345");
		ma1.setStartTime(new Date(System.currentTimeMillis()));
		ma1.setEndTime(new Date(System.currentTimeMillis()+30*24*60*60*1000l));
		ma1.setTopic("topic");
		ma1.setContent("Content");
		ma1.setSlogan("Slogan");
		ma1.setApplyReason("applyReason");
		ma1.setTarget1("100万");
		ma1.setContactsTel("1234-2321");
		ma1.setContactsId("123222");
		ma1.setContactsName("小辣椒");
		List<ActivityDept> l1 = new ArrayList<ActivityDept>();
		ActivityDept d1 = new ActivityDept();
		d1.setActivityId(ma1.getId());
		d1.setDeptStandardCode("DP00509");
		l1.add(d1);
		ma1.setActivityDepts(l1);
		ma1.setPreferType(MarketActivityConstance.OPTION_COUPON);
		List<ActivityOption> ao1 =new ArrayList<ActivityOption>();
		ActivityOption o1 = new ActivityOption();
		o1.setActivityId(ma1.getId());
		o1.setInfoType(MarketActivityConstance.OPTION_COUPON);
		o1.setType(MarketActivityConstance.PREFER_TYPE_BZ);
		o1.setValue("12322");
		ao1.add(o1);
		ma1.setCouponInfo(ao1);
		ma1.setUseRule("aaa");
		ma1.setCreateRule("bbb");
		ActivityClientBase c1 = new ActivityClientBase();
		c1.setId("22");
		c1.setClientBaseId("29");
		List<ActivityClientBase> cb1 = new ArrayList<ActivityClientBase>();
		cb1.add(c1);
		
		ma1.setClientBase(cb1);
		try{
			mam.saveMarketActivity(ma1, user1);
		}catch(Exception e){
			e.printStackTrace();
		}
		/*
		 * 全国市场推广活动
		 */
		cam.updateClientBaseStatus(ca);
		ma.setActivityType( MarketActivityConstance.ACTIVITY_TYPE_NATION);
		ma.setActivityCategory(MarketActivityConstance.ACTIVITY_CATEGORY_LTT);
		ma.setProposerName("张三");
		ma.setProposerCode("012345");
		ma.setStartTime(new Date(System.currentTimeMillis()));
		ma.setEndTime(new Date(System.currentTimeMillis()+30*24*60*60*1000l));
		ma.setTopic("topic");
		ma.setContent("Content");
		ma.setSlogan("Slogan");
		ma.setApplyReason("applyReason");
		ma.setTarget1("100万");
		ma.setContactsTel("1234-2321");
		ma.setContactsId("123222");
		ma.setContactsName("小辣椒");
		List<ActivityDept> l = new ArrayList<ActivityDept>();
		ActivityDept d = new ActivityDept();
		d.setActivityId(ma.getId());
		d.setDeptStandardCode("DP00509");
		l.add(d);
		ma.setActivityDepts(l);
		ma.setPreferType(MarketActivityConstance.OPTION_COUPON);
		List<ActivityOption> ao =new ArrayList<ActivityOption>();
		ActivityOption o = new ActivityOption();
		o.setActivityId(ma.getId());
		o.setInfoType(MarketActivityConstance.OPTION_COUPON);
		o.setType(MarketActivityConstance.PREFER_TYPE_BZ);
		o.setValue("12322");
		ao.add(o);
		ma.setCouponInfo(ao);
		ma.setUseRule("aaa");
		ma.setCreateRule("bbb");
		ActivityClientBase c = new ActivityClientBase();
		c.setId("22");
		c.setClientBaseId("29");
		List<ActivityClientBase> cb = new ArrayList<ActivityClientBase>();
		cb.add(c);
		
		ma.setClientBase(cb);
		try{
			mam.saveMarketActivity(ma, user);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
//	@Test
//	public void testGetUserDeptCharacter(){
//		mam.getUserDeptCharacter(user);
//		mam.getUserDeptCharacter(user1);
//		mam.getUserDeptCharacter(user2);
//		mam.getUserDeptCharacter(user3);
//	}
	@Test
	public void testLoadDeliverOrArriveTree(){
		DeptTree list = mam.loadDeliverOrArriveTree();
		System.out.println(list);
	}
	@Test
	public void testSearchLineDeptByTypeAndActivityId(){
		List<LineDept> list = mam.searchLineDeptByTypeAndActivityId("22", MarketActivityConstance.LINE_TYPE_LEAVE_ARRIVE, MarketActivityConstance.ACTIVITY_RELATION_ACTIVITY);
	}
	@Test
	public void testSaveActivityDept(){
		String activityId = "22";
		List<String> deptCodes = new ArrayList<String>();
		deptCodes.add("DP10271");
		mam.saveActivityDept(deptCodes, activityId, user);
	}
	@Test
	public void testCheckUpdateAuth(){
		try{
			mam.checkUpdateAuth("22", user);			
		}catch(Exception e){
			System.out.println(e.getLocalizedMessage());
		}
	}
	@Test
	public void testUpdateCompleteOrDeleteUselessActivities(){
		mam.updateCompleteOrDeleteUselessActivities();
	}
	@Test
	public void testSearchMarektActivityByWorkFlowNum(){
		MarketActivity ma =mam.searchMarketActivityByWorkflowNum("ICRM201404080000661");
		System.out.println(ma);
	}
	@Test
	public void testCheckWorkflow(){
		try{
			mam.checkDiscountWorkflow("13495576");
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	
	@Test
	public void testInsertLine(){
		List<LineDept> l=new ArrayList<LineDept>();
		LineDept ld=new LineDept();
		ld.setArriveDeptCode("DP07741");
		ld.setArriveDeptName("成都武侯区电脑城运作部");
		ld.setLeavedDeptName("佛山顺德区伦教木工机械城营业部");
		ld.setLeaveDeptCode("10588");
		ld.setConditionId("22");
		ld.setConditionType(MarketActivityConstance.ACTIVITY_RELATION_ACTIVITY);
		l.add(ld);
		l.add(ld);
		mam.insertLineDept(l,MarketActivityConstance.LINE_TYPE_LEAVE_ARRIVE , "22");
	}
	@Test
	public void testloadAuthTree(){
		
		mam.loadAuthTree(user.getId());	
	}
	@Test
	public void testSearchMarketActivityByMulCondition(){
		 MarketActivityVO mv=new  MarketActivityVO();
		 mv.setCreateUser(user.getId());
		 mv.setDeptId(user.getEmpCode().getDeptId().getId());
		 mam.searchMarketActivityByMulCondition(mv, user, 0, 25);
	}
	@Test
	public void testCountMarketActivityByMulCondition(){
		MarketActivityVO mv=new  MarketActivityVO();
		mv.setCreateUser(user.getId());
		mam.countMarketActivityByMulCondition(mv, user);
	}
	@Test
	public void testSearchMarketActivityById(){
		mam.searchMarketActivityById("22");
	}
	@Test
	public void testUpdateActivityClientById(){
		ActivityClientBase ac=new ActivityClientBase();
		ac.setActivityId("1176");
		ac.setClientBaseId("141");
		ac.setClientBaseStatus("ISSUED_COMPLETE");
		ac.setClientNum("222");
		mam.updateActivityClientById(ac, user);
	}
	@Test 
	public void testsearchActivityClientByName(){
		mam.searchActivityClientByName("1221", user, null, 0, 25);
	}
	@Test
	public void testCountActivityClientByName(){
		mam.countActivityClientByName("1221", user, null);
	}
	@Test
	public void testCheckCreateClientBasePlanAuth(){
		ActivityClientBase acb = new ActivityClientBase();
		acb.setClientBaseId("29");
		acb.setId("382");
		acb.setPlanStartTime(new Date());
		acb.setPlanEndTime(new Date(System.currentTimeMillis()+50000));
		try{
		mam.checkCreateClientBasePlanAuth("22", user, acb);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testUpdateMarketActivityClient(){
		ClientBase ca=new ClientBase();
		ca.setId("29");
		ca.setClientBaseStatus("UNUSED");
		ca.setModifyUser("150982");
		ca.setModifyDate(new Date());
		cam.updateClientBaseStatus(ca);
		List<ActivityClientBase> l = new ArrayList<ActivityClientBase>();
		ActivityClientBase acb = new ActivityClientBase();
		acb.setClientBaseId("29");
		acb.setId("382");
		acb.setPlanStartTime(new Date());
		acb.setPlanEndTime(new Date(System.currentTimeMillis()+50000));
		l.add(acb);
		acb =  new ActivityClientBase();
		acb.setClientBaseId("100");
		acb.setId("61");
		acb.setPlanStartTime(new Date());
		acb.setPlanEndTime(new Date(System.currentTimeMillis()+50000));
		l.add(acb);
	    try{
		mam.updateMarketActivityClient("22", l, user);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	}
//	@Test
//	public void testCreateClientBasePlan(){
//		ActivityClientBase acb = new ActivityClientBase();
//		acb.setClientBaseId("29");
//		acb.setActivityId("22");
//		acb.setClientBaseStatus(MarketActivityConstance.CLIENTBASE_STATUS_USEING);
//		acb.setPlanStartTime(new Date());
//		acb.setPlanEndTime(new Date(System.currentTimeMillis()+50000));
//		ClientBase ca=new ClientBase();
//		ca.setId("29");
//		ca.setClientBaseStatus(MarketActivityConstance.CLIENTBASE_STATUS_USEING);
//		ca.setModifyUser("150982");
//		ca.setModifyDate(new Date());
//		cam.updateClientBaseStatus(ca);
//		mao.insertActivityClient(acb);
//		mam.createClientBasePlan("22", acb, user);
//	}
	@Test
	public void testSearchLatestMarketActivityByDeptId(){
		mam.searchLatestMarketActivityByDeptId(user.getEmpCode().getDeptId().getId());
	}
	@Test
	public void testSearchActivityClientBaseUsed(){
		mam.searchActivityClientBaseUsed("22");
	}
	@Test
	public void testCreateClientBaseDetail(){
		mam.createClientBaseDetail();
	}
	@Test
	public void testCountClientBaseDetail(){
		mam.countClientBaseDetail();
	}
	@Test
	public void testCreateClientDetailReport(){
		mam.createClientDetailReport();
	}
	@Test
	public void testSearchMarketActivityForMarketPlan(){
		mam.searchMarketActivityForMarketPlan("aa",user);
	}
	@Test
	public void testSearchActivityOptionByActivityIdAndType(){
		mam.searchActivityOptionByActivityIdAndType("22");
	}
	@Test
	public void testCheckHaveSamNameMarketActivity(){
		try{
			mam.checkHaveSamNameMarketActivity("fasdbbdfgfasdfajjghjsdfasdfasdfafasdf");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
//	@Test
//	public void testMarketActivityDiscountInfoToFoss(){
//		MarketActivity ma = mam.searchMarketActivityById(id);
//	}
}
