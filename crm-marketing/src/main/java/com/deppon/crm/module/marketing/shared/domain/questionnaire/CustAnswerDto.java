package com.deppon.crm.module.marketing.shared.domain.questionnaire;

import java.util.List;
import java.util.Map;

public class CustAnswerDto {
	private String surveyId;
	private List<Map<String,List<String>>> answer;
	private String customerId;
	private String returnVisitId;
	public String getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	public List<Map<String, List<String>>> getAnswer() {
		return answer;
	}
	public void setAnswer(List<Map<String, List<String>>> answer) {
		this.answer = answer;
	}

	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getReturnVisitId() {
		return returnVisitId;
	}
	public void setReturnVisitId(String returnVisitId) {
		this.returnVisitId = returnVisitId;
	}
	
}
