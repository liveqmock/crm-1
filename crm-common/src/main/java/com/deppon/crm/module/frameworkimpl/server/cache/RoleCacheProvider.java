package com.deppon.crm.module.frameworkimpl.server.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.server.dao.IRoleDao;
import com.deppon.crm.module.authorization.server.dao.IUserDao;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.foss.framework.cache.provider.IBatchCacheProvider;
import com.deppon.foss.framework.entity.IRole;

/**
 * 角色缓存数据提供者
 * @author ztjie
 *
 */
public class RoleCacheProvider implements IBatchCacheProvider<String, IRole>{

	private IRoleDao roleDao;
	 private IUserDao userDao;
	
	 /***
	  * 由于用户分配角色后，角色表数据没有发生变动，只是在用户角色表增加一条记录，
	  * 同时用户表最后更新时间发生变动，所以此方法返回的是 用户表和角色表的最后更新时间
	  * @author dpadmin
	  */
	public Date getLastModifyTime() {
		
		Date roleUpdateTime = roleDao.getLastModifyTime();
		Date userUpdateATime = userDao.getLastModifyTime();	
		return userUpdateATime.before(roleUpdateTime)?roleUpdateTime:userUpdateATime;
	}

	public Map<String, IRole> get() {
		List<Role> roles = roleDao.getAllRole();
		Map<String, IRole> map = new HashMap<String, IRole>();
		for (Role role : roles) {
			map.put(role.getId(), role);
		}
		return map;
	}
	
	
	public IRole get(String id){
		return roleDao.getRoleById(id);
	}
	

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

}
