/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerGroupDetail.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author zhujunyong
 * @version 0.1 Apr 6, 2012
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;
import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:客户分组详情<br />
 * </p>
 * @title CustomerGroupDetail.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author zhujunyong
 * @version 0.1 Apr 6, 2012
 */

public class CustomerGroupDetail extends BaseEntity{

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -2736078948983661640L;
	// 部门id
	private String deptId;
	// 部门名称
	private String deptName;
	// 客户编号
	private String custNumber;
	// 客户名称
	private String custName;
	// 维护人id
	private String userId;
	// 维护人姓名
	private String empName;
	// 组id
	private String groupId;
	// 客户id
	private String custId;
	// 查询日期
	private Date queryDate;
	// 发到货标志
	private String fcAnalyseType;
	//上月发货周期
	private String sendGoodCycle = "0";
	//上月发货次数
	private int sendGoodCount;
	
	// 当月金额
	private String monthAmount = "0";
	// 上月金额
	private String month1Amount = "0";
	// 上上月金额
	private String month2Amount = "0";
	// 从本月1号到当前时间的每日发到货金额
	private List<String> dayAmount;
	
	private Day days;
	// 周期表页面调用参数
	private boolean cyclePage = false;
	//页面颜色表示,"1" 标识  "0"不标识
	private String isMarkColor;
	//部门列表 
	private List<String> deptIds;
	//发货类别
	private String sendGoodsType;
	//客户类别
	private String custType;
	
	public String getSendGoodsType() {
		return sendGoodsType;
	}
	public void setSendGoodsType(String sendGoodsType) {
		this.sendGoodsType = sendGoodsType;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	/**
	 * @return deptId : return the property deptId.
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId : set the property deptId.
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return deptName : return the property deptName.
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName : set the property deptName.
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return custNumber : return the property custNumber.
	 */
	public String getCustNumber() {
		return custNumber;
	}
	/**
	 * @param custNumber : set the property custNumber.
	 */
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	/**
	 * @return custName : return the property custName.
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * @param custName : set the property custName.
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * @return userId : return the property userId.
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId : set the property userId.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return empName : return the property empName.
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName : set the property empName.
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return groupId : return the property groupId.
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId : set the property groupId.
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return custId : return the property custId.
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * @param custId : set the property custId.
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * @return sendGoodCycle : return the property sendGoodCycle.
	 */
	public String getSendGoodCycle() {
		return sendGoodCycle;
	}
	/**
	 * @param sendGoodCycle : set the property sendGoodCycle.
	 */
	public void setSendGoodCycle(String sendGoodCycle) {
		this.sendGoodCycle = sendGoodCycle;
	}
	/**
	 * @return sendGoodCount : return the property sendGoodCount.
	 */
	public int getSendGoodCount() {
		return sendGoodCount;
	}
	/**
	 * @param sendGoodCount : set the property sendGoodCount.
	 */
	public void setSendGoodCount(int sendGoodCount) {
		this.sendGoodCount = sendGoodCount;
	}
	/**
	 * @return monthAmount : return the property monthAmount.
	 */
	public String getMonthAmount() {
		return monthAmount;
	}
	/**
	 * @param monthAmount : set the property monthAmount.
	 */
	public void setMonthAmount(String monthAmount) {
		this.monthAmount = monthAmount;
	}
	/**
	 * @return month1Amount : return the property month1Amount.
	 */
	public String getMonth1Amount() {
		return month1Amount;
	}
	/**
	 * @param month1Amount : set the property month1Amount.
	 */
	public void setMonth1Amount(String month1Amount) {
		this.month1Amount = month1Amount;
	}
	/**
	 * @return month2Amount : return the property month2Amount.
	 */
	public String getMonth2Amount() {
		return month2Amount;
	}
	/**
	 * @param month2Amount : set the property month2Amount.
	 */
	public void setMonth2Amount(String month2Amount) {
		this.month2Amount = month2Amount;
	}
	/**
	 * @return dayAmount : return the property dayAmount.
	 */
	public List<String> getDayAmount() {
		return dayAmount;
	}
	/**
	 * @param dayAmount : set the property dayAmount.
	 */
	public void setDayAmount(List<String> dayAmount) {
		this.dayAmount = dayAmount;
	}
	/**
	 * @return cyclePage : return the property cyclePage.
	 */
	public boolean isCyclePage() {
		return cyclePage;
	}
	/**
	 * @param cyclePage : set the property cyclePage.
	 */
	public void setCyclePage(boolean cyclePage) {
		this.cyclePage = cyclePage;
	}
	/**
	 * @return isMarkColor : return the property isMarkColor.
	 */
	public String getIsMarkColor() {
		return isMarkColor;
	}
	/**
	 * @param isMarkColor : set the property isMarkColor.
	 */
	public void setIsMarkColor(String isMarkColor) {
		this.isMarkColor = isMarkColor;
	}
	
	/**
	 * @return queryDate : return the property queryDate.
	 */
	public Date getQueryDate() {
		return queryDate;
	}
	/**
	 * @param queryDate : set the property queryDate.
	 */
	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
	}
	/**
	 * @return fcAnalyseType : return the property fcAnalyseType.
	 */
	public String getFcAnalyseType() {
		return fcAnalyseType;
	}
	/**
	 * @param fcAnalyseType : set the property fcAnalyseType.
	 */
	public void setFcAnalyseType(String fcAnalyseType) {
		this.fcAnalyseType = fcAnalyseType;
	}
	/**
	 * @return days : return the property days.
	 */
	public Day getDays() {
		return days;
	}
	/**
	 * @param days : set the property days.
	 */
	public void setDays(Day days) {
		this.days = days;
	}
	

	public List<String> getDeptIds() {
		return deptIds;
	}
	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}
	/* toString
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		//toString方法，返回字符串内容
		//部门ID + 部门名称 + 客户名称 +　客户编码  + 分组 。。。。
		return "CustomerGroupDetail [deptId=" + deptId + ", deptName="
				+ deptName + ", custNumber=" + custNumber + ", custName="
				+ custName + ", userId=" + userId + ", empName=" + empName
				+ ", groupId=" + groupId + ", custId=" + custId
				+ ", monthAmount=" + monthAmount + ", month1Amount="
				+ month1Amount + ", month2Amount=" + month2Amount
				+ ", dayAmount=" + dayAmount + "]";
	}
}
