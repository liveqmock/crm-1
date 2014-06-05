/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireInfo.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-5
 */
package com.deppon.crm.module.marketing.shared.domain.questionnaire;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireInfo.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-5
 */
public class QuestionnaireInfo extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//问卷名称
	private String questionnaireName;
	//问卷生效时间
	private Date effectiveTime;
	//问卷失效时间
	private Date invalidTime;
	//问卷适用范围
	private String useRange;
	//问卷说明与描述
	private String desc;
	//问卷状态
	private String status;
	//问卷编号
	private String questionnaireCode;
	//问卷采用次数
	private int useTimes;
	//创建人id
	private String createUserId;
	//修改人id
	private String lastModifyUserId;
	//回访id
	private String visitId;
	//客户联系人姓名
	private String linkManName;
	//计划主题
	private String planTopic;
	//营销方式
	private String marketingWay;
	//营销人
	private String executorName;
	//营销时间
	private Date marketingDate;
	//营销历史记录中的问卷id
	private String questionnaireId;	
	
	/**
	 * @param questionnaireId the questionnaireId to get
	 */
	public String getQuestionnaireId() {
		return questionnaireId;
	}
	/**
	 * @param questionnaireId the questionnaireId to set
	 */
	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	/**
	 * @param createUserId the createUserId to get
	 */
	public String getCreateUserId() {
		return createUserId;
	}
	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * @param lastModifyUserId the lastModifyUserId to get
	 */
	public String getLastModifyUserId() {
		return lastModifyUserId;
	}
	/**
	 * @param lastModifyUserId the lastModifyUserId to set
	 */
	public void setLastModifyUserId(String lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}
	/**
	 * @param useTimes the useTimes to get
	 */
	public int getUseTimes() {
		return useTimes;
	}
	/**
	 * @param useTimes the useTimes to set
	 */
	public void setUseTimes(int useTimes) {
		this.useTimes = useTimes;
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
	 * @param effectiveTime the effectiveTime to get
	 */
	public Date getEffectiveTime() {
		return effectiveTime;
	}
	/**
	 * @param effectiveTime the effectiveTime to set
	 */
	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	/**
	 * @param invalidTime the invalidTime to get
	 */
	public Date getInvalidTime() {
		return invalidTime;
	}
	/**
	 * @param invalidTime the invalidTime to set
	 */
	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
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
	 * @param desc the desc to get
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @param status the status to get
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	public String getVisitId() {
		return visitId;
	}
	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}
	public String getLinkManName() {
		return linkManName;
	}
	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}
	public String getPlanTopic() {
		return planTopic;
	}
	public void setPlanTopic(String planTopic) {
		this.planTopic = planTopic;
	}
	public String getMarketingWay() {
		return marketingWay;
	}
	public void setMarketingWay(String marketingWay) {
		this.marketingWay = marketingWay;
	}
	public String getExecutorName() {
		return executorName;
	}
	public void setExecutorName(String executorName) {
		this.executorName = executorName;
	}
	public Date getMarketingDate() {
		return marketingDate;
	}
	public void setMarketingDate(Date marketingDate) {
		this.marketingDate = marketingDate;
	}
}
