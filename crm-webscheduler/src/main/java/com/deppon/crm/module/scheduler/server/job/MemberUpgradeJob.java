package com.deppon.crm.module.scheduler.server.job;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.scheduler.server.service.IMemberTimerService;
import com.deppon.crm.module.scheduler.server.service.ISchedulingControlService;
import com.deppon.foss.framework.server.adapter.mail.IMailSender;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 
 * <p>
 *定时器声明-会员升级
 * </p>
 * @title MemberUpgradeJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author 唐亮
 * @version 0.1 2013-2-20
 */
public class MemberUpgradeJob extends GridJob{
	/**
	 * 
	 * <p>
	 * Description:会员升级<br />
	 * </p>
	 * @author 李盛
	 * @version 0.1 2012-10-20
	 * void
	 */
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		MailSenderService mailSenderService = getBean("mailSenderService", MailSenderService.class);
		ISchedulingControlService schedulingControlService = getBean("schedulingControlService", ISchedulingControlService.class);
		IMemberTimerService memberTimerService = getBean("memberTimerService",IMemberTimerService.class );
		Log logger = LogFactory.getLog(MemberFallJob.class);
		System.out.println(new Date() + " memberUpgradeJob  cust upgrade is Comming.....");
					
			try {
					System.out.println("-----------begin---------------");
					logger.info("会员升级执行开始");
					
					//会员升级
					memberTimerService.memberUpgrade();
					
					logger.info("会员升级执行完了");
					System.out.println("-----------end---------------");
			} catch (Exception e) {
				logger.error("T_CRM_MEMBERUPGRADE execute error", e);

				// 把e.printStackTrace转换成字符串
				Writer writer = new StringWriter();
				PrintWriter printWriter = new PrintWriter(writer);
				e.printStackTrace(printWriter);
				logger.error("T_CRM_MEMBERUPGRADE：" + writer);

				// 给相关开发员发邮件
				String _email = schedulingControlService.searchValueByKey("T_CRM_MEMBERUPGRADE");
				logger.info(_email);
				if (_email != null && !"".equals(_email)) {
					String[] _to = _email.split(";");
					try {
						MailInfo mi = new MailInfo("定时任务执行出错了，表名为：T_CRM_MEMBERUPGRADE",
								writer.toString(), mailSenderService.getUserName(), _to);
						mailSenderService.sendExtranetMail(mi);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			
		}
}
