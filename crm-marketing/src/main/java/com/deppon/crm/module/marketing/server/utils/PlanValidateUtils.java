package com.deppon.crm.module.marketing.server.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.PlanDevelopCondition;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.shared.exception.PlanException;
import com.deppon.crm.module.marketing.shared.exception.PlanExceptionType;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * <p>
 * 工具类<br />
 * </p>
 * 
 * @title ValidateUtils.java
 * @package com.deppon.crm.module.marketing.server.utils
 * @author 苏玉军
 * @version 0.1 2012-3-24
 */

public class PlanValidateUtils {
	/**
	 * <p>
	 * 判断两个日期是否为同一天<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-24
	 * @param date1
	 * @param date2
	 * @return boolean
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		return DateUtils.isSameDay(date1, date2);
	}

	/**
	 * 
	 * <p>
	 * 验证是否有重复的主题<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-24
	 * @param newPlan
	 *            当前开发计划
	 * @param developedPlanList
	 *            历史开发记录
	 * @param deptId
	 *            部门
	 * @return boolean
	 */
	public static boolean validateNewPlan(Plan newPlan, List<Plan> planList) {
		// 计划列表为空
		if (planList == null || planList.size() == 0) {
			return true;
		}

		// 遍历该部门的所有计划，验证是否有重复的计划
		for (Plan plan : planList) {
			// 计划主题不为空
			if (plan.getTopic() != null) {
				// 判定是否存在
				if (plan.getTopic().equals(newPlan.getTopic())
						&& (!plan.getId().equals(newPlan.getId()))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * 判断客户列表是否为空<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-26
	 * @param customerList
	 * @return boolean
	 */
	public static boolean isCustomerListNull(String[] customerList) {
		// 客户列表时否为空
		if (customerList == null || customerList.length < 1) {
			// 空
			return true;
		}
		// 不为空
		return false;
	}

	/**
	 * 
	 * <p>
	 * 两日期值进行比较<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-26
	 * @param current
	 * @param start
	 * @return boolean
	 */
	public static int isSameDate(Date current, Date start) {
		// 比较
		return DateUtils.truncatedCompareTo(current, start, Calendar.DATE);

	}

	/**
	 * 
	 * <p>
	 * 验证VO<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param vo
	 * @return boolean
	 */
	public static boolean canSearchMemberList(CustomerVo vo) {
		ScheduleValiateUtils.isQueryConditonIsNull(vo);
		// 开始时间
		Date d1 = vo.getBeginTime();
		// 结束时间
		Date d2 = vo.getOverTime();
		// 进行日期对比
		int result = DateUtils.truncatedCompareTo(DateUtils.addYears(d1, 1),
				d2, Calendar.DATE);
		if (result < 0) {
			// 查询日期大于1年
			throw new PlanException(PlanExceptionType.dateRangeTooLarge);
		}
		return true;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-3-26
	 * @param d1
	 * @param d2
	 * @return int 1：d1晚于d2 0：d1等于d2 -1：d1早于d2
	 */
	public static int compareToDate(Date d1, Date d2) {
		// compare
		return DateUtils.truncatedCompareTo(d1, d2, Calendar.DATE);
	}

	/**
	 * 
	 * <p>
	 * 计划的日程状态是否全是已指派<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-26
	 * @param plan
	 * @return boolean
	 */
	public static boolean isScheduleStatusOfPlan(List<Schedule> scheList) {
		// 循环
		for (Schedule sche : scheList) {
			// 不是已指派
			if (!sche.getStatus().equals(MarketingConstance.SCHEDULE_ASSIGNED)) {
				// true
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * 计划制定人和登陆人是否一致<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-26
	 * @param plan
	 * @param userId
	 * @return boolean
	 */
	public static boolean isSelfPlan(Plan plan, String userId) {
		// 计划创建人与当前用户是否一致
		if (!plan.getCreateUserId().equals(userId)) {
			// 不等，true
			return true;
		}
		// false
		return false;
	}

	/**
	 * 验证时间间隔
	 * 
	 * @param condition
	 * @return
	 */
	private static boolean isPassTimeLimit(PlanDevelopCondition condition) {
		// 计划结束时间
		Date planOverDate = condition.getPlanOverDate();
		// 计划开始时间
		Date planStartDate = condition.getPlanStartDate();
		// Calendar实例
		GregorianCalendar cs = (GregorianCalendar) Calendar.getInstance();
		// 开始时间
		cs.setTime(planStartDate);
		GregorianCalendar co = (GregorianCalendar) Calendar.getInstance();
		// 结束时间
		co.setTime(planOverDate);
		// 计算间隔月份是否大于3个月
		if (monthCount(cs, co) > 3) {
			return true;
		}
		if (monthCount(cs, co) > 3)
			throw new PlanException(
					PlanExceptionType.planTimeTooLargeThreeMonth);
		return false;

	}

	/**
	 * 验证开发计划条件
	 * 
	 * @param condition
	 * @return
	 */
	private static boolean isNullDevelopPlan(PlanDevelopCondition condition) {
		// 计划结束时间
		Date planOverDate = condition.getPlanOverDate();
		// 计划开始时间
		Date planStartDate = condition.getPlanStartDate();

		// 都为空
		if (null == planOverDate || null == planStartDate) {
			return true;
		}

		// 结束时间在 开始时间之前，不合法
		if (planOverDate.before(planStartDate)) {
			return true;
		}
		return false;
	}

	/**
	 * 验证计划开发查询条件
	 * 
	 * @param condition
	 * @return
	 */
	public static boolean validateDevelopPlanSearch(
			PlanDevelopCondition condition) {
		// 判断计划是否为空
		if (isNullDevelopPlan(condition)) {
			return false;
		}
		// 验证时间是否合法
		if (isPassTimeLimit(condition)) {
			return false;
		}
		return true;

	}

	/**
	 * 计算间隔月
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private static int monthCount(GregorianCalendar start, GregorianCalendar end) {
		// 计算两日期间相隔约束
		int months = end.get(Calendar.MONTH) - start.get(Calendar.MONTH) + 12
				* (end.get(Calendar.YEAR) - start.get(Calendar.YEAR))
				+ (end.get(Calendar.DATE) - start.get(Calendar.DATE) + 1)
				/ end.get(Calendar.DAY_OF_MONTH);
		return months;
	}

	/**
	 * 
	 * <p>
	 * 检查操作ID不能为空
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param ids
	 * @return boolean
	 */
	public static boolean checkDeleteIdsIsNotNull(String[] ids) {
		// ids为空，或为空数组
		if (ids == null || ids.length == 0) {
			// 计划id不能为空
			throw new PlanException(PlanExceptionType.planIdCannotBeNull);
		}
		return true;
	}

	/**
	 * <p>
	 * Description: 是否可删除计划<br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-4-14
	 * @param ids
	 * @return boolean
	 */
	public static boolean canDeletePlan(List<Plan> plans, String userId) {
		for (Plan p : plans) {
			// 计划创建人与当前操作人不一致
			if (!p.getCreateUserId().equals(userId)) {
				throw new PlanException(
						PlanExceptionType.cannotDeleteOtherPeoplePlan);
			}
			// 无法操作已完成/已过期的计划
			if (p.getStatus().equals(MarketingConstance.FINISHED)) {
				throw new PlanException(PlanExceptionType.canNotUpdDelPlan);
			}
			// 已过期的无法删除
			if (p.getStatus().equals(MarketingConstance.OVERDUE)) {
				throw new PlanException(PlanExceptionType.canNotUpdOverduePlan);
			}
			// 执行中的不能删除
			if (p.getStatus().equals(MarketingConstance.EXECUTING)) {
				throw new PlanException(
						PlanExceptionType.canNotUpdExecutingPlan);
			}
			// 验证日程状态
			List<Schedule> scheList = p.getScheduleList();
			if (scheList != null && scheList.size() > 0) {
				// 只要有一个计划的状态不等于10 ， 那么就不能批量删除
				for (Schedule s : scheList) {
					// 状态为10
					if (!s.getStatus().equals("10")) {
						throw new PlanException(
								PlanExceptionType.errorPlaneState);
					}
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:比较两个时间，仅比较日期，不包括时分秒<br />
	 * </p>
	 * 
	 * @author zhujunyong
	 * @version 0.1 Apr 10, 2012
	 * @param d1
	 * @param d2
	 * @return int
	 */
	public static int dateCompareTo(Date d1, Date d2) {
		// compare
		return compareToDate(d1, d2);
	}

	/**
	 * 
	 * <p>
	 * 计划验证<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param plan
	 * @param customerList
	 * @param planList
	 * @param deptId
	 * @return boolean
	 */
	public static boolean isPassedValidate(Plan plan, String[] customerList,
			List<Plan> planList, String deptId) {
		// 未选择执行部门，不能创建
		if (StringUtils.isEmpty(plan.getExecdeptid())) {
			// 执行部门空异常
			throw new PlanException(PlanExceptionType.execDeptIsNull);
		}
		// 计划开始时间
		Date start = plan.getBeginTime();
		// 计划结束时间
		Date end = plan.getEndTime();
		// 开始日期不能比结束日期大
		if (start == null || end == null) {
			// 不能为空异常
			throw new PlanException(PlanExceptionType.startDateBGEndDate);
		}
		// 开始时间大于结束日期
		if (PlanValidateUtils.compareToDate(start, end) >= 0) {
			// 开始时间大于结束日期
			throw new PlanException(PlanExceptionType.startDateBGEndDate);
		}
		// 计划时限范围最大1个月
		if (!MonitorPlanValidateUtils.comparisonDateByField(start, end,
				Calendar.MONTH, 1)) {
			// 计划时间范围太大异常
			throw new PlanException(PlanExceptionType.planTimeTooLarge, 1);
		}
		// 未选择客户，不能创建
		if (PlanValidateUtils.isCustomerListNull(customerList)) {
			// 客户未选择
			throw new PlanException(PlanExceptionType.customerListCannotBeNull);
		}

		// 修改时条件容许
		Date now = new Date();
		// 计划开始时间只能晚于今天
		if (!plan.getPlanType().equals(
				MarketingConstance.FIXEDPLAN_MONTHLY_TYPE)
				&& !plan.getPlanType().equals(
						MarketingConstance.FIXEDPLAN_ACTIVITY_TYPE)
				&& !plan.getPlanType().equals(
						MarketingConstance.FIXEDPLAN_WEEKLY_TYPE)) {
			// 当期日期大于计划开始日期
			if (PlanValidateUtils.compareToDate(now, start) >= 0) {
				// 当期日期大于计划开始日期
				throw new PlanException(
						PlanExceptionType.startCnnotMoreThanCurrent);
			}
		}

		// 检查计划主题是否重复
		if (!PlanValidateUtils.validateNewPlan(plan, planList)) {
			// 已存在主题异常
			throw new PlanException(PlanExceptionType.existsPlanTheme);
		}
		return true;
	}

	/**
	 * <p>
	 * Description: 计划修改规则<br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-12-7
	 * @param plan
	 * @param oldPlan
	 * @param planList
	 * @param scheList
	 * @param userId
	 * @param managerId
	 * @return boolean
	 */
	public static boolean canUpdatePlan(Plan plan, Plan oldPlan,
			List<Plan> planList, List<Schedule> scheList,
			String[] userAndManagerId, String[] contactIds, Employee emp,
			String bisYd, String[] custList) {
		String userId = userAndManagerId[0];
		String managerId = userAndManagerId[1];
		// String[] custs = custList;
		// if(MarketingConstance.MAINTAIN_TYPE.equals(oldPlan.getPlanType())){
		// custs = contactIds;
		// }
		if (plan == null) {
			throw new PlanException(PlanExceptionType.doNotExistPlan);
		}
		// 是否异动或离职
		boolean isOutOrLeave = isOutOrLeave(emp, oldPlan);
		if (!isOutOrLeave) {
			plan.setStatus(null);
		}
		// 有限进行固定计划的判断
		// 固定计划 或者 离职异动 仅可修改执行人
		if (oldPlan.getPlanType().equals(
				MarketingConstance.FIXEDPLAN_MONTHLY_TYPE)
				|| oldPlan.getPlanType().equals(
						MarketingConstance.FIXEDPLAN_WEEKLY_TYPE)
				|| oldPlan.getPlanType().equals(
						MarketingConstance.FIXEDPLAN_ACTIVITY_TYPE)) {
			checkModify(oldPlan, scheList, isOutOrLeave, plan, userId,
					managerId);
			// 判断联系人是否已更改
			if (!checkContactIdsNotChange(contactIds, scheList)) {
				throw new PlanException(PlanExceptionType.canNotChangeContact);
			}
			return true;
		}

		// 对于异动或离职 只能修改执行人
		if (!isOutOrLeave) {
			checkModify(oldPlan, scheList, isOutOrLeave, plan, userId,
					managerId);
			// 判断联系人是否已更改
			if (!checkCustIdsNotChange(custList, scheList)) {
				throw new PlanException(PlanExceptionType.canNotChangeContact);
			}
			return true;
		}
		// 无法操作已完成
		if (oldPlan.getStatus().equals(MarketingConstance.FINISHED)) {
			throw new PlanException(PlanExceptionType.canNotUpdDelPlan);
		}
		// 无法操作已过期的计划
		if (oldPlan.getStatus().equals(MarketingConstance.OVERDUE)) {
			throw new PlanException(PlanExceptionType.canNotUpdOverduePlan);
		}
		// 无法修改执行中的计划
		if (oldPlan.getStatus().equals(MarketingConstance.EXECUTING)) {
			if (isOutOrLeave) {
				throw new PlanException(
						PlanExceptionType.canNotUpdExecutingPlan);
			}
		}

		// 计划制定人和登陆人员是否一致
		if (PlanValidateUtils.isSelfPlan(oldPlan, userId)) {
			// 不一致，如果登录人不是经理的话，不可以修改
			// 执行人 离职或异动 计划制定人 也离职异动 的情况下，经理仍可修改该计划的执行人。
			if (isOutOrLeave && !userId.equals(managerId)) {
				throw new PlanException(PlanExceptionType.isNotSelfDevelopPlan);
			}

		}

		Date start = plan.getBeginTime();
		Date end = plan.getEndTime();
		if (start == null || end == null) {
			throw new PlanException(PlanExceptionType.startDateBGEndDate);
		}

		if (PlanValidateUtils.compareToDate(start, end) >= 0) {
			throw new PlanException(PlanExceptionType.startDateBGEndDate);
		}

		// 计划时限范围最大1个月
		if (!MonitorPlanValidateUtils.comparisonDateByField(start, end,
				Calendar.MONTH, 1)) {
			throw new PlanException(PlanExceptionType.planTimeTooLarge, 1);
		}
		// 当期日期大于计划开始日期
		// 修改时条件容许
		Date now = new Date();
		// 计划开始时间只能晚于今天
		if (!"1".equals(bisYd)) {
			if (!plan.getPlanType().equals(
					MarketingConstance.FIXEDPLAN_MONTHLY_TYPE)
					&& !plan.getPlanType().equals(
							MarketingConstance.FIXEDPLAN_ACTIVITY_TYPE)
					&& !plan.getPlanType().equals(
							MarketingConstance.FIXEDPLAN_WEEKLY_TYPE)) {
				if (PlanValidateUtils.compareToDate(now, start) >= 0) {
					throw new PlanException(
							PlanExceptionType.startCnnotMoreThanCurrent);
				}
			}
		}

		// 检查计划主题是否重复
		if (!PlanValidateUtils.validateNewPlan(plan, planList)) {
			throw new PlanException(PlanExceptionType.existsPlanTheme);
		}

		// 日程状态是否全为已指派
		if (PlanValidateUtils.isScheduleStatusOfPlan(scheList)) {
			// 日程状态已变更，无法修改该计划
			if (isOutOrLeave) {
				throw new PlanException(
						PlanExceptionType.notAllOfStatusIsAssigned);
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Description: 验证计划是否被修改<br />
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-3-20
	 * @return void
	 */
	public static void checkModify(Plan oldPlan, List<Schedule> scheList,
			boolean isOutOrLeave, Plan plan, String userId, String managerId) {
		/**
		 * 只要计划未过期且计划内所有日程状态都为"已指派"时允许营业部经理修改 计划执行人（除执行人以外的信息不可修改），否则不允许修改。
		 **/
		if (oldPlan.getStatus().equals(MarketingConstance.OVERDUE)) {
			throw new PlanException(PlanExceptionType.canNotUpdOverduePlan);
		}

		// 判断日程状态 是否全都为已指派
		if (isAllScheduleStatusNotAssigned(scheList)) {
			if (isOutOrLeave) {
				throw new PlanException(
						PlanExceptionType.notAllOfStatusIsAssigned);
			}
		}

		// 检查计划内容是否被修改
		if (!DateUtils.isSameDay(plan.getBeginTime(), oldPlan.getBeginTime())
				|| !DateUtils
						.isSameDay(plan.getEndTime(), oldPlan.getEndTime())
				|| !oldPlan.getTopic().equals(plan.getTopic())) {
			// 开始时间、结束时间、执行部门、计划主题、计划描述 均不可变更
			throw new PlanException(PlanExceptionType.canModifyFixedPlan);
		}
		if (StringUtils.isNotEmpty(oldPlan.getActivedesc())) {
			if (!oldPlan.getActivedesc().equals(plan.getActivedesc())) {
				throw new PlanException(PlanExceptionType.canModifyFixedPlan);
			}
		}
		if (!StringUtils.equals(
				oldPlan.getProjectType() == null ? "" : oldPlan
						.getProjectType(), plan.getProjectType() == null ? ""
						: plan.getProjectType())) {
			throw new PlanException(PlanExceptionType.canModifyFixedPlan);
		}
		if (!StringUtils.equals(
				oldPlan.getSurveyId() == null ? "" : oldPlan.getSurveyId(),
				plan.getSurveyId() == null ? "" : plan.getSurveyId())) {
			throw new PlanException(PlanExceptionType.canModifyFixedPlan);
		}

		// 只有经理可重新制定计划执行人
		if (!userId.equals(managerId)) {
			throw new PlanException(
					PlanExceptionType.onlyDeptManagerUpdateFixPlan);
		}

		// 验证是否更改了执行人
		if (oldPlan.getExecuserid().equals(plan.getExecuserid())) {
			throw new PlanException(
					PlanExceptionType.notModifyExePersonWithFixPlan);
		}
		// 验证是否修改了计划类别
	}

	/**
	 * <p>
	 * Description: 验证联系人是否已修改<br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-12-7
	 * @param contactIds
	 * @return boolean
	 */
	public static boolean checkContactIdsNotChange(String[] contactIds,
			List<Schedule> schList) {
		if (contactIds.length != schList.size()) {
			// 大小不同——联系人已被修改
			return false;
		}
		List<String> tmp = Arrays.asList(contactIds);
		// 循环比较日程联系人
		for (Schedule sch : schList) {
			// 没有包含
			if (!tmp.contains(sch.getContactId())) {
				// 已被修改
				return false;
			}
		}
		// 没有修改
		return true;
	}

	/**
	 * <p>
	 * Description: 验证客户人是否已修改<br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-12-7
	 * @param contactIds
	 * @return boolean
	 */
	public static boolean checkCustIdsNotChange(String[] contactIds,
			List<Schedule> schList) {
		if (contactIds.length != schList.size()) {
			// 大小不同——联系人已被修改
			return false;
		}
		List<String> tmp = Arrays.asList(contactIds);
		// 循环比较日程联系人
		for (Schedule sch : schList) {
			if (!tmp.contains(sch.getCustId())) {
				return false;
			}
		}
		return true;
	}

	public static boolean isAllScheduleStatusNotAssigned(List<Schedule> list) {
		// 逐个判断
		for (Schedule sche : list) {
			if (!sche.getStatus().equals(MarketingConstance.SCHEDULE_ASSIGNED)) {
				// true 不全是已指派
				return true;
			}
		}
		// false 全部是已指派
		return false;
	}

	public static boolean canSearchPlanDetail(String id) {
		return StringUtils.isNotEmpty(id);
	}

	/**
	 * <p>
	 * Description: 校验计划执行人是否已经离职<br />
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-02-19
	 * @param plan
	 * @return boolean
	 */
	public static boolean checkDemission(Employee emp) {
		// 员工为空 员工状态为空
		if (null == emp || null == emp.getStatus()) {
			return false;
		}
		// 校验计划执行人是否已经离职
		if (!emp.getStatus()) {
			// 此员工已经离职
			return true;
		}
		return false;
	}

	/**
	 * <p>
	 * Description: 校验计划执行人是否已经异动<br />
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-02-19
	 * @param plan
	 * @return boolean
	 */
	public static boolean checkExecuteEmpMove(Employee emp, Plan plan) {
		// 员工为空 部门为空 部门状态为空
		if (null == emp || null == emp.getDeptId()
				|| null == emp.getDeptId().getId()) {
			return false;
		}
		// 开发计划为空 开发计划执行人为空
		if (null == plan || null == plan.getExecdeptid()) {
			return false;
		}
		// 校验计划执行人是否已经异动
		if (!emp.getDeptId().getId().equals(plan.getExecdeptid())) {
			// 此员工已经异动
			return true;
		}
		return false;
	}

	/**
	 * <p>
	 * Description: 校验计划执行人是否已经异动 或 离职<br/>
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-02-19
	 * @param plan
	 * @return boolean
	 */
	public static boolean isOutOrLeave(Employee emp, Plan plan) {
		// false 表示 已经 离职或 异动
		return !checkDemission(emp) && !checkExecuteEmpMove(emp, plan);
	}

	/**
	 * <p>
	 * Description: 计划修改规则<br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-12-7
	 * @param plan
	 * @param oldPlan
	 * @param planList
	 * @param scheList
	 * @param userId
	 * @param managerId
	 * @return boolean
	 */
	public static boolean canUpdatePlan(Plan plan, List<Schedule> scheList,
			boolean isOutOrLeave) {
		// 有限进行固定计划的判断
		// 固定计划仅可修改执行人
		if (plan.getPlanType()
				.equals(MarketingConstance.FIXEDPLAN_MONTHLY_TYPE)
				|| plan.getPlanType().equals(
						MarketingConstance.FIXEDPLAN_WEEKLY_TYPE)
				|| plan.getPlanType().equals(
						MarketingConstance.FIXEDPLAN_ACTIVITY_TYPE)) {
			/**
			 * 只要计划未过期且计划内所有日程状态都为"已指派"时允许营业部经理修改 计划执行人（除执行人以外的信息不可修改），否则不允许修改。
			 **/
			// 判断日程状态 是否全都为已指派
			if (isAllScheduleStatusNotAssigned(scheList)) {
				if (isOutOrLeave) {
					PlanException e = new PlanException(
							PlanExceptionType.notAllOfStatusIsAssigned);
					throw new GeneralException(e.getErrorCode(),
							e.getMessage(), e, new Object[] {}) {
					};
				}
			}

			// 无法修改执行中的计划
			if (plan.getStatus().equals(MarketingConstance.EXECUTING)) {
				if (isOutOrLeave) {
					PlanException e = new PlanException(
							PlanExceptionType.canNotUpdExecutingPlan);
					throw new GeneralException(e.getErrorCode(),
							e.getMessage(), e, new Object[] {}) {
					};
				}
			}
			return true;
		}
		// 无法修改执行中的计划
		if (plan.getStatus().equals(MarketingConstance.EXECUTING)) {
			if (isOutOrLeave) {
				PlanException e = new PlanException(
						PlanExceptionType.canNotUpdExecutingPlan);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}

		// 日程状态是否全为已指派
		if (PlanValidateUtils.isScheduleStatusOfPlan(scheList)) {
			// 日程状态已变更，无法修改该计划
			if (isOutOrLeave) {
				PlanException e = new PlanException(
						PlanExceptionType.notAllOfStatusIsAssigned);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:验证查询计划中部门数量是否合法<br />
	 * </p>
	 * 
	 * @author 石应华
	 * @version 0.1 2013-7-25
	 * @param condition
	 *            void
	 */
	public static void validateDeptNum(PlanDevelopCondition condition) {
		if (condition.getExecuteDepts().length > MarketingConstance.DEPT_NUM_LIMIT) {
			PlanException e = new PlanException(PlanExceptionType.deptNumLimit);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
}
