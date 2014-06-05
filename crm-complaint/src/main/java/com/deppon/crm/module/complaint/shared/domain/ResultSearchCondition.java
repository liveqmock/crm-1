package com.deppon.crm.module.complaint.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * Description:处理结果表查询条件<br />
 * </p>
 * @title Bulletin.java
 * @package com.deppon.crm.module.complaint.shared.domain 
 * @author ouy
 * @version 0.1 2012-4-17
 */
public class ResultSearchCondition extends BaseEntity{
	private BigDecimal fid;
	// 创建时间
	private Date createtime;
	// 创建用户
	private Integer createuserid;
	//最后修改时间
	private Date lastupdatetime;
	//最后修改人
	private BigDecimal lastmodifyuserid;
	//投诉id
	private BigDecimal complainid;
	//处理事项
	private String dealmatters;
	//任务部门Id
	private BigDecimal taskpartmentid;
	//任务属性
	private String taskproperties;
	//反馈时限
	private BigDecimal feedtimelimit;
	//处理时间
	private BigDecimal processtimelimit;
	//申请延时
	private BigDecimal appdelay;
	//处理结果
	private String results;
	//处理记录
	private String prorecord;
	
	
	//是否超时
	private String ifovertime;
	//是否到期
	private String ifmaturity;
	//处理人
	private String dealman;
	//处理时间
	private Date dealtime;	
	//状态
	private String stat;
	//是否延时申请有效 Y/N
	private String delay;
	/**
	 * set get 方法
	 */
	// delay get方法
	public String getDelay() {
		return delay;
	}
	// delay set方法
	public void setDelay(String delay) {
		this.delay = delay;
	}
	// stat get方法
	public String getStat() {
		return stat;
	}
	// stat set方法
	public void setStat(String stat) {
		this.stat = stat;
	}
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
	// complainid get方法
	public BigDecimal getComplainid() {
		return complainid;
	}
	// complainid set方法
	public void setComplainid(BigDecimal complainid) {
		this.complainid = complainid;
	}
	// dealmatters set方法
	public String getDealmatters() {
		return dealmatters;
	}
	// dealmatters set方法
	public void setDealmatters(String dealmatters) {
		this.dealmatters = dealmatters;
	}
	// taskpartmentid get方法
	public BigDecimal getTaskpartmentid() {
		return taskpartmentid;
	}
	// taskpartmentid set方法
	public void setTaskpartmentid(BigDecimal taskpartmentid) {
		this.taskpartmentid = taskpartmentid;
	}
	// taskproperties get方法
	public String getTaskproperties() {
		return taskproperties;
	}
	// taskproperties set方法
	public void setTaskproperties(String taskproperties) {
		this.taskproperties = taskproperties;
	}
	// feedtimelimit get方法
	public BigDecimal getFeedtimelimit() {
		return feedtimelimit;
	}
	// feedtimelimit set方法
	public void setFeedtimelimit(BigDecimal feedtimelimit) {
		this.feedtimelimit = feedtimelimit;
	}
	// appdelay get方法
	public BigDecimal getAppdelay() {
		return appdelay;
	}
	// appdelay set方法
	public void setAppdelay(BigDecimal appdelay) {
		this.appdelay = appdelay;
	}
	// results get方法
	public String getResults() {
		return results;
	}
	// results set方法
	public void setResults(String results) {
		this.results = results;
	}
	// prorecord get方法
	public String getProrecord() {
		return prorecord;
	}
	// prorecord set方法
	public void setProrecord(String prorecord) {
		this.prorecord = prorecord;
	}
	// ifovertime get方法
	public String getIfovertime() {
		return ifovertime;
	}
	// ifovertime set方法
	public void setIfovertime(String ifovertime) {
		this.ifovertime = ifovertime;
	}
	// ifmaturity get方法
	public String getIfmaturity() {
		return ifmaturity;
	}
	// ifmaturity set方法
	public void setIfmaturity(String ifmaturity) {
		this.ifmaturity = ifmaturity;
	}
	// dealman get方法
	public String getDealman() {
		return dealman;
	}
	// dealman set方法
	public void setDealman(String dealman) {
		this.dealman = dealman;
	}
	// dealtime get方法
	public Date getDealtime() {
		return dealtime;
	}
	// dealtime set方法
	public void setDealtime(Date dealtime) {
		this.dealtime = dealtime;
	}
	// processtimelimit get方法
	public BigDecimal getProcesstimelimit() {
		return processtimelimit;
	}
	// processtimelimit set方法
	public void setProcesstimelimit(BigDecimal processtimelimit) {
		this.processtimelimit = processtimelimit;
	}
}
