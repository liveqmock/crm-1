/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title LoginLockService.java
 * @package com.deppon.crm.module.login.server.service.impl 
 * @author Administrator
 * @version 0.1 2012-7-9
 */
package com.deppon.crm.module.login.server.service.impl;

import java.util.Date;

import com.deppon.crm.module.authorization.server.dao.ILoginLockDao;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.login.server.service.ILoginLockService;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title LoginLockService.java
 * @package com.deppon.crm.module.login.server.service.impl 
 * @author 苏玉军
 * @version 0.1 2012-7-9
 */

public class LoginLockService implements ILoginLockService {
	private ILoginLockDao lockDao;
	
	public ILoginLockDao getLockDao() {
		return lockDao;
	}

	public void setLockDao(ILoginLockDao lockDao) {
		this.lockDao = lockDao;
	}

	public Date queryLastLoginErrTime(User user) {
		return lockDao.queryUserlastErrTime(user);
	}

	
	@Override
	public boolean insertUserlastErrTime(User user) {
		return lockDao.insertUserlastErrTime(user);
	}


	@Override
	public boolean cleanUserlastErrTime(User user) {
		return lockDao.cleanUserlastErrTime(user);
	}

	@Override
	public void cleanErrorTimes(User user) {
		lockDao.cleanErrorTimes(user);
	}

	@Override
	public int queryErrorTimes(User user) {
		return lockDao.queryErrorTimes(user);
	}

	@Override
	public boolean updateErrorTimes(User user) {
		return lockDao.updateErrorTimes(user);
	}
}
