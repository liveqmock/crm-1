
package com.deppon.foss.crm;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * �˵���Ϣ
 *
 * <p>FossQueryAcctinfo complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="FossQueryAcctinfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="waybillNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receiveCustomerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiveCustomerContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiveCustomerMobilephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiveCustomerPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiveCustomerAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerPickupOrgCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="activeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="discountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activeStartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="activeEndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FossQueryAcctinfo", propOrder = {
    "waybillNo",
    "receiveCustomerName",
    "receiveCustomerContact",
    "receiveCustomerMobilephone",
    "receiveCustomerPhone",
    "receiveCustomerAddress",
    "productCode",
    "customerPickupOrgCode",
    "billTime",
    "activeName",
    "activeType",
    "activeCode",
    "discountType",
    "activeStartTime",
    "activeEndTime"
})
public class FossQueryAcctinfo {

    @XmlElement(required = true)
    protected String waybillNo;
    protected String receiveCustomerName;
    protected String receiveCustomerContact;
    protected String receiveCustomerMobilephone;
    protected String receiveCustomerPhone;
    protected String receiveCustomerAddress;
    protected String productCode;
    protected String customerPickupOrgCode;
    @XmlSchemaType(name = "dateTime")
    protected Date billTime;
    protected String activeName;
    protected String activeType;
    protected String activeCode;
    protected String discountType;
    @XmlSchemaType(name = "dateTime")
    protected Date activeStartTime;
    @XmlSchemaType(name = "dateTime")
    protected Date activeEndTime;

    /**
     * ��ȡwaybillNo���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getWaybillNo() {
        return waybillNo;
    }

    /**
     * ����waybillNo���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setWaybillNo(String value) {
        this.waybillNo = value;
    }

    /**
     * ��ȡreceiveCustomerName���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReceiveCustomerName() {
        return receiveCustomerName;
    }

    /**
     * ����receiveCustomerName���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReceiveCustomerName(String value) {
        this.receiveCustomerName = value;
    }

    /**
     * ��ȡreceiveCustomerContact���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReceiveCustomerContact() {
        return receiveCustomerContact;
    }

    /**
     * ����receiveCustomerContact���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReceiveCustomerContact(String value) {
        this.receiveCustomerContact = value;
    }

    /**
     * ��ȡreceiveCustomerMobilephone���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReceiveCustomerMobilephone() {
        return receiveCustomerMobilephone;
    }

    /**
     * ����receiveCustomerMobilephone���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReceiveCustomerMobilephone(String value) {
        this.receiveCustomerMobilephone = value;
    }

    /**
     * ��ȡreceiveCustomerPhone���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReceiveCustomerPhone() {
        return receiveCustomerPhone;
    }

    /**
     * ����receiveCustomerPhone���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReceiveCustomerPhone(String value) {
        this.receiveCustomerPhone = value;
    }

    /**
     * ��ȡreceiveCustomerAddress���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReceiveCustomerAddress() {
        return receiveCustomerAddress;
    }

    /**
     * ����receiveCustomerAddress���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReceiveCustomerAddress(String value) {
        this.receiveCustomerAddress = value;
    }

    /**
     * ��ȡproductCode���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * ����productCode���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProductCode(String value) {
        this.productCode = value;
    }

    /**
     * ��ȡcustomerPickupOrgCode���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCustomerPickupOrgCode() {
        return customerPickupOrgCode;
    }

    /**
     * ����customerPickupOrgCode���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCustomerPickupOrgCode(String value) {
        this.customerPickupOrgCode = value;
    }

    /**
     * ��ȡbillTime���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getBillTime() {
        return billTime;
    }

    /**
     * ����billTime���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setBillTime(Date value) {
        this.billTime = value;
    }

    /**
     * ��ȡactiveName���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getActiveName() {
        return activeName;
    }

    /**
     * ����activeName���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setActiveName(String value) {
        this.activeName = value;
    }

    /**
     * ��ȡactiveType���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getActiveType() {
        return activeType;
    }

    /**
     * ����activeType���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setActiveType(String value) {
        this.activeType = value;
    }

    /**
     * ��ȡactiveCode���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getActiveCode() {
        return activeCode;
    }

    /**
     * ����activeCode���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setActiveCode(String value) {
        this.activeCode = value;
    }

    /**
     * ��ȡdiscountType���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDiscountType() {
        return discountType;
    }

    /**
     * ����discountType���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDiscountType(String value) {
        this.discountType = value;
    }

    /**
     * ��ȡactiveStartTime���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getActiveStartTime() {
        return activeStartTime;
    }

    /**
     * ����activeStartTime���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setActiveStartTime(Date value) {
        this.activeStartTime = value;
    }

    /**
     * ��ȡactiveEndTime���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getActiveEndTime() {
        return activeEndTime;
    }

    /**
     * ����activeEndTime���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setActiveEndTime(Date value) {
        this.activeEndTime = value;
    }

}
