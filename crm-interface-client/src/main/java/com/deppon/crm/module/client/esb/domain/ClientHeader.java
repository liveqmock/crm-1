package com.deppon.crm.module.client.esb.domain;

/**
 * 用于webservice 客户端
 * @author qiancheng
 *
 */
public class ClientHeader {
	/**
	 * 版本号，必填字段
	 */
    protected String version="1.0";
    /**
     * 交互模式,必填字段
     */
    protected String exchangePattern="InOUT";
    /**
     * 消息格式,必填字段
     */
    protected String messageFormat="SOAP";
    /**
     * 客户端系统名，必填字段
     */
    protected String sourceSystem="CRM";
    /**
     * 服务编码，必填字段
     */
    protected String esbServiceCode;
    /**
     * 业务关键字，选填字段
     */
    protected String businessId;
    /**
     * 辅助业务关键字，选填字段
     */
    protected String businessDesc1;
    /**
     * 辅助业务关键字，选填字段
     */
    protected String businessDesc2;
    /**
     * 辅助业务关键字，选填字段
     */
    protected String businessDesc3;
    /**
     * 用户名,选填字段
     */
    protected String userName;
    /**
     * 密码，选填字段
     */
    protected String password;
	public String getVersion() {
		return version;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getBusinessDesc1() {
		return businessDesc1;
	}

	public String getBusinessDesc2() {
		return businessDesc2;
	}

	public String getBusinessDesc3() {
		return businessDesc3;
	}

	public String getEsbServiceCode() {
		return esbServiceCode;
	}
	public void setEsbServiceCode(String esbServiceCode) {
		this.esbServiceCode = esbServiceCode;
	}

	public String getExchangePattern() {
		return exchangePattern;
	}
	public String getMessageFormat() {
		return messageFormat;
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
	public String getSourceSystem() {
		return sourceSystem;
	}
	public void setBusinessDesc1(String businessDesc1) {
		this.businessDesc1 = businessDesc1;
	}
	public void setBusinessDesc2(String businessDesc2) {
		this.businessDesc2 = businessDesc2;
	}
	public void setBusinessDesc3(String businessDesc3) {
		this.businessDesc3 = businessDesc3;
	}
	
}
