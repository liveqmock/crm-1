package com.deppon.crm.module.frameworkimpl.server.cache;

import com.deppon.crm.module.common.shared.domain.HardWareInfo;
import com.deppon.foss.framework.cache.DefaultStrongRedisCache;
import com.deppon.foss.framework.cache.storage.RedisCacheStorage;

/**
 * 
 * <p>
 * Description:主机硬件缓存类<br />
 * </p>
 * @title HardWareCache.java
 * @package com.deppon.crm.module.common.server.cache 
 * @author 石应华
 * @version 0.1 2013-6-26
 */
public class HardWareCache extends DefaultStrongRedisCache<String, HardWareInfo> {

	private HardWareCacheProvider cacheProvider;
	// 缓存仓库类
    private RedisCacheStorage<String, HardWareInfo> cacheStorage;
	
    public final static String UUID = HardWareCache.class.getName();

    public String getUUID() {
        return UUID;
    }

    
    public HardWareInfo get(String hostName){
    	HardWareInfo hardWareInfo = super.get(hostName);
    	if(hardWareInfo==null){
    		hardWareInfo = cacheProvider.get(hostName);
    		if(hardWareInfo!=null){//从数据中获取的 不为null，放入缓存中
        	try{	
    			cacheStorage.hset(UUID, hardWareInfo.getHostName(), hardWareInfo);
			}catch(Exception e){}//放入缓存时报异常，不做任何处理

    		}
    	}
    	
    
    	return hardWareInfo;
    }
    
    
	public void setCacheProvider(HardWareCacheProvider cacheProvider) {
		super.setCacheProvider(cacheProvider);
		this.cacheProvider = cacheProvider;
	}


	public void setCacheStorage(RedisCacheStorage<String, HardWareInfo> cacheStorage) {
		super.setCacheStorage(cacheStorage);
		this.cacheStorage = cacheStorage;
	}
    
    

    
    
    
    
}
