
package com.deppon.erp.custAndOrder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for openBank complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="openBank">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accounts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankserid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crmid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "openBank", propOrder = {
    "accounts",
    "bankid",
    "bankserid",
    "crmid",
    "parentid"
})
public class OpenBank {

    protected String accounts;
    protected String bankid;
    protected String bankserid;
    protected String crmid;
    protected String parentid;

    /**
     * Gets the value of the accounts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccounts() {
        return accounts;
    }

    /**
     * Sets the value of the accounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccounts(String value) {
        this.accounts = value;
    }

    /**
     * Gets the value of the bankid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankid() {
        return bankid;
    }

    /**
     * Sets the value of the bankid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankid(String value) {
        this.bankid = value;
    }

    /**
     * Gets the value of the bankserid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankserid() {
        return bankserid;
    }

    /**
     * Sets the value of the bankserid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankserid(String value) {
        this.bankserid = value;
    }

    /**
     * Gets the value of the crmid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmid() {
        return crmid;
    }

    /**
     * Sets the value of the crmid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmid(String value) {
        this.crmid = value;
    }

    /**
     * Gets the value of the parentid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentid() {
        return parentid;
    }

    /**
     * Sets the value of the parentid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentid(String value) {
        this.parentid = value;
    }

}
