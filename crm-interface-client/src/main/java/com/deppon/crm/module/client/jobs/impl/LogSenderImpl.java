package com.deppon.crm.module.client.jobs.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.jobs.ILogSender;
import com.deppon.crm.module.client.order.IOrderOperate;
import com.deppon.crm.module.client.order.domain.ChannelOrderStatusInfo;
import com.deppon.crm.module.client.order.domain.OrderStatusLog;
/**
 * 
 * @author davidcun @2012-5-4
 */
public class LogSenderImpl implements ILogSender<OrderStatusLog> {

	private static Log log = LogFactory.getLog(LogSenderImpl.class);
	
	private IOrderOperate orderOperate;
	
	public int[] send(List<OrderStatusLog> logs) {
		if (logs==null || logs.size()<1) {
			return null;
		}
		List<ChannelOrderStatusInfo> infos = new ArrayList<ChannelOrderStatusInfo>();
		for (int i=0;i<logs.size();i++) {
			ChannelOrderStatusInfo info = new ChannelOrderStatusInfo();
			info.setChannelOrderNumber(logs.get(i).getOrderNumber());
			info.setComment(logs.get(i).getRemark());
			info.setOrderSource(logs.get(i).getOrderSource());
			info.setOrderStatus(logs.get(i).getOrderStatus());
			infos.add(info);
		}
		try {
			List<String> result = orderOperate.updateOrderStatus(infos);
			if (result==null || result.size()==0) {
				return null;
			}
			List<Integer> rst = new ArrayList<Integer>();
//			String[] orderNumbers = ebmResponse.getMessage().split(",");
			for (int i=0;i<infos.size();i++) {
				for (String string : result) {
					if (string.equals(infos.get(i).getChannelOrderNumber())) {
						rst.add(i);
					}
				}
			}
			int[] rt = new int[rst.size()];
			for (int i = 0; i < rst.size(); i++) {
				rt[i] = rst.get(i);
			}
			return rt;
		} catch (CrmBusinessException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean send(OrderStatusLog t) {
		//
		ChannelOrderStatusInfo info = new ChannelOrderStatusInfo();
		if (t==null) {
			return false;
		}
		if (log.isDebugEnabled()) {
			log.debug(t);
		}
		info.setChannelOrderNumber(t.getOrderNumber());
		info.setComment(t.getRemark());
		info.setOrderStatus(t.getOrderStatus());
		info.setOrderSource(t.getOrderSource());
		try {
			boolean flag = orderOperate.updateOrderStatus(info);
			return flag;
		} catch (CrmBusinessException e) {
			if (log.isErrorEnabled()) {
				log.error(String.format("update EBM order satus fail OrderStatusLog info is {%s} ",t.toString()),e);
			}
			e.printStackTrace();
		}
		return false;
	}

	public IOrderOperate getOrderOperate() {
		return orderOperate;
	}

	public void setOrderOperate(IOrderOperate orderOperate) {
		this.orderOperate = orderOperate;
	}

}
