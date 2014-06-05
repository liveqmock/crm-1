/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MapService.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author zhujunyong
 * @version 0.1 Mar 28, 2012
 */
package com.deppon.crm.module.marketing.server.service.impl;

import java.util.List;

import com.deppon.crm.module.marketing.server.dao.IMapDao;
import com.deppon.crm.module.marketing.server.service.IMapService;
import com.deppon.crm.module.marketing.shared.domain.MapQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.CustomerLocation;
import com.deppon.crm.module.marketing.shared.domain.MapView;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MapService.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author zhujunyong
 * @version 0.1 Mar 28, 2012
 */

public class MapService implements IMapService {
	//封装地图dao操作
	private IMapDao mapDao;
	
	/**
	 * @param mapDao : set the property mapDao.
	 */
	public void setMapDao(IMapDao mapDao) {
		this.mapDao = mapDao;
	}

	/** (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IMapService#searchUsers(com.deppon.crm.module.marketing.shared.domain.MapQueryCondition, int, int)
	 */
	@Override
	public List<MapView> searchUsers(MapQueryCondition condition, int start,
			int limit) {
		return mapDao.searchUsers(condition, start, limit);
	}

	/** (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IMapService#isCustomerLocationExist(com.deppon.crm.module.marketing.shared.domain.CustomerLocation)
	 */
	@Override
	public boolean isCustomerLocationExist(CustomerLocation customerLocation) {
		return mapDao.isCustomerLocationExist(customerLocation);
	}

	/** (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IMapService#insertCustomerLocation(com.deppon.crm.module.marketing.shared.domain.CustomerLocation)
	 */
	@Override
	public boolean insertCustomerLocation(CustomerLocation customerLocation) {
		return mapDao.insertCustomerLocation(customerLocation);
	}

	/** (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IMapService#updateCustomerLocation(com.deppon.crm.module.marketing.shared.domain.CustomerLocation)
	 */
	@Override
	public boolean updateCustomerLocation(CustomerLocation customerLocation) {
		return mapDao.updateCustomerLocation(customerLocation);
	}

	/** (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IMapService#searchUsersCount(com.deppon.crm.module.marketing.shared.domain.MapQueryCondition)
	 */
	@Override
	public int searchUsersCount(MapQueryCondition condition) {
		return mapDao.searchUsersCount(condition);
	}

}
