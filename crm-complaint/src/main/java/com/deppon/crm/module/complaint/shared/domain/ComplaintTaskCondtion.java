package com.deppon.crm.module.complaint.shared.domain;

import java.io.Serializable;

/**
 * <p>
 * Description:部门工单管理查询条件<br />
 * </p>
 * 
 * @title ComplaintTaskCondtion.java
 * @package com.deppon.crm.module.complaint.shared.domain
 * @author justin.xu
 * @version 0.1 2012-4-23
 */
public class ComplaintTaskCondtion implements Serializable{
	
	private static final long serialVersionUID = 4433187379531410455L;
	
	// 工单id
	private String fid;
	// 退回原因/反馈结果
	private String feedbackContent;
	// 部门id
	private String deptId;
	//解决情况码
	private String resolveCode;
	//申请延时时间 必须为数值
	private String applyCode;
	/**
	 * set get方法
	 */
	//fid get方法
	public String getFid() {
		return fid;
	}
	//fid set方法
	public void setFid(String fid) {
		this.fid = fid;
	}
	//feedbackContent get方法
	public String getFeedbackContent() {
		return feedbackContent;
	}
	//feedbackContent set方法
	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}
	//deptId get方法
	public String getDeptId() {
		return deptId;
	}
	//deptId set方法
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	//resolveCode get方法
	public String getResolveCode() {
		return resolveCode;
	}
	//resolveCode set方法
	public void setResolveCode(String resolveCode) {
		this.resolveCode = resolveCode;
	}
	//applyCode get方法
	public String getApplyCode() {
		return applyCode;
	}
	//applyCode set方法
	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

}
