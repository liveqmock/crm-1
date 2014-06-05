package com.deppon.crm.module.interfaces.foss.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * 部门标杆编码：用户点击FOSS界面手动解锁，传递当前操作部门的部门标杆编码（单条）
 * 
 * 
 * <p>
 * Java class for ModifyOrderLockInfoRequest complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ModifyOrderLockInfoRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deptCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModifyOrderLockInfoRequest", propOrder = { "deptCode" })
public class ModifyOrderLockInfoRequest {

	@XmlElement(required = true)
	protected String deptCode;

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

}
