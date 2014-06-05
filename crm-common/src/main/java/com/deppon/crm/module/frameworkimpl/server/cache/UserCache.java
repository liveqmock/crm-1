package com.deppon.crm.module.frameworkimpl.server.cache;

import com.deppon.foss.framework.cache.DefaultLRURedisCache;
import com.deppon.foss.framework.entity.IUser;

/**
 * 用户对象缓存
 * @author ztjie
 *
 */
public class UserCache extends DefaultLRURedisCache<String, IUser>{
	
	public final static String UUID = IUser.class.getName();

	public String getUUID() {
		return UUID;
	}
}
