/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-10
 */
package com.deppon.crm.module.marketing.shared.exception;

/**
 * <p>
 * Description:问卷管理的异常类型枚举类<br />
 * </p>
 * @title QuestionnaireExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-10
 */
public enum QuestionnaireExceptionType {
	
	//创建时间范围与有效时间范围不能同时为空！
	questionnaireQueryDateWrong("i18n.questionnaireManage.queryDateWrong"),
	//创建时间最大查询范围为90天！
	questionnaireQueryCreateDateExceed("i18n.questionnaireManage.queryCreateDateExceed"),
	//有效时间最大查询范围为90天！
	questionnaireQueryValidDateExceed("i18n.questionnaireManage.queryValidDateExceed"),
	//问卷名称不能为空！
	nullQuestionnaireName("i18n.questionnaireManage.nullQuestionnaireName"),
	//问卷名称不能重复！
	repeatQuestionnaireName("i18n.questionnaireManage.repeatQuestionnaireName"),
	//每天最多可生成99份问卷，超出限制部分请改天生成！
	exceedQuestionnaireCode("i18n.questionnaireManage.exceedQuestionnaireCode"),
	//生效时间与失效时间最大允许时间范围为90天！
	exceedValidDateRange("i18n.questionnaireManage.exceedValidDateRange"),
	//生效时间与失效时间不能为空！
	nullValidDateRange("i18n.questionnaireManage.nullValidDateRange"),
	//生效时间必须早于失效时间！
	compareEffectiveDate("i18n.questionnaireManage.compareEffectiveDate"),
	//开始创建时间必须早于结束创建时间！
	compareCreateDate("i18n.questionnaireManage.compareCreateDate"),
	//开始生效时间必须早于结束生效时间！
	compareValidDate("i18n.questionnaireManage.compareValidDate"),
	//抱歉，一套问卷中最多有20道问题！
	exceedMaxQuestions("i18n.questionnaireManage.exceedMaxQuestions"),
	//抱歉，一套问卷中至少有2道问题！
	needMoreQuestions("i18n.questionnaireManage.needMoreQuestions"),
	//抱歉，此问卷状态为使用中或者已过期，不允许删除或者修改！
	invalidStatus("i18n.questionnaireManage.invalidStatus"),
	//抱歉，生效时间必须晚于当前时间！
	effectiveTimeTooEarly("i18n.questionnaireManage.effectiveTimeTooEarly"),
	//抱歉，问卷中的问题列表不能为空！
	nullQuestionList("i18n.questionnaireManage.nullQuestionList"),
	//抱歉，某个对象为空，不允许继续操作！
	nullObject("i18n.questionnaireManage.nullObject"),
	//每次最多只能删除一个问题
	moreThanOneChoosed("i18n.questionManage.message.chooseOne"),
	//您好，问题选项最少两个，最多六个
	illegalOption("i18n.questionManage.message.illegalOption"),
	//问题标题不能为空
	questionTitleCannotBeNull("i18n.questionManage.message.questionTitleCannotBeNull"),
	//问题标题少于100字
	questionTitleExceed("i18n.questionManage.message.questionTitleExceed"),
	//问题说明少于100字
	questionContentExceed("i18n.questionManage.message.questionContentExceed"),
	//查询时间范围不能为空
	searchTimeLimitNotnull("i18n.questionManage.message.searchTimeLimitNotnull"),
	//起始时间必须小于等于结束时间
	searchTimeLimitInvalid("i18n.questionManage.message.searchTimeLimitInvalid"),
	//查询时间范围在九十天
	timeLimitExceed("i18n.questionManage.message.timeLimitExceed"),
	//问题被引用
	questionIsOccupied("i18n.questionManage.message.questionIsOccupied"),
	//问题标题已经存在
	questionTitleIsExist("i18n.questionManage.message.questionTitleIsExist"),
	//问题适用范围不能为空
	useRangeCannotBeNull("i18n.questionManage.message.useRangeCannotBeNull"),
	//问题类型不能为空
	questionTypeCannotBeNull("i18n.questionManage.message.questionTypeCannotBeNull"),
	//编码转化异常
	codeConvertException("i18n.questionnaireManage.codeConvertException")
	;
	
	//异常代码
	private String errCode;
	
	
	/**
	 * @return errCode : return the property errCode.
	 */ 	
	public String getErrCode() {
		return errCode;
	}
	/**
	 * @param errCode : set the property errCode.
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	/**
	 * 
	 * constructer
	 * @param errorCode
	 */
	private QuestionnaireExceptionType(String errorCode) {
		this.errCode = errorCode;
	}
}
