/**
 * <p>
 * Description:<br />
 * </p>
 * @title CustomerAddressInfo.java
 * @package com.deppon.crm.module.marketing.shared.domain.visualMarket
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-7-3
 */
package com.deppon.crm.module.marketing.shared.domain.visualMarket;

/**
 * <p>
 * Description:客户地址信息<br />
 * </p>
 * @title CustomerAddressInfo.java
 * @package com.deppon.crm.module.marketing.shared.domain.visualMarket
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-7-3
 */
public class CustomerAddressInfo {
	//客户id，包括固定客户id和潜散客id
	private String custId;
	//客户类型，固定客户为常量MEMBER,潜客为PC_CUSTOMER，散客为SC_CUSTOMER
	private String custType;
	//城市id
	private String cityId;
	//城市名称
	private String cityName;
	//地址：如果是固定客户，查询的是主联系人的主偏好地址，如果是潜散客，查询的直接是潜散客的地址
	private String address;
	/**
	 * @param custId the custId to get
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * @param custType the custType to get
	 */
	public String getCustType() {
		return custType;
	}
	/**
	 * @param custType the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}
	/**
	 * @param cityId the cityId to get
	 */
	public String getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	/**
	 * @param cityName the cityName to get
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * @param address the address to get
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}
