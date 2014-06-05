package com.deppon.crm.module.complaint.shared.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:投诉通知信息类<br />
 * </p>
 * 
 * @title ComplaintInfo.java
 * @package com.deppon.crm.module.complaint.shared.domain
 * @author justin.xu
 * @version 0.1 2012-6-30
 */
public class ComplaintInfo implements Serializable{
	private static final long serialVersionUID = 3224069151209903038L;
	// id
	private Integer id;
	// 创建时间
	private Date createtime;
	// 创建用户
	private Integer createuserid;
	// 最后修改时间
	private Date lastupdatetime;
	// 最后修改人
	private BigDecimal lastmodifyuserid;
	// 员工编号
	private String empCode;
	// 投诉数量
	private Integer complaintCount;
	// 是否发送
	private String isSend;
	// 失败次数
	private Integer failureCount;
	//每次查出多少条
	private Integer perCount;
	/**
	 * set get方法
	 */
	//id get方法
	public Integer getId() {
		return id;
	}
	//id set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//createtime get方法
	public Date getCreatetime() {
		return createtime;
	}
	//createtime set方法
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	//createuserid get方法
	public Integer getCreateuserid() {
		return createuserid;
	}
	//createuserid get方法
	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
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
	//empCode get方法
	public String getEmpCode() {
		return empCode;
	}
	//empCode set方法
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	//complaintCount get方法
	public Integer getComplaintCount() {
		return complaintCount;
	}
	//complaintCount set方法
	public void setComplaintCount(Integer complaintCount) {
		this.complaintCount = complaintCount;
	}
	//isSend get方法
	public String getIsSend() {
		return isSend;
	}
	//isSend set方法
	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}
	//failureCount get方法
	public Integer getFailureCount() {
		return failureCount;
	}
	//failureCount set方法
	public void setFailureCount(Integer failureCount) {
		this.failureCount = failureCount;
	}
	//perCount get方法
	public Integer getPerCount() {
		return perCount;
	}
	//perCount set方法
	public void setPerCount(Integer perCount) {
		this.perCount = perCount;
	}

}
