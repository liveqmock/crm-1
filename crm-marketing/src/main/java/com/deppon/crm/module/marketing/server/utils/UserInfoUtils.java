package com.deppon.crm.module.marketing.server.utils;

import com.deppon.crm.module.authorization.shared.domain.User;

/**
 * <p>
 * 用户相关信息获取工具类<br />
 * </p>
 * 
 * @title UserInfoUtils.java
 * @package com.deppon.crm.module.marketing.server.utils
 * @author 苏玉军
 * @version 0.1 2012-11-8
 */

public class UserInfoUtils {
	/**
	 * 
	 * <p>
	 * 获取当前用户ID<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-11-8
	 * @return String
	 */
	public static String getCurrentUserId(User user) {
		//userid
		String userId = user.getEmpCode().getId();
		//return userid
		return userId;
	}
	
	/**
	 * 
	 * <p>
	 * 获取当前用户部门<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-11-8
	 * @param user
	 * @return
	 * String
	 */
	public static String getCurrentDeptId(User user){
		//deptID
		String deptId = user.getEmpCode().getDeptId().getId();
		//return deptid
		return deptId;
	}
}
