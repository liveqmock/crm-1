package com.deppon.crm.module.frameworkimpl.server.log;

import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;
import org.springframework.web.context.WebApplicationContext;

import com.deppon.crm.module.frameworkimpl.server.log.bean.LogInfo;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.components.logger.LogBuffer;
import com.deppon.foss.framework.server.context.WebApplicationContextHolder;

/**
 ******************************************* 
 * Copyright deppon.com. All rights reserved. Description: log4j的日志输出器 HISTORY
 * ID DATE PERSON REASON
 ******************************************** 
 * 1 2011-6-26 ztjie 新增
 ******************************************** 
 * 2 2012-07-10 WANGWENHU 修改
 ******************************************** 
 */
public class CrmBufferedAppender extends AppenderSkeleton {
	private LogHolder holder = LogHolder.getInstance();

	@Override
	public void close() {

	}

	@Override
	public boolean requiresLayout() {
		return true;
	}

	/**
	 * 记录日志
	 * 
	 * @see org.apache.log4j.AppenderSkeleton#append(org.apache.log4j.spi.LoggingEvent)
	 *      append
	 * @param event
	 * @since:0.7
	 */
	@Override
	protected void append(LoggingEvent event) {
		// if(true){
		// return;
		// }
		try {
			if (holder.getLogBuffer() == null) {
				String logName = event.getLoggerName();
				if (logName.startsWith("org.springframework.beans")) {
					// 系统启动的时候屏蔽spring加载Bean的日志，防止因Bean的注入导致的日志死循环；
					// 使用ConsoleAppender和File*Appender的日志不受影响；
					return;
				}
				WebApplicationContext wac = WebApplicationContextHolder
						.getWebApplicationContext();
				if (wac != null) {
					LogBuffer logBuffer = (LogBuffer) wac
							.getBean("performanceLog");
					holder.setLogBuffer(logBuffer);
				}
			}
			Level level = event.getLevel();
			if(level != null && "DEBUG".equalsIgnoreCase(level.toString())) {
				return ;
			}
			// 设置当前请求ID到当前线程中
			String requestId = Thread.currentThread().getName();
			// 日志对象
			LogInfo logInfo = new LogInfo();
			// 设置日志对象的请求ID
			logInfo.setRequestId(requestId);
			// 设置日志类型为性能日志
			logInfo.setLogType("performance");
			logInfo.setLogLevel(level.toString());
			logInfo.setCreateDate(new Date().getTime());
			// 日志信息 FIX:修正message为空的异常
			Object msgObj = event.getMessage();
			StringBuffer logInfoSb = new StringBuffer(msgObj == null ? ""
					: String.valueOf(msgObj));
			// // 是否异常
			String[] strs = event.getThrowableStrRep();
			// // 记录异常堆栈
			// // 取前面10个堆栈信息,并且只取前3000个字符串
			if (strs != null) {
				logInfo.setLogType("exception");
				logInfoSb.append(Arrays.toString(strs));
			}
			
			String errorCode = "";
			ThrowableInformation throwable = event.getThrowableInformation();
			if(throwable != null) {
				Throwable  r = throwable.getThrowable();
				GeneralException g = null;
				if (r instanceof GeneralException) {
					g = (GeneralException)r;
					errorCode = g.getErrorCode();
				}else{
					errorCode = "";
				}
			}
			logInfo.setErrorCode(errorCode);
			
			// logInfo.setLogInfo(logInfoSb.length() > 3000 ?
			// logInfoSb.subSequence(0, 3000).toString() : logInfoSb
			// .toString());
			logInfo.setLogInfo(logInfoSb.toString());
			// 记录日志信息
			holder.hold(logInfo);
		} catch (Throwable t) {
			System.err.println("=====日志处理异常=========" + t.getMessage());
		}
	}

	public synchronized void doAppend(LoggingEvent event) {
		// super.doAppend(event);
		try {
			if (holder.getLogBuffer() == null) {
				String logName = event.getLoggerName();
				if (logName.startsWith("org.springframework.beans")) {
					// 系统启动的时候屏蔽spring加载Bean的日志，防止因Bean的注入导致的日志死循环；
					// 使用ConsoleAppender和File*Appender的日志不受影响；
					return;
				}
				WebApplicationContext wac = WebApplicationContextHolder
						.getWebApplicationContext();
				if (wac != null) {
					LogBuffer logBuffer = (LogBuffer) wac
							.getBean("performanceLog");
					holder.setLogBuffer(logBuffer);
				}
			}
			Level level = event.getLevel();
			if(level != null && "DEBUG".equalsIgnoreCase(level.toString())) {
				return ;
			}
			// 设置当前请求ID到当前线程中
			String requestId = Thread.currentThread().getName();
			// 日志对象
			LogInfo logInfo = new LogInfo();
			// 设置日志对象的请求ID
			logInfo.setRequestId(requestId);
			// 设置日志类型为性能日志
			logInfo.setLogType("performance");
			logInfo.setLogLevel(level.toString());
			logInfo.setCreateDate(new Date().getTime());
			// 日志信息 FIX:修正message为空的异常
			Object msgObj = event.getMessage();
			StringBuffer logInfoSb = new StringBuffer(msgObj == null ? ""
					: String.valueOf(msgObj));
			// // 是否异常
			String[] strs = event.getThrowableStrRep();
			// // 记录异常堆栈
			// // 取前面10个堆栈信息,并且只取前3000个字符串
			if (strs != null) {
				logInfo.setLogType("exception");
				logInfoSb.append(Arrays.toString(strs));
			}
			
			String errorCode = "";
			ThrowableInformation throwable = event.getThrowableInformation();
			if(throwable != null) {
				Throwable  r = throwable.getThrowable();
				GeneralException g = null;
				if (r instanceof GeneralException) {
					g = (GeneralException)r;
					errorCode = g.getErrorCode();
				}else{
					errorCode = "";
				}
			}
			logInfo.setErrorCode(errorCode);
			
			// logInfo.setLogInfo(logInfoSb.length() > 3000 ? logInfoSb
			// .subSequence(0, 3000).toString() : logInfoSb.toString());
			logInfo.setLogInfo(logInfoSb.toString());
			// 记录日志信息
			holder.hold(logInfo);
		} catch (Throwable t) {
			System.err.println("=====日志处理异常=========" + t.getMessage());
		}
	}
}
