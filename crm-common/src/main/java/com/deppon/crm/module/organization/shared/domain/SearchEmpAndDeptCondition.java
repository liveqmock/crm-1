package com.deppon.crm.module.organization.shared.domain;
/**.
 * 
 *<p>作 者：张斌 最后修改时间：2012年5月23日 描 述：
 * 查询职员的条件
 * </p>
 */
public class SearchEmpAndDeptCondition {
	//工号
    private String empCode;
    //职员姓名
    private String empName;
    //职员所在部门
    private String deptName;
    
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
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
    
}
