
package com.deppon.erp.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for depClaimsBill complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="depClaimsBill">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.amsonline.ws.deppon.com/}depArApBillBase">
 *       &lt;sequence>
 *         &lt;element name="approvalOpinion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="approveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="approver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="badAcctCliamsType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createtime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="handleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="srcType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "depClaimsBill", propOrder = {
    "approvalOpinion",
    "approveDate",
    "approver",
    "badAcctCliamsType",
    "createtime",
    "handleType",
    "srcType",
    "workNumber"
})
public class DepClaimsBill
    extends DepArApBillBase
{

    protected String approvalOpinion;
    protected String approveDate;
    protected String approver;
    protected String badAcctCliamsType;
    protected String createtime;
    protected String handleType;
    protected String srcType;
    protected String workNumber;

    /**
     * Gets the value of the approvalOpinion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    /**
     * Sets the value of the approvalOpinion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprovalOpinion(String value) {
        this.approvalOpinion = value;
    }

    /**
     * Gets the value of the approveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApproveDate() {
        return approveDate;
    }

    /**
     * Sets the value of the approveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApproveDate(String value) {
        this.approveDate = value;
    }

    /**
     * Gets the value of the approver property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprover() {
        return approver;
    }

    /**
     * Sets the value of the approver property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprover(String value) {
        this.approver = value;
    }

    /**
     * Gets the value of the badAcctCliamsType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBadAcctCliamsType() {
        return badAcctCliamsType;
    }

    /**
     * Sets the value of the badAcctCliamsType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBadAcctCliamsType(String value) {
        this.badAcctCliamsType = value;
    }

    /**
     * Gets the value of the createtime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * Sets the value of the createtime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatetime(String value) {
        this.createtime = value;
    }

    /**
     * Gets the value of the handleType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandleType() {
        return handleType;
    }

    /**
     * Sets the value of the handleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandleType(String value) {
        this.handleType = value;
    }

    /**
     * Gets the value of the srcType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcType() {
        return srcType;
    }

    /**
     * Sets the value of the srcType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcType(String value) {
        this.srcType = value;
    }

    /**
     * Gets the value of the workNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkNumber() {
        return workNumber;
    }

    /**
     * Sets the value of the workNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkNumber(String value) {
        this.workNumber = value;
    }

}
