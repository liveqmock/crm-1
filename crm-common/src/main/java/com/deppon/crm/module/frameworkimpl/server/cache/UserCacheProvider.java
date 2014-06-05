package com.deppon.crm.module.frameworkimpl.server.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.deppon.crm.module.authorization.server.dao.IFunctionDao;
import com.deppon.crm.module.authorization.server.dao.IUserDao;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.foss.framework.cache.provider.ILazyCacheProvider;
import com.deppon.foss.framework.entity.IUser;

/**
 * 用户对象数据缓存提供者
 * @author Administrator
 *
 */
public class UserCacheProvider implements ILazyCacheProvider<String, IUser> {

	private IUserDao userDao;
	private IFunctionDao functionDao;

	public Date getLastModifyTime() {
		return userDao.getLastModifyTime();
	}

	public IUser get(String key) {
//		System.out.println("Find User:" + key);
		User user = userDao.getUserRoleFunDeptById(key);
		List<String> list = functionDao.getAllIdByUserId(key);
		Set<String> set = new HashSet<String>();
		for(int i = 0; i < list.size(); i++) {
			set.add((String)list.get(i));
		}
		if (user == null){
			return null;
		}
		user.setAccessUris(set);
		
		//User对象中不保存Role对象   modified by zdf
		/*
		Set<String> roleIds = user.getRoleids();
		@SuppressWarnings("unchecked")
		ICache<String, Role> roleCache = (ICache<String, Role>) CacheManager
				.getInstance().getCache(IRole.class.getName());
		
			List<IRole> roles = new ArrayList<IRole>();
		if (roleIds != null) {
			for (String roleId : roleIds) {
				Role role = roleCache.get(roleId);
				roles.add(role);
			}
		}
		user.setRoles(roles);
		*/
		return user;
	}

	public Map<String, IUser> getUpdateObjectMaps(Date time) {
		Map<String, IUser> map = new HashMap<String, IUser>();
		List<User> users = userDao.getByLastModifyDate(time);
		for (User user : users) {
			List<String> list = functionDao.getAllIdByUserId(user.getId());
			Set<String> set = new HashSet<String>();
			for(int i = 0; i < list.size(); i++) {
				set.add((String)list.get(i));
			}
			user.setAccessUris(set);
			map.put(user.getId(), user);
		}
		return map;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IFunctionDao getFunctionDao() {
		return functionDao;
	}

	public void setFunctionDao(IFunctionDao functionDao) {
		this.functionDao = functionDao;
	}

	@Override
	public Map<String, IUser> getUpdateObjectMaps(String... keys) {
		return null;
	}
	
}
