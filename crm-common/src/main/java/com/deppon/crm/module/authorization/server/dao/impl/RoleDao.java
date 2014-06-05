package com.deppon.crm.module.authorization.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.authorization.server.dao.IRoleDao;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

@SuppressWarnings("unchecked")
public class RoleDao extends iBatis3DaoImpl implements IRoleDao {

	// 增加批量处理的方法
	// private SqlSessionFactory sqlSessionFactory;
	//
	// private SqlSession getCustomSqlSession(ExecutorType executorType) {
	// // SqlSessionFactory ssf=template.getSqlSessionFactory();
	// SqlSession sqlSession = SqlSessionUtils.getSqlSession(
	// sqlSessionTemplate.getSqlSessionFactory(), executorType,
	// new PersistenceExceptionTranslator() {
	// @Override
	// public DataAccessException translateExceptionIfPossible(
	// RuntimeException re) {
	// return new DataAccessException("test batch fail!", re) {
	// };
	// }
	// });
	// return sqlSession;
	// }

	public List<Role> getAll() {
		List<Role> list = null;
		list = getSqlSession()
				.selectList(
						"com.deppon.crm.module.authorization.shared.domain.Role.getAll",
						new Role());
		return list;
	}


	public List<Role> getAllRole() {
		List<Role> list = null;
		list = getSqlSession()
				.selectList(
						"com.deppon.crm.module.authorization.shared.domain.Role.getAllRole");
		return list;
	}
	
	public List<Role> getAll(Role role) {
		List<Role> list = null;
		Role model = new Role();
		if (role != null) {
			if (role.getId() != null && !"".equals(role.getId())) {
				String id = "%" + role.getId() + "%";
				model.setId(id);
			}
			if (role.getRoleName() != null && !"".equals(role.getRoleName())) {
				String roleName = "%" + role.getRoleName() + "%";
				model.setRoleName(roleName);
			}
		}
		list = getSqlSession()
				.selectList(
						"com.deppon.crm.module.authorization.shared.domain.Role.getAll",
						model);
		return list;
	}

	public List<Role> getByIds(List<String> roleIds) {
		List<Role> roles = getSqlSession()
				.selectList(
						"com.deppon.crm.module.authorization.shared.domain.Role.getByIds",
						roleIds);
		return roles;
	}

	public Role getById(String id) {
		Role role = (Role) getSqlSession()
				.selectOne(
						"com.deppon.crm.module.authorization.shared.domain.Role.getById",
						id);
		return role;
	}

	public void update(Role role) {
		getSqlSession()
				.update("com.deppon.crm.module.authorization.shared.domain.Role.update",
						role);
	}

	public void insert(Role role) {
		getSqlSession()
				.insert("com.deppon.crm.module.authorization.shared.domain.Role.insert",
						role);
	}

	public void insert(String roleId, String functionId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("roleId", roleId);
		map.put("functionId", functionId);
		getSqlSession()
				.insert("com.deppon.crm.module.authorization.shared.domain.RoleFunction.insert",
						map);
	}

	public void deleteById(String id) {
		getSqlSession()
				.delete("com.deppon.crm.module.authorization.shared.domain.Role.deleteById",
						id);
	}

	public void deleteFunctionRoleById(String id) {
		getSqlSession()
				.delete("com.deppon.crm.module.authorization.shared.domain.RoleFunction.deleteById",
						id);
	}

	public void deleteUserAuthRoleById(String id) {
		getSqlSession()
				.delete("com.deppon.crm.module.authorization.shared.domain.UserAuthRole.deleteByRoleId",
						id);
	}

	public List<Role> getAll(Role role, int limit, int start) {
		RowBounds rowBounds = new RowBounds(start, limit);
		List<Role> list = null;
		Role model = new Role();
		if (role != null) {
			if (role.getId() != null && !"".equals(role.getId())) {
				String id = "%" + role.getId() + "%";
				model.setId(id);
			}
			if (role.getRoleName() != null && !"".equals(role.getRoleName())) {
				String roleName = "%" + role.getRoleName() + "%";
				model.setRoleName(roleName);
			}
		}
		list = getSqlSession()
				.selectList(
						"com.deppon.crm.module.authorization.shared.domain.Role.getAll",
						model, rowBounds);
		return list;
	}

	public Long count(Role role) {
		Role model = new Role();
		if (role != null) {
			if (role.getId() != null && !"".equals(role.getId())) {
				String id = "%" + role.getId() + "%";
				model.setId(id);
			}
			if (role.getRoleName() != null && !"".equals(role.getRoleName())) {
				String roleName = "%" + role.getRoleName() + "%";
				model.setRoleName(roleName);
			}
		}
		Long count = (Long) getSqlSession()
				.selectOne(
						"com.deppon.crm.module.authorization.shared.domain.Role.count",
						model);
		return count;
	}

	public Date getLastModifyTime() {
		Date lastModyfyTime = (Date) getSqlSession()
				.selectOne(
						"com.deppon.crm.module.authorization.shared.domain.Role.getLastModifyTime");
		return lastModyfyTime;
	}

	public Integer getUseRole(String roleId) {
		Integer count = (Integer) getSqlSession()
				.selectOne(
						"com.deppon.crm.module.authorization.shared.domain.UserRole.getUseRoleCount",
						roleId);
		return count;
	}

	public List<Role> getLeftRoles(String currentUserId, String userId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("currentUserId", currentUserId);
		map.put("userId", userId);
		List<Role> list = getSqlSession()
				.selectList(
						"com.deppon.crm.module.authorization.shared.domain.Role.getLeftRoleByUserId",
						map);
		return list;
	}

	public List<Role> getAuthedRoles(String currentUserId, String userId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("currentUserId", currentUserId);
		map.put("userId", userId);
		List<Role> list = getSqlSession()
				.selectList(
						"com.deppon.crm.module.authorization.shared.domain.Role.getAuthedRoleByUserId",
						map);
		return list;
	}
	
	public List<Role> getAllAuthRoles(String currentUserId) {
		List<Role> list = getSqlSession()
		.selectList(
				"com.deppon.crm.module.authorization.shared.domain.Role.getAllAuthRoleByUserId",
				currentUserId);
		return list;
	}

	public List<String> getIdByName(String roleName) {
		List<String> ids = getSqlSession()
				.selectList(
						"com.deppon.crm.module.authorization.shared.domain.Role.getIdByName",
						roleName);
		return ids;
	}

	@Override
	public List<String> getRoleIdsNoCurrentUserAuthRole(String currentUserId,
			String userId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("currentUserId", currentUserId);
		map.put("userId", userId);
		List<String> ids = getSqlSession()
		.selectList(
				"com.deppon.crm.module.authorization.shared.domain.Role.getRoleIdsNoCurrentUserAuthRole",
				map);
		return ids;
	}


	@Override
	public Role getRoleById(String roleId) {
		
		return (Role) getSqlSession()
				.selectOne("com.deppon.crm.module.authorization.shared.domain.Role.getAllRole",roleId);
	}

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
	@Override
	public List<Role> queryRoleByVirtualPositionName(String virtualPositionId,
			String roleName, String isMap) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("virtualPositionId",virtualPositionId);
		params.put("roleName",roleName);
		params.put("isMap",isMap);
		return this.getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.Role.queryRoleByVirtualPositionName", params);
	}

}
