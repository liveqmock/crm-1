package com.deppon.crm.module.client.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 此工具类不会抛异常，返回值可能为空
 * @author davidcun @2012-5-17
 */
public class DataFormatUtil {
	
	public static Double parseDouble(String source){
		try{
			return Double.valueOf(source);
		}catch (NumberFormatException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
		}catch (Exception e) {
//			e.printStackTrace();
		}
		return 0.0;
	}
	
	public static Date parseDate(String source,String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern/*"yyyy-MM-dd HH:mm:ss"*/);
		try {
			return format.parse(source);
		} catch (ParseException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
		}catch (Exception e) {
		}
		return null;
	}
	
	public static String formatDate(Date date,String pattern){
		if (date==null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern/*"yyyy-MM-dd HH:mm:ss"*/);
		return format.format(date);
	}
	
	public static String formatDate(Date date){
		if (date==null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
}
