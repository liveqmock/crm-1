package com.deppon.crm.module.common.file.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * 
 * @description 文件下载ACTION
 * @author 安小虎
 * @version 0.1 2012-4-16
 * @date 2012-4-16
 */
public class FileDownLoadAction extends AbstractAction {

	@SuppressWarnings("unused")
	private String fileName;// 初始的通过param指定的文件名属性
	private String inputPath;// 指定要被下载的文件路径

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public InputStream getInputStream() {
		try {
			if (inputPath != null && !"".equals(inputPath)) {
				File file = new File(inputPath);
				return new FileInputStream(file);

				// return
				// ServletActionContext.getServletContext().getResourceAsStream(inputPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @description 下载.
	 * @author 安小虎
	 * @version 0.1 2012-4-17
	 * @date 2012-4-17
	 * @update 2012-4-17 上午10:05:52
	 */
	public String downLoad() {
		return SUCCESS;
	}

	/** 提供转换编码后的供下载用的文件名 */

	public String getDownloadFileName() {
		String downFileName = fileName;
		try {
			downFileName = new String(downFileName.getBytes(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return downFileName;
	}

}
