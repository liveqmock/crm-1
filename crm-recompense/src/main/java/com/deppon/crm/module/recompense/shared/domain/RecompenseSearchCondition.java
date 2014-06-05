/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title QueryEntity.java
 * @package com.deppon.crm.recompense.domain 
 * @author Administrator
 * @version 0.1 2012-2-22
 */
package com.deppon.crm.module.recompense.shared.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @description 理赔查询对象
 * @author 安小虎
 * @version 0.1 2012-3-31
 * @date 2012-3-30
 */
public class RecompenseSearchCondition {
	//  理赔pojo中缺少创建部门、 最后审核时间

	// 审批时间
	private Date approveStartTime = null;
	// 最后审核时间
	private Date approveEndTime = null;
	// 上报部门
	private String reportDept = "";
	// 上报部门集合
	private List<String> reportDeptIdList = new ArrayList<String>();
	// 上报开始时间
	private Date reportStartTime = null;
	// 上报结束时间
	private Date reportEndTime = null;
	// 理赔方式
	private String recompenseMethod = "";

	// 理赔类型
	private String recompenseType = "";
	// 所属区域集合
	private List<String> deptIdList = new ArrayList<String>();
	// 所属区域
	private String belongArea = null;
	// 创建开始时间
	private Date createStartTime = null;
	// 创建结束时间
	private Date createEndTime = null;
	// 理赔状态
	private List<String> recompenseStateList = new ArrayList<String>();
	// 理赔状态
	private String recompenseState = null;
	// 运单号-【准确查询】
	private String waybillNum = "";

	// 客户编号-【准确查询】
	private String custNum = "";
	// 客户名称
	private String custName = "";
	// 客户级别
	private String customerLevel = "";
	// 持续开始时间
	private String beginDuration = "";
	// 持续结束时间
	private String endDuration = "";
	// 索赔金额
	private String recompenseAmount = "";
	private String beginRecompenseAmount = "";
	private String endRecompenseAmount = "";
	// 实际理赔金额
	private String realAmount = "";
	private String beginRealAmount = "";
	private String endRealAmount = "";
	//运输类型
	private String transType;
	//理赔收银员部门id
	private String cashierDept;
	//快递经理
	private String confirmAmountDept;

	// 开始行号
	private Integer start = 0;
	// 每页条数
	private Integer limit = 10;

	private boolean overpay = false;

	private boolean initSearch = false;
	
	//角色类型 1理赔专员 2营业部经理
	private Integer roleType;

	/**
	 * constructer
	 */
	public RecompenseSearchCondition() {
	}

	/**
	 * constructer
	 * 
	 * @param reportDept
	 * @param reportStartTime
	 * @param reportEndTime
	 * @param recompenseMethod
	 * @param recompenseType
	 * @param deptIdList
	 * @param createStartTime
	 * @param createEndTime
	 * @param recompenseStateList
	 * @param waybillNum
	 * @param custNum
	 * @param custName
	 * @param customerLevel
	 * @param beginDuration
	 * @param endDuration
	 * @param recompenseAmount
	 * @param beginRecompenseAmount
	 * @param endRecompenseAmount
	 * @param realAmount
	 * @param beginRealAmount
	 * @param endRealAmount
	 * @param start
	 * @param limit
	 */
	public RecompenseSearchCondition(String reportDept, Date reportStartTime,
			Date reportEndTime, String recompenseMethod, String recompenseType,
			List<String> deptIdList, Date createStartTime, Date createEndTime,
			List<String> recompenseStateList, String waybillNum,
			String custNum, String custName, String customerLevel,
			String beginDuration, String endDuration, 
			String recompenseAmount, String realAmount, 
			String beginRecompenseAmount, String endRecompenseAmount, 
			String beginRealAmount, String endRealAmount,
			Integer start, Integer limit) {
		super();
		this.reportDept = reportDept;
		this.reportStartTime = reportStartTime;
		this.reportEndTime = reportEndTime;
		this.recompenseMethod = recompenseMethod;
		this.recompenseType = recompenseType;
		this.deptIdList = deptIdList;
		this.createStartTime = createStartTime;
		this.createEndTime = createEndTime;
		this.recompenseStateList = recompenseStateList;
		this.waybillNum = waybillNum;
		this.custNum = custNum;
		this.custName = custName;
		this.customerLevel = customerLevel;
		this.beginDuration = beginDuration;
		this.endDuration = endDuration;
		this.recompenseAmount = recompenseAmount;
		this.realAmount = realAmount;
		this.beginRecompenseAmount = beginRecompenseAmount;
		this.endRecompenseAmount = endRecompenseAmount;
		this.beginRealAmount = beginRealAmount;
		this.endRealAmount = endRealAmount;
		this.start = start;
		this.limit = limit;
	}

	/**
	 * <p>
	 * Description:approveStartTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getApproveStartTime() {
		return approveStartTime;
	}

	/**
	 * <p>
	 * Description:approveStartTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setApproveStartTime(Date approveStartTime) {
		this.approveStartTime = approveStartTime;
	}

	/**
	 * <p>
	 * Description:approveEndTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getApproveEndTime() {
		return approveEndTime;
	}

	/**
	 * <p>
	 * Description:approveEndTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setApproveEndTime(Date approveEndTime) {
		this.approveEndTime = approveEndTime;
	}

	/**
	 * <p>
	 * Description:reportDept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getReportDept() {
		return reportDept;
	}

	/**
	 * <p>
	 * Description:reportDept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setReportDept(String reportDept) {
		this.reportDept = reportDept;
	}

	/**
	 * <p>
	 * Description:reportDeptIdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<String> getReportDeptIdList() {
		return reportDeptIdList;
	}

	/**
	 * <p>
	 * Description:reportDeptIdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setReportDeptIdList(List<String> reportDeptIdList) {
		this.reportDeptIdList = reportDeptIdList;
	}

	/**
	 * <p>
	 * Description:reportStartTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getReportStartTime() {
		return reportStartTime;
	}

	/**
	 * <p>
	 * Description:reportStartTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setReportStartTime(Date reportStartTime) {
		this.reportStartTime = reportStartTime;
	}

	/**
	 * <p>
	 * Description:reportEndTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getReportEndTime() {
		return reportEndTime;
	}

	/**
	 * <p>
	 * Description:reportEndTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setReportEndTime(Date reportEndTime) {
		this.reportEndTime = reportEndTime;
	}

	/**
	 * <p>
	 * Description:recompenseMethod<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseMethod() {
		return recompenseMethod;
	}

	/**
	 * <p>
	 * Description:recompenseMethod<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseMethod(String recompenseMethod) {
		this.recompenseMethod = recompenseMethod;
	}

	/**
	 * <p>
	 * Description:recompenseType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseType() {
		return recompenseType;
	}

	/**
	 * <p>
	 * Description:recompenseType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseType(String recompenseType) {
		this.recompenseType = recompenseType;
	}

	/**
	 * <p>
	 * Description:deptIdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<String> getDeptIdList() {
		return deptIdList;
	}

	/**
	 * <p>
	 * Description:deptIdList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDeptIdList(List<String> deptIdList) {
		this.deptIdList = deptIdList;
	}

	/**
	 * <p>
	 * Description:belongArea<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getBelongArea() {
		return belongArea;
	}

	/**
	 * <p>
	 * Description:belongArea<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setBelongArea(String belongArea) {
		this.belongArea = belongArea;
	}

	/**
	 * <p>
	 * Description:createStartTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getCreateStartTime() {
		return createStartTime;
	}

	/**
	 * <p>
	 * Description:createStartTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCreateStartTime(Date createStartTime) {
		this.createStartTime = createStartTime;
	}

	/**
	 * <p>
	 * Description:createEndTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getCreateEndTime() {
		return createEndTime;
	}

	/**
	 * <p>
	 * Description:createEndTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCreateEndTime(Date createEndTime) {
		this.createEndTime = createEndTime;
	}

	/**
	 * <p>
	 * Description:recompenseStateList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public List<String> getRecompenseStateList() {
		return recompenseStateList;
	}

	/**
	 * <p>
	 * Description:recompenseStateList<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseStateList(List<String> recompenseStateList) {
		this.recompenseStateList = recompenseStateList;
	}

	/**
	 * <p>
	 * Description:recompenseState<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseState() {
		return recompenseState;
	}

	/**
	 * <p>
	 * Description:recompenseState<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseState(String recompenseState) {
		this.recompenseState = recompenseState;
	}

	/**
	 * <p>
	 * Description:waybillNum<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getWaybillNum() {
		return waybillNum;
	}

	/**
	 * <p>
	 * Description:waybillNum<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setWaybillNum(String waybillNum) {
		this.waybillNum = waybillNum;
	}

	/**
	 * <p>
	 * Description:custNum<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCustNum() {
		return custNum;
	}

	/**
	 * <p>
	 * Description:custNum<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}

	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * <p>
	 * Description:customerLevel<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCustomerLevel() {
		return customerLevel;
	}

	/**
	 * <p>
	 * Description:customerLevel<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	/**
	 * <p>
	 * Description:start<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Integer getStart() {
		return start;
	}

	/**
	 * <p>
	 * Description:start<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setStart(Integer start) {
		this.start = start;
	}

	/**
	 * <p>
	 * Description:limit<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * <p>
	 * Description:limit<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * <p>
	 * Description:overpay<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public boolean isOverpay() {
		return overpay;
	}

	/**
	 * <p>
	 * Description:overpay<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setOverpay(boolean overpay) {
		this.overpay = overpay;
	}

	/**
	 * <p>
	 * Description:initSearch<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public boolean isInitSearch() {
		return initSearch;
	}

	/**
	 * <p>
	 * Description:initSearch<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setInitSearch(boolean initSearch) {
		this.initSearch = initSearch;
	}

	

	public String getBeginDuration() {
		return beginDuration;
	}

	public void setBeginDuration(String beginDuration) {
		this.beginDuration = beginDuration;
	}

	public String getEndDuration() {
		return endDuration;
	}

	public void setEndDuration(String endDuration) {
		this.endDuration = endDuration;
	}

	public String getRecompenseAmount() {
		return recompenseAmount;
	}

	public void setRecompenseAmount(String recompenseAmount) {
		this.recompenseAmount = recompenseAmount;
	}

	public String getBeginRecompenseAmount() {
		return beginRecompenseAmount;
	}

	public void setBeginRecompenseAmount(String beginRecompenseAmount) {
		this.beginRecompenseAmount = beginRecompenseAmount;
	}

	public String getEndRecompenseAmount() {
		return endRecompenseAmount;
	}

	public void setEndRecompenseAmount(String endRecompenseAmount) {
		this.endRecompenseAmount = endRecompenseAmount;
	}

	public String getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(String realAmount) {
		this.realAmount = realAmount;
	}

	public String getBeginRealAmount() {
		return beginRealAmount;
	}

	public void setBeginRealAmount(String beginRealAmount) {
		this.beginRealAmount = beginRealAmount;
	}

	public String getEndRealAmount() {
		return endRealAmount;
	}

	public void setEndRealAmount(String endRealAmount) {
		this.endRealAmount = endRealAmount;
	}

	

	public String getCashierDept() {
		return cashierDept;
	}

	public void setCashierDept(String cashierDept) {
		this.cashierDept = cashierDept;
	}

	public String getConfirmAmountDept() {
		return confirmAmountDept;
	}

	public void setConfirmAmountDept(String confirmAmountDept) {
		this.confirmAmountDept = confirmAmountDept;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	
	
}
