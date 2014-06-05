package com.deppon.crm.module.scheduler.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title OaDepartmentEntity.java
 * @package com.deppon.crm.module.scheduler.shared.domain 
 * @author 苏玉军
 * @version 0.1 2012-7-3
 */

public class OaDepartmentEntity extends BaseEntity {
	private static final long serialVersionUID = -3567068583845219563L;
//	private String id;  //ORGID
//	private Date createDate;// 创建日期
//	private String createUser;//创建人
//	private Date modifyDate;// 修改日期
//	private String modifyUser;//修改人
	//组织编码
	private String orgCode;
	//部门名称
	private String orgName;
	//负责人Id
	private String managerId;
	//联系电话
	private String linkTel;
	//传真
	private String fax;
	//组织描述
	private String deptDesc;
	//上级组织Id
	private String parentOrgId;
	//所属子公司
	private String parentComCode;
	//邮政号码
	private String zipCode;
	//部门地址
	private String orgAddr;
	//状态
	private String orgClose;
	//结束时间
	private Date endDate;
	//开始时间
	private Date startDate;
	//显示顺序
	private int sortNo;
	//部门层级 default 1
	private int orgLevel;
	//部门序列
	private String orgSeq;
	//标杆编码
	private String standardCode;
	
	
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getStandardCode() {
		return standardCode;
	}
	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getLinkTel() {
		return linkTel;
	}
	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getDesc() {
		return deptDesc;
	}
	public void setDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	public String getParentComCode() {
		return parentComCode;
	}
	public void setParentComCode(String parentComCode) {
		this.parentComCode = parentComCode;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getOrgAddr() {
		return orgAddr;
	}
	public void setOrgAddr(String orgAddr) {
		this.orgAddr = orgAddr;
	}
	public String getOrgClose() {
		return orgClose;
	}
	public void setOrgClose(String orgClose) {
		this.orgClose = orgClose;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getSortNo() {
		return sortNo;
	}
	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
	public int getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(int orgLevel) {
		this.orgLevel = orgLevel;
	}
	public String getOrgSeq() {
		return orgSeq;
	}
	public void setOrgSeq(String orgSeq) {
		this.orgSeq = orgSeq;
	}
}
