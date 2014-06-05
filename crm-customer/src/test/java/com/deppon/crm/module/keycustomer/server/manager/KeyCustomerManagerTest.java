package com.deppon.crm.module.keycustomer.server.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.bps.server.manager.IBpsWorkflowManager;
import com.deppon.crm.module.customer.server.service.IAlterMemberService;
import com.deppon.crm.module.customer.server.service.IContactService;
import com.deppon.crm.module.customer.server.service.IContractService;
import com.deppon.crm.module.customer.server.service.IMemberService;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberExtend;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.keycustomer.server.manager.impl.KeyCustomerManager;
import com.deppon.crm.module.keycustomer.server.service.IKeyCustomerService;
import com.deppon.crm.module.keycustomer.shared.domain.Constant;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomer;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerSearchCondition;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerVO;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerWorkflowInfo;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.sysmail.server.manager.ISysMailSendManager;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.context.UserContext;

public class KeyCustomerManagerTest extends TestCase {
	// 大客户管理dao
	private static KeyCustomerManager keyCustomerManager = new KeyCustomerManager();

	@Before
	public void setUp() {
		sysMailSendManagerJMock();
		contactServiceJMock();
		memberServiceJMock();
		bpsWorkflowManagerJMock();
		contractServiceJMock();
		alterMemberServiceJMock();
		KeyCustomerServiceJMock();
		contextInit();
		Mockery repeatedCustManagerJMock = new Mockery();
		final IRepeatedCustManager repeatedCustManager = repeatedCustManagerJMock.mock(IRepeatedCustManager.class);
		repeatedCustManagerJMock.checking(new Expectations() {
			{
				allowing(repeatedCustManager).isCustExistsRepeat("1");
				will(returnValue(true));
			}
		});
		keyCustomerManager.setRepeatedCustManager(repeatedCustManager);

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
	 * Description:邮件发送service<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月28日 void
	 */
	public void sysMailSendManagerJMock() {
		Mockery sysMailSendManagerJMock = new Mockery();
		final ISysMailSendManager sysMailSendManager = sysMailSendManagerJMock.mock(ISysMailSendManager.class);
		sysMailSendManagerJMock.checking(new Expectations() {
			{
				allowing(sysMailSendManager).sendEmail(with(any(MailInfo.class)),with(any(String.class)));
			}
		});
		keyCustomerManager.setSysMailSendManager(sysMailSendManager);

	}

	/**
	 * 
	 * <p>
	 * Description:联系人service<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月28日 void
	 */
	public void contactServiceJMock() {
		Mockery contactServiceJMock = new Mockery();
		final IContactService contactService = contactServiceJMock.mock(IContactService.class);
		contactServiceJMock.checking(new Expectations() {
			{
				allowing(contactService).getContactsByMemberId("1");
				List<Contact> clist=new ArrayList<Contact>();
				Contact c=new Contact();
				c.setIsMainLinkMan(true);
				c.setName("1");
				c.setNumber("11");
				clist.add(c);
				will(returnValue(clist));
			}
		});
		keyCustomerManager.setContactService(contactService);

	}

	/**
	 * 
	 * <p>
	 * Description:会员service<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月28日 void
	 */
	public void memberServiceJMock() {
		Mockery memberServiceMockery = new Mockery();
		final IMemberService memberService = memberServiceMockery.mock(IMemberService.class);
		memberServiceMockery.checking(new Expectations() {
			{
				
			}
		});
		keyCustomerManager.setMemberService(memberService);

	}

	/**
	 * 
	 * <p>
	 * Description: bpsWorkflowManager<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月28日 void
	 */
	public void bpsWorkflowManagerJMock() {
		Mockery bpsWorkflowManagerMockery = new Mockery();
		final IBpsWorkflowManager bpsWorkflowManager = bpsWorkflowManagerMockery.mock(IBpsWorkflowManager.class);
		bpsWorkflowManagerMockery.checking(new Expectations() {
			{
				allowing(bpsWorkflowManager).createWorkflow(with(any(String.class)), with(any(String.class)), with(any(String.class)), with(any(String.class)));
				Map<String, String> map=new HashMap<String, String>();
				map.put("bizCode", "1");
				will(returnValue(map));
				allowing(bpsWorkflowManager).bizCode();
				will(returnValue("ICRM1"));

			}
		});
		keyCustomerManager.setBpsWorkflowManager(bpsWorkflowManager);

	}

	/**
	 * 
	 * <p>
	 * Description:合同service<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月28日 void
	 */
	public void contractServiceJMock() {
		Mockery contractServiceMockery = new Mockery();
		final IContractService contractService = contractServiceMockery.mock(IContractService.class);
		contractServiceMockery.checking(new Expectations() {
			{
				

				allowing(contractService).getArrearaMountByCustId("1");
				will(returnValue("11111\\10000\\10000"));
				
			}
		});
		keyCustomerManager.setContractService(contractService);

	}

	/**
	 * 
	 * <p>
	 * Description:会员修改service<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月28日 void
	 */
	public void alterMemberServiceJMock() {
		Mockery alterMemberServiceMockery = new Mockery();
		final IAlterMemberService alterMemberService = alterMemberServiceMockery.mock(IAlterMemberService.class);
		alterMemberServiceMockery.checking(new Expectations() {
			{
				MemberCondition c=new MemberCondition();
				c.setMemberId("1");
				allowing(alterMemberService).searchMemberByCondition(c);
				List<Member> list=new ArrayList<Member>();
				Member m=new Member();
				MemberExtend e=new MemberExtend();
				e.setCreditTimes(1+"");
				m.setIsKeyCustomer(true);
				m.setMemberExtend(e);
				list.add(m);
				will(returnValue(list));
				MemberCondition c1=new MemberCondition();
				c1.setMemberId("2");
				allowing(alterMemberService).searchMemberByCondition(c1);
				List<Member> list1=new ArrayList<Member>();
				Member m1=new Member();
				m1.setIsKeyCustomer(false);
				m1.setMemberExtend(e);
				list1.add(m1);
				will(returnValue(list1));
				allowing(alterMemberService).updateMember(with(any(Member.class)));
			}
		});
		keyCustomerManager.setAlterMemberService(alterMemberService);

	}

	/**
	 * 
	 * <p>
	 * Description:keycustomerservice mock<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月28日 void
	 */
	public void KeyCustomerServiceJMock() {
		Mockery KeyCustomerServiceMockery = new Mockery();
		final IKeyCustomerService keyCustomerService = KeyCustomerServiceMockery.mock(IKeyCustomerService.class);
		KeyCustomerServiceMockery.checking(new Expectations() {
			{	
				allowing(keyCustomerService).listKeyCustomerList(with(any(KeyCustomerSearchCondition.class)));
				List<KeyCustomer> list = new ArrayList<KeyCustomer>();
				KeyCustomer key=new KeyCustomer();
				key.setId("1");
				list.add(key);
				will(returnValue(list));
				allowing(keyCustomerService).searchWorkflowList(with(any(KeyCustomerSearchCondition.class)));
				List<KeyCustomerWorkflowInfo> infolist = new ArrayList<KeyCustomerWorkflowInfo>();
				will(returnValue(infolist));
				allowing(keyCustomerService).countSearchWorkflowList(with(any(KeyCustomerSearchCondition.class)));
				will(returnValue(1l));
				allowing(keyCustomerService).findWorkflowListByCustId(with(any(String.class)),with(any(Integer.class)),with(any(Integer.class)));
				will(returnValue(new ArrayList<KeyCustomerWorkflowInfo>()));
				
				allowing(keyCustomerService).findWorkflowListByCustId("1",0,0);
				List<KeyCustomerWorkflowInfo> info=  new ArrayList<KeyCustomerWorkflowInfo>();
				KeyCustomerWorkflowInfo k=new KeyCustomerWorkflowInfo();
				k.setStatus(Constant.WORKFLOW_STATUS_UNAGREE);
				info.add(k);
				will(returnValue(info));
				allowing(keyCustomerService).updateKeyListStatus("1","0");
				allowing(keyCustomerService).updateKeyListStatus("2","0");
				allowing(keyCustomerService).countListKeyCustomerList(with(any(KeyCustomerSearchCondition.class)));
				will(returnValue(1L));
				allowing(keyCustomerService).countFindWorkflowListByCustId(with(any(String.class)));
				will(returnValue(1L));
				allowing(keyCustomerService).findWorkflowInfoByBusino("111");
				KeyCustomerWorkflowInfo kinfo=new KeyCustomerWorkflowInfo();
				kinfo.setCustId("111");
				kinfo.setWorkFlowType(Constant.OUT);
				will(returnValue(kinfo));
				allowing(keyCustomerService).findWorkflowInfoByBusino("222");
				KeyCustomerWorkflowInfo kinfo1=new KeyCustomerWorkflowInfo();
				kinfo1.setCustId("222");
				kinfo1.setWorkFlowType(Constant.IN);
				will(returnValue(kinfo));
				allowing(keyCustomerService).findKeyCustomerListByCustId("1");
				KeyCustomer k1=new KeyCustomer();
				k1.setType(Constant.IN);
				List<KeyCustomer> list1=new ArrayList<KeyCustomer>();
				list1.add(k1);
				will(returnValue(list1));
				allowing(keyCustomerService).findKeyCustomerListByCustId("2");
				will(returnValue(new ArrayList<KeyCustomer>()));
				allowing(keyCustomerService).selectKeyCustomer("111");
				KeyCustomerVO keyCustomerVO = new KeyCustomerVO();
				will(returnValue(keyCustomerVO));
				allowing(keyCustomerService).selectKeyCustomer("222");
				will(returnValue(keyCustomerVO));
				allowing(keyCustomerService).updateKeyCustomerWorkflowInfo(with(any(KeyCustomerWorkflowInfo.class)));
				allowing(keyCustomerService).updateKeyCustomer(with(any(KeyCustomerVO.class)));
				allowing(keyCustomerService).callStoredProcedure(Constant.CREATEKEYOUTLIST);
				allowing(keyCustomerService).callStoredProcedure(Constant.CREATEKEYINLIST);
				allowing(keyCustomerService).saveWorkflowInfo(with(any(KeyCustomerWorkflowInfo.class)));
			}
		});
		keyCustomerManager.setKeyCustomerService(keyCustomerService);

	}

	@Test
	public void testlistKeyCustomerList() {
		try {

			keyCustomerManager.listKeyCustomerList(null);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		KeyCustomerSearchCondition condition = new KeyCustomerSearchCondition();
		try {
			keyCustomerManager.listKeyCustomerList(condition);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		condition.setBelongDept("1");
		keyCustomerManager.listKeyCustomerList(condition);
		condition.setContactNum("1");
		keyCustomerManager.listKeyCustomerList(condition);
		condition.setCustNum("1");
		keyCustomerManager.listKeyCustomerList(condition);
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户id查询客户近三个月发货金额<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 * @return String
	 */
	@Test
	public void testfindAmountOfSignMent() {
		keyCustomerManager.findAmountOfSignMent("");
		keyCustomerManager.findAmountOfSignMent("1");

	}

	/**
	 * 
	 * <p>
	 * Description:根据条件查询大客户的工作流历史<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param condtion
	 * @return List<KeyCustomerWorkflowInfo>
	 */
	public void testSearchWorkflowList() {
		try {
			
			keyCustomerManager.searchWorkflowList(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		KeyCustomerSearchCondition condition=new KeyCustomerSearchCondition();
		try {
			keyCustomerManager.searchWorkflowList(condition);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		condition.setBelongDept("1");
		keyCustomerManager.searchWorkflowList(condition);
		condition.setContactNum("1");
		keyCustomerManager.searchWorkflowList(condition);
		condition.setCustNum("1");
		keyCustomerManager.searchWorkflowList(condition);
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户id查询客户的审批历史<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 * @return List<KeyCustomerWorkflowInfo>
	 */
	public void testfindWorkflowListByCustId() {
		keyCustomerManager.findWorkflowListByCustId("", 1, 1);
		keyCustomerManager.findWorkflowListByCustId("1", 1, 1);

	}

	/**
	 * 
	 * <p>
	 * Description:根据客户id对大客户进行退出处理<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 *            void
	 */
	public void testexitHadlle() {
		try {
			keyCustomerManager.exitHadlle("1", "92974");
		} catch (Exception e) {
		}
	}

	/**
	 * 
	 * <p>
	 * Description:大客户保留工作流申请<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param info
	 *            void
	 * @return
	 */
	public void testprocessWorkFlow() {
		KeyCustomerWorkflowInfo info=new KeyCustomerWorkflowInfo();
		info.setIsSpecialKeyCustomer(true);
		info.setApplicationReason("111");
		info.setWorkFlowType("in");
		info.setDeptId("92974");
		info.setCustId("2");
		keyCustomerManager.processWorkFlow(info);

	};

	/**
	 * 
	 * <p>
	 * Description:系统自动删除大客户标记<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-6 void
	 */
	public void testautoDeleteKeyCustomerFlag() {
		keyCustomerManager.autoDeleteKeyCustomerFlag();
	}

	/**
	 * 
	 * <p>
	 * Description:系统自动生成待准入大客户列表-定时器使用<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-6 void
	 */
	public void testcreateKeyCustomerInList() {
		keyCustomerManager.createKeyCustomerInList();
	}

	/**
	 * 
	 * <p>
	 * Description:系统自动生成待准入大客户列表---定时器使用<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-6 void
	 */
	public void testCreateKeyCustomerOutList() {
		keyCustomerManager.createKeyCustomerOutList();
		keyCustomerManager.getAlterMemberService();
		keyCustomerManager.getBpsWorkflowManager();
		keyCustomerManager.getContactService();
		keyCustomerManager.getContractService();
		keyCustomerManager.getHeaders();
		keyCustomerManager.getKeyCustomerService();
		keyCustomerManager.getMemberService();
		keyCustomerManager.getSysMailSendManager();

	}

	/**
	 * 
	 * <p>
	 * Description:工作流审批调用方法<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-6 void
	 */
	public void testworkflowApprove() {
		keyCustomerManager.workflowApprove("111", true, "106138", new Date(), "1111");
		keyCustomerManager.workflowApprove("111", false, "106138", new Date(), "1111");
		keyCustomerManager.workflowApprove("222", false, "106138", new Date(), "1111");
		keyCustomerManager.workflowApprove("222", true, "106138", new Date(), "1111");

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-8
	 * @param custId
	 *            void
	 * @return
	 */
	public void testFindCustomerInfo() {
		keyCustomerManager.findCustomerInfo("1");

	}

	/**
	 * 
	 * <p>
	 * Description:校验是否满足条件<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-8
	 * @param custId
	 * @return KeyStatusVO
	 */
	public void testCheckKeyStatusVO() {
		keyCustomerManager.checkKeyStatusVO("1");

	}

	/**
	 * 
	 * <p>
	 * Description:根据工作流号查询工作流信息<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param bizCode
	 * @return KeyCustomerWorkflowInfo
	 */
	public void testFindWorkflowInfoByBusino() {
		keyCustomerManager.findWorkflowInfoByBusino("111");

	}

	/**
	 * 
	 * <p>
	 * Description:统计该客户的工作流历史记录条数<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param custId
	 * @return long
	 */
	public void testCountFindWorkflowListByCustId() {
		keyCustomerManager.countFindWorkflowListByCustId("1");

	}

	/**
	 * 
	 * <p>
	 * Description:按条件统计工作流历史记录条数<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param condition
	 * @return long
	 */
	public void testCountListKeyCustomerList() {
		KeyCustomerSearchCondition ss=new KeyCustomerSearchCondition();
		keyCustomerManager.countListKeyCustomerList(ss);

	}
	/**
	 * 
	 * <p>
	 * Description:按条件统计工作流历史记录条数<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param condition
	 * @return long
	 */
	public void testCountSearchWorkflowList() {
		KeyCustomerSearchCondition ss=new KeyCustomerSearchCondition();
		ss.setBelongDept("1");
		keyCustomerManager.countSearchWorkflowList(ss);

	}
	/**
	 * 
	 * <p>
	 * Description:按条件统计工作流历史记录条数<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param condition
	 * @return long
	 */
	public void testcheckKeyCustomerWorkflowInfo() {
		KeyCustomerSearchCondition ss=new KeyCustomerSearchCondition();
		ss.setBelongDept("1");
		
	}

}
