package com.deppon.crm.module.duty.shared.domain;
import com.deppon.foss.framework.entity.BaseEntity;

/**
* @ClassName: DutyDept 
* @Description: 工单责任，责任部门实体
* @author LiuY
* @date 2014-1-8 下午2:54:30 
*
 */
@SuppressWarnings("serial")
public class DutyDept extends BaseEntity{
	
	//部门编号
	private Integer deptId;
	//部门标杆编码
	private String standardCode;
	//部门名称
	private String deptName;
	//统计
	private String counts;
	
	
	/**
	 * @return the deptId
	 */
	public Integer getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the standardCode
	 */
	public String getStandardCode() {
		return standardCode;
	}
	/**
	 * @param standardCode the standardCode to set
	 */
	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the counts
	 */
	public String getCounts() {
		return counts;
	}
	/**
	 * @param counts the counts to set
	 */
	public void setCounts(String counts) {
		this.counts = counts;
	}
}
