/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IMapDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author zhujunyong
 * @version 0.1 Mar 28, 2012
 */
package com.deppon.crm.module.marketing.server.dao;

import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.MapQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.CustomerLocation;
import com.deppon.crm.module.marketing.shared.domain.MapView;

/**   
 * <p>
 * Description:五公里地图<br />
 * </p>
 * @title IMapDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author zhujunyong
 * @version 0.1 Mar 28, 2012
 */

public interface IMapDao {
	
	/**
	 * 
	 * <p>
	 * Description:五公里地图-查询用户<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Mar 28, 2012
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<MapView>
	 */
	 List<MapView> searchUsers(MapQueryCondition condition, int start, int limit);

	/**
	 * 
	 * <p>
	 * Description:五公里地图-查询用户总数<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 11, 2012
	 * @param condition
	 * @return
	 * int
	 */
	 int searchUsersCount(MapQueryCondition condition);
	
	/**
	 * 
	 * <p>
	 * Description:五公里地图-判断地图标记是否存在<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Mar 29, 2012
	 * @param customerLocation
	 * @return
	 * boolean
	 */
	 boolean isCustomerLocationExist(CustomerLocation customerLocation);
	
	/**
	 * 
	 * <p>
	 * Description:五公里地图-新增地图标记<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Mar 29, 2012
	 * @param customerLocation
	 * boolean
	 */
	 boolean insertCustomerLocation(CustomerLocation customerLocation);
	
	/**
	 * 
	 * <p>
	 * Description:五公里地图-更新地图标记<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Mar 29, 2012
	 * @param customerLocation
	 * boolean
	 */
	 boolean updateCustomerLocation(CustomerLocation customerLocation);
	
	
}
