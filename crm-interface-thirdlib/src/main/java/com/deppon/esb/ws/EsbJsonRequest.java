
package com.deppon.esb.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for esbJsonRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="esbJsonRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://client.common.esb.deppon.com/}esbRequest">
 *       &lt;sequence>
 *         &lt;element name="body" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requestClassName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "esbJsonRequest", propOrder = {
    "body",
    "requestClassName"
})
public class EsbJsonRequest
    extends EsbRequest
{

    protected String body;
    protected String requestClassName;

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBody(String value) {
        this.body = value;
    }

    /**
     * Gets the value of the requestClassName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestClassName() {
        return requestClassName;
    }

    /**
     * Sets the value of the requestClassName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestClassName(String value) {
        this.requestClassName = value;
    }

}
