/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title GoodTradeMappingManager.java
 * @package com.deppon.crm.module.customer.server.manager 
 * @author 106138
 * @version 0.1 2014-3-18
 */
package com.deppon.crm.module.customer.server.manager;

import java.util.Map;

import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMapping;
import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMappingSearchCondition;

/**
 * <p>
 * Description:行业品名映射manager<br />
 * </p>
 * 
 * @title GoodTradeMappingManager.java
 * @package com.deppon.crm.module.customer.server.manager
 * @author 106138
 * @version 0.1 2014-3-18
 */ 

public interface IGoodTradeMappingManager {
	/**
	 * 
	 * <p>
	 * Description:查询行业与品名映射<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-18
	 * @param conition
	 * @return
	 * Map<String,Object>
	 */
	Map<String, Object> searchGoodTradeMappingByCondition(
			GoodTradeMappingSearchCondition conition);
	/**
	 * 
	 * <p>
	 * Description:更新行业与品名映射关系<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-18
	 * @param goodTradeMapping
	 * void
	 */
	void updateGoodTradeMapping(GoodTradeMapping goodTradeMapping);
	/**
	 * 
	 * <p>
	 * Description:系统更新客户行业信息 每月一次<<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月10日
	 * void
	 */
	void updateCustomerTradeMonth();
	/**
	 * 
	 * <p>
	 * Description:系统更新没有行业信息的客户的行业信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月10日
	 * void
	 */
	void updateCustomerTradeWeek();
}
