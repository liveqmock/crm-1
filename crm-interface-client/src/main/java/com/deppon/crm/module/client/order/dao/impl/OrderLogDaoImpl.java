package com.deppon.crm.module.client.order.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.client.order.dao.IOrderLogDao;
import com.deppon.crm.module.client.order.domain.OrderStatusLog;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * @作者：罗典
 * @时间：2012-5-2
 * @描述：接口订单DAO层
 * */
@SuppressWarnings("unchecked")
public class OrderLogDaoImpl extends iBatis3DaoImpl implements IOrderLogDao {
	private final String NAMESPACE = "com.deppon.crm.module.client.shared.domain.OrderLog.";
	// 查询需反馈订单状态的日志集合
	private final String QUERY_ORDERSTATUS = "queryOrderStatusUpdate";
	// 将操作完毕的日志集合修改为已完结
	private final String UPDATE_ORDERSTATUS = "updateManyLogs";

	/**
	 * @作者：罗典
	 * @时间：2012-5-2
	 * @描述：更新订单更新日志集合的状态为已处理
	 * @参数：logs，需修改状态的日志集合
	 * @返回值：无
	 * */
	public void updateOrderStatusLogStatus(List<OrderStatusLog> logs) {
		String ids = "";
		List<Integer> argus = new ArrayList<Integer>();
		for (int i = 0; i < logs.size(); i++) {
			if (i == 0) {
				ids += logs.get(i).getId();
			} else {
				ids += "," + logs.get(i).getId();
			}
			argus.add(logs.get(i).getId());
		}
		this.getSqlSession().update(NAMESPACE + UPDATE_ORDERSTATUS, argus);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-5-2
	 * @描述：查询需更新的订单更新日志集合
	 * @参数：无
	 * @返回值：订单状态更新日志集合
	 * */
	@Override
	public List<OrderStatusLog> getOrderStatusLog(int size) {
		return (List<OrderStatusLog>) this.getSqlSession().selectList(
				NAMESPACE + QUERY_ORDERSTATUS,size);
	}
	
	/**
	 * @作者：罗典
	 * @时间：2012-5-2
	 * @描述：更新订单更新日志的状态为已处理
	 * @参数：log，需修改状态的日志
	 * @返回值：无
	 * */
	public void updateOrderStatusLogStatus(OrderStatusLog log){
		this.getSqlSession().update(NAMESPACE + UPDATE_ORDERSTATUS, log.getId());
	}

	@Override
	public void updateLogById(OrderStatusLog log) {
		getSqlSession().update(NAMESPACE+"updateLogById", log);
	}
}
