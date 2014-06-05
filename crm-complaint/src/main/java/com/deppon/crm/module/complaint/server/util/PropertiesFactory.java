package com.deppon.crm.module.complaint.server.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Properties文件静态工厂
 * 
 * @author justin.xu
 * @since 2012-07-2
 */
public class PropertiesFactory {
	private static Log log = LogFactory.getLog(PropertiesFactory.class);
	
	/**
	 * 属性文件实例容器
	 */
	private static Map container = new HashMap();
	
	static{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			if (classLoader == null) {
				classLoader = PropertiesFactory.class.getClassLoader();
			}
			//加载属性文件complaintInfo.properties
			try {
			  InputStream is = classLoader.getResourceAsStream("complaintInfo.properties");
			  PropertiesHelper ph = new PropertiesHelper(is);
			  container.put(PropertiesFile.COMPLAINTINFO, ph);
			  } catch (Exception e1) {
			  }
	}
	
    /**
     * 获取属性文件实例
     * @param pFile 文件类型
     * @return 返回属性文件实例
     */
	public static PropertiesHelper getPropertiesHelper(String pFile){
		return (PropertiesHelper)container.get(pFile);
	}
}
