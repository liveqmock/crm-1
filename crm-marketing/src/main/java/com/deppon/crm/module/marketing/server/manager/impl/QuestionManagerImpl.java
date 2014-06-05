package com.deppon.crm.module.marketing.server.manager.impl;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.IQuestionManager;
import com.deppon.crm.module.marketing.server.service.IQuestionService;
import com.deppon.crm.module.marketing.server.utils.QuestionnaireUtils;
import com.deppon.crm.module.marketing.server.utils.QuestionnaireValidateUtils;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionOption;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireConstance;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;
import com.deppon.foss.framework.server.context.UserContext;

public class QuestionManagerImpl implements IQuestionManager{
	private IQuestionService questionService;
	/**
	 * @description 根据查询条件查询问题列表
	 * @author 盛诗庆
	 * @date 2014-04
	 * @param QuestionQueryCondition c:查询条件实体
	 * @param int start : 分页起始页
	 * @param int limit ： 每页显示条数
	 * @return map：包含问题总条数以及当前页问题详情列表
	 */
	@Override
	public Map<String,Object> searchQuestionsByCondition(QuestionQueryCondition c,int start, int limit){
		//验证查询条件,起始结束时间不为空，且不能超过九十天
		QuestionnaireValidateUtils.questionQueryConditionDetailValidator(c);
		String title = c.getQuestionTitle();
		//去除问卷标题前后空格
		if (title != null && !title.equalsIgnoreCase("")) {
			c.setQuestionTitle(QuestionnaireUtils.trimAllBlank(title));
		}
		HashMap<String,Object> map = new HashMap<String,Object>();
		//分页查询问题列表
		List<QuestionDetail> questionList = questionService.searchQuestionsByCondition(c, start, limit);
		//循环查询问题选项
		for(QuestionDetail q : questionList){
			List <QuestionOption> optionList = this.searchOptionByQuestion(q);
			q.setOptions(optionList);
		}
		map.put("questionList", questionList);
		//统计问题总数
		int totalCount = Integer.parseInt(questionService.countQuestionsByCondition(c));
		map.put("totalCount", totalCount);
		return map;
	}
	/**
	 * @description 根据问题查询对应选项
	 * @author 盛诗庆
	 * @date 2014-04
	 * @param QuestionDetail questionDetail:查询条件，问题详情使用了问题的id进行查询
	 * @return List：问题对应选项的列表
	 */
	private List<QuestionOption> searchOptionByQuestion(QuestionDetail questionDetail){
		return questionService.searchOptionByQuestion(questionDetail);
	}
	/**
	 * @description 创建问题
	 * @author 盛诗庆
	 * @date 2014-04
	 * @param QuestionDetail questionDetail:问题详情
	 * @return boolean：true 创建成功，false 创建失败 
	 */
	@Transactional
	@Override
	public boolean createQuestion(QuestionDetail questionDetail) {
		User user = (User)UserContext.getCurrentUser();
		//获取问题创建人id，并设置问题创建人id为当前系统登录用户
		questionDetail.setCreateUserId(user.getEmpCode().getId());
		//去除问题标题中的前后空格
		questionDetail.setQuestionTitle(QuestionnaireUtils.trimAllBlank(questionDetail.getQuestionTitle()));
		//验证问题实体，问题标题，类型，适用范围不能为空，问题标题不超过100，说明长度不超过300字符
		QuestionnaireValidateUtils.questionDetailValidator(questionDetail);
		//检查问题标题是否有重复
		//根据问题名称从数据库中查询是否存在同名问题
		List<QuestionDetail> questionList = questionService.searchQuestionDetailByTitle(questionDetail.getQuestionTitle());
		QuestionnaireValidateUtils.questionTitleIsExist(questionList,questionDetail);
		//验证问题类型，如果是选择题（单选，多选）
		String questionType = questionDetail.getQuestionType();
		if(QuestionnaireConstance.QUESTION_TYPE_RADIO.equalsIgnoreCase(questionType) || QuestionnaireConstance.QUESTION_TYPE_MULTIPLE_CHOICE.equalsIgnoreCase(questionType)){
			//获取选项列表
			List<QuestionOption> optionList = questionDetail.getOptions();
			//获取是否允许填空
			String elseOption = questionDetail.getElseOption();
			//验证选项是否合法
			QuestionnaireValidateUtils.questionOptionsValidator(optionList, elseOption);
			//保存问题
			int res = questionService.createQuestion(questionDetail);
			//获取问题id
			String id = questionDetail.getId();
			QuestionOption option = new QuestionOption();
			if(QuestionnaireConstance.BLANK_YES.equals(questionDetail.getElseOption())){//如允许填空，新增一条记录到选项列表，描述为其他
				option.setOptionSeq(optionList.size() + 1);
				option.setOptionDes(QuestionnaireConstance.BLANK_DES);
				optionList.add(option);
			}
			//循环保存问题选项
			for(QuestionOption o : optionList){
				o.setQuestionFid(id);//设置选项问题id
				o.setCreatorId(user.getEmpCode().getId());//设置选项创建人id
				res += questionService.createOption(o);
			}
			//如果影响行数总和等于选项数加问题数
			if(res == optionList.size() + 1){
				return true;
			}else{
				return false;
			}
		}else{//简答题
			questionService.createQuestion(questionDetail);
			return true;
		}
	}
	/**
	 * @description 删除问题
	 * @author 盛诗庆
	 * @date 2014-04
	 * @param List<String> ids:待删除问题id列表
	 * @return List<QuestionnaireInfo>：引用了该问题的问卷信息列表
	 */
	@Transactional
	@Override
	public List<QuestionnaireInfo> deleteQuestion(List<String> ids) {
		//不允许多选删除
		QuestionnaireValidateUtils.deleteValidatore(ids);
		List<QuestionnaireInfo> questionnaireList = null;//问卷列表
		//根据问题id删除问题
		for(String id : ids){
			//检查问题是否被引用
			questionnaireList = questionService.searchQuestionnaireByQuestionId(id);
			if(questionnaireList != null && questionnaireList.size() > 0){//问题被引用
				return questionnaireList;
			}else{
				questionService.deleteQuestionById(id);//删除问题
				questionService.deleteOptionByQuestionId(id);//删除问题选项
			}
		}
		return questionnaireList;
	}
	/**
	 * @description 修改问题
	 * @author 盛诗庆
	 * @date 2014-04
	 * @param QuestionDetail questionDetail:待修改问题详情
	 * @return List<QuestionnaireInfo>：引用了该问题的问卷信息列表
	 */
	@Transactional
	@Override
	public List<QuestionnaireInfo> updateQuestion(QuestionDetail questionDetail) {
		List<QuestionnaireInfo> questionnaireList = null;//引用该问题问卷列表
		User user = (User)UserContext.getCurrentUser();
		questionDetail.setLastModifyUserId(user.getEmpCode().getId());
		String id = questionDetail.getId();//获取问题id
		//检查问题是否被问卷引用
		questionnaireList = questionService.searchQuestionnaireByQuestionId(id);
		if(questionnaireList != null && questionnaireList.size() > 0){//问题被引用
			return questionnaireList;
		}
		//验证问题实体，问题标题，类型，适用范围不能为空，问题标题不超过100字符,说明长度不超过100
		QuestionnaireValidateUtils.questionDetailValidator(questionDetail);
		//检查问题标题是否有重复
		//根据问题名称从数据库中查询是否存在同名问题
		List<QuestionDetail> questionList = questionService.searchQuestionDetailByTitle(questionDetail.getQuestionTitle());
		QuestionnaireValidateUtils.questionTitleIsExist(questionList,questionDetail);
		questionService.updateQuestion(questionDetail);//修改问题信息
		questionService.deleteOptionByQuestionId(id);//删除原有选项
		List<QuestionOption> optionList = questionDetail.getOptions();
		QuestionOption option = new QuestionOption();
		if(QuestionnaireConstance.BLANK_YES.equals(questionDetail.getElseOption())){//如允许填空，新增一条记录到选项列表，描述为其他
			option.setOptionSeq(optionList.size() + 1);
			option.setOptionDes(QuestionnaireConstance.BLANK_DES);
			optionList.add(option);
		}
		//修改问题时根据选项列表插入新的问题选项
		if(optionList != null && optionList.size() > 0){
			for(QuestionOption o : optionList){
				o.setQuestionFid(id);
				o.setCreatorId(user.getEmpCode().getId());
				o.setModifierId(user.getEmpCode().getId());
				questionService.createOption(o);//新增选项
			}
		}
		return null;
	}
	/**
	 * @description 根据问题id查询引用了该问题的问卷信息列表
	 * @author 盛诗庆
	 * @date 2014-04
	 * @param String questionId:问题id
	 * @return List<QuestionnaireInfo>：引用了该问题的问卷信息列表
	 */
	@Override
	public List<QuestionnaireInfo> searchQuestionnaireByQuestionId(
			String questionId) {
		return questionService.searchQuestionnaireByQuestionId(questionId);
	}
	/**
	 * @param questionService the questionService to set
	 */
	public void setQuestionService(IQuestionService questionService) {
		this.questionService = questionService;
	}
}
