package com.deppon.crm.module.authorization.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultHandler;

import com.deppon.crm.module.authorization.server.dao.IUserDeptAndRoleDao;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * @description 用户授权部门和角色数据处理
 * @version 1.0
 * @author patern
 * @update 2011-10-21 下午07:52:39 
 */
public class UserDeptAndRoleDao  extends iBatis3DaoImpl implements IUserDeptAndRoleDao{

	public void deleteUserDeptAuthByUserId(String userId) {
		this.getSqlSession().delete("com.deppon.crm.module.authorization.shared.domain.UserDepartment.deleteUserDeptAuthByUserId", userId);
	}

	public void insertUserDeptAuth(String userId, String deptId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("deptId", deptId);
		this.getSqlSession().insert("com.deppon.crm.module.authorization.shared.domain.UserDepartment.insertUserDeptAuth", map);
	}
	/**
	 * 
	 * 根据条件删除分配表
	 * @param userId 用户ID
	 * @param deptId 用户所分配部门Id
	 */
	@Override
	public void deleteUserDept(String userId, String deptId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("deptId", deptId);
		this.getSqlSession().insert("com.deppon.crm.module.authorization.shared.domain.UserDepartment.deleteUserDept", map);
	}
	/**
	 * 
	 * 根据条件批量删除分配表
	 * @param userId 用户ID
	 * @param deptIds 用户所分配部门Id
	 */
	@Override
	public void deleteUserDeptAll(String userId, List<String> deptIds) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("deptIds", deptIds);
		this.getSqlSession().insert("com.deppon.crm.module.authorization.shared.domain.UserDepartment.deleteUserDeptAll", map);
	}

	public void deleteUserRoleAuthByUserId(String userId) {
		this.getSqlSession().delete("com.deppon.crm.module.authorization.shared.domain.UserRole.deleteUserRoleAuthByUserId",userId);
	}

	public void insertUserRoleAuth(String userId, String roleId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		this.getSqlSession().insert("com.deppon.crm.module.authorization.shared.domain.UserRole.insertUserRoleAuth", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getUserDeptIds(String userId){
		List<String> deptIds = this.getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.UserDepartment.getUserDeptIds", userId);		
		return deptIds;									
	}
}
