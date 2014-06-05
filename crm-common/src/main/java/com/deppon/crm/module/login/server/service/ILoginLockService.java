/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ILoginLockService.java
 * @package com.deppon.crm.module.login.server.service 
 * @author Administrator
 * @version 0.1 2012-7-9
 */
package com.deppon.crm.module.login.server.service;

import java.util.Date;

import com.deppon.crm.module.authorization.shared.domain.User;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ILoginLockService.java
 * @package com.deppon.crm.module.login.server.service 
 * @author 苏玉军
 * @version 0.1 2012-7-9
 */

public interface ILoginLockService {

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-9
	 * @param user
	 * @return
	 * long
	 */
	public Date queryLastLoginErrTime(User user);

	/**
	 * 
	 * <p>
	 * 新增最后一次登陆失败时间<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-9
	 * @param user
	 * @return
	 * boolean
	 */
	public boolean insertUserlastErrTime(User user);
	
	/**
	 * 
	 * <p>
	 * 清除最后一次登陆失败时间<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-9
	 * @param user
	 * @return
	 * boolean
	 */
	public boolean cleanUserlastErrTime(User user);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-11
	 * @param user
	 * void
	 */
	public void cleanErrorTimes(User user);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-11
	 * @param user
	 * @return
	 * int
	 */
	public int queryErrorTimes(User user);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-11
	 * @param user
	 * @return
	 * boolean
	 */
	public boolean updateErrorTimes(User user);
}
