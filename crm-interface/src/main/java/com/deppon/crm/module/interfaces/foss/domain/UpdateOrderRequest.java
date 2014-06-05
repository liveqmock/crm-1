
package com.deppon.crm.module.interfaces.foss.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ˾���ֻ�
 * 
 * <p>updateOrderRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="updateOrderRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="waybillNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oprateUserNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oprateDeptCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="driverName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="driverMobile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="goodsStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="backInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateOrderRequest", propOrder = {
    "orderNumber",
    "waybillNumber",
    "oprateUserNum",
    "oprateDeptCode",
    "driverName",
    "driverMobile",
    "goodsStatus",
    "backInfo",
    "earningDeptStandardCode",
    "earningDeptStandardName"
})
@XmlRootElement(name="updateOrderRequest",namespace="http://www.deppon.com/crm/inteface/foss/domain")
public class UpdateOrderRequest {

    @XmlElement(required = true)
    protected String orderNumber;
    @XmlElement(required = true)
    protected String waybillNumber;
    @XmlElement(required = true)
    protected String oprateUserNum;
    @XmlElement(required = true)
    protected String oprateDeptCode;
    @XmlElement(required = true)
    protected String driverName;
    @XmlElement(required = true)
    protected String driverMobile;
    @XmlElement(required = true)
    protected String goodsStatus;
    @XmlElement(required = true)
    protected String backInfo;
    protected String earningDeptStandardCode;
    protected String earningDeptStandardName;

    /**
     * ��ȡorderNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * ����orderNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderNumber(String value) {
        this.orderNumber = value;
    }

    /**
     * ��ȡwaybillNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaybillNumber() {
        return waybillNumber;
    }

    /**
     * ����waybillNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaybillNumber(String value) {
        this.waybillNumber = value;
    }

    /**
     * ��ȡoprateUserNum���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOprateUserNum() {
        return oprateUserNum;
    }

    /**
     * ����oprateUserNum���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOprateUserNum(String value) {
        this.oprateUserNum = value;
    }

    /**
     * ��ȡoprateDeptCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOprateDeptCode() {
        return oprateDeptCode;
    }

    /**
     * ����oprateDeptCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOprateDeptCode(String value) {
        this.oprateDeptCode = value;
    }

    /**
     * ��ȡdriverName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * ����driverName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverName(String value) {
        this.driverName = value;
    }

    /**
     * ��ȡdriverMobile���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverMobile() {
        return driverMobile;
    }

    /**
     * ����driverMobile���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverMobile(String value) {
        this.driverMobile = value;
    }

    /**
     * ��ȡgoodsStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsStatus() {
        return goodsStatus;
    }

    /**
     * ����goodsStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsStatus(String value) {
        this.goodsStatus = value;
    }

    /**
     * ��ȡbackInfo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBackInfo() {
        return backInfo;
    }

    /**
     * ����backInfo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBackInfo(String value) {
        this.backInfo = value;
    }

	public String getEarningDeptStandardCode() {
		return earningDeptStandardCode;
	}

	public void setEarningDeptStandardCode(String earningDeptStandardCode) {
		this.earningDeptStandardCode = earningDeptStandardCode;
	}

	public String getEarningDeptStandardName() {
		return earningDeptStandardName;
	}

	public void setEarningDeptStandardName(String earningDeptStandardName) {
		this.earningDeptStandardName = earningDeptStandardName;
	}

    
}
