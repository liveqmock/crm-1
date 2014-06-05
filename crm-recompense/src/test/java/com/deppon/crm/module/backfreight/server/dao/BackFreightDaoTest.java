package com.deppon.crm.module.backfreight.server.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.jdt.internal.core.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.backfreight.server.dao.impl.BackFreightDao;
import com.deppon.crm.module.backfreight.server.util.BackFreightConstant;
import com.deppon.crm.module.backfreight.shared.domain.BackFreight;
import com.deppon.crm.module.backfreight.shared.domain.BackFreightSearchCondition;
import com.deppon.crm.module.recompense.server.dao.impl.PaymentDao;
import com.deppon.crm.module.recompense.server.util.TestUtil;

import com.deppon.foss.framework.server.context.AppContext;

/**
 * Description:服务补救DAO测试 Create on 2012-11-1 下午3:04:04
 * 
 * @author 华龙
 * @version 1.0
 */
public class BackFreightDaoTest extends TestCase {
	private static BackFreightDao backFreightDao;
	static Connection conn = null;
	 private static PaymentDao paymentDao=null;

	static BackFreight backFreight=new BackFreight();
	 static BackFreight newBackFreight = null;
	static {
		backFreightDao = TestUtil.backFreightDao;
		paymentDao = TestUtil.paymentDao;
	}

	static {
		backFreight.setCreateUser("106138");
		backFreight.setModifyUser("106138");
		backFreight.setModifyDate(new Date());
		backFreight.setCreateDate(new Date());
		backFreight.setWaybillNumber("1000000001");
		backFreight.setApplicant("106138");
		backFreight.setApplyDept("1111");
		backFreight.setCustomerType("CONSIGNE");
		backFreight.setCustomerNum("123456");
		backFreight.setCustomerName("上海德邦物流有限公司");
		backFreight.setWaybillAmount(99d);
		backFreight.setApplyAmount(88);
		backFreight.setOutboundTime(new Date());
		backFreight.setFinanceDept("123456");
		backFreight.setApplicant("106138");
		backFreight.setApplyDept("11111");
		backFreight.setApplyTime(new Date());
		backFreight.setApplyStatus("APPLY_SUBMIT");
		backFreight.setOaWorkflowNum("159753");
		backFreight.setVerifyTime(new Date());
		backFreight.setVerifier("106138");
		backFreight.setSubsidiary("上海德邦物流有限公司");
		backFreight.setCustomerLevel("会员");
		backFreight.setContactName("许华龙");
		backFreight.setPaymentType("CASH");

	}

	@Test
	public void testAddBackFreight() {
		backFreightDao.addBackFreight(null);
		backFreight.setId(null);
		newBackFreight = backFreightDao.addBackFreight(backFreight);
		backFreight.setId("");
		newBackFreight = backFreightDao.addBackFreight(backFreight);
		backFreight.setId("11111");
		newBackFreight = backFreightDao.addBackFreight(backFreight);
		backFreight.setId(null);
		newBackFreight = backFreightDao.addBackFreight(backFreight);
		System.err.println(newBackFreight.getId());
	}

	@Test
	public void testSearchBackFreightByCondition() {
		BackFreightSearchCondition condition = new BackFreightSearchCondition();
		List<String> waybills = new ArrayList<String>();
//		waybills.add(newBackFreight.getWaybillNumber());
		waybills.add("123456");
		condition.setWaybillNumbers(waybills);
		condition.setApplyDept("11111");
		condition.setApplyStatus("APPLY_SUBMIT");
		condition.setEndDate(new Date());
		condition.setStartDate(new Date());
		condition.setPaymentType(BackFreightConstant.BACK_FREIGHT_PAYMENT_ONLINE);
		condition.setStart(1);
		condition.setLimit(10);

		List<BackFreight> backFreights = backFreightDao
				.searchBackFreightByCondition(condition);
		Assert.isNotNull(backFreights);
	}

	@Test
	public void testSearchBackFreightById() {
		BackFreight backFreight = backFreightDao
				.getBackFreightById(newBackFreight.getId());
		backFreight = backFreightDao.getBackFreightById(null);
		backFreight = backFreightDao.getBackFreightById("");
	}

	@Test
	public void testexportBackFreightByCondition() {
		BackFreightSearchCondition condition = new BackFreightSearchCondition();
		condition.getWaybillNumbers().add("111111");
		List<BackFreight> backFreights = backFreightDao
				.exportBackFreightByCondition(condition);
		Assert.isNotNull(backFreights);
		condition.getWaybillNumbers().add("111111111111111111");
		backFreights = backFreightDao.exportBackFreightByCondition(condition);
		backFreights = backFreightDao.exportBackFreightByCondition(null);

	}

	@Test
	public void testcountBackFreightByCondition() {
		BackFreightSearchCondition condition = new BackFreightSearchCondition();
		condition.getWaybillNumbers().add("111111");
		int a = backFreightDao.countBackFreightByCondition(condition);
		Assert.isNotNull(a);
	}

	@Test
	public void testUpdateBackFreight() {
		BackFreight newbackfreight = null;
		backFreightDao.updateBackFreight(newbackfreight);

		newbackfreight = new BackFreight();
		newbackfreight.setId("27");
		newbackfreight.setApplyStatus("APPLY_SUBMIT");
		newbackfreight.setVerifier("1111");
		newbackfreight.setVerifyTime(new Date());
		backFreightDao.updateBackFreight(newbackfreight);

	}

	@Test
	public void testUpdateBackFreightOaWorkfolwNum() {
		backFreightDao.updateBackFreightWorkflowNum(newBackFreight.getId(),
				"111111");
		backFreightDao.updateBackFreightWorkflowNum(null, "111111");
		backFreightDao.updateBackFreightWorkflowNum(newBackFreight.getId(),
				null);
		backFreightDao.updateBackFreightWorkflowNum(null, null);
	}

	@Test
	public void testgetBackFreightOaWorkflowNum() {
		BackFreight backFreight = backFreightDao
				.findBackFreightByOaWorkflowNum("111111");
		backFreight = backFreightDao.findBackFreightByOaWorkflowNum("");
		backFreight = backFreightDao.findBackFreightByOaWorkflowNum(null);

	}

	@Test
	public void testfindBackFreightByWaybillNum() {
		BackFreight backFreight = backFreightDao
				.findValidBackFreightByNum(null);
		backFreight = backFreightDao.findValidBackFreightByNum("");
		backFreight = backFreightDao.findValidBackFreightByNum("112321312312");

		backFreight = backFreightDao.findValidBackFreightByNum(newBackFreight
				.getWaybillNumber());
		backFreightDao.findValidBackFreightByNum(null);

	}

	@Test
	public void testDeleteBackFreightById() {
		backFreightDao.deleteBackFreightById(null);
	}
	@Test
	public void testGetBankByName(){
		
		paymentDao.searchBankListByName("银行");
	}

	@After
	public void tearDown() {
		if(null!=newBackFreight&&null!=newBackFreight.getId()){
		backFreightDao.deleteBackFreightById(newBackFreight.getId());}
		

	}
}
