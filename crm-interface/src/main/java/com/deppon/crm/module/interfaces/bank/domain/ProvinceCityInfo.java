
package com.deppon.crm.module.interfaces.bank.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ProvinceCityInfo complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="ProvinceCityInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="provinceCityId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="provinceOrCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="provinceCityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="provenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastUpdateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="isCancel" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProvinceCityInfo", propOrder = {
    "provinceCityId",
    "provinceOrCity",
    "provinceCityName",
    "provenceId",
    "operateCode",
    "lastUpdateTime",
    "isCancel"
})
/**
 * @作者: 罗典
 * @时间：2012-12-25下午3:20:35
 * @描述：银行省份城市信息
 */
public class ProvinceCityInfo {

	// 行政区域编码
    @XmlElement(required = true)
    protected String provinceCityId;
    // 行政区域等级, 1-省， 2-市
    @XmlElement(required = true)
    protected String provinceOrCity;
    // 省或市名称
    @XmlElement(required = true)
    protected String provinceCityName;
    // 所属省份， 若“行政区域等级”为市，则此字段必填
    protected String provenceId;
    // 操作类别, 1-新增; 2-修改; 3-作废
    @XmlElement(required = true)
    protected String operateCode;
    // 更新时间
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date lastUpdateTime;
    // 是否作废, 0-作废; 1-不作废
    protected boolean isCancel;

    /**
     * ��ȡprovinceCityId���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProvinceCityId() {
        return provinceCityId;
    }

    /**
     * ����provinceCityId���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProvinceCityId(String value) {
        this.provinceCityId = value;
    }

    /**
     * ��ȡprovinceOrCity���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProvinceOrCity() {
        return provinceOrCity;
    }

    /**
     * ����provinceOrCity���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProvinceOrCity(String value) {
        this.provinceOrCity = value;
    }

    /**
     * ��ȡprovinceCityName���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProvinceCityName() {
        return provinceCityName;
    }

    /**
     * ����provinceCityName���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProvinceCityName(String value) {
        this.provinceCityName = value;
    }

    /**
     * ��ȡprovenceId���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProvenceId() {
        return provenceId;
    }

    /**
     * ����provenceId���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProvenceId(String value) {
        this.provenceId = value;
    }

    /**
     * ��ȡoperateCode���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOperateCode() {
        return operateCode;
    }

    /**
     * ����operateCode���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOperateCode(String value) {
        this.operateCode = value;
    }


    public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
     * ��ȡisCancel���Ե�ֵ��
     *
     */
    public boolean isIsCancel() {
        return isCancel;
    }

    /**
     * ����isCancel���Ե�ֵ��
     *
     */
    public void setIsCancel(boolean value) {
        this.isCancel = value;
    }

}
