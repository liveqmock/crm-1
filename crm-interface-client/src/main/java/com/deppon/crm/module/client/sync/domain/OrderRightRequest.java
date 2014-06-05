
package com.deppon.crm.module.client.sync.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Ӫҵ����
 * 
 * <p>orderRightRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="orderRightRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderRightInfo" type="{http://www.deppon.com/crm/inteface/foss/domain}OrderRightInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderRightRequest", propOrder = {
    "orderRightInfo"
})
public class OrderRightRequest {

    @XmlElement(required = true)
    protected OrderRightInfo orderRightInfo;

    /**
     * ��ȡorderRightInfo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link OrderRightInfo }
     *     
     */
    public OrderRightInfo getOrderRightInfo() {
        return orderRightInfo;
    }

    /**
     * ����orderRightInfo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link OrderRightInfo }
     *     
     */
    public void setOrderRightInfo(OrderRightInfo value) {
        this.orderRightInfo = value;
    }

}
