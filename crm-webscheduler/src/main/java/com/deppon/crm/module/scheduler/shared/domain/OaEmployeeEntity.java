/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title OaEmployeeEntity.java
 * @package com.deppon.crm.module.scheduler.shared.domain 
 * @author Administrator
 * @version 0.1 2012-7-5
 */
package com.deppon.crm.module.scheduler.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * <p>
 * Employee实体<br />
 * </p>
 * 
 * @title OaEmployeeEntity.java
 * @package com.deppon.crm.module.scheduler.shared.domain
 * @author 苏玉军
 * @version 0.1 2012-7-5
 */

public class OaEmployeeEntity extends BaseEntity {
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = -6769341735976023161L;
	// private String id;// ID
	// private Date createDate;// 创建日期
	// private String createUser;//创建人
	// private Date modifyDate;// 修改日期
	// private String modifyUser;//修改人
	private int orgId;
	private String empCode;
	private String empName;
	private String gender;
	private String jobName;
	private Date birthday;
	private String empStatus;
	private Date inDate;
	private Date outDate;
	private String oTel;
	private String oAddress;
	private String oZipCode;
	private String oEmail;
	private String mobileNo;
	private String hTel;
	private String hAddress;
	private String hZipCode;
	private String pEmail;
	private String workExp;
	private String remark;

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getoTel() {
		return oTel;
	}

	public void setoTel(String oTel) {
		this.oTel = oTel;
	}

	public String getoAddress() {
		return oAddress;
	}

	public void setoAddress(String oAddress) {
		this.oAddress = oAddress;
	}

	public String getoZipCode() {
		return oZipCode;
	}

	public void setoZipCode(String oZipCode) {
		this.oZipCode = oZipCode;
	}

	public String getoEmail() {
		return oEmail;
	}

	public void setoEmail(String oEmail) {
		this.oEmail = oEmail;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String gethTel() {
		return hTel;
	}

	public void sethTel(String hTel) {
		this.hTel = hTel;
	}

	public String gethAddress() {
		return hAddress;
	}

	public void sethAddress(String hAddress) {
		this.hAddress = hAddress;
	}

	public String gethZipCode() {
		return hZipCode;
	}

	public void sethZipCode(String hZipCode) {
		this.hZipCode = hZipCode;
	}

	public String getpEmail() {
		return pEmail;
	}

	public void setpEmail(String pEmail) {
		this.pEmail = pEmail;
	}

	public String getWorkExp() {
		return workExp;
	}

	public void setWorkExp(String workExp) {
		this.workExp = workExp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
