package com.deppon.crm.module.logmoniting.utils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

public class UserUtil {

	public static void setUserToAdmin(){
		User user =new User();
		user.setId("29988");
		Department dept = new Department();
		dept.setId("1");
		dept.setDeptName("XXX营业部");
		Employee  empCode = new Employee();
		empCode.setDeptId(dept);
		empCode.setId("29988");
		user.setEmpCode(empCode);
		UserContext.setCurrentUser(user);
	}
}
