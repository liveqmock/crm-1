
package com.deppon.crm.module.interfaces.customer.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>ClaimInfo complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="ClaimInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="claimType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reportDept" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="claimNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="money" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="claimWay" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reportTime" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClaimInfo", propOrder = {
    "claimType",
    "reportDept",
    "claimNumber",
    "money",
    "claimWay",
    "reportTime",
    "status"
})
public class ClaimInfo {

    @XmlElement(required = true)
    protected String claimType;
    @XmlElement(required = true)
    protected String reportDept;
    @XmlElement(required = true)
    protected String claimNumber;
    @XmlElement(required = true)
    protected String money;
    @XmlElement(required = true)
    protected String claimWay;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected Date reportTime;
    @XmlElement(required = true)
    protected String status;

    /**
     * ��ȡclaimType���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getClaimType() {
        return claimType;
    }

    /**
     * ����claimType���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setClaimType(String value) {
        this.claimType = value;
    }

    /**
     * ��ȡreportDept���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReportDept() {
        return reportDept;
    }

    /**
     * ����reportDept���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReportDept(String value) {
        this.reportDept = value;
    }

    /**
     * ��ȡclaimNumber���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getClaimNumber() {
        return claimNumber;
    }

    /**
     * ����claimNumber���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setClaimNumber(String value) {
        this.claimNumber = value;
    }

    /**
     * ��ȡmoney���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMoney() {
        return money;
    }

    /**
     * ����money���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMoney(String value) {
        this.money = value;
    }

    /**
     * ��ȡclaimWay���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getClaimWay() {
        return claimWay;
    }

    /**
     * ����claimWay���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setClaimWay(String value) {
        this.claimWay = value;
    }

    /**
     * ��ȡreportTime���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getReportTime() {
        return reportTime;
    }

    /**
     * ����reportTime���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setReportTime(Date value) {
        this.reportTime = value;
    }

    /**
     * ��ȡstatus���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * ����status���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
