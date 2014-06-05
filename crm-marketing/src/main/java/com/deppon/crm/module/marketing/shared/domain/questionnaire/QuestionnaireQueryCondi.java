/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireQueryCondi.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-5
 */
package com.deppon.crm.module.marketing.shared.domain.questionnaire;

import java.util.Date;

/**
 * <p>
 * Description:问卷管理查询条件<br />
 * </p>
 * @title QuestionnaireQueryCondi.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-5
 */
public class QuestionnaireQueryCondi {
	//问卷编号
	private String questionnaireCode;
	//问卷名称
	private String questionnaireName;
	//问卷适用范围
	private String useRange;
	//开始创建时间
	private Date createStartDate;
	//结束创建时间
	private Date createEndDate;
	//开始生效时间
	private Date validStartDate;
	//结束生效时间
	private Date validEndDate;
	//问卷状态
	private String questionnaireStatus;
	//开始回访时间，用于问卷统计结果查询
	private Date visitStartDate;
	//结束回访时间
	private Date visitEndDate;
	//问卷id
	private String questionnaireId;
	//问卷状态查询类型，在选择问卷时只能查询适用中和生效的文件
	private String questionnaireStatusType;
	
	/**
	 * @param questionnaireStatusType the questionnaireStatusType to get
	 */
	public String getQuestionnaireStatusType() {
		return questionnaireStatusType;
	}
	/**
	 * @param questionnaireStatusType the questionnaireStatusType to set
	 */
	public void setQuestionnaireStatusType(String questionnaireStatusType) {
		this.questionnaireStatusType = questionnaireStatusType;
	}
	/**
	 * @param visitStartDate the visitStartDate to get
	 */
	public Date getVisitStartDate() {
		return visitStartDate;
	}
	/**
	 * @param visitStartDate the visitStartDate to set
	 */
	public void setVisitStartDate(Date visitStartDate) {
		this.visitStartDate = visitStartDate;
	}
	/**
	 * @param visitEndDate the visitEndDate to get
	 */
	public Date getVisitEndDate() {
		return visitEndDate;
	}
	/**
	 * @param visitEndDate the visitEndDate to set
	 */
	public void setVisitEndDate(Date visitEndDate) {
		this.visitEndDate = visitEndDate;
	}
	/**
	 * @param questionnaireCode the questionnaireCode to get
	 */
	public String getQuestionnaireCode() {
		return questionnaireCode;
	}
	/**
	 * @param questionnaireCode the questionnaireCode to set
	 */
	public void setQuestionnaireCode(String questionnaireCode) {
		this.questionnaireCode = questionnaireCode;
	}
	/**
	 * @param questionnaireName the questionnaireName to get
	 */
	public String getQuestionnaireName() {
		return questionnaireName;
	}
	/**
	 * @param questionnaireName the questionnaireName to set
	 */
	public void setQuestionnaireName(String questionnaireName) {
		this.questionnaireName = questionnaireName;
	}
	/**
	 * @param useRange the useRange to get
	 */
	public String getUseRange() {
		return useRange;
	}
	/**
	 * @param useRange the useRange to set
	 */
	public void setUseRange(String useRange) {
		this.useRange = useRange;
	}
	/**
	 * @param createStartDate the createStartDate to get
	 */
	public Date getCreateStartDate() {
		return createStartDate;
	}
	/**
	 * @param createStartDate the createStartDate to set
	 */
	public void setCreateStartDate(Date createStartDate) {
		this.createStartDate = createStartDate;
	}
	/**
	 * @param createEndDate the createEndDate to get
	 */
	public Date getCreateEndDate() {
		return createEndDate;
	}
	/**
	 * @param createEndDate the createEndDate to set
	 */
	public void setCreateEndDate(Date createEndDate) {
		this.createEndDate = createEndDate;
	}
	/**
	 * @param validStartDate the validStartDate to get
	 */
	public Date getValidStartDate() {
		return validStartDate;
	}
	/**
	 * @param validStartDate the validStartDate to set
	 */
	public void setValidStartDate(Date validStartDate) {
		this.validStartDate = validStartDate;
	}
	/**
	 * @param validEndDate the validEndDate to get
	 */
	public Date getValidEndDate() {
		return validEndDate;
	}
	/**
	 * @param validEndDate the validEndDate to set
	 */
	public void setValidEndDate(Date validEndDate) {
		this.validEndDate = validEndDate;
	}
	public String getQuestionnaireId() {
		return questionnaireId;
	}
	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	public String getQuestionnaireStatus() {
		return questionnaireStatus;
	}
	public void setQuestionnaireStatus(String questionnaireStatus) {
		this.questionnaireStatus = questionnaireStatus;
	}
}
