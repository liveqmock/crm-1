
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
 * <p>OrigCustSyncResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="OrigCustSyncResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="successCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="failCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="detail" type="{http://www.deppon.com/esb/inteface/domain/crm}OrigCustSyncRequestProcessDetail" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrigCustSyncResponse", propOrder = {
    "successCount",
    "failCount",
    "detail"
})
public class OrigCustSyncResponse {
	// 成功总数
    protected int successCount;
    // 失败总数
    protected int failCount;
    // 处理明细
    @XmlElement(required = true)
    protected List<OrigCustSyncRequestProcessDetail> detail;

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
     * {@link OrigCustSyncRequestProcessDetail }
     * 
     * 
     */
    public List<OrigCustSyncRequestProcessDetail> getDetail() {
        if (detail == null) {
            detail = new ArrayList<OrigCustSyncRequestProcessDetail>();
        }
        return this.detail;
    }

}
