package com.deppon.crm.module.client.order.dao;

import java.util.List;

import com.deppon.crm.module.client.order.domain.OrderStatusLog;

/**
 * @作者：罗典
 * @时间：2012-5-2
 * @描述：接口订单DAO层
 * */
public interface IOrderLogDao {
	/**
	 * @作者：罗典
	 * @时间：2012-5-2
	 * @描述：查询需更新的订单更新日志集合
	 * @参数：无
	 * @返回值：订单状态更新日志集合
	 * */
	public List<OrderStatusLog> getOrderStatusLog(int size);
	
	/**
	 * @作者：罗典
	 * @时间：2012-5-2
	 * @描述：更新订单更新日志集合的状态为已处理
	 * @参数：logs，需修改状态的日志集合
	 * @返回值：无
	 * */
	public void updateOrderStatusLogStatus(List<OrderStatusLog> logs);
	
	/**
	 * @作者：罗典
	 * @时间：2012-5-2
	 * @描述：更新订单更新日志的状态为已处理
	 * @参数：log，需修改状态的日志
	 * @返回值：无
	 * */
	public void updateOrderStatusLogStatus(OrderStatusLog log);
	
	
	public void updateLogById(OrderStatusLog log);
}
