package com.deppon.crm.module.bpsworkflow.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.deppon.bpms.module.shared.domain.BpmsParticipant;
import com.deppon.bpms.module.shared.domain.WorkFlowInfo;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.bps.server.manager.IBpsWorkflowManager;
import com.deppon.crm.module.bps.shared.domain.BpsWorkflowCondition;
import com.deppon.crm.module.bps.shared.domain.WorkflowApprove;
import com.deppon.crm.module.bpsworkflow.server.manager.ICRMWorkflowManager;
import com.deppon.crm.module.bpsworkflow.server.manager.impl.CRMWorkflowManager;
import com.deppon.crm.module.bpsworkflow.server.manager.impl.WorkflowApproveOperate;
import com.deppon.crm.module.bpsworkflow.utils.TestUtil;
import com.deppon.crm.module.customer.bpsworkflow.ContractApproveOperate;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.keycustomer.server.manager.IKeyCustomerManager;
import com.deppon.crm.module.marketing.server.manager.IMarketActivityManager;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.servicerecovery.server.manager.IServiceRecoveryManager;
import com.deppon.crm.module.workflow.server.manager.INormalClaimManager;
import com.deppon.crm.module.workflow.server.manager.impl.NormalClaimManagerImpl;
import com.deppon.foss.framework.server.context.UserContext;

import junit.framework.TestCase;

public class CRMWrokflowManagerTest extends TestCase{

	private static ICRMWorkflowManager crmWorkflowManager;
	private static WorkflowApproveOperate wao = new WorkflowApproveOperate();
	private static CRMWorkflowManager workflowManagerForApprove = new CRMWorkflowManager();
	private static 	NormalClaimManagerImpl nmi = new NormalClaimManagerImpl();
	private static ContractApproveOperate cao = new ContractApproveOperate();
	static{
		crmWorkflowManager = TestUtil.crmWorkflowManager;
		User user = new User();
		Employee e= new Employee();
		e.setEmpCode("084544");
		e.setId("324465");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
	}
	@Before
	public void setUp(){
		keyCustomerManagerJMock();
//		contractApproveOperateJMock();
//		normalClaimManagerJMock();
		bpsWorkflowManagerJMock();
		repeatedCustManagerJMock();
		marketActivityManagerJMock();
		contractManagerJMock();
		serviceRecoveryManagerJMock();
		recompenseManagerJMock();
//		workflowApproveOperateJMock();
	}
	
//	public void workflowApproveOperateJMock(){
//		Mockery workflowApproveOperateJMock = new Mockery();
//		final WorkflowApproveOperate workflowApproveOperate = workflowApproveOperateJMock.mock(WorkflowApproveOperate.class);
//		workflowApproveOperateJMock.checking(new Expectations(){
//			{
//				allowing(workflowApproveOperate).workflowApprove(with(any(WorkflowApprove.class)));
//				allowing(workflowApproveOperate).oneKeyForApprove(with(any(WorkflowApprove.class)));
//			}
//		});
//		workflowManagerForApprove.setWorkflowApproveOperate(workflowApproveOperate);
//	}
	
	public void keyCustomerManagerJMock(){
		Mockery keyCustomerManagerJMock = new Mockery();
		final IKeyCustomerManager keyCustomerManager = keyCustomerManagerJMock.mock(IKeyCustomerManager.class);
		keyCustomerManagerJMock.checking(new Expectations(){
			{
				allowing(keyCustomerManager).workflowApprove(
						with(any(String.class)),with(any(Boolean.class)), with(any(String.class)), with(any(Date.class)), with(any(String.class)));
			}
		});
		wao.setKeyCustomerManager(keyCustomerManager);
	}
	
//	public void contractApproveOperateJMock(){
//		Mockery contractApproveOperateJMock = new Mockery();
//		final ContractApproveOperate contractApproveOperate = contractApproveOperateJMock.mock(ContractApproveOperate.class);
//		contractApproveOperateJMock.checking(new Expectations(){
//			{
//				allowing(contractApproveOperate).contractApprove(with(any(String.class)), with(any(Long.class)), with(any(Long.class)), with(any(Boolean.class)), with(any(String.class)), with(any(String.class)), with(any(Date.class)));
//			}
//		});
//		wao.setContractApproveOperate(contractApproveOperate);
//	}
	
//	public void normalClaimManagerJMock(){
//		Mockery normalClaimManagerJMock = new Mockery();
//		final INormalClaimManager normalClaimManager = normalClaimManagerJMock.mock(INormalClaimManager.class);
//		normalClaimManagerJMock.checking(new Expectations(){
//			{
//				allowing(normalClaimManager).workflowApprove(with(any(Long.class)), with(any(Long.class)), with(any(String.class)), with(any(String.class)), with(any(BpmsParticipant[].class)), with(any(String.class)));
//			}
//		});
//		wao.setNormalClaimManager((NormalClaimManagerImpl)normalClaimManager);
//	}
	
	public void bpsWorkflowManagerJMock(){
		Mockery bpsWorkflowManagerJMock = new Mockery();
		final IBpsWorkflowManager bpsWorkflowManager = bpsWorkflowManagerJMock.mock(IBpsWorkflowManager.class);
		bpsWorkflowManagerJMock.checking(new Expectations(){
			{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sucess", true);
				map.put("isEnd", true);
				map.put("isDisAgree", true);
				List<WorkFlowInfo> wfList = new ArrayList<WorkFlowInfo>();
				WorkFlowInfo wf= new WorkFlowInfo();
				wfList.add(wf);
				allowing(bpsWorkflowManager).workflowApprove(with(any(Long.class)), with(any(Long.class)), with(any(String.class)), with(any(String.class)),  with(any(BpmsParticipant[].class)));
				will(returnValue(map));
				allowing(bpsWorkflowManager).queryMyWorkFlow(with(any(Map.class)));
				will(returnValue(wfList));
			}
		});
		wao.setBpsWorkflowManager(bpsWorkflowManager);
		cao.setBpsWorkflowManager(bpsWorkflowManager);
	}
	
	public void repeatedCustManagerJMock(){
		Mockery repeatedCustManagerJMock = new Mockery();
		final IRepeatedCustManager repeatedCustManager = repeatedCustManagerJMock.mock(IRepeatedCustManager.class);
		repeatedCustManagerJMock.checking(new Expectations(){
			{
				allowing(repeatedCustManager).workflowApprove(
						with(any(String.class)),with(any(Boolean.class)), with(any(String.class)), with(any(Date.class)), with(any(String.class)));
			}
		});
		wao.setRepeatedCustManager(repeatedCustManager);
	}
	
	public void marketActivityManagerJMock(){
		Mockery marketActivityManagerJMock = new Mockery();
		final IMarketActivityManager marketActivityManager = marketActivityManagerJMock.mock(IMarketActivityManager.class);
		marketActivityManagerJMock.checking(new Expectations(){
			{
				allowing(marketActivityManager).workflowApprove(
						with(any(String.class)),with(any(Boolean.class)), with(any(String.class)), with(any(Date.class)), with(any(String.class)),with(any(User.class)));
			}
		});
		wao.setMarketActivityManager(marketActivityManager);
	}
	
	public void contractManagerJMock(){
		Mockery contractManagerJMock = new Mockery();
		final IContractManager contractManager = contractManagerJMock.mock(IContractManager.class);
		contractManagerJMock.checking(new Expectations(){
			{
				allowing(contractManager).contractApprove(with(any(String.class)), with(any(Boolean.class)), with(any(String.class)), with(any(Date.class)));
			}
		});
		wao.setContractManager(contractManager);
		cao.setContractManager(contractManager);
	}
	
	public void serviceRecoveryManagerJMock(){
		Mockery serviceRecoveryManagerJMock = new Mockery();
		final IServiceRecoveryManager serviceRecoveryManager = serviceRecoveryManagerJMock.mock(IServiceRecoveryManager.class);
		serviceRecoveryManagerJMock.checking(new Expectations(){
			{
				allowing(serviceRecoveryManager).returnServiceRecoveryStatus(with(any(String.class)), with(any(String.class)),  with(any(Boolean.class)), with(any(Date.class)), with(any(String.class)));
			}
		});
		wao.setServiceRecoveryManager(serviceRecoveryManager);
	}
	
	public void recompenseManagerJMock(){
		Mockery recompenseManagerJMock = new Mockery();
		final RecompenseManager recompenseManager = recompenseManagerJMock.mock(RecompenseManager.class);
		recompenseManagerJMock.checking(new Expectations(){
			{
				allowing(recompenseManager).oaOverpayApprove(with(any(String.class)), with(any(User.class)), with(any(String.class)), with(any(Date.class)));
				allowing(recompenseManager).oaOverpayRefuse(with(any(String.class)), with(any(User.class)), with(any(String.class)), with(any(Date.class)));
				allowing(recompenseManager).oaNormalApprove(with(any(String.class)), with(any(User.class)), with(any(String.class)), with(any(Date.class)));
				allowing(recompenseManager).oaNormalRefuse(with(any(String.class)), with(any(User.class)), with(any(String.class)), with(any(Date.class)));
			}
		});
		wao.setRecompenseManager(recompenseManager);
		nmi.setRecompenseManager(recompenseManager);
	}
	
	@Test
	public void testFindToHandleWorkflow(){
		BpsWorkflowCondition workflowCondition = new BpsWorkflowCondition();
		workflowCondition.setType(null);
		workflowCondition.setApplerId("324465");
		workflowCondition.setDeptId("49311");
		Date endDate = new Date();
		workflowCondition.setEndTime(endDate);
		workflowCondition.setFstandardcode("DP02076");
		Date startDate = new Date();
		startDate.setTime((endDate.getTime() - 2592000l));
		workflowCondition.setStartTime(startDate);
		workflowCondition.setLimit(20);
		workflowCondition.setStart(1);
		crmWorkflowManager.findToHandleWorkflow(workflowCondition);
	}
	@Test
	public void testFindHandledWorkflow(){
		BpsWorkflowCondition workflowCondition = new BpsWorkflowCondition();
		workflowCondition.setType(null);
		workflowCondition.setApplerId("324465");
		workflowCondition.setDeptId("49311");
		Date endDate = new Date();
		workflowCondition.setEndTime(endDate);
		workflowCondition.setFstandardcode("DP02076");
		Date startDate = new Date();
		startDate.setTime((endDate.getTime() - 2592000l));
		workflowCondition.setStartTime(startDate);
		workflowCondition.setLimit(20);
		workflowCondition.setStart(1);
		crmWorkflowManager.findHandledWorkflow(workflowCondition);
	}
	@Test
	public void testFindWorkflow(){
		BpsWorkflowCondition workflowCondition = new BpsWorkflowCondition();
		workflowCondition.setType(null);
		workflowCondition.setApplerId("324465");
		workflowCondition.setDeptId("49311");
		Date endDate = new Date();
		workflowCondition.setEndTime(endDate);
		workflowCondition.setFstandardcode("DP02076");
		Date startDate = new Date();
		startDate.setTime((endDate.getTime() - 2592000l));
		workflowCondition.setStartTime(startDate);
		workflowCondition.setLimit(20);
		workflowCondition.setStart(1);
		crmWorkflowManager.findWorkflow(workflowCondition);
	}
	@Test
	public void testQueryApprovalInfoByProcessInstID(){
		Long proId = 13495122l;
		crmWorkflowManager.queryApprovalInfoByProcessInstID(proId);
	}
	
	@Test
	public void testGetCurrentApproval(){
		Long proId = 13495122l;
		crmWorkflowManager.getCurrentApproval(proId);
	}
	
	@Test
	public void testFindContractWorkflowInfoByWorkNo(){
		String workItemId = "ICRM201312280000198";
		String processType = "";
		crmWorkflowManager.findContractWorkflowInfoByWorkNo(workItemId, processType);
		
	}
	@Test
	public void testFindBigCustomerWorkflowInfoByWorkNo(){
		String workItemId = "ICRM201403170000592";
		String processType = "";
		crmWorkflowManager.findBigCustomerWorkflowInfoByWorkNo(workItemId, processType);
	}
	@Test
	public void testFindRepetitiveCustWorkFlowInfoByWorkNo(){
		String workItemId = "ICRM201403250000635";
		String processType = "";
		crmWorkflowManager.findRepetitiveCustWorkFlowInfoByWorkNo(workItemId, processType);
	}
	@Test
	public void testFindMarketActivityInfoByWorkNum(){
		String workItemId = "ICRM201403250000635";
		String processType = "";
		crmWorkflowManager.findMarketActivityInfoByWorkNum(workItemId, processType);
	}
	@Test
	public void testGetNormalClaim(){
		Long processInstId = 13495122l;
		crmWorkflowManager.getNormalClaim(processInstId);
	}
	@Test
	public void testGetDeptChargeByProcessinstId(){
		Long processInstId = 13495122l;
		crmWorkflowManager.getDeptChargeByProcessinstId(processInstId);
	}
	@Test
	public void testGetIssueItemByProcessinstId(){
		Long processInstId = 13495122l;
		crmWorkflowManager.getIssueItemByProcessinstId(processInstId);
	}
	@Test
	public void testGetAwardItemByProcessinstId(){
		Long processInstId = 13495122l;
		crmWorkflowManager.getAwardItemByProcessinstId(processInstId);
	}
	@Test
	public void testGetResponsibleDeptByProcessinstId(){
		Long processInstId = 13495122l;
		crmWorkflowManager.getResponsibleDeptByProcessinstId(processInstId);
	}
	@Test
	public void testGetDetailOverpayByWorkNumber(){
		String workItemId = "ICRM201403250000635";
		crmWorkflowManager.getDetailOverpayByWorkNumber(workItemId);
	}
	@Test
	public void testGetOverpayFileList(){
		Long processInstId = 13495122l;
		crmWorkflowManager.getOverpayFileList(processInstId.toString());
	}
	@Test
	public void testGetServiceRecoveryByOaWorkflowNum(){
		Long processInstId = 13495122l;
		crmWorkflowManager.getServiceRecoveryByOaWorkflowNum(processInstId.toString());
	}
	@Test
	public void testFindWorkFlowInfoEncByBusino(){
		String workFlowNo = "ICRM201403250000635";
		String workFlowType = "CUSTREPEAT_PROCESS";
		crmWorkflowManager.findWorkFlowInfoEncByBusino(workFlowNo, workFlowType);
	}
	
	@Test
	public void testWorkflowApprove(){
		WorkflowApprove workflowApprove = new WorkflowApprove();
		workflowApprove.setBusino("ICRM201403250000635");
		workflowApprove.setProcessDefName("com.deppon.bpms.module.crm.bpsdesign.customer.repeatedCostomer");
		workflowApprove.setApproveOpinion("审批意见");
		workflowApprove.setApproveOperateType("1");
		workflowApprove.setProcessInstId(13495121);
		workflowApprove.setWorkItemId(48536299);
		User user = (User)UserContext.getCurrentUser();
		Employee e= new Employee();
		e.setEmpCode("039728");
		e.setEmpName("邱俊勤");
		e.setId("27175");
		user.setEmpCode(e);
		wao.setContractApproveOperate(cao);
		wao.setNormalClaimManager(nmi);
		workflowManagerForApprove.setWorkflowApproveOperate(wao);
		workflowManagerForApprove.workflowApprove(workflowApprove);
		workflowApprove.setProcessDefName("com.deppon.bpms.module.crm.bpsdesign.customer.areaMarketingActivities");
		workflowManagerForApprove.workflowApprove(workflowApprove);
		workflowApprove.setProcessDefName("com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignAppLTL");
		workflowManagerForApprove.workflowApprove(workflowApprove);
		workflowApprove.setProcessDefName("com.deppon.bpms.module.crm.bpsdesign.customer.customerAccessAndSave");
		workflowManagerForApprove.workflowApprove(workflowApprove);
	}
	@Test
	public void testOneKeyForApprove(){
		WorkflowApprove workflowApprove = new WorkflowApprove();
		workflowApprove.setBusino("ICRM201403250000635");
		workflowApprove.setProcessDefName("com.deppon.bpms.module.crm.bpsdesign.customer.repeatedCostomer");
		workflowApprove.setApproveOpinion("审批意见");
		workflowApprove.setApproveOperateType("1");
		workflowApprove.setProcessInstId(13495121);
		workflowApprove.setWorkItemId(48536299);
		User user = (User)UserContext.getCurrentUser();
		Employee e= new Employee();
		e.setEmpCode("039728");
		e.setEmpName("邱俊勤");
		e.setId("27175");
		user.setEmpCode(e);
		wao.setContractApproveOperate(cao);
		wao.setNormalClaimManager(nmi);
		workflowManagerForApprove.setWorkflowApproveOperate(wao);
		workflowManagerForApprove.oneKeyForApprove(workflowApprove);
		workflowApprove.setProcessDefName("com.deppon.bpms.module.crm.bpsdesign.customer.areaMarketingActivities");
		workflowManagerForApprove.oneKeyForApprove(workflowApprove);
		workflowApprove.setProcessDefName("com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignAppLTL");
		workflowManagerForApprove.oneKeyForApprove(workflowApprove);
		workflowApprove.setProcessDefName("com.deppon.bpms.module.crm.bpsdesign.customer.customerAccessAndSave");
		workflowManagerForApprove.oneKeyForApprove(workflowApprove);
		workflowApprove.setProcessDefName("com.deppon.bpms.module.crm.bpsdesign.operate.multiReparation");
		workflowManagerForApprove.oneKeyForApprove(workflowApprove);
		workflowApprove.setProcessDefName("com.deppon.bpms.module.crm.bpsdesign.financial.serviceRecovery");
		workflowManagerForApprove.oneKeyForApprove(workflowApprove);
		workflowApprove.setProcessDefName("com.deppon.bpms.module.crm.bpsdesign.claims.generalclaims");
		workflowManagerForApprove.oneKeyForApprove(workflowApprove);
	}
}
