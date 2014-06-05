
package com.deppon.crm.module.interfaces.cc.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MarketingInfoQueryResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="MarketingInfoQueryResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="marketInfos" type="{http://www.deppon.com/crm/inteface/cc/domain}MarketingInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MarketingInfoQueryResponse", propOrder = {
    "marketInfos",
    "count"
})
public class MarketingInfoQueryResponse {

    protected List<MarketingInfo> marketInfos;
    protected int count;

    /**
     * Gets the value of the marketInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the marketInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarketInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MarketingInfo }
     * 
     * 
     */
    public List<MarketingInfo> getMarketInfos() {
        if (marketInfos == null) {
            marketInfos = new ArrayList<MarketingInfo>();
        }
        return this.marketInfos;
    }

    /**
     * ��ȡcount���Ե�ֵ��
     * 
     */
    public int getCount() {
        return count;
    }

    /**
     * ����count���Ե�ֵ��
     * 
     */
    public void setCount(int value) {
        this.count = value;
    }

}
