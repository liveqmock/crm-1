package com.deppon.crm.module.scheduler.server.job;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.workflow.IContractApplyOperate;
import com.deppon.crm.module.client.workflow.domain.WorkFlowState;
import com.deppon.crm.module.common.file.util.ExcelExporter;
import com.deppon.crm.module.common.file.util.PropertiesUtil;
import com.deppon.crm.module.customer.server.manager.impl.ContractManager;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;
import com.deppon.crm.module.scheduler.server.service.impl.SchedulingControlService;
import com.deppon.crm.module.scheduler.shared.domain.CompareContractWorkflow;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class CompareContractWorkflowJob extends GridJob{

	private static ContractManager contractManager;
	private static IContractApplyOperate contractApplyOperate;
	private static MailSenderService mailSenderService;
	private static SchedulingControlService schedulingControlService;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		contractManager = getBean(
				"contractManager", ContractManager.class);
		
		contractApplyOperate = getBean(
				"contractApplyOperate", IContractApplyOperate.class);
		
		mailSenderService = getBean(
				"mailSenderService", MailSenderService.class);
		schedulingControlService = getBean(
				"schedulingControlService", SchedulingControlService.class);
		
		compareCrmAndOaContractWorkflowResult();
	}

	
	/**
	 * <p>
	 * Description:crm和OA合同工作流比较结果<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-1
	 * void
	 */
	public void compareCrmAndOaContractWorkflowResult() {
		System.out.println("---successful（crm和OA合同工作流比较结果）定时器----");
		Date dNow = new Date(); // 当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dNow);// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
		dBefore = calendar.getTime();
	
		//crm前一天的操作日志详情
		List<ContractOperatorLog> contractOperatorLogs = new ArrayList<ContractOperatorLog>();
		//OA前一天的合同工作流详情
		List<WorkFlowState> workFlowStates = new ArrayList<WorkFlowState>();
		//CRM与OA比较的结果集
		Map<String, List<CompareContractWorkflow>> map = new HashMap<String, List<CompareContractWorkflow>>();
		try {
			//查询前一天 合同的操作日志
			contractOperatorLogs = contractManager.queryContractOperatorLogsForDate(dBefore, dNow);
			//调用接口 查询出前一天OA产生的合同工作流数据
			workFlowStates = contractApplyOperate.queryWorkFlowApproveState(dBefore, dNow, "WFS_XZ_HT_009");
			map = compareContractResult(contractOperatorLogs, workFlowStates);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//邮件主题
		StringBuffer subject = new StringBuffer();
		subject.append("从").append(dBefore.toLocaleString()).append("至")
		.append(dNow.toLocaleString()).append("CRM系统和OA系统合同工作流对比情况");
		//邮件内容
		StringBuffer content = new StringBuffer();
		content.append("尊敬的各位领导:时间从").append(dBefore.toLocaleString()).append("至")
		.append(dNow.toLocaleString());
		//附件路径
		String oneFilePath = null;
		String twoFilePath = null;
		String threeFilePath = null;
		
		if ((contractOperatorLogs == null || contractOperatorLogs.size() <=0)&& (workFlowStates == null || workFlowStates.size() <=0)) {
			content.append("    OA系统和CRM系统没有产生合同工作流异常数据，谢谢！");
		} else if (map.size() <= 0) {
			content.append("OA系统产生合同工作流数据总共:").append(workFlowStates.size()).append("条")
			.append(",CRM系统产生合同工作流数据总共:").append(contractOperatorLogs.size()).append("条。")
			.append("\n没有产生异常数据。").append("\t两者数据不相等是因为CRM修改和改签操作，会产生两条操作日志，但共用同一个工作流号,也或者纯月发月送操作不产生工作流号,请查收，谢谢！");
		} else if (map.size() > 0){
			content.append("OA和CRM系统产生的合同工作流异常数据，").append("\nOA系统产生合同工作流数据总共:");
			if (workFlowStates.size() <= 0) {
				content.append("0条");
			} else {
				content.append(workFlowStates.size()).append("条");
			}
			content.append("。\nCRM系统产生合同工作流数据总共:");
			if (contractOperatorLogs.size() <= 0) {
				content.append("0条,");
			} else {
				content.append(contractOperatorLogs.size()).append("条,");
			}
			content.append("请查收。\n异常数据分为三种：").append("1:工作流数据CRM存在，OA不存在;")
			.append("\n2:工作流数据OA存在,CRM不存在;").append("\n3:OA和CRM都存在，针对同一个工作流号，状态不一致的数据。")
			.append("\n请查收附件,谢谢!");
			
			// OA有。crm没有
			List<CompareContractWorkflow> oALists = map.get("oALists");
			// OA没有，CRM有的
			List<CompareContractWorkflow> crmLists = map.get("crmLists");
			// OA有，CRM有。但是两边工作流状态不一致的
			List<CompareContractWorkflow> crm_oa = map.get("crm_oa");
			
			if (listIsNotEmpty(oALists)) {
				oneFilePath = exportActionLogToExcel(oALists,"OA_EXITS_CRM_NOTEXITS.xlsx");
			}
			if (listIsNotEmpty(crmLists)) {
				twoFilePath = exportActionLogToExcel(crmLists,"CRM_EXITS_OA_NOTEXITS.xlsx");
			}
			if (listIsNotEmpty(crm_oa)) {
				threeFilePath = exportActionLogToExcel(crm_oa,"CRM_OA_STATUS_IS_NOT_NULL.xlsx");
			}
		}
		//查询出需要发送邮件的邮件地址
		String _email = this.schedulingControlService.searchValueByKey("T_CRM_CRMOAWORKFLOW");

		if ((_email != null) && (!"".equals(_email))) {
			String[] _to = _email.split(";");
			try {
				MailInfo mi = new MailInfo();
				mi.setFrom(this.mailSenderService.getUserName());
				mi.setTo(_to);
				mi.setSubject(subject.toString());
				mi.setContent(content.toString());
				//定义三个文件
				File oneFile = null;
				File twoFile = null;
				File threeFile = null;
				//需要发送的文件结果
				List<File> files = new ArrayList<File>();
				if (!StringUtils.isEmpty(oneFilePath)) {
					oneFile = new File(oneFilePath);
					files.add(oneFile);
				}
				if (!StringUtils.isEmpty(twoFilePath)) {
					twoFile = new File(twoFilePath);
					files.add(twoFile);
				}
				if (!StringUtils.isEmpty(threeFilePath)) {
					threeFile = new File(threeFilePath);
					files.add(threeFile);
				}
				if (0 >= files.size()) {
					mi.setAttachments(null);
				} else {
					mi.setAttachments(initFiles(files));
				}
				this.mailSenderService.sendExtranetMail(mi);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	/**
	 * <p>
	 * Description:转化成数组格式的文件内容,目的是不让抛异常<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-2
	 * @param files
	 * @return
	 * File[]
	 */
	private File[] initFiles(List<File> list) {
		if (objectIsEmpty(list)) {
			return null;
		}
		File[] files = new File[list.size()];
		for (int i = 0; i < files.length; i++) {
				files[i] = list.get(i);
			}
		return files;
	}
	/**
	 * <p>
	 * Description:封装成xlsx文档路径<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-1
	 * @param list
	 * @param fileName
	 * @return
	 * String
	 */
	public String exportActionLogToExcel(List<CompareContractWorkflow> list,
			String fileName) {
		String createPath = PropertiesUtil.getInstance().getProperty("excelExportFilePath").trim();

		ExcelExporter exporter = new ExcelExporter();
		//封装Excel数据
		List objList = transDataResult(list);
		//封装Excel文档
		exporter.exportExcel(getHeaders(), objList, "WorkflowCompareResult", createPath,fileName);
		//文件路径
		String realPath = createPath + "/" + fileName;
		return realPath;
	}
	
	/**
	 * 
	 * <p>
	 * Description:设置Excel的列头<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-1
	 * @return
	 * List<String>
	 */
	public List<String> getHeaders() {
		List headers = new ArrayList();
		headers.add("工作流号");
		headers.add("合同序号");
		headers.add("crm工作流状态 ");
		headers.add("OA工作流状态");
		return headers;
	}
	
	/**
	 * <p>
	 * Description:封装Excel数据<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-1
	 * @param list
	 * @return
	 * List<List<Object>>
	 */
	public List<List<Object>> transDataResult(List<CompareContractWorkflow> list) {
		List objList = new ArrayList();
		for (CompareContractWorkflow ccw : list) {
			List row = new ArrayList();
			row.add(ccw.getWorkflowId());
			row.add(ccw.getContractNum());
			row.add(ccw.getCrmStatus());
			row.add(ccw.getoAStatus());
			objList.add(row);
		}
		return objList;
	}
	/**
	 * <p>
	 * Description:返回三个结果集，1.crm存在，OA不存在，2.oa存在,crm不存在 3.两边同时存在，但是工作流状态不一致<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-1
	 * @param contractOperatorLogs
	 * @param workFlowStates
	 * @return
	 * Map<String,List<CompareContractWorkflow>>
	 */
	public  Map<String, List<CompareContractWorkflow>> compareContractResult(List<ContractOperatorLog> contractOperatorLogs,
			List<WorkFlowState> workFlowStates) {
		// 返回三个结果
		Map<String, List<CompareContractWorkflow>> map = new HashMap<String, List<CompareContractWorkflow>>();
		// OA有。crm没有
		List<CompareContractWorkflow> oALists = null;
		// OA没有，CRM有的
		List<CompareContractWorkflow> crmLists = null;
		// OA有，CRM有。但是两边工作流状态不一致的
		List<CompareContractWorkflow> crm_oa = null;

		// 两边都没产生数据。则返回空。不抛异常
		if ((workFlowStates == null || workFlowStates.size() ==0)  && (contractOperatorLogs == null || contractOperatorLogs.size() ==0)) {
			return null;
		}
		// 计算OA存在 而crm不存在的
		if (workFlowStates != null && workFlowStates.size()>0) {
			oALists = compareWorkFlowIdForOAAndCRM(workFlowStates,contractOperatorLogs);
			if (listIsNotEmpty(oALists)) {
				map.put("oALists", oALists);
			}
		}
		// 计算crm存在，而OA不存在的
		if (contractOperatorLogs != null && contractOperatorLogs.size()>0) {
			crmLists = compareWorkFlowIdForCRMAndOA(contractOperatorLogs,workFlowStates);
			if (listIsNotEmpty(crmLists)) {
				map.put("crmLists", crmLists);
			}
		}
		// 计算两边都存在的数据，状态不一致的工作流号
		if (!objectIsEmpty(contractOperatorLogs) && !objectIsEmpty(workFlowStates)) {
			crm_oa = compareWorkFlowState(contractOperatorLogs, workFlowStates);
			if (listIsNotEmpty(crm_oa)) {
				map.put("crm_oa", crm_oa);
			}
		}
		return map;
	}
	
	/**
	 * <p>
	 * Description:返回OA和CRM工作流状态不一致的数据<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-31
	 * @param crmList
	 * @param workFlowStates
	 * @return
	 * Map<String,List<Object>>
	 */
	public List<CompareContractWorkflow> compareWorkFlowState(List<ContractOperatorLog> crmList,
			List<WorkFlowState> workFlowStates) {
		if (objectIsEmpty(crmList) || objectIsEmpty(workFlowStates)) {
			return null;
		}
		List<CompareContractWorkflow> list = new ArrayList<CompareContractWorkflow>();
		// 返回两边工作状态不同的值
		CompareContractWorkflow compareContractWorkflow = null;
		WorkFlowState workFlowState = null;
		boolean flag;
		// 先循环crm
		for (ContractOperatorLog coLog : crmList) {
			flag = true;
			compareContractWorkflow = new CompareContractWorkflow();
			workFlowState = new WorkFlowState();
			for (int i = 0; i < workFlowStates.size(); i++) {
				workFlowState = workFlowStates.get(i);
				//两边都存在的值，对其状态进行比较
				if (!StringUtils.isEmpty(coLog.getWorkflowId()) && !StringUtils.isEmpty(workFlowStates.get(i).getProcessInstId())
						&& coLog.getWorkflowId().equals(workFlowStates.get(i).getProcessInstId())) {
					// OA工作流 同意4 ,不同意5, 未审批2,审批中3
					// crm 1审批中 2 同意,3不同意
					if (StringUtils.isEmpty(coLog.getApprovalState()) && StringUtils.isEmpty(workFlowState.getCondition())) {
						continue;
					}
					if (coLog.getApprovalState().equals("1")&& (workFlowState.getCondition().equals("2") 
							|| workFlowState.getCondition().equals("3"))) {
						flag = true;
					} else if (coLog.getApprovalState().equals("2")&& workFlowState.getCondition().equals("4")) {
						flag = true;
					} else if (coLog.getApprovalState().equals("3")&& workFlowState.getCondition().equals("5")) {
						flag = true;
					} else {
						flag = false;
					}
				} else {
					continue;
				}
			}
			if (flag == false) {
				//不包含这个值，才进行添加
				compareContractWorkflow = completeBasecInfo(compareContractWorkflow, coLog,workFlowState);
				if (!list.contains(compareContractWorkflow)) {
					//往list添加值
					list.add(compareContractWorkflow);
				}
			}
		}
		return list;
	}
	
	/**
	 * <p>
	 * Description:对比crm和OA的工作流数据。返回crm有，而OA不存在的工作流数据<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-31
	 * @param crmList
	 * @param workFlowStates
	 * @return
	 * List<ContractOperatorLog>
	 */
	public  List<CompareContractWorkflow> compareWorkFlowIdForCRMAndOA(List<ContractOperatorLog> crmList, List<WorkFlowState> workFlowStates) {
		if (objectIsEmpty(crmList) && objectIsEmpty(workFlowStates)) {
			return null;
		}
		List<ContractOperatorLog> logs = new ArrayList<ContractOperatorLog>();
		if (objectIsEmpty(workFlowStates)) {
			//oa为空，直接返回CRM的数据
			logs = crmList;
		} else{
		boolean flag = true;
			for (ContractOperatorLog coLog : crmList) {
				for (int i = 0; i < workFlowStates.size(); i++) {
					//判断比较 值返回CRM存在，而OA不存在的工作流数据
					if (!StringUtils.isEmpty(coLog.getWorkflowId()) && !StringUtils.isEmpty(workFlowStates.get(i).getProcessInstId())
							&& coLog.getWorkflowId().equals(workFlowStates.get(i).getProcessInstId())) {
						flag = true;
						break;
					} else {
						flag = false;
						continue;
					}
				}
				if (flag == false) {
					if (!StringUtils.isEmpty(coLog.getWorkflowId())) {
						if (!logs.contains(coLog)) {
							logs.add(coLog);
						}
					}
				}
			}
		}
		return initCompareContractWorkflows(null,logs);
	}
	
	/**
	 * <p>
	 * Description:返回OA存在，而CRM不存在的工作流数据<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-31
	 * @param workFlowStates
	 * @param crmList
	 * @return
	 * List<WorkFlowState>
	 */
	public  List<CompareContractWorkflow> compareWorkFlowIdForOAAndCRM(List<WorkFlowState> workFlowStates,
			List<ContractOperatorLog> crmList) {
		List<WorkFlowState> list = new ArrayList<WorkFlowState>();
		if (crmList!= null && crmList.size() > 0) {
			boolean flag = true;
			String workflowId;
			for (WorkFlowState wf : workFlowStates) {
				for (int i = 0; i < crmList.size(); i++) {
					//判断比较 值返回OA存在，而CRM不存在的工作流数据
					if (!StringUtils.isEmpty(crmList.get(i).getWorkflowId())) {
						workflowId = crmList.get(i).getWorkflowId();
						if (!StringUtils.isEmpty(wf.getProcessInstId()) && wf.getProcessInstId().equals(workflowId)) {
							flag = true;
							break;
						} else {
							flag = false;
							continue;
						}
					} else {
						continue;
					}
					
				}
				if (flag == false) {
					if (!StringUtils.isEmpty(wf.getProcessInstId())) {
						if (!list.contains(wf)) {
							list.add(wf);
						}
					}
				}
			}
		} else {
			//crm为空 直接返回OA的数据
			list = workFlowStates;
		}
		return initCompareContractWorkflows(list,null);
	}

	/**
	 * <p>
	 * Description:初始化封装最终返回的对象<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-1
	 * @param workFlowStates
	 * @param crmList
	 * @return
	 * List<CompareContractWorkflow>
	 */
	public  List<CompareContractWorkflow> initCompareContractWorkflows(List<WorkFlowState> workFlowStates,
			List<ContractOperatorLog> crmList) {
		List<CompareContractWorkflow> list = new ArrayList<CompareContractWorkflow>();
		CompareContractWorkflow compareContractWorkflow = null;
		// 如果OA的为空，那么只封装CRM
		if (objectIsEmpty(workFlowStates) && !objectIsEmpty(crmList)) {
			for (ContractOperatorLog contractOperatorLog : crmList) {
				if (!StringUtils.isEmpty(contractOperatorLog.getWorkflowId())) {
					compareContractWorkflow = new CompareContractWorkflow();
					compareContractWorkflow = completeBasecInfo(compareContractWorkflow,contractOperatorLog,null);
					list.add(compareContractWorkflow);
				}
			}
		}
		// 如果CRM为空，那么只封装OA
		else if (objectIsEmpty(crmList)&& !objectIsEmpty(workFlowStates)) {
			for (WorkFlowState workFlowState : workFlowStates) {
				if (!StringUtils.isEmpty(workFlowState.getProcessInstId())) {
					compareContractWorkflow = new CompareContractWorkflow();
					compareContractWorkflow = completeBasecInfo(compareContractWorkflow,null,workFlowState);
					list.add(compareContractWorkflow);
				}
			}
		} else {
			//两边都存在，同时封装
			for (WorkFlowState ccw : workFlowStates) {
				compareContractWorkflow = new CompareContractWorkflow();
				for (ContractOperatorLog col : crmList) {
					if (!StringUtils.isEmpty(ccw.getProcessInstId()) 
							&& !StringUtils.isEmpty(col.getWorkflowId())
							&& ccw.getProcessInstId().equals(col.getWorkflowId())) {
						compareContractWorkflow = completeBasecInfo(compareContractWorkflow,col,ccw);
						list.add(compareContractWorkflow);
					} else {
						continue;
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * <p>
	 * Description:根据情况封装对象值<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-1
	 * @param compareContractWorkflow
	 * @param contractOperatorLog
	 * @param workFlowState
	 * @return
	 * CompareContractWorkflow
	 */
	private  CompareContractWorkflow completeBasecInfo(CompareContractWorkflow compareContractWorkflow,
			ContractOperatorLog contractOperatorLog, WorkFlowState workFlowState) {
		if (objectIsEmpty(contractOperatorLog) && !objectIsEmpty(workFlowState)) {
			//工作流号
			compareContractWorkflow.setWorkflowId(workFlowState.getProcessInstId());
			//合同编码
			compareContractWorkflow.setContractNum(workFlowState.getBizCode());
			//OA工作流同意：4 ,不同意：5, 未审批：2,审批中：3
			if (workFlowState.getCondition().equals("4")) {
				compareContractWorkflow.setoAStatus("同意");
			} else if (workFlowState.getCondition().equals("5")) {
				compareContractWorkflow.setoAStatus("不同意");
			} else if (workFlowState.getCondition().equals("2")) {
				compareContractWorkflow.setoAStatus("未审批");
			} else if (workFlowState.getCondition().equals("3")) {
				compareContractWorkflow.setoAStatus("审批中");
			}
			//工作流状态
			compareContractWorkflow.setCrmStatus(null);
		} else if (!objectIsEmpty(contractOperatorLog) && objectIsEmpty(workFlowState)) {
			compareContractWorkflow.setWorkflowId(contractOperatorLog.getWorkflowId());
			// 审批中
			if (contractOperatorLog.getApprovalState().equals("1")) {
				compareContractWorkflow.setCrmStatus("审批中");
			} else if (contractOperatorLog.getApprovalState().equals("2")) {
				// 同意
				compareContractWorkflow.setCrmStatus("已同意");
			} else if (contractOperatorLog.getApprovalState().equals("3")) {
				// 拒绝
				compareContractWorkflow.setCrmStatus("拒绝");
			}
			compareContractWorkflow.setoAStatus(null);
			compareContractWorkflow.setContractNum(contractOperatorLog.getContract().getContractNum());
			
		} else {
			compareContractWorkflow.setWorkflowId(contractOperatorLog.getWorkflowId());
			// 审批中
			if (contractOperatorLog.getApprovalState().equals("1")) {
				compareContractWorkflow.setCrmStatus("审批中");
			} else if (contractOperatorLog.getApprovalState().equals("2")) {
				// 同意
				compareContractWorkflow.setCrmStatus("已同意");
			} else if (contractOperatorLog.getApprovalState().equals("3")) {
				// 拒绝
				compareContractWorkflow.setCrmStatus("拒绝");
			}
			//OA工作流同意：4 ,不同意：5, 未审批：2,审批中：3
			if (workFlowState.getCondition().equals("4")) {
				compareContractWorkflow.setoAStatus("同意");
			} else if (workFlowState.getCondition().equals("5")) {
				compareContractWorkflow.setoAStatus("不同意");
			} else if (workFlowState.getCondition().equals("2")) {
				compareContractWorkflow.setoAStatus("未审批");
			} else if (workFlowState.getCondition().equals("3")) {
				compareContractWorkflow.setoAStatus("审批中");
			}
			compareContractWorkflow.setContractNum(workFlowState.getBizCode());
		}
		return compareContractWorkflow;
	}
	/**
	 * <p>
	 * Description:比较对象是否为空<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-1
	 * @param obj
	 * @return
	 * boolean
	 */
	public  boolean objectIsEmpty(Object obj){
		if(obj == null) return true;
		if(obj instanceof String){
			if(((String)obj).trim().equals("")){
				return true;
			}
		}else if(obj instanceof List){
			List list = (List)obj;
			return list.isEmpty();
		}else if(obj instanceof Set){
			Set set = (Set) obj;
			return set.isEmpty();
		}else if(obj instanceof Map){
			Map map = (Map)obj;
			return map.isEmpty();
		}
		return false;
	}
	
	/**
	 * <p>
	 * Description:判断合同比较的结果集是否为空<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-1
	 * @param list
	 * @return
	 * boolean
	 */
	public  boolean listIsNotEmpty(List<CompareContractWorkflow> list) {
		if (null != list && 0 < list.size()) {
			return true;
		} else {
			return false;
		}
	}

}
