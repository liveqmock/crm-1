package com.deppon.crm.module.login.server.manager.impl;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.login.server.manager.ILoginLockManager;
import com.deppon.crm.module.login.server.service.ILoginLockService;

/**
 * @package com.deppon.crm.module.login.server.manager.impl
 * @author 苏玉军
 * @version 0.1 2012-7-9
 */

public class LoginLockManager implements ILoginLockManager {
	private ILoginLockService lockService;
	private static final int MINUTE = 30;
	
	
	public ILoginLockService getLockService() {
		return lockService;
	}

	public void setLockService(ILoginLockService lockService) {
		this.lockService = lockService;
	}

	/**
	 * 
	 * <p>
	 * 判断最后一次尝试登陆时间与当前时间是否相差超过30分钟<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-7-9
	 * @return boolean
	 */
	@Override
	public boolean isThirthMins(User user) {
		Date lastLoginErrTime = lockService.queryLastLoginErrTime(user);
		if (lastLoginErrTime == null) {
			return true;
		}
		long current = System.currentTimeMillis();
		long diffMin = (current - lastLoginErrTime.getTime()) / (60 * 1000);
		return diffMin > MINUTE;
	}

	/**
	 * 
	 * <p>
	 * 判断是否要锁定账号30分钟<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-7-9
	 * @return boolean
	 */
	@Transactional
	@Override
	public boolean isToLockUser(User user) {
		lockService.updateErrorTimes(user);
		int loginErrTimes = lockService.queryErrorTimes(user);
		return loginErrTimes >=5 ? true : false;
	}

	/**
	 * 清除最后一次输入错误密码时间
	 */
	@Override
	public void cleanLastErrorTime(User user) {
		lockService.cleanUserlastErrTime(user);
	}

	/**
	 * 查询最后一次输入错误密码时间
	 */
	@Override
	public Date queryLastLoginErrTime(User user) {
		return lockService.queryLastLoginErrTime(user);
	}

	/**
	 * 进行时间验证
	 */
	@Override
	public boolean validateTimes(User user) {
		if (isToLockUser(user)){
			lockService.insertUserlastErrTime(user);
			return true;	// 账号已锁定
		}else{
			return false; 	// 账号未锁定
		}

	}
	/**
	 * 清空错误次数
	 */
	@Override
	public void cleanErrorTimes(User user) {
		lockService.cleanErrorTimes(user);
	}
	
	/**
	 * 查询密码错误次数
	 */
	@Override
	public int queryErrorTimes(User user) {
		return  lockService.queryErrorTimes(user);
	}
}
