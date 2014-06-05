
package com.deppon.erp.payment;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for msg complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="msg">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fdescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flastupdateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fmsgNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fstandardcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fsubModule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fsubModuleNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fsubSys" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "msg", propOrder = {
    "fdescription",
    "flastupdateDate",
    "fmsgNum",
    "fstandardcode",
    "fsubModule",
    "fsubModuleNum",
    "fsubSys"
})
public class Msg {

    protected String fdescription;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date flastupdateDate;
    protected int fmsgNum;
    protected String fstandardcode;
    protected String fsubModule;
    protected int fsubModuleNum;
    protected String fsubSys;

    /**
     * Gets the value of the fdescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdescription() {
        return fdescription;
    }

    /**
     * Sets the value of the fdescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdescription(String value) {
        this.fdescription = value;
    }

    /**
     * Gets the value of the flastupdateDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFlastupdateDate() {
        return flastupdateDate;
    }

    /**
     * Sets the value of the flastupdateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlastupdateDate(Date value) {
        this.flastupdateDate = value;
    }

    /**
     * Gets the value of the fmsgNum property.
     * 
     */
    public int getFmsgNum() {
        return fmsgNum;
    }

    /**
     * Sets the value of the fmsgNum property.
     * 
     */
    public void setFmsgNum(int value) {
        this.fmsgNum = value;
    }

    /**
     * Gets the value of the fstandardcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFstandardcode() {
        return fstandardcode;
    }

    /**
     * Sets the value of the fstandardcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFstandardcode(String value) {
        this.fstandardcode = value;
    }

    /**
     * Gets the value of the fsubModule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFsubModule() {
        return fsubModule;
    }

    /**
     * Sets the value of the fsubModule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFsubModule(String value) {
        this.fsubModule = value;
    }

    /**
     * Gets the value of the fsubModuleNum property.
     * 
     */
    public int getFsubModuleNum() {
        return fsubModuleNum;
    }

    /**
     * Sets the value of the fsubModuleNum property.
     * 
     */
    public void setFsubModuleNum(int value) {
        this.fsubModuleNum = value;
    }

    /**
     * Gets the value of the fsubSys property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFsubSys() {
        return fsubSys;
    }

    /**
     * Sets the value of the fsubSys property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFsubSys(String value) {
        this.fsubSys = value;
    }

}
