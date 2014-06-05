package com.deppon.crm.module.frameworkimpl.server.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.server.dao.IServerParameterDao;
import com.deppon.crm.module.common.shared.domain.ServerParameter;
import com.deppon.foss.framework.cache.provider.IBatchCacheProvider;
/**
 * 
 * <p>
 * Description: * 系统配置缓存数据提供对象<br />
 * </p>
 * @title ServerParameterCacheProvider.java
 * @package com.deppon.crm.module.frameworkimpl.server.cache 
 * @author 106138
 * @version 0.1 2014-3-6
 */
public class ServerParameterCacheProvider  implements IBatchCacheProvider<String, String>{
	private  IServerParameterDao serverParameterDao;

	@Override
	public Date getLastModifyTime() {
		Date headLastModifyTime = getServerParameterDao().getLastModifyTime();
		return headLastModifyTime;
	}

	@Override
	public Map<String, String> get() {
		Map<String, String> map=new HashMap<String, String>();
		List<ServerParameter> ss = getServerParameterDao().getAllServerParameter();
		for (ServerParameter s : ss) {
			map.put(s.paramKey,s.pramValue);
		}
		return map;
	}

	public IServerParameterDao getServerParameterDao() {
		return serverParameterDao;
	}

	public void setServerParameterDao(IServerParameterDao serverParameterDao) {
		this.serverParameterDao = serverParameterDao;
	}

	public String get(String key) {
		return serverParameterDao.getValueByKey(key);
	}

	

}
