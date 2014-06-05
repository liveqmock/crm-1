
package com.deppon.fin.selfservice;

import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * ������ϸ
 * 
 * <p>costDetailsType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="costDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="costDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="reimbursementMoneyDetail" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="billNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="responsibilityDeptInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recompenseType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "costDetailsType", propOrder = {
    "costDate",
    "reimbursementMoneyDetail",
    "billNum",
    "responsibilityDeptInfo",
    "recompenseType"
})
public class CostDetailsType {

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date costDate;
    @XmlElement(required = true)
    protected BigDecimal reimbursementMoneyDetail;
    @XmlElement(required = true)
    protected String billNum;
    @XmlElement(required = true)
    protected String responsibilityDeptInfo;
    @XmlElement(required = true)
    protected String recompenseType;

    /**
     * ��ȡcostDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getCostDate() {
        return costDate;
    }

    /**
     * ����costDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCostDate(Date value) {
        this.costDate = value;
    }

    /**
     * ��ȡreimbursementMoneyDetail���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getReimbursementMoneyDetail() {
        return reimbursementMoneyDetail;
    }

    /**
     * ����reimbursementMoneyDetail���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setReimbursementMoneyDetail(BigDecimal value) {
        this.reimbursementMoneyDetail = value;
    }

    /**
     * ��ȡbillNum���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillNum() {
        return billNum;
    }

    /**
     * ����billNum���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillNum(String value) {
        this.billNum = value;
    }

    /**
     * ��ȡresponsibilityDeptInfo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsibilityDeptInfo() {
        return responsibilityDeptInfo;
    }

    /**
     * ����responsibilityDeptInfo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsibilityDeptInfo(String value) {
        this.responsibilityDeptInfo = value;
    }

    /**
     * ��ȡrecompenseType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecompenseType() {
        return recompenseType;
    }

    /**
     * ����recompenseType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecompenseType(String value) {
        this.recompenseType = value;
    }

}
