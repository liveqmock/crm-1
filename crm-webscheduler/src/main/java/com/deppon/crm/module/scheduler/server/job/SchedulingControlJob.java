package com.deppon.crm.module.scheduler.server.job;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.marketing.server.manager.IPlanManager;
import com.deppon.crm.module.scheduler.server.manager.TimerSchedulingControlEnum;
import com.deppon.crm.module.scheduler.server.service.ISchedulingControlService;
import com.deppon.crm.module.scheduler.shared.domain.SchedulingControl;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class SchedulingControlJob extends GridJob{
	/*
	 * 线程池
	 */
	private ExecutorService threadPool;
	// 邮件
	private MailSenderService mailSenderService = getBean("mailSenderService",MailSenderService.class);
	final ISchedulingControlService schedulingControlService = getBean("schedulingControlService", ISchedulingControlService.class);
	IPlanManager planManager = null;
	final Log logger =  LogFactory.getLog(SchedulingControlJob.class);
	public void setMailSenderService(MailSenderService mailSenderService) {
		this.mailSenderService = mailSenderService;
	}
	public SchedulingControlJob() {
		init();
	}

	private void init() {
		threadPool = Executors.newCachedThreadPool();
	}
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
//		 planManager = getBean(
//				"planManager", IPlanManager.class);
//		List<SchedulingControl> schedulingControlList = schedulingControlService
//				.searchNeedExecuteList();
//		if (schedulingControlList != null && schedulingControlList.size() > 0) {
//			logger.info(new Date() + "，获得任务调度记录数为："
//					+ schedulingControlList.size());
//			List<SchedulingControl> scList = new ArrayList<SchedulingControl>();// 统计月计划依赖的三个表数据
//			for (SchedulingControl sc : schedulingControlList) {
//				/**
//				 * 固定月计划：需要依赖发货前50、到货前50、流失客户3种数据。
//				 */
//				String tableName = sc.getTableName();
//				Date buzDate = sc.getBuzDate();
//				logger.info("表名为：" + tableName + "，业务时间为：" + buzDate);
//
//				if (tableName
//						.equals(TimerSchedulingControlEnum
//								.getValue(TimerSchedulingControlEnum.T_CRM_DELIVERTOP50CUSTMER))
//						|| tableName
//								.equals(TimerSchedulingControlEnum
//										.getValue(TimerSchedulingControlEnum.T_CRM_RECEIVETOP50CUSTMER))
//						|| tableName
//								.equals(TimerSchedulingControlEnum
//										.getValue(TimerSchedulingControlEnum.T_CRM_CUSTOMERLOSEDETAIL))) {
//					if (tableName
//							.equals(TimerSchedulingControlEnum
//									.getValue(TimerSchedulingControlEnum.T_CRM_DELIVERTOP50CUSTMER))) {
//						scList.add(sc);
//
//						int i = 0;
//						for (SchedulingControl sc1 : schedulingControlList) {
//							if (sc1.getTableName()
//									.equals(TimerSchedulingControlEnum
//											.getValue(TimerSchedulingControlEnum.T_CRM_RECEIVETOP50CUSTMER))
//									&& buzDate.equals(sc1.getBuzDate())) {
//								scList.add(sc1);
//								i++;
//								break;
//							}
//						}
//						for (SchedulingControl sc2 : schedulingControlList) {
//							if (sc2.getTableName()
//									.equals(TimerSchedulingControlEnum
//											.getValue(TimerSchedulingControlEnum.T_CRM_CUSTOMERLOSEDETAIL))
//									&& this.comparisonDateByField(sc2.getBuzDate(), buzDate, Calendar.MONTH, 2)) {
//								scList.add(sc2);
//								i++;
//								break;
//							}
//						}
//
//						if (i == 2) {
//							execute(
//									TimerSchedulingControlEnum
//											.getValue(TimerSchedulingControlEnum.T_CRM_DELIVERTOP50CUSTMER),
//									scList, buzDate);
//						}
//					}
//
//				}
//			}// for end
//		}
//	}
//	/*
//	 * 执行
//	 */
//	private void execute(final String tableName,
//			final List<SchedulingControl> scList, final Date buzDate) {
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		// 维护crm开始执行时间及状态
//		// 任务开始之前，现将其状态改为‘异常’
//		updateSchedulingControlBefore(scList);
//
//		synchronized (SchedulingControlJob.class) {
//			if (threadPool.isTerminated()) {
//				this.init();
//			}
//			threadPool.submit(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						executeBuz(tableName,
//								scList, buzDate);
//					} catch (Exception e) {
//						logger.error(tableName + " execute error", e);
//
//						// 把e.printStackTrace转换成字符串
////						Writer writer = new StringWriter();
////						PrintWriter printWriter = new PrintWriter(writer);
////						e.printStackTrace(printWriter);
////						logger.error(tableName + "：" + writer);
////
////						// 给相关开发员发邮件
////						String _email = schedulingControlService
////								.searchValueByKey(tableName);
////						logger.info(_email);
////						if (_email != null && !"".equals(_email)) {
////							String[] _to = _email.split(";");
////							try {
////								MailInfo mi = new MailInfo("定时任务执行出错了，表名为："
////										+ tableName + "，业务时间为：" + buzDate,
////										writer.toString(), mailSenderService
////												.getUserName(), _to);
////								mailSenderService.sendExtranetMail(mi);
////							} catch (Exception e1) {
////								e1.printStackTrace();
////							}
////
////						}
//					}
//				}
//			});
//		}
//	}
//	
//	/*
//	 * 执行业务前修改调度表数据
//	 */
//	private boolean updateSchedulingControlBefore(List<SchedulingControl> scList) {
//		if (scList != null && scList.size() > 0) {
//			SchedulingControl _sc = null;
//			for (SchedulingControl schedulingControl : scList) {
//				_sc = new SchedulingControl();
//				_sc.setId(schedulingControl.getId());
//				_sc.setCrmTime(new Date());
//				_sc.setState("2");// 异常
//				schedulingControlService.update(_sc);
//			}
//		}
//		return true;
//	}
//	/**
//	 * <p>
//	 * Description: 比较时间差<br />
//	 * </p>
//	 * @author ZhuPJ
//	 * @version 0.1 2012-10-8
//	 * @param d1
//	 * @param d2
//	 * @param field 比较时间差单位（年/月/日）
//	 * @param amount 比较时间差值
//	 * @return
//	 * boolean
//	 */
//	private boolean comparisonDateByField(Date d1, Date d2, int field, int amount){
//		Date tmp = null;
//		switch (field) {
//		case Calendar.DATE:
//			tmp = DateUtils.addDays(d1, amount);
//			break;
//		case Calendar.MONTH:
//			tmp = DateUtils.addMonths(d1, amount);
//			break;
//		case Calendar.YEAR:
//			tmp = DateUtils.addYears(d1, amount);
//			break;
//		}
//		return DateUtils.truncatedCompareTo(tmp, d2, Calendar.DATE)==0 ? true : false;
//	}
//	/*
//	 * 根据表名执行对应业务逻辑
//	 */
//	private void executeBuz(String tableName, List<SchedulingControl> scList,
//			Date buzDate) throws Exception {
//		if (tableName
//				.equals(TimerSchedulingControlEnum.T_CRM_DELIVERTOP50CUSTMER
//						.getStr())
//				|| tableName
//						.equals(TimerSchedulingControlEnum.T_CRM_RECEIVETOP50CUSTMER
//								.getStr())
//				|| tableName
//						.equals(TimerSchedulingControlEnum.T_CRM_CUSTOMERLOSEDETAIL
//								.getStr())) {
//			// 固定计划创建任务（月计划）
//			processMonthlyMaintainCustomer();
//
//			// 维护crm结束执行时间及状态
//			updateSchedulingControlEnd(scList);
//		} 
//	}
//	public void processMonthlyMaintainCustomer() {
//		this.processMonthlyMaintainCustomer(new Date());
//	}
//	 /**
//	 * <p>
//	 	系统每月第一天（必须为报表程序运行后）点自动依据如下规则制定计划，规定计划时限为下月1号至最后一天，
//	 	指派下发给营业部经理，执行人由营业部经理进行更改、指派。
//	 a) 营业部当月发货金额前50名客户
//	 b) 营业部当月到达金额前50名客户
//	 c) 当年归属本营业部降级会员客户（每年年底出具降级会员列表，此类客户计划制定时间为每年一月）。
//	 d) 当月归属本营业部升级会员客户
//	 e) 营业部当月投诉客户
//	 f) 流失客户：会员客户当月发货，后两个月未来发货，此种情况为客户在当月流失
//	 g) 是针对客户的主要联系人;
//	 h) 针对每月的固定计划：计划主题为“每月固定维护计划201204”.
//	 i) 每月固定维护计划描述：“货量前50名客户、升降级客户、投诉客户、重点客户、流失客户” *
//	 * </p>
//	 * @author zhujunyong
//	 * @version 0.1 Apr 19, 2012
//	 * void
//	 */
//	public void processMonthlyMaintainCustomer(Date biDate) {
//		System.out
//				.println("PlanScheduleTimer Call processMonthlyMaintainCustomer.........."
//						+ new Date());
//		try {
//			// 创建固定计划（存储过程） by zpj 2012.12.30
//			planManager.createMonthlyPlanByJob();
//
//		} catch (Exception e) {
//			logger.debug("每月维护计划创建存在异常", e);
//		}
//	}
//	/*
//	 * 执行业务后修改调度表数据
//	 */
//	private boolean updateSchedulingControlEnd(List<SchedulingControl> scList) {
//		if (scList != null && scList.size() > 0) {
//			SchedulingControl _sc = null;
//			for (SchedulingControl schedulingControl : scList) {
//				_sc = new SchedulingControl();
//				_sc.setId(schedulingControl.getId());
//				_sc.setCrmEndTime(new Date());
//				_sc.setState("1");// 正常
//				schedulingControlService.update(_sc);
//			}
//		}
//		return true;
	}
}
