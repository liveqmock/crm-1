
package com.deppon.erp.custAndOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for contract complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contract">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contractDepts" type="{http://service.cms.ws.deppon.com/}contractDept" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contractNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contractStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastupdateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contract", propOrder = {
    "contractDepts",
    "contractNum",
    "contractStatus",
    "custid",
    "lastupdateTime"
})
public class Contract {

    @XmlElement(nillable = true)
    protected List<ContractDept> contractDepts;
    protected String contractNum;
    protected String contractStatus;
    protected String custid;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date lastupdateTime;

    /**
     * Gets the value of the contractDepts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contractDepts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContractDepts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContractDept }
     * 
     * 
     */
    public List<ContractDept> getContractDepts() {
        if (contractDepts == null) {
            contractDepts = new ArrayList<ContractDept>();
        }
        return this.contractDepts;
    }

    /**
     * Gets the value of the contractNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractNum() {
        return contractNum;
    }

    /**
     * Sets the value of the contractNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractNum(String value) {
        this.contractNum = value;
    }

    /**
     * Gets the value of the contractStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractStatus() {
        return contractStatus;
    }

    /**
     * Sets the value of the contractStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractStatus(String value) {
        this.contractStatus = value;
    }

    /**
     * Gets the value of the custid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustid() {
        return custid;
    }

    /**
     * Sets the value of the custid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustid(String value) {
        this.custid = value;
    }

    /**
     * Gets the value of the lastupdateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getLastupdateTime() {
        return lastupdateTime;
    }

    /**
     * Sets the value of the lastupdateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastupdateTime(Date value) {
        this.lastupdateTime = value;
    }

}
