package com.deppon.crm.module.frameworkimpl.server.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.frameworkimpl.server.util.SpringContextUtil;
import com.deppon.crm.module.login.server.service.ILoginService;
import com.deppon.foss.framework.server.Definitions;
import com.deppon.foss.framework.server.context.SessionContext;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.DefaultFilter;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * 
 * <p>
 * Description:CRM模块间单点登录<br />
 * </p>
 * 
 * @title CRMSSOFilter.java
 * @package com.deppon.crm.module.frameworkimpl.server.filter
 * @author 106138
 * @version 0.1 2014年4月18日
 */
public class CRMSSOFilter extends DefaultFilter {

	

	private IUserService userService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		// 判断是否是http请求
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			try {
				throw new ServletException("非正常请求");
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		String  cookieNamePassword="";
		if (null == UserContext.getCurrentUser()) {
			// 强制转换为Http请求
			userService = (IUserService) SpringContextUtil.getBean("userService");
			HttpServletRequest req = (HttpServletRequest) request;
			Cookie[] cookies = req.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie c : cookies) {
					if (c.getName().equals("cookieNamePassword")) {
						cookieNamePassword = c.getValue();
					}
				}
			}
			if (StringUtil.isNotEmpty(cookieNamePassword)) {
				String[] ps = cookieNamePassword.split("\\|");
				String userName = ps[0];
				String passWord = ps[1];
				User user = userService.findByLoginName(userName);
				if (null != user && passWord.equals(passWord)) {
					HttpSession session = req.getSession();
					SessionContext.setSession(session);
					SessionContext.setCurrentUser(user.getId());
					SessionContext.getSession().setObject("FRAMEWORK_KEY_EMPCODE", user.getLoginName());
				}
			} else {
				HttpSession session = req.getSession();
				session.setAttribute(Definitions.KEY_USER, null);
			}
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
