
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
 * <p>Questionnaire complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="Questionnaire">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usingRange" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="useTimes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="instructions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastModifyTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="modifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="questionDetails" type="{http://www.deppon.com/crm/inteface/cc/domain}QuestionDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Questionnaire", propOrder = {
    "number",
    "name",
    "usingRange",
    "effectiveDate",
    "expiryDate",
    "useTimes",
    "instructions",
    "state",
    "createTime",
    "creator",
    "lastModifyTime",
    "modifier",
    "questionDetails"
})
public class Questionnaire {

    @XmlElement(required = true)
    protected String number;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String usingRange;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date effectiveDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date expiryDate;
    protected int useTimes;
    @XmlElement(required = true)
    protected String instructions;
    @XmlElement(required = true)
    protected String state;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date createTime;
    @XmlElement(required = true)
    protected String creator;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date lastModifyTime;
    @XmlElement(required = true)
    protected String modifier;
    protected List<QuestionDetail> questionDetails;

    /**
     * ��ȡnumber���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNumber() {
        return number;
    }

    /**
     * ����number���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNumber(String value) {
        this.number = value;
    }

    /**
     * ��ȡname���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * ����name���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * ��ȡusingRange���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUsingRange() {
        return usingRange;
    }

    /**
     * ����usingRange���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUsingRange(String value) {
        this.usingRange = value;
    }

    /**
     * ��ȡeffectiveDate���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * ����effectiveDate���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setEffectiveDate(Date value) {
        this.effectiveDate = value;
    }

    /**
     * ��ȡexpiryDate���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * ����expiryDate���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setExpiryDate(Date value) {
        this.expiryDate = value;
    }

    /**
     * ��ȡuseTimes���Ե�ֵ��
     *
     */
    public int getUseTimes() {
        return useTimes;
    }

    /**
     * ����useTimes���Ե�ֵ��
     *
     */
    public void setUseTimes(int value) {
        this.useTimes = value;
    }

    /**
     * ��ȡinstructions���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * ����instructions���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setInstructions(String value) {
        this.instructions = value;
    }

    /**
     * ��ȡstate���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getState() {
        return state;
    }

    /**
     * ����state���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setState(String value) {
        this.state = value;
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
     * ��ȡlastModifyTime���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * ����lastModifyTime���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setLastModifyTime(Date value) {
        this.lastModifyTime = value;
    }

    /**
     * ��ȡmodifier���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * ����modifier���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setModifier(String value) {
        this.modifier = value;
    }

    /**
     * Gets the value of the questionDetails property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the questionDetails property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuestionDetails().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QuestionDetail }
     *
     *
     */
    public List<QuestionDetail> getQuestionDetails() {
        if (questionDetails == null) {
            questionDetails = new ArrayList<QuestionDetail>();
        }
        return this.questionDetails;
    }

}
