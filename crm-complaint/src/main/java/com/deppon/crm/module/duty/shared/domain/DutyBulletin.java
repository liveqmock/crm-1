package com.deppon.crm.module.duty.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * <p>
 * Description:通报对象<br />
 * </p>
 * @title Bulletin.java
 * @package com.deppon.crm.module.complaint.shared.domain 
 * @author ouy
 * @version 0.1 2012-4-17
 */
public class DutyBulletin {
	private BigDecimal id;
	private Date createtime;
	private Integer createuserid;
	//最后修改时间
	private Date lastupdatetime;
	//最后修改人
	private BigDecimal lastmodifyuserid;
	//投诉人id
	private BigDecimal compid;
	//通报对象id
	private BigDecimal bulletinid;
	//通报对象名称
	private String bulletinname;
	//通报对象工号
	private String bulletinJobId;
	
	//通报对象联系电话
	private String bulletinTel;
	//通报对象所在部门
	private String bulletinDeptName;
	//职位
	private String position;
	//处理类型
	private String dealType;
	// 任务号(用于与任务部门做关联，为空时表示无关联)
	private String jobCode;
	
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getBulletinJobId() {
		return bulletinJobId;
	}
	public void setBulletinJobId(String bulletinJobId) {
		this.bulletinJobId = bulletinJobId;
	}
	public String getBulletinTel() {
		return bulletinTel;
	}
	public void setBulletinTel(String bulletinTel) {
		this.bulletinTel = bulletinTel;
	}
	public String getBulletinDeptName() {
		return bulletinDeptName;
	}
	public void setBulletinDeptName(String bulletinDeptName) {
		this.bulletinDeptName = bulletinDeptName;
	}
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Integer getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}
	public Date getLastupdatetime() {
		return lastupdatetime;
	}
	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getLastmodifyuserid() {
		return lastmodifyuserid;
	}
	public void setLastmodifyuserid(BigDecimal lastmodifyuserid) {
		this.lastmodifyuserid = lastmodifyuserid;
	}
	public BigDecimal getCompid() {
		return compid;
	}
	public void setCompid(BigDecimal compid) {
		this.compid = compid;
	}
	public BigDecimal getBulletinid() {
		return bulletinid;
	}
	public void setBulletinid(BigDecimal bulletinid) {
		this.bulletinid = bulletinid;
	}
	public String getBulletinname() {
		return bulletinname;
	}
	public void setBulletinname(String bulletinname) {
		this.bulletinname = bulletinname;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	
}
