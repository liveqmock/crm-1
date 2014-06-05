package com.deppon.crm.module.interfaces.ows.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for CreateNewCouponResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="CreateNewCouponResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="couponCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateNewCouponResponse", namespace = "http://www.deppon.com/crm/remote/ows/domain/entity", propOrder = { "couponCode" })
public class CreateNewCouponResponse {

	@XmlElement(required = true)
	protected String couponCode;

	/**
	 * Gets the value of the couponCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCouponCode() {
		return couponCode;
	}

	/**
	 * Sets the value of the couponCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCouponCode(String value) {
		this.couponCode = value;
	}

}
