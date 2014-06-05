/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionManagerImplTest.java
 * @package com.deppon.crm.module.marketing.manager
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-4-8
 */
package com.deppon.crm.module.marketing.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.IQuestionManager;
import com.deppon.crm.module.marketing.server.manager.impl.QuestionManagerImpl;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionOption;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireConstance;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * <p>
 * Description:<br />
 * </p>
 * @title QuestionManagerImplTest.java
 * @package com.deppon.crm.module.marketing.manager
 * @author xiaohongye
 * @version V0.1 
 * @Date 2014-4-8
 */
public class QuestionManagerImplTest {
	private IQuestionManager questionManager;
	
	@Before
	public void setUp() throws ParseException {
		questionManager = (IQuestionManager) SpringTestHelper.get().getBean(QuestionManagerImpl.class);
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
	public void searchQuestionsByConditionTest() throws ParseException{
		QuestionQueryCondition qqc = new QuestionQueryCondition();
		qqc.setCreateStartDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-03-01"));
		qqc.setCreateEndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-04-30"));
		questionManager.searchQuestionsByCondition(qqc, 0, 20);
	}
	@Test
	public void createQuestionTest(){
		QuestionDetail qd = new QuestionDetail();
		Date now = new Date();
		Long nowLong = now.getTime();//得到当前时间的毫秒数
		for(int i = 1; i <= 3; i++){
			String questionType = null;
			boolean haveOptions = false;
			if(i % 3 == 0){
				//单选
				questionType = QuestionnaireConstance.QUESTION_TYPE_RADIO;
				haveOptions = true;
			}else if(i % 3 == 1){
				//多选
				questionType = QuestionnaireConstance.QUESTION_TYPE_MULTIPLE_CHOICE;
				haveOptions = true;
			}else{
				//简答
				questionType = QuestionnaireConstance.QUESTION_TYPE_QUESTION_ANSWER;
				haveOptions = false;
			}
			
			qd.setQuestionTitle("Manager问题测试用例" + questionType + nowLong);
			qd.setQuestionContent("Manager问题测试用例" + questionType + nowLong);
			qd.setUseRange("VISIT");
			qd.setQuestionType(questionType);
			if(haveOptions){
				qd.setElseOption("1");
				List<QuestionOption> options = new ArrayList<QuestionOption>();
				for(int j = 1;j <= 4; j++){
					QuestionOption qo = new QuestionOption();
					qo.setOptionDes("选项内容" + j);
					qo.setOptionSeq(j);
					options.add(qo);
				}
				qd.setOptions(options);
			}else{
				qd.setElseOption("0");
				qd.setOptions(null);
			}
			questionManager.createQuestion(qd);
		}
	}
	@Test
	public void updateQuestionTest() throws ParseException{
		QuestionQueryCondition qqc = new QuestionQueryCondition();
		qqc.setCreateStartDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-03-01"));
		qqc.setCreateEndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-04-30"));
		Map<String,Object> map = questionManager.searchQuestionsByCondition(qqc, 0, 20);
		@SuppressWarnings("unchecked")
		List<QuestionDetail> questionList = (List<QuestionDetail>) map.get("questionList");
		if(questionList != null){
			QuestionDetail qd = questionList.get(0);
			qd.setElseOption("1");
			questionManager.updateQuestion(qd);
		}
	}
	@Test
	public void deleteQuestion() throws ParseException{
		QuestionQueryCondition qqc = new QuestionQueryCondition();
		qqc.setCreateStartDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-03-01"));
		qqc.setCreateEndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-04-30"));
		Map<String,Object> map = questionManager.searchQuestionsByCondition(qqc, 0, 20);
		@SuppressWarnings("unchecked")
		List<QuestionDetail> questionList = (List<QuestionDetail>) map.get("questionList");
		if(questionList != null){
			QuestionDetail qd = questionList.get(0);
			List<String> ids = new ArrayList<String>();
			ids.add(qd.getId());
			questionManager.searchQuestionnaireByQuestionId(qd.getId());
			questionManager.deleteQuestion(ids);
		}
	}
}
