package com.deppon.crm.module.complaint.shared.domain;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * <p>
 * Description:基础资料处理结果表<br />
 * </p>
 * @title ProcResult.java
 * @package com.deppon.crm.module.complaint.shared.domain 
 * @author ouy
 * @version 0.1 2012-4-18
 */
public class ProcResult {
	// 主键id
	private BigDecimal fid;
	// 创建时间
	private Date createtime;
	// 创建用户
	private BigDecimal createuserid;
	//最后修改时间
	private Date lastupdatetime;
	//最后修改人
	private BigDecimal lastmodifyuserid;
	//基础资料层级id
	private BigDecimal levelid;
	//处理语言
	private String deallan;
	//反馈时限
	private String feedbacklimit;
	//处理时限
	private String proclimit;
	//业务类型
	private String busType;
	/**
	 * set get方法
	 */
	//fid get方法
	public BigDecimal getFid() {
		return fid;
	}
	//fid set方法
	public void setFid(BigDecimal fid) {
		this.fid = fid;
	}
	//createtime get方法
	public Date getCreatetime() {
		return createtime;
	}
	//createtime set方法
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	//lastupdatetime get方法
	public Date getLastupdatetime() {
		return lastupdatetime;
	}
	//lastupdatetime set方法
	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}
	//lastmodifyuserid get方法
	public BigDecimal getLastmodifyuserid() {
		return lastmodifyuserid;
	}
	//lastmodifyuserid set方法
	public void setLastmodifyuserid(BigDecimal lastmodifyuserid) {
		this.lastmodifyuserid = lastmodifyuserid;
	}
	//levelid get方法
	public BigDecimal getLevelid() {
		return levelid;
	}
	//levelid set方法
	public void setLevelid(BigDecimal levelid) {
		this.levelid = levelid;
	}
	//deallan get方法
	public String getDeallan() {
		return deallan;
	}
	//deallan set方法
	public void setDeallan(String deallan) {
		this.deallan = deallan;
	}
	//feedbacklimit get方法
	public String getFeedbacklimit() {
		return feedbacklimit;
	}
	//feedbacklimit set方法
	public void setFeedbacklimit(String feedbacklimit) {
		this.feedbacklimit = feedbacklimit;
	}
	//proclimit get方法
	public String getProclimit() {
		return proclimit;
	}
	//proclimit set方法
	public void setProclimit(String proclimit) {
		this.proclimit = proclimit;
	}
	//createuserid set方法
	public void setCreateuserid(BigDecimal createuserid) {
		this.createuserid = createuserid;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	
}
