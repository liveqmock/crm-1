package com.deppon.crm.module.authorization.server.service;

import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.foss.framework.service.IService;

/**
 * 角色业务逻辑服务层
 * 
 * @author Administrator
 * 
 */
public interface IRoleService extends IService {

	/**
	 * 查询所有角色对象
	 * @return
	 */
	List<Role> queryAll(Role role);

	/**
	 * 分页查询所有的角色对象
	 * @param role 查询角色对象信息的条件
	 * @param limit    分页最大记录数
	 * @param start	        分页开始记录数
	 * @return
	 */
	List<Role> queryAll(Role role, int limit, int start);

	/**
	 * 保存角色对象
	 * 
	 * @param role 角色对象
	 */
	void save(Role role);

	/**
	 * 修改角色对象
	 * 
	 * @param role 角色对象
	 */
	void update(Role role);
	
//	/**
//	 * 修改角色对象
//	 * 
//	 * @param role 角色对象
//	 */
//	void update(Role role,String oldRoleName);

	/**
	 * 通过ID删除角色对象
	 * @param id 角色对象的ID
	 */
	void removeById(String id);
	
	/**
	 * 统计角色对象数量
	 * @param role 查询角色对象信息的条件
	 * @return
	 */
	Long count(Role role);


	/**
	 * 根据用户ID,查询当前授权用户所剩余的可分配的角色
	 * @param currentUserId 当前授权用户ID
	 * @param userId 待分配用户ID
	 * @return
	 */
	List<Role> queryLeftRoles(String currenUserId,String userId);
	
	/**
	 * 根据用户ID,查询当前授权用户给用户已经分配的角色
	 * @param currentUserId 当前授权用户ID
	 * @param userId 待分配用户ID
	 * @return
	 */
	List<Role> queryAuthedRoles(String currentUserId,String userId);
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

//	/**
//	 * @param userId
//	 * @return
//	 * @description function
//	 * @version 1.0
//	 * @author patern
//	 * @update 2011-10-26 下午04:31:28
//	 */
//	List<Role> queryAllRoles(String userId);

}
