/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerLocation.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author zhujunyong
 * @version 0.1 Mar 29, 2012
 */
package com.deppon.crm.module.marketing.shared.domain;

import org.apache.commons.lang.StringUtils;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerLocation.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author zhujunyong
 * @version 0.1 Mar 29, 2012
 */

public class CustomerLocation extends BaseEntity {
	
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 7208392573101001217L;
	// 客户id，
	private String customerId;
	// 客户类型
	private String customerType;
	// x轴坐标
	private String lat;
	// y轴坐标
	private String lng;
	// 联系人ID
	private String linkmanId;
	
	/**
	 * 
	 * <p>
	 * Description:五公里地图，标注，检查参数是否合法<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Mar 30, 2012
	 * @return true 合法， false 不合法
	 * boolean 
	 */
	public boolean validate() {
		//lat为为空
		if (StringUtils.isBlank(lat) || StringUtils.isBlank(lng) || StringUtils.isBlank(customerId) || StringUtils.isBlank(customerType)) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * @return customerId : return the property customerId.
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId : set the property customerId.
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return customerType : return the property customerType.
	 */
	public String getCustomerType() {
		return customerType;
	}
	/**
	 * @param customerType : set the property customerType.
	 */
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	/**
	 * @return lat : return the property lat.
	 */
	public String getLat() {
		return lat;
	}
	/**
	 * @param lat : set the property lat.
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * @return lng : return the property lng.
	 */
	public String getLng() {
		return lng;
	}
	/**
	 * @param lng : set the property lng.
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}
	/**
	 * @return linkmanId : return the property linkmanId.
	 */
	public String getLinkmanId() {
		return linkmanId;
	}
	/**
	 * @param linkmanId : set the property linkmanId.
	 */
	public void setLinkmanId(String linkmanId) {
		this.linkmanId = linkmanId;
	}

}
