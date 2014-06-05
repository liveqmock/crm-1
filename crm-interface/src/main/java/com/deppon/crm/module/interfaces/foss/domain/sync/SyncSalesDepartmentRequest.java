
package com.deppon.crm.module.interfaces.foss.domain.sync;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Ӫҵ����Ϣͬ��������Ϣ
 * 			
 * 
 * <p>SyncSalesDepartmentRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="SyncSalesDepartmentRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="depts" type="{http://www.deppon.com/ows/inteface/domain/}DepartmentInfo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SyncSalesDepartmentRequest", namespace = "http://www.deppon.com/ows/inteface/domain/", propOrder = {
    "depts"
})
public class SyncSalesDepartmentRequest {

    @XmlElement(required = true)
    protected List<DepartmentInfo> depts;

    /**
     * Gets the value of the depts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the depts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDepts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DepartmentInfo }
     * 
     * 
     */
    public List<DepartmentInfo> getDepts() {
        if (depts == null) {
            depts = new ArrayList<DepartmentInfo>();
        }
        return this.depts;
    }

}
