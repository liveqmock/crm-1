package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.customer.server.manager.ICustCreditManager;
import com.deppon.crm.module.customer.server.manager.impl.CustCreditManagerImpl;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * <p>
 * Description: 每月1号凌晨，自动恢复客户信息中散客、固客的“临欠额度”<br/>
 * </p>
 * @title CustCreditDeliverMoneyJob
 * @package com.deppon.crm.module.scheduler.server.job
 * @author andy
 * @version 1.0
 * @date 2014-04-04
 *
 */
public class CustCreditDeliverMoneyJob extends GridJob{
	private static ICustCreditManager custCreditManager;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		custCreditManager = getBean("custCreditManager", CustCreditManagerImpl.class);
		Log logger = LogFactory.getLog(CustCreditDeliverMoneyJob.class);
		logger.info("CustCreditDeliverMoneyJob_开始时间：" + new Date());
		custCreditManager.getDeliverMoneyByCondtion();
		logger.info("CustCreditDeliverMoneyJob_结束时间：" + new Date());
	}
}
