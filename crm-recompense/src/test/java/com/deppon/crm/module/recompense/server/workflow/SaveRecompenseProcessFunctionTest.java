/**   
 * @title SaveRecompenseProcessFunctionTest.java
 * @package com.deppon.crm.module.recompense.server.workflow
 * @description what to do
 * @author 安小虎
 * @update 2012-3-29 上午10:43:18
 * @version V1.0   
 */
package com.deppon.crm.module.recompense.server.workflow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.recompense.server.util.TestDataCreator;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.MessageReminder;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.deppon.crm.module.recompense.shared.domain.Waybill;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.module.propertyset.database.JDBCPropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;

/**
 * @description 理赔初步处理测试用例
 * @author 安小虎
 * @version 0.1 2012-3-29
 * @date 2012-3-29
 */

public class SaveRecompenseProcessFunctionTest {

//	ClassPathXmlApplicationContext factory = null;
	static FunctionProvider saveRecompenseProcessFunction = null;

	@Before
	public void setUp() throws Exception {
		// String resource =
		// "com/deppon/crm/module/recompense/server/META-INF/spring.xml";
//		AppContext.initAppContext("application", "");
//		String[] resource = { "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml" };
//		factory = new ClassPathXmlApplicationContext(resource);
//		saveRecompenseProcessFunction = (FunctionProvider) factory
//				.getBean("saveRecompenseProcessFunction");
	}

	static {
		saveRecompenseProcessFunction = TestUtil.saveRecompenseProcessFunction;
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testExecute() throws WorkflowException {
		RecompenseApplication app = TestDataCreator.createBase();
		Long l = 1220L;
		Waybill waybill = app.getWaybill();
		waybill.setId(l.toString());
		app.setWorkflowId(l);
		app.setWaybill(waybill);

		Map map = new HashMap();
		map.put(Constants.RECOMPENSE_APPLICATION, app);

		Map<String, List<DeptCharge>> deptChargeMap = new HashMap<String, List<DeptCharge>>();
		Map<String, List<ResponsibleDept>> responsibleDeptMap = new HashMap<String, List<ResponsibleDept>>();
		Map<String, List<MessageReminder>> messageReminderMap = new HashMap<String, List<MessageReminder>>();
		Map<String, List<AwardItem>> awardItemMap = new HashMap<String, List<AwardItem>>();

		map.put(Constants.RECOMPENSE_DEPTCHARGE_MAP, deptChargeMap);
		map.put(Constants.RECOMPENSE_RESPONSIBLEDEPT_MAP, responsibleDeptMap);
		map.put(Constants.RECOMPENSE_MESSAGEREMINDER_MAP, messageReminderMap);
		map.put(Constants.RECOMPENSE_AWARDITEM_MAP, awardItemMap);
		PropertySet ps = new JDBCPropertySet();
		saveRecompenseProcessFunction.execute(map, null, ps);
	}

}
