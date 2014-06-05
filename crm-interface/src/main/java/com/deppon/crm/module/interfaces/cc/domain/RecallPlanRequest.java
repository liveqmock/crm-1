
package com.deppon.crm.module.interfaces.cc.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RecallPlanRequest complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="RecallPlanRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="custNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="planSubject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="beginTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="executeDept" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecallPlanRequest", propOrder = {
    "custNumber",
    "planSubject",
    "projectType",
    "beginTime",
    "endTime",
    "executeDept",
    "creator",
    "remark"
})
public class RecallPlanRequest {

    @XmlElement(required = true)
    protected String custNumber;
    @XmlElement(required = true)
    protected String planSubject;
    @XmlElement(required = true)
    protected String projectType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date beginTime;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date endTime;
    @XmlElement(required = true)
    protected String executeDept;
    @XmlElement(required = true)
    protected String creator;
    @XmlElement(required = true)
    protected String remark;

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
     * ��ȡplanSubject���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPlanSubject() {
        return planSubject;
    }

    /**
     * ����planSubject���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPlanSubject(String value) {
        this.planSubject = value;
    }

    /**
     * ��ȡbeginTime���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
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
     *     {@link XMLGregorianCalendar }
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
     *     {@link XMLGregorianCalendar }
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
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setEndTime(Date value) {
        this.endTime = value;
    }

    /**
     * ��ȡexecuteDept���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getExecuteDept() {
        return executeDept;
    }

    /**
     * ����executeDept���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setExecuteDept(String value) {
        this.executeDept = value;
    }

    /**
     * ��ȡcreator���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCreator() {
        return creator;
    }

    /**
     * ����creator���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCreator(String value) {
        this.creator = value;
    }

    /**
     * ��ȡremark���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * ����remark���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRemark(String value) {
        this.remark = value;
    }

	/**
	 * @return projectType : return the property projectType.
	 */
	public String getProjectType() {
		return projectType;
	}

	/**
	 * @param projectType : set the property projectType.
	 */
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

}
