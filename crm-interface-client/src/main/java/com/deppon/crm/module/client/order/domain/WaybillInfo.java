package com.deppon.crm.module.client.order.domain;

import java.math.BigDecimal;
import java.util.Date;

public class WaybillInfo {
	
	//运单号
	private String waybillNumber;
	
	//---------时时创建会员-------------
	//判断是否是发货客户,如果false表示就是收货客户的单
	private Boolean shipperment;
	//客户类型
	private String customerType;
	//预付金额
	private double prepaid;
	//到付金额
	private double freightCollect;
	//劳务费
	private double servicefee;
	//代收货款
	private double collectionCharges;
	
	//---------订运单关联，未签收运单信息查询--------------
	
	//发货人姓名
	private String shipper;
	//发货人手机
	private String shipperPhone;
	//发货人固定电话：区号-主机号码-分机号
	private String shipperTelephone;
	//发货人地址
	private String shipperAddress;
	//运单状态
	private int waybillStatus;
	
	//---------理赔上报，异常签收状态的运单信息---------------
	// 收货人
	protected String consignee;
	 // 收货人手机
    protected String consigneeMobile;
    // 收货人编码
    protected String consigneeNumber;
    // 收货人固话
    protected String consigneePhone;
	//包装
	private String packaging;
	//运输类型
	private String transportType;
	//收货部门名称
	private String receiveDept;
	//目的站
	private String destinationStation;
	//始发站
	private String startStation;
	//发货日期
	private Date shipmentDate;
	//货物名称
	private String goodsName;
	private int piece;
	private double weight;
	private double volumn;
	//保价金额
	private double insurance;
	//发货客户编码
	private String shipperCustomer;
	//收货客户编码
	private String receiveCustomer;
	//是否是出库异常签收状态
	private Boolean failedSignReceive;
	//出发部门编码，和收货部门含义一样
	private String leaveDept;
	// 到达部门标杆编码
	private String arrivedDeptNum;
	
	//----------未积分运单查询-----------
	//到达部门名称
	private String arrivedDeptName;
	//开单日期
	private Date bizDate;
	//出发部门名称
	private String leaveDeptName;
	//支付方式
	private String payment;
	//到达部门库存状态
	private String storeState;
	//总费用（预付+到付-代收货款-劳务费）
	private BigDecimal totalFee;
	//运输类型（已有）
//	private String transType;
	
	public String getCustomerType() {
		return customerType;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getArrivedDeptNum() {
		return arrivedDeptNum;
	}
	public void setArrivedDeptNum(String arrivedDeptNum) {
		this.arrivedDeptNum = arrivedDeptNum;
	}
	public String getConsigneeMobile() {
		return consigneeMobile;
	}
	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}
	public String getConsigneeNumber() {
		return consigneeNumber;
	}
	public void setConsigneeNumber(String consigneeNumber) {
		this.consigneeNumber = consigneeNumber;
	}
	public String getConsigneePhone() {
		return consigneePhone;
	}
	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getWaybillNumber() {
		return waybillNumber;
	}
	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}
	public String getShipperPhone() {
		return shipperPhone;
	}
	public void setShipperPhone(String shipperPhone) {
		this.shipperPhone = shipperPhone;
	}
	public String getShipperTelephone() {
		return shipperTelephone;
	}
	public void setShipperTelephone(String shipperTelephone) {
		this.shipperTelephone = shipperTelephone;
	}
	public String getShipperAddress() {
		return shipperAddress;
	}
	public void setShipperAddress(String shipperAddress) {
		this.shipperAddress = shipperAddress;
	}
	public int getWaybillStatus() {
		return waybillStatus;
	}
	public void setWaybillStatus(int waybillStatus) {
		this.waybillStatus = waybillStatus;
	}
	public String getPackaging() {
		return packaging;
	}
	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	public String getTransportType() {
		return transportType;
	}
	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
	public String getReceiveDept() {
		return receiveDept;
	}
	public void setReceiveDept(String receiveDept) {
		this.receiveDept = receiveDept;
	}
	public String getDestinationStation() {
		return destinationStation;
	}
	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}
	public String getStartStation() {
		return startStation;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public Date getShipmentDate() {
		return shipmentDate;
	}
	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getPiece() {
		return piece;
	}
	public void setPiece(int piece) {
		this.piece = piece;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getVolumn() {
		return volumn;
	}
	public void setVolumn(double volumn) {
		this.volumn = volumn;
	}
	public double getInsurance() {
		return insurance;
	}
	public void setInsurance(double insurance) {
		this.insurance = insurance;
	}
	public String getShipperCustomer() {
		return shipperCustomer;
	}
	public void setShipperCustomer(String shipperCustomer) {
		this.shipperCustomer = shipperCustomer;
	}
	public String getReceiveCustomer() {
		return receiveCustomer;
	}
	public void setReceiveCustomer(String receiveCustomer) {
		this.receiveCustomer = receiveCustomer;
	}
	public Boolean isFailedSignReceive() {
		return failedSignReceive;
	}
	public void setFailedSignReceive(Boolean failedSignReceive) {
		this.failedSignReceive = failedSignReceive;
	}
	public String getLeaveDept() {
		return leaveDept;
	}
	public void setLeaveDept(String leaveDept) {
		this.leaveDept = leaveDept;
	}
	public double getPrepaid() {
		return prepaid;
	}
	public void setPrepaid(double prepaid) {
		this.prepaid = prepaid;
	}
	public double getFreightCollect() {
		return freightCollect;
	}
	public void setFreightCollect(double freightCollect) {
		this.freightCollect = freightCollect;
	}
	public double getServicefee() {
		return servicefee;
	}
	public void setServicefee(double servicefee) {
		this.servicefee = servicefee;
	}
	public double getCollectionCharges() {
		return collectionCharges;
	}
	public void setCollectionCharges(double collectionCharges) {
		this.collectionCharges = collectionCharges;
	}
	public String getShipper() {
		return shipper;
	}
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}
	public Boolean isShipperment() {
		return shipperment;
	}
	public void setShipperment(Boolean shipperment) {
		this.shipperment = shipperment;
	}
	public String getArrivedDeptName() {
		return arrivedDeptName;
	}
	public void setArrivedDeptName(String arrivedDeptName) {
		this.arrivedDeptName = arrivedDeptName;
	}
	public Date getBizDate() {
		return bizDate;
	}
	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}
	public String getLeaveDeptName() {
		return leaveDeptName;
	}
	public void setLeaveDeptName(String leaveDeptName) {
		this.leaveDeptName = leaveDeptName;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getStoreState() {
		return storeState;
	}
	public void setStoreState(String storeState) {
		this.storeState = storeState;
	}
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	@Override
	public String toString() {
		return "WaybillInfo [waybillNumber=" + waybillNumber + ", shipperment="
				+ shipperment + ", customerType=" + customerType + ", prepaid="
				+ prepaid + ", freightCollect=" + freightCollect
				+ ", servicefee=" + servicefee + ", collectionCharges="
				+ collectionCharges + ", shipper=" + shipper
				+ ", shipperPhone=" + shipperPhone + ", shipperTelephone="
				+ shipperTelephone + ", shipperAddress=" + shipperAddress
				+ ", waybillStatus=" + waybillStatus + ", packaging="
				+ packaging + ", transportType=" + transportType
				+ ", receiveDept=" + receiveDept + ", destinationStation="
				+ destinationStation + ", startStation=" + startStation
				+ ", shipmentDate=" + shipmentDate + ", goodsName=" + goodsName
				+ ", piece=" + piece + ", weight=" + weight + ", volumn="
				+ volumn + ", insurance=" + insurance + ", shipperCustomer="
				+ shipperCustomer + ", receiveCustomer=" + receiveCustomer
				+ ", failedSignReceive=" + failedSignReceive + ", leaveDept="
				+ leaveDept + ", arrivedDeptName=" + arrivedDeptName
				+ ", bizDate=" + bizDate + ", leaveDeptName=" + leaveDeptName
				+ ", payment=" + payment + ", storeState=" + storeState
				+ ", totalFee=" + totalFee + "]";
	}
}
