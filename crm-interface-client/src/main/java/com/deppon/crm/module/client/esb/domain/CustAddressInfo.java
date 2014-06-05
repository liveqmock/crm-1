
package com.deppon.crm.module.client.esb.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * �ͻ����ͻ���ַ
 * 
 * <p>CustAddressInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="CustAddressInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="faddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fpostcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fprovince" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fcity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="farea" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="faddresstype" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fstatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fcreateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="flastUpdateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustAddressInfo", propOrder = {
    "faddress",
    "fpostcode",
    "fprovince",
    "fcity",
    "farea",
    "faddresstype",
    "fstatus",
    "fcreateTime",
    "flastUpdateTime"
})
public class CustAddressInfo {

    @XmlElement(required = true)
    protected String faddress;
    @XmlElement(required = true)
    protected String fpostcode;
    @XmlElement(required = true)
    protected String fprovince;
    @XmlElement(required = true)
    protected String fcity;
    @XmlElement(required = true)
    protected String farea;
    protected int faddresstype;
    protected int fstatus;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date fcreateTime;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date flastUpdateTime;

    /**
     * ��ȡfaddress���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaddress() {
        return faddress;
    }

    /**
     * ����faddress���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaddress(String value) {
        this.faddress = value;
    }

    /**
     * ��ȡfpostcode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFpostcode() {
        return fpostcode;
    }

    /**
     * ����fpostcode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFpostcode(String value) {
        this.fpostcode = value;
    }

    /**
     * ��ȡfprovince���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFprovince() {
        return fprovince;
    }

    /**
     * ����fprovince���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFprovince(String value) {
        this.fprovince = value;
    }

    /**
     * ��ȡfcity���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFcity() {
        return fcity;
    }

    /**
     * ����fcity���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFcity(String value) {
        this.fcity = value;
    }

    /**
     * ��ȡfarea���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFarea() {
        return farea;
    }

    /**
     * ����farea���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFarea(String value) {
        this.farea = value;
    }

    /**
     * ��ȡfaddresstype���Ե�ֵ��
     * 
     */
    public int getFaddresstype() {
        return faddresstype;
    }

    /**
     * ����faddresstype���Ե�ֵ��
     * 
     */
    public void setFaddresstype(int value) {
        this.faddresstype = value;
    }

    /**
     * ��ȡfstatus���Ե�ֵ��
     * 
     */
    public int getFstatus() {
        return fstatus;
    }

    /**
     * ����fstatus���Ե�ֵ��
     * 
     */
    public void setFstatus(int value) {
        this.fstatus = value;
    }

    /**
     * ��ȡfcreateTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFcreateTime() {
        return fcreateTime;
    }

    /**
     * ����fcreateTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFcreateTime(Date value) {
        this.fcreateTime = value;
    }

    /**
     * ��ȡflastUpdateTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFlastUpdateTime() {
        return flastUpdateTime;
    }

    /**
     * ����flastUpdateTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlastUpdateTime(Date value) {
        this.flastUpdateTime = value;
    }

}
