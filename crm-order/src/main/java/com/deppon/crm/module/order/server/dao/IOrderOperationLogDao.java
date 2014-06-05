package com.deppon.crm.module.order.server.dao;

import java.util.List;

import com.deppon.crm.module.order.shared.domain.OrderOperationLog;

/**
 * 
 * @description 订单操作记录DAO接口.
 * @author 安小虎
 * @version 0.1 2012-3-13
 * @date 2012-3-13
 */
public interface IOrderOperationLogDao {
	/**
	 * 
	 * @description 订单操作记录DAO新增接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-13
	 * @param 订单操作记录对象
	 * @date 2012-3-13
	 * @return 订单操作记录对象
	 * @update 2012-3-13 上午9:11:57
	 */
	OrderOperationLog saveOrderOperationLog(
			OrderOperationLog orderOperationLog);

	/**
	 * 
	 * @description 订单操作记录DAO批量新增接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-13
	 * @param none
	 * @date 2012-3-13
	 * @return none
	 * @update 2012-3-13 下午1:44:06
	 */
	boolean batchSaveOrderOperationLog(List<OrderOperationLog> list);

	/**
	 * 
	 * @description 根据订单ID获得订单操作记录集合.
	 * @author 安小虎
	 * @version 0.1 2012-3-13
	 * @param 订单ID
	 * @date 2012-3-13
	 * @return 订单操作记录对象
	 * @update 2012-3-13 上午9:14:51
	 */
	List<OrderOperationLog> getListByOrderId(
			String orderId);

	/**
	 * 
	 * @description 根据订单ID获得订单操作记录条数.
	 * @author 安小虎
	 * @version 0.1 2012-3-22
	 * @param 订单ID
	 * @date 2012-3-22
	 * @return 订单操作记录条数
	 * @update 2012-3-22 上午11:44:23
	 */
	Long searchCountByOrderId(String orderId);

	/**
	 * 
	 * @description 根据条件查询操作记录.
	 * @author 安小虎
	 * @version 0.1 2012-3-15
	 * @param 操作记录对象
	 * @date 2012-3-15
	 * @return 按创建时间倒序的操作记录对象集合
	 * @update 2012-3-15 下午2:46:26
	 */
	List<OrderOperationLog> getListByCondion(
			OrderOperationLog orderOperationLog);

}