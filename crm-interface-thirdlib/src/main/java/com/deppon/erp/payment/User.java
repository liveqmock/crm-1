
package com.deppon.erp.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for user complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="user">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.amsonline.ws.deppon.com/}coreBase">
 *       &lt;sequence>
 *         &lt;element name="agentUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="controlunitid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deforgunitid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errcount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="forbidden" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fsupplierid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invalidationdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isbizAdmin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ischangedpw" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isdelete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="islocked" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isregister" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lockDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loginauthorway" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mainroleid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="personid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pwdhisstr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pweffectiveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="securityid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = {
    "agentUser",
    "controlunitid",
    "customerid",
    "deforgunitid",
    "description",
    "effectiveDate",
    "errcount",
    "forbidden",
    "fsupplierid",
    "groupid",
    "invalidationdate",
    "isbizAdmin",
    "ischangedpw",
    "isdelete",
    "islocked",
    "isregister",
    "lockDate",
    "loginauthorway",
    "mainroleid",
    "name",
    "number",
    "password",
    "personid",
    "pwdhisstr",
    "pweffectiveDate",
    "securityid",
    "type"
})
public class User
    extends CoreBase
{

    protected String agentUser;
    protected String controlunitid;
    protected String customerid;
    protected String deforgunitid;
    protected String description;
    protected String effectiveDate;
    protected String errcount;
    protected String forbidden;
    protected String fsupplierid;
    protected String groupid;
    protected String invalidationdate;
    protected String isbizAdmin;
    protected String ischangedpw;
    protected String isdelete;
    protected String islocked;
    protected String isregister;
    protected String lockDate;
    protected String loginauthorway;
    protected String mainroleid;
    protected String name;
    protected String number;
    protected String password;
    protected String personid;
    protected String pwdhisstr;
    protected String pweffectiveDate;
    protected String securityid;
    protected String type;

    /**
     * Gets the value of the agentUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentUser() {
        return agentUser;
    }

    /**
     * Sets the value of the agentUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentUser(String value) {
        this.agentUser = value;
    }

    /**
     * Gets the value of the controlunitid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlunitid() {
        return controlunitid;
    }

    /**
     * Sets the value of the controlunitid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlunitid(String value) {
        this.controlunitid = value;
    }

    /**
     * Gets the value of the customerid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerid() {
        return customerid;
    }

    /**
     * Sets the value of the customerid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerid(String value) {
        this.customerid = value;
    }

    /**
     * Gets the value of the deforgunitid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeforgunitid() {
        return deforgunitid;
    }

    /**
     * Sets the value of the deforgunitid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeforgunitid(String value) {
        this.deforgunitid = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the effectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the value of the effectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectiveDate(String value) {
        this.effectiveDate = value;
    }

    /**
     * Gets the value of the errcount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrcount() {
        return errcount;
    }

    /**
     * Sets the value of the errcount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrcount(String value) {
        this.errcount = value;
    }

    /**
     * Gets the value of the forbidden property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForbidden() {
        return forbidden;
    }

    /**
     * Sets the value of the forbidden property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForbidden(String value) {
        this.forbidden = value;
    }

    /**
     * Gets the value of the fsupplierid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFsupplierid() {
        return fsupplierid;
    }

    /**
     * Sets the value of the fsupplierid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFsupplierid(String value) {
        this.fsupplierid = value;
    }

    /**
     * Gets the value of the groupid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupid() {
        return groupid;
    }

    /**
     * Sets the value of the groupid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupid(String value) {
        this.groupid = value;
    }

    /**
     * Gets the value of the invalidationdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvalidationdate() {
        return invalidationdate;
    }

    /**
     * Sets the value of the invalidationdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvalidationdate(String value) {
        this.invalidationdate = value;
    }

    /**
     * Gets the value of the isbizAdmin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsbizAdmin() {
        return isbizAdmin;
    }

    /**
     * Sets the value of the isbizAdmin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsbizAdmin(String value) {
        this.isbizAdmin = value;
    }

    /**
     * Gets the value of the ischangedpw property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIschangedpw() {
        return ischangedpw;
    }

    /**
     * Sets the value of the ischangedpw property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIschangedpw(String value) {
        this.ischangedpw = value;
    }

    /**
     * Gets the value of the isdelete property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsdelete() {
        return isdelete;
    }

    /**
     * Sets the value of the isdelete property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsdelete(String value) {
        this.isdelete = value;
    }

    /**
     * Gets the value of the islocked property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIslocked() {
        return islocked;
    }

    /**
     * Sets the value of the islocked property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIslocked(String value) {
        this.islocked = value;
    }

    /**
     * Gets the value of the isregister property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsregister() {
        return isregister;
    }

    /**
     * Sets the value of the isregister property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsregister(String value) {
        this.isregister = value;
    }

    /**
     * Gets the value of the lockDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLockDate() {
        return lockDate;
    }

    /**
     * Sets the value of the lockDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLockDate(String value) {
        this.lockDate = value;
    }

    /**
     * Gets the value of the loginauthorway property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginauthorway() {
        return loginauthorway;
    }

    /**
     * Sets the value of the loginauthorway property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginauthorway(String value) {
        this.loginauthorway = value;
    }

    /**
     * Gets the value of the mainroleid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainroleid() {
        return mainroleid;
    }

    /**
     * Sets the value of the mainroleid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainroleid(String value) {
        this.mainroleid = value;
    }

    /**
     * Gets the value of the name property.
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
     * Sets the value of the name property.
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
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the personid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonid() {
        return personid;
    }

    /**
     * Sets the value of the personid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonid(String value) {
        this.personid = value;
    }

    /**
     * Gets the value of the pwdhisstr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPwdhisstr() {
        return pwdhisstr;
    }

    /**
     * Sets the value of the pwdhisstr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPwdhisstr(String value) {
        this.pwdhisstr = value;
    }

    /**
     * Gets the value of the pweffectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPweffectiveDate() {
        return pweffectiveDate;
    }

    /**
     * Sets the value of the pweffectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPweffectiveDate(String value) {
        this.pweffectiveDate = value;
    }

    /**
     * Gets the value of the securityid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityid() {
        return securityid;
    }

    /**
     * Sets the value of the securityid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityid(String value) {
        this.securityid = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
