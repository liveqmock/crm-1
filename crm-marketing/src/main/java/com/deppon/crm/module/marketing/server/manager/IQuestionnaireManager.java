/**
 * <p>
 * Description:<br />
 * </p>
 * @title IQuestionnaireManager.java
 * @package com.deppon.crm.module.marketing.server.manager
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
package com.deppon.crm.module.marketing.server.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.marketing.shared.domain.questionnaire.AnswerMainInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireDto;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireMapper;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireQueryCondi;

/**
 * <p>
 * Description:问卷管理manager层接口<br />
 * </p>
 * @title IQuestionnaireManager.java
 * @package com.deppon.crm.module.marketing.server.manager
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
public interface IQuestionnaireManager {
	/**
	 * <p>
	 * Description:查询问卷管理结果列表<br />
	 * @author xiaohongye
	 * @param queryCondi
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-6
	 */
	public Map<String,Object> searchQuestionnaireInfoList(QuestionnaireQueryCondi queryCondi,int start,int limit);
	
	/**
	 * 
	 * <p>
	 * Description:生成问卷：同时生成问卷主体信息与问题问卷映射信息<br />
	 * @author xiaohongye
	 * @param questionnaireInfo
	 * @param questionnaireMapper
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	public boolean createQuestionnaire(QuestionnaireInfo questionnaireInfo,List<QuestionnaireMapper> questionnaireMapperList);
	
	/**
	 * 
	 * <p>
	 * Description:修改问卷：同时修改问卷主体信息与问题问卷映射信息<br />
	 * @author xiaohongye
	 * @param questionnaireInfo
	 * @param questionnaireMapper
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	public boolean updateQuestionnaire(QuestionnaireInfo questionnaireInfo,
			List<QuestionnaireMapper> questionnaireMapperList,String haveOperateList);
	
	/**
	 * 
	 * <p>
	 * Description:根据问卷id数据删除问卷，同时删除对应的问题问卷映射<br />
	 * @author xiaohongye
	 * @param questionnaireId
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	public boolean deleteQuestionnaire(String[] questionnaireId,String questionnaireStatus);
	
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
	 * Description:根据问题id列表查询问题基本信息和问题选项信息<br />
	 * @author xiaohongye
	 * @param questionIdList
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-13
	 */
	public List<QuestionDetail> searchOptionByQuestionIds(List<String> questionIdList,String questionnaireId);
	/**
	 * 根据问卷id统计问卷结果
	 * @param staticEndDate 
	 * @param staticStartDate 
	 * @param surverId
	 * @return
	 */
	List<QuestionDetail> staticsQuestionsBySurveyId(String surveyId, Date staticStartDate, Date staticEndDate);
	/**
	 * 根据问卷id获取问卷统计导出信息
	 * @param surveyId
	 * @param staticStartDate
	 * @param staticEndDate
	 * @return
	 */
	List<List<String>> getStaticsData(String surveyId, Date staticStartDate,
			Date staticEndDate);
	/**
	 * 根据问卷id和问卷标题导出问卷统计信息
	 * @param surveyId
	 * @param questionnaireTitle
	 * @param staticEndDate 
	 * @param staticStartDate 
	 * @return 
	 */
	public Map<String, Object> exportStaticsExcel(String surveyId, String questionnaireTitle, Date staticStartDate, Date staticEndDate);
	/**
	 * 根据问卷id和问卷标题导出答过这份问卷的客户基本信息和答案
	 * @param surveyId
	 * @param questionnaireTitle
	 * @param staticStartDate
	 * @param staticEndDate
	 * @return
	 */
	Map<String, Object> exportCustQuestionnaireDetail(String surveyId,
			String questionnaireTitle, Date staticStartDate, Date staticEndDate);
	/**
	 * 根据问卷id查询回答过该问卷的客户信息和答案
	 * @param surveyId
	 * @param staticStartDate
	 * @param staticEndDate
	 * @return
	 */
	List<List<String>> getQuestionnaireVisitDetail(String surveyId,
			Date staticStartDate, Date staticEndDate);
	/**
	 * 保存问卷回访结果（答案结果）
	 * @author xiaohongye
	 * @param answerMainInfoList
	 */
	public void saveCustAnswer(List<AnswerMainInfo> answerMainInfoList);

	/**
	 * 
	 * <p>
	 * Description:根据问卷id更新问卷状态<br />
	 * @author xiaohongye
	 * @version V0.1 
	 * @Date 2014-3-29
	 */
	public boolean updateSurveyStatusByID(String questionnaireId,String operateType);
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
	Map<String,Object> queryQuestionnaireListByCustid(String custId,
			int start, int limit);
	
	
	/**
	 * 
	 * <p>
	 * Description:定时更新问卷状态<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-31
	 */
	public String updateSurveyStatus();
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
	 * Description:根据回访id查询问卷基本信息和问卷答案<br />
	 * @author 盛诗庆
	 * @param visitId
	 * @return
	 * @version V0.1 
	 * @Date 2014-4-11
	 */
	List<QuestionnaireDto> searchQuestionnaireByVisitId(String visitId);
	/**
	 * <p>
	 * Description:在服务器端创建一个统计结果excel<br />
	 * @author 盛诗庆
	 * @param fileName
	 * @param filePath
	 * @param questionnaireId
	 * @param staticStartDate
	 * @param staticEndDate
	 * @return
	 * @version V0.1 
	 * @Date 2014-4-11
	 *//*
	public boolean createStaticExcelOnServer(String fileName, String filePath,
			String questionnaireId, Date staticStartDate, Date staticEndDate);*/
	
	/**
	 * 
	 * <p>
	 * Description:根据问卷Id查询问卷<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年5月6日
	 * @param id
	 * @return
	 * QuestionnaireInfo
	 */
	QuestionnaireInfo queryQuestionnaireById(String id);
}
