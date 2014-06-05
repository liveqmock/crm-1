
package com.deppon.erp.waybill;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for custAmount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="custAmount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="agentReceivePay" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="arrivedAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="consignee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="prepayAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="refundRebate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="shipper" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wayBillNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "custAmount", propOrder = {
    "agentReceivePay",
    "arrivedAmount",
    "consignee",
    "custType",
    "prepayAmount",
    "refundRebate",
    "shipper",
    "wayBillNum"
})
public class CustAmount {

    protected BigDecimal agentReceivePay;
    protected BigDecimal arrivedAmount;
    protected String consignee;
    protected Integer custType;
    protected BigDecimal prepayAmount;
    protected BigDecimal refundRebate;
    protected String shipper;
    protected String wayBillNum;

    /**
     * Gets the value of the agentReceivePay property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAgentReceivePay() {
        return agentReceivePay;
    }

    /**
     * Sets the value of the agentReceivePay property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAgentReceivePay(BigDecimal value) {
        this.agentReceivePay = value;
    }

    /**
     * Gets the value of the arrivedAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getArrivedAmount() {
        return arrivedAmount;
    }

    /**
     * Sets the value of the arrivedAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setArrivedAmount(BigDecimal value) {
        this.arrivedAmount = value;
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
     * Gets the value of the custType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCustType() {
        return custType;
    }

    /**
     * Sets the value of the custType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCustType(Integer value) {
        this.custType = value;
    }

    /**
     * Gets the value of the prepayAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrepayAmount() {
        return prepayAmount;
    }

    /**
     * Sets the value of the prepayAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrepayAmount(BigDecimal value) {
        this.prepayAmount = value;
    }

    /**
     * Gets the value of the refundRebate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRefundRebate() {
        return refundRebate;
    }

    /**
     * Sets the value of the refundRebate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRefundRebate(BigDecimal value) {
        this.refundRebate = value;
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
     * Gets the value of the wayBillNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWayBillNum() {
        return wayBillNum;
    }

    /**
     * Sets the value of the wayBillNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWayBillNum(String value) {
        this.wayBillNum = value;
    }

}
