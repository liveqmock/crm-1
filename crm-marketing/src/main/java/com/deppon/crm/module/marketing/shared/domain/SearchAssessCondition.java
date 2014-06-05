package com.deppon.crm.module.marketing.shared.domain;

public class SearchAssessCondition {
	//查询条件
	private AssessDept searchDept;
	//group by的字段
	private AssessDept groupDept;
	//查询日期
	private String dateStr;
	//潜客来源
	private String potenSource;
	//业务类别
	private String custCategory;
	public AssessDept getSearchDept() {
		return searchDept;
	}
	public void setSearchDept(AssessDept searchDept) {
		this.searchDept = searchDept;
	}
	public AssessDept getGroupDept() {
		return groupDept;
	}
	public void setGroupDept(AssessDept groupDept) {
		this.groupDept = groupDept;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getPotenSource() {
		return potenSource;
	}
	public void setPotenSource(String potenSource) {
		this.potenSource = potenSource;
	}
	public String getCustCategory() {
		return custCategory;
	}
	public void setCustCategory(String custCategory) {
		this.custCategory = custCategory;
	}
	
}
