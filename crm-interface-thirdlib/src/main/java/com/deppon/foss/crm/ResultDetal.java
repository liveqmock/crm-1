package com.deppon.foss.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ResultDetal complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ResultDetal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deptCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resultCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultDetal", propOrder = { "deptCode", "resultCode", "reason" })
public class ResultDetal {

	@XmlElement(required = true)
	protected String deptCode;
	@XmlElement(required = true)
	protected String resultCode;
	@XmlElement(required = true)
	protected String reason;

	/**
	 * Gets the value of the deptCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * Sets the value of the deptCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeptCode(String value) {
		this.deptCode = value;
	}

	/**
	 * Gets the value of the resultCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResultCode() {
		return resultCode;
	}

	/**
	 * Sets the value of the resultCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResultCode(String value) {
		this.resultCode = value;
	}

	/**
	 * Gets the value of the reason property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * Sets the value of the reason property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReason(String value) {
		this.reason = value;
	}

}
