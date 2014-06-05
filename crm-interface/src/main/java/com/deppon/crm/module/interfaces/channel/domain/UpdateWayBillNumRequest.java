
package com.deppon.crm.module.interfaces.channel.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>UpdateWayBillNumRequest complex type
 * <p>
 * 
 * <pre>
 * &lt;complexType name="UpdateWayBillNumRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="channelNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="waybilNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateWayBillNumRequest", namespace = "http://www.deppon.com/esb/inteface/domain/crm", propOrder = {
    "channelNumber",
    "waybilNumber"
})
public class UpdateWayBillNumRequest {

    @XmlElement(required = true)
    protected String channelNumber;
    @XmlElement(required = true)
    protected String waybilNumber;

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelNumber() {
        return channelNumber;
    }

    /**
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelNumber(String value) {
        this.channelNumber = value;
    }

    /**
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaybilNumber() {
        return waybilNumber;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaybilNumber(String value) {
        this.waybilNumber = value;
    }

}
