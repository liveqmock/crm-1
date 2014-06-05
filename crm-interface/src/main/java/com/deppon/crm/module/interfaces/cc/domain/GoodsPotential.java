
package com.deppon.crm.module.interfaces.cc.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * �߻�Ǳ��
 * 
 * <p>GoodsPotential complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="GoodsPotential">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="startCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startCityCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="arrivalCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="arrivalCityCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="potential" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="coLogCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ifhasLine" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="colIntention" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ifOurCarriage" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GoodsPotential", propOrder = {
    "startCity",
    "startCityCode",
    "arrivalCity",
    "arrivalCityCode",
    "potential",
    "coLogCompany",
    "ifhasLine",
    "colIntention",
    "ifOurCarriage",
    "remark"
})
public class GoodsPotential {

    @XmlElement(required = true)
    protected String startCity;
    @XmlElement(required = true)
    protected String startCityCode;
    @XmlElement(required = true)
    protected String arrivalCity;
    @XmlElement(required = true)
    protected String arrivalCityCode;
    @XmlElement(required = true)
    protected String potential;
    @XmlElement(required = true)
    protected String coLogCompany;
    protected boolean ifhasLine;
    @XmlElement(required = true)
    protected String colIntention;
    protected boolean ifOurCarriage;
    @XmlElement(required = true)
    protected String remark;

    /**
     * ��ȡstartCity���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartCity() {
        return startCity;
    }

    /**
     * ����startCity���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartCity(String value) {
        this.startCity = value;
    }

    /**
     * ��ȡstartCityCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartCityCode() {
        return startCityCode;
    }

    /**
     * ����startCityCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartCityCode(String value) {
        this.startCityCode = value;
    }

    /**
     * ��ȡarrivalCity���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivalCity() {
        return arrivalCity;
    }

    /**
     * ����arrivalCity���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivalCity(String value) {
        this.arrivalCity = value;
    }

    /**
     * ��ȡarrivalCityCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivalCityCode() {
        return arrivalCityCode;
    }

    /**
     * ����arrivalCityCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivalCityCode(String value) {
        this.arrivalCityCode = value;
    }

    /**
     * ��ȡpotential���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPotential() {
        return potential;
    }

    /**
     * ����potential���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPotential(String value) {
        this.potential = value;
    }

    /**
     * ��ȡcoLogCompany���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoLogCompany() {
        return coLogCompany;
    }

    /**
     * ����coLogCompany���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoLogCompany(String value) {
        this.coLogCompany = value;
    }

    /**
     * ��ȡifhasLine���Ե�ֵ��
     * 
     */
    public boolean isIfhasLine() {
        return ifhasLine;
    }

    /**
     * ����ifhasLine���Ե�ֵ��
     * 
     */
    public void setIfhasLine(boolean value) {
        this.ifhasLine = value;
    }

    /**
     * ��ȡcolIntention���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColIntention() {
        return colIntention;
    }

    /**
     * ����colIntention���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColIntention(String value) {
        this.colIntention = value;
    }

    /**
     * ��ȡifOurCarriage���Ե�ֵ��
     * 
     */
    public boolean isIfOurCarriage() {
        return ifOurCarriage;
    }

    /**
     * ����ifOurCarriage���Ե�ֵ��
     * 
     */
    public void setIfOurCarriage(boolean value) {
        this.ifOurCarriage = value;
    }

    /**
     * ��ȡremark���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * ����remark���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

}
