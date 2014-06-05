
package com.deppon.crm.module.interfaces.cc.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MarketingInfoQueryDetailResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="MarketingInfoQueryDetailResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerNeed" type="{http://www.deppon.com/crm/inteface/cc/domain}CustomerNeed" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="GoodsPotential" type="{http://www.deppon.com/crm/inteface/cc/domain}GoodsPotential" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="questionnaire" type="{http://www.deppon.com/crm/inteface/cc/domain}Questionnaire" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MarketingInfoQueryDetailResponse", propOrder = {
    "customerNeed",
    "goodsPotential",
    "questionnaire"
})
public class MarketingInfoQueryDetailResponse {

    protected List<CustomerNeed> customerNeed;
    @XmlElement(name = "GoodsPotential")
    protected List<GoodsPotential> goodsPotential;
    protected List<Questionnaire> questionnaire;

    /**
     * Gets the value of the customerNeed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customerNeed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomerNeed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerNeed }
     * 
     * 
     */
    public List<CustomerNeed> getCustomerNeed() {
        if (customerNeed == null) {
            customerNeed = new ArrayList<CustomerNeed>();
        }
        return this.customerNeed;
    }

    /**
     * Gets the value of the goodsPotential property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the goodsPotential property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGoodsPotential().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GoodsPotential }
     * 
     * 
     */
    public List<GoodsPotential> getGoodsPotential() {
        if (goodsPotential == null) {
            goodsPotential = new ArrayList<GoodsPotential>();
        }
        return this.goodsPotential;
    }

    /**
     * Gets the value of the questionnaire property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the questionnaire property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuestionnaire().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Questionnaire }
     * 
     * 
     */
    public List<Questionnaire> getQuestionnaire() {
        if (questionnaire == null) {
            questionnaire = new ArrayList<Questionnaire>();
        }
        return this.questionnaire;
    }

}
