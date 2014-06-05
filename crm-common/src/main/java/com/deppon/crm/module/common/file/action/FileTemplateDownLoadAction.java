package com.deppon.crm.module.common.file.action;

import java.io.InputStream;

import com.deppon.crm.module.common.file.util.FileUtil;
import com.deppon.crm.module.common.file.util.PropertiesUtil;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * 
 * @description 文件模块下载ACTION
 * @author 安小虎
 * @version 0.1
 * @date 2012-5-21
 */
public class FileTemplateDownLoadAction extends AbstractAction {

	private String fileName=PropertiesUtil.getInstance().getProperty("PotentialCust");
	private String propKey;// 指定配置文件中的key

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}

	public InputStream getInputStream() {
		return FileUtil.getTemplateByKey(propKey);
	}

	/**
	 * 
	 * @description 模块下载.
	 * @author 安小虎
	 * @version 0.1
	 * @date 2012-5-21
	 */
	public String templateDownLoad() {
		return SUCCESS;
	}
	
	/** 提供转换编码后的供下载用的文件名 */

	public String getDownloadFileName() {
		String downFileName = PropertiesUtil.getInstance().getProperty(propKey);
		try {
			downFileName = new String(downFileName.getBytes(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return downFileName;
	}

}
