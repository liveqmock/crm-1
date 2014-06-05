package com.deppon.crm.module.interfaces.workflow.domain;

import java.util.Date;


public class ContractApplyResultInfo {
	
	//工作流编号
	private String workflowCode;
	//最后审批审批时间
	private Date examineDate;
	//最后审批人(工号)
	private String exminePerson;
	//审批结果，同意/不同意，true表示审批通过，false表示审批不通过
	private boolean exmineResult;
	
	//审批建议
	private String exmaineSuggestion;

	public String getWorkflowCode() {
		return workflowCode;
	}

	public void setWorkflowCode(String workflowCode) {
		this.workflowCode = workflowCode;
	}

	public Date getExamineDate() {
		return examineDate;
	}

	public void setExamineDate(Date examineDate) {
		this.examineDate = examineDate;
	}

	public String getExminePerson() {
		return exminePerson;
	}

	public void setExminePerson(String exminePerson) {
		this.exminePerson = exminePerson;
	}

	public boolean getExmineResult() {
		return exmineResult;
	}

	public void setExmineResult(boolean exmineResult) {
		this.exmineResult = exmineResult;
	}

	public String getExmaineSuggestion() {
		return exmaineSuggestion;
	}

	public void setExmaineSuggestion(String exmaineSuggestion) {
		this.exmaineSuggestion = exmaineSuggestion;
	}
}
