package com.deppon.crm.module.recompense.server.manager;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.order.domain.OaAccidentInfo;
import com.deppon.crm.module.common.shared.domain.BankView;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.Balance;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.GoodsTrans;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.OAWorkflow;
import com.deppon.crm.module.recompense.shared.domain.Payment;
import com.deppon.crm.module.recompense.shared.domain.PaymentBill;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.exception.RecompenseException;
import com.deppon.crm.module.recompense.shared.exception.RecompenseExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

public class RecompenseValidator {
	/**
	 * 
	 * @Title: validateRecompenseReportRequire
	 * @Description: 理赔出险上报校验
	 * @param @param app
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @see <a href="package.html#caseRule1_12">出险上报校验规则</a>
	 */
	public static boolean validateRecompenseReportCreate(
			RecompenseApplication app) {
		boolean result = false;
		if (app != null && isObjectNotNull(app.getRecompenseMethod())) {
			String method = app.getRecompenseMethod();
			result = validateRecompenseReportRequire(app);
			if (method.equals(Constants.NORMAL_TYPE)) {
				result = result && isObjectNotNull(app.getIssueItemList())
						&& isObjectNotNull(app.getGoodsTransList());
			}
		}
		if (!result) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_REPORT_REQUIRE_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		return result;

	}

	/**
	 * 
	 * @Title: validateRecompenseReportRequire
	 * @Description: 理赔出险上报必需填写的
	 * @param @param app
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @see <a href="package.html#caseRule1_12">出险上报校验规则</a>
	 */
	public static boolean validateRecompenseReportRequire(
			RecompenseApplication app) {
		boolean result = isObjectNotNull(app.getRecompenseMethod());
		result = result && isObjectNotNull(app.getRecompenseType());
		result = result && isObjectNotNull(app.getWaybill().getWaybillNumber());
		result = result && isObjectNotNull(app.getInsurType());
		result = result && isObjectNotNull(app.getInsurQuantity());
		result = result && isObjectNotNull(app.getDeptId());
		result = result && isObjectNotNull(app.getInsurDate());
		result = result && isObjectNotNull(app.getClaimParty());
		result = result && isObjectNotNull(app.getRecompenseAmount());
		result = result && isObjectNotNull(app.getCustomer());
		result = result && isObjectNotNull(app.getCompanyPhone());
		return result;
	}

	/**
	 * 
	 * <p>
	 * Description:判断对象是否为空<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param obj
	 * @return boolean
	 */
	public static boolean isObjectNotNull(Object obj) {
		if (null == obj) {
			return false;
		} else if (obj instanceof String) {
			return !StringUtils.isBlank((String) obj);
		} else if (obj instanceof List) {
			return !(((List) obj).isEmpty());
		} else if (obj instanceof Map) {
			return !((Map) obj).isEmpty();
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:判断是否存在<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param originalList
	 * @param addList
	 * @param deleteList
	 * @param updateList
	 * @return boolean
	 */
	public static boolean isExistListItem(List originalList, List addList,
			List deleteList, List updateList) {
		if (addList != null && addList.size() > 0) {
			return true;
		} else if (updateList != null && updateList.size() > 0) {
			return true;
		} else if (deleteList == null
				|| (deleteList != null && deleteList.size() == 0)) {
			return true;
		} else if (deleteList != null && deleteList.size() > 0) {
			return originalList.size() > deleteList.size();
		}
		return false;
	}

	/**
	 * 
	 * @Title: validateRecompenseReportRequire
	 * @Description: 理赔出险上报修改校验
	 * @param @param app
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @see <a href="package.html#caseRule1_12">出险上报校验规则</a>
	 */
	public static boolean validateRecompenseReportUpdate(
			RecompenseApplication app,
			Map<String, List<IssueItem>> issueItemModifyMap,
			Map<String, List<GoodsTrans>> goodsTransModifyMap) {
		boolean result = false;
		if (app != null && isObjectNotNull(app.getRecompenseMethod())) {
			String method = app.getRecompenseMethod();
			result = validateRecompenseReportRequire(app);
			if (method.equals(Constants.NORMAL_TYPE)) {
				List<IssueItem> issueOriginal = issueItemModifyMap
						.get(Constants.LIST_TYPE_ORIGINAL);
				List<IssueItem> issueAdd = issueItemModifyMap
						.get(Constants.LIST_TYPE_ADD);
				List<IssueItem> issueDelete = issueItemModifyMap
						.get(Constants.LIST_TYPE_DELETE);
				List<IssueItem> issueUpdate = issueItemModifyMap
						.get(Constants.LIST_TYPE_UPDATE);
				List<GoodsTrans> goodsTransOriginal = goodsTransModifyMap
						.get(Constants.LIST_TYPE_ORIGINAL);
				List<GoodsTrans> goodsTransAdd = goodsTransModifyMap
						.get(Constants.LIST_TYPE_ADD);
				List<GoodsTrans> goodsTransDelete = goodsTransModifyMap
						.get(Constants.LIST_TYPE_DELETE);
				List<GoodsTrans> goodsTransUpdate = goodsTransModifyMap
						.get(Constants.LIST_TYPE_UPDATE);
				result = result
						&& isExistListItem(issueOriginal, issueAdd,
								issueDelete, issueUpdate);
				result = result
						&& isExistListItem(goodsTransOriginal, goodsTransAdd,
								goodsTransDelete, goodsTransUpdate);
			}
		}
		if (!result) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_REPORT_REQUIRE_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		return result;

	}

	/**
	 * 
	 * @description 初步处理校验.
	 * @version 0.1 2012-3-28
	 * @date 2012-3-28
	 * @update 2012-3-28 下午3:28:40
	 */
	public static boolean validateRecompenseProcess(RecompenseApplication app,
			Map<String, List<DeptCharge>> deptChargeMap) {
		// 正常理赔金额
		Double normalAmount = app.getNormalAmount();
		// 实际理赔金额
		Double realAmount = app.getRealAmount();

		// 操作后（最终）部门费用集合
		List<DeptCharge> deptChargeList = deptChargeMap.get("deptChargeList");

		// 1、校验正常金额和实际金额必须大于0。
		if (normalAmount > 0 && realAmount > 0) {
			// 2、入部门费用，费用为整数，且费用之和等于实际赔偿金额；实际金额小于等于正常理赔与多赔金额之和。
			Double totalAmout = 0D;
			for (DeptCharge deptCharge : deptChargeList) {
				totalAmout = totalAmout + deptCharge.getAmount();
			}

			// TODO 这里使用封装类，使用“==”判断是否相等有问题
			if (totalAmout.equals(realAmount)) {
				if (realAmount <= normalAmount) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @Title: validateRecompenseBalance
	 * @Description: 冲账单检验
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws ValidateException
	 * @throws
	 * @see <a href="package.html#caseRule4">理赔付款</a>
	 */
	public static boolean validateRecompenseBalance(Double recompenseAmount,
			List<Balance> balanceList) {
		Double actualAmount = 0d;
		if (isObjectNotNull(balanceList)) {
			for (Balance balance : balanceList) {
				if (balance.getUsableAmount() != null
						&& balance.getBalanceAmount() != null
						&& balance.getBalanceAmount() > 0
						&& balance.getUsableAmount() >= balance
								.getBalanceAmount()) {
					actualAmount = actualAmount + balance.getBalanceAmount();
				} else {
					RecompenseException re = new RecompenseException(
							RecompenseExceptionType.RECOMPENSE_BALANCE_ITEM_UNASSGIN);
					throw new GeneralException(re.getErrorCode(),
							re.getMessage(), re, new Object[] {}) {
					};
				}
			}
		} else {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_BALANCE_LIST_NULL);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		if (actualAmount > recompenseAmount) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_BALANCE_TOTAL_UNASSGIN);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		return true;
	}

	/**
	 * 
	 * @Title: validateRecompensePayment
	 * @Description: 付款申请
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @see <a href="package.html#caseRule4">理赔付款</a>
	 */

	public static boolean validateRecompensePayment(PaymentBill paymentBill) {
		if (!isObjectNotNull(paymentBill)) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_PAYMENT_NULL_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		if (paymentBill.getPaymentAmount() == null
				|| paymentBill.getPaymentAmount() < 0) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_PAYMENT_AMOUNT_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		if (!validateRecompensePaymentRequire(paymentBill)) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_PAYMENT_REQUIRE_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}

		return true;
	}

	// TODO 此方法result只有两种情况 取285行的值与295行的值287行到294行无效
	/**
	 * 
	 * <p>
	 * Description:校验理赔付款<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param paymentBill
	 * @return boolean
	 */
	public static boolean validateRecompensePaymentRequire(
			PaymentBill paymentBill) {
		boolean result = true;
		result = isObjectNotNull(paymentBill.getPaymentType());
		if (paymentBill.getPaymentAmount() > 0) {
			result = result && isObjectNotNull(paymentBill.getBankAccount());
			result = result
					&& isObjectNotNull(paymentBill.getBankAccount()
							.getBankName());
			result = result
					&& isObjectNotNull(paymentBill.getBankAccount()
							.getOpenName());
			result = result
					&& isObjectNotNull(paymentBill.getBankAccount()
							.getAccount());
			result = result
					&& isObjectNotNull(paymentBill.getBankAccount()
							.getProvince());
			result = result
					&& isObjectNotNull(paymentBill.getBankAccount().getCity());
			result = result
					&& isObjectNotNull(paymentBill.getBankAccount()
							.getBranchName());
			result = result
					&& isObjectNotNull(paymentBill.getBankAccount().getMobile());
		}
		return result;
	}

	/**
	 * 
	 * @Title: canCreateRecompense
	 * @Description: 上报校验
	 * @param @param waybillMistakeList
	 * @param @param unbillMistakeList
	 * @param @param old
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @see <a href="package.html#caseRule1">出险上报</a>
	 */
	public static boolean canCreateRecompense(String recompenseMethod,
			FossWaybillInfo waybillInfo,
			List<OaAccidentInfo> waybillAccidentList,
			OaAccidentInfo unbillAccident, RecompenseApplication old) {
		if (old != null) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_RECORD_EXIST_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		if (recompenseMethod.equals(Constants.UNBILLED)) {
			if (unbillAccident != null) {
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
			/**
			 * 删除 waybillInfo != null 代码，在前面已经对waybillInfo 进行判断了 CRM-3122
			 */
			if (recompenseMethod.equals(Constants.ABNORMAL_SIGN)
					&& waybillInfo.getIsSigned() && !waybillInfo
					.getIsNormalSigned()) {
				
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
		}
	}

	/**
	 * 
	 * <p>
	 * Description:取消创建在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param waybillInfo
	 * @param waybillAccidentList
	 * @param old
	 * @return boolean
	 */
	public static boolean canCreateOnlineRecompense(
			FossWaybillInfo waybillInfo,
			List<OaAccidentInfo> waybillAccidentList, RecompenseApplication old) {
		if (old != null) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_RECORD_EXIST_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		if (waybillInfo != null
				&& (waybillInfo.getIsSigned() && !waybillInfo
						.getIsNormalSigned()) == true) {
			return true;
		}
		if (waybillAccidentList != null && waybillAccidentList.size() > 0) {
			for (OaAccidentInfo oaAccidentInfo : waybillAccidentList) {
				if (oaAccidentInfo.getAccidentType() == Constants.OA_ACCIDENT_ABNORMAL_SIGN) {
					return true;
				}
				if (oaAccidentInfo.getAccidentType() == Constants.OA_ACCIDENT_LOST_GOODS) {
					return true;
				}
			}
		}
		RecompenseException re = new RecompenseException(
				RecompenseExceptionType.RECOMPENSE_OTHER_MISTAKE_NOEXIST_ERROR);
		throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
				new Object[] {}) {
		};
	}

	/**
	 * 
	 * @Title: validateRecompenseOverpay
	 * @Description: 多赔数据校验
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @see <a href="package.html#caseRule3">金额确认</a>
	 */
	public static boolean validateRecompenseOverpay(RecompenseApplication app) {

		return true;
	}

	public static boolean validateRecompenseStatus(String oldStatus,
			List<String> statusList) {
		if (isDoableInfo(oldStatus, statusList)) {
			return true;
		} else {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_STATUS_NOTMATCH_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * Description:验证理赔状态<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param oldStatus
	 * @param matchStatus
	 * @return boolean
	 */
	public static boolean validateRecompenseStatus(String oldStatus,
			String matchStatus) {
		if (oldStatus.equals(matchStatus)) {
			return true;
		} else {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_STATUS_NOTMATCH_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * Description:校验理赔工作流是否为空<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param oaWorkflow
	 * @return boolean
	 */
	public static boolean validateOAWorkflowNull(OAWorkflow oaWorkflow) {
		if (oaWorkflow == null) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_OA_WORKFLOW_NULL);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		} else {
			return true;
		}
	}

	/**
	 * 
	 * <p>
	 * Description:校验理赔是否为空<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param app
	 * @return boolean
	 */
	public static boolean validateRecompenseNull(RecompenseApplication app) {
		if (app == null) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_APPLICATION_NULL);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		} else {
			return true;
		}
	}

	/**
	 * 
	 * <p>
	 * Description:是否包含<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param info
	 * @param infoList
	 * @return boolean
	 */
	public static boolean isDoableInfo(String info, List<String> infoList) {
		return infoList.contains(info);
	}

	public static boolean checkRecompenseStatus(
			RecompenseApplication application) {
		if (null == application) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_APPLICATION_NULL);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};

		} else if (application.getStatus().equals(Constants.STATUS_APPROVED)) {

			return true;
		} else if (application.getStatus().equals(Constants.STATUS_PAY_FAILED)) {
			return true;
		} else {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.PAYMENT_STATUS_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};

		}

	}

	/**
	 * 
	 * <p>
	 * Description:校验付款方式<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-5
	 * @return boolean
	 */
	public static boolean checkRecompense(RecompenseApplication application,
			Payment payment) {
		// 当理赔类型是未开单理赔的时候只能是汇款或者现金
		if (application.getRecompenseType().equals(Constants.UNBILLED)
				&& !(payment.getPaymentMode().equals(
						Constants.CUST_DROWMONEY_TYPE_CASH) || payment
						.getPaymentMode().equals(
								Constants.CUST_DROWMONEY_TYPE_REMIT))) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.UNBILLED_PAYMENTMODE_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		// 对于无唯一识别客户编码的理赔，客户领款方式仅可选择汇款或现金
		if (null != application.getCustomer()
				&& null == application.getCustomer().getCustNumber()
				&& !(payment.getPaymentMode().equals(
						Constants.CUST_DROWMONEY_TYPE_CASH) || payment
						.getPaymentMode().equals(
								Constants.CUST_DROWMONEY_TYPE_REMIT))) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.UNBILLED_PAYMENTMODE_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}

		return true;
	}

	public static boolean checkCasherPaymet(Payment payment,
			List<Payment> casherPaymets) {
		// 当客户领款方式为汇款或者先冲帐后汇款时，校验当前提交的账号是否为收银员账号，如若匹配上抛出异常
		if (payment.getPaymentMode()
				.equals(Constants.CUST_DROWMONEY_TYPE_REMIT)
				|| payment
						.getPaymentMode()
						.equals(Constants.CUST_DROWMONEY_TYPE_REMIT_AFTER_STRIKE_A_BALANCE)) {
			if (null != casherPaymets && casherPaymets.size() != 0) {
				for (Payment p : casherPaymets) {

					if (payment.getAccount().equals(p.getAccount())) {
						RecompenseException re = new RecompenseException(
								RecompenseExceptionType.NOT_INSERT_CUSTOMER_ACCOUNT);
						throw new GeneralException(re.getErrorCode(),
								re.getMessage(), re, new Object[] {}) {
						};
					}
				}
			}

		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:查询支行条件验证<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-12下午5:34:59
	 * @param bankView
	 * @return boolean
	 * @update 2013-1-12下午5:34:59
	 */
	public static boolean checkBank(BankView bankView) {

		if ((bankView.getAccountBank() == null || "".equals(bankView
				.getAccountBank()))
				|| bankView.getCityId() == null
				|| "".equals(bankView.getCityId())
				|| bankView.getProvinceId() == null
				|| "".equals(bankView.getProvinceId())) {

			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.NOT_NULL_BANK_PROVINCE_CITY);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		return true;
	}

}
