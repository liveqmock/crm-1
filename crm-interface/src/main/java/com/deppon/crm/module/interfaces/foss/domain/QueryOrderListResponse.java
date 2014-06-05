
package com.deppon.crm.module.interfaces.foss.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * ��ѯ�����б�
 * 
 * <p>queryOrderListResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="queryOrderListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderLists" type="{http://www.deppon.com/crm/inteface/foss/domain}OrderInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="totalCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryOrderListResponse", propOrder = {
    "orderLists",
    "totalCount"
})
public class QueryOrderListResponse {

    protected List<OrderInfo> orderLists;
    protected int totalCount;

    /**
     * Gets the value of the orderLists property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderLists property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderLists().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderInfo }
     * 
     * 
     */
    public List<OrderInfo> getOrderLists() {
        if (orderLists == null) {
            orderLists = new ArrayList<OrderInfo>();
        }
        return this.orderLists;
    }

    /**
     * ��ȡtotalCount���Ե�ֵ��
     * 
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * ����totalCount���Ե�ֵ��
     * 
     */
    public void setTotalCount(int value) {
        this.totalCount = value;
    }

}
