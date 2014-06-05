
package com.deppon.foss.waybill;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 运单费用信息
 * 
 * <p>WaybillCostInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WaybillCostInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="costType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="costName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="costMoney" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WaybillCostInfo", propOrder = {
    "costType",
    "costName",
    "costMoney"
})
public class WaybillCostInfo {

    @XmlElement(required = true)
    protected String costType;
    @XmlElement(required = true)
    protected String costName;
    @XmlElement(required = true)
    protected BigDecimal costMoney;

    /**
     * 获取costType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCostType() {
        return costType;
    }

    /**
     * 设置costType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCostType(String value) {
        this.costType = value;
    }

    /**
     * 获取costName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCostName() {
        return costName;
    }

    /**
     * 设置costName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCostName(String value) {
        this.costName = value;
    }

    /**
     * 获取costMoney属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCostMoney() {
        return costMoney;
    }

    /**
     * 设置costMoney属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCostMoney(BigDecimal value) {
        this.costMoney = value;
    }

}
