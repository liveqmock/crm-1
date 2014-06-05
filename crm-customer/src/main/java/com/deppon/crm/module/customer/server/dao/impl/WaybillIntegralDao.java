package com.deppon.crm.module.customer.server.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.customer.server.dao.IWaybillIntegralDao;
import com.deppon.crm.module.customer.server.utils.SqlUtil;
import com.deppon.crm.module.customer.shared.domain.integral.WaybillIntegral;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title WaybillIntegralDao.java
 * @package com.deppon.crm.module.customer.server.dao.impl 
 * @author 106138
 * @version 0.1 2014年4月10日
 */
public class WaybillIntegralDao extends iBatis3DaoImpl implements IWaybillIntegralDao{
	
	private static final String NAME_SPACE = "com.deppon.crm.module.customer.shared.domain.integral.WaybillIntegral.";
	
	@Override
	public long countSearchWaybillIntegrals(WaybillIntegral waybillIntegral) {
		return (Long) this.getSqlSession().selectOne(NAME_SPACE+"countSearchWaybillIntegrals",waybillIntegral);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WaybillIntegral> searchWaybillIntegrals(
			WaybillIntegral waybillIntegral,int start,int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAME_SPACE+"searchWaybillIntegrals",waybillIntegral,rowBounds);
	}

	@Override
	public WaybillIntegral getWaybillIntegralById(String id) {
		return (WaybillIntegral) this.getSqlSession().selectOne(NAME_SPACE+"getWaybillIntegralById",id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<WaybillIntegral> searchWaybillIntegralForContactId(
			List<String> contactIdList, int start, int limit) {
		RowBounds row = new RowBounds(start,limit);
		String contactIds = SqlUtil.getInSql(contactIdList);
		Map map = new HashMap();
		map.put("contactIdList", contactIds);
		return this.getSqlSession().selectList(NAME_SPACE+"searchWaybillIntegralForContactId",map,row);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public long countSearchWaybillIntegralForContactId(
			List<String> contactIdList) {
		String contactIds = SqlUtil.getInSql(contactIdList);
		Map map = new HashMap();
		map.put("contactIdList", contactIds);
		return  (Long) this.getSqlSession().selectOne(NAME_SPACE+"countSearchWaybillIntegralForContactId",map);
	}
	
}
