package com.deppon.crm.module.duty.dao;


import java.math.BigDecimal;
import java.util.Date;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.duty.server.dao.IDutyDao;
import com.deppon.crm.module.duty.server.util.DateCreateUtils;
import com.deppon.crm.module.duty.shared.domain.Duty;
import com.deppon.crm.module.duty.shared.domain.QueryDutyCondition;
import com.deppon.crm.module.duty.shared.domain.SearchDutyCondition;
import com.deppon.crm.module.duty.util.TestUtil;

public class DutyDaoTest extends TestCase{
	private IDutyDao dutyDao =  TestUtil.dutyDao;;
	//工单责任-查询
	@Test
	public void testQueryDutyList(){
		QueryDutyCondition queryDutyCondition = new QueryDutyCondition();
		Assert.assertNotNull(dutyDao.queryDutyList(queryDutyCondition, 0, 1));
	}
	//工单责任-查询条数
	@Test
	public void testQueryDutyListCount(){
		QueryDutyCondition queryDutyCondition = new QueryDutyCondition();
		Assert.assertNotNull(dutyDao.queryDutyListCount(queryDutyCondition));
	}
	//工单责任 -查询-工单责任详情
	@Test
	public void testGetDutyDetail(){
		Assert.assertNotNull(dutyDao.getDutyDetail("1"));
	}
	//工单责任 -查询-处理经过详情
	@Test
	public void testGetDutyDealProcess(){
		Assert.assertNotNull(dutyDao.getDutyDealProcess("1"));
	}
	//工单责任 -查询-划分结果
	@Test
	public void testGetDutyResult(){
		Assert.assertNotNull(dutyDao.getDutyResult("1"));
	}
	//工单责任 -查询-划分结果
	@Test
	public void testGetuserNameList(){
		Assert.assertNotNull(dutyDao.getuserNameList("1"));
	}

	@Test
	public void testSearchDutyByCondition(){
		RowBounds rw = new RowBounds(0,20);
		SearchDutyCondition sdc = new SearchDutyCondition();
		sdc.setReceiveBeginTime(new Date(System.currentTimeMillis()-1000*60*60*24));
		sdc.setReceiveEndTime(new Date());
		dutyDao.searchDutyByCondition(sdc);
		dutyDao.searchDutyByCondition(sdc, rw);
		sdc.setReceiveUser("王");
		dutyDao.searchDutyByCondition(sdc);
		dutyDao.searchDutyByCondition(sdc, rw);
		sdc.setCodeType("bill");
		sdc.setCode("11111");
		dutyDao.searchDutyByCondition(sdc);
		dutyDao.searchDutyByCondition(sdc, rw);
		
	}
	@Test
	public void testCountDutyByCondition(){
		SearchDutyCondition sdc = new SearchDutyCondition();
		sdc.setReceiveBeginTime(new Date(System.currentTimeMillis()-1000*60*60*24));
		sdc.setReceiveEndTime(new Date());
		dutyDao.countDutyByCondition(sdc);
		sdc.setReceiveUser("王");
		dutyDao.countDutyByCondition(sdc);
		sdc.setCodeType("bill");
		sdc.setCode("11111");
		dutyDao.countDutyByCondition(sdc);
	}
	@Test
	public void testUpdateDutyById(){
		Duty duty = new Duty();
		duty.setId("1");
		duty.setReceiveTime(new Date());
		duty.setReceiveUser("测试");
		duty.setReceiveUserId("111111");
		dutyDao.updateDutyById(duty);
	}

	
	//工单责任 -插入工单责任
	@Test
	public void testComplaintsInsert(){
		Complaint complaint = DateCreateUtils.createBean(Complaint.class);
		dutyDao.complaintsInsert(complaint);
	}
	
	//工单责任 -插入工单责任
	@Test
	public void testUpdateComplaint(){
		Complaint complaint = DateCreateUtils.createBean(Complaint.class);
		complaint.setFid(new BigDecimal(111));
		dutyDao.updateComplaint(complaint);
	}


}
