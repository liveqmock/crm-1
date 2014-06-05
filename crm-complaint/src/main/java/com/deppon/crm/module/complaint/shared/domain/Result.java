package com.deppon.crm.module.complaint.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * Description:处理结果表<br />
 * </p>
 * @title Bulletin.java
 * @package com.deppon.crm.module.complaint.shared.domain 
 * @author ouy
 * @version 0.1 2012-4-17
 */
public class Result extends BaseEntity{
	// 主键id
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
	//任务部门名称
	private String ftaskDeptName;
	//延时申请状态（0：申请延时 1：申请同意 2：申请拒绝）
	private String delay;
	//是否有过退回
	private String isBack;
	//剩余处理时限
	private Integer lastprocessltimeimit;
	//剩余反馈时限
	private Integer lastfeedtimelimit;
	//处理人类型 
	private String dealType;
	//延时申请原因
	private String postponeReason;
	//处理部门选“个人”的个人userId（非持久化字段）
	private String personUserId;
	// 任务号(用于与通知对象做关联)
	private String jobCode;
	// 部门类型（0 任务部门  1出发部门）
	private String deptType;
	
	//场景原因-处理标准
	private String procStandard;
	
	// jobCode get方法
	public String getJobCode() {
		return jobCode;
	}
	// jobCode set方法
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	// dealType get方法
	public String getDealType() {
		return dealType;
	}
	// dealType set方法
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	// isBack get方法
	public String getIsBack() {
		return isBack;
	}
	// isBack set方法
	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}
	// delay get方法
	public String getDelay() {
		return delay;
	}
	// delay set方法
	public void setDelay(String delay) {
		this.delay = delay;
	}
	// ftaskDeptName get方法
	public String getFtaskDeptName() {
		return ftaskDeptName;
	}
	// ftaskDeptName set方法
	public void setFtaskDeptName(String ftaskDeptName) {
		this.ftaskDeptName = ftaskDeptName;
	}
	// ftaskDeptName set方法
	public String getStat() {
		return stat;
	}
	// ftaskDeptName set方法
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
	// dealmatters get方法
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
	// ifmaturity get方法
	public String getDealman() {
		return dealman;
	}
	// ifmaturity set方法
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
	// lastprocessltimeimit get方法
	public Integer getLastprocessltimeimit() {
		return lastprocessltimeimit;
	}
	// lastprocessltimeimit set方法
	public void setLastprocessltimeimit(Integer lastprocessltimeimit) {
		this.lastprocessltimeimit = lastprocessltimeimit;
	}
	// lastprocessltimeimit get方法
	public Integer getLastfeedtimelimit() {
		return lastfeedtimelimit;
	}
	// lastprocessltimeimit set方法
	public void setLastfeedtimelimit(Integer lastfeedtimelimit) {
		this.lastfeedtimelimit = lastfeedtimelimit;
	}
	// postponeReason get方法
	public String getPostponeReason() {
		return postponeReason;
	}
	// postponeReason set方法
	public void setPostponeReason(String postponeReason) {
		this.postponeReason = postponeReason;
	}
	// personUserId get方法
    public String getPersonUserId() {
		return personUserId;
	}
 // personUserId set方法
	public void setPersonUserId(String personUserId) {
		this.personUserId = personUserId;
	}
	
	public String getDeptType() {
		return deptType;
	}
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}
	// 重写equals方法
	public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass().getPackage() != obj.getClass().getPackage()) {
            return false;
        }
        if (Result.class.isAssignableFrom((obj.getClass()))) {
            final Result other = (Result) obj;
            if (fid == null) {
                if (other.getFid() != null) {
                    return false;
                }
            } else if (!fid.equals(other.getFid())) {
                return false;
            }
            return true;
        }
        return false;
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
