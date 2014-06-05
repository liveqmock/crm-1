package com.deppon.crm.module.uums.shared.domain;
import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @description 接口传输参数员工�?
 * @author zzw
 * @version 0.1 2013-11-25
 */
public class EmployeeInfo extends BaseUUEntity implements Serializable {

	private static final long serialVersionUID = -6709034471711420330L;
	    private String employeeName;
	    private String gender;
	    private String deptName;
	    private String deptBenchmarkCode;
	    private String deptCode;
	    private String position;
	    private String degree;
	    private String docType;
	    private String docNumber;
	    private Double withCode;
	    private Double waistCode;
	    private Double height;
	    private Double weight;
	    private Date birthday;
	    private String status;
	    private Date entryDate;
	    private Date departureDate;
	    private String officeTel;
	    private String officeAddress;
	    private String officeZipCode;
	    private String officeEmail;
	    private String mobile;
	    private String homeTel;
	    private String homeAddress;
	    private String homeZipCode;
	    private String personalEmail;
	    private String workexp;
	    private String remark;
		public String getEmployeeName() {
			return employeeName;
		}
		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getDeptName() {
			return deptName;
		}
		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}
		public String getDeptBenchmarkCode() {
			return deptBenchmarkCode;
		}
		public void setDeptBenchmarkCode(String deptBenchmarkCode) {
			this.deptBenchmarkCode = deptBenchmarkCode;
		}
		public String getDeptCode() {
			return deptCode;
		}
		public void setDeptCode(String deptCode) {
			this.deptCode = deptCode;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public String getDegree() {
			return degree;
		}
		public void setDegree(String degree) {
			this.degree = degree;
		}
		public String getDocType() {
			return docType;
		}
		public void setDocType(String docType) {
			this.docType = docType;
		}
		public String getDocNumber() {
			return docNumber;
		}
		public void setDocNumber(String docNumber) {
			this.docNumber = docNumber;
		}
		public Double getWithCode() {
			return withCode;
		}
		public void setWithCode(Double withCode) {
			this.withCode = withCode;
		}
		public Double getWaistCode() {
			return waistCode;
		}
		public void setWaistCode(Double waistCode) {
			this.waistCode = waistCode;
		}
		public Double getHeight() {
			return height;
		}
		public void setHeight(Double height) {
			this.height = height;
		}
		public Double getWeight() {
			return weight;
		}
		public void setWeight(Double weight) {
			this.weight = weight;
		}
		public Date getBirthday() {
			return birthday;
		}
		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Date getEntryDate() {
			return entryDate;
		}
		public void setEntryDate(Date entryDate) {
			this.entryDate = entryDate;
		}
		public Date getDepartureDate() {
			return departureDate;
		}
		public void setDepartureDate(Date departureDate) {
			this.departureDate = departureDate;
		}
		public String getOfficeTel() {
			return officeTel;
		}
		public void setOfficeTel(String officeTel) {
			this.officeTel = officeTel;
		}
		public String getOfficeAddress() {
			return officeAddress;
		}
		public void setOfficeAddress(String officeAddress) {
			this.officeAddress = officeAddress;
		}
		public String getOfficeZipCode() {
			return officeZipCode;
		}
		public void setOfficeZipCode(String officeZipCode) {
			this.officeZipCode = officeZipCode;
		}
		public String getOfficeEmail() {
			return officeEmail;
		}
		public void setOfficeEmail(String officeEmail) {
			this.officeEmail = officeEmail;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getHomeTel() {
			return homeTel;
		}
		public void setHomeTel(String homeTel) {
			this.homeTel = homeTel;
		}
		public String getHomeAddress() {
			return homeAddress;
		}
		public void setHomeAddress(String homeAddress) {
			this.homeAddress = homeAddress;
		}
		public String getHomeZipCode() {
			return homeZipCode;
		}
		public void setHomeZipCode(String homeZipCode) {
			this.homeZipCode = homeZipCode;
		}
		public String getPersonalEmail() {
			return personalEmail;
		}
		public void setPersonalEmail(String personalEmail) {
			this.personalEmail = personalEmail;
		}
		public String getWorkexp() {
			return workexp;
		}
		public void setWorkexp(String workexp) {
			this.workexp = workexp;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getChangeType() {
			return changeType;
		}
		public void setChangeType(String changeType) {
			this.changeType = changeType;
		}
		public Date getChangeDate() {
			return changeDate;
		}
		public void setChangeDate(Date changeDate) {
			this.changeDate = changeDate;
		}
		
}
