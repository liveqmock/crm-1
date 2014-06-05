package com.deppon.crm.module.customer.server.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.CustCredit;
import com.deppon.crm.module.customer.shared.domain.CustCreditConfig;

public class CustCreditDaoImplTest extends BeanUtil{
	public void setUp(){
		
	}
	public void tearDown(){
		
	}
	@Test
	public void testgetCustCreditByCustId(){
		custCreditDao.getCustCreditByCustId("40001944");
	}
	@Test
	public void testupdateCustCreditConfig(){
		CustCreditConfig config =custCreditDao.getCustCreditConfig();
		custCreditDao.updateCustCreditConfig(config);
	}
	@Test
	public void testgetCustBadCreditList(){
		custCreditDao.getCustBadCreditList();
	}
	@Test
	public void testgetCustBadCreditExcelList(){
		custCreditDao.getCustBadCreditExcelList();
	}
	@Test
	public void testupdateEarlyWarnInfo(){
		custCreditDao.updateEarlyWarnInfo("1",true);
	}
	@Test
	public void testgetCustCreditListByCondition(){
		CustCredit custCredit = new CustCredit();
		custCredit.setId("1");
		custCredit.setModifyUser("1");
		custCreditDao.getCustCreditListByCondition(custCredit);
	}
	@Test
	public void testgetManagementDeptList(){
		custCreditDao.getManagementDeptList();
	}
	@Test
	public void testgetBusDeptList(){
		custCreditDao.getBusDeptList();
	}
	@Test
	public void testgetBigAreaDeptList(){
		custCreditDao.getBigAreaDeptList();
	}
	@Test
	public void testgetAreaDeptList(){
		custCreditDao.getAreaDeptList();
	}
	@Test
	public void testgetResponsibilityDeptList(){
		custCreditDao.getResponsibilityDeptList();
	}
	@Test
	public void testgetCustCreditListByConditions(){
		Map<String,String> map = new HashMap<String, String>();
		custCreditDao.getCustCreditListByConditions(map, 0, 1);
		custCreditDao.getCustCreditListByConditions(map, 0, -1);
	}
	@Test
	public void testgetCustCreditListByOtherConditions(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("deptId", "123");
		map.put("beginDate", "2014-01-01");
		map.put("endDate", "2014-01-01");
		custCreditDao.getCustCreditListByOtherConditions(map, 0, 1);
	}
	@Test
	public void testgetCustCreditListByBranch(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("deptId", "123");
		map.put("beginDate", "2014-01-01");
		map.put("endDate", "2014-01-15");
		custCreditDao.getCustCreditListByBranch(map);
	}
	@Test
	public void testgetDeliverMoneyByCondtion(){
		//TODO 太慢了
//		custCreditDao.getDeliverMoneyByCondtion(new Date(), new Date());
	}
}
