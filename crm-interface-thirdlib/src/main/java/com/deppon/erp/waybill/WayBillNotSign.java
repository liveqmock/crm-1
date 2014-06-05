
package com.deppon.erp.waybill;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wayBillNotSign complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wayBillNotSign">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="shipper" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wayBillNotSign", propOrder = {
    "shipper",
    "shipperAddr",
    "shipperMobile",
    "shipperPhone"
})
public class WayBillNotSign {

    protected String shipper;
    protected String shipperAddr;
    protected String shipperMobile;
    protected String shipperPhone;

    /**
     * Gets the value of the shipper property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipper() {
        return shipper;
    }

    /**
     * Sets the value of the shipper property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipper(String value) {
        this.shipper = value;
    }

    /**
     * Gets the value of the shipperAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperAddr() {
        return shipperAddr;
    }

    /**
     * Sets the value of the shipperAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperAddr(String value) {
        this.shipperAddr = value;
    }

    /**
     * Gets the value of the shipperMobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperMobile() {
        return shipperMobile;
    }

    /**
     * Sets the value of the shipperMobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperMobile(String value) {
        this.shipperMobile = value;
    }

    /**
     * Gets the value of the shipperPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperPhone() {
        return shipperPhone;
    }

    /**
     * Sets the value of the shipperPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperPhone(String value) {
        this.shipperPhone = value;
    }

}
