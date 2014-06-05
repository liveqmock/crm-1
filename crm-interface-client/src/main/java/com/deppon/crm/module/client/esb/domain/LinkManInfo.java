
package com.deppon.crm.module.client.esb.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * �ͻ���ϵ����Ϣ
 * 
 * <p>LinkManInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="LinkManInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fsex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="foffertel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fmobiletel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="flinkmanaddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="femail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fpostcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fborndate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="fidcard" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fpersonlove" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isReceiveEmail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isReceiveShortMsg" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isReceiveLetter" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fgainave" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ffolk" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fnativeplace" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fduty" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fdutydept" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fcreatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="flastupdatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LinkManInfo", propOrder = {
    "fsex",
    "foffertel",
    "fmobiletel",
    "fax",
    "flinkmanaddress",
    "femail",
    "fpostcode",
    "fborndate",
    "fidcard",
    "fpersonlove",
    "isReceiveEmail",
    "isReceiveShortMsg",
    "isReceiveLetter",
    "fgainave",
    "ffolk",
    "fnativeplace",
    "fduty",
    "fdutydept",
    "fname",
    "desc",
    "fcreatetime",
    "flastupdatetime"
})
public class LinkManInfo {

    protected int fsex;
    @XmlElement(required = true)
    protected String foffertel;
    @XmlElement(required = true)
    protected String fmobiletel;
    @XmlElement(required = true)
    protected String fax;
    @XmlElement(required = true)
    protected String flinkmanaddress;
    @XmlElement(required = true)
    protected String femail;
    @XmlElement(required = true)
    protected String fpostcode;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date fborndate;
    @XmlElement(required = true)
    protected String fidcard;
    @XmlElement(required = true)
    protected String fpersonlove;
    protected boolean isReceiveEmail;
    protected boolean isReceiveShortMsg;
    protected boolean isReceiveLetter;
    @XmlElement(required = true)
    protected String fgainave;
    @XmlElement(required = true)
    protected String ffolk;
    @XmlElement(required = true)
    protected String fnativeplace;
    @XmlElement(required = true)
    protected String fduty;
    @XmlElement(required = true)
    protected String fdutydept;
    @XmlElement(required = true)
    protected String fname;
    @XmlElement(required = true)
    protected String desc;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date fcreatetime;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date flastupdatetime;

    /**
     * ��ȡfsex���Ե�ֵ��
     * 
     */
    public int getFsex() {
        return fsex;
    }

    /**
     * ����fsex���Ե�ֵ��
     * 
     */
    public void setFsex(int value) {
        this.fsex = value;
    }

    /**
     * ��ȡfoffertel���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFoffertel() {
        return foffertel;
    }

    /**
     * ����foffertel���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFoffertel(String value) {
        this.foffertel = value;
    }

    /**
     * ��ȡfmobiletel���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFmobiletel() {
        return fmobiletel;
    }

    /**
     * ����fmobiletel���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFmobiletel(String value) {
        this.fmobiletel = value;
    }

    /**
     * ��ȡfax���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax() {
        return fax;
    }

    /**
     * ����fax���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax(String value) {
        this.fax = value;
    }

    /**
     * ��ȡflinkmanaddress���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlinkmanaddress() {
        return flinkmanaddress;
    }

    /**
     * ����flinkmanaddress���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlinkmanaddress(String value) {
        this.flinkmanaddress = value;
    }

    /**
     * ��ȡfemail���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFemail() {
        return femail;
    }

    /**
     * ����femail���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFemail(String value) {
        this.femail = value;
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
     * ��ȡfborndate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFborndate() {
        return fborndate;
    }

    /**
     * ����fborndate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFborndate(Date value) {
        this.fborndate = value;
    }

    /**
     * ��ȡfidcard���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFidcard() {
        return fidcard;
    }

    /**
     * ����fidcard���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFidcard(String value) {
        this.fidcard = value;
    }

    /**
     * ��ȡfpersonlove���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFpersonlove() {
        return fpersonlove;
    }

    /**
     * ����fpersonlove���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFpersonlove(String value) {
        this.fpersonlove = value;
    }

    /**
     * ��ȡisReceiveEmail���Ե�ֵ��
     * 
     */
    public boolean isIsReceiveEmail() {
        return isReceiveEmail;
    }

    /**
     * ����isReceiveEmail���Ե�ֵ��
     * 
     */
    public void setIsReceiveEmail(boolean value) {
        this.isReceiveEmail = value;
    }

    /**
     * ��ȡisReceiveShortMsg���Ե�ֵ��
     * 
     */
    public boolean isIsReceiveShortMsg() {
        return isReceiveShortMsg;
    }

    /**
     * ����isReceiveShortMsg���Ե�ֵ��
     * 
     */
    public void setIsReceiveShortMsg(boolean value) {
        this.isReceiveShortMsg = value;
    }

    /**
     * ��ȡisReceiveLetter���Ե�ֵ��
     * 
     */
    public boolean isIsReceiveLetter() {
        return isReceiveLetter;
    }

    /**
     * ����isReceiveLetter���Ե�ֵ��
     * 
     */
    public void setIsReceiveLetter(boolean value) {
        this.isReceiveLetter = value;
    }

    /**
     * ��ȡfgainave���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFgainave() {
        return fgainave;
    }

    /**
     * ����fgainave���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFgainave(String value) {
        this.fgainave = value;
    }

    /**
     * ��ȡffolk���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFfolk() {
        return ffolk;
    }

    /**
     * ����ffolk���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFfolk(String value) {
        this.ffolk = value;
    }

    /**
     * ��ȡfnativeplace���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFnativeplace() {
        return fnativeplace;
    }

    /**
     * ����fnativeplace���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFnativeplace(String value) {
        this.fnativeplace = value;
    }

    /**
     * ��ȡfduty���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFduty() {
        return fduty;
    }

    /**
     * ����fduty���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFduty(String value) {
        this.fduty = value;
    }

    /**
     * ��ȡfdutydept���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdutydept() {
        return fdutydept;
    }

    /**
     * ����fdutydept���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdutydept(String value) {
        this.fdutydept = value;
    }

    /**
     * ��ȡfname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFname() {
        return fname;
    }

    /**
     * ����fname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFname(String value) {
        this.fname = value;
    }

    /**
     * ��ȡdesc���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * ����desc���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * ��ȡfcreatetime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFcreatetime() {
        return fcreatetime;
    }

    /**
     * ����fcreatetime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFcreatetime(Date value) {
        this.fcreatetime = value;
    }

    /**
     * ��ȡflastupdatetime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFlastupdatetime() {
        return flastupdatetime;
    }

    /**
     * ����flastupdatetime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlastupdatetime(Date value) {
        this.flastupdatetime = value;
    }

}
