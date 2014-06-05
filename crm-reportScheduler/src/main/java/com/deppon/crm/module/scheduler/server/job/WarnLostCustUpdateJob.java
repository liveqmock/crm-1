package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.marketing.server.manager.impl.WarnLostCustManagerImp;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 
 * 流失预警客户更新
 * 每天  凌晨1点
 * */
public class WarnLostCustUpdateJob extends GridJob{
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		Log logger = LogFactory.getLog(WarnLostCustUpdateJob.class);
		logger.info("WarnLostCustUpdateJob_开始时间："+new Date());
		WarnLostCustManagerImp warnLostManager=
				this.getBean("warnLostCustManagerImp", WarnLostCustManagerImp.class);
		warnLostManager.updateCustWarnStatusAndPreSendTime();
		logger.info("WarnLostCustUpdateJob_结束时间："+new Date());
	}

}
