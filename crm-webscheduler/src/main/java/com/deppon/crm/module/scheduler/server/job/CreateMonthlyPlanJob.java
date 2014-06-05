package com.deppon.crm.module.scheduler.server.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.marketing.server.manager.IPlanManager;
import com.deppon.crm.module.scheduler.server.manager.TimerSchedulingControlEnum;
import com.deppon.crm.module.scheduler.server.service.ISchedulingControlService;
import com.deppon.crm.module.scheduler.shared.domain.SchedulingControl;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class CreateMonthlyPlanJob extends GridJob{
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		Log logger = LogFactory.getLog(CreateMonthlyPlanJob.class);
		IPlanManager planManager = getBean("planManager",IPlanManager.class);
		ISchedulingControlService schedulingControlService = getBean("schedulingControlService",ISchedulingControlService.class);
		List<SchedulingControl> schedulingControlList = schedulingControlService.searchNeedExecuteList();
		List<SchedulingControl> scList = new ArrayList<SchedulingControl>();
		int deliver = 0;
		int receive = 0;
		int detail = 0;
		for (SchedulingControl sc : schedulingControlList) {
			String tableName = sc.getTableName();
			if (tableName
					.equals(TimerSchedulingControlEnum
							.getValue(TimerSchedulingControlEnum.T_CRM_DELIVERTOP50CUSTMER))
					|| tableName
							.equals(TimerSchedulingControlEnum
									.getValue(TimerSchedulingControlEnum.T_CRM_RECEIVETOP50CUSTMER))
					|| tableName
							.equals(TimerSchedulingControlEnum
									.getValue(TimerSchedulingControlEnum.T_CRM_CUSTOMERLOSEDETAIL))) {
				scList.add(sc);
				if(tableName
					.equals(TimerSchedulingControlEnum
							.getValue(TimerSchedulingControlEnum.T_CRM_DELIVERTOP50CUSTMER))){
					deliver++;
				}
				if(tableName
							.equals(TimerSchedulingControlEnum
									.getValue(TimerSchedulingControlEnum.T_CRM_RECEIVETOP50CUSTMER))){
					receive++;
				}
				if(tableName
							.equals(TimerSchedulingControlEnum
									.getValue(TimerSchedulingControlEnum.T_CRM_CUSTOMERLOSEDETAIL))){
					detail++;
				}
			}
		}
		SchedulingControl _sc = null;
		if (scList != null && scList.size() > 0) {
			_sc = null;
			for (SchedulingControl schedulingControl : scList) {
				_sc = new SchedulingControl();
				_sc.setId(schedulingControl.getId());
				_sc.setCrmTime(new Date());
				_sc.setState("2");// 异常
				schedulingControlService.update(_sc);
			}
		}
		if( deliver > 0 && receive > 0 && detail > 0 ){
			logger.info(new Date() + "begin createMonthlyPlanJob");	
			planManager.createMonthlyPlanByJob();
			logger.info(new Date() + "end createMonthlyPlanJob");	
		}else{
			return;
		}
		 _sc = null;
		for(SchedulingControl schedulingControl :scList){
			_sc = new SchedulingControl();
			_sc.setId(schedulingControl.getId());
			_sc.setCrmEndTime(new Date());
			_sc.setState("1");// 正常
			schedulingControlService.update(_sc);
		}
	}
}
