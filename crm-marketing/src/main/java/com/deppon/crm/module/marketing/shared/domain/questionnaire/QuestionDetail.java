/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionDetail.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1
 * @Date 2014-3-5
 */
package com.deppon.crm.module.marketing.shared.domain.questionnaire;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * <p>
 * Description:问题明细信息实体<br />
 * </p>
 * @title QuestionDetail.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1
 * @Date 2014-3-5
 */
public class QuestionDetail extends BaseEntity {
	private static final long serialVersionUID = 1L;
	//问题标题
	private String questionTitle;
	//问题说明
	private String questionContent;
	//适用范围
	private String useRange;
	//问题类型
	private String questionType;
	//是否有"其他"选项
	private String elseOption;
	//创建人id
	private String createUserId;
	//修改人id
	private String lastModifyUserId;
	//问题编码
	private String questionCode;
	//问题被问卷引用次数
	private int frequency;
	//问题对应的选项列表
	private List<QuestionOption> options=new ArrayList<QuestionOption>();
	//问题序号
	private int questionSeq;
	//问卷id
	private String questionnaireId;
	//受访人数
	private int  respondents;
	//统计起始时间
	private Date staticStartTime;
	//统计结束时间
	private Date staticEndTime;
	//回访id
	private String visitId;


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
	 * @param questionSeq the questionSeq to get
	 */
	public int getQuestionSeq() {
		return questionSeq;
	}
	/**
	 * @param questionSeq the questionSeq to set
	 */
	public void setQuestionSeq(int questionSeq) {
		this.questionSeq = questionSeq;
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
	 * @param questionTitle the questionTitle to get
	 */
	public String getQuestionTitle() {
		return questionTitle;
	}
	/**
	 * @param questionTitle the questionTitle to set
	 */
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	/**
	 * @param questionContent the questionContent to get
	 */
	public String getQuestionContent() {
		return questionContent;
	}
	/**
	 * @param questionContent the questionContent to set
	 */
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
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
	 * @param questionType the questionType to get
	 */
	public String getQuestionType() {
		return questionType;
	}
	/**
	 * @param questionType the questionType to set
	 */
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	/**
	 * @param elseOption the elseOption to get
	 */
	public String getElseOption() {
		return elseOption;
	}
	/**
	 * @param elseOption the elseOption to set
	 */
	public void setElseOption(String elseOption) {
		this.elseOption = elseOption;
	}
	/**
	 * @return the questionCode
	 */
	public String getQuestionCode() {
		return questionCode;
	}
	/**
	 * @param questionCode the questionCode to set
	 */
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}
	/**
	 * @return the frequency
	 */
	public int getFrequency() {
		return frequency;
	}
	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	/**
	 * @return the options
	 */
	public List<QuestionOption> getOptions() {
		return options;
	}
	/**
	 * @param options the options to set
	 */
	public void setOptions(List<QuestionOption> options) {
		this.options = options;
	}
	/**
	 * @return the respondents
	 */
	public int getRespondents() {
		return respondents;
	}
	/**
	 * @param respondents the respondents to set
	 */
	public void setRespondents(int respondents) {
		this.respondents = respondents;
	}
	public Date getStaticStartTime() {
		return staticStartTime;
	}
	public void setStaticStartTime(Date staticStartTime) {
		this.staticStartTime = staticStartTime;
	}
	public Date getStaticEndTime() {
		return staticEndTime;
	}
	public void setStaticEndTime(Date staticEndTime) {
		this.staticEndTime = staticEndTime;
	}
	public String getVisitId() {
		return visitId;
	}
	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}
}
