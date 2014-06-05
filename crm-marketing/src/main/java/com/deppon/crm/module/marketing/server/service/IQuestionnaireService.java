/**
 * <p>
 * Description:<br />
 * </p>
 * @title IQuestionnaireService.java
 * @package com.deppon.crm.module.marketing.server.service
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
package com.deppon.crm.module.marketing.server.service;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.questionnaire.AnswerDetailInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.AnswerMainInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.CustQuestionnaireDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireMapper;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireQueryCondi;

/**
 * <p>
 * Description:问卷管理service层接口<br />
 * </p>
 * @title IQuestionnaireService.java
 * @package com.deppon.crm.module.marketing.server.service
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
public interface IQuestionnaireService {
	/**
	 * <p>
	 * Description:查询问卷管理结果列表<br />
	 * @author xiaohongye
	 * @param queryCondi
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-6
	 */
	public List<QuestionnaireInfo> searchQuestionnaireInfoList(QuestionnaireQueryCondi queryCondi,int start,int limit);
	
	/**
	 * 
	 * <p>
	 * Description:查询问卷管理结果列表的总条数<br />
	 * @author xiaohongye
	 * @param queryCondi
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-6
	 */
	public int searchQuestionnaireInfoCount(QuestionnaireQueryCondi queryCondi);
	
	/**
	 * 
	 * <p>
	 * Description:生成问卷主要信息<br />
	 * @author xiaohongye
	 * @param questionnaireInfo
	 * @return 问卷id
	 * @version V0.1 
	 * @Date 2014-3-8
	 */
	public String createQuestionnaire(QuestionnaireInfo questionnaireInfo);
	
	/**
	 * 
	 * <p>
	 * Description:生成问卷问题的映射信息<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-8
	 */
	public boolean createQuestionMapper(QuestionnaireMapper questionnaireMapper);
	
	/**
	 * 
	 * <p>
	 * Description:更新问卷主体信息<br />
	 * @author xiaohongye
	 * @param questionnaireInfo
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	public boolean updateQuestionnaire(QuestionnaireInfo questionnaireInfo);
	
	/**
	 * 
	 * <p>
	 * Description:根据问卷id列表删除问卷主体信息<br />
	 * @author xiaohongye
	 * @param id
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	public int deleteQuestionnaire(String[] id);
	
	/**
	 * 
	 * <p>
	 * Description:根据问卷id列表删除问卷问题映射信息<br />
	 * @author xiaohongye
	 * @param id
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	public int deleteQuestionnaireMapper(String[] questionnaireId);
	
	/**
	 * 
	 * <p>
	 * Description:查询即将生成的问卷的问卷编号<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	public String queryQuestionnaireCode();
	
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
	public QuestionnaireInfo searchQuestionnaireByName(String surveyName);
	
	/**
	 * 
	 * <p>
	 * Description:根据问卷id查询问题列表<br />
	 * @author xiaohongye
	 * @param questionnaireId
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-12
	 */
	public List<QuestionDetail> searchQuestionListByQuestionnaireId(String questionnaireId);
	/**
	 * 
	 * <p>
	 * Description:根据问卷id问卷统计结果<br />
	 * @author 盛诗庆
	 * @param surverId
	 * @return
	 * @version V0.1 
	 * @param staticEndDate 
	 * @param staticStartDate 
	 * @Date 2014-3-19
	 */
	List<QuestionDetail> staticsQuestionsBySurveyId(String surveyId, Date staticStartDate, Date staticEndDate);
	/**
	 * 统计问题在指定问卷中被回答的次数以及各个选项被回答的次数
	 * @param surverId
	 * @param questionId
	 * @param optionFlag
	 * @return
	 */
	String countOfOption(String surverId, String questionId, String optionFlag);
	/**
	 * 根据问卷id查询出回答了该问卷客户的基本信息和答案
	 * @param map
	 * @return
	 */
	List<CustQuestionnaireDetail> exportCustQuestionnaireDetail(String surveyId, Date staticStartDate, Date staticEndDate);
	/**
	 * 
	 * <p>
	 * Description:问卷回访时向用户问卷答案信息主表中插入数据<br />
	 * @author xiaohongye
	 * @param answerMainInfo
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-28
	 */
	public boolean insertSurveyMainAnswer(AnswerMainInfo answerMainInfo);
	
	/**
	 * 
	 * <p>
	 * Description:问卷回访时向用户问卷答案信息明细表中插入数据<br />
	 * @author xiaohongye
	 * @param answerDetailInfo
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-28
	 */
	public boolean insertSurveyDetailAnswer(AnswerDetailInfo answerDetailInfo);
	
	/**
	 * 
	 * <p>
	 * Description:根据问卷id更新问卷状态<br />
	 * @author xiaohongye
	 * @param questionnaireInfo
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-29
	 */
	public boolean updateQuestionnaireStatusById(QuestionnaireInfo questionnaireInfo);
	/**
	 * <p>
	 * Description:根据客户id查询客户答过的问卷列表<br />
	 * @author 盛诗庆
	 * @param custId
	 * @return
	 * @version V0.1 
	 * @param limit 
	 * @param start 
	 * @Date 2014-3-31
	 */
	List<QuestionnaireInfo> queryQuestionnaireListByCustid(String custId, int start, int limit);
	/**
	 * <p>
	 * Description:根据客户id查询客户答过的问卷总数<br />
	 * @author 盛诗庆
	 * @param custId
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-31
	 */
	int countQuestionnaireListByCustid(String custId);
	/**
	 * 
	 * <p>
	 * Description:定时更新问卷状态<br />
	 * @author xiaohongye
	 * @param questionnaireInfo
	 * @return 更新条数
	 * @version V0.1 
	 * @Date 2014-3-31
	 */
	public int updateSurveyStatus(String status);
	/**
	 * <p>
	 * Description:根据回访id查询该回访问卷答案<br />
	 * @author 盛诗庆
	 * @param visitId
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-31
	 */
	List<QuestionDetail> viewQuestionnaireByVisitId(String visitId);
	/**
	 * <p>
	 * Description:根据回访id查询该回访使用的问卷基本信息<br />
	 * @author 盛诗庆
	 * @param visitId
	 * @return
	 * @version V0.1 
	 * @Date 2014-4-11
	 */
	QuestionnaireInfo searchQuestionnaireByVisitId(String visitId);

	/**
	 * <p>
	 * Description:根据问卷ID查询问卷信息<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年5月6日
	 * @param id
	 * @return
	 * QuestionnaireInfo
	 */
	public QuestionnaireInfo queryQuestionnaireById(String id);
}
