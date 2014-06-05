
package com.deppon.crm.module.interfaces.uums.ws;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>CompanyInfo complex type
 * 
 * <p>
 * 
 * <pre>
 * &lt;complexType name="CompanyInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="companyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="companyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shortName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="companyStandCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isHasAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="linkMan1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fax1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phone1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="saleAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="briefintro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ownerShareRate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="isWorkingUnit" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isSeal" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="legalPerson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="changeType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="changeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompanyInfo", namespace = "http://www.deppon.com/crm/inteface/domain/companymanagement", propOrder = {
    "companyCode",
    "companyName",
    "shortName",
    "companyStandCode",
    "isHasAccount",
    "linkMan1",
    "fax1",
    "phone1",
    "zipCode",
    "postAddress",
    "saleAddress",
    "briefintro",
    "ownerShareRate",
    "isWorkingUnit",
    "isSeal",
    "legalPerson",
    "changeType",
    "changeDate"
})
public class CompanyInfo {

    @XmlElement(required = true)
    protected String companyCode;
    @XmlElement(required = true)
    protected String companyName;
    protected String shortName;
    @XmlElement(required = true)
    protected String companyStandCode;
    @XmlElement(required = true)
    protected String isHasAccount;
    protected String linkMan1;
    protected String fax1;
    protected String phone1;
    protected String zipCode;
    protected String postAddress;
    protected String saleAddress;
    protected String briefintro;
    protected Double ownerShareRate;
    protected boolean isWorkingUnit;
    protected boolean isSeal;
    protected String legalPerson;
    @XmlElement(required = true)
    protected String changeType;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date changeDate;

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyCode(String value) {
        this.companyCode = value;
    }

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyStandCode() {
        return companyStandCode;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyStandCode(String value) {
        this.companyStandCode = value;
    }

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsHasAccount() {
        return isHasAccount;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsHasAccount(String value) {
        this.isHasAccount = value;
    }

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkMan1() {
        return linkMan1;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkMan1(String value) {
        this.linkMan1 = value;
    }

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax1() {
        return fax1;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax1(String value) {
        this.fax1 = value;
    }

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone1() {
        return phone1;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone1(String value) {
        this.phone1 = value;
    }

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipCode(String value) {
        this.zipCode = value;
    }

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostAddress() {
        return postAddress;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostAddress(String value) {
        this.postAddress = value;
    }

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaleAddress() {
        return saleAddress;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaleAddress(String value) {
        this.saleAddress = value;
    }

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBriefintro() {
        return briefintro;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBriefintro(String value) {
        this.briefintro = value;
    }

    /**
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOwnerShareRate() {
        return ownerShareRate;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOwnerShareRate(Double value) {
        this.ownerShareRate = value;
    }

    /**
     */
    public boolean isIsWorkingUnit() {
        return isWorkingUnit;
    }

    /**
     */
    public void setIsWorkingUnit(boolean value) {
        this.isWorkingUnit = value;
    }

    /**
     */
    public boolean isIsSeal() {
        return isSeal;
    }

    /**
     */
    public void setIsSeal(boolean value) {
        this.isSeal = value;
    }

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalPerson() {
        return legalPerson;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalPerson(String value) {
        this.legalPerson = value;
    }

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangeType() {
        return changeType;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangeType(String value) {
        this.changeType = value;
    }

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getChangeDate() {
        return changeDate;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangeDate(Date value) {
        this.changeDate = value;
    }

}
