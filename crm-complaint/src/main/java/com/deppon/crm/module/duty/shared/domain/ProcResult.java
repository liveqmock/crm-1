package com.deppon.crm.module.duty.shared.domain;

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
	private BigDecimal fid;
	private Date createtime;
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
	//奖罚类型
	private String reworpusType;
	public BigDecimal getFid() {
		return fid;
	}
	public void setFid(BigDecimal fid) {
		this.fid = fid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getLastupdatetime() {
		return lastupdatetime;
	}
	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}
	public BigDecimal getLastmodifyuserid() {
		return lastmodifyuserid;
	}
	public void setLastmodifyuserid(BigDecimal lastmodifyuserid) {
		this.lastmodifyuserid = lastmodifyuserid;
	}
	public BigDecimal getLevelid() {
		return levelid;
	}
	public void setLevelid(BigDecimal levelid) {
		this.levelid = levelid;
	}
	public String getDeallan() {
		return deallan;
	}
	public void setDeallan(String deallan) {
		this.deallan = deallan;
	}
	public String getFeedbacklimit() {
		return feedbacklimit;
	}
	public void setFeedbacklimit(String feedbacklimit) {
		this.feedbacklimit = feedbacklimit;
	}
	public String getProclimit() {
		return proclimit;
	}
	public void setProclimit(String proclimit) {
		this.proclimit = proclimit;
	}
	public void setCreateuserid(BigDecimal createuserid) {
		this.createuserid = createuserid;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	/**
	 * @return the reworpusType
	 */
	public String getReworpusType() {
		return reworpusType;
	}
	/**
	 * @param reworpusType the reworpusType to set
	 */
	public void setReworpusType(String reworpusType) {
		this.reworpusType = reworpusType;
	}
	/**
	 * @return the createuserid
	 */
	public BigDecimal getCreateuserid() {
		return createuserid;
	}
	
}
