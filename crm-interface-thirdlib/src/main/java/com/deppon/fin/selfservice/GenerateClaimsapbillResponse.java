
package com.deppon.fin.selfservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ��ɱ��˱���ӿڣ����ز���
 * 
 * <p>Generate_claimsapbillResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="Generate_claimsapbillResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="voucherNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="workflowType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="workflowNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isSuccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="failedReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Generate_claimsapbillResponse", propOrder = {
    "voucherNumber",
    "workflowType",
    "workflowNumber",
    "isSuccess",
    "failedReason"
})
public class GenerateClaimsapbillResponse {

    @XmlElement(required = true)
    protected String voucherNumber;
    @XmlElement(required = true)
    protected String workflowType;
    @XmlElement(required = true)
    protected String workflowNumber;
    protected boolean isSuccess;
    protected String failedReason;

    /**
     * ��ȡvoucherNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoucherNumber() {
        return voucherNumber;
    }

    /**
     * ����voucherNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoucherNumber(String value) {
        this.voucherNumber = value;
    }

    /**
     * ��ȡworkflowType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflowType() {
        return workflowType;
    }

    /**
     * ����workflowType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflowType(String value) {
        this.workflowType = value;
    }

    /**
     * ��ȡworkflowNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflowNumber() {
        return workflowNumber;
    }

    /**
     * ����workflowNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflowNumber(String value) {
        this.workflowNumber = value;
    }

    /**
     * ��ȡisSuccess���Ե�ֵ��
     * 
     */
    public boolean isIsSuccess() {
        return isSuccess;
    }

    /**
     * ����isSuccess���Ե�ֵ��
     * 
     */
    public void setIsSuccess(boolean value) {
        this.isSuccess = value;
    }

    /**
     * ��ȡfailedReason���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFailedReason() {
        return failedReason;
    }

    /**
     * ����failedReason���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFailedReason(String value) {
        this.failedReason = value;
    }

}
