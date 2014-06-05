
package com.deppon.crm.module.interfaces.foss.domain;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * �˵������ϸ
 * 
 * <p>AmountInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="AmountInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="valuationCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valuationAmonut" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AmountInfo", propOrder = {
    "valuationCode",
    "valuationAmonut"
})
public class AmountInfo {

    @XmlElement(required = true)
    protected String valuationCode;
    @XmlElement(required = true)
    protected BigDecimal valuationAmonut;

    /**
     * ��ȡvaluationCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValuationCode() {
        return valuationCode;
    }

    /**
     * ����valuationCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValuationCode(String value) {
        this.valuationCode = value;
    }

    /**
     * ��ȡvaluationAmonut���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValuationAmonut() {
        return valuationAmonut;
    }

    /**
     * ����valuationAmonut���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValuationAmonut(BigDecimal value) {
        this.valuationAmonut = value;
    }

}
