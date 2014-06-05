package com.deppon.crm.module.marketing.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.dao.IMarketActivityDao;
import com.deppon.crm.module.marketing.server.dao.impl.MarketActivityDao;
import com.deppon.crm.module.marketing.server.utils.MarketActivityUtils;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityDept;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityExceptionHandle;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityOption;
import com.deppon.crm.module.marketing.shared.domain.activity.DeptTree;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivity;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityConstance;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityVO;
import com.deppon.crm.module.marketing.shared.domain.activity.Multiple;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;

public class MarketActivityDaoTest {
	private IMarketActivityDao mDao;
	@Before
	public void setUp(){
		mDao = (IMarketActivityDao)SpringTestHelper.get().getBean(MarketActivityDao.class);
	}
	@Test
	public void testInsertMarketActivity(){
		MarketActivity ma = new MarketActivity();
		mDao.insertMarketActivity(ma);
	}
	@Test
	public void testInsertMultiple(){
		Multiple m = new Multiple();
		mDao.insertMultiple(m);
	}
	@Test
	public void testInsertLineDept(){
		LineDept l = new LineDept();
		mDao.insertLineDept(l);
	}
	@Test
	public void testInsertOptions(){
		ActivityOption a  = new ActivityOption();
		a.setActivityId("1");
		a.setCreateDate(new Date());
		a.setInfoType(MarketActivityConstance.OPTION_COUPON);
		a.setType(MarketActivityConstance.PREFER_TYPE_BF);
		a.setValue("100");
		mDao.insertOptions(a);
		
	}
	@Test
	public void testInsertActivityDept(){
		ActivityDept ad = new ActivityDept();
		ad.setActivityId("1");
		ad.setDeptName("aa");
		ad.setDeptStandardCode("DP1023");
		mDao.insertActivityDept(ad);
	}
	@Test
	public void testUpdateMaketActivityById(){
		MarketActivity ma = new MarketActivity();
		ma.setActivityStatus(MarketActivityConstance.ACTIVITY_STATUS_ESTABLISHED);
		ma.setId("1");
		mDao.updateMaketActivityById(ma);
	}
	@Test
	public void testDeleteMultipleByType(){
		Multiple m = new Multiple();
		m.setConditionType(MarketActivityConstance.ACTIVITY_RELATION_ACTIVITY);
		m.setConditionId("1");
		m.setType(MarketActivityConstance.TYPE_TRADE);
		mDao.deleteMultipleByType(m);
	}
	@Test
	public void testDeleteMultipleById(){
		mDao.deleteMultipleById("1");
	}
	@Test
	public void testDeleteLineDeptByType(){
		LineDept ld = new LineDept();
		ld.setConditionId("1");
		ld.setConditionType(MarketActivityConstance.ACTIVITY_RELATION_ACTIVITY);
		mDao.deleteLineDeptByType(ld);
	}
	@Test
	public void testDeleteLineDeptByIdList(){
		List<LineDept> l = new ArrayList<LineDept>();
		LineDept d = new LineDept();
		d.setId("1");
		l.add(d);
		mDao.deleteLineDeptByIdList(l);
	}
	@Test
	public void testDeleteLineDeptById(){
		mDao.deleteLineDeptById("1");
	}
	@Test
	public void testDeleteActivityDeptByIdList(){
		List<ActivityDept> list = new ArrayList<ActivityDept>();
		ActivityDept l = new ActivityDept();
		l.setId("1");
		list.add(l);
		l = new ActivityDept();
		l.setId("2");
		list.add(l);
		mDao.deleteActivityDeptByIdList(list);
	}
	@Test
	public void testDeleteOptionsByType(){
		ActivityOption a = new ActivityOption();
		a.setActivityId("1");
		a.setInfoType(MarketActivityConstance.OPTION_COUPON);
		a.setType(MarketActivityConstance.PREFER_TYPE_BF);
		mDao.deleteOptionsByType(a);
	}
	@Test
	public void testDeleteActivityDeptByActivityId(){
		mDao.deleteActivityDeptByActivityId("1");
	}
	@Test
	public void testSearchAuthDeptCharacter(){
		mDao.searchAuthDeptCharacter("1111");
	}
	@Test
	public void testInsertActivityClient(){
		ActivityClientBase a = new ActivityClientBase();
		a.setActivityId("1");
		a.setClientBaseId("1");
		a.setPlanEndTime(new Date());
		a.setPlanStartTime(new Date());
		a.setClientBaseStatus(MarketActivityConstance.CLIENTBASE_STATUS_USEING);
		mDao.insertActivityClient(a);
	}
//	@Test
//	public void testLoadTreeRoot(){
//		List<DeptTree> depts = mDao.loadTreeRoot("22207");
//		System.out.println(depts);
//	}
	@Test
	public void testLoadAuthTree(){
//		Date begin = new Date();
		
		List<DeptTree> depts = mDao.loadAuthTree("22207");
		Date begin = new Date();
		DeptTree root = MarketActivityUtils.organizedDeptTree(depts);
		Date end = new Date();
		System.out.println((end.getTime()-begin.getTime())/1000);
		System.out.println(root);
	}
	@Test
	public void testSearchMarketActivityByMulCondition(){
		MarketActivityVO mav = new MarketActivityVO();
		mav.setCreateUser("1234");
		mav.setDeptCharacter(MarketActivityConstance.DEPT_CHARACTER_LTTGROUP);
		mDao.searchMarketActivityByMulCondition(mav,0,20);
		mDao.countMarketActivityByMulCondition(mav);
	}
	@Test
	public void testSearchMarketActivityByIdOrWorkFlowNum(){
		try{
		MarketActivity ma = new MarketActivity();
		ma.setActivityWorkflowNum("ICRM201404080000661");
		MarketActivity ma1 = mDao.searchMarketActivityByIdOrWorkFlowNum(ma);
		System.out.println(ma1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testLoadDeliverOrArriveTree(){
		mDao.loadDeliverOrArriveTree();
	}
	@Test
	public void testSearchLineDeptByTypeAndActivityId(){
		Map<String,String> map =new HashMap<String,String>();
		map.put("activityId", "1");
		map.put("conditionType", MarketActivityConstance.ACTIVITY_RELATION_ACTIVITY);
		mDao.searchLineDeptByTypeAndActivityId(map);
	}
	@Test
	public void testCountActivityDeptNum(){
		List<String> l = new ArrayList<String>();
		l.add("DP1203");
		mDao.countActivityDeptNum(l);
	}
	@Test
	public void testDeleteClientBaseByRelationId(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("relationId", "1");
		map.put("status", MarketActivityConstance.CLIENTBASE_STATUS_USEING);
		mDao.deleteClientBaseByRelationId(map);
	}
	@Test
	public void testUpdateActivityClientById(){
		ActivityClientBase acb = new ActivityClientBase();
		acb.setId("1");
		acb.setClientBaseStatus(MarketActivityConstance.CLIENTBASE_STATUS_UNUSED);
		mDao.updateActivityClientById(acb);
	}
	@Test
	public void testSearchActivityClientByName(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", "11");
		map.put("clientBaseStatus", MarketActivityConstance.CLIENTBASE_STATUS_UNUSED);
		String[] clients = new String[]{"1","2"};
		map.put("clients",clients);
		map.put("deptCharacter", "LTTGROUP");
		mDao.searchActivityClientByName(map, 0, 20);
		mDao.countActivityClientByName(map);
	}
	@Test
	public void testSearchActivityClientByMultipleCondition(){
		mDao.searchActivityClientByMultipleCondition(new HashMap<String,Object>());
	}
	@Test
	public void testSearchActivityClientByReId(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", "1");
		mDao.searchActivityClientByReId(map);
	}
	@Test
	public void testSearchActivityClientByClientIds(){
		List<ActivityClientBase> a = new ArrayList<ActivityClientBase>();
		ActivityClientBase abc = new ActivityClientBase();
		abc.setClientBaseId("1");
		a.add(abc);
		mDao.searchActivityClientByClientIds(a);
	}
	@Test
	public void testSearchTree(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", "22207");
		map.put("activityId", "1");
		List<DeptTree> deptList = mDao.loadAuthTree("85518");
//		List<DeptTree> deptList = new ArrayList<DeptTree>();
//		DeptTree t = new DeptTree();
//		t.setId("1");
//		t.setLevel(1);
//		t.setParentId(null);
//		deptList.add(t);
//		t = new DeptTree();
//		t.setId("2");
//		t.setLevel(2);
//		t.setParentId("1");
//		deptList.add(t);
//		t = new DeptTree();
//		t.setId("3");
//		t.setLevel(2);
//		t.setParentId("1");
//		deptList.add(t);
//		t = new DeptTree();
//		t.setId("4");
//		t.setLevel(2);
//		t.setParentId("1");
//		deptList.add(t);
//		t = new DeptTree();
//		t.setId("4");
//		t.setLevel(2);
//		t.setParentId("1");
//		deptList.add(t);
//		t = new DeptTree();
//		t.setId("5");
//		t.setLevel(3);
//		t.setParentId("2");
//		deptList.add(t);
//		t = new DeptTree();
//		t.setId("6");
//		t.setLevel(3);
//		t.setParentId("2");
//		deptList.add(t);
//		t = new DeptTree();
//		t.setId("7");
//		t.setLevel(3);
//		t.setParentId("4");
//		deptList.add(t);
//		t = new DeptTree();
//		t.setId("8");
//		t.setLevel(3);
//		t.setParentId("4");
//		deptList.add(t);
//		t = new DeptTree();
//		t.setId("9");
//		t.setLevel(3);
//		t.setParentId("4");
//		deptList.add(t);
		DeptTree root = MarketActivityUtils.organizedDeptTree(deptList);
		System.out.println(root);
	}
	@Test
	public void testSearchActivityClientByActivityId(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", "123");
		mDao.searchActivityClientByActivityId(map);
	}
	@Test
	public void testCountActivityDeptByActivityId(){
		mDao.countActivityDeptByActivityId("22");
	}
	@Test
	public void testInsertActivityException(){
		ActivityExceptionHandle aex = new ActivityExceptionHandle();
		aex.setActivityId("22");
		aex.setExceptionType(MarketActivityConstance.ACTIVITY_EXCEPTION_NODISCOUNT);
		aex.setHandle(MarketActivityConstance.ACTIVITY_EXCEPTION_UNHANDLE);
		mDao.insertActivityException(aex);
	}
	@Test
	public void testUpdateActivityExceptionById(){
		List<ActivityExceptionHandle> list = mDao.searchActivityExeptionUnhandle(MarketActivityConstance.ACTIVITY_EXCEPTION_UNHANDLE);
		for(ActivityExceptionHandle a : list){
			a.setHandle(MarketActivityConstance.ACTIVITY_EXCEPTION_HANDLED);
			mDao.updateActivityExceptionById(a);
		}
		
	}
	@Test
	public void testSearchUselessActivities(){
		mDao.searchUselessOrCompleteActivities(MarketActivityConstance.SEARCH_USELESS_ACTIVITY);
	}
	@Test
	public void testCreateClientBaseDetail(){
		mDao.createClientBaseDetail();
	}
	@Test
	public void testSearchMarketActivityForMarketPlan(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("activityName", "啊啊");
		map.put("activityCategory", "LTT");
		mDao.searchMarketActivityForMarketPlan(map);
	}
	@Test
	public void testSearchActivityOptionByActivityIdAndType(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", "1554");
		map.put("infoType","COUPON");
		mDao.searchActivityOptionByActivityIdAndType(map);
	}
	@Test
	public void testCountMarketActivityByName(){
		mDao.countMarketActivityByName("aaa");
	}
	@Test
	public void testDeleteMarketActivityByIds(){
		List<MarketActivity> list = new ArrayList<MarketActivity>();
		MarketActivity ma = new MarketActivity();
		ma.setId("1");
		list.add(ma);
		mDao.deleteMarketActivityByIds(list);
	}
}
