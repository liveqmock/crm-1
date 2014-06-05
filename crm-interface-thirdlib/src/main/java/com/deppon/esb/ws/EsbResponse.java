
package com.deppon.esb.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for esbResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="esbResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://common.esb.deppon.com}message" minOccurs="0"/>
 *         &lt;element ref="{http://common.esb.deppon.com}requestId" minOccurs="0"/>
 *         &lt;element ref="{http://common.esb.deppon.com}requestTime" minOccurs="0"/>
 *         &lt;element ref="{http://common.esb.deppon.com}responseTime" minOccurs="0"/>
 *         &lt;element ref="{http://common.esb.deppon.com}serviceCode" minOccurs="0"/>
 *         &lt;element ref="{http://common.esb.deppon.com}stacktrace" minOccurs="0"/>
 *         &lt;element ref="{http://common.esb.deppon.com}status" minOccurs="0"/>
 *         &lt;element ref="{http://common.esb.deppon.com}systemName" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "esbResponse", propOrder = {
    "message",
    "requestId",
    "requestTime",
    "responseTime",
    "serviceCode",
    "stacktrace",
    "status",
    "systemName"
})
@XmlSeeAlso({
    EsbJsonResponse.class
})
public class EsbResponse {

    @XmlElement(namespace = "http://common.esb.deppon.com")
    protected String message;
    @XmlElement(namespace = "http://common.esb.deppon.com")
    protected String requestId;
    @XmlElement(namespace = "http://common.esb.deppon.com")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar requestTime;
    @XmlElement(namespace = "http://common.esb.deppon.com")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar responseTime;
    @XmlElement(namespace = "http://common.esb.deppon.com")
    protected String serviceCode;
    @XmlElement(namespace = "http://common.esb.deppon.com")
    protected String stacktrace;
    @XmlElement(namespace = "http://common.esb.deppon.com")
    protected String status;
    @XmlElement(namespace = "http://common.esb.deppon.com")
    protected String systemName;

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the requestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * Gets the value of the requestTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestTime() {
        return requestTime;
    }

    /**
     * Sets the value of the requestTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestTime(XMLGregorianCalendar value) {
        this.requestTime = value;
    }

    /**
     * Gets the value of the responseTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getResponseTime() {
        return responseTime;
    }

    /**
     * Sets the value of the responseTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setResponseTime(XMLGregorianCalendar value) {
        this.responseTime = value;
    }

    /**
     * Gets the value of the serviceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Sets the value of the serviceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceCode(String value) {
        this.serviceCode = value;
    }

    /**
     * Gets the value of the stacktrace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStacktrace() {
        return stacktrace;
    }

    /**
     * Sets the value of the stacktrace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStacktrace(String value) {
        this.stacktrace = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the systemName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * Sets the value of the systemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemName(String value) {
        this.systemName = value;
    }

}
