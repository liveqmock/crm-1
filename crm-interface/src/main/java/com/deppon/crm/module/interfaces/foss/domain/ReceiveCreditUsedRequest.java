
package com.deppon.crm.module.interfaces.foss.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * �ͻ����ö����Ϣ
 * 
 * <p>ReceiveCreditUsedRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ReceiveCreditUsedRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hasUsedAmountList" type="{http://www.deppon.com/crm/inteface/foss/domain}HasUsedAmountInfo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReceiveCreditUsedRequest", propOrder = {
    "hasUsedAmountList"
})
public class ReceiveCreditUsedRequest {

    @XmlElement(required = true)
    protected List<HasUsedAmountInfo> hasUsedAmountList;

    /**
     * Gets the value of the hasUsedAmountList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hasUsedAmountList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHasUsedAmountList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HasUsedAmountInfo }
     * 
     * 
     */
    public List<HasUsedAmountInfo> getHasUsedAmountList() {
        if (hasUsedAmountList == null) {
            hasUsedAmountList = new ArrayList<HasUsedAmountInfo>();
        }
        return this.hasUsedAmountList;
    }

}
