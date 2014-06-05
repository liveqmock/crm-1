package com.deppon.crm.module.marketing.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.deppon.crm.module.marketing.server.manager.IBoCustomerManager;
import com.deppon.crm.module.marketing.server.manager.IBusinessOpportunityManager;
import com.deppon.crm.module.marketing.server.manager.impl.BoCustomerManager;
import com.deppon.crm.module.marketing.server.manager.impl.BusinessOpportunityManager;
import com.deppon.crm.module.marketing.server.service.IBusinessOpportunityService;
import com.deppon.crm.module.marketing.server.service.impl.BusinessOpportunityService;
import com.deppon.crm.module.marketing.server.utils.BusinessOpportunityValidator;
import com.deppon.crm.module.marketing.shared.domain.BoCustomerCondition;
import com.deppon.crm.module.marketing.shared.domain.BoOperationLog;
import com.deppon.crm.module.marketing.shared.domain.BoReportCondition;
import com.deppon.crm.module.marketing.shared.domain.BoReportInfo;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunity;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunityCondition;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunityCustomer;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportuntiyConstants;
import com.deppon.crm.module.marketing.shared.exception.BusinessOpportunityException;
import com.deppon.crm.module.marketing.shared.exception.BusinessOpportunityExceptionType;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Employee;

public class BusinessOpportunityManagerTest extends TestCase {
	private IBusinessOpportunityManager businessOpportunityManager;
	private IBusinessOpportunityService businessOpportunityService;
	private IBoCustomerManager boCustomerManager;
	public static ApplicationContext factory;

	static {
		// String[] resource = new String[] {
		// "com/deppon/crm/module/**/server/META-INF/spring.xml",
		// "com/deppon/crm/module/marketing/server/META-INF/DataSource.xml",
		// "com/deppon/crm/module/marketing/server/META-INF/businessOpportunityBean.xml"
		// };
		// factory = new ClassPathXmlApplicationContext(resource);
	}

	@Before
	public void setUp() throws Exception {
		businessOpportunityManager = (IBusinessOpportunityManager) SpringTestHelper
				.get().getBean(BusinessOpportunityManager.class);
		businessOpportunityService = (IBusinessOpportunityService) SpringTestHelper
				.get().getBean(BusinessOpportunityService.class);
		boCustomerManager = (IBoCustomerManager) SpringTestHelper.get()
				.getBean(BoCustomerManager.class);
		// businessOpportunityManager = (IBusinessOpportunityManager) factory
		// .getBean("businessOpportunityManager");
		// businessOpportunityService = (IBusinessOpportunityService) factory
		// .getBean("businessOpportunityService");
		// boCustomerManager = (IBoCustomerManager) factory
		// .getBean("boCustomerManager");
	}

	@Test
	public void testFail() {
		// fail("Not yet implemented");
	}

	@Test
	public void testQueryBusinessOpportunityByCondition() {
		Date current = new Date();
		BusinessOpportunityCondition boc = new BusinessOpportunityCondition();
		// boc.setBoName("测试商机");
		// boc.setBoNumber("SJ2014030700010");
		boc.setCustomerId("8807");
		boc.setCustomerName("华航商贸");
		boc.setCustomerNum("008807");
		boc.setContactName("张虎");
		boc.setContactPhone("0760-22223780");
		boc.setContactMobile("18607608788");
		boc.setBoStatus("ONGOING");
		boc.setBoStep("CONTACT");
		boc.setDeptType(0);
		boc.setDeptId("1");

		boc.setStart(1);
		boc.setLimit(5);

		Calendar c = Calendar.getInstance();
		c.setTime(current);
		c.add(Calendar.DATE, -30);
		Date beginDate = c.getTime();
		c.add(Calendar.DATE, 60);
		Date endDate = c.getTime();
		boc.setBeginTime(beginDate);
		boc.setEndTime(endDate);

		List<BusinessOpportunity> boList = businessOpportunityManager
				.queryBusinessOpportunityByCondition(boc);
		for (BusinessOpportunity bo : boList) {
			System.out.println(bo.getBoNumber() + "*"
					+ bo.getCustomer().getCustName() + "*" + bo.getDeptId()
					+ "*" + bo.getCustomer().getMainLinkmanName());
		}
		long boCount = businessOpportunityManager
				.countBusinessOpportunityByCondition(boc);
		System.out.println("Total:" + boCount);
	}

	@Test
	public void testExecBusinessOpportunityReminder() {
		businessOpportunityManager.execBusinessOpportunityReminder();
	}

	@Test
	public void testExecBusinessOpportunityToDormant() {
		businessOpportunityManager.execBusinessOpportunityToDormant();
	}

	@Test
	public void testExecBusinessOpportunityInSuccess() {
		 businessOpportunityManager.execBusinessOpportunityInSuccess();
	}

	@Test
	public void testExecBusinessOpportunityToStepDeliver() {
		businessOpportunityManager.execBusinessOpportunityToStepDeliver();
	}

	@Test
	public void testQueryBusinessOpportunityActiveByCustId() {
		List<BusinessOpportunity> list = businessOpportunityManager
				.queryBusinessOpportunityActiveByCustId("8807");
		System.out.println("testQueryBusinessOpportunityActiveByCustId");
		list = businessOpportunityManager
				.queryBusinessOpportunityActiveByCustNum("008807");
		System.out.println("queryBusinessOpportunityActiveByCustNum");
	}

	@Test
	public void testQueryBusinessOpportunityById() {
		String id = "27";
		BusinessOpportunity bo = businessOpportunityManager
				.queryBusinessOpportunityById(id);
		System.out.println(bo.getBoNumber() + "*"
				+ bo.getCustomer().getCustName() + "*"
				+ bo.getCustomer().getMainLinkmanName());
	}

	@Test
	public void testCreateBusinessOpportunity() {
		Date current = new Date();
		BusinessOpportunity bo = new BusinessOpportunity();
		bo.setBoName("TEST");
		bo.setBoDesc("TEST");
		bo.setExpectDeliveryAmount(999999);
		Calendar c = Calendar.getInstance();
		c.setTime(current);
		c.add(Calendar.MONTH, 1);
		bo.setExpectSuccessDate(c.getTime());
		bo.setCreateTime(current);
		bo.setModifyTime(current);
		bo.setExpectSuccessOdds(100);
		bo.setIsBOConfirm("1");
		bo.setIsBidProject("1");
		bo.setCustomerDemandDesc("不要钱的");
		bo.setCompetitiveInfo("跟老板要生意");
		bo.setSolutionDesc("抢啊");
		bo.setBoStatus(BusinessOpportuntiyConstants.BO_STATUS_ONGOING);
		bo.setBoStep(BusinessOpportuntiyConstants.BO_STEP_CONTACT);
		bo.setCloseReasonCode(BusinessOpportuntiyConstants.BO_CLOSE_REASON_OTHER);
		bo.setCloseReasonDesc(BusinessOpportuntiyConstants.BO_CLOSE_REASON_OTHER);
		bo.setDeptId("123");

		BusinessOpportunityCustomer boCustomer = new BusinessOpportunityCustomer();
		boCustomer.setCustId(Long.toString(current.getTime()));
		// boCustomer.setCustId("8807");
		bo.setCustomer(boCustomer);

		Employee operator = new Employee();
		operator.setId("26276");
		Employee creator = new Employee();
		creator.setId("21688");
		Employee modifier = new Employee();
		modifier.setId("21689");
		bo.setOperator(operator);
		bo.setCreator(creator);
		bo.setModifier(modifier);
		businessOpportunityManager.createBusinessOpportunity(bo);
	}

	@Test
	public void testSaveBusinessOpportunity() {
		Date current = new Date();
		BusinessOpportunity bo = new BusinessOpportunity();
		bo.setId("30");
		bo.setBoDesc("TEST XXX");
		bo.setExpectDeliveryAmount(888888);
		bo.setExpectSuccessDate(current);
		bo.setCreateTime(current);
		bo.setModifyTime(current);
		bo.setExpectSuccessOdds(99);
		bo.setIsBOConfirm("0");
		bo.setIsBidProject("0");
		bo.setCustomerDemandDesc("不要钱的X");
		bo.setCompetitiveInfo("跟老板要生意X");
		bo.setSolutionDesc("抢啊X");
		bo.setBoStatus(BusinessOpportuntiyConstants.BO_STATUS_ONGOING);
		bo.setBoStep(BusinessOpportuntiyConstants.BO_STEP_CONTACT);
		bo.setCloseReasonCode(BusinessOpportuntiyConstants.BO_CLOSE_REASON_OTHER);
		bo.setCloseReasonDesc(BusinessOpportuntiyConstants.BO_CLOSE_REASON_OTHER);

		BusinessOpportunityCustomer boCustomer = new BusinessOpportunityCustomer();
		boCustomer.setCustId("8808");
		bo.setCustomer(boCustomer);

		Employee operator = new Employee();
		operator.setId("26286");
		Employee creator = new Employee();
		creator.setId("21698");
		Employee modifier = new Employee();
		modifier.setId("21699");
		bo.setOperator(null);
		bo.setCreator(creator);
		bo.setModifier(modifier);
		
		Calendar c = Calendar.getInstance();
		c.setTime(current);
		c.add(Calendar.DATE, -1);
		bo.setExpectSuccessDate(c.getTime());
		
		businessOpportunityManager.saveBusinessOpportunity(bo);
	}

	@Test
	public void testIsBetweenYear() {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<开始测试>>>>>>>>>>>>>>>>>>>>>>");
		Date beginDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(beginDate);
		c.add(Calendar.YEAR, 1);
		Date endDate = c.getTime();
		boolean r = BusinessOpportunityValidator.isBetweenYear(beginDate,
				endDate);
		System.out.println(r);

		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<开始测试>>>>>>>>>>>>>>>>>>>>>>");
		c.setTime(beginDate);
		c.add(Calendar.YEAR, 1);
		c.add(Calendar.MINUTE, 1);
		endDate = c.getTime();
		try {
			r = BusinessOpportunityValidator.isBetweenYear(beginDate, endDate);
			fail("未抛出异常");
		} catch (BusinessOpportunityException e) {
			if (e.getErrorCode().equals(
					BusinessOpportunityExceptionType.queryRangeMoreOneYear
							.getErrCode())) {
				assertTrue(true);
				System.out.println(e.getErrorCode());
				System.out.println(e.getMessage());
				System.out.println("Execption:false");
			} else {
				fail("抛出异常不正确");
			}
		}

		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<开始测试>>>>>>>>>>>>>>>>>>>>>>");
		c.setTime(beginDate);
		c.add(Calendar.YEAR, 1);
		c.add(Calendar.MINUTE, -1);
		endDate = c.getTime();
		r = BusinessOpportunityValidator.isBetweenYear(beginDate, endDate);
		System.out.println(r);

		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<开始测试>>>>>>>>>>>>>>>>>>>>>>");
		c.setTime(beginDate);
		c.add(Calendar.YEAR, -1);
		c.add(Calendar.MINUTE, -1);
		endDate = c.getTime();
		try {
			r = BusinessOpportunityValidator.isBetweenYear(beginDate, endDate);
			fail("未抛出异常");
		} catch (BusinessOpportunityException e) {
			if (e.getErrorCode().equals(
					BusinessOpportunityExceptionType.queryRangeMoreOneYear
							.getErrCode())) {
				assertTrue(true);
				System.out.println(e.getErrorCode());
				System.out.println(e.getMessage());
				System.out.println("Execption:false");
			} else {
				fail("抛出异常不正确");
			}
		}
	}

	@Test
	public void testIsBetweenSixMonth() {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<开始测试>>>>>>>>>>>>>>>>>>>>>>");
		Date current = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(current);
		c.add(Calendar.MONTH, 1);
		Date doneDate = c.getTime();
		boolean r = BusinessOpportunityValidator.isBetweenSixMonth(current,
				doneDate);
		System.out.println(r);
	}

	@Test
	public void testQueryMemberByConditionForBo() {
		BoCustomerCondition condition = new BoCustomerCondition();
		// List<String> deptIds = new ArrayList<String>();
		// deptIds.add("123456");
		// condition.setDeptIds(deptIds);
		// condition.setCustNumber("20100411-02485230");
		// condition.setMobile("13916529600");
		// condition.setPhone("13920542100");
		// condition.setCustName("刘");
		// condition.setCustCategory("1");
		// condition.setPurpose("2");
		// condition.setCustResource("3");
		// condition.setFirstTrade("4");
		// condition.setSecondTrade("5");
		// condition.setDevelopStage("6");
		// condition.setLinkmanName("刘剑");
		condition.setDeptId("11216");
		condition.setDeptType(1);
		condition.setParentId("270");
		boCustomerManager.queryMemberByConditionForBo(condition);
	}

	@Test
	public void testQueryBizDeptBySubDept() {
		String biz = boCustomerManager.queryBizDeptBySubDept("316218");
		boCustomerManager.queryDeptByParentDept("九亭", null, 1, 10);
		boCustomerManager.countDeptByParentDept("九亭", null);

	}

	@Test
	public void testAddBusinessOpportunityLog() {
		List<BoOperationLog> logList = BusinessOpportunityManager
				.getBusinessOpportunityLog("1", null,
						BusinessOpportuntiyConstants.BO_STEP_CONTACT, "123456");
		businessOpportunityService.addBusinessOpportunityLog(logList);
		logList = BusinessOpportunityManager.getBusinessOpportunityLog("1",
				BusinessOpportuntiyConstants.BO_STEP_CONTACT,
				BusinessOpportuntiyConstants.BO_STEP_DELIVER, "123456");
		businessOpportunityService.addBusinessOpportunityLog(logList);
		System.out.println("testAddBusinessOpportunityLog");
	}

	@Test
	public void testList() {
		List<BoOperationLog> list = null;
		// System.out.println(list.isEmpty());
		list = new ArrayList<BoOperationLog>();
		System.out.println(list.isEmpty());
	}

	@Test
	public void testQueryCustDeptManagerId() {
		boCustomerManager.queryCustDeptManagerId("8807");
	}

	@Test
	public void testQueryBoReportByCondition() {
		businessOpportunityManager.calcBoReportForDaily();

		BoReportCondition brc = new BoReportCondition();
		// brc.setBeginTime(new Date());
		// brc.setEndTime(new Date());
		brc.setDeptId("0");

		List<BoReportInfo> list = businessOpportunityManager
				.queryBoReportByCondition(brc);
		for (BoReportInfo boReportInfo : list) {
			System.out.println(boReportInfo.getDivisionName() + '-'
					+ boReportInfo.getBizName());

		}
		list = businessOpportunityManager.queryBoReportByCondition(brc);
		for (BoReportInfo boReportInfo : list) {
			System.out.println(boReportInfo.getDivisionName() + '-'
					+ boReportInfo.getBizName());

		}
	}

}
