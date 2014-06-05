package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * <p>
 * 开发日程实体<br />
 * </p>
 * 
 * @title DevelopSchedule.java
 * @package com.deppon.crm.module.marketing.shared.domain
 * @author 苏玉军
 * @version 0.1 2012-3-23
 */

public class Schedule extends BaseEntity{

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -4144880269832436821L;

	//创建时间
	private Date createTime;

	//创建人
	private String createUserId;

	//	最后修改时间
	private Date lastUpdateTime;
	//最后修改人
	private String lastModifyUserId;
	//开发维护计划ID
	private String planId;
	//客户ID
	private String custId;
	//执行人
	private String exeManId;
	//执行部门
	private  String exeDeptId;
	//客户类型
	private String custType;
	//日程时间
	private Date time;
	//日程状态
	private String status;
	//日程类型
	private String type;
	//联系人id
	private String contactId;
	//日程备注
	private String remark;
	//问卷
	private String surveyId;
	//日程来源
	private String comeForm;
	//日程来源ID
	private String comeFormId;
	/**
	 * 重写toString方法
	 */
	@Override
	public String toString() {
		//StringBuilder
		StringBuilder sb = new StringBuilder();
		//拼装字符串
		sb.append("[id=").append(super.getId())
		//计划ＩＤ
		.append(", planId=").append(planId)
		//计划时间
		.append(", planTime=").append(time)
		//状态
		.append(", status=").append(status);
		//返回结果
		return sb.toString();
	}

	/**
	 * @return createTime : return the property createTime.
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime : set the property createTime.
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return createUserId : return the property createUserId.
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId : set the property createUserId.
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return lastUpdateTime : return the property lastUpdateTime.
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param lastUpdateTime : set the property lastUpdateTime.
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * @return lastModifyUserId : return the property lastModifyUserId.
	 */
	public String getLastModifyUserId() {
		return lastModifyUserId;
	}

	/**
	 * @param lastModifyUserId : set the property lastModifyUserId.
	 */
	public void setLastModifyUserId(String lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
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
	 * @return exeManId : return the property exeManId.
	 */
	public String getExeManId() {
		return exeManId;
	}

	/**
	 * @param exeManId : set the property exeManId.
	 */
	public void setExeManId(String exeManId) {
		this.exeManId = exeManId;
	}

	/**
	 * @return exeDeptId : return the property exeDeptId.
	 */
	public String getExeDeptId() {
		return exeDeptId;
	}

	/**
	 * @param exeDeptId : set the property exeDeptId.
	 */
	public void setExeDeptId(String exeDeptId) {
		this.exeDeptId = exeDeptId;
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
	 * @return time : return the property time.
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time : set the property time.
	 */
	public void setTime(Date time) {
		this.time = time;
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
	 * @return contactId : return the property contactId.
	 */
	public String getContactId() {
		return contactId;
	}

	/**
	 * @param contactId : set the property contactId.
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	/**
	 * @return remark : return the property remark.
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark : set the property remark.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	public String getComeForm() {
		return comeForm;
	}

	public void setComeForm(String comeForm) {
		this.comeForm = comeForm;
	}

	public String getComeFormId() {
		return comeFormId;
	}

	public void setComeFormId(String comeFormId) {
		this.comeFormId = comeFormId;
	}
	
}
