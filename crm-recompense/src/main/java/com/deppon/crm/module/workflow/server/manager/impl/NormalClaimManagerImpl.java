package com.deppon.crm.module.workflow.server.manager.impl;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.bpms.module.shared.domain.ApprovalInfo;
import com.deppon.bpms.module.shared.domain.BpmsParticipant;
import com.deppon.bpms.module.shared.domain.CommonConstant;
import com.deppon.bpms.module.shared.domain.WorkFlowInfo;
import com.deppon.bpmsapi.module.client.api.IBranchRuleManager;
import com.deppon.bpmsapi.module.client.api.IDpBpmsClientFacade;
import com.deppon.bpmsapi.module.client.api.IParticipantRuleManager;
import com.deppon.bpmsapi.module.client.api.impl.DpBpmsClientFacadeImpl;
import com.deppon.bpmsapi.module.client.domain.BpmsActivity;
import com.deppon.bpmsapi.module.client.domain.BpmsOperateInfo;
import com.deppon.bpmsapi.module.client.domain.BranchRule;
import com.deppon.bpmsapi.module.client.util.BPMSConstant;
import com.deppon.bpmsapi.module.client.util.BPSClientManagerFactory;
import com.deppon.bpmsapi.module.server.api.exception.BPMSException;
import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.bps.server.manager.IBpsWorkflowManager;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.crm.module.frameworkimpl.server.util.EncryptUtil;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.CellphoneMessageInfo;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.Overpay;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.deppon.crm.module.servicerecovery.server.manager.IServiceRecoveryManager;
import com.deppon.crm.module.servicerecovery.server.service.IServiceRecoveryService;
import com.deppon.crm.module.servicerecovery.server.util.ServiceRecoveryConstant;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.workflow.rule.BranchRuleManagerImpl;
import com.deppon.crm.module.workflow.rule.ParticipantRuleManagerImpl;
import com.deppon.crm.module.workflow.rule.participant.CommonParticipant;
import com.deppon.crm.module.workflow.server.manager.INormalClaimManager;
import com.deppon.crm.module.workflow.server.manager.ISignetManagerManager;
import com.deppon.crm.module.workflow.server.service.INormalClaimService;
import com.deppon.crm.module.workflow.server.util.NormalClaimUtil;
import com.deppon.crm.module.workflow.shared.domain.NormalClaim;
import com.deppon.crm.module.workflow.shared.domain.NormalClaimCondition;
import com.deppon.crm.module.workflow.shared.domain.WorkFlowInfoEnc;
import com.deppon.crm.module.workflow.shared.domain.WorkflowApprove;
import com.deppon.crm.module.workflow.shared.exception.WorkflowException;
import com.deppon.crm.module.workflow.shared.exception.WorkflowExceptionType;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.shared.util.classes.BeanUtils;
import com.deppon.foss.framework.shared.util.string.StringUtil;
import com.eos.workflow.data.WFWorkItem;
import com.primeton.workflow.api.WFServiceException;

/**
 * 
 * <p>
 * Description:理赔工作流管理类<br />
 * </p>
 * @title NormalClaimServiceImpl.java
 * @package com.deppon.crm.module.workflow.server.service.impl 
 * @author liuHuan
 * @version 0.1 2013-7-29
 */
public class NormalClaimManagerImpl implements INormalClaimManager{
	
	// bps工作流
	private IBpsWorkflowManager bpsWorkflowManager;

	//快递业务管理部
	public static final String EXPRESSDELIVERY = "W012219";
	
	private INormalClaimService normalClaimService;
	
	private IDepartmentService departmentService;
	
	private IEmployeeService employeeService;
	
	private IFileManager fileManager;
	
	private RecompenseManager recompenseManager;
	
	private IUserService userService;
	
	private RecompenseService recompenseService;
	
	private static final String STARTTIME = " 00:00:00";
	private static final String ENDTIME   = " 23:59:59";
	private static final String SEALMANAGER = "SealManager";
	private static final String SEALMANAGERFOREXP = "SealManagerForExp";
	private static final String SEALLEADERFOREXP = "SealLeaderForExp";
	private static final String OPERATDIVMANAGERFOREXP = "OperatDivManagerForExp";
	private static final String OPERATDIVMANAGER = "OperatDivManager";
	private static final String OPERATDIVLEADERFOREXP = "OperatDivLeaderForExp";
	
	private ISignetManagerManager signetManagerManager;
	
	private IServiceRecoveryService serviceRecoveryService;
	
	
	private IServiceRecoveryManager serviceRecoveryManager;

	public void setSignetManagerManager(ISignetManagerManager signetManagerManager) {
		this.signetManagerManager = signetManagerManager;
	}
	

	public INormalClaimService getNormalClaimService() {
		return normalClaimService;
	}


	public void setNormalClaimService(INormalClaimService normalClaimService) {
		this.normalClaimService = normalClaimService;
	}



	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	


	public void setFileManager(IFileManager fileManager) {
		this.fileManager = fileManager;
	}


	public RecompenseManager getRecompenseManager() {
		return recompenseManager;
	}


	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}
	
	public IUserService getUserService() {
		return userService;
	}


	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public RecompenseService getRecompenseService() {
		return recompenseService;
	}


	public void setRecompenseService(RecompenseService recompenseService) {
		this.recompenseService = recompenseService;
	}

	public void setServiceRecoveryService(
			IServiceRecoveryService serviceRecoveryService) {
		this.serviceRecoveryService = serviceRecoveryService;
	}

	
	public void setServiceRecoveryManager(
			IServiceRecoveryManager serviceRecoveryManager) {
		this.serviceRecoveryManager = serviceRecoveryManager;
	}


	/**
	 * 
	 * <p>
	 * Description:根据工作流号查询<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-7-29
	 * @param processInstId
	 * @return
	 * NormalClaim
	 */
	@Override
	public NormalClaim getNormalClaim(Long processInstId) {
		NormalClaim nc = normalClaimService.getNormalClaim(processInstId);
		if(nc != null){
			FileInfo fi = new FileInfo();
			fi.setSourceType(NormalClaimUtil.WORKFLOW_SOURCE_TYPE);
			fi.setSourceId(processInstId+"");
			List<FileInfo> list=fileManager.searchFileInfoByCondition(fi, 0, 5);
			nc.setFileInfoList(list);
		}
		return nc;
	}

	public IDpBpmsClientFacade getClient() {
		User user = (User) UserContext.getCurrentUser();
		String empCode = user.getEmpCode().getEmpCode();
		String empName = user.getEmpCode().getEmpName();
		//分支规则
		IBranchRuleManager brm = new BranchRuleManagerImpl();
		//参与者规则
		IParticipantRuleManager prm = new ParticipantRuleManagerImpl();
		//获取客户端API门面接口
		IDpBpmsClientFacade client = new DpBpmsClientFacadeImpl(brm, prm, empCode, empName);
		return client;
	}
	
	/**
	 * 生成业务编码
	 * 规则：CRM系统工作流号生成规则：ICRM+年月日+6位数字
	 * 年月日为创建时间，格式为：20130802
	 * 6为数字：从000001开始，往上递增至999999
	 * @return
	 */
	private String bizCode() {
		StringBuffer bizCode = new StringBuffer();
		bizCode.append(NormalClaimUtil.CRM_BIZ_CODE);
		bizCode.append(getCurrentYearMonthDay("yyyyMMdd"));
		bizCode.append(getNextSuffix());
		return bizCode.toString();
	}
	
	private String getCurrentYearMonthDay(String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		String currentDate = format.format(new Date());
		return currentDate;
	}
	
	private String getNextSuffix() {
		DecimalFormat df = new DecimalFormat("0000000");
		//取下一个序号，并更新数据库中的记录，保证下一个序号递增
		int nextSuffix = normalClaimService.getNextSuffix();
		if(nextSuffix > 0) {
			if(nextSuffix == 9999999) {
				nextSuffix = 1;
				normalClaimService.updateClaimNoSuffix(nextSuffix);
			}else{
				nextSuffix = nextSuffix + 1;
				normalClaimService.updateClaimNoSuffix(nextSuffix);
			}
		} else {
			nextSuffix = 1;
			normalClaimService.insertClaimNoSuffix(nextSuffix);
		}
		return df.format(nextSuffix);
	}

	/**
	 * 起草工作流
	 * @return
	 */
	@Override
	public long createAndStartProcess() {
		//获取客户端API门面接口
		IDpBpmsClientFacade client = this.getClient();
		
		//执行创建并起草方法
		long processId = client.createAndStartProcess(
				NormalClaimUtil.PROCESS_DEFINITION_NAME, 
				NormalClaimUtil.PROCESS_INSTANCE_NORMALCLAIM, 
				NormalClaimUtil.PROCESS_DESCRIPTION, 
				bizCode());
		return processId;
	}
	
	@Override
	public String createWorkflow(String recompenseNum,
			String transType, 
			String reportDept, 
			String reportMan, 
			String reportDeptName) {
		//获取客户端API门面接口
		IDpBpmsClientFacade client = this.getClient();
		//String bizCode = bizCode();
		String bizCode = bpsWorkflowManager.bizCode();
		NormalClaim normalClaim = new NormalClaim();
		normalClaim.setWorkflowNo(bizCode);
		normalClaim.setTransportOrErrorCode(recompenseNum);
		normalClaim.setProcessinstId("0");
		normalClaim.setHaulType(transType);
		normalClaim.setReportDept(reportDept);
		normalClaim.setReportDeptName(reportDeptName);
		normalClaim.setCaseReporter(reportMan);
		normalClaimService.insertNormalClaim(normalClaim);
		//执行创建并起草方法
		/*long processId = client.createAndStartProcess(
				NormalClaimUtil.PROCESS_DEFINITION_NAME, 
				NormalClaimUtil.PROCESS_INSTANCE_NORMALCLAIM, 
				NormalClaimUtil.PROCESS_DESCRIPTION, 
				bizCode);*/
		
		Map<String, String> map = bpsWorkflowManager.createWorkflow(NormalClaimUtil.PROCESS_DEFINITION_NAME, 
				NormalClaimUtil.PROCESS_INSTANCE_NORMALCLAIM, 
				NormalClaimUtil.PROCESS_DESCRIPTION, 
				bizCode);
		String processId = map.get("processId");
		
		return processId+","+bizCode+","+recompenseNum;
	}

	/**
	 * 根据流程实例ID，判断当前用户是否可以收回流程
	 * @param processInstID 流程实例ID
	 * @return
	 */
	@Override
	public boolean isDrawBackByProcessInstID(Long processInstID) {
		//获取客户端API门面接口
		IDpBpmsClientFacade client = this.getClient();
		boolean result = client.isDrawBackByProcessInstID(processInstID);
		return result;
	}

	/**
	 * 根据工作项ID，判断当前用户是否可以收回流程
	 * @param workItemID  工作项ID
	 * @return
	 */
	@Override
	public boolean isDrawBackByWorkItemId(Long workItemID) {
		//获取客户端API门面接口
		IDpBpmsClientFacade client = this.getClient();
		boolean result = client.isDrawBackByWorkItemId(workItemID);
		return result;
	}

	/**
	 * 通过流程实例ID收回流程
	 * @param processInstID 流程实例ID
	 * @return
	 */
	@Override
	public boolean drawBackByProcessInstID(Long processInstID) {
		if(this.isDrawBackByProcessInstID(processInstID)) {
			//获取客户端API门面接口
			IDpBpmsClientFacade client = this.getClient();
			boolean isSuccess = client.drawBackByProcessInstID(processInstID);
			return isSuccess;
		}
		return false;
	}

	/**
	 * 通过工作项ID收回流程
	 * @param workItemID  工作项ID
	 * @return
	 */
	@Override
	public boolean drawBackByWorkItemId(Long workItemID) {
		if(this.isDrawBackByWorkItemId(workItemID)) {
			//获取客户端API门面接口
			IDpBpmsClientFacade client = this.getClient();
			boolean isSuccess = client.drawBackByWorkItemId(workItemID);
			return isSuccess;
		}
		return false;
	}

	/**
	 * 判断该流程是否可以删除
	 * @param processInstID 流程实例ID
	 * @return
	 */
	@Override
	public boolean isRemoveProcessInstExt(Long processInstID) {
		//获取客户端API门面接口
		IDpBpmsClientFacade client = this.getClient();
		boolean result = client.isRemoveProcessInstExt(processInstID);
		return result;
	}

	/**
	 * 执行删除流程操作
	 * @param processInstID 流程实例ID
	 * @return
	 */
	@Override
	public boolean removeMyProcessInstExt(Long processInstID) {
		if(this.isRemoveProcessInstExt(processInstID)) {
			//获取客户端API门面接口
			IDpBpmsClientFacade client = this.getClient();
			boolean result = client.removeMyProcessInstExt(processInstID);
			return result;
		}
		return false;
	}

	/**
	 * 工作流审批
	 * @param workItemID 工作项ID
	 * @param processInstID 流程实例ID
	 * @param approveOperateType 操作类型
	 * @param approveOpinion 审批意见
	 * @param parts 转办参与者数组
	 * @return
	 */
	@Override
	public boolean workflowApprove(long workItemID, long processInstID, 
			String approveOperateType, String approveOpinion, BpmsParticipant[] parts,String processDefName ) {
		boolean result = false;
		try {
			if(!StringUtils.isEmpty(approveOperateType)) {
				//获取客户端API门面接口
				IDpBpmsClientFacade client = this.getClient();
				User user = (User) UserContext.getCurrentUser();
				String empCode = user.getEmpCode().getEmpCode();
				WFWorkItem workItem = getWFWorkItem(processInstID);
				boolean isEnd = false;
				if(BPMSConstant.APPROVE_OPERATETYPE_AGREE == Integer.parseInt(approveOperateType)) {           // 同意
					//构造审批人操作信息
					BpmsOperateInfo boi = new BpmsOperateInfo();
					//设置审批类型
					boi.setOperateType(BPMSConstant.APPROVE_OPERATETYPE_AGREE);
					//设置审批时间
					boi.setOperateDate(new Date());
					//设置审批意见
					boi.setApproveOpinion(approveOpinion);
					//调用send方法发送到下一步
					//client.send(processId, workItem, boi, null)  工作项ID，流程实例ID，审批操作信息，规则数组
					//注:这里的规则数组即是调用 getNextActivities(long workItemId,long processInstId,int operateType)此方法的返回值
					result = client.send(workItemID, processInstID, boi, null);
					
					//获得下一个流程实例 如果审批同意为最后一个节点 即为End
					BranchRule[] reles = client.getNextActivities(workItemID,
							processInstID, BPMSConstant.APPROVE_OPERATETYPE_AGREE);
					//如果获得的流程实例为end，则表示流程已经完了。返回一个结果标识
					if (reles[0].getNextActivity().getActivityDefId().equals("End")) {
						isEnd = true;
					}
					
					if(processDefName.equals(NormalClaimUtil.PROCESS_DEFINITION_NAME)){//常规理赔
						if(isEnd) {
							user = userService.findByLoginName(empCode);
							user = userService.getUserRoleFunDeptById(user.getId());
							oaNormalApprove(processInstID+"", user, approveOpinion, new Date());
						}
						
						//当前节点为“经营本部负责人”提交时发送短信提醒至“快递业务管理部负责人”
						if(workItem != null && 
								(workItem.getActivityDefID().equals(this.OPERATDIVMANAGERFOREXP) || 
										workItem.getActivityDefID().equals(this.OPERATDIVMANAGER) || 
										workItem.getActivityDefID().equals(this.OPERATDIVLEADERFOREXP))) {
							sendMsg(employeeService.getEmpByCode(user.getEmpCode().getEmpCode()).getDeptId().getId().toString(), processInstID);
						}
					}else if(processDefName.equals(NormalClaimUtil.SERVICE_PROCESS_DEFINITION_NAME) && isEnd){//服务补救
						 serviceRecoveryManager.returnServiceRecoveryStatus(processInstID+"", empCode, true, new Date(), approveOpinion);
					}else if(processDefName.equals(NormalClaimUtil.OVERPAY_PROCESS_DEFINITION_NAME) && isEnd){//多赔
						user = userService.findByLoginName(empCode);
						user = userService.getUserRoleFunDeptById(user.getId());
						recompenseManager.oaOverpayApprove(processInstID+"", user, approveOpinion, new Date());
						sendOverpayMsg(employeeService.getEmpByCode(user.getEmpCode().getEmpCode()).getDeptId().getId().toString(), processInstID);
					}
				}else if(BPMSConstant.APPROVE_OPERATETYPE_DISAGREE == Integer.parseInt(approveOperateType)) {  // 不同意
					//构造审批人操作信息
					BpmsOperateInfo boi = new BpmsOperateInfo();
					//设置审批类型
					boi.setOperateType(BPMSConstant.APPROVE_OPERATETYPE_DISAGREE);
					//设置审批时间
					boi.setOperateDate(new Date());
					//设置审批意见
					boi.setApproveOpinion(approveOpinion);
					//调用send方法发送到下一步
					//client.send(processId, workItem, boi, null)  工作项ID，流程实例ID，审批操作信息，规则数组
					//注:这里的规则数组即是调用 getNextActivities(long workItemId,long processInstId,int operateType)此方法的返回值
					result = client.send(workItemID, processInstID, boi, null);
					
					
					if(processDefName.equals(NormalClaimUtil.PROCESS_DEFINITION_NAME)){//常规理赔
						user = userService.findByLoginName(empCode);
						user = userService.getUserRoleFunDeptById(user.getId());
						oaNormalRefuse(processInstID+"", user, approveOpinion, new Date());
					}else if(processDefName.equals(NormalClaimUtil.SERVICE_PROCESS_DEFINITION_NAME)){//服务补救
						serviceRecoveryManager.returnServiceRecoveryStatus(processInstID+"", empCode, false, new Date(), approveOpinion);
					}else if(processDefName.equals(NormalClaimUtil.OVERPAY_PROCESS_DEFINITION_NAME)){//多赔
						user = userService.findByLoginName(empCode);
						user = userService.getUserRoleFunDeptById(user.getId());
						recompenseManager.oaOverpayRefuse(processInstID+"", user, approveOpinion, new Date());
					}
				}else if(BPMSConstant.APPROVE_OPERATETYPE_GOBACK == Integer.parseInt(approveOperateType)) {    // 退回
					//构造审批人操作信息
					BpmsOperateInfo boi = new BpmsOperateInfo();
					//设置审批类型
					boi.setOperateType(BPMSConstant.APPROVE_OPERATETYPE_GOBACK);
					//设置审批时间
					boi.setOperateDate(new Date());
					//设置审批意见
					boi.setApproveOpinion(approveOpinion);
					BranchRule[] reles = client.getNextActivities(workItemID, processInstID, BPMSConstant.APPROVE_OPERATETYPE_GOBACK);
					//获取目标回退节点定义ID
					String defId = reles[0].getNextActivity().getActivityDefId();
					//获取当前活动节点的实例ID
					long actInstId = reles[0].getCurrentActivity().getActivityInstId();
					//判断节点是否可退回
					result = client.canBackAcitivity(actInstId, defId);
					if(result) {
						//退回目标活动定义ID数组
						String[] destDefID = new String[1];
						destDefID[0] = defId;
						result = client.backActivity(workItemID, processInstID, boi, destDefID);
					}
				}else if(BPMSConstant.APPROVE_OPERATETYPE_TRANSFER.equalsIgnoreCase(approveOperateType)) {     // 转办
					//返回值为新生成的工作项ID
					long newWorkItemID = client.transfer(workItemID, parts);
					if(newWorkItemID > 0) {
						result = true;
					}
				}
			}
		} catch (BPMSException e) {
			WorkflowException re = new WorkflowException(
					WorkflowExceptionType.BPMS_APPROVE_WORKFLOW_FAIL);
			throw new GeneralException(e.getMsg(), e.getMsg(), re,
					new Object[] {}) {

			};
		}
		return result;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：经营本部负责人同意审批后给快递业务管理部负责人发送短信提醒
	 * 作者：andy
	 * 日期： 2013-8-19 上午9:51:37
	 * @param map
	 * </pre>
	 */
	public void sendMsg(String sendDept, long processinstId) {
		CellphoneMessageInfo cellphoneMessageInfo = recompenseService.findExpressDelivery(this.EXPRESSDELIVERY);
		if(cellphoneMessageInfo != null) {
			NormalClaim normalClaim = normalClaimService.getNormalClaim(processinstId);
			if(normalClaim != null) {
				String recompenseNum = normalClaim.getTransportOrErrorCode();
				String recompenseType = normalClaim.getHaulType();
				double actualClaimsAmount = normalClaim.getActualClaimsAmount();
				
				if(!StringUtil.isEmpty(recompenseType)) {
					if(Constants.TRANS_VEHICLE.equalsIgnoreCase(recompenseType)) {
						recompenseType = Constants.TRANS_VEHICLE_NAME;
					}else if(Constants.TRANS_AIRCRAFT.equalsIgnoreCase(recompenseType)) {
						recompenseType = Constants.TRANS_AIRCRAFT_NAME;
					}else if(Constants.TRANS_EXPRESS.equalsIgnoreCase(recompenseType)) {
						recompenseType = Constants.TRANS_EXPRESS_NAME;
					}
				}
				
				String smsTemplate = MessageFormat.format(
						NormalClaimUtil.EXPRESSDELIVERY_MSGCONTENT, new Object[] {
								recompenseNum, recompenseType, actualClaimsAmount });
				
				User user = (User) UserContext.getCurrentUser();
				String senderEmpCode = user.getEmpCode().getEmpCode();
				List<SmsInformation> smsList = new ArrayList<SmsInformation>();
				SmsInformation info = new SmsInformation();
				// 收信人电话
				info.setMobile(cellphoneMessageInfo.getMobile());
				// 消息内容
				info.setMsgContent(smsTemplate);
				// 业务类型
				info.setMsgType(Constant.SMS_RECOMPENSE_CODE);
				// 短信发送人
				info.setSender(senderEmpCode);
				// 短信发送部门(标杆编码)
				info.setSendDept(sendDept);
				smsList.add(info);
				try {
					recompenseService.sendSmsInfo(smsList);
				} catch (Exception e) {
					WorkflowException re = new WorkflowException(
							WorkflowExceptionType.WORKFLOW_SENDMSG_FAIL);
					throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
							new Object[] {}) {

					};
				}
			}
		}
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:最终审批金额大于5000时，短信通知快递业务管理部负责人<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2014-1-15
	 * @param sendDept
	 * @param processinstId
	 * void
	 */
	public void sendOverpayMsg(String sendDept, long processinstId) {
		CellphoneMessageInfo cellphoneMessageInfo = recompenseService.findExpressDelivery(this.EXPRESSDELIVERY);
		if(cellphoneMessageInfo != null) {
			Overpay overpay = getDetailOverpayByWorkNumber(processinstId+"");
			if(overpay != null && overpay.getTotalAmount()>5000 
					&& Constants.TRANS_EXPRESS.equalsIgnoreCase(overpay.getTransType())) {
				String waybillNumber = overpay.getWaybillNumber();
				double realAmount=overpay.getRealAmount();
				double overpayAmount=overpay.getOverpayAmount();
				
				String smsTemplate = MessageFormat.format(
						NormalClaimUtil.OVERPAY_EXPRESSDELIVERY_MSGCONTENT, new Object[] {
								waybillNumber, Constants.TRANS_EXPRESS_NAME, realAmount, overpayAmount});
				
				User user = (User) UserContext.getCurrentUser();
				String senderEmpCode = user.getEmpCode().getEmpCode();
				List<SmsInformation> smsList = new ArrayList<SmsInformation>();
				SmsInformation info = new SmsInformation();
				// 收信人电话
				info.setMobile(cellphoneMessageInfo.getMobile());
				// 消息内容
				info.setMsgContent(smsTemplate);
				// 业务类型
				info.setMsgType(Constant.SMS_RECOMPENSE_CODE);
				// 短信发送人
				info.setSender(senderEmpCode);
				// 短信发送部门(标杆编码)
				info.setSendDept(sendDept);
				smsList.add(info);
				try {
					recompenseService.sendSmsInfo(smsList);
				} catch (Exception e) {
					WorkflowException re = new WorkflowException(
							WorkflowExceptionType.WORKFLOW_SENDMSG_FAIL);
					throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
							new Object[] {}) {

					};
				}
			}
		}
	}
	
	
	
	private WFWorkItem getWFWorkItem(long processInstID) {
		StringBuffer currentApproval = new StringBuffer();
		IDpBpmsClientFacade client = this.getClient();
		Long[] workItemIds = client.getWorkItemId(processInstID,null);
		WFWorkItem workItem = null;
		
		for(int i = 0; i < workItemIds.length; i++) {
			long workItemId = workItemIds[i];
			try {
				//取得工作项对象
				workItem = BPSClientManagerFactory.newInstance().getWorkItemManager().queryWorkItemDetail(workItemId);
			} catch (WFServiceException e) {
				   e.printStackTrace();
			}
		}
		return workItem;
	}
	
	/**
	 * 
	 * @Title: oaNormalApprove
	 * @Description: oa普通审批同意
	 * @param @param workflowNum 工作流号
	 * @param @param approver 最后操作人ID
	 * @param @param reason 审批原因
	 * @param @param approveDate 审批日期
	 * @return void 返回类型
	 * @throws
	 */
	public void oaNormalApprove(String workflowNum, User approver, String reason,
			Date approveDate) {
		recompenseManager.oaNormalApprove(workflowNum, approver, reason, approveDate);
	}
	
	/**
	 * 
	 * @Title: oaNormalRefuse
	 * @Description: oa普通审批拒绝
	 * @param @param workflowNum 工作流号
	 * @param @param approver 最后操作人ID
	 * @param @param reason 审批原因
	 * @param @param approveDate 审批日期
	 * @return void 返回类型
	 * @throws
	 */
	public void oaNormalRefuse(String workflowNum, User approver, String reason,
			Date approveDate) {
		recompenseManager.oaNormalRefuse(workflowNum, approver, reason, approveDate);
	}

	/**
	 * 根据流程实例ID查询审批记录（包括起草、审批同意、审批不同意、退回、收回、转办等）
	 * @param processInstID 流程实例ID
	 * @return
	 */
	@Override
	public List<ApprovalInfo> queryApprovalInfoByProcessInstID(
			long processInstID) {
		//获取客户端API门面接口
		IDpBpmsClientFacade client = this.getClient();
		List<ApprovalInfo> list = client.queryApprovalInfoByProcessInstID(processInstID);
		for(ApprovalInfo app: list){
			Employee emp =employeeService.getEmpByCode(app.getUserid());
			if(emp!=null){
				app.setBusino(emp.getPosition());//此处借存审批人职位
			}
			
		}
		return list;
	}

	/**
	 * 查询指定系统指定用户的待办流程信息
	 * @param start 分页开始
	 * @param limit 每页条数
	 * @param processDefName 流程定义名称
	 * @param applerId 申请人工号
	 * @param fstandardcode 申请人所在部门编码
	 * @param state 
	 * 1    未提交
       2  未审批
       3  审批中
       4  已同意
       5  未同意
       6  已退回
       7  汇款失败
       8  汇款成功
	 * @param startTime 申请日期
	 * @param endTime 申请日期
	 * @param workflowNo 工作流号
	 * @return
	 */
	@Override
	public List<WorkFlowInfo> queryTodoItems(int start,
			int limit, String processDefName, String applerId, String fstandardcode, String state,
			Date startTime, Date endTime, String workflowNo) {
		User user = (User) UserContext.getCurrentUser();
		String empCode = user.getEmpCode().getEmpCode();
		String empName = user.getEmpCode().getEmpName();
		//分支规则
		IBranchRuleManager brm = new BranchRuleManagerImpl();
		//参与者规则
		IParticipantRuleManager prm = new ParticipantRuleManagerImpl();
		//获取客户端API门面接口
		IDpBpmsClientFacade client = new DpBpmsClientFacadeImpl(brm, prm, empCode, empName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CommonConstant.USERID, empCode);  // 当前登录者ID
		map.put(CommonConstant.SYSCODE, NormalClaimUtil.CRM_BIZ_CODE); // 业务编码
		map.put(CommonConstant.PROCESSDEFNAME, processDefName);
		
		if(StringUtil.isEmpty(workflowNo)){
			if(!StringUtil.isEmpty(state)) {
				map.put(CommonConstant.CONDITION, state);
			}
			//map.put(CommonConstant.BUSINO, busiNo);
			//开始时间
			if(null != startTime) {
				Date startDate = formatDate(formatDate(startTime)+this.STARTTIME);
				map.put(CommonConstant.STARTTIME, startDate);
			}
			//结束时间
			if(null != endTime) {
				Date endDate = formatDate(formatDate(endTime)+this.ENDTIME);
				map.put(CommonConstant.ENDTIME, endDate);
			}
			// 申请人工号
			if(!StringUtil.isEmpty(applerId)) {
				map.put(CommonConstant.APPLERID, applerId);
			}
			// 申请人所在部门编码
			if(!StringUtil.isEmpty(fstandardcode)) {
				map.put(CommonConstant.DEPARTMENTID, fstandardcode);
			}
		}else {
			Object obj=null;
			if(processDefName.equals(NormalClaimUtil.PROCESS_DEFINITION_NAME)){//常规理赔
				obj = normalClaimService.getNormalClaimByWorkflowNo(workflowNo);
			}else if(processDefName.equals(NormalClaimUtil.SERVICE_PROCESS_DEFINITION_NAME)){//服务补救
				obj = serviceRecoveryService.findServiceRecoveryByWorkflowNo(workflowNo);
			}else if(processDefName.equals(NormalClaimUtil.OVERPAY_PROCESS_DEFINITION_NAME)){//多赔
				obj = recompenseService.getOverPay(workflowNo);;
			}
			if(obj!=null){
				map.put(CommonConstant.BUSINO, workflowNo);
			}else{
				return new ArrayList<WorkFlowInfo>();
			}
		}
		map.put(CommonConstant.START, start);
		map.put(CommonConstant.LIMIT, limit);
		List<WorkFlowInfo> list = client.queryTodoItems(map);
		return list;
	}

	/**
	 * 查询指定用户的指定业务系统的待办流程数目
	 * @param processDefName 流程定义名称
	 * @param applerId 申请人工号
	 * @param fstandardcode 申请人所在部门编码
	 * @param state 
	 * 1    未提交
       2  未审批
       3  审批中
       4  已同意
       5  未同意
       6  已退回
       7  汇款失败
       8  汇款成功
	 * @param startTime 申请日期
	 * @param endTime 申请日期
	 * @param workflowNo 工作流号
	 * @return
	 */
	@Override
	public long queryTodoCounts(String processDefName, String applerId, String fstandardcode, String state, Date startTime, Date endTime, String workflowNo) {
		User user = (User) UserContext.getCurrentUser();
		String empCode = user.getEmpCode().getEmpCode();
		String empName = user.getEmpCode().getEmpName();
		//分支规则
		IBranchRuleManager brm = new BranchRuleManagerImpl();
		//参与者规则
		IParticipantRuleManager prm = new ParticipantRuleManagerImpl();
		//获取客户端API门面接口
		IDpBpmsClientFacade client = new DpBpmsClientFacadeImpl(brm, prm, empCode, empName);
		Map<String, Object> map = new HashMap<String, Object>(
				);
		map.put(CommonConstant.USERID, empCode);
		map.put(CommonConstant.SYSCODE, NormalClaimUtil.CRM_BIZ_CODE);   //业务系统编码
		map.put(CommonConstant.PROCESSDEFNAME, processDefName);
		if(StringUtil.isEmpty(workflowNo)){
			if(!StringUtil.isEmpty(state)) {
				map.put(CommonConstant.CONDITION, state);
			}
			//map.put(CommonConstant.BUSINO, busiNo);
			//开始时间
			if(null != startTime) {
				Date startDate = formatDate(formatDate(startTime)+this.STARTTIME);
				map.put(CommonConstant.STARTTIME, startDate);
			}
			//结束时间
			if(null != endTime) {
				Date endDate = formatDate(formatDate(endTime)+this.ENDTIME);
				map.put(CommonConstant.ENDTIME, endDate);
			}
			if(!StringUtil.isEmpty(applerId)) {
				map.put(CommonConstant.APPLERID, applerId);
			}
			if(!StringUtil.isEmpty(fstandardcode)) {
				map.put(CommonConstant.DEPARTMENTID, fstandardcode);
			}
		}else {
			Object obj=null;
			if(processDefName.equals(NormalClaimUtil.PROCESS_DEFINITION_NAME)){//常规理赔
				obj = normalClaimService.getNormalClaimByWorkflowNo(workflowNo);
			}else if(processDefName.equals(NormalClaimUtil.SERVICE_PROCESS_DEFINITION_NAME)){//服务补救
				obj = serviceRecoveryService.findServiceRecoveryByWorkflowNo(workflowNo);
			}else if(processDefName.equals(NormalClaimUtil.OVERPAY_PROCESS_DEFINITION_NAME)){//多赔
				obj = recompenseService.getOverPay(workflowNo);;
			}
			if(obj!=null){
				map.put(CommonConstant.BUSINO, workflowNo);
			}else{
				return 0L;
			}
		}
		long num = client.queryTodoCounts(map);
		return num;
	}

	/**
	 * 查询指定用户的指定系统的已审批流程信息
	 * @param start 分页开始
	 * @param limit 每页条数
	 * @param processDefName 流程定义名称
	 * @param applerId 申请人工号
	 * @param departmentId 申请人所在部门编码
	 * @param state 
	 * 1    未提交
       2  未审批
       3  审批中
       4  已同意
       5  未同意
       6  已退回
       7  汇款失败
       8  汇款成功
	 * @param startTime 申请日期
	 * @param endTime 申请日期
	 * @param workflowNo 工作流号
	 * @return
	 */
	@Override
	public List<WorkFlowInfo> queryFinishedItems(int start,
			int limit, String processDefName, String applerId, String departmentId, String state, 
			Date startTime, Date endTime, String workflowNo) {
		User user = (User) UserContext.getCurrentUser();
		String empCode = user.getEmpCode().getEmpCode();
		String empName = user.getEmpCode().getEmpName();
		//分支规则
		IBranchRuleManager brm = new BranchRuleManagerImpl();
		//参与者规则
		IParticipantRuleManager prm = new ParticipantRuleManagerImpl();
		//获取客户端API门面接口
		IDpBpmsClientFacade client = new DpBpmsClientFacadeImpl(brm, prm, empCode, empName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CommonConstant.USERID, empCode);           //当前登录者id
		map.put(CommonConstant.SYSCODE, NormalClaimUtil.CRM_BIZ_CODE);           //业务系统编码
		//流程定义名称
		map.put(CommonConstant.PROCESSDEFNAME, processDefName);
		if(StringUtil.isEmpty(workflowNo)){
			if(!StringUtil.isEmpty(state)) {
				map.put(CommonConstant.CONDITION, state);
			}
			//map.put(CommonConstant.BUSINO, busiNo);            //报账单号
			//开始时间
			if(null != startTime) {
				Date startDate = formatDate(formatDate(startTime)+this.STARTTIME);
				map.put(CommonConstant.STARTTIME, startDate);
			}
			//结束时间
			if(null != endTime) {
				Date endDate = formatDate(formatDate(endTime)+this.ENDTIME);
				map.put(CommonConstant.ENDTIME, endDate);
			}
			if(!StringUtil.isEmpty(applerId)) {
				map.put(CommonConstant.APPLERID, applerId);        //申请人工号
			}
			if(!StringUtil.isEmpty(departmentId)) {
				map.put(CommonConstant.DEPARTMENTID, departmentId);//申请人所在部门编码
			}
		}
		map.put(CommonConstant.START, start);              //分页开始位置 
		map.put(CommonConstant.LIMIT, limit);              //每页显示的条数
		if(!StringUtil.isEmpty(workflowNo)) {
			Object obj=null;
			if(processDefName.equals(NormalClaimUtil.PROCESS_DEFINITION_NAME)){//常规理赔
				obj = normalClaimService.getNormalClaimByWorkflowNo(workflowNo);
			}else if(processDefName.equals(NormalClaimUtil.SERVICE_PROCESS_DEFINITION_NAME)){//服务补救
				obj = serviceRecoveryService.findServiceRecoveryByWorkflowNo(workflowNo);
			}else if(processDefName.equals(NormalClaimUtil.OVERPAY_PROCESS_DEFINITION_NAME)){//多赔
				obj = recompenseService.getOverPay(workflowNo);;
			}
			if(obj!=null){
				map.put(CommonConstant.BUSINO, workflowNo);
			}else{
				return new ArrayList<WorkFlowInfo>();
			}
		}
		//已审批流程集合
		List<WorkFlowInfo> list = client.queryFinishedItems(map);
		return list;
	}

	/**
	 * 查询指定用户的指定业务系统的已办工作项数目
	 * @param start 分页开始
	 * @param limit 每页条数
	 * @param processDefName 流程定义名称
	 * @param applerId 申请人工号
	 * @param departmentId 申请人所在部门编码
	 * @param state 
	 * 1    未提交
       2  未审批
       3  审批中
       4  已同意
       5  未同意
       6  已退回
       7  汇款失败
       8  汇款成功
	 * @param startTime 申请日期
	 * @param endTime 申请日期
	 * @param workflowNo 工作流号
	 * @return
	 */
	@Override
	public Long queryFinishedCounts(String processDefName, String applerId, String departmentId, String state, Date startTime, Date endTime, String workflowNo) {
		User user = (User) UserContext.getCurrentUser();
		String empCode = user.getEmpCode().getEmpCode();
		String empName = user.getEmpCode().getEmpName();
		//分支规则
		IBranchRuleManager brm = new BranchRuleManagerImpl();
		//参与者规则
		IParticipantRuleManager prm = new ParticipantRuleManagerImpl();
		//获取客户端API门面接口
		IDpBpmsClientFacade client = new DpBpmsClientFacadeImpl(brm, prm, empCode, empName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CommonConstant.USERID, empCode);     //当前登录者id
		map.put(CommonConstant.SYSCODE, NormalClaimUtil.CRM_BIZ_CODE);    //业务系统编码
		//流程定义名称
		map.put(CommonConstant.PROCESSDEFNAME, processDefName);
		if(StringUtil.isEmpty(workflowNo)){
			if(!StringUtil.isEmpty(state)) {
				map.put(CommonConstant.CONDITION, state);
			}
			//map.put(CommonConstant.BUSINO, busiNo);      //报账单号
			//开始时间
			if(null != startTime) {
				Date startDate = formatDate(formatDate(startTime)+this.STARTTIME);
				map.put(CommonConstant.STARTTIME, startDate);
			}
			//结束时间
			if(null != endTime) {
				Date endDate = formatDate(formatDate(endTime)+this.ENDTIME);
				map.put(CommonConstant.ENDTIME, endDate);
			}
			if(!StringUtil.isEmpty(applerId)) {
				map.put(CommonConstant.APPLERID, applerId);        //申请人工号
			}
			if(!StringUtil.isEmpty(departmentId)) {
				map.put(CommonConstant.DEPARTMENTID, departmentId);//申请人所在部门编码
			}
		}else {
			Object obj=null;
			if(processDefName.equals(NormalClaimUtil.PROCESS_DEFINITION_NAME)){//常规理赔
				obj = normalClaimService.getNormalClaimByWorkflowNo(workflowNo);
			}else if(processDefName.equals(NormalClaimUtil.SERVICE_PROCESS_DEFINITION_NAME)){//服务补救
				obj = serviceRecoveryService.findServiceRecoveryByWorkflowNo(workflowNo);
			}else if(processDefName.equals(NormalClaimUtil.OVERPAY_PROCESS_DEFINITION_NAME)){//多赔
				obj = recompenseService.getOverPay(workflowNo);;
			}
			if(obj!=null){
				map.put(CommonConstant.BUSINO, workflowNo);
			}else{
				return 0L;
			}
		}
		//返回已办数目
		long num = client.queryFinishedCounts(map);
		return num;
	}

	/**
	 * 根据业务编号设置流程业务状态
	 * @param bizCode 业务编号
	 * @param state 业务状态
	 * @return
	 */
	@Override
	public boolean setProcessInstBusinessState(String bizCode, String state) {
		IDpBpmsClientFacade client = this.getClient();
		boolean result = client.setProcessInstBusinessState(bizCode, state);
		return result;
	}

	/**
	 * 查询流程实例下对应的参与者信息
	 * @param processInstId 流程实例ID
	 * @param activityDefId 环节定义ID
	 * @param flag 1：查询已处理参与者   2：查询环节配置参与者
	 * @return
	 */
	@Override
	public BpmsParticipant[] getActivityParticipant(Long processInstId, 
			String activityDefId, 
			String flag) {
		IDpBpmsClientFacade client = this.getClient();
		BpmsParticipant[] bpms = client.getActivityParticipant(processInstId, activityDefId, flag);
		return null;
	}

	/**
	 * 结束当前流程
	 * @param processInstId 流程实例ID
	 * @return
	 */
	@Override
	public boolean terminateProcessInstance(Long processInstId) {
		IDpBpmsClientFacade client = this.getClient();
		boolean result = client.terminateProcessInstance(processInstId);
		return result;
	}

	/**
	 * 获取下一步可到达的节点
	 * @param workItemId 工作项ID
	 * @param processInstId 流程实例ID
	 * @param approveOperateType 操作类型
	 * @return
	 */
	@Override
	public BranchRule[] getNextActivities(long workItemId, long processInstId, int operateType) {
		IDpBpmsClientFacade client = this.getClient();
		BranchRule[] br = client.getNextActivities(workItemId, processInstId, operateType);
		return br;
	}

	/**
	 * 理赔工作流详情
	 * @param workflowNo CRM流程号
	 * @return
	 */
	@Override
	public NormalClaim getNormalClaimByWorkflowNo(
			String workflowNo) {
		NormalClaim normalClaim = normalClaimService.getNormalClaimByWorkflowNo(workflowNo);
		return normalClaim;
	}
	
	/**
	 * 入部门费用
	 * @param processInstId 流程实例ID
	 * @return
	 */
	@Override
	public List<DeptCharge> getDeptChargeByProcessinstId(
			long processInstId) {
		List<DeptCharge> deptChargeList = normalClaimService.getDeptChargeByProcessinstId(processInstId);
		return deptChargeList;
	}
	
	/**
	 * 出险信息
	 * @param processInstId 流程实例ID
	 * @return
	 */
	@Override
	public List<IssueItem> getIssueItemByProcessinstId(
			long processInstId) {
		List<IssueItem> issueItemList = normalClaimService.getIssueItemByProcessinstId(processInstId);
		return issueItemList;
	}
	
	/**
	 * 奖罚明细
	 * @param processInstId 流程实例ID
	 * @return
	 */
	@Override
	public List<AwardItem> getAwardItemByProcessinstId(
			long processInstId) {
		List<AwardItem> awardItemList = normalClaimService.getAwardItemByProcessinstId(processInstId);
		for(AwardItem ai : awardItemList){
			if(ai.getDeptId()!=null){
				Department dep=departmentService.getDepartmentById(ai.getDeptId());
				if(dep!=null){
					ai.setDeptName(dep.getDeptName());
				}
			}
		}
		return awardItemList;
	}
	
	/**
	 * 责任部门
	 * @param processInstId 流程实例ID
	 * @return
	 */
	@Override
	public List<ResponsibleDept> getResponsibleDeptByProcessinstId(
			long processInstId) {
		List<ResponsibleDept> responsibleDeptList = normalClaimService.getResponsibleDeptByProcessinstId(processInstId);
		return responsibleDeptList;
	}

	/**
	 * 获取当前审批人信息
	 * @param processInstID 流程实例ID
	 * @return 审批节点      【姓名   工号】
	 */
	@Override
	public String getCurrentApproval(long processInstID) {
		StringBuffer currentApproval = new StringBuffer();
		IDpBpmsClientFacade client = this.getClient();
		Long[] workItemIds = client.getWorkItemId(processInstID,null);
		WFWorkItem workItem = null;
		currentApproval.append("当前审批人：");
		boolean current = false;
		for(int i = 0; i < workItemIds.length; i++) {
			long workItemId = workItemIds[i];
			try {
				//取得工作项对象
				workItem = BPSClientManagerFactory.newInstance().getWorkItemManager().queryWorkItemDetail(workItemId);
			} catch (WFServiceException e) {
				   e.printStackTrace();
			}
			//取得当前审批人ID
			String approverID = workItem.getParticipant();
			//取得当前审批人姓名
			String approverName = workItem.getPartiName();
			currentApproval.append(workItem.getActivityInstName()+"      【"+approverName+"   "+approverID+"】       ");
			if(workItem.getActivityInstName() != null && approverID != null) {
				current = true;
			}
		}
		if(current) {
			return currentApproval.toString();
		}else{
			return "流程已完成！";
		}
	}

	/**
	 * 查询指定用户的指定系统的我申请的流程信息
	 * @param start 分页开始
	 * @param limit 每页条数
	 * @param processDefName 流程定义名称
	 * @param state 
	 * 1    未提交
       2  未审批
       3  审批中
       4  已同意
       5  未同意
       6  已退回
       7  汇款失败
       8  汇款成功
	 * @param startTime 申请日期
	 * @param endTime 申请日期
	 * @param workflowNo 工作流号
	 * @return
	 */
	@Override
	public List<WorkFlowInfo> queryMyWorkFlow(
			int start,
			int limit, 
			String processDefName, 
			String state, 
			Date startTime, 
			Date endTime, 
			String workflowNo) {
		Map<String,Object> map = new HashMap<String, Object>();
		User user = (User) UserContext.getCurrentUser();
		String empCode = user.getEmpCode().getEmpCode();
		String empName = user.getEmpCode().getEmpName();
		//分支规则
		IBranchRuleManager brm = new BranchRuleManagerImpl();
		//参与者规则
		IParticipantRuleManager prm = new ParticipantRuleManagerImpl();
		//获取客户端API门面接口
		IDpBpmsClientFacade client = new DpBpmsClientFacadeImpl(brm, prm, empCode, empName);
		
		if(StringUtil.isEmpty(workflowNo)) {
			//当前登录者id
			map.put(CommonConstant.USERID, empCode);
			//申请人所在部门编码
			//map.put(CommonConstant.DEPARTMENTID, user.getEmpCode().getDeptId().getStandardCode());
		}else{
			if(processDefName.equals(NormalClaimUtil.PROCESS_DEFINITION_NAME)){//常规理赔
				NormalClaim normalClaim = getNormalClaimByWorkflowNo(workflowNo);
				if(normalClaim!=null){
					map.put(CommonConstant.USERID, normalClaim.getApplyPersonCode());
					//申请人所在部门编码
					//map.put(CommonConstant.DEPARTMENTID, normalClaim.getStandardCode());
					map.put(CommonConstant.BUSINO, workflowNo);
				}else{
					return new ArrayList<WorkFlowInfo>();
				}
			}else if(processDefName.equals(NormalClaimUtil.SERVICE_PROCESS_DEFINITION_NAME)){//服务补救
				
				ServiceRecovery serviceRecovery = serviceRecoveryService.findServiceRecoveryByWorkflowNo(workflowNo);
				if(serviceRecovery!=null){
					map.put(CommonConstant.USERID, serviceRecovery.getEmpCode());
					//申请人所在部门编码
					//map.put(CommonConstant.DEPARTMENTID, normalClaim.getStandardCode());
					map.put(CommonConstant.BUSINO, workflowNo);
				}else{
					return new ArrayList<WorkFlowInfo>();
				}
			}else if(processDefName.equals(NormalClaimUtil.OVERPAY_PROCESS_DEFINITION_NAME)){//多赔
				Overpay overpay = recompenseService.getOverPay(workflowNo);;
				if(overpay!=null){
					Employee emp=employeeService.getEmpById(overpay.getCreateUser());
					map.put(CommonConstant.USERID, emp.getEmpCode());
					//申请人所在部门编码
					//map.put(CommonConstant.DEPARTMENTID, normalClaim.getStandardCode());
					map.put(CommonConstant.BUSINO, workflowNo);
				}else{
					return new ArrayList<WorkFlowInfo>();
				}
			}
		}
		//业务系统编码
		map.put(CommonConstant.SYSCODE, NormalClaimUtil.CRM_BIZ_CODE);
		//流程定义名称
		map.put(CommonConstant.PROCESSDEFNAME, processDefName);
		if(StringUtil.isEmpty(workflowNo)){
			if(!StringUtil.isEmpty(state)) {
				//审批状态：审批中
				map.put(CommonConstant.CONDITION, state);
			}
			//开始时间
			if(null != startTime) {
				Date startDate = formatDate(formatDate(startTime)+this.STARTTIME);
				map.put(CommonConstant.STARTTIME, startDate);
			}
			//结束时间
			if(null != endTime) {
				Date endDate = formatDate(formatDate(endTime)+this.ENDTIME);
				map.put(CommonConstant.ENDTIME, endDate);
			}
		}
		//分页开始位置
		map.put(CommonConstant.START, start);
		//每页显示的条数
		map.put(CommonConstant.LIMIT, limit);
		List<WorkFlowInfo> wfList = client.queryMyWorkFlow(map);
		return wfList;
	}
	
	/**
	 * 查询指定用户的指定系统的我申请的流程数目
	 * @param processDefName 流程定义名称
	 * @param state 
	 * 1    未提交
       2  未审批
       3  审批中
       4  已同意
       5  未同意
       6  已退回
       7  汇款失败
       8  汇款成功
	 * @param startTime 申请日期
	 * @param endTime 申请日期
	 * @param workflowNo 工作流号
	 * @return
	 */
	@Override
	public long queryMyWorkFlowCount(
			String processDefName, 
			String state, 
			Date startTime, 
			Date endTime, 
			String workflowNo) {
		Map<String,Object> map = new HashMap<String, Object>();
		User user = (User) UserContext.getCurrentUser();
		String empCode = user.getEmpCode().getEmpCode();
		String empName = user.getEmpCode().getEmpName();
		//分支规则
		IBranchRuleManager brm = new BranchRuleManagerImpl();
		//参与者规则
		IParticipantRuleManager prm = new ParticipantRuleManagerImpl();
		//获取客户端API门面接口
		IDpBpmsClientFacade client = new DpBpmsClientFacadeImpl(brm, prm, empCode, empName);
		
		if(StringUtil.isEmpty(workflowNo)) {
			//当前登录者id
			map.put(CommonConstant.USERID, empCode);
			//申请人所在部门编码
			//map.put(CommonConstant.DEPARTMENTID, user.getEmpCode().getDeptId().getStandardCode());
		}else{
			if(processDefName.equals(NormalClaimUtil.PROCESS_DEFINITION_NAME)){//常规理赔
				NormalClaim normalClaim = getNormalClaimByWorkflowNo(workflowNo);
				if(normalClaim!=null){
					map.put(CommonConstant.USERID, normalClaim.getApplyPersonCode());
					//申请人所在部门编码
					//map.put(CommonConstant.DEPARTMENTID, normalClaim.getStandardCode());
					map.put(CommonConstant.BUSINO, workflowNo);
				}else{
					return 0L;
				}
			}else if(processDefName.equals(NormalClaimUtil.SERVICE_PROCESS_DEFINITION_NAME)){//服务补救
				ServiceRecovery serviceRecovery = serviceRecoveryService.findServiceRecoveryByWorkflowNo(workflowNo);
				if(serviceRecovery!=null){
					map.put(CommonConstant.USERID, serviceRecovery.getEmpCode());
					//申请人所在部门编码
					//map.put(CommonConstant.DEPARTMENTID, normalClaim.getStandardCode());
					map.put(CommonConstant.BUSINO, workflowNo);
				}else{
					return 0L;
				}
			}else if(processDefName.equals(NormalClaimUtil.OVERPAY_PROCESS_DEFINITION_NAME)){//多赔
				
				Overpay overpay = recompenseService.getOverPay(workflowNo);;
				if(overpay!=null){
					Employee emp=employeeService.getEmpById(overpay.getCreateUser());
					map.put(CommonConstant.USERID, emp.getEmpCode());
					//申请人所在部门编码
					//map.put(CommonConstant.DEPARTMENTID, normalClaim.getStandardCode());
					map.put(CommonConstant.BUSINO, workflowNo);
				}else{
					return 0L;
				}
			}
			
			
		}
		//业务系统编码
		map.put(CommonConstant.SYSCODE, NormalClaimUtil.CRM_BIZ_CODE);
		//流程定义名称
		map.put(CommonConstant.PROCESSDEFNAME, processDefName);
		if(StringUtil.isEmpty(workflowNo)){
			if(!StringUtil.isEmpty(state)) {
				//审批状态：审批中
				map.put(CommonConstant.CONDITION, state);
			}
			//开始时间
			if(null != startTime) {
				Date startDate = formatDate(formatDate(startTime)+this.STARTTIME);
				map.put(CommonConstant.STARTTIME, startDate);
			}
			//结束时间
			if(null != endTime) {
				Date endDate = formatDate(formatDate(endTime)+this.ENDTIME);
				map.put(CommonConstant.ENDTIME, endDate);
			}
		}
		return client.queryMyProcessCount(map);
	}


	/**
	 * 
	 * <p>
	 * Description:查询未处理的工作流<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-9
	 * @param normalClaimCondition 查询条件
	 * @return
	 * L Map<String,Object>
	 */
	@Override
	public Map<String,Object> findToHandleWorkflow(NormalClaimCondition normalClaimCondition) {
		Map<String,Object> map = new HashMap<String, Object>();
		if(!StringUtil.isEmpty(normalClaimCondition.getDeptId())){
			Department dep=departmentService.getDepartmentById(normalClaimCondition.getDeptId());
			if(dep!=null){
				normalClaimCondition.setFstandardcode(dep.getDeptCode());
			}			
		}
		if(!StringUtil.isEmpty(normalClaimCondition.getApplerId())){
			Employee emp=employeeService.getEmpById(normalClaimCondition.getApplerId());
			if(emp!=null){
				normalClaimCondition.setApplerId(emp.getEmpCode());
			}
		}
		String processDefName=null;
		if(normalClaimCondition.getType().equals("1")){
			processDefName=NormalClaimUtil.PROCESS_DEFINITION_NAME;
		}else if(normalClaimCondition.getType().equals("2")){
			processDefName=NormalClaimUtil.SERVICE_PROCESS_DEFINITION_NAME;
		}else if(normalClaimCondition.getType().equals("3")){
			processDefName=NormalClaimUtil.OVERPAY_PROCESS_DEFINITION_NAME;
		}
		List<WorkFlowInfo> list = queryTodoItems(
				normalClaimCondition.getStart(),
				normalClaimCondition.getLimit(),
				processDefName,
				normalClaimCondition.getApplerId(),
				normalClaimCondition.getFstandardcode(),
				normalClaimCondition.getState(),
				normalClaimCondition.getStartTime(),
				normalClaimCondition.getEndTime(),
				normalClaimCondition.getWorkflowNo());
		long totalCount = queryTodoCounts(
				processDefName,
				normalClaimCondition.getApplerId(),
				normalClaimCondition.getFstandardcode(),
				normalClaimCondition.getState(),
				normalClaimCondition.getStartTime(),
				normalClaimCondition.getEndTime(),
				normalClaimCondition.getWorkflowNo());
		List<WorkFlowInfoEnc> listEnc = new ArrayList<WorkFlowInfoEnc>();
		for(WorkFlowInfo wfi:list){
			WorkFlowInfoEnc wfie= new WorkFlowInfoEnc();
			BeanUtils.copyProperties(wfi, wfie);
			wfie.setProcessinstidEnc(EncryptUtil.encrypt(wfi.getProcessinstid()+"",NormalClaimUtil.WORKFLOW_DESC_KEY));
			wfie.setWorkItemIdEnc(EncryptUtil.encrypt(wfi.getWorkItemId()+"",NormalClaimUtil.WORKFLOW_DESC_KEY));
			listEnc.add(wfie);
		}
		
		map.put("list", listEnc);
		map.put("totalCount", totalCount);
		return map;
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询已处理的工作流<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-9
	 * @param normalClaimCondition 查询条件
	 * @return
	 *  Map<String,Object>
	 */
	@Override
	public Map<String,Object> findHandledWorkflow(NormalClaimCondition normalClaimCondition) {
		Map<String,Object> map = new HashMap<String, Object>();
		if(!StringUtil.isEmpty(normalClaimCondition.getDeptId())){
			Department dep=departmentService.getDepartmentById(normalClaimCondition.getDeptId());
			if(dep!=null){
				normalClaimCondition.setFstandardcode(dep.getDeptCode());
			}			
		}
		if(!StringUtil.isEmpty(normalClaimCondition.getApplerId())){
			Employee emp=employeeService.getEmpById(normalClaimCondition.getApplerId());
			if(emp!=null){
				normalClaimCondition.setApplerId(emp.getEmpCode());
			}
		}
		String processDefName=null;
		if(normalClaimCondition.getType().equals("1")){
			processDefName=NormalClaimUtil.PROCESS_DEFINITION_NAME;
		}else if(normalClaimCondition.getType().equals("2")){
			processDefName=NormalClaimUtil.SERVICE_PROCESS_DEFINITION_NAME;
		}else if(normalClaimCondition.getType().equals("3")){
			processDefName=NormalClaimUtil.OVERPAY_PROCESS_DEFINITION_NAME;
		}
		List<WorkFlowInfo> list = queryFinishedItems(
				normalClaimCondition.getStart(),
				normalClaimCondition.getLimit(),
				processDefName,
				normalClaimCondition.getApplerId(),
				normalClaimCondition.getFstandardcode(),
				normalClaimCondition.getState(),
				normalClaimCondition.getStartTime(),
				normalClaimCondition.getEndTime(),
				normalClaimCondition.getWorkflowNo());
		long totalCount = queryFinishedCounts(
				processDefName,
				normalClaimCondition.getApplerId(),
				normalClaimCondition.getFstandardcode(),
				normalClaimCondition.getState(),
				normalClaimCondition.getStartTime(),
				normalClaimCondition.getEndTime(),
				normalClaimCondition.getWorkflowNo());
		
		List<WorkFlowInfoEnc> listEnc = new ArrayList<WorkFlowInfoEnc>();
		for(WorkFlowInfo wfi:list){
			WorkFlowInfoEnc wfie= new WorkFlowInfoEnc();
			BeanUtils.copyProperties(wfi, wfie);
			wfie.setProcessinstidEnc(EncryptUtil.encrypt(wfi.getProcessinstid()+"",NormalClaimUtil.WORKFLOW_DESC_KEY));
			wfie.setWorkItemIdEnc(EncryptUtil.encrypt(wfi.getWorkItemId()+"",NormalClaimUtil.WORKFLOW_DESC_KEY));
			wfie.setProcessinstname(wfie.getProcessCHName());
			wfie.setProcessDefName(processDefName);
			listEnc.add(wfie);
		}
		map.put("list", listEnc);
		map.put("totalCount", totalCount);
		return map;
	}
	
	/**
	 * 
	 * <p>
	 * Description:工作流查询<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-9
	 * @param normalClaimCondition 查询条件
	 * @return
	 *  Map<String,Object>
	 */
	@Override
	public Map<String,Object> findWorkflow(NormalClaimCondition normalClaimCondition) {
		Map<String,Object> map = new HashMap<String, Object>();
		String processDefName=null;
		if(normalClaimCondition.getType().equals("1")){
			processDefName=NormalClaimUtil.PROCESS_DEFINITION_NAME;
		}else if(normalClaimCondition.getType().equals("2")){
			processDefName=NormalClaimUtil.SERVICE_PROCESS_DEFINITION_NAME;
		}else if(normalClaimCondition.getType().equals("3")){
			processDefName=NormalClaimUtil.OVERPAY_PROCESS_DEFINITION_NAME;
		}
		List<WorkFlowInfo> list = queryMyWorkFlow(
				normalClaimCondition.getStart(),
				normalClaimCondition.getLimit(),
				processDefName,
				normalClaimCondition.getState(),
				normalClaimCondition.getStartTime(),
				normalClaimCondition.getEndTime(),
				normalClaimCondition.getWorkflowNo());
		long totalCount = queryMyWorkFlowCount(
				processDefName,
				normalClaimCondition.getState(),
				normalClaimCondition.getStartTime(),
				normalClaimCondition.getEndTime(),
				normalClaimCondition.getWorkflowNo());
		
		List<WorkFlowInfoEnc> listEnc = new ArrayList<WorkFlowInfoEnc>();
		for(WorkFlowInfo wfi:list){
			WorkFlowInfoEnc wfie= new WorkFlowInfoEnc();
			BeanUtils.copyProperties(wfi, wfie);
			wfie.setProcessinstidEnc(EncryptUtil.encrypt(wfi.getProcessinstid()+"",NormalClaimUtil.WORKFLOW_DESC_KEY));
			wfie.setWorkItemIdEnc(EncryptUtil.encrypt(wfi.getWorkItemId()+"",NormalClaimUtil.WORKFLOW_DESC_KEY));
			listEnc.add(wfie);
		}
		map.put("list", listEnc);
		map.put("totalCount", totalCount);
		return map;
	}


	/**
	  * 
	  * <p>
	  * Description:工作流审批<br />
	  * </p>
	  * @author liuHuan
	  * @version 0.1 2013-8-12
	  * @param workflowApprove
	  * @return
	  * boolean
	  */
	@Override
	public boolean workflowApprove(WorkflowApprove workflowApprove) {
		boolean result = workflowApprove(
				workflowApprove.getWorkItemId(),
				workflowApprove.getProcessInstId(),
				workflowApprove.getApproveOperateType(),
				workflowApprove.getApproveOpinion(), null,workflowApprove.getProcessDefName());
		//添加上传文件记录
		if(result&&null!=workflowApprove.getFileInfoList()){
			//获取当前会话用户
			User user = (User) UserContext.getCurrentUser();
			for(FileInfo fileInfo : workflowApprove.getFileInfoList()){
				fileInfo.setCreateUser(user.getEmpCode().getId());
				fileInfo.setCreateDept(user.getEmpCode().getDeptId().getId());
				fileInfo.setCreateDate(new Date());
				fileInfo.setSourceId(workflowApprove.getProcessInstId()+"");
				fileInfo.setSourceType(NormalClaimUtil.WORKFLOW_SOURCE_TYPE);
				fileManager.saveFileInfo(fileInfo);
			}
		}
		return result;
	}

	/**
	  * 获取指定流程下所有的活动定义ID和活动定义名称
	  * @param processDefName 流程定义名称
	  * @return
	  */
	@Override
	public List<BpmsActivity> queryActivityByProcessDefID(String processDefName) {
		IDpBpmsClientFacade client = this.getClient();
		List<BpmsActivity> list = client.queryActivityByProcessDefName(
				processDefName);
		//取出定义ID和名称
		/*for(int i=0;i<list.size();i++){
				System.out.println("活动定义ID："   + list.get(i).getActivityDefId());
				System.out.println("活动定义名称：" + list.get(i).getActivityName());
		}*/
		return list;
	}
	
	private String formatDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	private Date formatDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}


	 /**
	  * 
	  * <p>
	  * Description:判断是否能选不同意<br />
	  * </p>
	  * @author liuHuan
	  * @version 0.1 2013-8-20
	  * @return
	  * boolean
	  */
	@Override
	public boolean findCanChoose(long processInstID) {
		User user = (User) UserContext.getCurrentUser();
		Integer empId=Integer.parseInt(user.getEmpCode().getId());
		boolean isSignetManager= signetManagerManager.findIfExistEmp(empId);
		StringBuffer activityDefID = new StringBuffer();
		if(isSignetManager){
			IDpBpmsClientFacade client = this.getClient();
			Long[] workItemIds = client.getWorkItemId(processInstID,null);
			WFWorkItem workItem = null;
			for(int i = 0; i < workItemIds.length; i++) {
				long workItemId = workItemIds[i];
				try {
					//取得工作项对象
					workItem = BPSClientManagerFactory.newInstance().getWorkItemManager().queryWorkItemDetail(workItemId);
				} catch (WFServiceException e) {
					   e.printStackTrace();
				}
				activityDefID.append(workItem.getActivityDefID());
			}
			String adi=activityDefID.toString();
			if(adi.equals(SEALMANAGERFOREXP) || adi.equals(SEALLEADERFOREXP)){//印章管理员并且快递不能选不同意
				return false;
			}
		}
		return  true;
	}
	
	 /**
	  * 
	  * <p>
	  * Description:判断是有上传权限<br />
	  * </p>
	  * @author liuHuan
	  * @version 0.1 2013-8-20
	  * @return
	  * boolean
	  */
	@Override
	public boolean findCanUpload() {
		Department department = departmentService.getDeptByCode(CommonParticipant.RECOMPENSEDEPTCODE);
		Department dept = departmentService.getDeptByCode(CommonParticipant.EXPRESS_MARKETING_GROUP);
		String empCode="";
		String code = "";
		if(department!=null){
			empCode=department.getPrincipal();//理赔研究小组
		}
		if(dept != null) {
			code=dept.getPrincipal();   //快递市场营销组
		}
		User user = (User) UserContext.getCurrentUser();
		return user.getEmpCode().getEmpCode().equals(empCode) || 
				user.getEmpCode().getEmpCode().equals(code);
	}


	/**
	 * 
	 * <p>
	 * Description:服务补救详情<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2014-1-11
	 * @param workflowNo
	 * @return
	 * ServiceRecovery
	 */
	public ServiceRecovery getServiceRecoveryByOaWorkflowNum(String workflowNo){
		ServiceRecovery serviceRecovery = serviceRecoveryService.getServiceRecoveryByOaWorkFlowNum(workflowNo);
		if(serviceRecovery!=null){
			FileInfo fileInfo = new FileInfo();
			fileInfo.setSourceType(ServiceRecoveryConstant.SERVICE_RECOVERY_SOURCE_TYPE);
			fileInfo.setSourceId(serviceRecovery.getId());
			List<FileInfo> fileInfoList = fileManager
					.searchFileInfoByCondition(fileInfo, 0, 10);
			serviceRecovery.setFileInfoList(fileInfoList);
		}
		return serviceRecovery;
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:多赔详情<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2014-1-14
	 * @param workNumber
	 * @return
	 * Overpay
	 */
	@Override
	public Overpay getDetailOverpayByWorkNumber(String workNumber) {
		 return recompenseService.getDetailOverpayByWorkNumber(workNumber);
	}
	
	/**
	 * 
	 * <p>
	 * Description:获取多赔上传的文件<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2014-2-13
	 * @param processInstId
	 * @return
	 * List<FileInfo>
	 */
	@Override
	public List<FileInfo> getOverpayFileList(String processInstId){
		FileInfo fi = new FileInfo();
		fi.setSourceType(NormalClaimUtil.WORKFLOW_SOURCE_TYPE);
		fi.setSourceId(processInstId+"");
		return fileManager.searchFileInfoByCondition(fi, 0, 5);
	}
	
	public IBpsWorkflowManager getBpsWorkflowManager() {
		return bpsWorkflowManager;
	}


	public void setBpsWorkflowManager(IBpsWorkflowManager bpsWorkflowManager) {
		this.bpsWorkflowManager = bpsWorkflowManager;
	}
	
	
	
}
