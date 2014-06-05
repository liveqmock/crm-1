package com.deppon.crm.module.servicerecovery.server.dao.impl;

import java.util.List;

import com.deppon.crm.module.servicerecovery.server.dao.IFinanceDeptDao;
import com.deppon.crm.module.servicerecovery.shared.domain.FinanceDept;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 * @description：查询财务部Dao接口
 * @author 华龙
 * @version 1.0
 * @date 2012-11-13 下午15:43:34
 */
public class FinanceDeptDao extends iBatis3DaoImpl implements IFinanceDeptDao {
	private static final String SEARCHFINANCELIST = "searchFinanceDeptList";

	/**
	 * 查询财务部
	 */
	@Override
	public List<FinanceDept> searchFinanceDeptList() {
		return getSqlSession().selectList(SEARCHFINANCELIST);
	}

}
