
package com.deppon.crm.module.interfaces.cc.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MarketingInfoQueryRequest complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="MarketingInfoQueryRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="custNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="limit" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MarketingInfoQueryRequest", propOrder = {
    "custNumber",
    "start",
    "limit"
})
public class MarketingInfoQueryRequest {

    @XmlElement(required = true)
    protected String custNumber;
    //当前页
    protected int start;
    //页大小
    protected int limit;

    /**
     * ��ȡcustNumber���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCustNumber() {
        return custNumber;
    }

    /**
     * ����custNumber���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCustNumber(String value) {
        this.custNumber = value;
    }

    /**
     * ��ȡstart���Ե�ֵ��
     *
     */
    public int getStart() {
        return start;
    }

    /**
     * ����start���Ե�ֵ��
     *
     */
    public void setStart(int value) {
        this.start = value;
    }

    /**
     * ��ȡlimit���Ե�ֵ��
     *
     */
    public int getLimit() {
        return limit;
    }

    /**
     * ����limit���Ե�ֵ��
     *
     */
    public void setLimit(int value) {
        this.limit = value;
    }

}
