package com.deppon.crm.module.client.common.util;

import java.io.File;
import java.io.IOException;

import org.apache.cxf.tools.wsdlto.WSDLToJava;

public class WsUrlToJavaUtil {
	
	public static void generate(String url){
		try {
			String name = url.substring(url.lastIndexOf("/")+1);
			File file = new File(".");
			file = new File(file.getCanonicalPath()+"/src/test/java");
			double d = Math.random()*1000;
			int i = (int) d;
			String wsdlPath = WSDLDownLoadUtil.downloadWsdl(url, name+i);
			generate(file.getAbsolutePath(), wsdlPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void generate(String javaPath,String wsdlPath){
		WSDLToJava.main(new String[]{"-d",javaPath,wsdlPath});
	}
	
}
