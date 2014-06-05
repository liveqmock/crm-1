
package com.deppon.crm.module.interfaces.foss.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * �ͻ�������
 * 
 * <p>ReceiveCreditUsedResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ReceiveCreditUsedResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CustCreditAmountList" type="{http://www.deppon.com/crm/inteface/foss/domain}CustCreditAmountInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReceiveCreditUsedResponse", propOrder = {
    "custCreditAmountList"
})
public class ReceiveCreditUsedResponse {

    @XmlElement(name = "CustCreditAmountList", required = true)
    protected CustCreditAmountInfo custCreditAmountList;

    /**
     * ��ȡcustCreditAmountList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link CustCreditAmountInfo }
     *     
     */
    public CustCreditAmountInfo getCustCreditAmountList() {
        return custCreditAmountList;
    }

    /**
     * ����custCreditAmountList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link CustCreditAmountInfo }
     *     
     */
    public void setCustCreditAmountList(CustCreditAmountInfo value) {
        this.custCreditAmountList = value;
    }

}
