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
 * Description:每月1号凌晨自动生成预退出大客户列表<br />
 * </p>
 * @title KeyCustomerOutListJob.java
 * @package com.deppon.crm.module.scheduler.server.job 
 * @author 106138
 * @version 0.1 2014年4月10日
 */
public class KeyCustomerOutListJob extends GridJob{
	private IKeyCustomerManager keyCustomerManager;
	@Override
	protected void doExecute(JobExecutionContext context) throws JobExecutionException {
		Log logger = LogFactory.getLog(KeyCustomerOutListJob.class);
		logger.info("KeyCustomerOutListJob开始时间：" + new Date());
		keyCustomerManager=getBean("keyCustomerManager", KeyCustomerManager.class);
		keyCustomerManager.createKeyCustomerOutList();
		logger.info("KeyCustomerOutListJob结束时间：" + new Date());
	}


}
