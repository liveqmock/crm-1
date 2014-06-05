/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireDaoImpl.java
 * @package com.deppon.crm.module.marketing.server.dao.impl
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.marketing.server.dao.IQuestionnaireDao;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.AnswerDetailInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.AnswerMainInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.CustQuestionnaireDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireMapper;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireQueryCondi;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * <p>
 * Description:问卷管理DAO层实现<br />
 * </p>
 * @title QuestionnaireDaoImpl.java
 * @package com.deppon.crm.module.marketing.server.dao.impl
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
public class QuestionnaireDaoImpl extends iBatis3DaoImpl implements IQuestionnaireDao{
	private static final String NAMESPACE_QUESTIONNAIRE = "com.deppon.crm.module.marketing.shared.domain.questionnaire.";
	//查询问卷管理结果列
	private static final String  SEARCH_QUESTIONNAIRE_INFO_LIST = "searchQuestionnaireInfoList";
	//生成问卷主要信息
	private static final String INSERT_QUESTIONNAIRE = "insertQuestionnaire";
	//查询问卷管理结果总条数
	private static final String SEARCH_QUESTIONNAIRE_INFO_COUNT = "searchQuestionnaireInfoCount";
	//生成问卷问题映射信息
	private static final String INSERT_QUESTION_MAPPER = "insertQuestionMapper";
	//删除问卷主体信息
	private static final String DELETE_QUESTIONNAIRE = "deleteQuestionnaire";
	//删除问卷、问题映射信息
	private static final String DELETE_QUESTIONNAIRE_MAPPER = "deleteQuestionnaireMapper";
	//更新问卷主体信息
	private static final String UPDATE_QUESTIONNAIRE = "updateQuestionnaire";
	//查询即将生成的问卷的问卷编号
	private static final String SEARCH_QUESTIONNAIRE_CODE = "searchQuestionnaireCode";
	//根据计划名称模糊查询出所有匹配的问卷信息
	private static final String SEARCH_QUESTIONNAIRE_BY_NAME = "searchQuestionnaireByName";
	//根据问卷id查询问题列表
	private static final String SEARCH_QUESTION_LIST_BY_QUESTIONNAIRE_ID = "searchQuestionListByQuestionnaireId";
	//更具问卷id和问题id选项标识计算该问题回访比例
	private static final String COUNT_OF_OPTION = "countOfOption";
	//根据问卷id查询问卷统计结果
	private static final String STATICS_QUESTIONNAIRE_BY_SURVERY_ID = "staticsQuestionsBySurveyId";
	//根据问卷id查询回答过该问卷的客户基本信息
	private static final String STATICS_CUST_QUESTIONNAIRE_DETAIL = "exportCustQuestionnaireDetail";
	//问卷回访时向用户问卷答案信息主表中插入数据
	private static final String INSERT_SURVEY_MAIN_ANSWER = "insertSurveyMainAnswer";
	//问卷回访时向用户问卷答案信息明细表中插入数据
	private static final String INSERT_SURVEY_DETAIL_ANSWER = "insertSurveyDetailAnswer";
	//根据问卷id更新问卷状态
	private static final String UPDATE_QUESTIONNAIRE_STATUS_BY_ID = "updateQuestionnaireStatusById";
	//根据客户id查询客户所答过的问卷列表
	private static final String SEARCH_QUESTIONNAIRELIST_BY_CUSTID = "queryQuestionnaireListByCustid";
	//根据客户id统计客户答过的问卷总数
	private static final String COUNT_QUESTIONNAIRE_BY_CUSTID = "countQuestionnaireListByCustid";
	//定时更新问卷状态
	private static final String UPDATE_SURVEY_STATUS = "updateSurveyStatus";
	//根据回访id查询问卷结果
	private static final String VIEW_QUESTIONNAIRE_ANSWER_BY_VISITID = "viewQuestionnaireByVisitId";
	//根据回访id查询问卷基本信息
	private static final String SEARCH_QUESTIONNAIRE_BY_VISITID = "searchQuestionnaireByVisitId";
	//根据问卷ID查询问卷信息
	private static final String QUERY_QUESTIONNAIRE_BY_ID="queryQuestionnaireById";
	/**
	 * <p>
	 * Description:查询问卷管理结果列表<br />
	 * @author xiaohongye
	 * @param queryCondi
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-6
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionnaireInfo> searchQuestionnaireInfoList(
			QuestionnaireQueryCondi queryCondi,int start,int limit) {
		RowBounds rb = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAMESPACE_QUESTIONNAIRE+SEARCH_QUESTIONNAIRE_INFO_LIST,queryCondi,rb);
	}
	/**
	 * <p>
	 * Description:<br />
	 * @author xiaohongye
	 * @param queryCondi
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-6
	 */
	@Override
	public int searchQuestionnaireInfoCount(QuestionnaireQueryCondi queryCondi) {
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_QUESTIONNAIRE+SEARCH_QUESTIONNAIRE_INFO_COUNT,queryCondi);
	}
	/**
	 * <p>
	 * Description:生成问卷主要信息<br />
	 * @author xiaohongye
	 * @param questionnaireInfo
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-8
	 */
	@Override
	public String createQuestionnaire(QuestionnaireInfo questionnaireInfo) {
		this.getSqlSession().insert(NAMESPACE_QUESTIONNAIRE+INSERT_QUESTIONNAIRE, questionnaireInfo);
		//返回生成问卷的问卷id
		return questionnaireInfo.getId();
	}
	/**
	 * <p>
	 * Description:生成问卷问题的映射信息<br />
	 * @author xiaohongye
	 * @param questionnaireId
	 * @param questionDetailList
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-8
	 */
	@Override
	public boolean createQuestionMapper(QuestionnaireMapper questionnaireMapper) {
		boolean result = this.getSqlSession().insert(NAMESPACE_QUESTIONNAIRE+INSERT_QUESTION_MAPPER, questionnaireMapper) > 0;
		return result;
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
		boolean result = this.getSqlSession().update(NAMESPACE_QUESTIONNAIRE+UPDATE_QUESTIONNAIRE, questionnaireInfo) > 0;
		return result;
	}
	/**
	 * <p>
	 * Description:根据问卷id删除问卷主体信息<br />
	 * @author xiaohongye
	 * @param id
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	@Override
	public int deleteQuestionnaire(String[] id) {
		int executeLine = this.getSqlSession().delete(NAMESPACE_QUESTIONNAIRE+DELETE_QUESTIONNAIRE, id);
		return executeLine;
	}
	/**
	 * <p>
	 * Description:根据问卷id删除问卷、问题映射信息<br />
	 * @author xiaohongye
	 * @param id
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	@Override
	public int deleteQuestionnaireMapper(String[] questionnaireId) {
		int executeLine = this.getSqlSession().delete(NAMESPACE_QUESTIONNAIRE+DELETE_QUESTIONNAIRE_MAPPER, questionnaireId);
		return executeLine;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionDetail> staticsQuestionsBySurveyId(String surveyId, Date staticStartDate, Date staticEndDate){
		//封装查询条件
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("surveyId", surveyId);
		map.put("staticStartTime", staticStartDate);
		map.put("staticEndTime", staticEndDate);
		return this.getSqlSession().selectList(NAMESPACE_QUESTIONNAIRE + STATICS_QUESTIONNAIRE_BY_SURVERY_ID, map);
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
	public String queryQuestionnaireCode(){
		return (String) this.getSqlSession().selectOne(NAMESPACE_QUESTIONNAIRE+SEARCH_QUESTIONNAIRE_CODE);
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据问卷名称精确查询出匹配的问卷信息<br />
	 * @author xiaohongye
	 * @param surveyName
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	public QuestionnaireInfo searchQuestionnaireByName(String surveyName){
		return (QuestionnaireInfo) this.getSqlSession().selectOne(NAMESPACE_QUESTIONNAIRE+SEARCH_QUESTIONNAIRE_BY_NAME,surveyName);
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
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionDetail> searchQuestionListByQuestionnaireId(
			String questionnaireId) {
		return this.getSqlSession().selectList(NAMESPACE_QUESTIONNAIRE+SEARCH_QUESTION_LIST_BY_QUESTIONNAIRE_ID, questionnaireId);
	}
	/**
	 * <p>
	 * Description:根据问卷id问题id以及选项id查询该选项被选择的总次数<br />
	 * @author 盛诗庆
	 * @param surveyId
	 * @param questionId
	 * @param optionFlag ： 选项标识，在答案表里面保存的就是选项fid
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-12
	 */
	@Override
	public String countOfOption(String surveyId, String questionId,String optionFlag) {
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("surveyId", surveyId);
		map.put("questionId", questionId);
		map.put("optionFlag", optionFlag);
		return (String) this.getSqlSession().selectOne(NAMESPACE_QUESTIONNAIRE + COUNT_OF_OPTION, map);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustQuestionnaireDetail> exportCustQuestionnaireDetail(String surveyId, Date staticStartDate, Date staticEndDate){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("surveyId",surveyId);
		map.put("staticStartTime",staticStartDate);
		map.put("staticEndTime",staticEndDate);
		return this.getSqlSession().selectList(NAMESPACE_QUESTIONNAIRE + STATICS_CUST_QUESTIONNAIRE_DETAIL, map);
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
		boolean result = this.getSqlSession().insert(NAMESPACE_QUESTIONNAIRE+INSERT_SURVEY_MAIN_ANSWER, answerMainInfo) > 0;
		return result;
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
		boolean result = this.getSqlSession().insert(NAMESPACE_QUESTIONNAIRE+INSERT_SURVEY_DETAIL_ANSWER, answerDetailInfo) > 0;
		return result;
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
		boolean result = this.getSqlSession().update(NAMESPACE_QUESTIONNAIRE+UPDATE_QUESTIONNAIRE_STATUS_BY_ID, questionnaireInfo) > 0;
		return result;
	}
	/**
	 * <p>
	 * Description:根据客户id查询客户答过的问卷列表<br />
	 * @author 盛诗庆
	 * @param custId
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-31
	 */
	@Override
	public List<QuestionnaireInfo> queryQuestionnaireListByCustid(
			String custId,int start,int limit) {
		RowBounds rb = new RowBounds(start,limit);
		@SuppressWarnings("unchecked")
		List<QuestionnaireInfo> result = this.getSqlSession().selectList(NAMESPACE_QUESTIONNAIRE + SEARCH_QUESTIONNAIRELIST_BY_CUSTID,custId,rb);
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
		int result = (Integer) this.getSqlSession().selectOne(NAMESPACE_QUESTIONNAIRE + COUNT_QUESTIONNAIRE_BY_CUSTID,custId);
		return result;
	}
	/**
	 * <p>
	 * Description:定时更新问卷状态<br />
	 * @author xiaohongye
	 * @param status
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-31
	 */
	@Override
	public int updateSurveyStatus(String status) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("status", status);
		map.put("compareStatus", status);
		return this.getSqlSession().update(NAMESPACE_QUESTIONNAIRE+UPDATE_SURVEY_STATUS, map);
		
	}
	/**
	 * 根据回访id查询问卷结果
	 * @author 盛诗庆
	 * @param visitId
	 * @return
	 * @date 2014-03-31
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<QuestionDetail> viewQuestionnaireByVisitId(String visitId){
		return this.getSqlSession().selectList(NAMESPACE_QUESTIONNAIRE + VIEW_QUESTIONNAIRE_ANSWER_BY_VISITID,visitId);
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
	public QuestionnaireInfo searchQuestionnaireByVisitId(String visitId){
		return (QuestionnaireInfo) this.getSqlSession().selectOne(NAMESPACE_QUESTIONNAIRE + SEARCH_QUESTIONNAIRE_BY_VISITID, visitId);
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IQuestionnaireDao#queryQuestionnaireById(java.lang.String)
	 */
	@Override
	public QuestionnaireInfo queryQuestionnaireById(String id) {
		return (QuestionnaireInfo) this.getSqlSession().selectOne(NAMESPACE_QUESTIONNAIRE + QUERY_QUESTIONNAIRE_BY_ID,id);
	}
}
