
package com.deppon.crm.module.interfaces.foss.domain;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ValidateCouponResponse complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="ValidateCouponResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isCanUse" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="couponAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="canNotUseReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidateCouponResponse", propOrder = {
    "isCanUse",
    "couponAmount",
    "canNotUseReason",
    "deductibleType"
})
public class ValidateCouponResponse {

    protected boolean isCanUse;
    @XmlElement(required = true)
    protected BigDecimal couponAmount;
    protected String canNotUseReason;
    protected String deductibleType;

    /**
     * ��ȡisCanUse���Ե�ֵ��
     *
     */
    public boolean isIsCanUse() {
        return isCanUse;
    }

    /**
     * ����isCanUse���Ե�ֵ��
     *
     */
    public void setIsCanUse(boolean value) {
        this.isCanUse = value;
    }

    /**
     * ��ȡcouponAmount���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    /**
     * ����couponAmount���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setCouponAmount(BigDecimal value) {
        this.couponAmount = value;
    }

    /**
     * ��ȡcanNotUseReason���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCanNotUseReason() {
        return canNotUseReason;
    }

    /**
     * ����canNotUseReason���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCanNotUseReason(String value) {
        this.canNotUseReason = value;
    }

	public String getDeductibleType() {
		return deductibleType;
	}

	public void setDeductibleType(String deductibleType) {
		this.deductibleType = deductibleType;
	}
}
