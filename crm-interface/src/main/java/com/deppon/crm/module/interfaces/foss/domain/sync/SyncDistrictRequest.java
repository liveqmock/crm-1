
package com.deppon.crm.module.interfaces.foss.domain.sync;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				FOSS����������Ϣ�仯�����ýӿ�ͬ�����
 * 			
 * 
 * <p>SyncDistrictRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="SyncDistrictRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DistrictInfo" type="{http://www.deppon.com/ows/inteface/domain/}DistrictInfo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SyncDistrictRequest", namespace = "http://www.deppon.com/ows/inteface/domain/", propOrder = {
    "districtInfo"
})
public class SyncDistrictRequest {

    @XmlElement(name = "DistrictInfo", required = true)
    protected List<DistrictInfo> districtInfo;

    /**
     * Gets the value of the districtInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the districtInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDistrictInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DistrictInfo }
     * 
     * 
     */
    public List<DistrictInfo> getDistrictInfo() {
        if (districtInfo == null) {
            districtInfo = new ArrayList<DistrictInfo>();
        }
        return this.districtInfo;
    }

}
