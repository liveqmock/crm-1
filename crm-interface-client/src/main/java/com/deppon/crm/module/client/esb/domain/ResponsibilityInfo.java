
package com.deppon.crm.module.client.esb.domain;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * ������Ϣ�����óе����ż��������
 * 
 * <p>ResponsibilityInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
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
/**
 * @作者：罗典
 * @描述：责任信息
 * @时间：2012-11-22
 * */
@XStreamAlias("responsibilityInfos")
public class ResponsibilityInfo {

	// 责任部门标杆编码(这笔费用的承担部门)
    @XmlElement(required = true)
    protected String responsibilityDeptCode;
    // 承担金额
    @XmlElement(required = true)
    protected BigDecimal responsibilityMoney;

    /**
     * ��ȡresponsibilityDeptCode���Ե�ֵ��
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
     * ����responsibilityDeptCode���Ե�ֵ��
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
     * ��ȡresponsibilityMoney���Ե�ֵ��
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
     * ����responsibilityMoney���Ե�ֵ��
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
