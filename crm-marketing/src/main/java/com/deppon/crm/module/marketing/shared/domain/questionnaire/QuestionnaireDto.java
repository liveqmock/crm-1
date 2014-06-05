package com.deppon.crm.module.marketing.shared.domain.questionnaire;

import java.util.ArrayList;
import java.util.List;


/**
 * CC接口问卷详细信息数据交换实体
 * @author shengshiqing
 *
 */
public class QuestionnaireDto {
	private QuestionnaireInfo questionnaire;//问卷基本信息
	private List<QuestionDetail> questionList=new ArrayList<QuestionDetail>();//问卷包含的问题信息列表
	public QuestionnaireInfo getQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(QuestionnaireInfo questionnaire) {
		this.questionnaire = questionnaire;
	}
	public List<QuestionDetail> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<QuestionDetail> questionList) {
		this.questionList = questionList;
	}
}
