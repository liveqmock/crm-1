package com.deppon.crm.module.interfaces.ows.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for StatusInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="StatusInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="statusId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="timeStamp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusInfo", propOrder = { "statusId", "timeStamp" })
public class StatusInfo {

	@XmlElement(required = true)
	protected String statusId;
	protected long timeStamp;

	/**
	 * Gets the value of the statusId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStatusId() {
		return statusId;
	}

	/**
	 * Sets the value of the statusId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStatusId(String value) {
		this.statusId = value;
	}

	/**
	 * Gets the value of the timeStamp property.
	 * 
	 */
	public long getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Sets the value of the timeStamp property.
	 * 
	 */
	public void setTimeStamp(long value) {
		this.timeStamp = value;
	}

}
