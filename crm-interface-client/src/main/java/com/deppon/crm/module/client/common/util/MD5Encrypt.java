package com.deppon.crm.module.client.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.cxf.common.util.Base64Utility;


public class MD5Encrypt {
	
	private static final String ALGORITHM = "MD5";
	
	/**
	 * 
	 * encrypt  md5加密+base64编码<br/>  
	 * <br/>  
	 * <b>创建人：</b>wanghui<br/>  
	 * <b>修改人：</b>wanghui<br/>  
	 * @param param
	 * @return  String  
	 * @date 2011-9-8
	 * @exception   
	 * @since  1.0.0
	 */
	public static String encrypt(String param){
		MessageDigest digest = null;
		String result = null;
		if(param == null){
			return null;
		}
		try {
			digest = MessageDigest.getInstance(ALGORITHM);
			result = Base64Utility.encode(digest.digest(param.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		MD5Encrypt.encrypt("hrsmstesthrsmstest");
	}
}
