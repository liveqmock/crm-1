
package com.deppon.map.baidu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deptByDistance complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deptByDistance">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deptStandardcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="distance" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deptByDistance", propOrder = {
    "deptStandardcode",
    "distance"
})
public class DeptByDistance {

    protected String deptStandardcode;
    protected double distance;

    /**
     * Gets the value of the deptStandardcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptStandardcode() {
        return deptStandardcode;
    }

    /**
     * Sets the value of the deptStandardcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptStandardcode(String value) {
        this.deptStandardcode = value;
    }

    /**
     * Gets the value of the distance property.
     * 
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets the value of the distance property.
     * 
     */
    public void setDistance(double value) {
        this.distance = value;
    }

}
