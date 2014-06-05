package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.customer.server.manager.impl.GoodTradeMappingManager;
import com.deppon.crm.module.keycustomer.server.manager.IKeyCustomerManager;
import com.deppon.crm.module.keycustomer.server.manager.impl.KeyCustomerManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 
 * <p>
 * Description:系统自动删除大客户标记<br />
 * </p>
 * @title KeyCustomerAutoDeleteFlagJob.java
 * @package com.deppon.crm.module.scheduler.server.job 
 * @author 106138
 * @version 0.1 2014年4月10日
 */
public class KeyCustomerAutoDeleteFlagJob extends GridJob{
	private IKeyCustomerManager keyCustomerManager;
	@Override
	protected void doExecute(JobExecutionContext context) throws JobExecutionException {
		Log logger = LogFactory.getLog(KeyCustomerAutoDeleteFlagJob.class);
		logger.info("KeyCustomerAutoDeleteFlagJob开始时间：" + new Date());
		keyCustomerManager=getBean("keyCustomerManager", KeyCustomerManager.class);
		keyCustomerManager.autoDeleteKeyCustomerFlag();
		logger.info("KeyCustomerAutoDeleteFlagJob结束时间：" + new Date());
	}

}
