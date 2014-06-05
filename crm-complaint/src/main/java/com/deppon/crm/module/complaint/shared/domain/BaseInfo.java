package com.deppon.crm.module.complaint.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * Description:工单基础资料<br />
 * </p>
 * @title Bulletin.java
 * @package com.deppon.crm.module.complaint.shared.domain 
 * @author 侯斌飞
 * @version 0.1 2013-09-13
 */
public class BaseInfo extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	//基础资料父Id
	private Integer parentId;
	//基础资料层级
	private Integer level;
	//层级名称
	private String levelName;
	//基础资料
	private String baseInfo;
	//基础资料分类，默认 COMPLAINT:工单,责任：DUTY
	private String classCode;
	//基础资料类型,默认 COMPLAINT:投诉,异常：UNUSUAL
	private String typeCode;
	//业务类型-处理语言
	private String deallan;
	//业务类型-反馈时限
	private String feedbackLimit;
	//业务类型-处理时限
	private String procLimit;
	//场景原因-处理标准
	private String procStandard;
	//是否为叶子节点 0否 1是
	private Integer isLeaf;
	
	public Integer getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * @return the levelName
	 */
	public String getLevelName() {
		return levelName;
	}
	/**
	 * @param levelName the levelName to set
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	/**
	 * @return the baseInfo
	 */
	public String getBaseInfo() {
		return baseInfo;
	}
	/**
	 * @param baseInfo the baseInfo to set
	 */
	public void setBaseInfo(String baseInfo) {
		this.baseInfo = baseInfo;
	}
	/**
	 * @return the classCode
	 */
	public String getClassCode() {
		return classCode;
	}
	/**
	 * @param classCode the classCode to set
	 */
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}
	/**
	 * @param typeCode the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	/**
	 * @return the deallan
	 */
	public String getDeallan() {
		return deallan;
	}
	/**
	 * @param deallan the deallan to set
	 */
	public void setDeallan(String deallan) {
		this.deallan = deallan;
	}
	/**
	 * @return the feedbackLimit
	 */
	public String getFeedbackLimit() {
		return feedbackLimit;
	}
	/**
	 * @param feedbackLimit the feedbackLimit to set
	 */
	public void setFeedbackLimit(String feedbackLimit) {
		this.feedbackLimit = feedbackLimit;
	}
	/**
	 * @return the procLimit
	 */
	public String getProcLimit() {
		return procLimit;
	}
	/**
	 * @param procLimit the procLimit to set
	 */
	public void setProcLimit(String procLimit) {
		this.procLimit = procLimit;
	}
	/**
	 * @return the procStandard
	 */
	public String getProcStandard() {
		return procStandard;
	}
	/**
	 * @param procStandard the procStandard to set
	 */
	public void setProcStandard(String procStandard) {
		this.procStandard = procStandard;
	}
	
	
}
