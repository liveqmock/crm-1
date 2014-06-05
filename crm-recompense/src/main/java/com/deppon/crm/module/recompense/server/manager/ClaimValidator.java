package com.deppon.crm.module.recompense.server.manager;

import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.order.domain.OaAccidentInfo;
import com.deppon.crm.module.common.shared.domain.ExpressPointBusinessDept;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.server.utils.ClaimConstants;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.Claim;
import com.deppon.crm.module.recompense.shared.domain.ClaimSearchCondition;
import com.deppon.crm.module.recompense.shared.domain.OnlineApply;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.Waybill;
import com.deppon.crm.module.recompense.shared.exception.ClaimException;
import com.deppon.crm.module.recompense.shared.exception.ClaimExceptionType;
import com.deppon.crm.module.recompense.shared.exception.RecompenseException;
import com.deppon.crm.module.recompense.shared.exception.RecompenseExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * 
 * <p>
 * Description:索赔验证类<br />
 * </p>
 * 
 * @title ClaimValidator.java
 * @package com.deppon.crm.module.recompense.server.manager
 * @author roy
 * @version 0.1 2013-3-1
 */
public class ClaimValidator {
	/**
	 * 
	 * <p>
	 * Description:校验凭证号<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-5
	 * @param voucherNo
	 * @param recompenseMethod
	 * @return boolean
	 */

	public static boolean validateWaybillNumber(String voucherNo,
			String recompenseMethod) {
		if (null == voucherNo) {
			ClaimException re = new ClaimException(
					ClaimExceptionType.NULL_VOUCHERNO);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};

		}

		if (Constants.LOST_GOODS.equals(recompenseMethod)
				&& (voucherNo.length() < 8 || voucherNo.length() > 10)) {
			ClaimException re = new ClaimException(
					ClaimExceptionType.ERROR_WAYBILLNUMBER_BIT);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}

		return true;
	}

	public static boolean canCreateClaim(String recompenseMethod,
			FossWaybillInfo waybillInfo,
			List<OaAccidentInfo> waybillAccidentList,
			OaAccidentInfo unbillAccident, RecompenseApplication old,
			OnlineApply onlineApply, Claim claim) {
		// 如果索赔不为空，提示该凭证号已经上报过索赔
		if (claim != null) {
			ClaimException re = new ClaimException(
					ClaimExceptionType.CLAIM_RECORD_EXIST_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};

		}
		// 如果理赔不为空，提示该凭证号已经上报过理赔
		if (old != null) {
			ClaimException re = new ClaimException(
					ClaimExceptionType.RECOMPENSE_RECORD_EXIST_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		// 如果该凭证号已经在线理赔并且状态不为“已拒绝”//已作废则提示：该凭证号已经在线理赔
		if (onlineApply.getId() != null
				&& !onlineApply.getStatus().equals(Constants.STATUS_REJECTED)
				&& !onlineApply.getStatus().equals(Constants.STATUS_INVALID)) {
			// STATUS_INVALID
			ClaimException re = new ClaimException(
					ClaimExceptionType.ONLINEAPPLY_RECORD_EXIST_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};

		}

		/**
		 * 当选择了理赔类型，所录入的凭证号必须是与所选类型一致， //即：选择的是异常签收理赔、丢货理赔，那么该凭证号必须已经上报异常签收；
		 * 选择的是未开单理赔，那么录入的必须是已经上报的差错编号。
		 */
		if (Constants.UNBILLED.equals(recompenseMethod)) {
			if (null != unbillAccident) {
				return true;
			}
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_UNBILLED_MISTAKE_NOEXIST_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		} else {
			if (waybillInfo == null) {
				RecompenseException re = new RecompenseException(
						RecompenseExceptionType.RECOMPENSE_NOEXIST_WAYBILL_ERROR);
				throw new GeneralException(re.getErrorCode(), re.getMessage(),
						re, new Object[] {}) {
				};
			}
			if (recompenseMethod.equals(Constants.ABNORMAL_SIGN)
					&& waybillInfo.getIsSigned()
					&& !waybillInfo.getIsNormalSigned()) {
				return true;
			}
			if (waybillAccidentList != null && waybillAccidentList.size() > 0) {
				boolean had = false;
				for (OaAccidentInfo oaAccidentInfo : waybillAccidentList) {

					if (oaAccidentInfo.getAccidentType() == Constants.OA_ACCIDENT_ABNORMAL_SIGN
							&& recompenseMethod.equals(Constants.ABNORMAL_SIGN)) {
						had = true;
						break;
					}
					if (oaAccidentInfo.getAccidentType() == Constants.OA_ACCIDENT_LOST_GOODS
							&& recompenseMethod.equals(Constants.LOST_GOODS)) {
						had = true;
						break;
					}
					// 接口差错无数据暂时使用 TODO
					// return true;
				}
				if (had) {
					return true;
				}
			}
			if (recompenseMethod.equals(Constants.ABNORMAL_SIGN)) {
				RecompenseException re = new RecompenseException(
						RecompenseExceptionType.RECOMPENSE_ABNORMAL_MISTAKE_NOEXIST_ERROR);
				throw new GeneralException(re.getErrorCode(), re.getMessage(),
						re, new Object[] {}) {
				};
			} else {
				RecompenseException re = new RecompenseException(
						RecompenseExceptionType.RECOMPENSE_GOODSLOST_MISTAKE_NOEXIST_ERROR);
				throw new GeneralException(re.getErrorCode(), re.getMessage(),
						re, new Object[] {}) {
				};
			}
			// RecompenseException re = new RecompenseException(
			// RecompenseExceptionType.RECOMPENSE_OTHER_MISTAKE_NOEXIST_ERROR);
			// throw new GeneralException(re.getErrorCode(), re.getMessage(),
			// re,
			// new Object[] {}) {
			// };

		}

	}

	/**
	 * 
	 * <p>
	 * Description:校验索赔部门<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-6
	 * @param currentDept
	 * @param recompenseMethod
	 * @param wb
	 * @return boolean
	 */
	public static boolean vadlidateDept(Department currentDept,
			String recompenseMethod, Waybill wb, FossWaybillInfo fwaybil) {
		if (Constants.TRANS_EXPRESS.equals(wb.getTransType())) {

			return true;
		} else {
			if (Constants.UNBILLED.equals(recompenseMethod)) {
				if (!currentDept.getId().equals(wb.getReceiveDept())) {
					ClaimException re = new ClaimException(
							ClaimExceptionType.CLAIMDEPT_ERROR);
					throw new GeneralException(re.getErrorCode(),
							re.getMessage(), re, new Object[] {}) {
					};
				}
			} else {
				if (!currentDept.getId().equals(wb.getArrivedDept())
						&& !currentDept.getId().equals(wb.getReceiveDept())) {

					ClaimException re = new ClaimException(
							ClaimExceptionType.CLAIMDEPT_ERROR);
					throw new GeneralException(re.getErrorCode(),
							re.getMessage(), re, new Object[] {}) {
					};

				}

			}

			return true;
		}
	}

	public static boolean checkClaimSearchCondition(
			ClaimSearchCondition claimSearchCondition) {
		if (null == claimSearchCondition) {
			ClaimException re = new ClaimException(
					ClaimExceptionType.NULL_SEARCH_CONDITION);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};

		} else {

			long endTime = claimSearchCondition.getEndTime().getTime();// 结束时间毫秒数
			long starTime = claimSearchCondition.getStartTime().getTime();// 开始时间毫秒数
			long daybwteen = (endTime - starTime) / (1000 * 60 * 60 * 24);// 天数差
			boolean datebetwen = (daybwteen <= 31);

			// @author 华龙 满足条件返回true

			// @author 华龙 超过31天抛出异常

			if (!datebetwen) {
				ClaimException re = new ClaimException(
						ClaimExceptionType.TOO_LONG_SEARCH_TIME);
				throw new GeneralException(re.getErrorCode(), re.getMessage(),
						re, new Object[] {}) {
				};

			}
		}

		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:校验发送内容是否为空<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-7
	 * @param reason
	 * @return boolean
	 */
	public static boolean checkReasonNotNull(String reason) {
		if (null == reason || "".equals(reason)) {
			ClaimException re = new ClaimException(
					ClaimExceptionType.NULL_SEND_REASON);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};

		}
		return true;
	}

	public static boolean canSendToAnotherDept(Claim claim) {
		if (null == claim.getArrivedDept()) {
			ClaimException re = new ClaimException(
					ClaimExceptionType.NULL_ARRIVEDDEPT_REASON);
	 	throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};

		}

		return true;
	}

	public static boolean CreateClaimDeptForExpress(User user,
			Department currentBigArea, FossWaybillInfo waybillInfo, Waybill wb,
			Department exLeaveBigArea, Department exArriveBigArea,
			boolean isLeaveExpressDept,boolean isArriveExpressDept) {
		//出发区域客服或到达区域客服
		if(isLeaveExpressDept&&isArriveExpressDept){
			if (user.getRoleids().contains(Constants.ROLE_CUSTOMSERVICE)
					&&((currentBigArea.getId().equals(exLeaveBigArea.getId()))
					||currentBigArea.getId().equals(exArriveBigArea.getId()))) {
				return true;
			}
		//出发区域客服或到达营业部
		}else if(isLeaveExpressDept&&!isArriveExpressDept){
			if(user.getEmpCode().getDeptId().getStandardCode().equals(
					waybillInfo.getLadingStationNumber())||(exLeaveBigArea!=null
					&&exLeaveBigArea.getId().equals(currentBigArea.getId()))){
				return true;
			}
		//到达区域客服或出发营业部
		}else if(!isLeaveExpressDept&&isArriveExpressDept){
			if(user.getEmpCode().getDeptId().getStandardCode().equals(
					waybillInfo.getReceiveDeptNumber())||(exArriveBigArea!=null
					&&exArriveBigArea.getId().equals(currentBigArea.getId()))){
				return true;
			}
		//到达营业部或出发营业部
		}else if(!isLeaveExpressDept&&!isArriveExpressDept){
			if(user.getEmpCode().getDeptId().getStandardCode().equals(waybillInfo
					.getReceiveDeptNumber())||user.getEmpCode().getDeptId().getStandardCode()
					.equals(waybillInfo.getLadingStationNumber())){
				return true;
			}
		}
		ClaimException re = new ClaimException(
				ClaimExceptionType.EXPRESS_DEPT_ERROR);
		throw new GeneralException(re.getErrorCode(),
				re.getMessage(), re, new Object[] {}) {
		};
	}

	public static boolean canCreateClaimDeptForExpress(User user,
			Department currentBigArea, Waybill wb, Department receiveBigArea) {

		if (!user.getRoleids().contains(Constants.ROLE_CUSTOMSERVICE)) {
			ClaimException re = new ClaimException(
					ClaimExceptionType.CURRENTNOTCUSTOMSERVICE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		} else {
			// 区域客服只能录入本大区的快递索赔单
			if (!currentBigArea.getId().equals(receiveBigArea.getId())) {
				ClaimException re = new ClaimException(
						ClaimExceptionType.NULLCURENEXPRESSBIGAREA);
				throw new GeneralException(re.getErrorCode(), re.getMessage(),
						re, new Object[] {}) {
				};
			}

		}
		return true;
	}
}
