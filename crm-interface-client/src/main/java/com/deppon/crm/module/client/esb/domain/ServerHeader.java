package com.deppon.crm.module.client.esb.domain;

import java.io.Serializable;
/**
 * 用于webserice服务端
 * @author qiancheng
 *
 */
public class ServerHeader
    implements Serializable
{

    private final static long serialVersionUID = 11082014L;
    protected String version;
    protected String businessId;
    protected String businessDesc1;
    protected String businessDesc2;
    protected String businessDesc3;
    protected String requestId;
    protected String responseId;
    protected String sourceSystem;
    protected String targetSystem;
    protected String esbServiceCode;
    protected String backServiceCode;
    protected String messageFormat;
    protected String exchangePattern;
    protected String userName;
    protected String password;
	protected String resultCode;
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
    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the businessId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessId() {
        return businessId;
    }

    /**
     * Sets the value of the businessId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessId(String value) {
        this.businessId = value;
    }

    /**
     * Gets the value of the businessDesc1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessDesc1() {
        return businessDesc1;
    }

    /**
     * Sets the value of the businessDesc1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessDesc1(String value) {
        this.businessDesc1 = value;
    }

    /**
     * Gets the value of the businessDesc2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessDesc2() {
        return businessDesc2;
    }

    /**
     * Sets the value of the businessDesc2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessDesc2(String value) {
        this.businessDesc2 = value;
    }

    /**
     * Gets the value of the businessDesc3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessDesc3() {
        return businessDesc3;
    }

    /**
     * Sets the value of the businessDesc3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessDesc3(String value) {
        this.businessDesc3 = value;
    }

    /**
     * Gets the value of the requestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * Gets the value of the responseId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseId() {
        return responseId;
    }

    /**
     * Sets the value of the responseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseId(String value) {
        this.responseId = value;
    }

    /**
     * Gets the value of the sourceSystem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceSystem() {
        return sourceSystem;
    }

    /**
     * Sets the value of the sourceSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceSystem(String value) {
        this.sourceSystem = value;
    }

    /**
     * Gets the value of the targetSystem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetSystem() {
        return targetSystem;
    }

    /**
     * Sets the value of the targetSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetSystem(String value) {
        this.targetSystem = value;
    }



    public String getEsbServiceCode() {
		return esbServiceCode;
	}

	public void setEsbServiceCode(String esbServiceCode) {
		this.esbServiceCode = esbServiceCode;
	}

	/**
     * Gets the value of the backServiceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBackServiceCode() {
        return backServiceCode;
    }

    /**
     * Sets the value of the backServiceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBackServiceCode(String value) {
        this.backServiceCode = value;
    }

    /**
     * Gets the value of the messageFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageFormat() {
        return messageFormat;
    }

    /**
     * Sets the value of the messageFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageFormat(String value) {
        this.messageFormat = value;
    }

    /**
     * Gets the value of the exchangePattern property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */

	public String getExchangePattern() {
		return exchangePattern;
	}

	public void setExchangePattern(String exchangePattern) {
		this.exchangePattern = exchangePattern;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}    
}
