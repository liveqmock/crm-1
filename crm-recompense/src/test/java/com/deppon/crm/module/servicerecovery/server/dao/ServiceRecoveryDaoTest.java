package com.deppon.crm.module.servicerecovery.server.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jdt.internal.core.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.recompense.server.dao.impl.RecompenseDaoImpl;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.servicerecovery.server.dao.impl.ServiceRecoveryDao;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecoverySearchCondition;
import com.deppon.foss.framework.server.context.AppContext;

/**
 * Description:服务补救DAO测试 Create on 2012-11-1 下午3:04:04
 * 
 * @author 华龙
 * @version 1.0
 */
public class ServiceRecoveryDaoTest {
	private static ServiceRecoveryDao serviceRecoveryDao;
	static String waybillNumbers = null;

	ServiceRecovery serviceRecovery = new ServiceRecovery();
	static ServiceRecovery newServiceRecovery = null;
	static {

		serviceRecoveryDao = TestUtil.serviceRecoveryDao;
	
	}

	@Before
	public void setUp() throws Exception {
		serviceRecovery.setCreateUser("106138");
		serviceRecovery.setModifyUser("106138");
		serviceRecovery.setModifyDate(new Date());
		serviceRecovery.setCreateDate(new Date());
		serviceRecovery.setWaybillNumber("1000000001");
		serviceRecovery.setApplicant("106138");
		serviceRecovery.setApplyDept("1111");
		serviceRecovery.setCustomerType("CONSIGNE");
		serviceRecovery.setCustomerNum("123456");
		serviceRecovery.setCustomerName("上海德邦物流有限公司");
		serviceRecovery.setWaybillAmount(99d);
		serviceRecovery.setReductionAmount(88d);
		serviceRecovery.setOutboundTime(new Date());
		serviceRecovery.setFinanceDept("123456");
		serviceRecovery.setOperator("106138");
		serviceRecovery.setReductionType("DAMAGE");
		serviceRecovery.setTotalPackage(4);
		serviceRecovery.setDamagePackage(3);
		serviceRecovery.setRecoveryReason("货物破损");
		serviceRecovery.setApplicant("106138");
		serviceRecovery.setApplyDept("11111");
		serviceRecovery.setApplyTime(new Date());
		serviceRecovery.setApplyStatus("APPLY_SUBMIT");
		serviceRecovery.setOaWorkflowNum("159753");
		serviceRecovery.setVerifyTime(new Date());
		serviceRecovery.setVerifier("106138");
		serviceRecovery.setSubsidiary("上海德邦物流有限公司");
		serviceRecovery.setCustomerLevel("会员");
		serviceRecovery.setContactName("许华龙");

	}

	@Test
	public void testAddServiceRecovery() {
		serviceRecoveryDao.addServiceRecovery(null);
		newServiceRecovery = serviceRecoveryDao
				.addServiceRecovery(serviceRecovery);
		serviceRecovery.setId("1111111111111");
		serviceRecoveryDao.addServiceRecovery(serviceRecovery);
		serviceRecovery.setId("");
		serviceRecoveryDao.addServiceRecovery(serviceRecovery);
		serviceRecovery.setId(null);
		serviceRecoveryDao.addServiceRecovery(serviceRecovery);
		Assert.isNotNull(newServiceRecovery);

	}

	@Test
	public void testupdateServiceRecoveryWorkflowNum() {
		serviceRecoveryDao.updateServiceRecoveryWorkflowNum(
				newServiceRecovery.getId(), "15698789", "ICRM201401010000123");
		serviceRecoveryDao.updateServiceRecoveryWorkflowNum(null, "15698789", "ICRM201401010000123");
		serviceRecoveryDao.updateServiceRecoveryWorkflowNum(
				newServiceRecovery.getId(), null, "ICRM201401010000123");
		serviceRecoveryDao.updateServiceRecoveryWorkflowNum(null, null, "ICRM201401010000123");
		serviceRecoveryDao.updateServiceRecoveryWorkflowNum(
				newServiceRecovery.getId(), "15698789", "ICRM201401010000123");

	}

	@Test
	public void testSearchServiceRecoveryByCondition() {
		ServiceRecoverySearchCondition condition = new ServiceRecoverySearchCondition();

		List<String> waybills = new ArrayList<String>();
		// waybills.add(newServiceRecovery.getWaybillNumber());
		// condition.setWaybillNumbers(waybills);
		// condition.setApplyStatus("UNAGREE");
		// condition.setCustomerType("CONSIGNE");
		// condition.setEndDate(new Date());
		// condition.setStartDate(new Date());
		// condition.setStart(1);
		// condition.setLimit(10);
		List<ServiceRecovery> ss = serviceRecoveryDao
				.searchServiceRecoveryByCondition(condition);
		Assert.isNotNull(ss);

		// System.out.println(ss.get(0).getOperatorName());

	}

	@Test
	public void testSearchServiceRecoveryById() {
		ServiceRecovery ss = serviceRecoveryDao
				.getServiceRecoveryById(newServiceRecovery.getId());
		ss = serviceRecoveryDao.getServiceRecoveryById(null);
		ss = serviceRecoveryDao.getServiceRecoveryById("");

	}

	@Test
	public void testexportServiceRecoveryByCondition() {
		ServiceRecoverySearchCondition condition = new ServiceRecoverySearchCondition();
		List<String> waybills = new ArrayList<String>();
		waybills.add(newServiceRecovery.getWaybillNumber());
		condition.setWaybillNumbers(waybills);
		condition.setApplyDept("11111");
		condition.setApplyStatus("UNAGREE");
		condition.setCustomerType("CONSIGNE");
		condition.setEndDate(new Date());
		condition.setStartDate(new Date());
		List<ServiceRecovery> ss = serviceRecoveryDao
				.exportServiceRecoveryByCondition(condition);
		Assert.isNotNull(ss);

	}

	@Test
	public void testcountServiceRecoveryByCondition() {
		ServiceRecoverySearchCondition condition = new ServiceRecoverySearchCondition();
		int count = serviceRecoveryDao
				.countServiceRecoveryByCondition(condition);
		Assert.isNotNull(count);

		System.out.println(count);
	}

	@Test
	public void testUpdateServiceRecovery() {
		serviceRecoveryDao.updateServiceRecovery(null);
		ServiceRecovery serviceRecovery1 = new ServiceRecovery();
		serviceRecovery1.setId(newServiceRecovery.getId());
		serviceRecovery1.setApplyStatus("UNAGREE");
		serviceRecovery1.setVerifier("1");
		serviceRecovery1.setVerifyTime(new Date());
		serviceRecovery1.setCustomerNum("12121212");
		serviceRecoveryDao.updateServiceRecovery(serviceRecovery1);

	}

	@Test
	public void testgetServiceRecoveryOaWorkflowNum() {
		serviceRecoveryDao.getServiceRecoveryByOaWorkflowNum("15698789");
		serviceRecoveryDao.getServiceRecoveryByOaWorkflowNum(null);
		serviceRecoveryDao.getServiceRecoveryByOaWorkflowNum("");

	}

	@Test
	public void testfindServiceRecoveryByWaybillNum() {
		serviceRecoveryDao.findValidServiceRecoveryByNum(null);
		serviceRecoveryDao.findValidServiceRecoveryByNum("");
		serviceRecoveryDao
				.findValidServiceRecoveryByNum("1111111111111111111111");

		ServiceRecovery ss = serviceRecoveryDao
				.findValidServiceRecoveryByNum(newServiceRecovery
						.getWaybillNumber());

	}

	@After
	public void tearDown() {
		serviceRecoveryDao.deleteServiceRecovery(newServiceRecovery.getId());

	}
}
