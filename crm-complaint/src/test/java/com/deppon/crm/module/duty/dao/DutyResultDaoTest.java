package com.deppon.crm.module.duty.dao;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.deppon.crm.module.duty.server.dao.IDutyResultDao;
import com.deppon.crm.module.duty.server.util.DateCreateUtils;
import com.deppon.crm.module.duty.shared.domain.DutyResult;
import com.deppon.crm.module.duty.util.TestUtil;

public class DutyResultDaoTest extends TestCase{

	private IDutyResultDao dutyResultDao =  TestUtil.dutyResultDao;
	
	
	//责任划分结果查询
	@Test
	public void testDearchDutyResult(){
		DutyResult dutyResult = DateCreateUtils.createBean(DutyResult.class);
		dutyResultDao.searchDutyResult(dutyResult);
	}
	
	//责任划分结果查询
	@Test
	public void testSelectDutyResultByPrimaryKey(){
		dutyResultDao.selectDutyResultByPrimaryKey("111");
	}
	
	//责任划分修改
	@Test
	public void testUpdateDutyResult(){
		DutyResult dutyResult = DateCreateUtils.createBean(DutyResult.class);
		dutyResult.setId("11111");
		dutyResultDao.updateDutyResult(dutyResult);
	}

	//责任划分删除
	@Test
	public void testDeleteDutyResults(){
		List<String> ids = new ArrayList<String>();
		ids.add("11111");
		dutyResultDao.deleteDutyResults(ids);
	}
	
	//责任划分删除
	@Test
	public void testDeleteDutyResultsByDutyId(){
		dutyResultDao.deleteDutyResultsByDutyId("11111");
	}
	
	
	//责任划分新增
	@Test
	public void testInsertDutyResult(){
		DutyResult dutyResult = DateCreateUtils.createBean(DutyResult.class);
		dutyResult.setId("11111");
		dutyResultDao.insertDutyResult(dutyResult);
	}
	
}


