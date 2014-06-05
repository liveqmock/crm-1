
package com.deppon.crm.module.interfaces.cc.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MarketingInfo complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="MarketingInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="planningTopic" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="marketingMode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="marketingType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recallId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="linkman" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="marketingPerson" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="marketingTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MarketingInfo", propOrder = {
    "planningTopic",
    "marketingMode",
    "marketingType",
    "recallId",
    "custId",
    "linkMan",
    "marketingPerson",
    "marketingTime"
})
public class MarketingInfo {

    @XmlElement(required = true)
    protected String planningTopic;
    @XmlElement(required = true)
    protected String marketingMode;
    @XmlElement(required = true)
    protected String marketingType;
    @XmlElement(required = true)
    protected String recallId;
    @XmlElement(required = true)
    protected String custId;
    @XmlElement(required = true)
    protected String linkMan;
    @XmlElement(required = true)
    protected String marketingPerson;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date marketingTime;

    /**
     * ��ȡplanningTopic���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPlanningTopic() {
        return planningTopic;
    }

    /**
     * ����planningTopic���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPlanningTopic(String value) {
        this.planningTopic = value;
    }

    /**
     * ��ȡmarketingMode���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMarketingMode() {
        return marketingMode;
    }

    /**
     * ����marketingMode���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMarketingMode(String value) {
        this.marketingMode = value;
    }

    /**
     * ��ȡmarketingType���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMarketingType() {
        return marketingType;
    }

    /**
     * ����marketingType���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMarketingType(String value) {
        this.marketingType = value;
    }

    /**
     * ��ȡrecallId���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRecallId() {
        return recallId;
    }

    /**
     * ����recallId���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRecallId(String value) {
        this.recallId = value;
    }

    /**
     * ��ȡcustId���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCustId() {
        return custId;
    }

    /**
     * ����custId���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCustId(String value) {
        this.custId = value;
    }



    public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	/**
     * ��ȡmarketingPerson���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMarketingPerson() {
        return marketingPerson;
    }

    /**
     * ����marketingPerson���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMarketingPerson(String value) {
        this.marketingPerson = value;
    }

    /**
     * ��ȡmarketingTime���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getMarketingTime() {
        return marketingTime;
    }

    /**
     * ����marketingTime���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setMarketingTime(Date value) {
        this.marketingTime = value;
    }

}
