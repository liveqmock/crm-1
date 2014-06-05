
package com.deppon.crm.module.interfaces.foss.domain.sync;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				FOSS��֯��Ϣ�仯�����ýӿ�ͬ�����
 * 			
 * 
 * <p>SyncOrganizationRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="SyncOrganizationRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrganizationInfo" type="{http://www.deppon.com/ows/inteface/domain/}OrganizationInfo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SyncOrganizationRequest", namespace = "http://www.deppon.com/ows/inteface/domain/", propOrder = {
    "organizationInfo"
})
public class SyncOrganizationRequest {

    @XmlElement(name = "OrganizationInfo", required = true)
    protected List<OrganizationInfo> organizationInfo;

    /**
     * Gets the value of the organizationInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the organizationInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrganizationInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganizationInfo }
     * 
     * 
     */
    public List<OrganizationInfo> getOrganizationInfo() {
        if (organizationInfo == null) {
            organizationInfo = new ArrayList<OrganizationInfo>();
        }
        return this.organizationInfo;
    }

}
