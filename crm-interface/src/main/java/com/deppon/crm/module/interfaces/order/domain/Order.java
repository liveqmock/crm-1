
package com.deppon.crm.module.interfaces.order.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



/**
 * <p>order complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
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
 *         &lt;element name="contactManId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *         &lt;element name="memberType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="onlineName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *         &lt;element name="receivingToPointName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *         &lt;element name="couponNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "memberType",
    "onlineName",
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
    "receivingToPointName",
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
    "waybillNumber",
    "couponNumber"
})
public class Order
    extends BaseEntity
{

    protected String acceptDept;
    protected String acceptDeptName;
    protected String accepterPersonMobile;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1.class)
    @XmlSchemaType(name = "dateTime")
    protected Date beginAcceptTime;
    protected String channelCustId;
    protected String channelNumber;
    protected String channelType;
    protected String contactAddress;
    protected String contactArea;
    protected String contactCity;
    protected String contactManId;
    protected String contactMobile;
    protected String contactName;
    protected String contactPhone;
    protected String contactProvince;
    protected String dealPerson;
    protected String deliveryMode;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1.class)
    @XmlSchemaType(name = "dateTime")
    protected Date endAcceptTime;
    protected String feedbackInfo;
    protected String goodsName;
    protected Integer goodsNumber;
    protected String goodsType;
    protected Integer hastenCount;
    protected Double insuredAmount;
    protected Boolean isReceiveGoods;
    protected Boolean isSendmessage;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1.class)
    @XmlSchemaType(name = "dateTime")
    protected Date lastHastenTime;
    protected String memberType;
    protected String onlineName;
    @XmlElement(nillable = true)
    protected List<OrderOperationLog> operationLogs;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1.class)
    @XmlSchemaType(name = "dateTime")
    protected Date orderAcceptTime;
    protected String orderNumber;
    protected String orderPerson;
    protected String orderStatus;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1.class)
    @XmlSchemaType(name = "dateTime")
    protected Date orderTime;
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
    protected String receivingToPointName;
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
    protected String couponNumber;

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
     * ��ȡbeginAcceptTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Date }
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
     *     {@link Date }
     *     
     */
    public void setBeginAcceptTime(Date value) {
        this.beginAcceptTime = value;
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
     * ��ȡendAcceptTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Date }
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
     *     {@link Date }
     *     
     */
    public void setEndAcceptTime(Date value) {
        this.endAcceptTime = value;
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
     * ��ȡgoodsNumber���Ե�ֵ��
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
     * ����goodsNumber���Ե�ֵ��
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
     * ��ȡhastenCount���Ե�ֵ��
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
     * ����hastenCount���Ե�ֵ��
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
     * ��ȡinsuredAmount���Ե�ֵ��
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
     * ����insuredAmount���Ե�ֵ��
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
     * ��ȡisReceiveGoods���Ե�ֵ��
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
     * ����isReceiveGoods���Ե�ֵ��
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
     * ��ȡisSendmessage���Ե�ֵ��
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
     * ����isSendmessage���Ե�ֵ��
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
     * ��ȡlastHastenTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Date }
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
     *     {@link Date }
     *     
     */
    public void setLastHastenTime(Date value) {
        this.lastHastenTime = value;
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
     * ��ȡorderAcceptTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Date }
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
     *     {@link Date }
     *     
     */
    public void setOrderAcceptTime(Date value) {
        this.orderAcceptTime = value;
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
     * ��ȡorderTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Date }
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
     *     {@link Date }
     *     
     */
    public void setOrderTime(Date value) {
        this.orderTime = value;
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
     * ��ȡreviceMoneyAmount���Ե�ֵ��
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
     * ����reviceMoneyAmount���Ե�ֵ��
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
     * ��ȡtotalVolume���Ե�ֵ��
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
     * ����totalVolume���Ե�ֵ��
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
     * ��ȡtotalWeight���Ե�ֵ��
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
     * ����totalWeight���Ե�ֵ��
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
     * ��ȡcouponNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCouponNumber() {
        return couponNumber;
    }

    /**
     * ����couponNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCouponNumber(String value) {
        this.couponNumber = value;
    }

}
