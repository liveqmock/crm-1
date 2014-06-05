package com.deppon.crm.module.client.customer.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

public class RegisterInfo extends BaseEntity {

	private static final long serialVersionUID = -4790325320848296758L;
	// 地址
	protected String address;
	// 区县
	protected String area;
	// 城市
	protected String city;
	// 联系人编码
	protected String cusCode;
	// 部门标杆编码
	protected String deptId;
	// E-Mail
	protected String email;
	// 
	protected String fixedPhone;
	protected String gender;
	protected Date lastLoginTime;
	protected Date lastUpdateTime;
	protected String newPwd;
	protected String password;
	protected String procity;
	protected String province;
	protected String realName;
	protected int refundPaymentOrder;
	protected Date regiterTime;
	protected int status;
	protected String telephone;
	protected int transportingOrder;
	protected String userName;
	protected String custsource;
	
	public String getCustsource() {
		return custsource;
	}
	public void setCustsource(String custsource) {
		this.custsource = custsource;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCusCode() {
		return cusCode;
	}
	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFixedPhone() {
		return fixedPhone;
	}
	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProcity() {
		return procity;
	}
	public void setProcity(String procity) {
		this.procity = procity;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getRefundPaymentOrder() {
		return refundPaymentOrder;
	}
	public void setRefundPaymentOrder(int refundPaymentOrder) {
		this.refundPaymentOrder = refundPaymentOrder;
	}
	public Date getRegiterTime() {
		return regiterTime;
	}
	public void setRegiterTime(Date regiterTime) {
		this.regiterTime = regiterTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getTransportingOrder() {
		return transportingOrder;
	}
	public void setTransportingOrder(int transportingOrder) {
		this.transportingOrder = transportingOrder;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "RegisterInfo [address=" + address + ", area=" + area
				+ ", city=" + city + ", cusCode=" + cusCode + ", deptId="
				+ deptId + ", email=" + email + ", fixedPhone=" + fixedPhone
				+ ", gender=" + gender + ", lastLoginTime=" + lastLoginTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", newPwd=" + newPwd
				+ ", password=" + password + ", procity=" + procity
				+ ", province=" + province + ", realName=" + realName
				+ ", refundPaymentOrder=" + refundPaymentOrder
				+ ", regiterTime=" + regiterTime + ", status=" + status
				+ ", telephone=" + telephone + ", transportingOrder="
				+ transportingOrder + ", userName=" + userName + "]";
	}
	
	
}
