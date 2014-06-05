
package com.deppon.ebm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for orderStatusTraceInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="orderStatusTraceInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.shared.aliOrder.module.alibaba.deppon.com/}resultInfo">
 *       &lt;sequence>
 *         &lt;element name="orders" type="{http://ws.shared.aliOrder.module.alibaba.deppon.com/}orderStatusReqInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderStatusTraceInfo", propOrder = {
    "orders"
})
public class OrderStatusTraceInfo
    extends ResultInfo
{

    @XmlElement(nillable = true)
    protected List<OrderStatusReqInfo> orders;

    /**
     * Gets the value of the orders property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orders property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrders().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderStatusReqInfo }
     * 
     * 
     */
    public List<OrderStatusReqInfo> getOrders() {
        if (orders == null) {
            orders = new ArrayList<OrderStatusReqInfo>();
        }
        return this.orders;
    }

}
