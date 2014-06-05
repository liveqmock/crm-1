package com.deppon.crm.module.logmoniting.utils;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.logmoniting.server.dao.IExceptionErrorCodeDao;
import com.deppon.crm.module.logmoniting.server.dao.ISynchroLogDao;
import com.deppon.crm.module.logmoniting.server.dao.impl.ExceptionErrorCodeDao;
import com.deppon.crm.module.logmoniting.server.dao.impl.LogInfoDao;
import com.deppon.crm.module.logmoniting.server.dao.impl.SynchroLogDao;
import com.deppon.crm.module.logmoniting.server.manager.IExceptionErrorCodeManager;
import com.deppon.crm.module.logmoniting.server.manager.impl.ExceptionErrorCodeManager;
import com.deppon.crm.module.logmoniting.server.manager.impl.LogInfoManager;
import com.deppon.crm.module.logmoniting.server.manager.impl.MailManager;
import com.deppon.crm.module.logmoniting.server.service.IExceptionErrorCodeService;
import com.deppon.crm.module.logmoniting.server.service.impl.ExceptionErrorCodeService;
import com.deppon.crm.module.logmoniting.server.service.impl.LogInfoService;

public class BeanUtils {

	public static ConfigurableApplicationContext  context = 
			new ClassPathXmlApplicationContext(
	"com/deppon/crm/module/logmoniting/server/META-INF/spring.xml");
	
	public static  LogInfoDao logDao = null;
	public static MailManager mailManager;
	public static LogInfoManager logInfoManager;
	public static LogInfoService logInfoService;
	public static IExceptionErrorCodeManager exceptionErrorCodeManager;
	public static IExceptionErrorCodeService exceptionErrorCodeService;
	public static IExceptionErrorCodeDao exceptionErrorCodeDao;
	public static ISynchroLogDao synchroLogDao;
	
	static{
		logDao = context.getBean(LogInfoDao.class);
		mailManager = context.getBean(MailManager.class);
		logInfoManager = context.getBean(LogInfoManager.class);
		logInfoService = context.getBean(LogInfoService.class);
		exceptionErrorCodeManager = context.getBean(ExceptionErrorCodeManager.class);
		exceptionErrorCodeService = context.getBean(ExceptionErrorCodeService.class);
		exceptionErrorCodeDao = context.getBean(ExceptionErrorCodeDao.class);
		synchroLogDao = context.getBean(SynchroLogDao.class);
	}
}
