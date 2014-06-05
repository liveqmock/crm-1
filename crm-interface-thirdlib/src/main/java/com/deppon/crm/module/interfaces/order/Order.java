
package com.deppon.crm.module.interfaces.order;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for order complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="order">
 *   &lt;complexContent>
 *     &lt;extension base="{http://order.interfaces.module.crm.deppon.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="acceptDept" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="acceptDeptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accepterPersonMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beginAcceptTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="channelCustId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="channelNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="channelType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactManId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="contactMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dealPerson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliveryMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endAcceptTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="feedbackInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="goodsName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="goodsNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="goodsType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hastenCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="insuredAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="isReceiveGoods" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="isSendmessage" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="lastHastenTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="operationLogs" type="{http://order.interfaces.module.crm.deppon.com/}orderOperationLog" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="orderAcceptTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="orderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderPerson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="packing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiverCustAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiverCustArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiverCustCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiverCustId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiverCustMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiverCustName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiverCustNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiverCustPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiverCustProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiverCustcompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivingToPoint" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reciveLoanType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="returnBillType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reviceMoneyAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="shipperId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startStation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startStationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalVolume" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="totalWeight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="transNote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transportMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="waybillNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "order", propOrder = {
    "acceptDept",
    "acceptDeptName",
    "accepterPersonMobile",
    "beginAcceptTime",
    "channelCustId",
    "channelNumber",
    "channelType",
    "contactAddress",
    "contactArea",
    "contactCity",
    "contactManId",
    "contactMobile",
    "contactName",
    "contactPhone",
    "contactProvince",
    "dealPerson",
    "deliveryMode",
    "endAcceptTime",
    "feedbackInfo",
    "goodsName",
    "goodsNumber",
    "goodsType",
    "hastenCount",
    "insuredAmount",
    "isReceiveGoods",
    "isSendmessage",
    "lastHastenTime",
    "operationLogs",
    "orderAcceptTime",
    "orderNumber",
    "orderPerson",
    "orderStatus",
    "orderTime",
    "packing",
    "paymentType",
    "receiver",
    "receiverCustAddress",
    "receiverCustArea",
    "receiverCustCity",
    "receiverCustId",
    "receiverCustMobile",
    "receiverCustName",
    "receiverCustNumber",
    "receiverCustPhone",
    "receiverCustProvince",
    "receiverCustcompany",
    "receivingToPoint",
    "reciveLoanType",
    "resource",
    "returnBillType",
    "reviceMoneyAmount",
    "shipperId",
    "shipperName",
    "shipperNumber",
    "startStation",
    "startStationName",
    "totalVolume",
    "totalWeight",
    "transNote",
    "transportMode",
    "waybillNumber"
})
public class Order
    extends BaseEntity
{

    protected String acceptDept;
    protected String acceptDeptName;
    protected String accepterPersonMobile;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar beginAcceptTime;
    protected String channelCustId;
    protected String channelNumber;
    protected String channelType;
    protected String contactAddress;
    protected String contactArea;
    protected String contactCity;
    protected Integer contactManId;
    protected String contactMobile;
    protected String contactName;
    protected String contactPhone;
    protected String contactProvince;
    protected String dealPerson;
    protected String deliveryMode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endAcceptTime;
    protected String feedbackInfo;
    protected String goodsName;
    protected Integer goodsNumber;
    protected String goodsType;
    protected Integer hastenCount;
    protected Double insuredAmount;
    protected Boolean isReceiveGoods;
    protected Boolean isSendmessage;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastHastenTime;
    @XmlElement(nillable = true)
    protected List<OrderOperationLog> operationLogs;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderAcceptTime;
    protected String orderNumber;
    protected String orderPerson;
    protected String orderStatus;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderTime;
    protected String packing;
    protected String paymentType;
    protected String receiver;
    protected String receiverCustAddress;
    protected String receiverCustArea;
    protected String receiverCustCity;
    protected String receiverCustId;
    protected String receiverCustMobile;
    protected String receiverCustName;
    protected String receiverCustNumber;
    protected String receiverCustPhone;
    protected String receiverCustProvince;
    protected String receiverCustcompany;
    protected String receivingToPoint;
    protected String reciveLoanType;
    protected String resource;
    protected String returnBillType;
    protected Double reviceMoneyAmount;
    protected String shipperId;
    protected String shipperName;
    protected String shipperNumber;
    protected String startStation;
    protected String startStationName;
    protected Double totalVolume;
    protected Double totalWeight;
    protected String transNote;
    protected String transportMode;
    protected String waybillNumber;

    /**
     * Gets the value of the acceptDept property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptDept() {
        return acceptDept;
    }

    /**
     * Sets the value of the acceptDept property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptDept(String value) {
        this.acceptDept = value;
    }

    /**
     * Gets the value of the acceptDeptName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptDeptName() {
        return acceptDeptName;
    }

    /**
     * Sets the value of the acceptDeptName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptDeptName(String value) {
        this.acceptDeptName = value;
    }

    /**
     * Gets the value of the accepterPersonMobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccepterPersonMobile() {
        return accepterPersonMobile;
    }

    /**
     * Sets the value of the accepterPersonMobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccepterPersonMobile(String value) {
        this.accepterPersonMobile = value;
    }

    /**
     * Gets the value of the beginAcceptTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBeginAcceptTime() {
        return beginAcceptTime;
    }

    /**
     * Sets the value of the beginAcceptTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBeginAcceptTime(XMLGregorianCalendar value) {
        this.beginAcceptTime = value;
    }

    /**
     * Gets the value of the channelCustId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelCustId() {
        return channelCustId;
    }

    /**
     * Sets the value of the channelCustId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelCustId(String value) {
        this.channelCustId = value;
    }

    /**
     * Gets the value of the channelNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelNumber() {
        return channelNumber;
    }

    /**
     * Sets the value of the channelNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelNumber(String value) {
        this.channelNumber = value;
    }

    /**
     * Gets the value of the channelType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelType() {
        return channelType;
    }

    /**
     * Sets the value of the channelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelType(String value) {
        this.channelType = value;
    }

    /**
     * Gets the value of the contactAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactAddress() {
        return contactAddress;
    }

    /**
     * Sets the value of the contactAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactAddress(String value) {
        this.contactAddress = value;
    }

    /**
     * Gets the value of the contactArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactArea() {
        return contactArea;
    }

    /**
     * Sets the value of the contactArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactArea(String value) {
        this.contactArea = value;
    }

    /**
     * Gets the value of the contactCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactCity() {
        return contactCity;
    }

    /**
     * Sets the value of the contactCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactCity(String value) {
        this.contactCity = value;
    }

    /**
     * Gets the value of the contactManId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getContactManId() {
        return contactManId;
    }

    /**
     * Sets the value of the contactManId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setContactManId(Integer value) {
        this.contactManId = value;
    }

    /**
     * Gets the value of the contactMobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactMobile() {
        return contactMobile;
    }

    /**
     * Sets the value of the contactMobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactMobile(String value) {
        this.contactMobile = value;
    }

    /**
     * Gets the value of the contactName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the value of the contactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactName(String value) {
        this.contactName = value;
    }

    /**
     * Gets the value of the contactPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * Sets the value of the contactPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPhone(String value) {
        this.contactPhone = value;
    }

    /**
     * Gets the value of the contactProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactProvince() {
        return contactProvince;
    }

    /**
     * Sets the value of the contactProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactProvince(String value) {
        this.contactProvince = value;
    }

    /**
     * Gets the value of the dealPerson property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDealPerson() {
        return dealPerson;
    }

    /**
     * Sets the value of the dealPerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDealPerson(String value) {
        this.dealPerson = value;
    }

    /**
     * Gets the value of the deliveryMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryMode() {
        return deliveryMode;
    }

    /**
     * Sets the value of the deliveryMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryMode(String value) {
        this.deliveryMode = value;
    }

    /**
     * Gets the value of the endAcceptTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndAcceptTime() {
        return endAcceptTime;
    }

    /**
     * Sets the value of the endAcceptTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndAcceptTime(XMLGregorianCalendar value) {
        this.endAcceptTime = value;
    }

    /**
     * Gets the value of the feedbackInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeedbackInfo() {
        return feedbackInfo;
    }

    /**
     * Sets the value of the feedbackInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeedbackInfo(String value) {
        this.feedbackInfo = value;
    }

    /**
     * Gets the value of the goodsName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * Sets the value of the goodsName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsName(String value) {
        this.goodsName = value;
    }

    /**
     * Gets the value of the goodsNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    /**
     * Sets the value of the goodsNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGoodsNumber(Integer value) {
        this.goodsNumber = value;
    }

    /**
     * Gets the value of the goodsType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsType() {
        return goodsType;
    }

    /**
     * Sets the value of the goodsType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsType(String value) {
        this.goodsType = value;
    }

    /**
     * Gets the value of the hastenCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHastenCount() {
        return hastenCount;
    }

    /**
     * Sets the value of the hastenCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHastenCount(Integer value) {
        this.hastenCount = value;
    }

    /**
     * Gets the value of the insuredAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getInsuredAmount() {
        return insuredAmount;
    }

    /**
     * Sets the value of the insuredAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setInsuredAmount(Double value) {
        this.insuredAmount = value;
    }

    /**
     * Gets the value of the isReceiveGoods property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsReceiveGoods() {
        return isReceiveGoods;
    }

    /**
     * Sets the value of the isReceiveGoods property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsReceiveGoods(Boolean value) {
        this.isReceiveGoods = value;
    }

    /**
     * Gets the value of the isSendmessage property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSendmessage() {
        return isSendmessage;
    }

    /**
     * Sets the value of the isSendmessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSendmessage(Boolean value) {
        this.isSendmessage = value;
    }

    /**
     * Gets the value of the lastHastenTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastHastenTime() {
        return lastHastenTime;
    }

    /**
     * Sets the value of the lastHastenTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastHastenTime(XMLGregorianCalendar value) {
        this.lastHastenTime = value;
    }

    /**
     * Gets the value of the operationLogs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operationLogs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperationLogs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderOperationLog }
     * 
     * 
     */
    public List<OrderOperationLog> getOperationLogs() {
        if (operationLogs == null) {
            operationLogs = new ArrayList<OrderOperationLog>();
        }
        return this.operationLogs;
    }

    /**
     * Gets the value of the orderAcceptTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderAcceptTime() {
        return orderAcceptTime;
    }

    /**
     * Sets the value of the orderAcceptTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderAcceptTime(XMLGregorianCalendar value) {
        this.orderAcceptTime = value;
    }

    /**
     * Gets the value of the orderNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets the value of the orderNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderNumber(String value) {
        this.orderNumber = value;
    }

    /**
     * Gets the value of the orderPerson property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderPerson() {
        return orderPerson;
    }

    /**
     * Sets the value of the orderPerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderPerson(String value) {
        this.orderPerson = value;
    }

    /**
     * Gets the value of the orderStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets the value of the orderStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderStatus(String value) {
        this.orderStatus = value;
    }

    /**
     * Gets the value of the orderTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderTime() {
        return orderTime;
    }

    /**
     * Sets the value of the orderTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderTime(XMLGregorianCalendar value) {
        this.orderTime = value;
    }

    /**
     * Gets the value of the packing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPacking() {
        return packing;
    }

    /**
     * Sets the value of the packing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPacking(String value) {
        this.packing = value;
    }

    /**
     * Gets the value of the paymentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the value of the paymentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentType(String value) {
        this.paymentType = value;
    }

    /**
     * Gets the value of the receiver property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Sets the value of the receiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiver(String value) {
        this.receiver = value;
    }

    /**
     * Gets the value of the receiverCustAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverCustAddress() {
        return receiverCustAddress;
    }

    /**
     * Sets the value of the receiverCustAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverCustAddress(String value) {
        this.receiverCustAddress = value;
    }

    /**
     * Gets the value of the receiverCustArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverCustArea() {
        return receiverCustArea;
    }

    /**
     * Sets the value of the receiverCustArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverCustArea(String value) {
        this.receiverCustArea = value;
    }

    /**
     * Gets the value of the receiverCustCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverCustCity() {
        return receiverCustCity;
    }

    /**
     * Sets the value of the receiverCustCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverCustCity(String value) {
        this.receiverCustCity = value;
    }

    /**
     * Gets the value of the receiverCustId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverCustId() {
        return receiverCustId;
    }

    /**
     * Sets the value of the receiverCustId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverCustId(String value) {
        this.receiverCustId = value;
    }

    /**
     * Gets the value of the receiverCustMobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverCustMobile() {
        return receiverCustMobile;
    }

    /**
     * Sets the value of the receiverCustMobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverCustMobile(String value) {
        this.receiverCustMobile = value;
    }

    /**
     * Gets the value of the receiverCustName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverCustName() {
        return receiverCustName;
    }

    /**
     * Sets the value of the receiverCustName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverCustName(String value) {
        this.receiverCustName = value;
    }

    /**
     * Gets the value of the receiverCustNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverCustNumber() {
        return receiverCustNumber;
    }

    /**
     * Sets the value of the receiverCustNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverCustNumber(String value) {
        this.receiverCustNumber = value;
    }

    /**
     * Gets the value of the receiverCustPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverCustPhone() {
        return receiverCustPhone;
    }

    /**
     * Sets the value of the receiverCustPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverCustPhone(String value) {
        this.receiverCustPhone = value;
    }

    /**
     * Gets the value of the receiverCustProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverCustProvince() {
        return receiverCustProvince;
    }

    /**
     * Sets the value of the receiverCustProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverCustProvince(String value) {
        this.receiverCustProvince = value;
    }

    /**
     * Gets the value of the receiverCustcompany property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverCustcompany() {
        return receiverCustcompany;
    }

    /**
     * Sets the value of the receiverCustcompany property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverCustcompany(String value) {
        this.receiverCustcompany = value;
    }

    /**
     * Gets the value of the receivingToPoint property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivingToPoint() {
        return receivingToPoint;
    }

    /**
     * Sets the value of the receivingToPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivingToPoint(String value) {
        this.receivingToPoint = value;
    }

    /**
     * Gets the value of the reciveLoanType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReciveLoanType() {
        return reciveLoanType;
    }

    /**
     * Sets the value of the reciveLoanType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReciveLoanType(String value) {
        this.reciveLoanType = value;
    }

    /**
     * Gets the value of the resource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResource() {
        return resource;
    }

    /**
     * Sets the value of the resource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResource(String value) {
        this.resource = value;
    }

    /**
     * Gets the value of the returnBillType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnBillType() {
        return returnBillType;
    }

    /**
     * Sets the value of the returnBillType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnBillType(String value) {
        this.returnBillType = value;
    }

    /**
     * Gets the value of the reviceMoneyAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getReviceMoneyAmount() {
        return reviceMoneyAmount;
    }

    /**
     * Sets the value of the reviceMoneyAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setReviceMoneyAmount(Double value) {
        this.reviceMoneyAmount = value;
    }

    /**
     * Gets the value of the shipperId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperId() {
        return shipperId;
    }

    /**
     * Sets the value of the shipperId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperId(String value) {
        this.shipperId = value;
    }

    /**
     * Gets the value of the shipperName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperName() {
        return shipperName;
    }

    /**
     * Sets the value of the shipperName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperName(String value) {
        this.shipperName = value;
    }

    /**
     * Gets the value of the shipperNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperNumber() {
        return shipperNumber;
    }

    /**
     * Sets the value of the shipperNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperNumber(String value) {
        this.shipperNumber = value;
    }

    /**
     * Gets the value of the startStation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartStation() {
        return startStation;
    }

    /**
     * Sets the value of the startStation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartStation(String value) {
        this.startStation = value;
    }

    /**
     * Gets the value of the startStationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartStationName() {
        return startStationName;
    }

    /**
     * Sets the value of the startStationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartStationName(String value) {
        this.startStationName = value;
    }

    /**
     * Gets the value of the totalVolume property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalVolume() {
        return totalVolume;
    }

    /**
     * Sets the value of the totalVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalVolume(Double value) {
        this.totalVolume = value;
    }

    /**
     * Gets the value of the totalWeight property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalWeight() {
        return totalWeight;
    }

    /**
     * Sets the value of the totalWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalWeight(Double value) {
        this.totalWeight = value;
    }

    /**
     * Gets the value of the transNote property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransNote() {
        return transNote;
    }

    /**
     * Sets the value of the transNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransNote(String value) {
        this.transNote = value;
    }

    /**
     * Gets the value of the transportMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportMode() {
        return transportMode;
    }

    /**
     * Sets the value of the transportMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportMode(String value) {
        this.transportMode = value;
    }

    /**
     * Gets the value of the waybillNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaybillNumber() {
        return waybillNumber;
    }

    /**
     * Sets the value of the waybillNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaybillNumber(String value) {
        this.waybillNumber = value;
    }

}
