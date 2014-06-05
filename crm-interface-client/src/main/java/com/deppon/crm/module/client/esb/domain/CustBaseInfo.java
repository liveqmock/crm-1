
package com.deppon.crm.module.client.esb.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * �ͻ�����Ϣ
 * 
 * <p>CustBaseInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="CustBaseInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fcustnumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fregistaddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fcustnature" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fcusttype" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="credit" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fcustname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fdegree" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ftaxregnumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fdeptid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fcrmcancel" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="monthSettlementCustOwe" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fcreatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
@XmlType(name = "CustBaseInfo", propOrder = {
    "fcustnumber",
    "fregistaddress",
    "fcustnature",
    "fcusttype",
    "credit",
    "fcustname",
    "fdegree",
    "ftaxregnumber",
    "fdeptid",
    "fcrmcancel",
    "fid",
    "monthSettlementCustOwe",
    "fcreatetime",
    "flastUpdateTime"
})
public class CustBaseInfo {

    @XmlElement(required = true)
    protected String fcustnumber;
    @XmlElement(required = true)
    protected String fregistaddress;
    protected int fcustnature;
    protected int fcusttype;
    @XmlElement(required = true)
    protected BigDecimal credit;
    @XmlElement(required = true)
    protected String fcustname;
    protected int fdegree;
    @XmlElement(required = true)
    protected String ftaxregnumber;
    @XmlElement(required = true)
    protected String fdeptid;
    protected boolean fcrmcancel;
    @XmlElement(required = true)
    protected String fid;
    @XmlElement(required = true)
    protected BigDecimal monthSettlementCustOwe;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date fcreatetime;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date flastUpdateTime;

    /**
     * ��ȡfcustnumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFcustnumber() {
        return fcustnumber;
    }

    /**
     * ����fcustnumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFcustnumber(String value) {
        this.fcustnumber = value;
    }

    /**
     * ��ȡfregistaddress���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFregistaddress() {
        return fregistaddress;
    }

    /**
     * ����fregistaddress���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFregistaddress(String value) {
        this.fregistaddress = value;
    }

    /**
     * ��ȡfcustnature���Ե�ֵ��
     * 
     */
    public int getFcustnature() {
        return fcustnature;
    }

    /**
     * ����fcustnature���Ե�ֵ��
     * 
     */
    public void setFcustnature(int value) {
        this.fcustnature = value;
    }

    /**
     * ��ȡfcusttype���Ե�ֵ��
     * 
     */
    public int getFcusttype() {
        return fcusttype;
    }

    /**
     * ����fcusttype���Ե�ֵ��
     * 
     */
    public void setFcusttype(int value) {
        this.fcusttype = value;
    }

    /**
     * ��ȡcredit���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCredit() {
        return credit;
    }

    /**
     * ����credit���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCredit(BigDecimal value) {
        this.credit = value;
    }

    /**
     * ��ȡfcustname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFcustname() {
        return fcustname;
    }

    /**
     * ����fcustname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFcustname(String value) {
        this.fcustname = value;
    }

    /**
     * ��ȡfdegree���Ե�ֵ��
     * 
     */
    public int getFdegree() {
        return fdegree;
    }

    /**
     * ����fdegree���Ե�ֵ��
     * 
     */
    public void setFdegree(int value) {
        this.fdegree = value;
    }

    /**
     * ��ȡftaxregnumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFtaxregnumber() {
        return ftaxregnumber;
    }

    /**
     * ����ftaxregnumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFtaxregnumber(String value) {
        this.ftaxregnumber = value;
    }

    /**
     * ��ȡfdeptid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdeptid() {
        return fdeptid;
    }

    /**
     * ����fdeptid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdeptid(String value) {
        this.fdeptid = value;
    }

    /**
     * ��ȡfcrmcancel���Ե�ֵ��
     * 
     */
    public boolean isFcrmcancel() {
        return fcrmcancel;
    }

    /**
     * ����fcrmcancel���Ե�ֵ��
     * 
     */
    public void setFcrmcancel(boolean value) {
        this.fcrmcancel = value;
    }

    /**
     * ��ȡfid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFid() {
        return fid;
    }

    /**
     * ����fid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFid(String value) {
        this.fid = value;
    }

    /**
     * ��ȡmonthSettlementCustOwe���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMonthSettlementCustOwe() {
        return monthSettlementCustOwe;
    }

    /**
     * ����monthSettlementCustOwe���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMonthSettlementCustOwe(BigDecimal value) {
        this.monthSettlementCustOwe = value;
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
