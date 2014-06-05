
package com.deppon.crm.module.client.esb.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ���ؽ��
 * 
 * <p>CustInfoSyncResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="CustInfoSyncResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="successCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="failCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="detail" type="{http://www.deppon.com/esb/inteface/domain/crm}ProcessDetail" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustInfoSyncResponse", propOrder = {
    "successCount",
    "failCount",
    "detail"
})
public class CustInfoSyncResponse {

    protected int successCount;
    protected int failCount;
    @XmlElement(required = true)
    protected List<ProcessDetail> detail;

    /**
     * ��ȡsuccessCount���Ե�ֵ��
     * 
     */
    public int getSuccessCount() {
        return successCount;
    }

    /**
     * ����successCount���Ե�ֵ��
     * 
     */
    public void setSuccessCount(int value) {
        this.successCount = value;
    }

    /**
     * ��ȡfailCount���Ե�ֵ��
     * 
     */
    public int getFailCount() {
        return failCount;
    }

    /**
     * ����failCount���Ե�ֵ��
     * 
     */
    public void setFailCount(int value) {
        this.failCount = value;
    }

    /**
     * Gets the value of the detail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProcessDetail }
     * 
     * 
     */
    public List<ProcessDetail> getDetail() {
        if (detail == null) {
            detail = new ArrayList<ProcessDetail>();
        }
        return this.detail;
    }

}
