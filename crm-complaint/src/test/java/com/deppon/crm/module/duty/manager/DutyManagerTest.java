package com.deppon.crm.module.duty.manager;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.duty.server.manager.IDutyManager;
import com.deppon.crm.module.duty.server.util.DutyConstants;
import com.deppon.crm.module.duty.shared.domain.SearchDutyCondition;
import com.deppon.crm.module.duty.util.TestUtil;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;

public class DutyManagerTest {
	IDutyManager dutyManager = TestUtil.dutyManager;
/*	String[] xmls = { "com/deppon/crm/module/duty/server/META-INF/spring.xml",
			"classpath*:com/deppon/crm/module/complaint/server/META-INF/springTest.xml"};
//			"classpath*:com/deppon/crm/module/common/server/META-INF/spring.xml"};
*/	
//	private static ApplicationContext ctx = null;
	private static User user;
	static{
		user=new User();
		Employee emp=new Employee();
		Department dept=new Department();
		dept.setDeptCode("505");
		dept.setDeptName("aaa");
		dept.setId("555");
		emp.setId("84352");
		emp.setDeptId(dept);
		emp.setEmpCode("111");
		user.setId("84352");
		user.setEmpCode(emp);
		user.setLoginName("lee");
		user.setEmpCode(emp);
		emp.setDeptId(dept);
		Set<String> roleIds = new HashSet<String>();
		roleIds.add(DutyConstants.RECEIVE_AUTH_COMPLAINTEMP);
		roleIds.add(DutyConstants.STATISTICS_ROLE_ID);
		user.setRoleids(roleIds);
	}

/*	@Before
	public void setUp() throws Exception {
		try {
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			dutyManager = ctx.getBean(DutyManager.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	@Test
	public void testReceiveDuty(){
		//投诉组员
		try {
			dutyManager.receiveDuty(user,1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//异常组员
		Set<String> roleIds = new HashSet<String>();		
		roleIds.add(DutyConstants.RECEIVE_AUTH_UNUSUALEMP);
		user.setRoleids(roleIds);
		try {
			dutyManager.receiveDuty(user,1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//没有权限
		roleIds = new HashSet<String>();
		user.setRoleids(roleIds);
		try {
			dutyManager.receiveDuty(user,1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		roleIds.add(DutyConstants.RECEIVE_AUTH_COMPLAINTEMP);
	}
	@Test
	public void testSearchDutyByCondition(){
		SearchDutyCondition sdc = new SearchDutyCondition();
		//空查询条件
		try {
			dutyManager.searchDutyByCondition(user, sdc, 0, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//开始时间 和结束时间不为空
		sdc.setReceiveBeginTime(new Date(System.currentTimeMillis()-1000*60*60*24*3));
		sdc.setReceiveEndTime(new Date());
		dutyManager.searchDutyByCondition(user, sdc, 0, 1);
		//接入人设置
		sdc.setReceiveUser("曹");
		dutyManager.searchDutyByCondition(user, sdc, 0, 1);
		//重复工单码
		sdc.setCodeType(DutyConstants.REBINDNO);
		sdc.setCode("1234");
		dutyManager.searchDutyByCondition(user, sdc, 0, 1);
		//凭证号
		sdc.setCodeType(DutyConstants.VOUCHERNUMBER);
		dutyManager.searchDutyByCondition(user, sdc, 0, 1);
		//处理编号
		sdc.setCodeType(DutyConstants.DEALNUMBER);
		dutyManager.searchDutyByCondition(user, sdc, 0, 1);
	}
	@Test
	public void testInitReceiveDuty(){
		dutyManager.initReceiveDuty(user);
	}
	
//	@Test
//	public void testStatisAgreeOfApproval(){
//		DutyFeedback dfb = new DutyFeedback();
//		dfb.setFeedbackId("1");
//		dfb.setDetailId("1");
//		dutyManager.statisOperationOfApproval(dfb,DutyConstants.STATISTICS_APPROVAL_DISAGREE, user);
//	}
	
	@Test
	public void feedBackExtendedTime(){
		dutyManager.feedBackExtendedTime(2);
	}
	
	@Test
	public void feedBackExtended(){
		dutyManager.feedBackExtended();
	}
	
	@Test
	public void feedBackApprovalExtended(){
		dutyManager.feedBackApprovalExtended();
	}
}
