/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ScheduleValiateUtils.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author Administrator
 * @version 0.1 2012-3-28
 */
package com.deppon.crm.module.marketing.server.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.action.DevelopScheduleVO;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.shared.exception.PlanException;
import com.deppon.crm.module.marketing.shared.exception.PlanExceptionType;
import com.deppon.crm.module.marketing.shared.exception.ScheduleException;
import com.deppon.crm.module.marketing.shared.exception.ScheduleExceptionType;

/**
 * <p>
 * 日程规则验证类<br />
 * </p>
 * 
 * @title ScheduleValiateUtils.java
 * @package com.deppon.crm.module.marketing.server.utils
 * @author 苏玉军
 * @version 0.1 2012-3-28
 */

public class ScheduleValiateUtils {
	private final static int PERIOD = 3;

	/**
	 * 
	 * <p>
	 * 验证日期是否可以删除<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param plan
	 * @param sche
	 * @param user
	 * @return
	 * boolean
	 */
	public static boolean isScheduleCannDelete(Plan plan, Schedule sche,
			User user) {
		// 有计划的日程：a.在计划期限内，“已完成”日程不可修改和删除，其他均可修改和删除
		if (!plan.getStatus().equals(MarketingConstance.OVERDUE)
				//已完成
				&& !plan.getStatus().equals(MarketingConstance.FINISHED)
				//已完成
				&& sche.getStatus().equals(MarketingConstance.SCHEDULE_FINISH)) {
			//不能删除已完成日程
			throw new ScheduleException(
					ScheduleExceptionType.cannotDeleteFinishSchedule);

		}
		// 计划过期日程均不能进行修改
		if (plan.getStatus().equals(MarketingConstance.FINISHED)
				//过期
				|| plan.getStatus().equals(MarketingConstance.OVERDUE)) {
			//不能删除已完成计划日程
			throw new ScheduleException(
					ScheduleExceptionType.cannotDeleteScheduleForFinishPlan);
		}
		// 只能操作自己的日程
		if (!ScheduleValiateUtils.canDeleteSchedule(sche, user)) {
			//不能修改他人日程
			throw new ScheduleException(
					ScheduleExceptionType.cannotModifyNotSelfSchedule);
		}
		return true;

	}

	/**
	 * 
	 * <p>
	 * 验证是否删除日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-25
	 * @param schedule
	 * @param user
	 * @return
	 * boolean
	 */
	public static boolean canDeleteSchedule(Schedule schedule, User user) {
		//用户ID
		String userId = user.getEmpCode().getId();
		//比较
		return schedule.getExeManId().equals(userId);
	}

	/**
	 * 
	 * <p>
	 * 日程时间是否在开发计划内
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-28
	 * @param plan
	 * @param schedule
	 * @return boolean
	 */
	public static boolean canCreateSchedule(Plan plan, Schedule schedule) {
		//计划的开始时间与日程时间对比
		if ((PlanValidateUtils.compareToDate(plan.getBeginTime(),
				schedule.getTime()) <= 0)
				&& (PlanValidateUtils.compareToDate(plan.getEndTime(),
						schedule.getTime()) >= 0)) {
			return true;
		}
		return false;
	}

	/**
	 * <p>
	 * 根据时间判断日程的状态<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-28
	 * @param sche
	 * @return Plan
	 */
	public static Schedule checkScheduleStatus(Schedule sche) {
		// 日程不能为空
		if (sche == null) {
			//日程不能为空
			throw new PlanException(PlanExceptionType.scheduleCannotBeNull);
		}
		Date now = new Date();
		Date scheDate = sche.getTime();
		// 日程时间为空，则为已指派
		if (scheDate == null) {
			//已指派
			sche.setStatus(MarketingConstance.SCHEDULE_ASSIGNED);
			//返回日程对象
			return sche;
		}
		// 如果日程时间不空
		int i = PlanValidateUtils.isSameDate(now, scheDate);
		// 当前日期大于日程日期
		/**
		 * 1: 已过期 0：进行中 -1：已制定
		 */
		switch (i) {
		// 当前日期大于日程日期
		case 1:
			//设置已过期
			sche.setStatus(MarketingConstance.SCHEDULE_OVERDUE);
			//结束
			break;
		// 当前日期等于日程日期
		case 0:
			//设置执行中
			sche.setStatus(MarketingConstance.SCHEDULE_EXECUTING);
			//结束
			break;
		// 当前日期小于日程日期
		case -1:
			//设置已制定
			sche.setStatus(MarketingConstance.SCHEDULE_FORMULATE);
		}
		//返回日程
		return sche;
	}

	/**
	 * 
	 * <p>
	 * 判断日程是否为空
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param schedule
	 * @return
	 * boolean
	 */
	public static boolean isNotEmptySchedule(Schedule schedule) {
		//日程判断
		if (schedule == null) {
			//日程不能为空异常
			throw new ScheduleException(
					ScheduleExceptionType.scheduleIdConntBeNull);
		}
		//返回true
		return true;
	}

	/**
	 * <p>
	 * 验证计划和日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-28
	 * @param plan
	 * @param schedule
	 * @return boolean
	 */
	public static boolean canUpdateSchedule(Plan plan, Schedule oldschedule,
			Schedule newSchedule, User user) {
		//日程时间为空
		if (newSchedule.getTime() == null) {
			//未选择日程时间异常
			throw new ScheduleException(
					ScheduleExceptionType.doNotSetScheduleTime);
		}
		//能否删除日程
		if (!canUserDelete(oldschedule, user)) {
			throw new ScheduleException(
					ScheduleExceptionType.cannotModifyNotSelfSchedule);
		}
		//不能修改已完成或过期的日程
		if (!isPlanInUpdateStatus(plan)) {
			throw new ScheduleException(
					ScheduleExceptionType.cannotModifyFinOrOverSchedule);
		}
		// 新日程日期不在计划范围
		if (!isPlanInDateRange(plan, newSchedule)) {
			//定义日期格式
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			//格式化日期
			String d1 = dateformat.format(plan.getBeginTime());
			//格式化日期
			String d2 = dateformat.format(plan.getEndTime());
			//消息定义
			String msg = "(%s ~ %s)";
			//抛出日程时间不在计划时间里
			throw new ScheduleException(
					ScheduleExceptionType.scheduleDateNotBetweenPlanDate,
					String.format(msg, d1, d2));
		}
		//日程时间不能小于当前时间
		if (PlanValidateUtils.isSameDate(newSchedule.getTime(), new Date()) == -1) {
			throw new ScheduleException(
					ScheduleExceptionType.scheduleCannotLTCurrent);
		}
		//不能修改已完成日程
		if (!isScheduleInUpdateStatus(oldschedule)) {
			throw new ScheduleException(
					ScheduleExceptionType.cannotModifyFinishedSchedule);
		}
		return true;
	}

	/**
	 * <p>
	 *  自己删除本人的
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param schedule
	 * @param user
	 * @return boolean
	 */
	private static boolean canUserDelete(Schedule schedule, User user) {
		//日程执行人与当前用户是否一致
		if (schedule.getExeManId().equals(user.getEmpCode().getId())) {
			//一致
			return true;
		}
		//不一致
		return false;
	}

	/**
	 * <p>
	 * 验证计划状态，是否允许更新日程
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param plan
	 * @return
	 * boolean
	 */
	private static boolean isPlanInUpdateStatus(Plan plan) {
		//计划为空
		if (plan == null) {
			//客户更新
			return true;
		}
		// 计划处于已完成或已过期状态时，不可更新相关日程
		String status = plan.getStatus();
		//计划状态已过期或者已完成
		if (status.equals(MarketingConstance.OVERDUE)
				|| status.equals(MarketingConstance.FINISHED)) {
			// 不能更新
			return false;
		}
		//可以更新
		return true;
	}

	/**
	 * 
	 * <p>
	 *  验证日程时间是否在计划范围内
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param plan
	 * @param schedule
	 * @return
	 * boolean
	 */
	private static boolean isPlanInDateRange(Plan plan, Schedule schedule) {
		//plan为空
		if (plan == null) {
			//在指定范围
			return true;
		}
		//计划开始时间与日程时间比较，日程时间在计划时间范围内
		if ((PlanValidateUtils.compareToDate(plan.getBeginTime(),
				schedule.getTime()) <= 0)
				&& (PlanValidateUtils.compareToDate(plan.getEndTime(),
						schedule.getTime()) >= 0)) {
			//在指定范围
			return true;
		}
		//否则，不再指定范围
		return false;
	}

	/**
	 * <p>
	 * 验证日程状态，是否允许更新日程
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param schedule
	 * @return
	 * boolean
	 */
	private static boolean isScheduleInUpdateStatus(Schedule schedule) {
		// 日程已完成状态时，不可更新日程
		if (schedule.getStatus().equals(MarketingConstance.SCHEDULE_FINISH)) {
			//不能更新
			return false;
		}
		//可以更新
		return true;
	}

	/**
	 * <p>
	 *  判断查询条件是否都为空
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param condition
	 * @return
	 * boolean
	 * @revision 盛诗庆 验证条件新增业务类别
	 */
	public static boolean isQueryConditonIsNull(CustomerVo condition) {
		//条件为空
		if (condition == null
				//业务状态为空
				|| (objectIsEmpty(condition.getBussinesState())
						//客户名为空
						&& objectIsEmpty(condition.getCustName())
						//联系人名称为空
						&& objectIsEmpty(condition.getLinkManName())
						//联系人手机为空
						&& objectIsEmpty(condition.getLinkManMobile())
						//联系人电话
						&& objectIsEmpty(condition.getLinkManPhone())
						//客户属性
						&& objectIsEmpty(condition.getCustProperty())
						//合作意向
						&& objectIsEmpty(condition.getCoopIntention())
						//客户行业
						&& objectIsEmpty(condition.getCustbase())
						//货量潜力
						&& objectIsEmpty(condition.getVolumePotential())
						//客户类型
						&& objectIsEmpty(condition.getCustType())
						//开始时间
						&& objectIsEmpty(condition.getBeginTime())
						//结束时间
						&& objectIsEmpty(condition.getOverTime())
						//行业
						&& objectIsEmpty(condition.getTrade())
						//客户编码
						&& objectIsEmpty(condition.getMemberNo())
						//客户等级
						&& objectIsEmpty(condition.getMemberLevel())
						//联系人属性
						&& objectIsEmpty(condition.getLinkManProperty())
						//联系人编码
						&& objectIsEmpty(condition.getLinkManCode())
						//业务类别
						&&objectIsEmpty(condition.getBusinessType()))) {
			//抛出查询条件不能为空异常
			throw new PlanException(
					//查询条件不能为空
					PlanExceptionType.queryConditionCannotBeNull);
		}
		//不为空
		return false;
	}

	/**
	 * 
	 * <p>
	 * 验证两日期间相差时间是否超过period月<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-1
	 * @param start
	 * @param end
	 * @param perioid
	 * @return boolean
	 */
	public static boolean getDaysBetweenDates(Date start, Date end, int perioid) {
		//Calendar实例
		Calendar calenderStart = Calendar.getInstance();
		Calendar calenderEnd = Calendar.getInstance();
		//给实例设置时间
		calenderStart.setTime(start);
		calenderEnd.setTime(end);
		//给calender日期加 perioid月
		calenderStart.set(Calendar.MONTH, calenderStart.get(Calendar.MONTH)
				+ perioid);
		// 范围大于period 返回true
		if (calenderEnd.before(calenderStart)
				|| calenderEnd.equals(calenderStart)) {
			//否
			return false;
		}
		//是
		return true;
	}

	/**
	 * 
	 * <p>
	 * 判断对象是否为空<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param obj
	 * @return
	 * boolean
	 */
	public static boolean objectIsEmpty(Object obj) {
		//如果对象为空，直接返回true
		if (obj == null){
			//ture
			return true;
		}
		//obj 是 String类型
		if (obj instanceof String) {
			//空字符串
			if (((String) obj).trim().equals("")) {
				//true
				return true;
			}
		} else if (obj instanceof List) {
			//obj为List类型
			List list = (List) obj;
			return list.isEmpty();
		} else if (obj instanceof Set) {
			//obj为Set类型
			Set set = (Set) obj;
			return set.isEmpty();
		} else if (obj instanceof Map) {
			//obj为Map类型
			Map map = (Map) obj;
			return map.isEmpty();
		}
		return false;
	}

	/**
	 * <p>
	 *  创建日程检查
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param sche
	 * @return
	 * boolean
	 */
	public static boolean isPassedValidate(Schedule sche,boolean isRepeat,boolean isExistBO) {
		if (StringUtils.isEmpty(sche.getPlanId())) {
			// 无计划日程
			if (sche.getTime() == null) {
				//日程为空异常
				throw new PlanException(PlanExceptionType.scheduleCannotBeNull);
			}
			// 判断日程时间是否在当前时间前
			int i = PlanValidateUtils.isSameDate(sche.getTime(), new Date());
			//日程时间小于当前时间，爆出异常
			if (i == -1) {
				throw new ScheduleException(
						ScheduleExceptionType.scheduleCannotLTCurrent);
			}
		}
		// 检查是否已选择客户
		if (StringUtils.isEmpty(sche.getCustId())) {
			//客户不能为空
			throw new ScheduleException(ScheduleExceptionType.customerIsNull);
		}
		if(!MarketingConstance.RES_SCHEDULE_BUSINESS.equals(sche.getComeForm())){
			if(isRepeat){
				throw new ScheduleException(ScheduleExceptionType.isRepeatedCust,new Object[]{sche.getCustId()});
			}
			if(isExistBO){
				throw new ScheduleException(ScheduleExceptionType.isExistBO,new Object[]{sche.getCustId()});
			}			
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * 验证查询条件<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param condition
	 * @return
	 * boolean
	 */
	public static boolean canSearchCustomerInfo(CustomerVo condition) {

		// 验证查询条件是否为空，为空抛出异常
		if (ScheduleValiateUtils.isQueryConditonIsNull(condition)) {
			//查询条件非空异常
			throw new ScheduleException(
					ScheduleExceptionType.queryConditionCannotBeNull);
		}

		// 创建日期超过给定范围
		if (!MonitorPlanValidateUtils.comparisonDateByField(
				condition.getBeginTime(), condition.getOverTime(),
				Calendar.YEAR, 1)) {
			// 查询日期大于1年
			throw new PlanException(PlanExceptionType.dateRangeTooLarge);
		}
		//查询时间校验,全选或者全不选
		if((condition.getQueryBeginTime() ==null &&condition.getQueryOverTime() !=null)
				||(condition.getQueryBeginTime() !=null &&condition.getQueryOverTime() ==null)){
			throw new ScheduleException(ScheduleExceptionType.queryAllSelectOrNot);
		}
		//查询时间必须在创建时间之内
		if(condition.getQueryBeginTime()!=null && condition.getQueryOverTime()!=null){
			// 查询日期超过给定范围
			if (!MonitorPlanValidateUtils.comparisonDateByField(
					condition.getQueryBeginTime(), condition.getQueryOverTime(),
					Calendar.YEAR, 1)) {
				// 查询日期大于1年
				throw new PlanException(PlanExceptionType.dateRangeTooLarge);
			}
			//查询开始时间不能小于创建开始时间
			if(condition.getBeginTime().after(condition.getQueryBeginTime())){
				throw new ScheduleException(ScheduleExceptionType.qbtCannotLessThanbct);
			}
			//查询结束时间不能大于创建开始时间
			if(condition.getOverTime().before(condition.getQueryOverTime())){
				throw new ScheduleException(ScheduleExceptionType.qotCannotLargeThanoct);
			}
		}
		if(condition.getDeptId()==null || "".equals(condition.getDeptId())){
			throw new ScheduleException(ScheduleExceptionType.mustSelectDept);
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * 验证日程查询条件<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param vo
	 * @return
	 * boolean
	 */
	public static boolean canSearchSchedule(DevelopScheduleVO vo) {
		//非空判断
		if (vo == null) {
			//查询条件非空异常
			throw new ScheduleException(
					ScheduleExceptionType.queryConditionCannotBeNull);
		}
		//两日期相差月数
		if (ScheduleValiateUtils.getDaysBetweenDates(vo.getCreateStartTime(),
				vo.getCreateEndTime(), PERIOD)) {
			//日期范围太大异常
			throw new ScheduleException(ScheduleExceptionType.dateRangeTooLarge);
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014-3-20
	 * @param vo
	 * @return
	 * boolean
	 */
	public static boolean canSearchBusinessSchedule(DevelopScheduleVO vo){
		//非空判断
		if (vo == null) {
			//查询条件非空异常
			throw new ScheduleException(
					ScheduleExceptionType.queryConditionCannotBeNull);
		}
		//两日期相差月数
		if (ScheduleValiateUtils.getDaysBetweenDates(vo.getCreateStartTime(),
				vo.getCreateEndTime(), 6)) {
			//日期范围太大异常
			throw new ScheduleException(ScheduleExceptionType.dateRangeTooLarge6);
		}
		return true;
	}
}
