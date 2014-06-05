/**
 * @description
 * @author 赵斌
 * @2012-3-29
 * @return
 */
package com.deppon.crm.module.customer.shared.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author 赵斌
 * @功能 合同查询条件
 *
 */
public class ContractCondition 
{
	//部门Id
	private String deptId = "";
	//部门权限
	private List<String> deptIds = new ArrayList<String>();
	//客户编码
	private String custNumber = "";
	// 客户全称
	private String custCompany = "";
	//合同编号
	private String contractNum = "";
	//协议联系人
	private String contactName = "";
	//创建起始时间
	private Date searchStartTime;
	//创建结束时间
	private Date searchEndTime;
	// 合同起始日期
	private Date contractBeginDate;
	// 合同到期日期
	private Date contractendDate;
	//客户id
	private String custId;
	//合同id
	private String contractId;
	//合同优惠类型
	private String prefrentialType;
	//合同优惠类型
	private String exPrefrentialType;
	//合同状态
	private String contractStatus;
	//客户发货开始统计时间
	private Date custBeginDate;
	//客户发货结束统计时间
	private Date custEndDate;

	//是否初始化查询(主要用于合同界面初始化界面标志)
	private String initSearch;
	// 合同主体
	private String contractSubject;
	/**
	 * <p>
	 * Description:custBeginDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-18
	 */

	public Date getCustBeginDate() {
		return custBeginDate;
	}
	/**
	 * <p>
	 * Description:custBeginDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-18
	 */

	public void setCustBeginDate(Date custBeginDate) {
		this.custBeginDate = custBeginDate;
	}
	/**
	 * <p>
	 * Description:custEndDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-18
	 */

	public Date getCustEndDate() {
		return custEndDate;
	}
	/**
	 * <p>
	 * Description:custEndDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-18
	 */

	public void setCustEndDate(Date custEndDate) {
		this.custEndDate = custEndDate;
	}
	/**
	 * <p>
	 * Description:prefrentialType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-18
	 */

	public String getPrefrentialType() {
		return prefrentialType;
	}
	/**
	 * <p>
	 * Description:prefrentialType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-18
	 */

	public void setPrefrentialType(String prefrentialType) {
		this.prefrentialType = prefrentialType;
	}
	/**
	 * <p>
	 * Description:initSearch<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-18
	 */

	public String getInitSearch() {
		return initSearch;
	}
	/**
	 * <p>
	 * Description:initSearch<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-18
	 */

	public void setInitSearch(String initSearch) {
		this.initSearch = initSearch;
	}
	/**
	 * <p>
	 * Description:contractStatus<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-18
	 */

	public String getContractStatus() {
		return contractStatus;
	}
	/**
	 * <p>
	 * Description:contractStatus<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-18
	 */

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	/**
	 * <p>
	 * Description:contractSubject<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-18
	 */
	public String getContractSubject() {
		return contractSubject;
	}
	/**
	 * <p>
	 * Description:contractSubject<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-18
	 */
	public String setContractSubject(String contractSubject ) {
		return this.contractSubject = contractSubject;
	}

	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * <p>
	 * Description:deptIds<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<String> getDeptIds() {
		return deptIds;
	}
	/**
	 * <p>
	 * Description:deptIds<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}
	/**
	 * <p>
	 * Description:custNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustNumber() {
		return custNumber;
	}
	/**
	 * <p>
	 * Description:custNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	/**
	 * <p>
	 * Description:custCompany<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustCompany() {
		return custCompany;
	}
	/**
	 * <p>
	 * Description:custCompany<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustCompany(String custCompany) {
		this.custCompany = custCompany;
	}
	/**
	 * <p>
	 * Description:contractNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContractNum() {
		return contractNum;
	}
	/**
	 * <p>
	 * Description:contractNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * <p>
	 * Description:searchStartTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getSearchStartTime() {
		return searchStartTime;
	}
	/**
	 * <p>
	 * Description:searchStartTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setSearchStartTime(Date searchStartTime) {
		this.searchStartTime = searchStartTime;
	}
	/**
	 * <p>
	 * Description:searchEndTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getSearchEndTime() {
		return searchEndTime;
	}
	/**
	 * <p>
	 * Description:searchEndTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setSearchEndTime(Date searchEndTime) {
		this.searchEndTime = searchEndTime;
	}
	/**
	 * <p>
	 * Description:contractBeginDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getContractBeginDate() {
		return contractBeginDate;
	}
	/**
	 * <p>
	 * Description:contractBeginDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractBeginDate(Date contractBeginDate) {
		this.contractBeginDate = contractBeginDate;
	}
	/**
	 * <p>
	 * Description:contractendDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getContractendDate() {
		return contractendDate;
	}
	/**
	 * <p>
	 * Description:contractendDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractendDate(Date contractendDate) {
		this.contractendDate = contractendDate;
	}
	/**
	 * <p>
	 * Description:custId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * <p>
	 * Description:custId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * <p>
	 * Description:contractId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * <p>
	 * Description:contractId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 *@user pgj
	 *2013-10-12上午9:45:41
	 * @return exPrefrentialType : return the property exPrefrentialType.
	 */
	public String getExPrefrentialType() {
		return exPrefrentialType;
	}
	/**
	 * @param exPrefrentialType : set the property exPrefrentialType.
	 */
	public void setExPrefrentialType(String exPrefrentialType) {
		this.exPrefrentialType = exPrefrentialType;
	}
	
}
