
package com.deppon.crm.module.client.esb.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * ��ϵ�˽��ͻ���ַ
 * 
 * <p>LinkManAddresInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="LinkManAddresInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fshuttleaddressid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="flinkmanid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="faddresstype" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="faddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fstartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="fendTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="fbillRequest" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fhasstopcost" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fpaytype" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fissendtofloor" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fotherneed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fismainaddress" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fstatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fcreateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
@XmlType(name = "LinkManAddresInfo", propOrder = {
    "fshuttleaddressid",
    "flinkmanid",
    "faddresstype",
    "faddress",
    "fstartTime",
    "fendTime",
    "fbillRequest",
    "fhasstopcost",
    "fpaytype",
    "fissendtofloor",
    "fotherneed",
    "fismainaddress",
    "fstatus",
    "fcreateTime",
    "flastupdatetime"
})
public class LinkManAddresInfo {

    @XmlElement(required = true)
    protected String fshuttleaddressid;
    @XmlElement(required = true)
    protected String flinkmanid;
    protected int faddresstype;
    @XmlElement(required = true)
    protected String faddress;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date fstartTime;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date fendTime;
    protected int fbillRequest;
    @XmlElement(required = true)
    protected BigDecimal fhasstopcost;
    protected int fpaytype;
    protected boolean fissendtofloor;
    @XmlElement(required = true)
    protected String fotherneed;
    protected boolean fismainaddress;
    protected int fstatus;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date fcreateTime;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date flastupdatetime;

    /**
     * ��ȡfshuttleaddressid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFshuttleaddressid() {
        return fshuttleaddressid;
    }

    /**
     * ����fshuttleaddressid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFshuttleaddressid(String value) {
        this.fshuttleaddressid = value;
    }

    /**
     * ��ȡflinkmanid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlinkmanid() {
        return flinkmanid;
    }

    /**
     * ����flinkmanid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlinkmanid(String value) {
        this.flinkmanid = value;
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
     * ��ȡfstartTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFstartTime() {
        return fstartTime;
    }

    /**
     * ����fstartTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFstartTime(Date value) {
        this.fstartTime = value;
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
     * ��ȡfbillRequest���Ե�ֵ��
     * 
     */
    public int getFbillRequest() {
        return fbillRequest;
    }

    /**
     * ����fbillRequest���Ե�ֵ��
     * 
     */
    public void setFbillRequest(int value) {
        this.fbillRequest = value;
    }

    /**
     * ��ȡfhasstopcost���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFhasstopcost() {
        return fhasstopcost;
    }

    /**
     * ����fhasstopcost���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFhasstopcost(BigDecimal value) {
        this.fhasstopcost = value;
    }

    /**
     * ��ȡfpaytype���Ե�ֵ��
     * 
     */
    public int getFpaytype() {
        return fpaytype;
    }

    /**
     * ����fpaytype���Ե�ֵ��
     * 
     */
    public void setFpaytype(int value) {
        this.fpaytype = value;
    }

    /**
     * ��ȡfissendtofloor���Ե�ֵ��
     * 
     */
    public boolean isFissendtofloor() {
        return fissendtofloor;
    }

    /**
     * ����fissendtofloor���Ե�ֵ��
     * 
     */
    public void setFissendtofloor(boolean value) {
        this.fissendtofloor = value;
    }

    /**
     * ��ȡfotherneed���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFotherneed() {
        return fotherneed;
    }

    /**
     * ����fotherneed���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFotherneed(String value) {
        this.fotherneed = value;
    }

    /**
     * ��ȡfismainaddress���Ե�ֵ��
     * 
     */
    public boolean isFismainaddress() {
        return fismainaddress;
    }

    /**
     * ����fismainaddress���Ե�ֵ��
     * 
     */
    public void setFismainaddress(boolean value) {
        this.fismainaddress = value;
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
