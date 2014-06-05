
package com.deppon.crm.module.interfaces.cc.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * �ͻ�����
 * 
 * <p>CustomerNeed complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="CustomerNeed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="needsType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="needsAndQuestion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="solution" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerNeed", propOrder = {
    "needsType",
    "needsAndQuestion",
    "solution"
})
public class CustomerNeed {

    @XmlElement(required = true)
    protected String needsType;
    @XmlElement(required = true)
    protected String needsAndQuestion;
    @XmlElement(required = true)
    protected String solution;

    /**
     * ��ȡneedsType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNeedsType() {
        return needsType;
    }

    /**
     * ����needsType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNeedsType(String value) {
        this.needsType = value;
    }

    /**
     * ��ȡneedsAndQuestion���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNeedsAndQuestion() {
        return needsAndQuestion;
    }

    /**
     * ����needsAndQuestion���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNeedsAndQuestion(String value) {
        this.needsAndQuestion = value;
    }

    /**
     * ��ȡsolution���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolution() {
        return solution;
    }

    /**
     * ����solution���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolution(String value) {
        this.solution = value;
    }

}
