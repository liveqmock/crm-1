
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
 * <p>Java class for wayBill complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wayBill">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="abnormalSign" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="arrivedDeptNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bizDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="consignee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consigneeMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consigneeNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consigneePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cubage" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="departure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destination" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="goodsName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="insuranceValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="leaveDeptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leaveDeptNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="packing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pieces" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="shipper" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="weight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wayBill", propOrder = {
    "abnormalSign",
    "arrivedDeptNum",
    "bizDate",
    "consignee",
    "consigneeMobile",
    "consigneeNumber",
    "consigneePhone",
    "cubage",
    "departure",
    "destination",
    "goodsName",
    "insuranceValue",
    "leaveDeptName",
    "leaveDeptNum",
    "packing",
    "pieces",
    "shipper",
    "shipperMobile",
    "shipperNumber",
    "shipperPhone",
    "transType",
    "weight"
})
public class WayBill {

    protected boolean abnormalSign;
    protected String arrivedDeptNum;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date bizDate;
    protected String consignee;
    protected String consigneeMobile;
    protected String consigneeNumber;
    protected String consigneePhone;
    protected BigDecimal cubage;
    protected String departure;
    protected String destination;
    protected String goodsName;
    protected BigDecimal insuranceValue;
    protected String leaveDeptName;
    protected String leaveDeptNum;
    protected String packing;
    protected Integer pieces;
    protected String shipper;
    protected String shipperMobile;
    protected String shipperNumber;
    protected String shipperPhone;
    protected Integer transType;
    protected BigDecimal weight;

    /**
     * Gets the value of the abnormalSign property.
     * 
     */
    public boolean isAbnormalSign() {
        return abnormalSign;
    }

    /**
     * Sets the value of the abnormalSign property.
     * 
     */
    public void setAbnormalSign(boolean value) {
        this.abnormalSign = value;
    }

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
     * Gets the value of the consignee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * Sets the value of the consignee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsignee(String value) {
        this.consignee = value;
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
     * Gets the value of the consigneeNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsigneeNumber() {
        return consigneeNumber;
    }

    /**
     * Sets the value of the consigneeNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsigneeNumber(String value) {
        this.consigneeNumber = value;
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
     * Gets the value of the cubage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCubage() {
        return cubage;
    }

    /**
     * Sets the value of the cubage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCubage(BigDecimal value) {
        this.cubage = value;
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
     * Gets the value of the goodsName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * Sets the value of the goodsName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsName(String value) {
        this.goodsName = value;
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
     * Gets the value of the leaveDeptName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeaveDeptName() {
        return leaveDeptName;
    }

    /**
     * Sets the value of the leaveDeptName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeaveDeptName(String value) {
        this.leaveDeptName = value;
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
     * Gets the value of the packing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPacking() {
        return packing;
    }

    /**
     * Sets the value of the packing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPacking(String value) {
        this.packing = value;
    }

    /**
     * Gets the value of the pieces property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPieces() {
        return pieces;
    }

    /**
     * Sets the value of the pieces property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPieces(Integer value) {
        this.pieces = value;
    }

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
     * Gets the value of the shipperNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperNumber() {
        return shipperNumber;
    }

    /**
     * Sets the value of the shipperNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperNumber(String value) {
        this.shipperNumber = value;
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
     * Gets the value of the transType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTransType() {
        return transType;
    }

    /**
     * Sets the value of the transType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTransType(Integer value) {
        this.transType = value;
    }

    /**
     * Gets the value of the weight property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWeight(BigDecimal value) {
        this.weight = value;
    }

}
