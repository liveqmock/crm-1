package com.deppon.crm.module.coupon.shared.domain;

import java.util.Date;
import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

public class CouponForInterface extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//部门标杆编码
	private String deptStandardCode;
	//活动类型
	private String activityType;
	//优惠券生效时间
	private Date beginTime;
	//优惠券失效时间
	private Date endTime;
	//优惠券金额
	private String couponValue;
	//金额抵扣类型    默认为“2” 分级抵扣模式，“1”严格抵扣模式
	private String costMode;
	//金额类型      默认为“FRT” 运费，“BILLING”开单金额
	private String costType;
	//满减金额
	private String value;
	//分级抵扣金额 分级抵扣模式使用
	private String discount;
	//增值费类型	包装、保价、代收、送货、接货费
	private String costAddedType;;
	//增值费金额
	private String costAdded;
	//产品类型
	private String productType;
	//订单来源
	private String orderType;
	//客户等级
	private String custLevel;
	//客户行业
	private String custTrade;
	//线路区域要求 	(1、2、3、4)空、走货线路、发货区域、到达区域
	private String regdemand;
	//走货线路
	private List<GoodsLine> goodsLines;
	//短信内容
	private String smsContent;
	// 优惠券描述
	private String describe;
	
	/**
	 * 
	 * @param getDeptStandardCode : return the property :String
	 */
	public String getDeptStandardCode() {
		return deptStandardCode;
	}
	/**
	 * 
	 * @param setDeptStandardCode : return the property :void
	 */
	public void setDeptStandardCode(String deptStandardCode) {
		this.deptStandardCode = deptStandardCode;
	}
	/**
	 * 
	 * @param getActivityType : return the property :String
	 */
	public String getActivityType() {
		return activityType;
	}
	/**
	 * 
	 * @param setActivityType : return the property :void
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	/**
	 * 
	 * @param getBeginTime : return the property :Date
	 */
	public Date getBeginTime() {
		return beginTime;
	}
	/**
	 * 
	 * @param setBeginTime : return the property :void
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * 
	 * @param getEndTime : return the property :Date
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 
	 * @param setEndTime : return the property :void
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 
	 * @param getCouponValue : return the property :String
	 */
	public String getCouponValue() {
		return couponValue;
	}
	/**
	 * 
	 * @param setCouponValue : return the property :void
	 */
	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}
	/**
	 * 
	 * @param getCostMode : return the property :String
	 */
	public String getCostMode() {
		return costMode;
	}
	public void setCostMode(String costMode) {
		this.costMode = costMode;
	}
	/**
	 * 
	 * @param getCostType : return the property :String
	 */
	public String getCostType() {
		return costType;
	}
	/**
	 * 
	 * @param setCostType : return the property :void
	 */
	public void setCostType(String costType) {
		this.costType = costType;
	}
	/**
	 * 
	 * @param getValue : return the property :String
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 
	 * @param setValue : return the property :void
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 
	 * @param getDiscount : return the property :String
	 */
	public String getDiscount() {
		return discount;
	}
	/**
	 * 
	 * @param setDiscount : return the property :void
	 */
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	/**
	 * 
	 * @param getCostAddedType : return the property :String
	 */
	public String getCostAddedType() {
		return costAddedType;
	}
	/**
	 * 
	 * @param setCostAddedType : return the property :void
	 */
	public void setCostAddedType(String costAddedType) {
		this.costAddedType = costAddedType;
	}
	/**
	 * 
	 * @param getCostAdded : return the property :String
	 */
	public String getCostAdded() {
		return costAdded;
	}
	/**
	 * 
	 * @param setCostAdded : return the property :void
	 */
	public void setCostAdded(String costAdded) {
		this.costAdded = costAdded;
	}
	/**
	 * 
	 * @param getProductType : return the property :String
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * 
	 * @param setProductType : return the property :void
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * 
	 * @param getOrderType : return the property :String
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * 
	 * @param setOrderType : return the property :void
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * 
	 * @param getCustLevel : return the property :String
	 */
	public String getCustLevel() {
		return custLevel;
	}
	/**
	 * 
	 * @param setCustLevel : return the property :void
	 */
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	/**
	 * 
	 * @param getCustTrade : return the property :String
	 */
	public String getCustTrade() {
		return custTrade;
	}
	/**
	 * 
	 * @param setCustTrade : return the property :void
	 */
	public void setCustTrade(String custTrade) {
		this.custTrade = custTrade;
	}
	/**
	 * 
	 * @param getRegdemand : return the property :String
	 */
	public String getRegdemand() {
		return regdemand;
	}
	/**
	 * 
	 * @param setRegdemand : return the property :void
	 */
	public void setRegdemand(String regdemand) {
		this.regdemand = regdemand;
	}
	/**
	 * 
	 * @param getGoodsLines : return the property :List<GoodsLine>
	 */
	public List<GoodsLine> getGoodsLines() {
		return goodsLines;
	}
	/**
	 * 
	 * @param setGoodsLines : return the property :void
	 */
	public void setGoodsLines(List<GoodsLine> goodsLines) {
		this.goodsLines = goodsLines;
	}
	/**
	 * 
	 * @param getSmsContent : return the property :String
	 */
	public String getSmsContent() {
		return smsContent;
	}
	/**
	 * 
	 * @param setSmsContent : return the property :void
	 */
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	/**
	 * 
	 * @param getDescribe : return the property :String
	 */
	public String getDescribe() {
		return describe;
	}
	/**
	 * 
	 * @param setDescribe : return the property :void
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
	
	

}
