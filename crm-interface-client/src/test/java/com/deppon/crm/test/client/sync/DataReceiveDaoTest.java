package com.deppon.crm.test.client.sync;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.sync.dao.IDataReceiveDao;
import com.deppon.crm.module.client.sync.domain.OrderRightInfo;
import com.deppon.crm.module.client.sync.domain.SiteReceiveRequest;
import com.deppon.crm.test.client.util.SpringHelper;

public class DataReceiveDaoTest {
	IDataReceiveDao dataReceiveDao;

	@Before
	public void init() {
		dataReceiveDao = (IDataReceiveDao) SpringHelper.getApplicationContext()
				.getBean("dataReceiveDao");
	}

	@Test
	public void testSaveOrderRight() {
		OrderRightInfo info = new OrderRightInfo();
		info.setDepartment("DDDDD");
		info.setOperateTime(new Date());
		info.setOperateType("I");
		info.setOrderTeam("DDD");
		dataReceiveDao.saveOrderRight(info);
		dataReceiveDao.cancelOrderRight(info);
	}
	
	@Test
	public void testSaveSiteReceive() {
		SiteReceiveRequest siteReceiveRequest = new SiteReceiveRequest();
		siteReceiveRequest.setDeptName("1");
		siteReceiveRequest.setDeptCode("1");
		siteReceiveRequest.setDeptProvince("1");
		siteReceiveRequest.setDeptCity("1");
		siteReceiveRequest.setIsAB(true);
		siteReceiveRequest.setDeptArea("1");
		siteReceiveRequest.setDeptAddress("1");
		siteReceiveRequest.setDescript("1");
		siteReceiveRequest.setContactWay("1");
		siteReceiveRequest.setLeaveOutDept("1");
		siteReceiveRequest.setLeaveMiddleChange("1");
		siteReceiveRequest.setIsUsed(true);
		siteReceiveRequest.setSuperiorArea("1");
		siteReceiveRequest.setIsOpen(true);
		siteReceiveRequest.setOrganisationId("1");
		siteReceiveRequest.setIsArrived(true);
		siteReceiveRequest.setIsLeave(true);
		siteReceiveRequest.setIsSendToHome(true);
		siteReceiveRequest.setIsGetBySelf(true);
		siteReceiveRequest.setIsOutSend(true);
		siteReceiveRequest.setIsCarTeam(true);
		siteReceiveRequest.setIsHasPDA(true);
		siteReceiveRequest.setStandardCode("1");
		siteReceiveRequest.setHandType("1");
		dataReceiveDao.saveSiteReceive(siteReceiveRequest);
	}
}
