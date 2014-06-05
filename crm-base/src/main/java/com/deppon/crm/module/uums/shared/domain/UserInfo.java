package com.deppon.crm.module.uums.shared.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 
 * @description 接口传输参数用户�?
 * @author zzw
 * @version 0.1 2013-11-25
 */
public class UserInfo extends BaseUUEntity implements Serializable {
	    /**
	 * 
	 */
	private static final long serialVersionUID = 5188341671055610745L;
		private String userName;
	    private String empName;
	    private String desPassword;
	    private String sysName;
	    private String orgName;
	    private String orgBenchmarkCode;
	    private String orgCode;
	    private String position;
	    private String workCode;
	    private String gender;
	    private boolean isTemp;
	    private boolean isActive;
	    private Date validDate;
	    private Date invalidDate;
	    private Date lastModifyTime;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		public String getDesPassword() {
			return desPassword;
		}
		public void setDesPassword(String desPassword) {
			this.desPassword = desPassword;
		}
		public String getSysName() {
			return sysName;
		}
		public void setSysName(String sysName) {
			this.sysName = sysName;
		}
		public String getOrgName() {
			return orgName;
		}
		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}
		public String getOrgBenchmarkCode() {
			return orgBenchmarkCode;
		}
		public void setOrgBenchmarkCode(String orgBenchmarkCode) {
			this.orgBenchmarkCode = orgBenchmarkCode;
		}
		public String getOrgCode() {
			return orgCode;
		}
		public void setOrgCode(String orgCode) {
			this.orgCode = orgCode;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public String getWorkCode() {
			return workCode;
		}
		public void setWorkCode(String workCode) {
			this.workCode = workCode;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public boolean isTemp() {
			return isTemp;
		}
		public void setTemp(boolean isTemp) {
			this.isTemp = isTemp;
		}
		public boolean isActive() {
			return isActive;
		}
		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}
		public Date getValidDate() {
			return validDate;
		}
		public void setValidDate(Date validDate) {
			this.validDate = validDate;
		}
		public Date getInvalidDate() {
			return invalidDate;
		}
		public void setInvalidDate(Date invalidDate) {
			this.invalidDate = invalidDate;
		}
		public Date getLastModifyTime() {
			return lastModifyTime;
		}
		public void setLastModifyTime(Date lastModifyTime) {
			this.lastModifyTime = lastModifyTime;
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
