package com.deppon.crm.module.client.common.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class ExceptionStack2String {

	public static String stack2String(Throwable e){
		return stack2String(e, "UTF-8");
	}
	
	public static String stack2String(Throwable e,String charset){
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
		PrintStream printStream;
		try {
			printStream = new PrintStream(outputStream,true,charset);
			e.printStackTrace(printStream);
			return outputStream.toString(charset);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return null;
		
	}
}
