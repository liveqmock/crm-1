
package com.deppon.foss.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 订单运单管理信息
 * 
 * <p>BindOrderRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BindOrderRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="oldWayBillNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newWayBillNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BindOrderRequest", propOrder = {
    "oldWayBillNumber",
    "newWayBillNumber",
    "orderNo"
})
public class BindOrderRequest {

    protected String oldWayBillNumber;
    @XmlElement(required = true)
    protected String newWayBillNumber;
    @XmlElement(required = true)
    protected String orderNo;

    /**
     * 获取oldWayBillNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldWayBillNumber() {
        return oldWayBillNumber;
    }

    /**
     * 设置oldWayBillNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldWayBillNumber(String value) {
        this.oldWayBillNumber = value;
    }

    /**
     * 获取newWayBillNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewWayBillNumber() {
        return newWayBillNumber;
    }

    /**
     * 设置newWayBillNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewWayBillNumber(String value) {
        this.newWayBillNumber = value;
    }

    /**
     * 获取orderNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置orderNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderNo(String value) {
        this.orderNo = value;
    }

}
