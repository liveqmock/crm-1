package com.deppon.crm.module.client.common.exception;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * 异常信息提供类
 * @author davidcun @2012-4-24
 */
public class BusinessExceptionMessageProvider {

	private static HashMap<String, String> errorMsg;

	static {
		errorMsg = new HashMap<String, String>();
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			Resource[] resources = resolver.getResources("classpath*:com/deppon/**/META-INF/errorMsg.properties");
			for (Resource resource : resources) {
				
				errorMsg.putAll(MessageUtil.loadResource(resource.getInputStream()));
				/*
				Properties properties = new Properties();
				properties.load(resource.getInputStream());
				Set<Entry<Object, Object>> set = properties.entrySet();
				for (Entry<Object, Object> entry : set) {
					errorMsg.put(entry.getKey().toString(),new String(entry.getValue().toString().getBytes("ISO-8859-1"), "UTF-8"));
				}*/
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}
	
	public static String getMessage(String errorCode){
		return errorMsg.get(errorCode);
	}
	
	public static String getMessage(String errorCode,Object... argu){
		
		return MessageFormat.format(getMessage(errorCode), argu);
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(BusinessExceptionMessageProvider.getMessage("0002"));
		System.out.println(BusinessExceptionMessageProvider.getMessage("0003"));
		System.out.println(BusinessExceptionMessageProvider.getMessage("0004"));
		System.out.println(BusinessExceptionMessageProvider.getMessage("0005"));
		System.out.println(BusinessExceptionMessageProvider.getMessage("0006"));
		System.out.println(BusinessExceptionMessageProvider.getMessage("0007"));
		System.out.println(BusinessExceptionMessageProvider.getMessage("0008"));
		System.out.println(BusinessExceptionMessageProvider.getMessage("0009"));
		System.out.println(BusinessExceptionMessageProvider.getMessage("0010"));
		System.out.println(BusinessExceptionMessageProvider.getMessage("0011"));
		System.out.println(BusinessExceptionMessageProvider.getMessage("0012"));
		System.out.println(BusinessExceptionMessageProvider.getMessage("0013"));
		System.out.println(BusinessExceptionMessageProvider.getMessage("0014"));
		System.out.println(BusinessExceptionMessageProvider.getMessage("0015"));
		System.out.println(BusinessExceptionMessageProvider.getMessage("0021", "adfasdfasdad"));
		
		
	}
}
