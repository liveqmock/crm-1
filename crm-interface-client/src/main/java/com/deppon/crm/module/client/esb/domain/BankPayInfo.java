
package com.deppon.crm.module.client.esb.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * ����֧����Ϣ
 * 
 * <p>BankPayInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="BankPayInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payWay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subbranchCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subbranchName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankProvinceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankProvinceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankCityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankCityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BankPayInfo", propOrder = {
    "payWay",
    "accountName",
    "accountNumber",
    "accountProperty",
    "bankCode",
    "bankName",
    "subbranchCode",
    "subbranchName",
    "bankProvinceCode",
    "bankProvinceName",
    "bankCityCode",
    "bankCityName"
})
/**
 * @作者：罗典
 * @描述：银行支付信息
 * @时间：2012-12-13
 * */
public class BankPayInfo {
	//支付方式：1-电汇，2-不限
    protected int payWay;
    // 开户人姓名
    protected String accountName;
    // 开户人账号
    protected String accountNumber;
    // 开户行编码
    protected String bankCode;
    // 开户行名称
    protected String bankName;
    // 支行编码
    protected String subbranchCode;
    // 支行名称
    protected String subbranchName;
    // 开户行所在省份编码
    protected String bankProvinceCode;
    // 开户行所在省份名称
    protected String bankProvinceName;
    // 开户行所在城市编码
    protected String bankCityCode;
    // 开户行所在城市名称
    protected String bankCityName;
    // 账户性质账户性质:(PUBLIC_ACCOUNT-对公账户、PRIVATE_ACCOUNT-对私账户、BACKUP_ACCOUNT-收银员账户)
    protected String accountProperty;
    /**
     * ��ȡpayWay���Ե�ֵ��
     * 
     */
    public int getPayWay() {
        return payWay;
    }

    /**
     * ����payWay���Ե�ֵ��
     * 
     */
    public void setPayWay(int value) {
        this.payWay = value;
    }

    public String getAccountProperty() {
		return accountProperty;
	}

	public void setAccountProperty(String accountProperty) {
		this.accountProperty = accountProperty;
	}

	/**
     * ��ȡaccountName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * ����accountName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountName(String value) {
        this.accountName = value;
    }

    /**
     * ��ȡaccountNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * ����accountNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
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
     * ��ȡbankName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * ����bankName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankName(String value) {
        this.bankName = value;
    }

    /**
     * ��ȡsubbranchCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubbranchCode() {
        return subbranchCode;
    }

    /**
     * ����subbranchCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubbranchCode(String value) {
        this.subbranchCode = value;
    }

    /**
     * ��ȡsubbranchName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubbranchName() {
        return subbranchName;
    }

    /**
     * ����subbranchName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubbranchName(String value) {
        this.subbranchName = value;
    }

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
     * ��ȡbankProvinceName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankProvinceName() {
        return bankProvinceName;
    }

    /**
     * ����bankProvinceName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankProvinceName(String value) {
        this.bankProvinceName = value;
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
     * ��ȡbankCityName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCityName() {
        return bankCityName;
    }

    /**
     * ����bankCityName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCityName(String value) {
        this.bankCityName = value;
    }

}
