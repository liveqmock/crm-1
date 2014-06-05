
package com.deppon.erp.custAndOrder;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for exceptionOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="exceptionOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deptNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="driver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="driverPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exceptionReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="operateNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderStatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wayBillNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "exceptionOrder", propOrder = {
    "deptNum",
    "driver",
    "driverPhone",
    "exceptionReason",
    "operateDate",
    "operateNum",
    "orderNum",
    "orderStatus",
    "remark",
    "wayBillNum"
})
public class ExceptionOrder {

    protected String deptNum;
    protected String driver;
    protected String driverPhone;
    protected String exceptionReason;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date operateDate;
    protected String operateNum;
    protected String orderNum;
    protected Integer orderStatus;
    protected String remark;
    protected String wayBillNum;

    /**
     * Gets the value of the deptNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptNum() {
        return deptNum;
    }

    /**
     * Sets the value of the deptNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptNum(String value) {
        this.deptNum = value;
    }

    /**
     * Gets the value of the driver property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Sets the value of the driver property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver(String value) {
        this.driver = value;
    }

    /**
     * Gets the value of the driverPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverPhone() {
        return driverPhone;
    }

    /**
     * Sets the value of the driverPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverPhone(String value) {
        this.driverPhone = value;
    }

    /**
     * Gets the value of the exceptionReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionReason() {
        return exceptionReason;
    }

    /**
     * Sets the value of the exceptionReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionReason(String value) {
        this.exceptionReason = value;
    }

    /**
     * Gets the value of the operateDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getOperateDate() {
        return operateDate;
    }

    /**
     * Sets the value of the operateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperateDate(Date value) {
        this.operateDate = value;
    }

    /**
     * Gets the value of the operateNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperateNum() {
        return operateNum;
    }

    /**
     * Sets the value of the operateNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperateNum(String value) {
        this.operateNum = value;
    }

    /**
     * Gets the value of the orderNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * Sets the value of the orderNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderNum(String value) {
        this.orderNum = value;
    }

    /**
     * Gets the value of the orderStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets the value of the orderStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOrderStatus(Integer value) {
        this.orderStatus = value;
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
     * Gets the value of the wayBillNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWayBillNum() {
        return wayBillNum;
    }

    /**
     * Sets the value of the wayBillNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWayBillNum(String value) {
        this.wayBillNum = value;
    }

}
