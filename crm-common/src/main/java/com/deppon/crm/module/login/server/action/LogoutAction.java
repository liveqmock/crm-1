package com.deppon.crm.module.login.server.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.deppon.crm.module.frameworkimpl.server.cache.UserCache;
import com.deppon.crm.module.login.server.service.ILoginService;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.entity.IUser;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * 用户退出系统WEB接入层
 * @author Administrator
 *
 */
public class LogoutAction extends AbstractAction {

	private static final long serialVersionUID = 4776710729717441838L;
	//注入loginService
	private ILoginService loginService;
	
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	/**
	 * 退出系统
	 */
	@Override
	public String execute() throws Exception {
		loginService.userLogout();
		//清理缓存内容
		IUser cuurentUser = UserContext.getCurrentUser();
		if(cuurentUser!=null){
			@SuppressWarnings("unchecked")
			ICache<String,IUser> userCache = CacheManager.getInstance().getCache(UserCache.UUID);
			userCache.invalid(cuurentUser.getId());
		}
		Cookie cookie = new Cookie("cookieNamePassword", null); 
		UserContext.setCurrentUser(null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		ServletActionContext.getResponse().addCookie(cookie);
		return super.execute();
	}


	
}
