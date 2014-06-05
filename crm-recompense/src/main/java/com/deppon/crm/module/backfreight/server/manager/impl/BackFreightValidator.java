package com.deppon.crm.module.backfreight.server.manager.impl;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.backfreight.server.util.BackFreightConstant;
import com.deppon.crm.module.backfreight.shared.domain.BackFreight;
import com.deppon.crm.module.backfreight.shared.domain.BackFreightSearchCondition;
import com.deppon.crm.module.backfreight.shared.exception.BackFreightException;
import com.deppon.crm.module.backfreight.shared.exception.BackFreightExceptionType;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.Waybill;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * Description: BackFreightValidator.java Create on 2012-11-9 下午2:10:49
 * 
 * @author 华龙
 * @version 1.0
 */
public class BackFreightValidator {

	/**
	 * 
	 * @description ：校验运单号
	 * @author 华龙
	 * @version 1.0
	 * @param Waybill
	 *            waybil : 运单
	 * @date 2012-10-29 下午3:02:45
	 * @return boolean true/false
	 */
	public static boolean checkWaybill(Waybill waybill, int days, User user) {

		// TODO
		String standardCode = user.getEmpCode().getDeptId().getStandardCode();
		// 运单号为空
		if (null == waybill) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.WAYBILL_NOT_EXIST_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};
		}
		// 已经签收
		if (!waybill.getIsSigned()) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.WAYBILL_NOT_SIGNIN_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};
		}
		// 运输类型不能为空
		if (null == waybill.getTranType()) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.WAYBILL_ERROR_TRANTYPE_NULL_EXECEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};
		}
		// 当运输性质不为精准城运和精准空运的时候抛出异常
		if ((null != waybill.getTranType())
				&& (!(waybill.getTranType().equals(
						BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_CITY) || waybill
						.getTranType().equals(
								BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_AIR)))) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.WAYBILL_ERROR_TRANTYPE_EXECEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};
		}
		// 运输性质为精准空运，申请部门为收货部门
		if (waybill.getTranType().equals(
				BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_AIR)
				&& !(standardCode.equals(waybill.getSenderDeptStandardCode()))) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.WAYBILL_ERROR_APPLY_DEPT_EXECEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};
		}
		// 运输性质为精准城运,付款方式为到付时，申请部门为到达部门
		if (waybill.getTranType().equals(
				BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_CITY)
				&& waybill.getPaymentType().equals(
						BackFreightConstant.BACK_FREIGHT_PAYMENT_ARRIVE)
				&& !standardCode.equals(waybill.getConsigneeDeptStandardCode())) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.WAYBILL_ERROR_APPLY_DEPT_EXECEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};
		}
		// 运输性质为精准城运，付款方式为现金/银行卡/月结/临时欠款时，申请部门为收货部门
		if (waybill.getTranType().equals(
				BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_CITY)
				&& !waybill.getPaymentType().equals(
						BackFreightConstant.BACK_FREIGHT_PAYMENT_ARRIVE)
				&& !standardCode.equals(waybill.getSenderDeptStandardCode())) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.WAYBILL_ERROR_APPLY_DEPT_EXECEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};
		}
		// 运输性质为精准空运时，该单号的签收时间（不含出库当天）至申请时间不超过7天（含），遇法定节假日和周六周日则顺延；
		if (waybill.getTranType().equals(
				BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_AIR)
				&& (days > 7)) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.WAYBILL_OUT_OF_APPLYDATE_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};

		}
		// 运输性质为精准城运时，该单号的签收时间（不含出库当天）至申请时间不超过14天（含），遇法定节假日和周六周日则顺延；
		if (waybill.getTranType().equals(
				BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_CITY)
				&& (days > 14)) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.WAYBILL_OUT_OF_APPLYDATE_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};

		}

		return true;

	}

	/**
	 * 
	 * @description ：校验是否有理赔服务补救退运费时效差错记录
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreight
	 *            backfreight, ServiceRecovery serviceRecovery,
	 *            RecompenseApplication recompense, DelayAccident
	 *            delayAccident,BadDebt badDebt
	 * @date 2012-10-29 下午3:13:57
	 * @return boolean
	 */
	public static boolean checkAllServiceInvalid(BackFreight backfreight,
			ServiceRecovery serviceRecovery, RecompenseApplication recompense,
			Boolean isDelayAccident, Boolean IsBadDebt) {

		// 华龙 如果存在退运费，抛出异常
		if (null != backfreight) {

			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.WAYBILL_APPLED_BACKFREIGHT_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};

		}
		// 服务补救
		if (null != serviceRecovery) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.WAYBILL_APPLED_SERVICE_RECOVERY_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};

		}
		// 理赔不为空 抛出异常
		if (null != recompense) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.WAYBILL_APPLED_RECOMPENSE_EXCPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};

		}
		// 差错不为空，抛出异常
		if (isDelayAccident) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.WAYBILL_APPLED_DELAYACCIDENT_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};

		}
		// 坏账不为空，抛出异常
		if (IsBadDebt) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.WAYBILL_APPLED_BADDEBT_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};

		}

		return true;
	}

	/**
	 * 
	 * @description ：校验搜索条件
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreightSearchCondition
	 *            backFreightSearchSearchCondition :
	 * @date 2012-10-29 下午3:18:21
	 * @return boolean
	 */
	public static boolean checkBackFreightSearchCondition(
			BackFreightSearchCondition backFreightSearchCondition) {
		if (null == backFreightSearchCondition) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.BACK_FREIGHT_NULL_SERACH_CONDITION_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};

		} else if (backFreightSearchCondition.getWaybillNumbers() != null
				&& backFreightSearchCondition.getWaybillNumbers().size() > 0) {
			boolean waybillCount = (backFreightSearchCondition
					.getWaybillNumbers().size() <= 10);
			// @author 华龙 运单总数超过10个抛出异常
			if (!waybillCount) {
				BackFreightException backFreightException = new BackFreightException(
						BackFreightExceptionType.BACK_FREIGHT_WAYBILL_TOMORE_EXCEPTION);
				throw new GeneralException(backFreightException.getErrorCode(),
						backFreightException.getMessage(),
						backFreightException, new Object[] {}) {
				};
			}

		} else {
			long endTime = backFreightSearchCondition.getEndDate().getTime();// 结束时间毫秒数
			long starTime = backFreightSearchCondition.getStartDate().getTime();// 开始时间毫秒数
			long daybwteen = (endTime - starTime) / (1000 * 60 * 60 * 24);// 天数差
			boolean datebetwen = (daybwteen <= 90);

			// @author 华龙 满足条件返回true

			// @author 华龙 超过九十天抛出异常

			if (!datebetwen) {
				BackFreightException backFreightException = new BackFreightException(
						BackFreightExceptionType.BACK_FREIGHT_DATE_OUT_90_EXCEPTION);
				throw new GeneralException(backFreightException.getErrorCode(),
						backFreightException.getMessage(),
						backFreightException, new Object[] {}) {
				};
			}

		}
		return true;
	}

	/**
	 * 
	 * @description ：校验是否有理赔服务补救退运费时效差错记录
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreight
	 *            backFreight ：校验退运费
	 * 
	 * @date 2012-10-29 下午3:13:57
	 * @return boolean
	 */
	public static boolean checkBackFreightValid(BackFreight backFreight) {
		// 申请金额不能为空
		if (null == backFreight.getApplyAmount()
				|| "".equals(backFreight.getApplyAmount())) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.BACK_FREIGHT_APPLYAMOUNT_NULL_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};

		}
		// 申请金额不能小于0
		if (backFreight.getApplyAmount() < 0) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.BACK_FREIGHT_APPLYAMOUNT_ZERO_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};

		}
		// 运输性质为空运的时候 申请金额不能大于纯运费
		if (backFreight.getTranType().equals(
				BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_AIR)
				&& backFreight.getApplyAmount() > backFreight
						.getWaybillAmount()) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.BACK_FREIGHT_APPLYAMOUNT_MORE_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};

		}
		// 运输性质为城运的时候 申请金额不能大于三倍纯运费
		if (backFreight.getTranType().equals(
				BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_CITY)
				&& backFreight.getApplyAmount() > (backFreight
						.getWaybillAmount() * 3)) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.BACK_FREIGHT_APPLYAMOUNT_THREEMORE_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};
		}
		// 附件最多为10个
		if (backFreight.getFileInfoList().size() > 10) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.BACK_FREIGHT_ATTCHMENT_MORE_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};
		}

		return true;
	}

	/**
	 * 
	 * @Description: 退运费数据校验
	 * @author 华龙
	 * @param serviceRecovery
	 * @return
	 * @return boolean
	 * @date 2012-11-12 下午4:12:48
	 * @version V1.0
	 */
	public static boolean checkBackFreightExist(BackFreight backFreight) {
		// 退运费为空
		if (null == backFreight) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.BACK_FREIGHT_NOT_EXIST_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};
		}
		// 运单号为空
		if (null == backFreight.getWaybillNumber()) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.WAYBILL_NOT_EXIST_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};

		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:校验退运费状态<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-6
	 * @param backFreight
	 *            void
	 */

	public static boolean checkApplyStatus(BackFreight backFreight) {
		// 校验劳务费状态
		if (!BackFreightConstant.BACK_FREIGHT_STATUS_WAIT_ACCEPT
				.equals(backFreight.getApplyStatus())) {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.BACK_FREIGHT_APPLY_ERROR_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};
		}
		return true;
	}

}
