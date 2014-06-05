package com.deppon.crm.module.frameworkimpl.server.cache;

import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.foss.framework.cache.DefaultStrongRedisCache;
import com.deppon.foss.framework.cache.storage.RedisCacheStorage;

/**
 * 数据字典缓存
 * @author ztjie
 *
 */
public class DataDictionaryCache extends DefaultStrongRedisCache<String, Head>{
	
	 private DataDictionaryCacheProvider cacheProvider;
		// 缓存仓库类
	private RedisCacheStorage<String, Head> cacheStorage;
	 
	public final static String UUID = Head.class.getName();
	
	public String getUUID() {
		return UUID;
	}
	

	public Head get(String codeType){
		Head head = super.get(codeType);
		if(head==null){
			head = cacheProvider.get(codeType);
			if(head!=null){
			try{
				cacheStorage.hset(UUID, codeType, head);
			}catch(Exception e){}//放入缓存时报异常，不做任何处理

			}
		}
		return head;
	}
	
	
    public void setCacheProvider(DataDictionaryCacheProvider cacheProvider) {
    	super.setCacheProvider(cacheProvider);
        this.cacheProvider = cacheProvider;
    }


	public void setCacheStorage(RedisCacheStorage<String, Head> cacheStorage) {
		super.setCacheStorage(cacheStorage);
		this.cacheStorage = cacheStorage;
	}
	
	
}
