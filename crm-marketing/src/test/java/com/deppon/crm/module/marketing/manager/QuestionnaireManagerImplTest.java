/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireManagerImplTest.java
 * @package com.deppon.crm.module.marketing.manager
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-4-3
 */
package com.deppon.crm.module.marketing.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.IQuestionManager;
import com.deppon.crm.module.marketing.server.manager.IQuestionnaireManager;
import com.deppon.crm.module.marketing.server.manager.impl.QuestionManagerImpl;
import com.deppon.crm.module.marketing.server.manager.impl.QuestionnaireManagerImpl;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireConstance;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireMapper;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireQueryCondi;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionnaireManagerImplTest.java
 * @package com.deppon.crm.module.marketing.manager
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-4-3
 */
public class QuestionnaireManagerImplTest {
	private IQuestionnaireManager questionnaireManager;
	private IQuestionManager questionManager;
	private QuestionnaireQueryCondi queryCondi;
	private List<QuestionDetail> questionList;
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws ParseException {
		questionnaireManager = (IQuestionnaireManager) SpringTestHelper.get().getBean(QuestionnaireManagerImpl.class);		
		questionManager = (IQuestionManager) SpringTestHelper.get().getBean(QuestionManagerImpl.class);
		queryCondi = new QuestionnaireQueryCondi();
		queryCondi.setCreateStartDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-04-01"));
		queryCondi.setCreateEndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-06-01"));
		QuestionQueryCondition qqc = new QuestionQueryCondition();
		qqc.setCreateStartDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-04-01"));
		qqc.setCreateEndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-06-06"));
		Map<String,Object> result = questionManager.searchQuestionsByCondition(qqc, 0, 20);
		questionList = (List<QuestionDetail>) result.get("questionList");
		User user = new User();
		user.setId("1");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("238217", null);
		map.put("1", null);
		
		map.put("250218", null);
		map.put("22241", null);
		map.put("49708", null);
		Set<String> ids = map.keySet();
		user.setDeptids(ids);
		Employee emp = new Employee();
		emp.setId("86301");
		Department dept=new Department();
		dept.setId("1");
		emp.setDeptId(dept);
		user.setEmpCode(emp);
		UserContext.setCurrentUser(user);
	}
	
	@Test
	public void searchQuestionnaireInfoListTest(){
		Map<String,Object> map = questionnaireManager.searchQuestionnaireInfoList(queryCondi, 0, 20);
		System.out.println(map.get("totalCount"));
	}
	@Test
	public void createQuestionnaireTest() throws ParseException{
		Date now = new Date();
		Long nowLong = now.getTime();//得到当前时间的毫秒数
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date effectiveTime = sdf.parse(sdf.format(now));
		c.setTime(effectiveTime);
		c.add(Calendar.MONTH, 1);
		Date invalidTime = c.getTime();
		QuestionnaireInfo questionnaireInfo = new QuestionnaireInfo();
		questionnaireInfo.setQuestionnaireName("Manager测试用例" + nowLong);
		questionnaireInfo.setDesc("Manager测试用例" + nowLong);
		questionnaireInfo.setEffectiveTime(effectiveTime);
		questionnaireInfo.setInvalidTime(invalidTime);
		List<QuestionnaireMapper> questionnaireMapperList = new ArrayList<QuestionnaireMapper>();
		int count = 0;
		for(QuestionDetail questionDetail:questionList){
			count = count + 1;
			QuestionnaireMapper mapper = new QuestionnaireMapper();
			mapper.setQuestionId(questionDetail.getId());
			mapper.setQuestionSeq(count);
			questionnaireMapperList.add(mapper);
			if(count >= 10){
				break;
			}
		}
		questionnaireManager.createQuestionnaire(questionnaireInfo, questionnaireMapperList);
	}
	@Test
	public void updateQuestionnaireTest(){
		queryCondi.setQuestionnaireName("Manager测试用例");
		QuestionnaireInfo questionnaireInfo = null;
		Map<String,Object> map = questionnaireManager.searchQuestionnaireInfoList(queryCondi, 0, 20);
		@SuppressWarnings("unchecked")
		List<QuestionnaireInfo> questionnaireList = (List<QuestionnaireInfo>) map.get("questionnaireList");
		if(questionnaireList != null && questionnaireList.size() > 0){
			questionnaireInfo = questionnaireList.get(0);
		}
		List<QuestionnaireMapper> questionnaireMapperList = new ArrayList<QuestionnaireMapper>();
		int count = 0;
		for(QuestionDetail questionDetail:questionList){
			count = count + 1;
			QuestionnaireMapper mapper = new QuestionnaireMapper();
			mapper.setQuestionId(questionDetail.getId());
			mapper.setQuestionSeq(count);
			questionnaireMapperList.add(mapper);
			if(count >= 10){
				break;
			}
		}
		questionnaireManager.updateQuestionnaire(questionnaireInfo, questionnaireMapperList, "YES");
	}
	@Test
	public void deleteQuestionnaireTest(){
		queryCondi.setQuestionnaireName("Manager测试用例");
		QuestionnaireInfo questionnaireInfo = null;
		Map<String,Object> map = questionnaireManager.searchQuestionnaireInfoList(queryCondi, 0, 20);
		@SuppressWarnings("unchecked")
		List<QuestionnaireInfo> questionnaireList = (List<QuestionnaireInfo>) map.get("questionnaireList");
		if(questionnaireList != null && questionnaireList.size() > 0){
			questionnaireInfo = questionnaireList.get(0);
			String questionnaireId[] = {questionnaireInfo.getId()};
			questionnaireManager.deleteQuestionnaire(questionnaireId, "");
		}
	}
	@Test
	public void searchQuestionnaireByVisitId(){
		questionnaireManager.searchQuestionnaireByVisitId("40002815");
	}
	//根据问卷id查询问卷包含的问题列表
	@Test
	public void searchQuestionListByQuestionnaireId(){
		questionnaireManager.searchQuestionListByQuestionnaireId("243");
	}
	//根据问题id列表或者问卷id查询问题详情列表
	@Test
	public void searchOptionByQuestionnaireId(){
		List<String> ids = new ArrayList<String>();
		ids.add("567");
		ids.add("568");
		ids.add("569");
		ids.add("570");
		questionnaireManager.searchOptionByQuestionIds(ids, "243");
	}
	//根据问卷id和回访时间统计问卷结果
	@Test
	public void staticsQuestionsBySurveyId(){
		String id = "243";
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, 4);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date startTime = cal.getTime();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, 4);
		cal.set(Calendar.DAY_OF_MONTH, 30);
		Date endTime = cal.getTime();
		questionnaireManager.staticsQuestionsBySurveyId(id, startTime, endTime);
	}
	//根据问卷id和回访时间导出问卷统计结果
	@Test
	public void exportStaticsData(){
		String id = "243";
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, 4);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date startTime = cal.getTime();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, 4);
		cal.set(Calendar.DAY_OF_MONTH, 30);
		Date endTime = cal.getTime();
		questionnaireManager.getStaticsData(id, startTime, endTime);
	}
//	//导出问卷统计结果到excel
//	@Test
//	public void exportStaticsExcel(){
//		String id = "243";
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, 2014);
//		cal.set(Calendar.MONTH, 4);
//		cal.set(Calendar.DAY_OF_MONTH, 1);
//		Date startTime = cal.getTime();
//		cal.set(Calendar.YEAR, 2014);
//		cal.set(Calendar.MONTH, 4);
//		cal.set(Calendar.DAY_OF_MONTH, 30);
//		Date endTime = cal.getTime();
//		String name = "盛诗庆测试";
//		questionnaireManager.exportStaticsExcel(id, name, startTime, endTime);
//		//questionnaireManager.exportStaticsExcel(id, null, startTime, endTime);
//	}
	//查询客户问卷回访详情
	@Test
	public void exportStaticsCustData(){
		String id = "243";
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, 4);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date startTime = cal.getTime();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, 4);
		cal.set(Calendar.DAY_OF_MONTH, 30);
		Date endTime = cal.getTime();
		questionnaireManager.getQuestionnaireVisitDetail(id, startTime, endTime);
	}
	//更新问卷
	@Test
	public void updateSurveyStatusByID(){
		String questionnaireId = "243";
		String operateType = QuestionnaireConstance.OPERATE_TYPE_UPDATE;
		questionnaireManager.updateSurveyStatusByID(questionnaireId, operateType);
	}
	//更新问卷
	@Test
	public void updateSurveyStatusByIDAdd(){
		String questionnaireId = "243";
		String operateType = QuestionnaireConstance.OPERATE_TYPE_DELETE;
		questionnaireManager.updateSurveyStatusByID(questionnaireId, operateType);
	}
	//根据客户id查询问卷列表
	@Test
	public void queryQuestionnaireListByCustid(){
		questionnaireManager.queryQuestionnaireListByCustid("4568", 0, 1);
	}
	//根据回访id查询问题列表
	@Test
	public void viewQuestionnaireByVisitId(){
		questionnaireManager.viewQuestionnaireByVisitId("12306");
	}
	//定时器更新问卷状态
	@Test
	public void updateSurveyStatus(){
		String res = questionnaireManager.updateSurveyStatus();
		System.out.println(res);
	}
}
