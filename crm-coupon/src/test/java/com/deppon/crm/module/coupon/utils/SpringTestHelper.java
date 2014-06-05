package com.deppon.crm.module.coupon.utils;

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
					"com/deppon/crm/module/coupon/server/META-INF/SpringTest.xml",
					"com/deppon/crm/module/organization/server/META-INF/spring.xml",
					"com/deppon/crm/module/authorization/server/META-INF/spring.xml",
					"com/deppon/crm/module/common/server/META-INF/spring.xml",
					"com/deppon/crm/module/client/server/META-INF/spring.xml",
					"com/deppon/crm/module/customer/server/META-INF/spring.xml",
					"com/deppon/crm/module/order/server/META-INF/spring.xml"
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
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
}
