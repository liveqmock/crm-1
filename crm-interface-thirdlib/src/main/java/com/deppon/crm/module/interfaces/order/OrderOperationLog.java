
package com.deppon.crm.module.interfaces.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for orderOperationLog complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="orderOperationLog">
 *   &lt;complexContent>
 *     &lt;extension base="{http://order.interfaces.module.crm.deppon.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="contactName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operatorContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operatorId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operatorOrgId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operatorTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="operatorType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderOperationLog", propOrder = {
    "contactName",
    "operatorContent",
    "operatorId",
    "operatorOrgId",
    "operatorTime",
    "operatorType",
    "orderId"
})
public class OrderOperationLog
    extends BaseEntity
{

    protected String contactName;
    protected String operatorContent;
    protected String operatorId;
    protected String operatorOrgId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar operatorTime;
    protected String operatorType;
    protected String orderId;

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
     * Gets the value of the operatorContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorContent() {
        return operatorContent;
    }

    /**
     * Sets the value of the operatorContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorContent(String value) {
        this.operatorContent = value;
    }

    /**
     * Gets the value of the operatorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * Sets the value of the operatorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorId(String value) {
        this.operatorId = value;
    }

    /**
     * Gets the value of the operatorOrgId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorOrgId() {
        return operatorOrgId;
    }

    /**
     * Sets the value of the operatorOrgId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorOrgId(String value) {
        this.operatorOrgId = value;
    }

    /**
     * Gets the value of the operatorTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOperatorTime() {
        return operatorTime;
    }

    /**
     * Sets the value of the operatorTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOperatorTime(XMLGregorianCalendar value) {
        this.operatorTime = value;
    }

    /**
     * Gets the value of the operatorType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorType() {
        return operatorType;
    }

    /**
     * Sets the value of the operatorType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorType(String value) {
        this.operatorType = value;
    }

    /**
     * Gets the value of the orderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderId(String value) {
        this.orderId = value;
    }

}
