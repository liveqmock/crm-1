package com.deppon.crm.module.authorization.server.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;

public interface IUserDao {
	/**
	 * 查询用户对象
	 * 
	 * @param id
	 *            查询条件
	 * @return 用户对象
	 */
	User getById(String id);

	/**
	 * 查询所有用户信息
	 * 
	 * @param user
	 *            查询条件
	 * @return 用户对象List
	 */
	List<User> getAll(User user);

	/**
	 * 更新用户对象
	 * 
	 * @param user
	 */
	void update(User user);

	/**
	 * 新增用户对象
	 * 
	 * @param user
	 */
	void insert(User user);

	/**
	 * 通过userId删除用户对象
	 * 
	 * @param userId
	 */
	void deleteById(String userId);

	/**
	 * 分页查询所有的用户对象
	 * 
	 * @param user
	 *            查询对象
	 * @param limit
	 *            查询条数
	 * @param start
	 *            查询的起始位置
	 * @return 用户对象List
	 */
	List<User> getAll(User user, int limit, int start);

	/**
	 * 统计用户对象的数量
	 * 
	 * @param user
	 * @return 用户数量
	 */
	Long count(User user);

	/**
	 * 最近更新时间
	 * 
	 * @return 最近更新时间
	 */
	Date getLastModifyTime();

	/**
	 * 通过UserId得到用户所拥有的角色ID、功能编码与权限部门ID
	 * 
	 * @param userId
	 * @return
	 */
	User getUserRoleFunDeptById(String userId);

	/**
	 * 通过最后更新时间，得到所有的最近更新的用户对象列表
	 * 
	 * @param lastModifyDate
	 * @return
	 */
	List<User> getByLastModifyDate(Date lastModifyDate);

	/**
	 * 通过用户的登录名，得到用户对象
	 * 
	 * @param loginName
	 * @return
	 */
	User getByLoginName(String loginName);

	/**
	 * 修改用户密码
	 * 
	 * @param user
	 */
	void updateUserPassword(User user);

	/**
	 * 登录成功后更新最后登录时间
	 * 
	 * @param user
	 */
	void updateLastLoginDate(User user);

	/**
	 * 查询所有用户信息根据部门ID
	 * 
	 * @param deptId
	 *            查询条件
	 * @return 用户对象List
	 */
	List<User> queryAllByDeptId(String deptId, int limit, int start);

	/**
	 * 统计用户对象的数量
	 * 
	 * @param deptId
	 * @return 用户数量
	 */
	Long count(String deptId);

	/**
	 * 通过部门ID得到部门负责人的用户ID
	 * 
	 * @param deptId
	 *            部门ID
	 * @return userId 用户Id
	 */
	String getManagerUserIdByDeptId(String deptId);

	/**
	 * 通过部门ID得到部门负责人的Employee ID
	 * 
	 * @param deptId
	 *            部门ID
	 * @return empId 用户Id
	 */
	String getManagerEmployeeIdByDeptId(String deptId);
	
	/**
	 * 通过部门ID，得到部门下的所有用户信息
	 * 
	 * @param deptIds
	 * @return
	 */
	List<User> getUsersByDeptIds(List<String> deptIds);
	List<User> queryUsersByDeptAndRole(String deptId, String roleId);
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
