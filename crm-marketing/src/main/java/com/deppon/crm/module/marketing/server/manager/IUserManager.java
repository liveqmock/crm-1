/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title UserManager.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-4-19
 */
package com.deppon.crm.module.marketing.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**   
 * <p>
 * Description: 用户manager,调用common中的IUserService<br />
 * </p>
 * @title UserManager.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-4-19
 */

public interface IUserManager {

	/**
	 * 通过用户ID得到用户分配的权限的部门ID
	 * @param userId 用户ID
	 * @return deptIds 用户所分配部门Id
	 */
	 List<String> getUserDeptIds(String userId);

	/**
	 * 通过部门ID得到部门负责人的用户ID
	 * @param deptId 部门ID
	 * @return userId 用户Id
	 */
	 String queryManagerUserIdByDeptId(String deptId);

	/**
	 * 通过部门ID，得到部门下的所有用户信息
	 * @param deptIds
	 * @return
	 */
	 List<User> queryUsersByDeptIds(List<String> deptIds);
	
	/**
	 * <p>
	 * Description: 通过部门ID获取部门下所有人员<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-26
	 * @param deptId
	 * @return
	 * List<Employee>
	 */
	 List<Employee> getAllEmployeesByDeptId(String[] deptIds);
	
	
	/**
	 * <p>
	 * Description: 通过部门ID获取部门下所有人员<br />
	 * </p>
	 * @author zhangdeng
	 * @version 0.1 2012-4-26
	 * @param deptId
	 * @return
	 * List<Employee>
	 */
	 List<Employee> getEmployeesByDeptId(String deptIds);
	
	/**
	 * <p>
	 * Description: 获取用户授权部门信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-26
	 * @param deptIds
	 * @return
	 * List<Department>
	 */
	 List<Department> getAllAuthedDepts(User user);
	
	/**
	 * <p>
	 * Description: 根据部门名称<br />
	 * </p>
	 * @author zhangdeng
	 * @version 0.1 2012-4-26
	 * @param deptIds
	 * @return
	 * List<Department>
	 */
	 Map<String,Object>  queryDeptListByDeptName(User user,String deptName, int start,
			int limit);
	
	/**
	 * <p>
	 * Description: 获取默认部门信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-7-3
	 * @param user
	 * @return
	 * Department
	 */
	 Department getDefaultDep(User user);
//	/**
//	 * 通过授权部门名称模糊查询部门对象集合
//	 * 
//	 * @param depIds、deptName、start、limit
//	 * @return Map<String,Object>
//	 * @since JDK1.6
//	 */
//	
//	 Map<String, Object> getExecuteDep(Set<String> depIds,
//			String deptName, int start, int limit);
	
	/**
	 * 
	 * <p>
	 * Description:开发监控计划部门查询方法<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-1-15
	 * @param user
	 * @param deptName
	 * @param start
	 * @param limit
	 * @return
	 * Map<String,Object>
	 */
	Map<String,Object> queryDeptListByDeptNameForMonitor(User user, String deptName, int start,int limit);
}
