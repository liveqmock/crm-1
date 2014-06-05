package com.deppon.crm.module.interfaces.workflow.domain;

import java.util.Date;

/**
 * 网厅在线理赔申请的时候需要判断某个运单是否可以理赔，如果可以理赔并且需要返回相关信息
 * @author davidcun @2012-4-7
 */
public class RecompenseOnlineVerifyInfo {

	//是否可以理赔标识
	private boolean canRecompense;
	
	//发货人电话
	private String shipperTelePhone;
	//发货人手机
	private String shipperMobilePhone;
	//收货人电话
	private String receiveTelephone;
	//收货人手机
	private String receiveMobilePhone;
	//到达部门编号
	private String receiveDept;
	//发货部门编号
	private String shipmentsDept;
	//保价金额
	private double insurance;
	//始发站
	private String startStation;
	//目的站
	private String destinationStation;
	//发货时间
	private Date shipmentsTime;
	//发货部门子公司名称
	private String shipCompanyName;
	//收货部门子公司名称
	private String receiveCompanyName;
	//公司名称(德邦物流有限公司)
//	private String companyName;
	//运输性质(方式，名称)。
	private String transportType;
	
	public String getShipCompanyName() {
		return shipCompanyName;
	}
	public void setShipCompanyName(String shipCompanyName) {
		this.shipCompanyName = shipCompanyName;
	}
	public String getReceiveCompanyName() {
		return receiveCompanyName;
	}
	public void setReceiveCompanyName(String receiveCompanyName) {
		this.receiveCompanyName = receiveCompanyName;
	}
	public boolean isCanRecompense() {
		return canRecompense;
	}
	public void setCanRecompense(boolean canRecompense) {
		this.canRecompense = canRecompense;
	}
	
	public String getShipperTelePhone() {
		return shipperTelePhone;
	}
	public void setShipperTelePhone(String shipperTelePhone) {
		this.shipperTelePhone = shipperTelePhone;
	}
	public String getShipperMobilePhone() {
		return shipperMobilePhone;
	}
	public void setShipperMobilePhone(String shipperMobilePhone) {
		this.shipperMobilePhone = shipperMobilePhone;
	}
	public String getReceiveTelephone() {
		return receiveTelephone;
	}
	public void setReceiveTelephone(String receiveTelephone) {
		this.receiveTelephone = receiveTelephone;
	}
	public String getReceiveMobilePhone() {
		return receiveMobilePhone;
	}
	public void setReceiveMobilePhone(String receiveMobilePhone) {
		this.receiveMobilePhone = receiveMobilePhone;
	}
	public String getReceiveDept() {
		return receiveDept;
	}
	public void setReceiveDept(String receiveDept) {
		this.receiveDept = receiveDept;
	}
	public String getShipmentsDept() {
		return shipmentsDept;
	}
	public void setShipmentsDept(String shipmentsDept) {
		this.shipmentsDept = shipmentsDept;
	}
	public double getInsurance() {
		return insurance;
	}
	public void setInsurance(double insurance) {
		this.insurance = insurance;
	}
	public String getStartStation() {
		return startStation;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public String getDestinationStation() {
		return destinationStation;
	}
	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}
	public Date getShipmentsTime() {
		return shipmentsTime;
	}
	public void setShipmentsTime(Date shipmentsTime) {
		this.shipmentsTime = shipmentsTime;
	}
//	public String getCompanyName() {
//		return companyName;
//	}
//	public void setCompanyName(String companyName) {
//		this.companyName = companyName;
//	}
	public String getTransportType() {
		return transportType;
	}
	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
}
