package com.deppon.crm.module.customer.server.dao.impl;


import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.testutils.DataTestUtil;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.ApprovingWorkflowData;

public class ApprovingWorkflowDataDaoTest extends BeanUtil {
	private  static ApprovingWorkflowData approvingWorkflowData;
	
	@Before
	public void setUP() throws Exception {
		//初始化修改会员模块数据
		DataTestUtil.initApprovingWorkflowData();
	}

	@After
	public void tearDown() throws Exception {
		//清理修改会员模块测试数据
		DataTestUtil.cleanApprovingWorkflowData();
	}
	@Test
	public void testInsertContactWorkflowData() {
		approvingWorkflowData = initApprovingWorkflowData();
		approvingWokflowDataDao.insertContactWorkflowData(approvingWorkflowData);
	}

	@Test
	public void testUpdateConWorkflowData() {
		ApprovingWorkflowData approvingWorkflowData2 = initApprovingWorkflowData();
		approvingWokflowDataDao.insertContactWorkflowData(approvingWorkflowData2);
		approvingWokflowDataDao.updateConWorkflowData(approvingWorkflowData2);
	}

	@Test
	public void testDeleteConWorkflowData() {
		ApprovingWorkflowData approvingWorkflowData2 = initApprovingWorkflowData();
		approvingWokflowDataDao.insertContactWorkflowData(approvingWorkflowData2);
		approvingWokflowDataDao.deleteConWorkflowData(approvingWorkflowData2.getId());
		
	}

	@Test
	public void testQueryConWorkflowData() {
		List<ApprovingWorkflowData> list = approvingWokflowDataDao.queryConWorkflowData(approvingWorkflowData);
		assertNotNull(list);
		
	}

	@Test
	public void testQueyById() {
		ApprovingWorkflowData approvingWorkflowData2 = initApprovingWorkflowData();
		approvingWokflowDataDao.insertContactWorkflowData(approvingWorkflowData2);
		assertNotNull(approvingWokflowDataDao.queyById(approvingWorkflowData2.getId()));
	}

	@Test
	public void testUpdateByWorkflowId() {
		approvingWokflowDataDao.updateByWorkflowId(approvingWorkflowData.getId(), true);
	}

	private static ApprovingWorkflowData initApprovingWorkflowData(){
		ApprovingWorkflowData approvingWorkflowData = new ApprovingWorkflowData();
		approvingWorkflowData.setContactMobile("123");
		approvingWorkflowData.setContactName("111");
		approvingWorkflowData.setContactNum("111");
		approvingWorkflowData.setContactTel("111");
		approvingWorkflowData.setCreateDate(new Date());
		approvingWorkflowData.setCreateUser("86301");
		approvingWorkflowData.setWorkflowId("111");
		return approvingWorkflowData;
	}
}
