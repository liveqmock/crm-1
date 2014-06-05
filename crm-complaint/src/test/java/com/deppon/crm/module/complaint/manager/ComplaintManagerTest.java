package com.deppon.crm.module.complaint.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.complaint.server.manager.ComplaintRule;
import com.deppon.crm.module.complaint.server.manager.ComplaintValidate;
import com.deppon.crm.module.complaint.server.manager.impl.ComplaintManager;
import com.deppon.crm.module.complaint.server.service.IProcResultService;
import com.deppon.crm.module.complaint.server.util.BasicInfoConstants;
import com.deppon.crm.module.complaint.server.util.Constants;
import com.deppon.crm.module.complaint.shared.domain.BasciLevel;
import com.deppon.crm.module.complaint.shared.domain.BasciLevelSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.BasicBusScopeVO;
import com.deppon.crm.module.complaint.shared.domain.BasicBusType;
import com.deppon.crm.module.complaint.shared.domain.BasicInfo;
import com.deppon.crm.module.complaint.shared.domain.BasicSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.Bulletin;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.ComplaintSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.FeedBackReasion;
import com.deppon.crm.module.complaint.shared.domain.ProcResult;
import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.complaint.shared.exception.ComplaintException;
import com.deppon.crm.module.complaint.shared.exception.ComplaintValidatorException;
import com.deppon.crm.module.duty.util.TestUtil;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title ComplaintManagerTest.java
 * @package com.deppon.crm.module.complaint.manager
 * @author ouy
 * @version 0.1 2012-4-14
 */
public class ComplaintManagerTest extends TestCase {

	private ComplaintManager complaintManager = TestUtil.complaintManager;
	private IProcResultService procResultService = TestUtil.procResultService;
	private IMessageManager messageManager = TestUtil.messageManager;
//	private static ApplicationContext ctx = null;
//	private IComplaintService complaintService = TestUtil.complaintService;
//	String[] xmls = {
//			"classpath*:com/deppon/crm/module/authorization/server/META-INF/spring.xml",
//			"classpath*:com/deppon/crm/module/common/server/META-INF/spring.xml",
//			"classpath*:com/deppon/crm/module/duty/server/META-INF/spring.xml",
//			"com/deppon/crm/module/complaint/server/META-INF/spring.xml",
//			"classpath*:com/deppon/crm/module/client/server/META-INF/spring.xml"
//			};
	private static User user;
	static {
		user = new User();
		Employee emp = new Employee();
		Department dept = new Department();
		dept.setDeptCode("505");
		dept.setDeptName("aaa");
		dept.setId("505");
		emp.setId("84352");
		emp.setDeptId(dept);
		emp.setEmpCode("111");
		user.setId("84352");
		user.setEmpCode(emp);
		user.setLoginName("lee");
		user.setEmpCode(emp);
		emp.setDeptId(dept);
	}
	@After
	public void tearDown() throws Exception {
		
	}
	@Before
	public void setUp() throws Exception {
//		try {
//			if (ctx == null) {
//				ctx = new ClassPathXmlApplicationContext(xmls);
//			}
//			complaintManager = ctx.getBean(ComplaintManager.class);
//			procResultService = ctx.getBean(ProcResultServiceImpl.class);
//			messageManager = ctx.getBean(MessageManager.class);
//			complaintService = ctx.getBean(ComplaintServiceImpl.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Test
	public void testInitPageData() {
		complaintManager.initPageData();
	}

	@Test
	public void testSearchAppByConditions() {
		complaintManager.searchAppByConditions(new Complaint());
	}

	@Test
	public void testSearchReportedComplaints() {
		ComplaintSearchCondition csc = new ComplaintSearchCondition();
		csc.setStart(0);
		csc.setLimit(1);

		csc.setToBeBanging("");
		complaintManager.searchReportedComplaints(csc);

		csc.setBill("");
		complaintManager.searchReportedComplaints(csc);

		csc.setBill("1232");
		complaintManager.searchReportedComplaints(csc);

		csc.setBill(null);
		complaintManager.searchReportedComplaints(csc);

		csc.setDealbill("");
		complaintManager.searchReportedComplaints(csc);

		csc.setDealbill("1234");
		complaintManager.searchReportedComplaints(csc);

		csc.setDealbill(null);

		complaintManager.searchReportedComplaints(csc);

		csc.setRecomcode("");
		complaintManager.searchReportedComplaints(csc);

		csc.setRecomcode("123");
		complaintManager.searchReportedComplaints(csc);

		csc.setProstatus(null);
		complaintManager.searchReportedComplaints(csc);

		csc.setProstatus("");
		complaintManager.searchReportedComplaints(csc);

		csc.setProstatus("aaa");
		complaintManager.searchReportedComplaints(csc);

		csc.setProstatus("aa,bb,cc,dd");
		complaintManager.searchReportedComplaints(csc);

		csc.setToBeBanging(Constants.TO_BE_BANGING_YES);
		csc.setProstatus(null);
		complaintManager.searchReportedComplaints(csc);

		csc.setProstatus("");
		complaintManager.searchReportedComplaints(csc);

		csc.setProstatus("aaa");
		complaintManager.searchReportedComplaints(csc);

		csc.setProstatus("aa,bb,cc,dd");
		complaintManager.searchReportedComplaints(csc);

	}

	@Test
	public void testSearchAccessComplaints() {
		ComplaintSearchCondition csc = new ComplaintSearchCondition();
		csc.setReportType("aa");
		complaintManager.searchAccessComplaints(csc, user);
	}

	@Test
	public void testGetComplaintById() {
		complaintManager.getComplaintById("1233");
	}

	// 未通过测试
	@Test
	public void testSearchMembersByOWNum() {
		try {
			complaintManager.searchMembersByOWNum("2873",
					new ComplaintSearchCondition());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	// 未通过测试
	public void testGetContractByPhone() {
		try {
			complaintManager.getContractByPhone("aadfasdf",
					new ComplaintSearchCondition());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			complaintManager.getContractByPhone("13711858585",
					new ComplaintSearchCondition());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void testGetRepeatedCode() {
		try {
			complaintManager.getRepeatedCode("", "", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			complaintManager.getRepeatedCode("1", "400001033", "1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			complaintManager
					.getRepeatedCode("045057", "400001033", "COMPLAINT");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			complaintManager.getRepeatedCode("045057", "400001035", "UNUSUAL");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			complaintManager.getRepeatedCode("045057", "400001033", "UNUSUAL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	// 接口不测
	public void testSearchWaitProccesComplaint() {
		// try {
		// complaintManager.searchWaitProccesComplaint(user, 0, 1);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	@Test
	// 从未被调用过
	public void testGetSpecialStatus() {
		System.out.print("ddd");
	}

	@Test
	public void testSearchComplaintCustomersByDate() {
		complaintManager.searchComplaintCustomersByDate("2012-07");
	}

	@Test
	public void testGetFinishProccessComplaintNextStatus() {

		Complaint complaint = new Complaint();
		complaint.setFeedback(Constants.IF_FEED_BACK_NO);
		String result = ComplaintRule
				.getFinishProccessComplaintNextStatus(complaint);
		assertEquals(result, Constants.COMPLAINT_STATUS_WAITE_RESPONSE);

		complaint.setFeedback(Constants.IF_FEED_BACK_YES);
		result = ComplaintRule.getFinishProccessComplaintNextStatus(complaint);
		assertEquals(result, Constants.COMPLAINT_STATUS_BACKGROUND);

		try {
			complaint.setFeedback(null);
			result = ComplaintRule
					.getFinishProccessComplaintNextStatus(complaint);
		} catch (ComplaintException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testCanFinishProccessComplaint() {
		Complaint complaint = new Complaint();
		complaint.setProstatus(Constants.COMPLAINT_STATUS_WAIT_APPROVAL);
		assertTrue(ComplaintValidate.canFinishProccessComplaint(complaint));

		complaint.setProstatus(Constants.COMPLAINT_STATUS_UPGRADED);
		assertTrue(ComplaintValidate.canFinishProccessComplaint(complaint));

		complaint.setProstatus(Constants.COMPLAINT_STATUS_PENDING);
		assertTrue(ComplaintValidate.canFinishProccessComplaint(complaint));

		complaint.setProstatus(Constants.COMPLAINT_STATUS_APPROVAL_RETURNED);
		assertTrue(ComplaintValidate.canFinishProccessComplaint(complaint));

		try {
			complaint.setProstatus(Constants.COMPLAINT_STATUS_WAITE_RESPONSE);
			assertTrue(ComplaintValidate.canFinishProccessComplaint(complaint));
		} catch (ComplaintValidatorException e) {
			assertTrue(true);
		}
	}
	@Test
	public void testSearchTaskComplaints() {
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		condition.setLimit(10);
		condition.setStart(0);
		condition.setUser(user);
		Map map = complaintManager.searchTaskComplaints(condition);
	}
	@Test
	public void testReturnToProcess() {
		String complaintId = "400010025";
		String resultId = "400000973";
		String feedbackContent = "ccadddee";
		try {
			complaintManager.returnToProcess(complaintId, resultId,
					feedbackContent, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		complaintId = "400158713";
		resultId = "400000973";
		feedbackContent = "ccadddee";
		try {
			complaintManager.returnToProcess(complaintId, resultId,
					feedbackContent, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		complaintId = "400158668";
		resultId = "400000973";
		feedbackContent = "ccadddee";
		try {
			complaintManager.returnToProcess(complaintId, resultId,
					feedbackContent, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		complaintId = "400001033";
		resultId = "400000973";
		feedbackContent = "ccadddee";
		try {
			complaintManager.returnToProcess(complaintId, resultId,
					feedbackContent, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		complaintId = "400001033";
		resultId = "400000973";
		feedbackContent = "ccadddee";
		try {
			complaintManager.returnToProcess(complaintId, resultId,
					feedbackContent, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		complaintId = "400001033";
		resultId = "400000964";
		feedbackContent = "ccadddee";
		try {
			complaintManager.returnToProcess(complaintId, resultId,
					feedbackContent, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		complaintId = "400001171";
		resultId = "400001109";
		feedbackContent = "ccadddee";
		try {
			complaintManager.returnToProcess(complaintId, resultId,
					feedbackContent, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		complaintId = "400001018";
		resultId = "400001182";
		feedbackContent = "ccadddee";
		try {
			complaintManager.returnToProcess(complaintId, resultId,
					feedbackContent, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 进一步优化 需要先找到接入工单前处理
	 */
	@Test
	public void testGetRecently3ComplaintsByReportType() {
		// 异常
		Complaint c = new Complaint();
		c.setFid(new BigDecimal("400001033"));
		c.setCreatetime(new Date());
		c.setLastupdatetime(new Date());
		c.setReporttime(new Date());
		c.setCreateuserid(new BigDecimal(user.getEmpCode().getId()));
		c.setLastmodifyuserid(new BigDecimal(user.getEmpCode().getId()));
		c.setFeedback("2");
		c.setReporttype("COMPLAINT");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		c.setBusinessModel(Constants.BUSINESS_MODEL_UNEXPRESS);
		complaintManager.submitComplaint(c,user);
		System.out.println(c.getFid());
		c.setFid(c.getFid());
		complaintManager.managerLockComplaint(c, user);
//		c.setFid(new BigDecimal("400001033"));
		complaintManager.managerLockComplaint(c, user);
//		c.setFid(new BigDecimal("400001033"));
		complaintManager.managerLockComplaint(c, user);
//		c.setFid(new BigDecimal("400001033"));
		complaintManager.managerLockComplaint(c, user);
		// 投诉
//		c.setFid(new BigDecimal("400001033"));
		complaintManager.managerLockComplaint(c, user);
//		c.setFid(new BigDecimal("400001033"));
		complaintManager.managerLockComplaint(c, user);
//		c.setFid(new BigDecimal("400001033"));
		complaintManager.managerLockComplaint(c, user);
//		c.setFid(new BigDecimal("400001033"));
		complaintManager.managerLockComplaint(c, user);

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("sessionUser", user);
		parameterMap.put("reportType", "COMPLAINT");
		List<Complaint> compList = complaintManager
				.getRecently3ComplaintsByReportType(parameterMap);

		parameterMap = new HashMap<String, Object>();
		parameterMap.put("sessionUser", user);
		parameterMap.put("reportType", "UNUSUAL");
		complaintManager.getRecently3ComplaintsByReportType(parameterMap);
//		complaintService.deleteComplaint(new BigDecimal("400001033"));
		
	}

	@Test
	public void testInsertCompAccessLog() {
		System.out.print("ddd");
	}

	@Test
	public void testPassFeedback() {
		String complaintId = "1";
		String resultId = "373";
		String feedbackContent = "aa";
		String resolveCode = "11";
		try {
			complaintManager.passFeedback(complaintId, resultId,
					feedbackContent, resolveCode, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		complaintId = "400001222";
		resultId = "400001082";
		feedbackContent = "aa";
		resolveCode = "11";
		try {
			complaintManager.passFeedback(complaintId, resultId,
					feedbackContent, resolveCode, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		complaintId = "400001222";
		resultId = "400001082";
		feedbackContent = "aa";
		resolveCode = Constants.COMPLAINT_RESOVLE_STATUS_UNRESOVLE;
		try {
			complaintManager.passFeedback(complaintId, resultId,
					feedbackContent, resolveCode, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelayApplication() {
		String complaintId = "1";
		String resultId = "373";
		String feedbackContent = "aa";
		String applyCode = "24";
		try {
			complaintManager.delayApplication(complaintId, resultId,
					feedbackContent, applyCode, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		complaintId = "400001057";
		resultId = "400001123";
		feedbackContent = "aa";
		applyCode = "24";
		try {
			complaintManager.delayApplication(complaintId, resultId,
					feedbackContent, applyCode, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		complaintId = "400001014";
		resultId = "400000963";
		feedbackContent = "aa";
		applyCode = "24";
		try {
			complaintManager.delayApplication(complaintId, resultId,
					feedbackContent, applyCode, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// FROMPROCESS
		complaintId = "400001036";
		resultId = "400000981";
		feedbackContent = "aa";
		applyCode = "24";
		try {
			complaintManager.delayApplication(complaintId, resultId,
					feedbackContent, applyCode, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// DELAY_FROM_PROCESS
		complaintId = "400001032";
		resultId = "400000962";
		feedbackContent = "aa";
		applyCode = "24";
		try {
			complaintManager.delayApplication(complaintId, resultId,
					feedbackContent, applyCode, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// RETURN_FROM_PROCESS
		complaintId = "400001013";
		resultId = "400000961";
		feedbackContent = "aa";
		applyCode = "24";
		try {
			complaintManager.delayApplication(complaintId, resultId,
					feedbackContent, applyCode, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ROMAPPROVAL
		complaintId = "400001040";
		resultId = "400000966";
		feedbackContent = "aa";
		applyCode = "24";
		try {
			complaintManager.delayApplication(complaintId, resultId,
					feedbackContent, applyCode, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// DELAY_FORM_APPROVAL
		complaintId = "400159930";
		resultId = "400158417";
		feedbackContent = "aa";
		applyCode = "24";
		try {
			complaintManager.delayApplication(complaintId, resultId,
					feedbackContent, applyCode, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// RETURN_FROM_APPROVAL
		complaintId = "400001042";
		resultId = "400000980";
		feedbackContent = "aa";
		applyCode = "24";
		try {
			complaintManager.delayApplication(complaintId, resultId,
					feedbackContent, applyCode, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		// //RETURN_FROM_APPROVAL
		// complaintId="400001042";
		// resultId="400000980";
		// feedbackContent="aa";
		// applyCode="24";
		// try{
		// complaintManager.delayApplication(complaintId, resultId,
		// feedbackContent, applyCode, user);
		// }catch (Exception e) {
		// e.printStackTrace();
		// }

		// FROMUPGRADE
		complaintId = "400156930";
		resultId = "400158273";
		feedbackContent = "aa";
		applyCode = "24";
		try {
			complaintManager.delayApplication(complaintId, resultId,
					feedbackContent, applyCode, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// DELAY_FROM_UPGRADE
		complaintId = "400092647";
		resultId = "400093008";
		feedbackContent = "aa";
		applyCode = "24";
		try {
			complaintManager.delayApplication(complaintId, resultId,
					feedbackContent, applyCode, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// RETURN_FROM_UPGRADE
		complaintId = "400001041";
		resultId = "400000986";
		feedbackContent = "aa";
		applyCode = "24";
		try {
			complaintManager.delayApplication(complaintId, resultId,
					feedbackContent, applyCode, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSubmitComplaint() {
		Complaint c = new Complaint();
		try {
			complaintManager.submitComplaint(null, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFeedback(Constants.IF_FEED_BACK_YES);
		try {
			complaintManager.submitComplaint(c, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFeedback(Constants.IF_FEED_BACK_NO);
		c.setReporttype("COMPLAINT");
		c.setBill("045057");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		try {
			c.setMainFid("400001033");
			c.setServiceId("1");
			complaintManager.submitComplaint(c,user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFeedback(Constants.IF_FEED_BACK_NO);
		c.setReporttype("UNUSUAL");
		c.setBill("045057");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("NORMAL");
		try {
			c.setMainFid("400001035");
			c.setServiceId("1");
			complaintManager.submitComplaint(c,user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFeedback(Constants.IF_FEED_BACK_NO);
		c.setReporttype("COMPLAINT");
		c.setBill("045057");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		try {
			c.setMainFid("400001033");
			complaintManager.submitComplaint(c, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFeedback(Constants.IF_FEED_BACK_NO);
		c.setReporttype("UNUSUAL");
		c.setBill("045057");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("NORMAL");
		try {
			c.setMainFid("400001035");
			complaintManager.submitComplaint(c, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFeedback(Constants.IF_FEED_BACK_NO);
		c.setReporttype("COMPLAINT");
		c.setBill("045057");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		try {
			c.setMainFid("");
			complaintManager.submitComplaint(c,user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFeedback(Constants.IF_FEED_BACK_NO);
		c.setReporttype("UNUSUAL");
		c.setBill("045057");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("NORMAL");
		try {
			complaintManager.submitComplaint(c, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchReturnedComplaints() {
		ComplaintSearchCondition condition = new ComplaintSearchCondition();

		try {
			complaintManager.searchReturnedComplaints(condition, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSubmitComplaint1() {
		Complaint c = new Complaint();
		try {
			complaintManager.submitComplaint(c, null, null, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFid(new BigDecimal("400001095"));
		try {
			complaintManager.submitComplaint(c, null, null, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFid(new BigDecimal("400001033"));
		c.setFeedback(Constants.IF_FEED_BACK_NO);
		c.setReporttype("COMPLAINT");
		c.setBill("045057");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		try {
			complaintManager.submitComplaint(c, "400001033", null, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new Complaint();
		c.setFid(new BigDecimal("400001035"));
		c.setFeedback(Constants.IF_FEED_BACK_NO);
		c.setReporttype("UNUSUAL");
		c.setBill("045057");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		try {
			complaintManager.submitComplaint(c, "400001035", null, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFid(new BigDecimal("400018657"));
		c.setFeedback(Constants.IF_FEED_BACK_YES);
		c.setReporttype("COMPLAINT");
		c.setBill("045057");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		c.setRebindno("D2012082000949");
		try {
			complaintManager.submitComplaint(c, "400018657", "123", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new Complaint();
		c.setFid(new BigDecimal("400016921"));
		c.setFeedback(Constants.IF_FEED_BACK_NO);
		c.setReporttype("UNUSUAL");
		c.setBill("045057");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		c.setRebindno("Y2012081800604");
		try {
			complaintManager.submitComplaint(c, "400001035", "Y2012081800604",
					user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFid(new BigDecimal("400016921"));
		c.setFeedback(Constants.IF_FEED_BACK_YES);
		c.setReporttype("UNUSUAL");
		c.setBill("045057");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		c.setRebindno("Y2012081800604");
		try {
			complaintManager.submitComplaint(c, "400001035", "Y2012081800604",
					user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFid(new BigDecimal("400001159"));
		c.setFeedback(Constants.IF_FEED_BACK_YES);
		c.setReporttype("UNUSUAL");
		c.setBill("045057");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		c.setRebindno("Y2012081800604");
		try {
			complaintManager.submitComplaint(c, "400001159", "Y2012081800604",
					user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFid(new BigDecimal("400003667"));
		c.setFeedback(Constants.IF_FEED_BACK_YES);
		c.setReporttype("UNUSUAL");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		c.setRebindno("Y2012081800604");
		try {
			complaintManager.submitComplaint(c, "400003667", "Y2012081800604",
					user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new Complaint();
		c.setFid(new BigDecimal("400003667"));
		c.setFeedback(Constants.IF_FEED_BACK_YES);
		c.setReporttype("UNUSUAL");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		c.setRebindno("Y2012081800604");
		try {
			complaintManager.submitComplaint(c, "400003667", "Y2012081800604",
					user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFid(new BigDecimal("400022681"));
		c.setFeedback(Constants.IF_FEED_BACK_YES);
		c.setReporttype("UNUSUAL");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		c.setBill("1234");
		c.setRebindno("Y2012082500451");
		try {
			complaintManager.submitComplaint(c, "400022681", "Y2012081800604",
					user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new Complaint();
		c.setFid(new BigDecimal("400022681"));
		c.setFeedback(Constants.IF_FEED_BACK_YES);
		c.setReporttype("UNUSUAL");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		c.setBill("85475281");
		c.setRebindno("Y2012082500451");
		try {
			complaintManager.submitComplaint(c, "400022681", "Y2012081800604",
					user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFid(new BigDecimal("400005324"));
		c.setFeedback(Constants.IF_FEED_BACK_YES);
		c.setReporttype("UNUSUAL");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		c.setBill("64785091");
		c.setRebindno("Y2012080400114");
		try {
			complaintManager.submitComplaint(c, "400005324", "Y2012080400114",
					user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFid(new BigDecimal("400022537"));
		c.setFeedback(Constants.IF_FEED_BACK_YES);
		c.setReporttype("UNUSUAL");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		c.setBill("76101254");
		c.setRebindno("Y2012082500315");
		try {
			complaintManager.submitComplaint(c, "400022537", "Y2012082500315",
					user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFid(new BigDecimal("400022175"));
		c.setFeedback(Constants.IF_FEED_BACK_YES);
		c.setReporttype("UNUSUAL");
		c.setTimelimit(new BigDecimal(0));
		c.setTilimitcycle("MINUTE");
		c.setPririty("HIGH");
		c.setBill("123");
		c.setRebindno("Y2012082400804");
		try {
			complaintManager.submitComplaint(c, null, null, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testApplyMoreTimeApproval() {
		Complaint c = new Complaint();
		c.setFid(new BigDecimal("400001042"));
		String agreeFlag = Constants.IF_DELAY_APPLICATION_EFFECTIVE_NO;
		List<Result> list = new ArrayList<Result>();
		Result r = new Result();
		list.add(r);
		r = new Result();
		r.setFid(new BigDecimal("400000980"));
		r.setProcesstimelimit(new BigDecimal("19000"));
		r.setAppdelay(new BigDecimal("12000"));
		list.add(r);
		r = new Result();
		r.setFid(new BigDecimal("400000974"));
		r.setProcesstimelimit(new BigDecimal("19000"));
		r.setAppdelay(new BigDecimal("12000"));
		list.add(r);
		r = new Result();
		r.setFid(new BigDecimal("400000975"));
		r.setProcesstimelimit(new BigDecimal("19000"));
		r.setAppdelay(new BigDecimal("12000"));
		list.add(r);
		r = new Result();
		r.setFid(new BigDecimal("400000976"));
		r.setProcesstimelimit(new BigDecimal("19000"));
		r.setAppdelay(new BigDecimal("12000"));
		list.add(r);
		try {
			complaintManager.applyMoreTimeApproval(c, list, agreeFlag, user,
					"aa");
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new Complaint();
		c.setFid(new BigDecimal("400001042"));
		agreeFlag = Constants.IF_DELAY_APPLICATION_EFFECTIVE_NO;
		list = new ArrayList<Result>();
		r = new Result();
		list.add(r);
		r = new Result();
		r.setFid(new BigDecimal("400000980"));
		r.setProcesstimelimit(new BigDecimal("19000"));
		r.setAppdelay(new BigDecimal("12000"));
		list.add(r);
		r = new Result();
		r.setFid(new BigDecimal("400000974"));
		r.setProcesstimelimit(new BigDecimal("19000"));
		r.setAppdelay(new BigDecimal("12000"));
		list.add(r);
		r = new Result();
		r.setFid(new BigDecimal("400000975"));
		r.setProcesstimelimit(new BigDecimal("19000"));
		r.setAppdelay(new BigDecimal("12000"));
		list.add(r);
		r = new Result();
		r.setFid(new BigDecimal("400000976"));
		r.setProcesstimelimit(new BigDecimal("19000"));
		r.setAppdelay(new BigDecimal("12000"));
		list.add(r);
		try {
			complaintManager.applyMoreTimeApproval(c, list, agreeFlag, user,
					"aa");
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFid(new BigDecimal("400001042"));
		agreeFlag = Constants.IF_DELAY_APPLICATION_EFFECTIVE_YES;
		list = new ArrayList<Result>();
		r = new Result();
		r.setFid(new BigDecimal("400000980"));
		r.setProcesstimelimit(new BigDecimal("19000"));
		r.setAppdelay(new BigDecimal("12000"));
		list.add(r);
		r = new Result();
		r.setFid(new BigDecimal("400000974"));
		r.setProcesstimelimit(new BigDecimal("19000"));
		r.setAppdelay(new BigDecimal("12000"));
		list.add(r);
		r = new Result();
		r.setFid(new BigDecimal("400000975"));
		r.setProcesstimelimit(new BigDecimal("19000"));
		r.setAppdelay(new BigDecimal("12000"));
		list.add(r);
		r = new Result();
		r.setFid(new BigDecimal("400000976"));
		r.setProcesstimelimit(new BigDecimal("19000"));
		r.setAppdelay(new BigDecimal("12000"));
		list.add(r);
		try {
			complaintManager.applyMoreTimeApproval(c, list, agreeFlag, user,
					"aa");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetMostRecentlyComplaintByCustCode() {
		String custCode = "1";
		Complaint complaint = complaintManager
				.getMostRecentlyComplaintByCustCode(custCode);

	}
	@Test
	public void testTearchComplaintIncludeSatisfy() {
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		condition.setComplainid("1");
		complaintManager.searchComplaintIncludeSatisfy(condition);

	}
	@Test
	public void testGetAllComplaintByCustCode() {
		String custCode = "124";
		List<Complaint> complaintList = complaintManager
				.getAllComplaintByCustCode(custCode);
		assertNotNull(complaintList);
	}

	// public void testGetAllDepartment() {
	// List<Department> departments = complaintManager.getAllDepartment();
	//
	// boolean flag = Boolean.FALSE;
	// if (null != departments && departments.size() > 0) {
	// flag = Boolean.TRUE;
	// }
	// assertTrue(flag);
	// }

	// public void testGetAllEmployeesByDepartmentId() {
	// String deptId = "22239";
	// List<Employee> employees =
	// complaintManager.getAllEmployeesByDepartmentId(deptId);
	// boolean flag = Boolean.FALSE;
	// if (null != employees && employees.size() > 0) {
	// flag = Boolean.TRUE;
	// }
	// assertTrue(flag);
	// }

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-4-25 void
	 */
	@Test
	public void testUpdateFoundationData() {
		// 准备数据
		List<ProcResult> oldProcResult = new ArrayList<ProcResult>();
		List<BigDecimal> idList = new ArrayList<BigDecimal>();
		for (int i = 0; i < 3; i++) {
			ProcResult procResult = new ProcResult();
			procResult.setCreateuserid(new BigDecimal(1));
			procResult.setDeallan("ENGELISH");
			procResult.setFeedbacklimit("1000");
			procResult.setLastmodifyuserid(new BigDecimal(1));
			procResult.setLevelid(new BigDecimal(702));
			procResult.setProclimit("250");
			procResultService.savaProcResult(procResult);
			oldProcResult.add(procResult);
			idList.add(procResult.getFid());
		}
		// 连个要修改的数据
		List<ProcResult> newProcResult = new ArrayList<ProcResult>();
		ProcResult newpr0 = procResultService.getProcResultById(idList.get(0));
		newpr0.setDeallan("CHINESE");
		newProcResult.add(newpr0);
		ProcResult newpr1 = procResultService.getProcResultById(idList.get(1));
		newpr1.setDeallan("CHINESE");
		newProcResult.add(newpr1);
		// 要新增的数据
		ProcResult procResult = new ProcResult();
		procResult.setCreateuserid(new BigDecimal(168));
		procResult.setDeallan("GEMANY");
		procResult.setFeedbacklimit("1000");
		procResult.setLastmodifyuserid(new BigDecimal(168));
		procResult.setLevelid(new BigDecimal(702));
		procResult.setProclimit("250");

		newProcResult.add(procResult);

		BasciLevel childBasciLevel = new BasciLevel();
		childBasciLevel.setFid(new BigDecimal(702));
		User user = new User();
		Employee empCode = new Employee();
		empCode.setId("456");
		user.setId("456");
		user.setEmpCode(empCode);
		// 业务范围id
		BigDecimal fid = new BigDecimal(123);
		complaintManager.saveOrUpdateFoundationData(newProcResult,
				childBasciLevel, fid, Constants.COMPLAINT_REPEATCODE_REPORT,
				user);

		List<ProcResult> prList = procResultService
				.getProcResultByLevelId(childBasciLevel.getFid());
		boolean ifUpdate = false;// 修否修改成功
		boolean ifInsert = false;// 是否增加成功
		for (ProcResult pr : prList) {
			if (pr.getFid().equals(idList.get(0))
					|| pr.getFid().equals(idList.get(1))) {
				ifUpdate = false;
				if ("CHINESE".equals(pr.getDeallan())) {
					ifUpdate = true;
				}
			} else {
				if ("GEMANY".equals(pr.getDeallan())) {
					ifInsert = true;
				}
			}
			procResultService.deleteProcResultById(pr.getFid());
		}
		// 是否删除成功
		ProcResult prd = procResultService.getProcResultById(idList.get(2));
		assertEquals(prd, null);

		assertEquals(ifUpdate, true);
		assertEquals(ifInsert, true);
	}

	/**
	 * <p>
	 * Description: 退回上报人<br />
	 * </p>
	 * 
	 * @author admin
	 * @version 0.1 2012-4-28 void
	 */
	@Test
	public void testReturnReportor() {
		Map map = new HashMap();
		map.put("compId", "400001256");
		map.put("feedbackreason", "测试用例退回原因");
		// User user = new User();
		// Employee emp = new Employee();
		// emp.setEmpName("测试用例用户姓名");
		Department dept = new Department();
		dept.setDeptCode("111");
		// emp.setDeptId(dept);
		// user.setEmpCode(emp);
		// user.setId("10220");
		map.put("sessionUser", user);
		boolean flag = Boolean.TRUE;
		try {
			complaintManager.returnReportor(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			map.put("compId", "123");
			complaintManager.returnReportor(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			map.put("compId", "123");
			map.put("sessionUser", null);
			complaintManager.returnReportor(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// assertTrue(flag);
	}

	@Test
	public void testSubmitForApproval() {
		Complaint c = new Complaint();
		c.setFid(new BigDecimal("400001036"));
		c.setProstatus(Constants.COMPLAINT_STATUS_UPGRADED_RETURNED);

		List<Result> rList = new ArrayList<Result>();
		Result r = new Result();
		r.setComplainid(new BigDecimal("400001036"));
		r.setDealman(user.getEmpCode().getId());
		r.setDealmatters("无异议");
		r.setTaskpartmentid(new BigDecimal(user.getEmpCode().getDeptId()
				.getId().toString()));
		r.setDealType("");
		r.setFid(new BigDecimal("400000981"));
		rList.add(r);

		List<Bulletin> bulletinList = new ArrayList<Bulletin>();
		Bulletin b = new Bulletin();
		b.setFid(new BigDecimal("400000981"));
		b.setCompid(new BigDecimal("400001036"));
		b.setLastmodifyuserid(new BigDecimal(user.getEmpCode().getId()));
		b.setCreateuserid(new Integer(user.getEmpCode().getId()));
		b.setBulletinid(new BigDecimal("32766"));
		b.setBulletinname("夏夏");
		b.setBulletinJobId("123");

		bulletinList.add(b);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("complaint", c);
		map.put("bulletinList", bulletinList);
		map.put("resultList", rList);
		map.put("sessionUser", user);
		map.put("processLimit", "10");
		map.put("feedbackLimit", "10");

		try {
			complaintManager.submitForApproval(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new Complaint();
		c.setFid(new BigDecimal("400001036"));
		c.setProstatus(Constants.COMPLAINT_STATUS_UPGRADED_RETURNED);

		rList = new ArrayList<Result>();
		r = new Result();
		r.setComplainid(new BigDecimal("400001036"));
		r.setDealman(user.getEmpCode().getId());
		r.setDealmatters("无异议");
		r.setTaskpartmentid(new BigDecimal(user.getEmpCode().getDeptId()
				.getId().toString()));
		r.setDealType("");
		r.setFid(new BigDecimal("0"));
		rList.add(r);

		r = new Result();
		r.setComplainid(new BigDecimal("400001036"));
		r.setDealman(user.getEmpCode().getId());
		r.setDealmatters("无异议");

		r.setTaskpartmentid(new BigDecimal(user.getEmpCode().getDeptId()
				.getId().toString()));
		r.setDealType("");
		r.setFid(null);
		rList.add(r);

		r = new Result();
		r.setComplainid(new BigDecimal("400001036"));
		r.setDealman(user.getEmpCode().getId());
		r.setDealmatters("无异议");
		r.setTaskpartmentid(new BigDecimal(user.getEmpCode().getDeptId()
				.getId().toString()));
		r.setDealType("");
		r.setFid(new BigDecimal("400000981"));
		rList.add(r);

		bulletinList = new ArrayList<Bulletin>();
		b = new Bulletin();
		b.setFid(new BigDecimal("400000981"));
		b.setCompid(new BigDecimal("400001036"));
		b.setLastmodifyuserid(new BigDecimal(user.getEmpCode().getId()));
		b.setCreateuserid(new Integer(user.getEmpCode().getId()));
		b.setBulletinid(new BigDecimal("32766"));
		b.setBulletinname("夏夏");
		b.setBulletinJobId("123");

		bulletinList.add(b);

		map = new HashMap<String, Object>();
		map.put("complaint", c);
		map.put("bulletinList", bulletinList);
		map.put("resultList", rList);
		map.put("sessionUser", user);
		map.put("processLimit", "10");
		map.put("feedbackLimit", "10");

		try {
			complaintManager.submitForApproval(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFinishedComplaintProccess() {
		try {
			complaintManager.finishedComplaintProccess(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Complaint c = new Complaint();
		c.setFid(new BigDecimal("400001036"));
		c.setProstatus(Constants.COMPLAINT_STATUS_UPGRADED_RETURNED);

		List<Result> rList = new ArrayList<Result>();
		Result r = new Result();
		r.setComplainid(new BigDecimal("400001036"));
		r.setDealman(user.getEmpCode().getId());
		r.setDealmatters("无异议");
		r.setTaskpartmentid(new BigDecimal(user.getEmpCode().getDeptId()
				.getId().toString()));
		r.setDealType("");
		r.setFid(new BigDecimal("400000981"));
		rList.add(r);

		List<Bulletin> bulletinList = new ArrayList<Bulletin>();
		Bulletin b = new Bulletin();
		b.setFid(new BigDecimal("400000981"));
		b.setCompid(new BigDecimal("400001036"));
		b.setLastmodifyuserid(new BigDecimal(user.getEmpCode().getId()));
		b.setCreateuserid(new Integer(user.getEmpCode().getId()));
		b.setBulletinid(new BigDecimal("32766"));
		b.setBulletinname("夏夏");
		b.setBulletinJobId("123");

		bulletinList.add(b);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("complaint", c);
		map.put("bulletinList", bulletinList);
		map.put("resultList", rList);
		map.put("sessionUser", user);
		map.put("processLimit", "10");
		map.put("feedbackLimit", "10");

		try {
			complaintManager.finishedComplaintProccess(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new Complaint();
		c.setFid(new BigDecimal("400001036"));
		c.setProstatus(Constants.COMPLAINT_STATUS_UPGRADED_RETURNED);
		c.setFeedback(Constants.IF_FEED_BACK_YES);
		rList = new ArrayList<Result>();
		r = new Result();
		r.setComplainid(new BigDecimal("400001036"));
		r.setDealman(user.getEmpCode().getId());
		r.setDealmatters("无异议");
		r.setTaskpartmentid(new BigDecimal(user.getEmpCode().getDeptId()
				.getId().toString()));
		r.setDealType(Constants.COMPLAINT_PROCESSTYPE_EMPLOYEE);
		r.setFid(new BigDecimal("400000981"));
		r.setTaskproperties("feedback_people");

		rList.add(r);

		bulletinList = new ArrayList<Bulletin>();
		b = new Bulletin();
		b.setFid(new BigDecimal("400000981"));
		b.setCompid(new BigDecimal("400001036"));
		b.setLastmodifyuserid(new BigDecimal(user.getEmpCode().getId()));
		b.setCreateuserid(new Integer(user.getEmpCode().getId()));
		b.setBulletinid(new BigDecimal("32766"));
		b.setBulletinname("夏夏");
		b.setBulletinJobId("123");

		bulletinList.add(b);

		map = new HashMap<String, Object>();
		map.put("complaint", c);
		map.put("bulletinList", bulletinList);
		map.put("resultList", rList);
		map.put("sessionUser", user);
		map.put("processLimit", "10");
		map.put("feedbackLimit", "10");
		try {
			complaintManager.finishedComplaintProccess(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFid(new BigDecimal("400001036"));
		c.setProstatus(Constants.COMPLAINT_STATUS_UPGRADED_RETURNED);
		c.setFeedback(Constants.IF_FEED_BACK_YES);
		rList = new ArrayList<Result>();
		r = new Result();
		r.setComplainid(new BigDecimal("400001036"));
		r.setDealman(user.getEmpCode().getId());
		r.setDealmatters("无异议");
		r.setTaskpartmentid(new BigDecimal(user.getEmpCode().getDeptId()
				.getId().toString()));
		r.setDealType("");
		r.setFid(new BigDecimal("400000981"));
		r.setTaskproperties("feedback_people");

		rList.add(r);

		bulletinList = new ArrayList<Bulletin>();
		b = new Bulletin();
		b.setFid(new BigDecimal("400000981"));
		b.setCompid(new BigDecimal("400001036"));
		b.setLastmodifyuserid(new BigDecimal(user.getEmpCode().getId()));
		b.setCreateuserid(new Integer(user.getEmpCode().getId()));
		b.setBulletinid(new BigDecimal("32766"));
		b.setBulletinname("夏夏");
		b.setBulletinJobId("123");

		bulletinList.add(b);

		map = new HashMap<String, Object>();
		map.put("complaint", c);
		map.put("bulletinList", bulletinList);
		map.put("resultList", rList);
		map.put("sessionUser", user);
		map.put("processLimit", "10");
		map.put("feedbackLimit", "10");
		try {
			complaintManager.finishedComplaintProccess(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFid(new BigDecimal("400001036"));
		c.setProstatus(Constants.COMPLAINT_STATUS_UPGRADED_RETURNED);
		c.setFeedback(Constants.IF_FEED_BACK_YES);
		rList = new ArrayList<Result>();
		r = new Result();
		r.setComplainid(new BigDecimal("400001036"));
		r.setDealman(user.getEmpCode().getId());
		r.setDealmatters("无异议");
		r.setTaskpartmentid(new BigDecimal(user.getEmpCode().getDeptId()
				.getId().toString()));
		r.setDealType(Constants.COMPLAINT_PROCESSTYPE_EMPLOYEE);
		r.setFid(new BigDecimal("400000981"));
		r.setTaskproperties("duty_people");

		rList.add(r);

		bulletinList = new ArrayList<Bulletin>();
		b = new Bulletin();
		b.setFid(new BigDecimal("400000981"));
		b.setCompid(new BigDecimal("400001036"));
		b.setLastmodifyuserid(new BigDecimal(user.getEmpCode().getId()));
		b.setCreateuserid(new Integer(user.getEmpCode().getId()));
		b.setBulletinid(new BigDecimal("32766"));
		b.setBulletinname("夏夏");
		b.setBulletinJobId("123");

		bulletinList.add(b);

		map = new HashMap<String, Object>();
		map.put("complaint", c);
		map.put("bulletinList", bulletinList);
		map.put("resultList", rList);
		map.put("sessionUser", user);
		map.put("processLimit", "10");
		map.put("feedbackLimit", "10");
		try {
			complaintManager.finishedComplaintProccess(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFid(new BigDecimal("400001036"));
		c.setProstatus(Constants.COMPLAINT_STATUS_UPGRADED_RETURNED);
		c.setFeedback(Constants.IF_FEED_BACK_YES);
		rList = new ArrayList<Result>();
		r = new Result();
		r.setComplainid(new BigDecimal("400001036"));
		r.setDealman(user.getEmpCode().getId());
		r.setDealmatters("无异议");
		r.setTaskpartmentid(new BigDecimal(user.getEmpCode().getDeptId()
				.getId().toString()));
		r.setDealType(Constants.COMPLAINT_PROCESSTYPE_EMPLOYEE);
		r.setFid(new BigDecimal("400000981"));
		r.setTaskproperties("duty_feedback_people");

		rList.add(r);

		bulletinList = new ArrayList<Bulletin>();
		b = new Bulletin();
		b.setFid(new BigDecimal("400000981"));
		b.setCompid(new BigDecimal("400001036"));
		b.setLastmodifyuserid(new BigDecimal(user.getEmpCode().getId()));
		b.setCreateuserid(new Integer(user.getEmpCode().getId()));
		b.setBulletinid(new BigDecimal("32766"));
		b.setBulletinname("夏夏");
		b.setBulletinJobId("123");

		bulletinList.add(b);

		map = new HashMap<String, Object>();
		map.put("complaint", c);
		map.put("bulletinList", bulletinList);
		map.put("resultList", rList);
		map.put("sessionUser", user);
		map.put("processLimit", "10");
		map.put("feedbackLimit", "10");
		try {
			complaintManager.finishedComplaintProccess(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 运行前测试1次
	 */
	@Test
	public void testFinishedComplaintProccess1() {
		Complaint c = new Complaint();
		c.setFid(new BigDecimal("400159862"));
		c.setProstatus(Constants.COMPLAINT_STATUS_WAIT_APPROVAL);

		List<Result> rList = new ArrayList<Result>();
		Result r = new Result();
		r.setComplainid(new BigDecimal("400159862"));
		r.setDealman(user.getEmpCode().getId());
		r.setDealmatters("无异议");
		r.setTaskpartmentid(new BigDecimal(user.getEmpCode().getDeptId()
				.getId().toString()));
		r.setDealType("");
		r.setFid(new BigDecimal("400000981"));
		rList.add(r);

		List<Bulletin> bulletinList = new ArrayList<Bulletin>();
		Bulletin b = new Bulletin();
		b.setFid(new BigDecimal("400000981"));
		b.setCompid(new BigDecimal("400159862"));
		b.setLastmodifyuserid(new BigDecimal(user.getEmpCode().getId()));
		b.setCreateuserid(new Integer(user.getEmpCode().getId()));
		b.setBulletinid(new BigDecimal("32766"));
		b.setBulletinname("夏夏");
		b.setBulletinJobId("123");
		bulletinList.add(b);

		List<Result> deList = new ArrayList<Result>();
		Result dr = new Result();
		dr.setComplainid(new BigDecimal("400159862"));
		dr.setDealman(user.getEmpCode().getId());
		dr.setDealmatters("无异议");
		dr.setTaskpartmentid(new BigDecimal(user.getEmpCode().getDeptId()
				.getId().toString()));
		dr.setDealType("");
		dr.setFid(new BigDecimal("400000981"));
		deList.add(dr);
		try {
			complaintManager.finishedComplaintProccess(c, rList, deList,
					bulletinList, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFid(new BigDecimal("400158713"));
		c.setProstatus(Constants.COMPLAINT_STATUS_UPGRADED);
		c.setProcessltimeimit(new BigDecimal(100000000));

		rList = new ArrayList<Result>();
		r = new Result();
		r.setComplainid(new BigDecimal("400158713"));
		r.setDealman(user.getEmpCode().getId());
		r.setDealmatters("无异议");
		r.setTaskpartmentid(new BigDecimal(user.getEmpCode().getDeptId()
				.getId().toString()));
		r.setDealType("");
		r.setProcesstimelimit(new BigDecimal(1000000));
		r.setFid(new BigDecimal("400000981"));
		r.setFeedtimelimit(new BigDecimal(10000000));
		rList.add(r);

		bulletinList = new ArrayList<Bulletin>();
		b = new Bulletin();
		b.setFid(new BigDecimal("400000981"));
		b.setCompid(new BigDecimal("400158713"));
		b.setLastmodifyuserid(new BigDecimal(user.getEmpCode().getId()));
		b.setCreateuserid(new Integer(user.getEmpCode().getId()));
		b.setBulletinid(new BigDecimal("32766"));
		b.setBulletinname("夏夏");
		b.setBulletinJobId("123");
		bulletinList.add(b);

		deList = new ArrayList<Result>();
		dr = new Result();
		dr.setComplainid(new BigDecimal("400158713"));
		dr.setDealman(user.getEmpCode().getId());
		dr.setDealmatters("无异议");
		dr.setTaskpartmentid(new BigDecimal(user.getEmpCode().getDeptId()
				.getId().toString()));
		dr.setDealType("");
		dr.setFid(new BigDecimal("400000981"));
		deList.add(dr);
		try {
			complaintManager.finishedComplaintProccess(c, rList, deList,
					bulletinList, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		c = new Complaint();
		c.setFid(new BigDecimal("400001043"));
		c.setProstatus(Constants.COMPLAINT_STATUS_PENDING);
		c.setProcessltimeimit(new BigDecimal(100000000));

		rList = new ArrayList<Result>();
		r = new Result();
		r.setComplainid(new BigDecimal("400001043"));
		r.setDealman(user.getEmpCode().getId());
		r.setDealmatters("无异议");
		r.setTaskpartmentid(new BigDecimal(user.getEmpCode().getDeptId()
				.getId().toString()));
		r.setDealType("");
		r.setProcesstimelimit(new BigDecimal(1000000));
		r.setFid(new BigDecimal("400000989"));
		r.setFeedtimelimit(new BigDecimal(10000000));
		rList.add(r);

		bulletinList = new ArrayList<Bulletin>();
		b = new Bulletin();
		b.setFid(new BigDecimal("400001043"));
		b.setCompid(new BigDecimal("400158713"));
		b.setLastmodifyuserid(new BigDecimal(user.getEmpCode().getId()));
		b.setCreateuserid(new Integer(user.getEmpCode().getId()));
		b.setBulletinid(new BigDecimal("32766"));
		b.setBulletinname("夏夏");
		b.setBulletinJobId("123");
		bulletinList.add(b);

		deList = new ArrayList<Result>();
		dr = new Result();
		dr.setComplainid(new BigDecimal("400158713"));
		dr.setDealman(user.getEmpCode().getId());
		dr.setDealmatters("无异议");
		dr.setTaskpartmentid(new BigDecimal(user.getEmpCode().getDeptId()
				.getId().toString()));
		dr.setDealType("");
		dr.setFid(new BigDecimal("400000981"));
		deList.add(dr);
		try {
			complaintManager.finishedComplaintProccess(c, rList, deList,
					bulletinList, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testUpgradeComplaint() {
		Complaint c = new Complaint();
		c.setFid(new BigDecimal("400001036"));
		c.setProstatus(Constants.COMPLAINT_STATUS_UPGRADED_RETURNED);

		List<Result> rList = new ArrayList<Result>();
		Result r = new Result();
		r.setComplainid(new BigDecimal("400001036"));
		r.setDealman(user.getEmpCode().getId());
		r.setDealmatters("无异议");
		r.setFid(new BigDecimal("400000981"));
		r.setTaskpartmentid(new BigDecimal(user.getEmpCode().getDeptId()
				.getId().toString()));
		r.setDealType("");
		rList.add(r);

		List<Bulletin> bulletinList = new ArrayList<Bulletin>();
		Bulletin b = new Bulletin();
		b.setFid(new BigDecimal("400000981"));
		b.setCompid(new BigDecimal("400001036"));
		bulletinList.add(b);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("complaint", c);
		map.put("bulletinList", bulletinList);
		map.put("resultList", rList);
		map.put("sessionUser", user);
		map.put("processLimit", 10);
		map.put("feedbackLimit", 10);

		complaintManager.upgradeComplaint(map);

		try {
			complaintManager.upgradeComplaint(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			complaintManager.upgradeComplaint(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			map = new HashMap<String, Object>();
			map.put("complaint", null);
			complaintManager.upgradeComplaint(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 一次测试
	 */
	@Test
	public void testReturnSubmitor() {
		Complaint c = new Complaint();
		c.setFid(new BigDecimal("400001099"));
		c.setProstatus(Constants.COMPLAINT_STATUS_WAIT_APPROVAL);
		try {
			complaintManager.returnSubmitor(c, user, "aaaaaa");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void testGetPendingComplaintCount() {
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		condition.setProstatus(Constants.COMPLAINT_STATUS_PENDING);
		condition.setReportType("COMPLAINT");
		int count = complaintManager.getPendingComplaintCount(condition);
		boolean flag = false;
		if (count > 0) {
			flag = true;
		}
	}
	@Test
	public void testExpireComplaint() {
		complaintManager.expireComplaint();
	}
	@Test
	public void testIsOverTime() {
		Calendar now = Calendar.getInstance();
		now.set(Calendar.YEAR, 2012);
		now.set(Calendar.HOUR_OF_DAY, 25);
		now.set(Calendar.DAY_OF_MONTH, 5);
		now.set(Calendar.HOUR_OF_DAY, 5);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);

		Calendar deal = Calendar.getInstance();
		deal.set(Calendar.YEAR, 2012);
		deal.set(Calendar.HOUR_OF_DAY, 25);
		deal.set(Calendar.DAY_OF_MONTH, 5);
		deal.set(Calendar.HOUR_OF_DAY, 2);
		deal.set(Calendar.MINUTE, 0);
		deal.set(Calendar.SECOND, 0);
		deal.set(Calendar.MILLISECOND, 0);

		boolean over = ComplaintValidate.isOverTime(now.getTime(),
				deal.getTime(), new BigDecimal(2), "hour");
		assertTrue(over);

		now.set(Calendar.HOUR_OF_DAY, 3);
		over = ComplaintValidate.isOverTime(now.getTime(), deal.getTime(),
				new BigDecimal(2), "hour");
		assertFalse(over);

		now.set(Calendar.HOUR_OF_DAY, 3);

		over = ComplaintValidate.isOverTime(now.getTime(), null,
				new BigDecimal(2.5), "hour");
		assertFalse(over);
	}

	@Test
	public void testProcessComplaintMessage() {
		try {
			// 内有四个私有方法，数据量虽算不上太大，但也足够跑一段时间了
			complaintManager.processComplaintMessage();
		} catch (ComplaintException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testProcessSendComplaintInfo() {
		try {
			// 有点耗时间
			complaintManager.processSendComplaintInfo();
		} catch (ComplaintException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testProcesscellphoneMsg() {
		complaintManager.processcellphoneMsg();
	}

	@Test
	public void testGetSmsSender() {
		complaintManager.getSmsInfoSender();
	}

	@Test
	public void testGetCellphoneMessageInfoService() {
		complaintManager.getCellphoneMessageInfoService();
	}

	@Test
	public void testisComplaintFeedbackOverTime() {
		try {
			String resultId = "400001130";
			complaintManager.isComplaintFeedbackOverTime(resultId);
		} catch (Exception e) {
		}
	}
	@Test
	public void testSearchBasicInfo() {
		BasicSearchCondition bsc = new BasicSearchCondition();
		bsc.setBasicType(BasicInfoConstants.BASICCONTYPEBUSITEM);
		bsc.setBasicContent("业务");

		complaintManager.searchBasicInfo(bsc, 0, 10);
		bsc = new BasicSearchCondition();
		bsc.setBasicType(BasicInfoConstants.BASICCONTYPEBUSITEM);
		bsc.setBasicContent("业务sssssssssss");

		complaintManager.searchBasicInfo(bsc, 0, 10);

	}

	@Test
	public void testSearchBasicBusScopeVO() {
		BasicBusScopeVO bs = new BasicBusScopeVO();
		bs.setBusItemId("4");
		bs.setBusScopeId("401");

		complaintManager.searchBasicBusScopeVO(bs);
	}

	@Test
	public void testOperateBusItem() {
		List<BasicInfo> basicItems = new ArrayList<BasicInfo>();
		for (int i = 0; i < 3; i++) {
			BasicInfo bi = new BasicInfo();
			bi.setBusItemName("测试" + i);
			bi.setReportType(BasicInfoConstants.BASICCOMPTYPECOMPAINT);
			basicItems.add(bi);
		}
		BasicInfo bi = new BasicInfo();
		bi.setBusItemName("测试修改" + System.currentTimeMillis() / 100000000);
		bi.setReportType(BasicInfoConstants.BASICCOMPTYPECOMPAINT);
		bi.setBusItemId("800001373");
		basicItems.add(bi);
		User user = new User();
		user.setId("1234");
		complaintManager.operateBusItem(basicItems, user);
	}

	@Test
	public void testOperateBasicBusInfo() {
		BasicBusScopeVO bv = new BasicBusScopeVO();
		User user = new User();
		user.setId("111");
		try {
			complaintManager.operateBasicBusInfo(bv, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bv.setBusItemId("800000459");
		try {
			complaintManager.operateBasicBusInfo(bv, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			bv.setBusItemName("修改测试1");
			complaintManager.operateBasicBusInfo(bv, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			bv.setBusScopeName("新增业务范围测试1");
			complaintManager.operateBasicBusInfo(bv, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bv.setBusScopeId("");
		try {
			bv.setBusScopeName("新增业务范围测试1");
			complaintManager.operateBasicBusInfo(bv, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			bv.setBusScopeId("800000460");
			bv.setBusScopeName("修改业务范围测试1");
			complaintManager.operateBasicBusInfo(bv, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bv.setReportType(BasicInfoConstants.BASICCOMPTYPECOMPAINT);
		try {
			complaintManager.operateBasicBusInfo(bv, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<BasicBusType> bt = new ArrayList<BasicBusType>();
		BasicBusType b = new BasicBusType();
		b.setBusType("新增测试业务类型");
		b.setDealLanguage("测试测试测试测试测试测试");
		bt.add(b);
		bv.setBusTypes(bt);
		try {
			complaintManager.operateBasicBusInfo(bv, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		b.setFeedbackLimit("60");
		b.setProcLimit("24");
		try {
			complaintManager.operateBasicBusInfo(bv, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		b = new BasicBusType();
		b.setBusType("新增测试业务类型");
		b.setDealLanguage("测试测试测试测试测试测试");
		bt.add(b);
		bv.setBusTypes(bt);
		b.setFeedbackLimit("60");
		b.setProcLimit("24");
		b.setId("1234");
		bt.add(b);
		try {
			complaintManager.operateBasicBusInfo(bv, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteBusTypeByIds() {

		List<BasicInfo> basicInfo = new ArrayList<BasicInfo>();
		BasicInfo bi = new BasicInfo();
		bi.setBusScopeId("800000460");
		bi.setBusTypeId("800000400");
		basicInfo.add(bi);

		complaintManager.deleteBusTypeByIds(basicInfo);
	}

	@Test
	public void testDeleteBusTypeByIdsInBusScopeVO() {
		List<BasicBusType> busTypes = new ArrayList<BasicBusType>();
		BasicBusType bt = new BasicBusType();
		bt.setId("12345");
		busTypes.add(bt);
		bt = new BasicBusType();
		bt.setId("123456");
		busTypes.add(bt);
		bt = new BasicBusType();
		bt.setId("1234567");
		busTypes.add(bt);
		complaintManager.deleteBusTypeByIdsInBusScopeVO(busTypes);

	}

	@Test
	public void testDeleteBusItemByIds() {
		List<BasicInfo> basicInfo = new ArrayList<BasicInfo>();
		BasicInfo b = new BasicInfo();
		b.setBusItemId("800000461");
		basicInfo.add(b);
		b = new BasicInfo();
		b.setBusItemId("800000449");
		basicInfo.add(b);
		b = new BasicInfo();
		b.setBusItemId("800000448");
		basicInfo.add(b);
		b = new BasicInfo();
		b.setBusItemId("800000447");
		basicInfo.add(b);
		b = new BasicInfo();
		b.setBusItemId("800000446");
		basicInfo.add(b);
		b = new BasicInfo();
		b.setBusItemId("800000445");
		basicInfo.add(b);

		complaintManager.deleteBusItemByIds(basicInfo);
	}

	@Test
	public void testSearchBusItemByType() {
		complaintManager
				.searchBusItemByType(BasicInfoConstants.BASICCOMPTYPEUNUSUAL);
		complaintManager.searchBusItemByType(null);
	}

	@Test
	public void testShowBusItemByType() {
		complaintManager.showBusItemByType();

	}

	@Test
	public void testSearchBusScopeByBusItemId() {
		complaintManager.searchBusScopeByBusItemId("1234");
	}

	@Test
	public void testSearchBusTypeByBusScope() {
		complaintManager.searchBusTypeByBusScope("1234");
	}

	@Test
	public void testUpdateBusItemById() {
		BasicInfo basicInfo = new BasicInfo();
		User user = new User();
		user.setId("1234");
		try {
			complaintManager.updateBusItemById(basicInfo, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		basicInfo.setBusItemId("1234");
		basicInfo.setBusItemName("测测测");
		basicInfo.setReportType(BasicInfoConstants.BASICCOMPTYPECOMPAINT);
		complaintManager.updateBusItemById(basicInfo, user);
		complaintManager.getWorkOrderService();
		complaintManager.getResultService();
		complaintManager.getBasciLevelService();
		complaintManager.getBulletinService();
		complaintManager.getUserService();
	}

	@Test
	public void testgetAllEmployeesByDepartmentId() {
		String deptId = "106139";
		complaintManager.getAllEmployeesByDepartmentId(deptId);
	}

	@Test
	public void testSaveOrUpdateFoundationData() {
		List<ProcResult> procResultList = new ArrayList<ProcResult>();
		BasciLevel childBasciLevel = new BasciLevel();
		BigDecimal fBasciLevelId = new BigDecimal(106139);
		String reportType = "overdue";
		complaintManager.saveOrUpdateFoundationData(procResultList,
				childBasciLevel, fBasciLevelId, reportType, user);
		childBasciLevel.setFid(fBasciLevelId);
		complaintManager.saveOrUpdateFoundationData(procResultList,
				childBasciLevel, fBasciLevelId, reportType, user);
		childBasciLevel.setFid(new BigDecimal(0));
		complaintManager.saveOrUpdateFoundationData(procResultList,
				childBasciLevel, fBasciLevelId, reportType, user);
	}

	@Test
	public void testSearchReturnedRecordListByCompId() {
		BigDecimal compId = new BigDecimal(106139);
		complaintManager.searchReturnedRecordListByCompId(compId);
	}

	@Test
	public void testSearchProcessResultListByCompId() {
		BigDecimal compId = new BigDecimal(106139);
		complaintManager.searchProcessResultListByCompId(compId);
	}

	@Test
	public void testsearchFoundationData() {
		BigDecimal childId = new BigDecimal(106139);
		String reportType = "106139";
		complaintManager.searchFoundationData(childId, reportType);
	}

	@Test
	public void testsetUpParentBasciLevel() {
		complaintManager.setUpParentBasciLevel(null, null, user);
		List<BasciLevel> reportbasciLevel = new ArrayList<BasciLevel>();
		List<BasciLevel> exceptionBasciLevel = new ArrayList<BasciLevel>();
		BasciLevel b1 = new BasciLevel();
		BasciLevel b2 = new BasciLevel();
		complaintManager.setUpParentBasciLevel(reportbasciLevel,
				exceptionBasciLevel, user);
		reportbasciLevel.add(b1);
		exceptionBasciLevel.add(b2);
		complaintManager.setUpParentBasciLevel(reportbasciLevel,
				exceptionBasciLevel, user);
	}

	@Test
	public void testgetAllDepartment() {
		String deptName = "";
		complaintManager.getAllDepartment(null);
		complaintManager.getAllDepartment(deptName);
		deptName = "益阳营业部";
		complaintManager.getAllDepartment(deptName);
	}

	@Test
	public void testsearchExceptionLevelDataList() {
		BasciLevelSearchCondition condition = new BasciLevelSearchCondition();
		complaintManager.searchExceptionLevelDataList(condition);
		complaintManager.searchComplaintLevelDataList(condition);
	}

	@Test
	public void testSearchFeedBackReasionByCompId() {
		BigDecimal compId = new BigDecimal(106139);
		complaintManager.searchFeedBackReasionByCompId(compId);
	}

	@Test
	public void testGetChildBasciLevel() {
		complaintManager.getFBasciLevlel(null);
		complaintManager.getChildBasciLevel(null);
		BasciLevelSearchCondition condition = new BasciLevelSearchCondition();
		complaintManager.getChildBasciLevel(condition);
		complaintManager.getFBasciLevlel(condition);
		condition.setFeedBack("feedBack");
		complaintManager.getChildBasciLevel(condition);
		complaintManager.getFBasciLevlel(condition);
		condition.setFeedBack("1");
		complaintManager.getChildBasciLevel(condition);
		complaintManager.getFBasciLevlel(condition);
	}

	@Test
	public void testgetMostRecentlyComplaintByCustCode() {
		String condition = "710711";
		complaintManager.getMostRecentlyComplaintByCustCode(condition);
	}

	@Test
	public void testsearchBulletinToProcByCompId() {
		BigDecimal compId = new BigDecimal(106139);
		complaintManager.searchBulletinToProcByCompId(compId);
		complaintManager.searchBulletinByCompId(compId);
	}

	@Test
	public void testSubmitVerfiyComplaint() {
		BigDecimal fid1 = new BigDecimal(106139);
		BigDecimal fid2 = new BigDecimal(400001014);
		Complaint complaint = new Complaint();
		complaint.setFid(fid1);
		complaint.setBill("bill");
		user.getEmpCode().getDeptId().setId("346222");
		FeedBackReasion feedBackReasion = new FeedBackReasion();
		// 勾选最终反馈
		complaint.setFeedback(Constants.IF_FEED_BACK_YES);
		complaintManager
				.submitVerfiyComplaint(complaint, feedBackReasion, user);
		complaint.setFeedback(Constants.IF_FEED_BACK_NO);
		complaintManager
				.submitVerfiyComplaint(complaint, feedBackReasion, user);
		complaint.setFid(fid2);
		complaintManager
				.submitVerfiyComplaint(complaint, feedBackReasion, user);
	}

	@Test
	public void testSearchVerfiyComplaint() {
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		condition.setStart(new Integer(0));
		condition.setLimit(new Integer(2));
		boolean ifDedult = false;
		complaintManager.searchVerfiyComplaint(condition, user, ifDedult);
		ifDedult = true;
		complaintManager.searchVerfiyComplaint(condition, user, ifDedult);
	}
}
