
package com.deppon.foss.crm;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * ��ݿͻ�������ʱ���ȡ�˵���Ϣ
 *
 * <p>FossQueryAcctinfoRequest complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="FossQueryAcctinfoRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deliveryCustomerCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="currentPage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FossQueryAcctinfoRequest", propOrder = {
    "deliveryCustomerCode",
    "startDate",
    "endDate",
    "pageSize",
    "currentPage"
})
public class FossQueryAcctinfoRequest {

    @XmlElement(required = true)
    protected String deliveryCustomerCode;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date startDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date endDate;
    protected int pageSize;
    protected int currentPage;

    /**
     * ��ȡdeliveryCustomerCode���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDeliveryCustomerCode() {
        return deliveryCustomerCode;
    }

    /**
     * ����deliveryCustomerCode���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDeliveryCustomerCode(String value) {
        this.deliveryCustomerCode = value;
    }

    /**
     * ��ȡstartDate���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * ����startDate���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setStartDate(Date value) {
        this.startDate = value;
    }

    /**
     * ��ȡendDate���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * ����endDate���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setEndDate(Date value) {
        this.endDate = value;
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

    /**
     * ��ȡcurrentPage���Ե�ֵ��
     *
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * ����currentPage���Ե�ֵ��
     *
     */
    public void setCurrentPage(int value) {
        this.currentPage = value;
    }

}
