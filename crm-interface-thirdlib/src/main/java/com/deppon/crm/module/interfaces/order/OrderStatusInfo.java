
package com.deppon.crm.module.interfaces.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for orderStatusInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="orderStatusInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="crmOrderState" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="processPerson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="processDept" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="processDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="orderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="waybillNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="failReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderStatusInfo", propOrder = {
    "crmOrderState",
    "processPerson",
    "processDept",
    "processDate",
    "orderNumber",
    "waybillNumber",
    "failReason"
})
public class OrderStatusInfo {

    protected int crmOrderState;
    protected String processPerson;
    protected String processDept;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar processDate;
    protected String orderNumber;
    protected String waybillNumber;
    protected String failReason;

    /**
     * Gets the value of the crmOrderState property.
     * 
     */
    public int getCrmOrderState() {
        return crmOrderState;
    }

    /**
     * Sets the value of the crmOrderState property.
     * 
     */
    public void setCrmOrderState(int value) {
        this.crmOrderState = value;
    }

    /**
     * Gets the value of the processPerson property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessPerson() {
        return processPerson;
    }

    /**
     * Sets the value of the processPerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessPerson(String value) {
        this.processPerson = value;
    }

    /**
     * Gets the value of the processDept property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessDept() {
        return processDept;
    }

    /**
     * Sets the value of the processDept property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessDept(String value) {
        this.processDept = value;
    }

    /**
     * Gets the value of the processDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProcessDate() {
        return processDate;
    }

    /**
     * Sets the value of the processDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProcessDate(XMLGregorianCalendar value) {
        this.processDate = value;
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

    /**
     * Gets the value of the failReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFailReason() {
        return failReason;
    }

    /**
     * Sets the value of the failReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFailReason(String value) {
        this.failReason = value;
    }

}
