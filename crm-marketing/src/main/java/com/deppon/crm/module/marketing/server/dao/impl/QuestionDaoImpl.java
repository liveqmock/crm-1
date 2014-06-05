package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.marketing.server.dao.IQuestionDao;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionOption;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class QuestionDaoImpl extends iBatis3DaoImpl implements IQuestionDao{
	//命名空间
	private static final String NAMESPACE_QUESTION = "com.deppon.crm.module.marketing.shared.domain.Question.";
	//按照条件查询问题列表
	private static final String SEARCHQUESTIONSBYCONDITION = "searchQuestionsByCondition";
	//按照条件统计问题数量
	private static final String COUNTQUESTIONSBYCONDITION = "countQuestionsByCondition";
	//根据问题查询问题选项列表
	private static final String SEARCHOPTIONBYQUESTION = "searchOptionByQuestion";
	//新增问题
	private static final String SAVEQUESTION = "createQuestion";
	//新增选项
	private static final String SAVEOPTION = "createQuestionOption";
	//删除问题
	private static final String DELETEQUESTION = "deleteQuestion";
	//删除问题对应选项
	private static final String DELETEOPTION = "deleteOption";
	//修改问题
	private static final String UPDATEQUESTION = "updateQuestion";
	//根据问题id查询问题选项
	private static final String SEARCHOPTIONBYQUESTIONID = "searchOptionByQuestionIds";
	//根据问题id查询引用了此问题的问卷列表
	private static final String SEARCHQUESTIONNAIREBYQUESTIONID = "searchQuestionnaireByQuestionId";
	//根据问题名称，精确查询问题详情
	private static final String SEARCHQUESTIONBYQUESTIONNAME = "searchQuestionDetailByQuestionName";
	/**
	 * 根据问题id查询引用了该问题的问卷列表
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionnaireInfo> searchQuestionnaireByQuestionId(String id){
		return this.getSqlSession().selectList(NAMESPACE_QUESTION + SEARCHQUESTIONNAIREBYQUESTIONID,id);
	}
	/**
	 * 根据给定条件分页查询问题列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionDetail> searchQuestionsByCondition(QuestionQueryCondition c,int start, int limit){
		RowBounds rowBounds = new RowBounds(start, limit);
		return  (List<QuestionDetail> )this.getSqlSession().selectList(NAMESPACE_QUESTION + SEARCHQUESTIONSBYCONDITION
				, c, rowBounds);	
	}
	/**
	 * 查询符合条件的问题总数
	 */
	@Override
	public String countQuestionsByCondition(QuestionQueryCondition c) {
		return (String) this.getSqlSession().selectOne(NAMESPACE_QUESTION + COUNTQUESTIONSBYCONDITION, c);
	}
	/**
	 * 根据问题id查询问题的选项
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionOption> searchOptionByQuestion(QuestionDetail q){
		return this.getSqlSession().selectList(NAMESPACE_QUESTION + SEARCHOPTIONBYQUESTION, q);
	}
	/**
	 * 保存问题
	 */
	@Override
	public int saveQuestion(QuestionDetail q) {
		return this.getSqlSession().insert(NAMESPACE_QUESTION + SAVEQUESTION, q);
	}
	/**
	 * 保存选项
	 */
	@Override
	public int saveOption(QuestionOption option) {
		return this.getSqlSession().insert(NAMESPACE_QUESTION + SAVEOPTION, option);	
	}
	/**
	 * 根据问题id删除问题
	 * @param id
	 * @return
	 */
	@Override
	public int deleteQuestion(String id) {
		return this.getSqlSession().delete(NAMESPACE_QUESTION + DELETEQUESTION, id);
	}
	/**
	 * 根据问题id删除对应问题选项
	 * @param id
	 * @return
	 */
	@Override
	public int deleteOptions(String id) {
		return this.getSqlSession().delete(NAMESPACE_QUESTION + DELETEOPTION, id);
	}
	@Override
	public int updateQuestion(QuestionDetail q) {
		return this.getSqlSession().update(NAMESPACE_QUESTION + UPDATEQUESTION, q);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionDetail> searchOptionByQuestionId(List<String> ids,String questionnaireId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("questionnaireId", questionnaireId);
		map.put("list", ids);
		return this.getSqlSession().selectList(NAMESPACE_QUESTION + SEARCHOPTIONBYQUESTIONID, map);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionDetail> searchQuestionDetailByQuestionTitle(String questionTitle){
		return this.getSqlSession().selectList(NAMESPACE_QUESTION + SEARCHQUESTIONBYQUESTIONNAME, questionTitle);
	}
}
