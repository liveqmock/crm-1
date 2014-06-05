package com.deppon.crm.module.authorization.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.server.dao.IAuthorizeDao;
import com.deppon.crm.module.authorization.server.dao.IRoleDao;
import com.deppon.crm.module.authorization.server.dao.IUserDao;
import com.deppon.crm.module.authorization.server.dao.IUserDeptAndRoleDao;
import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.authorization.shared.exception.UserException;
import com.deppon.crm.module.authorization.shared.exception.UserExceptionType;
import com.deppon.crm.module.frameworkimpl.server.util.CryptoUtil;
import com.deppon.crm.module.organization.server.dao.IDepartmentDao;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;

@Transactional(readOnly = true)
public class UserService implements IUserService {

	private IUserDao userDao;

	private IUserDeptAndRoleDao userDeptAndRoleDao;

	private IRoleDao roleDao;

	private IDepartmentDao departmentDao;
	private IAuthorizeDao authorizeDao;

	public List<User> queryAll(User user) {
		return userDao.getAll(user);
	}

	@SuppressWarnings("serial")
	@Transactional
	public void save(User user, List<String> roleIds, List<String> departmentIds,
			List<String> departmentIdsRemove) {
		validateAndSetField(user);
		// 验证用户是否已经存在
		User dbUser = userDao.getByLoginName(user.getLoginName());
		if (dbUser != null) {
			UserException e = new UserException(UserExceptionType.UserIsExist);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		userDao.insert(user);
		if (departmentIds != null) {
			// 修改结束
			for (String dept : departmentIds) {
				userDeptAndRoleDao.insertUserDeptAuth(user.getId(), dept);
			}
		}
		if (departmentIdsRemove != null) {
			// 修改结束
			for (String dept : departmentIdsRemove) {
				userDeptAndRoleDao.deleteUserDept(user.getId(), dept);
			}
		}
		if (roleIds != null) {
			for (String role : roleIds) {
				userDeptAndRoleDao.insertUserRoleAuth(user.getId(), role);
			}
		}
	}

	@SuppressWarnings("serial")
	private void validateAndSetField(User user) {
		// 验证用户是否为空
		if (user == null) {
			UserException e = new UserException(UserExceptionType.UserIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 验证用户名是否为空
		if (user.getLoginName() == null || "".equals(user.getLoginName())) {
			UserException e = new UserException(
					UserExceptionType.LoginNameIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 验证密码不能为空
		if (user.getPassword() == null || "".equals(user.getPassword())) {
			UserException e = new UserException(
					UserExceptionType.UserPasswordIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 验证用户状态不能为空
		if (null == user.getStatus()) {
			UserException e = new UserException(
					UserExceptionType.UserStatusIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 设置启用与禁用时间
		if (user.getStatus() == 1) {
			user.setValidDate(new Date());
			user.setInvalidDate(null);
		} else {
			user.setInvalidDate(new Date());
			user.setValidDate(null);
		}
	}

	@SuppressWarnings("serial")
	@Transactional
	public void update(User user, List<String> roleIds, List<String> departmentIds,
			List<String> departmentIdsRemove) {
		validateAndSetField(user);
		if (user.getId() == null && "".equals(user.getId().trim())) {
			UserException e = new UserException(UserExceptionType.UserIdIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 验证用户是否已经存在
		User dbUser = userDao.getByLoginName(user.getLoginName());
		if (dbUser != null) {
			if (!dbUser.getId().equals(user.getId())) {
				UserException e = new UserException(
						UserExceptionType.UserIsExist);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}
		// 修改人:ztjie
		// 修改时间：2012-04-17
		// 修改说明：得到非当前用户分配的部门信息与角色信息
		// 修改前：
		// 修改后:
		// 得到非当前用户授权的角色信息
		List<String> authRoleIds = roleDao.getRoleIdsNoCurrentUserAuthRole(
				user.getId(), UserContext.getCurrentUser().getId());
		// 修改结束
		userDeptAndRoleDao.deleteUserRoleAuthByUserId(user.getId());
		User oldUser = userDao.getById(user.getId());
		oldUser.setStatus(user.getStatus());
		oldUser.setValidDate(user.getValidDate());
		oldUser.setInvalidDate(user.getInvalidDate());
		userDao.update(oldUser);
		if (departmentIds != null) {
			// 插入
			for (String dept : departmentIds) {
				userDeptAndRoleDao.insertUserDeptAuth(user.getId(), dept);
			}
		}
		if (departmentIdsRemove != null) {
			int begin = 0 ;
			int end =0;
			int size = 1000;//每次删除1000条
			while (true) {
				end =(begin+size)>departmentIdsRemove.size()?departmentIdsRemove.size():begin+size;
				if(end == 0){
					break;
				}
				//修改人:zhangbin
				//修改时间：2013-07-18 9:15
				List<String> depts = departmentIdsRemove.subList(begin,end);
				userDeptAndRoleDao.deleteUserDeptAll(user.getId(),depts);
				if(end>=departmentIdsRemove.size()){
					break;
				}
				begin+=size;
			}
		}
		if (roleIds != null) {
			// 修改人:ztjie
			// 修改时间：2012-04-17
			// 修改说明：把非当前用户分配的角色ID加入到待分配的角色信息表中
			// 修改前：
			// 修改后:
			// 把非当前用户分配的角色ID加入到待分配的角色信息表中
			roleIds.addAll(authRoleIds);
			// 修改结束
			for (String role : roleIds) {
				userDeptAndRoleDao.insertUserRoleAuth(user.getId(), role);
			}
		}
	}

	public List<User> queryAll(User user, int limit, int start) {
		return userDao.getAll(user, limit, start);
	}

	public Long count(User user) {
		Long num = userDao.count(user);
		if (num == 0) {
			num = (long) 1;
		}
		return num;
	}

	@SuppressWarnings("serial")
	public User findByLoginName(String loginName) {
		if (loginName == null && "".equals(loginName)) {
			UserException e = new UserException(
					UserExceptionType.LoginNameIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		User user = userDao.getByLoginName(loginName);
		return user;
	}

	@SuppressWarnings("serial")
	public void updatePassword(User user) {
		if (user == null) {
			UserException e = new UserException(UserExceptionType.UserIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		if (user.getId() == null && "".equals(user.getId().trim())) {
			UserException e = new UserException(UserExceptionType.UserIdIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		if (user.getPassword() == null && "".equals(user.getPassword())) {
			UserException e = new UserException(
					UserExceptionType.UserPasswordIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 应用OA的加密方式 modify by ztjie 2011-11-21
		String pwd = CryptoUtil.digestByMD5(user.getPassword());
		user.setPassword(pwd);
		userDao.updateUserPassword(user);
	}

	public List<User> queryAllByDeptId(String deptId, int limit, int start) {
		// TODO Auto-generated method stub
		return userDao.queryAllByDeptId(deptId, limit, start);
	}

	public Long count(String deptId) {
		Long num = userDao.count(deptId);
		if (num == 0) {
			num = (long) 1;
		}
		return num;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IUserDeptAndRoleDao getUserDeptAndRoleDao() {
		return userDeptAndRoleDao;
	}

	public void setUserDeptAndRoleDao(IUserDeptAndRoleDao userDeptAndRoleDao) {
		this.userDeptAndRoleDao = userDeptAndRoleDao;
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public IDepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(IDepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public IAuthorizeDao getAuthorizeDao() {
		return authorizeDao;
	}

	public void setAuthorizeDao(IAuthorizeDao authorizeDao) {
		this.authorizeDao = authorizeDao;
	}

	/**
	 * @description
	 * @author 赵斌
	 * @2012-3-19
	 * @return
	 */
	@Override
	public List<String> getUserDeptIds(String userId) {
		List<String> list = userDeptAndRoleDao.getUserDeptIds(userId);
		return list;
	}

	@SuppressWarnings("serial")
	@Override
	public String queryManagerUserIdByDeptId(String deptId) {
		if (deptId == null && "".equals(deptId)) {
			UserException e = new UserException(UserExceptionType.DeptIdIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		return userDao.getManagerUserIdByDeptId(deptId);
	}

	@SuppressWarnings("serial")
	@Override
	public List<User> queryUsersByDeptIds(List<String> deptIds) {
		if (deptIds == null) {
			UserException e = new UserException(UserExceptionType.DeptIdsIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		return userDao.getUsersByDeptIds(deptIds);
	}

	@Override
	public List<User> queryUsersByDeptAndRole(String deptId, String roleId) {
		return userDao.queryUsersByDeptAndRole(deptId, roleId);
	}

	@Override
	public User getUserById(String id) {
		return userDao.getById(id);
	}

	public User getUserRoleFunDeptById(String userId) {
		return userDao.getUserRoleFunDeptById(userId);
	}
	@Override
	public String queryManagerEmployeeIdByDeptId(String deptId) {
		return userDao.getManagerEmployeeIdByDeptId(deptId);
	}
	/**
	 * 同步UUMS 数据，进行相应的数据同步，权限分配
	 * @author ZhangZW
	 * @update 2014-1-6 
	 * @param empCode changeType postion orgId
	 * @return
	 */
	@Override
	public void syncUser(String empCode, String changeType, String postion,
			String orgId) {
		userDao.syncUser(empCode, changeType, postion, orgId);	
	}

	@Override
	public User selectByCode(String empCode) {
		User user=(User)userDao.selectByCode(empCode);
		return user;
	}
}
