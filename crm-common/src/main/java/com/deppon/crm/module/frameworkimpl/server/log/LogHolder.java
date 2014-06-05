package com.deppon.crm.module.frameworkimpl.server.log;

import java.util.ArrayList;
import java.util.List;

import com.deppon.foss.framework.server.components.logger.LogBuffer;

public class LogHolder {
	/**
	 * 日志缓冲
	 */
	private LogBuffer logBuffer;
	
	/**
	 * 是否初始化
	 */
	private boolean flag;
	
	@SuppressWarnings("rawtypes")
	private List list;
	
	/**
	 * 日志缓冲的get方法
	 * getLogBuffer
	 * @return
	 * @return LogBuffer
	 * @since JDK1.6
	 */
	public LogBuffer getLogBuffer() {
		return logBuffer;
	}

	/**
	 * 日志缓冲的set方法
	 * setLogBuffer
	 * @param logBuffer
	 * @return void
	 * @since JDK1.6
	 */
	public void setLogBuffer(LogBuffer logBuffer) {
		this.logBuffer = logBuffer;
	}

	/**
	 * 
	  * 创建一个新的实例 LogHolder.
	  * @since JDK1.6
	 */
	@SuppressWarnings("rawtypes")
	private LogHolder() {
		list = new ArrayList();
	}
	
	/**
	 * 单例
	 */
	private static LogHolder instance = new LogHolder();
	
	public static LogHolder getInstance() {
		return instance;
	}
	
	/**
	 * 如果日志缓冲未初始化则先保持在holder的缓冲区
	 * 日志缓冲初始化后把已缓冲的输出到日志缓冲并使用日志缓冲替换原有缓冲
	 * hold
	 * @param obj
	 * @return void
	 * @since JDK1.6
	 */
	@SuppressWarnings("unchecked")
	public void hold(Object obj) {
		if (logBuffer == null) {
			list.add(obj);
		} else {
			if (!flag) {
				for (Object val : list) {
					logBuffer.write(val);
				}
				flag = true;
			}
			logBuffer.write(obj);
		}
	}
	
}
