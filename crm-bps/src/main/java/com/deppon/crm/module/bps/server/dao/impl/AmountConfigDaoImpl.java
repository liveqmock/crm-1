package com.deppon.crm.module.bps.server.dao.impl;



import java.util.HashMap;
import java.util.Map;

import com.deppon.crm.module.bps.server.dao.IAmountConfigDao;
import com.deppon.crm.module.bps.server.util.AmountConfigEntity;
import com.deppon.crm.module.bps.shared.domain.NoSuffixEntity;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 *<pre>
 *功能:审批权限金额配置DAO实现
 *作者：andy
 *日期：2013-8-13 下午6:02:03
 *</pre>
 */
public class AmountConfigDaoImpl extends iBatis3DaoImpl implements IAmountConfigDao {
	//命名空间
	private static final String NAMESPACE = "com.deppon.crm.module.bps.server.dao.impl.AmountConfigDaoImpl.";
	
	private static final String QUERY_FOR_BRANCH = "queryForBranch";
	//查询业务编码6位数字
	public static final String GET_NEXT_SUFFIX = "getNextSuffix";
	public static final String INSERT_NEXT_SUFFIX = "insertNextSuffix";
	public static final String UPDATE_NEXT_SUFFIX = "updateNextSuffix";
	public static final String PROC_NEXT_SUFFIX = "procNextSuffix";
		
	/**
	 * 
	 *<pre>
	 * 方法体说明：根据条件查询金额配置
	 * 作者： andy
	 * 日期： 2013-8-12 下午5:55:08
	 * @param amountConfigEntity
	 * @return
	 *</pre>
	 */
	@Override
	public AmountConfigEntity queryForBranch(
			AmountConfigEntity amountConfigEntity) {
		return (AmountConfigEntity)this.getSqlSession().selectOne(NAMESPACE + QUERY_FOR_BRANCH, amountConfigEntity);
	}
	/**
	 * 获取业务编码6位数字
	 * @return
	 */
	@Override
	public int getNextSuffix() {
		NoSuffixEntity nextsuffix = (NoSuffixEntity)this.getSqlSession().selectOne(NAMESPACE + GET_NEXT_SUFFIX);
		if(nextsuffix == null) {
			return 0;
		}
		return Integer.parseInt(nextsuffix.getNextSuffix());
	}

	@Override
	public void updateClaimNoSuffix(int nextSuffix) {
		this.getSqlSession().update(NAMESPACE + UPDATE_NEXT_SUFFIX, nextSuffix+"");
	}

	@Override
	public void insertClaimNoSuffix(int nextSuffix) {
		this.getSqlSession().insert(NAMESPACE + INSERT_NEXT_SUFFIX, nextSuffix+"");
	}
	
	@Override
	public int getProcNextSuffix() {
		Map map = new HashMap();
		this.getSqlSession().selectOne(NAMESPACE + PROC_NEXT_SUFFIX, map);
		String ret = (String)map.get("suffix");
		return Integer.parseInt(ret);
	}
}
