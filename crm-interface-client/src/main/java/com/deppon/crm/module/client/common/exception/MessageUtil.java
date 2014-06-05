package com.deppon.crm.module.client.common.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageUtil {

	private static final Log log = LogFactory.getLog(MessageUtil.class);
	
	public static Map<String, String> loadResource(InputStream inStream) {
		Map<String,String> src = new HashMap<String, String>();
		
		//如果统一规范通过 new InputStreamReader(inStream,"UTF-8"),指定字符集;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(inStream,"UTF-8"));
			
		} catch (UnsupportedEncodingException e1) {
			log.error("system not surpport UTF-8 encoding",e1);
			
			reader = new BufferedReader(new InputStreamReader(inStream));
		}  
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				//国际化资源中名字或者值不能出现"="
				
				String[] strs = line.split("=");
				
				if(strs==null || strs.length!=2){
					continue;
				}
				src.put(strs[0].trim(), strs[1].trim());
			}
			reader.close();
		} catch (IOException e) {
			
			log.error("reader file error...", e);
			
		}
		return src;
	}
}
