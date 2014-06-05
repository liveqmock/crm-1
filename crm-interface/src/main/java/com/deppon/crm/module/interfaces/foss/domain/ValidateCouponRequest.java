
package com.deppon.crm.module.interfaces.foss.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * �˵������ϸ
 * 
 * <p>ValidateCouponRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ValidateCouponRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="couponNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isUsed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="waybillInfo" type="{http://www.deppon.com/crm/inteface/foss/domain}CouponWaybillInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidateCouponRequest", propOrder = {
    "couponNumber",
    "isUsed",
    "waybillInfo"
})
public class ValidateCouponRequest {

    @XmlElement(required = true)
    protected String couponNumber;
    protected boolean isUsed;
    @XmlElement(required = true)
    protected CouponWaybillInfo waybillInfo;

    /**
     * ��ȡcouponNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCouponNumber() {
        return couponNumber;
    }

    /**
     * ����couponNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCouponNumber(String value) {
        this.couponNumber = value;
    }

    /**
     * ��ȡisUsed���Ե�ֵ��
     * 
     */
    public boolean isIsUsed() {
        return isUsed;
    }

    /**
     * ����isUsed���Ե�ֵ��
     * 
     */
    public void setIsUsed(boolean value) {
        this.isUsed = value;
    }

    /**
     * ��ȡwaybillInfo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link CouponWaybillInfo }
     *     
     */
    public CouponWaybillInfo getWaybillInfo() {
        return waybillInfo;
    }

    /**
     * ����waybillInfo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link CouponWaybillInfo }
     *     
     */
    public void setWaybillInfo(CouponWaybillInfo value) {
        this.waybillInfo = value;
    }

}
