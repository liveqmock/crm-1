package com.deppon.crm.module.organization.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;
import java.util.Date;
/**
 * 
	作        者：张斌
	最后修改时间：2011年10月20日
	描        述： T_ORG_EMPLOYEE员工表的实体对象
 */
public class Employee extends BaseEntity {

	private static final long serialVersionUID = -3786055567600558333L;
	//部门实体
	private Department deptId;
	//职员编号
	private String empCode;
	//人员姓名
	private String empName;
	//性别
	private Boolean gender;
	//生日
	private Date birthdate;
	//状态（在职/离职）
	private Boolean status;
	//入职日期
	private Date inDate;
	//离职日期
	private Date outDate;
	//办公电话
	private String offerTel;
	//办公地址
	private String offerAddress;
	//办公邮编
	private String offerZipCode;
	//办公邮箱
	private String offerEmail;
	//手机号码
	private String mobileNumber;
	//家庭电话
	private String homeTel;
	//家庭地址
	private String homeAddress;
	//家庭邮编
	private String homeZipCode;
	//私人邮箱
	private String personEmail;
	//工作描述
	private String workExp;
	//备注
	private String remark;
	//职位
	private String position;
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Department getDeptId() {
		return this.deptId;
	}
		 	
	public void setDeptId(Department deptId) {
		this.deptId = deptId;
	}
		
	public String getEmpCode() {
		return this.empCode;
	}
		 	
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
		
	public String getEmpName() {
		return this.empName;
	}
		 	
	public void setEmpName(String empName) {
		this.empName = empName;
	}
		
	public Boolean getGender() {
		return this.gender;
	}
		 	
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
		
	public Date getBirthdate() {
		return this.birthdate;
	}
		 	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
		
	public Boolean getStatus() {
		return this.status;
	}
		 	
	public void setStatus(Boolean status) {
		this.status = status;
	}
		
	public Date getInDate() {
		return this.inDate;
	}
		 	
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
		
	public Date getOutDate() {
		return this.outDate;
	}
		 	
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
		
	public String getOfferTel() {
		return this.offerTel;
	}
		 	
	public void setOfferTel(String offerTel) {
		this.offerTel = offerTel;
	}
		
	public String getOfferAddress() {
		return this.offerAddress;
	}
		 	
	public void setOfferAddress(String offerAddress) {
		this.offerAddress = offerAddress;
	}
		
	public String getOfferZipCode() {
		return this.offerZipCode;
	}
		 	
	public void setOfferZipCode(String offerZipCode) {
		this.offerZipCode = offerZipCode;
	}
		
	public String getOfferEmail() {
		return this.offerEmail;
	}
		 	
	public void setOfferEmail(String offerEmail) {
		this.offerEmail = offerEmail;
	}
		
	public String getMobileNumber() {
		return this.mobileNumber;
	}
		 	
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
		
	public String getHomeTel() {
		return this.homeTel;
	}
		 	
	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}
		
	public String getHomeAddress() {
		return this.homeAddress;
	}
		 	
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
		
	public String getHomeZipCode() {
		return this.homeZipCode;
	}
		 	
	public void setHomeZipCode(String homeZipCode) {
		this.homeZipCode = homeZipCode;
	}
		
	public String getPersonEmail() {
		return this.personEmail;
	}
		 	
	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}
		
	public String getWorkExp() {
		return this.workExp;
	}
		 	
	public void setWorkExp(String workExp) {
		this.workExp = workExp;
	}
		
	public String getRemark() {
		return this.remark;
	}
		 	
	public void setRemark(String remark) {
		this.remark = remark;
	}
		
	
}
