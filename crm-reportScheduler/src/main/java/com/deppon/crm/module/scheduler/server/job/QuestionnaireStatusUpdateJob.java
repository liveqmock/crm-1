/**
 * <p>
 * Description:问卷状态变更定时器<br />
 * </p>
 * @title QuestionnaireStatusUpdateJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-4-9
 */
package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.deppon.crm.module.marketing.server.manager.IQuestionnaireManager;
import com.deppon.crm.module.marketing.server.manager.impl.QuestionnaireManagerImpl;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * <p>
 * Description:每日凌晨一点更新问卷的状态<br />
 * </p>
 * @title QuestionnaireStatusUpdateJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-4-9
 */
public class QuestionnaireStatusUpdateJob extends GridJob{
	//问卷manager
	private static IQuestionnaireManager questionnaireManager;
	/**
	 * <p>
	 * Description:每天根据一定的条件更新问卷的状态<br />
	 * @author xiaohongye
	 * @param arg0
	 * @throws JobExecutionException
	 * @version V0.1 
	 * @Date 2014-4-9
	 */
	@Override
	protected void doExecute(JobExecutionContext arg0)
			throws JobExecutionException {
		questionnaireManager = getBean("questionnaireManagerImpl", QuestionnaireManagerImpl.class);
		Log logger = LogFactory.getLog(QuestionnaireStatusUpdateJob.class);
		logger.info("QuestionnaireStatusUpdateJob_开始时间：" + new Date());
		String result = questionnaireManager.updateSurveyStatus();
		logger.info("QuestionnaireStatusUpdateJob_执行结果：" + result);
		logger.info("QuestionnaireStatusUpdateJob_结束时间：" + new Date());
	}

}
