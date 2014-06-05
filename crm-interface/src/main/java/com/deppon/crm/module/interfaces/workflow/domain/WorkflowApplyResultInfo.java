package com.deppon.crm.module.interfaces.workflow.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 工作流申请需要返回的信息
 * @author davidcun @2012-3-23
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkflowApplyResultInfo {
	
	//工作流编号
	private String workflowNumber;
	
	//最后审批审批时间
	private Date examineDate;
	
	//最后审批人（工号）
	private String exminePerson;
	
	//审批结果，同意/不同意，true表示审批通过，false表示审批不通过
	private boolean exmineResult;
	
	//审批建议，不同意就写原因
	private String exmaineSuggestion;

	public String getWorkflowNumber() {
		return workflowNumber;
	}

	public void setWorkflowNumber(String workflowNumber) {
		this.workflowNumber = workflowNumber;
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

	public boolean isExmineResult() {
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
