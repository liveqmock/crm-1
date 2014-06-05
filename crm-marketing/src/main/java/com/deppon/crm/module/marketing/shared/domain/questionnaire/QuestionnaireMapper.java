/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireMapper.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-10
 */
package com.deppon.crm.module.marketing.shared.domain.questionnaire;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * <p>
 * Description:问题问卷映射关系表<br />
 * </p>
 * @title QuestionnaireMapper.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-10
 */
public class QuestionnaireMapper extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//问卷id
	private String questionnaireId;
	//问题id
	private String questionId;
	//问题编号
	private int questionSeq;
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
}
