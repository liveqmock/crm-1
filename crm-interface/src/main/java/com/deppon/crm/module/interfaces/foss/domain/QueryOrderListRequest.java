
package com.deppon.crm.module.interfaces.foss.domain;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * �����ͻ�
 * 
 * <p>queryOrderListRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="queryOrderListRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beginTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="acceptStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperCust" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperLinkman" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipperPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pageNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryOrderListRequest", propOrder = {
    "orderType",
    "orderNumber",
    "beginTime",
    "endTime",
    "acceptStatus",
    "shipperCust",
    "shipperLinkman",
    "shipperMobile",
    "shipperPhone",
    "deptCode",
    "pageNum",
    "pageSize",
    "transType",
	 "waybillNumber"
})
public class QueryOrderListRequest {

    protected String orderType;
    protected String orderNumber;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date beginTime;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date endTime;
    protected String acceptStatus;
    protected String shipperCust;
    protected String shipperLinkman;
    protected String shipperMobile;
    protected String shipperPhone;
    protected String deptCode;
    protected int pageNum;
    protected int pageSize;
    protected String transType;
	protected String waybillNumber;
    /**
     * ��ȡorderType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * ����orderType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderType(String value) {
        this.orderType = value;
    }

    public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
     * ��ȡorderNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * ����orderNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderNumber(String value) {
        this.orderNumber = value;
    }

    /**
     * ��ȡbeginTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * ����beginTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeginTime(Date value) {
        this.beginTime = value;
    }

    /**
     * ��ȡendTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * ����endTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTime(Date value) {
        this.endTime = value;
    }

    /**
     * ��ȡacceptStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptStatus() {
        return acceptStatus;
    }

    /**
     * ����acceptStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptStatus(String value) {
        this.acceptStatus = value;
    }

    /**
     * ��ȡshipperCust���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperCust() {
        return shipperCust;
    }

    /**
     * ����shipperCust���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperCust(String value) {
        this.shipperCust = value;
    }

    /**
     * ��ȡshipperLinkman���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperLinkman() {
        return shipperLinkman;
    }

    /**
     * ����shipperLinkman���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperLinkman(String value) {
        this.shipperLinkman = value;
    }

    /**
     * ��ȡshipperMobile���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperMobile() {
        return shipperMobile;
    }

    /**
     * ����shipperMobile���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperMobile(String value) {
        this.shipperMobile = value;
    }

    /**
     * ��ȡshipperPhone���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperPhone() {
        return shipperPhone;
    }

    /**
     * ����shipperPhone���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperPhone(String value) {
        this.shipperPhone = value;
    }

    /**
     * ��ȡpageNum���Ե�ֵ��
     * 
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * ����pageNum���Ե�ֵ��
     * 
     */
    public void setPageNum(int value) {
        this.pageNum = value; 
    }

    /**
     * ��ȡpageSize���Ե�ֵ��
     * 
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * ����pageSize���Ե�ֵ��
     * 
     */
    public void setPageSize(int value) {
        this.pageSize = value;
    }

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

   

}
