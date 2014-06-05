package com.deppon.crm.module.client.customer.dao.impl;

import com.deppon.crm.module.client.common.domain.WaitCustomerLogInfo;
import com.deppon.crm.module.client.customer.dao.ISyncCallbackDao;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class SyncCallbackDaoImpl extends iBatis3DaoImpl implements
		ISyncCallbackDao {

	private static final String NAMESPACE = "com.deppon.crm.module.client.shared.domain.WaitCustomerLogInfo.";

	// 根据事务号查询信息
	private static final String QUERY_TRANSNO = "queryByTransactionNo";
	// 根据事务号修改客户日志信息
	private static final String UPDATE_TRANSNO = "updateByTransactionNo";

	/**
	 * @作者：罗典
	 * @时间：2012-7-24
	 * @参数：transNo 事务号
	 * @返回值： 客户日志信息
	 * @描述：根据事务号查询客户出错日志信息
	 * */
	@Override
	public WaitCustomerLogInfo queryWaitCustomerLogInfo(String transNo) {
		return (WaitCustomerLogInfo) this.getSqlSession().selectOne(
				NAMESPACE + QUERY_TRANSNO, transNo);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-7-24
	 * @参数：waitCustomerLogInfo 客户日志信息(事务号必填)
	 * @返回值： 客户日志信息
	 * @描述：根据事务号修改客户出错日志信息
	 * */
	public boolean updateWaitCustomerLogInfo(WaitCustomerLogInfo waitCustomerLogInfo){
		return this.getSqlSession().update(
				NAMESPACE + UPDATE_TRANSNO, waitCustomerLogInfo)>0?true:false;
	}
}
