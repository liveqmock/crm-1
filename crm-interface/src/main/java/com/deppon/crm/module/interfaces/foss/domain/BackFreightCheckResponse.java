
package com.deppon.crm.module.interfaces.foss.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BackFreightCheckResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="BackFreightCheckResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="checkResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BackFreightCheckResponse", propOrder = {
    "checkResult"
})
public class BackFreightCheckResponse {

    protected boolean checkResult;

    /**
     * ��ȡcheckResult���Ե�ֵ��
     * 
     */
    public boolean isCheckResult() {
        return checkResult;
    }

    /**
     * ����checkResult���Ե�ֵ��
     * 
     */
    public void setCheckResult(boolean value) {
        this.checkResult = value;
    }

}
