 /**     
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IFiveKilometreManager.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author zhujunyong
 * @version 0.1 Mar 28, 2012
 */
package com.deppon.crm.module.marketing.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.shared.domain.CustomerLocation;
import com.deppon.crm.module.marketing.shared.domain.MapQueryCondition;

/**   
 * <p>
 * Description:五公里地图<br />
 * </p>
 * @title IFiveKilometreManager.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author zhujunyong
 * @version 0.1 Mar 28, 2012
 */

public interface IMapManager {

	/**
	 * 
	 * <p>
	 * Description:五公里地图-查询客户列表<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Mar 28, 2012
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * Map<String, Object>
	 */
	 Map<String, Object> getCustomerList(MapQueryCondition condition, int start, int limit,  User user);

	/**
	 * 
	 * <p>
	 * Description:五公里地图-保存标注<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 1, 2012
	 * @param customerLocationList
	 * @return
	 * List<Boolean>
	 */
	 List<Boolean> markCustomerLocation(List<CustomerLocation> customerLocationList,User user);
	
}



