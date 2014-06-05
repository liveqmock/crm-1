
package com.deppon.crm.module.interfaces.bank.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BankInfoNotificationRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="BankInfoNotificationRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bankInfo" type="{http://www.deppon.com/esb/inteface/domain/payment}BankInfo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BankInfoNotificationRequest", propOrder = {
    "bankInfo"
})
/**
 * @作者: 罗典
 * @时间：2012-12-21下午4:16:44
 * @描述：银行支行请求信息
 */
public class BankInfoNotificationRequest {

    @XmlElement(required = true)
    protected List<BankInfo> bankInfo;

    /**
     * Gets the value of the bankInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bankInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBankInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BankInfo }
     * 
     * 
     */
    public List<BankInfo> getBankInfo() {
        if (bankInfo == null) {
            bankInfo = new ArrayList<BankInfo>();
        }
        return this.bankInfo;
    }

}
