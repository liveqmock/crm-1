package com.deppon.crm.module.customer.server.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.customer.server.dao.IGoodTradeMappingDao;
import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMapping;
import com.deppon.crm.module.customer.shared.domain.base.GoodTradeMappingSearchCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 * <p>
 * Description:行业与品名映射关系dao<br />
 * </p>
 *  
 * @title GoodTradeMappingDao.java
 * @package com.deppon.crm.module.customer.server.dao.impl
 * @author 106138
 * @version 0.1 2014-3-18
 */
public class GoodTradeMappingDao extends iBatis3DaoImpl implements
		IGoodTradeMappingDao {
	//命名空间
	private static final String NAMESPACE = "com.deppon.crm.module.customer.shared.domain.base.GoodTradeMapping.";
	//查询
	private static final String SEARCHGOODTRADEMAPPINGBYCONDITION = "searchGoodTradeMappingByCondition";
	//count
	private static final String COUNTGOODTRADEMAPPINGBYCONDITION = "countGoodTradeMappingByCondition";
	private static final String UPDATEGOODTRADEMAPPING = "updateGoodTradeMapping";

	/**
	 * 
	 * <p>
	 * Description:查询行业与品名映射<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-18
	 * @param conition
	 * @return List<GoodTradeMapping>
	 */
	public List<GoodTradeMapping> searchGoodTradeMappingByCondition(
			GoodTradeMappingSearchCondition condition) {
		RowBounds bounds = new RowBounds(condition.getStart(),
				condition.getLimit());
		@SuppressWarnings("unchecked")
		List<GoodTradeMapping> list = (List<GoodTradeMapping>) getSqlSession()
				.selectList(NAMESPACE + SEARCHGOODTRADEMAPPINGBYCONDITION,
						condition, bounds);
		return list;
	}

	/**
	 * 
	 * <p>
	 * Description:统计条数<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-18
	 * @param conition
	 * @return int
	 */
	public int countGoodTradeMappingByCondition(
			GoodTradeMappingSearchCondition condition) {
		return (Integer) this.getSqlSession().selectOne(
				NAMESPACE + COUNTGOODTRADEMAPPINGBYCONDITION, condition);
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
		if (null != goodTradeMapping) {
			this.getSqlSession().update(NAMESPACE + UPDATEGOODTRADEMAPPING,
					goodTradeMapping);
		}
	}
}
