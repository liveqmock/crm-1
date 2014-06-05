package com.deppon.crm.module.authorization.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.authorization.server.dao.IUserDao;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

@SuppressWarnings("unchecked")
public class UserDao extends iBatis3DaoImpl implements IUserDao {

	public List<User> getAll(User user) {
		List<User> list = null;
		User model = new User();
		if (user != null) {
			if (user.getId() != null && !"".equals(user.getId())) {
				String id = "%" + user.getId() + "%";
				model.setId(id);
			}
			model.setEmpCode(user.getEmpCode());
			if (user.getLoginName() != null && !"".equals(user.getLoginName())) {
				String loginName = "%" + user.getLoginName() + "%";
				model.setLoginName(loginName);
			}
			if (user.getPassword() != null && !"".equals(user.getPassword())) {
				String password = "%" + user.getPassword() + "%";
				model.setPassword(password);
			}
			model.setLastLogin(user.getLastLogin());
			model.setStatus(user.getStatus());
			model.setInvalidDate(user.getInvalidDate());
			model.setValidDate(user.getValidDate());
		}
		list = getSqlSession()
				.selectList(
						"com.deppon.crm.module.authorization.shared.domain.User.getAll",
						model);
		return list;
	}

	public void update(User user) {
		getSqlSession()
				.update("com.deppon.crm.module.authorization.shared.domain.User.update",
						user);
	}

	public void insert(User user) {
		getSqlSession()
				.insert("com.deppon.crm.module.authorization.shared.domain.User.insert",
						user);
	}

	public void deleteById(String id) {
		getSqlSession()
				.delete("com.deppon.crm.module.authorization.shared.domain.User.deleteById",
						id);
	}

	public List<User> getAll(User user, int limit, int start) {
		RowBounds rowBounds = new RowBounds(start, limit);
		List<User> list = null;
		User model = new User();
		if (user != null) {
			if (user.getId() != null && !"".equals(user.getId())) {
				String id = "%" + user.getId() + "%";
				model.setId(id);
			}
			if (user.getEmpCode()!= null) {
				if(user.getEmpCode().getEmpName()!=null&&!"".equals(user.getEmpCode().getEmpName())){
					String empName = "%" + user.getEmpCode().getEmpName() + "%";
					user.getEmpCode().setEmpName(empName);
				}
			}
			model.setEmpCode(user.getEmpCode());
			if (user.getLoginName() != null && !"".equals(user.getLoginName())) {
				String loginName = "%" + user.getLoginName() + "%";
				model.setLoginName(loginName);
			}
			if (user.getPassword() != null && !"".equals(user.getPassword())) {
				String password = "%" + user.getPassword() + "%";
				model.setPassword(password);
			}
			model.setLastLogin(user.getLastLogin());
			model.setStatus(user.getStatus());
			model.setInvalidDate(user.getInvalidDate());
			model.setValidDate(user.getValidDate());
		}
		list = getSqlSession()
				.selectList(
						"com.deppon.crm.module.authorization.shared.domain.User.getAll",
						model, rowBounds);
		return list;
	}

	public Long count(User user) {
		User model = new User();
		if (user != null) {
			if (user.getId() != null && !"".equals(user.getId())) {
				String id = "%" + user.getId() + "%";
				model.setId(id);
			}
			if (user.getEmpCode()!= null) {
				if(user.getEmpCode().getEmpName()!=null&&!"".equals(user.getEmpCode().getEmpName())){
					String empName = "%" + user.getEmpCode().getEmpName() + "%";
					user.getEmpCode().setEmpName(empName);
				}
			}
			model.setEmpCode(user.getEmpCode());
			if (user.getLoginName() != null && !"".equals(user.getLoginName())) {
				String loginName = "%" + user.getLoginName() + "%";
				model.setLoginName(loginName);
			}
			if (user.getPassword() != null && !"".equals(user.getPassword())) {
				String password = "%" + user.getPassword() + "%";
				model.setPassword(password);
			}
			model.setLastLogin(user.getLastLogin());
			model.setStatus(user.getStatus());
			model.setInvalidDate(user.getInvalidDate());
			model.setValidDate(user.getValidDate());
		}
		Long count = (Long) getSqlSession()
				.selectOne(
						"com.deppon.crm.module.authorization.shared.domain.User.count",
						model);
		return count;
	}

	public Date getLastModifyTime() {
		Date lastModifyTime = (Date) getSqlSession()
				.selectOne(
						"com.deppon.crm.module.authorization.shared.domain.User.getLastModifyTime");
		return lastModifyTime;
	}

	public List<User> getByLastModifyDate(Date lastModifyDate) {
		if (lastModifyDate == null)
			return null;
		List<User> userList = getSqlSession().selectList(
				"com.deppon.crm.module.authorization.shared.domain.User.getByLastModifyDate",
				lastModifyDate);
		return userList;
	}

	public User getByLoginName(String loginName) {
		User user = (User) getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.User.getByLoginName",loginName);
		return user;
	}

	public void updateUserPassword(User user) {
		getSqlSession().update("com.deppon.crm.module.authorization.shared.domain.User.updatePassword", user);
	}

	public void updateLastLoginDate(User user) {
		getSqlSession().update("com.deppon.crm.module.authorization.shared.domain.User.updateLastLoginDate",user.getId());
	}

	public User getById(String id) {
		// TODO Auto-generated method stub
		return (User)getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.User.getById",id);
	}

	public List<User> queryAllByDeptId(String deptId, int limit, int start) {
		// TODO Auto-generated method stub
		RowBounds rowBounds = new RowBounds(start, limit);
		return getSqlSession()
		.selectList(
				"com.deppon.crm.module.authorization.shared.domain.User.getAllBydeptId",
				deptId, rowBounds);
	}

	public Long count(String deptId) {
		Long count = (Long) getSqlSession()
		.selectOne(
				"com.deppon.crm.module.authorization.shared.domain.User.countBydeptId",
				deptId);
		return count;
	}

	@Override
	public User getUserRoleFunDeptById(String userId) {
		User user = (User) getSqlSession()
		.selectOne(
				"com.deppon.crm.module.authorization.shared.domain.User.getUserRoleFunDeptById",userId);
		return user;
	}

	@Override
	public String getManagerUserIdByDeptId(String deptId) {
		String managerUserId = (String) getSqlSession()
		.selectOne(
				"com.deppon.crm.module.authorization.shared.domain.User.getManagerUserIdByDeptId",deptId);
		return managerUserId;
	}

	@Override
	public List<User> getUsersByDeptIds(List<String> deptIds) {
		List<User> userList = getSqlSession().selectList(
				"com.deppon.crm.module.authorization.shared.domain.User.getUsersByDeptIds",
				deptIds);
		return userList;
	}

	@Override
	public List<User> queryUsersByDeptAndRole(String deptId, String roleId) {
		Map map = new HashMap();
		map.put("deptId", deptId);
		map.put("roleId", roleId);
		List<User> userList = getSqlSession()
				.selectList(
						"com.deppon.crm.module.authorization.shared.domain.User.queryUsersByDeptAndRole",
						map);
		return userList;
	}

	@Override
	public String getManagerEmployeeIdByDeptId(String deptId) {
		String managerUserId = (String) getSqlSession()
		.selectOne(
				"com.deppon.crm.module.authorization.shared.domain.User.getManagerEmployeeIdByDeptId",deptId);
		return managerUserId;
	}
	@Override
	public User selectByCode(String empCode) {
		return (User)getSqlSession().selectOne("com.deppon.crm.module.authorization.shared.domain.User.selectByCode",empCode);
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
		Map<String,String> map=new HashMap<String,String>();
		map.put("empCode", empCode);
		map.put("changeType", changeType);
		map.put("position", postion);
		map.put("orgId", orgId);
		getSqlSession().update("com.deppon.crm.module.authorization.shared.domain.User.syncUser", map);
	}
}
