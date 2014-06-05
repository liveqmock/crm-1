package com.deppon.crm.module.scheduler.server.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.marketing.server.manager.IPlanManager;
import com.deppon.crm.module.marketing.server.manager.IScheduleManager;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class ProcessPlanScheduleStatusJob extends GridJob {
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		IPlanManager planManager = getBean("planManager", IPlanManager.class);
		IScheduleManager scheduleManager = getBean("scheduleManager",
				IScheduleManager.class);
		Logger logger = Logger.getLogger(ProcessPlanScheduleStatusJob.class);
		System.out
				.println("PlanScheduleTimer Call processPlanScheduleStatus.........."
						+ new Date());
		// 更新日程状态
		int count = 0;
		int number = 0;
		int start = 0;
		int limit = 20;
		List<Schedule> schedules = new ArrayList<Schedule>();
		List<Schedule> executing = new ArrayList<Schedule>();
		try {
			System.out.println("把已经回访过的日程状态， 重新更新为 “已完成”");
			//已经回访过的日程数量
			count = scheduleManager.countForsearchScheduleByReturnVisit();
			number = (int) Math.ceil((double)count/limit);
			for(int i = 0; i< number; i++){
				// 把已经回访过的日程状态， 重新更新为 “已完成”
				schedules = scheduleManager.searchScheduleByReturnVisit(start,limit);
				scheduleManager.processScheduleStatus(schedules,
						MarketingConstance.SCHEDULE_FINISH);				
				start += limit;
			}
			
			System.out.println("查询计划今天执行的日程,更新为执行中");
			count = scheduleManager.countForsearchScheduleByStatus(MarketingConstance.SCHEDULE_FORMULATE);
			number = (int) Math.ceil((double)count/limit);
			start = 0;
			for(int i = 0;i<number;i++){
				// 查询计划今天执行的日程
				schedules = scheduleManager
						.searchScheduleByStatus(MarketingConstance.SCHEDULE_FORMULATE,start,limit);
				scheduleManager.processScheduleStatus(schedules,
						MarketingConstance.SCHEDULE_EXECUTING);
				start += limit;
			}

			System.out.println("查询今天过期的日程,更新为已过期");
			count = scheduleManager.countForsearchScheduleByStatus(MarketingConstance.SCHEDULE_EXECUTING);
			number = (int) Math.ceil((double)count/limit);
			start = 0;
			for(int i = 0;i<number;i++){
				// 查询今天过期的日程
				executing = scheduleManager
						.searchScheduleByStatus(MarketingConstance.SCHEDULE_EXECUTING,start,limit);
				scheduleManager.processScheduleStatus(executing,
						MarketingConstance.SCHEDULE_OVERDUE);	
				start += limit;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		// 进行更新操作
		boolean b;
		try {
			// 定义状态list
			List<String> notInStatusList = new ArrayList<String>();
			Date date = new Date();
			notInStatusList.add(MarketingConstance.OVERDUE);
			notInStatusList.add(MarketingConstance.FINISHED);
			
			start = 0;
			count = planManager.countForsearchPlansByStatusList(notInStatusList);
			number = (int) Math.ceil((double)count/limit);
			
			List<Plan> resultPlan = new ArrayList<Plan>();
			List<Plan> planList = new ArrayList<Plan>();
			for(int i = 0 ; i< number ; i++){
				System.out.println("计划状态更新，第" + i +"次");
				// 查找未完成和未过期的开发计划,数据量大，需要进行分页分批处理
				planList = planManager.searchPlansByStatusList(notInStatusList,start,limit);
				if (planList != null && planList.size() > 0){
					System.out.println("update operate");
					// 根据日程更新计划状态
					resultPlan = planManager.processPlansStatusByDate(planList, date);
					b = planManager.updatePlanList(resultPlan);
					if (b == false) {
						logger.debug("未找到需要进行状态更改的计划日程");
					} else {
						logger.debug("计划日程状态更改成功");
					}
				}				
				start += limit;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("计划日程状态更改产生异常", e);
		}
	}
}
