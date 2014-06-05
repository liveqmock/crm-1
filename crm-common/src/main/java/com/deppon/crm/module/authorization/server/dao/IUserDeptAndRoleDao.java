package com.deppon.crm.module.authorization.server.dao;

import java.util.List;
import java.util.Map;



/**
 * 用户分配角色与部门数据访问层
 */

public interface IUserDeptAndRoleDao {
	/**
	 * 
	 * 通过用户id删除授权部门
	 * @param userId 用户ID
	 */
	void deleteUserDeptAuthByUserId(String userId);
	
	/**
	 * 
	 * 根据用户Id删除用户拥有角色
	 * @param userId 用户ID
	 */
	void deleteUserRoleAuthByUserId(String userId);
	
	/**
	 * 
	 * 插入用户拥有部门权限
	 * @param userId 用户ID
	 * @param deptIds 用户所分配部门Id
	 */
	void insertUserDeptAuth(String userId,String deptId);
	
	
	/**
	 * 
	 * 根据条件删除分配表
	 * @param userId 用户ID
	 * @param deptId 用户所分配部门Id
	 */
	void deleteUserDept(String userId,String deptId);
	
	/**
	 * 
	 * 插入用户拥有角色
	 * @param userId 用户ID
	 * @param roleId 角色ID
	 */
	void insertUserRoleAuth(String userId,String roleId);

	/**
	 * 通过用户ID得到用户分配的权限的部门ID
	 * @param userId 用户ID
	 * @return deptIds 用户所分配部门Id
	 */
	public List<String> getUserDeptIds(String userId);
	/**
	 * 
	 * 根据条件批量删除分配表
	 * @param userId 用户ID
	 * @param deptIds 用户所分配部门Ids
	 */
	void deleteUserDeptAll(String userId, List<String> deptIds);

}
