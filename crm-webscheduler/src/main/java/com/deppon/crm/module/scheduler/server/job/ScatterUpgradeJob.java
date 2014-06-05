package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.scheduler.server.service.TimerPotentialCustomerService;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 
 * <p>
 * 散客升级定时器
 * </p>
 * @title ScatterUpgradeJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author 唐亮
 * @version 0.1 2013-5-15
 */
public class ScatterUpgradeJob extends GridJob{
	private final static String UPGRADELIST = "UPGRADELIST";
	private TimerPotentialCustomerService timerPotentialCustomerService;
	/**
	 * 
	 * @Title: upgradeListDataProcess
	 *  <p>
	 * @Description: TODO(这里用一句话描述这个方法的作用)<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-5-15
	 * @return void
	 * @throws
	 */
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		Date time = new Date();
		upgradeListDataProcess(time);
	}
	/**
	 * 
	 * @Title: upgradeListDataProcess
	 *  <p>
	 * @Description: TODO(这里用一句话描述这个方法的作用)<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-5-15
	 * @return void
	 * @throws
	 */
	public void upgradeListDataProcess(Date time) {
		//读取昨天的数据
		time.setDate(time.getDate()-1);
		java.sql.Date timer = new java.sql.Date(time.getTime());
		timerPotentialCustomerService=getBean("timerPotentialCustomerService", TimerPotentialCustomerService.class);
		timerPotentialCustomerService.callStoredProcedure(UPGRADELIST,timer);
	}
}
