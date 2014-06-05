
package com.deppon.crm.module.interfaces.cc.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ESBHeader complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ESBHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="businessId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="businessDesc1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessDesc2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessDesc3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requestId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="responseId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceSystem" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetSystem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="esbServiceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="backServiceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="messageFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exchangePattern" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="sentSequence" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="resultCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="authentication" type="{http://www.deppon.com/esb/header}AuthInfo" minOccurs="0"/>
 *         &lt;element name="statusList" type="{http://www.deppon.com/esb/header}StatusList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ESBHeader", namespace = "http://www.deppon.com/esb/header", propOrder = {
    "version",
    "businessId",
    "businessDesc1",
    "businessDesc2",
    "businessDesc3",
    "requestId",
    "responseId",
    "sourceSystem",
    "targetSystem",
    "esbServiceCode",
    "backServiceCode",
    "messageFormat",
    "exchangePattern",
    "sentSequence",
    "resultCode",
    "authentication",
    "statusList"
})
public class ESBHeader {

    @XmlElement(required = true)
    protected String version;
    @XmlElement(required = true)
    protected String businessId;
    protected String businessDesc1;
    protected String businessDesc2;
    protected String businessDesc3;
    @XmlElement(required = true)
    protected String requestId;
    protected String responseId;
    @XmlElement(required = true)
    protected String sourceSystem;
    protected String targetSystem;
    @XmlElement(required = true)
    protected String esbServiceCode;
    protected String backServiceCode;
    protected String messageFormat;
    protected Integer exchangePattern;
    protected Integer sentSequence;
    protected Integer resultCode;
    protected AuthInfo authentication;
    protected StatusList statusList;

    /**
     * ��ȡversion���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * ����version���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * ��ȡbusinessId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessId() {
        return businessId;
    }

    /**
     * ����businessId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessId(String value) {
        this.businessId = value;
    }

    /**
     * ��ȡbusinessDesc1���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessDesc1() {
        return businessDesc1;
    }

    /**
     * ����businessDesc1���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessDesc1(String value) {
        this.businessDesc1 = value;
    }

    /**
     * ��ȡbusinessDesc2���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessDesc2() {
        return businessDesc2;
    }

    /**
     * ����businessDesc2���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessDesc2(String value) {
        this.businessDesc2 = value;
    }

    /**
     * ��ȡbusinessDesc3���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessDesc3() {
        return businessDesc3;
    }

    /**
     * ����businessDesc3���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessDesc3(String value) {
        this.businessDesc3 = value;
    }

    /**
     * ��ȡrequestId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * ����requestId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * ��ȡresponseId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseId() {
        return responseId;
    }

    /**
     * ����responseId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseId(String value) {
        this.responseId = value;
    }

    /**
     * ��ȡsourceSystem���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceSystem() {
        return sourceSystem;
    }

    /**
     * ����sourceSystem���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceSystem(String value) {
        this.sourceSystem = value;
    }

    /**
     * ��ȡtargetSystem���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetSystem() {
        return targetSystem;
    }

    /**
     * ����targetSystem���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetSystem(String value) {
        this.targetSystem = value;
    }

    /**
     * ��ȡesbServiceCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsbServiceCode() {
        return esbServiceCode;
    }

    /**
     * ����esbServiceCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsbServiceCode(String value) {
        this.esbServiceCode = value;
    }

    /**
     * ��ȡbackServiceCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBackServiceCode() {
        return backServiceCode;
    }

    /**
     * ����backServiceCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBackServiceCode(String value) {
        this.backServiceCode = value;
    }

    /**
     * ��ȡmessageFormat���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageFormat() {
        return messageFormat;
    }

    /**
     * ����messageFormat���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageFormat(String value) {
        this.messageFormat = value;
    }

    /**
     * ��ȡexchangePattern���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExchangePattern() {
        return exchangePattern;
    }

    /**
     * ����exchangePattern���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExchangePattern(Integer value) {
        this.exchangePattern = value;
    }

    /**
     * ��ȡsentSequence���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSentSequence() {
        return sentSequence;
    }

    /**
     * ����sentSequence���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSentSequence(Integer value) {
        this.sentSequence = value;
    }

    /**
     * ��ȡresultCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getResultCode() {
        return resultCode;
    }

    /**
     * ����resultCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setResultCode(Integer value) {
        this.resultCode = value;
    }

    /**
     * ��ȡauthentication���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link AuthInfo }
     *     
     */
    public AuthInfo getAuthentication() {
        return authentication;
    }

    /**
     * ����authentication���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link AuthInfo }
     *     
     */
    public void setAuthentication(AuthInfo value) {
        this.authentication = value;
    }

    /**
     * ��ȡstatusList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link StatusList }
     *     
     */
    public StatusList getStatusList() {
        return statusList;
    }

    /**
     * ����statusList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link StatusList }
     *     
     */
    public void setStatusList(StatusList value) {
        this.statusList = value;
    }

}
