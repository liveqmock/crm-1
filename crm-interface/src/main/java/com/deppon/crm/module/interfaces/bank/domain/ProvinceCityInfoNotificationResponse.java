
package com.deppon.crm.module.interfaces.bank.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ProvinceCityInfoNotificationResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ProvinceCityInfoNotificationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="successCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="failedCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="processResult" type="{http://www.deppon.com/esb/inteface/domain/payment}ProvinceCityInfoProcessResult" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProvinceCityInfoNotificationResponse", propOrder = {
    "successCount",
    "failedCount",
    "processResult"
})
public class ProvinceCityInfoNotificationResponse {

    protected int successCount;
    protected int failedCount;
    @XmlElement(required = true)
    protected List<ProvinceCityInfoProcessResult> processResult;

    /**
     * ��ȡsuccessCount���Ե�ֵ��
     * 
     */
    public int getSuccessCount() {
        return successCount;
    }

    /**
     * ����successCount���Ե�ֵ��
     * 
     */
    public void setSuccessCount(int value) {
        this.successCount = value;
    }

    /**
     * ��ȡfailedCount���Ե�ֵ��
     * 
     */
    public int getFailedCount() {
        return failedCount;
    }

    /**
     * ����failedCount���Ե�ֵ��
     * 
     */
    public void setFailedCount(int value) {
        this.failedCount = value;
    }

    /**
     * Gets the value of the processResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the processResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProvinceCityInfoProcessResult }
     * 
     * 
     */
    public List<ProvinceCityInfoProcessResult> getProcessResult() {
        if (processResult == null) {
            processResult = new ArrayList<ProvinceCityInfoProcessResult>();
        }
        return this.processResult;
    }

}
