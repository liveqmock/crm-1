
package com.deppon.crm.module.client.esb.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * �ͻ���ͬ��Ϣ
 * 
 * <p>ContractInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ContractInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fpayway" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="farrearamount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="flinkmanname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="flinkmanphone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="flinkmanmobile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="flinkmanaddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="frecondate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="finvoicdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="fresultdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="fcontractbegindate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="fcontractenddate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="fapplication" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fcustid" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="fdeptidStandardcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fiddiscount" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fcontractstatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fcontractsubject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fregicapital" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fbeforecontractnum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fcontractnum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fgoodsname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fcustcompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fcontactid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fpreferentialtype" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fpreferential" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "ContractInfo", propOrder = {
    "fpayway",
    "farrearamount",
    "flinkmanname",
    "flinkmanphone",
    "flinkmanmobile",
    "flinkmanaddress",
    "frecondate",
    "finvoicdate",
    "fresultdate",
    "fcontractbegindate",
    "fcontractenddate",
    "fapplication",
    "fcustid",
    "fdeptidStandardcode",
    "fiddiscount",
    "fcontractstatus",
    "fcontractsubject",
    "fregicapital",
    "fbeforecontractnum",
    "fcontractnum",
    "fgoodsname",
    "fcustcompany",
    "fcontactid",
    "fpreferentialtype",
    "fpreferential",
    "fcreatetime",
    "flastupdatetime"
})
public class ContractInfo {

    protected int fpayway;
    @XmlElement(required = true)
    protected BigDecimal farrearamount;
    @XmlElement(required = true)
    protected String flinkmanname;
    @XmlElement(required = true)
    protected String flinkmanphone;
    @XmlElement(required = true)
    protected String flinkmanmobile;
    @XmlElement(required = true)
    protected String flinkmanaddress;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date frecondate;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date finvoicdate;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date fresultdate;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date fcontractbegindate;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date fcontractenddate;
    @XmlElement(required = true)
    protected String fapplication;
    @XmlElement(required = true)
    protected List<String> fcustid;
    @XmlElement(required = true)
    protected String fdeptidStandardcode;
    protected boolean fiddiscount;
    protected int fcontractstatus;
    @XmlElement(required = true)
    protected String fcontractsubject;
    @XmlElement(required = true)
    protected BigDecimal fregicapital;
    @XmlElement(required = true)
    protected String fbeforecontractnum;
    @XmlElement(required = true)
    protected String fcontractnum;
    @XmlElement(required = true)
    protected String fgoodsname;
    @XmlElement(required = true)
    protected String fcustcompany;
    @XmlElement(required = true)
    protected String fcontactid;
    protected int fpreferentialtype;
    @XmlElement(required = true)
    protected String fpreferential;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date fcreatetime;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date flastupdatetime;

    /**
     * ��ȡfpayway���Ե�ֵ��
     * 
     */
    public int getFpayway() {
        return fpayway;
    }

    /**
     * ����fpayway���Ե�ֵ��
     * 
     */
    public void setFpayway(int value) {
        this.fpayway = value;
    }

    /**
     * ��ȡfarrearamount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFarrearamount() {
        return farrearamount;
    }

    /**
     * ����farrearamount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFarrearamount(BigDecimal value) {
        this.farrearamount = value;
    }

    /**
     * ��ȡflinkmanname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlinkmanname() {
        return flinkmanname;
    }

    /**
     * ����flinkmanname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlinkmanname(String value) {
        this.flinkmanname = value;
    }

    /**
     * ��ȡflinkmanphone���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlinkmanphone() {
        return flinkmanphone;
    }

    /**
     * ����flinkmanphone���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlinkmanphone(String value) {
        this.flinkmanphone = value;
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
     * ��ȡfrecondate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFrecondate() {
        return frecondate;
    }

    /**
     * ����frecondate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrecondate(Date value) {
        this.frecondate = value;
    }

    /**
     * ��ȡfinvoicdate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFinvoicdate() {
        return finvoicdate;
    }

    /**
     * ����finvoicdate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinvoicdate(Date value) {
        this.finvoicdate = value;
    }

    /**
     * ��ȡfresultdate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFresultdate() {
        return fresultdate;
    }

    /**
     * ����fresultdate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFresultdate(Date value) {
        this.fresultdate = value;
    }

    /**
     * ��ȡfcontractbegindate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFcontractbegindate() {
        return fcontractbegindate;
    }

    /**
     * ����fcontractbegindate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFcontractbegindate(Date value) {
        this.fcontractbegindate = value;
    }

    /**
     * ��ȡfcontractenddate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFcontractenddate() {
        return fcontractenddate;
    }

    /**
     * ����fcontractenddate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFcontractenddate(Date value) {
        this.fcontractenddate = value;
    }

    /**
     * ��ȡfapplication���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFapplication() {
        return fapplication;
    }

    /**
     * ����fapplication���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFapplication(String value) {
        this.fapplication = value;
    }

    /**
     * Gets the value of the fcustid property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fcustid property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFcustid().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFcustid() {
        if (fcustid == null) {
            fcustid = new ArrayList<String>();
        }
        return this.fcustid;
    }

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
     * ��ȡfiddiscount���Ե�ֵ��
     * 
     */
    public boolean isFiddiscount() {
        return fiddiscount;
    }

    /**
     * ����fiddiscount���Ե�ֵ��
     * 
     */
    public void setFiddiscount(boolean value) {
        this.fiddiscount = value;
    }

    /**
     * ��ȡfcontractstatus���Ե�ֵ��
     * 
     */
    public int getFcontractstatus() {
        return fcontractstatus;
    }

    /**
     * ����fcontractstatus���Ե�ֵ��
     * 
     */
    public void setFcontractstatus(int value) {
        this.fcontractstatus = value;
    }

    /**
     * ��ȡfcontractsubject���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFcontractsubject() {
        return fcontractsubject;
    }

    /**
     * ����fcontractsubject���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFcontractsubject(String value) {
        this.fcontractsubject = value;
    }

    /**
     * ��ȡfregicapital���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFregicapital() {
        return fregicapital;
    }

    /**
     * ����fregicapital���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFregicapital(BigDecimal value) {
        this.fregicapital = value;
    }

    /**
     * ��ȡfbeforecontractnum���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFbeforecontractnum() {
        return fbeforecontractnum;
    }

    /**
     * ����fbeforecontractnum���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFbeforecontractnum(String value) {
        this.fbeforecontractnum = value;
    }

    /**
     * ��ȡfcontractnum���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFcontractnum() {
        return fcontractnum;
    }

    /**
     * ����fcontractnum���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFcontractnum(String value) {
        this.fcontractnum = value;
    }

    /**
     * ��ȡfgoodsname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFgoodsname() {
        return fgoodsname;
    }

    /**
     * ����fgoodsname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFgoodsname(String value) {
        this.fgoodsname = value;
    }

    /**
     * ��ȡfcustcompany���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFcustcompany() {
        return fcustcompany;
    }

    /**
     * ����fcustcompany���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFcustcompany(String value) {
        this.fcustcompany = value;
    }

    /**
     * ��ȡfcontactid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFcontactid() {
        return fcontactid;
    }

    /**
     * ����fcontactid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFcontactid(String value) {
        this.fcontactid = value;
    }

    /**
     * ��ȡfpreferentialtype���Ե�ֵ��
     * 
     */
    public int getFpreferentialtype() {
        return fpreferentialtype;
    }

    /**
     * ����fpreferentialtype���Ե�ֵ��
     * 
     */
    public void setFpreferentialtype(int value) {
        this.fpreferentialtype = value;
    }

    /**
     * ��ȡfpreferential���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFpreferential() {
        return fpreferential;
    }

    /**
     * ����fpreferential���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFpreferential(String value) {
        this.fpreferential = value;
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
