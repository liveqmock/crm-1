
package com.deppon.crm.module.interfaces.foss.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * ��ѯ���ⵥ�ӿڷ��ز���
 * 
 * <p>Query_claimbillResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="Query_claimbillResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="custNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="waybillNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="riskType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="riskTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="reportCaseTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="claimSum" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="actualPaySum" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="receivingDeptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="riskInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dealingPeople" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dealingTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="finalApproval" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="finalApprovalOpinion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="indeptCharge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="inCompanyCharge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="otherCharge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Query_claimbillResponse", propOrder = {
    "custNum",
    "custName",
    "waybillNum",
    "riskType",
    "riskTime",
    "reportCaseTime",
    "claimSum",
    "actualPaySum",
    "receivingDeptName",
    "riskInfo",
    "status",
    "dealingPeople",
    "dealingTime",
    "finalApproval",
    "finalApprovalOpinion",
    "indeptCharge",
    "inCompanyCharge",
    "otherCharge"
})
public class QueryClaimbillResponse {

    protected String custNum;
    protected String custName;
    @XmlElement(required = true)
    protected String waybillNum;
    protected String riskType;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date riskTime;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date reportCaseTime;
    protected BigDecimal claimSum;
    protected BigDecimal actualPaySum;
    protected String receivingDeptName;
    protected String riskInfo;
    protected String status;
    protected String dealingPeople;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date dealingTime;
    protected String finalApproval;
    protected String finalApprovalOpinion;
    protected String indeptCharge;
    protected BigDecimal inCompanyCharge;
    protected BigDecimal otherCharge;

    /**
     * ��ȡcustNum���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustNum() {
        return custNum;
    }

    /**
     * ����custNum���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustNum(String value) {
        this.custNum = value;
    }

    /**
     * ��ȡcustName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustName() {
        return custName;
    }

    /**
     * ����custName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustName(String value) {
        this.custName = value;
    }

    /**
     * ��ȡwaybillNum���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaybillNum() {
        return waybillNum;
    }

    /**
     * ����waybillNum���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaybillNum(String value) {
        this.waybillNum = value;
    }

    /**
     * ��ȡriskType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiskType() {
        return riskType;
    }

    /**
     * ����riskType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiskType(String value) {
        this.riskType = value;
    }

    /**
     * ��ȡriskTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRiskTime() {
        return riskTime;
    }

    /**
     * ����riskTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiskTime(Date value) {
        this.riskTime = value;
    }

    /**
     * ��ȡreportCaseTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getReportCaseTime() {
        return reportCaseTime;
    }

    /**
     * ����reportCaseTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportCaseTime(Date value) {
        this.reportCaseTime = value;
    }

    /**
     * ��ȡclaimSum���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getClaimSum() {
        return claimSum;
    }

    /**
     * ����claimSum���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setClaimSum(BigDecimal value) {
        this.claimSum = value;
    }

    /**
     * ��ȡactualPaySum���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getActualPaySum() {
        return actualPaySum;
    }

    /**
     * ����actualPaySum���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setActualPaySum(BigDecimal value) {
        this.actualPaySum = value;
    }

    /**
     * ��ȡreceivingDeptName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivingDeptName() {
        return receivingDeptName;
    }

    /**
     * ����receivingDeptName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivingDeptName(String value) {
        this.receivingDeptName = value;
    }

    /**
     * ��ȡriskInfo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiskInfo() {
        return riskInfo;
    }

    /**
     * ����riskInfo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiskInfo(String value) {
        this.riskInfo = value;
    }

    /**
     * ��ȡstatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * ����status���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * ��ȡdealingPeople���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDealingPeople() {
        return dealingPeople;
    }

    /**
     * ����dealingPeople���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDealingPeople(String value) {
        this.dealingPeople = value;
    }

    /**
     * ��ȡdealingTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDealingTime() {
        return dealingTime;
    }

    /**
     * ����dealingTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDealingTime(Date value) {
        this.dealingTime = value;
    }

    /**
     * ��ȡfinalApproval���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinalApproval() {
        return finalApproval;
    }

    /**
     * ����finalApproval���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinalApproval(String value) {
        this.finalApproval = value;
    }

    /**
     * ��ȡfinalApprovalOpinion���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinalApprovalOpinion() {
        return finalApprovalOpinion;
    }

    /**
     * ����finalApprovalOpinion���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinalApprovalOpinion(String value) {
        this.finalApprovalOpinion = value;
    }

    /**
     * ��ȡindeptCharge���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public String getIndeptCharge() {
        return indeptCharge;
    }

    /**
     * ����indeptCharge���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIndeptCharge(String value) {
        this.indeptCharge = value;
    }

    /**
     * ��ȡinCompanyCharge���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInCompanyCharge() {
        return inCompanyCharge;
    }

    /**
     * ����inCompanyCharge���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInCompanyCharge(BigDecimal value) {
        this.inCompanyCharge = value;
    }

    /**
     * ��ȡotherCharge���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOtherCharge() {
        return otherCharge;
    }

    /**
     * ����otherCharge���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOtherCharge(BigDecimal value) {
        this.otherCharge = value;
    }

}
