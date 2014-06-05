
package com.deppon.oa.workflow;

import java.util.Date;
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
 *         &lt;element name="in0" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="in1" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="in2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "in0",
    "in1",
    "in2"
})
@XmlRootElement(name = "getWorkFlowApproveState")
public class GetWorkFlowApproveState {

    @XmlElementRef(name = "in0", namespace = "http://www.primeton.com/QueryWorkFlowStateService", type = JAXBElement.class)
    protected JAXBElement<Date> in0;
    @XmlElementRef(name = "in1", namespace = "http://www.primeton.com/QueryWorkFlowStateService", type = JAXBElement.class)
    protected JAXBElement<Date> in1;
    @XmlElementRef(name = "in2", namespace = "http://www.primeton.com/QueryWorkFlowStateService", type = JAXBElement.class)
    protected JAXBElement<String> in2;

    /**
     * Gets the value of the in0 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Date }{@code >}
     *     
     */
    public JAXBElement<Date> getIn0() {
        return in0;
    }

    /**
     * Sets the value of the in0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Date }{@code >}
     *     
     */
    public void setIn0(JAXBElement<Date> value) {
        this.in0 = ((JAXBElement<Date> ) value);
    }

    /**
     * Gets the value of the in1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Date }{@code >}
     *     
     */
    public JAXBElement<Date> getIn1() {
        return in1;
    }

    /**
     * Sets the value of the in1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Date }{@code >}
     *     
     */
    public void setIn1(JAXBElement<Date> value) {
        this.in1 = ((JAXBElement<Date> ) value);
    }

    /**
     * Gets the value of the in2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIn2() {
        return in2;
    }

    /**
     * Sets the value of the in2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIn2(JAXBElement<String> value) {
        this.in2 = ((JAXBElement<String> ) value);
    }

}
