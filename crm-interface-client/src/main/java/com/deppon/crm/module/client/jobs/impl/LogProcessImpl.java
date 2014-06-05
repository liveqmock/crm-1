package com.deppon.crm.module.client.jobs.impl;

import java.util.List;

import com.deppon.crm.module.client.jobs.ILogProcess;
import com.deppon.crm.module.client.order.dao.IOrderLogDao;
import com.deppon.crm.module.client.order.domain.OrderStatusLog;

public class LogProcessImpl implements ILogProcess<OrderStatusLog> {

	private IOrderLogDao orderLogDao;
	@Override
	public void update(List<OrderStatusLog> logs, LogStatus status) {
		if (status ==null || logs==null || logs.size()<1) {
			return;
		}else if(status.equals(LogStatus.FAIL)){
			for (OrderStatusLog orderStatusLog : logs) {
				update(orderStatusLog, LogStatus.FAIL);
			}
		}else{
			try {
				orderLogDao.updateOrderStatusLogStatus(logs);
			} catch (Exception e) {

				for (int i = 0; i < logs.size(); i++) {
					try {
						update(logs.get(i), status);
					} catch (Exception ex) {
						break;
					}
				}
			}
		}
	}

	@Override
	public boolean update(OrderStatusLog log, LogStatus status) {
		if (status == null || log==null) {
			
			return false;
		}else if(status.equals(LogStatus.FAIL)){
			log.setSendCount(log.getSendCount()+1);
			orderLogDao.updateLogById(log);
		}else{
			orderLogDao.updateOrderStatusLogStatus(log);
		}
		return true;
	}

	public IOrderLogDao getOrderLogDao() {
		return orderLogDao;
	}

	public void setOrderLogDao(IOrderLogDao orderLogDao) {
		this.orderLogDao = orderLogDao;
	}

}
