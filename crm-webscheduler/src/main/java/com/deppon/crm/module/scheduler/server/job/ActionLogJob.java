package com.deppon.crm.module.scheduler.server.job;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.common.file.util.ExcelExporter;
import com.deppon.crm.module.common.file.util.PropertiesUtil;
import com.deppon.crm.module.scheduler.server.service.impl.ActionLogService;
import com.deppon.crm.module.scheduler.server.service.impl.SchedulingControlService;
import com.deppon.crm.module.scheduler.shared.domain.ActionLog;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * 
 * <p>
 * Description:ActionLog 日志监控<br />
 * </p>
 * 
 * @title ActionLogJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author 华龙
 * @version 0.1 2013-2-20
 */
public class ActionLogJob extends GridJob {
	/**
	 * 
	 * <p>
	 * Description：日期转换<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-11-19
	 * @param date
	 * @param addDay
	 * @return Date
	 */
	public static Date getDateMidnight(Date date, int addDay) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(5, addDay);
			date = df.parse(df.format(cal.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * <p>
	 * Description:action日志定时方法<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-11-19 void
	 */
	public void actionLogStatus() {
		// 注入Action日志Service
		ActionLogService actionLogService = getBean("actionLogService",
				ActionLogService.class);
		SchedulingControlService schedulingControlService = getBean(
				"schedulingControlService", SchedulingControlService.class);
		MailSenderService mailSenderService = getBean(
				"mailSenderService", MailSenderService.class);
		// 获取日志
		List actionLogList = actionLogService.findActionLogList();
		// 如果没有日志就返回
		if (actionLogList.size() == 0) {
			return;
		}

		String path = PropertiesUtil.getInstance()
				.getProperty("excelExportTemplatePath").trim();

		Date fromDate = getDateMidnight(new Date(), -1);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String subject = "从" + sf.format(fromDate) + "到"
				+ sf.format(new Date()) + "Action状态监控";
		String content = "尊敬的各位领导：附件是：" + sf.format(fromDate)
				+ " 0:00:00到23:59:59的Action状态监控，请您及时查阅！";
		String fileName = "CRM_Action_log.xlsx";
		String filePath = exportActionLogToExcel(actionLogList, fileName);
		String _email = schedulingControlService
				.searchValueByKey("T_CRM_ACTIONLOG");

		if ((_email != null) && (!"".equals(_email))) {
			String[] _to = _email.split(";");
			try {
				MailInfo mi = new MailInfo();
				mi.setFrom(mailSenderService.getUserName());

				mi.setTo(_to);
				mi.setSubject(subject);
				mi.setContent(content);
				File file = new File(filePath);
				File[] files = { file };
				mi.setAttachments(files);
				mailSenderService.sendExtranetMail(mi);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * <p>
	 * Description:把数据导出为Excel<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-11-19
	 * @param actionLoglist
	 * @param fileName
	 * @return String
	 */
	public String exportActionLogToExcel(List<ActionLog> actionLoglist,
			String fileName) {
		String createPath = PropertiesUtil.getInstance()
				.getProperty("excelExportFilePath").trim();

		ExcelExporter exporter = new ExcelExporter();

		List objList = transActionLog(actionLoglist);
		exporter.exportExcel(getHeaders(), objList, "ActionLog", createPath,
				fileName);

		String realPath = createPath + "/" + fileName;
		return realPath;
	}

	/**
	 * 
	 * <p>
	 * Description:设置Excel的列头<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-11-19
	 * @return List<String>
	 */
	public List<String> getHeaders() {

		List headers = new ArrayList();
		headers.add("模块名");
		headers.add("请求");
		headers.add("次数");
		headers.add("请求总时长");
		headers.add("平均时长");
		headers.add("最长时长");
		return headers;
	}

	/**
	 * 
	 * <p>
	 * Description:封装Excel数据<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-11-19
	 * @param actionLoglist
	 * @return List<List<Object>>
	 */
	public List<List<Object>> transActionLog(List<ActionLog> actionLoglist) {
		List objList = new ArrayList();
		for (ActionLog actionLog : actionLoglist) {
			List row = new ArrayList();
			row.add(actionLog.getModuleName());
			row.add(actionLog.getRequestUri());
			row.add(actionLog.getCountRequest());
			row.add(actionLog.getReqdurationTimes());
			row.add(actionLog.getReqdurationAvgTimes());
			row.add(actionLog.getMaxreqdurationTime());
			objList.add(row);
		}
		return objList;
	}
	
	/**
	 * 
	 * <p>
	 * Description: 调用actionLog 任务<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-21
	 */
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("begin time:"+new Date() + ":ActionLogJob");
		this.actionLogStatus();
		System.out.println("end time:"+new Date() + ":ActionLogJob");
	}
}