package com.deppon.crm.module.order.server.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * @description 属性文件工具类
 * @author 安小虎
 * @version 0.1
 * @date 2012-6-8
 */
@SuppressWarnings("serial")
public class PropertiesUtil extends Properties {

	// 加载属性文件
	private PropertiesUtil() {
		//输入流
		InputStream is = getClass()
				.getResourceAsStream(
						"/com/deppon/crm/module/order/server/META-INF/remindOrderMonitor.properties");
		try {
			load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//声明加载文件的实例名
	private static PropertiesUtil instance;
	
	/**
	 * 
	 * <p>
	 * Description:获得实例<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午10:29:54
	 * @return
	 * PropertiesUtil
	 * @update 2013-1-22上午10:29:54
	 */
	public static PropertiesUtil getInstance() {
		if (instance != null) {
			return instance;
		} else {
			makeInstance();
			return instance;
		}
	}

	/**
	 * 
	 * <p>
	 * Description:同步方法<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午10:30:00
	 * void
	 * @update 2013-1-22上午10:30:00
	 */
	private static synchronized void makeInstance() {
		if (instance == null) {
			instance = new PropertiesUtil();
		}
	}
}
