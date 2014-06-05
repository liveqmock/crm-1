package com.deppon.crm.module.recompense.shared.domain;

/**
 * 
 * <p>
 * Description:理赔监控，短信通知信息<br />
 * </p>
 * 
 * @title RecSmsInformation.java
 * @package com.deppon.crm.module.recompense.shared.domain
 * @author andy
 * @version 0.1 2013-7-5
 */

public class RecSmsInformation {
	// 人员姓名
	private String empName;
	// 职员编号
	private String empCode;
	// 索赔处理天数
	private String claimDuration;
	// 理赔处理天数
	private String recDuration;
	// 手机号码
	private String mobileNumber;
	// 凭证号
	private String recompenseNum;
	// 理赔类型
	private String recompenseType;
	// 报案部门
	private String deptName;
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getClaimDuration() {
		return claimDuration;
	}
	public void setClaimDuration(String claimDuration) {
		this.claimDuration = claimDuration;
	}
	public String getRecDuration() {
		return recDuration;
	}
	public void setRecDuration(String recDuration) {
		this.recDuration = recDuration;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getRecompenseNum() {
		return recompenseNum;
	}
	public void setRecompenseNum(String recompenseNum) {
		this.recompenseNum = recompenseNum;
	}
	public String getRecompenseType() {
		return recompenseType;
	}
	public void setRecompenseType(String recompenseType) {
		this.recompenseType = recompenseType;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
}
