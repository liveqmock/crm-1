package com.deppon.crm.module.order.shared.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * Description:订单实体<br />
 * </p>
 * @title Order.java
 * @package com.deppon.crm.module.order.shared.domain 
 * @author zouming
 * @version 0.1 2013-1-22上午11:13:13
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
	private String contactManId;
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
	// 到达网点名称
	private String receivingToPointName;
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
	// 渠道客户Id
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
	//始发网点名称
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
	// 官网收货人德邦用户名
	private String onlineName;
	// 阿里巴巴订单的会员类型
	private String memberType;
	// 到待受理时间
	private Date toWaitAcceptTime;
	// 优惠券
	private String couponNumber;
	/*		修改人：kuang
	修改时间：2013-9-3
	修改原因：增加延迟发货，订单表中增加延迟时间
	修改内容：增加delayOrderTime字段，以及它的get，set方法
	*/
	//提示的延迟时间
	private Date delayTime;

	// 订单操作记录
	private Set<OrderOperationLog> operationLogs = new HashSet<OrderOperationLog>();
	
	/**
	 *@return  delayOrderTime;
	 */
	public Date getDelayTime() {
		return delayTime;
	}
	
	/**
	 * @param hastenCount : set the property delayOrderTime.
	 */
	public void setDelayTime(Date delayOrderTime) {
		this.delayTime = delayOrderTime;
	}

	/**
	 *@return  hastenCount;
	 */
	public Integer getHastenCount() {
		return hastenCount;
	}

	/**
	 * @param hastenCount : set the property hastenCount.
	 */
	public void setHastenCount(Integer hastenCount) {
		this.hastenCount = hastenCount;
	}

	/**
	 *@return  lastHastenTime;
	 */
	public Date getLastHastenTime() {
		return lastHastenTime;
	}

	/**
	 * @param lastHastenTime : set the property lastHastenTime.
	 */
	public void setLastHastenTime(Date lastHastenTime) {
		this.lastHastenTime = lastHastenTime;
	}

	/**
	 *@return  contactManId;
	 */
	public String getContactManId() {
		return contactManId;
	}

	/**
	 * @param contactManId : set the property contactManId.
	 */
	public void setContactManId(String contactManId) {
		this.contactManId = contactManId;
	}

	/**
	 *@return  orderNumber;
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber : set the property orderNumber.
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 *@return  channelNumber;
	 */
	public String getChannelNumber() {
		return channelNumber;
	}

	/**
	 * @param channelNumber : set the property channelNumber.
	 */
	public void setChannelNumber(String channelNumber) {
		this.channelNumber = channelNumber;
	}

	/**
	 *@return  shipperId;
	 */
	public String getShipperId() {
		return shipperId;
	}

	/**
	 * @param shipperId : set the property shipperId.
	 */
	public void setShipperId(String shipperId) {
		this.shipperId = shipperId;
	}

	/**
	 *@return  shipperNumber;
	 */
	public String getShipperNumber() {
		return shipperNumber;
	}

	/**
	 * @param shipperNumber : set the property shipperNumber.
	 */
	public void setShipperNumber(String shipperNumber) {
		this.shipperNumber = shipperNumber;
	}

	/**
	 *@return  shipperName;
	 */
	public String getShipperName() {
		return shipperName;
	}

	/**
	 * @param shipperName : set the property shipperName.
	 */
	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	/**
	 *@return  contactName;
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName : set the property contactName.
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 *@return  contactMobile;
	 */
	public String getContactMobile() {
		return contactMobile;
	}

	/**
	 * @param contactMobile : set the property contactMobile.
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	/**
	 *@return  contactPhone;
	 */
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * @param contactPhone : set the property contactPhone.
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/**
	 *@return  contactProvince;
	 */
	public String getContactProvince() {
		return contactProvince;
	}

	/**
	 * @param contactProvince : set the property contactProvince.
	 */
	public void setContactProvince(String contactProvince) {
		this.contactProvince = contactProvince;
	}

	/**
	 *@return  contactCity;
	 */
	public String getContactCity() {
		return contactCity;
	}

	/**
	 * @param contactCity : set the property contactCity.
	 */
	public void setContactCity(String contactCity) {
		this.contactCity = contactCity;
	}

	/**
	 *@return  contactArea;
	 */
	public String getContactArea() {
		return contactArea;
	}

	/**
	 * @param contactArea : set the property contactArea.
	 */
	public void setContactArea(String contactArea) {
		this.contactArea = contactArea;
	}

	/**
	 *@return  contactAddress;
	 */
	public String getContactAddress() {
		return contactAddress;
	}

	/**
	 * @param contactAddress : set the property contactAddress.
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	/**
	 *@return  isReceiveGoods;
	 */
	public Boolean getIsReceiveGoods() {
		return isReceiveGoods;
	}

	/**
	 * @param isReceiveGoods : set the property isReceiveGoods.
	 */
	public void setIsReceiveGoods(Boolean isReceiveGoods) {
		this.isReceiveGoods = isReceiveGoods;
	}

	/**
	 *@return  beginAcceptTime;
	 */
	public Date getBeginAcceptTime() {
		return beginAcceptTime;
	}

	/**
	 * @param beginAcceptTime : set the property beginAcceptTime.
	 */
	public void setBeginAcceptTime(Date beginAcceptTime) {
		this.beginAcceptTime = beginAcceptTime;
	}

	/**
	 *@return  endAcceptTime;
	 */
	public Date getEndAcceptTime() {
		return endAcceptTime;
	}

	/**
	 * @param endAcceptTime : set the property endAcceptTime.
	 */
	public void setEndAcceptTime(Date endAcceptTime) {
		this.endAcceptTime = endAcceptTime;
	}

	/**
	 *@return  receiverCustId;
	 */
	public String getReceiverCustId() {
		return receiverCustId;
	}

	/**
	 * @param receiverCustId : set the property receiverCustId.
	 */
	public void setReceiverCustId(String receiverCustId) {
		this.receiverCustId = receiverCustId;
	}

	/**
	 *@return  receiverCustNumber;
	 */
	public String getReceiverCustNumber() {
		return receiverCustNumber;
	}

	/**
	 * @param receiverCustNumber : set the property receiverCustNumber.
	 */
	public void setReceiverCustNumber(String receiverCustNumber) {
		this.receiverCustNumber = receiverCustNumber;
	}

	/**
	 *@return  receiverCustcompany;
	 */
	public String getReceiverCustcompany() {
		return receiverCustcompany;
	}

	/**
	 * @param receiverCustcompany : set the property receiverCustcompany.
	 */
	public void setReceiverCustcompany(String receiverCustcompany) {
		this.receiverCustcompany = receiverCustcompany;
	}

	/**
	 *@return  receiverCustName;
	 */
	public String getReceiverCustName() {
		return receiverCustName;
	}

	/**
	 * @param receiverCustName : set the property receiverCustName.
	 */
	public void setReceiverCustName(String receiverCustName) {
		this.receiverCustName = receiverCustName;
	}

	/**
	 *@return  receiverCustMobile;
	 */
	public String getReceiverCustMobile() {
		return receiverCustMobile;
	}

	/**
	 * @param receiverCustMobile : set the property receiverCustMobile.
	 */
	public void setReceiverCustMobile(String receiverCustMobile) {
		this.receiverCustMobile = receiverCustMobile;
	}

	/**
	 *@return  receiverCustPhone;
	 */
	public String getReceiverCustPhone() {
		return receiverCustPhone;
	}

	/**
	 * @param receiverCustPhone : set the property receiverCustPhone.
	 */
	public void setReceiverCustPhone(String receiverCustPhone) {
		this.receiverCustPhone = receiverCustPhone;
	}

	/**
	 *@return  receiverCustProvince;
	 */
	public String getReceiverCustProvince() {
		return receiverCustProvince;
	}

	/**
	 * @param receiverCustProvince : set the property receiverCustProvince.
	 */
	public void setReceiverCustProvince(String receiverCustProvince) {
		this.receiverCustProvince = receiverCustProvince;
	}

	/**
	 *@return  receiverCustCity;
	 */
	public String getReceiverCustCity() {
		return receiverCustCity;
	}

	/**
	 * @param receiverCustCity : set the property receiverCustCity.
	 */
	public void setReceiverCustCity(String receiverCustCity) {
		this.receiverCustCity = receiverCustCity;
	}

	/**
	 *@return  receiverCustArea;
	 */
	public String getReceiverCustArea() {
		return receiverCustArea;
	}

	/**
	 * @param receiverCustArea : set the property receiverCustArea.
	 */
	public void setReceiverCustArea(String receiverCustArea) {
		this.receiverCustArea = receiverCustArea;
	}

	/**
	 *@return  receiverCustAddress;
	 */
	public String getReceiverCustAddress() {
		return receiverCustAddress;
	}

	/**
	 * @param receiverCustAddress : set the property receiverCustAddress.
	 */
	public void setReceiverCustAddress(String receiverCustAddress) {
		this.receiverCustAddress = receiverCustAddress;
	}

	/**
	 *@return  isSendmessage;
	 */
	public Boolean getIsSendmessage() {
		return isSendmessage;
	}

	/**
	 * @param isSendmessage : set the property isSendmessage.
	 */
	public void setIsSendmessage(Boolean isSendmessage) {
		this.isSendmessage = isSendmessage;
	}

	/**
	 *@return  receivingToPoint;
	 */
	public String getReceivingToPoint() {
		return receivingToPoint;
	}

	/**
	 * @param receivingToPoint : set the property receivingToPoint.
	 */
	public void setReceivingToPoint(String receivingToPoint) {
		this.receivingToPoint = receivingToPoint;
	}

	/**
	 *@return  receivingToPointName;
	 */
	public String getReceivingToPointName() {
		return receivingToPointName;
	}

	/**
	 * @param receivingToPointName : set the property receivingToPointName.
	 */
	public void setReceivingToPointName(String receivingToPointName) {
		this.receivingToPointName = receivingToPointName;
	}

	/**
	 *@return  transportMode;
	 */
	public String getTransportMode() {
		return transportMode;
	}

	/**
	 * @param transportMode : set the property transportMode.
	 */
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	/**
	 *@return  goodsName;
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * @param goodsName : set the property goodsName.
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 *@return  packing;
	 */
	public String getPacking() {
		return packing;
	}

	/**
	 * @param packing : set the property packing.
	 */
	public void setPacking(String packing) {
		this.packing = packing;
	}

	/**
	 *@return  goodsType;
	 */
	public String getGoodsType() {
		return goodsType;
	}

	/**
	 * @param goodsType : set the property goodsType.
	 */
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	/**
	 *@return  goodsNumber;
	 */
	public Integer getGoodsNumber() {
		return goodsNumber;
	}

	/**
	 * @param goodsNumber : set the property goodsNumber.
	 */
	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	/**
	 *@return  totalWeight;
	 */
	public Double getTotalWeight() {
		return totalWeight;
	}

	/**
	 * @param totalWeight : set the property totalWeight.
	 */
	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	/**
	 *@return  totalVolume;
	 */
	public Double getTotalVolume() {
		return totalVolume;
	}

	/**
	 * @param totalVolume : set the property totalVolume.
	 */
	public void setTotalVolume(Double totalVolume) {
		this.totalVolume = totalVolume;
	}

	/**
	 *@return  paymentType;
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType : set the property paymentType.
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 *@return  channelType;
	 */
	public String getChannelType() {
		return channelType;
	}

	/**
	 * @param channelType : set the property channelType.
	 */
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	/**
	 *@return  channelCustId;
	 */
	public String getChannelCustId() {
		return channelCustId;
	}

	/**
	 * @param channelCustId : set the property channelCustId.
	 */
	public void setChannelCustId(String channelCustId) {
		this.channelCustId = channelCustId;
	}

	/**
	 *@return  deliveryMode;
	 */
	public String getDeliveryMode() {
		return deliveryMode;
	}

	/**
	 * @param deliveryMode : set the property deliveryMode.
	 */
	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	/**
	 *@return  reciveLoanType;
	 */
	public String getReciveLoanType() {
		return reciveLoanType;
	}

	/**
	 * @param reciveLoanType : set the property reciveLoanType.
	 */
	public void setReciveLoanType(String reciveLoanType) {
		this.reciveLoanType = reciveLoanType;
	}

	/**
	 *@return  reviceMoneyAmount;
	 */
	public Double getReviceMoneyAmount() {
		return reviceMoneyAmount;
	}

	/**
	 * @param reviceMoneyAmount : set the property reviceMoneyAmount.
	 */
	public void setReviceMoneyAmount(Double reviceMoneyAmount) {
		this.reviceMoneyAmount = reviceMoneyAmount;
	}

	/**
	 *@return  insuredAmount;
	 */
	public Double getInsuredAmount() {
		return insuredAmount;
	}

	/**
	 * @param insuredAmount : set the property insuredAmount.
	 */
	public void setInsuredAmount(Double insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	/**
	 *@return  orderTime;
	 */
	public Date getOrderTime() {
		return orderTime;
	}

	/**
	 * @param orderTime : set the property orderTime.
	 */
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 *@return  startStation;
	 */
	public String getStartStation() {
		return startStation;
	}

	/**
	 * @param startStation : set the property startStation.
	 */
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	/**
	 *@return  startStationName;
	 */
	public String getStartStationName() {
		return startStationName;
	}

	/**
	 * @param startStationName : set the property startStationName.
	 */
	public void setStartStationName(String startStationName) {
		this.startStationName = startStationName;
	}

	/**
	 *@return  acceptDept;
	 */
	public String getAcceptDept() {
		return acceptDept;
	}

	/**
	 * @param acceptDept : set the property acceptDept.
	 */
	public void setAcceptDept(String acceptDept) {
		this.acceptDept = acceptDept;
	}

	/**
	 *@return  acceptDeptName;
	 */
	public String getAcceptDeptName() {
		return acceptDeptName;
	}

	/**
	 * @param acceptDeptName : set the property acceptDeptName.
	 */
	public void setAcceptDeptName(String acceptDeptName) {
		this.acceptDeptName = acceptDeptName;
	}

	/**
	 *@return  orderStatus;
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus : set the property orderStatus.
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 *@return  dealPerson;
	 */
	public String getDealPerson() {
		return dealPerson;
	}

	/**
	 * @param dealPerson : set the property dealPerson.
	 */
	public void setDealPerson(String dealPerson) {
		this.dealPerson = dealPerson;
	}

	/**
	 *@return  orderAcceptTime;
	 */
	public Date getOrderAcceptTime() {
		return orderAcceptTime;
	}

	/**
	 * @param orderAcceptTime : set the property orderAcceptTime.
	 */
	public void setOrderAcceptTime(Date orderAcceptTime) {
		this.orderAcceptTime = orderAcceptTime;
	}

	/**
	 *@return  receiver;
	 */
	public String getReceiver() {
		return receiver;
	}

	/**
	 * @param receiver : set the property receiver.
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	/**
	 *@return  accepterPersonMobile;
	 */
	public String getAccepterPersonMobile() {
		return accepterPersonMobile;
	}

	/**
	 * @param accepterPersonMobile : set the property accepterPersonMobile.
	 */
	public void setAccepterPersonMobile(String accepterPersonMobile) {
		this.accepterPersonMobile = accepterPersonMobile;
	}

	/**
	 *@return  waybillNumber;
	 */
	public String getWaybillNumber() {
		return waybillNumber;
	}

	/**
	 * @param waybillNumber : set the property waybillNumber.
	 */
	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	/**
	 *@return  resource;
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * @param resource : set the property resource.
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}

	/**
	 *@return  transNote;
	 */
	public String getTransNote() {
		return transNote;
	}

	/**
	 * @param transNote : set the property transNote.
	 */
	public void setTransNote(String transNote) {
		this.transNote = transNote;
	}

	/**
	 *@return  returnBillType;
	 */
	public String getReturnBillType() {
		return returnBillType;
	}

	/**
	 * @param returnBillType : set the property returnBillType.
	 */
	public void setReturnBillType(String returnBillType) {
		this.returnBillType = returnBillType;
	}

	/**
	 *@return  orderPerson;
	 */
	public String getOrderPerson() {
		return orderPerson;
	}

	/**
	 * @param orderPerson : set the property orderPerson.
	 */
	public void setOrderPerson(String orderPerson) {
		this.orderPerson = orderPerson;
	}

	/**
	 *@return  feedbackInfo;
	 */
	public String getFeedbackInfo() {
		return feedbackInfo;
	}

	/**
	 * @param feedbackInfo : set the property feedbackInfo.
	 */
	public void setFeedbackInfo(String feedbackInfo) {
		this.feedbackInfo = feedbackInfo;
	}

	/**
	 *@return  onlineName;
	 */
	public String getOnlineName() {
		return onlineName;
	}

	/**
	 * @param onlineName : set the property onlineName.
	 */
	public void setOnlineName(String onlineName) {
		this.onlineName = onlineName;
	}

	/**
	 *@return  memberType;
	 */
	public String getMemberType() {
		return memberType;
	}

	/**
	 * @param memberType : set the property memberType.
	 */
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	/**
	 *@return  operationLogs;
	 */
	public Set<OrderOperationLog> getOperationLogs() {
		return operationLogs;
	}

	/**
	 * @param operationLogs : set the property operationLogs.
	 */
	public void setOperationLogs(Set<OrderOperationLog> operationLogs) {
		this.operationLogs = operationLogs;
	}

	public Date getToWaitAcceptTime() {
		return toWaitAcceptTime;
	}

	public void setToWaitAcceptTime(Date toWaitAcceptTime) {
		this.toWaitAcceptTime = toWaitAcceptTime;
	}

	public String getCouponNumber() {
		return couponNumber;
	}

	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}

}