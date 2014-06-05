/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title GoodTradeMappingService.java
 * @package com.deppon.crm.module.customer.server.service.impl 
 * @author 106138
 * @version 0.1 2014-3-18
 */
package com.deppon.crm.module.customer.server.service.impl;

import java.util.List;

import com.deppon.crm.module.customer.server.dao.IGoodTradeMappingDao;
import com.deppon.crm.module.customer.server.dao.impl.GoodTradeMappingDao;
import com.deppon.crm.module.customer.server.service.IGoodTradeMappingService;
import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMapping;
import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMappingSearchCondition;

/**
 * <p>
 * Description:行业品名映射service<br />
 * </p>
 * 
 * @title GoodTradeMappingService.java
 * @package com.deppon.crm.module.customer.server.service.impl
 * @author 106138
 * @version 0.1 2014-3-18
 */

public class GoodTradeMappingService implements IGoodTradeMappingService {
	private GoodTradeMappingDao goodTradeMappingDao;

	/** 
	 * 
	 * <p>
	 * Description:查询行业与品名映射<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-18
	 * @param condition
	 * @return List<GoodTradeMapping>
	 */
	public List<GoodTradeMapping> searchGoodTradeMappingByCondition(
			GoodTradeMappingSearchCondition condition) {
		return getGoodTradeMappingDao().searchGoodTradeMappingByCondition(condition);
	}

	/**
	 * 
	 * <p>
	 * Description:统计条数<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-18
	 * @param condition
	 * @return int
	 */
	public int countGoodTradeMappingByCondition(
			GoodTradeMappingSearchCondition condition) {
		return getGoodTradeMappingDao().countGoodTradeMappingByCondition(condition);
	}

	/**
	 * 
	 * <p>
	 * Description:更新行业与品名映射关系<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-18
	 * @param goodTradeMapping
	 *            void
	 */
	public void updateGoodTradeMapping(GoodTradeMapping goodTradeMapping) {
		getGoodTradeMappingDao().updateGoodTradeMapping(goodTradeMapping);
	}

	public GoodTradeMappingDao getGoodTradeMappingDao() {
		return goodTradeMappingDao;
	}

	public void setGoodTradeMappingDao(GoodTradeMappingDao goodTradeMappingDao) {
		this.goodTradeMappingDao = goodTradeMappingDao;
	}

	
}
