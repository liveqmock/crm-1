package com.deppon.crm.module.duty.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

public class BasicLevel extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//创建时间
	private Date createTime;
	private String createUserId;
	// 最后修改时间
	private Date lastUpdateTime;
	// 最后修改人
	private String lastModifyUserId;
	// 父id
	private String parentId;
	// 层级名称
	private String levelName;
	// 层级级别
	private String level;
	// 层级名称
	private String basciLevelName;
	// 上报类型
	private String type;
	// 是否使用
	private String useYn;
	//责任与工单表示
	private String mark;
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
	 * @return parentId : return the property parentId.
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * @param parentId : set the property parentId.
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return levelName : return the property levelName.
	 */
	public String getLevelName() {
		return levelName;
	}
	/**
	 * @param levelName : set the property levelName.
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	/**
	 * @return level : return the property level.
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level : set the property level.
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * @return basciLevelName : return the property basciLevelName.
	 */
	public String getBasciLevelName() {
		return basciLevelName;
	}
	/**
	 * @param basciLevelName : set the property basciLevelName.
	 */
	public void setBasciLevelName(String basciLevelName) {
		this.basciLevelName = basciLevelName;
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
	 * @return useYn : return the property useYn.
	 */
	public String getUseYn() {
		return useYn;
	}
	/**
	 * @param useYn : set the property useYn.
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	/**
	 * @return mark : return the property mark.
	 */
	public String getMark() {
		return mark;
	}
	/**
	 * @param mark : set the property mark.
	 */
	public void setMark(String mark) {
		this.mark = mark;
	}
	/**
	 * @return serialversionuid : return the property serialversionuid.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
