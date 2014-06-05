package com.deppon.crm.module.marketing.server.action;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.marketing.server.manager.IQuestionManager;
import com.deppon.crm.module.marketing.server.manager.IWarnLostCustManager;
import com.deppon.crm.module.marketing.server.manager.impl.WarnLostCustManagerImp;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * @description 问题管理相关
 * @author 盛诗庆
 * @date 2014-03-07
 * @version 0.1
 */
public class QuestionAction extends AbstractAction {
		/**
	 * 
	 */
	private static final long serialVersionUID = -993329217542606422L;
		//问题管理的manager层实现
		private IQuestionManager questionManager;
		//问卷管理的查询条件
		private QuestionQueryCondition questionSearchCondition;
		//问卷管理查询结果列表
		private List<QuestionDetail> questionList;
		//分页要用参数
		private int start;
		//每页记录数
		private int limit;
		//总行数
		private Long totalCount;
		//问题对象
		private QuestionDetail questionDetail;
		//问题id集合
		private List<String> questionIds;
		//问卷集合
		List<QuestionnaireInfo> questionnaireList;
		//流失预警
		private IWarnLostCustManager warnLostCustManagerImp;
		/**
		 * 发送邮件
		 * @throws Exception 
		 */
		public String sendMail() throws Exception
		{
			((WarnLostCustManagerImp)warnLostCustManagerImp).sendMial();
			return SUCCESS;
		}
		/**
		 * @Description:查询问题结果列表<br />
		 * @author 盛诗庆
		 * @version 0.1 
		 * @Date 2014-3-7
		 */
		@SuppressWarnings("unchecked")
		@JSON
		public String searchQuestionsByCondition() {
			Map<String,Object> map = questionManager.searchQuestionsByCondition(questionSearchCondition, start, limit);
			questionList = (List<QuestionDetail>) map.get("questionList");
			totalCount = Long.valueOf(map.get("totalCount").toString());
			return SUCCESS;
		}
		/**
		 * @Description:新建问题<br />
		 * @author 盛诗庆
		 * @version 0.1 
		 * @Date 2014-3-13
		 */
		@JSON
		public String createQuestion() {
			questionManager.createQuestion(questionDetail);
			return SUCCESS;
		}
		/**
		 * @Description:修改问题<br />
		 * @author 盛诗庆
		 * @version 0.1 
		 * @Date 2014-3-13
		 */
		@JSON
		public String updateQuestion() {
			questionnaireList = questionManager.updateQuestion(questionDetail);
			return SUCCESS;
		}
		/**
		 * @Description:删除问题<br />
		 * @author 盛诗庆
		 * @version 0.1 
		 * @Date 2014-3-13
		 */
		@JSON
		public String deleteQuestion() {
			questionnaireList = questionManager.deleteQuestion(questionIds);
			return SUCCESS;
		}
		/**
		 * @Description:查询引用了该问题的问卷列表<br />
		 * @author 盛诗庆
		 * @version 0.1 
		 * @Date 2014-3-18
		 */
		@JSON
		public String searchQuestionnaireByQuestionId() {
			questionnaireList = questionManager.searchQuestionnaireByQuestionId(questionIds.get(0));
			return SUCCESS;
		}
		/**
		 * @param questionManager the questionManager to set
		 */
		public void setQuestionManager(IQuestionManager questionManager) {
			this.questionManager = questionManager;
		}

		/**
		 * @return the questionSearchCondition
		 */
		public QuestionQueryCondition getQuestionSearchCondition() {
			return questionSearchCondition;
		}

		/**
		 * @param questionSearchCondition the questionSearchCondition to set
		 */
		public void setQuestionSearchCondition(
				QuestionQueryCondition questionSearchCondition) {
			this.questionSearchCondition = questionSearchCondition;
		}

		/**
		 * @return the questionList
		 */
		public List<QuestionDetail> getQuestionList() {
			return questionList;
		}

		/**
		 * @param questionList the questionList to set
		 */
		public void setQuestionList(List<QuestionDetail> questionList) {
			this.questionList = questionList;
		}

		/**
		 * @return the start
		 */
		public int getStart() {
			return start;
		}

		/**
		 * @param start the start to set
		 */
		public void setStart(int start) {
			this.start = start;
		}

		/**
		 * @return the limit
		 */
		public int getLimit() {
			return limit;
		}

		/**
		 * @param limit the limit to set
		 */
		public void setLimit(int limit) {
			this.limit = limit;
		}

		/**
		 * @return the totalCount
		 */
		public Long getTotalCount() {
			return totalCount;
		}

		/**
		 * @param totalCount the totalCount to set
		 */
		public void setTotalCount(Long totalCount) {
			this.totalCount = totalCount;
		}
		/**
		 * @return the questionIds
		 */
		public List<String> getQuestionIds() {
			return questionIds;
		}
		/**
		 * @param questionIds the questionIds to set
		 */
		public void setQuestionIds(List<String> questionIds) {
			this.questionIds = questionIds;
		}
		/**
		 * @return the questionDetail
		 */
		public QuestionDetail getQuestionDetail() {
			return questionDetail;
		}
		/**
		 * @param questionDetail the questionDetail to set
		 */
		public void setQuestionDetail(QuestionDetail questionDetail) {
			this.questionDetail = questionDetail;
		}
		/**
		 * @return the questionnaireList
		 */
		public List<QuestionnaireInfo> getQuestionnaireList() {
			return questionnaireList;
		}
		/**
		 * @param questionnaireList the questionnaireList to set
		 */
		public void setQuestionnaireList(List<QuestionnaireInfo> questionnaireList) {
			this.questionnaireList = questionnaireList;
		}
		public void setWarnLostCustManagerImp(
				IWarnLostCustManager warnLostCustManagerImp) {
			this.warnLostCustManagerImp = warnLostCustManagerImp;
		}
	
}
