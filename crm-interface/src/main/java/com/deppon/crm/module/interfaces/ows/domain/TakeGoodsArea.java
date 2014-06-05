package com.deppon.crm.module.interfaces.ows.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for TakeGoodsArea complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="TakeGoodsArea">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deliveryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deliveryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receivedCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receivedName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TakeGoodsArea", namespace = "http://www.deppon.com/crm/remote/ows/domain/entity", propOrder = {
		"deliveryCode", "deliveryName", "receivedCode", "receivedName" })
public class TakeGoodsArea {

	@XmlElement(required = true)
	protected String deliveryCode;
	@XmlElement(required = true)
	protected String deliveryName;
	@XmlElement(required = true)
	protected String receivedCode;
	@XmlElement(required = true)
	protected String receivedName;

	/**
	 * Gets the value of the deliveryCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeliveryCode() {
		return deliveryCode;
	}

	/**
	 * Sets the value of the deliveryCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeliveryCode(String value) {
		this.deliveryCode = value;
	}

	/**
	 * Gets the value of the deliveryName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeliveryName() {
		return deliveryName;
	}

	/**
	 * Sets the value of the deliveryName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeliveryName(String value) {
		this.deliveryName = value;
	}

	/**
	 * Gets the value of the receivedCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceivedCode() {
		return receivedCode;
	}

	/**
	 * Sets the value of the receivedCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceivedCode(String value) {
		this.receivedCode = value;
	}

	/**
	 * Gets the value of the receivedName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceivedName() {
		return receivedName;
	}

	/**
	 * Sets the value of the receivedName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceivedName(String value) {
		this.receivedName = value;
	}

}
