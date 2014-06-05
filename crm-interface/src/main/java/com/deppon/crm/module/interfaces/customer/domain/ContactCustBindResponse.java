
package com.deppon.crm.module.interfaces.customer.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Contact_cust_bindResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="Contact_cust_bindResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isSuccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="failReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Contact_cust_bindResponse", propOrder = {
    "isSuccess",
    "failReason"
})
public class ContactCustBindResponse {

    protected boolean isSuccess;
    protected String failReason;

    /**
     * ��ȡisSuccess���Ե�ֵ��
     * 
     */
    public boolean isIsSuccess() {
        return isSuccess;
    }

    /**
     * ����isSuccess���Ե�ֵ��
     * 
     */
    public void setIsSuccess(boolean value) {
        this.isSuccess = value;
    }

    /**
     * ��ȡfailReason���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFailReason() {
        return failReason;
    }

    /**
     * ����failReason���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFailReason(String value) {
        this.failReason = value;
    }

}
