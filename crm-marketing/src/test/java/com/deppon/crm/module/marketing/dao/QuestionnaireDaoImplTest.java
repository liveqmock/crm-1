/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireDaoImplTest.java
 * @package com.deppon.crm.module.marketing.dao
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-8
 */
package com.deppon.crm.module.marketing.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.dao.IQuestionnaireDao;
import com.deppon.crm.module.marketing.server.dao.impl.QuestionnaireDaoImpl;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.AnswerDetailInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.AnswerMainInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireQueryCondi;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;

/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireDaoImplTest.java
 * @package com.deppon.crm.module.marketing.dao
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-3-8
 */
public class QuestionnaireDaoImplTest {
	private IQuestionnaireDao questionnaireDao;
	
	@Before
	public void setUp(){
		questionnaireDao = (IQuestionnaireDao)SpringTestHelper.get().getBean(QuestionnaireDaoImpl.class);
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-13
	 * @throws java.lang.Exception
	 * void
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	//根据条件查询问卷总条数
	public void searchQuestionnaireInfoCountTest(){
		QuestionnaireQueryCondi queryCondi = null;
		int num = questionnaireDao.searchQuestionnaireInfoCount(queryCondi);
		System.out.println(num);
	}
	
	@Test
	//查询问卷信息列表
	public void searchQuestionnaireInfoTest(){
		QuestionnaireQueryCondi queryCondi = null;
		List<QuestionnaireInfo> list = questionnaireDao.searchQuestionnaireInfoList(queryCondi, 0, 20);
		for(int i = 0;i < list.size();i++){
			System.out.println(list.get(i).getId());
		}
	}
	
	@Test
	//创建问卷
	public void createQuestionnaireTest(){
		QuestionnaireInfo questionnaireInfo = new QuestionnaireInfo();
		Date today = new Date();
		Long nowLong = today.getTime();//得到当前时间的毫秒数
		SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd");
		String d=dd.format(new Date());
		questionnaireInfo.setEffectiveTime(new Date(today.getTime()+10*24*60*60*1000));
		questionnaireInfo.setInvalidTime(new Date(today.getTime()+20*24*60*60*1000));
		questionnaireInfo.setCreateDate(today);
		questionnaireInfo.setQuestionnaireName("dao层测试用例"+nowLong);
		//查询问卷编号
		String questionnaireCode = questionnaireDao.queryQuestionnaireCode();
		questionnaireInfo.setQuestionnaireCode(questionnaireCode);
		questionnaireInfo.setUseRange("VISIT");
		questionnaireInfo.setDesc("dao层测试用例"+nowLong);
		questionnaireInfo.setStatus("WAITINGVALID");
		questionnaireInfo.setCreateUserId("86301");
		questionnaireInfo.setLastModifyUserId("86301");
		String questionnaireId = questionnaireDao.createQuestionnaire(questionnaireInfo);
		System.out.println(questionnaireId);
	}
	
//	@Test
	//创建问题、问卷信息映射
//	public void createQuestionMapperTest(){
//		QuestionnaireMapper questionDetail = new QuestionnaireMapper();
//		questionDetail.setQuestionSeq(1);
//		questionDetail.setCreateUser("86301");
//		questionDetail.setModifyUser("86301");
//		questionDetail.setQuestionnaireId("12");
//		questionDetail.setQuestionId("44");
//		boolean result = questionnaireDao.createQuestionMapper(questionDetail);
//		System.out.println(result);
//	}
	
	@Test
	//更新问卷信息
	public void updateQuestionnaireTest(){
		QuestionnaireQueryCondi queryCondi = new QuestionnaireQueryCondi();
		queryCondi.setQuestionnaireName("dao层测试用例");
		List<QuestionnaireInfo> list = questionnaireDao.searchQuestionnaireInfoList(queryCondi, 0, 20);
		if(list != null && list.size() > 0){
			QuestionnaireInfo questionnaireInfo = list.get(0);
			questionnaireInfo.setDesc("dao层测试用例修改");
			questionnaireDao.updateQuestionnaire(questionnaireInfo);
		}
	}
	
	//根据问卷id查询问卷问题数量
	@Test
	public void testCountOfOption(){
		String surveyId = "243";
		String questionId = "566";
		String optionFlag = "899";
		String count = questionnaireDao.countOfOption(surveyId, questionId, optionFlag);
		System.out.println(count);
	}
	//问卷回访时向用户问卷答案信息主表中插入数据
	@Test
	public void testInsertSurveyMainAnswer(){
		AnswerMainInfo a = new AnswerMainInfo();
		a.setCreateDate(new Date());
		questionnaireDao.insertSurveyMainAnswer(a);
	}
	//问卷回访时向用户问卷答案信息表中插入数据
	@Test
	public void testInsertSurveyDetailAnswer(){
		AnswerDetailInfo detail = new AnswerDetailInfo();
		detail.setAnswer("盛诗庆测试");
		questionnaireDao.insertSurveyDetailAnswer(detail);
	}
	//更新问卷状态
	@Test
	public void testUpdateQuestionnaireStatusById(){
		QuestionnaireInfo questionnaire = new QuestionnaireInfo();
		questionnaire.setQuestionnaireId("243");
		questionnaireDao.updateQuestionnaire(questionnaire);
	}
	//根据客户id查询该客户回答过的问卷数量
	@Test
	public void testCountQuestionnaireListByCustid(){
		questionnaireDao.countQuestionnaireListByCustid("5568");
	}
	//定时更新问卷状态
	@Test
	public void testUpdateSurveyStatus(){
		questionnaireDao.updateSurveyStatus("USING");
	}
	@Test
	//删除问卷信息
	public void deleteQuestionnaireTest(){
		QuestionnaireQueryCondi queryCondi = new QuestionnaireQueryCondi();
		queryCondi.setQuestionnaireName("dao层测试用例");
		List<QuestionnaireInfo> list = questionnaireDao.searchQuestionnaireInfoList(queryCondi, 0, 20);
		if(list != null && list.size() > 0){
			QuestionnaireInfo questionnaireInfo = list.get(0);
			String ids[] = {questionnaireInfo.getId()};
			questionnaireDao.deleteQuestionnaire(ids);
		}
	}
	
//	@Test
//	//删除问卷、问题映射信息
//	public void deleteQuestionnaireMapperTest(){
//		QuestionnaireQueryCondi queryCondi = new QuestionnaireQueryCondi();
//		queryCondi.setQuestionnaireName("dao层测试用例");
//		List<QuestionnaireInfo> list = questionnaireDao.searchQuestionnaireInfoList(queryCondi, 0, 20);
//		if(list != null){
//			QuestionnaireInfo questionnaireInfo = list.get(0);
//			String ids[] = {questionnaireInfo.getId()};
//			questionnaireDao.deleteQuestionnaireMapper(ids);
//		}
//	}
	
	@Test
	//查询即将生成的问卷的问卷编号
	public void queryQuestionnaireCodeTest(){
		String code = questionnaireDao.queryQuestionnaireCode();
		System.out.println(code);
	}
	
	@Test
	//根据问卷名称精确查询出匹配的问卷信息
	public void searchQuestionnaireByNameTest(){
		String questionnaireName = "dao层测试用例";
		QuestionnaireInfo result = questionnaireDao.searchQuestionnaireByName(questionnaireName);
		if(result != null){
			System.out.println(result.getQuestionnaireName());
		}
	}
	@Test
	//根据问卷id查询问题列表
	public void searchQuestionListByQuestionnaireIdTest(){
		String id = "21";
		List<QuestionDetail> list = questionnaireDao.searchQuestionListByQuestionnaireId(id);
		for(int i = 0;i < list.size();i++){
			System.out.println(list.get(i).getId());
		}
	}
	
//	@Test
//	//问卷回访时向用户问卷答案信息主表中插入数据
//	public void insertSurveyMainAnswerTest(){
//		AnswerMainInfo answerMainInfo = new AnswerMainInfo();
//		answerMainInfo.setCreateUser("123");
//		answerMainInfo.setCustId("12306");
//		answerMainInfo.setQuestionId("42");
//		answerMainInfo.setVisitId("12306");
//		answerMainInfo.setQuestionnaireId("12306");
//		answerMainInfo.setModifyUser("86301");
//		questionnaireDao.insertSurveyMainAnswer(answerMainInfo);
//		System.out.println(answerMainInfo.getId());
//	}
//	
//	@Test
//	public void insertSurveyDetailAnswerTest(){
//		AnswerDetailInfo answerDetailInfo = new AnswerDetailInfo();
//		answerDetailInfo.setAnswer("12306");
//		answerDetailInfo.setAnswerMainInfoId("402");
//		answerDetailInfo.setAnswerRemark("我是一只小毛驴");
//		questionnaireDao.insertSurveyDetailAnswer(answerDetailInfo);
//		System.out.println(answerDetailInfo.getId());
//	}
	@Test
	public void testStaticQuestionnaire(){
		Calendar c1 = Calendar.getInstance();
		c1.set(2014, 2, 2);
		Calendar c2 = Calendar.getInstance();
		c2.set(2014, 2, 4);
		questionnaireDao.staticsQuestionsBySurveyId("51",c1.getTime(),c2.getTime());
	}
	@Test
	public void exportStatic(){
		Calendar c1 = Calendar.getInstance();
		c1.set(2014, 2, 2);
		Calendar c2 = Calendar.getInstance();
		c2.set(2014, 2, 4);
		questionnaireDao.staticsQuestionsBySurveyId("51",c1.getTime(),c2.getTime());
	}
	@Test
	public void exportCustAnswer(){
		Calendar c1 = Calendar.getInstance();
		c1.set(2014, 2, 2);
		Calendar c2 = Calendar.getInstance();
		c2.set(2014, 2, 4);
		questionnaireDao.exportCustQuestionnaireDetail("51",null,null);
		//questionnaireDao.exportCustQuestionnaireDetail("51",c1.getTime(),c2.getTime());
	}
	@Test 
	public void queryQuestionnaireListByCustid(){
		List<QuestionnaireInfo> list = questionnaireDao.queryQuestionnaireListByCustid("4588",0,2);
		System.out.println(list.size());
	}
	@Test 
	public void viewQuestionnaireByVisitId(){
		List<QuestionDetail> list = questionnaireDao.viewQuestionnaireByVisitId("1");
		System.out.println(list.size());
	}
//	@Test
//	public void updateQuestionnaireStatusTest(){
//		questionnaireDao.updateSurveyStatus("OVERDUE");
//		
//	}
	@Test
	public void searchQuestionnaireByVisitId(){
		questionnaireDao.searchQuestionnaireByVisitId("40002815");
	}
	
	@Test
	public void queryQuestionnaireById(){
		QuestionnaireInfo q = questionnaireDao.queryQuestionnaireById("40002815");
	}
}
