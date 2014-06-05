/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionQueryCondition.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-5
 */
package com.deppon.crm.module.marketing.shared.domain.questionnaire;

import java.util.Date;

/**
 * <p>
 * Description:问题管理的查询条件实体<br />
 * </p>
 * @title QuestionQueryCondition.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-5
 */
public class QuestionQueryCondition {
	//问题标题
	private String questionTitle;
	//适用范围
	private String useRange;
	//问题类型
	private String questionType;
	//开始创建日期
	private Date createStartDate;
	//结束创建日期
	private Date createEndDate;
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
}
