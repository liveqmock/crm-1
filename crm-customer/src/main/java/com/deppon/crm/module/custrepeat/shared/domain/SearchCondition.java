package com.deppon.crm.module.custrepeat.shared.domain;

import java.util.List;

/***********************************************************************
 * Module:  SearchCondition.java
 * Author:  120750
 * Purpose: Defines the Class SearchCondition
 ***********************************************************************/


/**
 * 疑似重复客户查询条件实体
 * 
 * @pdOid bc6d80ee-8d93-4d89-aa90-69900a6c0765
 */
public class SearchCondition {
	/**
	 * 分页：start
	 */
	private Integer start;
	/**
	 * 分页: limit
	 */
	private Integer limit;
	/**
	 * 部门ID
	 * 
	 * @pdOid f797a407-d972-476f-ab32-15b4017a2a5b
	 */
	private String deptId;
	/**
	 * 部门级别， 
	 * (普通部门) DEPT_ORDINARY、 小区 DEPT_SMALL_REGION、 大区 DEPT_BIG_REGION、 特殊部门
	 * dept_special
	 * 
	 * @pdOid cdb18d84-b641-4f93-b883-69033244a018
	 */
	private String deptLevel;
	
	/**
	 * 部门类型
	 * 快递类型 DEPT_EXPRESS/普通的 DEPT_ORDINARY、特殊部门 DEPT_SPECIAL
	 */
	private String deptType;
	
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 当前登录用户所在部门
	 */
	private String currtDeptName;
	/**
	 * 客户编码
	 * 
	 * @pdOid d24f80d5-3afc-4bb5-934e-b622a33732b5
	 */
	private String custCode;
	/**
	 * 客户名称
	 * 
	 * @pdOid c6eba6a3-1ea2-48c9-9252-6c3149a6ec22
	 */
	private String custName;
	/**
	 * 固定电话
	 * 
	 * @pdOid e60d8ff0-bd75-458d-a798-5002ee82895b
	 */
	private String telephone;
	/**
	 * 处理意见
	 */
	private String disposeOpinion;
	/**
	 * 主客户ID
	 */
	private String mainCustId;
	
	/**
	 * 是否为主客户
	 */
	private Integer isMainCust;

	/**
	 * 疑似重复列表集合
	 */
	private List<RepeatedCustomer> custIdList;
	
	/**
	 * 查询状态 status_approve 审批中、status_mobile 商机.日程.计划
	 */
	private String searchStatus;
	
	/**
	 * 客户状态
	 */
	private Integer custStatus;
	
	/**
	 * 是否随机查询一组疑似重复客户
	 * 1 查询、其他 不查询
	 */
	private Integer isRandomOneGroup;
	
	/**
	 * @return the custIdList
	 */
	public List<RepeatedCustomer> getCustIdList() {
		return custIdList;
	}


	/**
	 * @param custIdList the custIdList to set
	 */
	public void setCustIdList(List<RepeatedCustomer> custIdList) {
		this.custIdList = custIdList;
	}


	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}
	

	/**
	 * @param deptId
	 *            the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the deptLevel
	 */
	public String getDeptLevel() {
		return deptLevel;
	}

	/**
	 * @param deptLevel
	 *            the deptLevel to set
	 */
	public void setDeptLevel(String deptLevel) {
		this.deptLevel = deptLevel;
	}

	/**
	 * @return the custCode
	 */
	public String getCustCode() {
		return custCode;
	}

	/**
	 * @param custCode
	 *            the custCode to set
	 */
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * @param custName
	 *            the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the disposeOpinion
	 */
	public String getDisposeOpinion() {
		return disposeOpinion;
	}

	/**
	 * @param disposeOpinion the disposeOpinion to set
	 */
	public void setDisposeOpinion(String disposeOpinion) {
		this.disposeOpinion = disposeOpinion;
	}

	/**
	 * @return the mainCustId
	 */
	public String getMainCustId() {
		return mainCustId;
	}

	/**
	 * @param mainCustId the mainCustId to set
	 */
	public void setMainCustId(String mainCustId) {
		this.mainCustId = mainCustId;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
	}

	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * @return the isMainCust
	 */
	public Integer getIsMainCust() {
		return isMainCust;
	}

	/**
	 * @param isMainCust the isMainCust to set
	 */
	public void setIsMainCust(Integer isMainCust) {
		this.isMainCust = isMainCust;
	}


	/**
	 * @return the custStatus
	 */
	public Integer getCustStatus() {
		return custStatus;
	}


	/**
	 * @param custStatus the custStatus to set
	 */
	public void setCustStatus(Integer custStatus) {
		if(custStatus != null && custStatus == 2){
			custStatus = null;
		}
		this.custStatus = custStatus;
	}


	/**
	 * @return the searchStatus
	 */
	public String getSearchStatus() {
		return searchStatus;
	}


	/**
	 * @param searchStatus the searchStatus to set
	 */
	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}


	/**
	 * @return the deptType
	 */
	public String getDeptType() {
		return deptType;
	}


	/**
	 * @param deptType the deptType to set
	 */
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}


	/**
	 * @return the currtDeptName
	 */
	public String getCurrtDeptName() {
		return currtDeptName;
	}


	/**
	 * @param currtDeptName the currtDeptName to set
	 */
	public void setCurrtDeptName(String currtDeptName) {
		this.currtDeptName = currtDeptName;
	}


	/**
	 * @return the isRandomOneGroup
	 */
	public Integer getIsRandomOneGroup() {
		return isRandomOneGroup;
	}


	/**
	 * @param isRandomOneGroup the isRandomOneGroup to set
	 */
	public void setIsRandomOneGroup(Integer isRandomOneGroup) {
		this.isRandomOneGroup = isRandomOneGroup;
	}
}