package com.deppon.crm.module.complaint.shared.domain;

/**
 * 
 * <p>
 * Description:基础资料查询实体
 * </p>
 * @title BaseInfoSearchCondition.java
 * @package com.deppon.crm.module.complaint.shared.domain 
 * @author LiuY
 * @version 0.1 2013-9-23
 */
public class BaseInfoSearchCondition{

	//基础资料父I
	private Integer parentId;
	//基础资料层级
	private Integer level;
	//基础资料
	private String baseInfo;
	//基础资料分类，默认 complaint:工单
	private String classCode;
	//基础资料类型,默认 complaint:投诉
	private String typeCode;
	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * @return baseInfo : return the property baseInfo.
	 */
	public String getBaseInfo() {
		return baseInfo;
	}
	/**
	 * @param baseInfo : set the property baseInfo.
	 */
	public void setBaseInfo(String baseInfo) {
		this.baseInfo = baseInfo;
	}
	/**
	 * @return classCode : return the property classCode.
	 */
	public String getClassCode() {
		return classCode;
	}
	/**
	 * @param classCode : set the property classCode.
	 */
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	/**
	 * @return typeCode : return the property typeCode.
	 */
	public String getTypeCode() {
		return typeCode;
	}
	/**
	 * @param typeCode : set the property typeCode.
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
}
