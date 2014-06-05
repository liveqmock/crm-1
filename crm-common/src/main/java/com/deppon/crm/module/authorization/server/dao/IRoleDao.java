package com.deppon.crm.module.authorization.server.dao;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.Role;

/**
 * 角色数据处理方法
 * @author Administrator
 *
 */
public interface IRoleDao {

	/**
	 * 查询所有角色
	 * @return
	 */
	List<Role> getAll();
	
	/**
	 * 提供缓存加载所有的角色对象与角色所对应的功能ID
	 * @return
	 */
	List<Role> getAllRole();
	
	/**
	 * 查询所有角色
	 * @param role 查询条件信息类
	 * @return 角色对象List
	 */
	List<Role> getAll(Role role);
	

	/**
	 * 分页查询所有角色
	 * @param role 查询条件信息类
	 * @param limit 查询条数
	 * @param start 查询的起始位置
	 * @return 角色对象List
	 */
	List<Role> getAll(Role role, int limit, int start);
	
	/**
	 * 通过一些角色ID得到一个角色对象的集合
	 * @param roleIds
	 * @return
	 */
	List<Role> getByIds(List<String> roleIds);
	
	/**
	 * 通过ID，得到功能对象
	 * @param id
	 * @return
	 */
	Role getById(String id);

	/**
	 * 更新角色信息
	 * @param role 角色信息
	 */
	void update(Role role);

	/**
	 * 保存角色信息
	 * @param role 角色信息
	 */
	void insert(Role role);
	
	/**
	 * 保存角色权限信息
	 * @param roleId
	 * @param functionId
	 */
	void insert(String roleId, String functionId);

	/**
	 * 通过ID删除角色信息
	 * @param id
	 */
	void deleteById(String id);
	
	/**
	 * 根据角色ID，删除权限角色表中的数据
	 * @param id
	 */
	void deleteFunctionRoleById(String id);
	
	/**
	 * 根据角色ID，删除授权用户角色表中的数据
	 * @param roleId
	 */
	void deleteUserAuthRoleById(String roleId);

	/**
	 * 统计角色的条数
	 * @param function
	 * @return
	 */
	Long count(Role role);
	
	/**
	 * 查询最后更新时间
	 * @return
	 */
	Date getLastModifyTime();

	/**
	 * 根据角色ID，得到角色被分配的次数
	 * @param roleId
	 * @return
	 */
	Integer getUseRole(String roleId);

	/**
	 * 根据用户ID,查询当前授权用户所剩余的可分配的角色
	 * @param currentUserId 当前授权用户ID
	 * @param userId 待分配用户ID
	 * @return
	 */
	List<Role> getLeftRoles(String currentUserId,String userId);
	/**
	 * 根据用户ID,查询当前授权用户给用户已经分配的角色
	 * @param currentUserId 当前授权用户ID
	 * @param userId 待分配用户ID
	 * @return
	 */
	List<Role> getAuthedRoles(String currentUserId,String userId);

	/**
	 * 根据角色名称，得到角色的ID
	 * @param roleName
	 * @return
	 */
	List<String> getIdByName(String roleName);
//
//	/**
//	 * @param userId
//	 * @return
//	 * @description 根据登录用户Id查询该用户可以授权的角色
//	 * @version 1.0
//	 * @author patern
//	 * @update 2011-10-26 下午04:32:24
//	 */
//	List<Role> queryAllRoles(String userId);

	/**
	 * 查询该用户可以授权的角色
	 * @param currentUserId 当前授权用户ID
	 */
	List<Role> getAllAuthRoles(String currentUserId);

	/**
	 * 通过用户ID，非当前用户授权的角色信息ID集合
	 * getDeptIdsNoCurrentUserAuthDept
	 * @param currentUserId
	 * @param userId
	 * @return
	 * @return List<String>
	 * @since:
	 */
	List<String> getRoleIdsNoCurrentUserAuthRole(String currentUserId, String userId);
	
	/**
	 * 通过角色ID，获取角色信息以及对应的funtionIds
	 * @author dpadmin
	 * @createDate 2013-8-29
	 * getDeptIdsNoCurrentUserAuthDept
	 * @param roleId
	 * @return Role
	 */
	Role getRoleById(String roleId);
	/**
	 * 
	 * <p>
	 * 根据角色名称模糊查询角色(虚拟岗位角色 对应)<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-28
	 * @param roleName
	 * @param isMap  0 未映射   1已映射
	 * @return
	 * Map<String,Object>
	 */
	List<Role> queryRoleByVirtualPositionName(String virtualPositionId,
			String roleName, String isMap);

}
