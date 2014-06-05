package com.deppon.crm.module.scheduler.service;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.scheduler.server.service.ISchedulingControlService;
import com.deppon.crm.module.scheduler.shared.domain.SchedulingControl;

/**
 * @description 定时控制.
 * @author 安小虎
 * @version 0.1
 * @date 2012-5-31
 */

public class SchedulingControlServiceTest {
	ApplicationContext appContext = null;
	private ISchedulingControlService scs;

	@Before
	public void setUp() throws Exception {
		appContext = new ClassPathXmlApplicationContext(
				new String[] { "schedulingControlBean.xml" });
		scs = (ISchedulingControlService) appContext
				.getBean("schedulingControlService");
		Assert.assertNotNull(scs);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSearchValue() {
		List list = this.scs.searchValues();
		this.scs.searchValueByKey("T_CRM_DELIVERTOP50CUSTMER");
	}

	@Test
	public void testSearchListByCondition() {
		Date _now = new Date();

		SchedulingControl sc = new SchedulingControl();
		// sc.setTableName(TimerSchedulingControlEnum
		// .getValue(TimerSchedulingControlEnum.T_CRM_CUSTOMERLOSEDETAIL));
		sc.setReportTime(new Date(112, 1, 1));
		sc.setCrmTime(new Date(112, 1, 1));
		sc.setCrmEndTime(_now);
		sc.setState("1");
		List<SchedulingControl> list = this.scs.searchListByCondition(sc, _now,
				_now, 1, 10);
		if (list != null && list.size() > 0) {
			System.out.println(list.size());
		}
	}

	/**
	 * {@link com.deppon.crm.module.scheduler.server.service.impl.SchedulingControlService#getOneByTableName(java.lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testSearchNeedExecuteList() {
		List<SchedulingControl> list = scs.searchNeedExecuteList();
		if (list != null && list.size() > 0) {
			System.out.println(list.size());
		}
	}

	/**
	 * {@link com.deppon.crm.module.scheduler.server.service.impl.SchedulingControlService#update(com.deppon.crm.module.scheduler.shared.domain.SchedulingControl)}
	 * 的测试方法。
	 */
	@Test
	public void testUpdateByTableName() {
		SchedulingControl sc = new SchedulingControl();
		sc.setId(1);
		sc.setCrmTime(new Date());
		sc.setCrmEndTime(new Date());
		sc.setState("1");
		scs.update(sc);
	}

}
