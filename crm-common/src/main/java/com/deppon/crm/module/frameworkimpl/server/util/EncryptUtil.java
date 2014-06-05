/**
 * 
 * @(#)EncryptUtil.java    Dec 2, 2010
 * 
 * Copyright 2010 QinYu, Inc. All rights reserved.
 * 
 */
package com.deppon.crm.module.frameworkimpl.server.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


/**
 * 一般加密解密工具<br>
 * DESede/DES/BlowFish<br>
 * DES的密钥(key)长度是为8个字节,加密解密的速度是最快的<br>
 * DESede的密钥(key)长度是24个字节<br>
 * BlowFish的密钥(key)是可变的 加密最快，强度最高,密钥长度范围(1<=key<=16)<br>
 * 
 * @author liuhuan
 * @version 1.0
 * @date 2012-10-20
 */
public class EncryptUtil {

    private static final String CHARSET = "UTF-8";

    public  static final String DEFAULT_KEY="crm_oa";
  

	private static Cipher getCipher(boolean isEncrypt, byte[] key)
			throws InvalidKeyException, NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException {
		SecretKeySpec keySpec = new SecretKeySpec(key, "Blowfish");
		SecureRandom sr = new SecureRandom();
		Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
		cipher.init(isEncrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE,
				keySpec, sr);
		return cipher;
	}

	public static String encrypt(String data, String key) {
		if(data==null){
			return null;
		}
		Cipher cipher;
		try {
			cipher = getCipher(true, getBytes(key));
			return byte2hex(cipher.doFinal(data.getBytes(CHARSET)));
		} catch (Exception e) {
			return null;
		} 
	}

	public static String decrypt(String data, String key){
		if(data==null){
			return null;
		}
		Cipher cipher;
		try {
			
			cipher = getCipher(false, getBytes(key));
			byte[] b=hex2byte(data.getBytes(CHARSET));
			if(b==null){
				return null;
			}
            return new String(cipher.doFinal(b),CHARSET);
		} catch (Exception e) {
			return null;
		} 
		
	}

	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp ;
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();

	}

	private static byte[] hex2byte(byte[] b) throws  Exception{
		if ((b.length % 2) != 0)
			return null;
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2,CHARSET);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}
	
	private static byte[] getBytes(String str) throws UnsupportedEncodingException {
		return str.getBytes(CHARSET);
	}


    public static void main(String[] args) {
      decrypt("3B1F97D071D45DA01B65EC14CD47EDE01D215F81149ADB04FAA7A7297A9504EB511925F8A7608F1308576D3D4C610C6518361AE0AD05D79BC9CCBA50228FE809B9956EAE0F8EA25477E4CD08E5687ED7FF23E33634568EF049F5AD8800746BFA7F2C5195A64006700419BA2CA26189AC3A5DF8F9293659D6858C50FAC673173FDE5E67464600FF9476B26CC84C486E5DED50E5B26FAD215C851248DE9AA427418076CD020E6C76AB819D5BED2D7295AC1719FDA309AD3E5F1CA20B15E7406765CAE16E2C4217072420B9082E1ADA72696F5907DE362ADA80D61542FD6E042426F5D6B21D62DD8777CFFFFCA4DC9CBB71", DEFAULT_KEY);
    }


}
