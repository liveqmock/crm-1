
package com.deppon.crm.module.interfaces.uums.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SendCompanyInfoResponse complex type
 * 
 * <p>
 * 
 * <pre>
 * &lt;complexType name="SendCompanyInfoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="successCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="failedCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="processResult" type="{http://www.deppon.com/crm/inteface/domain/companymanagement}SendCompanyInfoProcessReult" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SendCompanyInfoResponse", namespace = "http://www.deppon.com/crm/inteface/domain/companymanagement", propOrder = {
    "successCount",
    "failedCount",
    "processResult"
})
public class SendCompanyInfoResponse {

    protected int successCount;
    protected int failedCount;
    @XmlElement(required = true)
    protected List<SendCompanyInfoProcessReult> processResult;

    /**
     */
    public int getSuccessCount() {
        return successCount;
    }

    /**
     */
    public void setSuccessCount(int value) {
        this.successCount = value;
    }

    /**
     */
    public int getFailedCount() {
        return failedCount;
    }

    /**
     */
    public void setFailedCount(int value) {
        this.failedCount = value;
    }

    /**
     * Gets the value of the processResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the processResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SendCompanyInfoProcessReult }
     * 
     * 
     */
    public List<SendCompanyInfoProcessReult> getProcessResult() {
        if (processResult == null) {
            processResult = new ArrayList<SendCompanyInfoProcessReult>();
        }
        return this.processResult;
    }

}
