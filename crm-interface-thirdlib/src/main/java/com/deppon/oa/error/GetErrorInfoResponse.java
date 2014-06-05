
package com.deppon.oa.error;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="out1" type="{http://www.primeton.com/ErrorService}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "out1"
})
@XmlRootElement(name = "getErrorInfoResponse")
public class GetErrorInfoResponse {

    @XmlElementRef(name = "out1", namespace = "http://www.primeton.com/ErrorService", type = JAXBElement.class)
    protected JAXBElement<ArrayOfString> out1;

    /**
     * Gets the value of the out1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}
     *     
     */
    public JAXBElement<ArrayOfString> getOut1() {
        return out1;
    }

    /**
     * Sets the value of the out1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}
     *     
     */
    public void setOut1(JAXBElement<ArrayOfString> value) {
        this.out1 = ((JAXBElement<ArrayOfString> ) value);
    }

}
