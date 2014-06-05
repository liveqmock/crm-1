
package com.deppon.foss.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 银行支付信息
 * 
 * <p>BankPayInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BankPayInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payWay" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountProperty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "accountProperty",
    "accountNumber",
    "bankCode",
    "bankName",
    "subbranchCode",
    "subbranchName",
    "bankProvinceCode",
    "bankProvinceName",
    "bankCityCode",
    "bankCityName"
})
public class BankPayInfo {

    @XmlElement(required = true)
    protected String payWay;
    protected String accountName;
    protected String accountProperty;
    protected String accountNumber;
    protected String bankCode;
    protected String bankName;
    protected String subbranchCode;
    protected String subbranchName;
    protected String bankProvinceCode;
    protected String bankProvinceName;
    protected String bankCityCode;
    protected String bankCityName;

    /**
     * 获取payWay属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayWay() {
        return payWay;
    }

    /**
     * 设置payWay属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayWay(String value) {
        this.payWay = value;
    }

    /**
     * 获取accountName属性的值。
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
     * 设置accountName属性的值。
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
     * 获取accountProperty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountProperty() {
        return accountProperty;
    }

    /**
     * 设置accountProperty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountProperty(String value) {
        this.accountProperty = value;
    }

    /**
     * 获取accountNumber属性的值。
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
     * 设置accountNumber属性的值。
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
     * 获取bankCode属性的值。
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
     * 设置bankCode属性的值。
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
     * 获取bankName属性的值。
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
     * 设置bankName属性的值。
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
     * 获取subbranchCode属性的值。
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
     * 设置subbranchCode属性的值。
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
     * 获取subbranchName属性的值。
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
     * 设置subbranchName属性的值。
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
     * 获取bankProvinceCode属性的值。
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
     * 设置bankProvinceCode属性的值。
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
     * 获取bankProvinceName属性的值。
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
     * 设置bankProvinceName属性的值。
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
     * 获取bankCityCode属性的值。
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
     * 设置bankCityCode属性的值。
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
     * 获取bankCityName属性的值。
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
     * 设置bankCityName属性的值。
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
