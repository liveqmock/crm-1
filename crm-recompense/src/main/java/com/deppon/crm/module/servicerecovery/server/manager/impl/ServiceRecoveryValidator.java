package com.deppon.crm.module.servicerecovery.server.manager.impl;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.backfreight.shared.domain.BackFreight;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.servicerecovery.server.util.ServiceRecoveryConstant;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecoverySearchCondition;
import com.deppon.crm.module.servicerecovery.shared.domain.Waybill;
import com.deppon.crm.module.servicerecovery.shared.exception.ServiceRecoveryException;
import com.deppon.crm.module.servicerecovery.shared.exception.ServiceRecoveryExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * 
 * @description：服务补救校验类
 * @author 华龙
 * @version 1.0
 * @date 2012-10-29下午3:00:41
 */
public class ServiceRecoveryValidator {
	/**
	 * 
	 * @description ：校验运单号
	 * @author 华龙
	 * @version 1.0
	 * @param user
	 * @param arriveDepartment
	 * @param sendDepartment
	 * @param Waybill
	 *            waybil : 运单
	 * @date 2012-10-29 下午3:02:45
	 * @return boolean true/false
	 */
	public static String checkWaybill(Waybill waybill, int date, User user,
			Department sendExpressDepartment,Department arriveExpressDepartment,
			Employee sendPrincipal,Employee arrivePrincipal) {
		if (null == waybill) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.WAYBILL_NOT_EXIST_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};
		}
		// 如果运单未签收，则提示该单号未签收
		if (!waybill.getIsSigned()) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.WAYBILL_NOT_SIGNIN_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};
		}
		// 如果一个单号已经签收，但是异常签收，则提示
		if (waybill.getIsSigned() && !waybill.getIsNormalSigned()) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.WAYBILL_NOT_NORMAL_SIGNIN_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};
		}
		if (date > 10) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.WAYBILL_OUT_OF_APPLYDATE_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};

		}
		//如果存在快递点部的映射关系，且映射关系部门的负责人职位为点部经理就由点部上报，否则，就由营业部上报
		if (ServiceRecoveryConstant.SERVICE_RECOVERY_WAYBILL_TRANSTYPE_PACKAGE_DESC
				.equals(waybill.getTranType())) {
			//出发点部上报
			if(sendPrincipal !=null
					&&ServiceRecoveryConstant.SERVICE_RECOVERY_EXPRESS_POSITION_POINTMANAGER
						.equals(sendPrincipal.getPosition())
					&&user.getEmpCode().getDeptId().getId().
						equals(sendPrincipal.getDeptId().getId())){
						return "SHIPPER";
			}
			//出发营业部上报
			if((sendPrincipal==null||(sendPrincipal!=null&&!ServiceRecoveryConstant
					.SERVICE_RECOVERY_EXPRESS_POSITION_POINTMANAGER	.equals(
							sendPrincipal.getPosition())))&&user.getEmpCode()
							.getDeptId().getStandardCode().equals(waybill
									.getSenderDeptStandardCode())){
				return "SHIPPER";
			}
			//到达点部上报
			if(arrivePrincipal !=null
					&&ServiceRecoveryConstant.SERVICE_RECOVERY_EXPRESS_POSITION_POINTMANAGER
						.equals(arrivePrincipal.getPosition())
					&&user.getEmpCode().getDeptId().getId().
						equals(arrivePrincipal.getDeptId().getId())){
						return "CONSIGNEE";
			}
			//到达营业部
			if((arrivePrincipal==null||(arrivePrincipal!=null&&!ServiceRecoveryConstant
					.SERVICE_RECOVERY_EXPRESS_POSITION_POINTMANAGER.equals(arrivePrincipal
					.getPosition())))&&user.getEmpCode().getDeptId().getStandardCode()
					.equals(waybill.getConsigneeDeptStandardCode())){
				return "CONSIGNEE";
			}
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.SERVICE_RECOVERY_APPLY_EXPRESS_AUTH_ERROR);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};
		}
		// 校验当前登录部门是否为运单上对应的出发和到达部门,如果不是则提示
		if (user.getEmpCode().getDeptId().getStandardCode()
				.equals(waybill.getSenderDeptStandardCode())){
			return "SHIPPER";
		}
		if(user.getEmpCode().getDeptId().getStandardCode()
				.equals(waybill.getConsigneeDeptStandardCode())) {
			return "CONSIGNEE";
		}
		ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
				ServiceRecoveryExceptionType.WAYBILL_DEPT_ERROR_EXCEPTION);
		throw new GeneralException(serviceRecoveryException.getErrorCode(),
				serviceRecoveryException.getMessage(),
				serviceRecoveryException, new Object[] {}) {
		};

	}

	/**
	 * 
	 * @description ：校验是否有理赔服务补救退运费时效差错记录
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreight
	 *            backfreight, ServiceRecovery serviceRecovery,
	 *            RecompenseApplication recompense, DelayAccident delayAccident
	 * @date 2012-10-29 下午3:13:57
	 * @return boolean
	 */
	public static boolean checkAllServiceInvalid(BackFreight backfreight,
			ServiceRecovery serviceRecovery, RecompenseApplication recompense,
			Boolean isDelayAccident) {

		// 华龙 如果存在服务补救，抛出异常

		if (null != serviceRecovery) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.WAYBILL_APPLED_SERVICE_RECOVERY_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};
			// 理赔不为空 抛出异常

		}
		if (null != recompense) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.WAYBILL_APPLED_RECOMPENSE_EXCPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};
			// 差错不为空，抛出异常

		}
		if (isDelayAccident) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.WAYBILL_APPLED_DELAYACCIDENT_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};
			// 退运费不为空，抛出异常

		}
		if (null != backfreight) {

			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.WAYBILL_APPLED_BACKFREIGHT_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};

		}

		return true;
	}

	/**
	 * 
	 * @description ：校验服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param ServiceRecovery
	 *            serviceRecovery :服务补救实体
	 * @date 2012-10-29 下午3:16:01
	 * @return boolean
	 */
	public static boolean checkServiceRecoveryValid(
			ServiceRecovery serviceRecovery) {

		// 客户类型是否为空
		if (null == serviceRecovery.getCustomerType()) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.SERVICE_RECOVERY_NULL_CUSTOMER_TYPE_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};
		}
		// 减免金额是否为空
		if ((null == serviceRecovery.getReductionAmount())) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.SERVICE_RECOVERY_INPUTAMOUNT_IS_NULL_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};
		}
		// 减免金额是否超出100
		if (serviceRecovery.getReductionAmount() > 100) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.SERVICE_RECOVERY_INPUTAMOUNT_MORE_100_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};
		}
		// 减免金额是否小于开单金额
		if (serviceRecovery.getReductionAmount() > serviceRecovery
				.getWaybillAmount()) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.SERVICE_RECOVERY_INPUTAMOUNT_MORE_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};
		}
		// 破损件数为空
		if (serviceRecovery.getReductionType().equals(
				ServiceRecoveryConstant.SERVICE_RECOVERY_REDUCTION_DAMAGE)
				&& serviceRecovery.getDamagePackage() == null) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.SERVICE_RECOVERY_DAMAGEPACKAGE_IS_NULL_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};
		}
		// 破损件数是否小于总件数
		if (serviceRecovery.getReductionType().equals(
				ServiceRecoveryConstant.SERVICE_RECOVERY_REDUCTION_DAMAGE)
				&& serviceRecovery.getDamagePackage() > serviceRecovery
						.getTotalPackage()) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.SERVICE_RECOVERY_DAMAGEPACKAGE_MORE_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};
		}
		// 选择的减免类别为破损时，附件要至少一件
		if (serviceRecovery.getReductionType().equals(
				ServiceRecoveryConstant.SERVICE_RECOVERY_REDUCTION_DAMAGE)
				&& null == serviceRecovery.getFileInfoList()) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.SERVICE_RECOVERY_DAMAGE_ATTCHMENT_NOT_NULL_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};
		}

		// 附件是否超过十个
		if (serviceRecovery.getFileInfoList().size() > 10) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.SERVICE_RECOVERY_ATTCHMENT_MORE_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};
		}

		return true;

	}

	/**
	 * 
	 * @Description: 服务补救数据
	 * @author huangzhanming
	 * @param serviceRecovery
	 * @return
	 * @return boolean
	 * @date 2012-11-12 下午4:12:48
	 * @version V1.0
	 */
	public static boolean checkServiceRecoveryExist(
			ServiceRecovery serviceRecovery) {
		if (null == serviceRecovery) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.SERVICE_RECOVERY__NOT_EXIST_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};
		}
		if (null == serviceRecovery.getWaybillNumber()) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.WAYBILL_NOT_EXIST_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};

		}
		return true;
	}

	/**
	 * 
	 * @description ：校验搜索条件
	 * @author 华龙
	 * @version 1.0
	 * @param ServiceRecoverySearchCondition
	 *            recoverySearchCondition :
	 * @date 2012-10-29 下午3:18:21
	 * @return boolean
	 */
	public static boolean checkServiceRecoveryCondition(
			ServiceRecoverySearchCondition recoverySearchCondition) {
		if (null == recoverySearchCondition) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.SERVICE_RECOVERY_NULL_SERACH_CONDITION_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};

		} else if (recoverySearchCondition.getWaybillNumbers() != null
				&& recoverySearchCondition.getWaybillNumbers().size() > 0) {
			boolean waybillCount = (recoverySearchCondition.getWaybillNumbers()
					.size() <= 10);
			// @author 华龙 运单总数超过10个抛出异常
			if (!waybillCount) {
				ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
						ServiceRecoveryExceptionType.SERVICE_RECOVERY_WAYBILL_TOMORE_EXCEPTION);
				throw new GeneralException(
						serviceRecoveryException.getErrorCode(),
						serviceRecoveryException.getMessage(),
						serviceRecoveryException, new Object[] {}) {
				};
			}

		} else {

			long endTime = recoverySearchCondition.getEndDate().getTime();// 结束时间毫秒数
			long starTime = recoverySearchCondition.getStartDate().getTime();// 开始时间毫秒数
			long daybwteen = (endTime - starTime) / (1000 * 60 * 60 * 24);// 天数差
			boolean datebetwen = (daybwteen <= 90);

			// @author 华龙 满足条件返回true

			// @author 华龙 超过九十天抛出异常

			if (!datebetwen) {
				ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
						ServiceRecoveryExceptionType.SERVICE_RECOVERY_DATE_OUT_90_EXCEPTION);
				throw new GeneralException(
						serviceRecoveryException.getErrorCode(),
						serviceRecoveryException.getMessage(),
						serviceRecoveryException, new Object[] {}) {
				};
			}

		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:检验申请状态<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-6
	 * @param serviceRecovery
	 * @return boolean
	 */
	public static boolean checkApplyStatus(ServiceRecovery serviceRecovery) {
		if (!ServiceRecoveryConstant.SERVICE_RECOVERY_STATUS_WAIT_ACCEPT
				.equals(serviceRecovery.getApplyStatus())) {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.SERVICE_RECOVERY_APPLY_ERROR_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};

		}
		return true;
	}

}
