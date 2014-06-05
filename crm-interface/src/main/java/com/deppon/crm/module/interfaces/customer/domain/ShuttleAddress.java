package com.deppon.crm.module.interfaces.customer.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @作者：罗典
 * @时间：2012-3-21
 * @描述：接送货地址
 * */
public class ShuttleAddress extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 地址类型
	private String addressType;
	// 详细地址
	private String address;
	// 省份ID
	private String province;
	//省份名称
	private String provinceName;
	// 城市ID
	private String city;
	//城市名称
	private String cityName;
	// 区县
	private String area;
	//区县名称
	private String areaName;
	// 邮编
	private String postcode;
	// 会员id
	private String memberId;
	// 状态  正常：0；  审批中：1  ；无效 ：2；
	private String status;
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
	 * Description:province<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * <p>
	 * Description:province<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * <p>
	 * Description:provinceName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getProvinceName() {
		return provinceName;
	}
	/**
	 * <p>
	 * Description:provinceName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	/**
	 * <p>
	 * Description:city<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCity() {
		return city;
	}
	/**
	 * <p>
	 * Description:city<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * <p>
	 * Description:cityName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * <p>
	 * Description:cityName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * <p>
	 * Description:area<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getArea() {
		return area;
	}
	/**
	 * <p>
	 * Description:area<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * <p>
	 * Description:areaName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getAreaName() {
		return areaName;
	}
	/**
	 * <p>
	 * Description:areaName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * <p>
	 * Description:postcode<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getPostcode() {
		return postcode;
	}
	/**
	 * <p>
	 * Description:postcode<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	/**
	 * <p>
	 * Description:memberId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMemberId() {
		return memberId;
	}
	/**
	 * <p>
	 * Description:memberId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
	 * Description:serialversionuid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
