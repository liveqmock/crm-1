/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireManagerImpl.java
 * @package com.deppon.crm.module.marketing.server.manager.impl
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
package com.deppon.crm.module.marketing.server.manager.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.util.CreateExcel;
import com.deppon.crm.module.marketing.server.manager.IQuestionnaireManager;
import com.deppon.crm.module.marketing.server.service.IQuestionService;
import com.deppon.crm.module.marketing.server.service.IQuestionnaireService;
import com.deppon.crm.module.marketing.server.utils.QuestionnaireUtils;
import com.deppon.crm.module.marketing.server.utils.QuestionnaireValidateUtils;
import com.deppon.crm.module.marketing.shared.domain.CustMatAssess;
import com.deppon.crm.module.marketing.shared.domain.MarketAssessConstance;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.AnswerDetailInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.AnswerMainInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.CustQuestionnaireDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionOption;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireConstance;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireDto;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireMapper;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireQueryCondi;
import com.deppon.foss.framework.server.components.export.excel.ExcelExporter;
import com.deppon.foss.framework.server.components.export.excel.ExportResource;
import com.deppon.foss.framework.server.components.export.excel.ExportSetting;
import com.deppon.foss.framework.server.components.export.excel.ExporterExecutor;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * <p>
 * Description:问卷管理manager层实现<br />
 * </p>
 * @title QuestionnaireManagerImpl.java
 * @package com.deppon.crm.module.marketing.server.manager.impl
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
public class QuestionnaireManagerImpl implements IQuestionnaireManager{
	//问卷管理的service层实现
	private IQuestionnaireService questionnaireService;
	//问题管理的service层实现
	private IQuestionService questionService;
	//文件导出工具类
	private ExcelExporter exporter; 
	//客户回访统计数据
	private static List<List<String>> staticData;
	//客户回访详细数据
	private static List<List<String>> detailData;
	//问卷包含的所有问题标题
	private static List<QuestionDetail> questionList;
	/**
	 * @param questionService the questionService to set
	 */
	public void setQuestionService(IQuestionService questionService) {
		this.questionService = questionService;
	}

	/**
	 * @param questionnaireService the questionnaireService to set
	 */
	public void setQuestionnaireService(IQuestionnaireService questionnaireService) {
		this.questionnaireService = questionnaireService;
	}
	/**
	 * <p>
	 * Description:根据回访id查询问卷基本信息和问卷答案<br />
	 * @author 盛诗庆
	 * @param visitId
	 * @return
	 * @version V0.1 
	 * @Date 2014-4-11
	 */
	@Override
	public List<QuestionnaireDto> searchQuestionnaireByVisitId(String visitId){
		QuestionnaireDto questionnire = new QuestionnaireDto();
		List<QuestionnaireDto> questionnaireList = new ArrayList<QuestionnaireDto>();
		//根据回访id查询问卷基本信息
		QuestionnaireInfo questionnaireInfo = questionnaireService.searchQuestionnaireByVisitId(visitId);
		List<QuestionDetail> questionList = questionnaireService.viewQuestionnaireByVisitId(visitId);
		questionnire.setQuestionnaire(questionnaireInfo);
		questionnire.setQuestionList(questionList);
		questionnaireList.add(questionnire);
		return questionnaireList;
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
	public Map<String,Object> searchQuestionnaireInfoList(
			QuestionnaireQueryCondi queryCondi,int start,int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		//如果查询条件为空
		if(null == queryCondi){
			return null;
		}
		if(queryCondi.getQuestionnaireId() == null || queryCondi.getQuestionnaireId().equals("")){//问卷id为空的时候
			//1判断输入的查询条件是否合法
			QuestionnaireValidateUtils.canQueryQuestionnaire(queryCondi);
			//2封装输入的查询条件
			QuestionnaireUtils.reorganizeQueryCondi(queryCondi);
		}
		
		List<QuestionnaireInfo> questionnaireList = questionnaireService.searchQuestionnaireInfoList(queryCondi,start,limit);
		int totalCount = questionnaireService.searchQuestionnaireInfoCount(queryCondi);
		map.put("totalCount", totalCount);
		map.put("questionnaireList", questionnaireList);
		return map;
	}

	/**
	 * <p>
	 * Description:生成问卷：同时生成问卷主体信息与问题问卷映射信息<br />
	 * @author xiaohongye
	 * @param questionnaireInfo
	 * @param questionnaireMapper
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	@Transactional
	@Override
	public boolean createQuestionnaire(QuestionnaireInfo questionnaireInfo,
			List<QuestionnaireMapper> questionnaireMapperList) {
		// 1校验问卷名称是否为空
		String questionnaireName = questionnaireInfo.getQuestionnaireName();
		QuestionnaireValidateUtils.nullQuestionnaireName(questionnaireName);
		//2问卷名称去空格
		questionnaireInfo.setQuestionnaireName(questionnaireName.trim());
		//3根据问卷名称在数据库中查询有没有相应的问卷信息
		QuestionnaireInfo questionnaire = questionnaireService.searchQuestionnaireByName(questionnaireInfo.getQuestionnaireName());
		//4校验当前要生成的问卷名称在数据库中是否已经存在
		QuestionnaireValidateUtils.repeatQuestionnaireName(questionnaire);
		//5校验问卷编号是否超过01~99的范围
		String questionnaireCode = questionnaireService.queryQuestionnaireCode();
		QuestionnaireValidateUtils.exceedQuestionnaireCode(questionnaireCode);
		//6设置问卷编号
		questionnaireInfo.setQuestionnaireCode(questionnaireCode);
		//7校验生成问卷其他信息是否合法
		QuestionnaireValidateUtils.canCreateQuestionnaire(questionnaireInfo, 
				questionnaireMapperList,QuestionnaireConstance.HAVE_OPERATE_LIST_YES);
		//设置问卷状态为待生效
		questionnaireInfo.setStatus(QuestionnaireUtils.operateQuestionnaireStatus(
				questionnaireInfo.getEffectiveTime(), questionnaireInfo.getInvalidTime()));
		//获得当前登录的用户
		User user=(User)UserContext.getCurrentUser();
		String userId = user.getEmpCode().getId();
		questionnaireInfo.setCreateUserId(userId);
		questionnaireInfo.setLastModifyUserId(userId);
		//8插入问卷主体信息,返回问卷id
		String questionnaireId = questionnaireService.createQuestionnaire(questionnaireInfo);
		//9循环插入问题问卷映射信息
		QuestionnaireMapper mapper = new QuestionnaireMapper();
		for(int i = 0;i < questionnaireMapperList.size();i++){
			mapper = questionnaireMapperList.get(i);
			mapper.setQuestionnaireId(questionnaireId);
			mapper.setCreateUser(userId);
			mapper.setModifyUser(userId);
			questionnaireService.createQuestionMapper(mapper);
		}
		return true;
	}

	/**
	 * <p>
	 * Description:修改问卷：同时修改问卷主体信息与问题问卷映射信息<br />
	 * @author xiaohongye
	 * @param questionnaireInfo
	 * @param questionnaireMapper
	 * @param questionnaireMapper 是否操作过问题列表的标记字段，YES代表操作过，NO代表未操作过
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	@Transactional
	@Override
	public boolean updateQuestionnaire(QuestionnaireInfo questionnaireInfo,
			List<QuestionnaireMapper> questionnaireMapperList,String haveOperateList) {
		//校验问卷信息是否为空
		QuestionnaireValidateUtils.nullObject(questionnaireInfo,
				QuestionnaireConstance.OBJECT_QUESTIONNAIRE);
		//校验问卷id是否为空
		QuestionnaireValidateUtils.nullObject(questionnaireInfo.getId(),
				QuestionnaireConstance.OBJECT_QUESTIONNAIRE_ID);
		//校验问卷中问题列表是否为空
		if(QuestionnaireConstance.HAVE_OPERATE_LIST_YES.equals(haveOperateList)){
			QuestionnaireValidateUtils.nullObject(questionnaireMapperList,
					QuestionnaireConstance.OBJECT_QUESTION_MAPPER);
		}
		//校验问卷的状态是否为可修改状态
		String status = questionnaireInfo.getStatus(); 
		QuestionnaireValidateUtils.questionnaireStatusValidator(status,
				QuestionnaireConstance.OPERATOR_UPDATE);
		//校验生成问卷其他信息是否合法
		QuestionnaireValidateUtils.canCreateQuestionnaire(questionnaireInfo, 
				questionnaireMapperList,haveOperateList);
		//设置问卷状态
		questionnaireInfo.setStatus(QuestionnaireUtils.operateQuestionnaireStatus(
				questionnaireInfo.getEffectiveTime(), questionnaireInfo.getInvalidTime()));
		//获得当前登录的用户
		User user=(User)UserContext.getCurrentUser();
		String userId = user.getEmpCode().getId();
		questionnaireInfo.setLastModifyUserId(userId);
		//修改问卷主体信息
		questionnaireService.updateQuestionnaire(questionnaireInfo);
		//如果修改时需要修改问题列表，则首先执行删除操作，在执行插入问卷问题映射的操作
		if(QuestionnaireConstance.HAVE_OPERATE_LIST_YES.equals(haveOperateList)){
			String idList[] = {questionnaireInfo.getId()};
			//根据问卷id删除原有的问题问卷映射信息
			questionnaireService.deleteQuestionnaireMapper(idList);
			//循环插入新的问题问卷映射信息
			QuestionnaireMapper mapper = new QuestionnaireMapper();
			for(int i = 0;i < questionnaireMapperList.size();i++){
				mapper = questionnaireMapperList.get(i);
				mapper.setQuestionnaireId(questionnaireInfo.getId());
				mapper.setCreateUser(userId);
				mapper.setModifyUser(userId);
				questionnaireService.createQuestionMapper(mapper);
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Description:根据问卷id数据删除问卷，同时删除对应的问题问卷映射<br />
	 * @author xiaohongye
	 * @param questionnaireId
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	@Transactional
	@Override
	public boolean deleteQuestionnaire(String[] questionnaireId,String questionnaireStatus) {
		//校验问卷id数组是否为空
		QuestionnaireValidateUtils.nullObject(questionnaireId,
				QuestionnaireConstance.OBJECT_QUESTIONNAIRE_ID);
		//根据问卷id查询问卷基本信息
		QuestionnaireQueryCondi questionnaireQueryCondi = new QuestionnaireQueryCondi();
		questionnaireQueryCondi.setQuestionnaireId(questionnaireId[0]);
		List<QuestionnaireInfo> questionnaireInfoList = questionnaireService.searchQuestionnaireInfoList(questionnaireQueryCondi,0,1);
		if(questionnaireInfoList != null){
			questionnaireStatus = questionnaireInfoList.get(0).getStatus();
			//校验问卷的状态是否为可修改状态
			QuestionnaireValidateUtils.questionnaireStatusValidator(questionnaireStatus,
					QuestionnaireConstance.OPERATOR_DELETE);
			//删除问卷主体信息
			questionnaireService.deleteQuestionnaire(questionnaireId);
			//删除问题问卷映射信息
			questionnaireService.deleteQuestionnaireMapper(questionnaireId);
		}
		return true;
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
		//校验问卷id是否为空 
		QuestionnaireValidateUtils.nullObject(questionnaireId,QuestionnaireConstance.OBJECT_QUESTIONNAIRE_ID);
		return questionnaireService.searchQuestionListByQuestionnaireId(questionnaireId);
	}

	/**
	 * <p>
	 * Description:根据问题id列表查询问题基本信息和问题选项信息<br />
	 * @author xiaohongye
	 * @param questionIdList
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-13
	 */
	@Override
	public List<QuestionDetail> searchOptionByQuestionIds(
		List<String> questionIdList,String questionnaireId) {
		//校验问题id列表是否为空 
		QuestionnaireValidateUtils.nullObject(questionIdList,QuestionnaireConstance.OBJECT_QUESTION_ID_LIST);
		return questionService.searchOptionByQuestionId(questionIdList,questionnaireId);
	}
	/**
	 * <p>
	 * Description:根据问卷id统计问卷结果<br />
	 * @author 盛诗庆
	 * @param surverId
	 * @return
	 * @version V0.1 
	 * @param staticEndDate 
	 * @param staticStartDate 
	 * @Date 2014-3-18
	 */
	@Override
	public List<QuestionDetail> staticsQuestionsBySurveyId(String surveyId, Date staticStartDate, Date staticEndDate) {
		//校验问卷id是否为空
		QuestionnaireValidateUtils.nullObject(surveyId, QuestionnaireConstance.OBJECT_QUESTIONNAIRE_ID);
		//？？？查询时间不能为空，截至时间大于起始时间
		
		//获取问卷对应问题列表，排除简答题
		List<QuestionDetail> questionList = questionnaireService.staticsQuestionsBySurveyId(surveyId,staticStartDate,staticEndDate);
		return questionList;
	}
	/**
	 * <p>
	 * Description:根据问卷id和回访时间段查询问卷统计结果<br />
	 * @author 盛诗庆
	 * @param surverId ： 问卷id
	 * @param staticEndDate ：统计开始时间
	 * @param staticStartDate ：统计结束时间
	 * @return List<List<String>>：待导出到excel 的数据
	 * @Date 2014-3-18
	 */
	@Override
	public List<List<String>> getStaticsData(String surveyId,Date staticStartDate,Date staticEndDate) {
		//校验问卷id是否为空
		QuestionnaireValidateUtils.nullObject(surveyId, QuestionnaireConstance.OBJECT_QUESTIONNAIRE_ID);
		List <QuestionDetail> questionList = staticsQuestionsBySurveyId(surveyId,staticStartDate,staticEndDate);
		List<List<String>> exportData = new ArrayList<List<String>>(); 
		for(QuestionDetail question : questionList){
			for(QuestionOption option : question.getOptions()){
				List<String> dataRow = new ArrayList<String>();
				dataRow.add(question.getQuestionTitle());
				dataRow.add(option.getOptionDes());
				dataRow.add(option.getCountTimes());
				exportData.add(dataRow);
			}
			List<String> dataRow = new ArrayList<String>();
			exportData.add(dataRow);
		}
		return exportData;
	}
	/**
	 * <p>
	 * Description:根据问卷id和回访时间段导出问卷统计结果<br />
	 * @author 盛诗庆
	 * @param surverId ： 问卷id
	 * @param staticEndDate ：统计开始时间
	 * @param staticStartDate ：统计结束时间
	 * @return Map<String,Object>：前后台数据流
	 * @Date 2014-3-18
	 */
	@Override
	public Map<String,Object> exportStaticsExcel(String surveyId, String questionnaireTitle,Date staticStartDate,Date staticEndDate){
		Map<String,Object> map = new HashMap<String, Object>();
		if(questionnaireTitle == null){//问卷标题为null，表示执行数据统计，返回统计是否成功标识
			//校验问卷id是否为空
			QuestionnaireValidateUtils.nullObject(surveyId, QuestionnaireConstance.OBJECT_QUESTIONNAIRE_ID);
			//查询统计数据
			staticData = getStaticsData(surveyId, staticStartDate, staticEndDate);
			map.put("canExport", "1");
		}else{//执行下载动作
			// 导出的文件流 
			InputStream inputStream = null;  
			ExportResource exportResource = new ExportResource();//待导出的源文件
			// 列头         
			exportResource.setHeads(new String[] { "问题标题", "选项", "回访情况" }); 
			// 设置导出的数据
			exportResource.setRowList(staticData); 
			//导出设置类
			ExportSetting exportSetting = new ExportSetting();
			//获取文件流
			inputStream = exporter.exportBySheet(exportResource,exportSetting); 
			map.put("questionnaireTitle", questionnaireTitle + QuestionnaireConstance.EXPORT_SUFFIX);//文件后缀xls
			map.put("inputStream", inputStream);
		}
		return map;
	}
	/**
	 * <p>
	 * Description:根据问卷id和回访时间段查出问卷回访的详细结果<br />
	 * @author 盛诗庆
	 * @param surverId ： 问卷id
	 * @param staticEndDate ：统计开始时间
	 * @param staticStartDate ：统计结束时间
	 * @return List<List<String>>：问卷回访的详细客户数据
	 * @Date 2014-3-18
	 */
	@Override
	public List<List<String>> getQuestionnaireVisitDetail(String surveyId,Date staticStartDate,Date staticEndDate){
		//校验问卷id是否为空
		QuestionnaireValidateUtils.nullObject(surveyId, QuestionnaireConstance.OBJECT_QUESTIONNAIRE_ID);
		//获取客户答案信息
		List<CustQuestionnaireDetail> custQuestionnaireList = questionnaireService.exportCustQuestionnaireDetail(surveyId,staticStartDate,staticEndDate);
		return QuestionnaireUtils.convertQuestionAnswer(custQuestionnaireList);
	}
	/**
	 * <p>
	 * Description:根据问卷id和回访时间段导出问卷回访的详细结果<br />
	 * @author 盛诗庆
	 * @param surverId ： 问卷id
	 * @param staticEndDate ：统计开始时间
	 * @param staticStartDate ：统计结束时间
	 * @return Map<String,Object>：前后台数据流
	 * @Date 2014-3-18
	 */
	@Override
	public Map<String,Object> exportCustQuestionnaireDetail(String surveyId, String questionnaireTitle,Date staticStartDate,Date staticEndDate){
		Map<String,Object> map = new HashMap<String, Object>();
		if(questionnaireTitle == null){//执行查询客户问卷回访详细记录操作
			questionList = questionnaireService.searchQuestionListByQuestionnaireId(surveyId);
			detailData = getQuestionnaireVisitDetail(surveyId,staticStartDate,staticEndDate);
			map.put("canExport", "1");
		}else{//执行导出客户问卷回访详情
			// 导出的文件流 
			InputStream inputStream = null;  
			//待导出的源文件
			ExportResource exportResource = new ExportResource();
			// 列头         
			exportResource.setHeads(QuestionnaireUtils.getHeads(questionList));
			//设置导出数据
			exportResource.setRowList(detailData); 
			//导出设置类
			ExportSetting exportSetting = new ExportSetting();
			// 导出最大行数20000
			exportSetting.setMaxCount(QuestionnaireConstance.MAX_ROW); 
			//获取文件流
			inputStream = exporter.exportBySheet(exportResource,exportSetting); 
			map.put("questionnaireTitle", questionnaireTitle + QuestionnaireConstance.EXPORT_SUFFIX);//添加文件后缀
			map.put("inputStream", inputStream);
		}
		return map;
	}
	/**
	 * 保存问卷回访结果（答案结果）
	 * @author xiaohongye
	 * @param answerMainInfoList
	 */
	@Override
	@Transactional
	public void saveCustAnswer(List<AnswerMainInfo> answerMainInfoList) {
		//问卷答案信息主表id
		String answerMainInfoId = null;
		for(AnswerMainInfo answerMainInfo:answerMainInfoList){
			//问卷回访时向用户问卷答案信息主表中插入数据
			questionnaireService.insertSurveyMainAnswer(answerMainInfo);
			//获得问卷答案信息主表id
			answerMainInfoId = answerMainInfo.getId();
			//获得问卷答案明细信息列表
			List<AnswerDetailInfo> answerDetailInfoList = answerMainInfo.getAnswerList();
			//为问卷信息列表中的问卷明细信息设置问卷答案信息主表id
			for(AnswerDetailInfo answerDetailInfo:answerDetailInfoList){
				answerDetailInfo.setAnswerMainInfoId(answerMainInfoId);
				//问卷回访时向用户问卷答案信息明细表中插入数据
				questionnaireService.insertSurveyDetailAnswer(answerDetailInfo);
			}
		}
	}

	/**
	 * <p>
	 * Description:新增、修改含问卷的计划时根据问卷id更新问卷状态<br />
	 * @author xiaohongye
	 * @param questionnaireId 问卷id
	 * @param operateType 操作类型，ADD表示新增；UPDATE表示修改；DELETE表示删除
	 * @version V0.1 
	 * @Date 2014-3-29
	 */
	@Override
	@Transactional
	public boolean updateSurveyStatusByID(String questionnaireId,String operateType) {
		//判断问卷id是否为空
		QuestionnaireValidateUtils.nullObject(questionnaireId,
				QuestionnaireConstance.OBJECT_QUESTIONNAIRE_ID);
		//更新问卷状态的问卷信息实体
		QuestionnaireInfo surveyInfo = new QuestionnaireInfo();
		surveyInfo.setId(questionnaireId);
		//获得当前登录的用户问卷状态变更不算问卷变更，不需要记录变更人
//		User user=(User)UserContext.getCurrentUser();
//		String userId = user.getEmpCode().getId();
//		surveyInfo.setLastModifyUserId(userId);
		//根据问卷id查询问卷基本信息
		QuestionnaireQueryCondi questionnaireQueryCondi = new QuestionnaireQueryCondi();
		questionnaireQueryCondi.setQuestionnaireId(questionnaireId);
		List<QuestionnaireInfo> questionnaireInfoList = questionnaireService.searchQuestionnaireInfoList(questionnaireQueryCondi,0,1);
		if(questionnaireInfoList != null){
			QuestionnaireInfo questionnaireInfo = questionnaireInfoList.get(0);
			//根据问卷适用次数、问卷生效时间、问卷失效时间设置问卷状态
			String questionnaireStatus = questionnaireInfo.getStatus();
			//如果是新增含有问卷的计划
			if(QuestionnaireConstance.OPERATE_TYPE_ADD.equals(operateType)){
				//如果问卷当前状态不为适用中,则更新问卷状态为使用中
				if(!(QuestionnaireConstance.SURVEY_STATUS_USING.equals(questionnaireStatus))){
					surveyInfo.setStatus(QuestionnaireConstance.SURVEY_STATUS_USING);
					//更新问卷状态
					questionnaireService.updateQuestionnaireStatusById(surveyInfo);
					return true;
				}
			}
			//如果是修改或者删除含有问卷的计划
			if(QuestionnaireConstance.OPERATE_TYPE_UPDATE.equals(operateType) ||
					QuestionnaireConstance.OPERATE_TYPE_DELETE.equals(operateType)){
				//获得问卷适用次数
				int questionnaireUseTimes = questionnaireInfo.getUseTimes();
				questionnaireUseTimes--;
				//如果问卷被使用次数大于零，设置问卷状态为使用中
				if(questionnaireUseTimes > 0){
					//如果问卷当前状态不为适用中,则更新问卷状态为使用中
					if(!(QuestionnaireConstance.SURVEY_STATUS_USING.equals(questionnaireStatus))){
						surveyInfo.setUseTimes(questionnaireUseTimes);
						surveyInfo.setStatus(QuestionnaireConstance.SURVEY_STATUS_USING);
						//更新问卷状态
						questionnaireService.updateQuestionnaireStatusById(surveyInfo);
						return true;
					}
				}
				else{
					//问卷生效时间
					Date effectiveTime =  questionnaireInfo.getEffectiveTime();
					//问卷失效时间
					Date invalidTime =  questionnaireInfo.getInvalidTime();
					//比较当前时间与问卷生效时间与失效时间之间的关系
					String latestQuestionnaireStatus = QuestionnaireUtils.operateQuestionnaireStatus(effectiveTime, invalidTime);
					//如果查询返回的问卷状态与当前应该有的最新的问卷状态不一致，则更新问卷状态为最新状态
					if(!(latestQuestionnaireStatus.equals(questionnaireStatus))){
						surveyInfo.setStatus(latestQuestionnaireStatus);
						//更新问卷状态
						surveyInfo.setUseTimes(questionnaireUseTimes);
						questionnaireService.updateQuestionnaireStatusById(surveyInfo);
						return true;
					}
				}	
			}
		}
		return true;
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
	public Map<String,Object> queryQuestionnaireListByCustid(
			String custId,int start,int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<QuestionnaireInfo> questionnaireList = questionnaireService.queryQuestionnaireListByCustid(custId,start,limit);
		int totalCount = questionnaireService.countQuestionnaireListByCustid(custId);
		map.put("totalCount", totalCount);
		map.put("questionnaireList", questionnaireList);
		return map;
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
	public List<QuestionDetail> viewQuestionnaireByVisitId(String visitId){
		//回访id不能为空
		QuestionnaireValidateUtils.nullObject(visitId, QuestionnaireConstance.OBJECT_VISIT_ID);
		return questionnaireService.viewQuestionnaireByVisitId(visitId);
	}
	/**
	 * <p>
	 * Description:定时更新问卷状态<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-31
	 */
	@Override
	public String updateSurveyStatus() {
		// 如果问卷的状态为“待生效”，且“当前时间”晚于“问卷生效时间”早于“问卷失效时间”，更新问卷状态为“生效”
		int i = questionnaireService.updateSurveyStatus(QuestionnaireConstance.SURVEY_STATUS_VALID);
		// 如果问卷的状态为“生效”或“使用中”，且“当前时间”晚于“问卷失效时间”，更新问卷状态为“过期”
		int j = questionnaireService.updateSurveyStatus(QuestionnaireConstance.SURVEY_STATUS_OVERDUE);
		String result = i +"份问卷被更新为生效，" + j +"份问卷被更新为过期。";
		return result;
	}
	/**
	 * @return the exporter
	 */
	public ExcelExporter getExporter() {
		return exporter;
	}
	/**
	 * @param exporter the exporter to set
	 */
	public void setExporter(ExcelExporter exporter) {
		this.exporter = exporter;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IQuestionnaireManager#queryQuestionnaireById(java.lang.String)
	 */
	@Override
	public QuestionnaireInfo queryQuestionnaireById(String id) {
		return questionnaireService.queryQuestionnaireById(id);
	}
}
