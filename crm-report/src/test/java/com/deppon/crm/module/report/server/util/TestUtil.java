package com.deppon.crm.module.report.server.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.foss.framework.server.context.AppContext;

import junit.framework.TestCase;

public class TestUtil{
	private static final ApplicationContext appContext;
	static {
		AppContext.initAppContext("application", "", "");
		String[] resource = {
		"classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml"};
		appContext = new ClassPathXmlApplicationContext(resource);
	}

	public static Object getBean(String beanName) {
		return appContext.getBean(beanName);
	}

}
