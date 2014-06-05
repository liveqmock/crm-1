package com.deppon.crm.module.client.common.domain;

/**
 * @作者：罗典
 * @描述：接口地址详细信息
 * @时间：2012-10-17
 * */
public class InterfaceAddressConfig {
	// ID
	private String id;
	// 服务地址
	private String address;
	// 服务编码
	private String code;
	// 服务接口类
	private String serviceClass;
	// 目标系统
	private String targetSystem;
	// 收件人,以";"分割
	private String emailReceiver;
	// 用户名
	private String userName;
	// 密码
	private String password;
	// slnName
	private String slnName;
	// oracle版本号
	private String dcName;
	// 语言
	private String language;
	// 数据库类型
	private int dbType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getServiceClass() {
		return serviceClass;
	}

	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSlnName() {
		return slnName;
	}

	public void setSlnName(String slnName) {
		this.slnName = slnName;
	}

	public String getDcName() {
		return dcName;
	}

	public void setDcName(String dcName) {
		this.dcName = dcName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getDbType() {
		return dbType;
	}

	public void setDbType(int dbType) {
		this.dbType = dbType;
	}

	public String getTargetSystem() {
		return targetSystem;
	}

	public void setTargetSystem(String targetSystem) {
		this.targetSystem = targetSystem;
	}

	public String getEmailReceiver() {
		return emailReceiver;
	}

	public void setEmailReceiver(String emailReceiver) {
		this.emailReceiver = emailReceiver;
	}

}
