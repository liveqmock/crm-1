package com.deppon.crm.module.common.server.workflow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.opensymphony.workflow.loader.XMLWorkflowFactory;

public class DepponXMLWorkflowFactory extends XMLWorkflowFactory {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	protected InputStream getInputStream(String name) {
		InputStream is = super.getInputStream(name);
		if (is != null) {
			return is;
		}
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		java.net.URL url = classLoader.getResource("/");
		String fileLocation = null;
		if (url != null) {
			File file = new File(url.getPath());// WEB-INF/classes
			String parent = file.getParent();// WEB-INF
			file = new File(parent + "/" + name);// WEB-INF/workflows.xml
			try {
				fileLocation = file.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			if (fileLocation != null) {
				File target = new File(fileLocation);
				is = new FileInputStream(target);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return is;
	}
}
