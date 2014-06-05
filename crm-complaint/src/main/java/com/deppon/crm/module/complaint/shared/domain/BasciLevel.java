package com.deppon.crm.module.complaint.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * <p>
 * Description:基础层级<br />
 * </p>
 * 
 * @title Bulletin.java
 * @package com.deppon.crm.module.complaint.shared.domain
 * @author ouy
 * @version 0.1 2012-4-17
 */
public class BasciLevel {
	private BigDecimal fid;
	// 创建时间
	private Date createtime;
	// 创建用户
	private Integer createuserid;
	// 最后修改时间
	private Date lastupdatetime;
	// 最后修改人
	private BigDecimal lastmodifyuserid;
	// 父id
	private BigDecimal parentid;
	// 层级名称
	private String levelname;
	// 层级级别
	private String level;
	// 层级名称
	private String bascilevelname;
	// 上报类型
	private String type;
	// 是否使用
	private String useYn;
	// fid get方法
	public BigDecimal getFid() {
		return fid;
	}
	// fid set方法
	public void setFid(BigDecimal fid) {
		this.fid = fid;
	}
	// createtime get方法
	public Date getCreatetime() {
		return createtime;
	}
	// createtime set方法
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	// createuserid get方法
	public Integer getCreateuserid() {
		return createuserid;
	}
	// createuserid set方法
	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}
	// lastupdatetime get方法
	public Date getLastupdatetime() {
		return lastupdatetime;
	}
	// lastupdatetime set方法
	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}
	// lastmodifyuserid get方法
	public BigDecimal getLastmodifyuserid() {
		return lastmodifyuserid;
	}
	// lastmodifyuserid set方法
	public void setLastmodifyuserid(BigDecimal lastmodifyuserid) {
		this.lastmodifyuserid = lastmodifyuserid;
	}
	// parentid get方法
	public BigDecimal getParentid() {
		return parentid;
	}
	// parentid set方法
	public void setParentid(BigDecimal parentid) {
		this.parentid = parentid;
	}
	// levelname get方法
	public String getLevelname() {
		return levelname;
	}
	// levelname set方法
	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}
	// level get方法
	public String getLevel() {
		return level;
	}
	// level set方法
	public void setLevel(String level) {
		this.level = level;
	}
	// bascilevelname get方法
	public String getBascilevelname() {
		return bascilevelname;
	}
	// bascilevelname set方法
	public void setBascilevelname(String bascilevelname) {
		this.bascilevelname = bascilevelname;
	}
	// type get方法
	public String getType() {
		return type;
	}
	// type set方法
	public void setType(String type) {
		this.type = type;
	}
	// useYn set方法
	public String getUseYn() {
		return useYn;
	}
	// useYn set方法
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

}
