
package com.deppon.ebm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for orderStatusReqInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="orderStatusReqInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ordernum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderstate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updatetime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderStatusReqInfo", propOrder = {
    "ordernum",
    "orderstate",
    "source",
    "updatetime"
})
public class OrderStatusReqInfo {

    protected String ordernum;
    protected String orderstate;
    protected String source;
    protected String updatetime;

    /**
     * Gets the value of the ordernum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrdernum() {
        return ordernum;
    }

    /**
     * Sets the value of the ordernum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrdernum(String value) {
        this.ordernum = value;
    }

    /**
     * Gets the value of the orderstate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderstate() {
        return orderstate;
    }

    /**
     * Sets the value of the orderstate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderstate(String value) {
        this.orderstate = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the updatetime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatetime() {
        return updatetime;
    }

    /**
     * Sets the value of the updatetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatetime(String value) {
        this.updatetime = value;
    }

}
