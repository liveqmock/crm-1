/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerWaybill.java
 * @package com.deppon.crm.module.client.order.domain
 * @author 105681
 * @version 0.1 2014-4-15
 */
package com.deppon.crm.module.client.order.domain;

import java.util.Date;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 *
 * @title CustomerWaybill.java
 * @package com.deppon.crm.module.client.order.domain
 * @author 105681
 * @version 0.1 2014-4-15
 */

public class CustomerWaybill {
	// 运单号
	private String waybillNo;
	// 收货客户名称
	private String receiveCustomerName;
	// 收货联系人
	private String receiveCustomerContact;
	// 收货人手机
	private String receiveCustomerMobilephone;
	// 收货人电话
	private String receiveCustomerPhone;
	// 收货人地址
	private String receiveCustomerAddress;
	// 运输性质
	private String productCode;
	// 提货网点
	private String customerPickupOrgCode;
	// 开单时间
	private Date billTime;
	// 活动名称
	private String activeName;
	// 活动类型
	private String activeType;
	// 活动编码
	private String activeCode;
	// 优惠方式
	private String discountType;
	// 活动开始时间
	private Date activeStartTime;
	// 活动结束时间
	private Date activeEndTime;

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public String getReceiveCustomerName() {
		return receiveCustomerName;
	}

	public void setReceiveCustomerName(String receiveCustomerName) {
		this.receiveCustomerName = receiveCustomerName;
	}

	public String getReceiveCustomerContact() {
		return receiveCustomerContact;
	}

	public void setReceiveCustomerContact(String receiveCustomerContact) {
		this.receiveCustomerContact = receiveCustomerContact;
	}

	public String getReceiveCustomerMobilephone() {
		return receiveCustomerMobilephone;
	}

	public void setReceiveCustomerMobilephone(String receiveCustomerMobilephone) {
		this.receiveCustomerMobilephone = receiveCustomerMobilephone;
	}

	public String getReceiveCustomerPhone() {
		return receiveCustomerPhone;
	}

	public void setReceiveCustomerPhone(String receiveCustomerPhone) {
		this.receiveCustomerPhone = receiveCustomerPhone;
	}

	public String getReceiveCustomerAddress() {
		return receiveCustomerAddress;
	}

	public void setReceiveCustomerAddress(String receiveCustomerAddress) {
		this.receiveCustomerAddress = receiveCustomerAddress;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCustomerPickupOrgCode() {
		return customerPickupOrgCode;
	}

	public void setCustomerPickupOrgCode(String customerPickupOrgCode) {
		this.customerPickupOrgCode = customerPickupOrgCode;
	}

	public Date getBillTime() {
		return billTime;
	}

	public void setBillTime(Date billTime) {
		this.billTime = billTime;
	}

	public String getActiveName() {
		return activeName;
	}

	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}

	public String getActiveType() {
		return activeType;
	}

	public void setActiveType(String activeType) {
		this.activeType = activeType;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public Date getActiveStartTime() {
		return activeStartTime;
	}

	public void setActiveStartTime(Date activeStartTime) {
		this.activeStartTime = activeStartTime;
	}

	public Date getActiveEndTime() {
		return activeEndTime;
	}

	public void setActiveEndTime(Date activeEndTime) {
		this.activeEndTime = activeEndTime;
	}

}
