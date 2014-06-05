package com.deppon.crm.module.client.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.deppon.crm.module.client.common.exception.DownLoadWsdlException;

public class WSDLDownLoadUtil {
	
	
	/**
	 * davidcun
	 * 
	 * @param serviceUrl serviceUrl,example：http://localhost:8080/ws/helloword
	 * @param toFile     fileName,example:helloworld.wsdl
	 * @return 返回生成的WSDL文件的绝对路径
	 */
	public static String downloadWsdl(String serviceUrl,String fileName) {
		
		URL url=null;
		try {
			if (!serviceUrl.endsWith("?wsdl")) {
				serviceUrl = serviceUrl+"?wsdl";
			}
			url = new URL(serviceUrl);
			InputStream ins = url.openConnection().getInputStream();
			
			File file = new File(".");
			String rootPath = file.getCanonicalPath();
			rootPath += "/src/main/resources/wsdl";
			file = new File(rootPath);
			if (!file.exists()) {
				
				file.mkdirs();
			}
			if (!fileName.endsWith(".wsdl")) {
				fileName = fileName+".wsdl";
			}
			
			file = new File(file.getAbsolutePath()+"/"+fileName);
			FileOutputStream fos = new FileOutputStream(file);
			
			byte[] bt = new byte[1024];
			int len = 0;
			while ((len = ins.read(bt)) != -1) {
				
				fos.write(bt, 0, len);
			}
			ins.close();
			fos.close();
			return file.getAbsolutePath();
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
			throw new DownLoadWsdlException(String.format("your url %s is wrong", serviceUrl), e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DownLoadWsdlException(String.format("service url(%s) not can read", serviceUrl), e);

		}
		

	}
}
