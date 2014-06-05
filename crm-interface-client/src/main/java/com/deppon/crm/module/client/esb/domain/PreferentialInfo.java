
package com.deppon.crm.module.client.esb.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * �Ż���Ϣ
 * 
 * <p>PreferentialInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="PreferentialInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fchargerebate" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fagentgathrate" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="finsuredpricerate" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="freceivepricerate" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fdeliverypricerate" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
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
@XmlType(name = "PreferentialInfo", propOrder = {
    "fchargerebate",
    "fagentgathrate",
    "finsuredpricerate",
    "freceivepricerate",
    "fdeliverypricerate",
    "fcreatetime",
    "flastupdatetime"
})
public class PreferentialInfo {

    @XmlElement(required = true)
    protected BigDecimal fchargerebate;
    @XmlElement(required = true)
    protected BigDecimal fagentgathrate;
    @XmlElement(required = true)
    protected BigDecimal finsuredpricerate;
    @XmlElement(required = true)
    protected BigDecimal freceivepricerate;
    @XmlElement(required = true)
    protected BigDecimal fdeliverypricerate;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date fcreatetime;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date flastupdatetime;

    /**
     * ��ȡfchargerebate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFchargerebate() {
        return fchargerebate;
    }

    /**
     * ����fchargerebate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFchargerebate(BigDecimal value) {
        this.fchargerebate = value;
    }

    /**
     * ��ȡfagentgathrate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFagentgathrate() {
        return fagentgathrate;
    }

    /**
     * ����fagentgathrate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFagentgathrate(BigDecimal value) {
        this.fagentgathrate = value;
    }

    /**
     * ��ȡfinsuredpricerate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFinsuredpricerate() {
        return finsuredpricerate;
    }

    /**
     * ����finsuredpricerate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFinsuredpricerate(BigDecimal value) {
        this.finsuredpricerate = value;
    }

    /**
     * ��ȡfreceivepricerate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFreceivepricerate() {
        return freceivepricerate;
    }

    /**
     * ����freceivepricerate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFreceivepricerate(BigDecimal value) {
        this.freceivepricerate = value;
    }

    /**
     * ��ȡfdeliverypricerate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFdeliverypricerate() {
        return fdeliverypricerate;
    }

    /**
     * ����fdeliverypricerate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFdeliverypricerate(BigDecimal value) {
        this.fdeliverypricerate = value;
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
