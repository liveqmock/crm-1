
package com.deppon.crm.module.interfaces.bank.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BankInfoProcessResult complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="BankInfoProcessResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bankId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "BankInfoProcessResult", propOrder = {
    "bankId",
    "operateCode",
    "result",
    "reason"
})
/**
 * 
 * @作者: 罗典
 * @时间：2012-12-21下午4:20:37
 * @描述：处理明细
 */
public class BankInfoProcessResult {
	// 银行编码
    @XmlElement(required = true)
    protected String bankId;
    // 操作类别, 1-新增; 2-修改; 3-作废
    @XmlElement(required = true)
    protected String operateCode;
    // 处理结果，是否成功 0: 失败  1：成功
    protected boolean result;
    // 失败原因
    protected String reason;

    /**
     * ��ȡbankId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankId() {
        return bankId;
    }

    /**
     * ����bankId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankId(String value) {
        this.bankId = value;
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
