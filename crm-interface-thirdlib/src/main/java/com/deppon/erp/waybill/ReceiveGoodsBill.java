
package com.deppon.erp.waybill;

import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for receiveGoodsBill complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="receiveGoodsBill">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creatorId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creatorNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cubage" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="custAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliverMode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="deliveryVehId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliveryVehNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstPickTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="goodsName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="goodsType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ladingStationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastPickTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="orderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="packing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pieces" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transProperty" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="usingVehicleDeptId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usingVehicleDeptNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vehDeptId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vehDeptNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="weight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "receiveGoodsBill", propOrder = {
    "creatorId",
    "creatorNum",
    "cubage",
    "custAddress",
    "custName",
    "custTel",
    "deliverMode",
    "deliveryVehId",
    "deliveryVehNum",
    "firstPickTime",
    "goodsName",
    "goodsType",
    "ladingStationId",
    "lastPickTime",
    "orderNumber",
    "orderType",
    "orderedTime",
    "packing",
    "pieces",
    "remark",
    "size",
    "transProperty",
    "usingVehicleDeptId",
    "usingVehicleDeptNum",
    "vehDeptId",
    "vehDeptNum",
    "weight"
})
public class ReceiveGoodsBill {

    protected String creatorId;
    protected String creatorNum;
    protected BigDecimal cubage;
    protected String custAddress;
    protected String custName;
    protected String custTel;
    protected Integer deliverMode;
    protected String deliveryVehId;
    protected String deliveryVehNum;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date firstPickTime;
    protected String goodsName;
    protected String goodsType;
    protected String ladingStationId;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date lastPickTime;
    protected String orderNumber;
    protected String orderType;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date orderedTime;
    protected String packing;
    protected Integer pieces;
    protected String remark;
    protected String size;
    protected Integer transProperty;
    protected String usingVehicleDeptId;
    protected String usingVehicleDeptNum;
    protected String vehDeptId;
    protected String vehDeptNum;
    protected BigDecimal weight;

    /**
     * Gets the value of the creatorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * Sets the value of the creatorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatorId(String value) {
        this.creatorId = value;
    }

    /**
     * Gets the value of the creatorNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatorNum() {
        return creatorNum;
    }

    /**
     * Sets the value of the creatorNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatorNum(String value) {
        this.creatorNum = value;
    }

    /**
     * Gets the value of the cubage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCubage() {
        return cubage;
    }

    /**
     * Sets the value of the cubage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCubage(BigDecimal value) {
        this.cubage = value;
    }

    /**
     * Gets the value of the custAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustAddress() {
        return custAddress;
    }

    /**
     * Sets the value of the custAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustAddress(String value) {
        this.custAddress = value;
    }

    /**
     * Gets the value of the custName property.
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
     * Sets the value of the custName property.
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
     * Gets the value of the custTel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTel() {
        return custTel;
    }

    /**
     * Sets the value of the custTel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTel(String value) {
        this.custTel = value;
    }

    /**
     * Gets the value of the deliverMode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDeliverMode() {
        return deliverMode;
    }

    /**
     * Sets the value of the deliverMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDeliverMode(Integer value) {
        this.deliverMode = value;
    }

    /**
     * Gets the value of the deliveryVehId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryVehId() {
        return deliveryVehId;
    }

    /**
     * Sets the value of the deliveryVehId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryVehId(String value) {
        this.deliveryVehId = value;
    }

    /**
     * Gets the value of the deliveryVehNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryVehNum() {
        return deliveryVehNum;
    }

    /**
     * Sets the value of the deliveryVehNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryVehNum(String value) {
        this.deliveryVehNum = value;
    }

    /**
     * Gets the value of the firstPickTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFirstPickTime() {
        return firstPickTime;
    }

    /**
     * Sets the value of the firstPickTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstPickTime(Date value) {
        this.firstPickTime = value;
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
     * Gets the value of the ladingStationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLadingStationId() {
        return ladingStationId;
    }

    /**
     * Sets the value of the ladingStationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLadingStationId(String value) {
        this.ladingStationId = value;
    }

    /**
     * Gets the value of the lastPickTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getLastPickTime() {
        return lastPickTime;
    }

    /**
     * Sets the value of the lastPickTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastPickTime(Date value) {
        this.lastPickTime = value;
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
     * Gets the value of the orderType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * Sets the value of the orderType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderType(String value) {
        this.orderType = value;
    }

    /**
     * Gets the value of the orderedTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getOrderedTime() {
        return orderedTime;
    }

    /**
     * Sets the value of the orderedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderedTime(Date value) {
        this.orderedTime = value;
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
     * Gets the value of the pieces property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPieces() {
        return pieces;
    }

    /**
     * Sets the value of the pieces property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPieces(Integer value) {
        this.pieces = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSize(String value) {
        this.size = value;
    }

    /**
     * Gets the value of the transProperty property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTransProperty() {
        return transProperty;
    }

    /**
     * Sets the value of the transProperty property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTransProperty(Integer value) {
        this.transProperty = value;
    }

    /**
     * Gets the value of the usingVehicleDeptId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsingVehicleDeptId() {
        return usingVehicleDeptId;
    }

    /**
     * Sets the value of the usingVehicleDeptId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsingVehicleDeptId(String value) {
        this.usingVehicleDeptId = value;
    }

    /**
     * Gets the value of the usingVehicleDeptNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsingVehicleDeptNum() {
        return usingVehicleDeptNum;
    }

    /**
     * Sets the value of the usingVehicleDeptNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsingVehicleDeptNum(String value) {
        this.usingVehicleDeptNum = value;
    }

    /**
     * Gets the value of the vehDeptId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVehDeptId() {
        return vehDeptId;
    }

    /**
     * Sets the value of the vehDeptId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVehDeptId(String value) {
        this.vehDeptId = value;
    }

    /**
     * Gets the value of the vehDeptNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVehDeptNum() {
        return vehDeptNum;
    }

    /**
     * Sets the value of the vehDeptNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVehDeptNum(String value) {
        this.vehDeptNum = value;
    }

    /**
     * Gets the value of the weight property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWeight(BigDecimal value) {
        this.weight = value;
    }

}
