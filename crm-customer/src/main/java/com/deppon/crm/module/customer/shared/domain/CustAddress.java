package com.deppon.crm.module.customer.shared.domain;

/**   
 * Description:修改 客户 潜客 散客 前端传入的对象<br />
 * @title CustAddress.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @version 0.1 2013-4-23
 */
public class CustAddress {

	//客户ID
	private String custId;
	//客户类型
	private String custType;
	//客户地址
	private String address;
	//省份ID
	private String provinceId;
	//城市ID
	private String cityId;
	//省份名字
	private String provinceName;
	//城市名字
	private String cityName;
	//区域名字
	private String areaName;
	//区域ID
	private String areaId;
	//主联系人ID
	
	private String contactId;
	
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	/**
	 * Description:custId<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * Description:custId<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * Description:custType<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public String getCustType() {
		return custType;
	}
	/**
	 * Description:custType<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}
	/**
	 * Description:address<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * Description:address<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * Description:provinceId<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public String getProvinceId() {
		return provinceId;
	}
	/**
	 * Description:provinceId<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * Description:cityId<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public String getCityId() {
		return cityId;
	}
	/**
	 * Description:cityId<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	/**
	 * Description:provinceName<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public String getProvinceName() {
		return provinceName;
	}
	/**
	 * Description:provinceName<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	/**
	 * Description:cityName<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * Description:cityName<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * <p>
	 * Description:areaName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public String getAreaName() {
		return areaName;
	}
	/**
	 * <p>
	 * Description:areaName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
s	 * Description:areaId<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public String getAreaId() {
		return areaId;
	}
	/**
	 * Description:areaId<br />
	 * @author CoCo
	 * @version 0.1 2013-4-23
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
}
