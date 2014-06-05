package com.deppon.crm.module.client.order.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @作者：罗典
 * @描述：FOSS运单详情
 * @时间：2012-11-7
 * */
public class FossWaybillInfo {
	// 运单号
	private String number;
	// 运输类型(一级产品)：汽运:TRANS_VEHICLE 空运:TRANS_AIRCRAFT
	private String tranType;
	// 发货人
	private String sender;
	// 发货人电话
	private String senderPhone;
	// 发货人手机
	private String senderMobile;
	// 始发站
	private String departure;
	// 发货人地址
	private String senderAddress;
	// 收货人
	private String consignee;
	// 收货人电话
	private String consigneePhone;
	// 收货人手机
	private String consigneeMobile;
	// 目的站
	private String destination;
	// 收货人地址
	private String consigneeAddress;
	// 货物名称
	private String goodName;
	// 件数
	private int pieces;
	// 重量
	private float weight;
	// 体积
	private float cubage;
	// 总费用(公布价运费 + 增值服务费用 - 优惠总费用=到付+预付—代收)
	private BigDecimal totalCharge;
	// 付款方式
	private String payment;
	// 预付（客户预付款金额）
	private BigDecimal preCharge;
	// 到付（客户到付金额）
	private BigDecimal arriveCharge;
	// 代收货款类型
	private String refundType;
	// 代收货款
	private BigDecimal refund;
	// 代收货款手续费
	private BigDecimal refundFee;
	// 开单提货方式
	private String deliveryType;
	// 接货费
	private BigDecimal consignCharge;
	// 送货费
	private BigDecimal deliveryCharge;
	// 签收回单费
	private BigDecimal signBackCharge;
	// 包装费
	private BigDecimal pickCharge;
	// 劳务费
	private BigDecimal laborRebate;
	// 公布价运费（重量/体积 * 费率 * 折扣）
	private BigDecimal publishCharge;
	// 出发部门名称
	private String departureDeptName;
	// 出发部门标杆编码
	private String departureDeptNumber;
	// 出发部门电话
	private String departureDeptPhone;
	// 到达网点名称
	private String ladingStationName;
	// 到达网点标杆编码
	private String ladingStationNumber;
	// 到达网点电话
	private String ladingStationPhone;
	// 是否签收
	private Boolean isSigned;
	// 是否正常签收
	private Boolean isNormalSigned;
	// 签收录入人
	private String signRecorderId;
	// 签收时间
	private Date signedDate;
	// 签收备注
	private String signedDesc;
	// 订单号
	private String orderNumber;
	// 保价声明
	private BigDecimal insuranceValue;
	// 保价手续费
	private BigDecimal insurance;
	// 货物包装
	private String packing;
	// 订单状态
	private String orderState;
	// 其它费用
	private BigDecimal otherPayment;
	// 托运备注
	private String tranDesc;
	// 发货客户编码
	private String senderNumber;
	// 收货客户编码
	private String consigneeNumber;
	// 是否已结款
	private String isClear;
	// 返单类型
	private String signBackType;
	// 配载类型
    //2013.03.25 添加了第一次签收时间
	private Date firstSignedDate;
	// 储运事项
	private String transNotice;
	// 发货时间
	private Date sendTime;
	// 收货部门名称
	private String receiveDeptName;
	// 收货部门标杆代码
	private String receiveDeptNumber;
	// 配载部门
	private String stowageDept;
	/* 运输性质(三级产品)： 
	 * 精准汽运（长途）LRF 
	 * 精准卡航 FLF 
	 * 精准汽运（短途） SRF 
	 * 精准城运  FSF 
	 * 汽运偏线 PLF 
	 * 精准空运 AF 
	 * 整车 FV
	 */
	protected String tranProperty;
	// 出发部门地址
	protected String departureDeptAddr;
	// 出发部门传真
	protected String departureDeptFax;
	// 到达网点地址
	protected String ladingStationAddr;
	// 到达网点传真
	protected String ladingStationFax;
	// 发货人城市编码
	protected String senderCityCode;
	// 发货人城市名称
	protected String senderCityName;
	// 发货人省份编码
	protected String senderProvinceCode;
	// 发货人省份名称
	protected String senderProvinceName;
	// 收货人城市编码
	protected String consigneeCityCode;
	// 收货人城市名称
	protected String consigneeCityName;
	// 收货人省份编码
	protected String consigneeProvinceCode;
	// 收货人省份名称
	protected String consigneeProvinceName;
	// 是否上门接货
	protected Boolean isDoorToDoorPick;
	// 是否短信通知
	protected String smsNoticeResult;
	/*
	 * 签收单返回方式: 
	 * 1-无需返单 
	 * 2-客户签收单原件返回 
	 * 3-客户签收单复印件返回 
	 * 4-运单签收单原件返回 
	 * 5-运单签收单复印件返回
	 * 6-派送代理签收单原件返回 
	 * 7-派送代理签收单传真件返回
	 * */
	protected String signBillBackWay;
 
	//运单所属快递大区编码-出发
	protected String exDepartureRegionNubmer;
	//运单所属快递大区名称-出发
	protected String exDepartureRegionName;
	//运单所属快递大区标杆编码-出发
	protected String exDepartureRegionStandardNubmer;
	//运单所属快递大区编码-到达
	protected String exDestinationRegionNubmer;
	//运单所属快递大区名称-到达
	protected String exDestinationRegionName;
	//运单所属快递大区标杆编码-到达
	protected String exDestinationRegionStandardNubmer;
	//快递员CODE-出发
	protected String exDepartureCourierNumber;
	//快递员名称-出发
	protected String exDepartureCourierName;
	//快递点部CODE-出发
	protected String exDepartureDeptNumber;
	//快递点部标杆编码-出发
	protected String exDepartureDeptStandardNumber;
	//快递点部名称-出发
	protected String exDepartureDeptName;
	//快递员CODE-到达
	protected String exDestinationCourierNumber;
	//快递员名称-到达
	protected String exDestinationCourierName;
	//快递点部CODE-到达
	protected String  exDestinationDeptNumber;
	//快递点部标杆编码-到达
	protected String  exDestinationDeptStandardNumber;
	//快递点部名称-到达
	protected String  exDestinationDeptName;
	public String getStowageDept() {
		return stowageDept;
	}

	public void setStowageDept(String stowageDept) {
		this.stowageDept = stowageDept;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getTranProperty() {
		return tranProperty;
	}

	public void setTranProperty(String tranProperty) {
		this.tranProperty = tranProperty;
	}

	public String getDepartureDeptAddr() {
		return departureDeptAddr;
	}

	public void setDepartureDeptAddr(String departureDeptAddr) {
		this.departureDeptAddr = departureDeptAddr;
	}

	public String getDepartureDeptFax() {
		return departureDeptFax;
	}

	public void setDepartureDeptFax(String departureDeptFax) {
		this.departureDeptFax = departureDeptFax;
	}

	public String getLadingStationAddr() {
		return ladingStationAddr;
	}

	public void setLadingStationAddr(String ladingStationAddr) {
		this.ladingStationAddr = ladingStationAddr;
	}

	public String getLadingStationFax() {
		return ladingStationFax;
	}

	public void setLadingStationFax(String ladingStationFax) {
		this.ladingStationFax = ladingStationFax;
	}

	public String getSenderCityCode() {
		return senderCityCode;
	}

	public void setSenderCityCode(String senderCityCode) {
		this.senderCityCode = senderCityCode;
	}

	public String getSenderCityName() {
		return senderCityName;
	}

	public void setSenderCityName(String senderCityName) {
		this.senderCityName = senderCityName;
	}

	public String getSenderProvinceCode() {
		return senderProvinceCode;
	}

	public void setSenderProvinceCode(String senderProvinceCode) {
		this.senderProvinceCode = senderProvinceCode;
	}

	public String getSenderProvinceName() {
		return senderProvinceName;
	}

	public void setSenderProvinceName(String senderProvinceName) {
		this.senderProvinceName = senderProvinceName;
	}

	public String getConsigneeCityCode() {
		return consigneeCityCode;
	}

	public void setConsigneeCityCode(String consigneeCityCode) {
		this.consigneeCityCode = consigneeCityCode;
	}

	public String getConsigneeCityName() {
		return consigneeCityName;
	}

	public void setConsigneeCityName(String consigneeCityName) {
		this.consigneeCityName = consigneeCityName;
	}

	public String getConsigneeProvinceCode() {
		return consigneeProvinceCode;
	}

	public void setConsigneeProvinceCode(String consigneeProvinceCode) {
		this.consigneeProvinceCode = consigneeProvinceCode;
	}

	public String getConsigneeProvinceName() {
		return consigneeProvinceName;
	}

	public void setConsigneeProvinceName(String consigneeProvinceName) {
		this.consigneeProvinceName = consigneeProvinceName;
	}

	public Boolean getIsDoorToDoorPick() {
		return isDoorToDoorPick;
	}

	public void setIsDoorToDoorPick(Boolean isDoorToDoorPick) {
		this.isDoorToDoorPick = isDoorToDoorPick;
	}

	public String getSmsNoticeResult() {
		return smsNoticeResult;
	}

	public void setSmsNoticeResult(String smsNoticeResult) {
		this.smsNoticeResult = smsNoticeResult;
	}

	public String getSignBillBackWay() {
		return signBillBackWay;
	}

	public void setSignBillBackWay(String signBillBackWay) {
		this.signBillBackWay = signBillBackWay;
	}

	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	public String getSenderMobile() {
		return senderMobile;
	}

	public void setSenderMobile(String senderMobile) {
		this.senderMobile = senderMobile;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneePhone() {
		return consigneePhone;
	}

	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}

	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public int getPieces() {
		return pieces;
	}

	public void setPieces(int pieces) {
		this.pieces = pieces;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getCubage() {
		return cubage;
	}

	public void setCubage(float cubage) {
		this.cubage = cubage;
	}

	public BigDecimal getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public BigDecimal getPreCharge() {
		return preCharge;
	}

	public void setPreCharge(BigDecimal preCharge) {
		this.preCharge = preCharge;
	}

	public BigDecimal getArriveCharge() {
		return arriveCharge;
	}

	public void setArriveCharge(BigDecimal arriveCharge) {
		this.arriveCharge = arriveCharge;
	}

	public String getRefundType() {
		return refundType;
	}

	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}

	public BigDecimal getRefund() {
		return refund;
	}

	public void setRefund(BigDecimal refund) {
		this.refund = refund;
	}

	public BigDecimal getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public BigDecimal getConsignCharge() {
		return consignCharge;
	}

	public void setConsignCharge(BigDecimal consignCharge) {
		this.consignCharge = consignCharge;
	}

	public BigDecimal getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(BigDecimal deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public BigDecimal getSignBackCharge() {
		return signBackCharge;
	}

	public void setSignBackCharge(BigDecimal signBackCharge) {
		this.signBackCharge = signBackCharge;
	}

	public BigDecimal getPickCharge() {
		return pickCharge;
	}

	public void setPickCharge(BigDecimal pickCharge) {
		this.pickCharge = pickCharge;
	}

	public BigDecimal getLaborRebate() {
		return laborRebate;
	}

	public void setLaborRebate(BigDecimal laborRebate) {
		this.laborRebate = laborRebate;
	}

	public BigDecimal getPublishCharge() {
		return publishCharge;
	}

	public void setPublishCharge(BigDecimal publishCharge) {
		this.publishCharge = publishCharge;
	}

	public String getDepartureDeptName() {
		return departureDeptName;
	}

	public void setDepartureDeptName(String departureDeptName) {
		this.departureDeptName = departureDeptName;
	}

	public String getDepartureDeptNumber() {
		return departureDeptNumber;
	}

	public void setDepartureDeptNumber(String departureDeptNumber) {
		this.departureDeptNumber = departureDeptNumber;
	}

	public String getDepartureDeptPhone() {
		return departureDeptPhone;
	}

	public void setDepartureDeptPhone(String departureDeptPhone) {
		this.departureDeptPhone = departureDeptPhone;
	}

	public String getLadingStationName() {
		return ladingStationName;
	}

	public void setLadingStationName(String ladingStationName) {
		this.ladingStationName = ladingStationName;
	}

	public String getLadingStationNumber() {
		return ladingStationNumber;
	}

	public void setLadingStationNumber(String ladingStationNumber) {
		this.ladingStationNumber = ladingStationNumber;
	}

	public String getLadingStationPhone() {
		return ladingStationPhone;
	}

	public void setLadingStationPhone(String ladingStationPhone) {
		this.ladingStationPhone = ladingStationPhone;
	}

	public Boolean getIsSigned() {
		return isSigned;
	}

	public void setIsSigned(Boolean isSigned) {
		this.isSigned = isSigned;
	}

	public Boolean getIsNormalSigned() {
		return isNormalSigned;
	}

	public void setIsNormalSigned(Boolean isNormalSigned) {
		this.isNormalSigned = isNormalSigned;
	}

	public String getSignRecorderId() {
		return signRecorderId;
	}

	public void setSignRecorderId(String signRecorderId) {
		this.signRecorderId = signRecorderId;
	}

	public Date getSignedDate() {
		return signedDate;
	}

	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}

	public String getSignedDesc() {
		return signedDesc;
	}

	public void setSignedDesc(String signedDesc) {
		this.signedDesc = signedDesc;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public BigDecimal getInsuranceValue() {
		return insuranceValue;
	}

	public void setInsuranceValue(BigDecimal insuranceValue) {
		this.insuranceValue = insuranceValue;
	}

	public BigDecimal getInsurance() {
		return insurance;
	}

	public void setInsurance(BigDecimal insurance) {
		this.insurance = insurance;
	}

	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public BigDecimal getOtherPayment() {
		return otherPayment;
	}

	public void setOtherPayment(BigDecimal otherPayment) {
		this.otherPayment = otherPayment;
	}

	public String getTranDesc() {
		return tranDesc;
	}

	public void setTranDesc(String tranDesc) {
		this.tranDesc = tranDesc;
	}

	public String getSenderNumber() {
		return senderNumber;
	}

	public void setSenderNumber(String senderNumber) {
		this.senderNumber = senderNumber;
	}

	public String getConsigneeNumber() {
		return consigneeNumber;
	}

	public void setConsigneeNumber(String consigneeNumber) {
		this.consigneeNumber = consigneeNumber;
	}

	public String getIsClear() {
		return isClear;
	}

	public void setIsClear(String isClear) {
		this.isClear = isClear;
	}

	public String getSignBackType() {
		return signBackType;
	}

	public void setSignBackType(String signBackType) {
		this.signBackType = signBackType;
	}

	/*public String getStowageType() {
		return stowageType;
	}

	public void setStowageType(String stowageType) {
		this.stowageType = stowageType;
	}
*/
	public String getTransNotice() {
		return transNotice;
	}

	public void setTransNotice(String transNotice) {
		this.transNotice = transNotice;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getReceiveDeptName() {
		return receiveDeptName;
	}

	public void setReceiveDeptName(String receiveDeptName) {
		this.receiveDeptName = receiveDeptName;
	}

	public String getReceiveDeptNumber() {
		return receiveDeptNumber;
	}

	public void setReceiveDeptNumber(String receiveDeptNumber) {
		this.receiveDeptNumber = receiveDeptNumber;
	}
	public Date getFirstSignedDate() {
		return firstSignedDate;
	}

	public void setFirstSignedDate(Date firstSignedDate) {
		this.firstSignedDate = firstSignedDate;
	}

	public String getExDepartureRegionNubmer() {
		return exDepartureRegionNubmer;
	}

	public void setExDepartureRegionNubmer(String exDepartureRegionNubmer) {
		this.exDepartureRegionNubmer = exDepartureRegionNubmer;
	}

	public String getExDepartureRegionName() {
		return exDepartureRegionName;
	}

	public void setExDepartureRegionName(String exDepartureRegionName) {
		this.exDepartureRegionName = exDepartureRegionName;
	}

	public String getExDepartureRegionStandardNubmer() {
		return exDepartureRegionStandardNubmer;
	}

	public void setExDepartureRegionStandardNubmer(
			String exDepartureRegionStandardNubmer) {
		this.exDepartureRegionStandardNubmer = exDepartureRegionStandardNubmer;
	}

	public String getExDestinationRegionNubmer() {
		return exDestinationRegionNubmer;
	}

	public void setExDestinationRegionNubmer(String exDestinationRegionNubmer) {
		this.exDestinationRegionNubmer = exDestinationRegionNubmer;
	}

	public String getExDestinationRegionName() {
		return exDestinationRegionName;
	}

	public void setExDestinationRegionName(String exDestinationRegionName) {
		this.exDestinationRegionName = exDestinationRegionName;
	}

	public String getExDestinationRegionStandardNubmer() {
		return exDestinationRegionStandardNubmer;
	}

	public void setExDestinationRegionStandardNubmer(
			String exDestinationRegionStandardNubmer) {
		this.exDestinationRegionStandardNubmer = exDestinationRegionStandardNubmer;
	}

	public String getExDepartureCourierNumber() {
		return exDepartureCourierNumber;
	}

	public void setExDepartureCourierNumber(String exDepartureCourierNumber) {
		this.exDepartureCourierNumber = exDepartureCourierNumber;
	}

	public String getExDepartureCourierName() {
		return exDepartureCourierName;
	}

	public void setExDepartureCourierName(String exDepartureCourierName) {
		this.exDepartureCourierName = exDepartureCourierName;
	}

	public String getExDepartureDeptNumber() {
		return exDepartureDeptNumber;
	}

	public void setExDepartureDeptNumber(String exDepartureDeptNumber) {
		this.exDepartureDeptNumber = exDepartureDeptNumber;
	}

	public String getExDepartureDeptStandardNumber() {
		return exDepartureDeptStandardNumber;
	}

	public void setExDepartureDeptStandardNumber(
			String exDepartureDeptStandardNumber) {
		this.exDepartureDeptStandardNumber = exDepartureDeptStandardNumber;
	}

	public String getExDepartureDeptName() {
		return exDepartureDeptName;
	}

	public void setExDepartureDeptName(String exDepartureDeptName) {
		this.exDepartureDeptName = exDepartureDeptName;
	}

	public String getExDestinationCourierNumber() {
		return exDestinationCourierNumber;
	}

	public void setExDestinationCourierNumber(String exDestinationCourierNumber) {
		this.exDestinationCourierNumber = exDestinationCourierNumber;
	}

	public String getExDestinationCourierName() {
		return exDestinationCourierName;
	}

	public void setExDestinationCourierName(String exDestinationCourierName) {
		this.exDestinationCourierName = exDestinationCourierName;
	}

	public String getExDestinationDeptNumber() {
		return exDestinationDeptNumber;
	}

	public void setExDestinationDeptNumber(String exDestinationDeptNumber) {
		this.exDestinationDeptNumber = exDestinationDeptNumber;
	}

	public String getExDestinationDeptStandardNumber() {
		return exDestinationDeptStandardNumber;
	}

	public void setExDestinationDeptStandardNumber(
			String exDestinationDeptStandardNumber) {
		this.exDestinationDeptStandardNumber = exDestinationDeptStandardNumber;
	}

	public String getExDestinationDeptName() {
		return exDestinationDeptName;
	}

	public void setExDestinationDeptName(String exDestinationDeptName) {
		this.exDestinationDeptName = exDestinationDeptName;
	}
	
}
