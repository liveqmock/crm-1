/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IGoodTradeMappingService.java
 * @package com.deppon.crm.module.customer.server.service 
 * @author 106138
 * @version 0.1 2014-3-18
 */
package com.deppon.crm.module.customer.server.service;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMapping;
import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMappingSearchCondition;

/**   
 * <p>
 * Description:行业品名映射service<br />
 * </p>
 * @title IGoodTradeMappingService.java
 * @package com.deppon.crm.module.customer.server.service 
 * @author 106138
 * @version 0.1 2014-3-18
 */

public interface IGoodTradeMappingService {
	/**
	 *  
	 * <p>
	 * Description:查询行业与品名映射<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-18
	 * @param conition
	 * @return
	 * List<GoodTradeMapping>
	 */
	List<GoodTradeMapping> searchGoodTradeMappingByCondition(
			GoodTradeMappingSearchCondition conition);
	
	/**
	 * 
	 * <p>
	 * Description:统计条数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-18
	 * @param conition
	 * @return
	 * int
	 */
	int  countGoodTradeMappingByCondition(
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

}
