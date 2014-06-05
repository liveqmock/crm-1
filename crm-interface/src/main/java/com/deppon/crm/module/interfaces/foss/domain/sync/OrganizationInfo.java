
package com.deppon.crm.module.interfaces.foss.domain.sync;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>OrganizationInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="OrganizationInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orgCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pinYin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unifiedCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leader" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="principalNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentOrgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentOrgUnifiedCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subsidiaryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="costCenterCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="beginTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="division" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="divisionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bigRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smallRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptArea" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="provCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesDepartment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transferCenter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="doAirDispatch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transDepartment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dispatchTeam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billingGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transTeam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isDeliverSchedule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isArrangeGoods" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliverOutfield" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arrangeOutfield" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arrangeBizType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="modifyTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifyUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="airDispatch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entityFinance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depCoordinate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depTelephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isEntityFinance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgSimpleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fullPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="versionNo" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="uumsId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uumsParentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isLeaf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayOrder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptAttribute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="canceledSystems" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ehrDeptCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countryRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgEnSimple" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uumsIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentOrgCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganizationInfo", namespace = "http://www.deppon.com/ows/inteface/domain/", propOrder = {
    "id",
    "orgCode",
    "orgName",
    "pinYin",
    "unifiedCode",
    "leader",
    "principalNo",
    "parentOrgName",
    "parentOrgUnifiedCode",
    "subsidiaryCode",
    "costCenterCode",
    "status",
    "endTime",
    "beginTime",
    "division",
    "divisionCode",
    "bigRegion",
    "smallRegion",
    "address",
    "deptArea",
    "provCode",
    "cityCode",
    "countyCode",
    "salesDepartment",
    "transferCenter",
    "doAirDispatch",
    "transDepartment",
    "dispatchTeam",
    "billingGroup",
    "transTeam",
    "isDeliverSchedule",
    "isArrangeGoods",
    "deliverOutfield",
    "arrangeOutfield",
    "arrangeBizType",
    "createTime",
    "modifyTime",
    "active",
    "createUserCode",
    "modifyUserCode",
    "airDispatch",
    "entityFinance",
    "depCoordinate",
    "depTelephone",
    "depFax",
    "isEntityFinance",
    "orgSimpleName",
    "fullPath",
    "versionNo",
    "uumsId",
    "uumsParentId",
    "isLeaf",
    "displayOrder",
    "deptLevel",
    "deptDesc",
    "areaCode",
    "deptEmail",
    "zipCode",
    "deptAttribute",
    "canceledSystems",
    "ehrDeptCode",
    "countryRegion",
    "orgEnSimple",
    "uumsIds",
    "parentOrgCode",
    "isExpressRegion",
    "isVirualLading"
})
public class OrganizationInfo {

    @XmlElement(required = true)
    protected String id;
    protected String orgCode;
    protected String orgName;
    protected String pinYin;
    protected String unifiedCode;
    protected String leader;
    protected String principalNo;
    protected String parentOrgName;
    protected String parentOrgUnifiedCode;
    protected String subsidiaryCode;
    protected String costCenterCode;
    protected String status;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar beginTime;
    protected String division;
    protected String divisionCode;
    protected String bigRegion;
    protected String smallRegion;
    protected String isExpressRegion;
    protected String address;
    protected Double deptArea;
    protected String provCode;
    protected String cityCode;
    protected String countyCode;
    protected String salesDepartment;
    protected String transferCenter;
    protected String doAirDispatch;
    protected String transDepartment;
    protected String dispatchTeam;
    protected String billingGroup;
    protected String transTeam;
    protected String isDeliverSchedule;
    protected String isArrangeGoods;
    protected String deliverOutfield;
    protected String arrangeOutfield;
    protected String arrangeBizType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar modifyTime;
    protected String active;
    protected String createUserCode;
    protected String modifyUserCode;
    protected String airDispatch;
    protected String entityFinance;
    protected String depCoordinate;
    protected String depTelephone;
    protected String depFax;
    protected String isEntityFinance;
    protected String orgSimpleName;
    protected String fullPath;
    protected Double versionNo;
    protected String uumsId;
    protected String uumsParentId;
    protected String isLeaf;
    protected String displayOrder;
    protected String deptLevel;
    protected String deptDesc;
    protected String areaCode;
    protected String deptEmail;
    protected String zipCode;
    protected String deptAttribute;
    protected String canceledSystems;
    protected String ehrDeptCode;
    protected String countryRegion;
    protected String orgEnSimple;
    protected String uumsIds;
    protected String parentOrgCode;
    protected String isVirualLading;
    
    //是否快递大区
    public String getIsExpressRegion() {
		return isExpressRegion;
	}

	public void setIsExpressRegion(String isExpressRegion) {
		this.isExpressRegion = isExpressRegion;
	}

	//是否虚拟网点（Y:是，N：否）
	public String getIsVirualLading() {
		return isVirualLading;
	}

	public void setIsVirualLading(String isVirualLading) {
		this.isVirualLading = isVirualLading;
	}
	
	/**
     * ��ȡid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * ����id���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * ��ȡorgCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * ����orgCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgCode(String value) {
        this.orgCode = value;
    }

    /**
     * ��ȡorgName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * ����orgName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgName(String value) {
        this.orgName = value;
    }

    /**
     * ��ȡpinYin���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPinYin() {
        return pinYin;
    }

    /**
     * ����pinYin���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPinYin(String value) {
        this.pinYin = value;
    }

    /**
     * ��ȡunifiedCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnifiedCode() {
        return unifiedCode;
    }

    /**
     * ����unifiedCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnifiedCode(String value) {
        this.unifiedCode = value;
    }

    /**
     * ��ȡleader���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeader() {
        return leader;
    }

    /**
     * ����leader���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeader(String value) {
        this.leader = value;
    }

    /**
     * ��ȡprincipalNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrincipalNo() {
        return principalNo;
    }

    /**
     * ����principalNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrincipalNo(String value) {
        this.principalNo = value;
    }

    /**
     * ��ȡparentOrgName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentOrgName() {
        return parentOrgName;
    }

    /**
     * ����parentOrgName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentOrgName(String value) {
        this.parentOrgName = value;
    }

    /**
     * ��ȡparentOrgUnifiedCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentOrgUnifiedCode() {
        return parentOrgUnifiedCode;
    }

    /**
     * ����parentOrgUnifiedCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentOrgUnifiedCode(String value) {
        this.parentOrgUnifiedCode = value;
    }

    /**
     * ��ȡsubsidiaryCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubsidiaryCode() {
        return subsidiaryCode;
    }

    /**
     * ����subsidiaryCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubsidiaryCode(String value) {
        this.subsidiaryCode = value;
    }

    /**
     * ��ȡcostCenterCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCostCenterCode() {
        return costCenterCode;
    }

    /**
     * ����costCenterCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCostCenterCode(String value) {
        this.costCenterCode = value;
    }

    /**
     * ��ȡstatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * ����status���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * ��ȡendTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTime() {
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
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

    /**
     * ��ȡbeginTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBeginTime() {
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
    public void setBeginTime(XMLGregorianCalendar value) {
        this.beginTime = value;
    }

    /**
     * ��ȡdivision���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDivision() {
        return division;
    }

    /**
     * ����division���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDivision(String value) {
        this.division = value;
    }

    /**
     * ��ȡdivisionCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDivisionCode() {
        return divisionCode;
    }

    /**
     * ����divisionCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDivisionCode(String value) {
        this.divisionCode = value;
    }

    /**
     * ��ȡbigRegion���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBigRegion() {
        return bigRegion;
    }

    /**
     * ����bigRegion���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBigRegion(String value) {
        this.bigRegion = value;
    }

    /**
     * ��ȡsmallRegion���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmallRegion() {
        return smallRegion;
    }

    /**
     * ����smallRegion���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmallRegion(String value) {
        this.smallRegion = value;
    }

    /**
     * ��ȡaddress���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * ����address���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * ��ȡdeptArea���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDeptArea() {
        return deptArea;
    }

    /**
     * ����deptArea���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDeptArea(Double value) {
        this.deptArea = value;
    }

    /**
     * ��ȡprovCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvCode() {
        return provCode;
    }

    /**
     * ����provCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvCode(String value) {
        this.provCode = value;
    }

    /**
     * ��ȡcityCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * ����cityCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityCode(String value) {
        this.cityCode = value;
    }

    /**
     * ��ȡcountyCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountyCode() {
        return countyCode;
    }

    /**
     * ����countyCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountyCode(String value) {
        this.countyCode = value;
    }

    /**
     * ��ȡsalesDepartment���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesDepartment() {
        return salesDepartment;
    }

    /**
     * ����salesDepartment���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesDepartment(String value) {
        this.salesDepartment = value;
    }

    /**
     * ��ȡtransferCenter���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransferCenter() {
        return transferCenter;
    }

    /**
     * ����transferCenter���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransferCenter(String value) {
        this.transferCenter = value;
    }

    /**
     * ��ȡdoAirDispatch���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoAirDispatch() {
        return doAirDispatch;
    }

    /**
     * ����doAirDispatch���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoAirDispatch(String value) {
        this.doAirDispatch = value;
    }

    /**
     * ��ȡtransDepartment���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransDepartment() {
        return transDepartment;
    }

    /**
     * ����transDepartment���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransDepartment(String value) {
        this.transDepartment = value;
    }

    /**
     * ��ȡdispatchTeam���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispatchTeam() {
        return dispatchTeam;
    }

    /**
     * ����dispatchTeam���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispatchTeam(String value) {
        this.dispatchTeam = value;
    }

    /**
     * ��ȡbillingGroup���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingGroup() {
        return billingGroup;
    }

    /**
     * ����billingGroup���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingGroup(String value) {
        this.billingGroup = value;
    }

    /**
     * ��ȡtransTeam���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransTeam() {
        return transTeam;
    }

    /**
     * ����transTeam���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransTeam(String value) {
        this.transTeam = value;
    }

    /**
     * ��ȡisDeliverSchedule���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsDeliverSchedule() {
        return isDeliverSchedule;
    }

    /**
     * ����isDeliverSchedule���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsDeliverSchedule(String value) {
        this.isDeliverSchedule = value;
    }

    /**
     * ��ȡisArrangeGoods���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsArrangeGoods() {
        return isArrangeGoods;
    }

    /**
     * ����isArrangeGoods���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsArrangeGoods(String value) {
        this.isArrangeGoods = value;
    }

    /**
     * ��ȡdeliverOutfield���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliverOutfield() {
        return deliverOutfield;
    }

    /**
     * ����deliverOutfield���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliverOutfield(String value) {
        this.deliverOutfield = value;
    }

    /**
     * ��ȡarrangeOutfield���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrangeOutfield() {
        return arrangeOutfield;
    }

    /**
     * ����arrangeOutfield���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrangeOutfield(String value) {
        this.arrangeOutfield = value;
    }

    /**
     * ��ȡarrangeBizType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrangeBizType() {
        return arrangeBizType;
    }

    /**
     * ����arrangeBizType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrangeBizType(String value) {
        this.arrangeBizType = value;
    }

    /**
     * ��ȡcreateTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateTime() {
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
    public void setCreateTime(XMLGregorianCalendar value) {
        this.createTime = value;
    }

    /**
     * ��ȡmodifyTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getModifyTime() {
        return modifyTime;
    }

    /**
     * ����modifyTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setModifyTime(XMLGregorianCalendar value) {
        this.modifyTime = value;
    }

    /**
     * ��ȡactive���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActive() {
        return active;
    }

    /**
     * ����active���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActive(String value) {
        this.active = value;
    }

    /**
     * ��ȡcreateUserCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateUserCode() {
        return createUserCode;
    }

    /**
     * ����createUserCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateUserCode(String value) {
        this.createUserCode = value;
    }

    /**
     * ��ȡmodifyUserCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifyUserCode() {
        return modifyUserCode;
    }

    /**
     * ����modifyUserCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifyUserCode(String value) {
        this.modifyUserCode = value;
    }

    /**
     * ��ȡairDispatch���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirDispatch() {
        return airDispatch;
    }

    /**
     * ����airDispatch���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirDispatch(String value) {
        this.airDispatch = value;
    }

    /**
     * ��ȡentityFinance���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityFinance() {
        return entityFinance;
    }

    /**
     * ����entityFinance���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityFinance(String value) {
        this.entityFinance = value;
    }

    /**
     * ��ȡdepCoordinate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepCoordinate() {
        return depCoordinate;
    }

    /**
     * ����depCoordinate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepCoordinate(String value) {
        this.depCoordinate = value;
    }

    /**
     * ��ȡdepTelephone���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepTelephone() {
        return depTelephone;
    }

    /**
     * ����depTelephone���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepTelephone(String value) {
        this.depTelephone = value;
    }

    /**
     * ��ȡdepFax���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepFax() {
        return depFax;
    }

    /**
     * ����depFax���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepFax(String value) {
        this.depFax = value;
    }

    /**
     * ��ȡisEntityFinance���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsEntityFinance() {
        return isEntityFinance;
    }

    /**
     * ����isEntityFinance���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsEntityFinance(String value) {
        this.isEntityFinance = value;
    }

    /**
     * ��ȡorgSimpleName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgSimpleName() {
        return orgSimpleName;
    }

    /**
     * ����orgSimpleName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgSimpleName(String value) {
        this.orgSimpleName = value;
    }

    /**
     * ��ȡfullPath���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * ����fullPath���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullPath(String value) {
        this.fullPath = value;
    }

    /**
     * ��ȡversionNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVersionNo() {
        return versionNo;
    }

    /**
     * ����versionNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVersionNo(Double value) {
        this.versionNo = value;
    }

    /**
     * ��ȡuumsId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUumsId() {
        return uumsId;
    }

    /**
     * ����uumsId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUumsId(String value) {
        this.uumsId = value;
    }

    /**
     * ��ȡuumsParentId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUumsParentId() {
        return uumsParentId;
    }

    /**
     * ����uumsParentId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUumsParentId(String value) {
        this.uumsParentId = value;
    }

    /**
     * ��ȡisLeaf���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsLeaf() {
        return isLeaf;
    }

    /**
     * ����isLeaf���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsLeaf(String value) {
        this.isLeaf = value;
    }

    /**
     * ��ȡdisplayOrder���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayOrder() {
        return displayOrder;
    }

    /**
     * ����displayOrder���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayOrder(String value) {
        this.displayOrder = value;
    }

    /**
     * ��ȡdeptLevel���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptLevel() {
        return deptLevel;
    }

    /**
     * ����deptLevel���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptLevel(String value) {
        this.deptLevel = value;
    }

    /**
     * ��ȡdeptDesc���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptDesc() {
        return deptDesc;
    }

    /**
     * ����deptDesc���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptDesc(String value) {
        this.deptDesc = value;
    }

    /**
     * ��ȡareaCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * ����areaCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaCode(String value) {
        this.areaCode = value;
    }

    /**
     * ��ȡdeptEmail���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptEmail() {
        return deptEmail;
    }

    /**
     * ����deptEmail���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptEmail(String value) {
        this.deptEmail = value;
    }

    /**
     * ��ȡzipCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * ����zipCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipCode(String value) {
        this.zipCode = value;
    }

    /**
     * ��ȡdeptAttribute���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptAttribute() {
        return deptAttribute;
    }

    /**
     * ����deptAttribute���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptAttribute(String value) {
        this.deptAttribute = value;
    }

    /**
     * ��ȡcanceledSystems���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanceledSystems() {
        return canceledSystems;
    }

    /**
     * ����canceledSystems���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanceledSystems(String value) {
        this.canceledSystems = value;
    }

    /**
     * ��ȡehrDeptCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEhrDeptCode() {
        return ehrDeptCode;
    }

    /**
     * ����ehrDeptCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEhrDeptCode(String value) {
        this.ehrDeptCode = value;
    }

    /**
     * ��ȡcountryRegion���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryRegion() {
        return countryRegion;
    }

    /**
     * ����countryRegion���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryRegion(String value) {
        this.countryRegion = value;
    }

    /**
     * ��ȡorgEnSimple���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgEnSimple() {
        return orgEnSimple;
    }

    /**
     * ����orgEnSimple���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgEnSimple(String value) {
        this.orgEnSimple = value;
    }

    /**
     * ��ȡuumsIds���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUumsIds() {
        return uumsIds;
    }

    /**
     * ����uumsIds���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUumsIds(String value) {
        this.uumsIds = value;
    }

    /**
     * ��ȡparentOrgCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentOrgCode() {
        return parentOrgCode;
    }

    /**
     * ����parentOrgCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentOrgCode(String value) {
        this.parentOrgCode = value;
    }

}
