package com.deppon.crm.module.client.log;

import org.apache.log4j.spi.LoggingEvent;

import com.deppon.crm.module.client.common.util.ClientContext;

/**
 * @作者: 罗典
 * @时间：2012-12-28下午6:52:54
 * @描述：接口记录日志信息至数据库(配置此行至log4j)
 */
public class InterfaceLog4jRecorder extends org.apache.log4j.AppenderSkeleton
		implements org.apache.log4j.Appender {
	private static final String SPLIT_SIGN = " | ";

	@Override
	protected void append(LoggingEvent event) {
		ClientContext.setLogInfo(event.getLevel() + SPLIT_SIGN
				+ event.getLoggerName() + SPLIT_SIGN + event.getMessage()
				+ "     ");
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean requiresLayout() {
		// TODO Auto-generated method stub
		return false;
	}

}
