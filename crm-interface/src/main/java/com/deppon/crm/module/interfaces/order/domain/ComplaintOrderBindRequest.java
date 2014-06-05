package com.deppon.crm.module.interfaces.order.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ComplaintOrderBindRequest complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ComplaintOrderBindRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="senderOrConsignee" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="billnumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="clientRequest" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplaintOrderBindRequest", propOrder = { "name",
		"senderOrConsignee", "phone", "billnumber", "content", "clientRequest" })
public class ComplaintOrderBindRequest {

	@XmlElement(required = true)
	protected String name;
	@XmlElement(required = true)
	protected String senderOrConsignee;
	@XmlElement(required = true)
	protected String phone;
	@XmlElement(required = true)
	protected String billnumber;
	@XmlElement(required = true)
	protected String content;
	@XmlElement(required = true)
	protected String clientRequest;

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the value of the senderOrConsignee property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSenderOrConsignee() {
		return senderOrConsignee;
	}

	/**
	 * Sets the value of the senderOrConsignee property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSenderOrConsignee(String value) {
		this.senderOrConsignee = value;
	}

	/**
	 * Gets the value of the phone property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the value of the phone property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPhone(String value) {
		this.phone = value;
	}

	/**
	 * Gets the value of the billnumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBillnumber() {
		return billnumber;
	}

	/**
	 * Sets the value of the billnumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBillnumber(String value) {
		this.billnumber = value;
	}

	/**
	 * Gets the value of the content property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the value of the content property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContent(String value) {
		this.content = value;
	}

	/**
	 * Gets the value of the clientRequest property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getClientRequest() {
		return clientRequest;
	}

	/**
	 * Sets the value of the clientRequest property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setClientRequest(String value) {
		this.clientRequest = value;
	}

}
