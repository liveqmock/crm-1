
package com.deppon.crm.module.interfaces.customer.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;



/**
 * <p>QueryClaimInfoResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="QueryClaimInfoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clamInfos" type="{http://www.deppon.com/bho/remote/crm/domain/entity}ClaimInfo" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "QueryClaimInfoResponse", propOrder = {
    "clamInfos",
    "totalCount"
})
public class QueryClaimInfoResponse {

    protected List<ClaimInfo> clamInfos;
    protected int totalCount;

    /**
     * Gets the value of the clamInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clamInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClamInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClaimInfo }
     * 
     * 
     */
    public List<ClaimInfo> getClamInfos() {
        if (clamInfos == null) {
            clamInfos = new ArrayList<ClaimInfo>();
        }
        return this.clamInfos;
    }

    /**
     * 获取totalCount属性的值。
     * 
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 设置totalCount属性的值。
     * 
     */
    public void setTotalCount(int value) {
        this.totalCount = value;
    }

}
