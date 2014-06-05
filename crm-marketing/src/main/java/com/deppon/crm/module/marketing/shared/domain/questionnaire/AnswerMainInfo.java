/**
 * <p>
 * Description:<br />
 * </p>
 * @title AnswerMainInfo.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
package com.deppon.crm.module.marketing.shared.domain.questionnaire;

import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * <p>
 * Description:问卷答案主要信息<br />
 * </p>
 * @title AnswerMainInfo.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
public class AnswerMainInfo extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//问卷id
	private String questionnaireId;
	//客户id
	private String custId;
	//问题id
	private String questionId;
	//回访主表id
	private String visitId;
	//答案信息列表
	private List<AnswerDetailInfo> answerList;
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
	 * @param custId the custId to get
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * @param questionId the questionId to get
	 */
	public String getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	/**
	 * @param visitId the visitId to get
	 */
	public String getVisitId() {
		return visitId;
	}
	/**
	 * @param visitId the visitId to set
	 */
	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}
	/**
	 * @param answerList the answerList to get
	 */
	public List<AnswerDetailInfo> getAnswerList() {
		return answerList;
	}
	/**
	 * @param answerList the answerList to set
	 */
	public void setAnswerList(List<AnswerDetailInfo> answerList) {
		this.answerList = answerList;
	}
	/**
	 * @param serialVersionUID the serialversionuid to get
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
