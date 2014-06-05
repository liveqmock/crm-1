package com.deppon.crm.module.customer.server.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.customer.server.manager.CustomerValidator;
/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title SpringServiceTestHelper.java
 * @package com.deppon.crm.module.customer.server.utils 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class SpringServiceTestHelper {
/**
 * 
 */
	protected ApplicationContext appContext = null;

	private static SpringServiceTestHelper instance = new SpringServiceTestHelper();
	/**
	 * 
	 * constructer
	 */
	private SpringServiceTestHelper() {
		initContext();
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * SpringServiceTestHelper
	 */
	public static SpringServiceTestHelper get() {
		return instance;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * void
	 */
	protected void initContext() {
		try {
			//初始化
			appContext = new ClassPathXmlApplicationContext(
					"com/deppon/crm/module/customer/server/META-INF/SpringTest.xml");
		} catch (BeansException e) {
		}
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param clazz
	 * @return
	 * T
	 */
	public <T> T getBean(Class<T> clazz){
		if(appContext != null){
		//获得bean
			String className = clazz.getSimpleName();
			String startName = className.substring(0,1);
			String otherName = className.substring(1);
			
			String beanName =StringUtils.lowerCase(startName) + otherName;
			return (T) appContext.getBean(beanName);
		}
		return null;
	}

}
