package com.deppon.crm.module.client.map.domain;
/**
 * example：上海(省),上海(市)，青浦区徐泾镇明珠路1018号(地址)
 * @author davidcun @2012-5-16
 */
public class AddressInfo {

	//省
	private String province;
	//市
	private String city;
	//地址
	private String address;
	
	public AddressInfo() {
	}

	public AddressInfo(String province, String city, String address) {
		super();
		this.province = province;
		this.city = city;
		this.address = address;
	}
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
