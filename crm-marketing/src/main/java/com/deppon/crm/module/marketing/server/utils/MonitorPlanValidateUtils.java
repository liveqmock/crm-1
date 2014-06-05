/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanUtils.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author ZhuPJ
 * @version 0.1 2012-4-11
 */
package com.deppon.crm.module.marketing.server.utils;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition;
import com.deppon.crm.module.marketing.shared.exception.MonitorPlanException;
import com.deppon.crm.module.marketing.shared.exception.MonitorPlanExceptionType;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanUtils.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author ZhuPJ
 * @version 0.1 2012-4-11
 */

public class MonitorPlanValidateUtils {
	/**
	 * <p>
	 * Description: 查询条件检查<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-20
	 * @param condition
	 * @return
	 * boolean
	 */
	public static boolean canSearchMonitorPlan(
			MonitorPlanQueryCondition condition) {
		//计划开始时间
		Date d1 = condition.getPlanStartDate();
		//计划结束时间
		Date d2 = condition.getPlanOverDate();
		//日期比较
		if (!comparisonDateByField(d1, d2, Calendar.DATE,  90)){
			// 查询日期大于3个月
			throw new MonitorPlanException(MonitorPlanExceptionType.beginEndTimeOffset);
		}
		return true;
	}
	/**
	 * 
	 * <p>
	 * 判断监控计划查询条件是否合法
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-25
	 * @param condition
	 * @return
	 * boolean
	 */
	public static boolean canSearchMonitorPlanDetail(MonitorPlanQueryCondition condition){
		//计划开始时间
		Date d1 = condition.getPlanStartDate();
		//计划结束时间
		Date d2 = condition.getPlanOverDate();
		//时间对比
		if (!comparisonDateByField(d1, d2, Calendar.MONTH,  3)){
			// 查询日期大于3个月
			throw new MonitorPlanException(MonitorPlanExceptionType.beginEndTimeOffset);
		}

		//执行部门
		String depid = condition.getExecdeptId();
		if (StringUtils.isEmpty(depid)){
			// 待查询的详情部门不能为空
			throw new MonitorPlanException(MonitorPlanExceptionType.searchConditionIsEmpty);
		}
		return true;
	}
	
	/**
	 * <p>
	 * Description: 比较时间差<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-20
	 * @param d1
	 * @param d2
	 * @param field 比较时间差单位（年/月/日）
	 * @param amount 比较时间差值
	 * @return
	 * boolean
	 */
	public static boolean comparisonDateByField(Date d1, Date d2, int field, int amount){
		//临时变量
		Date tmp = null;
		//field字段
		switch (field) {
		//日期
		case Calendar.DATE:
			//增加amount天
			tmp = DateUtils.addDays(d1, amount);
			//比较大小
			return (PlanValidateUtils.compareToDate(tmp,d2) >=0) ? true : false;
			//月份
		case Calendar.MONTH:
			//增加amount 月
			tmp = DateUtils.addMonths(d1, amount);
			break;
			//年份
		case Calendar.YEAR:
			//增加amount年
			tmp = DateUtils.addYears(d1, amount);
			break;
			//否则
		default:
			return false;
		}
		//返回比较结果
		return (PlanValidateUtils.compareToDate(tmp,d2) > 0) ? true : false;
	}
	
}
