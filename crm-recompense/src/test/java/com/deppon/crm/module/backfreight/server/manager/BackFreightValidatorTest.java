package com.deppon.crm.module.backfreight.server.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.backfreight.server.manager.impl.BackFreightValidator;
import com.deppon.crm.module.backfreight.server.util.BackFreightConstant;
import com.deppon.crm.module.backfreight.shared.domain.BackFreight;
import com.deppon.crm.module.backfreight.shared.domain.BackFreightSearchCondition;
import com.deppon.crm.module.backfreight.shared.exception.BackFreightExceptionType;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.Waybill;
import com.deppon.foss.framework.exception.GeneralException;

import junit.framework.TestCase;

/**
 * Description: ServiceRecoveryValidatorTest.java Create on 2012-11-6 上午8:34:08
 * 
 * @author 华龙
 * @version 1.0
 */
public class BackFreightValidatorTest extends TestCase {
	@Test
	public void testCheckWaybill() {
		Waybill waybill = null;
		boolean result;
		User user = new User();
		Employee employee = new Employee();
		Department department = new Department();
		department.setStandardCode("1");
		employee.setDeptId(department);
		user.setEmpCode(employee);
		try {
			BackFreightValidator.checkWaybill(waybill, 11, user);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					BackFreightExceptionType.WAYBILL_NOT_EXIST_EXCEPTION
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
			BackFreightValidator.checkWaybill(waybill, 9, user);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					BackFreightExceptionType.WAYBILL_NOT_SIGNIN_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		waybill.setIsSigned(true);
		waybill.setConsigneeDeptStandardCode("1");
		waybill.setTranType("1");
		try {
			BackFreightValidator.checkWaybill(waybill, 6, user);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					BackFreightExceptionType.WAYBILL_ERROR_TRANTYPE_EXECEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		waybill.setTranType(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_AIR);
		waybill.setConsigneeDeptStandardCode("2");
		waybill.setPaymentType(BackFreightConstant.BACK_FREIGHT_PAYMENT_ARRIVE);

		try {
			BackFreightValidator.checkWaybill(waybill, 7, user);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(BackFreightExceptionType.WAYBILL_ERROR_APPLY_DEPT_EXECEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		waybill.setConsigneeDeptStandardCode("2");
		waybill.setTranType(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_CITY);
		waybill.setPaymentType(BackFreightConstant.BACK_FREIGHT_PAYMENT_CARD);
		try {
			BackFreightValidator.checkWaybill(waybill, 9, user);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					BackFreightExceptionType.WAYBILL_ERROR_APPLY_DEPT_EXECEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		waybill.setTranType(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_CITY);
		waybill.setPaymentType(BackFreightConstant.BACK_FREIGHT_PAYMENT_ARRIVE);
		waybill.setConsigneeDeptStandardCode("1");
		try {
			BackFreightValidator.checkWaybill(waybill, 15, user);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					BackFreightExceptionType.WAYBILL_OUT_OF_APPLYDATE_EXCEPTION
							.getErrorCode())) {
				System.err.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		waybill.setTranType(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_CITY);
		waybill.setPaymentType(BackFreightConstant.BACK_FREIGHT_PAYMENT_ARRIVE);
		waybill.setConsigneeDeptStandardCode("1");
		try {
			BackFreightValidator.checkWaybill(waybill, 15, user);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					BackFreightExceptionType.WAYBILL_OUT_OF_APPLYDATE_EXCEPTION
							.getErrorCode())) {
				System.err.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}

	}

	@Test
	public void testCheckAllServiceInvalid() {
		BackFreight backfreight = null;
		ServiceRecovery serviceRecovery = null;
		RecompenseApplication recompense = null;
		Boolean delayAccident = false;
		Boolean badDebt = false;
		boolean isTrue = BackFreightValidator.checkAllServiceInvalid(
				backfreight, serviceRecovery, recompense, delayAccident,
				badDebt);
		assertTrue(isTrue);
		boolean result = false;
		try {
			backfreight = new BackFreight();
			result = BackFreightValidator.checkAllServiceInvalid(backfreight,
					serviceRecovery, recompense, delayAccident, badDebt);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(BackFreightExceptionType.WAYBILL_APPLED_BACKFREIGHT_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}

		try {
			backfreight = null;
			serviceRecovery = new ServiceRecovery();
			result = BackFreightValidator.checkAllServiceInvalid(backfreight,
					serviceRecovery, recompense, delayAccident, badDebt);
			fail("未抛出异常");
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(BackFreightExceptionType.WAYBILL_APPLED_SERVICE_RECOVERY_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		try {
			backfreight = null;
			serviceRecovery = null;
			recompense = new RecompenseApplication();
			result = BackFreightValidator.checkAllServiceInvalid(backfreight,
					serviceRecovery, recompense, delayAccident, badDebt);
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					BackFreightExceptionType.WAYBILL_APPLED_RECOMPENSE_EXCPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		try {
			backfreight = null;
			serviceRecovery = null;
			recompense = null;
			delayAccident = true;
			result = BackFreightValidator.checkAllServiceInvalid(backfreight,
					serviceRecovery, recompense, delayAccident, badDebt);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(BackFreightExceptionType.WAYBILL_APPLED_DELAYACCIDENT_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);

			} else {
				fail("抛出异常不正确");
			}
		}
		try {
			backfreight = null;
			serviceRecovery = null;
			recompense = null;
			delayAccident = false;
			badDebt = true;
			result = BackFreightValidator.checkAllServiceInvalid(backfreight,
					serviceRecovery, recompense, delayAccident, badDebt);
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					BackFreightExceptionType.WAYBILL_APPLED_BADDEBT_EXCEPTION
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
		BackFreight backFreight = new BackFreight();
		backFreight.setApplyAmount(null);
		boolean result = false;
		try {
			result = BackFreightValidator.checkBackFreightValid(backFreight);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(BackFreightExceptionType.BACK_FREIGHT_APPLYAMOUNT_NULL_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		backFreight.setApplyAmount(-1);
		try {
			result = BackFreightValidator.checkBackFreightValid(backFreight);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(BackFreightExceptionType.BACK_FREIGHT_APPLYAMOUNT_ZERO_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}

		backFreight.setTranType(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_AIR);
		backFreight.setWaybillAmount(100);
		backFreight.setApplyAmount(101);
		try {
			result = BackFreightValidator.checkBackFreightValid(backFreight);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(BackFreightExceptionType.BACK_FREIGHT_APPLYAMOUNT_MORE_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		backFreight
				.setTranType(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_CITY);
		backFreight.setWaybillAmount(100);
		backFreight.setApplyAmount(301);
		try {
			result = BackFreightValidator.checkBackFreightValid(backFreight);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(BackFreightExceptionType.BACK_FREIGHT_APPLYAMOUNT_THREEMORE_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		backFreight.setApplyAmount(101);
		for (int i = 0; i < 12; i++) {
			backFreight.getFileInfoList().add(new FileInfo());

		}
		System.out.println(backFreight.getFileInfoList().size());
		try {
			result = BackFreightValidator.checkBackFreightValid(backFreight);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(BackFreightExceptionType.BACK_FREIGHT_ATTCHMENT_MORE_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}

	}

	@Test
	public void testCheckServiceRecoveryCondition() {
		BackFreightSearchCondition condition = null;
		boolean result = false;
		try {
			result = BackFreightValidator
					.checkBackFreightSearchCondition(condition);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(BackFreightExceptionType.BACK_FREIGHT_NULL_SERACH_CONDITION_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}

		condition = new BackFreightSearchCondition();
		condition.setStartDate(new Date(2011, 1, 3));
		condition.setEndDate(new Date(2012, 4, 7));
		condition.setWaybillNumbers(new ArrayList<String>());
		//condition.getWaybillNumbers().add("1");

		try {
			result = BackFreightValidator
					.checkBackFreightSearchCondition(condition);
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					BackFreightExceptionType.BACK_FREIGHT_DATE_OUT_90_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		condition.setStartDate(new Date(2012, 1, 9));
		condition.setEndDate(new Date(2012, 4, 7));
		for (int i = 0; i < 13; i++) {
			condition.getWaybillNumbers().add("1");
		}
		try {
			result = BackFreightValidator
					.checkBackFreightSearchCondition(condition);
		} catch (GeneralException e) {
			if (e.getErrorCode()
					.equals(BackFreightExceptionType.BACK_FREIGHT_WAYBILL_TOMORE_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}

	}

	@Test
	public void testCheckApplyStatus() {
		BackFreight backFreight = new BackFreight();
		backFreight.setApplyStatus(BackFreightConstant.BACK_FREIGHT_STATUS_WAIT_ACCEPT);
		BackFreightValidator.checkApplyStatus(backFreight);
		backFreight.setApplyStatus("null");
		boolean result;
		try {
			result = BackFreightValidator.checkApplyStatus(backFreight);
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					BackFreightExceptionType.BACK_FREIGHT_APPLY_ERROR_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}

	}
	@Test
	public void testCheckBackFreightExist() {
		BackFreight backFreight = null;
		boolean result;
		try {
			result = BackFreightValidator.checkBackFreightExist(backFreight);
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					BackFreightExceptionType.BACK_FREIGHT_NOT_EXIST_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}backFreight=new BackFreight();
		backFreight.setWaybillNumber(null);
		try {
			result = BackFreightValidator.checkBackFreightExist(backFreight);
		} catch (GeneralException e) {
			if (e.getErrorCode().equals(
					BackFreightExceptionType.WAYBILL_NOT_EXIST_EXCEPTION
							.getErrorCode())) {
				System.out.println(e.getErrorCode());
				assertTrue(true);
			} else {
				fail("抛出异常不正确");
			}
		}
		backFreight.setWaybillNumber("11111");
		result = BackFreightValidator.checkBackFreightExist(backFreight);
	}
}
