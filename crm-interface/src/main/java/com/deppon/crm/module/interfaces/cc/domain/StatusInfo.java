
package com.deppon.crm.module.interfaces.cc.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>StatusInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="StatusInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="statusId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="timeStamp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusInfo", namespace = "http://www.deppon.com/esb/header", propOrder = {
    "statusId",
    "timeStamp"
})
public class StatusInfo {

    @XmlElement(required = true)
    protected String statusId;
    protected long timeStamp;

    /**
     * ��ȡstatusId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusId() {
        return statusId;
    }

    /**
     * ����statusId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusId(String value) {
        this.statusId = value;
    }

    /**
     * ��ȡtimeStamp���Ե�ֵ��
     * 
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * ����timeStamp���Ե�ֵ��
     * 
     */
    public void setTimeStamp(long value) {
        this.timeStamp = value;
    }

}
