package com.deppon.crm.module.report.shared.domain;
/**
 * 	流失预警月报表Bean对象
 * **/
public class WarnLostCustMonthly extends WarnLostCustPub{
	/**
	 *流失数据统计
	 * **/
	//流失客户（上上月发货 近两月未发货）
	private int lostCust;
	//上上月有发货
	private int totalCustMonthBeforeLastSend;
	//零担上上月有发货
	private int lTTCustMonthBeforeLastSend;
	//快递上上月有发货
	private int expressCustMonthBeforeLastSend;
	//客户流失率
	private float lostPercentage;
	//零担流失客户
	private int lTTLostCust;
	//快递流失客户
	private int expressLostCust;
	//零担客户流失率
	private float lTTLostPercentage;
	//快递客户流失率
	private float expressLostPercentage;
	public int getLostCust() {
		return lostCust;
	}
	public void setLostCust(int lostCust) {
		this.lostCust = lostCust;
	}
	public int getTotalCustMonthBeforeLastSend() {
		return totalCustMonthBeforeLastSend;
	}
	public void setTotalCustMonthBeforeLastSend(int totalCustMonthBeforeLastSend) {
		this.totalCustMonthBeforeLastSend = totalCustMonthBeforeLastSend;
	}
	public float getLostPercentage() {
		return lostPercentage;
	}
	public void setLostPercentage(float lostPercentage) {
		this.lostPercentage = lostPercentage;
	}
	public int getlTTLostCust() {
		return lTTLostCust;
	}
	public void setlTTLostCust(int lTTLostCust) {
		this.lTTLostCust = lTTLostCust;
	}
	public int getExpressLostCust() {
		return expressLostCust;
	}
	public void setExpressLostCust(int expressLostCust) {
		this.expressLostCust = expressLostCust;
	}
	public float getlTTLostPercentage() {
		return lTTLostPercentage;
	}
	public void setlTTLostPercentage(float lTTLostPercentage) {
		this.lTTLostPercentage = lTTLostPercentage;
	}
	public float getExpressLostPercentage() {
		return expressLostPercentage;
	}
	public void setExpressLostPercentage(float expressLostPercentage) {
		this.expressLostPercentage = expressLostPercentage;
	}
	public int getlTTCustMonthBeforeLastSend() {
		return lTTCustMonthBeforeLastSend;
	}
	public void setlTTCustMonthBeforeLastSend(int lTTCustMonthBeforeLastSend) {
		this.lTTCustMonthBeforeLastSend = lTTCustMonthBeforeLastSend;
	}
	public int getExpressCustMonthBeforeLastSend() {
		return expressCustMonthBeforeLastSend;
	}
	public void setExpressCustMonthBeforeLastSend(int expressCustMonthBeforeLastSend) {
		this.expressCustMonthBeforeLastSend = expressCustMonthBeforeLastSend;
	}
	
}
