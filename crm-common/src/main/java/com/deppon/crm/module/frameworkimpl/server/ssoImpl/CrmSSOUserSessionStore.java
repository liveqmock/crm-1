package com.deppon.crm.module.frameworkimpl.server.ssoImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.foss.framework.entity.IUser;
import com.deppon.foss.framework.server.context.SessionContext;
import com.deppon.foss.framework.server.context.WebApplicationContextHolder;
import com.deppon.foss.framework.server.sso.ISSOUserStore;
import com.deppon.foss.framework.server.sso.domain.Token;

public class CrmSSOUserSessionStore implements ISSOUserStore {

	// 在session中保存用户id的key
	private static final String USERID_KEY = "FRAMEWORK__KEY_USER__";

	// 查询用户业务服务UserService
	private IUserService userService;
	/**
	 * 返回保存在session中的用户ID的key
	 * 
	 * @return
	 */
	public String getUserIDKey() {
		return USERID_KEY;
	}
	
	public CrmSSOUserSessionStore(){
		userService = (IUserService) WebApplicationContextHolder.getWebApplicationContext().getBean("userService");
	}

	/**
	 * 通过传入的用户工号，查询用户ID，并把用户ID存入会话中
	 * 
	 * @param session
	 * @param empNo
	 *            传入的用户ID是用户的工号
	 */
	@Override
	public void putCurrentUser(HttpSession session, String empNo) {
		// 通过用户工号查询UserID
		IUser user = userService.findByLoginName(empNo);
		String userId = user.getId();
		session.setAttribute(USERID_KEY, userId);
		session.setAttribute("FRAMEWORK_KEY_EMPCODE",empNo);
	}

	/**
	 * 删除session中的用户
	 * 
	 * @param oldUserID
	 * @return
	 */
	@Override
	public String deleteUserId(String oldUserID) {
		return oldUserID;
	}

	/**
	 * 把用户id注册到session中
	 * @author suyujun
	 * @param session HttpSession
	 * @param userID 用户id
	 * @see com.deppon.foss.framework.server.sso.ISSOUserStore#saveLoginInfo(javax.servlet.http.HttpSession, com.deppon.foss.framework.server.sso.domain.Token)
	 */
	@Override
	public void saveLoginInfo(HttpSession session, Token token) {
		//工号
		String empNo = token.getUserId();
		IUser user = userService.findByLoginName(empNo);
		String userId = user.getId();
		session.setAttribute(USERID_KEY, userId);
		//将工号注册到session中
		session.setAttribute("FRAMEWORK_KEY_EMPCODE", empNo);
	}

	/**
	 * 释放在session中的用户id
	 * @author suyujun
     * @param session 过期session
     * @param expiredUserId 过期的用户ID户
     * @return String
	 * @see com.deppon.foss.framework.server.sso.ISSOUserStore#logoff(javax.servlet.http.HttpSession, java.lang.String)
	 */
	@Override
	public String logoff(HttpSession session, String expiredUserId) {
		String retVal = "";
		String empNo = String.valueOf(session.getAttribute("FRAMEWORK_KEY_EMPCODE"));
		if(StringUtils.isNotEmpty(empNo)){
			if(empNo.equals(expiredUserId)){
				session.removeAttribute("FRAMEWORK_KEY_EMPCODE");
				retVal = expiredUserId;
			}
		}
		return retVal;
	}

	/**
     * 返回用户登录后注册到session中的键值
     * @param session
     * @return String 
	 * @see com.deppon.foss.framework.server.sso.ISSOUserStore#getLoginUserId(javax.servlet.http.HttpSession)
	 */
	@Override
	public String getLoginUserId(HttpSession session) {
		return String.valueOf(session.getAttribute("FRAMEWORK_KEY_EMPCODE"));
	}

	/**
	 * 判断用户是否登录
	 * @author suyujun
     * @param session
     * @param t
     * @return
	 * @see com.deppon.foss.framework.server.sso.ISSOUserStore#isUserLogedIn(javax.servlet.http.HttpSession, com.deppon.foss.framework.server.sso.domain.Token)
	 */
	@Override
	public boolean isUserLogedIn(HttpSession session, Token t) {
		boolean retVal = false;
		//session中获得工号
		String empNo = (String) session.getAttribute("FRAMEWORK_KEY_EMPCODE");
		//工号不为空
		if(StringUtils.isNotEmpty(empNo)){
			//session中的工号与token中的一致，则为登陆
			if(empNo.equals(t.getUserId())){
				//return true
				retVal = true;
			}
		}
		//return false
		return retVal;
	}

	@Override
	public boolean isUserLogedIn(HttpServletRequest arg0,
			HttpServletResponse arg1, Token arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void saveLoginInfo(HttpServletRequest arg0,
			HttpServletResponse arg1, Token arg2) {
		// TODO Auto-generated method stub
		
	}


}
