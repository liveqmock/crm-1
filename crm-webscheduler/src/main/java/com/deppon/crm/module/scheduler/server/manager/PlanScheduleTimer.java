/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PlanScheduleTimer.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author Administrator
 * @version 0.1 2012-5-10
 */
package com.deppon.crm.module.scheduler.server.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.deppon.crm.module.marketing.server.manager.IPlanManager;
import com.deppon.crm.module.marketing.server.manager.IScheduleManager;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.ReduceCustomer;
import com.deppon.crm.module.marketing.shared.domain.Schedule;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title PlanScheduleTimer.java
 * @package com.deppon.crm.module.marketing.server.manager.impl
 * @author 苏玉军
 * @version 0.1 2012-5-10
 */

public class PlanScheduleTimer {
	// 计划Manager
	private IPlanManager planManager;
	private static Logger logger = Logger.getLogger(PlanScheduleTimer.class);
	private IScheduleManager scheduleManager;

	public IScheduleManager getScheduleManager() {
		return scheduleManager;
	}

	public void setScheduleManager(IScheduleManager scheduleManager) {
		this.scheduleManager = scheduleManager;
	}

	public IPlanManager getPlanManager() {
		return planManager;
	}

	public void setPlanManager(IPlanManager planManager) {
		this.planManager = planManager;
	}
	 /**
	 * 每天定时修正计划和日程状态
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 19, 2012
	 * void
	 */
//	public void processPlanScheduleStatus() {
//		System.out
//				.println("PlanScheduleTimer Call processPlanScheduleStatus.........."
//						+ new Date());
//		// 更新日程状态
//		try {
//			// 把已经回访过的日程状态， 重新更新为 “已完成”
//			List<Schedule> schedules = scheduleManager
//					.searchScheduleByReturnVisit();
//			scheduleManager.processScheduleStatus(schedules,
//					MarketingConstance.SCHEDULE_FINISH);
//			
//			// 查询计划今天执行的日程
//			List<Schedule> formulate = scheduleManager
//					.searchScheduleByStatus(MarketingConstance.SCHEDULE_FORMULATE);
//			scheduleManager.processScheduleStatus(formulate,
//					MarketingConstance.SCHEDULE_EXECUTING);
//
//			// 查询今天过期的日程
//			List<Schedule> executing = scheduleManager
//					.searchScheduleByStatus(MarketingConstance.SCHEDULE_EXECUTING);
//			scheduleManager.processScheduleStatus(executing,
//					MarketingConstance.SCHEDULE_OVERDUE);
//		} catch (Exception e) {
//		}
//
//		// 进行更新操作
//		boolean b;
//		try {
//			// 定义状态list
//			List<String> notInStatusList = new ArrayList<String>();
//			Date date = new Date();
//			notInStatusList.add(MarketingConstance.OVERDUE);
//			notInStatusList.add(MarketingConstance.FINISHED);
//			// 查找未完成和未过期的开发计划
//			List<Plan> planList = planManager
//					.searchPlansByStatusList(notInStatusList);
//			// 根据日程更新计划状态
//			List<Plan> resultPlan = planManager.processPlansStatusByDate(
//					planList, date);
//			b = planManager.updatePlanList(resultPlan);
//			if (b == false) {
//				logger.debug("未找到需要进行状态更改的计划日程");
//			} else {
//				logger.debug("计划日程状态更改成功");
//			}
//		} catch (Exception e) {
//			logger.debug("计划日程状态更改产生异常", e);
//		}
//
//	}

	public void processMonthlyMaintainCustomer() {
		this.processMonthlyMaintainCustomer(new Date());
	}
	 /**
	 * <p>
	 	系统每月第一天（必须为报表程序运行后）点自动依据如下规则制定计划，规定计划时限为下月1号至最后一天，
	 	指派下发给营业部经理，执行人由营业部经理进行更改、指派。
	 a) 营业部当月发货金额前50名客户
	 b) 营业部当月到达金额前50名客户
	 c) 当年归属本营业部降级会员客户（每年年底出具降级会员列表，此类客户计划制定时间为每年一月）。
	 d) 当月归属本营业部升级会员客户
	 e) 营业部当月投诉客户
	 f) 流失客户：会员客户当月发货，后两个月未来发货，此种情况为客户在当月流失
	 g) 是针对客户的主要联系人;
	 h) 针对每月的固定计划：计划主题为“每月固定维护计划201204”.
	 i) 每月固定维护计划描述：“货量前50名客户、升降级客户、投诉客户、重点客户、流失客户” *
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 19, 2012
	 * void
	 */
	public void processMonthlyMaintainCustomer(Date biDate) {
		System.out
				.println("PlanScheduleTimer Call processMonthlyMaintainCustomer.........."
						+ new Date());
		try {
			// 创建固定计划（存储过程） by zpj 2012.12.30
			planManager.createMonthlyPlanByJob();
		
//			// 每月
//			boolean result = false;
//			Date date = DateUtils.addMonths(biDate, -1); // 查询时间为上月
//			
//			List<String> monthlyCustNumberList = new ArrayList<String>();
//			monthlyCustNumberList = planManager
//					.getAutoMonthlyMaintainPlanCustomerList(date);
//			if (monthlyCustNumberList != null) {
//				result = planManager
//						.createNewAutoMonthlyMaintainPlan(monthlyCustNumberList);
//			}
//			if (result == false) {
//				logger.debug("未找到符合条件的客户需要制定每月维护计划");
//			} else {
//				logger.debug("每月维护计划创建成功");
//			}
		} catch (Exception e) {
			logger.debug("每月维护计划创建存在异常", e);
		}

	}

//	public void processDailyMaintainCustomer() {
//		this.processDailyMaintainCustomer(new Date());
//	}
	 /**
	 * <p>
	 * 系统每天00:00:00自动依据如下规则制定计划，且一周之内系统不会再次将同一个客户筛选进入固定计划；
	 	固定计划指派下发给营业部经理，执行人由营业部经理进行更改、指派。
	 a) 发货频率超期客户：发货周期表发货频率超过客户发货周期。此类客户维护方式固定为电话回访。
	 b) 发货量下降客户：
		 铂金会员及以上的客户货量金额当月环比上月下降10%以上即标记为当月货量下降客户；
		 黄金会员这部分客户货量金额当月环比上月下降30%以上即标记为当月货量下降客户；
		 普通会员之间的这部分客户货量金额当月环比上月下降50%以上即标记为当月货量下降客户。此类客户维护方式固定为上门拜访。
	 c) 是针对客户的主要联系人;
	 d) 计划主题为“每日固定维护计划20120416”，计划完成时限为7天，次日开始为第一天.
	 e) 每日固定维护计划描述：“发货频率下降客户、发货量下降客户”
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 19, 2012
	 * void
	 */
	/*public void processDailyMaintainCustomer(Date biDate) {
		System.out
				.println("PlanScheduleTimer Call processDailyMaintainCustomer.........."
						+ new Date());
		try {
			boolean result = false;
			// 每日
			List<ReduceCustomer> dailyCustomer = new ArrayList<ReduceCustomer>();
			dailyCustomer = planManager
					.getAutoDailyMaintainPlanCustomerList(biDate);
			if (dailyCustomer != null && dailyCustomer.size() > 0) {
				result = planManager
						.createNewAutoDailyMaintainPlan(dailyCustomer);
			}
			if (result == false) {
				logger.debug("未找到符合创建每日维护计划的客户列表");
			} else {
				logger.debug("创建每日维护计划成功");
			}
		} catch (Exception e) {
			logger.debug("每日维护计划创建存在异常", e);
		}
	}*/
	
	/**
	 * <p>
	 * Description: 营销模块-待办事宜Job<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-28
	 * void
	 */
	public void processMarketingTODO(){
		try {
			// 计划信息统计
			boolean planJob = planManager.processPlanTODO();
			
			// 日程信息统计
			boolean scheJob = scheduleManager.processScheduleTODO();
			if (planJob && scheJob){
				logger.debug("任务正常执行完成");
			}
		} catch (Exception e) {
		}
	}
}
