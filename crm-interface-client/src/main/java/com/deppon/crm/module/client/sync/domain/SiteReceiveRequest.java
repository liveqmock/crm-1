
package com.deppon.crm.module.client.sync.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ��������
 * 
 * <p>siteReceiveRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="siteReceiveRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deptName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deptCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deptProvince" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deptCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isAB" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="deptArea" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deptAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descript" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactWay" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="leaveOutDept" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="leaveMiddleChange" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isUsed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="superiorArea" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isOpen" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="organisationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isArrived" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isLeave" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isSendToHome" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isGetBySelf" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isOutSend" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isCarTeam" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isHasPDA" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="standardCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="handType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "siteReceiveRequest", propOrder = {
    "deptName",
    "deptCode",
    "deptProvince",
    "deptCity",
    "isAB",
    "deptArea",
    "deptAddress",
    "descript",
    "contactWay",
    "leaveOutDept",
    "leaveMiddleChange",
    "isUsed",
    "superiorArea",
    "isOpen",
    "organisationId",
    "isArrived",
    "isLeave",
    "isSendToHome",
    "isGetBySelf",
    "isOutSend",
    "isCarTeam",
    "isHasPDA",
    "standardCode",
    "handType"
})
public class SiteReceiveRequest {

    @XmlElement(required = true)
    protected String deptName;
    @XmlElement(required = true)
    protected String deptCode;
    @XmlElement(required = true)
    protected String deptProvince;
    @XmlElement(required = true)
    protected String deptCity;
    protected boolean isAB;
    @XmlElement(required = true)
    protected String deptArea;
    @XmlElement(required = true)
    protected String deptAddress;
    @XmlElement(required = true)
    protected String descript;
    @XmlElement(required = true)
    protected String contactWay;
    @XmlElement(required = true)
    protected String leaveOutDept;
    @XmlElement(required = true)
    protected String leaveMiddleChange;
    protected boolean isUsed;
    @XmlElement(required = true)
    protected String superiorArea;
    protected boolean isOpen;
    @XmlElement(required = true)
    protected String organisationId;
    protected boolean isArrived;
    protected boolean isLeave;
    protected boolean isSendToHome;
    protected boolean isGetBySelf;
    protected boolean isOutSend;
    protected boolean isCarTeam;
    protected boolean isHasPDA;
    @XmlElement(required = true)
    protected String standardCode;
    @XmlElement(required = true)
    protected String handType;

    /**
     * ��ȡdeptName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * ����deptName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptName(String value) {
        this.deptName = value;
    }

    /**
     * ��ȡdeptCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * ����deptCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptCode(String value) {
        this.deptCode = value;
    }

    /**
     * ��ȡdeptProvince���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptProvince() {
        return deptProvince;
    }

    /**
     * ����deptProvince���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptProvince(String value) {
        this.deptProvince = value;
    }

    /**
     * ��ȡdeptCity���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptCity() {
        return deptCity;
    }

    /**
     * ����deptCity���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptCity(String value) {
        this.deptCity = value;
    }

    /**
     * ��ȡisAB���Ե�ֵ��
     * 
     */
    public boolean isIsAB() {
        return isAB;
    }

    /**
     * ����isAB���Ե�ֵ��
     * 
     */
    public void setIsAB(boolean value) {
        this.isAB = value;
    }

    /**
     * ��ȡdeptArea���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptArea() {
        return deptArea;
    }

    /**
     * ����deptArea���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptArea(String value) {
        this.deptArea = value;
    }

    /**
     * ��ȡdeptAddress���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptAddress() {
        return deptAddress;
    }

    /**
     * ����deptAddress���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptAddress(String value) {
        this.deptAddress = value;
    }

    /**
     * ��ȡdescript���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescript() {
        return descript;
    }

    /**
     * ����descript���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescript(String value) {
        this.descript = value;
    }

    /**
     * ��ȡcontactWay���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactWay() {
        return contactWay;
    }

    /**
     * ����contactWay���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactWay(String value) {
        this.contactWay = value;
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
     * ��ȡleaveMiddleChange���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeaveMiddleChange() {
        return leaveMiddleChange;
    }

    /**
     * ����leaveMiddleChange���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeaveMiddleChange(String value) {
        this.leaveMiddleChange = value;
    }

    /**
     * ��ȡisUsed���Ե�ֵ��
     * 
     */
    public boolean isIsUsed() {
        return isUsed;
    }

    /**
     * ����isUsed���Ե�ֵ��
     * 
     */
    public void setIsUsed(boolean value) {
        this.isUsed = value;
    }

    /**
     * ��ȡsuperiorArea���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuperiorArea() {
        return superiorArea;
    }

    /**
     * ����superiorArea���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuperiorArea(String value) {
        this.superiorArea = value;
    }

    /**
     * ��ȡisOpen���Ե�ֵ��
     * 
     */
    public boolean isIsOpen() {
        return isOpen;
    }

    /**
     * ����isOpen���Ե�ֵ��
     * 
     */
    public void setIsOpen(boolean value) {
        this.isOpen = value;
    }

    /**
     * ��ȡorganisationId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganisationId() {
        return organisationId;
    }

    /**
     * ����organisationId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganisationId(String value) {
        this.organisationId = value;
    }

    /**
     * ��ȡisArrived���Ե�ֵ��
     * 
     */
    public boolean isIsArrived() {
        return isArrived;
    }

    /**
     * ����isArrived���Ե�ֵ��
     * 
     */
    public void setIsArrived(boolean value) {
        this.isArrived = value;
    }

    /**
     * ��ȡisLeave���Ե�ֵ��
     * 
     */
    public boolean isIsLeave() {
        return isLeave;
    }

    /**
     * ����isLeave���Ե�ֵ��
     * 
     */
    public void setIsLeave(boolean value) {
        this.isLeave = value;
    }

    /**
     * ��ȡisSendToHome���Ե�ֵ��
     * 
     */
    public boolean isIsSendToHome() {
        return isSendToHome;
    }

    /**
     * ����isSendToHome���Ե�ֵ��
     * 
     */
    public void setIsSendToHome(boolean value) {
        this.isSendToHome = value;
    }

    /**
     * ��ȡisGetBySelf���Ե�ֵ��
     * 
     */
    public boolean isIsGetBySelf() {
        return isGetBySelf;
    }

    /**
     * ����isGetBySelf���Ե�ֵ��
     * 
     */
    public void setIsGetBySelf(boolean value) {
        this.isGetBySelf = value;
    }

    /**
     * ��ȡisOutSend���Ե�ֵ��
     * 
     */
    public boolean isIsOutSend() {
        return isOutSend;
    }

    /**
     * ����isOutSend���Ե�ֵ��
     * 
     */
    public void setIsOutSend(boolean value) {
        this.isOutSend = value;
    }

    /**
     * ��ȡisCarTeam���Ե�ֵ��
     * 
     */
    public boolean isIsCarTeam() {
        return isCarTeam;
    }

    /**
     * ����isCarTeam���Ե�ֵ��
     * 
     */
    public void setIsCarTeam(boolean value) {
        this.isCarTeam = value;
    }

    /**
     * ��ȡisHasPDA���Ե�ֵ��
     * 
     */
    public boolean isIsHasPDA() {
        return isHasPDA;
    }

    /**
     * ����isHasPDA���Ե�ֵ��
     * 
     */
    public void setIsHasPDA(boolean value) {
        this.isHasPDA = value;
    }

    /**
     * ��ȡstandardCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStandardCode() {
        return standardCode;
    }

    /**
     * ����standardCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStandardCode(String value) {
        this.standardCode = value;
    }

    /**
     * ��ȡhandType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandType() {
        return handType;
    }

    /**
     * ����handType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandType(String value) {
        this.handType = value;
    }

}
