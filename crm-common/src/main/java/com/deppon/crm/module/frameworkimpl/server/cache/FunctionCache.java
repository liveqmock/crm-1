package com.deppon.crm.module.frameworkimpl.server.cache;

import com.deppon.foss.framework.cache.DefaultStrongRedisCache;
import com.deppon.foss.framework.cache.provider.IBatchCacheProvider;
import com.deppon.foss.framework.cache.storage.RedisCacheStorage;
import com.deppon.foss.framework.entity.IFunction;

/**
 * 功能权限缓存
 * @author ztjie
 *
 */
public class FunctionCache extends DefaultStrongRedisCache<String, IFunction>{
	
	 private FunctionCacheProvider cacheProvider;
		// 缓存仓库类
	private RedisCacheStorage<String, IFunction> cacheStorage;
	
	
	public final static String UUID = IFunction.class.getName();
	
	public String getUUID() {
		return UUID;
	}
	
	 /**
     *<p>通过uri获取功能信息，从缓存中获取不到的，访问数据库从数据库中获取，
     *    并将获取到的信息放入缓存中</p>
     * @author dpadmin
     * @param uri
     * @return IFunction
     */
	public IFunction get(String uri){
		IFunction function = super.get(uri);
		/*if(function==null){//缓存中没有获取的对象，改为从数据中获取
			function = cacheProvider.get(uri);
			if(function!=null){//数据中获取的对象不为null，则将对象放入缓存中
				try{
				cacheStorage.hset(UUID, uri, function);
				}catch(Exception e){}//放入缓存时报异常，不做任何处理
			}
		}*/
		
		return function;
	}
	
	
    public void setCacheProvider(IBatchCacheProvider<String, IFunction> cacheProvider) {
    	super.setCacheProvider(cacheProvider);
        this.cacheProvider = (FunctionCacheProvider)cacheProvider;
    }


	public void setCacheStorage(RedisCacheStorage<String, IFunction> cacheStorage) {
		super.setCacheStorage(cacheStorage);
		this.cacheStorage = cacheStorage;
	}
	
    
}
