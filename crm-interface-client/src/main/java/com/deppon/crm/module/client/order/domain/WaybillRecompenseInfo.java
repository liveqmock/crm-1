package com.deppon.crm.module.client.order.domain;

import java.util.Date;

public class WaybillRecompenseInfo {

	//发货人电话
	private String shipperTelePhone;
	//发货人手机
	private String shipperMobilePhone;
	//收货人电话
	private String receiveTelephone;
	//收货人手机
	private String receiveMobilePhone;
	//到达部门(标杆编码)
	private String receiveDept;
	//发货部门(标杆编码)
	private String shipmentsDept;
	//保价金额
	private double insurance;
	//始发站
	private String startStation;
	//目的站
	private String destinationStation;
	//发货时间
	private Date shipmentsTime;
	
	//运输性质(方式，名称)。
	private String transportType;
	
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
	
	public String getTransportType() {
		return transportType;
	}
	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
	@Override
	public String toString() {
		return "WaybillRecompenseInfo [shipperTelePhone=" + shipperTelePhone
				+ ", shipperMobilePhone=" + shipperMobilePhone
				+ ", receiveTelephone=" + receiveTelephone
				+ ", receiveMobilePhone=" + receiveMobilePhone
				+ ", receiveDept=" + receiveDept + ", shipmentsDept="
				+ shipmentsDept + ", insurance=" + insurance
				+ ", startStation=" + startStation + ", destinationStation="
				+ destinationStation + ", shipmentsTime=" + shipmentsTime
				+ ", transportType=" + transportType + "]";
	}
}
