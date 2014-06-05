package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.keycustomer.server.manager.IKeyCustomerManager;
import com.deppon.crm.module.keycustomer.server.manager.impl.KeyCustomerManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 
 * <p>
 * Description:每月1号或者16号凌晨自动生成待准入大客户列表<br />
 * </p>
 * @title KeyCustomerInListJob.java
 * @package com.deppon.crm.module.scheduler.server.job 
 * @author 106138
 * @version 0.1 2014年4月10日
 */
public class KeyCustomerInListJob extends GridJob {
	private IKeyCustomerManager keyCustomerManager;
	@Override
	protected void doExecute(JobExecutionContext context) throws JobExecutionException {
		Log logger = LogFactory.getLog(KeyCustomerInListJob.class);
		logger.info("KeyCustomerInListJob开始时间：" + new Date());
		keyCustomerManager=getBean("keyCustomerManager", KeyCustomerManager.class);
		keyCustomerManager.createKeyCustomerInList();
		logger.info("KeyCustomerInListJob结束时间：" + new Date());
	}

}
