package com.deppon.crm.module.client.common;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigFileLoad {

	private static Log log = LogFactory.getLog(ConfigFileLoad.class);
	protected File config;
	protected String filePath = null;

	public ConfigFileLoad(String filePath) {
		if (filePath == null) {
			throw new IllegalArgumentException("arguments can not be null");
		}
		this.filePath = filePath;

		String dir = ConfigFileLoad.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		if (log.isDebugEnabled()) {
			log.debug("dir path is " + dir);
		}
		if (dir.indexOf("WEB-INF") < 0) {
			config = new File(filePath);
		} else {
			dir = dir.substring(0, dir.indexOf("WEB-INF"));
			if (log.isInfoEnabled()) {
				log.info("interface config root directory is " + dir);
			}
			config = new File(dir + filePath);
		}
	}
}
