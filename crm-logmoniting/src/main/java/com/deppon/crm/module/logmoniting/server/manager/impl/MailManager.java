package com.deppon.crm.module.logmoniting.server.manager.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.deppon.crm.module.common.file.util.ExcelExporter;
import com.deppon.crm.module.common.file.util.PropertiesUtil;
import com.deppon.crm.module.logmoniting.server.utils.DateUtil;
import com.deppon.crm.module.logmoniting.server.utils.ValidateUtil;
import com.deppon.crm.module.logmoniting.shared.domain.ActionLogInfo;
import com.deppon.crm.module.logmoniting.shared.domain.BasicLoginfo;
import com.deppon.crm.module.logmoniting.shared.domain.Constant;
import com.deppon.crm.module.logmoniting.shared.domain.ErrorCodeInfo;
import com.deppon.crm.module.logmoniting.shared.domain.LogStatistics;
import com.deppon.crm.module.logmoniting.shared.domain.ReqLogInfo_averageCount;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;
import com.mongodb.DBObject;

/**   
 * @Description:邮件发送处理类<br />
 * @title MailManager.java
 * @package com.deppon.crm.module.logmoniting.server.manager.impl 
 * @author CoCo
 * @version 0.1 2013-7-1
 */
public class MailManager {
	private static Logger log = Logger.getLogger(MailManager.class);
	//邮件发送
	private MailSenderService mailSenderService;
	
	/**
	 * Description:mailSenderService<br />
	 * @author CoCo
	 * @version 0.1 2013-7-1
	 */
	public MailSenderService getMailSenderService() {
		return mailSenderService;
	}

	/**
	 * Description:mailSenderService<br />
	 * @author CoCo
	 * @version 0.1 2013-7-1
	 */
	public void setMailSenderService(MailSenderService mailSenderService) {
		this.mailSenderService = mailSenderService;
	}
	
	
	/**
	 * @Description:
	 * 每小时统计一次每个Action的请求次数，如果请求超过了前一天该时段请求及浮动计算的值，
	 * 就以邮件的形式通知开发人员改Action出现异常请求。<br />
	 * @author CoCo
	 * @version 0.1 2013-7-15
	 * @param actionLogInfos 需要发送邮件的list数据
	 */
	public  void actionInfoErrorForOneHour(List<ActionLogInfo> actionLogInfos){
		if (actionLogInfos != null && actionLogInfos.size() > 0) {
		
			Date date = new Date();
			Date beforeDay = DateUtil.returnPreviousDay(date, 1);
			//邮件主题
			StringBuffer subject = new StringBuffer();
			subject.append("时间:").append(DateUtil.getDateStringByFormat(DateUtil.returnPreviousHour(beforeDay, 1), "yyyy-MM-dd hh"))
			.append("-").append(DateUtil.getDateStringByFormat(beforeDay, "hh")).append("点").append("与")
			.append(DateUtil.getDateStringByFormat(DateUtil.returnPreviousHour(date, 1), "yyyy-MM-dd hh"))
			.append("-").append(DateUtil.getDateStringByFormat(date, "hh")).append("点").append("action请求预警详细信息");
			
			//邮件内容
			StringBuffer content = new StringBuffer();
			content.append("尊敬的各位领导同事:\n").append(subject.toString()).append("详细情况;\n").
			append("附件信息为:action为黑名单,且已经达到预警发送邮件的请求日志信息,请查收验证!");
			//文件路径
			String filePath = null;
			//封装成xlsx文档路径
			filePath = exportActionLogToExcel(actionLogInfos,null,"ActionLogInfo.xlsx",Constant.LOGININFO_ERROR_ONEHOUR);
			//需要发送的文件结果
			List<File> files = new ArrayList<File>();
			File file = null;
			if (!StringUtils.isEmpty(filePath)) {
				file = new File(filePath);
				files.add(file);
			}
			//收件人
//			String _email = Constant.MAIL_PERSON; 
			String _email = Constant.MAILPERSON;
			String from = this.mailSenderService.getUserName();
			SendEmail(from,_email,subject.toString(),content.toString(),files);
		}else {
			return ;
		}
	}
	/**
	 * @Description:
	 * 每天根据每个Action设置的请求时间的最大值，和请求次数的最大值，
	 * 对每天汇总的所有请求的请求时间和请求次数的统计进行对比，如果发现请求次数或者请求时长超过设置的最大值，
	 * 则以邮件的形式通知开发人员说明Action请求时间过长或者请求次数过多。<br />
	 * @author CoCo
	 * @version 0.1 2013-7-22
	 * @param actionLogInfos
	 * void
	 */
	public void compareActionAverageContrastByOneDay(List<ActionLogInfo> actionLogInfos) {
		if (actionLogInfos != null && actionLogInfos.size() > 0) {
			Date date = new Date();
			Date newDay = DateUtil.returnPreviousDay(date, 1);
			//邮件主题
			StringBuffer subject = new StringBuffer();
			subject.append("时间:").append(DateUtil.getDateStringByFormat(newDay, "yyyy-MM-dd"))
			.append("的action预警信息");
			//邮件内容
			StringBuffer content = new StringBuffer();
			content.append("尊敬的各位领导同事:\n").append(subject.toString()).append("详细情况;\n").
			append("附件信息为具体统计的详细信息,").append("\n具体业务规则为:" +
					"每天根据每个Action设置的请求时间的最大值，" +
					"和请求次数的最大值，对每天汇总的所有请求的请求时间和请求次数的统计进行对比，" +
					"如果发现请求次数或者请求时长超过设置的最大值，" +
					"则以邮件的形式通知开发人员说明Action请求时间过长或者请求次数过多。");
			//文件路径
			String filePath = null;
			//封装成xlsx文档路径
			filePath = exportActionLogToExcel(actionLogInfos,null,"AverageActionLogInfo.xlsx",Constant.AVERAGE_LOGINFO_ERROR_ONEDAY);
			//需要发送的文件结果
			List<File> files = new ArrayList<File>();
			File file = null;
			if (!StringUtils.isEmpty(filePath)) {
				file = new File(filePath);
				files.add(file);
			}
			//收件人
//			String _email = Constant.MAIL_PERSON; TODO
			String _email = Constant.MAILPERSON;
			String from = this.mailSenderService.getUserName();//发送者
			SendEmail(from,_email,subject.toString(),content.toString(),files);
		}else {
			return ; 
		}
	}
	
	/**
	 * @Description:
	 * 根据每天统计的前十位的Action请求次数和前一天前十位的Action请求次数进行对比，
	 * 如果两天前十位的Action名称不一样，则以邮件的方式通知开发人员确认是否存在问题。<br />
	 * @author CoCo
	 * @version 0.1 2013-7-17
	 * void
	 */
	public void CompareTheTopTenActionLogInfoByOneDay(List<ActionLogInfo> timeCursorList,List<ActionLogInfo> countCursorList){
		if ((timeCursorList !=null && timeCursorList.size() <= 1)
				&& (countCursorList != null && countCursorList.size() <= 1)) {
			log.warn("There have no date,please check the right of data");
			return ;
		}
		Date date = new Date();
		Date newDay = DateUtil.returnPreviousDay(date, 1);
		Date oldDay = DateUtil.returnPreviousDay(date, 2);
		//邮件主题
		StringBuffer subject = new StringBuffer();
		subject.append("时间:").append(DateUtil.getDateStringByFormat(oldDay, "yyyy-MM-dd"))
		.append("与").append(DateUtil.getDateStringByFormat(newDay, "yyyy-MM-dd"))
		.append("的前十action统计详细信息");
		//邮件内容
		StringBuffer content = new StringBuffer();
		content.append("尊敬的各位领导同事:\n").append(subject.toString()).append("详细情况;\n").
		append("附件信息:1.TimeCursorList为昨天和前天_请求时间分别前十的Action详细信息;\n")
		.append("2.CountCursorList为昨天和前天_请求次数分别前十的Action详细信息。\n")
		.append("ps:Action名字为间隔以上数据昨天前十数据,间隔以下为前天前十数据")
		.append("前十请求Action信息;\n").append("对比规则为:根据每天统计的前十位的Action请求次数或请求平均时间和前一天前十位的Action请求次数或请求平均时间进行对比," +
					"如果两天前十位的Action名称不一样,则发送邮件.");
		//文件路径
		String timeCursorListFilePath = null;
		if (timeCursorList != null && timeCursorList.size() > 1) {
			//封装成xlsx文档路径
			timeCursorListFilePath = exportActionLogToExcel(timeCursorList,null,"TimeCursorList.xlsx",Constant.TOPTEN_LOGINFO_ERROR_ONEDAY);
		}
		String countCursorListFilePath = null;
		if (countCursorList != null && countCursorList.size() > 1) {
			countCursorListFilePath = exportActionLogToExcel(countCursorList,null,"CountCursorList.xlsx",Constant.TOPTEN_LOGINFO_ERROR_ONEDAY);
		}
		//需要发送的文件结果
		List<File> files = new ArrayList<File>();
		File file = null;
		if (!StringUtils.isEmpty(timeCursorListFilePath)) {
			file = new File(timeCursorListFilePath);
			files.add(file);
		}
		if (!StringUtils.isEmpty(countCursorListFilePath)) {
			file = new File(countCursorListFilePath);
			files.add(file);
		}
		//收件人
//			String _email = Constant.MAIL_PERSON; TODO
		String _email = Constant.MAILPERSON;
			String from = this.mailSenderService.getUserName();//发送者
			SendEmail(from,_email,subject.toString(),content.toString(),files);
	}
	
	/**
	 * @Description:定时器执行。异常编码的收集，每半天执行一次，并相应的发送邮件
	 *  根据收集的非正常的业务异常和系统日志记录的日志中的异常进行比对，若发
		发现日志中存在这个异常，以邮件的方式通知开发人员进行问题跟踪及解决。
		把异常出现的次数，以及出现异常的详细信息一起发送。
	 * @author CoCo
	 * @version 0.1 2013-7-30
	 * @param errorCodeInfos
	 * void
	 */
	public void completeExceptionErrorCode(List<ErrorCodeInfo> errorCodeInfos){
		if (errorCodeInfos != null && errorCodeInfos.size() > 0) {
			Date date = new Date();
			Date oldDate = DateUtil.returnPreviousHour(date, 12);
			
			//邮件主题
			StringBuffer subject = new StringBuffer();
			subject.append("时间:").append(oldDate.toLocaleString())
			.append("至").append(date.toLocaleString())
			.append("的异常ErrorCode收集详细信息");
			//邮件内容
			StringBuffer content = new StringBuffer();
			content.append("尊敬的各位领导同事:\n").
			append("附件信息为").append(oldDate.toLocaleString())
			.append("至").append(date.toLocaleString()).append("的异常ErrorCode收集详细信息;\n")
			.append("业务规则为:异常编码的收集，每半天执行一次，并相应的发送邮件 根据收集的非正常的业务异常和系统日志记录的日志中的异常进行比对，\n" +
					"若发现日志中存在这个异常，以邮件的方式通知开发人员进行问题跟踪及解决。\n把异常出现的次数，以及出现异常的详细信息一起发送。" );
			//文件路径
			String filePath = null;
			//封装成xlsx文档路径
			filePath = exportActionLogToExcel(null,errorCodeInfos,"ExceptionErrorCodeInfo.xlsx",null);
			//需要发送的文件结果
			List<File> files = new ArrayList<File>();
			File file = null;
			if (!StringUtils.isEmpty(filePath)) {
				file = new File(filePath);
				files.add(file);
			}
			//收件人
			//String _email = Constant.MAIL_PERSON; TODO
			String _email = Constant.MAILPERSON;
			String from = this.mailSenderService.getUserName();//发送者
			SendEmail(from,_email,subject.toString(),content.toString(),files);
		}else {
			log.warn("There have no data to send email!");
			return ;
		}
	}
	
	/**
	 * @Description:邮件发送<br />
	 * @author CoCo
	 * @version 0.1 2013-7-17
	 * @param from 发送者
	 * @param to 发给谁
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param files 附件
	 * void
	 */
	private  void SendEmail(String from,String _email,String subject,String content,List<File> files){
		if (StringUtils.isEmpty(from) || StringUtils.isEmpty(_email)
				|| StringUtils.isEmpty(subject) || StringUtils.isEmpty(content)
				||(files == null)) {
			return ;
		}else {
			if (!StringUtils.isEmpty(_email)) {
				String[] _to = _email.split(";");
				MailInfo mi = new MailInfo();
				mi.setFrom(from);
				mi.setTo(_to);
				mi.setSubject(subject);
				mi.setContent(content);
				if (files.size() <= 0) {
					mi.setAttachments(null);
				} else {
					mi.setAttachments(initFiles(files));
				}
				try {
					this.mailSenderService.sendExtranetMail(mi);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * <p>
	 * Description:转化成数组格式的文件内容,目的是不让抛异常<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-2
	 * @param files
	 * @return File[]
	 */
	private File[] initFiles(List<File> list) {
		if (ValidateUtil.objectIsEmpty(list)) {
			return null;
		}
		File[] files = new File[list.size()];
		for (int i = 0; i < files.length; i++) {
				files[i] = list.get(i);
			}
		return files;
	}
	/**
	 * <p>
	 * Description:封装成xlsx文档路径<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-1
	 * @param list
	 * @param fileName
	 * @return String
	 */
	public static String exportActionLogToExcel(List<ActionLogInfo> list,
			List<ErrorCodeInfo> errorCodeInfos,String fileName,String type) {
		String createPath = PropertiesUtil.getInstance().getProperty("excelExportFilePath").trim();

		ExcelExporter exporter = new ExcelExporter();
		if (list != null && list.size() > 0 && errorCodeInfos == null) {
			//封装Excel数据
			List objList = transDataResult(list,type);
			//封装Excel文档
			exporter.exportExcel(getHeaders(type), objList, "ActionLogInfo", createPath,fileName);
		}
		if (errorCodeInfos != null && errorCodeInfos.size() > 0 && list == null) {
			List objList = new ArrayList();
			for (ErrorCodeInfo errorCodeInfo : errorCodeInfos) {
				List row = new ArrayList();
				row.add(errorCodeInfo.getModuleName());
				row.add(errorCodeInfo.getErrorCode());
				row.add(errorCodeInfo.getCount());
				row.add(errorCodeInfo.getExceptionInfo());
				objList.add(row);
			}
			List headers = new ArrayList();
			headers.add("模块名");
			headers.add("异常编码");
			headers.add("总共次数");
			headers.add("异常信息");
			//封装Excel文档
			exporter.exportExcel(headers, objList, "errorCodeInfo", createPath,fileName);
		}
		//文件路径
		String realPath = createPath + "/" + fileName;
		return realPath;
	}
	
	/**
	 * 
	 * <p>
	 * Description:设置Excel的列头 根据type设置<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-1
	 * 	 * @param type
	 * @return
	 * List<String>
	 */
	public static List<String> getHeaders(String type) {
		List headers = new ArrayList();
		if (Constant.LOGININFO_ERROR_ONEHOUR.equals(type)) {
			//一小时发送的附件表头
			headers.add("模块名");
			headers.add("Action名称");
			headers.add("Action_URL");
			headers.add("请求时间");
			headers.add("时间浮动值");
			headers.add("请求次数");
			headers.add("次数浮动值");
			headers.add("old请求平均时间");
			headers.add("old请求次数");
			headers.add("old请求最大时间");
			headers.add("new请求平均时间");
			headers.add("new请求次数");
			headers.add("new请求最大时间");
		}
		if (Constant.TOPTEN_LOGINFO_ERROR_ONEDAY.equals(type)) {
			//一小时发送的附件表头
			headers.add("模块名");
			headers.add("Action名称");
			headers.add("Action_URL");
			headers.add("Action请求时间(基础)");
			headers.add("Action时间浮动值(基础)");
			headers.add("Action请求次数(基础)");
			headers.add("Action次数浮动值(基础)");
			headers.add("当天请求平均时间");
			headers.add("当天请求次数");
			headers.add("当天请求最大时间");
		}
		if (Constant.AVERAGE_LOGINFO_ERROR_ONEDAY.equals(type)) {
			//一小时发送的附件表头
			headers.add("模块名");
			headers.add("Action名称");
			headers.add("Action_URL");
			headers.add("Action请求时间");
			headers.add("Action时间浮动值");
			headers.add("Action请求次数");
			headers.add("Action次数浮动值");
			headers.add("昨天请求平均时间");
			headers.add("昨天请求平均次数");
			headers.add("昨天请求最大时间");
		}
		return headers;
	}
	
	/**
	 * <p>
	 * Description:封装Excel数据,type为类型<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-1
	 * @param list
	 * @param type
	 * @return List<List<Object>>
	 */
	public static List<List<Object>> transDataResult(List<ActionLogInfo> list,String type) {
		List objList = new ArrayList();
		if (Constant.LOGININFO_ERROR_ONEHOUR.equals(type)) {
			for (ActionLogInfo actionLogInfo : list) {
				List row = new ArrayList();
				row.add(actionLogInfo.getModulename());
				row.add(actionLogInfo.getActionName());
				row.add(actionLogInfo.getUrl());
				row.add(actionLogInfo.getBaseTime());
				row.add(actionLogInfo.getTimeFloat());
				row.add(actionLogInfo.getBaseCount());
				row.add(actionLogInfo.getCountFloat());
				row.add(actionLogInfo.getOldAvgRequestTime());
				row.add(actionLogInfo.getOldRequestCount());
				row.add(actionLogInfo.getOldLongestRequestTime());
				row.add(actionLogInfo.getNewAvgRequestTime());
				row.add(actionLogInfo.getNewRequestCount());
				row.add(actionLogInfo.getNewLongestRequestTime());
				objList.add(row);
			}
		}
		if (Constant.TOPTEN_LOGINFO_ERROR_ONEDAY.equals(type)) {
			for (ActionLogInfo actionLogInfo : list) {
				List row = new ArrayList();
				row.add(actionLogInfo.getModulename());
				row.add(actionLogInfo.getActionName());
				row.add(actionLogInfo.getUrl());
				row.add(actionLogInfo.getBaseTime());
				row.add(actionLogInfo.getTimeFloat());
				row.add(actionLogInfo.getBaseCount());
				row.add(actionLogInfo.getCountFloat());
				row.add(actionLogInfo.getNewAvgRequestTime());
				row.add(actionLogInfo.getNewRequestCount());
				row.add(actionLogInfo.getNewLongestRequestTime());
				objList.add(row);
			}
		}
		
		if (Constant.AVERAGE_LOGINFO_ERROR_ONEDAY.equals(type)) {
			for (ActionLogInfo actionLogInfo : list) {
				List row = new ArrayList();
				row.add(actionLogInfo.getModulename());
				row.add(actionLogInfo.getActionName());
				row.add(actionLogInfo.getUrl());
				row.add(actionLogInfo.getBaseTime());
				row.add(actionLogInfo.getTimeFloat());
				row.add(actionLogInfo.getBaseCount());
				row.add(actionLogInfo.getCountFloat());
				row.add(actionLogInfo.getNewAvgRequestTime());
				row.add(actionLogInfo.getNewRequestCount());
				row.add(actionLogInfo.getNewLongestRequestTime());
				objList.add(row);
			}
		}
		return objList;
	}
	
	/**
	 * @Description: 每小时统计一次每个Action的请求次数，如果请求超过了前一天该时段请求及浮动计算的值，
	 * 就以邮件的形式通知开发人员改Action出现异常请求。
	 * --此处为封装数据<br />
	 * @author CoCo
	 * @version 0.1 2013-7-13
	 * void
	 */
	public static void completeActionInfoForSendMailByOneHour(BasicLoginfo basicLoginfo,
			LogStatistics oldlogStatistics,LogStatistics newlogStatistics,
			ActionLogInfo actionLogInfo){
		if (ValidateUtil.objectIsEmpty(basicLoginfo) || ValidateUtil.objectIsEmpty(actionLogInfo) 
				|| ValidateUtil.objectIsEmpty(oldlogStatistics) || ValidateUtil.objectIsEmpty(newlogStatistics)) {
			log.error("some objects are null");
			//其中有一个对象为空,则不封装数据
			return ;
		}
		actionLogInfo.setActionName(basicLoginfo.getActionName());
		actionLogInfo.setModulename(basicLoginfo.getModulename());
		actionLogInfo.setUrl(basicLoginfo.getUrl());
		actionLogInfo.setBaseCount(basicLoginfo.getBaseCount());
		actionLogInfo.setBaseTime(basicLoginfo.getBaseTime());
		actionLogInfo.setCountFloat(basicLoginfo.getCountFloat());
		actionLogInfo.setTimeFloat(basicLoginfo.getTimeFloat());
//		actionLogInfo.setMailPerson(basicLoginfo.getMailPerson());邮箱已写死 TODO
		actionLogInfo.setNewAvgRequestTime(newlogStatistics.getAvgRequestTime());
		actionLogInfo.setNewLongestRequestTime(newlogStatistics.getMaxRequestTime());
		actionLogInfo.setNewRequestCount(newlogStatistics.getRequestCount());
		actionLogInfo.setOldAvgRequestTime(oldlogStatistics.getAvgRequestTime());
		actionLogInfo.setOldLongestRequestTime(oldlogStatistics.getMaxRequestTime());
		actionLogInfo.setOldRequestCount(oldlogStatistics.getRequestCount());
	}
	
	/**
	 * @Description:发送邮件对象封装<br />
	 * @author CoCo
	 * @version 0.1 2013-7-17
	 * @param basicLoginfo
	 * @param logStatistics
	 * @param actionLogInfo
	 * void
	 */
	public static void completeTopTenActionInfoForSendMailByOneDay(BasicLoginfo basicLoginfo,DBObject dbObject,ActionLogInfo actionLogInfo){
		actionLogInfo.setActionName(basicLoginfo.getActionName());
		actionLogInfo.setModulename(basicLoginfo.getModulename());
		actionLogInfo.setUrl(basicLoginfo.getUrl());
		actionLogInfo.setBaseCount(basicLoginfo.getBaseCount());
		actionLogInfo.setBaseTime(basicLoginfo.getBaseTime());
		actionLogInfo.setCountFloat(basicLoginfo.getCountFloat());
		actionLogInfo.setTimeFloat(basicLoginfo.getTimeFloat());
		actionLogInfo.setNewAvgRequestTime(parseValueToLong(dbObject.get("avgRequestTime")));
		actionLogInfo.setNewLongestRequestTime(parseValueToLong(dbObject.get("maxRequestTime")));
		actionLogInfo.setNewRequestCount(parseValueToLong(dbObject.get("requestCount")));
	}
	
	/**
	 * @Description:将数据转换成Long类型<br />
	 * @author CoCo
	 * @version 0.1 2013-8-1
	 * @param object
	 * @return long
	 */
	private static long parseValueToLong(Object object){
		long temp = 0;
		if (object instanceof Integer) {
			temp = (Long)object;
		}
		if (object instanceof Double) {
			double d = (Double)object;
			temp = (long)d;
		}
		if (object instanceof Long) {
			temp = (Long)object;
		}
		return temp;
	}
	/**
	 * @Description:对象封装
	 * 业务:每天根据每个Action设置的请求时间的最大值，
	 * 和请求次数的最大值，对每天汇总的所有请求的请求时间和请求次数的统计进行对比，
	 * 如果发现请求次数或者请求时长超过设置的最大值，则以邮件的形式通知开发人员说明Action请求时间过长或者请求次数过多<br />
	 * @author CoCo
	 * @version 0.1 2013-7-22
	 * void
	 */
	public static void completeActionAverageContrastByOneDay(ReqLogInfo_averageCount count,BasicLoginfo basicLoginfo,ActionLogInfo actionLogInfo){
		actionLogInfo.setActionName(basicLoginfo.getActionName());
		actionLogInfo.setModulename(basicLoginfo.getModulename());
		actionLogInfo.setUrl(basicLoginfo.getUrl());
		actionLogInfo.setBaseCount(basicLoginfo.getBaseCount());
		actionLogInfo.setBaseTime(basicLoginfo.getBaseTime());
		actionLogInfo.setCountFloat(basicLoginfo.getCountFloat());
		actionLogInfo.setTimeFloat(basicLoginfo.getTimeFloat());
		actionLogInfo.setNewAvgRequestTime(count.getAvgRequestTime());
		actionLogInfo.setNewLongestRequestTime(count.getMaxRequestTime());
		actionLogInfo.setNewRequestCount(count.getRequestCount());
	}
}
