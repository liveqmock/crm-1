package com.deppon.crm.module.scheduler.shared.domain;

import java.util.ArrayList;
import java.util.List;

public class CompareContract {
	// id
	private String id;
	// 合同编码
	private String number;
	// 合同状态
	private String status;
	// 客户ID
	private String custId;
	// 最近更新时间
	private String lastUpdateTime;
	//合同部门信息
	List<CompareContractDept> contractDeptList = new ArrayList<CompareContractDept>();
	
	public List<CompareContractDept> getContractDeptList() {
		return contractDeptList;
	}

	public void setContractDeptList(List<CompareContractDept> contractDeptList) {
		this.contractDeptList = contractDeptList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
