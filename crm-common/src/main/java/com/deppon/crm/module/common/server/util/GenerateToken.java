package com.deppon.crm.module.common.server.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.deppon.crm.module.common.shared.domain.HardWareInfo;
import com.deppon.foss.framework.server.sso.util.BASE64Encoder;

/**
 * <p>
 * Description:生成Token<br />
 * </p>
 * 
 * @title GenerateToken.java
 * @package com.deppon.crm.module.common.server.util
 * @author Weill
 * @version 0.1 2012-7-8
 */
public class GenerateToken {

	private static Logger log = LogManager.getLogger(GenerateToken.class);

	public static String generateTokenByHardWareInfo(HardWareInfo info) {
		String token = null;
		String hardWareInfoString = info.toString() + getUUID();
		BASE64Encoder encoder = new BASE64Encoder();
		String base64Str = encoder
				.encode(hardWareInfoString.getBytes());

		token = getMd5(base64Str);
		return token;
		
	}

	private static String getUUID() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		return sdf.format(new Date())+System.currentTimeMillis();
	}

	/**
	 * <p>
	 * Description:生成MD5<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-7-8
	 * @param token
	 * @return
	 * String
	 */
	private static String getMd5(String token) {

		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(token.getBytes("UTF-8"));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			log.error(e.getMessage());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5Buffer = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5Buffer.append("0")
						.append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5Buffer.append(Integer
						.toHexString(0xFF & byteArray[i]));
		}
		return md5Buffer.toString();

	}
	
	public static String generateErrorHTML(String message){
		StringBuffer html = new StringBuffer();
		html.append("<html>");
		html.append("<head>");
		html.append("<title>");
		html.append("CRM hardware check faile");
		html.append("</title>");
		html.append("</head>");
		html.append("<body>");
		html.append("<h3>");
		html.append("抱歉,您的请求被拒绝。错误代码:");
		html.append(message);
		html.append("</h3>");
		html.append("<br />");
		html.append("无法正常登陆CRM系统！请登陆OA系统下载“CRM安全助手”进行登陆。");
		html.append("</body>");
		html.append("</html>");
		log.debug(html);
		return html.toString();
	}
	
	public static String getTimeToken(String s){
		StringBuffer v = new StringBuffer();
		int asc = 0 ;
		for(int i = 0;i<s.length();i++){
			asc = (s.charAt(i) + 0) << 1;
			v.append((char)asc);
		}
		return v.toString();
	}
	
}
