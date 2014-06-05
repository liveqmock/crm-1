/**
 * <p>
 * Description:<br />
 * </p>
 * @title AnswerDetailInfo.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
package com.deppon.crm.module.marketing.shared.domain.questionnaire;

/**
 * <p>
 * Description:用户答案明细信息<br />
 * </p>
 * @title AnswerDetailInfo.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-6
 */
public class AnswerDetailInfo {
	//id
	private String id;
	//问卷答案信息主表ID
	private String answerMainInfoId;
	//答案
	private String answer;
	//答案备注
	private String answerRemark;
	/**
	 * @param id the id to get
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param answerMainInfoId the answerMainInfoId to get
	 */
	public String getAnswerMainInfoId() {
		return answerMainInfoId;
	}
	/**
	 * @param answerMainInfoId the answerMainInfoId to set
	 */
	public void setAnswerMainInfoId(String answerMainInfoId) {
		this.answerMainInfoId = answerMainInfoId;
	}
	/**
	 * @param answer the answer to get
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * @param answerRemark the answerRemark to get
	 */
	public String getAnswerRemark() {
		return answerRemark;
	}
	/**
	 * @param answerRemark the answerRemark to set
	 */
	public void setAnswerRemark(String answerRemark) {
		this.answerRemark = answerRemark;
	}
}
