package com.deppon.crm.module.customer.server.manager.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.manager.ICustLabelManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.server.manager.IMemberWorkFlowManager;
import com.deppon.crm.module.customer.server.service.IAlterMemberService;
import com.deppon.crm.module.customer.server.service.IApprovingWokflowDataService;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.Member4All;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

public class AlterMemberManagerMockTest extends TestCase {
	public static AlterMemberManager alterMemberManager = new AlterMemberManager();

	@Before
	public void setUp() {
		alterMemberServiceJMock();
		memberManagerJMock();
		MemberWorkFlowManagerJMock();
		custLabelManagerJMock();
		approvingWokflowDataServiceJMock();
		contextInit();

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-10-26 void
	 */
	public void contextInit() {
		User user = new User();
		Department depart = new Department();
		depart.setId("92974");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);

	}

	/**
	 * 
	 * <p>
	 * Description:修改会员mock<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-10-26 void
	 */
	public void alterMemberServiceJMock() {
		Mockery alterMemberServiceMockery = new Mockery();
		final IAlterMemberService alterMemberService = alterMemberServiceMockery
				.mock(IAlterMemberService.class);
		final Contact contact = new Contact();
		alterMemberServiceMockery.checking(new Expectations() {
			{
				allowing(alterMemberService).getContactByNum("1");
				will(returnValue(contact));
				allowing(alterMemberService).queryMember4AllById("1");
				will(returnValue(null));
				allowing(alterMemberService).queryMember4AllById("2");
				Member4All member4All = new Member4All();
				will(returnValue(member4All));
				allowing(alterMemberService).queryMember4AllById("2");
				member4All.setVersionNumber(2);
				will(returnValue(member4All));
				allowing(alterMemberService).queryMember4AllById("3");
				member4All.setVersionNumber(3);
				will(returnValue(member4All));
				allowing(alterMemberService).saveOldValueToApproveData(
						with(any(List.class)),with(any(Member.class)));
				allowing(alterMemberService).savePojoInfo(with(any(Map.class)),
						with(any(List.class)), with(any(Member.class)),
						with(any(String.class)));
				allowing(alterMemberService).changeCancelDataToApproveList(
						with(any(List.class)), with(any(List.class)));
				allowing(alterMemberService).changeCancelDataToApproveList(
						with(any(List.class)), with(any(List.class)));
				allowing(alterMemberService).getApproveType("1", "1");
				will(returnValue(""));
				allowing(alterMemberService).getApproveType("1", "2");
				will(returnValue("baseDateType"));
				allowing(alterMemberService).getApproveType("Member",
						"taxregNumber");
				will(returnValue("baseDateType"));
				allowing(alterMemberService).getApproveType("Member", "number");
				will(returnValue("baseDateType"));
				allowing(alterMemberService).getApproveType("Member",
						"mobilePhone");
				will(returnValue("baseDateType"));
				allowing(alterMemberService).getApproveType("Member",
						"telPhone");
				will(returnValue("baseDateType"));
				allowing(alterMemberService).getApproveType("Member", "name");
				will(returnValue("baseDateType"));
				allowing(alterMemberService)
						.getApproveType("Contact", "number");
				will(returnValue("baseDateType"));
				allowing(alterMemberService).getApproveType("Contact",
						"mobilePhone");
				will(returnValue("baseDateType"));
				allowing(alterMemberService).getApproveType("Contact",
						"telPhone");
				will(returnValue("baseDateType"));
				allowing(alterMemberService).getApproveType("Contact", "name");
				will(returnValue("baseDateType"));
				allowing(alterMemberService)
						.getApproveType("Contact", "idCard");
				will(returnValue("baseDateType"));
				allowing(alterMemberService).getContact("1");
				will(returnValue(null));
				allowing(alterMemberService).getInvalidMemberAllById("1");
				will(returnValue(null));
				allowing(alterMemberService).getInvalidMemberAllById("2");
				Member member = new Member();
				member.setFinOver(true);
				will(returnValue(member));

				allowing(alterMemberService).updateMemberInfo(
						with(any(List.class)), with(any(Boolean.class)));
				allowing(alterMemberService).saveMemberOperationLog(
						with(any(MemberOperationLog.class)));
				allowing(alterMemberService).saveApproveData(
						with(any(List.class)), with(any(String.class)));
				allowing(alterMemberService).alterMemberStatus("1", "1");

			}
		});
		alterMemberManager.setAlterMemberService(alterMemberService);

	}

	/**
	 * 
	 * <p>
	 * Description:会员managermock<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-10-26 void
	 */
	public void memberManagerJMock() {
		Mockery memberManagerMockery = new Mockery();
		final IMemberManager memberManager = memberManagerMockery
				.mock(IMemberManager.class);
		memberManagerMockery.checking(new Expectations() {
			{
				allowing(memberManager).isContractMember("1");
				will(returnValue(true));
				allowing(memberManager).isContractMember("2");
				will(returnValue(false));
			}
		});
		alterMemberManager.setMemberManager(memberManager);

	}

	/**
	 * 
	 * <p>
	 * Description:会员managermock<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-10-26 void
	 */
	public void MemberWorkFlowManagerJMock() {
		Mockery memberWorkFlowManagerMockery = new Mockery();
		final IMemberWorkFlowManager memberWorkFlowManager = memberWorkFlowManagerMockery
				.mock(IMemberWorkFlowManager.class);
		memberWorkFlowManagerMockery.checking(new Expectations() {
			{

				allowing(memberWorkFlowManager).createNewModifyMemberWorkFlow(
						with(any(Set.class)), with(any(String.class)),with(any(List.class)));
				will(returnValue(1L));

			}
		});
		alterMemberManager.setMemberWorkFlowManager(memberWorkFlowManager);

	}

	/**
	 * 
	 * <p>
	 * Description:客户标签mock<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-10-30 void
	 */
	public void custLabelManagerJMock() {
		Mockery custLabelManagerMockery = new Mockery();
		final ICustLabelManager custLabelManager = custLabelManagerMockery
				.mock(ICustLabelManager.class);
		custLabelManagerMockery.checking(new Expectations() {
			{

				allowing(custLabelManager).updateCustLabel(
						with(any(List.class)), with(any(String.class)),
						with(any(String.class)), with(any(String.class)));

			}
		});
		alterMemberManager.setCustLabelManager(custLabelManager);

	}

	public void approvingWokflowDataServiceJMock() {

		Mockery approvingWokflowDataServiceMockery = new Mockery();
		final IApprovingWokflowDataService approvingWokflowDataService = approvingWokflowDataServiceMockery
				.mock(IApprovingWokflowDataService.class);
		approvingWokflowDataServiceMockery.checking(new Expectations() {
			{

				allowing(approvingWokflowDataService)
						.batchCreateContactWorkflowData(with(any(Set.class)));

			}
		});
		alterMemberManager
				.setApprovingWokflowDataService(approvingWokflowDataService);

	}

	/**
	 * 
	 * <p>
	 * Description:根据联系人编码查询联系人信息<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-10-26 void
	 */
	@Test
	public void testGetContactByNum() {
		alterMemberManager.getContactByNum("1");

	}

	/**
	 * 
	 * <p>
	 * Description:会员操作日志测试<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-10-26 void
	 */
	@Test
	public void testCreateLog() {
		String memberId = "";
		MemberOperationLog memberOperationLog = null;
		try {
			Method method = alterMemberManager.getClass().getDeclaredMethod(
					"createLog", String.class,String.class);
			memberId = "9527";
			method.setAccessible(true);
			memberOperationLog = (MemberOperationLog) method.invoke(
					alterMemberManager, memberId,null);
			Assert.assertNotNull(memberOperationLog);
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e2) {
			e2.printStackTrace();
		} catch (IllegalAccessException e3) {
			e3.printStackTrace();
		} catch (InvocationTargetException e4) {
			e4.printStackTrace();
		}

	}

//	@Test
//	public void testAlterMember() {
//		ApproveData approveData = new ApproveData();
//		List<ApproveData> updateDataAllList = new ArrayList<ApproveData>();
//		List<ApproveData> deleteDataList = new ArrayList<ApproveData>();
//		String memberId = "1";
//		Member member = new Member();
//		alterMemberManager.alterMember(null, null, null, memberId, member);
//		alterMemberManager.alterMember(updateDataAllList, null, null, memberId,
//				member);
//		Map<String, List> addPojoMap = new HashMap<String, List>();
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap, null,
//				memberId, member);
//
//		/**
//		 * 随便场景测试
//		 */
//
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		approveData.setClassName("1");
//		approveData.setFieldName("2");
//		approveData.setHandleType(2);
//		updateDataAllList.add(approveData);
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		deleteDataList.add(approveData);
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		addPojoMap.put("1", new ArrayList<String>());
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		approveData.setClassName("Member");
//		approveData.setFieldName("taxregNumber");
//		approveData.setNewValue("1");
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		memberId = "2";
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		memberId = "1";
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		approveData.setHandleType(1);
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		approveData.setHandleType(2);
//
//		approveData.setFieldName("number");
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		approveData.setFieldName("mobilePhone");
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		approveData.setFieldName("telPhone");
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		approveData.setFieldName("name");
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		approveData.setFieldName("number");
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		approveData.setFieldName("number");
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		approveData.setClassName("Contact");
//		approveData.setHandleType(1);
//		approveData.setClassId("1");
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		approveData.setFieldName("mobilePhone");
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		approveData.setFieldName("telPhone");
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		approveData.setFieldName("name");
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//		approveData.setFieldName("idCard");
//		alterMemberManager.alterMember(updateDataAllList, addPojoMap,
//				deleteDataList, memberId, member);
//
//	}

	@Test
	public void testGetInvalidMemberAllById() {
		String a="1111111111111111,";
		System.err.println(a.substring(0, a.length()-1));
		MemberCondition searchCustCondition = new MemberCondition();
		searchCustCondition.setMemberId("1");
		try {
			alterMemberManager.getInvalidMemberAllById(searchCustCondition);

		} catch (Exception e) {
		}
		searchCustCondition.setCustStatus((Constant.Lose));
		searchCustCondition.setMemberId("2");
		try {
			alterMemberManager.getInvalidMemberAllById(searchCustCondition);

		} catch (Exception e) {
		}

	}

}
