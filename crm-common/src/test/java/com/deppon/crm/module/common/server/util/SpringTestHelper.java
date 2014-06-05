package com.deppon.crm.module.common.server.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTestHelper {

	protected ApplicationContext appContext = null;
	private static SpringTestHelper instance = new SpringTestHelper();

	private SpringTestHelper() {
		initContext();
	}

	public static SpringTestHelper get() {
		return instance;
	}

	protected void initContext() {
		try {
			appContext = new ClassPathXmlApplicationContext(
					"com/deppon/crm/module/common/server/META-INF/SpringTest.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public <T> T getBean(Class<T> clazz){
		if(appContext != null){
			String className = clazz.getSimpleName();
			String startName = className.substring(0,1);
			String otherName = className.substring(1);
			
			String beanName =StringUtils.lowerCase(startName) + otherName;
			return (T) appContext.getBean(beanName);
		}
		return null;
	}

	public <T> T getBeanByInterface(Class<T> clazz){
		if(appContext != null){
			String className = clazz.getSimpleName();
			String otherName = className.substring(1);
			String anyotherName = otherName.substring(1);
			String startName = otherName.substring(0,1);
			
			String beanName =StringUtils.lowerCase(startName) + anyotherName;
			return (T) appContext.getBean(beanName);
		}
		return null;
	}
}
