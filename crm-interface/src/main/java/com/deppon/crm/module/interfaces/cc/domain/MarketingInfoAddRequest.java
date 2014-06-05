
package com.deppon.crm.module.interfaces.cc.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MarketingInfoAddRequest complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="MarketingInfoAddRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="custNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cellphone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telephone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="needType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="needQuestion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="solution" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="marketPerson" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="marketDept" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="marketTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MarketingInfoAddRequest", propOrder = {
    "custNumber",
    "custName",
    "cellphone",
    "telephone",
    "needType",
    "needQuestion",
    "solution",
    "marketPerson",
    "marketDept",
    "marketTime"
})
public class MarketingInfoAddRequest {

    @XmlElement(required = true)
    protected String custNumber;
    @XmlElement(required = true)
    protected String custName;
    @XmlElement(required = true)
    protected String cellphone;
    @XmlElement(required = true)
    protected String telephone;
    @XmlElement(required = true)
    protected String needType;
    @XmlElement(required = true)
    protected String needQuestion;
    @XmlElement(required = true)
    protected String solution;
    @XmlElement(required = true)
    protected String marketPerson;
    @XmlElement(required = true)
    protected String marketDept;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date marketTime;

    /**
     * ��ȡcustNumber���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCustNumber() {
        return custNumber;
    }

    /**
     * ����custNumber���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCustNumber(String value) {
        this.custNumber = value;
    }

    /**
     * ��ȡcustName���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCustName() {
        return custName;
    }

    /**
     * ����custName���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCustName(String value) {
        this.custName = value;
    }

    /**
     * ��ȡcellphone���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * ����cellphone���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCellphone(String value) {
        this.cellphone = value;
    }

    /**
     * ��ȡtelephone���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * ����telephone���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTelephone(String value) {
        this.telephone = value;
    }

    /**
     * ��ȡneedType���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNeedType() {
        return needType;
    }

    /**
     * ����needType���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNeedType(String value) {
        this.needType = value;
    }

    /**
     * ��ȡneedQuestion���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNeedQuestion() {
        return needQuestion;
    }

    /**
     * ����needQuestion���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNeedQuestion(String value) {
        this.needQuestion = value;
    }

    /**
     * ��ȡsolution���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSolution() {
        return solution;
    }

    /**
     * ����solution���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSolution(String value) {
        this.solution = value;
    }

    /**
     * ��ȡmarketPerson���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMarketPerson() {
        return marketPerson;
    }

    /**
     * ����marketPerson���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMarketPerson(String value) {
        this.marketPerson = value;
    }

    /**
     * ��ȡmarketDept���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMarketDept() {
        return marketDept;
    }

    /**
     * ����marketDept���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMarketDept(String value) {
        this.marketDept = value;
    }

    /**
     * ��ȡmarketTime���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getMarketTime() {
        return marketTime;
    }

    /**
     * ����marketTime���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setMarketTime(Date value) {
        this.marketTime = value;
    }

}
