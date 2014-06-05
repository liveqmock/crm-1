package com.deppon.crm.module.marketing.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.dao.IReturnVisitDao;
import com.deppon.crm.module.marketing.server.dao.impl.ReturnVisitDao;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotential;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotentialVo;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;

/**   
 * <p>
 * Description:回访信息-测试<br />
 * </p>
 * @title PlanDaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author zhujunyong
 * @version 0.1 2012-3-27
 */

public class ReturnVisitDaoTest {
	private IReturnVisitDao returnVisitDao;
	
	@Before
	public void setup() throws Exception{
		returnVisitDao = (IReturnVisitDao) SpringTestHelper.get().getBean(
				ReturnVisitDao.class);
	}
	
	@After
	public void teardown() throws Exception{
	}

	
	@Test
	public void testGetReturnVisit() {
		int id = 84;
		ReturnVisit rv = returnVisitDao.getReturnVisit(id);
		Assert.assertNull(rv);
	}

	@Test
	public void testGetReturnVisitOpinionList() {
		int returnVisitId = 84;
		List<ReturnVisitOpinion> list = returnVisitDao.getReturnVisitOpinionList(returnVisitId);
		Assert.assertNotNull(list);
	}

	@Test
	public void testGetReturnVisitVolumePotentialList() {
		int returnVisitId = 84;
		List<ReturnVisitVolumePotential> list = returnVisitDao.getReturnVisitVolumePotentialList(returnVisitId);
		Assert.assertNotNull(list);
	}
	
//	@Test
//	public void testGetReturnVisitList(){
//		ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
//		condition.setLinkName("1");
//		condition.setOpinion("2");
//		condition.setLinkManId("168");
//		condition.setScheType("mat");
//		List<String> sids = new ArrayList<String>();
//		sids.add("3507");
//		condition.setScheduleIds(sids);
//		List<ReturnVisit> rvList = returnVisitDao.getReturnVisitList(condition, 0, 20);
//		Assert.assertNotNull(rvList);
//		returnVisitDao.getReturnVisitCount(condition);
//		
//	}

	@Test
	public void testCreateReturnVisit(){
		// 回访信息主表
		ReturnVisit rv = new ReturnVisit();
		rv.setId("1230000000");
		rv.setCreateDate(new Date());
		rv.setCreateUser("22222");
		rv.setModifyDate(new Date());
		rv.setModifyUser("1111111");
		rv.setPlanId(null);
		rv.setMemberId("2208");
		rv.setSchedule(new Date());
		rv.setLinkManId("6422");
		rv.setLinkName("坑哥绰号");
		rv.setAccording("HIGHER_APPOINT");
		rv.setWay("go");
		rv.setLinkManMobile("13917097761");
		rv.setLinkManPhone("02131350806");
		rv.setExecutorTime(new Date());
		rv.setExecuteDeptId("11469");
		rv.setExecutorId("111111111111111");
		rv.setScheduleId("99999999");
		rv.setReturnVisitType("dev");
		String id = returnVisitDao.saveReturnVisit(rv);
		rv = null;
		rv = returnVisitDao.getReturnVisit(Integer.valueOf(id));
		Assert.assertEquals(id, rv.getId());
		returnVisitDao.deleteReturnVisitById(id);
	}

	@Test
	public void testCreateReturnVisitOpinion(){
		// 回访-意见信息表
		ReturnVisitOpinion rvo = new ReturnVisitOpinion();
		rvo.setOpinionType("时效类");
		rvo.setProblem("有意见有意见有意见");
		rvo.setSolution("解决解决解决");
		rvo.setReturnVisitId("6");
		
		boolean rs = returnVisitDao.saveReturnVisitOpinion(rvo);
		Assert.assertTrue(rs);
		System.out.println(rs);
	}

	@Test
	public void testCreateReturnVisitVolumePotential(){
		// 回访-走货量信息表
		ReturnVisitVolumePotential rvvp = new ReturnVisitVolumePotential();
		rvvp.setComeFromCity("SH");
		rvvp.setComeToCity("BJ");
		rvvp.setVolumePotential("500");
		rvvp.setCompanyId("SF");
		rvvp.setReturnVisitId("6");
		rvvp.setRemark("备注123");
		rvvp.setCooperate("我愿意合作");
		rvvp.setAccord("1");
		boolean rs = returnVisitDao.saveReturnVisitVolumePotential(rvvp);
		System.out.println(rs);
		Assert.assertTrue(rs);
	}

//	@Test
//	public void testInitCreateReturnvisitPage(){
//		String id = "1845";
//		ReturnVisit rv =returnVisitDao.initDevCreateReturnvisitPage(id);
//		Assert.assertNotNull(rv);
//	}

//	@Test
//	public void testGetReturnVisitLog(){
//		ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
//		condition.setScheType("dev");
//		condition.setPsCustomerId("3098");
//		List<?> list = returnVisitDao.getReturnVisitLog(condition);
//		System.out.println(list.size());
//		
//		condition.setScheType("mat");
//		condition.setPsCustomerId("999999999999999");
//		condition.setMemberId("999999999999999");
//		condition.setLinkManId("111111");
//		list = returnVisitDao.getReturnVisitLog(condition);
//		System.out.println(list.size());
//	}
	
	@Test
	public void testQueryRvListByCustId(){
		String custId = "111111";
		Map paraMap = new HashMap();
		paraMap.put("custId", custId);
		List<String> list = returnVisitDao.queryRvListByCustId(paraMap);
		Assert.assertNotNull(list);
	}
	
//	@Test
//	public void testQueryRvOptionByMarketingIds(){
//		int start = 1;
//		int limit = 10;
//		String custId = "12";
//		String custType = MarketingConstance.MEMBER;
//		Map paraMap = new HashMap();
//		paraMap.put("custId", custId);
//		paraMap.put("custType", custType);
//		List<String> ids = returnVisitDao.queryRvListByCustId(paraMap);
//		List<ReturnVisitOpinionVo> rvOptList = returnVisitDao.queryRvOptionByMarketingIds(ids, start, limit);
//		Assert.assertNotNull(rvOptList);
//	}
	
	@Test 
	public void testQueryRvPotentialByMarketingIds(){
		int start = 1;
		int limit = 10;
		List<String> ids = new ArrayList<String>();
		ids.add("414432635");
		ids.add("414432646");
		ids.add("414432770");
		ids.add("414432776");
		ids.add("414432759");
		ids.add("414432796");
		ids.add("414432797");
		ids.add("414432837");
		
		List<ReturnVisitVolumePotentialVo> list = returnVisitDao.queryRvPotentialByMarketingIds(ids, start, limit);
		Assert.assertNotNull(list);
	}
	
/*	@Test
	public void testCountRvOptionByMarketingIds(){
		String custId = "12";
		String custType = MarketingConstance.MEMBER;
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("custId", custId);
		paraMap.put("custType", custType);
		List<String> ids = returnVisitDao.queryRvListByCustId(paraMap);
		int count = returnVisitDao.countRvOptionByMarketingIds(ids);
		Assert.assertNotNull(count);
	}*/
	
/*	@Test
	public void testCountRvPotentialByMarketingIds(){
		String custId = "12";
		String custType = MarketingConstance.MEMBER;
		Map paraMap = new HashMap();
		paraMap.put("custId", custId);
		paraMap.put("custType", custType);
		List<String> ids = returnVisitDao.queryRvListByCustId(paraMap);
		int count = returnVisitDao.countRvPotentialByMarketingIds(ids);
		
		ids = null;
		count = returnVisitDao.countRvPotentialByMarketingIds(ids);
		Assert.assertNotNull(count);
	}*/
	
	@Test
	public void testInitCase(){
		String id = "403231115";
		returnVisitDao.initMatCreateReturnvisitPage(id);
		//id = "400116094";
		//returnVisitDao.initDevCreateReturnvisitPageByCust(id);
		id = "2518";
		returnVisitDao.initMatCreateReturnvisitPageByCust(id);
	}
	
	@Test
	public void testSaveReturnVisitOpinion(){
		ReturnVisitOpinion rvp = new ReturnVisitOpinion();
		rvp.setReturnVisitId("001");
		Boolean b = returnVisitDao.saveReturnVisitOpinion(rvp);
		System.out.print(b);
	}
	
	@Test
	public void testDeleteReturnVisitById(){
		String id = "408785155";
		returnVisitDao.deleteReturnVisitById(id);
	}
	
	@Test
	public void testInitDevCreateReturnvisitPage(){
		ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
		condition.setCustNumber("055378");
		List<ReturnVisit> list = returnVisitDao.getReturnVisitLog(condition);
		Assert.assertNotNull(list);
	}
}
