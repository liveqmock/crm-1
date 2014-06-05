package com.deppon.crm.module.servicerecovery.server.manager;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.backfreight.shared.domain.BackFreight;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.servicerecovery.server.manager.impl.ServiceRecoveryValidator;
import com.deppon.crm.module.servicerecovery.server.util.ServiceRecoveryConstant;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecoverySearchCondition;
import com.deppon.crm.module.servicerecovery.shared.domain.Waybill;
import com.deppon.crm.module.servicerecovery.shared.exception.ServiceRecoveryExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

import junit.framework.TestCase;

/**
 * Description: ServiceRecoveryValidatorTest.java Create on 2012-11-6 上午8:34:08
 * 
 * @author 华龙
 * @version 1.0
 */
public class ServiceRecoveryValidatorTest extends TestCase {
	@Test
	public void testCheckWaybill() {
		Waybill waybill = null;
		boolean result;
		User user=new User();
		try {
			ServiceRecoveryValidator.checkWaybill(waybill, 11,user, null, null, null, null);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					ServiceRecoveryExceptionType.WAYBILL_NOT_EXIST_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		waybill = new Waybill();
		waybill.setIsSigned(false);
		try {
			ServiceRecoveryValidator.checkWaybill(waybill, 9,user, null, null, null, null);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					ServiceRecoveryExceptionType.WAYBILL_NOT_SIGNIN_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		waybill.setIsSigned(true);
		waybill.setIsNormalSigned(true);
		waybill.setSenderDeptStandardCode("1212");
		waybill.setConsigneeDeptStandardCode("1212");
		Employee employee = new Employee();
		Department dept = new Department();
		dept.setStandardCode("1212");
		employee.setDeptId(dept);
		user.setEmpCode(employee);
		try {
			ServiceRecoveryValidator.checkWaybill(waybill, 11,user, null, null, null, null);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.WAYBILL_OUT_OF_APPLYDATE_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		ServiceRecoveryValidator.checkWaybill(waybill, 9,user, null, null, null, null);
	}

	@Test
	public void testCheckAllServiceInvalid() {
		BackFreight backfreight = null;
		ServiceRecovery serviceRecovery = null;
		RecompenseApplication recompense = null;
		boolean delayAccident = false;
		boolean isTrue = ServiceRecoveryValidator.checkAllServiceInvalid(
				backfreight, serviceRecovery, recompense, delayAccident);
		assertTrue(isTrue);
		boolean result = false;
		try {
			serviceRecovery = new ServiceRecovery();
			result = ServiceRecoveryValidator.checkAllServiceInvalid(
					backfreight, serviceRecovery, recompense, delayAccident);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.WAYBILL_APPLED_SERVICE_RECOVERY_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		try {
			serviceRecovery = null;
			recompense = new RecompenseApplication();
			result = ServiceRecoveryValidator.checkAllServiceInvalid(
					backfreight, serviceRecovery, recompense, delayAccident);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.WAYBILL_APPLED_RECOMPENSE_EXCPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		try {
			serviceRecovery = null;
			recompense = null;
			delayAccident = true;
			result = ServiceRecoveryValidator.checkAllServiceInvalid(
					backfreight, serviceRecovery, recompense, delayAccident);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.WAYBILL_APPLED_DELAYACCIDENT_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);

			} else {
				fail("抛出异常不正确");
			}
		}
		try {
			serviceRecovery = null;
			recompense = null;
			delayAccident = false;
			backfreight = new BackFreight();
			result = ServiceRecoveryValidator.checkAllServiceInvalid(
					backfreight, serviceRecovery, recompense, delayAccident);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.WAYBILL_APPLED_BACKFREIGHT_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}

	}

	@Test
	public void testCheckServiceRecoveryValid() {
		ServiceRecovery serviceRecovery = new ServiceRecovery();
		serviceRecovery.setReductionType("11");
		boolean result = false;
		try {
			result = ServiceRecoveryValidator
					.checkServiceRecoveryValid(serviceRecovery);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.SERVICE_RECOVERY_NULL_CUSTOMER_TYPE_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		serviceRecovery.setCustomerType("CONSIGNEE");
		try {
			result = ServiceRecoveryValidator
					.checkServiceRecoveryValid(serviceRecovery);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.SERVICE_RECOVERY_INPUTAMOUNT_IS_NULL_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		serviceRecovery.setReductionAmount(189.1);
		try {
			result = ServiceRecoveryValidator
					.checkServiceRecoveryValid(serviceRecovery);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.SERVICE_RECOVERY_INPUTAMOUNT_MORE_100_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		serviceRecovery.setReductionAmount(89.1);
		serviceRecovery.setWaybillAmount(88.1);
		serviceRecovery.setDamagePackage(null);
		try {
			result = ServiceRecoveryValidator
					.checkServiceRecoveryValid(serviceRecovery);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.SERVICE_RECOVERY_INPUTAMOUNT_MORE_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		serviceRecovery.setWaybillAmount(90.1);
		serviceRecovery
				.setReductionType(ServiceRecoveryConstant.SERVICE_RECOVERY_REDUCTION_DAMAGE);
		serviceRecovery.setDamagePackage(null);
		try {
			result = ServiceRecoveryValidator
					.checkServiceRecoveryValid(serviceRecovery);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.SERVICE_RECOVERY_DAMAGEPACKAGE_IS_NULL_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		serviceRecovery.setDamagePackage(3);
		serviceRecovery.setTotalPackage(2);
		try {
			result = ServiceRecoveryValidator
					.checkServiceRecoveryValid(serviceRecovery);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.SERVICE_RECOVERY_DAMAGEPACKAGE_MORE_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}

		serviceRecovery.setTotalPackage(4);
		for (int i = 0; i < 12; i++) {
			serviceRecovery.getFileInfoList().add(new FileInfo());

		}
		try {
			result = ServiceRecoveryValidator
					.checkServiceRecoveryValid(serviceRecovery);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.SERVICE_RECOVERY_ATTCHMENT_MORE_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		// serviceRecovery = new ServiceRecovery();
		serviceRecovery
				.setReductionType(ServiceRecoveryConstant.SERVICE_RECOVERY_REDUCTION_DAMAGE);
		serviceRecovery.setFileInfoList(null);
		try {
			result = ServiceRecoveryValidator
					.checkServiceRecoveryValid(serviceRecovery);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.SERVICE_RECOVERY_DAMAGE_ATTCHMENT_NOT_NULL_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		serviceRecovery.setFileInfoList(new ArrayList<FileInfo>());
		result = ServiceRecoveryValidator
				.checkServiceRecoveryValid(serviceRecovery);
	}

	@Test
	public void testCheckServiceRecoveryCondition() {
		boolean result = false;
		try {
			result = ServiceRecoveryValidator
					.checkServiceRecoveryCondition(null);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.SERVICE_RECOVERY_NULL_SERACH_CONDITION_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		ServiceRecoverySearchCondition condition = new ServiceRecoverySearchCondition();
		condition.setStartDate(new Date(2011, 1, 3));
		condition.setEndDate(new Date(2012, 4, 7));
		condition.setWaybillNumbers(new ArrayList<String>());
		//condition.getWaybillNumbers().add("1");

		try {
			result = ServiceRecoveryValidator
					.checkServiceRecoveryCondition(condition);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.SERVICE_RECOVERY_DATE_OUT_90_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		condition.setStartDate(new Date(2012, 1, 9));
		condition.setEndDate(new Date(2012, 2, 7));
		for (int i = 0; i < 13; i++) {
			condition.getWaybillNumbers().add("1");
		}
		try {
			result = ServiceRecoveryValidator
					.checkServiceRecoveryCondition(condition);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.SERVICE_RECOVERY_WAYBILL_TOMORE_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		condition.setWaybillNumbers(new ArrayList<String>());
		for (int i = 0; i < 9; i++) {
			condition.getWaybillNumbers().add("1");
		}
		result = ServiceRecoveryValidator
				.checkServiceRecoveryCondition(condition);
		condition.setWaybillNumbers(null);
		result = ServiceRecoveryValidator
				.checkServiceRecoveryCondition(condition);
		condition.setWaybillNumbers(new ArrayList<String>());
		result = ServiceRecoveryValidator
				.checkServiceRecoveryCondition(condition);

	}

	@Test
	public void testCheckApplyStatus() {
		ServiceRecovery serviceRecovery = new ServiceRecovery();
		serviceRecovery
				.setApplyStatus(ServiceRecoveryConstant.SERVICE_RECOVERY_STATUS_WAIT_ACCEPT);
		ServiceRecoveryValidator.checkApplyStatus(serviceRecovery);
		serviceRecovery.setApplyStatus("null");
		boolean result;
		try {
			result = ServiceRecoveryValidator.checkApplyStatus(serviceRecovery);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.SERVICE_RECOVERY_APPLY_ERROR_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}

	}

	@Test
	public void testCheckServiceRecoveryExist() {
		ServiceRecovery serviceRecovery = null;
		boolean result;
		try {
			result = ServiceRecoveryValidator
					.checkServiceRecoveryExist(serviceRecovery);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(ServiceRecoveryExceptionType.SERVICE_RECOVERY__NOT_EXIST_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		serviceRecovery = new ServiceRecovery();
		serviceRecovery.setWaybillNumber(null);
		try {
			result = ServiceRecoveryValidator
					.checkServiceRecoveryExist(serviceRecovery);
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					ServiceRecoveryExceptionType.WAYBILL_NOT_EXIST_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		serviceRecovery.setWaybillNumber("11111");
		result = ServiceRecoveryValidator
				.checkServiceRecoveryExist(serviceRecovery);
	}
}
