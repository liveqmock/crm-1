package com.deppon.crm.module.client.jobs.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.client.jobs.ILogProvider;
import com.deppon.crm.module.client.order.dao.IOrderLogDao;
import com.deppon.crm.module.client.order.domain.OrderStatusLog;

/**
 * 
 * @author davidcun @2012-5-4
 */

public class LogProviderImpl implements ILogProvider<OrderStatusLog> {

	private IOrderLogDao orderLogDao;
	
	private int dataSize = 1000;

	Map<String, Integer> cache = new HashMap<String, Integer>();

	@Override
	public Map<String, List<OrderStatusLog>> load() {
		List<OrderStatusLog> logList = orderLogDao.getOrderStatusLog(dataSize);
		Map<String, List<OrderStatusLog>> logMap = changeOrderStatusLogToMap(logList);
		return logMap;
	}
	
	/**
	 * 加载固定量的数据
	 */
	public List<OrderStatusLog> loadList(){
		return loadList(dataSize);
	}
	

	/**
	 * @作者：罗典
	 * @时间：2012-5-3
	 * @描述：将日志集合转换为MAP集合
	 * */
	private Map<String, List<OrderStatusLog>> changeOrderStatusLogToMap(
			List<OrderStatusLog> logList) {
		Map<String, List<OrderStatusLog>> logMap = new HashMap<String, List<OrderStatusLog>>();
		for (OrderStatusLog log : logList) {
			List<OrderStatusLog> orderLogList = new ArrayList<OrderStatusLog>();
			if (logMap.containsKey(log.getOrderNumber())) {
				orderLogList = logMap.get(log.getOrderNumber());
				logMap.remove(log.getOrderNumber());
			}
			orderLogList.add(log);
			logMap.put(log.getOrderNumber(), orderLogList);
		}
		return logMap;
	}
	

	public IOrderLogDao getOrderLogDao() {
		return orderLogDao;
	}

	public void setOrderLogDao(IOrderLogDao orderLogDao) {
		this.orderLogDao = orderLogDao;
	}

	@Override
	public void clearCache() {
		cache.clear();
	}

	/**
	 * size 可设置
	 * @author davidcun
	 */
	@Override
	public List<OrderStatusLog> loadList(int size) {
		List<OrderStatusLog> logs = orderLogDao.getOrderStatusLog(size);
		return logs;
//		List<OrderStatusLog> fail = new ArrayList<OrderStatusLog>();
//		for (OrderStatusLog orderStatusLog : logs) {
//			if (cache.containsKey(orderStatusLog.getOrderNumber())) {
//				Integer count = cache.get(orderStatusLog.getOrderNumber());
//				if (count>=5) {
//					fail.add(orderStatusLog);
//				}else{
//					cache.put(orderStatusLog.getOrderNumber(), count+1);
//				}
//			}else{
//				cache.put(orderStatusLog.getOrderNumber(), 1);
//			}
//		}
//		logs.removeAll(fail);
//		return logs;
	}
}
