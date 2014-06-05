
package com.deppon.crm.module.interfaces.fin.domain;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ֪ͨ����֧��״̬�ӿ�,�������
 * 
 * <p>Notify_claims_stateRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="Notify_claims_stateRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="departmentCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentMoney" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="voucherNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentResults" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "Notify_claims_stateRequest", propOrder = {
    "departmentCode",
    "paymentMoney",
    "voucherNumber",
    "paymentResults",
    "failedReason"
})
public class NotifyClaimsStateRequest {

    @XmlElement(required = true)
    protected String departmentCode;
    @XmlElement(required = true)
    protected BigDecimal paymentMoney;
    @XmlElement(required = true)
    protected String voucherNumber;
    @XmlElement(required = true)
    protected String paymentResults;
    protected String failedReason;

    /**
     * ��ȡdepartmentCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * ����departmentCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartmentCode(String value) {
        this.departmentCode = value;
    }

    /**
     * ��ȡpaymentMoney���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPaymentMoney() {
        return paymentMoney;
    }

    /**
     * ����paymentMoney���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPaymentMoney(BigDecimal value) {
        this.paymentMoney = value;
    }

    /**
     * ��ȡvoucherNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoucherNumber() {
        return voucherNumber;
    }

    /**
     * ����voucherNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoucherNumber(String value) {
        this.voucherNumber = value;
    }

    /**
     * ��ȡpaymentResults���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentResults() {
        return paymentResults;
    }

    /**
     * ����paymentResults���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentResults(String value) {
        this.paymentResults = value;
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
