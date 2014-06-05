package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMapping;
import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMappingSearchCondition;

/**
 * 
 * <p>
 * Description:行业与品名映射关系<br />
 * </p>
 * @title GoodTradeMappingDao.java
 * @package com.deppon.crm.module.customer.server.dao 
 * @author 106138
 * @version 0.1 2014-3-18
 */ 
public interface IGoodTradeMappingDao {
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
