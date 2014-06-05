/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CityCache.java
 * @package com.deppon.crm.module.common.server.cache 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */
package com.deppon.crm.module.common.server.cache;

import com.deppon.foss.framework.cache.DefaultStrongRedisCache;

/**   
 * <p>
 * Description:配置省份城市缓存<br />
 * </p>
 * @title CityCache.java
 * @package com.deppon.crm.module.common.server.cache 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */

public class CityCache extends DefaultStrongRedisCache<String, CityCacheProvider> {
	//给其一个唯一的UUID标志
	private final static String UUID = CityCacheProvider.class.getName();
	@Override
	public String getUUID() {
		return UUID;
	}

}
