package com.deppon.crm.module.scheduler.server.manager;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.deppon.crm.module.common.file.util.ExcelExporter;
import com.deppon.crm.module.common.file.util.FileUtil;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.scheduler.server.service.ISchedulingControlService;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;

public class TimerRecompense {
	private static final String EXCELNAME = "recompenseStatusCompare.xlsx";
	private RecompenseManager recompenseManager;

	public RecompenseManager getRecompenseManager() {
		return recompenseManager;
	}

	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}

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

	// 邮件
	private MailSenderService mailSenderService;

	public MailSenderService getMailSenderService() {
		return mailSenderService;
	}

	public void setMailSenderService(MailSenderService mailSenderService) {
		this.mailSenderService = mailSenderService;
	}

	public void recompenseReminderTimer() {
		System.out.println("TimerRecompense.generateTodoReminder()");
		recompenseManager.generateTodoReminder();
	}

	public static Date getDateMidnight(Date date, int addDay) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, addDay);
			date = df.parse(df.format(cal.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public void recompenseStatusCompare() {
		System.out.println("TimerRecompense.recompenseStatusCompare()");
		Date startDate = getDateMidnight(new Date(), -1);
		Date endDate = getDateMidnight(new Date(), 0);
		List<String> notInPaymentList = recompenseManager
				.getRecompenseInPayment(startDate, endDate);
		List<String> notPaidList = recompenseManager.getRecompensePaymented(
				startDate, endDate);
		if (notInPaymentList.size() == 0 && notInPaymentList.size() == 0) {
			return;
		}

		String path = com.deppon.crm.module.common.file.util.PropertiesUtil
				.getInstance().getProperty("excelExportTemplatePath").trim();
		String filePath = path + "/" + EXCELNAME;// 组织文件路径
		ExcelExporter exporter = new ExcelExporter();
		XSSFWorkbook wb = exporter.getExcel07Wb(filePath);// 获得工作薄
		for (int i = 0; i < notInPaymentList.size(); i++) {
			wb = exporter.setExcel07WbValue(wb, i + 1, 0,
					notInPaymentList.get(i));
		}
		for (int i = 0; i < notPaidList.size(); i++) {
			wb = exporter.setExcel07WbValue(wb, i + 1, 1, notPaidList.get(i));
		}

		// 生成日数据
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateData = "时间：从" + sf.format(startDate) + "到"
				+ sf.format(endDate);
		String subject = "从" + sf.format(startDate) + "到" + sf.format(endDate)
				+ "理赔单付款状态监控";
		String content = "各位领导：这是从" + sf.format(startDate) + "到"
				+ sf.format(endDate) + "理赔单付款状态监控";

		String createPath = com.deppon.crm.module.common.file.util.PropertiesUtil
				.getInstance().getProperty("excelExportFilePath").trim();
		String fileName = FileUtil.createFileName(".xlsx");
		String realPath = exporter.createExcelDecrypt(wb, createPath, fileName);
		// 给相关开发员发邮件
		String _email = schedulingControlService
				.searchValueByKey("T_CRM_RECOMPENSESTATUSCOMPARE");
		if (_email != null && !"".equals(_email)) {
			String[] _to = _email.split(";");
			try {
				MailInfo mi = new MailInfo();
				mi.setFrom(mailSenderService.getUserName());// 谁发
				mi.setTo(_to);// 发给谁
				mi.setSubject(subject);
				mi.setContent(content);
				File file = new File(realPath);
				File[] files = { file };
				mi.setAttachments(files);
				mailSenderService.sendExtranetMail(mi);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
