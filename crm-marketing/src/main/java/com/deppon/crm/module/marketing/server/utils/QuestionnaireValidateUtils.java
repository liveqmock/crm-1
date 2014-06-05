/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireValidateUtils.java
 * @package com.deppon.crm.module.marketing.server.utils
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-10
 */
package com.deppon.crm.module.marketing.server.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;


import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionOption;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireConstance;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireMapper;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireQueryCondi;
import com.deppon.crm.module.marketing.shared.exception.QuestionnaireException;
import com.deppon.crm.module.marketing.shared.exception.QuestionnaireExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireValidateUtils.java
 * @package com.deppon.crm.module.marketing.server.utils
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-10
 */
public class QuestionnaireValidateUtils {
	/**
	 * 
	 * <p>
	 * Description:比较两个时间，仅比较日期，不包括时分秒<br />
	 * @author xiaohongye
	 * @param d1
	 * @param d2
	 * @return d1早于d2,返回-1;d1等于d2,返回0;d1晚于d2,返回1;
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	public static int dateCompareTo(Date d1, Date d2) {
		//compare
		return DateUtils.truncatedCompareTo(d1, d2, Calendar.DATE);
	}

	/**
	 * 
	 * <p>
	 * Description:比较两个时间的时间差<br />
	 * @author xiaohongye
	 * @param d1
	 * @param d2
	 * @param field
	 * @param amount
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	public static boolean compareTwoDateByField(Date d1, Date d2, int field, int amount){
		//临时变量
		Date tmp = null;
		//field字段
		switch (field) {
		//日期
		case Calendar.DATE:
			//增加amount天
			tmp = DateUtils.addDays(d1, amount);
			break;
		case Calendar.MONTH:
			//增加amount 月
			tmp = DateUtils.addMonths(d1, amount);
			break;
			//年份
		case Calendar.YEAR:
			//增加amount年
			tmp = DateUtils.addYears(d1, amount);
			break;
		//否则
		default:
			return false;
		}
		//返回比较结果
		return (QuestionnaireValidateUtils.dateCompareTo(tmp,d2) >= 0) ? true : false;
	}
	
	/**
	 * 
	 * <p>
	 * Description:校验问卷管理查询主页面的查询条件是否合法<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	public static void canQueryQuestionnaire(QuestionnaireQueryCondi queryCondi){
		//开始创建时间
		Date createStartDate = queryCondi.getCreateStartDate();
		//结束创建时间
		Date createEndDate = queryCondi.getCreateEndDate();
		//开始有效时间
		Date validStartDate = queryCondi.getValidStartDate();
		//结束有效时间
		Date validEndDate = queryCondi.getValidEndDate();
		//问卷编号为空时，校验创建时间范围与有效时间范围是否合法
		if(queryCondi.getQuestionnaireCode() == null || queryCondi.getQuestionnaireCode().trim() == null
				 || queryCondi.getQuestionnaireCode().trim() == ""){
			//问卷编号为空时，创建时间范围与有效时间范围至少有一组不为空
			if(!((createStartDate != null && createEndDate != null)
					|| (validStartDate != null && validEndDate != null))){
				QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.questionnaireQueryDateWrong);
				//抛出异常
	            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
					private static final long serialVersionUID = 1L;};
			}
			else if(createStartDate != null && createEndDate != null){
				//开始创建时间必须早于结束创建时间！
				if(QuestionnaireValidateUtils.dateCompareTo(createStartDate, createEndDate) >= 0){
					QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.compareCreateDate);
					//抛出异常
		            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
						private static final long serialVersionUID = 1L;};
				}
				//创建时间最大查询范围为90天！
				else if(!QuestionnaireValidateUtils.compareTwoDateByField(createStartDate, createEndDate, Calendar.DATE, 90)){
					QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.questionnaireQueryCreateDateExceed);
					//抛出异常
		            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
						private static final long serialVersionUID = 1L;};
				};
			}
			else if(validStartDate != null && validEndDate != null){
				//开始有效时间必须早于结束有效时间！
				if(QuestionnaireValidateUtils.dateCompareTo(validStartDate, validEndDate) >= 0){
					QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.compareValidDate);
					//抛出异常
		            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
						private static final long serialVersionUID = 1L;};
				}
				//有效时间最大查询范围为90天！
				else if(!QuestionnaireValidateUtils.compareTwoDateByField(validStartDate, validEndDate, Calendar.DATE, 90)){
					QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.questionnaireQueryValidDateExceed);
					//抛出异常
		            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
						private static final long serialVersionUID = 1L;};
				};
			}
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:校验生成问卷的问卷名称是否为空<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	public static void nullQuestionnaireName(String questionnaireName){
		//问卷名称不可为空
		if(questionnaireName == null || 
				questionnaireName.trim() == null|| 
						questionnaireName.trim() == ""){
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.nullQuestionnaireName);
			//抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
				private static final long serialVersionUID = 1L;};
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:判断一个对象是否为空<br />
	 * @author xiaohongye
	 * @param o
	 * @param objectName
	 * @version V0.1 
	 * @Date 2014-3-11
	 */
	public static void nullObject(Object o,String objectName){
		if(o == null){
			throw new QuestionnaireException(QuestionnaireExceptionType.nullObject,
					new Object[]{objectName});
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:校验当前要生成的问卷名称在数据库中是否已经存在<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	public static void repeatQuestionnaireName(QuestionnaireInfo questionnaire){
		//问卷名称不能重复！
		if(questionnaire != null && questionnaire.getQuestionnaireCode() != null){
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.repeatQuestionnaireName);
			//抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
				private static final long serialVersionUID = 1L;};
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:校验问卷编号是否超过01~99的范围<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	public static void exceedQuestionnaireCode(String questionnaireCode){
		////每天最多可生成99份问卷，超出限制部分请改天生成！
		if("EXCEED".equals(questionnaireCode)){
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.exceedQuestionnaireCode);
			//抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
				private static final long serialVersionUID = 1L;};
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:校验生成\修改问卷的其他信息是否合法<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-10
	 */
	public static void canCreateQuestionnaire(QuestionnaireInfo questionnaireInfo,
			List<QuestionnaireMapper> questionnaireMapperList,String haveOperateList){
		//生效日期
		Date effectiveTime = questionnaireInfo.getEffectiveTime();
		//生效日期
		Date invalidTime = questionnaireInfo.getInvalidTime();
		//生效时间与失效时间不能为空！
		if(effectiveTime == null || invalidTime == null){
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.nullValidDateRange);
			//抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
				private static final long serialVersionUID = 1L;};
		}
		//生效时间必须早于失效时间！
		if(QuestionnaireValidateUtils.dateCompareTo(effectiveTime, invalidTime) >= 0){
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.compareEffectiveDate);
			//抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
				private static final long serialVersionUID = 1L;};
		}
		//生效时间与失效时间最大允许时间范围为90天！
		if(!QuestionnaireValidateUtils.compareTwoDateByField(effectiveTime, invalidTime, Calendar.DATE, 90)){
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.exceedValidDateRange);
			//抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
				private static final long serialVersionUID = 1L;};
		}
		//校验问题列表是否合法
		if(QuestionnaireConstance.HAVE_OPERATE_LIST_YES.equals(haveOperateList)){
			//抱歉，问卷中的问题列表不能为空！
			if(questionnaireMapperList == null || questionnaireMapperList.size() == 0 ){
				QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.nullQuestionList);
				//抛出异常
	            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
					private static final long serialVersionUID = 1L;};
			}
			//抱歉，一套问卷中最多有20道问题！
			if(questionnaireMapperList.size() > QuestionnaireConstance.MAX_QUESTION_NUMBER){
	            throw new QuestionnaireException(QuestionnaireExceptionType.exceedMaxQuestions,
	            		new Object[]{QuestionnaireConstance.MAX_QUESTION_NUMBER});
			}
			//抱歉，一套问卷中至少有2道问题！
			if(questionnaireMapperList.size() < QuestionnaireConstance.MIN_QUESTION_NUMBER){
	            throw new QuestionnaireException(QuestionnaireExceptionType.needMoreQuestions,
	            		new Object[]{QuestionnaireConstance.MIN_QUESTION_NUMBER});
			}	
		}
	}

	/**
	 * 
	 * <p>
	 * Description:校验问卷的状态是否合法<br />
	 * @author xiaohongye
	 * @param status
	 * @param operator 操作内容：DELETE表示删除；UPDATE表示修改;
	 * @version V0.1 
	 * @Date 2014-3-11
	 */
	public static void questionnaireStatusValidator(String status,String operator){
		//如果问卷的状态为使用中或者已过期，则不允许修改删除
		if(QuestionnaireConstance.SURVEY_STATUS_USING.equals(status) ||
				QuestionnaireConstance.SURVEY_STATUS_OVERDUE.equals(status)){
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.illegalOption);
			//抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
				private static final long serialVersionUID = 1L;};
		}
	}
	/**
	 * 
	 * <p>
	 * Description:校验问题选项数是否合法<br />
	 * @author 盛诗庆
	 * @param optionList
	 * @param elseOption 是否允许填空：1表示允许；0表示不允许;
	 * @version V0.1 
	 * @Date 2014-3-14
	 */
	public static void questionOptionsValidator(List<QuestionOption> optionList,String elseOption){
		int count = elseOption.equals("1") ? 1:0;//是否允许填空
		//如果问题选项为空
		if(optionList == null){
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.illegalOption);
			//抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
				private static final long serialVersionUID = 1L;};
		}else{//选项超过六个
			if(optionList.size() + count > 6){
				throw new QuestionnaireException(QuestionnaireExceptionType.illegalOption);
			}else if(optionList.size() + count < 2){//选项不足两个
				QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.illegalOption);
				//抛出异常
	            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
					private static final long serialVersionUID = 1L;};
			}
		}
	}
	/**
	 * 
	 * <p>
	 * Description:校验问题内容是否合法<br />
	 * @author 盛诗庆
	 * @param questionDetail
	 * @version V0.1 
	 * @Date 2014-3-11
	 */
	public static void questionDetailValidator(QuestionDetail questionDetail){
		String useRange = questionDetail.getUseRange();//问题适用范围
		String questionType = questionDetail.getQuestionType();//问题类型
		String questionTitle = questionDetail.getQuestionTitle();//问题标题
		String questionContent = questionDetail.getQuestionContent();//问题说明
		if(useRange == null || "".equals(useRange)){//适用范围不能为空
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.useRangeCannotBeNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
					private static final long serialVersionUID = 1L;};
		}
		if(questionType == null || "".equals(questionType)){//问题类型不能为空
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.questionTypeCannotBeNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
					private static final long serialVersionUID = 1L;};
		}
		if(questionTitle == null || "".equals(questionTitle)){//问题标题不能为空
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.questionTitleCannotBeNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
					private static final long serialVersionUID = 1L;};
		}else if(questionTitle.length() > 100){//问题标题长度不能大于100个字符
			QuestionnaireException e =  new QuestionnaireException(QuestionnaireExceptionType.questionTitleExceed);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
					private static final long serialVersionUID = 1L;};
		}
		if(questionContent != null && questionContent.length() > 300){//问题说明长度不能大于300
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.questionContentExceed);
			//抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
				private static final long serialVersionUID = 1L;};
		}
	}
	/**
	 * 
	 * <p>
	 * Description:验证查询问题的条件是否合法<br />
	 * @author 盛诗庆
	 * @param questionDetail
	 * @version V0.1 
	 * @Date 2014-3-11
	 */
	public static void questionQueryConditionDetailValidator(QuestionQueryCondition questionQueryCondition){
		Date startTime = questionQueryCondition.getCreateStartDate();//起始时间
		Date endTime = questionQueryCondition.getCreateEndDate();//结束时间
		//时间范围不能为空
		if(startTime == null || endTime == null){
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.searchTimeLimitNotnull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
				private static final long serialVersionUID = 1L;};
		}
		//起始时间小于结束时间
		if(QuestionnaireValidateUtils.dateCompareTo(endTime, startTime) < 0){
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.searchTimeLimitInvalid);
			
			//抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
				private static final long serialVersionUID = 1L;};
		}
		//时间范围超过九十天
		if(!QuestionnaireValidateUtils.compareTwoDateByField(startTime, endTime, Calendar.DATE, 90)){
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.timeLimitExceed);
			//抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
				private static final long serialVersionUID = 1L;};
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:检查是否多选删除<br />
	 * @author 盛诗庆
	 * @param questionDetail
	 * @version V0.1 
	 * @Date 2014-3-17
	 */
	public static void deleteValidatore(List<String> ids){
		//多选删除
		if(ids !=null && ids.size() > 1){
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.moreThanOneChoosed);
			//抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
				private static final long serialVersionUID = 1L;};
		}
	}
	/**
	 * <p>
	 * Description:验证问题标题是否重复<br />
	 * @author 盛诗庆
	 * @param questionTitle
	 * @version V0.1 
	 * @Date 2014-3-17
	 */
	public static void questionTitleIsExist(List<QuestionDetail> questionList,QuestionDetail question){
		//名称重复
		if(questionList !=null && questionList.size() > 1){
			QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.questionTitleIsExist);
			//抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
				private static final long serialVersionUID = 1L;};
		}
		if(questionList !=null && questionList.size() == 1){//有重名
			if(question.getId() != null && !"".equals(question.getId())){//修改问题时
				QuestionDetail q = questionList.get(0);
				if(q.getId() != null && q.getId().equalsIgnoreCase(question.getId())){//问题id一致，本问题不算重名
					return;
				}else{
					QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.questionTitleIsExist);
					//抛出异常
		            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
						private static final long serialVersionUID = 1L;};
				}
			}else{//新增问题
				QuestionnaireException e = new QuestionnaireException(QuestionnaireExceptionType.questionTitleIsExist);
				//抛出异常
	            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}){
					private static final long serialVersionUID = 1L;};
			}
		}
	}
}
