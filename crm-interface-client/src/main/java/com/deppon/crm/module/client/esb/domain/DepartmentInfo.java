
package com.deppon.crm.module.client.esb.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * ��ͬ���ò���
 * 
 * <p>DepartmentInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="DepartmentInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fdeptidStandardcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fcontractid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fdeptid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fbegintime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="fendTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="fworkflowid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fapprovalstate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fapprovalman" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fworkflowtype" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fstate" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fisdept" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "DepartmentInfo", propOrder = {
    "fdeptidStandardcode",
    "fcontractid",
    "fdeptid",
    "fbegintime",
    "fendTime",
    "fworkflowid",
    "fapprovalstate",
    "fapprovalman",
    "fworkflowtype",
    "fstate",
    "fisdept",
    "fcreatetime",
    "flastupdatetime"
})
public class DepartmentInfo {

    @XmlElement(required = true)
    protected String fdeptidStandardcode;
    @XmlElement(required = true)
    protected String fcontractid;
    @XmlElement(required = true)
    protected String fdeptid;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date fbegintime;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date fendTime;
    @XmlElement(required = true)
    protected String fworkflowid;
    @XmlElement(required = true)
    protected String fapprovalstate;
    @XmlElement(required = true)
    protected String fapprovalman;
    protected int fworkflowtype;
    protected boolean fstate;
    protected boolean fisdept;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date fcreatetime;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date flastupdatetime;

    /**
     * ��ȡfdeptidStandardcode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdeptidStandardcode() {
        return fdeptidStandardcode;
    }

    /**
     * ����fdeptidStandardcode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdeptidStandardcode(String value) {
        this.fdeptidStandardcode = value;
    }

    /**
     * ��ȡfcontractid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFcontractid() {
        return fcontractid;
    }

    /**
     * ����fcontractid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFcontractid(String value) {
        this.fcontractid = value;
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
     * ��ȡfbegintime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFbegintime() {
        return fbegintime;
    }

    /**
     * ����fbegintime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFbegintime(Date value) {
        this.fbegintime = value;
    }

    /**
     * ��ȡfendTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFendTime() {
        return fendTime;
    }

    /**
     * ����fendTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFendTime(Date value) {
        this.fendTime = value;
    }

    /**
     * ��ȡfworkflowid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFworkflowid() {
        return fworkflowid;
    }

    /**
     * ����fworkflowid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFworkflowid(String value) {
        this.fworkflowid = value;
    }

    /**
     * ��ȡfapprovalstate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFapprovalstate() {
        return fapprovalstate;
    }

    /**
     * ����fapprovalstate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFapprovalstate(String value) {
        this.fapprovalstate = value;
    }

    /**
     * ��ȡfapprovalman���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFapprovalman() {
        return fapprovalman;
    }

    /**
     * ����fapprovalman���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFapprovalman(String value) {
        this.fapprovalman = value;
    }

    /**
     * ��ȡfworkflowtype���Ե�ֵ��
     * 
     */
    public int getFworkflowtype() {
        return fworkflowtype;
    }

    /**
     * ����fworkflowtype���Ե�ֵ��
     * 
     */
    public void setFworkflowtype(int value) {
        this.fworkflowtype = value;
    }

    /**
     * ��ȡfstate���Ե�ֵ��
     * 
     */
    public boolean isFstate() {
        return fstate;
    }

    /**
     * ����fstate���Ե�ֵ��
     * 
     */
    public void setFstate(boolean value) {
        this.fstate = value;
    }

    /**
     * ��ȡfisdept���Ե�ֵ��
     * 
     */
    public boolean isFisdept() {
        return fisdept;
    }

    /**
     * ����fisdept���Ե�ֵ��
     * 
     */
    public void setFisdept(boolean value) {
        this.fisdept = value;
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
