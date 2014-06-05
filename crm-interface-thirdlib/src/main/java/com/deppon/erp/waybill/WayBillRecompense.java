
package com.deppon.erp.waybill;

import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for wayBillRecompense complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wayBillRecompense">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arrivedDeptNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bizDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="consigneeMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consigneePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destination" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="insuranceValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="leaveDeptNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transProperty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wayBillRecompense", propOrder = {
    "arrivedDeptNum",
    "bizDate",
    "consigneeMobile",
    "consigneePhone",
    "departure",
    "destination",
    "insuranceValue",
    "leaveDeptNum",
    "shipperMobile",
    "shipperPhone",
    "transProperty"
})
public class WayBillRecompense {

    protected String arrivedDeptNum;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date bizDate;
    protected String consigneeMobile;
    protected String consigneePhone;
    protected String departure;
    protected String destination;
    protected BigDecimal insuranceValue;
    protected String leaveDeptNum;
    protected String shipperMobile;
    protected String shipperPhone;
    protected String transProperty;

    /**
     * Gets the value of the arrivedDeptNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivedDeptNum() {
        return arrivedDeptNum;
    }

    /**
     * Sets the value of the arrivedDeptNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivedDeptNum(String value) {
        this.arrivedDeptNum = value;
    }

    /**
     * Gets the value of the bizDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getBizDate() {
        return bizDate;
    }

    /**
     * Sets the value of the bizDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBizDate(Date value) {
        this.bizDate = value;
    }

    /**
     * Gets the value of the consigneeMobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    /**
     * Sets the value of the consigneeMobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsigneeMobile(String value) {
        this.consigneeMobile = value;
    }

    /**
     * Gets the value of the consigneePhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsigneePhone() {
        return consigneePhone;
    }

    /**
     * Sets the value of the consigneePhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsigneePhone(String value) {
        this.consigneePhone = value;
    }

    /**
     * Gets the value of the departure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeparture() {
        return departure;
    }

    /**
     * Sets the value of the departure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeparture(String value) {
        this.departure = value;
    }

    /**
     * Gets the value of the destination property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the value of the destination property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestination(String value) {
        this.destination = value;
    }

    /**
     * Gets the value of the insuranceValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInsuranceValue() {
        return insuranceValue;
    }

    /**
     * Sets the value of the insuranceValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInsuranceValue(BigDecimal value) {
        this.insuranceValue = value;
    }

    /**
     * Gets the value of the leaveDeptNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeaveDeptNum() {
        return leaveDeptNum;
    }

    /**
     * Sets the value of the leaveDeptNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeaveDeptNum(String value) {
        this.leaveDeptNum = value;
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

    /**
     * Gets the value of the transProperty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransProperty() {
        return transProperty;
    }

    /**
     * Sets the value of the transProperty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransProperty(String value) {
        this.transProperty = value;
    }

}
