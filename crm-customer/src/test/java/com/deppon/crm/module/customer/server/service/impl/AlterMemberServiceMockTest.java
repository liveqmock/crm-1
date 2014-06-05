package com.deppon.crm.module.customer.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import javassist.expr.NewArray;

import org.ietf.jgss.Oid;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.customer.ICustomerOperate;
import com.deppon.crm.module.customer.server.dao.IAlterMemberDao;
import com.deppon.crm.module.customer.server.dao.IContactDao;
import com.deppon.crm.module.customer.server.dao.IMemberDao;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;

import junit.framework.TestCase;

public class AlterMemberServiceMockTest extends TestCase {

	public static AlterMemberService alterMemberService = new AlterMemberService();

	@Before
	public void setUp() {
		alterMemberDaoJMock();
		memberDaoJMock();
			try {
				customerOperateJMock();
			} catch (CrmBusinessException e) {
				e.printStackTrace();
			}
		
		contactDaoJMock();
	}

	public void alterMemberDaoJMock() {
		Mockery alterMemberDaoMockery = new Mockery();
		final IAlterMemberDao alterMemberDao = alterMemberDaoMockery
				.mock(IAlterMemberDao.class);
		alterMemberDaoMockery.checking(new Expectations() {
			{
				allowing(alterMemberDao).getContactByNum("1");
				will(returnValue(new Contact()));
				allowing(alterMemberDao).getMemberBylinkmanId(
						with(any(String.class)), with(any(String.class)));
				will(returnValue(new Member()));

				allowing(alterMemberDao).saveApproveData(with(any(List.class)));
				allowing(alterMemberDao).searchApproveData(
						with(any(String.class)));
				will(returnValue(new ArrayList<ApproveData>()));

				allowing(alterMemberDao).getApproveType(
						with(any(String.class)), with(any(String.class)));
				will(returnValue("1"));
				allowing(alterMemberDao).searchApproveData(
						with(any(String.class)));
				will(returnValue(new ArrayList<ApproveData>()));
				allowing(alterMemberDao).alterMemberStatus(
						with(any(String.class)), with(any(String.class)));
				will(returnValue(true));
				allowing(alterMemberDao).searchMember(
						with(any(MemberCondition.class)));
				will(returnValue(new ArrayList<MemberResult>()));
				allowing(alterMemberDao).searchMemberListFor360(
						with(any(MemberCondition.class)));
				will(returnValue(new ArrayList<MemberResult>()));
				allowing(alterMemberDao).searchMemberWithAuth(
						with(any(MemberCondition.class)));
				will(returnValue(new ArrayList<MemberResult>()));
				allowing(alterMemberDao).alterMemberStatus(
						with(any(String.class)), with(any(String.class)));
				will(returnValue(true));

				// allowing(alterMemberDao).updateMemberFinStatus(with(any(List.class)),with(any(Boolean.class)));
				allowing(alterMemberDao).queryCustIdByNumber(
						with(any(List.class)));
				will(returnValue(new ArrayList<String>()));
				allowing(alterMemberDao).invalidMemberFinStatus(
						with(any(List.class)), with(any(List.class)),
						with(any(Boolean.class)));
				allowing(alterMemberDao).queryMember4WorkflowById("1");
				will(returnValue(new Member()));
				allowing(alterMemberDao).queryInvalidCust();
				allowing(alterMemberDao).updateContactForCustCoordinates(
						with(any(Contact.class)));
				allowing(alterMemberDao).updatePreferenceAddress(
						with(any(PreferenceAddress.class)));
				allowing(alterMemberDao).getCustWorkflow(
						with(any(String.class)));
				will(returnValue(""));
				allowing(alterMemberDao).updateCustWorkflow(
						with(any(String.class)), with(any(String.class)));
				will(returnValue(true));
				allowing(alterMemberDao).insertCustWorkflow(
						with(any(String.class)), with(any(String.class)));
				allowing(alterMemberDao).updateMemberAllInfo(
						with(any(Member.class)));
				allowing(alterMemberDao).updateLinkmanCustType(
						with(any(Member.class)));
				allowing(alterMemberDao).getMember(with(any(String.class)));
				will(returnValue(new Member()));
				allowing(alterMemberDao).getAccount(with(any(String.class)));
				will(returnValue(new Account()));
				allowing(alterMemberDao).getContact(with(any(String.class)));
				Contact contact=new Contact();
				contact.setIsMainLinkMan(true);
				will(returnValue(contact));
				allowing(alterMemberDao).getShuttleAddress(
						with(any(String.class)));
				will(returnValue(new ShuttleAddress()));
				allowing(alterMemberDao).getPreferenceAddress(
						with(any(String.class)));
				will(returnValue(new PreferenceAddress()));
				allowing(alterMemberDao).updateShuttleAddressAllInfo(
						with(any(ShuttleAddress.class)));
				allowing(alterMemberDao).updatePreferenceAddressAllInfo(
						with(any(PreferenceAddress.class)));
				allowing(alterMemberDao).updateAccountAllInfo(
						with(any(Account.class)));
				allowing(alterMemberDao).alterContactStatus(
						with(any(String.class)), with(any(String.class)));
				allowing(alterMemberDao).updateMember(
						with(any(Member.class)));
				allowing(alterMemberDao).queryPreferenceAddressByContactId(
						with(any(String.class)));
				allowing(alterMemberDao).updateShuttleAddress(
						with(any(ShuttleAddress.class)));
				allowing(alterMemberDao).searchApproveDataByLogId(
						with(any(String.class)));
				allowing(alterMemberDao).updateMemberOperationLog(
						with(any(MemberOperationLog.class)));
				allowing(alterMemberDao).insertMemberOperationLog(
						with(any(MemberOperationLog.class)));
				allowing(alterMemberDao).searchMyWorkflowByCondition(
						with(any(WorkFlowCondition.class)),with(any(Integer.class)),with(any(Integer.class)));
				allowing(alterMemberDao).getPreferAddByContactId(
						with(any(String.class)),with(any(String.class)));
				allowing(alterMemberDao).updateMember(
						with(any(Member.class)));

			}

		});
		alterMemberService.setAlterMemberDao(alterMemberDao);
	}

	public void memberDaoJMock() {
		Mockery memberDaoMockery = new Mockery();
		final IMemberDao memberDao = memberDaoMockery.mock(IMemberDao.class);
		memberDaoMockery.checking(new Expectations() {
			{
				allowing(memberDao).saveShuttleAddress(
						with(any(ShuttleAddress.class)));
				allowing(memberDao).savePreferenceAddress(
						with(any(PreferenceAddress.class)));

			}

		});
		alterMemberService.setMemberDao(memberDao);
	}

	public void customerOperateJMock() throws CrmBusinessException {
		Mockery customerOperateMockery = new Mockery();
		final ICustomerOperate customerOperate = customerOperateMockery
				.mock(ICustomerOperate.class);
		customerOperateMockery.checking(new Expectations() {
			{
				allowing(customerOperate).bindToContact(with(any(String.class)),with(any(String.class)) );
			}

		});
		alterMemberService.setCustomerOperate(customerOperate);
	}
	public void contactDaoJMock()  {
		Mockery contactDaoMockery = new Mockery();
		final IContactDao contactDao = contactDaoMockery
				.mock(IContactDao.class);
		contactDaoMockery.checking(new Expectations() {
			{
				allowing(contactDao).unboundContactOnlineNum(with(any(String.class)));
			}

		});
		alterMemberService.setContactDao(contactDao);
	}
	@Test
	public void testUpdateMemberFinStatus() {
		alterMemberService.updateMemberFinStatus(null, true);
		alterMemberService.updateMemberFinStatus(new ArrayList<String>(), true);
		List<String> aList = new ArrayList<String>();
		aList.add("1");
		alterMemberService.updateMemberFinStatus(aList, true);

	}

	@Test
	public void testSearchMember4WorkflowById() {
		alterMemberService.searchMember4WorkflowById("1");

	}

	@Test
	public void testFindInvalidCustNumber() {
		alterMemberService.findInvalidCustNumber();
	}

	@Test
	public void testUpdateContactForCustCoordinates() {
		alterMemberService.updateContactForCustCoordinates(new Contact());
	}

	@Test
	public void testSavePreferenceAddress() {
		alterMemberService.savePreferenceAddress(new PreferenceAddress());
	}

	@Test
	public void testSaveShuttleAddress() {
		alterMemberService.saveShuttleAddress(new ShuttleAddress());
	}

	@Test
	public void testModifyPreferenceAddress() {
		alterMemberService.modifyPreferenceAddress(new PreferenceAddress());
	}

	@Test
	public void testGetCustWorkflow() {
		alterMemberService.getCustWorkflow("");
	}

	@Test
	public void testUpdateCustWorkflow() {
		alterMemberService.updateCustWorkflow("", "");
	}

	@Test
	public void testInsertCustWorkflow() {
		alterMemberService.insertCustWorkflow("", "");
	}

	@Test
	public void testGetContactByNum() {
		alterMemberService.getContactByNum("1");
	}

	@Test
	public void testGetMemberBylinkManId() {
		alterMemberService.getMemberBylinkmanId("1", "1");

	}

	@Test
	public void testSaveApproveData() {
		ApproveData ap = new ApproveData();
		List<ApproveData> approveDataList = new ArrayList<ApproveData>();
		approveDataList.add(ap);
		alterMemberService.saveApproveData(approveDataList, "1");

	}

	@Test
	public void testGetApproveType() {
		alterMemberService.getApproveType("1", "1");
	}

	@Test
	public void testSearchApproveData() {

		alterMemberService.searchApproveData("1");
	}

	@Test
	public void testAlterMemberStatus() {
		alterMemberService.alterMemberStatus("1", "1");

	}

	@Test
	public void testSearchMember() {
		alterMemberService.searchMember(new MemberCondition());

	}

	@Test
	public void testSearchMemberListFor360() {
		alterMemberService.searchMemberListFor360(new MemberCondition());

	}

	@Test
	public void testSearchMemberWithAuth() {
		alterMemberService.searchMemberWithAuth(new MemberCondition());

	}

	@Test
	public void testUpdateMemberInfo() {

		List<ApproveData> adList = new ArrayList<ApproveData>();
		alterMemberService.updateMemberInfo(adList, true);
		ApproveData ad1 = new ApproveData();
		ad1.setClassName(Contact.class.getSimpleName());
		ad1.setHandleType(Constant.APPROVEDATA_INSERT);
		ApproveData ad2 = new ApproveData();
		ad2.setHandleType(Constant.APPROVEDATA_DELETE);
		ad2.setClassName(Contact.class.getSimpleName());
		ad2.setClassId("1");

		ApproveData ad31 = new ApproveData();
		ApproveData ad32 = new ApproveData();
		ApproveData ad33 = new ApproveData();
		ApproveData ad34 = new ApproveData();
		ApproveData ad35 = new ApproveData();
		ad31.setHandleType(Constant.APPROVEDATA_UPDATE);
		ad31.setClassName("Member");
		ad31.setFieldName("custType");
		ad32.setHandleType(Constant.APPROVEDATA_UPDATE);
		ad32.setClassName("Contact");
		ad32.setFieldName("isMainLinkMan");
		ad33.setHandleType(Constant.APPROVEDATA_UPDATE);
		ad33.setClassName("Account");
		ad33.setFieldName("id");
		ad34.setHandleType(Constant.APPROVEDATA_UPDATE);
		ad34.setClassName("ShuttleAddress");
		ad34.setFieldName("id");

		ad35.setHandleType(Constant.APPROVEDATA_UPDATE);
		ad35.setClassName("PreferenceAddress");
		ad35.setFieldName("id");

		adList.add(ad31);
		adList.add(ad33);
		adList.add(ad34);
		adList.add(ad35);
		adList.add(ad2);
		adList.add(ad1);
//		alterMemberService.updateMemberInfo(adList, true);
	}
	@Test
	public void testQueryPreferenceAddrByContactId(){
		alterMemberService.queryPreferenceAddrByContactId("1");
		
	}
	@Test
	public void testModifyMemberShuttleAddress(){
		alterMemberService.modifyMemberShuttleAddress(new ShuttleAddress());
		
	}
	@Test
	public void testSaveMemberOperationLog(){
		alterMemberService.saveMemberOperationLog(new MemberOperationLog());
		
	}
	@Test
	public void testUpdateMemberOperationLog(){
		alterMemberService.updateMemberOperationLog(new MemberOperationLog());
		
	}
	@Test
	public void testSearchMyWorkflowByCondition(){
		WorkFlowCondition w=	new WorkFlowCondition();
		w.setDeptId(1111);
		alterMemberService.searchMyWorkflowByCondition( w,1,1);
		
	}
	@Test
	public void testsearchPreferAddByContactId(){
		alterMemberService.searchPreferAddByContactId("1","1");
		
	}
	@Test
	public void testUpdateMember(){
		alterMemberService.updateMember(new Member());
		
	}
}
