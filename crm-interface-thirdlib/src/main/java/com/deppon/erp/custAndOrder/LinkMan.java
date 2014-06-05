
package com.deppon.erp.custAndOrder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for linkMan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="linkMan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="crmid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobiletel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "linkMan", propOrder = {
    "crmid",
    "customerid",
    "mobiletel",
    "number",
    "status"
})
public class LinkMan {

    protected String crmid;
    protected String customerid;
    protected String mobiletel;
    protected String number;
    protected String status;

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
     * Gets the value of the customerid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerid() {
        return customerid;
    }

    /**
     * Sets the value of the customerid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerid(String value) {
        this.customerid = value;
    }

    /**
     * Gets the value of the mobiletel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobiletel() {
        return mobiletel;
    }

    /**
     * Sets the value of the mobiletel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobiletel(String value) {
        this.mobiletel = value;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumber(String value) {
        this.number = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
