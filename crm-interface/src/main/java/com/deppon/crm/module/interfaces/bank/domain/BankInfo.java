
package com.deppon.crm.module.interfaces.bank.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BankInfo complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="BankInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bankId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="superiorBankId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bankName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="provenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cityId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastUpdateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="isCancel" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BankInfo", propOrder = {
    "bankId",
    "superiorBankId",
    "bankName",
    "provenceId",
    "cityId",
    "operateCode",
    "lastUpdateTime",
    "isCancel"
})
/**
 * @作者: 罗典
 * @时间：2012-12-21下午4:17:09
 * @描述：银行支行详情
 */
public class BankInfo {
	// 银行编码
    @XmlElement(required = true)
    protected String bankId;
    // 上级银行编码
    @XmlElement(required = true)
    protected String superiorBankId;
    // 银行名称
    @XmlElement(required = true)
    protected String bankName;
    // 省份编码
    protected String provenceId;
    // 城市编码
    protected String cityId;
    // 操作类别, 1-新增; 2-修改; 3-作废
    @XmlElement(required = true)
    protected String operateCode;
    // 更新时间
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date lastUpdateTime;
    // 是否作废, 0-作废; 1-不作废
    protected boolean isCancel;

    /**
     * ��ȡbankId���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBankId() {
        return bankId;
    }

    /**
     * ����bankId���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBankId(String value) {
        this.bankId = value;
    }

    /**
     * ��ȡsuperiorBankId���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSuperiorBankId() {
        return superiorBankId;
    }

    /**
     * ����superiorBankId���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSuperiorBankId(String value) {
        this.superiorBankId = value;
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
     * ��ȡprovenceId���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProvenceId() {
        return provenceId;
    }

    /**
     * ����provenceId���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProvenceId(String value) {
        this.provenceId = value;
    }

    /**
     * ��ȡcityId���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * ����cityId���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCityId(String value) {
        this.cityId = value;
    }

    /**
     * ��ȡoperateCode���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOperateCode() {
        return operateCode;
    }

    /**
     * ����operateCode���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOperateCode(String value) {
        this.operateCode = value;
    }

    /**
     * ��ȡlastUpdateTime���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * ����lastUpdateTime���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setLastUpdateTime(Date value) {
        this.lastUpdateTime = value;
    }

    /**
     * ��ȡisCancel���Ե�ֵ��
     *
     */
    public boolean isIsCancel() {
        return isCancel;
    }

    /**
     * ����isCancel���Ե�ֵ��
     *
     */
    public void setIsCancel(boolean value) {
        this.isCancel = value;
    }

}
