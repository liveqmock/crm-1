package com.deppon.crm.module.frameworkimpl.server.ssoImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.deppon.crm.module.authorization.server.dao.IUserDao;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.frameworkimpl.server.util.CryptoUtil;
import com.deppon.crm.module.frameworkimpl.server.util.SpringContextUtil;
import com.deppon.foss.framework.server.Definitions;
import com.deppon.foss.framework.server.context.SessionContext;

import edu.yale.its.tp.cas.client.IContextInit;

public class OAToCRMByCasInit implements IContextInit{


	private Logger log=Logger.getLogger(OAToCRMByCasInit.class);
	//用户工号信息
	private String userName;
	//注入
	private IUserDao userDao;
	
	/**
	 * 用户信息实体Bean
	 */
	private User user;
	/**
	 * 获取用户工号信息
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置用户工号信息
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Description:userDao<br />
	 * @author CoCo
	 * @version 0.1 2013-9-3
	 */
	public IUserDao getUserDao() {
		return userDao;
	}
	/**
	 * Description:userDao<br />
	 * @author CoCo
	 * @version 0.1 2013-9-3
	 */
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * Description:user<br />
	 * @author CoCo
	 * @version 0.1 2013-9-3
	 */
	public User getUser() {
		return user;
	}
	/**
	 * Description:user<br />
	 * @author CoCo
	 * @version 0.1 2013-9-3
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String getTranslatorUser(String userName) {
		this.userName = userName;
		return userName;
	}
	
	@Override
	public void initContext(ServletRequest request, ServletResponse response,
			FilterChain arg2, String userName) {
		//跳转开始log
		log.info("跳转到CRM开始----------");
		// 判断是否是http请求
		if (!(request instanceof HttpServletRequest)
				|| !(response instanceof HttpServletResponse)) {
			try {
				throw new ServletException(
						"CasFileter is protected http resources");
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isEmpty(userName)) {
			log.error("The userName info"
					+ " must not be null. ");
			try {
				throw new ServletException("The user info "
						+ "FRAMEWORK_KEY_EMPCODE must not be null. ");
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		// 强制转换为Http请求
		HttpServletRequest req = (HttpServletRequest) request;
		//session
		HttpSession session = req.getSession();
		//通过SpringContextUtil取bean
		userDao = (IUserDao) SpringContextUtil.getBean("userDao");
		User user = userDao.getByLoginName(userName);
		if (user == null || StringUtils.isEmpty(user.getLoginName())) {
			log.error("The User info"
					+ " must not be null");
			try {
				throw new ServletException("The user info "
						+ "FRAMEWORK_KEY_EMPCODE must not be null. ");
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		session.setAttribute(Definitions.KEY_USER,user.getId());
		HttpServletResponse res = (HttpServletResponse) response;
		 Cookie cookieNamePassword = new Cookie("cookieNamePassword",user.getLoginName()+"|"+user.getPassword());
		 cookieNamePassword.setPath("/");
		 res.addCookie(cookieNamePassword);
		log.info("跳转到crm成功----------");
	}
}
