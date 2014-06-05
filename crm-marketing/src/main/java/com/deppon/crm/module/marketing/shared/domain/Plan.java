/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DevelopPlan.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author Administrator
 * @version 0.1 2012-3-23
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;
import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

;
/**
 * <p>
 * 开发计划实体<br />
 * </p>
 * 
 * @title DevelopPlan.java
 * @package com.deppon.crm.module.marketing.shared.domain
 * @author 苏玉军
 * @version 0.1 2012-3-23
 */

public class Plan extends BaseEntity{

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -1928921192378913789L;

	//创建时间
	private Date createTime;

	//创建人
	private String createUserId;

	//最后修改时间
	private Date lastUpdateTime;

	//最后修改人
	private String lastModifyUserId;

	//开发维护主题
	private String topic;

	//开发维护活动描述
	private String activedesc;

	//执行部门
	private String execdeptid;

	//执行人员
	private String execuserid;

	//开发维护方式
	private String way;

	//开发维护状态
	private String status;

	//客户类型
	private String custType;

	//开始时间
	private Date beginTime;

	//结束时间
	private Date endTime;

	//计划类型
	private String planType;
	
	//联系人id
	private String contactId;
	
	//日程列表
	private List<Schedule> scheduleList;

	//客户id
	private List<Integer> custIds;
	//问卷ID
	private String surveyId;
	//计划来源对应表ID  大客户开发  客户流失预警 市场推广活动
	private String comeFormId;
	//计划来源  流失预警维护  大客户开发 市场推广活动
	private String comeForm;
	/**
	 * 增加业务类型
	 * auth:lichunyu
	 * date:2014-01-13
	 */
	private String projectType;
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
	 * @return topic : return the property topic.
	 */
	public String getTopic() {
		return topic;
	}


	/**
	 * @param topic : set the property topic.
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}


	/**
	 * @return activedesc : return the property activedesc.
	 */
	public String getActivedesc() {
		return activedesc;
	}


	/**
	 * @param activedesc : set the property activedesc.
	 */
	public void setActivedesc(String activedesc) {
		this.activedesc = activedesc;
	}


	/**
	 * @return execdeptid : return the property execdeptid.
	 */
	public String getExecdeptid() {
		return execdeptid;
	}


	/**
	 * @param execdeptid : set the property execdeptid.
	 */
	public void setExecdeptid(String execdeptid) {
		this.execdeptid = execdeptid;
	}


	/**
	 * @return execuserid : return the property execuserid.
	 */
	public String getExecuserid() {
		return execuserid;
	}


	/**
	 * @param execuserid : set the property execuserid.
	 */
	public void setExecuserid(String execuserid) {
		this.execuserid = execuserid;
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
	 * @return planType : return the property planType.
	 */
	public String getPlanType() {
		return planType;
	}


	/**
	 * @param planType : set the property planType.
	 */
	public void setPlanType(String planType) {
		this.planType = planType;
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
	 * @return scheduleList : return the property scheduleList.
	 */
	public List<Schedule> getScheduleList() {
		return scheduleList;
	}


	/**
	 * @param scheduleList : set the property scheduleList.
	 */
	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}


	/**
	 * @return custIds : return the property custIds.
	 */
	public List<Integer> getCustIds() {
		return custIds;
	}


	/**
	 * @param custIds : set the property custIds.
	 */
	public void setCustIds(List<Integer> custIds) {
		this.custIds = custIds;
	}


	public String getProjectType() {
		return projectType;
	}


	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}


	

	public String getSurveyId() {
		return surveyId;
	}


	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}


	public String getComeFormId() {
		return comeFormId;
	}


	public void setComeFormId(String comeFormId) {
		this.comeFormId = comeFormId;
	}


	public String getComeForm() {
		return comeForm;
	}


	public void setComeForm(String comeForm) {
		this.comeForm = comeForm;
	}


	/**
	 * toString重写
	 */
	@Override
	public String toString() {
		//字符串构造器
		StringBuilder sb = new StringBuilder();
		//组装字符串
		sb.append("[id=").append(super.getId())
		.append(",theme=").append(topic)
		.append(",status=").append(status)
		.append(",schedule.size=").append(scheduleList == null ? 0 : scheduleList.size()).append("]");
		//返回结果
		return sb.toString();
	}
}
