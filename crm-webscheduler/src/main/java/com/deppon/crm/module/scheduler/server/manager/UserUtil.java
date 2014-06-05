package com.deppon.crm.module.scheduler.server.manager;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

public class UserUtil {
	/**
	 * 
	 * <p>
	 * 设置登录用户为超级管理员<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-1
	 * void
	 */
	public static void setUserToAdmin(){
		User user =new User();
		user.setId("1");
		Employee  empCode = new Employee();
		empCode.setId("86301");
		user.setEmpCode(empCode);
		UserContext.setCurrentUser(user);
	}
}
