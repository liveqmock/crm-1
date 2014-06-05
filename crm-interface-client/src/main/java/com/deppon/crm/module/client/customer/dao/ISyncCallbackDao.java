package com.deppon.crm.module.client.customer.dao;

import com.deppon.crm.module.client.common.domain.WaitCustomerLogInfo;

/**
 * @作者：罗典
 * @时间：2012-7-24
 * @描述：客户数据同步日志信息返回
 * */
public interface ISyncCallbackDao {

	/**
	 * @作者：罗典
	 * @时间：2012-7-24
	 * @参数：transNo 事务号
	 * @返回值： 客户日志信息
	 * @描述：根据事务号查询客户出错日志信息
	 * */
	public WaitCustomerLogInfo queryWaitCustomerLogInfo(String transNo);

	/**
	 * @作者：罗典
	 * @时间：2012-7-24
	 * @参数：waitCustomerLogInfo 客户日志信息(事务号必填)
	 * @返回值： 客户日志信息
	 * @描述：根据事务号修改客户出错日志信息
	 * */
	public boolean updateWaitCustomerLogInfo(WaitCustomerLogInfo waitCustomerLogInfo);
}
