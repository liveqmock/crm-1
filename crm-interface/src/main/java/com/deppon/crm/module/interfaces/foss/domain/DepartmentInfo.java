
package com.deppon.crm.module.interfaces.foss.domain;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 * 				��֯��Ϣ
 * 			
 * 
 * <p>DepartmentInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="DepartmentInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pinyin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arrive" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="station" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="slogans" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="openDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="maxTempArrears" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="usedTempArrears" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="billingGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transferCenter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pickupSelf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="delivery" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="airArrive" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="truckArrive" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="singlePieceLimitKG" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="singleBillLimitKG" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="singlePieceLimitVOL" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="singleBillLimitVOL" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="pickupAreaDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliveryAreaDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creatTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="modifyTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifyUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliveryCoordinate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="versionNo" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="canCentralizedPickup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="canPaySerivceFee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="canReturnSignBill" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="canCashOnDelivery" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="canAgentCollected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DepartmentInfo", namespace = "http://www.deppon.com/ows/inteface/domain/", propOrder = {
    "id",
    "code",
    "name",
    "pinyin",
    "leave",
    "arrive",
    "station",
    "slogans",
    "openDate",
    "maxTempArrears",
    "usedTempArrears",
    "billingGroup",
    "transferCenter",
    "pickupSelf",
    "delivery",
    "airArrive",
    "truckArrive",
    "singlePieceLimitKG",
    "singleBillLimitKG",
    "singlePieceLimitVOL",
    "singleBillLimitVOL",
    "pickupAreaDesc",
    "deliveryAreaDesc",
    "creatTime",
    "modifyTime",
    "active",
    "createUserCode",
    "modifyUserCode",
    "deliveryCoordinate",
    "versionNo",
    "canCentralizedPickup",
    "canPaySerivceFee",
    "canReturnSignBill",
    "canCashOnDelivery",
    "canAgentCollected"
})
public class DepartmentInfo {

    @XmlElement(required = true)
    protected String id;
    protected String code;
    protected String name;
    protected String pinyin;
    protected String leave;
    protected String arrive;
    protected String station;
    protected String slogans;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar openDate;
    protected BigDecimal maxTempArrears;
    protected BigDecimal usedTempArrears;
    protected String billingGroup;
    protected String transferCenter;
    protected String pickupSelf;
    protected String delivery;
    protected String airArrive;
    protected String truckArrive;
    protected Double singlePieceLimitKG;
    protected Double singleBillLimitKG;
    protected Double singlePieceLimitVOL;
    protected Double singleBillLimitVOL;
    protected String pickupAreaDesc;
    protected String deliveryAreaDesc;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creatTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar modifyTime;
    protected String active;
    protected String createUserCode;
    protected String modifyUserCode;
    protected String deliveryCoordinate;
    protected Double versionNo;
    protected String canCentralizedPickup;
    protected String canPaySerivceFee;
    protected String canReturnSignBill;
    protected String canCashOnDelivery;
    protected String canAgentCollected;

    /**
     * ��ȡid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * ����id���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * ��ȡcode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * ����code���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * ��ȡname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * ����name���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * ��ȡpinyin���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * ����pinyin���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPinyin(String value) {
        this.pinyin = value;
    }

    /**
     * ��ȡleave���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeave() {
        return leave;
    }

    /**
     * ����leave���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeave(String value) {
        this.leave = value;
    }

    /**
     * ��ȡarrive���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrive() {
        return arrive;
    }

    /**
     * ����arrive���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrive(String value) {
        this.arrive = value;
    }

    /**
     * ��ȡstation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStation() {
        return station;
    }

    /**
     * ����station���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStation(String value) {
        this.station = value;
    }

    /**
     * ��ȡslogans���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlogans() {
        return slogans;
    }

    /**
     * ����slogans���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlogans(String value) {
        this.slogans = value;
    }

    /**
     * ��ȡopenDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOpenDate() {
        return openDate;
    }

    /**
     * ����openDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOpenDate(XMLGregorianCalendar value) {
        this.openDate = value;
    }

    /**
     * ��ȡmaxTempArrears���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaxTempArrears() {
        return maxTempArrears;
    }

    /**
     * ����maxTempArrears���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaxTempArrears(BigDecimal value) {
        this.maxTempArrears = value;
    }

    /**
     * ��ȡusedTempArrears���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUsedTempArrears() {
        return usedTempArrears;
    }

    /**
     * ����usedTempArrears���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUsedTempArrears(BigDecimal value) {
        this.usedTempArrears = value;
    }

    /**
     * ��ȡbillingGroup���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingGroup() {
        return billingGroup;
    }

    /**
     * ����billingGroup���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingGroup(String value) {
        this.billingGroup = value;
    }

    /**
     * ��ȡtransferCenter���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransferCenter() {
        return transferCenter;
    }

    /**
     * ����transferCenter���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransferCenter(String value) {
        this.transferCenter = value;
    }

    /**
     * ��ȡpickupSelf���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupSelf() {
        return pickupSelf;
    }

    /**
     * ����pickupSelf���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupSelf(String value) {
        this.pickupSelf = value;
    }

    /**
     * ��ȡdelivery���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelivery() {
        return delivery;
    }

    /**
     * ����delivery���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelivery(String value) {
        this.delivery = value;
    }

    /**
     * ��ȡairArrive���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirArrive() {
        return airArrive;
    }

    /**
     * ����airArrive���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirArrive(String value) {
        this.airArrive = value;
    }

    /**
     * ��ȡtruckArrive���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTruckArrive() {
        return truckArrive;
    }

    /**
     * ����truckArrive���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTruckArrive(String value) {
        this.truckArrive = value;
    }

    /**
     * ��ȡsinglePieceLimitKG���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSinglePieceLimitKG() {
        return singlePieceLimitKG;
    }

    /**
     * ����singlePieceLimitKG���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSinglePieceLimitKG(Double value) {
        this.singlePieceLimitKG = value;
    }

    /**
     * ��ȡsingleBillLimitKG���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSingleBillLimitKG() {
        return singleBillLimitKG;
    }

    /**
     * ����singleBillLimitKG���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSingleBillLimitKG(Double value) {
        this.singleBillLimitKG = value;
    }

    /**
     * ��ȡsinglePieceLimitVOL���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSinglePieceLimitVOL() {
        return singlePieceLimitVOL;
    }

    /**
     * ����singlePieceLimitVOL���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSinglePieceLimitVOL(Double value) {
        this.singlePieceLimitVOL = value;
    }

    /**
     * ��ȡsingleBillLimitVOL���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSingleBillLimitVOL() {
        return singleBillLimitVOL;
    }

    /**
     * ����singleBillLimitVOL���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSingleBillLimitVOL(Double value) {
        this.singleBillLimitVOL = value;
    }

    /**
     * ��ȡpickupAreaDesc���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupAreaDesc() {
        return pickupAreaDesc;
    }

    /**
     * ����pickupAreaDesc���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupAreaDesc(String value) {
        this.pickupAreaDesc = value;
    }

    /**
     * ��ȡdeliveryAreaDesc���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryAreaDesc() {
        return deliveryAreaDesc;
    }

    /**
     * ����deliveryAreaDesc���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryAreaDesc(String value) {
        this.deliveryAreaDesc = value;
    }

    /**
     * ��ȡcreatTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatTime() {
        return creatTime;
    }

    /**
     * ����creatTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatTime(XMLGregorianCalendar value) {
        this.creatTime = value;
    }

    /**
     * ��ȡmodifyTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getModifyTime() {
        return modifyTime;
    }

    /**
     * ����modifyTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setModifyTime(XMLGregorianCalendar value) {
        this.modifyTime = value;
    }

    /**
     * ��ȡactive���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActive() {
        return active;
    }

    /**
     * ����active���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActive(String value) {
        this.active = value;
    }

    /**
     * ��ȡcreateUserCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateUserCode() {
        return createUserCode;
    }

    /**
     * ����createUserCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateUserCode(String value) {
        this.createUserCode = value;
    }

    /**
     * ��ȡmodifyUserCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifyUserCode() {
        return modifyUserCode;
    }

    /**
     * ����modifyUserCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifyUserCode(String value) {
        this.modifyUserCode = value;
    }

    /**
     * ��ȡdeliveryCoordinate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryCoordinate() {
        return deliveryCoordinate;
    }

    /**
     * ����deliveryCoordinate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryCoordinate(String value) {
        this.deliveryCoordinate = value;
    }

    /**
     * ��ȡversionNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVersionNo() {
        return versionNo;
    }

    /**
     * ����versionNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVersionNo(Double value) {
        this.versionNo = value;
    }

    /**
     * ��ȡcanCentralizedPickup���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanCentralizedPickup() {
        return canCentralizedPickup;
    }

    /**
     * ����canCentralizedPickup���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanCentralizedPickup(String value) {
        this.canCentralizedPickup = value;
    }

    /**
     * ��ȡcanPaySerivceFee���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanPaySerivceFee() {
        return canPaySerivceFee;
    }

    /**
     * ����canPaySerivceFee���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanPaySerivceFee(String value) {
        this.canPaySerivceFee = value;
    }

    /**
     * ��ȡcanReturnSignBill���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanReturnSignBill() {
        return canReturnSignBill;
    }

    /**
     * ����canReturnSignBill���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanReturnSignBill(String value) {
        this.canReturnSignBill = value;
    }

    /**
     * ��ȡcanCashOnDelivery���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanCashOnDelivery() {
        return canCashOnDelivery;
    }

    /**
     * ����canCashOnDelivery���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanCashOnDelivery(String value) {
        this.canCashOnDelivery = value;
    }

    /**
     * ��ȡcanAgentCollected���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanAgentCollected() {
        return canAgentCollected;
    }

    /**
     * ����canAgentCollected���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanAgentCollected(String value) {
        this.canAgentCollected = value;
    }

}
