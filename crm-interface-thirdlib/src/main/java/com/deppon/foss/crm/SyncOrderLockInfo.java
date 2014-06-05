package com.deppon.foss.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for SyncOrderLockInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SyncOrderLockInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deptCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="promptCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lockCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SyncOrderLockInfo", propOrder = { "deptCode", "promptCount",
		"lockCount" })
public class SyncOrderLockInfo {

	@XmlElement(required = true)
	protected String deptCode;
	protected int promptCount;
	protected int lockCount;

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
	 * Gets the value of the promptCount property.
	 * 
	 */
	public int getPromptCount() {
		return promptCount;
	}

	/**
	 * Sets the value of the promptCount property.
	 * 
	 */
	public void setPromptCount(int value) {
		this.promptCount = value;
	}

	/**
	 * Gets the value of the lockCount property.
	 * 
	 */
	public int getLockCount() {
		return lockCount;
	}

	/**
	 * Sets the value of the lockCount property.
	 * 
	 */
	public void setLockCount(int value) {
		this.lockCount = value;
	}

}
