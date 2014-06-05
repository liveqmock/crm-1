package com.deppon.crm.module.scheduler.shared.domain;

/**
 * @作者：罗典 
 * @时间：2012-3-13 
 * @功能：客户临时表实体
 * */
public class CustomerTemp {
	private String id;
	// 客户姓名
	private String customerName;
	// 联系人编码
	private String number;
	// 联系人姓名
	private String name;
	// 联系人手机
	private String callPhone;
	// 联系人电话
	private String telephone;
	// 部门
	private String deptId;
	// 地址
	private String address;
	// 客户类型
	private String custType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCallPhone() {
		return callPhone;
	}

	public void setCallPhone(String callPhone) {
		this.callPhone = callPhone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

}
