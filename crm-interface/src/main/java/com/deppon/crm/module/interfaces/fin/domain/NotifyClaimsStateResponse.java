
package com.deppon.crm.module.interfaces.fin.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Notify_claims_stateResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="Notify_claims_stateResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isSuccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="failedReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Notify_claims_stateResponse", propOrder = {
    "isSuccess",
    "failedReason"
})
public class NotifyClaimsStateResponse {

    protected boolean isSuccess;
    protected String failedReason;

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
     * ��ȡfailedReason���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFailedReason() {
        return failedReason;
    }

    /**
     * ����failedReason���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFailedReason(String value) {
        this.failedReason = value;
    }

}
