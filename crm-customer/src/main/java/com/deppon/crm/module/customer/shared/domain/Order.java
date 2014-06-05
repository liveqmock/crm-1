/**
 * @description
 * @author 赵斌
 * @2012-4-25
 * @return
 */
package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @author Administrator
 * 
 */
public class Order extends BaseEntity {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 5695371687588381164L;

	// 催单次数
	private Integer hastenCount;
	// 最后催单时间
	private Date lastHastenTime;
	// 联系人id
	private Integer contactManId;
	// 订单号
	private String orderNumber;
	// 渠道单号
	private String channelNumber;
	// 发货客户id
	private String shipperId;
	// 发货客户编号
	private String shipperNumber;
	// 发货客户名称
	private String shipperName;
	// 发货联系人名称
	private String contactName;
	// 联系人手机
	private String contactMobile;
	// 联系人电话
	private String contactPhone;
	// 联系人省份
	private String contactProvince;
	// 联系人城市
	private String contactCity;
	// 联系人区县
	private String contactArea;
	// 联系地址
	private String contactAddress;
	// 是否接货
	private Boolean isReceiveGoods;
	// 接货起始时间
	private Date beginAcceptTime;
	// 接货结束时间
	private Date endAcceptTime;
	// // 是否与接货联系人一致
	// private String isSameName;
	// 收货客户id
	private String receiverCustId;
	// 收货客户编号
	private String receiverCustNumber;
	// 收货客户所属公司
	private String receiverCustcompany;
	// 收货人联系姓名
	private String receiverCustName;
	// 接货人联系手机
	private String receiverCustMobile;
	// 接货人联系电话
	private String receiverCustPhone;
	// 接货人省份
	private String receiverCustProvince;
	// 接货人城市
	private String receiverCustCity;
	// 接货人区县
	private String receiverCustArea;
	// 接货人详细地址
	private String receiverCustAddress;
	// 是否短信通知
	private Boolean isSendmessage;
	// 到达网点
	private String receivingToPoint;
	// 货物运输方式
	private String transportMode;
	// 货物名称
	private String goodsName;
	// 货物包装类型
	private String packing;
	// 货物类型
	private String goodsType;
	// 托运货物总件数
	private Integer goodsNumber;
	// 托运货物总重量
	private Double totalWeight;
	// 货物总体积
	private Double totalVolume;
	// 付款方式
	private String paymentType;
	// 渠道类型
	private String channelType;
	// 起到客户Id
	private String channelCustId;
	// 提货方式
	private String deliveryMode;
	// 代收货款类型
	private String reciveLoanType;
	// 代收货款金额
	private Double reviceMoneyAmount;
	// 报价申明价值
	private Double insuredAmount;
	// 下单时间
	private Date orderTime;
	// 始发网点
	private String startStation;
	// 始发网点名称
	private String startStationName;
	// 受理部门
	private String acceptDept;
	// 受理部门名称
	private String acceptDeptName;
	// 订单状态
	private String orderStatus;
	// 受理人
	private String dealPerson;
	// 订单受理时间
	private Date orderAcceptTime;
	// 接货员姓名
	private String receiver;
	// 接货员手机
	private String accepterPersonMobile;
	// 运单号
	private String waybillNumber;
	// 订单来源
	private String resource;
	// 储运事项
	private String transNote;
	// 签收单
	private String returnBillType;
	// 下单人
	private String orderPerson;
	// 反馈信息
	private String feedbackInfo;

	// 订单操作记录
	private Set<OrderOperationLog> operationLogs = new HashSet<OrderOperationLog>();
	
	/**
	 * <p>
	 * Description:hastenCount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Integer getHastenCount() {
		return hastenCount;
	}

	/**
	 * <p>
	 * Description:hastenCount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setHastenCount(Integer hastenCount) {
		this.hastenCount = hastenCount;
	}

	/**
	 * <p>
	 * Description:lastHastenTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getLastHastenTime() {
		return lastHastenTime;
	}

	/**
	 * <p>
	 * Description:lastHastenTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLastHastenTime(Date lastHastenTime) {
		this.lastHastenTime = lastHastenTime;
	}

	/**
	 * <p>
	 * Description:contactManId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Integer getContactManId() {
		return contactManId;
	}

	/**
	 * <p>
	 * Description:contactManId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactManId(Integer contactManId) {
		this.contactManId = contactManId;
	}

	/**
	 * <p>
	 * Description:orderNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * <p>
	 * Description:orderNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * <p>
	 * Description:channelNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getChannelNumber() {
		return channelNumber;
	}

	/**
	 * <p>
	 * Description:channelNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setChannelNumber(String channelNumber) {
		this.channelNumber = channelNumber;
	}

	/**
	 * <p>
	 * Description:shipperId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getShipperId() {
		return shipperId;
	}

	/**
	 * <p>
	 * Description:shipperId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setShipperId(String shipperId) {
		this.shipperId = shipperId;
	}

	/**
	 * <p>
	 * Description:shipperNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getShipperNumber() {
		return shipperNumber;
	}

	/**
	 * <p>
	 * Description:shipperNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setShipperNumber(String shipperNumber) {
		this.shipperNumber = shipperNumber;
	}

	/**
	 * <p>
	 * Description:shipperName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getShipperName() {
		return shipperName;
	}

	/**
	 * <p>
	 * Description:shipperName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * <p>
	 * Description:contactMobile<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactMobile() {
		return contactMobile;
	}

	/**
	 * <p>
	 * Description:contactMobile<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	/**
	 * <p>
	 * Description:contactPhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * <p>
	 * Description:contactPhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/**
	 * <p>
	 * Description:contactProvince<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactProvince() {
		return contactProvince;
	}

	/**
	 * <p>
	 * Description:contactProvince<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactProvince(String contactProvince) {
		this.contactProvince = contactProvince;
	}

	/**
	 * <p>
	 * Description:contactCity<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactCity() {
		return contactCity;
	}

	/**
	 * <p>
	 * Description:contactCity<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactCity(String contactCity) {
		this.contactCity = contactCity;
	}

	/**
	 * <p>
	 * Description:contactArea<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactArea() {
		return contactArea;
	}

	/**
	 * <p>
	 * Description:contactArea<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactArea(String contactArea) {
		this.contactArea = contactArea;
	}

	/**
	 * <p>
	 * Description:contactAddress<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactAddress() {
		return contactAddress;
	}

	/**
	 * <p>
	 * Description:contactAddress<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	/**
	 * <p>
	 * Description:isReceiveGoods<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIsReceiveGoods() {
		return isReceiveGoods;
	}

	/**
	 * <p>
	 * Description:isReceiveGoods<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIsReceiveGoods(Boolean isReceiveGoods) {
		this.isReceiveGoods = isReceiveGoods;
	}

	/**
	 * <p>
	 * Description:beginAcceptTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getBeginAcceptTime() {
		return beginAcceptTime;
	}

	/**
	 * <p>
	 * Description:beginAcceptTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBeginAcceptTime(Date beginAcceptTime) {
		this.beginAcceptTime = beginAcceptTime;
	}

	/**
	 * <p>
	 * Description:endAcceptTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getEndAcceptTime() {
		return endAcceptTime;
	}

	/**
	 * <p>
	 * Description:endAcceptTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setEndAcceptTime(Date endAcceptTime) {
		this.endAcceptTime = endAcceptTime;
	}

	/**
	 * <p>
	 * Description:receiverCustId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getReceiverCustId() {
		return receiverCustId;
	}

	/**
	 * <p>
	 * Description:receiverCustId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReceiverCustId(String receiverCustId) {
		this.receiverCustId = receiverCustId;
	}

	/**
	 * <p>
	 * Description:receiverCustNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getReceiverCustNumber() {
		return receiverCustNumber;
	}

	/**
	 * <p>
	 * Description:receiverCustNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReceiverCustNumber(String receiverCustNumber) {
		this.receiverCustNumber = receiverCustNumber;
	}

	/**
	 * <p>
	 * Description:receiverCustcompany<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getReceiverCustcompany() {
		return receiverCustcompany;
	}

	/**
	 * <p>
	 * Description:receiverCustcompany<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReceiverCustcompany(String receiverCustcompany) {
		this.receiverCustcompany = receiverCustcompany;
	}

	/**
	 * <p>
	 * Description:receiverCustName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getReceiverCustName() {
		return receiverCustName;
	}

	/**
	 * <p>
	 * Description:receiverCustName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReceiverCustName(String receiverCustName) {
		this.receiverCustName = receiverCustName;
	}

	/**
	 * <p>
	 * Description:receiverCustMobile<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getReceiverCustMobile() {
		return receiverCustMobile;
	}

	/**
	 * <p>
	 * Description:receiverCustMobile<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReceiverCustMobile(String receiverCustMobile) {
		this.receiverCustMobile = receiverCustMobile;
	}

	/**
	 * <p>
	 * Description:receiverCustPhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getReceiverCustPhone() {
		return receiverCustPhone;
	}

	/**
	 * <p>
	 * Description:receiverCustPhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReceiverCustPhone(String receiverCustPhone) {
		this.receiverCustPhone = receiverCustPhone;
	}

	/**
	 * <p>
	 * Description:receiverCustProvince<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getReceiverCustProvince() {
		return receiverCustProvince;
	}

	/**
	 * <p>
	 * Description:receiverCustProvince<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReceiverCustProvince(String receiverCustProvince) {
		this.receiverCustProvince = receiverCustProvince;
	}

	/**
	 * <p>
	 * Description:receiverCustCity<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getReceiverCustCity() {
		return receiverCustCity;
	}

	/**
	 * <p>
	 * Description:receiverCustCity<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReceiverCustCity(String receiverCustCity) {
		this.receiverCustCity = receiverCustCity;
	}

	/**
	 * <p>
	 * Description:receiverCustArea<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getReceiverCustArea() {
		return receiverCustArea;
	}

	/**
	 * <p>
	 * Description:receiverCustArea<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReceiverCustArea(String receiverCustArea) {
		this.receiverCustArea = receiverCustArea;
	}

	/**
	 * <p>
	 * Description:receiverCustAddress<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getReceiverCustAddress() {
		return receiverCustAddress;
	}

	/**
	 * <p>
	 * Description:receiverCustAddress<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReceiverCustAddress(String receiverCustAddress) {
		this.receiverCustAddress = receiverCustAddress;
	}

	/**
	 * <p>
	 * Description:isSendmessage<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIsSendmessage() {
		return isSendmessage;
	}

	/**
	 * <p>
	 * Description:isSendmessage<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIsSendmessage(Boolean isSendmessage) {
		this.isSendmessage = isSendmessage;
	}

	/**
	 * <p>
	 * Description:receivingToPoint<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getReceivingToPoint() {
		return receivingToPoint;
	}

	/**
	 * <p>
	 * Description:receivingToPoint<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReceivingToPoint(String receivingToPoint) {
		this.receivingToPoint = receivingToPoint;
	}

	/**
	 * <p>
	 * Description:transportMode<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTransportMode() {
		return transportMode;
	}

	/**
	 * <p>
	 * Description:transportMode<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	/**
	 * <p>
	 * Description:goodsName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * <p>
	 * Description:goodsName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * <p>
	 * Description:packing<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getPacking() {
		return packing;
	}

	/**
	 * <p>
	 * Description:packing<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPacking(String packing) {
		this.packing = packing;
	}

	/**
	 * <p>
	 * Description:goodsType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getGoodsType() {
		return goodsType;
	}

	/**
	 * <p>
	 * Description:goodsType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	/**
	 * <p>
	 * Description:goodsNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Integer getGoodsNumber() {
		return goodsNumber;
	}

	/**
	 * <p>
	 * Description:goodsNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	/**
	 * <p>
	 * Description:totalWeight<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getTotalWeight() {
		return totalWeight;
	}

	/**
	 * <p>
	 * Description:totalWeight<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	/**
	 * <p>
	 * Description:totalVolume<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getTotalVolume() {
		return totalVolume;
	}

	/**
	 * <p>
	 * Description:totalVolume<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTotalVolume(Double totalVolume) {
		this.totalVolume = totalVolume;
	}

	/**
	 * <p>
	 * Description:paymentType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * <p>
	 * Description:paymentType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * <p>
	 * Description:channelType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getChannelType() {
		return channelType;
	}

	/**
	 * <p>
	 * Description:channelType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	/**
	 * <p>
	 * Description:channelCustId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getChannelCustId() {
		return channelCustId;
	}

	/**
	 * <p>
	 * Description:channelCustId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setChannelCustId(String channelCustId) {
		this.channelCustId = channelCustId;
	}

	/**
	 * <p>
	 * Description:deliveryMode<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDeliveryMode() {
		return deliveryMode;
	}

	/**
	 * <p>
	 * Description:deliveryMode<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	/**
	 * <p>
	 * Description:reciveLoanType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getReciveLoanType() {
		return reciveLoanType;
	}

	/**
	 * <p>
	 * Description:reciveLoanType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReciveLoanType(String reciveLoanType) {
		this.reciveLoanType = reciveLoanType;
	}

	/**
	 * <p>
	 * Description:reviceMoneyAmount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getReviceMoneyAmount() {
		return reviceMoneyAmount;
	}

	/**
	 * <p>
	 * Description:reviceMoneyAmount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReviceMoneyAmount(Double reviceMoneyAmount) {
		this.reviceMoneyAmount = reviceMoneyAmount;
	}

	/**
	 * <p>
	 * Description:insuredAmount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Double getInsuredAmount() {
		return insuredAmount;
	}

	/**
	 * <p>
	 * Description:insuredAmount<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setInsuredAmount(Double insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	/**
	 * <p>
	 * Description:orderTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getOrderTime() {
		return orderTime;
	}

	/**
	 * <p>
	 * Description:orderTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * <p>
	 * Description:startStation<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getStartStation() {
		return startStation;
	}

	/**
	 * <p>
	 * Description:startStation<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	/**
	 * <p>
	 * Description:startStationName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getStartStationName() {
		return startStationName;
	}

	/**
	 * <p>
	 * Description:startStationName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setStartStationName(String startStationName) {
		this.startStationName = startStationName;
	}

	/**
	 * <p>
	 * Description:acceptDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getAcceptDept() {
		return acceptDept;
	}

	/**
	 * <p>
	 * Description:acceptDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAcceptDept(String acceptDept) {
		this.acceptDept = acceptDept;
	}

	/**
	 * <p>
	 * Description:acceptDeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getAcceptDeptName() {
		return acceptDeptName;
	}

	/**
	 * <p>
	 * Description:acceptDeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAcceptDeptName(String acceptDeptName) {
		this.acceptDeptName = acceptDeptName;
	}

	/**
	 * <p>
	 * Description:orderStatus<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * <p>
	 * Description:orderStatus<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * <p>
	 * Description:dealPerson<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDealPerson() {
		return dealPerson;
	}

	/**
	 * <p>
	 * Description:dealPerson<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDealPerson(String dealPerson) {
		this.dealPerson = dealPerson;
	}

	/**
	 * <p>
	 * Description:orderAcceptTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getOrderAcceptTime() {
		return orderAcceptTime;
	}

	/**
	 * <p>
	 * Description:orderAcceptTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOrderAcceptTime(Date orderAcceptTime) {
		this.orderAcceptTime = orderAcceptTime;
	}

	/**
	 * <p>
	 * Description:receiver<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getReceiver() {
		return receiver;
	}

	/**
	 * <p>
	 * Description:receiver<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	/**
	 * <p>
	 * Description:accepterPersonMobile<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getAccepterPersonMobile() {
		return accepterPersonMobile;
	}

	/**
	 * <p>
	 * Description:accepterPersonMobile<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAccepterPersonMobile(String accepterPersonMobile) {
		this.accepterPersonMobile = accepterPersonMobile;
	}

	/**
	 * <p>
	 * Description:waybillNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getWaybillNumber() {
		return waybillNumber;
	}

	/**
	 * <p>
	 * Description:waybillNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	/**
	 * <p>
	 * Description:resource<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * <p>
	 * Description:resource<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}

	/**
	 * <p>
	 * Description:transNote<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTransNote() {
		return transNote;
	}

	/**
	 * <p>
	 * Description:transNote<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTransNote(String transNote) {
		this.transNote = transNote;
	}

	/**
	 * <p>
	 * Description:returnBillType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getReturnBillType() {
		return returnBillType;
	}

	/**
	 * <p>
	 * Description:returnBillType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReturnBillType(String returnBillType) {
		this.returnBillType = returnBillType;
	}

	/**
	 * <p>
	 * Description:orderPerson<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getOrderPerson() {
		return orderPerson;
	}

	/**
	 * <p>
	 * Description:orderPerson<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOrderPerson(String orderPerson) {
		this.orderPerson = orderPerson;
	}

	/**
	 * <p>
	 * Description:feedbackInfo<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getFeedbackInfo() {
		return feedbackInfo;
	}

	/**
	 * <p>
	 * Description:feedbackInfo<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setFeedbackInfo(String feedbackInfo) {
		this.feedbackInfo = feedbackInfo;
	}

	/**
	 * <p>
	 * Description:operationLogs<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Set<OrderOperationLog> getOperationLogs() {
		return operationLogs;
	}

	/**
	 * <p>
	 * Description:operationLogs<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOperationLogs(Set<OrderOperationLog> operationLogs) {
		this.operationLogs = operationLogs;
	}

	/**
	 * <p>
	 * Description:serialversionuid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((acceptDept == null) ? 0 : acceptDept.hashCode());
		result = prime
				* result
				+ ((accepterPersonMobile == null) ? 0 : accepterPersonMobile
						.hashCode());
		result = prime * result
				+ ((beginAcceptTime == null) ? 0 : beginAcceptTime.hashCode());
		result = prime * result
				+ ((channelCustId == null) ? 0 : channelCustId.hashCode());
		result = prime * result
				+ ((channelNumber == null) ? 0 : channelNumber.hashCode());
		result = prime * result
				+ ((channelType == null) ? 0 : channelType.hashCode());
		result = prime * result
				+ ((contactAddress == null) ? 0 : contactAddress.hashCode());
		result = prime * result
				+ ((contactArea == null) ? 0 : contactArea.hashCode());
		result = prime * result
				+ ((contactCity == null) ? 0 : contactCity.hashCode());
		result = prime * result
				+ ((contactManId == null) ? 0 : contactManId.hashCode());
		result = prime * result
				+ ((contactMobile == null) ? 0 : contactMobile.hashCode());
		result = prime * result
				+ ((contactName == null) ? 0 : contactName.hashCode());
		result = prime * result
				+ ((contactPhone == null) ? 0 : contactPhone.hashCode());
		result = prime * result
				+ ((contactProvince == null) ? 0 : contactProvince.hashCode());
		result = prime * result
				+ ((dealPerson == null) ? 0 : dealPerson.hashCode());
		result = prime * result
				+ ((deliveryMode == null) ? 0 : deliveryMode.hashCode());
		result = prime * result
				+ ((endAcceptTime == null) ? 0 : endAcceptTime.hashCode());
		result = prime * result
				+ ((feedbackInfo == null) ? 0 : feedbackInfo.hashCode());
		result = prime * result
				+ ((goodsName == null) ? 0 : goodsName.hashCode());
		result = prime * result
				+ ((goodsNumber == null) ? 0 : goodsNumber.hashCode());
		result = prime * result
				+ ((goodsType == null) ? 0 : goodsType.hashCode());
		result = prime * result
				+ ((hastenCount == null) ? 0 : hastenCount.hashCode());
		result = prime * result
				+ ((insuredAmount == null) ? 0 : insuredAmount.hashCode());
		result = prime * result
				+ ((isReceiveGoods == null) ? 0 : isReceiveGoods.hashCode());
		result = prime * result
				+ ((isSendmessage == null) ? 0 : isSendmessage.hashCode());
		result = prime * result
				+ ((lastHastenTime == null) ? 0 : lastHastenTime.hashCode());
		result = prime * result
				+ ((operationLogs == null) ? 0 : operationLogs.hashCode());
		result = prime * result
				+ ((orderAcceptTime == null) ? 0 : orderAcceptTime.hashCode());
		result = prime * result
				+ ((orderNumber == null) ? 0 : orderNumber.hashCode());
		result = prime * result
				+ ((orderPerson == null) ? 0 : orderPerson.hashCode());
		result = prime * result
				+ ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result
				+ ((orderTime == null) ? 0 : orderTime.hashCode());
		result = prime * result + ((packing == null) ? 0 : packing.hashCode());
		result = prime * result
				+ ((paymentType == null) ? 0 : paymentType.hashCode());
		result = prime * result
				+ ((receiver == null) ? 0 : receiver.hashCode());
		result = prime
				* result
				+ ((receiverCustAddress == null) ? 0 : receiverCustAddress
						.hashCode());
		result = prime
				* result
				+ ((receiverCustArea == null) ? 0 : receiverCustArea.hashCode());
		result = prime
				* result
				+ ((receiverCustCity == null) ? 0 : receiverCustCity.hashCode());
		result = prime * result
				+ ((receiverCustId == null) ? 0 : receiverCustId.hashCode());
		result = prime
				* result
				+ ((receiverCustMobile == null) ? 0 : receiverCustMobile
						.hashCode());
		result = prime
				* result
				+ ((receiverCustName == null) ? 0 : receiverCustName.hashCode());
		result = prime
				* result
				+ ((receiverCustNumber == null) ? 0 : receiverCustNumber
						.hashCode());
		result = prime
				* result
				+ ((receiverCustPhone == null) ? 0 : receiverCustPhone
						.hashCode());
		result = prime
				* result
				+ ((receiverCustProvince == null) ? 0 : receiverCustProvince
						.hashCode());
		result = prime * result
				+ ((acceptDeptName == null) ? 0 : acceptDeptName.hashCode());
		result = prime
				* result
				+ ((receiverCustcompany == null) ? 0 : receiverCustcompany
						.hashCode());
		result = prime
				* result
				+ ((receivingToPoint == null) ? 0 : receivingToPoint.hashCode());
		result = prime * result
				+ ((reciveLoanType == null) ? 0 : reciveLoanType.hashCode());
		result = prime * result
				+ ((resource == null) ? 0 : resource.hashCode());
		result = prime * result
				+ ((returnBillType == null) ? 0 : returnBillType.hashCode());
		result = prime
				* result
				+ ((reviceMoneyAmount == null) ? 0 : reviceMoneyAmount
						.hashCode());
		result = prime * result
				+ ((shipperId == null) ? 0 : shipperId.hashCode());
		result = prime * result
				+ ((shipperName == null) ? 0 : shipperName.hashCode());
		result = prime * result
				+ ((shipperNumber == null) ? 0 : shipperNumber.hashCode());
		result = prime * result
				+ ((startStation == null) ? 0 : startStation.hashCode());
		result = prime * result
				+ ((totalVolume == null) ? 0 : totalVolume.hashCode());
		result = prime * result
				+ ((totalWeight == null) ? 0 : totalWeight.hashCode());
		result = prime * result
				+ ((transNote == null) ? 0 : transNote.hashCode());
		result = prime * result
				+ ((transportMode == null) ? 0 : transportMode.hashCode());
		result = prime * result
				+ ((waybillNumber == null) ? 0 : waybillNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Order other = (Order) obj;
		if (acceptDept == null) {
			if (other.acceptDept != null) {
				return false;
			}
		} else if (!acceptDept.equals(other.acceptDept)) {
			return false;
		}
		if (accepterPersonMobile == null) {
			if (other.accepterPersonMobile != null) {
				return false;
			}
		} else if (!accepterPersonMobile.equals(other.accepterPersonMobile)) {
			return false;
		}
		if (beginAcceptTime == null) {
			if (other.beginAcceptTime != null) {
				return false;
			}
		} else if (!beginAcceptTime.equals(other.beginAcceptTime)) {
			return false;
		}
		if (channelCustId == null) {
			if (other.channelCustId != null) {
				return false;
			}
		} else if (!channelCustId.equals(other.channelCustId)) {
			return false;
		}
		if (channelNumber == null) {
			if (other.channelNumber != null) {
				return false;
			}
		} else if (!channelNumber.equals(other.channelNumber)) {
			return false;
		}
		if (channelType == null) {
			if (other.channelType != null) {
				return false;
			}
		} else if (!channelType.equals(other.channelType)) {
			return false;
		}
		if (contactAddress == null) {
			if (other.contactAddress != null) {
				return false;
			}
		} else if (!contactAddress.equals(other.contactAddress)) {
			return false;
		}
		if (contactArea == null) {
			if (other.contactArea != null) {
				return false;
			}
		} else if (!contactArea.equals(other.contactArea)) {
			return false;
		}
		if (contactCity == null) {
			if (other.contactCity != null) {
				return false;
			}
		} else if (!contactCity.equals(other.contactCity)) {
			return false;
		}
		if (contactManId == null) {
			if (other.contactManId != null) {
				return false;
			}
		} else if (!contactManId.equals(other.contactManId)) {
			return false;
		}
		if (contactMobile == null) {
			if (other.contactMobile != null) {
				return false;
			}
		} else if (!contactMobile.equals(other.contactMobile)) {
			return false;
		}
		if (contactName == null) {
			if (other.contactName != null) {
				return false;
			}
		} else if (!contactName.equals(other.contactName)) {
			return false;
		}
		if (contactPhone == null) {
			if (other.contactPhone != null) {
				return false;
			}
		} else if (!contactPhone.equals(other.contactPhone)) {
			return false;
		}
		if (contactProvince == null) {
			if (other.contactProvince != null) {
				return false;
			}
		} else if (!contactProvince.equals(other.contactProvince)) {
			return false;
		}
		if (dealPerson == null) {
			if (other.dealPerson != null) {
				return false;
			}
		} else if (!dealPerson.equals(other.dealPerson)) {
			return false;
		}
		if (deliveryMode == null) {
			if (other.deliveryMode != null) {
				return false;
			}
		} else if (!deliveryMode.equals(other.deliveryMode)) {
			return false;
		}
		if (endAcceptTime == null) {
			if (other.endAcceptTime != null) {
				return false;
			}
		} else if (!endAcceptTime.equals(other.endAcceptTime)) {
			return false;
		}
		if (feedbackInfo == null) {
			if (other.feedbackInfo != null) {
				return false;
			}
		} else if (!feedbackInfo.equals(other.feedbackInfo)) {
			return false;
		}
		if (goodsName == null) {
			if (other.goodsName != null) {
				return false;
			}
		} else if (!goodsName.equals(other.goodsName)) {
			return false;
		}
		if (goodsNumber == null) {
			if (other.goodsNumber != null) {
				return false;
			}
		} else if (!goodsNumber.equals(other.goodsNumber)) {
			return false;
		}
		if (goodsType == null) {
			if (other.goodsType != null) {
				return false;
			}
		} else if (!goodsType.equals(other.goodsType)) {
			return false;
		}
		if (hastenCount == null) {
			if (other.hastenCount != null) {
				return false;
			}
		} else if (!hastenCount.equals(other.hastenCount)) {
			return false;
		}
		if (insuredAmount == null) {
			if (other.insuredAmount != null) {
				return false;
			}
		} else if (!insuredAmount.equals(other.insuredAmount)) {
			return false;
		}
		if (isReceiveGoods == null) {
			if (other.isReceiveGoods != null) {
				return false;
			}
		} else if (!isReceiveGoods.equals(other.isReceiveGoods)) {
			return false;
		}
		if (isSendmessage == null) {
			if (other.isSendmessage != null) {
				return false;
			}
		} else if (!isSendmessage.equals(other.isSendmessage)) {
			return false;
		}
		if (lastHastenTime == null) {
			if (other.lastHastenTime != null) {
				return false;
			}
		} else if (!lastHastenTime.equals(other.lastHastenTime)) {
			return false;
		}
		if (orderAcceptTime == null) {
			if (other.orderAcceptTime != null) {
				return false;
			}
		} else if (!orderAcceptTime.equals(other.orderAcceptTime)) {
			return false;
		}
		if (orderNumber == null) {
			if (other.orderNumber != null) {
				return false;
			}
		} else if (!orderNumber.equals(other.orderNumber)) {
			return false;
		}
		if (orderPerson == null) {
			if (other.orderPerson != null) {
				return false;
			}
		} else if (!orderPerson.equals(other.orderPerson)) {
			return false;
		}
		if (orderStatus == null) {
			if (other.orderStatus != null) {
				return false;
			}
		} else if (!orderStatus.equals(other.orderStatus)) {
			return false;
		}
		if (orderTime == null) {
			if (other.orderTime != null) {
				return false;
			}
		} else if (!orderTime.equals(other.orderTime)) {
			return false;
		}
		if (packing == null) {
			if (other.packing != null) {
				return false;
			}
		} else if (!packing.equals(other.packing)) {
			return false;
		}
		if (paymentType == null) {
			if (other.paymentType != null) {
				return false;
			}
		} else if (!paymentType.equals(other.paymentType)) {
			return false;
		}
		if (receiver == null) {
			if (other.receiver != null) {
				return false;
			}
		} else if (!receiver.equals(other.receiver)) {
			return false;
		}
		if (receiverCustAddress == null) {
			if (other.receiverCustAddress != null) {
				return false;
			}
		} else if (!receiverCustAddress.equals(other.receiverCustAddress)) {
			return false;
		}
		if (receiverCustArea == null) {
			if (other.receiverCustArea != null) {
				return false;
			}
		} else if (!receiverCustArea.equals(other.receiverCustArea)) {
			return false;
		}
		if (receiverCustCity == null) {
			if (other.receiverCustCity != null) {
				return false;
			}
		} else if (!receiverCustCity.equals(other.receiverCustCity)) {
			return false;
		}
		if (receiverCustId == null) {
			if (other.receiverCustId != null) {
				return false;
			}
		} else if (!receiverCustId.equals(other.receiverCustId)) {
			return false;
		}
		if (receiverCustMobile == null) {
			if (other.receiverCustMobile != null) {
				return false;
			}
		} else if (!receiverCustMobile.equals(other.receiverCustMobile)) {
			return false;
		}
		if (receiverCustName == null) {
			if (other.receiverCustName != null) {
				return false;
			}
		} else if (!receiverCustName.equals(other.receiverCustName)) {
			return false;
		}
		if (receiverCustNumber == null) {
			if (other.receiverCustNumber != null) {
				return false;
			}
		} else if (!receiverCustNumber.equals(other.receiverCustNumber)) {
			return false;
		}
		if (receiverCustPhone == null) {
			if (other.receiverCustPhone != null) {
				return false;
			}
		} else if (!receiverCustPhone.equals(other.receiverCustPhone)) {
			return false;
		}
		if (receiverCustProvince == null) {
			if (other.receiverCustProvince != null) {
				return false;
			}
		} else if (!receiverCustProvince.equals(other.receiverCustProvince)) {
			return false;
		}
		if (receiverCustcompany == null) {
			if (other.receiverCustcompany != null) {
				return false;
			}
		} else if (!receiverCustcompany.equals(other.receiverCustcompany)) {
			return false;
		}
		if (receivingToPoint == null) {
			if (other.receivingToPoint != null) {
				return false;
			}
		} else if (!receivingToPoint.equals(other.receivingToPoint)) {
			return false;
		}
		if (reciveLoanType == null) {
			if (other.reciveLoanType != null) {
				return false;
			}
		} else if (!reciveLoanType.equals(other.reciveLoanType)) {
			return false;
		}
		if (resource == null) {
			if (other.resource != null) {
				return false;
			}
		} else if (!resource.equals(other.resource)) {
			return false;
		}
		if (returnBillType == null) {
			if (other.returnBillType != null) {
				return false;
			}
		} else if (!returnBillType.equals(other.returnBillType)) {
			return false;
		}
		if (reviceMoneyAmount == null) {
			if (other.reviceMoneyAmount != null) {
				return false;
			}
		} else if (!reviceMoneyAmount.equals(other.reviceMoneyAmount)) {
			return false;
		}
		if (shipperId == null) {
			if (other.shipperId != null) {
				return false;
			}
		} else if (!shipperId.equals(other.shipperId)) {
			return false;
		}
		if (shipperName == null) {
			if (other.shipperName != null) {
				return false;
			}
		} else if (!shipperName.equals(other.shipperName)) {
			return false;
		}
		if (shipperNumber == null) {
			if (other.shipperNumber != null) {
				return false;
			}
		} else if (!shipperNumber.equals(other.shipperNumber)) {
			return false;
		}
		if (acceptDeptName == null) {
			if (other.acceptDeptName != null) {
				return false;
			}
		} else if (!acceptDeptName.equals(other.acceptDeptName)) {
			return false;
		}
		if (startStation == null) {
			if (other.startStation != null) {
				return false;
			}
		} else if (!startStation.equals(other.startStation)) {
			return false;
		}
		if (totalVolume == null) {
			if (other.totalVolume != null) {
				return false;
			}
		} else if (!totalVolume.equals(other.totalVolume)) {
			return false;
		}
		if (totalWeight == null) {
			if (other.totalWeight != null) {
				return false;
			}
		} else if (!totalWeight.equals(other.totalWeight)) {
			return false;
		}
		if (transNote == null) {
			if (other.transNote != null) {
				return false;
			}
		} else if (!transNote.equals(other.transNote)) {
			return false;
		}
		if (transportMode == null) {
			if (other.transportMode != null) {
				return false;
			}
		} else if (!transportMode.equals(other.transportMode)) {
			return false;
		}
		if (waybillNumber == null) {
			if (other.waybillNumber != null) {
				return false;
			}
		} else if (!waybillNumber.equals(other.waybillNumber)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Order [hastenCount=" + hastenCount + ", shipperNumber="
				+ shipperNumber + ", shipperName=" + shipperName
				+ ", contactName=" + contactName + ", contactMobile="
				+ contactMobile + ", contactPhone=" + contactPhone
				+ ", contactProvince=" + contactProvince + ", contactCity="
				+ contactCity + ", contactArea=" + contactArea
				+ ", contactAddress=" + contactAddress + ", isReceiveGoods="
				+ isReceiveGoods + ", beginAcceptTime=" + beginAcceptTime
				+ ", endAcceptTime=" + endAcceptTime + ", receiverCustNumber="
				+ receiverCustNumber + ", receiverCustcompany="
				+ receiverCustcompany + ", receiverCustName="
				+ receiverCustName + ", receiverCustMobile="
				+ receiverCustMobile + ", receiverCustPhone="
				+ receiverCustPhone + ", receiverCustProvince="
				+ receiverCustProvince + ", receiverCustCity="
				+ receiverCustCity + ", receiverCustArea=" + receiverCustArea
				+ ", receiverCustAddress=" + receiverCustAddress
				+ ", isSendmessage=" + isSendmessage + ", receivingToPoint="
				+ receivingToPoint + ", transportMode=" + transportMode
				+ ", goodsName=" + goodsName + ", packing=" + packing
				+ ", goodsType=" + goodsType + ", goodsNumber=" + goodsNumber
				+ ", totalWeight=" + totalWeight + ", totalVolume="
				+ totalVolume + ", paymentType=" + paymentType
				+ ", channelType=" + channelType + ", channelCustId="
				+ channelCustId + ", deliveryMode=" + deliveryMode
				+ ", reciveLoanType=" + reciveLoanType + ", reviceMoneyAmount="
				+ reviceMoneyAmount + ", insuredAmount=" + insuredAmount
				+ ", orderTime=" + orderTime + ", startStation=" + startStation
				+ ", acceptDept=" + acceptDept + ",acceptDeptName="
				+ acceptDeptName + ", orderStatus=" + orderStatus
				+ ", dealPerson=" + dealPerson + ", orderAcceptTime="
				+ orderAcceptTime + ", receiver=" + receiver
				+ ", accepterPersonMobile=" + accepterPersonMobile
				+ ", transNote=" + transNote + ", returnBillType="
				+ returnBillType + ", orderPerson=" + orderPerson
				+ ", feedbackInfo=" + feedbackInfo + "]";
	}
}
