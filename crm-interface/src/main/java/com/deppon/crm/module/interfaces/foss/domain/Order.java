
package com.deppon.crm.module.interfaces.foss.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * ��ϵ��id
 * 
 * <p>Order complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="Order">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hastenCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lastHastenTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="contactManId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="channelNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shipperId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shipperNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shipperName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactMobile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactProvince" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactArea" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isReceiveGoods" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="beginAcceptTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="endAcceptTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="receiverCustId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receiverCustNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receiverCustcompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receiverCustName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receiverCustMobile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receiverCustPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receiverCustProvince" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receiverCustCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receiverCustArea" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receiverCustAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isSendmessage" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="receivingToPoint" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receivingToPointName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transportMode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="goodsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="packing" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="goodsType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="goodsNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalWeight" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="totalVolume" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="paymentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="channelType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="channelCustId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deliveryMode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reciveLoanType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reviceMoneyAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="insuredAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="orderTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="startStation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startStationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="acceptDept" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="acceptDeptName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dealPerson" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderAcceptTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="receiver" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accepterPersonMobile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="waybillNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resource" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transNote" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="returnBillType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderPerson" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="feedbackInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="onlineName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="memberType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Order", propOrder = {
    "hastenCount",
    "lastHastenTime",
    "contactManId",
    "orderNumber",
    "channelNumber",
    "shipperId",
    "shipperNumber",
    "shipperName",
    "contactName",
    "contactMobile",
    "contactPhone",
    "contactProvince",
    "contactCity",
    "contactArea",
    "contactAddress",
    "isReceiveGoods",
    "beginAcceptTime",
    "endAcceptTime",
    "receiverCustId",
    "receiverCustNumber",
    "receiverCustcompany",
    "receiverCustName",
    "receiverCustMobile",
    "receiverCustPhone",
    "receiverCustProvince",
    "receiverCustCity",
    "receiverCustArea",
    "receiverCustAddress",
    "isSendmessage",
    "receivingToPoint",
    "receivingToPointName",
    "transportMode",
    "goodsName",
    "packing",
    "goodsType",
    "goodsNumber",
    "totalWeight",
    "totalVolume",
    "paymentType",
    "channelType",
    "channelCustId",
    "deliveryMode",
    "reciveLoanType",
    "reviceMoneyAmount",
    "insuredAmount",
    "orderTime",
    "startStation",
    "startStationName",
    "acceptDept",
    "acceptDeptName",
    "orderStatus",
    "dealPerson",
    "orderAcceptTime",
    "receiver",
    "accepterPersonMobile",
    "waybillNumber",
    "resource",
    "transNote",
    "returnBillType",
    "orderPerson",
    "feedbackInfo",
    "onlineName",
    "memberType",
    "couponNumber"
})
public class Order {

    protected int hastenCount;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date lastHastenTime;
    @XmlElement(required = true)
    protected String contactManId;
    @XmlElement(required = true)
    protected String orderNumber;
    @XmlElement(required = true)
    protected String channelNumber;
    @XmlElement(required = true)
    protected String shipperId;
    @XmlElement(required = true)
    protected String shipperNumber;
    @XmlElement(required = true)
    protected String shipperName;
    @XmlElement(required = true)
    protected String contactName;
    @XmlElement(required = true)
    protected String contactMobile;
    @XmlElement(required = true)
    protected String contactPhone;
    @XmlElement(required = true)
    protected String contactProvince;
    @XmlElement(required = true)
    protected String contactCity;
    @XmlElement(required = true)
    protected String contactArea;
    @XmlElement(required = true)
    protected String contactAddress;
    protected boolean isReceiveGoods;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date beginAcceptTime;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date endAcceptTime;
    @XmlElement(required = true)
    protected String receiverCustId;
    @XmlElement(required = true)
    protected String receiverCustNumber;
    @XmlElement(required = true)
    protected String receiverCustcompany;
    @XmlElement(required = true)
    protected String receiverCustName;
    @XmlElement(required = true)
    protected String receiverCustMobile;
    @XmlElement(required = true)
    protected String receiverCustPhone;
    @XmlElement(required = true)
    protected String receiverCustProvince;
    @XmlElement(required = true)
    protected String receiverCustCity;
    @XmlElement(required = true)
    protected String receiverCustArea;
    @XmlElement(required = true)
    protected String receiverCustAddress;
    protected boolean isSendmessage;
    @XmlElement(required = true)
    protected String receivingToPoint;
    @XmlElement(required = true)
    protected String receivingToPointName;
    @XmlElement(required = true)
    protected String transportMode;
    @XmlElement(required = true)
    protected String goodsName;
    @XmlElement(required = true)
    protected String packing;
    @XmlElement(required = true)
    protected String goodsType;
    protected int goodsNumber;
    protected double totalWeight;
    protected double totalVolume;
    @XmlElement(required = true)
    protected String paymentType;
    @XmlElement(required = true)
    protected String channelType;
    @XmlElement(required = true)
    protected String channelCustId;
    @XmlElement(required = true)
    protected String deliveryMode;
    @XmlElement(required = true)
    protected String reciveLoanType;
    @XmlElement(required = true)
    protected BigDecimal reviceMoneyAmount;
    @XmlElement(required = true)
    protected BigDecimal insuredAmount;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date orderTime;
    @XmlElement(required = true)
    protected String startStation;
    @XmlElement(required = true)
    protected String startStationName;
    @XmlElement(required = true)
    protected String acceptDept;
    @XmlElement(required = true)
    protected String acceptDeptName;
    @XmlElement(required = true)
    protected String orderStatus;
    @XmlElement(required = true)
    protected String dealPerson;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date orderAcceptTime;
    @XmlElement(required = true)
    protected String receiver;
    @XmlElement(required = true)
    protected String accepterPersonMobile;
    @XmlElement(required = true)
    protected String waybillNumber;
    @XmlElement(required = true)
    protected String resource;
    @XmlElement(required = true)
    protected String transNote;
    @XmlElement(required = true)
    protected String returnBillType;
    @XmlElement(required = true)
    protected String orderPerson;
    @XmlElement(required = true)
    protected String feedbackInfo;
    @XmlElement(required = true)
    protected String onlineName;
    @XmlElement(required = true)
    protected String memberType;
    protected String couponNumber;

    /**
     * ��ȡhastenCount���Ե�ֵ��
     * 
     */
    public int getHastenCount() {
        return hastenCount;
    }

    /**
     * ����hastenCount���Ե�ֵ��
     * 
     */
    public void setHastenCount(int value) {
        this.hastenCount = value;
    }

    /**
     * ��ȡlastHastenTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getLastHastenTime() {
        return lastHastenTime;
    }

    /**
     * ����lastHastenTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastHastenTime(Date value) {
        this.lastHastenTime = value;
    }

    /**
     * ��ȡcontactManId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactManId() {
        return contactManId;
    }

    /**
     * ����contactManId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactManId(String value) {
        this.contactManId = value;
    }

    /**
     * ��ȡorderNumber���Ե�ֵ��
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
     * ����orderNumber���Ե�ֵ��
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
     * ��ȡchannelNumber���Ե�ֵ��
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
     * ����channelNumber���Ե�ֵ��
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
     * ��ȡshipperId���Ե�ֵ��
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
     * ����shipperId���Ե�ֵ��
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
     * ��ȡshipperNumber���Ե�ֵ��
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
     * ����shipperNumber���Ե�ֵ��
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
     * ��ȡshipperName���Ե�ֵ��
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
     * ����shipperName���Ե�ֵ��
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
     * ��ȡcontactName���Ե�ֵ��
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
     * ����contactName���Ե�ֵ��
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
     * ��ȡcontactMobile���Ե�ֵ��
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
     * ����contactMobile���Ե�ֵ��
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
     * ��ȡcontactPhone���Ե�ֵ��
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
     * ����contactPhone���Ե�ֵ��
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
     * ��ȡcontactProvince���Ե�ֵ��
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
     * ����contactProvince���Ե�ֵ��
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
     * ��ȡcontactCity���Ե�ֵ��
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
     * ����contactCity���Ե�ֵ��
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
     * ��ȡcontactArea���Ե�ֵ��
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
     * ����contactArea���Ե�ֵ��
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
     * ��ȡcontactAddress���Ե�ֵ��
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
     * ����contactAddress���Ե�ֵ��
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
     * ��ȡisReceiveGoods���Ե�ֵ��
     * 
     */
    public boolean isIsReceiveGoods() {
        return isReceiveGoods;
    }

    /**
     * ����isReceiveGoods���Ե�ֵ��
     * 
     */
    public void setIsReceiveGoods(boolean value) {
        this.isReceiveGoods = value;
    }

    /**
     * ��ȡbeginAcceptTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getBeginAcceptTime() {
        return beginAcceptTime;
    }

    /**
     * ����beginAcceptTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeginAcceptTime(Date value) {
        this.beginAcceptTime = value;
    }

    /**
     * ��ȡendAcceptTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getEndAcceptTime() {
        return endAcceptTime;
    }

    /**
     * ����endAcceptTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndAcceptTime(Date value) {
        this.endAcceptTime = value;
    }

    /**
     * ��ȡreceiverCustId���Ե�ֵ��
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
     * ����receiverCustId���Ե�ֵ��
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
     * ��ȡreceiverCustNumber���Ե�ֵ��
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
     * ����receiverCustNumber���Ե�ֵ��
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
     * ��ȡreceiverCustcompany���Ե�ֵ��
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
     * ����receiverCustcompany���Ե�ֵ��
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
     * ��ȡreceiverCustName���Ե�ֵ��
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
     * ����receiverCustName���Ե�ֵ��
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
     * ��ȡreceiverCustMobile���Ե�ֵ��
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
     * ����receiverCustMobile���Ե�ֵ��
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
     * ��ȡreceiverCustPhone���Ե�ֵ��
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
     * ����receiverCustPhone���Ե�ֵ��
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
     * ��ȡreceiverCustProvince���Ե�ֵ��
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
     * ����receiverCustProvince���Ե�ֵ��
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
     * ��ȡreceiverCustCity���Ե�ֵ��
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
     * ����receiverCustCity���Ե�ֵ��
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
     * ��ȡreceiverCustArea���Ե�ֵ��
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
     * ����receiverCustArea���Ե�ֵ��
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
     * ��ȡreceiverCustAddress���Ե�ֵ��
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
     * ����receiverCustAddress���Ե�ֵ��
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
     * ��ȡisSendmessage���Ե�ֵ��
     * 
     */
    public boolean isIsSendmessage() {
        return isSendmessage;
    }

    /**
     * ����isSendmessage���Ե�ֵ��
     * 
     */
    public void setIsSendmessage(boolean value) {
        this.isSendmessage = value;
    }

    /**
     * ��ȡreceivingToPoint���Ե�ֵ��
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
     * ����receivingToPoint���Ե�ֵ��
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
     * ��ȡreceivingToPointName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivingToPointName() {
        return receivingToPointName;
    }

    /**
     * ����receivingToPointName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivingToPointName(String value) {
        this.receivingToPointName = value;
    }

    /**
     * ��ȡtransportMode���Ե�ֵ��
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
     * ����transportMode���Ե�ֵ��
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
     * ��ȡgoodsName���Ե�ֵ��
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
     * ����goodsName���Ե�ֵ��
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
     * ��ȡpacking���Ե�ֵ��
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
     * ����packing���Ե�ֵ��
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
     * ��ȡgoodsType���Ե�ֵ��
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
     * ����goodsType���Ե�ֵ��
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
     * ��ȡgoodsNumber���Ե�ֵ��
     * 
     */
    public int getGoodsNumber() {
        return goodsNumber;
    }

    /**
     * ����goodsNumber���Ե�ֵ��
     * 
     */
    public void setGoodsNumber(int value) {
        this.goodsNumber = value;
    }

    /**
     * ��ȡtotalWeight���Ե�ֵ��
     * 
     */
    public double getTotalWeight() {
        return totalWeight;
    }

    /**
     * ����totalWeight���Ե�ֵ��
     * 
     */
    public void setTotalWeight(double value) {
        this.totalWeight = value;
    }

    /**
     * ��ȡtotalVolume���Ե�ֵ��
     * 
     */
    public double getTotalVolume() {
        return totalVolume;
    }

    /**
     * ����totalVolume���Ե�ֵ��
     * 
     */
    public void setTotalVolume(double value) {
        this.totalVolume = value;
    }

    /**
     * ��ȡpaymentType���Ե�ֵ��
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
     * ����paymentType���Ե�ֵ��
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
     * ��ȡchannelType���Ե�ֵ��
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
     * ����channelType���Ե�ֵ��
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
     * ��ȡchannelCustId���Ե�ֵ��
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
     * ����channelCustId���Ե�ֵ��
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
     * ��ȡdeliveryMode���Ե�ֵ��
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
     * ����deliveryMode���Ե�ֵ��
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
     * ��ȡreciveLoanType���Ե�ֵ��
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
     * ����reciveLoanType���Ե�ֵ��
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
     * ��ȡreviceMoneyAmount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getReviceMoneyAmount() {
        return reviceMoneyAmount;
    }

    /**
     * ����reviceMoneyAmount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setReviceMoneyAmount(BigDecimal value) {
        this.reviceMoneyAmount = value;
    }

    /**
     * ��ȡinsuredAmount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInsuredAmount() {
        return insuredAmount;
    }

    /**
     * ����insuredAmount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInsuredAmount(BigDecimal value) {
        this.insuredAmount = value;
    }

    /**
     * ��ȡorderTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * ����orderTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderTime(Date value) {
        this.orderTime = value;
    }

    /**
     * ��ȡstartStation���Ե�ֵ��
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
     * ����startStation���Ե�ֵ��
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
     * ��ȡstartStationName���Ե�ֵ��
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
     * ����startStationName���Ե�ֵ��
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
     * ��ȡacceptDept���Ե�ֵ��
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
     * ����acceptDept���Ե�ֵ��
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
     * ��ȡacceptDeptName���Ե�ֵ��
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
     * ����acceptDeptName���Ե�ֵ��
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
     * ��ȡorderStatus���Ե�ֵ��
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
     * ����orderStatus���Ե�ֵ��
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
     * ��ȡdealPerson���Ե�ֵ��
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
     * ����dealPerson���Ե�ֵ��
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
     * ��ȡorderAcceptTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getOrderAcceptTime() {
        return orderAcceptTime;
    }

    /**
     * ����orderAcceptTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderAcceptTime(Date value) {
        this.orderAcceptTime = value;
    }

    /**
     * ��ȡreceiver���Ե�ֵ��
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
     * ����receiver���Ե�ֵ��
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
     * ��ȡaccepterPersonMobile���Ե�ֵ��
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
     * ����accepterPersonMobile���Ե�ֵ��
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
     * ��ȡwaybillNumber���Ե�ֵ��
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
     * ����waybillNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaybillNumber(String value) {
        this.waybillNumber = value;
    }

    /**
     * ��ȡresource���Ե�ֵ��
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
     * ����resource���Ե�ֵ��
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
     * ��ȡtransNote���Ե�ֵ��
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
     * ����transNote���Ե�ֵ��
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
     * ��ȡreturnBillType���Ե�ֵ��
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
     * ����returnBillType���Ե�ֵ��
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
     * ��ȡorderPerson���Ե�ֵ��
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
     * ����orderPerson���Ե�ֵ��
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
     * ��ȡfeedbackInfo���Ե�ֵ��
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
     * ����feedbackInfo���Ե�ֵ��
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
     * ��ȡonlineName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnlineName() {
        return onlineName;
    }

    /**
     * ����onlineName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnlineName(String value) {
        this.onlineName = value;
    }

    /**
     * ��ȡmemberType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemberType() {
        return memberType;
    }

    /**
     * ����memberType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemberType(String value) {
        this.memberType = value;
    }

	public String getCouponNumber() {
		return couponNumber;
	}

	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}
}
