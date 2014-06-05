
package com.deppon.erp.waybill;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wayBillCustomer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wayBillCustomer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consigneeNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wayBillCustomer", propOrder = {
    "consigneeNum",
    "shipperNum"
})
public class WayBillCustomer {

    protected String consigneeNum;
    protected String shipperNum;

    /**
     * Gets the value of the consigneeNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsigneeNum() {
        return consigneeNum;
    }

    /**
     * Sets the value of the consigneeNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsigneeNum(String value) {
        this.consigneeNum = value;
    }

    /**
     * Gets the value of the shipperNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperNum() {
        return shipperNum;
    }

    /**
     * Sets the value of the shipperNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperNum(String value) {
        this.shipperNum = value;
    }

}
