/**   
 * @title BaseModelDaoJdbcImpl.java
 * @package com.deppon.crm.recompense.dao.impl
 * @description what to do
 * @author 潘光均
 * @update 2012-2-10 上午11:00:07
 * @version V1.0   
 */
package com.deppon.crm.module.recompense.server.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.server.dao.BaseModelDao;
import com.deppon.crm.module.recompense.shared.domain.UserRoleDeptRelation;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * @description 实现基础表的查询 ----------------待添加校验
 * @author 潘光均
 * @version 0.1 2012-2-10
 * @date 2012-2-10
 */

public class BaseModelDaoImpl extends iBatis3DaoImpl implements BaseModelDao {
	private static final String NAMESPACE = "com.deppon.crm.recompense.domain.BaseModelDao.";

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
	@Override
	public boolean insertUserRoleDepartment(UserRoleDeptRelation urd) {
		int result = this.getSqlSession().insert(
				NAMESPACE + "insertUserRoleDepartment", urd);
		return result > 0 ? true : false;
	}

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
	@Override
	public boolean insertUserRoleDepartment(String userId, String roleId,
			List<String> deptIds) {
		if (deptIds != null && deptIds.size() > 0) {
			UserRoleDeptRelation urd = new UserRoleDeptRelation();

			User u = new User();
			u.setId(userId);
			urd.setUser(u);

			Role r = new Role();
			r.setId(roleId);
			urd.setRole(r);

			Department dept = null;
			//便利部门id循环插入
			for (String deptId : deptIds) {
				dept = new Department();
				dept.setId(deptId);
				urd.setDept(dept);

				this.insertUserRoleDepartment(urd);
			}
			return true;
		}
		return false;
	}

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
	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleDeptRelation> getUserRoleDeptRelationByUserId(
			String userId) {
		//判断user是否为空
		if (userId == null || userId.equals("")) {
			return this.getSqlSession().selectList(NAMESPACE + "getByAllUser");
		} else {
			return this.getSqlSession().selectList(NAMESPACE + "getByUserId",
					userId);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:获取用户部门角色关系<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param id
	 * @return
	 * boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleDeptRelation> getAllUserRoleDeptRelation(int start,
			int limit) {
		//设置分页
		RowBounds bound = new RowBounds(start, limit);
		return getSqlSession().selectList(NAMESPACE + "getAll", bound);
	}

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
	@Override
	public boolean deleteUserRoleDeptRelationById(String id) {
		int result = this.getSqlSession().delete(NAMESPACE + "deleteById", id);
		return result > 0 ? true : false;
	}

	/**
	 * 
	 * <p>
	 * Description:根据用户id删除用户部门角色<<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param userId
	 * @return
	 *
	 */
	@Override
	public boolean deleteUserRoleDepartmentByUserId(String userId) {
		int result = getSqlSession().delete(NAMESPACE + "deleteByUserId",
				userId);
		return result > 0 ? true : false;
	}

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
	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleDeptRelation> getUserRoleDeptRelationByDeptId(
			String deptId) {
		return this.getSqlSession().selectList(NAMESPACE + "getByDeptId",
				deptId);
	}

}
