package com.deppon.crm.module.scheduler.server.manager;

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

import com.deppon.crm.module.scheduler.server.service.ISchedulingControlService;
import com.deppon.crm.module.scheduler.shared.domain.SchedulingControl;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;

/**
 * @description 定时控制.
 * @author 安小虎
 * @version 0.1
 * @date 2012-6-2
 * @version 0.2
 * @date 2012-6-4
 */
public class TimerSchedulingControl {

	Log logger = LogFactory.getLog(TimerSchedulingControl.class);

	/*
	 * 注入
	 */
	private ISchedulingControlService schedulingControlService;

	public ISchedulingControlService getSchedulingControlService() {
		return schedulingControlService;
	}

	public void setSchedulingControlService(
			ISchedulingControlService schedulingControlService) {
		this.schedulingControlService = schedulingControlService;
	}

	// 计划日程定时业务处理类
	private PlanScheduleTimer planScheduleTimer11;

	public PlanScheduleTimer getPlanScheduleTimer11() {
		return planScheduleTimer11;
	}

	public void setPlanScheduleTimer11(PlanScheduleTimer planScheduleTimer11) {
		this.planScheduleTimer11 = planScheduleTimer11;
	}


	// 会员
	private TimerMemberCustomer timerMemberCustomer;

	public TimerMemberCustomer getTimerMemberCustomer() {
		return timerMemberCustomer;
	}

	public void setTimerMemberCustomer(TimerMemberCustomer timerMemberCustomer) {
		this.timerMemberCustomer = timerMemberCustomer;
	}

	// 数据加工
	public TimeDataProcessTimer timeDataProcessTimer;

	public TimeDataProcessTimer getTimeDataProcessTimer() {
		return timeDataProcessTimer;
	}

	public void setTimeDataProcessTimer(
			TimeDataProcessTimer timeDataProcessTimer) {
		this.timeDataProcessTimer = timeDataProcessTimer;
	}

	// 积分运单
	private TimerWaybillIntegral timerWaybillIntegral;

	public TimerWaybillIntegral getTimerWaybillIntegral() {
		return timerWaybillIntegral;
	}

	public void setTimerWaybillIntegral(
			TimerWaybillIntegral timerWaybillIntegral) {
		this.timerWaybillIntegral = timerWaybillIntegral;
	}

	// 邮件
	private MailSenderService mailSenderService;

	public MailSenderService getMailSenderService() {
		return mailSenderService;
	}

	public void setMailSenderService(MailSenderService mailSenderService) {
		this.mailSenderService = mailSenderService;
	}

	/*
	 * 线程池
	 */
	private ExecutorService threadPool;

	public TimerSchedulingControl() {
		init();
	}

	private void init() {
		threadPool = Executors.newCachedThreadPool();
	}
	
	/**
	 * <p>
	 * Description: 比较时间差<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-10-8
	 * @param d1
	 * @param d2
	 * @param field 比较时间差单位（年/月/日）
	 * @param amount 比较时间差值
	 * @return
	 * boolean
	 */
	private boolean comparisonDateByField(Date d1, Date d2, int field, int amount){
		Date tmp = null;
		switch (field) {
		case Calendar.DATE:
			tmp = DateUtils.addDays(d1, amount);
			break;
		case Calendar.MONTH:
			tmp = DateUtils.addMonths(d1, amount);
			break;
		case Calendar.YEAR:
			tmp = DateUtils.addYears(d1, amount);
			break;
		}
		return DateUtils.truncatedCompareTo(tmp, d2, Calendar.DATE)==0 ? true : false;
	}
	
	/**
	 * 
	 * @description 调度控制定时器.
	 * @author 安小虎
	 * @version 0.1
	 * @date 2012-6-4
	 */
	public void getSchedulingControlByConditon() {
		List<SchedulingControl> schedulingControlList = this.schedulingControlService
				.searchNeedExecuteList();
		if (schedulingControlList != null && schedulingControlList.size() > 0) {
			logger.info(new Date() + "，获得任务调度记录数为："
					+ schedulingControlList.size());
			List<SchedulingControl> scList = new ArrayList<SchedulingControl>();// 统计月计划依赖的三个表数据
			for (SchedulingControl sc : schedulingControlList) {
				/**
				 * 固定月计划：需要依赖发货前50、到货前50、流失客户3种数据。
				 */
				String tableName = sc.getTableName();
				Date buzDate = sc.getBuzDate();
				logger.info("表名为：" + tableName + "，业务时间为：" + buzDate);

				List<SchedulingControl> scList1 = null;
				if (tableName
						.equals(TimerSchedulingControlEnum
								.getValue(TimerSchedulingControlEnum.T_CRM_DELIVERTOP50CUSTMER))
						|| tableName
								.equals(TimerSchedulingControlEnum
										.getValue(TimerSchedulingControlEnum.T_CRM_RECEIVETOP50CUSTMER))
						|| tableName
								.equals(TimerSchedulingControlEnum
										.getValue(TimerSchedulingControlEnum.T_CRM_CUSTOMERLOSEDETAIL))) {
					if (tableName
							.equals(TimerSchedulingControlEnum
									.getValue(TimerSchedulingControlEnum.T_CRM_DELIVERTOP50CUSTMER))) {
						scList.add(sc);

						int i = 0;
						for (SchedulingControl sc1 : schedulingControlList) {
							if (sc1.getTableName()
									.equals(TimerSchedulingControlEnum
											.getValue(TimerSchedulingControlEnum.T_CRM_RECEIVETOP50CUSTMER))
									&& buzDate.equals(sc1.getBuzDate())) {
								scList.add(sc1);
								i++;
								break;
							}
						}
						for (SchedulingControl sc2 : schedulingControlList) {
							if (sc2.getTableName()
									.equals(TimerSchedulingControlEnum
											.getValue(TimerSchedulingControlEnum.T_CRM_CUSTOMERLOSEDETAIL))
									&& this.comparisonDateByField(sc2.getBuzDate(), buzDate, Calendar.MONTH, 2)) {
								scList.add(sc2);
								i++;
								break;
							}
						}

						if (i == 2) {
							this.execute(
									TimerSchedulingControlEnum
											.getValue(TimerSchedulingControlEnum.T_CRM_DELIVERTOP50CUSTMER),
									scList, buzDate);
						}
					}

				} else {
					scList1 = new ArrayList<SchedulingControl>();
					scList1.add(sc);
					this.execute(sc.getTableName(), scList1, sc.getBuzDate());
				}
			}// for end
		}
	}

	/*
	 * 执行业务前修改调度表数据
	 */
	private boolean updateSchedulingControlBefore(List<SchedulingControl> scList) {
		if (scList != null && scList.size() > 0) {
			SchedulingControl _sc = null;
			for (SchedulingControl schedulingControl : scList) {
				_sc = new SchedulingControl();
				_sc.setId(schedulingControl.getId());
				_sc.setCrmTime(new Date());
				_sc.setState("2");// 异常
				this.schedulingControlService.update(_sc);
			}
		}
		return true;
	}

	/*
	 * 执行业务后修改调度表数据
	 */
	private boolean updateSchedulingControlEnd(List<SchedulingControl> scList) {
		if (scList != null && scList.size() > 0) {
			SchedulingControl _sc = null;
			for (SchedulingControl schedulingControl : scList) {
				_sc = new SchedulingControl();
				_sc.setId(schedulingControl.getId());
				_sc.setCrmEndTime(new Date());
				_sc.setState("1");// 正常
				this.schedulingControlService.update(_sc);
			}
		}
		return true;
	}
	 
	/**
	 * 
	 * <p>
	 * Description:把散客生成定时器分离，单独执行<br />
	 * </p>
	 * @author 李盛
	 * @version 0.1 2012-9-26
	 * void
	 */
	public void executePotentialCustTimer(){
		List<SchedulingControl> schedulingControlList = this.schedulingControlService
				.searchNeedExecuteList();
		if (schedulingControlList != null && schedulingControlList.size() > 0) {
			logger.info(new Date() + "，获得任务调度记录数为：" + schedulingControlList.size());
			for (SchedulingControl sc : schedulingControlList) {
				String tableName = sc.getTableName();
				Date buzDate = sc.getBuzDate();
				logger.info("表名为：" + tableName + "，业务时间为：" + buzDate);

				List<SchedulingControl> scList = null;
				scList = new ArrayList<SchedulingControl>();
				scList.add(sc);
				// 维护crm开始执行时间及状态
				this.updateSchedulingControlBefore(scList);
				
				try {
					if (tableName.equals(TimerSchedulingControlEnum.T_CRM_DISPERSECLIENT.getStr())) {
						System.out.println("-----------begin---------------");
						logger.info("散客生成执行开始");
						// 散客新增
						
						// 维护crm结束执行时间及状态
						this.updateSchedulingControlEnd(scList);
						
						logger.info("散客生成执行完了");
						System.out.println("-----------end---------------");
					} 
				} catch (Exception e) {
					logger.error(tableName + " execute error", e);

					// 把e.printStackTrace转换成字符串
					Writer writer = new StringWriter();
					PrintWriter printWriter = new PrintWriter(writer);
					e.printStackTrace(printWriter);
					logger.error(tableName + "：" + writer);

					// 给相关开发员发邮件
					String _email = schedulingControlService.searchValueByKey(tableName);
					logger.info(_email);
					if (_email != null && !"".equals(_email)) {
						String[] _to = _email.split(";");
						try {
							MailInfo mi = new MailInfo("定时任务执行出错了，表名为："+ tableName + "，业务时间为：" + buzDate,
									writer.toString(), mailSenderService
											.getUserName(), _to);
							mailSenderService.sendExtranetMail(mi);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}
	}

	/*
	 * 执行
	 */
	private void execute(final String tableName,
			final List<SchedulingControl> scList, final Date buzDate) {
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		// 维护crm开始执行时间及状态
//		this.updateSchedulingControlBefore(scList);
//
//		synchronized (TimerSchedulingControl.class) {
//			if (threadPool.isTerminated()) {
//				this.init();
//			}
//			threadPool.submit(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						TimerSchedulingControl.this.executeBuz(tableName,
//								scList, buzDate);
//					} catch (Exception e) {
//						logger.error(tableName + " execute error", e);
//
//						// 把e.printStackTrace转换成字符串
//						Writer writer = new StringWriter();
//						PrintWriter printWriter = new PrintWriter(writer);
//						e.printStackTrace(printWriter);
//						logger.error(tableName + "：" + writer);
//
//						// 给相关开发员发邮件
//						String _email = schedulingControlService
//								.searchValueByKey(tableName);
//						logger.info(_email);
//						if (_email != null && !"".equals(_email)) {
//							String[] _to = _email.split(";");
//							try {
//								MailInfo mi = new MailInfo("定时任务执行出错了，表名为："
//										+ tableName + "，业务时间为：" + buzDate,
//										writer.toString(), mailSenderService
//												.getUserName(), _to);
//								mailSenderService.sendExtranetMail(mi);
//							} catch (Exception e1) {
//								e1.printStackTrace();
//							}
//
//						}
//					}
//				}
//			});
//		}
	}

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
//			this.planScheduleTimer11.processMonthlyMaintainCustomer();
//
//			// 维护crm结束执行时间及状态
//			this.updateSchedulingControlEnd(scList);
//		} else if (tableName
//				.equals(TimerSchedulingControlEnum.T_CRM_DELIVEROVERDUE
//						.getStr())) {
//			// 固定计划创建任务（日计划）
//			this.planScheduleTimer11.processDailyMaintainCustomer();
//
//			// 维护crm结束执行时间及状态
//			this.updateSchedulingControlEnd(scList);
//		} else if (tableName
//				.equals(TimerSchedulingControlEnum.T_CRM_WAYBILLCLIENT.getStr())) {
//			System.out.println("-----------begin---------------");
//			//如果是白天，就跳过执行
////			if(isDate()){
////				return;
////			}
//			// 到达客户潜客生成
//			this.timerPotentialCustomer.timerArrivalCustomerList(buzDate);
//			System.out.println("-----------end---------------");
//			// 维护crm结束执行时间及状态
//			this.updateSchedulingControlEnd(scList);
//		} 
////		else if (tableName
////				.equals(TimerSchedulingControlEnum.T_CRM_DISPERSECLIENT
////						.getStr())) {
////			System.out.println("-----------begin---------------");
////			//如果是白天，就跳过执行
////			if(isDate()){
////				return;
////			}
////			// 散客新增
////			this.timerPotentialCustomer.createScatterCustomer(buzDate);
////			
////			// 维护crm结束执行时间及状态
////			this.updateSchedulingControlEnd(scList);
////			System.out.println("-----------end---------------");
////		} 
//		else if (tableName
//				.equals(TimerSchedulingControlEnum.T_CRM_MEMBERUPGRADE.getStr())) {
//			System.out.println("-----------begin---------------");
//			//如果是白天，就跳过执行
////			if(isDate()){
////				return;
////			}
//			// 会员升级数据加工
//			timeDataProcessTimer.memberupgradeDataProcess(buzDate);
//			// 会员升级
//			this.timerMemberCustomer.timerMemberUpgrade(buzDate);
//
//			// 维护crm结束执行时间及状态
//			this.updateSchedulingControlEnd(scList);
//			System.out.println("-----------end---------------");
//		} else if (tableName.equals(TimerSchedulingControlEnum.T_CRM_MEMBERFALL
//				.getStr())) {
//			System.out.println("-----------begin---------------");
//			//如果是白天，就跳过执行
////			if(isDate()){
////				return;
////			}
//			// 会员降级数据加工
//			timeDataProcessTimer.memberfailDataProcess(buzDate);
//			// 会员降级
//			this.timerMemberCustomer.timerMemberDemotion(buzDate);
//
//			// 维护crm结束执行时间及状态
//			this.updateSchedulingControlEnd(scList);
//			System.out.println("-----------end---------------");
//		} else if (tableName
//				.equals(TimerSchedulingControlEnum.T_CRM_INTEGRALDETAIL
//						.getStr())) {
//			System.out.println("-----------begin---------------");
//			//如果是白天，就跳过执行
////			if(isDate()){
////				return;
////			}
//			// 积分
//			timerWaybillIntegral.timerWaybillIntegral();
//
//			// 维护crm结束执行时间及状态
//			this.updateSchedulingControlEnd(scList);
//			System.out.println("-----------end---------------");
//		} 
////		else if (tableName
////				.equals(TimerSchedulingControlEnum.T_CRM_NOTMEMBERTOMEMBER
////						.getStr())) {
////			System.out.println("-----------begin---------------");
////			//如果是白天，就跳过执行
////			if(isDate()){
////				return;
////			}
////			// 散客升级数据加工
////			timeDataProcessTimer.upgradeListDataProcess(buzDate);
////
////			// 维护crm结束执行时间及状态
////			this.updateSchedulingControlEnd(scList);
////			System.out.println("-----------end---------------");
////		}
//
//	}

	/**
	 * @description 校验定时器执行的时间是否白天.  
	 * @author 潘光均
	 * @version 0.1 2012-9-19
	 * @param 
	 *@date 2012-9-19
	 * @return void
	 * @update 2012-9-19 下午4:06:16
	 */
	private boolean isDate() {
		int hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if (5<hours&&hours<23) {
			return true;
		}
		return false;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		threadPool.shutdown();
	}

}
