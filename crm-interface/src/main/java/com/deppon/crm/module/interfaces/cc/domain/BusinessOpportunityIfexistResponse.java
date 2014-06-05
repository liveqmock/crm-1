
package com.deppon.crm.module.interfaces.cc.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BusinessOpportunityIfexistResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="BusinessOpportunityIfexistResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ifHasBusinessOpportunity" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="errorMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessOpportunityIfexistResponse", propOrder = {
    "ifHasBusinessOpportunity",
    "errorMessage"
})
public class BusinessOpportunityIfexistResponse {

    protected boolean ifHasBusinessOpportunity;
    @XmlElement(required = true)
    protected String errorMessage;

    /**
     * ��ȡifHasBusinessOpportunity���Ե�ֵ��
     * 
     */
    public boolean isIfHasBusinessOpportunity() {
        return ifHasBusinessOpportunity;
    }

    /**
     * ����ifHasBusinessOpportunity���Ե�ֵ��
     * 
     */
    public void setIfHasBusinessOpportunity(boolean value) {
        this.ifHasBusinessOpportunity = value;
    }

    /**
     * ��ȡerrorMessage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * ����errorMessage���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

}
