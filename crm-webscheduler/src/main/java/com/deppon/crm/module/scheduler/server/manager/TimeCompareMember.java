package com.deppon.crm.module.scheduler.server.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.DateUtils;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.client.customer.ICustomerOperate;
import com.deppon.crm.module.client.workflow.IContractApplyOperate;
import com.deppon.crm.module.client.workflow.domain.WorkFlowState;
import com.deppon.crm.module.common.file.util.ExcelExporter;
import com.deppon.crm.module.common.file.util.PropertiesUtil;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.scheduler.server.service.ICompareDataService;
import com.deppon.crm.module.scheduler.server.service.ISchedulingControlService;
import com.deppon.crm.module.scheduler.shared.domain.CompareAccount;
import com.deppon.crm.module.scheduler.shared.domain.CompareContact;
import com.deppon.crm.module.scheduler.shared.domain.CompareContract;
import com.deppon.crm.module.scheduler.shared.domain.CompareContractDept;
import com.deppon.crm.module.scheduler.shared.domain.CompareContractWorkflow;
import com.deppon.crm.module.scheduler.shared.domain.CompareErrorInfo;
import com.deppon.crm.module.scheduler.shared.domain.CompareMember;
import com.deppon.crm.module.scheduler.shared.domain.CompareWorkflow;
import com.deppon.erp.custAndOrder.Contract;
import com.deppon.erp.custAndOrder.ContractDept;
import com.deppon.erp.custAndOrder.ExceptionCust;
import com.deppon.erp.custAndOrder.LinkMan;
import com.deppon.erp.custAndOrder.OpenBank;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;

/**
 * @作者：罗典
 * @时间：2012-8-23
 * @描述：会员及相关基本数据对比定时器
 * */
public class TimeCompareMember {
	ICompareDataService compareDataService;
	// ERP客户查询接口
	private ICustomerOperate customerOperate;
	// 邮件发送服务
	private MailSenderService mailSenderService;
	// OA工作流查询接口
	private IContractApplyOperate contractApplyOperate;
	// 调度服务
	private ISchedulingControlService schedulingControlService;
	// 对比模板文件
	private static final String EXCEL_SRC = "CompareCustErro.xlsx";
	
	private IContractManager contractManager;
	private static Logger logger = Logger.getLogger(TimeCompareMember.class);

	public IContractManager getContractManager() {
		return contractManager;
	}

	public void setContractManager(IContractManager contractManager) {
		this.contractManager = contractManager;
	}
	public void execute() {
		long time1 = System.currentTimeMillis();
		logger.info("TimeCompareMember.compareCustDataByCrm Call........"
				+ new Date());
		try{
			//客户数据对比
			compareCustDataByCrm();
		}catch(Exception e){
			e.printStackTrace();
		}
		long time2 = System.currentTimeMillis();
		logger.info("TimeCompareMember.compareCustDataByCrm use time :"
				+ (time1 - time2) + "ms \n");
		logger.info("TimeCompareMember.compareWorkflowData Call........"
				+ new Date());
		try{
			//工作流对比
			compareWorkflowData();
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("compareWorkflowData use time :"
				+ (time2 - System.currentTimeMillis()) + "ms \n");
		logger.info("TimeCompareMember end.....");
	}

	/**
	 * @作者：罗典
	 * @描述：对比客户数据
	 * @时间：2012-10-12
	 * */
	public void compareCustDataByCrm() {
		Map<String, CompareMember> custNumberMap = new HashMap<String, CompareMember>();
		Map<String, CompareMember> memberList = compareDataService
				.queryMember();
		Map<String, CompareMember> accountList = compareDataService
				.queryMemberByAccount();
		Map<String, CompareMember> contractList = compareDataService
				.queryMemberByContract();
		Map<String, CompareMember> linkmanList = compareDataService
				.queryMemberByLinkman();
		custNumberMap = memberList;
		Iterator<String> iterator = accountList.keySet().iterator();
		while (iterator.hasNext()) {
			String custNumber = iterator.next();
			if (!custNumberMap.containsKey(custNumber)) {
				custNumberMap.put(custNumber, accountList.get(custNumber));
			}
		}
		iterator = contractList.keySet().iterator();
		while (iterator.hasNext()) {
			String custNumber = iterator.next();
			if (!custNumberMap.containsKey(custNumber)) {
				custNumberMap.put(custNumber, contractList.get(custNumber));
			}
		}
		iterator = linkmanList.keySet().iterator();
		while (iterator.hasNext()) {
			String custNumber = iterator.next();
			if (!custNumberMap.containsKey(custNumber)) {
				custNumberMap.put(custNumber, linkmanList.get(custNumber));
			}
		}
		if (custNumberMap == null || custNumberMap.size() == 0) {
			logger.info("未查询到ERP数据信息");
			return;
		}
		logger.info("Member info total :" + custNumberMap.size());
		// 清空内存
		memberList.clear();
		accountList.clear();
		contractList.clear();
		linkmanList.clear();

		List<String> custList = new ArrayList<String>();
		iterator = custNumberMap.keySet().iterator();
		while (iterator.hasNext()) {
			custList.add(iterator.next());
		}

		List<ExceptionCust> erpCustList = new ArrayList<ExceptionCust>();
		List<String> custTempList = new ArrayList<String>();
		for (int i = 0; i < custList.size(); i++) {
			custTempList.add(custList.get(i));
			if (i == 199 || i == custList.size() - 1) {
				try {
					erpCustList.addAll(customerOperate
							.queryERPCustInfo(custTempList));
				} catch (CrmBusinessException e) {
					e.printStackTrace();
				}
				custTempList.clear();
			}
		}
		compareCust(erpCustList, custNumberMap);
	}

	/**
	 * @作者:罗典
	 * @时间：2012-8-31
	 * @描述：对比ERP客户信息和CRM客户信息
	 * */
	private void compareCust(List<ExceptionCust> erpCustList,
			Map<String, CompareMember> crmCustMap) {
		List<CompareErrorInfo> errorList = new ArrayList<CompareErrorInfo>();
		for (ExceptionCust erpCustInfo : erpCustList) {
			if (crmCustMap.containsKey(erpCustInfo.getNumber())) {
				CompareMember crmCustInfo = crmCustMap.get(erpCustInfo
						.getNumber());
				List<CompareAccount> crmAccountList = crmCustInfo
						.getAccountList();
				List<CompareContact> crmContactList = crmCustInfo
						.getContactList();
				List<CompareContract> crmContractList = crmCustInfo
						.getContractList();

				List<Contract> erpContractList = new ArrayList<Contract>();
				erpContractList = erpCustInfo.getContracts();
				List<LinkMan> erpLinkmanList = new ArrayList<LinkMan>();
				erpLinkmanList = erpCustInfo.getLinkMans();
				List<OpenBank> erpOpenBanksList = new ArrayList<OpenBank>();
				erpOpenBanksList = erpCustInfo.getOpenBanks();
				// 对比会员基本信息
				// 客户编码
				String custNum = erpCustInfo.getNumber();
				Double erpCreditrated = erpCustInfo.getCreditrated() == null ? 0
						: erpCustInfo.getCreditrated().doubleValue();
				collectInfo("客户编码：" + custNum, "客户临欠额度 ", erpCreditrated,
						crmCustInfo.getProcRedit(), errorList);
				collectInfo("客户编码：" + custNum, "客户主键ID  ",
						erpCustInfo.getCrmid(), crmCustInfo.getCrmId(),
						errorList);
				if (crmCustInfo.getCustStatus() != null
						&& crmCustInfo.getCustStatus().equals("1")) {
					crmCustInfo.setCustStatus("0");
				}
				collectInfo("客户编码：" + custNum, "客户状态   ",
						erpCustInfo.getCrmcancal(),
						crmCustInfo.getCustStatus(), errorList);
				if (crmContactList.size() != erpLinkmanList.size()) {
					CompareErrorInfo info = new CompareErrorInfo();
					info.setKeyInfo("客户编码：" + custNum);
					info.setMismatchingInfo("联系人数量不一致 crm:"
							+ crmContactList.size() + " erp:"
							+ erpLinkmanList.size());
					info.setCompareTime(DateUtils.getCurrentDate());
					errorList.add(info);
				}
				// 对比联系人信息
				for (CompareContact crmContact : crmContactList) {
					// 联系人ID
					String linkId = crmContact.getCrmId();
					for (LinkMan erpLinkMan : erpLinkmanList) {
						if (erpLinkMan.getCrmid().equals(crmContact.getCrmId())) {
							collectInfo("联系人CRMID：" + linkId, "联系人编码 ",
									erpLinkMan.getNumber(),
									crmContact.getNumber(), errorList);
							if (crmContact.getStatus() != null
									&& crmContact.getStatus().equals("1")) {
								crmContact.setStatus("0");
							}
							collectInfo("联系人CRMID：" + linkId, "联系人状态 ",
									erpLinkMan.getStatus(),
									crmContact.getStatus(), errorList);
							collectInfo("联系人CRMID：" + linkId, "联系人手机号码 ",
									erpLinkMan.getMobiletel(),
									crmContact.getMobilePhone(), errorList);
						}
					}
				}
				// 对比账号信息
				if (crmAccountList.size() != erpOpenBanksList.size()) {
					CompareErrorInfo info = new CompareErrorInfo();
					info.setKeyInfo("客户编码：" + custNum);
					info.setMismatchingInfo("账号数量不一致 crm:"
							+ crmAccountList.size() + " erp:"
							+ erpOpenBanksList.size());
					info.setCompareTime(DateUtils.getCurrentDate());
					errorList.add(info);
				}
				for (CompareAccount crmAccount : crmAccountList) {
					// 账号ID
					String accountId = crmAccount.getCrmId();
					for (OpenBank erpBank : erpOpenBanksList) {
						if (erpBank.getCrmid().equals(crmAccount.getCrmId())) {
							collectInfo("账号CRMID：" + accountId, "银行账号 ",
									erpBank.getAccounts(),
									crmAccount.getBankAccount(), errorList);
							collectInfo("账号CRMID：" + accountId, "银行开户行ID ",
									erpBank.getBankid(),
									crmAccount.getBankId(), errorList);
							collectInfo("账号CRMID：" + accountId, "银行支行ID ",
									erpBank.getBankid(),
									crmAccount.getBankId(), errorList);
						}
					}
				}
				// 对比合同信息
				if (crmContractList.size() != erpContractList.size()) {
					CompareErrorInfo info = new CompareErrorInfo();
					info.setKeyInfo("客户编码：" + custNum);
					info.setMismatchingInfo("合同数量不一致 crm:"
							+ crmContractList.size() + " erp:"
							+ erpContractList.size());
					info.setCompareTime(DateUtils.getCurrentDate());
					errorList.add(info);
				}
				for (CompareContract crmContract : crmContractList) {
					// 合同编码
					String contractNum = crmContract.getNumber();
					for (Contract erpContract : erpContractList) {
						if (erpContract.getContractNum() == crmContract
								.getNumber()) {
							if (crmContract.getStatus() != null
									&& crmContract.getStatus().equals("1")) {
								crmContract.setStatus("0");
							}
							collectInfo("合同编码：" + contractNum, "合同状态 ",
									erpContract.getContractStatus(),
									crmContract.getStatus(), errorList);
							// 对比合同部门信息
							List<CompareContractDept> contractDeptlist = new ArrayList<CompareContractDept>();
							contractDeptlist = crmContract
									.getContractDeptList();
							List<ContractDept> contractDeptList = new ArrayList<ContractDept>();
							contractDeptList = erpContract.getContractDepts();
							if (contractDeptlist.size() != contractDeptList
									.size()) {
								CompareErrorInfo info = new CompareErrorInfo();
								info.setKeyInfo("合同ID：" + contractNum);
								info.setMismatchingInfo("合同部门数量不一致 crm:"
										+ contractDeptlist.size() + " erp:"
										+ contractDeptList.size());
								info.setCompareTime(DateUtils.getCurrentDate());
								errorList.add(info);
							}
							for (CompareContractDept crmDept : contractDeptlist) {
								// 合同部门CRMID
								String contractDeptId = crmDept.getCrmId();
								for (ContractDept erpCDept : contractDeptList) {
									collectInfo("合同部门ID：" + contractDeptId,
											"合同部门状态 ", crmDept.getState(),
											erpCDept.getState(), errorList);
									collectInfo("合同部门ID：" + contractDeptId,
											"合同部门标杆编码 ", crmDept.getDeptCode(),
											erpCDept.getStandardCode(),
											errorList);
								}
							}
						}
					}
				}
			}
		}
		if (errorList != null) {
			createExcel(errorList, "CompareCustErro.xlsx");
		}
	}

	/**
	 * @作者：罗典
	 * @描述：对比工作流基本数据
	 * @时间：2012-10-12
	 * */
	public void compareWorkflowData() {
		// 异常数据集合
		List<CompareErrorInfo> errorList = new ArrayList<CompareErrorInfo>();
		Map<String, CompareWorkflow> crmContractMap = new HashMap<String, CompareWorkflow>();
		crmContractMap = compareDataService.queryContractWorkflow();
		Map<String, CompareWorkflow> crmNormalRecMap = new HashMap<String, CompareWorkflow>();
		crmNormalRecMap = compareDataService.queryNormalRecomWorkflow();
		Map<String, CompareWorkflow> crmMuchRecMap = new HashMap<String, CompareWorkflow>();
		crmMuchRecMap = compareDataService.queryMuchRecomWorkflow();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		try {
			List<WorkFlowState> oaContractList = contractApplyOperate
					.queryWorkFlowApproveState(c.getTime(), new Date(),
							"WFS_HT_001");
			List<WorkFlowState> oaMuchRecList = contractApplyOperate
					.queryWorkFlowApproveState(c.getTime(), new Date(),
							"WFS_LP_001");
			List<WorkFlowState> oaNormalRecList = contractApplyOperate
					.queryWorkFlowApproveState(c.getTime(), new Date(),
							"WFS_LP_002");

			// 对比合同
			String contractNums = ""; // OA有，CRM没有的工作流编号
			// OA工作流数量
			int oaCount = 0;
			if(oaContractList!=null){
				oaCount = oaContractList.size();
			}
			// crm工作流数量
			int crmCount=0;
			if(crmContractMap != null){
				crmCount = crmContractMap.size();
			}
			for (WorkFlowState oaWorkFlowState : oaContractList) {
				// 合同编码
				String contractNum = oaWorkFlowState.getBizCode();
				if (crmContractMap.containsKey(contractNum)) {
					CompareWorkflow crmCompareWorkflow = crmContractMap
							.get(contractNum);
					// OA工作流同意：4 ,不同意：5, 未审批：2,审批中：3
					if (oaWorkFlowState.getCondition() == "4") {
						oaWorkFlowState
								.setCondition(Constant.CONTRACT_STATUS_EFFECT);
					} else if (oaWorkFlowState.getCondition() == "5") {
						oaWorkFlowState
								.setCondition(Constant.CONTRACT_STATUS_UNEFFECT);
					} else if (oaWorkFlowState.getCondition() == "2"
							|| oaWorkFlowState.getCondition() == "3") {
						oaWorkFlowState
								.setCondition(Constant.CONTRACT_STATUS_INPROCESS);
					}
					collectInfo("合同编码：" + contractNum, "合同状态 ",
							crmCompareWorkflow.getState(),
							oaWorkFlowState.getCondition(), errorList);
					crmContractMap.remove(contractNum);
				} else {
					contractNums += "##" + oaWorkFlowState.getBizCode()+"_"+oaWorkFlowState.getProcessInstId();
				}
			}
			if(contractNums!= null && !contractNums.equals("")){
				CompareErrorInfo errorInfo = new CompareErrorInfo();
				errorInfo.setCompareTime(DateUtils.getCurrentDate());
				errorInfo.setKeyInfo("合同工作流数量");
				errorInfo.setMismatchingInfo("数量为：crm:" + crmCount
						+ " oa:" + oaCount + " OA有，CRM没有的工作流编号："
						+ contractNums);
				errorList.add(errorInfo);
				contractNums = "";
				logger.info(JsonMapperUtil.writeValue(errorInfo));
			}
			if(crmContractMap!=null && crmContractMap.size()>0){
				String info = "";
				for(CompareWorkflow workFlow : crmContractMap.values()){
					info += "##"+workFlow.getBizNum()+"_"+workFlow.getWorkflowNum();
				}
				CompareErrorInfo errorInfo = new CompareErrorInfo();
				errorInfo.setCompareTime(DateUtils.getCurrentDate());
				errorInfo.setKeyInfo("合同工作流数量");
				errorInfo.setMismatchingInfo("数量为：crm:" + crmCount
						+ " oa:" + oaCount + " CRM有，OA没有的工作流编号："
						+ info);
				errorList.add(errorInfo);
				crmContractMap.clear();
				logger.info(JsonMapperUtil.writeValue(errorInfo));
			}
			crmCount =0;
			oaCount=0;
			if(oaNormalRecList!=null){
				oaCount = oaNormalRecList.size();
			}
			if(crmNormalRecMap!=null){
				crmCount = crmNormalRecMap.size();
			}
			// 对比正常理赔
			for (WorkFlowState oaWorkFlowState : oaNormalRecList) {
				// 理赔编码
				String recompenseNum = oaWorkFlowState.getBizCode();
				if (crmNormalRecMap.containsKey(recompenseNum)) {
					CompareWorkflow crmCompareWorkflow = crmNormalRecMap
							.get(recompenseNum);
					// OA工作流同意：4 ,不同意：5, 未审批：2,审批中：3
					if (oaWorkFlowState.getCondition() == "4") {
						oaWorkFlowState
								.setCondition(Constants.WORKFLOW_STATUS_APPROVE
										+ "");
					} else if (oaWorkFlowState.getCondition() == "5") {
						oaWorkFlowState
								.setCondition(Constants.WORKFLOW_STATUS_REFUSE
										+ "");
					} else if (oaWorkFlowState.getCondition() == "2"
							|| oaWorkFlowState.getCondition() == "3") {
						oaWorkFlowState
								.setCondition(Constants.WORKFLOW_STATUS_SUBMIT
										+ "");
					}
					collectInfo("正常理赔编码：" + recompenseNum, "正常理赔工作流状态 ",
							crmCompareWorkflow.getState(),
							oaWorkFlowState.getCondition(), errorList);
					crmNormalRecMap.remove(recompenseNum);
				} else {
					contractNums += "##" + oaWorkFlowState.getBizCode()+"_"+oaWorkFlowState.getProcessInstId();
				}
			}
			if (contractNums!=null && !contractNums.equals("")) {
				CompareErrorInfo errorInfo = new CompareErrorInfo();
				errorInfo.setCompareTime(DateUtils.getCurrentDate());
				errorInfo.setKeyInfo("正常理赔工作流数量");
				errorInfo.setMismatchingInfo("数量为：crm:"
						+ crmCount + " oa:"	+ oaCount + " oa有，CRM没有" + contractNums);
				errorList.add(errorInfo);
				contractNums="";
				logger.info(JsonMapperUtil.writeValue(errorInfo));
			}
			if(crmNormalRecMap!=null && crmNormalRecMap.size()>0){
				String info = "";
				for(CompareWorkflow workFlow : crmNormalRecMap.values()){
					info += "##"+workFlow.getBizNum()+"_"+workFlow.getWorkflowNum();
				}
				CompareErrorInfo errorInfo = new CompareErrorInfo();
				errorInfo.setCompareTime(DateUtils.getCurrentDate());
				errorInfo.setKeyInfo("正常理赔工作流数量");
				errorInfo.setMismatchingInfo("数量为：crm:"
						+ crmCount + " oa:"	+ oaCount +" CRM有,OA没有"+info);
				errorList.add(errorInfo);
				logger.info(JsonMapperUtil.writeValue(errorInfo));
			}
			// 对比多赔
			crmCount =0;
			oaCount=0;
			if(oaNormalRecList!=null){
				oaCount = oaMuchRecList.size();
			}
			if(crmNormalRecMap!=null){
				crmCount = crmMuchRecMap.size();
			}
			for (WorkFlowState oaWorkFlowState : oaMuchRecList) {
				// 理赔编码
				String recompenseNum = oaWorkFlowState.getBizCode();
				if (crmMuchRecMap.containsKey(recompenseNum)) {
					CompareWorkflow crmCompareWorkflow = crmMuchRecMap
							.get(recompenseNum);
					// OA工作流同意：4 ,不同意：5, 未审批：2,审批中：3
					if (oaWorkFlowState.getCondition() == "4") {
						oaWorkFlowState
								.setCondition(Constants.WORKFLOW_STATUS_APPROVE
										+ "");
					} else if (oaWorkFlowState.getCondition() == "5") {
						oaWorkFlowState
								.setCondition(Constants.WORKFLOW_STATUS_REFUSE
										+ "");
					} else if (oaWorkFlowState.getCondition() == "2"
							|| oaWorkFlowState.getCondition() == "3") {
						oaWorkFlowState
								.setCondition(Constants.WORKFLOW_STATUS_SUBMIT
										+ "");
					}
					collectInfo("多赔编码：" + recompenseNum, "多赔理赔工作流状态 ",
							crmCompareWorkflow.getState(),
							oaWorkFlowState.getCondition(), errorList);
					crmMuchRecMap.remove(recompenseNum);
				} else {
					contractNums += "##" + oaWorkFlowState.getBizCode()+"_"+oaWorkFlowState.getProcessInstId();
				}
			}
			if (contractNums!=null && !contractNums.equals("")) {
				CompareErrorInfo errorInfo = new CompareErrorInfo();
				errorInfo.setCompareTime(DateUtils.getCurrentDate());
				errorInfo.setKeyInfo("多赔工作流数量");
				errorInfo.setMismatchingInfo("数量为：crm:" + crmCount
						+ " oa:" + oaCount+" oa有,crm没有"+contractNums);
				errorList.add(errorInfo);
				logger.info(JsonMapperUtil.writeValue(errorInfo));
			}
			if(crmMuchRecMap!= null && crmMuchRecMap.size()>0){
				String info = "";
				for(CompareWorkflow workFlow : crmMuchRecMap.values()){
					info += "##"+workFlow.getBizNum()+"_"+workFlow.getWorkflowNum();
				}
				CompareErrorInfo errorInfo = new CompareErrorInfo();
				errorInfo.setCompareTime(DateUtils.getCurrentDate());
				errorInfo.setKeyInfo("多赔工作流数量");
				errorInfo.setMismatchingInfo("数量为：crm:" + crmCount
						+ " oa:" + oaCount+ " crm有,oa没有"+info);
				errorList.add(errorInfo);
				logger.info(JsonMapperUtil.writeValue(errorInfo));
			}
			createExcel(errorList, "CompareWorkflow.xlsx");
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @作者：罗典
	 * @时间：2012-8-31
	 * @描述：根据信息生成EXCEL文件，并发送邮件
	 * */
	private void createExcel(List<CompareErrorInfo> errorList, String excelName) {
		if (errorList == null || errorList.size() == 0) {
			return;
		}
		StringBuffer errorInfo = new StringBuffer();
		for(CompareErrorInfo info : errorList){
			errorInfo.append("keyInfo: "+info.getKeyInfo()+"\t");
			errorInfo.append("descrition: "+info.getMismatchingInfo()+"\t");
			errorInfo.append("compareTime: "+info.getCompareTime()+"\n");
		}
		/*String path = com.deppon.crm.module.common.file.util.PropertiesUtil
				.getInstance().getProperty("excelExportTemplatePath").trim();
		String filePath = path + "/" + EXCEL_SRC;// 组织文件路径
		ExcelExporter exporter = new ExcelExporter();
		XSSFWorkbook wb = exporter.getExcel07Wb(filePath);// 获得工作薄
		for (int i = 0; i < errorList.size(); i++) {
			CompareErrorInfo compareInfo = errorList.get(i);
			wb = exporter.setExcel07WbValue(wb, i + 1, 0,
					compareInfo.getKeyInfo());
			wb = exporter.setExcel07WbValue(wb, i + 1, 1,
					compareInfo.getMismatchingInfo());
			wb = exporter.setExcel07WbValue(wb, i + 1, 2,
					compareInfo.getCompareTime());
		}
		String createPath = com.deppon.crm.module.common.file.util.PropertiesUtil
				.getInstance().getProperty("excelExportFilePath").trim();
		String fileName = FileUtil.createFileName(excelName);
		String realPath = exporter.createExcel(wb, createPath, fileName);*/
		String _email = schedulingControlService
				.searchValueByKey("T_CRM_INTERFACE");
		logger.info("send Email......."+_email);
		if (_email != null && !"".equals(_email)) {
			String[] _to = _email.split(";");
			try {
				MailInfo mi = new MailInfo();
				mi.setFrom(mailSenderService.getUserName());// 谁发
				mi.setTo(_to);// 发给谁
				mi.setSubject("数据对比");
				mi.setContent(errorInfo.toString());
				/*File file = new File(realPath);
				File[] files = { file };
				mi.setAttachments(files);*/
				mailSenderService.sendExtranetMail(mi);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		logger.info("send Email Success......"+_email);

	}

	private boolean compareString(String arg0, String arg1) {
		if (arg0.equals(arg1)) {
			return true;
		}
		return false;
	}

	private void collectInfo(String keyInfo, String msg, Object erp,
			Object crm, List<CompareErrorInfo> errorList) {
		boolean needRecord = false;
		if (erp instanceof String) {
			if (!compareString(erp + "", crm + "")) {
				needRecord = true;
			}
		} else if (erp instanceof Double) {
			if (((Double) erp).intValue() != ((Double) crm).intValue()) {
				needRecord = true;
			}
		} else {
			if (erp != crm) {
				needRecord = true;
			}
		}
		if (needRecord) {
			CompareErrorInfo info = new CompareErrorInfo();
			info.setCompareTime(DateUtils.getCurrentDate());
			info.setKeyInfo(keyInfo);
			info.setMismatchingInfo(msg + "[crm:" + crm + " erp:" + erp);
			errorList.add(info);
			try {
				logger.info(JsonMapperUtil.writeValue(info));
			} catch (CrmBusinessException e) {
				e.printStackTrace();
			}
		}
	}

	public ICompareDataService getCompareDataService() {
		return compareDataService;
	}

	public void setCompareDataService(ICompareDataService compareDataService) {
		this.compareDataService = compareDataService;
	}

	public ISchedulingControlService getSchedulingControlService() {
		return schedulingControlService;
	}

	public void setSchedulingControlService(
			ISchedulingControlService schedulingControlService) {
		this.schedulingControlService = schedulingControlService;
	}

	public ICustomerOperate getCustomerOperate() {
		return customerOperate;
	}

	public void setCustomerOperate(ICustomerOperate customerOperate) {
		this.customerOperate = customerOperate;
	}

	public MailSenderService getMailSenderService() {
		return mailSenderService;
	}

	public void setMailSenderService(MailSenderService mailSenderService) {
		this.mailSenderService = mailSenderService;
	}

	public IContractApplyOperate getContractApplyOperate() {
		return contractApplyOperate;
	}

	public void setContractApplyOperate(
			IContractApplyOperate contractApplyOperate) {
		this.contractApplyOperate = contractApplyOperate;
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
		
		if (!(0 < contractOperatorLogs.size()) && (0 < workFlowStates.size())) {
			content.append("    OA系统和CRM系统没有产生合同工作流异常数据，谢谢！");
		} else if (!(0 < map.size())) {
			content.append("OA系统产生合同工作流数据总共:").append(workFlowStates.size()).append("条")
			.append(",CRM系统产生合同工作流数据总共:").append(contractOperatorLogs.size()).append("条。")
			.append("\n没有产生异常数据。").append("\t两者数据不相等是因为CRM修改和改签操作，会产生两条操作日志，但共用同一个工作流号,也或者纯月发月送操作不产生工作流号,请查收，谢谢！");
		} else {
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
			
			if (!listIsEmpty(oALists)) {
				oneFilePath = exportActionLogToExcel(oALists,"OA_EXITS存在_CRM_NOTEXITS不存在.xlsx");
			}
			if (!listIsEmpty(crmLists)) {
				twoFilePath = exportActionLogToExcel(crmLists,"CRM_EXITS存在_OA_NOTEXITS不存在.xlsx");
			}
			if (!listIsEmpty(crm_oa)) {
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
				if (!objectIsEmpty(oneFilePath)) {
					oneFile = new File(oneFilePath);
					files.add(oneFile);
				}
				if (!objectIsEmpty(twoFilePath)) {
					twoFile = new File(twoFilePath);
					files.add(twoFile);
				}
				if (!objectIsEmpty(threeFilePath)) {
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
		if (objectIsEmpty(workFlowStates)&& objectIsEmpty(contractOperatorLogs)) {
			return null;
		}
		// 计算OA存在 而crm不存在的
		if (!objectIsEmpty(workFlowStates)) {
			oALists = compareWorkFlowIdForOAAndCRM(workFlowStates,contractOperatorLogs);
			if (!listIsEmpty(oALists)) {
				map.put("oALists", oALists);
			}
		}
		// 计算crm存在，而OA不存在的
		if (!objectIsEmpty(contractOperatorLogs)) {
			crmLists = compareWorkFlowIdForCRMAndOA(contractOperatorLogs,workFlowStates);
			if (!listIsEmpty(crmLists)) {
				map.put("crmLists", crmLists);
			}
		}
		// 计算两边都存在的数据，状态不一致的工作流号
		if (!objectIsEmpty(contractOperatorLogs) && !objectIsEmpty(workFlowStates)) {
			crm_oa = compareWorkFlowState(contractOperatorLogs, workFlowStates);
			if (!listIsEmpty(crm_oa)) {
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
			logger.info("this param Data is error");
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
			logger.info("this param Data is error");
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
		if (objectIsEmpty(workFlowStates) && objectIsEmpty(crmList)) {
			logger.info("this param Data is error");
			return null;
		}
		List<WorkFlowState> list = new ArrayList<WorkFlowState>();
		if (objectIsEmpty(crmList)) {
			//crm为空 直接返回OA的数据
			list = workFlowStates;
		} else {
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
	public  boolean listIsEmpty(List<CompareContractWorkflow> list) {
		if (null == list || 0 >= list.size()) {
			return true;
		} else {
			return false;
		}
	}
}
