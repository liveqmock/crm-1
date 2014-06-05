
package com.deppon.fin.selfservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ��ɱ��˱���ӿڣ��������
 * 
 * <p>Generate_claimsapbillRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="Generate_claimsapbillRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="recompenseAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="accountNature" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bankAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bankCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bankBranchCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bankCityCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bankProvinceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mobilePhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="applyUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="costDetails" type="{http://www.deppon.com/fssc/remote/crm/domain}costDetailsType" maxOccurs="unbounded"/>
 *         &lt;element name="voucherNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receiverName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="workflowtype" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Generate_claimsapbillRequest", propOrder = {
    "recompenseAmount",
    "accountNature",
    "bankAccount",
    "bankCode",
    "bankBranchCode",
    "bankCityCode",
    "bankProvinceCode",
    "mobilePhone",
    "applyUser",
    "costDetails",
    "voucherNumber",
    "receiverName",
    "workflowtype"
})
public class GenerateClaimsapbillRequest {

    @XmlElement(required = true)
    protected BigDecimal recompenseAmount;
    @XmlElement(required = true)
    protected String accountNature;
    @XmlElement(required = true)
    protected String bankAccount;
    @XmlElement(required = true)
    protected String bankCode;
    @XmlElement(required = true)
    protected String bankBranchCode;
    @XmlElement(required = true)
    protected String bankCityCode;
    @XmlElement(required = true)
    protected String bankProvinceCode;
    @XmlElement(required = true)
    protected String mobilePhone;
    @XmlElement(required = true)
    protected String applyUser;
    @XmlElement(required = true)
    protected List<CostDetailsType> costDetails;
    @XmlElement(required = true)
    protected String voucherNumber;
    @XmlElement(required = true)
    protected String receiverName;
    @XmlElement(required = true)
    protected String workflowtype;

    /**
     * ��ȡrecompenseAmount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRecompenseAmount() {
        return recompenseAmount;
    }

    /**
     * ����recompenseAmount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRecompenseAmount(BigDecimal value) {
        this.recompenseAmount = value;
    }

    /**
     * ��ȡaccountNature���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNature() {
        return accountNature;
    }

    /**
     * ����accountNature���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNature(String value) {
        this.accountNature = value;
    }

    /**
     * ��ȡbankAccount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * ����bankAccount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAccount(String value) {
        this.bankAccount = value;
    }

    /**
     * ��ȡbankCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * ����bankCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCode(String value) {
        this.bankCode = value;
    }

    /**
     * ��ȡbankBranchCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankBranchCode() {
        return bankBranchCode;
    }

    /**
     * ����bankBranchCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankBranchCode(String value) {
        this.bankBranchCode = value;
    }

    /**
     * ��ȡbankCityCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCityCode() {
        return bankCityCode;
    }

    /**
     * ����bankCityCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCityCode(String value) {
        this.bankCityCode = value;
    }

    /**
     * ��ȡbankProvinceCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankProvinceCode() {
        return bankProvinceCode;
    }

    /**
     * ����bankProvinceCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankProvinceCode(String value) {
        this.bankProvinceCode = value;
    }

    /**
     * ��ȡmobilePhone���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * ����mobilePhone���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobilePhone(String value) {
        this.mobilePhone = value;
    }

    /**
     * ��ȡapplyUser���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplyUser() {
        return applyUser;
    }

    /**
     * ����applyUser���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplyUser(String value) {
        this.applyUser = value;
    }

    /**
     * Gets the value of the costDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the costDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCostDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CostDetailsType }
     * 
     * 
     */
    public List<CostDetailsType> getCostDetails() {
        if (costDetails == null) {
            costDetails = new ArrayList<CostDetailsType>();
        }
        return this.costDetails;
    }

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
     * ��ȡreceiverName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * ����receiverName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverName(String value) {
        this.receiverName = value;
    }

    /**
     * ��ȡworkflowtype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflowtype() {
        return workflowtype;
    }

    /**
     * ����workflowtype���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflowtype(String value) {
        this.workflowtype = value;
    }

}
