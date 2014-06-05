package com.deppon.crm.module.customer.server.util;

import java.util.HashSet;
import java.util.Set;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.shared.domain.Department;
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
		user.setId("29988");
		Department dept = new Department();
		dept.setId("1");
		dept.setStandardCode("DP123456");
		dept.setDeptName("XXX营业部");
		Employee  empCode = new Employee();
		empCode.setDeptId(dept);
		empCode.setId("29988");
		user.setEmpCode(empCode);
		Set<String> roles = new HashSet<String>();
		roles.add("1004");
		user.setRoleids(roles);
		UserContext.setCurrentUser(user);
	}
}
