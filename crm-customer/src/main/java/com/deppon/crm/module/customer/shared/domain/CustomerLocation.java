package com.deppon.crm.module.customer.shared.domain;

/**   
 * Description:修改客户标注 实体<br />
 * @title CustomerLocation.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author CoCo
 * @version 0.1 2013-5-27
 */
public class CustomerLocation {
	//潜散客ID 或 客户ID
	private String customerId;
	//类型
	private String customerType;
	//X轴
	private String lat;
	//Y轴
	private String lng;
	//主联系人ID
	private String linkmanId;
	/**
	 * Description:customerId<br />
	 * @author CoCo
	 * @version 0.1 2013-5-27
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * Description:customerId<br />
	 * @author CoCo
	 * @version 0.1 2013-5-27
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * Description:customerType<br />
	 * @author CoCo
	 * @version 0.1 2013-5-27
	 */
	public String getCustomerType() {
		return customerType;
	}
	/**
	 * Description:customerType<br />
	 * @author CoCo
	 * @version 0.1 2013-5-27
	 */
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	/**
	 * Description:lat<br />
	 * @author CoCo
	 * @version 0.1 2013-5-27
	 */
	public String getLat() {
		return lat;
	}
	/**
	 * Description:lat<br />
	 * @author CoCo
	 * @version 0.1 2013-5-27
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * Description:lng<br />
	 * @author CoCo
	 * @version 0.1 2013-5-27
	 */
	public String getLng() {
		return lng;
	}
	/**
	 * Description:lng<br />
	 * @author CoCo
	 * @version 0.1 2013-5-27
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}
	/**
	 * Description:linkmanId<br />
	 * @author CoCo
	 * @version 0.1 2013-5-27
	 */
	public String getLinkmanId() {
		return linkmanId;
	}
	/**
	 * Description:linkmanId<br />
	 * @author CoCo
	 * @version 0.1 2013-5-27
	 */
	public void setLinkmanId(String linkmanId) {
		this.linkmanId = linkmanId;
	}
	
}
