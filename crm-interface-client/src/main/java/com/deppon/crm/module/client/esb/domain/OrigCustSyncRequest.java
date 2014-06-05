
package com.deppon.crm.module.client.esb.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * ͬ��ɢ����Ϣ
 * 
 * <p>OrigCustSyncRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="OrigCustSyncRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="scatterCustInfo" type="{http://www.deppon.com/esb/inteface/domain/crm}ScatterCustInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrigCustSyncRequest", propOrder = {
	    "scatterCustInfo"
	})
/**
 * @作者：罗典
 * @描述：同步散客信息
 * @时间：2012-11-7
 * */
public class OrigCustSyncRequest {
	//散客信息
    @XmlElement(required = true)
    protected ScatterCustInfo scatterCustInfo;

    /**
     * ��ȡscatterCustInfo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ScatterCustInfo }
     *     
     */
    public ScatterCustInfo getScatterCustInfo() {
        return scatterCustInfo;
    }

    /**
     * ����scatterCustInfo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ScatterCustInfo }
     *     
     */
    public void setScatterCustInfo(ScatterCustInfo value) {
        this.scatterCustInfo = value;
    }

}
