/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title OwsCustomer.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author pgj
 * @version 0.1 2014-3-3
 */
package com.deppon.crm.module.customer.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:官网用户实体<br />
 * </p>
 * @title OwsCustomer.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author pgj
 * @version 0.1 2014-3-3
 */

public class OwsCustomer extends BaseEntity{
	//官网用户名
	private String userName;
	//联系人姓名
	private String linknamName;
	//联系人手机
	private String mobile;
	//联系人电话
	private String phone;
	//接货地址
	private String address;
	/**
	 *@user pgj
	 *2014-3-3下午7:44:46
	 * @return userName : return the property userName.
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName : set the property userName.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 *@user pgj
	 *2014-3-3下午7:44:46
	 * @return linknamName : return the property linknamName.
	 */
	public String getLinknamName() {
		return linknamName;
	}
	/**
	 * @param linknamName : set the property linknamName.
	 */
	public void setLinknamName(String linknamName) {
		this.linknamName = linknamName;
	}
	/**
	 *@user pgj
	 *2014-3-3下午7:44:46
	 * @return mobile : return the property mobile.
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile : set the property mobile.
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 *@user pgj
	 *2014-3-3下午7:44:46
	 * @return phone : return the property phone.
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone : set the property phone.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 *@user pgj
	 *2014-3-3下午7:44:46
	 * @return address : return the property address.
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address : set the property address.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
