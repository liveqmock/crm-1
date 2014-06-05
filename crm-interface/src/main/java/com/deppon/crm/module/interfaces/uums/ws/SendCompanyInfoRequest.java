
package com.deppon.crm.module.interfaces.uums.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>SendCompanyInfoRequest complex type�� Java �ࡣ
 * <pre>
 * &lt;complexType name="SendCompanyInfoRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="companyInfo" type="{http://www.deppon.com/crm/inteface/domain/companymanagement}CompanyInfo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SendCompanyInfoRequest", namespace = "http://www.deppon.com/crm/inteface/domain/companymanagement", propOrder = {
    "companyInfo"
})
public class SendCompanyInfoRequest {

    @XmlElement(required = true)
    protected List<CompanyInfo> companyInfo;

    /**
     * Gets the value of the companyInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the companyInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompanyInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CompanyInfo }
     * 
     * 
     */
    public List<CompanyInfo> getCompanyInfo() {
        if (companyInfo == null) {
            companyInfo = new ArrayList<CompanyInfo>();
        }
        return this.companyInfo;
    }

}
