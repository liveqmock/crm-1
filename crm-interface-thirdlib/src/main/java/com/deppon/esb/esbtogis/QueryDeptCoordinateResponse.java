
package com.deppon.esb.esbtogis;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>QueryDeptCoordinateResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="QueryDeptCoordinateResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="coordinateList" type="{http://www.deppon.com/deppon/gis/inteface/domain}CoordinateInfo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryDeptCoordinateResponse", namespace = "http://www.deppon.com/deppon/gis/inteface/domain", propOrder = {
    "coordinateList"
})
public class QueryDeptCoordinateResponse {

    @XmlElement(required = true)
    protected List<CoordinateInfo> coordinateList;

    /**
     * Gets the value of the coordinateList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coordinateList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCoordinateList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CoordinateInfo }
     * 
     * 
     */
    public List<CoordinateInfo> getCoordinateList() {
        if (coordinateList == null) {
            coordinateList = new ArrayList<CoordinateInfo>();
        }
        return this.coordinateList;
    }

}
