
package com.deppon.crm.module.interfaces.foss.domain.sync;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ����Ե���к�����������Ϣͬ�����ؽ��
 * 
 * <p>SyncExpressCityResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="SyncExpressCityResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="failInfos" type="{http://www.deppon.com/ows/inteface/domain/failInfo}FailInfo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SyncExpressCityResponse", propOrder = {
    "failInfos"
})
public class SyncExpressCityResponse {

    @XmlElement(required = true)
    protected List<FailInfo> failInfos;

    /**
     * Gets the value of the failInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the failInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFailInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FailInfo }
     * 
     * 
     */
    public List<FailInfo> getFailInfos() {
        if (failInfos == null) {
            failInfos = new ArrayList<FailInfo>();
        }
        return this.failInfos;
    }

}
