/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PlanDaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author Administrator
 * @version 0.1 2012-3-27
 */
package com.deppon.crm.module.marketing.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.manager.DataUtilTest;
import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.dao.IPlanDao;
import com.deppon.crm.module.marketing.server.dao.IQuestionDao;
import com.deppon.crm.module.marketing.server.dao.impl.PlanDao;
import com.deppon.crm.module.marketing.server.dao.impl.QuestionDaoImpl;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.NeedMaintainCustomer;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.PlanDevelopCondition;
import com.deppon.crm.module.marketing.shared.domain.PlanList;
import com.deppon.crm.module.marketing.shared.domain.ReduceCustomer;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionOption;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionQueryCondition;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PlanDaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author 苏玉军
 * @version 0.1 2012-3-27
 */

public class QuestionDaoTest {
	private static IQuestionDao questionDao;
	
	private static Plan staticPlan;
	
	static {
		questionDao = (IQuestionDao) SpringTestHelper.get().getBean(QuestionDaoImpl.class);
	}
	
	@Before
	public void setUp() throws Exception {
//		planDao = (IPlanDao) SpringTestHelper.get().getBean(PlanDao.class);
//		scheduleDao=(IScheduleDao)SpringTestHelper.get().getBean(ScheduleDao.class);
	}
	
	@Test
	public void testCountQuestionsByCondition(){
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal1.set(Calendar.YEAR, 2011);
		cal2.set(Calendar.YEAR, 2012);
		QuestionQueryCondition c = new QuestionQueryCondition();
		c.setCreateEndDate(new Date(cal1.getTimeInMillis()));
		c.setCreateStartDate(new Date(cal2.getTimeInMillis()));
		c.setQuestionTitle("盛诗庆");
		c.setQuestionType("RADIO");
		c.setUseRange("VISIT");
		String count = questionDao.countQuestionsByCondition(c);
		System.out.println(count);
	}
	@Test
	public void testDeleteOptions(){
		String id = "1";
		questionDao.deleteOptions(id);
		
	}
	@Test
	public void testDeleteQuestion(){
		String id = "1";
		questionDao.deleteQuestion(id);
		
	}
	@Test
	public void testSaveOption(){
		QuestionOption option = new QuestionOption();
		option.setQuestionFid("11");
		questionDao.saveOption(option);
		
	}
	@Test
	public void testSaveQuestion(){
		QuestionDetail q = new QuestionDetail();
		q.setQuestionTitle("创建问题测试用例");
		questionDao.saveQuestion(q);
		
	}
	@Test
	public void testSearchOptionByQuestion(){
		QuestionDetail q = new QuestionDetail();
		q.setQuestionTitle("盛诗庆测试");
		q.setId("51");
		questionDao.searchOptionByQuestion(q);
		
	}
	@Test
	public void testSearchOptionByQuestionId(){
		List<String> ids = new ArrayList<String>();
		ids.add("567");
		ids.add("568");
		ids.add("569");
		ids.add("570");
		String questionnaireId = "243";
		questionDao.searchOptionByQuestionId(ids, questionnaireId);
	}
	@After
	public void tearDown() throws Exception {
		// 清理DAO层测试数据
		//DataTestUtil.cleanDaoData();
	}

	

}
