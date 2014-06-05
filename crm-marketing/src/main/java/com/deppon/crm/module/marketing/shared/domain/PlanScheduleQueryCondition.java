/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PlanMaintainVo.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author Administrator
 * @version 0.1 2012-3-31
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;
import java.util.Set;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * <p>
 * 封装开发计划新增查询、日程新增查询及接收查询结果<br />
 * </p>
 * 
 * @title PlanMaintainVo.java
 * @package com.deppon.crm.module.marketing.shared.domain
 * @author 苏玉军
 * @version 0.1 2012-3-31
 */

public class PlanScheduleQueryCondition extends BaseEntity {
	private static final long serialVersionUID = 6503195306799688867L;
	//id
	private String id;
	// 客户名称
	private String custName;
	// 联系人姓名
	private String contactName;
	// 联系人手机
	private String contactMobile;
	// 联系人电话
	private String contactTel;
	// 客户属性
	private String custProperty;
	// 合作意向
	private String coopIntention;
	// 商机状态
	private String bizStatus = "";
	// 客户来源
	private String custSource = "";
	// 货量潜力
	private String goodsPotential = "";
	// 客户类型
	private String custType;
	// 行业
	private String industry;
	//已制定的计划名称
	private String planName;
	//日程时间
	private Date sheduleTime;
	//开发状态
	private String status;
	// 创建时间
	private Date beginTime;
	//结束时间
	private Date overTime;
	// 发货量
	private double beginShipWeight;
	//结束发货量
	private double overShipWeight;
	// 发货票数
	private Integer beginShipVotes;
	//结束发货票数
	private Integer overShipVotes;
	// 发货金额
	private double beginShipAmount;
	// 结束发货金额
	private double overShipAmount;
	// 到达货量
	private double beginArrivalWeight;
	//结束到达货量
	private double overArrivalWeight;
	// 到达票数
	private Integer beginArrivalVotes;
	//结束到达票数
	private Integer overArrivalVotes;
	// 到达金额
	private double beginArrivalAmount;
	//结束到达金额
	private double overArrivalAmount;
	// 前台传过来的部门id
	private String deptId;
	//职位
	private String position;
	//授权部门的Id
	private Set<String> deptIds;
	/**
	 * @return id : return the property id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id : set the property id.
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return contactName : return the property contactName.
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * @param contactName : set the property contactName.
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * @return contactMobile : return the property contactMobile.
	 */
	public String getContactMobile() {
		return contactMobile;
	}
	/**
	 * @param contactMobile : set the property contactMobile.
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	/**
	 * @return contactTel : return the property contactTel.
	 */
	public String getContactTel() {
		return contactTel;
	}
	/**
	 * @param contactTel : set the property contactTel.
	 */
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	/**
	 * @return custProperty : return the property custProperty.
	 */
	public String getCustProperty() {
		return custProperty;
	}
	/**
	 * @param custProperty : set the property custProperty.
	 */
	public void setCustProperty(String custProperty) {
		this.custProperty = custProperty;
	}
	/**
	 * @return coopIntention : return the property coopIntention.
	 */
	public String getCoopIntention() {
		return coopIntention;
	}
	/**
	 * @param coopIntention : set the property coopIntention.
	 */
	public void setCoopIntention(String coopIntention) {
		this.coopIntention = coopIntention;
	}
	/**
	 * @return bizStatus : return the property bizStatus.
	 */
	public String getBizStatus() {
		return bizStatus;
	}
	/**
	 * @param bizStatus : set the property bizStatus.
	 */
	public void setBizStatus(String bizStatus) {
		this.bizStatus = bizStatus;
	}
	/**
	 * @return custSource : return the property custSource.
	 */
	public String getCustSource() {
		return custSource;
	}
	/**
	 * @param custSource : set the property custSource.
	 */
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}
	/**
	 * @return goodsPotential : return the property goodsPotential.
	 */
	public String getGoodsPotential() {
		return goodsPotential;
	}
	/**
	 * @param goodsPotential : set the property goodsPotential.
	 */
	public void setGoodsPotential(String goodsPotential) {
		this.goodsPotential = goodsPotential;
	}
	/**
	 * @return custType : return the property custType.
	 */
	public String getCustType() {
		return custType;
	}
	/**
	 * @param custType : set the property custType.
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}
	/**
	 * @return industry : return the property industry.
	 */
	public String getIndustry() {
		return industry;
	}
	/**
	 * @param industry : set the property industry.
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
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
	 * @return sheduleTime : return the property sheduleTime.
	 */
	public Date getSheduleTime() {
		return sheduleTime;
	}
	/**
	 * @param sheduleTime : set the property sheduleTime.
	 */
	public void setSheduleTime(Date sheduleTime) {
		this.sheduleTime = sheduleTime;
	}
	/**
	 * @return status : return the property status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status : set the property status.
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return overTime : return the property overTime.
	 */
	public Date getOverTime() {
		return overTime;
	}
	/**
	 * @param overTime : set the property overTime.
	 */
	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}
	/**
	 * @return beginShipWeight : return the property beginShipWeight.
	 */
	public double getBeginShipWeight() {
		return beginShipWeight;
	}
	/**
	 * @param beginShipWeight : set the property beginShipWeight.
	 */
	public void setBeginShipWeight(double beginShipWeight) {
		this.beginShipWeight = beginShipWeight;
	}
	/**
	 * @return overShipWeight : return the property overShipWeight.
	 */
	public double getOverShipWeight() {
		return overShipWeight;
	}
	/**
	 * @param overShipWeight : set the property overShipWeight.
	 */
	public void setOverShipWeight(double overShipWeight) {
		this.overShipWeight = overShipWeight;
	}
	/**
	 * @return beginShipVotes : return the property beginShipVotes.
	 */
	public Integer getBeginShipVotes() {
		return beginShipVotes;
	}
	/**
	 * @param beginShipVotes : set the property beginShipVotes.
	 */
	public void setBeginShipVotes(Integer beginShipVotes) {
		this.beginShipVotes = beginShipVotes;
	}
	/**
	 * @return overShipVotes : return the property overShipVotes.
	 */
	public Integer getOverShipVotes() {
		return overShipVotes;
	}
	/**
	 * @param overShipVotes : set the property overShipVotes.
	 */
	public void setOverShipVotes(Integer overShipVotes) {
		this.overShipVotes = overShipVotes;
	}
	/**
	 * @return beginShipAmount : return the property beginShipAmount.
	 */
	public double getBeginShipAmount() {
		return beginShipAmount;
	}
	/**
	 * @param beginShipAmount : set the property beginShipAmount.
	 */
	public void setBeginShipAmount(double beginShipAmount) {
		this.beginShipAmount = beginShipAmount;
	}
	/**
	 * @return overShipAmount : return the property overShipAmount.
	 */
	public double getOverShipAmount() {
		return overShipAmount;
	}
	/**
	 * @param overShipAmount : set the property overShipAmount.
	 */
	public void setOverShipAmount(double overShipAmount) {
		this.overShipAmount = overShipAmount;
	}
	/**
	 * @return beginArrivalWeight : return the property beginArrivalWeight.
	 */
	public double getBeginArrivalWeight() {
		return beginArrivalWeight;
	}
	/**
	 * @param beginArrivalWeight : set the property beginArrivalWeight.
	 */
	public void setBeginArrivalWeight(double beginArrivalWeight) {
		this.beginArrivalWeight = beginArrivalWeight;
	}
	/**
	 * @return overArrivalWeight : return the property overArrivalWeight.
	 */
	public double getOverArrivalWeight() {
		return overArrivalWeight;
	}
	/**
	 * @param overArrivalWeight : set the property overArrivalWeight.
	 */
	public void setOverArrivalWeight(double overArrivalWeight) {
		this.overArrivalWeight = overArrivalWeight;
	}
	/**
	 * @return beginArrivalVotes : return the property beginArrivalVotes.
	 */
	public Integer getBeginArrivalVotes() {
		return beginArrivalVotes;
	}
	/**
	 * @param beginArrivalVotes : set the property beginArrivalVotes.
	 */
	public void setBeginArrivalVotes(Integer beginArrivalVotes) {
		this.beginArrivalVotes = beginArrivalVotes;
	}
	/**
	 * @return overArrivalVotes : return the property overArrivalVotes.
	 */
	public Integer getOverArrivalVotes() {
		return overArrivalVotes;
	}
	/**
	 * @param overArrivalVotes : set the property overArrivalVotes.
	 */
	public void setOverArrivalVotes(Integer overArrivalVotes) {
		this.overArrivalVotes = overArrivalVotes;
	}
	/**
	 * @return beginArrivalAmount : return the property beginArrivalAmount.
	 */
	public double getBeginArrivalAmount() {
		return beginArrivalAmount;
	}
	/**
	 * @param beginArrivalAmount : set the property beginArrivalAmount.
	 */
	public void setBeginArrivalAmount(double beginArrivalAmount) {
		this.beginArrivalAmount = beginArrivalAmount;
	}
	/**
	 * @return overArrivalAmount : return the property overArrivalAmount.
	 */
	public double getOverArrivalAmount() {
		return overArrivalAmount;
	}
	/**
	 * @param overArrivalAmount : set the property overArrivalAmount.
	 */
	public void setOverArrivalAmount(double overArrivalAmount) {
		this.overArrivalAmount = overArrivalAmount;
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
	 * @return position : return the property position.
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position : set the property position.
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return deptIds : return the property deptIds.
	 */
	public Set<String> getDeptIds() {
		return deptIds;
	}
	/**
	 * @param deptIds : set the property deptIds.
	 */
	public void setDeptIds(Set<String> deptIds) {
		this.deptIds = deptIds;
	}
	
}
