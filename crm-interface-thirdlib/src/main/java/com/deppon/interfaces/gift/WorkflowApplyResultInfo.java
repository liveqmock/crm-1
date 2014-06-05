
package com.deppon.interfaces.gift;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for workflowApplyResultInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="workflowApplyResultInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="workflowNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="examineDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="exminePerson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exmineResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="exmaineSuggestion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workflowApplyResultInfo", propOrder = {
    "workflowNumber",
    "examineDate",
    "exminePerson",
    "exmineResult",
    "exmaineSuggestion"
})
public class WorkflowApplyResultInfo {

    protected String workflowNumber;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date examineDate;
    protected String exminePerson;
    protected boolean exmineResult;
    protected String exmaineSuggestion;

    /**
     * Gets the value of the workflowNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflowNumber() {
        return workflowNumber;
    }

    /**
     * Sets the value of the workflowNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflowNumber(String value) {
        this.workflowNumber = value;
    }

    /**
     * Gets the value of the examineDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getExamineDate() {
        return examineDate;
    }

    /**
     * Sets the value of the examineDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExamineDate(Date value) {
        this.examineDate = value;
    }

    /**
     * Gets the value of the exminePerson property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExminePerson() {
        return exminePerson;
    }

    /**
     * Sets the value of the exminePerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExminePerson(String value) {
        this.exminePerson = value;
    }

    /**
     * Gets the value of the exmineResult property.
     * 
     */
    public boolean isExmineResult() {
        return exmineResult;
    }

    /**
     * Sets the value of the exmineResult property.
     * 
     */
    public void setExmineResult(boolean value) {
        this.exmineResult = value;
    }

    /**
     * Gets the value of the exmaineSuggestion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExmaineSuggestion() {
        return exmaineSuggestion;
    }

    /**
     * Sets the value of the exmaineSuggestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExmaineSuggestion(String value) {
        this.exmaineSuggestion = value;
    }

}
