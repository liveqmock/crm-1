
package com.deppon.crm.module.interfaces.cc.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CreateCallCenterCustomerResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="CreateCallCenterCustomerResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ifSuccess" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="errorMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateCallCenterCustomerResponse", propOrder = {
    "ifSuccess",
    "errorMsg"
})
public class CreateCallCenterCustomerResponse {

    @XmlElement(required = true)
    protected String ifSuccess;
    @XmlElement(required = true)
    protected String errorMsg;

    /**
     * ��ȡifSuccess���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIfSuccess() {
        return ifSuccess;
    }

    /**
     * ����ifSuccess���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIfSuccess(String value) {
        this.ifSuccess = value;
    }

    /**
     * ��ȡerrorMsg���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * ����errorMsg���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMsg(String value) {
        this.errorMsg = value;
    }

}
