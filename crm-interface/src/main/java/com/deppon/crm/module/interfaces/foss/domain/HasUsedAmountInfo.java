
package com.deppon.crm.module.interfaces.foss.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ��ʹ�ö����Ϣ
 * 
 * <p>HasUsedAmountInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="HasUsedAmountInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="custNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hasUsedAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="earliestDebtDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HasUsedAmountInfo", propOrder = {
    "custNumber",
    "custName",
    "hasUsedAmount",
    "earliestDebtDate"
})
public class HasUsedAmountInfo {

    @XmlElement(required = true)
    protected String custNumber;
    @XmlElement(required = true)
    protected String custName;
    @XmlElement(required = true)
    protected BigDecimal hasUsedAmount;
    @XmlElement(required = true)
    protected Date earliestDebtDate;

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
     * ��ȡhasUsedAmount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHasUsedAmount() {
        return hasUsedAmount;
    }

    /**
     * ����hasUsedAmount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHasUsedAmount(BigDecimal value) {
        this.hasUsedAmount = value;
    }

	public Date getEarliestDebtDate() {
		return earliestDebtDate;
	}

	public void setEarliestDebtDate(Date earliestDebtDate) {
		this.earliestDebtDate = earliestDebtDate;
	}


}
