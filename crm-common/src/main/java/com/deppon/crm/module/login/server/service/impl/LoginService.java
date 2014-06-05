package com.deppon.crm.module.login.server.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.server.dao.IUserDao;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.frameworkimpl.server.util.CryptoUtil;
import com.deppon.crm.module.login.server.manager.ILoginLockManager;
import com.deppon.crm.module.login.server.service.ILoginService;
import com.deppon.crm.module.login.shared.exception.LoginException;
import com.deppon.crm.module.login.shared.exception.LoginExceptionType;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.SessionContext;

/**
 * 用户登录管理服务层
 * 
 * @author ztjie
 * 
 */
public class LoginService implements ILoginService {
	private static final int REMAINDMINS = 5;
	private IUserDao userDao;
	private ILoginLockManager lockManager;

	public User userLogin(String username, String pwd) {
		if (username == null) {
			throw new LoginException(LoginExceptionType.UserNameIsNull);
		}
		if (pwd == null) {
			throw new LoginException(LoginExceptionType.LoginPasswordIsNull);
		}
		// 应用OA的加密方式 modify by ztjie 2011-11-21
		pwd = CryptoUtil.digestByMD5(pwd);
		User user = this.userDao.getByLoginName(username);
		if (user == null) {
			throw new LoginException(LoginExceptionType.UserIsNull);
		}
		// 如果用户已经被禁用，则不能登录
		if (user.getStatus() == 0) {
			throw new LoginException(LoginExceptionType.UserIsDisable);
		}
		// 账号锁定功能
		if (lockManager.isThirthMins(user)) {
			// 锁定时间到期，解除锁定
			if (lockManager.queryLastLoginErrTime(user) != null) {
				lockManager.cleanLastErrorTime(user);
				lockManager.cleanErrorTimes(user);
			}
		} else {
			// 账号已锁定
			throw new LoginException(LoginExceptionType.CurrentAccountLocked);
		}
		if (!pwd.equals(user.getPassword())) {
			if (lockManager.validateTimes(user)) {
				throw new LoginException(LoginExceptionType.TryMoreThanTimes);
			}
			try {
				throw new LoginException(LoginExceptionType.LoginPasswordIsError,new Object[] { REMAINDMINS - lockManager.queryErrorTimes(user) });
			} catch (LoginException e) {
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						e.getErrorArguments()) {
							private static final long serialVersionUID = 3037393409783572875L;
				};
			}
		}
		/*
		 * 密码正确，清除计数及时间
		 */
		lockManager.cleanErrorTimes(user);
		lockManager.cleanLastErrorTime(user);

		userDao.updateLastLoginDate(user);
		// 把登录用户ID与工号存入session中
		SessionContext.setCurrentUser(user.getId());
		SessionContext.getSession().setObject("FRAMEWORK_KEY_EMPCODE",
				user.getLoginName());
		return user;
	}

	@Transactional(readOnly = true)
	public void userLogout() {
		SessionContext.invalidateSession();
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public ILoginLockManager getLockManager() {
		return lockManager;
	}

	public void setLockManager(ILoginLockManager lockManager) {
		this.lockManager = lockManager;
	}

}
