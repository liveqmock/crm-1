
package com.deppon.foss.crm;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 发到货金额
 * 
 * <p>AmountInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AmountInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="waybillNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isSender" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="custType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prePayment" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="arrivePayment" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="serviceFee" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="refund" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AmountInfo", propOrder = {
    "waybillNo",
    "isSender",
    "custType",
    "prePayment",
    "arrivePayment",
    "serviceFee",
    "refund"
})
public class AmountInfo {

    @XmlElement(required = true)
    protected String waybillNo;
    protected boolean isSender;
    @XmlElement(required = true)
    protected String custType;
    @XmlElement(required = true)
    protected BigDecimal prePayment;
    @XmlElement(required = true)
    protected BigDecimal arrivePayment;
    @XmlElement(required = true)
    protected BigDecimal serviceFee;
    @XmlElement(required = true)
    protected BigDecimal refund;

    /**
     * 获取waybillNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaybillNo() {
        return waybillNo;
    }

    /**
     * 设置waybillNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaybillNo(String value) {
        this.waybillNo = value;
    }

    /**
     * 获取isSender属性的值。
     * 
     */
    public boolean isIsSender() {
        return isSender;
    }

    /**
     * 设置isSender属性的值。
     * 
     */
    public void setIsSender(boolean value) {
        this.isSender = value;
    }

    /**
     * 获取custType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustType() {
        return custType;
    }

    /**
     * 设置custType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustType(String value) {
        this.custType = value;
    }

    /**
     * 获取prePayment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrePayment() {
        return prePayment;
    }

    /**
     * 设置prePayment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrePayment(BigDecimal value) {
        this.prePayment = value;
    }

    /**
     * 获取arrivePayment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getArrivePayment() {
        return arrivePayment;
    }

    /**
     * 设置arrivePayment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setArrivePayment(BigDecimal value) {
        this.arrivePayment = value;
    }

    /**
     * 获取serviceFee属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    /**
     * 设置serviceFee属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setServiceFee(BigDecimal value) {
        this.serviceFee = value;
    }

    /**
     * 获取refund属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRefund() {
        return refund;
    }

    /**
     * 设置refund属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRefund(BigDecimal value) {
        this.refund = value;
    }

}
