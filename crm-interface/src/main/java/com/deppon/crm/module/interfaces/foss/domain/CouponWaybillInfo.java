
package com.deppon.crm.module.interfaces.foss.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CouponWaybillInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="CouponWaybillInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="waybillNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="produceType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="totalAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="leaveMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leavePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="leaveDept" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="arrivedDept" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="leaveOutDept" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="arrivedOutDept" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="amountList" type="{http://www.deppon.com/crm/inteface/foss/domain}AmountInfo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CouponWaybillInfo", propOrder = {
    "waybillNumber",
    "orderNumber",
    "orderSource",
    "produceType",
    "totalAmount",
    "leaveMobile",
    "leavePhone",
    "custNumber",
    "leaveDept",
    "arrivedDept",
    "leaveOutDept",
    "arrivedOutDept",
    "amountList"
})
public class CouponWaybillInfo {

    @XmlElement(required = true)
    protected String waybillNumber;
    protected String orderNumber;
    protected String orderSource;
    @XmlElement(required = true)
    protected String produceType;
    @XmlElement(required = true)
    protected BigDecimal totalAmount;
    protected String leaveMobile;
    protected String leavePhone;
    @XmlElement(required = true)
    protected String custNumber;
    @XmlElement(required = true)
    protected String leaveDept;
    @XmlElement(required = true)
    protected String arrivedDept;
    @XmlElement(required = true)
    protected String leaveOutDept;
    @XmlElement(required = true)
    protected String arrivedOutDept;
    @XmlElement(required = true)
    protected List<AmountInfo> amountList;

    /**
     * ��ȡwaybillNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaybillNumber() {
        return waybillNumber;
    }

    /**
     * ����waybillNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaybillNumber(String value) {
        this.waybillNumber = value;
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
     * ��ȡorderSource���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderSource() {
        return orderSource;
    }

    /**
     * ����orderSource���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderSource(String value) {
        this.orderSource = value;
    }

    /**
     * ��ȡproduceType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProduceType() {
        return produceType;
    }

    /**
     * ����produceType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProduceType(String value) {
        this.produceType = value;
    }

    /**
     * ��ȡtotalAmount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * ����totalAmount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalAmount(BigDecimal value) {
        this.totalAmount = value;
    }

    /**
     * ��ȡleaveMobile���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeaveMobile() {
        return leaveMobile;
    }

    /**
     * ����leaveMobile���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeaveMobile(String value) {
        this.leaveMobile = value;
    }

    /**
     * ��ȡleavePhone���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeavePhone() {
        return leavePhone;
    }

    /**
     * ����leavePhone���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeavePhone(String value) {
        this.leavePhone = value;
    }

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
     * ��ȡleaveDept���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeaveDept() {
        return leaveDept;
    }

    /**
     * ����leaveDept���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeaveDept(String value) {
        this.leaveDept = value;
    }

    /**
     * ��ȡarrivedDept���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivedDept() {
        return arrivedDept;
    }

    /**
     * ����arrivedDept���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivedDept(String value) {
        this.arrivedDept = value;
    }

    /**
     * ��ȡleaveOutDept���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeaveOutDept() {
        return leaveOutDept;
    }

    /**
     * ����leaveOutDept���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeaveOutDept(String value) {
        this.leaveOutDept = value;
    }

    /**
     * ��ȡarrivedOutDept���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivedOutDept() {
        return arrivedOutDept;
    }

    /**
     * ����arrivedOutDept���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivedOutDept(String value) {
        this.arrivedOutDept = value;
    }

    /**
     * Gets the value of the amountList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amountList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmountList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AmountInfo }
     * 
     * 
     */
    public List<AmountInfo> getAmountList() {
        if (amountList == null) {
            amountList = new ArrayList<AmountInfo>();
        }
        return this.amountList;
    }

}
