
package com.deppon.crm.module.client.sync.domain;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>OrderRightInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="OrderRightInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderTeam" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="department" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operateType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderRightInfo", propOrder = {
    "orderTeam",
    "department",
    "operateType",
    "operateTime"
})
public class OrderRightInfo {

    @XmlElement(required = true)
    protected String orderTeam;
    @XmlElement(required = true)
    protected String department;
    @XmlElement(required = true)
    protected String operateType;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date operateTime;

    /**
     * ��ȡorderTeam���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderTeam() {
        return orderTeam;
    }

    /**
     * ����orderTeam���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderTeam(String value) {
        this.orderTeam = value;
    }

    /**
     * ��ȡdepartment���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartment() {
        return department;
    }

    /**
     * ����department���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartment(String value) {
        this.department = value;
    }

    /**
     * ��ȡoperateType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * ����operateType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperateType(String value) {
        this.operateType = value;
    }

    /**
     * ��ȡoperateTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * ����operateTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperateTime(Date value) {
        this.operateTime = value;
    }

}
