
package com.deppon.fin.selfservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CashierAccount complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="CashierAccount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bankProvinceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bankCityCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bankBranchCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bankCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="account" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountNature" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="openBankUserName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CashierAccount", propOrder = {
    "bankProvinceCode",
    "bankCityCode",
    "bankBranchCode",
    "bankCode",
    "account",
    "accountNature",
    "mobile",
    "openBankUserName"
})
public class CashierAccount {

    @XmlElement(required = true)
    protected String bankProvinceCode;
    @XmlElement(required = true)
    protected String bankCityCode;
    @XmlElement(required = true)
    protected String bankBranchCode;
    @XmlElement(required = true)
    protected String bankCode;
    @XmlElement(required = true)
    protected String account;
    @XmlElement(required = true)
    protected String accountNature;
    @XmlElement(required = true)
    protected String mobile;
    @XmlElement(required = true)
    protected String openBankUserName;

    /**
     * ��ȡbankProvinceCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankProvinceCode() {
        return bankProvinceCode;
    }

    /**
     * ����bankProvinceCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankProvinceCode(String value) {
        this.bankProvinceCode = value;
    }

    /**
     * ��ȡbankCityCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCityCode() {
        return bankCityCode;
    }

    /**
     * ����bankCityCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCityCode(String value) {
        this.bankCityCode = value;
    }

    /**
     * ��ȡbankBranchCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankBranchCode() {
        return bankBranchCode;
    }

    /**
     * ����bankBranchCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankBranchCode(String value) {
        this.bankBranchCode = value;
    }

    /**
     * ��ȡbankCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * ����bankCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCode(String value) {
        this.bankCode = value;
    }

    /**
     * ��ȡaccount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccount() {
        return account;
    }

    /**
     * ����account���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccount(String value) {
        this.account = value;
    }

    /**
     * ��ȡaccountNature���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNature() {
        return accountNature;
    }

    /**
     * ����accountNature���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNature(String value) {
        this.accountNature = value;
    }

    /**
     * ��ȡmobile���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * ����mobile���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

    /**
     * ��ȡopenBankUserName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpenBankUserName() {
        return openBankUserName;
    }

    /**
     * ����openBankUserName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpenBankUserName(String value) {
        this.openBankUserName = value;
    }

}
