/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionOption.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-5
 */
package com.deppon.crm.module.marketing.shared.domain.questionnaire;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * <p>
 * Description:问题选项实体信息<br />
 * </p>
 * @title QuestionOption.java
 * @package com.deppon.crm.module.marketing.shared.domain.questionnaire
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-5
 */
public class QuestionOption extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//选项编号
	private String optionCode;
	//选项内容描述
	private String optionDes;
	//问题id
	private String questionFid;
	//创建人id
	private String creatorId;
	//修改人id
	private String modifierId;
	//选项排列顺序
	private int optionSeq;
	//选项占比
	private double percentage;
	//选项被选择次数
	private String countTimes;
	//是否被选中
	private String isSelected;
	//简答题答案
	private String answer;
	/**
	 * @param optionCode the optionCode to get
	 */
	public String getOptionCode() {
		return optionCode;
	}
	/**
	 * @param optionCode the optionCode to set
	 */
	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}
	/**
	 * @param optionDes the optionDes to get
	 */
	public String getOptionDes() {
		return optionDes;
	}
	/**
	 * @param optionDes the optionDes to set
	 */
	public void setOptionDes(String optionDes) {
		this.optionDes = optionDes;
	}
	/**
	 * @return the questionFid
	 */
	public String getQuestionFid() {
		return questionFid;
	}
	/**
	 * @param questionFid the questionFid to set
	 */
	public void setQuestionFid(String questionFid) {
		this.questionFid = questionFid;
	}
	/**
	 * @return the creatorId
	 */
	public String getCreatorId() {
		return creatorId;
	}
	/**
	 * @param creatorId the creatorId to set
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * @return the modifierId
	 */
	public String getModifierId() {
		return modifierId;
	}
	/**
	 * @param modifierId the modifierId to set
	 */
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	/**
	 * @return the optionSeq
	 */
	public int getOptionSeq() {
		return optionSeq;
	}
	/**
	 * @param optionSeq the optionSeq to set
	 */
	public void setOptionSeq(int optionSeq) {
		this.optionSeq = optionSeq;
	}
	/**
	 * @return the percentage
	 */
	public double getPercentage() {
		return percentage;
	}
	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	/**
	 * @return the countTimes
	 */
	public String getCountTimes() {
		return countTimes;
	}
	/**
	 * @param countTimes the countTimes to set
	 */
	public void setCountTimes(String countTimes) {
		this.countTimes = countTimes;
	}
	public String getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
