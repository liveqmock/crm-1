package com.deppon.crm.module.common.file.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @description
 * @author 安小虎
 * @version 0.1
 * @date 2012-5-4
 */

@SuppressWarnings("serial")
public class PropertiesUtil extends Properties {
	private static Log log = LogFactory.getLog(PropertiesUtil.class);
	private static PropertiesUtil instance;

	public static PropertiesUtil getInstance() {
		if (instance != null) {
			return instance;
		} else {
			makeInstance();
			return instance;
		}
	}

	// 同步方法
	private static synchronized void makeInstance() {
		if (instance == null) {
			instance = new PropertiesUtil();
		}
	}

	// 加载属性文件
	private PropertiesUtil() {
		InputStream is = getClass()
				.getResourceAsStream(
						"/com/deppon/crm/module/common/server/META-INF/file.properties");
		try {
			load(is);
		} catch (Exception e) {
			log.info("---读取属性文件出错了---");
			e.printStackTrace();
		}
	}
}
