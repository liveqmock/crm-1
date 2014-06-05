
package com.deppon.crm.module.interfaces.foss.domain.sync;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ����Ե���к�����������Ϣͬ��������Ϣ
 * 
 * <p>SyncExpressCityRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="SyncExpressCityRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="citys" type="{http://www.deppon.com/ows/inteface/domain/}cityInfo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SyncExpressCityRequest", propOrder = {
    "citys"
})
public class SyncExpressCityRequest {

    @XmlElement(required = true)
    protected List<CityInfo> citys;

    /**
     * Gets the value of the citys property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the citys property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCitys().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CityInfo }
     * 
     * 
     */
    public List<CityInfo> getCitys() {
        if (citys == null) {
            citys = new ArrayList<CityInfo>();
        }
        return this.citys;
    }

}
