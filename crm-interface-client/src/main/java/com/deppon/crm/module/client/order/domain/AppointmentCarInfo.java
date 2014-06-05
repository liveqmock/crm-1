package com.deppon.crm.module.client.order.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 约车信息封装类
 * @author davidcun @2012-3-23
 */
public class AppointmentCarInfo {

	/*
	//订单编号
	private String orderNumber;
	//客户名称
	private String customerName;
	//接货地址
	private String receiveShipmentAddr;
	//订单类型
	private String orderType;
	//订单受理部门(编码)
	private String processDept;
	//订单所属部门(编码)
	private String belongDept;
	//派车部门(编码)
	private String dispatchDept;
	//货物名称
	private String cargoName;
	//重量
	private double weight;
	//体积
	private double volume;
	//包装
	private String packing;
	//件数
	private int pieceAmount;
	//尺寸
	private double size;
	//备注
	private String comment;
	//运输性质(枚举)
	private String transportType;
	//提货方式(枚举)
	private String pickUpGoodsType;
	//提货网点(编码)
	private String pickUpGoodsDept;
	//下单时间
	private Date orderTime;
	//最早接货时间
	private Date earliestReceive;
	//最晚接货时间
	private Date latestReceive;
	//A、B货，为空或者""
	private String cargoType;
	//用车部门
	private String deptOfUseCar;
	*/
	// 会员类型
		private String memberType;
	//客户名称
	private String custName;
	//联系方式
	private String custTel;
	// 接货省
	private String custProvince;
	// 接货市
	private String custCity;
	// 接货区
	private String custArea;
	//接货地址
	private String custAddress;
	//收货地址
	private String custArrAddress;
	//货物名称
	private String goodsName;
	//重量
	private BigDecimal weight;
	//体积
	private BigDecimal cubage;
	//包装
	private String packing;
	//件数
	private Integer pieces;
	//尺寸
	private String size;
	//备注
	private String remark;
	//运输性质 
	/*
	 * 精准汽运（长途）LRF
	 * 精准卡航 FLF
	 * 精准汽运（短途）SRF
	 * 精准城运 FSF
	 * 汽运偏线 PLF
	 * 精准空运 AF
	 * 整车 WVH
	 */
	private String transProperty;
	
	//提货方式取值：
	/*PICKSELF(自提),
	 *PICKFOOR(送货上门),
	 *PICKONAIEPORT 机场自提,
	 *PICKUPSTAIRS 送货上楼,
	 *PICKNOTUPSTAIRS 送货(不含上楼)
	 */
	private String deliverMode;
	//部门标杆编码
	private String deptCode;
	
	//货物类型(由于erp中VehReservation实体此属性为String，所以此处也为String)
	private String goodsType;
	// 用车车型
	private String vehicleType;
	//用车部门编码
	private String usingVehicleDeptNum;
	//用车部门id（此值不设置）
	private String usingVehicleDeptId;
	//最早接货时间
	private Date firstPickTime;
	//最晚接货事件
	private Date lastPickTime;
	//派车车队(标杆编码)
	private String deliveryVehNum;
	//派送车车队id;
	private String deliveryVehId;
	//订单号
	private String orderNumber;
	//下单时间
	private Date orderedTime;
	//订单类型(由于erp中VehReservation实体此属性为String，所以此处也为String)
	private String orderType;
	//订单受理部门(约车部门标杆编码)
	private String vehDeptNum;
	//订单受理部门(约车部门)ID,此值不设置
	private String vehDeptId;
	
	//当前操作人工号（必填）
	private String creatorNum;
	
	//当前操作人id（只需传工号，此处不需要传值）
	private String creatorId;
	// 客户手机号
	private String custMobile;
	//优惠券编码
	private String couponNumber;
	//运单号
	private String waybillNumber;
	//开单付款方式
	private String paidMethod;
	
	/**
	 * Description:paidMethod<br />
	 * @author CoCo
	 * @version 0.1 2013-11-12
	 */
	public String getPaidMethod() {
		return paidMethod;
	}
	/**
	 * Description:paidMethod<br />
	 * @author CoCo
	 * @version 0.1 2013-11-12
	 */
	public void setPaidMethod(String paidMethod) {
		this.paidMethod = paidMethod;
	}
	public String getCustProvince() {
		return custProvince;
	}
	public void setCustProvince(String custProvince) {
		this.custProvince = custProvince;
	}
	public String getCustCity() {
		return custCity;
	}
	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}
	public String getCustArea() {
		return custArea;
	}
	public void setCustArea(String custArea) {
		this.custArea = custArea;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getCustMobile() {
		return custMobile;
	}
	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustTel() {
		return custTel;
	}
	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public BigDecimal getCubage() {
		return cubage;
	}
	public void setCubage(BigDecimal cubage) {
		this.cubage = cubage;
	}
	public String getPacking() {
		return packing;
	}
	public void setPacking(String packing) {
		this.packing = packing;
	}
	public Integer getPieces() {
		return pieces;
	}
	public void setPieces(Integer pieces) {
		this.pieces = pieces;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTransProperty() {
		return transProperty;
	}
	public void setTransProperty(String transProperty) {
		this.transProperty = transProperty;
	}
	public String getDeliverMode() {
		return deliverMode;
	}
	public void setDeliverMode(String deliverMode) {
		this.deliverMode = deliverMode;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getUsingVehicleDeptNum() {
		return usingVehicleDeptNum;
	}
	public void setUsingVehicleDeptNum(String usingVehicleDeptNum) {
		this.usingVehicleDeptNum = usingVehicleDeptNum;
	}
	public String getUsingVehicleDeptId() {
		return usingVehicleDeptId;
	}
	public void setUsingVehicleDeptId(String usingVehicleDeptId) {
		this.usingVehicleDeptId = usingVehicleDeptId;
	}
	public Date getFirstPickTime() {
		return firstPickTime;
	}
	public void setFirstPickTime(Date firstPickTime) {
		this.firstPickTime = firstPickTime;
	}
	public Date getLastPickTime() {
		return lastPickTime;
	}
	public void setLastPickTime(Date lastPickTime) {
		this.lastPickTime = lastPickTime;
	}
	public String getDeliveryVehNum() {
		return deliveryVehNum;
	}
	public void setDeliveryVehNum(String deliveryVehNum) {
		this.deliveryVehNum = deliveryVehNum;
	}
	public String getDeliveryVehId() {
		return deliveryVehId;
	}
	public void setDeliveryVehId(String deliveryVehId) {
		this.deliveryVehId = deliveryVehId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Date getOrderedTime() {
		return orderedTime;
	}
	public void setOrderedTime(Date orderedTime) {
		this.orderedTime = orderedTime;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getVehDeptNum() {
		return vehDeptNum;
	}
	public void setVehDeptNum(String vehDeptNum) {
		this.vehDeptNum = vehDeptNum;
	}
	public String getVehDeptId() {
		return vehDeptId;
	}
	public void setVehDeptId(String vehDeptId) {
		this.vehDeptId = vehDeptId;
	}
	public String getCreatorNum() {
		return creatorNum;
	}
	public void setCreatorNum(String creatorNum) {
		this.creatorNum = creatorNum;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getCouponNumber() {
		return couponNumber;
	}
	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}
	public String getWaybillNumber() {
		return waybillNumber;
	}
	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}
		public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getCustArrAddress() {
		return custArrAddress;
	}
	public void setCustArrAddress(String custArrAddress) {
		this.custArrAddress = custArrAddress;
	}

}
