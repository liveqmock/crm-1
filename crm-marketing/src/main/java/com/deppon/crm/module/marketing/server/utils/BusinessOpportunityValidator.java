package com.deppon.crm.module.marketing.server.utils;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.marketing.shared.domain.BoCustomerCondition;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunity;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportuntiyConstants;
import com.deppon.crm.module.marketing.shared.exception.BusinessOpportunityException;
import com.deppon.crm.module.marketing.shared.exception.BusinessOpportunityExceptionType;

public class BusinessOpportunityValidator {

	/**
	 * @discription 商机查询范围必须在一年以内
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:14:51
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static boolean isBetweenYear(Date beginDate, Date endDate) {
		// 起始日期或截止日期为空抛出异常
		if (null == beginDate || null == endDate) {
			throw new BusinessOpportunityException(
					BusinessOpportunityExceptionType.queryRangeDateIsNull);
		}

		Calendar c = Calendar.getInstance();
		c.setTime(beginDate);
		c.add(Calendar.YEAR, 1);

		// 判断日期是否超过一年
		if (!beginDate.after(endDate) && endDate.compareTo(c.getTime()) <= 0) {
			return true;
		} else {
			throw new BusinessOpportunityException(
					BusinessOpportunityExceptionType.queryRangeMoreOneYear);
		}
	}

	/**
	 * @discription 商机的预计成功时间必须是一年内
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:17:08
	 * @param createDate
	 * @param doneDate
	 * @return
	 */
	public static boolean isBetweenSixMonth(Date createDate, Date doneDate) {
		// 如创建为空，则以当前时间进行比较
		if (null == createDate) {
			createDate = new Date();
		}
		// 如预计完成时间为空抛异常
		if (null == doneDate) {
			throw new BusinessOpportunityException(
					BusinessOpportunityExceptionType.expectSuccessDateMustSixMonth);
		}

		Calendar c = Calendar.getInstance();
		c.setTime(createDate);
		c.add(Calendar.MONTH, 6);

		// 如果预计成功时间超过6个月则抛异常
		if (createDate.before(doneDate) && doneDate.compareTo(c.getTime()) <= 0) {
			return true;
		} else {
			throw new BusinessOpportunityException(
					BusinessOpportunityExceptionType.expectSuccessDateMustSixMonth);
		}
	}

	/**
	 * @discription 检查商机信息内容
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:19:20
	 * @param bo
	 * @return
	 */
	public static boolean checkBusinessOpportunityCreateInfo(
			BusinessOpportunity bo) {
		// 录入商机名称，文本格式，字符≤50，包含特殊字符以及非法字符空格等。必填项
		if (StringUtils.isEmpty(bo.getBoName())
				|| StringUtils.length(bo.getBoName()) > 50) {
			throw new BusinessOpportunityException(
					BusinessOpportunityExceptionType.boNameTooLong);
		}
		// 商机描述，文本格式，字符≤200，包含特殊字符以及非法字符空格等。必填项
		if (StringUtils.isEmpty(bo.getBoDesc())
				|| StringUtils.length(bo.getBoDesc()) > 200) {
			throw new BusinessOpportunityException(
					BusinessOpportunityExceptionType.boDescTooLong);
		}
		// 预计成功几率范围：0%~100%，预计成功几率可修改，只有商机的执行人可以修改商机的预计成功几率。默认为空，必填项
		if (bo.getExpectSuccessOdds() < 0 || bo.getExpectSuccessOdds() > 100) {
			throw new BusinessOpportunityException(
					BusinessOpportunityExceptionType.expectSuccessOddsOverRange);
		}
		// 预计发货金额为空，必填项，金额必须≥10000
		if (bo.getExpectDeliveryAmount() < 10000) {
			throw new BusinessOpportunityException(
					BusinessOpportunityExceptionType.expectDeliveryAmountOverRange);
		}
		return isBetweenSixMonth(bo.getCreateTime(), bo.getExpectSuccessDate());

	}

	/**
	 * @discription 检查商机客户查询条件是否填写
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:19:45
	 * @param condition
	 * @return
	 */
	public static boolean checkBoCustomerCondition(BoCustomerCondition condition) {
		if (condition != null) {
			if (StringUtils.isEmpty(condition.getDeptId())
					&& StringUtils.isEmpty(condition.getCustNumber())
					&& StringUtils.isEmpty(condition.getCustName())
					&& StringUtils.isEmpty(condition.getLinkmanName())
					&& StringUtils.isEmpty(condition.getMobile())
					&& StringUtils.isEmpty(condition.getPhone())
					&& StringUtils.isEmpty(condition.getCustResource())
					&& StringUtils.isEmpty(condition.getCustCategory())
					&& StringUtils.isEmpty(condition.getPurpose())
					&& StringUtils.isEmpty(condition.getDevelopStage())
					&& StringUtils.isEmpty(condition.getFirstTrade())
					&& StringUtils.isEmpty(condition.getSecondTrade())) {
				throw new BusinessOpportunityException(
						BusinessOpportunityExceptionType.queryCustConditionIsNull);
			} else if (StringUtils.isEmpty(condition.getDeptId())
					&& StringUtils.isEmpty(condition.getCustNumber())
					&& StringUtils.isEmpty(condition.getMobile())
					&& (StringUtils.isEmpty(condition.getLinkmanName()) || StringUtils
							.isEmpty(condition.getPhone()))) {
				throw new BusinessOpportunityException(
						BusinessOpportunityExceptionType.queryCustMustConditionIsNull);
			} else {
				return true;
			}
		} else {
			throw new BusinessOpportunityException(
					BusinessOpportunityExceptionType.queryCustConditionIsNull);
		}
	}

	/**
	 * @discription 检查商机阶段的内容是否完整
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:20:18
	 * @param bo
	 * @param lastStep
	 * @return
	 */
	public static boolean checkBusinessOpportunityInfo(BusinessOpportunity bo,
			String lastStep) {
		if (StringUtils.equals(lastStep,
				BusinessOpportuntiyConstants.BO_STEP_CONTACT)) {
			// 初步接触商机阶段判断
			return checkBusinessOpportunityStepContact(bo);
		} else if (StringUtils.equals(lastStep,
				BusinessOpportuntiyConstants.BO_STEP_ANALYZE)) {
			// 需求分析商机阶段判断
			return checkBusinessOpportunityStepAnalyze(bo);
		} else if (StringUtils.equals(lastStep,
				BusinessOpportuntiyConstants.BO_STEP_SCHEME)) {
			// 制定方案商机阶段判断
			return checkBusinessOpportunityStepScheme(bo);
		} else if (StringUtils.equals(lastStep,
				BusinessOpportuntiyConstants.BO_STEP_OFFER)) {
			// 报价/竞标商机阶段判断
			return checkBusinessOpportunityStepOffer(bo);
		} else if (StringUtils.equals(lastStep,
				BusinessOpportuntiyConstants.BO_STEP_DELIVER)) {
			// 持续发货商机阶段判断
			return checkBusinessOpportunityStepDeliver(bo);
		}
		return false;
	}

	/**
	 * @discription 初步接触商机阶段判断
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:22:17
	 * @param bo
	 * @return
	 */
	public static boolean checkBusinessOpportunityStepContact(
			BusinessOpportunity bo) {
		return true;
	}

	/**
	 * @discription 需求分析商机阶段判断
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:22:25
	 * @param bo
	 * @return
	 */
	public static boolean checkBusinessOpportunityStepAnalyze(
			BusinessOpportunity bo) {
		// 检查所需字段
		if (checkBusinessOpportunityStepContact(bo)
				&& StringUtils.isNotEmpty(bo.getCustomer().getCustFirstType())
				&& StringUtils.isNotEmpty(bo.getCustomer().getCustSecondType())
				&& StringUtils.isNotEmpty(bo.getIsBidProject())
				&& StringUtils.isNotEmpty(bo.getIsBOConfirm())
				&& StringUtils.equals(bo.getIsBOConfirm(), "1")) {
			return true;
		} else {
			throw new BusinessOpportunityException(
					BusinessOpportunityExceptionType.stepAnalyzeIsNull);
		}
	}

	/**
	 * @discription 制定方案商机阶段判断
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:22:34
	 * @param bo
	 * @return
	 */
	public static boolean checkBusinessOpportunityStepScheme(
			BusinessOpportunity bo) {
		// 检查所需字段
		if (checkBusinessOpportunityStepAnalyze(bo)
				&& StringUtils.isNotEmpty(bo.getCustomerDemandDesc())) {
			return true;
		} else {
			throw new BusinessOpportunityException(
					BusinessOpportunityExceptionType.stepSchemeIsNull);
		}
	}

	/**
	 * @discription 报价/竞标商机阶段判断
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:22:59
	 * @param bo
	 * @return
	 */
	public static boolean checkBusinessOpportunityStepOffer(
			BusinessOpportunity bo) {
		// 检查所需字段
		if (checkBusinessOpportunityStepScheme(bo)
				&& StringUtils.isNotEmpty(bo.getSolutionDesc())) {
			return true;
		} else {
			throw new BusinessOpportunityException(
					BusinessOpportunityExceptionType.stepOfferIsNull);
		}
	}

	/**
	 * @discription 持续发货商机阶段判断
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:22:48
	 * @param bo
	 * @return
	 */
	public static boolean checkBusinessOpportunityStepDeliver(
			BusinessOpportunity bo) {
		// 检查所需字段
		if (checkBusinessOpportunityStepOffer(bo)
				&& StringUtils.isNotEmpty(bo.getCompetitiveInfo())) {
			return true;
		} else {
			throw new BusinessOpportunityException(
					BusinessOpportunityExceptionType.stepDeliverIsNull);
		}
	}

}
