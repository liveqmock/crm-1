package com.deppon.crm.module.recompense.server.dao;

import java.util.List;

import com.deppon.crm.module.recompense.shared.domain.UserRoleDeptRelation;

/**
 * 
 * @description 角色部门用户关联
 * @author 安小虎
 * @version 0.1 2012-4-14
 * @date 2012-4-14
 */
public interface BaseModelDao {
	/**
	 * 
	 * <p>
	 * Description:保存用户角色部门<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param urd
	 * @return
	 * boolean
	 */
	boolean insertUserRoleDepartment(UserRoleDeptRelation urd);
	/**
	 * 
	 * <p>
	 * Description:保存用户角色部门<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param userId
	 * @param roleId
	 * @param deptIds
	 * @return
	 * boolean
	 */
	boolean insertUserRoleDepartment(String userId, String roleId,
			List<String> deptIds);
	/**
	 * 
	 * <p>
	 * Description:根据用户id或者用户部门权限<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param userId
	 * @return
	 * List<UserRoleDeptRelation>
	 */
	List<UserRoleDeptRelation> getUserRoleDeptRelationByUserId(String userId);
	/**
	 * 
	 * <p>
	 * Description:删除用户部门角色<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param id
	 * @return
	 * boolean
	 */
	boolean deleteUserRoleDeptRelationById(String id);
	/**
	 * 
	 * <p>
	 * Description:根据用户id删除用户部门角色<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param userId
	 * @return
	 * boolean
	 */
	boolean deleteUserRoleDepartmentByUserId(String userId);

	/**
	 * 
	 * <p>
	 * Description:获得分页的用户部门角色<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param start
	 * @param limit
	 * @return
	 * List<UserRoleDeptRelation>
	 */
	List<UserRoleDeptRelation> getAllUserRoleDeptRelation(int start, int limit);

	/**
	 * 
	 * @description 根据部门ID获得部门角色用户关系表.
	 * @author 安小虎
	 * @version 0.1 2012-4-21
	 * @param 部门ID
	 * @date 2012-4-21
	 * @return 部门角色用户对象集合
	 * @update 2012-4-21 上午11:01:09
	 */
	List<UserRoleDeptRelation> getUserRoleDeptRelationByDeptId(String deptId);
}
