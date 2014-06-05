
package com.deppon.crm.module.client.esb.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * �ͻ���������
 * 
 * <p>CustBankInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="CustBankInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fsubbankname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fbankaccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fcountname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fbankcityid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fbankprovinceid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fbankid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="flinkmanmobile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="frelation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fsubbanknameid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fdesciption" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fisdefaultaccount" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustBankInfo", propOrder = {
    "fsubbankname",
    "fbankaccount",
    "fcountname",
    "fbankcityid",
    "fbankprovinceid",
    "fbankid",
    "flinkmanmobile",
    "frelation",
    "fsubbanknameid",
    "fdesciption",
    "fisdefaultaccount"
})
public class CustBankInfo {

    @XmlElement(required = true)
    protected String fsubbankname;
    @XmlElement(required = true)
    protected String fbankaccount;
    @XmlElement(required = true)
    protected String fcountname;
    @XmlElement(required = true)
    protected String fbankcityid;
    @XmlElement(required = true)
    protected String fbankprovinceid;
    @XmlElement(required = true)
    protected String fbankid;
    @XmlElement(required = true)
    protected String flinkmanmobile;
    @XmlElement(required = true)
    protected String frelation;
    @XmlElement(required = true)
    protected String fsubbanknameid;
    @XmlElement(required = true)
    protected String fdesciption;
    protected boolean fisdefaultaccount;

    /**
     * ��ȡfsubbankname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFsubbankname() {
        return fsubbankname;
    }

    /**
     * ����fsubbankname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFsubbankname(String value) {
        this.fsubbankname = value;
    }

    /**
     * ��ȡfbankaccount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFbankaccount() {
        return fbankaccount;
    }

    /**
     * ����fbankaccount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFbankaccount(String value) {
        this.fbankaccount = value;
    }

    /**
     * ��ȡfcountname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFcountname() {
        return fcountname;
    }

    /**
     * ����fcountname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFcountname(String value) {
        this.fcountname = value;
    }

    /**
     * ��ȡfbankcityid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFbankcityid() {
        return fbankcityid;
    }

    /**
     * ����fbankcityid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFbankcityid(String value) {
        this.fbankcityid = value;
    }

    /**
     * ��ȡfbankprovinceid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFbankprovinceid() {
        return fbankprovinceid;
    }

    /**
     * ����fbankprovinceid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFbankprovinceid(String value) {
        this.fbankprovinceid = value;
    }

    /**
     * ��ȡfbankid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFbankid() {
        return fbankid;
    }

    /**
     * ����fbankid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFbankid(String value) {
        this.fbankid = value;
    }

    /**
     * ��ȡflinkmanmobile���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlinkmanmobile() {
        return flinkmanmobile;
    }

    /**
     * ����flinkmanmobile���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlinkmanmobile(String value) {
        this.flinkmanmobile = value;
    }

    /**
     * ��ȡfrelation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrelation() {
        return frelation;
    }

    /**
     * ����frelation���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrelation(String value) {
        this.frelation = value;
    }

    /**
     * ��ȡfsubbanknameid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFsubbanknameid() {
        return fsubbanknameid;
    }

    /**
     * ����fsubbanknameid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFsubbanknameid(String value) {
        this.fsubbanknameid = value;
    }

    /**
     * ��ȡfdesciption���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdesciption() {
        return fdesciption;
    }

    /**
     * ����fdesciption���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdesciption(String value) {
        this.fdesciption = value;
    }

    /**
     * ��ȡfisdefaultaccount���Ե�ֵ��
     * 
     */
    public boolean isFisdefaultaccount() {
        return fisdefaultaccount;
    }

    /**
     * ����fisdefaultaccount���Ե�ֵ��
     * 
     */
    public void setFisdefaultaccount(boolean value) {
        this.fisdefaultaccount = value;
    }

}
