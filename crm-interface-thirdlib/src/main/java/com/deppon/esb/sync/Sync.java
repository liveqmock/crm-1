
package com.deppon.esb.sync;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sync complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sync">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="jsonRequest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sync", propOrder = {
    "jsonRequest"
})
public class Sync {

    protected String jsonRequest;

    /**
     * Gets the value of the jsonRequest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJsonRequest() {
        return jsonRequest;
    }

    /**
     * Sets the value of the jsonRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJsonRequest(String value) {
        this.jsonRequest = value;
    }

}
