
package com.deppon.crm.module.interfaces.cc.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>UpdateCallCenterCustomerRequest complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="UpdateCallCenterCustomerRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="custNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ifBigCustomer" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="custName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custDegree" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stdeptcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ifvalid" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="lastUpdateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="externalSysID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="linkmanInfos" type="{http://www.deppon.com/crm/inteface/cc/domain}LinkmanInfoUpdate" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateCallCenterCustomerRequest", propOrder = {
    "custNumber",
    "custType",
    "ifBigCustomer",
    "custName",
    "custDegree",
    "stdeptcode",
    "ifvalid",
    "createTime",
    "lastUpdateTime",
    "pickAddress",
    "custCategory",
    "externalSysID",
    "linkmanInfos"
})
public class UpdateCallCenterCustomerRequest {

    @XmlElement(required = true)
    protected String custNumber;
    @XmlElement(required = true)
    protected String custType;
    protected boolean ifBigCustomer;
    @XmlElement(required = true)
    protected String custName;
    @XmlElement(required = true)
    protected String custDegree;
    @XmlElement(required = true)
    protected String stdeptcode;
    protected boolean ifvalid;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date createTime;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date lastUpdateTime;
    protected String pickAddress;
    protected String custCategory;
    @XmlElement(required = true)
    protected String externalSysID;
    protected List<LinkmanInfoUpdate> linkmanInfos;

    /**
     * ��ȡcustNumber���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCustNumber() {
        return custNumber;
    }

    /**
     * ����custNumber���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCustNumber(String value) {
        this.custNumber = value;
    }

    /**
     * ��ȡcustType���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCustType() {
        return custType;
    }

    /**
     * ����custType���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCustType(String value) {
        this.custType = value;
    }

    /**
     * ��ȡifBigCustomer���Ե�ֵ��
     *
     */
    public boolean isIfBigCustomer() {
        return ifBigCustomer;
    }

    /**
     * ����ifBigCustomer���Ե�ֵ��
     *
     */
    public void setIfBigCustomer(boolean value) {
        this.ifBigCustomer = value;
    }

    /**
     * ��ȡcustName���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCustName() {
        return custName;
    }

    /**
     * ����custName���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCustName(String value) {
        this.custName = value;
    }

    /**
     * ��ȡcustDegree���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCustDegree() {
        return custDegree;
    }

    /**
     * ����custDegree���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCustDegree(String value) {
        this.custDegree = value;
    }

    /**
     * ��ȡstdeptcode���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStdeptcode() {
        return stdeptcode;
    }

    /**
     * ����stdeptcode���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStdeptcode(String value) {
        this.stdeptcode = value;
    }

    /**
     * ��ȡifvalid���Ե�ֵ��
     *
     */
    public boolean isIfvalid() {
        return ifvalid;
    }

    /**
     * ����ifvalid���Ե�ֵ��
     *
     */
    public void setIfvalid(boolean value) {
        this.ifvalid = value;
    }

    /**
     * ��ȡcreateTime���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * ����createTime���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setCreateTime(Date value) {
        this.createTime = value;
    }

    /**
     * ��ȡlastUpdateTime���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * ����lastUpdateTime���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setLastUpdateTime(Date value) {
        this.lastUpdateTime = value;
    }

    /**
     * ��ȡexternalSysID���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getExternalSysID() {
        return externalSysID;
    }

    /**
     * ����externalSysID���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setExternalSysID(String value) {
        this.externalSysID = value;
    }



    public String getPickAddress() {
		return pickAddress;
	}

	public void setPickAddress(String pickAddress) {
		this.pickAddress = pickAddress;
	}

	public String getCustCategory() {
		return custCategory;
	}

	public void setCustCategory(String custCategory) {
		this.custCategory = custCategory;
	}

	/**
     * Gets the value of the linkmanInfos property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkmanInfos property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkmanInfos().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinkmanInfoUpdate }
     *
     *
     */
    public List<LinkmanInfoUpdate> getLinkmanInfos() {
        if (linkmanInfos == null) {
            linkmanInfos = new ArrayList<LinkmanInfoUpdate>();
        }
        return this.linkmanInfos;
    }

}
