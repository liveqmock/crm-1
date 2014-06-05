package com.deppon.crm.module.frameworkimpl.server.ssoImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.deppon.foss.framework.server.sso.SSOManager;
import com.deppon.foss.framework.server.sso.config.SSOApplicationConfig;
import com.deppon.foss.framework.server.sso.config.SSOConfig;
import com.deppon.foss.framework.server.sso.domain.Token;
import com.deppon.foss.framework.server.sso.config.SSOXmlConfigLoader;
import com.deppon.foss.framework.server.sso.util.TokenMarshal;

/**
 * 
 * @ClassName: SSORequestServlet 
 * @Description: 
 *  <b style="font-family:微软雅黑">
 *	 <small>
 *	     处理sso登录请求 验证目的服务器是否可信 如果可信向目的服务器发送验证请求
 *    如果验证通过，获得目的服务器返回的token，附加到url上，重定向到目的服务器
 *    请求参数格式：app=destServer&url=serverURI
 *	 </small>
 *  </b>
 * @author Glen Zhang  yigang.zhang@achievo.com
 * @date 2011-6-13 下午05:55:27 
 *
 */
public class CrmRemoteSSOURLRequestServlet extends HttpServlet {

	private static final long serialVersionUID = 3413183552969805066L;

	// Log4j
	private Logger log = LogManager.getLogger(getClass());
	
	/**
     * 
     * @Title:sendValidate
     * @Description:向目的服务器发送验证请求
     * @param @param validateUrl
     * @param @return
     * @return String 校验码，重定位到目的服务器时带过去
     * @throws
     */
    private String sendValidate(String validateUrl) {
    	HttpURLConnection con=null;
		try{
			URL url=new URL(validateUrl);
			con=(HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			con.setUseCaches(false);
			con.setRequestProperty("content-type", "text/html;charset=UTF-8");
			con.connect();
			String token = "";
			int responseCode = con.getResponseCode();
			if(responseCode==HttpServletResponse.SC_OK){
				InputStream is = con.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line = "";
				while((line=br.readLine())!=null){
					token += line;
				}
				br.close();
				return token;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(con!=null){
				con.disconnect();
			}
		}
        return null;
    }

	/**
	 * 处理sso登录请求 验证目的服务器是否可信 如果可信向目的服务器发送验证请求
	 * 如果验证通过，获得目的服务器返回的token，附加到url上，重定向到目的服务器
	 * 请求参数格式：app=destServer&url=serverURI
	 * 
	 */
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		SSOManager ssoManager = SSOManager.getInstance("crm");
		HttpSession session = request.getSession();
		String userID = (String) session
				.getAttribute("FRAMEWORK_KEY_EMPCODE");
		if (userID == null) {
			log.error("The user info for id FRAMEWORK_KEY_EMPCODE"
					+ " must not be null. ");
			throw new ServletException("The user info for id "
					+ "FRAMEWORK_KEY_EMPCODE must not be null. ");
		}
		
		// 目的服务器ID
		String destAppID = request.getParameter("app");
		// 验证通过后，返回的token标志
		String tokenValue = "";
		SSOApplicationConfig destApp = null;
		try {
			// 加载sso.xml文件，根据目的服务器验证是否可信，存在即表示可信
			SSOXmlConfigLoader resource = SSOXmlConfigLoader.getInstance();
			SSOConfig sso = resource.getSso();
			if (sso == null) {
				log.error("can't find sso.xml file in classpath!");
				throw new ServletException(
						"can't find sso.xml file in classpath!");
			}
			destApp = sso.getApplication(destAppID);
			if (destApp == null) {
				log.error("The application " + destAppID + " is forbiddened. ");
				throw new ServletException("This application " + destAppID
						+ " is forbiddened");
			}
			// 根据目的服务器的验证URL，到目的服务器验证用户是否可以登录目的服务器
			Token token = new Token(userID,request.getSession().getId(), 
					      sso.getApplication("crm").getId(),Token.genUUID());
			String requestToken = TokenMarshal.marshal(token);
			ssoManager.store(token.getTokenKey(), requestToken);
			requestToken = URLEncoder.encode(requestToken, "UTF-8");
			String validateURL = destApp.getUrl() + "/tokenGenerate?app="
					+ sso.getApplication("crm").getId() + "&Token"
					+ "=" + requestToken;
			tokenValue = sendValidate(validateURL);
			if (tokenValue == null) {
				log.error("This SSO login for " + destAppID + " is failure.");
				throw new ServletException("The SSO login for " + destAppID
						+ " is failure.");
			}
		} catch (ServletException e) {
			e.printStackTrace();
			throw e;
		}

		/**
		 *  验证通过后，重定向到目的服务器
		 *  如果url没有值，直接重定向到homePage
		 */
		String url = request.getParameter("url");
		if (url == null || "".equals(url)) {
			url = destApp.getHomePage();
		}else{
			url = request.getQueryString();
			if(url!=null){
				int index = url.lastIndexOf("&url=");
				url = url.substring(index+5);
			}
		}
		StringBuffer redirect = new StringBuffer().append("<script>window.location.href='").append(destApp.getUrl())
				.append(url);
		if (url.indexOf("?") >= 0) {
			redirect.append("&").append("Token").append("=")
					.append(tokenValue).append("&forward=true")
					;
		} else {
			redirect.append("?").append("Token").append("=")
					.append(tokenValue).append("&forward=true")
					;
		}
		redirect.append("'</script>");
		response.setHeader("Content-Type", "text/html");
		response.getOutputStream().write(redirect.toString().getBytes());
		//response.getWriter().write(redirect.toString());
		//response.sendRedirect(redirect.toString());

	}
}