
package com.deppon.erp.custAndOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for exceptionCust complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="exceptionCust">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contracts" type="{http://service.cms.ws.deppon.com/}contract" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="creditrated" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="crmcancal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crmid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="linkMans" type="{http://service.cms.ws.deppon.com/}linkMan" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="openBanks" type="{http://service.cms.ws.deppon.com/}openBank" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "exceptionCust", propOrder = {
    "contracts",
    "creditrated",
    "crmcancal",
    "crmid",
    "linkMans",
    "number",
    "openBanks"
})
public class ExceptionCust {

    @XmlElement(nillable = true)
    protected List<Contract> contracts;
    protected BigDecimal creditrated;
    protected String crmcancal;
    protected String crmid;
    @XmlElement(nillable = true)
    protected List<LinkMan> linkMans;
    protected String number;
    @XmlElement(nillable = true)
    protected List<OpenBank> openBanks;

    /**
     * Gets the value of the contracts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contracts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContracts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Contract }
     * 
     * 
     */
    public List<Contract> getContracts() {
        if (contracts == null) {
            contracts = new ArrayList<Contract>();
        }
        return this.contracts;
    }

    /**
     * Gets the value of the creditrated property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCreditrated() {
        return creditrated;
    }

    /**
     * Sets the value of the creditrated property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCreditrated(BigDecimal value) {
        this.creditrated = value;
    }

    /**
     * Gets the value of the crmcancal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmcancal() {
        return crmcancal;
    }

    /**
     * Sets the value of the crmcancal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmcancal(String value) {
        this.crmcancal = value;
    }

    /**
     * Gets the value of the crmid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmid() {
        return crmid;
    }

    /**
     * Sets the value of the crmid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmid(String value) {
        this.crmid = value;
    }

    /**
     * Gets the value of the linkMans property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkMans property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkMans().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinkMan }
     * 
     * 
     */
    public List<LinkMan> getLinkMans() {
        if (linkMans == null) {
            linkMans = new ArrayList<LinkMan>();
        }
        return this.linkMans;
    }

    /**
     * Gets the value of the number property.
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
     * Sets the value of the number property.
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
     * Gets the value of the openBanks property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the openBanks property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOpenBanks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OpenBank }
     * 
     * 
     */
    public List<OpenBank> getOpenBanks() {
        if (openBanks == null) {
            openBanks = new ArrayList<OpenBank>();
        }
        return this.openBanks;
    }

}
