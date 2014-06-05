package com.deppon.crm.module.report.server.manager;


import junit.framework.TestCase;

import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.report.server.manager.impl.KeycustReportManager;
import com.deppon.crm.module.report.server.util.TestUtil;
import com.deppon.crm.module.report.shared.exception.ReportException;
import com.deppon.crm.module.report.shared.exception.ReportExceptionType;
import com.deppon.foss.framework.server.context.UserContext;

public class KeycustReportManagerTest extends TestCase {
	private KeycustReportManager keycustReportManager;
	private String custNum;
	private String errorCustNum;

	public void setUp() {
		keycustReportManager = (KeycustReportManager) TestUtil
				.getBean("keycustReportManager");
		custNum = "40001904";
		errorCustNum = "aaaaaaa";
		User user =new User();
		Employee emp =new Employee();
		Department dept = new Department();
		dept.setId("49311");
		emp.setDeptId(dept);
		user.setEmpCode(emp);
		UserContext.setCurrentUser(user);
	}

	@Test
	public void test_queryShipmentAmount() {
		keycustReportManager.queryShipmentAmount(custNum);
		try {
			keycustReportManager.queryShipmentAmount(null);
			fail("该抛不抛");
		} catch (ReportException e) {
			assertEquals(ReportExceptionType.NULL_CUSTNUM.getErrorCode(),
					e.getErrorCode());
		}
	}

	@Test
	public void test_queryProductAmount() {
		keycustReportManager.queryProductAmount(custNum);
		keycustReportManager.queryProductAmount(errorCustNum);
	}

	@Test
	public void test_queryRoadAmount() {
		keycustReportManager.queryRoadAmount(custNum);
		keycustReportManager.queryRoadAmount(errorCustNum);
	}

	@Test
	public void test_queryShipmentAging() {
		keycustReportManager.queryShipmentAging(custNum);
		keycustReportManager.queryShipmentAging(errorCustNum);
	}

	@Test
	public void test_queryShipmentQuality() {
		keycustReportManager.queryShipmentQuality(custNum);
		keycustReportManager.queryShipmentQuality(errorCustNum);
	}

	@Test
	public void test_queryShipmentQualityForChart() {
		keycustReportManager.queryShipmentQualityForChart(custNum);
		keycustReportManager.queryShipmentQualityForChart(errorCustNum);
	}

	@Test
	public void test_checkCustNum() {
		try {
			keycustReportManager.checkCustNum(null);
			fail("该抛不抛");
		} catch (ReportException e) {
			assertEquals(ReportExceptionType.NULL_CUSTNUM.getErrorCode(),
					e.getErrorCode());
		}
		keycustReportManager.checkCustNum(custNum);
		try {
			keycustReportManager.checkCustNum(errorCustNum);
			fail("该抛不抛");
		} catch (ReportException e) {
			assertEquals(ReportExceptionType.MEMBER_NULL.getErrorCode(),
					e.getErrorCode());
		}
		try {
			keycustReportManager.checkCustNum("000890");
			fail("该抛不抛");
		} catch (ReportException e) {
			assertEquals(ReportExceptionType.DEPTID_NOT_SAME.getErrorCode(),
					e.getErrorCode());
		}
		try {
			keycustReportManager.checkCustNum("40000464");
			fail("该抛不抛");
		} catch (ReportException e) {
			assertEquals(ReportExceptionType.NOT_KEY_CUST.getErrorCode(),
					e.getErrorCode());
		}
		keycustReportManager.checkCustNum(custNum);
	}
	@Test
	public void test_export(){
		try {
			keycustReportManager.exportKeyCustomerWord("40001904",false);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		keycustReportManager.exportKeyCustomerExcel("40001904",false);
	}
}
