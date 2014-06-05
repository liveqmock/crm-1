package com.deppon.crm.module.frameworkimpl.server.cache;

import com.deppon.foss.framework.cache.DefaultStrongRedisCache;
import com.deppon.foss.framework.cache.storage.RedisCacheStorage;
import com.deppon.foss.framework.entity.IRole;

/**
 * 角色缓存对象
 * @author ztjie
 *
 */
public class RoleCache extends DefaultStrongRedisCache<String, IRole> {

	public final static String UUID = IRole.class.getName();
	// 数据提供者
	private RoleCacheProvider cacheProvider;
	// 缓存仓库类
	private RedisCacheStorage<String, IRole> cacheStorage;


	public String getUUID() {
		return UUID;
	}
    /**
     *<p>通过角色ID获取角色信息，从缓存中获取不到的，访问数据且从数据中获取，
     *    并将获取到的信息放入缓存中</p>
     * @author dpadmin
     * @param roleId
     * @return IRole
     */
	public IRole get(String id){
		IRole role = super.get(id);
		if(role==null){//缓存中没有此对象，从数据中获取
			role = cacheProvider.get(id);
			if(role!=null){//从数据中获取的对象不为，则将此对象放入缓存
				try{
				  cacheStorage.hset(UUID, role.getId(), role);
		    	}catch(Exception e){}//放入缓存时报异常，不做任何处理

			}
		}
		
		return role;
	}
	
	
	public void setCacheProvider(RoleCacheProvider cacheProvider) {
		super.setCacheProvider(cacheProvider);
		this.cacheProvider = cacheProvider;
	}
	
	public void setCacheStorage(RedisCacheStorage<String, IRole> cacheStorage) {
		super.setCacheStorage(cacheStorage);
		this.cacheStorage = cacheStorage;
	}

	
	
	
}
