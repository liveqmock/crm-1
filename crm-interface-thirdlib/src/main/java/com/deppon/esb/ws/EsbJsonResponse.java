
package com.deppon.esb.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for esbJsonResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="esbJsonResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://client.common.esb.deppon.com/}esbResponse">
 *       &lt;sequence>
 *         &lt;element ref="{http://common.esb.deppon.com}response" minOccurs="0"/>
 *         &lt;element ref="{http://common.esb.deppon.com}responseClassName" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "esbJsonResponse", propOrder = {
    "response",
    "responseClassName"
})
public class EsbJsonResponse
    extends EsbResponse
{

    @XmlElement(namespace = "http://common.esb.deppon.com")
    protected String response;
    @XmlElement(namespace = "http://common.esb.deppon.com")
    protected String responseClassName;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponse(String value) {
        this.response = value;
    }

    /**
     * Gets the value of the responseClassName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseClassName() {
        return responseClassName;
    }

    /**
     * Sets the value of the responseClassName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseClassName(String value) {
        this.responseClassName = value;
    }

}
