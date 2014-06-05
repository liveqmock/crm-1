package com.deppon.crm.module.login.server.action;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.frameworkimpl.server.util.CryptoUtil;
import com.deppon.crm.module.login.server.service.ILoginService;
import com.deppon.crm.module.login.shared.exception.LoginException;
import com.deppon.crm.module.login.shared.exception.LoginExceptionType;
import com.deppon.foss.framework.server.components.security.SecurityNonCheckRequired;
import com.deppon.foss.framework.server.context.SessionContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * 用户登录WEB接入层
 * @author Administrator
 *
 */
public class LoginAction extends AbstractAction implements  ServletResponseAware{
	
	private static final long serialVersionUID = -5182857592065055743L;

	//注入loginService
	private ILoginService loginService;
	
	//用户名
	private String loginName;
	
	//密码
	private String password;
	
	//登录日期
	private Date loginDate;
	private HttpServletResponse response;  
	
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 用户登录
	 */
	@Override
	@SecurityNonCheckRequired
	@JSON
	public String execute() throws Exception{
		if(null!=loginDate){
			if(getDistance(new Date(),loginDate)>10 || getDistance(new Date(),loginDate)<-10){
				// 账号已锁定
				throw new LoginException(LoginExceptionType.UserLogindateNoSame);
			}
		}
		User user = loginService.userLogin(loginName, password);
		SessionContext.setCurrentUser(user.getId());
		 Cookie cookieNamePassword = new Cookie("cookieNamePassword",loginName+"|"+CryptoUtil.digestByMD5(password));
		 cookieNamePassword.setPath("/");
		 response.addCookie(cookieNamePassword);
		return super.execute();
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
	
	public static long getDistance(Date startTime, Date endTime){ 
		long distance = endTime.getTime()-startTime.getTime(); 
		return distance/(60*1000); 
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
}
