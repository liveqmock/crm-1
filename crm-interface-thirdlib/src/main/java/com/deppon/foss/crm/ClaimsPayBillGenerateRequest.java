
package com.deppon.foss.crm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 在CRM中申请理赔工作流，当工作流审批通过后，需要在FOSS中生成理赔应付单
 * 
 * <p>ClaimsPayBillGenerateRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ClaimsPayBillGenerateRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="claimType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="claimWay" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="businessType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deptNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="claimMoney" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="billNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creatorNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="responsibilityInfos" type="{http://www.deppon.com/esb/inteface/domain/crm}ResponsibilityInfo" maxOccurs="unbounded"/>
 *         &lt;element name="bankPayInfo" type="{http://www.deppon.com/esb/inteface/domain/crm}BankPayInfo" minOccurs="0"/>
 *         &lt;element name="payBillLastTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="paymentCategory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClaimsPayBillGenerateRequest", propOrder = {
    "claimType",
    "claimWay",
    "businessType",
    "deptNo",
    "custNo",
    "claimMoney",
    "billNo",
    "creatorNo",
    "responsibilityInfos",
    "bankPayInfo",
    "payBillLastTime",
    "paymentCategory"
})
public class ClaimsPayBillGenerateRequest {

    @XmlElement(required = true)
    protected String claimType;
    @XmlElement(required = true)
    protected String claimWay;
    @XmlElement(required = true)
    protected String businessType;
    @XmlElement(required = true)
    protected String deptNo;
    @XmlElement(required = true)
    protected String custNo;
    @XmlElement(required = true)
    protected BigDecimal claimMoney;
    @XmlElement(required = true)
    protected String billNo;
    @XmlElement(required = true)
    protected String creatorNo;
    @XmlElement(required = true)
    protected List<ResponsibilityInfo> responsibilityInfos;
    protected BankPayInfo bankPayInfo;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date payBillLastTime;
    @XmlElement(required = true)
    protected String paymentCategory;

    /**
     * 获取claimType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimType() {
        return claimType;
    }

    /**
     * 设置claimType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimType(String value) {
        this.claimType = value;
    }

    /**
     * 获取claimWay属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimWay() {
        return claimWay;
    }

    /**
     * 设置claimWay属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimWay(String value) {
        this.claimWay = value;
    }

    /**
     * 获取businessType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 设置businessType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessType(String value) {
        this.businessType = value;
    }

    /**
     * 获取deptNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptNo() {
        return deptNo;
    }

    /**
     * 设置deptNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptNo(String value) {
        this.deptNo = value;
    }

    /**
     * 获取custNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * 设置custNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustNo(String value) {
        this.custNo = value;
    }

    /**
     * 获取claimMoney属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getClaimMoney() {
        return claimMoney;
    }

    /**
     * 设置claimMoney属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setClaimMoney(BigDecimal value) {
        this.claimMoney = value;
    }

    /**
     * 获取billNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillNo() {
        return billNo;
    }

    /**
     * 设置billNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillNo(String value) {
        this.billNo = value;
    }

    /**
     * 获取creatorNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatorNo() {
        return creatorNo;
    }

    /**
     * 设置creatorNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatorNo(String value) {
        this.creatorNo = value;
    }

    /**
     * Gets the value of the responsibilityInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the responsibilityInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResponsibilityInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResponsibilityInfo }
     * 
     * 
     */
    public List<ResponsibilityInfo> getResponsibilityInfos() {
        if (responsibilityInfos == null) {
            responsibilityInfos = new ArrayList<ResponsibilityInfo>();
        }
        return this.responsibilityInfos;
    }

    /**
     * 获取bankPayInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BankPayInfo }
     *     
     */
    public BankPayInfo getBankPayInfo() {
        return bankPayInfo;
    }

    /**
     * 设置bankPayInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BankPayInfo }
     *     
     */
    public void setBankPayInfo(BankPayInfo value) {
        this.bankPayInfo = value;
    }

    /**
     * 获取payBillLastTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPayBillLastTime() {
        return payBillLastTime;
    }

    /**
     * 设置payBillLastTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayBillLastTime(Date value) {
        this.payBillLastTime = value;
    }

    /**
     * 获取paymentCategory属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentCategory() {
        return paymentCategory;
    }

    /**
     * 设置paymentCategory属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentCategory(String value) {
        this.paymentCategory = value;
    }

}
