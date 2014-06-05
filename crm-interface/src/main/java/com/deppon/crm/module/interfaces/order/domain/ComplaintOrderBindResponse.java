package com.deppon.crm.module.interfaces.order.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ComplaintOrderBindResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ComplaintOrderBindResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isSuccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="failReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplaintOrderBindResponse", propOrder = { "isSuccess",
		"failReason" })
public class ComplaintOrderBindResponse {

	protected boolean isSuccess;
	protected String failReason;

	public ComplaintOrderBindResponse(){}
	
	public ComplaintOrderBindResponse(boolean isSuccess,String failReason){
		this.isSuccess = isSuccess;
		this.failReason = failReason;
	}
	
	
	/**
	 * Gets the value of the isSuccess property.
	 * 
	 */
	public boolean isIsSuccess() {
		return isSuccess;
	}

	/**
	 * Sets the value of the isSuccess property.
	 * 
	 */
	public void setIsSuccess(boolean value) {
		this.isSuccess = value;
	}

	/**
	 * Gets the value of the failReason property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFailReason() {
		return failReason;
	}

	/**
	 * Sets the value of the failReason property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFailReason(String value) {
		this.failReason = value;
	}

}
