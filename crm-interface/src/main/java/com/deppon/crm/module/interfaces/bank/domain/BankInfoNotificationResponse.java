
package com.deppon.crm.module.interfaces.bank.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BankInfoNotificationResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="BankInfoNotificationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="successCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="failedCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="processResult" type="{http://www.deppon.com/esb/inteface/domain/payment}BankInfoProcessResult" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BankInfoNotificationResponse", propOrder = {
    "successCount",
    "failedCount",
    "processResult"
})
/**
 * 
 * @作者: 罗典
 * @时间：2012-12-21下午4:20:17
 * @描述：银行支行同步响应信息（处理结果）
 */
public class BankInfoNotificationResponse {

    protected int successCount;
    protected int failedCount;
    @XmlElement(required = true)
    protected List<BankInfoProcessResult> processResult;

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
     * {@link BankInfoProcessResult }
     * 
     * 
     */
    public List<BankInfoProcessResult> getProcessResult() {
        if (processResult == null) {
            processResult = new ArrayList<BankInfoProcessResult>();
        }
        return this.processResult;
    }

}
