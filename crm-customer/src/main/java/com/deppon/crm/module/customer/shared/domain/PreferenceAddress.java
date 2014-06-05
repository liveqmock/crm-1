package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @作者：罗典
 * @时间：2012-3-12
 * @描述：联系人偏好地址
 * */
public class PreferenceAddress extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 地址类型
	private String addressType;
	// 地址
	private String address;
	// 偏好起始时间
	private Date startTime;
	// 偏好截止时间
	private Date endTime;
	// 发票要求
	private String billRequest;
	// 停车费用
	private Boolean hasStopCost;
	// 付款方式
	private String payType;
	// 是否送货上楼 1为是，0为否
	private Boolean isSendToFloor;
	// 其它要求
	private String otherNeed;
	// 是否主地址
	private Boolean isMainAddress;
	//状态 正常：0；  审批中：1  ；无效 ：2；
	private String status;
	// 接送货地址ID
	private String shuttleAddressId;
	// 关联的联系人ID 
	private String linkManId;

	/**
	 * <p>
	 * Description:addressType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getAddressType() {
		return addressType;
	}
	/**
	 * <p>
	 * Description:addressType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	/**
	 * <p>
	 * Description:address<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * <p>
	 * Description:address<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * <p>
	 * Description:startTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * <p>
	 * Description:startTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * <p>
	 * Description:endTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * <p>
	 * Description:endTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * <p>
	 * Description:billRequest<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBillRequest() {
		return billRequest;
	}
	/**
	 * <p>
	 * Description:billRequest<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBillRequest(String billRequest) {
		this.billRequest = billRequest;
	}
	/**
	 * <p>
	 * Description:hasStopCost<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getHasStopCost() {
		return hasStopCost;
	}
	/**
	 * <p>
	 * Description:hasStopCost<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setHasStopCost(Boolean hasStopCost) {
		this.hasStopCost = hasStopCost;
	}
	/**
	 * <p>
	 * Description:payType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getPayType() {
		return payType;
	}
	/**
	 * <p>
	 * Description:payType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * <p>
	 * Description:isSendToFloor<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIsSendToFloor() {
		return isSendToFloor;
	}
	/**
	 * <p>
	 * Description:isSendToFloor<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIsSendToFloor(Boolean isSendToFloor) {
		this.isSendToFloor = isSendToFloor;
	}
	/**
	 * <p>
	 * Description:otherNeed<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getOtherNeed() {
		return otherNeed;
	}
	/**
	 * <p>
	 * Description:otherNeed<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOtherNeed(String otherNeed) {
		this.otherNeed = otherNeed;
	}
	/**
	 * <p>
	 * Description:isMainAddress<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIsMainAddress() {
		return isMainAddress;
	}
	/**
	 * <p>
	 * Description:isMainAddress<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIsMainAddress(Boolean isMainAddress) {
		this.isMainAddress = isMainAddress;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * <p>
	 * Description:shuttleAddressId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getShuttleAddressId() {
		return shuttleAddressId;
	}
	/**
	 * <p>
	 * Description:shuttleAddressId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setShuttleAddressId(String shuttleAddressId) {
		this.shuttleAddressId = shuttleAddressId;
	}
	/**
	 * <p>
	 * Description:linkManId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLinkManId() {
		return linkManId;
	}
	/**
	 * <p>
	 * Description:linkManId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLinkManId(String linkManId) {
		this.linkManId = linkManId;
	}
}
