package com.deppon.crm.module.authorization.server.service;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.foss.framework.service.IService;

/**
 * 用户管理业务逻辑层
 * 
 * @author Administrator
 * 
 */
public interface IUserService extends IService {

	/**
	 * 查询所有的用户
	 * 
	 * @param user
	 * @return
	 */
	List<User> queryAll(User user);

	/**
	 * 保存用户
	 * 
	 * @param user
	 * @param roleIds
	 * @param deptIds
	 */
	void save(User user, List<String> roleIds, List<String> departmentIds,
			List<String> departmentIdsRemove);

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @param roleIds
	 * @param deptIds
	 */
	void update(User user, List<String> roleIds, List<String> departmentIds,
			List<String> departmentIdsRemove);

	/**
	 * 分页查询用户信息
	 * 
	 * @param user
	 * @param limit
	 * @param start
	 * @return
	 */
	List<User> queryAll(User user, int limit, int start);

	/**
	 * 统计用户数据
	 * 
	 * @param user
	 * @return
	 */
	Long count(User user);

	/**
	 * 通过用户名查询用户
	 * 
	 * @param loginName
	 * @return
	 */
	User findByLoginName(String loginName);

	/**
	 * @param user
	 * @description 更改用户密码
	 * @version 1.0
	 * @author patern
	 * @update 2011-10-24 上午11:54:22
	 */
	void updatePassword(User user);

	/**
	 * 分页查询用户信息根据部门ID
	 * 
	 * @param deptId
	 * @param limit
	 * @param start
	 * @return
	 */
	List<User> queryAllByDeptId(String deptId, int limit, int start);

	/**
	 * 统计用户数据根据deptId
	 * 
	 * @param deptId
	 * @return
	 */
	Long count(String deptId);

	/**
	 * 通过用户ID得到用户分配的权限的部门ID
	 * 
	 * @param userId
	 *            用户ID
	 * @return deptIds 用户所分配部门Id
	 */
	public List<String> getUserDeptIds(String userId);

	/**
	 * 通过部门ID得到部门负责人的用户ID
	 * 
	 * @param deptId
	 *            部门ID
	 * @return userId 用户Id
	 */
	public String queryManagerUserIdByDeptId(String deptId);

	/**
	 * 通过部门ID得到部门负责人的Employee ID
	 * 
	 * @param deptId
	 *            部门ID
	 * @return empId 用户Id
	 */
	public String queryManagerEmployeeIdByDeptId(String deptId);
	
	/**
	 * 通过部门ID，得到部门下的所有用户信息
	 * 
	 * @param deptIds
	 * @return
	 */
	List<User> queryUsersByDeptIds(List<String> deptIds);

	List<User> queryUsersByDeptAndRole(String deptId, String roleId);

	public User getUserById(String id);
	public User getUserRoleFunDeptById(String userId);
	/**
	 * 同步UUMS 数据，进行相应的数据同步，权限分配
	 * @author ZhangZW
	 * @update 2014-1-6 
	 * @param empCode changeType postion orgId
	 * @return
	 */
	void  syncUser(String empCode,String changeType,String postion,String orgId);
	public User selectByCode(String empCode);
}
