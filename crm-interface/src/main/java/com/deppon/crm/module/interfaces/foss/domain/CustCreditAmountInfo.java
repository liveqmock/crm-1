
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
 * �ͻ������ܶ����Ϣ
 * 
 * <p>CustCreditAmountInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="CustCreditAmountInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="custNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="newTotalCreditAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="bizDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustCreditAmountInfo", propOrder = {
    "custNumber",
    "custName",
    "newTotalCreditAmount",
    "bizDate"
})
public class CustCreditAmountInfo {

    @XmlElement(required = true)
    protected String custNumber;
    @XmlElement(required = true)
    protected String custName;
    @XmlElement(required = true)
    protected BigDecimal newTotalCreditAmount;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date bizDate;

    /**
     * ��ȡcustNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustNumber() {
        return custNumber;
    }

    /**
     * ����custNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustNumber(String value) {
        this.custNumber = value;
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
     * ��ȡnewTotalCreditAmount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNewTotalCreditAmount() {
        return newTotalCreditAmount;
    }

    /**
     * ����newTotalCreditAmount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNewTotalCreditAmount(BigDecimal value) {
        this.newTotalCreditAmount = value;
    }

    /**
     * ��ȡbizDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getBizDate() {
        return bizDate;
    }

    /**
     * ����bizDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBizDate(Date value) {
        this.bizDate = value;
    }

}
