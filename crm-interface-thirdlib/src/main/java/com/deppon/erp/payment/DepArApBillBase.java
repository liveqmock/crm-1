
package com.deppon.erp.payment;

import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for depArApBillBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="depArApBillBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.amsonline.ws.deppon.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="acctDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="alterBill" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="amountUnVerify" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="amountVerify" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="auditDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="auditor" type="{http://service.amsonline.ws.deppon.com/}user" minOccurs="0"/>
 *         &lt;element name="beRedReversed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="billState" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="bizDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="cashier" type="{http://service.amsonline.ws.deppon.com/}user" minOccurs="0"/>
 *         &lt;element name="cashierDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="companyContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ctrlUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customer" type="{http://service.amsonline.ws.deppon.com/}custBaseData" minOccurs="0"/>
 *         &lt;element name="departFrom" type="{http://service.amsonline.ws.deppon.com/}businessDept" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="effectVersion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fiVouchered" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="handler" type="{http://service.amsonline.ws.deppon.com/}user" minOccurs="0"/>
 *         &lt;element name="hasEffected" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="init" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="originalCustomer" type="{http://service.amsonline.ws.deppon.com/}custBaseData" minOccurs="0"/>
 *         &lt;element name="paymentType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="period" type="{http://service.amsonline.ws.deppon.com/}period" minOccurs="0"/>
 *         &lt;element name="person" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="redBill" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="sourceBillId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceFunction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="srcBillID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="srcBillType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="subBillType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="transType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="verifyState" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="vouchor" type="{http://service.amsonline.ws.deppon.com/}user" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "depArApBillBase", propOrder = {
    "acctDate",
    "alterBill",
    "amount",
    "amountUnVerify",
    "amountVerify",
    "auditDate",
    "auditor",
    "beRedReversed",
    "billState",
    "bizDate",
    "cashier",
    "cashierDate",
    "company",
    "companyContact",
    "ctrlUnit",
    "customer",
    "departFrom",
    "description",
    "effectVersion",
    "fiVouchered",
    "handler",
    "hasEffected",
    "init",
    "number",
    "originalCustomer",
    "paymentType",
    "period",
    "person",
    "redBill",
    "sourceBillId",
    "sourceFunction",
    "srcBillID",
    "srcBillType",
    "subBillType",
    "transType",
    "verifyState",
    "version",
    "vouchor"
})
@XmlSeeAlso({
    DepClaimsBill.class
})
public abstract class DepArApBillBase
    extends BaseEntity
{

    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date acctDate;
    protected boolean alterBill;
    protected BigDecimal amount;
    protected BigDecimal amountUnVerify;
    protected BigDecimal amountVerify;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date auditDate;
    protected User auditor;
    protected boolean beRedReversed;
    protected int billState;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date bizDate;
    protected User cashier;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date cashierDate;
    protected String company;
    protected String companyContact;
    protected String ctrlUnit;
    protected CustBaseData customer;
    protected BusinessDept departFrom;
    protected String description;
    protected boolean effectVersion;
    protected boolean fiVouchered;
    protected User handler;
    protected boolean hasEffected;
    protected boolean init;
    protected String number;
    protected CustBaseData originalCustomer;
    protected int paymentType;
    protected Period period;
    protected String person;
    protected boolean redBill;
    protected String sourceBillId;
    protected String sourceFunction;
    protected String srcBillID;
    protected int srcBillType;
    protected int subBillType;
    protected int transType;
    protected int verifyState;
    protected Integer version;
    protected User vouchor;

    /**
     * Gets the value of the acctDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getAcctDate() {
        return acctDate;
    }

    /**
     * Sets the value of the acctDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctDate(Date value) {
        this.acctDate = value;
    }

    /**
     * Gets the value of the alterBill property.
     * 
     */
    public boolean isAlterBill() {
        return alterBill;
    }

    /**
     * Sets the value of the alterBill property.
     * 
     */
    public void setAlterBill(boolean value) {
        this.alterBill = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the amountUnVerify property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountUnVerify() {
        return amountUnVerify;
    }

    /**
     * Sets the value of the amountUnVerify property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountUnVerify(BigDecimal value) {
        this.amountUnVerify = value;
    }

    /**
     * Gets the value of the amountVerify property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountVerify() {
        return amountVerify;
    }

    /**
     * Sets the value of the amountVerify property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountVerify(BigDecimal value) {
        this.amountVerify = value;
    }

    /**
     * Gets the value of the auditDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getAuditDate() {
        return auditDate;
    }

    /**
     * Sets the value of the auditDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuditDate(Date value) {
        this.auditDate = value;
    }

    /**
     * Gets the value of the auditor property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getAuditor() {
        return auditor;
    }

    /**
     * Sets the value of the auditor property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setAuditor(User value) {
        this.auditor = value;
    }

    /**
     * Gets the value of the beRedReversed property.
     * 
     */
    public boolean isBeRedReversed() {
        return beRedReversed;
    }

    /**
     * Sets the value of the beRedReversed property.
     * 
     */
    public void setBeRedReversed(boolean value) {
        this.beRedReversed = value;
    }

    /**
     * Gets the value of the billState property.
     * 
     */
    public int getBillState() {
        return billState;
    }

    /**
     * Sets the value of the billState property.
     * 
     */
    public void setBillState(int value) {
        this.billState = value;
    }

    /**
     * Gets the value of the bizDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getBizDate() {
        return bizDate;
    }

    /**
     * Sets the value of the bizDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBizDate(Date value) {
        this.bizDate = value;
    }

    /**
     * Gets the value of the cashier property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getCashier() {
        return cashier;
    }

    /**
     * Sets the value of the cashier property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setCashier(User value) {
        this.cashier = value;
    }

    /**
     * Gets the value of the cashierDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getCashierDate() {
        return cashierDate;
    }

    /**
     * Sets the value of the cashierDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCashierDate(Date value) {
        this.cashierDate = value;
    }

    /**
     * Gets the value of the company property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompany(String value) {
        this.company = value;
    }

    /**
     * Gets the value of the companyContact property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyContact() {
        return companyContact;
    }

    /**
     * Sets the value of the companyContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyContact(String value) {
        this.companyContact = value;
    }

    /**
     * Gets the value of the ctrlUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCtrlUnit() {
        return ctrlUnit;
    }

    /**
     * Sets the value of the ctrlUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCtrlUnit(String value) {
        this.ctrlUnit = value;
    }

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link CustBaseData }
     *     
     */
    public CustBaseData getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustBaseData }
     *     
     */
    public void setCustomer(CustBaseData value) {
        this.customer = value;
    }

    /**
     * Gets the value of the departFrom property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessDept }
     *     
     */
    public BusinessDept getDepartFrom() {
        return departFrom;
    }

    /**
     * Sets the value of the departFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessDept }
     *     
     */
    public void setDepartFrom(BusinessDept value) {
        this.departFrom = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the effectVersion property.
     * 
     */
    public boolean isEffectVersion() {
        return effectVersion;
    }

    /**
     * Sets the value of the effectVersion property.
     * 
     */
    public void setEffectVersion(boolean value) {
        this.effectVersion = value;
    }

    /**
     * Gets the value of the fiVouchered property.
     * 
     */
    public boolean isFiVouchered() {
        return fiVouchered;
    }

    /**
     * Sets the value of the fiVouchered property.
     * 
     */
    public void setFiVouchered(boolean value) {
        this.fiVouchered = value;
    }

    /**
     * Gets the value of the handler property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getHandler() {
        return handler;
    }

    /**
     * Sets the value of the handler property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setHandler(User value) {
        this.handler = value;
    }

    /**
     * Gets the value of the hasEffected property.
     * 
     */
    public boolean isHasEffected() {
        return hasEffected;
    }

    /**
     * Sets the value of the hasEffected property.
     * 
     */
    public void setHasEffected(boolean value) {
        this.hasEffected = value;
    }

    /**
     * Gets the value of the init property.
     * 
     */
    public boolean isInit() {
        return init;
    }

    /**
     * Sets the value of the init property.
     * 
     */
    public void setInit(boolean value) {
        this.init = value;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumber(String value) {
        this.number = value;
    }

    /**
     * Gets the value of the originalCustomer property.
     * 
     * @return
     *     possible object is
     *     {@link CustBaseData }
     *     
     */
    public CustBaseData getOriginalCustomer() {
        return originalCustomer;
    }

    /**
     * Sets the value of the originalCustomer property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustBaseData }
     *     
     */
    public void setOriginalCustomer(CustBaseData value) {
        this.originalCustomer = value;
    }

    /**
     * Gets the value of the paymentType property.
     * 
     */
    public int getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the value of the paymentType property.
     * 
     */
    public void setPaymentType(int value) {
        this.paymentType = value;
    }

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link Period }
     *     
     */
    public Period getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link Period }
     *     
     */
    public void setPeriod(Period value) {
        this.period = value;
    }

    /**
     * Gets the value of the person property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerson() {
        return person;
    }

    /**
     * Sets the value of the person property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerson(String value) {
        this.person = value;
    }

    /**
     * Gets the value of the redBill property.
     * 
     */
    public boolean isRedBill() {
        return redBill;
    }

    /**
     * Sets the value of the redBill property.
     * 
     */
    public void setRedBill(boolean value) {
        this.redBill = value;
    }

    /**
     * Gets the value of the sourceBillId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceBillId() {
        return sourceBillId;
    }

    /**
     * Sets the value of the sourceBillId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceBillId(String value) {
        this.sourceBillId = value;
    }

    /**
     * Gets the value of the sourceFunction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceFunction() {
        return sourceFunction;
    }

    /**
     * Sets the value of the sourceFunction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceFunction(String value) {
        this.sourceFunction = value;
    }

    /**
     * Gets the value of the srcBillID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcBillID() {
        return srcBillID;
    }

    /**
     * Sets the value of the srcBillID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcBillID(String value) {
        this.srcBillID = value;
    }

    /**
     * Gets the value of the srcBillType property.
     * 
     */
    public int getSrcBillType() {
        return srcBillType;
    }

    /**
     * Sets the value of the srcBillType property.
     * 
     */
    public void setSrcBillType(int value) {
        this.srcBillType = value;
    }

    /**
     * Gets the value of the subBillType property.
     * 
     */
    public int getSubBillType() {
        return subBillType;
    }

    /**
     * Sets the value of the subBillType property.
     * 
     */
    public void setSubBillType(int value) {
        this.subBillType = value;
    }

    /**
     * Gets the value of the transType property.
     * 
     */
    public int getTransType() {
        return transType;
    }

    /**
     * Sets the value of the transType property.
     * 
     */
    public void setTransType(int value) {
        this.transType = value;
    }

    /**
     * Gets the value of the verifyState property.
     * 
     */
    public int getVerifyState() {
        return verifyState;
    }

    /**
     * Sets the value of the verifyState property.
     * 
     */
    public void setVerifyState(int value) {
        this.verifyState = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVersion(Integer value) {
        this.version = value;
    }

    /**
     * Gets the value of the vouchor property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getVouchor() {
        return vouchor;
    }

    /**
     * Sets the value of the vouchor property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setVouchor(User value) {
        this.vouchor = value;
    }

}
