
package com.deppon.foss.crm;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 责任信息：费用承担部门及金额描述
 * 
 * <p>ResponsibilityInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ResponsibilityInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="responsibilityDeptCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="responsibilityMoney" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponsibilityInfo", propOrder = {
    "responsibilityDeptCode",
    "responsibilityMoney"
})
public class ResponsibilityInfo {

    @XmlElement(required = true)
    protected String responsibilityDeptCode;
    @XmlElement(required = true)
    protected BigDecimal responsibilityMoney;

    /**
     * 获取responsibilityDeptCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsibilityDeptCode() {
        return responsibilityDeptCode;
    }

    /**
     * 设置responsibilityDeptCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsibilityDeptCode(String value) {
        this.responsibilityDeptCode = value;
    }

    /**
     * 获取responsibilityMoney属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getResponsibilityMoney() {
        return responsibilityMoney;
    }

    /**
     * 设置responsibilityMoney属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setResponsibilityMoney(BigDecimal value) {
        this.responsibilityMoney = value;
    }

}
