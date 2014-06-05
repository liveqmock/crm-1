package com.deppon.crm.module.marketing.server.service;

import java.util.List;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionOption;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;

public interface IQuestionService {
	/**
	 * 根据条件查询对应问题列表
	 * @param c
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<QuestionDetail> searchQuestionsByCondition(QuestionQueryCondition c,int start, int limit);
	/**
	 * 统计符合条件的问题数量
	 * @param c
	 * @return 
	 */
	public String countQuestionsByCondition(QuestionQueryCondition c);
	/**
	 * 根据问题id查询对应的选项
	 * @param q
	 * @return
	 */
	public List<QuestionOption> searchOptionByQuestion(QuestionDetail q);
	/**
	 * 根据问题id集合查询问题选项
	 * @param ids
	 * @return
	 */
	public List<QuestionDetail> searchOptionByQuestionId(List<String> ids,String questionnaireId);
	/**
	 * 创建问题
	 * @param q
	 * @return
	 */
	public int createQuestion(QuestionDetail q);
	/**
	 * 创建选项
	 * @param o
	 * @return
	 */
	public int createOption(QuestionOption o);
	/**
	 * 根据问题id删除问题
	 * @param id
	 * @return
	 */
	public int deleteQuestionById(String id);
	/**
	 * 根据问题id删除对应选项
	 * @param id
	 * @return
	 */
	public int deleteOptionByQuestionId(String id);
	/**
	 * 修改问题
	 * @param q
	 * @return
	 */
	public int updateQuestion(QuestionDetail q);
	/**
	 * 根据问题id查询引用了该问题的问卷列表
	 * @param id
	 * @return
	 */
	public List<QuestionnaireInfo> searchQuestionnaireByQuestionId(String id);
	List<QuestionDetail> searchQuestionDetailByTitle(String title);
}
