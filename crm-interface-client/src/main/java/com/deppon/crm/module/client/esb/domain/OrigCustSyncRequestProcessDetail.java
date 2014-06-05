
package com.deppon.crm.module.client.esb.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ������ϸ
 * 
 * <p>OrigCustSyncRequestProcessDetail complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="OrigCustSyncRequestProcessDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fscatterid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operateType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrigCustSyncRequestProcessDetail", propOrder = {
    "fscatterid",
    "operateType",
    "result",
    "reason"
})
/**
 * @作者：罗典
 * @描述：处理明细
 * @时间：2013-3-5
 * */
public class OrigCustSyncRequestProcessDetail {
	// 散客ID
    @XmlElement(required = true)
    protected String fscatterid;
    // 操作类别：整数类型，1、新增；2、修改；3、删除；
    @XmlElement(required = true)
    protected String operateType;
    // 成功或失败的标识:0、失败；1、成功
    protected boolean result;
    // 失败原因,如果处理失败，此字段为必填
    protected String reason;

    /**
     * ��ȡfscatterid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFscatterid() {
        return fscatterid;
    }

    /**
     * ����fscatterid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFscatterid(String value) {
        this.fscatterid = value;
    }

    /**
     * ��ȡoperateType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * ����operateType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperateType(String value) {
        this.operateType = value;
    }

    /**
     * ��ȡresult���Ե�ֵ��
     * 
     */
    public boolean isResult() {
        return result;
    }

    /**
     * ����result���Ե�ֵ��
     * 
     */
    public void setResult(boolean value) {
        this.result = value;
    }

    /**
     * ��ȡreason���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * ����reason���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

}
