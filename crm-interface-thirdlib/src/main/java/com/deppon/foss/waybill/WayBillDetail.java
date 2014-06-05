
package com.deppon.foss.waybill;

import java.math.BigDecimal;
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
 * �˵���Ϣ
 * 
 * <p>WayBillDetail complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="WayBillDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tranType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tranProperty" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sender" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="senderPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="senderMobile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="departure" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="senderAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consignee" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="consigneePhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="consigneeMobile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="destination" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="consigneeAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="goodName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pieces" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="weight" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="cubage" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="totalCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="payment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="preCharge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="arriveCharge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="refundType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refund" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="refundFee" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="deliveryType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="consignCharge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="deliveryCharge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="signBackCharge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="pickCharge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="laborRebate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="publishCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="departureDeptName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="departureDeptNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="departureDeptAddr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="departureDeptPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="departureDeptFax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ladingStationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ladingStationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ladingStationAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ladingStationPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ladingStationFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isSigned" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="isNormalSigned" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="signRecorderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="firstSignedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="signedDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="insuranceValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="insurance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="packing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otherPayment" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="tranDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="senderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consigneeNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isClear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signBackType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transNotice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sendTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="receiveDeptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiveDeptNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stowageDept" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="senderCityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="senderCityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="senderProvinceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="senderProvinceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consigneeCityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consigneeCityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consigneeProvinceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consigneeProvinceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isDoorToDoorPick" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="smsNoticeResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signBillBackWay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="waybillCostInfos" type="{http://www.deppon.com/esb/inteface/domain/waybillService}WaybillCostInfo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WayBillDetail", propOrder = {
    "number",
    "tranType",
    "tranProperty",
    "sender",
    "senderPhone",
    "senderMobile",
    "departure",
    "senderAddress",
    "consignee",
    "consigneePhone",
    "consigneeMobile",
    "destination",
    "consigneeAddress",
    "goodName",
    "pieces",
    "weight",
    "cubage",
    "totalCharge",
    "payment",
    "preCharge",
    "arriveCharge",
    "refundType",
    "refund",
    "refundFee",
    "deliveryType",
    "consignCharge",
    "deliveryCharge",
    "signBackCharge",
    "pickCharge",
    "laborRebate",
    "publishCharge",
    "departureDeptName",
    "departureDeptNumber",
    "departureDeptAddr",
    "departureDeptPhone",
    "departureDeptFax",
    "ladingStationName",
    "ladingStationNumber",
    "ladingStationAddr",
    "ladingStationPhone",
    "ladingStationFax",
    "isSigned",
    "isNormalSigned",
    "signRecorderId",
    "signedDate",
    "firstSignedDate",
    "signedDesc",
    "orderNumber",
    "insuranceValue",
    "insurance",
    "packing",
    "orderState",
    "otherPayment",
    "tranDesc",
    "senderNumber",
    "consigneeNumber",
    "isClear",
    "signBackType",
    "transNotice",
    "sendTime",
    "receiveDeptName",
    "receiveDeptNumber",
    "stowageDept",
    "senderCityCode",
    "senderCityName",
    "senderProvinceCode",
    "senderProvinceName",
    "consigneeCityCode",
    "consigneeCityName",
    "consigneeProvinceCode",
    "consigneeProvinceName",
    "isDoorToDoorPick",
    "smsNoticeResult",
    "signBillBackWay",
    "waybillCostInfos",
    "exDepartureRegionNubmer",
    "exDepartureRegionName",
    "exDepartureRegionStandardNubmer",
    "exDestinationRegionNubmer",
    "exDestinationRegionName",
    "exDestinationRegionStandardNubmer",
    "exDepartureCourierNumber",
    "exDepartureCourierName",
    "exDepartureDeptNumber",
    "exDepartureDeptStandardNumber",
    "exDepartureDeptName",
    "exDestinationCourierNumber",
    "exDestinationCourierName",
    "exDestinationDeptNumber",
    "exDestinationDeptStandardNumber",
    "exDestinationDeptName"
})
public class WayBillDetail {

    @XmlElement(required = true)
    protected String number;
    @XmlElement(required = true)
    protected String tranType;
    @XmlElement(required = true)
    protected String tranProperty;
    @XmlElement(required = true)
    protected String sender;
    @XmlElement(required = true)
    protected String senderPhone;
    @XmlElement(required = true)
    protected String senderMobile;
    @XmlElement(required = true)
    protected String departure;
    protected String senderAddress;
    @XmlElement(required = true)
    protected String consignee;
    @XmlElement(required = true)
    protected String consigneePhone;
    @XmlElement(required = true)
    protected String consigneeMobile;
    @XmlElement(required = true)
    protected String destination;
    @XmlElement(required = true)
    protected String consigneeAddress;
    @XmlElement(required = true)
    protected String goodName;
    protected int pieces;
    protected Float weight;
    protected Float cubage;
    @XmlElement(required = true)
    protected BigDecimal totalCharge;
    @XmlElement(required = true)
    protected String payment;
    protected BigDecimal preCharge;
    protected BigDecimal arriveCharge;
    protected String refundType;
    protected BigDecimal refund;
    protected BigDecimal refundFee;
    @XmlElement(required = true)
    protected String deliveryType;
    protected BigDecimal consignCharge;
    protected BigDecimal deliveryCharge;
    protected BigDecimal signBackCharge;
    protected BigDecimal pickCharge;
    protected BigDecimal laborRebate;
    @XmlElement(required = true)
    protected BigDecimal publishCharge;
    @XmlElement(required = true)
    protected String departureDeptName;
    @XmlElement(required = true)
    protected String departureDeptNumber;
    @XmlElement(required = true)
    protected String departureDeptAddr;
    @XmlElement(required = true)
    protected String departureDeptPhone;
    @XmlElement(required = true)
    protected String departureDeptFax;
    protected String ladingStationName;
    protected String ladingStationNumber;
    protected String ladingStationAddr;
    protected String ladingStationPhone;
    protected String ladingStationFax;
    protected Boolean isSigned;
    protected Boolean isNormalSigned;
    protected String signRecorderId;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date signedDate;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date firstSignedDate;
    protected String signedDesc;
    protected String orderNumber;
    protected BigDecimal insuranceValue;
    protected BigDecimal insurance;
    protected String packing;
    protected String orderState;
    protected BigDecimal otherPayment;
    protected String tranDesc;
    protected String senderNumber;
    protected String consigneeNumber;
    protected String isClear;
    protected String signBackType;
    protected String transNotice;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date sendTime;
    protected String receiveDeptName;
    protected String receiveDeptNumber;
    @XmlElement(required = true)
    protected String stowageDept;
    protected String senderCityCode;
    protected String senderCityName;
    protected String senderProvinceCode;
    protected String senderProvinceName;
    protected String consigneeCityCode;
    protected String consigneeCityName;
    protected String consigneeProvinceCode;
    protected String consigneeProvinceName;
    protected Boolean isDoorToDoorPick;
    protected String smsNoticeResult;
    protected String signBillBackWay;
    protected String exDepartureRegionNubmer;
    protected String exDepartureRegionName;
    protected String exDepartureRegionStandardNubmer;
    protected String exDestinationRegionNubmer;
    protected String exDestinationRegionName;
    protected String exDestinationRegionStandardNubmer;
    protected String exDepartureCourierNumber;
    protected String exDepartureCourierName;
    protected String exDepartureDeptNumber;
    protected String exDepartureDeptStandardNumber;
    protected String exDepartureDeptName;
    protected String exDestinationCourierNumber;
    protected String exDestinationCourierName;
    protected String exDestinationDeptNumber;
    protected String exDestinationDeptStandardNumber;
    protected String exDestinationDeptName;
    
    @XmlElement(required = true)
    protected List<WaybillCostInfo> waybillCostInfos;

	/**
     * ��ȡnumber���Ե�ֵ��
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
     * ����number���Ե�ֵ��
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
     * ��ȡtranType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranType() {
        return tranType;
    }

    /**
     * ����tranType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranType(String value) {
        this.tranType = value;
    }

    /**
     * ��ȡtranProperty���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranProperty() {
        return tranProperty;
    }

    /**
     * ����tranProperty���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranProperty(String value) {
        this.tranProperty = value;
    }

    /**
     * ��ȡsender���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSender() {
        return sender;
    }

    /**
     * ����sender���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSender(String value) {
        this.sender = value;
    }

    /**
     * ��ȡsenderPhone���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderPhone() {
        return senderPhone;
    }

    /**
     * ����senderPhone���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderPhone(String value) {
        this.senderPhone = value;
    }

    /**
     * ��ȡsenderMobile���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderMobile() {
        return senderMobile;
    }

    /**
     * ����senderMobile���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderMobile(String value) {
        this.senderMobile = value;
    }

    /**
     * ��ȡdeparture���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeparture() {
        return departure;
    }

    /**
     * ����departure���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeparture(String value) {
        this.departure = value;
    }

    /**
     * ��ȡsenderAddress���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderAddress() {
        return senderAddress;
    }

    /**
     * ����senderAddress���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderAddress(String value) {
        this.senderAddress = value;
    }

    /**
     * ��ȡconsignee���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * ����consignee���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsignee(String value) {
        this.consignee = value;
    }

    /**
     * ��ȡconsigneePhone���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsigneePhone() {
        return consigneePhone;
    }

    /**
     * ����consigneePhone���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsigneePhone(String value) {
        this.consigneePhone = value;
    }

    /**
     * ��ȡconsigneeMobile���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    /**
     * ����consigneeMobile���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsigneeMobile(String value) {
        this.consigneeMobile = value;
    }

    /**
     * ��ȡdestination���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestination() {
        return destination;
    }

    /**
     * ����destination���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestination(String value) {
        this.destination = value;
    }

    /**
     * ��ȡconsigneeAddress���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    /**
     * ����consigneeAddress���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsigneeAddress(String value) {
        this.consigneeAddress = value;
    }

    /**
     * ��ȡgoodName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodName() {
        return goodName;
    }

    /**
     * ����goodName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodName(String value) {
        this.goodName = value;
    }

    /**
     * ��ȡpieces���Ե�ֵ��
     * 
     */
    public int getPieces() {
        return pieces;
    }

    /**
     * ����pieces���Ե�ֵ��
     * 
     */
    public void setPieces(int value) {
        this.pieces = value;
    }

    /**
     * ��ȡweight���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getWeight() {
        return weight;
    }

    /**
     * ����weight���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setWeight(Float value) {
        this.weight = value;
    }

    /**
     * ��ȡcubage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getCubage() {
        return cubage;
    }

    /**
     * ����cubage���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setCubage(Float value) {
        this.cubage = value;
    }

    /**
     * ��ȡtotalCharge���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    /**
     * ����totalCharge���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalCharge(BigDecimal value) {
        this.totalCharge = value;
    }

    /**
     * ��ȡpayment���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayment() {
        return payment;
    }

    /**
     * ����payment���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayment(String value) {
        this.payment = value;
    }

    /**
     * ��ȡpreCharge���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPreCharge() {
        return preCharge;
    }

    /**
     * ����preCharge���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPreCharge(BigDecimal value) {
        this.preCharge = value;
    }

    /**
     * ��ȡarriveCharge���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getArriveCharge() {
        return arriveCharge;
    }

    /**
     * ����arriveCharge���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setArriveCharge(BigDecimal value) {
        this.arriveCharge = value;
    }

    /**
     * ��ȡrefundType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefundType() {
        return refundType;
    }

    /**
     * ����refundType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefundType(String value) {
        this.refundType = value;
    }

    /**
     * ��ȡrefund���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRefund() {
        return refund;
    }

    /**
     * ����refund���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRefund(BigDecimal value) {
        this.refund = value;
    }

    /**
     * ��ȡrefundFee���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRefundFee() {
        return refundFee;
    }

    /**
     * ����refundFee���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRefundFee(BigDecimal value) {
        this.refundFee = value;
    }

    /**
     * ��ȡdeliveryType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryType() {
        return deliveryType;
    }

    /**
     * ����deliveryType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryType(String value) {
        this.deliveryType = value;
    }

    /**
     * ��ȡconsignCharge���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getConsignCharge() {
        return consignCharge;
    }

    /**
     * ����consignCharge���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setConsignCharge(BigDecimal value) {
        this.consignCharge = value;
    }

    /**
     * ��ȡdeliveryCharge���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDeliveryCharge() {
        return deliveryCharge;
    }

    /**
     * ����deliveryCharge���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDeliveryCharge(BigDecimal value) {
        this.deliveryCharge = value;
    }

    /**
     * ��ȡsignBackCharge���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSignBackCharge() {
        return signBackCharge;
    }

    /**
     * ����signBackCharge���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSignBackCharge(BigDecimal value) {
        this.signBackCharge = value;
    }

    /**
     * ��ȡpickCharge���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPickCharge() {
        return pickCharge;
    }

    /**
     * ����pickCharge���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPickCharge(BigDecimal value) {
        this.pickCharge = value;
    }

    /**
     * ��ȡlaborRebate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLaborRebate() {
        return laborRebate;
    }

    /**
     * ����laborRebate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLaborRebate(BigDecimal value) {
        this.laborRebate = value;
    }

    /**
     * ��ȡpublishCharge���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPublishCharge() {
        return publishCharge;
    }

    /**
     * ����publishCharge���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPublishCharge(BigDecimal value) {
        this.publishCharge = value;
    }

    /**
     * ��ȡdepartureDeptName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureDeptName() {
        return departureDeptName;
    }

    /**
     * ����departureDeptName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureDeptName(String value) {
        this.departureDeptName = value;
    }

    /**
     * ��ȡdepartureDeptNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureDeptNumber() {
        return departureDeptNumber;
    }

    /**
     * ����departureDeptNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureDeptNumber(String value) {
        this.departureDeptNumber = value;
    }

    /**
     * ��ȡdepartureDeptAddr���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureDeptAddr() {
        return departureDeptAddr;
    }

    /**
     * ����departureDeptAddr���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureDeptAddr(String value) {
        this.departureDeptAddr = value;
    }

    /**
     * ��ȡdepartureDeptPhone���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureDeptPhone() {
        return departureDeptPhone;
    }

    /**
     * ����departureDeptPhone���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureDeptPhone(String value) {
        this.departureDeptPhone = value;
    }

    /**
     * ��ȡdepartureDeptFax���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureDeptFax() {
        return departureDeptFax;
    }

    /**
     * ����departureDeptFax���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureDeptFax(String value) {
        this.departureDeptFax = value;
    }

    /**
     * ��ȡladingStationName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLadingStationName() {
        return ladingStationName;
    }

    /**
     * ����ladingStationName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLadingStationName(String value) {
        this.ladingStationName = value;
    }

    /**
     * ��ȡladingStationNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLadingStationNumber() {
        return ladingStationNumber;
    }

    /**
     * ����ladingStationNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLadingStationNumber(String value) {
        this.ladingStationNumber = value;
    }

    /**
     * ��ȡladingStationAddr���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLadingStationAddr() {
        return ladingStationAddr;
    }

    /**
     * ����ladingStationAddr���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLadingStationAddr(String value) {
        this.ladingStationAddr = value;
    }

    /**
     * ��ȡladingStationPhone���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLadingStationPhone() {
        return ladingStationPhone;
    }

    /**
     * ����ladingStationPhone���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLadingStationPhone(String value) {
        this.ladingStationPhone = value;
    }

    /**
     * ��ȡladingStationFax���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLadingStationFax() {
        return ladingStationFax;
    }

    /**
     * ����ladingStationFax���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLadingStationFax(String value) {
        this.ladingStationFax = value;
    }

    /**
     * ��ȡisSigned���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSigned() {
        return isSigned;
    }

    /**
     * ����isSigned���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSigned(Boolean value) {
        this.isSigned = value;
    }

    /**
     * ��ȡisNormalSigned���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsNormalSigned() {
        return isNormalSigned;
    }

    /**
     * ����isNormalSigned���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsNormalSigned(Boolean value) {
        this.isNormalSigned = value;
    }

    /**
     * ��ȡsignRecorderId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignRecorderId() {
        return signRecorderId;
    }

    /**
     * ����signRecorderId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignRecorderId(String value) {
        this.signRecorderId = value;
    }

    /**
     * ��ȡsignedDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getSignedDate() {
        return signedDate;
    }

    /**
     * ����signedDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignedDate(Date value) {
        this.signedDate = value;
    }

    /**
     * ��ȡfirstSignedDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFirstSignedDate() {
        return firstSignedDate;
    }

    /**
     * ����firstSignedDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstSignedDate(Date value) {
        this.firstSignedDate = value;
    }

    /**
     * ��ȡsignedDesc���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignedDesc() {
        return signedDesc;
    }

    /**
     * ����signedDesc���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignedDesc(String value) {
        this.signedDesc = value;
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
     * ��ȡinsuranceValue���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInsuranceValue() {
        return insuranceValue;
    }

    /**
     * ����insuranceValue���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInsuranceValue(BigDecimal value) {
        this.insuranceValue = value;
    }

    /**
     * ��ȡinsurance���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInsurance() {
        return insurance;
    }

    /**
     * ����insurance���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInsurance(BigDecimal value) {
        this.insurance = value;
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
     * ��ȡorderState���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderState() {
        return orderState;
    }

    /**
     * ����orderState���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderState(String value) {
        this.orderState = value;
    }

    /**
     * ��ȡotherPayment���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOtherPayment() {
        return otherPayment;
    }

    /**
     * ����otherPayment���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOtherPayment(BigDecimal value) {
        this.otherPayment = value;
    }

    /**
     * ��ȡtranDesc���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranDesc() {
        return tranDesc;
    }

    /**
     * ����tranDesc���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranDesc(String value) {
        this.tranDesc = value;
    }

    /**
     * ��ȡsenderNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderNumber() {
        return senderNumber;
    }

    /**
     * ����senderNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderNumber(String value) {
        this.senderNumber = value;
    }

    /**
     * ��ȡconsigneeNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsigneeNumber() {
        return consigneeNumber;
    }

    /**
     * ����consigneeNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsigneeNumber(String value) {
        this.consigneeNumber = value;
    }

    /**
     * ��ȡisClear���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsClear() {
        return isClear;
    }

    /**
     * ����isClear���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsClear(String value) {
        this.isClear = value;
    }

    /**
     * ��ȡsignBackType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignBackType() {
        return signBackType;
    }

    /**
     * ����signBackType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignBackType(String value) {
        this.signBackType = value;
    }

    /**
     * ��ȡtransNotice���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransNotice() {
        return transNotice;
    }

    /**
     * ����transNotice���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransNotice(String value) {
        this.transNotice = value;
    }

    /**
     * ��ȡsendTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * ����sendTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendTime(Date value) {
        this.sendTime = value;
    }

    /**
     * ��ȡreceiveDeptName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiveDeptName() {
        return receiveDeptName;
    }

    /**
     * ����receiveDeptName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiveDeptName(String value) {
        this.receiveDeptName = value;
    }

    /**
     * ��ȡreceiveDeptNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiveDeptNumber() {
        return receiveDeptNumber;
    }

    /**
     * ����receiveDeptNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiveDeptNumber(String value) {
        this.receiveDeptNumber = value;
    }

    /**
     * ��ȡstowageDept���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStowageDept() {
        return stowageDept;
    }

    /**
     * ����stowageDept���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStowageDept(String value) {
        this.stowageDept = value;
    }

    /**
     * ��ȡsenderCityCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderCityCode() {
        return senderCityCode;
    }

    /**
     * ����senderCityCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderCityCode(String value) {
        this.senderCityCode = value;
    }

    /**
     * ��ȡsenderCityName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderCityName() {
        return senderCityName;
    }

    /**
     * ����senderCityName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderCityName(String value) {
        this.senderCityName = value;
    }

    /**
     * ��ȡsenderProvinceCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderProvinceCode() {
        return senderProvinceCode;
    }

    /**
     * ����senderProvinceCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderProvinceCode(String value) {
        this.senderProvinceCode = value;
    }

    /**
     * ��ȡsenderProvinceName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderProvinceName() {
        return senderProvinceName;
    }

    /**
     * ����senderProvinceName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderProvinceName(String value) {
        this.senderProvinceName = value;
    }

    /**
     * ��ȡconsigneeCityCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsigneeCityCode() {
        return consigneeCityCode;
    }

    /**
     * ����consigneeCityCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsigneeCityCode(String value) {
        this.consigneeCityCode = value;
    }

    /**
     * ��ȡconsigneeCityName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsigneeCityName() {
        return consigneeCityName;
    }

    /**
     * ����consigneeCityName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsigneeCityName(String value) {
        this.consigneeCityName = value;
    }

    /**
     * ��ȡconsigneeProvinceCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsigneeProvinceCode() {
        return consigneeProvinceCode;
    }

    /**
     * ����consigneeProvinceCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsigneeProvinceCode(String value) {
        this.consigneeProvinceCode = value;
    }

    /**
     * ��ȡconsigneeProvinceName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsigneeProvinceName() {
        return consigneeProvinceName;
    }

    /**
     * ����consigneeProvinceName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsigneeProvinceName(String value) {
        this.consigneeProvinceName = value;
    }

    /**
     * ��ȡisDoorToDoorPick���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsDoorToDoorPick() {
        return isDoorToDoorPick;
    }

    /**
     * ����isDoorToDoorPick���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDoorToDoorPick(Boolean value) {
        this.isDoorToDoorPick = value;
    }

    /**
     * ��ȡsmsNoticeResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsNoticeResult() {
        return smsNoticeResult;
    }

    /**
     * ����smsNoticeResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsNoticeResult(String value) {
        this.smsNoticeResult = value;
    }

    /**
     * ��ȡsignBillBackWay���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignBillBackWay() {
        return signBillBackWay;
    }

    /**
     * ����signBillBackWay���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignBillBackWay(String value) {
        this.signBillBackWay = value;
    }

    
    public String getExDepartureRegionNubmer() {
		return exDepartureRegionNubmer;
	}

	public void setExDepartureRegionNubmer(String exDepartureRegionNubmer) {
		this.exDepartureRegionNubmer = exDepartureRegionNubmer;
	}

	public String getExDepartureRegionName() {
		return exDepartureRegionName;
	}

	public void setExDepartureRegionName(String exDepartureRegionName) {
		this.exDepartureRegionName = exDepartureRegionName;
	}

	public String getExDepartureRegionStandardNubmer() {
		return exDepartureRegionStandardNubmer;
	}

	public void setExDepartureRegionStandardNubmer(
			String exDepartureRegionStandardNubmer) {
		this.exDepartureRegionStandardNubmer = exDepartureRegionStandardNubmer;
	}

	public String getExDestinationRegionNubmer() {
		return exDestinationRegionNubmer;
	}

	public void setExDestinationRegionNubmer(String exDestinationRegionNubmer) {
		this.exDestinationRegionNubmer = exDestinationRegionNubmer;
	}

	public String getExDestinationRegionName() {
		return exDestinationRegionName;
	}

	public void setExDestinationRegionName(String exDestinationRegionName) {
		this.exDestinationRegionName = exDestinationRegionName;
	}

	public String getExDestinationRegionStandardNubmer() {
		return exDestinationRegionStandardNubmer;
	}

	public void setExDestinationRegionStandardNubmer(
			String exDestinationRegionStandardNubmer) {
		this.exDestinationRegionStandardNubmer = exDestinationRegionStandardNubmer;
	}

	public String getExDepartureCourierNumber() {
		return exDepartureCourierNumber;
	}

	public void setExDepartureCourierNumber(String exDepartureCourierNumber) {
		this.exDepartureCourierNumber = exDepartureCourierNumber;
	}

	public String getExDepartureCourierName() {
		return exDepartureCourierName;
	}

	public void setExDepartureCourierName(String exDepartureCourierName) {
		this.exDepartureCourierName = exDepartureCourierName;
	}

	public String getExDepartureDeptNumber() {
		return exDepartureDeptNumber;
	}

	public void setExDepartureDeptNumber(String exDepartureDeptNumber) {
		this.exDepartureDeptNumber = exDepartureDeptNumber;
	}

	public String getExDepartureDeptStandardNumber() {
		return exDepartureDeptStandardNumber;
	}

	public void setExDepartureDeptStandardNumber(
			String exDepartureDeptStandardNumber) {
		this.exDepartureDeptStandardNumber = exDepartureDeptStandardNumber;
	}

	public String getExDepartureDeptName() {
		return exDepartureDeptName;
	}

	public void setExDepartureDeptName(String exDepartureDeptName) {
		this.exDepartureDeptName = exDepartureDeptName;
	}

	public String getExDestinationCourierNumber() {
		return exDestinationCourierNumber;
	}

	public void setExDestinationCourierNumber(String exDestinationCourierNumber) {
		this.exDestinationCourierNumber = exDestinationCourierNumber;
	}

	public String getExDestinationCourierName() {
		return exDestinationCourierName;
	}

	public void setExDestinationCourierName(String exDestinationCourierName) {
		this.exDestinationCourierName = exDestinationCourierName;
	}

	public String getExDestinationDeptNumber() {
		return exDestinationDeptNumber;
	}

	public void setExDestinationDeptNumber(String exDestinationDeptNumber) {
		this.exDestinationDeptNumber = exDestinationDeptNumber;
	}

	public String getExDestinationDeptStandardNumber() {
		return exDestinationDeptStandardNumber;
	}

	public void setExDestinationDeptStandardNumber(
			String exDestinationDeptStandardNumber) {
		this.exDestinationDeptStandardNumber = exDestinationDeptStandardNumber;
	}

	public String getExDestinationDeptName() {
		return exDestinationDeptName;
	}

	public void setExDestinationDeptName(String exDestinationDeptName) {
		this.exDestinationDeptName = exDestinationDeptName;
	}

	/**
     * Gets the value of the waybillCostInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the waybillCostInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWaybillCostInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WaybillCostInfo }
     * 
     * 
     */
    public List<WaybillCostInfo> getWaybillCostInfos() {
        if (waybillCostInfos == null) {
            waybillCostInfos = new ArrayList<WaybillCostInfo>();
        }
        return this.waybillCostInfos;
    }

}
