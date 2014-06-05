/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireServiceImpl.java
 * @package com.deppon.crm.module.marketing.server.service.impl
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
package com.deppon.crm.module.marketing.server.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.marketing.server.dao.IQuestionnaireDao;
import com.deppon.crm.module.marketing.server.service.IQuestionnaireService;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.AnswerDetailInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.AnswerMainInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.CustQuestionnaireDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireMapper;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireQueryCondi;

/**
 * <p>
 * Description:问卷管理service层实现<br />
 * </p>
 * @title QuestionnaireServiceImpl.java
 * @package com.deppon.crm.module.marketing.server.service.impl
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
public class QuestionnaireServiceImpl implements IQuestionnaireService{
	//注入问卷管理的dao层实现
	private IQuestionnaireDao questionnaireDao;

	/**
	 * @param questionnaireDao the questionnaireDao to set
	 */
	public void setQuestionnaireDao(IQuestionnaireDao questionnaireDao) {
		this.questionnaireDao = questionnaireDao;
	}

	/**
	 * <p>
	 * Description:查询问卷管理结果列表<br />
	 * @author xiaohongye
	 * @param queryCondi
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-6
	 */
	@Override
	public List<QuestionnaireInfo> searchQuestionnaireInfoList(
			QuestionnaireQueryCondi queryCondi,int start,int limit) {
		return questionnaireDao.searchQuestionnaireInfoList(queryCondi,start,limit);
	}

	/**
	 * <p>
	 * Description:查询问卷管理结果列表的总条数<br />
	 * @author xiaohongye
	 * @param queryCondi
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-6
	 */
	@Override
	public int searchQuestionnaireInfoCount(QuestionnaireQueryCondi queryCondi) {
		return questionnaireDao.searchQuestionnaireInfoCount(queryCondi);
	}

	/**
	 * <p>
	 * Description:生成问卷主要信息<br />
	 * @author xiaohongye
	 * @param questionnaireInfo
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	@Override
	public String createQuestionnaire(QuestionnaireInfo questionnaireInfo) {
		return questionnaireDao.createQuestionnaire(questionnaireInfo);
	}

	/**
	 * <p>
	 * Description:生成问卷问题的映射信息<br />
	 * @author xiaohongye
	 * @param questionnaireMapper
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	@Override
	public boolean createQuestionMapper(QuestionnaireMapper questionnaireMapper) {
		return questionnaireDao.createQuestionMapper(questionnaireMapper);
	}

	/**
	 * <p>
	 * Description:更新问卷主体信息<br />
	 * @author xiaohongye
	 * @param questionnaireInfo
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	@Override
	public boolean updateQuestionnaire(QuestionnaireInfo questionnaireInfo) {
		return questionnaireDao.updateQuestionnaire(questionnaireInfo);
	}

	/**
	 * <p>
	 * Description:根据问卷id数组删除问卷主体信息<br />
	 * @author xiaohongye
	 * @param id
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	@Override
	public int deleteQuestionnaire(String[] id) {
		return questionnaireDao.deleteQuestionnaire(id);
	}

	/**
	 * <p>
	 * Description:根据问卷id数组删除问卷问题映射信息<br />
	 * @author xiaohongye
	 * @param questionnaireId
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	@Override
	public int deleteQuestionnaireMapper(String[] questionnaireId) {
		return questionnaireDao.deleteQuestionnaireMapper(questionnaireId);
	}

	/**
	 * 
	 * <p>
	 * Description:查询即将生成的问卷的问卷编号<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	@Override
	public String queryQuestionnaireCode() {
		return questionnaireDao.queryQuestionnaireCode();
	}

	/**
	 * 
	 * <p>
	 * Description:根据计划名称精确查询出匹配的问卷信息<br />
	 * @author xiaohongye
	 * @param surveyName
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	@Override
	public QuestionnaireInfo searchQuestionnaireByName(String surveyName) {
		return questionnaireDao.searchQuestionnaireByName(surveyName);
	}

	/**
	 * <p>
	 * Description:根据问卷id查询问题列表<br />
	 * @author xiaohongye
	 * @param questionnaireId
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-12
	 */
	@Override
	public List<QuestionDetail> searchQuestionListByQuestionnaireId(
			String questionnaireId) {
		return questionnaireDao.searchQuestionListByQuestionnaireId(questionnaireId);
	}
	@Override
	public List<QuestionDetail> staticsQuestionsBySurveyId(String surveyId, Date staticStartDate, Date staticEndDate){
		return questionnaireDao.staticsQuestionsBySurveyId(surveyId,staticStartDate,staticEndDate);
	}
	@Override
	public String countOfOption(String surveyId,String questionId,String optionFlag){
		return questionnaireDao.countOfOption(surveyId, questionId, optionFlag);
	}
	@Override
	public List<CustQuestionnaireDetail> exportCustQuestionnaireDetail(String surveyId, Date staticStartDate, Date staticEndDate ){
		return questionnaireDao.exportCustQuestionnaireDetail(surveyId,staticStartDate,staticEndDate);
	}

	/**
	 * <p>
	 * Description:问卷回访时向用户问卷答案信息主表中插入数据<br />
	 * @author xiaohongye
	 * @param answerMainInfo
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-28
	 */
	@Override
	public boolean insertSurveyMainAnswer(AnswerMainInfo answerMainInfo) {
		return questionnaireDao.insertSurveyMainAnswer(answerMainInfo);
	}

	/**
	 * <p>
	 * Description:问卷回访时向用户问卷答案信息明细表中插入数据<br />
	 * @author xiaohongye
	 * @param answerDetailInfo
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-28
	 */
	@Override
	public boolean insertSurveyDetailAnswer(AnswerDetailInfo answerDetailInfo) {
		return questionnaireDao.insertSurveyDetailAnswer(answerDetailInfo);
	}

	/**
	 * <p>
	 * Description:根据问卷id更新问卷状态<br />
	 * @author xiaohongye
	 * @param questionnaireInfo
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-29
	 */
	@Override
	public boolean updateQuestionnaireStatusById(
			QuestionnaireInfo questionnaireInfo) {
		return questionnaireDao.updateQuestionnaireStatusById(questionnaireInfo);
	}
	/**
	 * <p>
	 * Description:根据客户id查询客户答过的问卷列表<br />
	 * @author 盛诗庆
	 * @param custId
	 * @param start
	 * @param limit
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-31
	 */
	@Override
	public List<QuestionnaireInfo> queryQuestionnaireListByCustid(
			String custId,int start,int limit) {
		List<QuestionnaireInfo> result = questionnaireDao.queryQuestionnaireListByCustid(custId,start,limit);
		return result;
	}
	/**
	 * <p>
	 * Description:根据客户id查询客户答过的问卷总数<br />
	 * @author 盛诗庆
	 * @param custId
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-31
	 */
	@Override
	public int countQuestionnaireListByCustid(
			String custId) {
		int result = questionnaireDao.countQuestionnaireListByCustid(custId);
		return result;
	}
	/**
	 * <p>
	 * Description:根据回访id查询该回访问卷答案<br />
	 * @author 盛诗庆
	 * @param visitId
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-31
	 */
	@Override
	public List<QuestionDetail> viewQuestionnaireByVisitId(
			String visitId) {
		List<QuestionDetail> result = questionnaireDao.viewQuestionnaireByVisitId(visitId);
		return result;
	}
	
	
	/**
	 * <p>
	 * Description:定时更新问卷状态<br />
	 * @author xiaohongye
	 * @param status
	 * @return 更新条数
	 * @version V0.1 
	 * @Date 2014-3-31
	 */
	@Override
	public int updateSurveyStatus(String status) {
		if(StringUtils.isEmpty(status)){
			return 0;
		}
		return questionnaireDao.updateSurveyStatus(status);
	}
	/**
	 * <p>
	 * Description:根据回访id查询该回访使用的问卷基本信息<br />
	 * @author 盛诗庆
	 * @param visitId
	 * @return
	 * @version V0.1 
	 * @Date 2014-4-11
	 */
	@Override
	public QuestionnaireInfo searchQuestionnaireByVisitId(String visitId) {
		return questionnaireDao.searchQuestionnaireByVisitId(visitId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IQuestionnaireService#queryQuestionnaireById(java.lang.String)
	 */
	@Override
	public QuestionnaireInfo queryQuestionnaireById(String id) {
		return questionnaireDao.queryQuestionnaireById(id);
	}
}
