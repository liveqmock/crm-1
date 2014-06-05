package com.deppon.crm.module.client.order.domain;

public class ChannelOrderStatusInfo{

	//订单号(暂时不用)
	private String orderNumber;
	//渠道单号
	private String channelOrderNumber;
	//状态
	/*
	 * 淘宝：
	 * 		已拒绝(REJECT)->已拒绝，已撤销(CANCEL)->已撤销，
	 * 		已受理(ACCEPT)->已受理，已开单(GOT)->揽货成功，揽货失败(FAILGOT)->揽货失败，
	 * 		正常签收(SIGNSUCCESS)->签收成功，异常签收(SIGNFAIL)->签收失败
	 * 阿里巴巴：
	 * 		已拒绝(REJECT)->已拒绝，已撤销(CANCEL)->已撤销，
	 * 		已受理(ACCEPT)->已受理，已开单(GOT)->揽货成功，揽货失败(FAILGOT)->揽货失败，
	 * 		正常签收(SIGNSUCCESS)->签收成功，异常签收(SIGNFAIL)->签收失败
	 * 淘宝商城：
	 * 		已开单(GOT)->已开单
	 * 		正常签收(SIGNSUCCESS)->签收成功，异常签收(SIGNFAIL)->签收失败
	 * 		
	 */
	private String orderStatus;
	//备注，存放失败原因
	private String comment;
	//运单号(暂时不用)
	private String waybillNumber;
	//订单来源(TAOBAO=淘宝网,ALIBABA=阿里巴巴网,YOUSHANG=金蝶友商网,SHANGCHENG=淘宝商城)
	private String orderSource;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getChannelOrderNumber() {
		return channelOrderNumber;
	}

	public void setChannelOrderNumber(String channelOrderNumber) {
		this.channelOrderNumber = channelOrderNumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
}
