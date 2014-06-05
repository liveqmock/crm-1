package com.deppon.crm.module.interfaces.channel.impl;

import java.util.UUID;

import javax.xml.ws.Holder;

import com.deppon.crm.module.interfaces.channel.ChannelService;
import com.deppon.crm.module.interfaces.channel.domain.CommonException;
import com.deppon.crm.module.interfaces.channel.domain.ESBHeader;
import com.deppon.crm.module.interfaces.channel.domain.UpdateWayBillNumRequest;
import com.deppon.crm.module.interfaces.channel.domain.UpdateWayBillNumResponse;
import com.deppon.crm.module.interfaces.common.util.Constant;
import com.deppon.crm.module.order.server.manager.impl.OrderManager;

public class ChannelServiceImpl implements ChannelService{

	//订单Manager
	private OrderManager orderManager;
	@Override
	public UpdateWayBillNumResponse channelUpdateWayBillNum(
			UpdateWayBillNumRequest request, Holder<ESBHeader> esbHeader)
			throws CommonException {
		ESBHeader header = esbHeader.value;
		checkNull(header, Constant.PARAMS_LOSE);
		checkNull(request, Constant.PARAMS_LOSE);
		checkNull(request.getChannelNumber(), Constant.PARAMS_LOSE);
		checkNull(request.getWaybilNumber(), Constant.PARAMS_LOSE);
		header.setResponseId(UUID.randomUUID().toString());
		header.setResultCode(1);
		UpdateWayBillNumResponse updateWayBillNumResponse = new UpdateWayBillNumResponse();
		try {
			//调用业务模块
			boolean result = orderManager.updateWaybillNumByChannel(request.getChannelNumber(), request.getWaybilNumber());
			updateWayBillNumResponse.setResult(result);
		} catch (Exception e) {
			e.printStackTrace();
			header.setResultCode(0);
			updateWayBillNumResponse.setResult(false);
			throw new CommonException(e.getMessage());
		}
		return updateWayBillNumResponse;
	}



	public static void checkNull(Object obj, String errorInfo)
			throws CommonException {
		if (obj == null) {
			throw new CommonException(errorInfo + " " + null);
		}
	}



	/**
	 * Description:orderManager<br />
	 * @author CoCo
	 * @version 0.1 2013-12-5
	 */
	public OrderManager getOrderManager() {
		return orderManager;
	}



	/**
	 * Description:orderManager<br />
	 * @author CoCo
	 * @version 0.1 2013-12-5
	 */
	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}
	
}
