package com.deppon.crm.module.frameworkimpl.server.cache;

import java.util.Map;

import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.crm.module.common.shared.domain.ServerParameter;
import com.deppon.foss.framework.cache.DefaultStrongRedisCache;
import com.deppon.foss.framework.cache.storage.RedisCacheStorage;
import com.deppon.foss.framework.server.sso.util.StringUtil;
/**
 * 
 * <p>
 * Description:系统配置缓存<br />
 * </p>
 * @title ServerParameterCache.java
 * @package com.deppon.crm.module.frameworkimpl.server.cache 
 * @author 106138
 * @version 0.1 2014-3-6
 */
public class ServerParameterCache extends DefaultStrongRedisCache<String, String> {
	private ServerParameterCacheProvider cacheProvider;
		// 缓存仓库类
	private RedisCacheStorage<String, String> cacheStorage;
	public final static String UUID = ServerParameterCache.class.getName();

	@Override
	public String getUUID() {
		return UUID;
	}
	
	public String get(String key){
		Map<String, String> serverParameter = super.get();
		String value=serverParameter.get(key);
		if(StringUtil.isNull(value)){
		value=	cacheProvider.get(key);
		}
		return value;
			
	}

	public ServerParameterCacheProvider getCacheProvider() {
		super.setCacheProvider(cacheProvider);
		return cacheProvider;
	}

	public void setCacheProvider(ServerParameterCacheProvider cacheProvider) {
		super.setCacheProvider(cacheProvider);
		this.cacheProvider = cacheProvider;
	}

	
	
	

}
