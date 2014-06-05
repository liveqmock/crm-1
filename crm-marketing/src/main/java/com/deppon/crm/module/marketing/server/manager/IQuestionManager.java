package com.deppon.crm.module.marketing.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;

public interface IQuestionManager {
	/**
	 * 根据条件查询问题详情
	 * @param c
	 * @param start
	 * @param limit
	 * @return
	 */
	public Map<String,Object> searchQuestionsByCondition(QuestionQueryCondition c,int start, int limit);
	/**
	 * 创建问题
	 * @param q
	 * @return
	 */
	public boolean createQuestion(QuestionDetail q);
	/**
	 * 根据id删除问题
	 * @param id
	 * @return
	 */
	public List<QuestionnaireInfo> deleteQuestion(List<String> id);
	/**
	 * 修改问题
	 * @param q
	 * @return
	 */
	public List<QuestionnaireInfo> updateQuestion(QuestionDetail q);
	public List<QuestionnaireInfo> searchQuestionnaireByQuestionId(String questionId);
	
}
