package com.deppon.crm.module.marketing.server.dao;

import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionOption;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;

public interface IQuestionDao {
	/**
	 * 根据条件查询对应的问题列表
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
	 * 根据问题查询选项列表
	 * @param q
	 * @return
	 */
	public List<QuestionOption> searchOptionByQuestion(QuestionDetail q);
	/**
	 * 保存问题
	 * @param q
	 * @return
	 */
	public int saveQuestion(QuestionDetail q );
	/**
	 * 保存问题选项
	 * @param options
	 * @return
	 */
	public int saveOption(QuestionOption option);
	/**
	 * 根据问题id删除单个问题
	 * @param id
	 * @return
	 */
	public int deleteQuestion(String id);
	/**
	 * 根据问题id删除对应问题选项
	 * @param id
	 * @return
	 */
	public int deleteOptions(String id);
	/**
	 * 修改问题
	 * @param q
	 * @return
	 */
	public int updateQuestion(QuestionDetail q);
	/**
	 * 根据问题id集合查询问题选项
	 * @param ids
	 * @return
	 */
	public List<QuestionDetail> searchOptionByQuestionId(List<String> ids,String questionnaireId);
	/**
	 * 根据问题id查询引用了该问题的问卷列表
	 * @param id
	 * @return
	 */
	public List<QuestionnaireInfo> searchQuestionnaireByQuestionId(String id);
	/**
	 * 根据问题名称精确查询问题详情
	 * @param questionName
	 * @return
	 */
	List<QuestionDetail> searchQuestionDetailByQuestionTitle(String questionTitle);
}
