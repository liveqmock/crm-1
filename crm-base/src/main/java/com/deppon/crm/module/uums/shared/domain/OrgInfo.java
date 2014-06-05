package com.deppon.crm.module.uums.shared.domain;
import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @description 接口传输参数组织�?
 * @author zzw
 * @version 0.1 2013-11-25
 */
public class OrgInfo extends BaseUUEntity implements Serializable {
	/** 
	 * @fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 6771667825944166873L;

	//上级组织编码
	private String parentOrgCode;
	//组织名称
	private String orgName;
	//组织性质
	private String orgAttribute;
	//组织标杆编码
	private String orgBenchmarkCode;
	//组织负责�?
	private String orgManager;
	//组织联系电话
	private String orgPhone;
	//组织传真号码
	private String orgFax;
	//上级组织ID
	private String parentOrgId;
	//上级组织标杆编码
	private String parentOrgBenchmarkCode;
	private String subCompName;
	//�?��子公司编�?
	private String subCompCode;
	//�?��成本中心编码
	private String costCenterCode;
	//�?��成本中心名称
	private String costCenterName;
	//组织省份
	private String orgProvince;
	//组织城市
	private String orgCity;
	//组织区县
	private String orgCountry;
	//组织邮编
	private String orgZipCode;
	//组织邮箱
	private String orgEmail;
	//组织地址
	private String orgAddress;
	//组织状�?(正常、待撤销、已撤销)
	private String orgStatus;
	//启用日期
	private Date validDate;
	//作废日期
	private Date invalidDate;
	//是否事业�?
	private boolean isCareerDept;
	//是否大区
	private boolean isBigArea;
	//是否小区
	private boolean isSmallArea;
	//	组织层级
	private String orgLevel;
	//组织描述
	private String orgDesc;
	//组织序列
	private String orgSeq;
	//是否叶子节点
	private boolean isLeaf;
	//显示顺序
	private String displayOrder;
	//部门�?��
	private String deptShortName;
	//部门性质
	private String deptAttribute;
	//地区编码
	private String areaCode;
	//已封存系�?
	private String canceledSystems;
	//HER部门编码
	private String ehrDeptCode;
	/**
	 * @return parentOrgCode : return the property parentOrgCode.
	 */
	public String getParentOrgCode() {
		return parentOrgCode;
	}
	/**
	 * @param parentOrgCode : set the property parentOrgCode.
	 */
	public void setParentOrgCode(String parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}
	/**
	 * @return orgName : return the property orgName.
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * @param orgName : set the property orgName.
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * @return orgAttribute : return the property orgAttribute.
	 */
	public String getOrgAttribute() {
		return orgAttribute;
	}
	/**
	 * @param orgAttribute : set the property orgAttribute.
	 */
	public void setOrgAttribute(String orgAttribute) {
		this.orgAttribute = orgAttribute;
	}
	/**
	 * @return orgBenchmarkCode : return the property orgBenchmarkCode.
	 */
	public String getOrgBenchmarkCode() {
		return orgBenchmarkCode;
	}
	/**
	 * @param orgBenchmarkCode : set the property orgBenchmarkCode.
	 */
	public void setOrgBenchmarkCode(String orgBenchmarkCode) {
		this.orgBenchmarkCode = orgBenchmarkCode;
	}
	/**
	 * @return orgManager : return the property orgManager.
	 */
	public String getOrgManager() {
		return orgManager;
	}
	/**
	 * @param orgManager : set the property orgManager.
	 */
	public void setOrgManager(String orgManager) {
		this.orgManager = orgManager;
	}
	/**
	 * @return orgPhone : return the property orgPhone.
	 */
	public String getOrgPhone() {
		return orgPhone;
	}
	/**
	 * @param orgPhone : set the property orgPhone.
	 */
	public void setOrgPhone(String orgPhone) {
		this.orgPhone = orgPhone;
	}
	/**
	 * @return orgFax : return the property orgFax.
	 */
	public String getOrgFax() {
		return orgFax;
	}
	/**
	 * @param orgFax : set the property orgFax.
	 */
	public void setOrgFax(String orgFax) {
		this.orgFax = orgFax;
	}
	/**
	 * @return parentOrgId : return the property parentOrgId.
	 */
	public String getParentOrgId() {
		return parentOrgId;
	}
	/**
	 * @param parentOrgId : set the property parentOrgId.
	 */
	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	/**
	 * @return parentOrgBenchmarkCode : return the property parentOrgBenchmarkCode.
	 */
	public String getParentOrgBenchmarkCode() {
		return parentOrgBenchmarkCode;
	}
	/**
	 * @param parentOrgBenchmarkCode : set the property parentOrgBenchmarkCode.
	 */
	public void setParentOrgBenchmarkCode(String parentOrgBenchmarkCode) {
		this.parentOrgBenchmarkCode = parentOrgBenchmarkCode;
	}
	/**
	 * @return subCompName : return the property subCompName.
	 */
	public String getSubCompName() {
		return subCompName;
	}
	/**
	 * @param subCompName : set the property subCompName.
	 */
	public void setSubCompName(String subCompName) {
		this.subCompName = subCompName;
	}
	/**
	 * @return subCompCode : return the property subCompCode.
	 */
	public String getSubCompCode() {
		return subCompCode;
	}
	/**
	 * @param subCompCode : set the property subCompCode.
	 */
	public void setSubCompCode(String subCompCode) {
		this.subCompCode = subCompCode;
	}
	/**
	 * @return costCenterCode : return the property costCenterCode.
	 */
	public String getCostCenterCode() {
		return costCenterCode;
	}
	/**
	 * @param costCenterCode : set the property costCenterCode.
	 */
	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}
	/**
	 * @return costCenterName : return the property costCenterName.
	 */
	public String getCostCenterName() {
		return costCenterName;
	}
	/**
	 * @param costCenterName : set the property costCenterName.
	 */
	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}
	/**
	 * @return orgProvince : return the property orgProvince.
	 */
	public String getOrgProvince() {
		return orgProvince;
	}
	/**
	 * @param orgProvince : set the property orgProvince.
	 */
	public void setOrgProvince(String orgProvince) {
		this.orgProvince = orgProvince;
	}
	/**
	 * @return orgCity : return the property orgCity.
	 */
	public String getOrgCity() {
		return orgCity;
	}
	/**
	 * @param orgCity : set the property orgCity.
	 */
	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}
	/**
	 * @return orgCountry : return the property orgCountry.
	 */
	public String getOrgCountry() {
		return orgCountry;
	}
	/**
	 * @param orgCountry : set the property orgCountry.
	 */
	public void setOrgCountry(String orgCountry) {
		this.orgCountry = orgCountry;
	}
	/**
	 * @return orgZipCode : return the property orgZipCode.
	 */
	public String getOrgZipCode() {
		return orgZipCode;
	}
	/**
	 * @param orgZipCode : set the property orgZipCode.
	 */
	public void setOrgZipCode(String orgZipCode) {
		this.orgZipCode = orgZipCode;
	}
	/**
	 * @return orgEmail : return the property orgEmail.
	 */
	public String getOrgEmail() {
		return orgEmail;
	}
	/**
	 * @param orgEmail : set the property orgEmail.
	 */
	public void setOrgEmail(String orgEmail) {
		this.orgEmail = orgEmail;
	}
	/**
	 * @return orgAddress : return the property orgAddress.
	 */
	public String getOrgAddress() {
		return orgAddress;
	}
	/**
	 * @param orgAddress : set the property orgAddress.
	 */
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	/**
	 * @return orgStatus : return the property orgStatus.
	 */
	public String getOrgStatus() {
		return orgStatus;
	}
	/**
	 * @param orgStatus : set the property orgStatus.
	 */
	public void setOrgStatus(String orgStatus) {
		this.orgStatus = orgStatus;
	}
	/**
	 * @return validDate : return the property validDate.
	 */
	public Date getValidDate() {
		return validDate;
	}
	/**
	 * @param validDate : set the property validDate.
	 */
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	/**
	 * @return invalidDate : return the property invalidDate.
	 */
	public Date getInvalidDate() {
		return invalidDate;
	}
	/**
	 * @param invalidDate : set the property invalidDate.
	 */
	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}
	/**
	 * @return isCareerDept : return the property isCareerDept.
	 */
	public boolean isCareerDept() {
		return isCareerDept;
	}
	/**
	 * @param isCareerDept : set the property isCareerDept.
	 */
	public void setCareerDept(boolean isCareerDept) {
		this.isCareerDept = isCareerDept;
	}
	/**
	 * @return isBigArea : return the property isBigArea.
	 */
	public boolean isBigArea() {
		return isBigArea;
	}
	/**
	 * @param isBigArea : set the property isBigArea.
	 */
	public void setBigArea(boolean isBigArea) {
		this.isBigArea = isBigArea;
	}
	/**
	 * @return isSmallArea : return the property isSmallArea.
	 */
	public boolean isSmallArea() {
		return isSmallArea;
	}
	/**
	 * @param isSmallArea : set the property isSmallArea.
	 */
	public void setSmallArea(boolean isSmallArea) {
		this.isSmallArea = isSmallArea;
	}
	/**
	 * @return orgLevel : return the property orgLevel.
	 */
	public String getOrgLevel() {
		return orgLevel;
	}
	/**
	 * @param orgLevel : set the property orgLevel.
	 */
	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}
	/**
	 * @return orgDesc : return the property orgDesc.
	 */
	public String getOrgDesc() {
		return orgDesc;
	}
	/**
	 * @param orgDesc : set the property orgDesc.
	 */
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
	/**
	 * @return orgSeq : return the property orgSeq.
	 */
	public String getOrgSeq() {
		return orgSeq;
	}
	/**
	 * @param orgSeq : set the property orgSeq.
	 */
	public void setOrgSeq(String orgSeq) {
		this.orgSeq = orgSeq;
	}
	/**
	 * @return isLeaf : return the property isLeaf.
	 */
	public boolean isLeaf() {
		return isLeaf;
	}
	/**
	 * @param isLeaf : set the property isLeaf.
	 */
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	/**
	 * @return changeType : return the property changeType.
	 */
	public String getChangeType() {
		return changeType;
	}
	/**
	 * @param changeType : set the property changeType.
	 */
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	/**
	 * @return changeDate : return the property changeDate.
	 */
	public Date getChangeDate() {
		return changeDate;
	}
	/**
	 * @param changeDate : set the property changeDate.
	 */
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	/**
	 * @return displayOrder : return the property displayOrder.
	 */
	public String getDisplayOrder() {
		return displayOrder;
	}
	/**
	 * @param displayOrder : set the property displayOrder.
	 */
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	/**
	 * @return deptShortName : return the property deptShortName.
	 */
	public String getDeptShortName() {
		return deptShortName;
	}
	/**
	 * @param deptShortName : set the property deptShortName.
	 */
	public void setDeptShortName(String deptShortName) {
		this.deptShortName = deptShortName;
	}
	/**
	 * @return deptAttribute : return the property deptAttribute.
	 */
	public String getDeptAttribute() {
		return deptAttribute;
	}
	/**
	 * @param deptAttribute : set the property deptAttribute.
	 */
	public void setDeptAttribute(String deptAttribute) {
		this.deptAttribute = deptAttribute;
	}
	/**
	 * @return areaCode : return the property areaCode.
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * @param areaCode : set the property areaCode.
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * @return canceledSystems : return the property canceledSystems.
	 */
	public String getCanceledSystems() {
		return canceledSystems;
	}
	/**
	 * @param canceledSystems : set the property canceledSystems.
	 */
	public void setCanceledSystems(String canceledSystems) {
		this.canceledSystems = canceledSystems;
	}
	/**
	 * @return ehrDeptCode : return the property ehrDeptCode.
	 */
	public String getEhrDeptCode() {
		return ehrDeptCode;
	}
	/**
	 * @param ehrDeptCode : set the property ehrDeptCode.
	 */
	public void setEhrDeptCode(String ehrDeptCode) {
		this.ehrDeptCode = ehrDeptCode;
	}

}
