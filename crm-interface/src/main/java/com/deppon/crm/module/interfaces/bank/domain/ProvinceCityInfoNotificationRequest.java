
package com.deppon.crm.module.interfaces.bank.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ProvinceCityInfoNotificationRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ProvinceCityInfoNotificationRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProvinceCityInfo" type="{http://www.deppon.com/esb/inteface/domain/payment}ProvinceCityInfo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProvinceCityInfoNotificationRequest", propOrder = {
    "provinceCityInfo"
})
/**
 * @作者: 罗典
 * @时间：2012-12-25下午3:20:50
 * @描述：银行省份城市信息请求参数
 */
public class ProvinceCityInfoNotificationRequest {

    @XmlElement(name = "ProvinceCityInfo", required = true)
    protected List<ProvinceCityInfo> provinceCityInfo;

    /**
     * Gets the value of the provinceCityInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the provinceCityInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProvinceCityInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProvinceCityInfo }
     * 
     * 
     */
    public List<ProvinceCityInfo> getProvinceCityInfo() {
        if (provinceCityInfo == null) {
            provinceCityInfo = new ArrayList<ProvinceCityInfo>();
        }
        return this.provinceCityInfo;
    }

}
