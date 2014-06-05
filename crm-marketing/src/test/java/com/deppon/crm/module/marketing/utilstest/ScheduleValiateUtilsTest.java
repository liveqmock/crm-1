/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ScheduleValiateUtilsTest.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author Administrator
 * @version 0.1 2012-4-16
 */
package com.deppon.crm.module.marketing.utilstest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.action.DevelopScheduleVO;
import com.deppon.crm.module.marketing.server.utils.ScheduleValiateUtils;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.shared.exception.ScheduleException;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.BusinessException;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title ScheduleValiateUtilsTest.java
 * @package com.deppon.crm.module.marketing.manager
 * @author 苏玉军
 * @version 0.1 2012-4-16
 */

public class ScheduleValiateUtilsTest {
	private ScheduleValiateUtils validate;
	private Plan plan;
	private Schedule sche;
	private CustomerVo cvo;
	private User user = new User();

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-16
	 * @throws java.lang.Exception
	 *             void
	 */
	@Before
	public void setUp() throws Exception {
		user.setId("1");
		Department d = new Department();
		d.setId("1");
		Employee e = new Employee();
		e.setDeptId(d);
		user.setEmpCode(e);
		validate = new ScheduleValiateUtils();
		plan = new Plan();
		sche = new Schedule();
		cvo = new CustomerVo();
		Assert.assertNotNull(validate);
		Assert.assertNotNull(plan);
		Assert.assertNotNull(sche);
		Assert.assertNotNull(cvo);
		System.out.println("--------ScheduleValiateUtils初始化完成---------");
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-16
	 * @throws java.lang.Exception
	 *             void
	 */
	@After
	public void tearDown() throws Exception {
		validate = null;
		plan = null;
		sche = null;
		cvo = null;
		Assert.assertNull(validate);
		Assert.assertNull(plan);
		Assert.assertNull(sche);
		Assert.assertNull(cvo);
		System.out.println("--------ScheduleValiateUtils清理环境完成---------");
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.marketing.server.utils.ScheduleValiateUtils#isScheduleCannDelete(com.deppon.crm.module.marketing.shared.domain.Plan, com.deppon.crm.module.marketing.shared.domain.Schedule)}
	 * .
	 */
	@Test
	public void testIsScheduleCannDelete() {
		boolean b = false;
		try {
			plan.setStatus(MarketingConstance.FINISHED);
			b = validate.isScheduleCannDelete(plan, sche,user);
			Assert.assertFalse(b);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				plan.setStatus(MarketingConstance.OVERDUE);
				b = validate.isScheduleCannDelete(plan, sche,user);
				Assert.assertFalse(b);
			} catch (Exception e1) {
				e1.printStackTrace();
				try {
					plan.setStatus(MarketingConstance.EXECUTING);
					sche.setStatus(MarketingConstance.SCHEDULE_FINISH);
					sche.setExeManId("1");
					b = validate.isScheduleCannDelete(plan, sche,user);
					Assert.assertFalse(b);
				} catch (Exception e2) {
					try {
						e2.printStackTrace();
						plan.setStatus(MarketingConstance.NOTEXECUTE);
						sche.setStatus(MarketingConstance.SCHEDULE_ASSIGNED);
						sche.setExeManId("1");
						b = validate.isScheduleCannDelete(plan, sche,user);
						Assert.assertTrue(b);
					} catch (Exception e3) {
						e3.printStackTrace();
						
					}
				}
			}
		}
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.marketing.server.utils.ScheduleValiateUtils#canCreateSchedule(com.deppon.crm.module.marketing.shared.domain.Plan, com.deppon.crm.module.marketing.shared.domain.Schedule)}
	 * .
	 */
	@Test
	public void testCanCreateSchedule() {
		//设置计划开始时间
		Date start = new Date();
		Date end = new Date();
		Date scheDate = new Date();
		boolean b = false;
		start.setMonth(1);
		end.setMonth(3);
		scheDate.setMonth(4);
		plan.setBeginTime(start);
		plan.setEndTime(end);
		sche.setTime(scheDate);
		b = validate.canCreateSchedule(plan, sche);
		//日程时间验证不在计划范围内
		Assert.assertFalse(b);
		
		scheDate.setMonth(2);
		b = validate.canCreateSchedule(plan, sche);
		//日程时间验证在计划范围内
		Assert.assertTrue(b);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.marketing.server.utils.ScheduleValiateUtils#checkScheduleStatus(com.deppon.crm.module.marketing.shared.domain.Schedule)}
	 * .
	 */
	@Test
	public void testCheckScheduleStatus() {
		Date date = new Date();
		//sche.setTime(date);
		//日程时间为空，已指派
		sche = validate.checkScheduleStatus(sche);
		Assert.assertEquals(MarketingConstance.SCHEDULE_ASSIGNED, sche.getStatus());
		
		date = new Date(System.currentTimeMillis()-1000*60*60*48);
		//日程时间小于当前时间,已过期
		sche.setTime(date);
		//sche.setStatus(MarketingConstance.SCHEDULE_OVERDUE);
		sche = validate.checkScheduleStatus(sche);
		Assert.assertEquals(MarketingConstance.SCHEDULE_OVERDUE,sche.getStatus());
		
		//日程时间大于当前时间，已制定
		date = new Date(System.currentTimeMillis()+1000*60*60*48);
		sche.setTime(date);
		sche = validate.checkScheduleStatus(sche);
			
		//日常时间等于当前时间，进行中
		date = new Date();
		sche.setTime(date);
		sche = validate.checkScheduleStatus(sche);
		Assert.assertEquals(MarketingConstance.SCHEDULE_EXECUTING, sche.getStatus());
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.marketing.server.utils.ScheduleValiateUtils#canUpdateSchedule(com.deppon.crm.module.marketing.shared.domain.Plan, com.deppon.crm.module.marketing.shared.domain.Schedule)}
	 * .
	 *//*
	@Test
	public void testCanUpdateSchedule() {
		//validate.canUpdateSchedule(plan, sche);
		fail("Not yet implemented");
	}
*/
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.marketing.server.utils.ScheduleValiateUtils#isQueryConditonIsNull(com.deppon.crm.module.marketing.server.action.CustomerVo)}
	 * .
	 */
	@Test
	public void testIsQueryConditonIsNull() {
		boolean b = false;
		try {
			b =validate.isQueryConditonIsNull(cvo);
			Assert.assertTrue(b);
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			cvo.setLinkManMobile("13917097761");
			b =validate.isQueryConditonIsNull(cvo);
			Assert.assertFalse(b);
		} catch (Exception e) {
			// TODO: handle exception
		}
			
		try {
			cvo = null;
			b =validate.isQueryConditonIsNull(cvo);
			Assert.assertTrue(b);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.marketing.server.utils.ScheduleValiateUtils#getDaysBetweenDates(java.util.Date, java.util.Date, int)}
	 * .
	 */
	@Test
	public void testGetDaysBetweenDates() {
		//超过三个月返回false  
		int perioid =3;
	    Date start = new Date();
		Date end = new Date();
		boolean b = validate.getDaysBetweenDates(start, end, perioid);
//		Assert.assertFalse(b);
		
		end.setMonth(8);
		b = validate.getDaysBetweenDates(start, end, perioid);
//		Assert.assertFalse(b);
		
		start.setMonth(2);
		b = validate.getDaysBetweenDates(start, end, perioid);
//		Assert.assertTrue(b);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.marketing.server.utils.ScheduleValiateUtils#objectIsEmpty(java.lang.Object)}
	 * .
	 */
	@Test
	public void testObjectIsEmpty() {
		Object obj = new Object();
		boolean b = validate.objectIsEmpty(obj);
		Assert.assertFalse(b);
		
		String str = "";
		b = validate.objectIsEmpty(str);
		Assert.assertTrue(b);
		
		
		List list = new ArrayList();
		b = validate.objectIsEmpty(list);
		Assert.assertTrue(b);
		
		Set set =  new HashSet();
		b = validate.objectIsEmpty(set);
		Assert.assertTrue(b);
		
		Map map =new HashMap();
		b = validate.objectIsEmpty(map);
		Assert.assertTrue(b);
		
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.marketing.server.utils.ScheduleValiateUtils#canSearchSchedule(com.deppon.crm.module.marketing.server.action.DevelopScheduleVO)}
	 * .
	 */
	@Test
	public void testCanSearchSchedule() {
		DevelopScheduleVO vo =null;
		Date start = new Date();
		Date end = new Date();
		boolean b = false;
		int period =  3;
		try {
			b = validate.canSearchSchedule(vo);
			Assert.assertFalse(b);
		} catch (BusinessException e) {
			e.printStackTrace();
			try {
				vo = new DevelopScheduleVO();
				start.setMonth(3);
				end.setMonth(9);
				vo.setCreateStartTime(start);
				vo.setCreateEndTime(end);
				b = validate.canSearchSchedule(vo);
				Assert.assertFalse(b);
			} catch (BusinessException e1) {
				e1.printStackTrace();
				vo = new DevelopScheduleVO();
				start.setMonth(3);
				end.setMonth(5);
				vo.setCreateStartTime(start);
				vo.setCreateEndTime(end);
				b = validate.canSearchSchedule(vo);
				Assert.assertTrue(b);
			}
		}
	}

}
