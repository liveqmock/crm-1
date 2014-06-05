package com.deppon.crm.module.frameworkimpl.server.cache;

import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.cache.DefaultStrongRedisCache;
import com.deppon.foss.framework.cache.storage.RedisCacheStorage;

/**
 * 数据字典缓存
 * @author ztjie
 *
 */
public class DepartmentCache extends DefaultStrongRedisCache<String, Department>{
	
	 private DepartmentCacheProvider departmentCacheProvider;
		// 缓存仓库类
	private RedisCacheStorage<String, Department> cacheStorage;
	 
	


	public final static String UUID = Department.class.getName();
	
	public String getUUID() {
		return UUID;
	}
	

	public Department get(String deptId){
		Department department = super.get(deptId);
		if(department==null){
			department = departmentCacheProvider.get(deptId);
			if(department!=null){
			try{
				cacheStorage.hset(UUID, deptId, department);
			}catch(Exception e){}//放入缓存时报异常，不做任何处理

			}
		}
		return department;
	}

	public void setCacheProvider(DepartmentCacheProvider cacheProvider) {
		super.setCacheProvider(cacheProvider);
		this.departmentCacheProvider = cacheProvider;
	}
	@Override
	public void setCacheStorage(RedisCacheStorage<String, Department> cacheStorage) {
		super.setCacheStorage(cacheStorage);
		this.cacheStorage = cacheStorage;
	}
	
	
	
}
