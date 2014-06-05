package com.deppon.crm.module.complaint.shared.domain;

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
public class Bulletin {
	private BigDecimal fid;
	// 创建时间
	private Date createtime;
	// 创建用户
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
	//剩余处理时限
	private Integer processtimelimit;
	//剩余反馈时限
	private Integer feedtimelimit;
	
	
	
	/**
	 * @return the processtimelimit
	 */
	public Integer getProcesstimelimit() {
		return processtimelimit;
	}
	/**
	 * @param processtimelimit the processtimelimit to set
	 */
	public void setProcesstimelimit(Integer processtimelimit) {
		this.processtimelimit = processtimelimit;
	}
	/**
	 * @return the feedtimelimit
	 */
	public Integer getFeedtimelimit() {
		return feedtimelimit;
	}
	/**
	 * @param feedtimelimit the feedtimelimit to set
	 */
	public void setFeedtimelimit(Integer feedtimelimit) {
		this.feedtimelimit = feedtimelimit;
	}
	// jobCode get方法
	public String getJobCode() {
		return jobCode;
	}
	// jobCode set方法
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	// bulletinJobId get方法
	public String getBulletinJobId() {
		return bulletinJobId;
	}
	// bulletinJobId set方法
	public void setBulletinJobId(String bulletinJobId) {
		this.bulletinJobId = bulletinJobId;
	}
	// bulletinTel get方法
	public String getBulletinTel() {
		return bulletinTel;
	}
	// bulletinTel set方法
	public void setBulletinTel(String bulletinTel) {
		this.bulletinTel = bulletinTel;
	}
	// bulletinDeptName get方法
	public String getBulletinDeptName() {
		return bulletinDeptName;
	}
	// bulletinDeptName set方法
	public void setBulletinDeptName(String bulletinDeptName) {
		this.bulletinDeptName = bulletinDeptName;
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
	public BigDecimal getFid() {
		return fid;
	}
	public void setFid(BigDecimal fid) {
		this.fid = fid;
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
