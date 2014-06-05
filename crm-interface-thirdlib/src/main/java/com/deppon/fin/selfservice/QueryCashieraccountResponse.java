
package com.deppon.fin.selfservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ��ѯ����Ա�˺Žӿڣ����ز���
 * 
 * <p>Query_cashieraccountResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="Query_cashieraccountResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cashierAccountInformation" type="{http://www.deppon.com/fssc/remote/crm/domain}CashierAccount" maxOccurs="unbounded"/>
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
@XmlType(name = "Query_cashieraccountResponse", propOrder = {
    "cashierAccountInformation",
    "isSuccess",
    "failedReason"
})
public class QueryCashieraccountResponse {

    @XmlElement(required = true)
    protected List<CashierAccount> cashierAccountInformation;
    protected boolean isSuccess;
    protected String failedReason;

    /**
     * Gets the value of the cashierAccountInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cashierAccountInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCashierAccountInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CashierAccount }
     * 
     * 
     */
    public List<CashierAccount> getCashierAccountInformation() {
        if (cashierAccountInformation == null) {
            cashierAccountInformation = new ArrayList<CashierAccount>();
        }
        return this.cashierAccountInformation;
    }

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
