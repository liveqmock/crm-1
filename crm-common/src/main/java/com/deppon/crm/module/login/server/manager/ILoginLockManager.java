/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ILoginLockManager.java
 * @package com.deppon.crm.module.login.server.manager 
 * @author Administrator
 * @version 0.1 2012-7-9
 */
package com.deppon.crm.module.login.server.manager;

import java.util.Date;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.foss.framework.server.web.session.ISession;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ILoginLockManager.java
 * @package com.deppon.crm.module.login.server.manager 
 * @author 苏玉军
 * @version 0.1 2012-7-9
 */

public interface ILoginLockManager {
	/**
	 * 
	 * <p>
	 * 判断是否要锁定账号30分钟<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-9
	 * @return
	 * boolean
	 */
	public boolean isToLockUser(User user);
	
	/**
	 * 
	 * <p>
	 * 判断最后一次尝试登陆时间与当前时间是否相差超过30分钟<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-9
	 * @return
	 * boolean
	 */
	public boolean isThirthMins(User user);

	/**
	 * <p>
	 * 清除记忆时间<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-9
	 * @param user
	 * void
	 */
	public void cleanLastErrorTime(User user);
	/**
	 * 
	 * <p>
	 * 查询最后一次错误时间<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-9
	 * @param user
	 * @return
	 * Date
	 */
	public Date queryLastLoginErrTime(User user);
	/**
	 * 
	 * <p>
	 * 验证是否可登陆操作br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-9
	 * @param user
	 * @return
	 * boolean
	 */
	public boolean validateTimes(User user);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-10
	 * @param user
	 * void
	 */
	public void cleanErrorTimes(User user);
	/**
	 * 查询密码错误次数
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-12
	 * @param user
	 * @return
	 * int
	 */
	public int queryErrorTimes(User user);
}
