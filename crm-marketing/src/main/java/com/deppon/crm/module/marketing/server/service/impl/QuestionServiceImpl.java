package com.deppon.crm.module.marketing.server.service.impl;

import java.util.List;

import com.deppon.crm.module.marketing.server.dao.IQuestionDao;
import com.deppon.crm.module.marketing.server.service.IQuestionService;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionOption;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;

public class QuestionServiceImpl implements IQuestionService{
	public IQuestionDao questionDao;
	@Override
	public List<QuestionDetail> searchQuestionsByCondition(QuestionQueryCondition c,int start, int limit){
		return questionDao.searchQuestionsByCondition(c, start, limit);
	}
	@Override
	public int createQuestion(QuestionDetail q){
		return questionDao.saveQuestion(q);
	}
	@Override
	public int createOption(QuestionOption o){
		return questionDao.saveOption(o);
	}
	/**
	 * @return the questionDao
	 */
	public IQuestionDao getQuestionDao() {
		return questionDao;
	}
	/**
	 * @param questionDao the questionDao to set
	 */
	public void setQuestionDao(IQuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	@Override
	public String countQuestionsByCondition(QuestionQueryCondition c) {
		return questionDao.countQuestionsByCondition(c);
	}
	@Override
	public List<QuestionOption> searchOptionByQuestion(QuestionDetail q){
		return questionDao.searchOptionByQuestion(q);
	}
	@Override
	public List<QuestionDetail> searchOptionByQuestionId(List<String> ids,String questionnaireId) {
		return questionDao.searchOptionByQuestionId(ids,questionnaireId);
	}
	@Override
	public int deleteQuestionById(String id) {
		return questionDao.deleteQuestion(id);
	}
	@Override
	public int deleteOptionByQuestionId(String id) {
		return questionDao.deleteOptions(id);
	}
	@Override
	public int updateQuestion(QuestionDetail q) {
		return questionDao.updateQuestion(q);
	}
	@Override
	public List<QuestionnaireInfo> searchQuestionnaireByQuestionId(String id) {
		return questionDao.searchQuestionnaireByQuestionId(id);
	}
	@Override
	public List<QuestionDetail> searchQuestionDetailByTitle(String title){
		return questionDao.searchQuestionDetailByQuestionTitle(title);
	}
}
