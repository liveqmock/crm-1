
package com.deppon.crm.test.client.esb.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * �����˵�������Ϣ
 * 
 * <p>BindOrderRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
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
},namespace="http://www.deppon.com/crm/domain")
public class BindOrderRequest {

    protected String oldWayBillNumber;
    @XmlElement(required = true)
    protected String newWayBillNumber;
    @XmlElement(required = true)
    protected String orderNo;

    /**
     * ��ȡoldWayBillNumber���Ե�ֵ��
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
     * ����oldWayBillNumber���Ե�ֵ��
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
     * ��ȡnewWayBillNumber���Ե�ֵ��
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
     * ����newWayBillNumber���Ե�ֵ��
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
     * ��ȡorderNo���Ե�ֵ��
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
     * ����orderNo���Ե�ֵ��
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
