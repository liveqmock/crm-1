/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitQueryCondition.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author zhujunyong
 * @version 0.1 Mar 26, 2012
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.marketing.server.utils.PlanValidateUtils;

/**   
 * <p>
 * Description:回访信息-查询<br />
 * </p>
 * @title ReturnVisitQueryCondition.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author zhujunyong
 * @version 0.1 Mar 26, 2012
 */

public class ReturnVisitQueryCondition {
	//部门Id
	private String deptId;
	// 联系人姓名
	private String linkName;

	// 计划主题
	private String planId;
	
	// 客户意见
	private String opinion;

	// 开发维护方式
	private String way;
	
	// 开始时间
	private Date beginTime;
	//结束时间
	private Date endTime;
	
	// 会员ID
	private String memberId;
	
	// 联系人ID
	private String linkManId;

	// 日程类型
	private String scheType;
	
	// 执行人员
	private String execUserId;
	
	// 主题所对应的日程id列表
	private List<String> scheduleIds; 
	
	// 授权部门ID
	private List<String> deptIds;

	// 客户类别
	private String customerType;
	// 日程类型（DEV，MAT）
	private String type;
	//计划名称
	private String planName;
	//客户编码
	private String custNumber;
	//客户来源,配合需求编号为ROWS2013070101新增的字段，用以查询客户的潜客来源
	private String potentialCustSource;
	
	/**
	 * @param potentialCustSource the potentialCustSource to get
	 */
	public String getPotentialCustSource() {
		return potentialCustSource;
	}

	/**
	 * @param potentialCustSource the potentialCustSource to set
	 */
	public void setPotentialCustSource(String potentialCustSource) {
		this.potentialCustSource = potentialCustSource;
	}

	/**
	 * 
	 * <p>
	 * Description:验证查询条件除了时间以外，不能全部为空<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Mar 27, 2012
	 * @return 
	 * boolean
	 */
	public boolean validate(){
		//是否所有的字段都为空
		//联系人名称
		boolean allBlank = StringUtils.isBlank(linkName)
				//计划ID
				&& StringUtils.isBlank(planId)
				//客户意见
				&& StringUtils.isBlank(opinion)
				//回访方式
				&& StringUtils.isBlank(way)
				//执行人ＩＤ
				&& StringUtils.isBlank(execUserId);
		return !allBlank;
	}
	
	/**
	 * 
	 * <p>
	 * Description:录入回访查询-检查开始时间必须早于结束时间，且时间差不超过3个月<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 1, 2012
	 * @return
	 * boolean
	 */
	public boolean validateBeginEndTime() {
		//开始时间和结束时间都不能为空
		if (beginTime == null || endTime == null) {
			return false;
		}
		//日期比较
		return PlanValidateUtils.compareToDate(beginTime, endTime) < 1 
				? true : false;
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
	 * @return planName : return the property planName.
	 */
	public String getPlanName() {
		return planName;
	}

	/**
	 * @param planName : set the property planName.
	 */
	public void setPlanName(String planName) {
		this.planName = planName;
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
	 * @return type : return the property type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type : set the property type.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return customerType : return the property customerType.
	 */
	public String getCustomerType() {
		return customerType;
	}

	/**
	 * @param customerType : set the property customerType.
	 */
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	/**
	 * @return memberId : return the property memberId.
	 */
	public String getMemberId() {
		return memberId;
	}



	/**
	 * @param memberId : set the property memberId.
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	/**
	 * @return linkManId : return the property linkManId.
	 */
	public String getLinkManId() {
		return linkManId;
	}



	/**
	 * @param linkManId : set the property linkManId.
	 */
	public void setLinkManId(String linkManId) {
		this.linkManId = linkManId;
	}



	/**
	 * @return scheType : return the property scheType.
	 */
	public String getScheType() {
		return scheType;
	}



	/**
	 * @param scheType : set the property scheType.
	 */
	public void setScheType(String scheType) {
		this.scheType = scheType;
	}

	/**
	 * @return deptIds : return the property deptIds.
	 */
	public List<String> getDeptIds() {
		return deptIds;
	}


	/**
	 * @param deptIds : set the property deptIds.
	 */
	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}

	/**
	 * @return linkName : return the property linkName.
	 */
	public String getLinkName() {
		return linkName;
	}

	/**
	 * @param linkName : set the property linkName.
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	/**
	 * @return planId : return the property planId.
	 */
	public String getPlanId() {
		return planId;
	}

	/**
	 * @param planId : set the property planId.
	 */
	public void setPlanId(String planId) {
		this.planId = planId;
	}

	/**
	 * @return opinion : return the property opinion.
	 */
	public String getOpinion() {
		return opinion;
	}

	/**
	 * @param opinion : set the property opinion.
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	/**
	 * @return way : return the property way.
	 */
	public String getWay() {
		return way;
	}

	/**
	 * @param way : set the property way.
	 */
	public void setWay(String way) {
		this.way = way;
	}

	/**
	 * @return beginTime : return the property beginTime.
	 */
	public Date getBeginTime() {
		return beginTime;
	}

	/**
	 * @param beginTime : set the property beginTime.
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * @return endTime : return the property endTime.
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime : set the property endTime.
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return execUserId : return the property execUserId.
	 */
	public String getExecUserId() {
		return execUserId;
	}

	/**
	 * @param execUserId : set the property execUserId.
	 */
	public void setExecUserId(String execUserId) {
		this.execUserId = execUserId;
	}

	/**
	 * @return scheduleIds : return the property scheduleIds.
	 */
	public List<String> getScheduleIds() {
		return scheduleIds;
	}

	/**
	 * @param scheduleIds : set the property scheduleIds.
	 */
	public void setScheduleIds(List<String> scheduleIds) {
		this.scheduleIds = scheduleIds;
	}
	
	
}
