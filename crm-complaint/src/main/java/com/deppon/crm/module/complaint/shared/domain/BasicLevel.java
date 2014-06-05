package com.deppon.crm.module.complaint.shared.domain;

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
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getLastModifyUserId() {
		return lastModifyUserId;
	}
	public void setLastModifyUserId(String lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getBasciLevelName() {
		return basciLevelName;
	}
	public void setBasciLevelName(String basciLevelName) {
		this.basciLevelName = basciLevelName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
