package com.deppon.crm.module.authorization.server.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.server.dao.IRoleDao;
import com.deppon.crm.module.authorization.server.service.IRoleService;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.shared.exception.RoleException;
import com.deppon.crm.module.authorization.shared.exception.RoleExceptionType;
import com.deppon.crm.module.authorization.shared.exception.UserException;
import com.deppon.crm.module.authorization.shared.exception.UserExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

@Transactional(readOnly = true)
public class RoleService implements IRoleService {

	private IRoleDao roleDao;

	public List<Role> queryAll(Role role) {
		return roleDao.getAll(role);
	}

	@SuppressWarnings("serial")
	@Transactional
	public void save(Role role) {
		try {
			// 验证角色对象属性
			validateRoleField(role);
			// 判断角色名是否已经存在
			if (roleDao.getIdByName(role.getRoleName()).size() > 0) {
				RoleException e = new RoleException(
						RoleExceptionType.RoleHadName);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						e.getErrorArguments()) {
				};
			}
			roleDao.insert(role);
			for (String functionId : role.getFunctionIds()) {
				roleDao.insert(role.getId(), functionId);
			}
		} catch (RoleException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}

	@SuppressWarnings("serial")
	@Transactional
	public void update(Role role) {
		try {
			// 验证角色ID是否为空
			if (role.getId() == null && "".equals(role.getId().trim())) {
				RoleException e = new RoleException(
						RoleExceptionType.RoleIdNull);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						e.getErrorArguments()) {
				};
			}
			// 验证角色对象属性
			validateRoleField(role);
			Role dbRole = roleDao.getById(role.getId());
			// 判断角色名是否改变
			if (!dbRole.getRoleName().equals(role.getRoleName())) {
				// 判断角色名是否已经存在
				if (roleDao.getIdByName(role.getRoleName()).size() > 0) {
					RoleException e = new RoleException(
							RoleExceptionType.RoleHadName);
					throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
							e.getErrorArguments()) {
					};
				}
			}
			setUpdateRoleField(role, dbRole);
			// 设置属性的值
			roleDao.update(dbRole);
			// 删除角色权限表中的角色权限信息
			roleDao.deleteFunctionRoleById(dbRole.getId());
			// 插入角色权限信息
			for (String functionId : role.getFunctionIds()) {
				roleDao.insert(role.getId(), functionId);
			}
		} catch (RoleException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 设置更新角色信息
	 * 
	 * @param role
	 * @param dbRole
	 */
	private void setUpdateRoleField(Role role, Role dbRole) {
		// 设置角色名
		if (!role.getRoleName().equals(dbRole.getRoleName())) {
			dbRole.setRoleName(role.getRoleName());
		}
		// // 设置角色状态
		// if (!role.getStatus().equals(dbRole.getStatus())) {
		// dbRole.setStatus(role.getStatus());
		// // 如果角色状态为1,设置生效时间为当前时间，失效时间为空
		// if (dbRole.getStatus() == 1) {
		// dbRole.setValidDate(new Date());
		// dbRole.setInvalidDate(null);
		// }
		// // 如果角色状态为2，设置失效时间为当前时间，生效时间为空
		// if (dbRole.getStatus() == 2) {
		// dbRole.setInvalidDate(new Date());
		// dbRole.setValidDate(null);
		// }
		// }
		// 设置角色描述信息
		dbRole.setRoleDesc(role.getRoleDesc());
		// 设置修改人ID
		dbRole.setModifyUser(role.getModifyUser());
	}

	@SuppressWarnings("serial")
	@Transactional
	public void removeById(String roleId) {
		// 验证角色ID是否为空
		if (roleId == null || "".equals(roleId.trim())) {
			RoleException e = new RoleException(RoleExceptionType.RoleIdNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 删除前，查看角色是否已经被分配
		Integer count = roleDao.getUseRole(roleId);
		if (count != null && count > 0) {
			RoleException e = new RoleException(
					RoleExceptionType.RoleIsDistributed);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 删除授权用户角色表中的记录
		roleDao.deleteUserAuthRoleById(roleId);
		// 删除功能角色表中的记录
		roleDao.deleteFunctionRoleById(roleId);
		// 删除角色表中的记录
		roleDao.deleteById(roleId);
	}

	public List<Role> queryAll(Role role, int limit, int start) {
		return roleDao.getAll(role, limit, start);
	}

	public Long count(Role role) {
		Long num=roleDao.count(role);
		if(num==0){
			num=(long)1;
		}
		return num;
	}

	/*
	 * 验证角色对象属性
	 * 
	 * @param function
	 */
	@SuppressWarnings("serial")
	private void validateRoleField(Role role) {
		// 验证角色对象
		if (role == null) {
			RoleException e = new RoleException(
					RoleExceptionType.RoleIdNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		// 验证角色名
		if (role.getRoleName() == null || "".equals(role.getRoleName().trim())) {
			RoleException e = new RoleException(
					RoleExceptionType.RoleNameIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		// 判断角色名中是否包含空格
		if (role.getRoleName().contains(" ")) {
			RoleException e = new RoleException(
					RoleExceptionType.RoleHadBlank);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};

		}
		// 修改人：钟庭杰  修改时间：2012-4-13 17:37  修改说明：增加了roleName长度限定
		if (role.getRoleName().length()>64) {
			RoleException e = new RoleException(
					RoleExceptionType.RoleNameIsLong);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		// 修改人：钟庭杰  修改时间：2012-5-31 20:20  修改说明：增加了roleDesc长度限定
		if (role.getRoleDesc().length()>256) {
			RoleException e = new RoleException(
					RoleExceptionType.RoleDescIsLong);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		// // 验证角色状态
		// if (role.getStatus() == null) {
		// throw new RoleException(RoleExceptionType.RoleStatusIsNull);
		// }
	}

	public List<Role> queryLeftRoles(String currentUserId,String userId) {
		List<Role> roles = null;
		if(userId==null){
			roles = roleDao.getAllAuthRoles(currentUserId);			
		}else{
			roles = roleDao.getLeftRoles(currentUserId,userId);
		}
		return roles;
	}

	@SuppressWarnings({ "serial"})
	public List<Role> queryAuthedRoles(String currentUserId,String userId) {
		if(userId==null||"".equals(userId.trim())){
			UserException e = new UserException(UserExceptionType.UserIdIsNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		return roleDao.getAuthedRoles(currentUserId,userId);
	}

	@Override
	public List<Role> queryRoleByVirtualPositionName(String virtualPositionId,
			String roleName, String isMap) {
		return roleDao.queryRoleByVirtualPositionName(virtualPositionId,roleName,isMap);
	}
	
	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}


}
