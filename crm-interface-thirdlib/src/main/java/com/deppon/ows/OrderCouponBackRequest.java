package com.deppon.ows;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for orderCouponBackRequest complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="orderCouponBackRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="couponNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderCouponBackRequest", namespace = "http://www.deppon.com/ows/inteface/domain/coupon", propOrder = {
		"couponNumber", "orderNumber", "status" })
public class OrderCouponBackRequest {

	@XmlElement(required = true)
	protected String couponNumber;
	@XmlElement(required = true)
	protected String orderNumber;
	@XmlElement(required = true)
	protected String status;

	/**
	 * Gets the value of the couponNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCouponNumber() {
		return couponNumber;
	}

	/**
	 * Sets the value of the couponNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCouponNumber(String value) {
		this.couponNumber = value;
	}

	/**
	 * Gets the value of the orderNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * Sets the value of the orderNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrderNumber(String value) {
		this.orderNumber = value;
	}

	/**
	 * Gets the value of the status property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStatus(String value) {
		this.status = value;
	}

}
