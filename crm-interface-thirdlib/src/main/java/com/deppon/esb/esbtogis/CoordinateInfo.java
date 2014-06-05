
package com.deppon.esb.esbtogis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CoordinateInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CoordinateInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deptName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="baiduLng" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="baiduLat" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="googleLng" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="googleLat" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoordinateInfo", namespace = "http://www.deppon.com/deppon/gis/inteface/domain", propOrder = {
    "deptName",
    "baiduLng",
    "baiduLat",
    "googleLng",
    "googleLat",
    "type",
    "address",
    "desc"
})
public class CoordinateInfo {

    @XmlElement(required = true)
    protected String deptName;
    @XmlElement(required = true)
    protected String baiduLng;
    @XmlElement(required = true)
    protected String baiduLat;
    @XmlElement(required = true)
    protected String googleLng;
    @XmlElement(required = true)
    protected String googleLat;
    protected String type;
    protected String address;
    protected String desc;

    /**
     * 获取deptName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 设置deptName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptName(String value) {
        this.deptName = value;
    }

    /**
     * 获取baiduLng属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaiduLng() {
        return baiduLng;
    }

    /**
     * 设置baiduLng属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaiduLng(String value) {
        this.baiduLng = value;
    }

    /**
     * 获取baiduLat属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaiduLat() {
        return baiduLat;
    }

    /**
     * 设置baiduLat属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaiduLat(String value) {
        this.baiduLat = value;
    }

    /**
     * 获取googleLng属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoogleLng() {
        return googleLng;
    }

    /**
     * 设置googleLng属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoogleLng(String value) {
        this.googleLng = value;
    }

    /**
     * 获取googleLat属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoogleLat() {
        return googleLat;
    }

    /**
     * 设置googleLat属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoogleLat(String value) {
        this.googleLat = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * 获取address属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置address属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * 获取desc属性的值。
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
     * 设置desc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

}
