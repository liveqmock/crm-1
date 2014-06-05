package com.deppon.crm.module.client.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.cxf.common.util.Base64Utility;

public class EncryptUtil {

	public static String encrypt(String param){
		MessageDigest digest = null;
		String result = null;
		if(param == null){
			return null;
		}
		try {
			digest = MessageDigest.getInstance("MD5");
			result = Base64Utility.encode(digest.digest(param.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
