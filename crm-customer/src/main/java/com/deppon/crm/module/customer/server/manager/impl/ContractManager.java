package com.deppon.crm.module.customer.server.manager.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.deppon.crm.module.bps.server.manager.IBpsWorkflowManager;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.sync.impl.CustomerInfoLog;
import com.deppon.crm.module.client.workflow.IContractApplyOperate;
import com.deppon.crm.module.client.workflow.domain.ContractApplyType;
import com.deppon.crm.module.client.workflow.domain.ContractInfo;
import com.deppon.crm.module.client.workflow.domain.ContractNounInfo;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.customer.server.manager.ContractValidator;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.server.manager.IPrefrentialDealManager;
import com.deppon.crm.module.customer.server.service.IContractService;
import com.deppon.crm.module.customer.server.service.IContractWorkflowService;
import com.deppon.crm.module.customer.server.service.IExamineRecordService;
import com.deppon.crm.module.customer.server.service.impl.AlterMemberService;
import com.deppon.crm.module.customer.server.service.impl.ExamineRecordService;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ContractUtil;
import com.deppon.crm.module.customer.server.utils.ExpressAuthDeptUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractCondition;
import com.deppon.crm.module.customer.shared.domain.ContractDebtDay;
import com.deppon.crm.module.customer.shared.domain.ContractDept;
import com.deppon.crm.module.customer.shared.domain.ContractDetailView;
import com.deppon.crm.module.customer.shared.domain.ContractDto;
import com.deppon.crm.module.customer.shared.domain.ContractExaminView;
import com.deppon.crm.module.customer.shared.domain.ContractHandleType;
import com.deppon.crm.module.customer.shared.domain.ContractMonthendDay;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;
import com.deppon.crm.module.customer.shared.domain.ContractTax;
import com.deppon.crm.module.customer.shared.domain.ContractWorkflowInfo;
import com.deppon.crm.module.customer.shared.domain.CustMonthSum;
import com.deppon.crm.module.customer.shared.domain.ExamineRecord;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.customer.shared.domain.Preferential;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDeal;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;
import com.deppon.crm.module.customer.shared.exception.CustomerException;
import com.deppon.crm.module.customer.shared.exception.CustomerExceptionType;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.exception.BusinessException;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.shared.util.classes.BeanUtils;

/**
 * 
 * @description 合同管理Manager  
 * @author 潘光均
 * @version 0.1 2013-2-25
 *@date 2013-2-25
 */
@Transactional
public class ContractManager implements IContractManager {
	//logger
	private Logger log = Logger.getLogger(ContractManager.class);
	// 合同service
	public IContractService contractService;
	// 合同处理OA调用接口
	private IContractApplyOperate contractApplyOperate;
	// 附件manager
	public IFileManager fileManager;
	// 基础数据manger
	public IBaseDataManager baseDataManager;
	// 会员manager
	public IAlterMemberManager alterMemberManager;
	//客户修改manager
	public AlterMemberService alterMemberService;
	//审批数据manager
	public IExamineRecordService examineRecordService;
	//月发月送优惠方案Manager
	public IPrefrentialDealManager prefrentialDealManager;
	//疑重manager
	private IRepeatedCustManager repeatedCustManager;
	/**
	 * <p>
	 * Description:examineRecordService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 */
	public IExamineRecordService getExamineRecordService() {
		return examineRecordService;
	}
	/**
	 * <p>
	 * Description:examineRecordService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 */
	public void setExamineRecordService(IExamineRecordService examineRecordService) {
		this.examineRecordService = examineRecordService;
	}
	/**
	 * <p>
	 * Description:contractApplyOperate<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 */
	public IContractApplyOperate getContractApplyOperate() {
		return contractApplyOperate;
	}
	/**
	 * <p>
	 * Description:contractWorkflowService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 */
	public IContractWorkflowService getContractWorkflowService() {
		return contractWorkflowService;
	}

	//bps工作流管理
	private IBpsWorkflowManager bpsWorkflowManager;
	//合同工作流service
	private IContractWorkflowService contractWorkflowService;
     // 发送消息管理类
	private IMessageManager messageManager;
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param prefrentialDealManager
	 * void
	 */
	public void setPrefrentialDealManager(
			IPrefrentialDealManager prefrentialDealManager) {
		this.prefrentialDealManager = prefrentialDealManager;
	}
	/**
	 * @return messageManager : return the property messageManager.
	 */
	public IMessageManager getMessageManager() {
		return messageManager;
	}

	/**
	 * @param messageManager : set the property messageManager.
	 */
	public void setMessageManager(IMessageManager messageManager) {
		this.messageManager = messageManager;
	}
 
	/**
	 * <p>
	 * Description:contractService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 */
	public IContractService getContractService() {
		return contractService;
	}
	/**
	 * <p>
	 * Description:contractService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 */
	public void setContractService(IContractService contractService) {
		this.contractService = contractService;
	}
	/**
	 * <p>
	 * Description:fileManager<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 */
	public IFileManager getFileManager() {
		return fileManager;
	}
	/**
	 * <p>
	 * Description:fileManager<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 */
	public void setFileManager(IFileManager fileManager) {
		this.fileManager = fileManager;
	}
	/**
	 * <p>
	 * Description:baseDataManager<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 */
	public IBaseDataManager getBaseDataManager() {
		return baseDataManager;
	}
	/**
	 * <p>
	 * Description:baseDataManager<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 */
	public void setBaseDataManager(IBaseDataManager baseDataManager) {
		this.baseDataManager = baseDataManager;
	}
	/**
	 * <p>
	 * Description:alterMemberManager<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 */
	public IAlterMemberManager getAlterMemberManager() {
		return alterMemberManager;
	}
	/**
	 * <p>
	 * Description:alterMemberManager<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 */
	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}
	/**
	 * <p>
	 * Description:alterMemberService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 */
	public AlterMemberService getAlterMemberService() {
		return alterMemberService;
	}
	/**
	 * <p>
	 * Description:alterMemberService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 */
	public void setAlterMemberService(AlterMemberService alterMemberService) {
		this.alterMemberService = alterMemberService;
	}
	/**
	 * <p>
	 * Description:prefrentialDealManager<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 */
	public IPrefrentialDealManager getPrefrentialDealManager() {
		return prefrentialDealManager;
	}
	/**
	 * 
	 * @合同创建
	 * @author 赵斌
	 * @2012-3-28
	 * @return void
	 * @param contract
	 *            合同
	 * @param contractType 
	 */
	@Override
	public Map<String,String> createContract(Contract contract, List<FileInfo> fileInfoList, String contractType) {
		Assert.notNull(contract, "contract must not be null");
		ContractCondition condition = new ContractCondition();
		condition.setCustId(contract.getMember().getId());
		List<Contract> list = contractService
				.queryContractByCondition(condition);
		List<Double> moneyList = contractService.getLatelyDeliverMoney(contract
				.getMember().getId(), 3);
		// 1 ,合同业务逻辑验证
		ContractValidator.validateCreateContract(contract,list,moneyList);
		// 2,准备数据
		// 取得合同id
		String contractId = contractService.getContractId();
		// 合同操作记录id
		String logId = contractService.getConOperaLogSequence();
		// 设置合同创建人为当前用户
		contract.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		// 设置合同id
		contract.setId(contractId);
		// 设置合同部门
		if (null==contract.getDept()) {
			contract.setDept(ContextUtil.getCurrentUserDept());
		}
		// 合同编号去空格
		contract.setContractNum(getContractNum(contractId).toString());

		// 优惠信息
		if (null != contract.getPreferential()) {
			contract.getPreferential().setContractId(contractId);
		}
		
		//校验结果
		boolean result = checkAmountByCustId(contract,contract
				.getMember().getId(),contract.getArrearaMount());
		// 调用OA审批接口生成工作流 得到工作流id
		String workflowId = "";
		/**@新签 chenaichun
		 * 校验税务信息是否为01  （01 需要走工作流）
		 */
		boolean flag = isContractTaxNeedOAWorkFlow(contract);
		//传入校验结果
		Map<String, String> map = new HashMap<String,String>();
		/*
		 * 如果付款方式为非月结，并且优惠方式为月底发送，并且优惠全是1
		 */
		if (ContractUtil.isNewNotNeedNotOAWorkFlow(contract)&&!flag) {
			contract.setContractStatus(Constant.CONTRACT_STATUS_EFFECT);
			if (new Date().after(contract.getContractBeginDate())) {
				contract.setContractBeginDate(new Date());
			}
		} else {
			//传入校验结果
			map.put("isStatified", String.valueOf(result));
			// 设置合同的状态为审批中
			contract.setContractStatus(Constant.CONTRACT_STATUS_INPROCESS);
		}
		map.put("isContractTaxUpd", String.valueOf(flag));
		/**添加合同税务标记字段
		 * @author chenaichun
		 * @description 新签或者续签 只插入一条contractTax信息
		 */
		contract.setContractTaxList(produceContractTax(contract));
		// 构造操作日志的附件信息
		contract.setFileInfoList(
				produceFileInfos(fileInfoList, logId,
						Constant.FileSourceType_ContractOperatorLog));
		// 设置合同部门
		contract.setContractDepartList(produceContractDept(contract, true));
		// 设置合同操作记录
		contract.setContractWorkflowList(produceOpeLogs(contract, logId,
				workflowId, ContractHandleType.INSERT.getHandleType(),
				contract.getContractStatus(), null));
		// 如果合同的状态时有效，不需要走工作流的，操作记录的工作流状态为空
		if (contract.getContractStatus()
				.equals(Constant.CONTRACT_STATUS_EFFECT)) {
			contract.getContractWorkflowList().get(0).setApprovalState(null);
		}
		//设置价格版本
		setContractPriceVersionDate(contract, null,null);
		/**
		 * @description:合同是否有快递信息，决定合同新签工作流流向
		 */
		ContractApplyType type = contract.hasExpressInfo() ? ContractApplyType.EX_NEW
				: ContractApplyType.NEW;
		// 3，数据持久化
		workflowId = saveNewOrUpdateContract(contract, type,
				map, contract.getContractWorkflowList(), null, contractType);
		// 4，返回数据
		//返回到前台的map
		Map<String,String> jsMap = new HashMap<String,String>();
		jsMap.put("workflowId", workflowId);
		jsMap.put("updFlag", String.valueOf(flag));
		return jsMap;
	}
	/**
	 * 
	* @Title: isContractTaxNeedOAWorkFlow
	* @Description: 判断新合同的税务信息是否为01 （01需要走工作流）
	* @author chenaichun 
	* @param @return    设定文件
	* @date 2013-12-2 下午3:44:02
	* @return boolean    返回类型
	* @throws
	* @update 2013-12-2 下午3:44:02
	 */
	public boolean isContractTaxNeedOAWorkFlow(Contract newerContract){
		if(ValidateUtil.objectIsEmpty(newerContract)){
			throw new ContractException(ContractExceptionType.CONTRACT_DATA_ERROR);
		}
		ContractTax newerContractTax = ContractUtil.changeListToContractTax(newerContract);
		if(ValidateUtil.objectIsEmpty(newerContractTax)){
			return false;
		}
		if(null != newerContractTax.getInvoiceType() && newerContractTax.getInvoiceType().equals("INVOICE_TYPE_01")){
			return true;
		}
		return false;
	}
	/**
	 * 
	* @Title: isUpdContractTaxNeedOAWorkFlow
	* @Description: 判断发票标记的修改是否会产生工作流（不管签署合同子公司）
	* @author chenaichun 
	* @param @param newerContract
	* @param @param oldContract
	* @param @return    设定文件
	* @date 2013-12-16 下午4:53:11
	* @return boolean    返回类型
	* @throws
	* @update 2013-12-16 下午4:53:11
	 */
	public boolean isUpdContractTaxNeedOAWorkFlow(Contract newerContract,Contract oldContract){
		return ContractValidator.isUpdContractTaxChange(newerContract, oldContract);
	}
	/**
	 * @description 生成合同操作记录.
	 * @author 潘光均
	 * @version 0.1 2012-6-28
	 * @param
	 * @date 2012-6-28
	 * @return List<ContractOperatorLog>
	 * @update 2012-6-28 下午5:28:14
	 */
	private List<ContractOperatorLog> produceOpeLogs(Contract contract,
			String id, String workflowId, String handleType,String status,Date createDate) {
		Assert.notNull(contract,"contract con't null !");
		ContractOperatorLog log = new ContractOperatorLog();
		log.setId(id);
		log.setWorkflowId(workflowId);
		log.setOperatorDept(ContextUtil.getCurrentUserDept());
		log.setOperatorType(handleType);
		log.setContract(contract);
		log.setCreateDate(createDate);
		log.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		if (Constant.CONTRACT_STATUS_EFFECT.equals(status)) {
			log.setApprovalState(null);
		}else{
			log.setApprovalState(Constant.CONTRACT_APPROVING);
		}

		List<ContractOperatorLog> logs = new ArrayList<ContractOperatorLog>();
		logs.add(log);
		return logs;
	}

	/**
	 * @description 生成合同部门.
	 * @author 潘光均
	 * @version 0.1 2012-6-28
	 * @param
	 * @date 2012-6-28
	 * @return List<ContractDept>
	 * @update 2012-6-28 下午5:27:27
	 */
	private List<ContractDept> produceContractDept(Contract contract,
			boolean isBelongDept) {
		ContractDept contractDept = new ContractDept();
		contractDept.setContract(contract);
		Date boundTime = contract.getContractBeginDate().before(new Date()) ? new Date()
				: contract.getContractBeginDate();
		contractDept.setBoundTime(boundTime);
		contractDept.setContractDept(contract.getDept());
		contractDept.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		contractDept.setRemoveTime(contract.getContractendDate());
		contractDept.setDept(isBelongDept);

		List<ContractDept> contractDepartList = new ArrayList<ContractDept>();
		contractDepartList.add(contractDept);
		return contractDepartList;
	}
	/**
	 * 
	* @Title: produceContractTax
	* @Description: 新签和续签时生成一条合同税务标记
	* @author chenaichun 
	* @param @param contract
	* @param @param isBelongDept
	* @param @return    设定文件
	* @date 2013-11-19 上午10:41:37
	* @return List<ContractDept>    返回类型
	* @throws
	* @update 2013-11-19 上午10:41:37
	 */
	private List<ContractTax> produceContractTax(Contract contract
			) {
		Assert.notNull(contract, "contract must not be null");
		ContractValidator.checkContactOnlyOneTax(contract.getContractTaxList());
		ContractTax  contractTax = new ContractTax();
		contractTax.setContractId(contract.getId());
		//设置 税务标记的开始时间为合同开始时间
		Date beginTime = contract.getContractBeginDate();
		contractTax.setBeginTime(beginTime);
		//设置税务 标记的结束时间为合同结束时间
		contractTax.setEndTime(contract.getContractendDate());
		//合同的签署合同公司和发票标记
		contractTax.setSignCompany(contract.getContractTaxList().get(0).getSignCompany());
		contractTax.setInvoiceType(contract.getContractTaxList().get(0).getInvoiceType());
		List<ContractTax> contractTaxList = new ArrayList<ContractTax>();
		contractTaxList.add(contractTax);
		return contractTaxList;
	}
	/**
	 * 
	* @Title: produceTwoContractTax
	* @Description: 改签和修改时插入两条税务标记信息(先校验新合同的时间）
	* @author chenaichun 
	* @param @param oldContract
	* @param @param newerContract
	* @param @return    设定文件
	* @date 2013-11-19 下午2:40:02
	* @return List<ContractTax>    返回类型
	* @throws
	* @update 2013-11-19 下午2:40:02
	 */
	private List<ContractTax> produceTwoContractTax(Contract oldContract,Contract newerContract)
	{
		if(ValidateUtil.objectIsEmpty(newerContract)){
			throw new ContractException(ContractExceptionType.CONTRACT_DATA_ERROR);
		}
		//校验合同税务信息
		//获取旧合同的税务信息
		ContractTax oldContractTax = ContractUtil.changeListToContractTax(oldContract);
		if(null == oldContractTax||"".equals(oldContractTax.getSignCompany())||"".equals(oldContractTax.getInvoiceType())){
			throw new ContractException(ContractExceptionType.CONTRACT_CONTRACTTAX_NULL);
		}
        ContractValidator.checkContactOnlyOneTax(newerContract.getContractTaxList());
        ContractTax contractTax = new ContractTax();
        //设置税务标记的合同ID为新合同的ID
        contractTax.setContractId(newerContract.getId());
        List<ContractTax> contractTaxList = new ArrayList<ContractTax>();
        //	校验税务信息是否修改
        boolean result = ContractValidator.isContractTaxChange(newerContract, oldContract);
        /**
         * 如果税务信息没有修改
         */
        if(!result){
        	contractTax.setBeginTime(newerContract.getContractBeginDate());
        	contractTax.setEndTime(newerContract.getContractendDate());
        	//设置税务信息为新合同的税务信息
        	contractTax.setSignCompany(newerContract.getContractTaxList().get(0).getSignCompany());
        	contractTax.setInvoiceType(newerContract.getContractTaxList().get(0).getInvoiceType());
			contractTaxList.add(contractTax);
        	return contractTaxList;
        }
        /**
          *修改了
          */
        //如果新合同的终止时间小于下个月1号
        if(newerContract.getContractendDate().before(ContractUtil.nextMonthOneDay(new Date()))){
        	//如果新合同的税务信息修改了 抛异常
        		throw new ContractException(ContractExceptionType.CONTRACT_CONTRACTTAX_NOCHANGE);
        }
      //新合同的开始时间大于等于下个月1号 只插入一条数据（说明是下个月生效合同：税务信息为新合同的税务信息）
      if(newerContract.getContractBeginDate().getTime()>=(ContractUtil.nextMonthOneDay(new Date())).getTime()){
      	contractTax.setBeginTime(newerContract.getContractBeginDate());
      	contractTax.setEndTime(newerContract.getContractendDate());
      	//设置税务信息为新合同的税务信息
      	contractTax.setSignCompany(newerContract.getContractTaxList().get(0).getSignCompany());
      	contractTax.setInvoiceType(newerContract.getContractTaxList().get(0).getInvoiceType());
			contractTaxList.add(contractTax);
      	return contractTaxList;
      }
       //新合同的开始时间小于下个月1号
    	//即在当月生效的插入两条
    
		//税务标记信息一
        
		//设置税务开始时间为新合同的开始时间
		Date beginTime =  newerContract.getContractBeginDate();
		contractTax.setBeginTime(beginTime);
		//设置税务结束时间为下月1号前一天的最后一秒
		contractTax.setEndTime(ContractUtil.nextMonthDaySecond(new Date()));
		//设置税务信息为旧合同正在使用的税务信息
		contractTax.setSignCompany(oldContractTax.getSignCompany());
		contractTax.setInvoiceType(oldContractTax.getInvoiceType());
		contractTaxList.add(contractTax);
		
		//税务信息二
		contractTax = new ContractTax();
		contractTax.setContractId(newerContract.getId());
		//设置税务开始时间为下月1号00:00:00
		contractTax.setBeginTime(ContractUtil.nextMonthOneDay(new Date()));
		contractTax.setEndTime(newerContract.getContractendDate());
		//设置税务信息为新合同的税务信息
		contractTax.setSignCompany(newerContract.getContractTaxList().get(0).getSignCompany());
		contractTax.setInvoiceType(newerContract.getContractTaxList().get(0).getInvoiceType());
		//设置税务结束时间为合同结束时间
		contractTax.setEndTime(newerContract.getContractendDate());
		contractTaxList.add(contractTax);
		return contractTaxList;
	}
	
	/**
	 * @description 合同新增OA工作流申请.
	 * @author 潘光均
	 * @version 0.1 2012-6-28
	 * @param
	 * @date 2012-6-28
	 * @return String
	 * @update 2012-6-28 下午3:56:51
	 */
	@SuppressWarnings("serial")
	private String processWorkflow(Contract contract,ContractApplyType applyType,Map<String,String> map,String bizCode) {
		String workflowid = null;
		map.put("applyType", applyType.toString());
		ContractInfo contractInfo = createContractInfo(contract,map);
		/**
		 * 保存工作流审批信息
		 */
		if(applyType.toString().equals(ContractApplyType.NEW.toString())||applyType.toString().equals(ContractApplyType.EX_NEW.toString())||
		   applyType.toString().equals(ContractApplyType.UPDATE.toString())||applyType.toString().equals(ContractApplyType.EX_UPDATE.toString())||
		   applyType.toString().equals(ContractApplyType.CANCEL.toString())||applyType.toString().equals(ContractApplyType.EX_CANCEL.toString())){
		saveContractWorkInfo(contract,contractInfo,bizCode,applyType.toString());
		}
		try {
			// 生成OA工作流
			workflowid = contractApplyOperate.contractApply(contractInfo,
					applyType,bizCode);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getMessage(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		return workflowid;
	}
	/**
	 * 
	 * <p>
	 * Description:保存工作流信息br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param contract
	 * @param contractInfo
	 * @param bizCode
	 * @param applyType
	 * void
	 */
	private void saveContractWorkInfo(Contract contract,ContractInfo contractInfo, String bizCode, String applyType) {
		ContractWorkflowInfo contractWorkflowInfo = new ContractWorkflowInfo();
		//复制对象
		BeanUtils.copyProperties(contractInfo, contractWorkflowInfo);
		if(applyType.equals(ContractApplyType.NEW.toString())||applyType.equals(ContractApplyType.EX_NEW.toString())){
			ContractTax cTax = contract.getNewContractTax();
			contractWorkflowInfo.setSignCompany(cTax.getSignCompany());
			contractWorkflowInfo.setInvoiceType(cTax.getInvoiceType());
			
		}else if(applyType.equals(ContractApplyType.UPDATE.toString())||applyType.equals(ContractApplyType.EX_UPDATE.toString())){
			ContractTax newTax = contract.getNewContractTax();
			ContractTax oldTax=new ContractTax();
			if(null!=contract.getOldContract()){
			 oldTax = ContractUtil
					.changeListToContractTax(contract.getOldContract());
			 boolean ismodifyInvoiceOnly =contractInfo.getModifyContractType().equals("1000");
				contractWorkflowInfo.setIsmodifyInvoiceOnly(ismodifyInvoiceOnly);
			}
			contractWorkflowInfo.setNewSignCompany(newTax.getSignCompany());
			contractWorkflowInfo.setNewInvoiceType(newTax.getInvoiceType());
			contractWorkflowInfo.setSignCompany(oldTax.getSignCompany());
			contractWorkflowInfo.setInvoiceType(oldTax.getInvoiceType());
			/**
			 * 合同终止，取出当前有效的税务信息
			 */
		}else if(applyType.equals(ContractApplyType.CANCEL.toString())||applyType.equals(ContractApplyType.EX_CANCEL.toString())){
			ContractTax contractTax = ContractUtil.changeListToContractTax(contract);
			contractWorkflowInfo.setSignCompany(contractTax.getSignCompany());
			contractWorkflowInfo.setInvoiceType(contractTax.getInvoiceType());
		}
		contractWorkflowInfo.setInvoiceTypeName(DataDictionaryUtil.getCodeDesc(
				DataHeadTypeEnum.INVOICE_MARK, contractWorkflowInfo.getInvoiceType()));
		contractWorkflowInfo.setNewInvoiceTypeName(DataDictionaryUtil.getCodeDesc(
				DataHeadTypeEnum.INVOICE_MARK, contractWorkflowInfo.getNewInvoiceType()));
		
		// 判断是否有零担的折扣
		if (contract.getPreferential() == null) {
			contractWorkflowInfo.setHasLTTDiscount(false);
		} else {
			if (Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(contract
					.getPreferentialType())) {

				if ((contract.getPreferential().getAgentgathRate().equals(1.0))
						&& (contract.getPreferential().getInsuredPriceRate()
								.equals(1.0))
								&&contract.getPreferential().getReceivePriceRate()==null) {
					contractWorkflowInfo.setHasLTTDiscount(false);
				} else {
					contractWorkflowInfo.setHasLTTDiscount(true);

				}
				// 表示有零担的折扣
			} else {
				if ((null != contract.getPreferential())
						&& (contract.getPreferential().getAgentgathRate()
								.equals(1.0))
						&& (contract.getPreferential().getInsuredPriceRate()
								.equals(1.0))
						&& contract.getPreferential().getChargeRebate()
								.equals(1.0)
						&&contract.getPreferential().getReceivePriceRate()==null) {
					contractWorkflowInfo.setHasLTTDiscount(false);
				} else {
					contractWorkflowInfo.setHasLTTDiscount(true);
				}
			}
		}
		// 判断是否修改了快递折扣
		if (contract.getExPreferential() == null) {
			contractWorkflowInfo.setHasExpDiscount(false);
		} else {
			// 如果是越发越折不判断运费折扣

			if (Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(contract
					.getExPreferentialType())) {
				if ((contract.getExPreferential().getAgentgathRate()
						.equals(1.0))
						&& (contract.getExPreferential().getInsuredPriceRate()
								.equals(1.0))) {
					contractWorkflowInfo.setHasExpDiscount(false);
				} else {
					contractWorkflowInfo.setHasExpDiscount(true);
				}
			} else {
				if ((null != contract.getExPreferential())
						&& (contract.getExPreferential().getAgentgathRate()
								.equals(1.0))
						&& (contract.getExPreferential().getInsuredPriceRate()
								.equals(1.0))
						&& contract.getExPreferential().getChargeRebate()
								.equals(1.0)) {
					contractWorkflowInfo.setHasExpDiscount(false);
				} else {
					contractWorkflowInfo.setHasExpDiscount(true);
				}
			}
		}
		contractWorkflowInfo.setBusino(bizCode);
		contractWorkflowInfo.setCreateUser(UserContext.getCurrentUser().getId());
		contractWorkflowInfo.setModifyUser(UserContext.getCurrentUser().getId());
		//终止的时候不需要保存附件信息
		if(!applyType.equals(ContractApplyType.CANCEL.toString())&&!applyType.equals(ContractApplyType.EX_CANCEL.toString())){
			contractWorkflowInfo.setLogId(contract.getFileInfoList().get(0).getSourceId());
		}
		
		contractWorkflowService.saveContractWorkflowInfo(contractWorkflowInfo);
	}

	/**
	 * <p>
	 * Description:并发删除合同新签或者改签所用<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-1
	 * @param contract
	 * @param applyType
	 * @param map
	 * @param newCoLogs
	 * @param oldCoLogs
	 * void
	 */
	private String saveNewOrUpdateContract(Contract contract,ContractApplyType applyType,Map<String,String> map
			,List<ContractOperatorLog> newCoLogs,List<ContractOperatorLog> oldCoLogs,String contractType) {
		String bizCode=bpsWorkflowManager.bizCode();
		//如果产生工作流 则需要在新操作日志里设置工作流号
		if (!ContractUtil.isNewNotNeedNotOAWorkFlow(contract)||"true".equals(map.get("isContractTaxUpd"))) {
			for(int i=0;i<newCoLogs.size();i++){
				newCoLogs.get(i).setWorkflowId(bizCode);
			}
		}
		saveContract(contract,applyType);
		map.put("contractType", contractType);
		String workflowId = null;
		//判断是否走工作流
		if (!ContractUtil.isNewNotNeedNotOAWorkFlow(contract)||"true".equals(map.get("isContractTaxUpd"))) {
			//需要走工作流的申请
			workflowId = processWorkflow(contract,applyType,map,bizCode);
		} 
		//判断是否需要出现了异常，而且删除OA工作流数据
		if (!StringUtils.isEmpty(workflowId)) {
			boolean flag = validatorIfDeleteWorkFlow(workflowId, contract.getContractWorkflowList(),applyType);
			if (!ValidateUtil.objectIsEmpty(oldCoLogs) && !flag) {
				validatorIfDeleteWorkFlow(workflowId, oldCoLogs,applyType);
			}
		}
		return workflowId;
	}
	
	/**
	 * <p>
	 * Description:判断是否删除OA工作流<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-3
	 * @param workflowId
	 * @param contractOperatorLogs
	 * @param applyType
	 * @return
	 * boolean true删除成功
	 */
	private boolean validatorIfDeleteWorkFlow(String workflowId,List<ContractOperatorLog> contractOperatorLogs,
			ContractApplyType applyType) {
		boolean flag = false;
		if (CollectionUtils.isNotEmpty(contractOperatorLogs)) {
			flag = false;
			//如果操作记录存在，则更新操作记录的工作流号 可优化
			for (ContractOperatorLog contractOperatorLog : contractOperatorLogs) {
				contractOperatorLog.setWorkflowId(workflowId);
				contractService.updateContractOperaLog(contractOperatorLog);
			}
		} else {
			try {
				//如果无操作记录则调用接口删除OA生成的工作流
				// 这里只会返回true,返回false会抛异常
				flag = contractApplyOperate.deleteWorkflow(workflowId,applyType.toString());
			} catch (CrmBusinessException e) {
				throw new ContractException(ContractExceptionType.contractCallOA_deleteInterface_error);
			}
		}
		return flag;
	}
	/**
	 * 
	 * @description 保存合同信息，合同操作记录信息，合同附件，合同优惠信息.
	 * @author 潘光均
	 * @version 0.1 2012-6-28
	 * @param
	 * @date 2012-6-28
	 * @return String
	 * @update 2012-6-28 下午6:49:49
	 */
	private void saveContract(Contract contract, ContractApplyType applyType) {
		/**
		 * @description:根据客户最近三个月发货情况（出整车和空运），设置运费折扣
		 * @author 潘光均
		 * @date 2013-3-12
		 */
		if (null != contract && null != contract.getMember()
				&& null != contract.getMember().getId()) {
			if (Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(contract
					.getPreferentialType())) {
				//零担发货情况
				List<CustMonthSum> monthSums = contractService
						.queryCustMonthSumsByCustId(contract.getMember()
								.getId());
				//零担折扣信息
				PrefrentialDeal deal = this
						.getEffectPrefrentialDeal(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND);
				double rate = this.getMonthSendRate(deal, monthSums);
				if (null != contract.getPreferential()) {
					contract.getPreferential().setChargeRebate(rate);
				} else {
					Preferential preferential = new Preferential();
					preferential.setChargeRebate(rate);
					preferential.setAgentgathRate(1d);
					preferential.setContractId(contract.getId());
					preferential.setDeliveryPriceRate(1d);
//					preferential.setReceivePriceRate(1d);
					preferential.setInsuredPriceRate(1d);
					contract.setPreferential(preferential);
				}
			}
			/**
			 * @description:增加快递运费折扣-客户最近三个月发货情况（出整车和空运），设置运费折扣
			 * @author 潘光均
			 * @date 2013-09-22
			 */
			if (Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE.equals(contract
					.getExPreferentialType())) {
				//快递发货情况
				List<CustMonthSum> exMonthSums = contractService
						.queryExCustMonthSumsByCustId(contract.getMember()
								.getId());
				//快递折扣信息
				PrefrentialDeal exDeal = this
						.getEffectPrefrentialDeal(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE);
				double exRate = this.getMonthSendRate(exDeal, exMonthSums);
				if (null != contract.getExPreferential()) {
					contract.getExPreferential().setChargeRebate(exRate);
				} else {
					Preferential preferential = new Preferential();
					preferential.setChargeRebate(exRate);
					preferential.setAgentgathRate(1d);
					preferential.setContractId(contract.getId());
					preferential.setDeliveryPriceRate(1d);
//					preferential.setReceivePriceRate(1d);
					preferential.setInsuredPriceRate(1d);
					contract.setExPreferential(preferential);
				}
			}
		}
		// 新增和改签合同的时候，是否异地调货都默认为否
		contract.setIfForeignGoods(false);
		// 创建合同
		contract = contractService.createContract(contract);
		// 新增合同条数为0则抛出异常提示"对不起，数据异常，请刷新重试，多次还不行，请联系IT服务中心"
		if (null == contract) {
			throw new ContractException(ContractExceptionType.Data_Error);
		}
		// 合同状态为生效，不需要走工作流时，直接生成协议联系人
		if (contract.getContractStatus()
				.equals(Constant.CONTRACT_STATUS_EFFECT)) {
			// 生成协议联系人
			alterMemberManager.addContactType(contract.getContactId(),
					Constant.ContactType_Treaty);
		}
		// 保存合同其他信息
		saveContractAttach(contract);
	}

	/**
	 * @description 保存合同附件，优惠信息，操作记录.++++添加税务标记信息
	 * @author 潘光均
	 * @version 0.1 2012-6-29
	 * @param
	 * @date 2012-6-29
	 * @return void
	 * @update 2012-6-29 下午9:22:47
	 */
	private void saveContractAttach(Contract contract) {
		// 创建合同附件
		if (contract.getFileInfoList() != null
				&& 0 < contract.getFileInfoList().size()) {
			for (FileInfo fileInfo : contract.getFileInfoList()) {
				if (!StringUtils.isEmpty(fileInfo.getFileName())) {
					fileManager.saveFileInfo(fileInfo);
				}
			}
		}
		// 创建合同优惠信息
		if (contract.getPreferential() != null) {
			geneNewPreferential(contract, contract.getPreferential(),
					Constant.CONTRACT_TYPE_LINGDAN);
			contractService.createPreferential(contract.getPreferential());
		}
		/**创建合同税务标记信息 @chenaichun  插入数据库
		 * 如果是新签或者续签  list只有一条
		 * 如果是改签或者修改 list 只有两条（新合同的开始时间 下个月才生效的也只插入一条信息）
		 */
		if(contract.getContractTaxList()!= null){
			for (ContractTax contractTax : contract.getContractTaxList()) {
				//新增
				createNewContractTaxObj(contractTax);
				contractService.createContractTax(contractTax);
			}
		}
		/**
		 * @description:增加快递折扣保存
		 * @author pgj
		 * @date 2013-09-22
		 */
		if (contract.getExPreferential() != null) {
			geneNewPreferential(contract, contract.getExPreferential(),
					Constant.CONTRACT_TYPE_EXPRESS);
			contractService.createPreferential(contract.getExPreferential());
		}
		// 保存操作记录
		if (null != contract.getContractWorkflowList()) {
			for (ContractOperatorLog log : contract.getContractWorkflowList()) {
				//操作记录创建时间为空则设置成当前时间
				log.setCreateDate(null == log.getCreateDate()?new Date():log.getCreateDate());
				contractService.createContractOpeLog(log);
			}
		}
		// 保存合同部门
		if (null != contract.getContractDepartList()) {
			for (ContractDept contractDept : contract.getContractDepartList()) {
				contractService.createContractDept(contractDept);
			}
		}
	}

	/**
	 * 
	 * @将合同对象转换成工作流合同对象
	 * @author 赵斌
	 * @2012-4-26
	 * @return
	 */
	public ContractInfo createContractInfo(Contract contract,Map<String,String> extendMap) {
		Assert.notNull(contract.getFileInfoList(), "file must not null！");
		ContractInfo contractInfo = new ContractInfo();
		List<ContractNounInfo> contractNounList = new ArrayList<ContractNounInfo>();
		contractInfo.setApplyType(extendMap.get("applyType"));// 申请类型
		contractInfo.setOverRange((null==extendMap.get("isStatified"))?"":extendMap.get("isStatified"));// 超系统额定
		contractInfo.setApplyPersonCode(ContextUtil.getCurrentUser().getLoginName());// 申请人工号
		contractInfo.setApplyPersonName(ContextUtil.getCurrentUserName());// 申请人姓名
		
		Department department = ContextUtil.getCurrentUserDept();
		contractInfo.setApplyPersonDept(department.getDeptName());// 所属部门

		Department businessdept = baseDataManager
				.getCauseDepartment(ContextUtil.getCurrentUserDept().getId());
		String applyType=extendMap.get("applyType");
		if (businessdept != null) {
			/**如果是迁移后的工作流,则为事业部名称，其他的为标杆编码*/
			if (applyType.equals(ContractApplyType.NEW.toString())
					|| applyType.equals(ContractApplyType.EX_NEW.toString())
					|| applyType.toString().equals(
							ContractApplyType.UPDATE.toString())
					|| applyType.equals(ContractApplyType.EX_UPDATE.toString())
					|| applyType.toString().equals(
							ContractApplyType.CANCEL.toString())
					||applyType.toString().equals(
				ContractApplyType.EX_CANCEL.toString())) {
				contractInfo.setDivisionCode(businessdept.getDeptName());// 所属事业部
			}else{
				contractInfo.setDivisionCode(businessdept.getStandardCode());// 所属事业部
			}
		}

		if (contract.getMember().getId() != null) {
			Member member = contractService.getMember(contract.getMember()
					.getId());
			contractInfo.setCustomerCode(member.getCustNumber());// 客户编码
			contractInfo.setCustomerName(member.getCustName());// 客户名称
			if (member.getMainContact() != null) {
				contractInfo.setProtocolContactName(member.getMainContact().getName());// 协议联系人
				contractInfo.setContactPhone(member.getMainContact()
						.getMobilePhone());// 联系人手机
				contractInfo.setContactTel(member.getMainContact()
						.getTelPhone());// 联系人电话
			}
		}
		//老合同优惠折扣
		Contract oldContract = contract.getOldContract();
		
		/**@description:增加快递合同条款
		 * @date 2013-09-24
		 * @author pgj
		 */
		contractInfo.setFisPointManager(ExpressAuthDeptUtil.isPointManager(ContextUtil.getCurrentUserRoleIds())?"Y":"N");
		contractInfo.setExpAmountOfConsignMent(contractService.getExArrearaMountByCustId(contract.getMember().getId()));
		contractInfo.setExpNodesectionType(DataDictionaryUtil.getCodeDesc(
				DataHeadTypeEnum.RECKON_WAY, contract.getExPayWay()));
		String contractType = extendMap.get("contractType");
		if (StringUtils.isEmpty(contractType)) {
			if(contract.hasLttInfo()&&!contract.hasExpressInfo()){
				contractType=Constant.CONTRACT_TYPE_LINGDAN;
			}else if (!contract.hasLttInfo()&&contract.hasExpressInfo()) {
				contractType=Constant.CONTRACT_TYPE_EXPRESS;
			}else{
				contractType=Constant.CONTRACT_TYPE_EXANDLD;
			}
		}
		//是否修改了公用信息,合同类型为零担快递
		if (null!= oldContract) {
			if (ContractUtil.modifyContractCommon(
				contract, oldContract,false)) {
				contractType=Constant.CONTRACT_TYPE_EXANDLD;
			}
		}
		contractInfo.setModifyPreferentialType(contractType);
		contractInfo.setExpPriceVersiondate(contract.getExPriceVersionDate());
		//快递点部标杆编码
		if (ExpressAuthDeptUtil.isPointManager(ContextUtil.getCurrentUserRoleIds())) {
			contractInfo.setExpressPointDeptCode(ContextUtil.getCurrentUserDept().getStandardCode());
		}else{
			List<Department> pointDepts = baseDataManager
					.getPointDepartment(ContextUtil.getCurrentUserDept()
							.getStandardCode());
			if (!CollectionUtils.isEmpty(pointDepts)) {
				contractInfo.setExpressPointDeptCode(pointDepts.get(0).getStandardCode());
			}
		}
		
		contractInfo.setOldContractNumber(contract.getBeforeContractNum());// 原合同序号
		contractInfo.setContractNumber(contract.getContractNum());// 合同序号
		contractInfo.setCustomerAllName(contract.getCustCompany());// 客户全称

		// 结款方式---数据字典转换
		contractInfo.setNodeSectionType(DataDictionaryUtil.getCodeDesc(
				DataHeadTypeEnum.RECKON_WAY, contract.getPayWay()));// 结款方式
		contractInfo
				.setBalanceAccount(new BigDecimal(null == contract
						.getArrearaMount() ? 0 : contract.getArrearaMount())
						.toString());// 结算限额
		contractInfo.setSubsidiary(contract.getContractSubject());// 所属子公司
		contractInfo.setProtocolContactName(contract.getLinkManName());// 协议联系人
		contractInfo.setContactTel(contract.getLinkManPhone());// 联系人电话
		contractInfo.setContactPhone(contract.getLinkManMobile());// 联系人手机
		contractInfo.setContractStartDate(contract.getContractBeginDate());// 合同起始日期
		contractInfo.setContractEndDate(contract.getContractendDate());// 合同到期日期
		contractInfo.setApplyReason(contract.getApplication());// 申请事由
		contractInfo.setAmountOfConsignment(contractService.getArrearaMountByCustId(contract.getMember().getId()));// 近三月发货金额
		contractInfo.setSettleAccountsDate(String.valueOf(contract.getResultDate()));// 结款日期
		if (Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract.getPayWay())) {
			contractInfo.setPriceVersionDate(contract.getPriceVersionDate());
		}else {
			contractInfo.setPriceVersionDate(null);
		}
		if (ContractApplyType.UPDATE.toString().equals(extendMap.get("applyType"))||
				ContractApplyType.EX_UPDATE.toString().equals(extendMap.get("applyType"))) {
			//设置修改类型
			contractInfo.setModifyContractType(getModifyType(contract));
			contractInfo.setExpNewPreferentialType(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.EXPRIVILEGE_TYPE,
					contract.getExPreferentialType()));
			/**
			 * @description 因现在月发月送合同会产生折扣，所以把折扣去掉 以免影响OA工作流流程
			 * @date 2013-5-15
			 * @author 潘光均
			 */
			if (!Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(contract.getPreferentialType())) {
				contractInfo.setNewFreightDiscount(returnStrRate(contract.getPreferential()
						.getChargeRebate()));// 新运费折扣
			}
			contractInfo.setNewGenerationRateDiscount(returnStrRate(contract.getPreferential()
					.getAgentgathRate()));// 新代收费率折扣
			contractInfo.setNewChargeRateDiscount(returnStrRate(contract.getPreferential()
					.getInsuredPriceRate()));// 新保价费率折扣
			contractInfo.setNewCargoFeeDiscount(returnStrNullRate(contract.getPreferential()
					.getReceivePriceRate()));// 新接货费折扣
			contractInfo.setNewDeliveryFeeDiscount(returnStrRate(contract.getPreferential()
					.getDeliveryPriceRate()));// 新送货费折扣
			
			//设置快递的折扣
			if (!Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE.equals(contract.getExPreferentialType())) {
				contractInfo.setExpNewFreightDiscount(returnStrRate(contract.getExPreferential()
						.getChargeRebate()));// 新运费折扣
			}
			contractInfo.setExpNewDiscount(returnStrRate((contract.getExPreferential()
					.getAgentgathRate())));// 新代收费率折扣
			contractInfo.setExpNewChargeRateDiscount(returnStrRate(contract.getExPreferential()
					.getInsuredPriceRate()));// 新保价费率折扣
			contractInfo.setExpNewCargoFeeDiscount(returnStrNullRate(contract.getExPreferential()
					.getReceivePriceRate()));// 新接货费折扣
			contractInfo.setExpNewDeliveryFeeDiscount(returnStrRate(contract.getExPreferential()
					.getDeliveryPriceRate()));// 新送货费折扣

			
			
			if (null!=oldContract) {
					/**
					 * @description 因现在月发月送合同会产生折扣，所以把折扣去掉 以免影响OA工作流流程
					 * @date 2013-5-15
					 * @author 潘光均
				 */
				if (null!=oldContract.getPreferential()) {
					if (!Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(oldContract.getPreferentialType())) {
						contractInfo.setFreightDiscount(returnStrRate(oldContract.getPreferential()
								.getChargeRebate()));// 运费折扣
					}
					contractInfo.setGenerationRateDiscount(returnStrRate(oldContract.getPreferential()
							.getAgentgathRate()));// 代收费率折扣
					contractInfo.setChargeRateDiscount(returnStrRate(oldContract.getPreferential()
							.getInsuredPriceRate()));// 保价费率折扣
					contractInfo.setCargoFeeDiscount(returnStrNullRate(oldContract.getPreferential()
							.getReceivePriceRate()));// 接货费折扣
					contractInfo.setDeliveryFeeDiscount(returnStrRate(oldContract.getPreferential()
							.getDeliveryPriceRate()));// 送货费折扣
				}
				if (null!=oldContract.getExPreferential()) {
					//快递折扣
					if (!Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE.equals(oldContract.getExPreferentialType())) {
						contractInfo.setExpFreightDiscount(returnStrRate(oldContract.getExPreferential()
								.getChargeRebate()));// 运费折扣
					}
					contractInfo.setExpWxpgenerationRateDiscount(returnStrRate(oldContract.getExPreferential()
							.getAgentgathRate()));// 代收费率折扣
					contractInfo.setExpChargeRateDiscount(returnStrRate(oldContract.getExPreferential()
							.getInsuredPriceRate()));// 保价费率折扣
					contractInfo.setExpCargoFeeDiscount(returnStrNullRate(oldContract.getExPreferential()
							.getReceivePriceRate()));// 接货费折扣
					contractInfo.setExpDeliVeryFeeDiscount(returnStrRate(oldContract.getExPreferential()
							.getDeliveryPriceRate()));// 送货费折扣
				}
			contractInfo.setBalanceAccount(new BigDecimal(null == oldContract
					.getArrearaMount() ? 0 : oldContract.getArrearaMount()).toString());//结算限额
			contractInfo.setPreferentialType(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.PRIVILEGE_TYPE,
					oldContract.getPreferentialType()));// 老合同优惠类型---数据字典转换
			contractInfo.setExpressPreferentialType(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.EXPRIVILEGE_TYPE,
					oldContract.getExPreferentialType()));// 老合同快递优惠类型---数据字典转换
			}
		}else{
			if (null!=contract.getPreferential()) {
				if (!Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(contract.getPreferentialType())) {
					contractInfo.setFreightDiscount(returnStrRate(contract.getPreferential()
							.getChargeRebate()));// 运费折扣
				}
				contractInfo.setGenerationRateDiscount(returnStrRate(contract.getPreferential()
						.getAgentgathRate()));// 代收费率折扣
				contractInfo.setChargeRateDiscount(returnStrRate(contract.getPreferential()
						.getInsuredPriceRate()));// 保价费率折扣
				contractInfo.setCargoFeeDiscount(returnStrNullRate(contract.getPreferential()
						.getReceivePriceRate()));// 接货费折扣
				contractInfo.setDeliveryFeeDiscount(returnStrRate(contract.getPreferential()
						.getDeliveryPriceRate()));// 送货费折扣
			}
			if (null!=contract.getExPreferential()) {
				if (!Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE.equals(contract.getExPreferentialType())) {
					contractInfo.setExpFreightDiscount(returnStrRate(contract.getExPreferential()
							.getChargeRebate()));// 运费折扣
				}
				contractInfo.setExpWxpgenerationRateDiscount(returnStrRate(contract.getExPreferential()
						.getAgentgathRate()));// 代收费率折扣
				contractInfo.setExpChargeRateDiscount(returnStrRate(contract.getExPreferential()
						.getInsuredPriceRate()));// 保价费率折扣
				contractInfo.setExpCargoFeeDiscount(returnStrNullRate(contract.getExPreferential()
						.getReceivePriceRate()));// 接货费折扣
				contractInfo.setExpDeliVeryFeeDiscount(returnStrRate(contract.getExPreferential()
						.getDeliveryPriceRate()));// 送货费折扣
			}
			contractInfo.setBalanceAccount(new BigDecimal(null == contract
					.getArrearaMount() ? 0 : contract.getArrearaMount()).toString());//结算限额
			contractInfo.setPreferentialType(DataDictionaryUtil.getCodeDesc(
							DataHeadTypeEnum.PRIVILEGE_TYPE,
							contract.getPreferentialType()));// 老合同优惠类型---数据字典转换
			contractInfo.setExpressPreferentialType(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.EXPRIVILEGE_TYPE,
					contract.getExPreferentialType()));// 老合同快递优惠类型---数据字典转换
		}
		contractInfo.setNewBalanceAccount(new BigDecimal(null == contract
				.getArrearaMount() ? 0 : contract.getArrearaMount()).toString());// 新结算限额
		contractInfo.setNewPreferentialType(DataDictionaryUtil.getCodeDesc(
				DataHeadTypeEnum.PRIVILEGE_TYPE,
				contract.getPreferentialType()));//新优惠类型
		//合同归属部门标杆编码
		String belongDeptCode=baseDataManager.getDeptById(
				contract.getDept().getId()).getStandardCode();
		
		contractInfo.setDiscount(reDiscount(contract,extendMap.get("applyType")));
		contractInfo.setContrctAscriptionDept(belongDeptCode);// 合同归属部门
		contractInfo.setApplyBondingDept(department.getStandardCode());// 申请绑定部门
		contractInfo.setAscriptionDept(belongDeptCode);// 现有归属部门
		contractInfo.setApplyRenewalDept(department.getStandardCode());// 申请变更部门
		// 组装转换合同附件
		for (FileInfo file : contract.getFileInfoList()) {
			ContractNounInfo contractNounInfo = new ContractNounInfo();
			contractNounInfo.setContractName(file.getFileName());
			// 转换文件的相对路径
//			String prex = PropertiesUtil.getInstance().getProperty("savePath")
//					+ "/";
//			String realPath = org.apache.commons.lang.StringUtils.remove(
//					file.getSavePath(), prex);
			contractNounInfo.setSavePath(file.getSavePath());
			contractNounInfo.setFileName(file.getFileName());
			contractNounInfo.setContractId(file.getSourceId());
			contractNounList.add(contractNounInfo);
		}
		contractInfo.setFile(contractNounList);// 合同附件
		return contractInfo;
	}

	/**
	 * @description 根据合同修改内容获得修改类型.  
	 * @author 潘光均
	 * @version 0.1 2012-9-27
	 * @param Contract
	 *@date 2012-9-27
	 * @return void
	 * @update 2012-9-27 上午9:13:40
	 */
	private String getModifyType(Contract contract) {
		/* 如果修改价格折扣 则对 合同修改类型 set成 discount：只修改折扣
		1、只修改预算的就传 budget 
		2、只修改折扣的传 discount
		3、两项都修改或其它的就传 other*/ 
		Assert.notNull(contract,"contract is null !");
		Contract oldContract = contract.getOldContract();
		
		String modifyContractTax=ContractUtil.compare(contract.getNewContractTax().getInvoiceType(),
				ContractUtil.changeListToContractTax(oldContract).getInvoiceType())?"0":"1";
		String modifyBudget = isBudgetNotModify(contract, oldContract)?"0":"1";
		String modifyDiscount = isDiscountNotModify(contract, oldContract)?"0":"1";
		String modifyExDiscount = isExDiscountNotModify(contract, oldContract)?"0":"1";
		return modifyContractTax+modifyBudget+modifyDiscount+modifyExDiscount;
	}
	
	/**
	 * 
	 * @description 判断新旧合同的零担折扣是否一样.
	 * @author 潘光均
	 * @version 0.1 2012-9-27
	 * @param Contract
	 *            contract Contract oldContract
	 * @date 2012-9-27
	 * @return boolean
	 * @update 2012-9-27 上午9:32:27
	 */
	private boolean isDiscountNotModify(Contract contract, Contract oldContract) {
		if (null == contract || null == oldContract) {
			throw new ContractException(ContractExceptionType.Data_Error);
		}
		
		if (Constant.CONTRACT_PREFERENTIALTYPE_PRICE_REBATE.equals(contract
				.getPreferentialType())
				|| Constant.CONTRACT_PREFERENTIALTYPE_PRICE_REBATE
						.equals(oldContract.getPreferentialType())) {
			return contract.getPreferential().equals(oldContract.getPreferential());
		}else{
			return contract.getPreferential().monthSendEquals(oldContract.getPreferential());
		}
	}
	/**
	 * 
	 * @description 判断新旧合同的快递折扣是否一样.
	 * @author 潘光均
	 * @version 0.1 2012-9-27
	 * @param Contract
	 *            contract Contract oldContract
	 * @date 2012-9-27
	 * @return boolean
	 * @update 2012-9-27 上午9:32:27
	 */
	private boolean isExDiscountNotModify(Contract contract, Contract oldContract) {
		if (null == contract || null == oldContract) {
			throw new ContractException(ContractExceptionType.Data_Error);
		}
		
		if (Constant.CONTRACT_PREFERENTIALTYPE_PRICE_REBATE.equals(contract
				.getExPreferentialType())
				|| Constant.CONTRACT_PREFERENTIALTYPE_PRICE_REBATE
						.equals(oldContract.getExPreferentialType())) {
			return contract.getExPreferential().equals(oldContract.getExPreferential());
		}else{
			return contract.getExPreferential().monthSendEquals(oldContract.getExPreferential());
		}
		
	}
	/**
	 * 
	 * @description 判断新旧合同的结算额度是否一样.
	 * @author 潘光均
	 * @version 0.1 2012-9-27
	 * @param Contract
	 *            contract Contract oldContract
	 * @date 2012-9-27
	 * @return boolean
	 * @update 2012-9-27 上午9:32:27
	 */
	private boolean isBudgetNotModify(Contract contract, Contract oldContract) {
		if (null == contract || null == oldContract) {
			throw new ContractException(ContractExceptionType.Data_Error);
		}
		if (null == contract.getArrearaMount()) {
			contract.setArrearaMount(0d);
		}
		if (null == oldContract.getArrearaMount()) {
			oldContract.setArrearaMount(0d);
		}
		return oldContract.getArrearaMount().equals(contract.getArrearaMount());
	}
	
	//优惠折扣 如果是0或者1则 显示为空
	private String  returnStrRate(Double rate){
		return (0 == rate || 1 == rate)?"":(String.valueOf(rate));
	}
	/**
	 * 
	* @Title: returnStrNullRate
	* @Description: 接货费的null 转换为“”
	* @author chenaichun 
	* @param @param rate
	* @param @return    设定文件
	* @date 2014-2-19 下午3:04:19
	* @return String    返回类型
	* @throws
	* @update 2014-2-19 下午3:04:19
	 */
	private String  returnStrNullRate(Double rate){
		return (null == rate)?"":(String.valueOf(rate));
	}
	
	//合同新签的时候，是否有折扣，有就传 true 没有或其它就传 false
	private String reDiscount(Contract contract, String applyType) {
		if (ContractApplyType.NEW.toString().equals(applyType) && !isNotDiscount(contract)) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 
	 * @description 判断合同是否有折扣.  
	 * @author 潘光均
	 * @version 0.1 2012-9-26
	 * @param Contract
	 *@date 2012-9-26
	 * @return boolean
	 * @update 2012-9-26 下午7:59:15
	 */
	private boolean isNotDiscount(Contract contract) {
		if (null == contract.getPreferential()) {
			return true;
		}
		if (Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(contract.getPreferentialType())) {
			return contract.getPreferential().getAgentgathRate() == 1d
					&& (contract.getPreferential().getDeliveryPriceRate() == 1d)
					&&(contract.getPreferential().getInsuredPriceRate()==1d)
					&&(contract.getPreferential().getReceivePriceRate() == null);
		}
		return contract.getPreferential().getAgentgathRate() == 1d
				&& (contract.getPreferential().getChargeRebate() == 1d)
				&& (contract.getPreferential().getDeliveryPriceRate() == 1d)
				&&(contract.getPreferential().getInsuredPriceRate()==1d)
				&&(contract.getPreferential().getReceivePriceRate() == null);
	}

	/**
	 * @作者：赵斌
	 * @时间：2012-4-13
	 * @描述：改签合同及相关信息
	 * @参数：approveDataList 需修改的审批数据集合，memberId,会员ID
	 * @返回值：工作流号
	 * */
	@Override
	public Map<String,String> changeContract(Contract cont,String contractType) {
		Assert.notNull(cont, "contract must not null !");
		// 1 ,合同业务逻辑验证
		// 合同到期时间
		ContractValidator.validateContractForm(cont);
		
		// 验证结果
		boolean checkResult = checkAmountByCustId(cont, cont
				.getMember().getId(),cont.getArrearaMount());

		// 取得合同id--前台将老合同id设置到了新合同的id上
		String contractId = cont.getId(); //老合同的ID
		Contract contract = contractService.getAllContractInfoById(contractId);
		//查询原合同
		/**
		 * @description:如果是合同新签不同意的合同进行改签， 则合同的状态是无效的
		 *							 则这是修改的合同实际是修改客户的有效合同
		 * @author 潘光均
		 * @date 2012-10-15
		 */
		if (Constant.CONTRACT_STATUS_UNEFFECT.equals(cont.getContractStatus())) {
			List<Contract> contracts = contractService.searchContractAllInfoByCustId(cont.getMember().getId());
			if (CollectionUtils.isEmpty(contracts)) {
				throw new ContractException(ContractExceptionType.ContractDataError);
			}
			for (int i = 0; i < contracts.size(); i++) {
				if (!ContractValidator.isWaitEffectContract(contracts.get(i))
						&& Constant.CONTRACT_STATUS_EFFECT.equals(contracts
								.get(i).getContractStatus())) {
					contract=contracts.get(i);
				}
			}
		}
		// 校验合同是否有未审批的工作流
		ContractValidator.canOperate(contract,ContractHandleType.CHANGESIGN.getHandleType());

		// 如果从ERP迁移的数据，可能没有合同部门，则根据合同的所属部门，创建合同部门
		if (!cheakContractHasBelongDept(contract)) {
			List<ContractDept> depts = produceContractDept(contract, true);
			cont.setContractDepartList(depts);
		}
		//校验是否归属部门--快递经理问题
		if (ExpressAuthDeptUtil.isPointManager(ContextUtil.getCurrentUserRoleIds())) {
			List<Department> departs = baseDataManager
					.getAuthorityBusinessDept(ContextUtil
							.getCurrentUserDept().getStandardCode());
			ContractValidator.isExpressAuthDept(ExpressAuthDeptUtil
					.getAuthDept(departs), contract.getDept().getId());
		}else{
			ContractValidator.isBelongDept(contract);
		}
		// 获取id
		String id = contractService.getContractId();//新的合同ID @cont为新合同
		cont.setId(id);
		// 合同操作记录id
		String logId = contractService.getConOperaLogSequence();
		//设置合同序号
		cont.setContractNum(getContractNum(id));
		// 构造附件信息
		List<FileInfo> fileInfos = cont.getFileInfoList();
		// 完善合同附件信息
		produceFileInfos(fileInfos, logId,
				Constant.FileSourceType_ContractOperatorLog);
		// 优惠信息
		if (null != cont.getPreferential()) {
			cont.getPreferential().setContractId(id);
		}
		/**合同改签 @chenaichun(发票标记修改了就走工作流）
		 * 校验税务信息是否需要走工作流 (true为走工作流)
		 */
		boolean flag = isUpdContractTaxNeedOAWorkFlow(cont,contract);
		
		//设置原合同信息
		cont.setOldContract(contract);
		// 调用OA审批接口生成工作流 得到工作流id
		String workflowId = null;
		Map<String, String> map =new HashMap<String,String>();
		if (ContractUtil.isNewNotNeedNotOAWorkFlow(cont)&&!flag) {
			cont.setContractStatus(Constant.CONTRACT_STATUS_EFFECT);
			//根据合同开始时间和当前时间比较，那个在后边就去那个
			Date beginDate=cont.getContractBeginDate().after(new Date())?cont.getContractBeginDate():new Date();
			Date oldContractEndDate = contract.getContractendDate();
			//如果新合同开始时间在原合同结束时间之前，则原合同的时间为新合同结束时间前一秒
			if (contract.getContractendDate().after(beginDate)) {
				oldContractEndDate = new Date(beginDate.getTime() - 1000);
			}
			/**@author chenaichun
			 * 更新税务时间
			 */
			setContractTaxDate(contract,null,oldContractEndDate,null);
			cont.setContractBeginDate(beginDate);
			contract.setContractendDate(oldContractEndDate);
			//当原合同的时间小于当前时间，则设置原合同为无效状态
			if (contract.getContractendDate().before(new Date())) {
				contract.setContractStatus(Constant.CONTRACT_STATUS_UNEFFECT);
			}
			// 设置新合同状态和生效时间
			// 查询出原合同的合同部门
			List<ContractDept> depts = contract.getContractDepartList();//ConcontractService
			contractService.updateContract(contract);
			
			// 设置合同部门的合同为修改后的合同
			saveApproveConDept(depts,contract,cont);
		}else{
			cont.setContractStatus(Constant.CONTRACT_STATUS_INPROCESS);
			map.put("isStatified", String.valueOf(checkResult));
		}
		map.put("isContractTaxUpd", String.valueOf(flag));
		/**
		 * 合同改签
		 * 添加税务标记 @chenaichun
		 * 插入两条税务标记信息
		 */
		//原合同contract和新合同cont
		cont.setContractTaxList(produceTwoContractTax(contract,cont));
		
		// 设置新增合同操作记录
		cont.setContractWorkflowList(produceOpeLogs(cont, logId, workflowId,
				ContractHandleType.CHANGESIGN.getHandleType(),cont.getContractStatus(),new Date(System.currentTimeMillis()+1000)));
		// 从新得到原合同的操作日志记录的logId
		

		String logId2 = contractService.getConOperaLogSequence();
		// 生成原合同操作记录
		/**
		 * 为了记录原合同id,产生的操作日志
		 */
		contract.setId(contractId);
		cont.getContractWorkflowList().add(
				produceOpeLogs(contract, logId2, workflowId,
						ContractHandleType.CHANGESIGN.getHandleType(),cont.getContractStatus(),null).get(0));
		/**
		 * @description:合同是否有快递信息，决定合同改签工作流流向
		 */
		ContractApplyType type = cont.hasExpressInfo() ? ContractApplyType.EX_NEW
				: ContractApplyType.NEW;
		//设置价格版本
		setContractPriceVersionDate(cont, contract,null);
		
		
		// 保存新合同
		workflowId = saveNewOrUpdateContract(cont, type, map,
				cont.getContractWorkflowList(), null, contractType);
		// 返回值
		//返回到前台的map
		Map<String,String> jsMap = new HashMap<String,String>();
		jsMap.put("workflowId", workflowId);
		jsMap.put("updFlag", String.valueOf(flag));
		return jsMap;
	}
	
	/**
	 * 
	 * <p>
	 * Description:设置合同零担和快递的价格版本<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-27
	 * @param cont
	 * @param contract
	 * void
	 * @param update 
	 */
	private void setContractPriceVersionDate(Contract cont, Contract contract, ContractApplyType update) {
		// 使合同的创建时间和价格版本时间保持一致
		Date date = new Date();
		cont.setCreateDate(date);
		
		//如果是修改，不改变价格版本
		if (ContractApplyType.UPDATE.equals(update)) {
			if (cont.hasExpressInfo()&&Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(cont.getPayWay())) {
				cont.setPriceVersionDate(cont.getPriceVersionDate());
			}
			if (cont.hasLttInfo()&&Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(cont.getExPayWay())) {
				cont.setExPriceVersionDate(cont.getExPriceVersionDate());
			}
		}else{
			/**
			 * 合同新签设置合同价格版本
			 */
			if (null==contract) {
				if (cont.hasExpressInfo()&&Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(cont.getPayWay())) {
					cont.setPriceVersionDate(date);
				}
				if (cont.hasLttInfo()&&Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(cont.getExPayWay())) {
					cont.setExPriceVersionDate(date);
				}
			}
			
			/**
			 * 合同改签或者修改设置价格版本
			 */
			//设置零担合同的价格版本
			if (Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(cont.getPayWay())) {
				if (ContractUtil.modifyContractCommon(cont, contract,true)
						||ContractUtil.modifyLTTContract(cont, contract)) {
					cont.setPriceVersionDate(date);
				}else{
					cont.setPriceVersionDate(contract.getPriceVersionDate());
				}
			} else {
				cont.setPriceVersionDate(null);
			}
			//设置快递合同的价格版本
			if (Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(cont.getExPayWay())) {
				if (ContractUtil.modifyContractCommon(cont, contract,true)
						||ContractUtil.modifyExpressContract(cont, contract)) {
					cont.setExPriceVersionDate(date);
				}else{
					cont.setExPriceVersionDate(contract.getExPriceVersionDate());
				}
			} else {
				cont.setExPriceVersionDate(null);
			}
		}
	}

	/**
	 * 
	 * @绑定合同部门
	 * @author 赵斌
	 * @2012-4-14
	 * @return boolean
	 * @param contractId
	 *            合同id
	 * @param contractDeptId
	 *            合同对应部门id
	 */
	@Override
	public String boundContract(String contractId, String deptId,
			List<FileInfo> fileInfoList) {
		// 参数校验
		Assert.notNull(contractId, "Contract infomation can't null !");
		Assert.notNull(deptId, "dept infomation can't null !");
		// 根据合同id取出合同全部信息
		Contract contract = contractService.getAllContractInfoById(contractId);
		String employeeSubjectName =baseDataManager.getDeptByStandardCode(ContextUtil.getCurrentUserDept().getStandardCode()).getCompanyName();
		if (!ValidateUtil.objectIsEmpty(employeeSubjectName)){
			//验证是否能绑定 
			ContractValidator.checkContractSubject(contract,employeeSubjectName,ContractHandleType.BINDING);
		} else {
			throw new ContractException(ContractExceptionType.Data_Error);
		}
	
		// 校验是否可以操作--是否有正在进行的工作流
		if (ContractValidator.isWaitEffectContract(contract)) {
			ContractValidator.isWaitEffectCanOperate(contract,
					ContractHandleType.BINDING.getHandleType());
		} else {
			ContractValidator.canOperate(contract,ContractHandleType.BINDING.getHandleType());
		}
		// 需要保存的合同信息
		Contract 	cont = new Contract();
		// 如果从ERP迁移的数据，可能没有合同部门，则根据合同的所属部门，创建合同部门
		if (!cheakContractHasBelongDept(contract)) {
			ContractDept dept = produceContractDept(contract, true).get(0);
			cont.getContractDepartList().add(dept);
		}
		// 校验改部门是不是合同的绑定部门--如果绑定过就不能让在绑定
		ContractValidator.chackDeptHasBound(contract.getContractDepartList(),
				deptId);
		/**
		 * @description:合同是否有快递信息，决定合同绑定工作流流向
		 */
		ContractApplyType type = contract.hasExpressInfo() ? ContractApplyType.EX_ADD_BELONGDEPT
				: ContractApplyType.ADD_BELONGDEPT;
		// 调用OA审批接口生成工作流 得到工作流id
		String workflowid = processWorkflow(contract,type,new HashMap<String,String>(),"");
		if (workflowid != null) {
			// 主要进行合同信息的构造
			cont.setId(contract.getId());
			// 操作记录id
			String logId = contractService.getConOperaLogSequence();
			// 操作记录
			cont.setContractWorkflowList(produceOpeLogs(contract, logId,
					workflowid, ContractHandleType.BINDING.getHandleType(),Constant.CONTRACT_STATUS_INPROCESS,null));
			// 设置操作记录绑定部门
			cont.getContractWorkflowList().get(0)
					.setChangedDept(ContextUtil.getCurrentUserDept());
		}
		// 保存合同的附件，合同部门，操作记录
		saveContractAttach(cont);
		// 返回数据
		return workflowid;
	}

	/**
	 * 
	 * @解除合同部门绑定
	 * @author 赵斌
	 * @2012-4-14
	 * @return boolean
	 * @param contractId
	 *            合同id
	 * @param contractDeptId
	 *            合同对应部门id
	 * 单元测试           
	 * @see com.deppon.crm.module.customer.server.manager.BoundContractTest
	 */
	@Override
	public boolean removeContract(Contract contractInfo, String deptId) {
		// 如果 合同状态为已同意 才允许进行合同部门绑定
		Contract contract = contractService.getAllContractInfoById(contractInfo.getId());
		if (null == contract) {
			throw new ContractException(ContractExceptionType.ContractIsNull);
		}
		// 如果从ERP迁移的数据，可能没有合同部门，则根据合同的所属部门，创建合同部门
		if (!cheakContractHasBelongDept(contract)) {
			ContractDept dept = produceContractDept(contract, true).get(0);
			contractService.createContractDept(dept);
		}
		List<ContractDept> depts = contract.getContractDepartList();
		ContractValidator.isBondingDept(depts, deptId,contractInfo.getContractDepartList());
		// 校验是否可以操作
		// 校验是否可以操作--是否有正在进行的工作流
		if (ContractValidator.isWaitEffectContract(contract)) {
			ContractValidator.isWaitEffectCanOperate(contract,
					ContractHandleType.UNBINDING.getHandleType());
		} else {
			ContractValidator.canOperate(contract,ContractHandleType.UNBINDING.getHandleType());
		}
		String ownerDeptId = contract.getDept().getId();
		// 解绑部门的id为空或者解绑部门是归属部门则不让解绑
		if (deptId == null || deptId.equals(ownerDeptId)) {
			throw new ContractException(ContractExceptionType.OwnerDeptIsError);
		}
		ContractDept dept= null;
		for(int i=0;i< contract.getContractDepartList().size();i++){
			if (deptId.equals(contract.getContractDepartList().get(i).getContractDept().getId())) {
				dept =contract.getContractDepartList().get(i);
			}
		}
		if (null!=dept) {
			dept.setRemoveTime(new Date());
			dept.setState(false);
		}
		contractService.updateContractDept(dept);
		/**
		 * @description 待生效合同也解绑
		 * @author 潘光均
		 * @date 2012-09-27
		 */
		List<Contract> contracts = contractService.getWaitEffectContractByContractId(contract.getId());
		if (CollectionUtils.isNotEmpty(contracts)) {
			if (1 < contracts.size()) {
				throw new ContractException(ContractExceptionType.CUSTOMERHASTWOWAITEFFECTCONRACT);
			}
			if (CollectionUtils.isNotEmpty(contract.getContractDepartList())) {
				for (int i = 0; i < contracts.get(0).getContractDepartList()
						.size(); i++) {

					contractService.removeContractDept(
							contracts.get(0).getId(), deptId);
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * @改变合同归属部门
	 * @author 赵斌
	 * @2012-4-17
	 * @return String
	 * @param contractId
	 *            合同id
	 * @param deptId
	 *            合同对应部门id
	 * @param contractNoun
	 *            合同附件
	 */
	@Override
	public String changeContractOwner(String contractId, String deptId,
			List<FileInfo> fileInfoList) {
		Contract contract = contractService.getAllContractInfoById(contractId);
		if (null == contract) {
			throw new ContractException(ContractExceptionType.ContractIsNull);
		}
		// 当合同的归属部门操作时，提示“贵部已是合同的归属部门，无需变更！”
		// 得到合用所属部门Id
		String contractDeptId = contract.getDept().getId();
		// 得到当前用户所属部门Id
		String currentDeptId = deptId;
		// 判断用户身份，若当前用户部门为此合同归属部门
		if (currentDeptId.equals(contractDeptId)) {
			throw new ContractException(ContractExceptionType.DeptIsBelongDept);
		}
		MemberCondition searchCustCondition = new MemberCondition();
		searchCustCondition.setLimit(-1);//-1标识不用分页
		//添加数据权限
		List<String> deptIds = baseDataManager.getDataAuthorityDepts(currentDeptId);
		searchCustCondition.setMemberId(contract.getMember().getId());
		searchCustCondition.setDeptIds(deptIds);
		
		List<MemberResult> memberResultList = alterMemberManager.searchMemberWithAuth(searchCustCondition);
		if (CollectionUtils.isEmpty(memberResultList)) {
			throw new ContractException(ContractExceptionType.CUSTOMERINVALIDE);
		}
		//验证是否能变跟 
		String employeeSubjectName =baseDataManager.getDeptByStandardCode(ContextUtil.getCurrentUserDept().getStandardCode()).getCompanyName() ;
		if (!ValidateUtil.objectIsEmpty(employeeSubjectName)){
			//验证是否能绑定 
			ContractValidator.checkContractSubject(contract,employeeSubjectName,ContractHandleType.CHANGEBELONGDEPT);
		} else {
			throw new ContractException(ContractExceptionType.Data_Error);
		}
		
		// 校验是否可以操作--是否有正在进行的工作流
		if (ContractValidator.isWaitEffectContract(contract)) {
			ContractValidator.isWaitEffectCanOperate(contract,
					ContractHandleType.CHANGEBELONGDEPT.getHandleType());
		} else {
			ContractValidator.canOperate(contract,ContractHandleType.CHANGEBELONGDEPT.getHandleType());
		}
		if (null != fileInfoList && 0 < fileInfoList.size()) {
			for (FileInfo f : fileInfoList) {
				f.setSourceId(contractId);
			}
			contract.setFileInfoList(fileInfoList);
		}
		/**
		 * @description:合同是否有快递信息，决定合同归属变更工作流流向
		 */
		ContractApplyType type = contract.hasExpressInfo() ? ContractApplyType.EX_CONVERT_BELONGDEPT
				: ContractApplyType.CONVERT_BELONGDEPT;
		
		// 调用OA审批接口生成工作流 得到工作流id
		String workflowid = processWorkflow(contract,type,new HashMap<String,String>(),"");
		// 需要保存的合同信息
		Contract cont = new Contract();
		if (workflowid != null) {
			// 如果从ERP迁移的数据，可能没有合同部门，则根据合同的所属部门，创建合同部门
			if (!cheakContractHasBelongDept(contract)) {
				ContractDept dept = produceContractDept(contract, true).get(0);
				contract.getContractDepartList().add(dept);
				cont.getContractDepartList().add(dept);
			}
			// 变更前部门
			ContractDept previousDept = null;
			// 合同变更前部门和绑定部门
			List<ContractDept> depts = contract.getContractDepartList();
			// 遍历查找归属部门
			if (null != depts && 0 < depts.size()) {
				for (int i = 0; i < depts.size(); i++) {
					if (depts.get(i).isDept()) {
						previousDept = depts.get(i);
					}
				}
			}
			// 操作记录id
			String logId = contractService.getConOperaLogSequence();
			// 操作记录
			List<ContractOperatorLog> logs = produceOpeLogs(contract, logId,
					workflowid,
					ContractHandleType.CHANGEBELONGDEPT.getHandleType(),Constant.CONTRACT_STATUS_INPROCESS,null);
			Department changedDept = new Department();
			changedDept.setId(deptId);
			// 设置操作记录的变更前部门和变更后部门
			if (null != logs && 0 < logs.size()) {
				logs.get(0).setPreviousDept(previousDept.getContractDept());
				logs.get(0).setChangedDept(changedDept);
			}
			cont.setContractWorkflowList(logs);

			// 完善合同信息
			produceFileInfos(fileInfoList, logId,
					Constant.FileSourceType_ContractOperatorLog);
			cont.setFileInfoList(fileInfoList);
		}
		// 保存合同的附件，合同部门，操作记录，不能
		saveContractAttach(cont);
		return workflowid;
	}

	/**
	 * @description 完善附件信息.
	 * @author 潘光均
	 * @version 0.1 2012-6-30
	 * @param
	 * @date 2012-6-30
	 * @return List<FileInfo>
	 * @update 2012-6-30 上午9:23:13
	 */
	private List<FileInfo> produceFileInfos(List<FileInfo> fileInfoList,
			String logId, String fileType) {
		FileInfo fileInfo = null;
		// 对附件进行遍历，设置附件的sourceId，创建人，sourceType等
		if (null != fileInfoList && 0 <= fileInfoList.size()) {
			for (int i = 0; i < fileInfoList.size(); i++) {
				fileInfo = fileInfoList.get(i);
				fileInfo.setCreateDate(new Date());
				fileInfo.setCreateDept(ContextUtil.getCurrentUserDeptId());
				fileInfo.setCreateUser(ContextUtil.getCreateOrModifyUserId());
				fileInfo.setSourceId(logId);
				fileInfo.setCreateUser(ContextUtil.getCreateOrModifyUserId());
				fileInfo.setSourceType(fileType);
			}
		}
		return fileInfoList;
	}

	/**
	 * 
	 * <p>
	 * 根据ID查询出合同所有信息<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-6-13
	 * @param id
	 * @return ContractView
	 */
	public Contract lookDetailContractInfo(String id) {
		Contract cont = contractService.getAllContractInfoById(id);
		if (null!=cont&&null!=cont.getPreferential()
				&& !Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(cont.getPreferentialType())) {
			cont.getPreferential().setModifyDate(null);
		}
		if(null!=cont&&null!=cont.getExPreferential()
				&& !Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE.equals(cont.getExPreferentialType())){
			cont.getExPreferential().setModifyDate(null);
		}
		cont.setArrAmount(contractService.getArrearaMountByCustId(cont
				.getMember().getId()));
		cont.setExArrAmount(contractService.getExArrearaMountByCustId(cont
				.getMember().getId()));
		return cont;
	}

	/**
	 * 
	 * <p>
	 * 获得要修改的合同信息数据<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-6-13
	 * @param contractId
	 * @return Contract
	 */
	public Contract getUpdateContractInfo(String contractId,String handType) {
		// 校验合同状态
		Assert.notNull(contractId, "cancel contract id con't null !");
		Contract contract = contractService.getAllContractInfoById(contractId);
		// 设置零担最近三个月份发货金额
		contract.setArrAmount(contractService.getArrearaMountByCustId(contract
				.getMember().getId()));
		// 设置快递最近三个月份发货金额
		contract.setExArrAmount(contractService.getExArrearaMountByCustId(contract
				.getMember().getId()));
		List<Contract> contracts = contractService.searchContractAllInfoByCustId(contract.getMember().getId());
		if (CollectionUtils.isEmpty(contracts)) {
			throw new RuntimeException("customer has't contract !");
		}else{
			ContractValidator.checkCustHasInproContract(contracts);
		}
		// 校验合同是否可以修改
		ContractValidator.canOperate(contract,ContractHandleType.UPDATE.getHandleType());
		//快递点部对应的营业部id集合
		List<Department> departs = baseDataManager
				.getAuthorityBusinessDept(ContextUtil
						.getCurrentUserDept().getStandardCode());
		List<String> expressDepts = ExpressAuthDeptUtil.getAuthDept(departs);
		// 校验当前部门是否合同归属部门
		ContractValidator.checkIsOwnerDept(contract, handType, expressDepts);
		return contract;
	}

	/**
	 * @根据传入条件分页查询合同信息
	 * @author 赵斌
	 * @2012-4-18
	 * @return
	 */
	@Override
	public List<Contract> searchContract(ContractCondition condition,
			int start, int limit) {
		try {
			// 当客户编码 和 合同编码 不为空时，才进行数据权限控制。
			if (ValidateUtil.objectIsEmpty(condition.getCustNumber())
					&& ValidateUtil.objectIsEmpty(condition.getContractNum())) {
				if (Constant.ORDER_MANAGER_DEPARTMENT_STANDARDCODE
						.equals(ContextUtil.getCurrentUserDept()
								.getStandardCode())) {
					condition.setContractSubject(Constant.DEPPON_COMPANY);
				}else{
					// 添加数据权限
					/**
					 * @description 如果是快递点部，取当前部门对应的营业部部门权限
					 * @author pgj
					 * @date 2013-09-24
					 */
					List<Department> departs = baseDataManager
							.getAuthorityBusinessDept(ContextUtil
									.getCurrentUserDept().getStandardCode());
					List<String> deptIds = ExpressAuthDeptUtil.getAuthDept(
							departs);
					if (!StringUtils.isEmpty(condition.getDeptId())) {
						List<String> depts = new ArrayList<String>();
						depts.add(condition.getDeptId());
						condition.setDeptIds(depts);
					}else{
						if (!CollectionUtils.isEmpty(deptIds)) {
							condition.setDeptIds(deptIds);
						}else{
							condition.setDeptIds(ContextUtil
									.getDataAuthorityDepts(condition.getDeptId()));
						}
					}
				}
			}
			
			return contractService.searchContract(condition, start, limit);
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
				private static final long serialVersionUID = 1L;
			};
		}
	}

	/**
	 * 
	 * @作废合同信息
	 * @author 赵斌
	 * @2012-4-19
	 * @return String 工作流号
	 * @param contractId
	 *            合同id
	 */
	@Override
	public String cancelContract(String contractId) {
		if (StringUtils.isEmpty(contractId)) {
			throw new ContractException(ContractExceptionType.Data_Error);
		}
		Assert.notNull(contractId, "cancel contract id con't null !");
		// 取出合同信息
		Contract contract = contractService.getAllContractInfoById(contractId);
		if (null == contract) {
			throw new ContractException(ContractExceptionType.ContractIsNull);
		}
		
		if (ContractValidator.isWaitEffectContract(contract)) {
			ContractValidator.isWaitEffectCanOperate(contract, ContractHandleType.OBSOLETE.getHandleType());
		}else{
			ContractValidator.canOperate(contract,ContractHandleType.OBSOLETE.getHandleType());
		}
		// 得到合用所属部门Id
		String contractDeptId = contract.getDept().getId();
		// 得到当前用户所属部门Id
		String deptId = ContextUtil.getCurrentUserDeptId();
		if (!deptId.equals(contractDeptId)) {
			throw new ContractException(
					ContractExceptionType.ContractCanNotCancell);
		}
		/**
		 * @description:合同是否有快递信息，决定合同作废工作流流向
		 */
		ContractApplyType type = contract.hasExpressInfo() ? ContractApplyType.EX_CANCEL
				: ContractApplyType.CANCEL;
		/**
		 * @chenaichun 合同终止工作流迁移到CRM
		 * @date 2014/1/13
		 */
		String bizCode=bpsWorkflowManager.bizCode();
		// 调用OA审批接口生成工作流 得到工作流id
		String workflowId = processWorkflow(contract,type,new HashMap<String,String>(),bizCode);
		// 操作日志
		String logId = contractService.getConOperaLogSequence();
		List<ContractOperatorLog> logs = produceOpeLogs(contract, logId,
				workflowId, ContractHandleType.OBSOLETE.getHandleType(),Constant.CONTRACT_STATUS_INPROCESS,new Date());
		contractService.createContractOpeLog(logs.get(0));
		return workflowId;
	}

	/**
	 * 
	 * @查询出满足条件的合同总数
	 * @author 赵斌
	 * @2012-4-19
	 * @return
	 */
	@SuppressWarnings("serial")
	@Override
	public int countContract(ContractCondition condition) {
		try {
			if (condition == null) {
				throw new ContractException(
						ContractExceptionType.QueryConditionIsNull);
			}
			return contractService.countContract(condition);
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户查询所有合同<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param memberId
	 * @return
	 *
	 */
	@Override
	public List<ContractDetailView> getContractsByMemberId(String memberId) {
		List<ContractDetailView> list = new ArrayList<ContractDetailView>();
		if (StringUtils.isEmpty(memberId)) {
			return list;
		}
		List<Contract> conList = contractService
				.getContractsByMemberId(memberId);
		//客户三个月发货情况
		String arrAmout = contractService.getArrearaMountByCustId(memberId);
		for (Contract contract : conList) {
			contract.setArrAmount(arrAmout);
			list.add(ContractUtil.changeContractToContractDetailView(contract));
		}
		return list;
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户编码查询合同<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param custNumber
	 * @return
	 *
	 */
	@Override
	public List<Contract> getContractsByCustNumber(String custNumber) {
		return contractService.getContractsByCustNumber(custNumber);
	}
	/**
	 * 
	 * <p>
	 * Description:获得最新的合同信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param memberId
	 * @return
	 *
	 */
	@Override
	public ContractDetailView getLastestContract(String memberId) {
		Contract contract = null;
		List<Contract> list = null;
		if (!StringUtils.isEmpty(memberId)) {
			list = contractService.getContractsByMemberId(memberId);
			if (CollectionUtils.isNotEmpty(list)) {
				contract = list.get(0);
			}
		}
		return ContractUtil.changeContractToContractDetailView(contract);
	}

	/**
	 * 
	 * <p>
	 * Description:查询合同详情<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param id
	 * @return
	 *
	 */
	@Override
	public ContractDetailView getContractDetailById(String id) {
		Contract contract = null;
		if (!StringUtils.isEmpty(id)) {
			contract = contractService.getContractDetailById(id);
			if (null!=contract&&null!=contract.getPreferential()
					&& !Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(contract.getPreferentialType())) {
				contract.getPreferential().setModifyDate(null);
			}
			if(null!=contract&&null!=contract.getExPreferential()
					&& !Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE.equals(contract.getExPreferentialType())){
				contract.getExPreferential().setModifyDate(null);
			}
			//近三个月金额
			contract.setArrAmount(contractService.getArrearaMountByCustId(contract.getMember().getId()));
			//快递近三个月发货金额
			contract.setExArrAmount(contractService.getExArrearaMountByCustId(contract.getMember().getId()));
			if (!StringUtils.isEmpty(contract.getId())) {
				List<ContractOperatorLog> logs = contractService
						.getContractOperatorLogByContractId(contract.getId());
				contract.setContractWorkflowList(logs);
			}
		}
		return ContractUtil.changeContractToContractDetailView(contract);
	}

	/**
	 * 
	 * @判断当前部门是否可以做出发或者到达
	 * @author 赵斌
	 * @2012-4-27
	 * @return boolean
	 * @param deptId
	 *            当前部门
	 */
	public boolean isLeaveArrive(String deptId) {
		int leave = Integer.parseInt(contractService.getLeave(deptId));
		int arrive = Integer.parseInt(contractService.getArrive(deptId));
		if (leave == 1 || arrive == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @工作流审批完成之后调用
	 * @author 赵斌
	 * @2012-5-3
	 * @return void
	 * @param wkid
	 *            工作流id
	 * @param wkStatus
	 *            工作流审批状态
	 * @param wkman
	 *            最后审批人
	 * @param wktime
	 *            最后审批时间
	 */
	@Override
	public boolean contractApprove(String wkid, boolean wkStatus, String wkman,
			Date wktime) {
		// 获取合同操作记录信息
		List<ContractOperatorLog> logs = contractService
				.searchOperaLogByWorkflowId(wkid);
		// 工作流信息可能是多条
		if (null != logs && 0 < logs.size()) {
			// 取得操第一条操作记录
			ContractOperatorLog log = logs.get(0);
			// 更新工作流信息
			updateWorkflowInfo(wkStatus, wkman, logs);
			// 合同新增
			if (ContractHandleType.INSERT.getHandleType().equals(
					log.getOperatorType())) {
				contractCreateApprove(logs, wkStatus,wktime);
				// 绑定部门
			} else if (ContractHandleType.BINDING.getHandleType().equals(
					log.getOperatorType())) {
				boundContractApprove(logs, wkStatus, wktime);
				// 变更归属部门
			} else if (ContractHandleType.CHANGEBELONGDEPT.getHandleType()
					.equals(log.getOperatorType())) {
				updateOwnerContract(logs, wkStatus);
				// 作废
			} else if (ContractHandleType.OBSOLETE.getHandleType().equals(
					log.getOperatorType())) {
				updateCancelContract(logs, wkStatus, wktime);
				// 改签
			} else if (ContractHandleType.CHANGESIGN.getHandleType().equals(
					log.getOperatorType())) {
				updateContractApprove(logs, wkStatus, wktime);
				// 修改
			} else if (ContractHandleType.UPDATE.getHandleType().equals(
					log.getOperatorType())) {
				contractUpdateApprove(logs, wkStatus, wktime);
			}
			CustomerInfoLog.commit();
		} else {
			throw new RuntimeException(
					"workflow Data exception! appdata not exist !");
		}

		return true;
	}

	/**
	 * @description 合同修改回调方法.
	 * @author 潘光均
	 * @version 0.1 2012-9-3
	 * @param
	 * @date 2012-9-3
	 * @return void
	 * @update 2012-9-3 下午4:50:09
	 */
	private void contractUpdateApprove(List<ContractOperatorLog> logs,
			boolean wkStatus, Date wktime) {
		// 新合同信息
		Contract cont = null;
		// 原合同信息
		Contract oldContract = null;
		//分别得到新旧合同
		Map<String, Contract> contracts = getBeforeAndNewContract(logs);
		oldContract = contracts.get("bConctract");
		cont = contracts.get("mContract");
		if (Constant.CONTRACT_STATUS_UNEFFECT.equals(oldContract.getContractStatus())) {
			ContractCondition con = new ContractCondition();
			con.setCustId(cont.getMember().getId());
			List<Contract> contracts2 = contractService.searchContract(con, 0, Integer.MAX_VALUE);
			if (CollectionUtils.isEmpty(contracts2)) {
				throw new ContractException(ContractExceptionType.ContractDataError);
			}
			for (int i = 0; i < contracts2.size(); i++) {
				if (!ContractValidator.isWaitEffectContract(contracts2.get(i))
						&& Constant.CONTRACT_STATUS_EFFECT.equals(contracts2
								.get(i).getContractStatus())) {
					oldContract=contracts2.get(i);
				}
			}
		}
			//审批同意
		if (wkStatus) {
			//@修改  如果新合同一直未审批 那么旧合同状态和时间不会修改 @author chenaichun
			if (new Date().after(cont.getContractendDate())) {
				cont.setContractStatus(Constant.CONTRACT_STATUS_UNEFFECT);
			}else{
				cont.setContractStatus(Constant.CONTRACT_STATUS_EFFECT);
				//设置税务时间@ chenaichun
				setContractTaxDate(oldContract, cont,new Date(wktime.getTime() - 1000),wktime);
				// 设置旧合同状态和失效时间
				oldContract.setContractendDate(new Date(wktime.getTime() - 1000));
				oldContract.setContractStatus(Constant.CONTRACT_STATUS_UNEFFECT);
				// 设置新合同状态和生效时间
				cont.setContractBeginDate(wktime);
			}
			// 查询出原合同的合同部门
			List<ContractDept> depts = contractService.queryContractDeptByContractId(oldContract.getId());
			// 设置合同部门的合同为修改和的合同
			saveApproveConDept(depts,oldContract,cont);
			/**
			 * @description 合同附件都是从工作流中取得，合同附件根本不用保存
			 * @author 潘光均
			 */
			// 更新原合同信息
			contractService.updateContract(oldContract);
		} else {
			cont.setContractStatus(Constant.CONTRACT_STATUS_UNEFFECT);
		}
		// 更新新合同状态
		contractService.updateContract(cont);
	}

	/**
	 * @description 合同修改审批同意后，保存合同附件.
	 * @author 潘光均
	 * @version 0.1 2012-9-4
	 * @param
	 * @date 2012-9-4
	 * @return void
	 * @update 2012-9-4 上午10:16:14
	 */
	@SuppressWarnings("unused")
	private void saveApproveFiles(Contract cont, List<FileInfo> files) {
		if (null != files && 0 < files.size()) {
			for (int i = 0; i < files.size(); i++) {
				files.get(i).setSourceId(cont.getId());
				// 保存修改后合同附件
				if (!StringUtils.isEmpty(files.get(i).getFileName())) {
					fileManager.saveFileInfo(files.get(i));
				}
			}
		}
	}

	/**
	 * @description 合同审批同意后，保存合同部门.
	 * @author 潘光均
	 * @version 0.1 2012-9-4
	 * @param
	 * @date 2012-9-4
	 * @return void
	 * @update 2012-9-4 上午10:15:55
	 */
	private void saveApproveConDept(List<ContractDept> depts,Contract beforeContract,Contract mContract) {
		/**
		 * @date 2012-11-08
		 * @author 潘光均
		 * 为新合同产生归属部门
		 */
		ContractDept contractDept = new ContractDept();
		contractDept.setContract(mContract);
		contractDept.setBoundTime(mContract.getContractBeginDate());
		contractDept.setContractDept(mContract.getDept());
		contractDept.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		contractDept.setRemoveTime(mContract.getContractendDate());
		contractDept.setDept(true);
		contractService.createContractDept(contractDept);
		
		// 设置合同部门的合同为修改后的合同
		if (null != depts && 0 < depts.size()) {
			for (int i = 0; i < depts.size(); i++) {
				//如果合同部门不是一个有效的合同部门，就不需要添加
				if (!depts.get(i).isState()) {
					continue;
				}
				// 先把原合同绑定部门的解绑时间设置为原合同的终止时间
				depts.get(i).setRemoveTime(
						beforeContract.getContractendDate());
				contractService.updateContractDept(depts.get(i));
				// 在设置新合同绑定部门的新的绑定时间和解绑

				/**
				 *  @date 2012-11-08
				 * @author 潘光均
				 * 上边已经产生过合同归属部门，不用再添加合同归属部门
				 */
				if (depts.get(i).isDept()) {
					continue;
				}
				
				depts.get(i).setContract(mContract);
				depts.get(i).setState(true);
				depts.get(i).setBoundTime(mContract.getContractBeginDate());
				//加解绑时间
				depts.get(i).setRemoveTime(mContract.getContractendDate());
				// 保存改签后合同部门
				contractService.createContractDept(depts.get(i));
			}
		}
	}

	/**
	 * @description 工作流审批完成后，更新工作流信息.
	 * @author 潘光均
	 * @version 0.1 2012-7-9
	 * @param
	 * @date 2012-7-9
	 * @return void
	 * @update 2012-7-9 下午3:56:14
	 */
	private void updateWorkflowInfo(boolean wkStatus, String wkman,
			List<ContractOperatorLog> logs) {
		// 构造需要更新的操作记录对象集合
		ContractOperatorLog modifyLog = null;
		for (int i = 0; i < logs.size(); i++) {
			modifyLog = new ContractOperatorLog();
			modifyLog.setId(logs.get(i).getId());
			modifyLog.setApprovalMan(wkman);
			modifyLog.setModifyDate(new Date());
			// 如果审批同意
			if (wkStatus) {
				modifyLog.setApprovalState(Constant.CONTRACT_AGREED);
				// 审批不同意
			} else {
				modifyLog.setApprovalState(Constant.CONTRACT_DISAGREE);
			}
			// 更新操作记录
			contractService.updateContractOperaLog(modifyLog);
		}
	}

	/**
	 * @description 合同归属部门变更回调方法.
	 * @author 潘光均
	 * @version 0.1 2012-6-30
	 * @param
	 * @date 2012-6-30
	 * @return void
	 * @update 2012-6-30 上午9:40:04
	 */
	private void updateOwnerContract(List<ContractOperatorLog> logs,
			boolean wkStatus) {
		// 操作记录只能是一条
		Assert.isTrue(1 == logs.size(), "workflow exception !");
		// 变更前部门
		ContractDept previosDept = null;
		// 变更后部门
		ContractDept changedDept = null;
		// 操作日志只能是一条
		ContractOperatorLog log = logs.get(0);
		Contract contract  = logs.get(0).getContract();
		if (wkStatus) {
			//同意才去查询修改
			previosDept = contractService.getContractDeptByDeptId(log.getContract()
					.getId(), log.getPreviousDept().getId());
			changedDept = contractService.getContractDeptByDeptId(log.getContract()
					.getId(), log.getChangedDept().getId());
			//用于修改的合同信息
			Contract updateContract = new Contract();
			updateContract.setId(contract.getId());
			updateContract.setDept(log.getChangedDept());
			updateContract.setIfForeignGoods(contract.getIfForeignGoods());
			updateContract.setDunningDeptCode(contract.getDunningDeptCode());
			if (Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract.getPayWay())
				||Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract.getExPayWay())) {
				//月结合同原有的可使用额度
				updateContract.setUseableArrearAmount(contract.getUseableArrearAmount());
				updateContract.setDebtDays(contract.getDebtDays());
				updateContract.setResultDate(contract.getResultDate());
				updateContract.setArrearaMount(contract.getArrearaMount());
			}
			
			contractService.updateContract(updateContract);
			
			previosDept.setDept(false);
			// 更新绑定前部门为非归属部门
			contractService.updateContractDept(previosDept);
			// 如果变更的归属部门部门不是合同的绑定部门，则新增一条合同部门
			if (null == changedDept||!changedDept.isState()) {
				changedDept = produceContractDept(contract, true).get(0);
				changedDept.setContractDept(log.getChangedDept());
				contractService.createContractDept(changedDept);
			} else {
				// 如果变更部门是一个绑定部门，则更新改部门为归属部门呢
				changedDept.setDept(true);
				contractService.updateContractDept(changedDept);
			}
		}
	}

	/**
	 * @description 合同作废工作流回调方法.
	 * @author 潘光均
	 * @version 0.1 2012-6-29
	 * @param
	 * @date 2012-6-29
	 * @return void
	 * @update 2012-6-29 下午8:41:10
	 */
	private void updateCancelContract(List<ContractOperatorLog> logs,
			boolean wkStatus, Date wktime) {
		// 取出的合同
		Contract contract;
		// 需要修改的合同
		Contract modifyContract;
		Assert.isTrue(1==logs.size(),"contractWorkflow exception");
		contract = logs.get(0).getContract();
		if (null != contract) {
			modifyContract = new Contract();
			modifyContract.setPreferential(contract.getPreferential());
			modifyContract.setId(contract.getId());
			// 如果审批通过，合同作废，合同失效
			if (wkStatus) {
				
				modifyContract.setContractStatus(Constant.CONTRACT_STATUS_UNEFFECT);
				/*
				 * 当终止待生效合同时，审批通过后，将合同生效时间和失效时间都清空，
				 * 因为此合同从未生效，为避免合同日期重叠故做此处理。
				 */
				if (ContractValidator.isWaitEffectContract(contract)) {
					/**
					 * @author chenaichun 
					 * 修改待生效合同的税务时间 如果有两条税务信息 删掉没有在用的那条
					 */
					if(contract.getContractTaxList().size()==2){
						ContractTax usingContractTax = ContractUtil.changeListToContractTax(contract);
						ContractTax notUsingContractTax = ContractUtil.findAnotherContractTax(contract, usingContractTax);
						contractService.deleteContractTax(notUsingContractTax.getId());
					}
					modifyContract.setContractBeginDate(null);
					modifyContract.setContractendDate(null);
					contractService.updateContract4Wait(modifyContract);
					return;
				}
				// 反写合同失效时间为工作流同意时间
				wktime = wktime == null ? new Date() : wktime;
				Date oldContractEndDate =  new Date();
				oldContractEndDate = contract.getContractendDate();
				if(wktime.before(oldContractEndDate)){
					oldContractEndDate = wktime;
				}
				/**@author chenaichun
				 * 合同终止后 修改对应税务时间
				 */
				modifyContract.setContractTaxList(contract.getContractTaxList());
				modifyContract.setContractendDate(contract.getContractendDate());
				setContractTaxDate(modifyContract, null,oldContractEndDate,null);
				modifyContract.setContractendDate(oldContractEndDate);
				
				
				/**
				 * @description :修改绑定部门的解绑时间
				 * @author 潘光均
				 * @date :2012-10-25
				 */
				if (null != contract.getContractDepartList()
						&& 0 < contract.getContractDepartList().size()) {
					for (int i = 0; i < contract.getContractDepartList().size(); i++) {
						Date removeTime = modifyContract.getContractendDate()
								.after(contract.getContractDepartList().get(i)
										.getRemoveTime()) ? contract
								.getContractDepartList().get(i).getRemoveTime()
								: modifyContract.getContractendDate();
						contract.getContractDepartList().get(i)
								.setRemoveTime(removeTime);
						contractService.updateContractDept(contract
								.getContractDepartList().get(i));
					}
				}
			}
			modifyContract.setIfForeignGoods(contract.getIfForeignGoods());
			modifyContract.setDunningDeptCode(contract.getDunningDeptCode());
			if (Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract.getPayWay()) ||
					Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract.getExPayWay())) {
				//月结合同原有的可使用额度
				modifyContract.setUseableArrearAmount(contract.getUseableArrearAmount());
				modifyContract.setDebtDays(contract.getDebtDays());
				modifyContract.setResultDate(contract.getResultDate());
				modifyContract.setArrearaMount(contract.getArrearaMount());
			}
			// 更新合同信息
			contractService.updateContract(modifyContract);
		}
	}

	/**
	 * @description 改签合同审批后回调方法.
	 * @author 潘光均
	 * @version 0.1 2012-6-29
	 * @param
	 * @date 2012-6-29
	 * @return void
	 * @update 2012-6-29 下午6:42:49
	 */
	private void updateContractApprove(List<ContractOperatorLog> logs,
			boolean wkStatus, Date appDate) {
		// 原合同
		Contract beforeContract =  new Contract();
		// 新合同
		Contract mContract = new Contract();
		// 分别得到原合同和新合同
		Map<String, Contract> contracts = getBeforeAndNewContract(logs);
		beforeContract = contracts.get("bConctract");
		mContract = contracts.get("mContract");
		/**
		 * 如果改签的合同是无效的合同则，实际修改需要修改客户有效的合同
		 */
		if (Constant.CONTRACT_STATUS_UNEFFECT.equals(beforeContract.getContractStatus())) {
			List<Contract> contracts2 = contractService.searchContractAllInfoByCustId(mContract.getMember().getId());
				if (CollectionUtils.isEmpty(contracts2)) {
					throw new ContractException(ContractExceptionType.ContractDataError);
				}
				for (int i = 0; i < contracts2.size(); i++) {
					if (!ContractValidator.isWaitEffectContract(contracts2.get(i))
							&& Constant.CONTRACT_STATUS_EFFECT.equals(contracts2
									.get(i).getContractStatus())) {
						beforeContract=contracts2.get(i);
					}
				}
		}
		// 如果审批同意，则新合同生效，原合同无效
		if (wkStatus) {
			// 设置新合同生效时间和原合同失效时间
			if (new Date().after(mContract.getContractendDate())) {
				mContract.setContractStatus(Constant.CONTRACT_STATUS_UNEFFECT);
			}else{
				// 如果新合同审批同意时间早于当前时间，则新合同的生效开始时间为当前时间
				Date newerContractBeginDate = mContract.getContractBeginDate();
				if (mContract.getContractBeginDate().before(appDate)) {
					newerContractBeginDate = appDate;
				}
				// 原合同失效时间
				Date oldConctractEndDate = beforeContract.getContractendDate();
				// 如果原合同的失效时间晚于新合同起始生效时间
				if (!oldConctractEndDate.before(newerContractBeginDate)) {
					oldConctractEndDate = new Date(newerContractBeginDate.getTime()-1000);
				}
				//改签  审批后重新设置税务时间@ chenaichun
				setContractTaxDate(beforeContract, mContract,oldConctractEndDate,newerContractBeginDate);
				
				mContract.setContractStatus(Constant.CONTRACT_STATUS_EFFECT);
				// 设置合同时间
				setContractDate(beforeContract, mContract, appDate);
				
				
			}
			
			
			// 设置合同信息失效，并更新合同信息
			contractService.updateContract(beforeContract);
			// 查询出原合同的合同部门
			List<ContractDept> depts = beforeContract.getContractDepartList();
			saveApproveConDept(depts,beforeContract,mContract);
		} else {
			// 如果不同意，原合同不变，新合同无效
			 mContract.setContractStatus(Constant.CONTRACT_STATUS_UNEFFECT);
		}
		// 设置新合同信息生效，设置原合同单号，保存新合同信息
		contractService.updateContract(mContract);

	}

	/**
	 * 
	 * @description 分别得到修旧合同.  
	 * @author 潘光均
	 * @version 0.1 2012-9-7
	 * @param 
	 *@date 2012-9-7
	 * @return void
	 * @update 2012-9-7 下午12:55:50
	 */
	private Map<String, Contract> getBeforeAndNewContract(List<ContractOperatorLog> logs) {
		Contract beforeContract = null;
		Contract mContract = null;
		Map<String, Contract> contracts = new HashMap<String, Contract>();
		
		if (null == logs || 2 != logs.size()) {
			throw new RuntimeException("workflow Data exception!");
		}

		Contract con1 = logs.get(0).getContract();
		Contract con2 = logs.get(1).getContract();

		if (con1 == null || con2 == null) {
			throw new RuntimeException(
					"can't find before contract and new contract !!");
		}
		//根据合同编码确定新旧合同
		if (null!=con1.getBeforeContractNum()&&con1.getBeforeContractNum().equals(con2.getContractNum())) {
			mContract = con1;
			beforeContract = con2;
		} else if (null!=con2.getBeforeContractNum()&& con2.getBeforeContractNum().equals(con1.getContractNum())) {
			mContract = con2;
			beforeContract = con1;
		}
		//改签必须能找到新旧合同
		if (mContract == null || beforeContract == null) {
			throw new RuntimeException(
					"can't find before contract and new contract !!");
		}
		if (!Constant.CONTRACT_STATUS_INPROCESS.equals(mContract
				.getContractStatus())) {
			throw new RuntimeException(
					"newContract status must be  inprocess!!");
		}
		contracts.put("bConctract", beforeContract);
		contracts.put("mContract", mContract);
		
		return contracts;
	}
    /**
     * 
    * @Title: setContractTaxDate
    * @Description: 修改和改签工作流回调 
    * 设置新合同和旧合同的税务时间（前提：新旧合同的时间已经修改）
    * @author chenaichun 
    * @param @param beforeContract
    * @param @param mContract
    * @param @param appDate   审批后新合同最新的开始时间
    * @date 2013-11-25 下午5:19:49
    * @return void    返回类型
    * @throws
    * @update 2013-11-25 下午5:19:49
     */
	private void setContractTaxDate(Contract beforeContract, Contract mContract,Date oldContractEndDate,Date newerContractBeginDate)
	{
		if(!ValidateUtil.objectIsEmpty(mContract)&&!ValidateUtil.objectIsEmpty(newerContractBeginDate)){
			//获取新合同的当前有效的那条税务信息
			ContractTax usefulContractTax = ContractUtil.changeListToContractTax(mContract);
			if(null == usefulContractTax){
				throw new ContractException(ContractExceptionType.CONTRACT_CONTRACTTAX_NULL);
			}
			//获取新合同中没有在用的那条税务信息
			ContractTax notUsingContractTax = ContractUtil.findAnotherContractTax(mContract, usefulContractTax);
			//更新当前有效的税务信息的那条
			if(usefulContractTax.getBeginTime().before(newerContractBeginDate)){
				usefulContractTax.setBeginTime(newerContractBeginDate);
				contractService.updateContractTax(usefulContractTax);
				if(notUsingContractTax != null&&notUsingContractTax.getEndTime().before(newerContractBeginDate)){
					notUsingContractTax.setEndTime(new Date(newerContractBeginDate.getTime()-1000));
					contractService.updateContractTax(notUsingContractTax);
				}
			}
		}
		if(!ValidateUtil.objectIsEmpty(beforeContract)&&!ValidateUtil.objectIsEmpty(oldContractEndDate)){
			//设置旧合同的税务时间
			//获取旧合同当前正在使用的税务信息contractTax
			ContractTax oUsingContractTax = ContractUtil.changeListToContractTax(beforeContract);
			if(null == oUsingContractTax){
				throw new ContractException(ContractExceptionType.CONTRACT_CONTRACTTAX_NULL);
			}
			//获取旧合同另外一条没有在用的税务信息anotherContracttax
			ContractTax oNotUsingContractTax = ContractUtil.findAnotherContractTax(beforeContract, oUsingContractTax);
			
			if(oldContractEndDate.before(oUsingContractTax.getEndTime())){
				oUsingContractTax.setEndTime(oldContractEndDate);
				contractService.updateContractTax(oUsingContractTax);
				if(oNotUsingContractTax != null&&oNotUsingContractTax.getBeginTime().after(oldContractEndDate)){
					contractService.deleteContractTax(oNotUsingContractTax.getId());
				}
			}
		}
		else{
			throw new ContractException(ContractExceptionType.CONTRACT_DATA_ERROR);
		}
		
		
		
	}
	/**
	 * @description 设置新合同生效时间和原合同失效时间
	 * @author patern
	 * @version 0.1 2012-8-17
	 * @param b
	 *            true or false
	 * @date 2012-8-17
	 * @return none
	 * @update 2012-8-17 上午9:16:49
	 */
	private void setContractDate(Contract beforeContract, Contract mContract,
			Date appDate) {
		Assert.notNull(beforeContract, "before contract can't null !");
		Assert.notNull(mContract, "new contract cannot null !");
		// 新合同开始生效时间
		Date mContractBeginDate = mContract.getContractBeginDate();
		// 如果新合同审批同意时间早于当前时间，则新合同的生效开始时间为当前时间
		if (mContractBeginDate.before(appDate)) {
			mContract.setContractBeginDate(appDate);
			// 新合同立即生效
			mContract.setContractStatus(Constant.CONTRACT_STATUS_EFFECT);
			// 原合同立即失效
			beforeContract.setContractStatus(Constant.CONTRACT_STATUS_UNEFFECT);
		}
		// 原合同失效时间
		Date bConctractEndDate = beforeContract.getContractendDate();
		// 原合同新失效时间
		Date bNewDate = new Date(mContract.getContractBeginDate().getTime()-1000);
		// 如果原合同的失效时间晚于新合同起始生效时间
		if (!bConctractEndDate.before(mContract.getContractBeginDate())) {
			beforeContract.setContractendDate(bNewDate);
		}
	}

	/**
	 * @description 绑定合同回调方法.
	 * @author 潘光均
	 * @version 0.1 2012-6-29
	 * @param
	 * @date 2012-6-29
	 * @return void
	 * @update 2012-6-29 上午10:18:26
	 */
	private void boundContractApprove(List<ContractOperatorLog> modifyLog,
			boolean wkstatus, Date wkTime) {
		Assert.notNull(modifyLog,"log is null !");
		Assert.isTrue(1==modifyLog.size(), "log's size is not 1 !");
		ContractDept dept = null;
		ContractOperatorLog log = null;
		// 绑定合同的工作流只有一条
			log = modifyLog.get(0);
			if (wkstatus) {
				/**
				 * @description 给待生效合同也增加绑定部门
				 * @author 潘光均
				 * @date 2012-09-27
				 */
				Contract contract = log.getContract();
				List<Contract> contracts = contractService.getWaitEffectContractByContractId(contract.getId());
				if (CollectionUtils.isNotEmpty(contracts)&&!ContractValidator.isWaitEffectContract(contract)) {
					if (1<contracts.size()) {
						throw new ContractException(ContractExceptionType.CUSTOMERHASTWOWAITEFFECTCONRACT);
					}
					ContractDept bindingDepts  = new ContractDept();
					bindingDepts.setContract(contracts.get(0));
					wkTime = wkTime != null ? wkTime : new Date();
					if (wkTime.before(contracts.get(0).getContractBeginDate())) {
						bindingDepts.setBoundTime(contracts.get(0).getContractBeginDate());
					}else{
						bindingDepts.setBoundTime(wkTime);
					}
					
					bindingDepts.setDept(false);
					bindingDepts.setRemoveTime(contracts.get(0).getContractendDate());
					bindingDepts.setContractDept(log.getChangedDept());
					bindingDepts.setState(true);
					/**
					 * @description 如果存在改绑定部门，则不保存数据
					 */
					// 保存合同部门
					if (!isBondingDept(contract, bindingDepts)) {
						contractService.createContractDept(bindingDepts);
					}
				}
				
				// 构造合同部门
			dept = new ContractDept();
			dept.setContract(contract);
			wkTime = wkTime != null ? wkTime : new Date();
			Date bondTime = contract.getContractBeginDate().after(wkTime) ? contract
					.getContractBeginDate() : wkTime;
			dept.setBoundTime(bondTime);
			dept.setDept(false);
			dept.setRemoveTime(log.getContract().getContractendDate());
			dept.setContractDept(log.getChangedDept());
			dept.setState(true);
			// 保存合同部门
			if (!isBondingDept(contract, dept)) {
				contractService.createContractDept(dept);
			}
		}
	}

	/**
	 * @description 判断绑定部门是否是合同的绑定部门.  
	 * @author 潘光均
	 * @version 0.1 2012-10-15
	 * @param contract :待校验合同
	 * bindingDepts:待校验绑定部门
	 *@date 2012-10-15
	 * @return void
	 * @update 2012-10-15 下午5:27:46
	 */
	private boolean isBondingDept(Contract contract, ContractDept bindingDepts) {
		if (null != contract && CollectionUtils.isNotEmpty(contract.getContractDepartList())
				) {
			for (int j = 0; j < contract.getContractDepartList().size(); j++) {
				ContractDept databaseDept = contract
						.getContractDepartList().get(j);
				if (databaseDept.getBoundTime().equals(bindingDepts.getBoundTime())
						&& databaseDept.getRemoveTime().equals(
								bindingDepts.getRemoveTime())
						&& databaseDept.isDept() == bindingDepts.isDept()
						&& databaseDept.getContractDept().getId().equals(bindingDepts.getId())
						&& databaseDept.isState() == bindingDepts.isState()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @description 创建合同工作流审批回调方法.
	 * @author 潘光均
	 * @version 0.1 2012-6-29
	 * @param
	 * @date 2012-6-29
	 * @return void
	 * @update 2012-6-29 上午10:10:08
	 */
	private void contractCreateApprove(List<ContractOperatorLog> logs,
			boolean wkStatus,Date wkTime) {
		// 新增的工作流只能一条
		Assert.isTrue(1 == logs.size(), "workflow infomation error ！");
		Contract modifyContract = logs.get(0).getContract();
		// 审批通过
		if (wkStatus) {
			// 更新合同信息
			if (new Date().after(modifyContract.getContractendDate())) {
				modifyContract.setContractStatus(Constant.CONTRACT_STATUS_UNEFFECT);
			}else{
				modifyContract.setContractStatus(Constant.CONTRACT_STATUS_EFFECT);
				wkTime=wkTime==null?new Date():wkTime;
				//更新合同的开始时间
				modifyContract.setContractBeginDate(wkTime.after(modifyContract
						.getContractBeginDate()) ? wkTime : modifyContract
						.getContractBeginDate());
				
				//@chenaichun  新签时更新合同税务信息的开始时间和结束时间
				// 校验 创建新合同时 ，该税务信息只有一条
				ContractValidator.checkContactOnlyOneTax(modifyContract.getContractTaxList());
				//设置税务信息的开始时间为合同更新后的时间
				modifyContract.getContractTaxList().get(0).setBeginTime(modifyContract.getContractBeginDate());
				contractService.updateContractTax(modifyContract.getContractTaxList().get(0));
				//更新合同绑定部门的绑定时间
				List<ContractDept> depts = modifyContract.getContractDepartList();
				if (CollectionUtils.isNotEmpty(depts)) {
					for (int i = 0; i < depts.size(); i++) {
						depts.get(i).setBoundTime(modifyContract.getContractBeginDate());
						contractService.updateContractDept(depts.get(i));
					}
				}
			}
			
			// 生成协议联系人
			alterMemberManager.addContactType(modifyContract.getContactId(),
					Constant.ContactType_Treaty);
			if(Constant.DEPPON_COMPANY.equals(modifyContract.getContractSubject())){
				Department department = baseDataManager.getDeptByStandardCode(Constant.ORDER_MANAGER_DEPARTMENT_STANDARDCODE);
				Message message = new Message();
				message.setTasktype(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_CUSTMER_MESSAGE);
				message.setTaskcount(1);
				message.setDeptId(Integer.parseInt(department.getId()));
				message.setIshaveinfo("客户编码"+modifyContract.getMember().getCustNumber()+"，签订了德邦物流股份有限公司合同，合同序号为"+modifyContract.getContractNum()+"，请在合同管理界面查看！");
				messageManager.addMessage(message);
			}
		    
		} else {
			// 审批不通过
			modifyContract.setContractStatus(Constant.CONTRACT_STATUS_UNEFFECT);
		}
		contractService.updateContract(modifyContract);
	}

	/**
	 * 
	 * <p>
	 * Description:查询合同操作日志<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param id
	 * @return
	 *
	 */
	@Override
	public List<ContractOperatorLog> getContractOperatorLogByContractId(
			String id) {
		return contractService.getContractOperatorLogByContractId(id);
	}

	/**
	 * 
	 * @description 校验合同是否有工作流在审批，如果存在则不能进行任何操作.
	 * @author 潘光均
	 * @version 0.1 2012-7-4
	 * @param
	 * @date 2012-7-4
	 * @return boolean
	 * @update 2012-7-4 上午10:04:00
	 */
	@Override
	public boolean isContractCanOperate(String contractId, String handleType) {
		boolean result = false;
		Contract contract = contractService.getAllContractInfoById(contractId);
		if (ContractValidator.isWaitEffectContract(contract)) {
			result= ContractValidator.isWaitEffectCanOperate(contract,
					handleType);
		} else {
			result= ContractValidator.canOperate(contract, handleType);
		}
		/**
		 * 如果是新增，改签或者修改，如果客户存在审批中的合同，则不然操作
		 * 如果是新增或改签，则客户有待生效合同，也不能进行操作
		 */
		if (ContractHandleType.INSERT.getHandleType().equals(handleType)
				|| ContractHandleType.CHANGESIGN.getHandleType().equals(
						handleType)
				|| ContractHandleType.UPDATE.getHandleType().equals(handleType)) {
			if (null != contract && null != contract.getMember()) {
				List<Contract> contracts = contractService
						.getContractsByMemberId(contract.getMember().getId());
					ContractValidator.checkCustHasInproContract(contracts);
					ContractValidator.checkCustHasWaitEffectCont(handleType, contracts);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @description 作废过期合同.
	 * @author 潘光均
	 * @version 0.1 2012-7-10
	 * @param
	 * @date 2012-7-10
	 * @return void
	 * @update 2012-7-10 下午2:56:03
	 */
	@Override
	public void cancelTimeOutContract() {
		CustomerInfoLog.init();
		String status = "2";
		Date tempDate = new Date();
		contractService.cancelTimeOutContract(status, tempDate);
		CustomerInfoLog.commit();
	}

	/**
	 * 
	 * @description 生效待生效合同.
	 * @author 潘光均
	 * @version 0.1 2012-7-10
	 * @param
	 * @date 2012-7-10
	 * @return void
	 * @update 2012-7-10 下午2:56:03
	 */
	@Override
	public void effectWaitEffectContract() {
		CustomerInfoLog.init();
		String status = "1";
		Date tempDate = new Date();
		contractService.effectContract(status, tempDate);
		CustomerInfoLog.commit();
	}
	/**
	 * 
	 * @description 获取客户存在的有效合同.
	 * @author 潘光均
	 * @version 0.1 2012-7-10
	 * @param
	 * @date 2012-7-10
	 * @return void
	 * @update 2012-7-10 下午2:56:03
	 */
	@Override
	public Contract getNewContract(String memberId)  {
		
		Assert.notNull(memberId, "memberId must not be null");
		// 本部门客户验证
		Member member = alterMemberManager.getMemberById(memberId);
		if (member == null) {
			throw new ContractException(ContractExceptionType.Data_Error);
		}
		String deptName = repeatedCustManager.searchMainCustDeptName(member.getCustNumber());
		if (!StringUtils.isEmpty(deptName)) {
			throw new CustomerException(CustomerExceptionType.MemberInRepeats, new Object[]{deptName});
		}
		/**
		 * @description增加快递经理对应营业部的权限
		 * @author pgj
		 * @date 2013-09-20
		 */
		List<Department> departs = baseDataManager
				.getAuthorityBusinessDept(ContextUtil
						.getCurrentUserDept().getStandardCode());
		List<String> deptIds = ExpressAuthDeptUtil.getAuthDept(
				departs);
		if (!ContextUtil.getCurrentUserDeptId().equals(member.getDeptId())&&
				!deptIds.contains(member.getDeptId())) {
			throw new ContractException(ContractExceptionType.Not_Member_Dept);
		}
		// 得到这个客户的全部合同
		List<Contract> contracts = contractService.queryContractWithDeptById(memberId);
		//零担近三个月发货情况
		String liverArrearaMount = contractService.getArrearaMountByCustId(memberId);
		//快递近三个月发货情况
		String exLiverArrAmount = contractService.getExArrearaMountByCustId(memberId);
		if (null == contracts || 0 >= contracts.size()) {
			// 构建合同返回对象，提供给前台使用
			Contract reContract = new Contract();
			reContract.setDept(ContextUtil.getCurrentUserDept());
			reContract.setArrAmount(liverArrearaMount);
			reContract.setExArrAmount(exLiverArrAmount);
			reContract.setMember(member);
			if (null==reContract.getExPreferential()) {
				reContract.setExPreferential(new Preferential());
			}
			return reContract;
		}else{
			// 验证这个合同是否可以做新增操作,不通过抛出异常
			ContractValidator.validateCreateContract(contracts,ContractHandleType.CHANGESIGN.getHandleType());
			//构建合同返回对象，提供给前台使用
			Contract newContract = getCurrentContract(contracts);
			newContract.setMember(member);
			// 生成近3个月的发货情况
			newContract.setArrAmount(liverArrearaMount);
			//快递近三个月发货情况
			newContract.setExArrAmount(exLiverArrAmount);
			if (null==newContract.getExPreferential()) {
				newContract.setExPreferential(new Preferential());
			}
			return newContract;
		}
	}
	/**
	 * 
	 * <p>
	 * Description:获得当前有效合同<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param contracts
	 * @return
	 * Contract
	 */
	private Contract getCurrentContract(List<Contract> contracts) {
		Date now = new Date();
		for (Contract contract : contracts) {
			if(Constant.CONTRACT_STATUS_EFFECT.equals(contract.getContractStatus())
					&& now.before(contract.getContractendDate())
					&& now.after(contract.getContractBeginDate())
					){
				return contract;
			}
		}
		return new Contract();
	}
	/**
	 * 
	 * <p>
	 * Description:创建新合同<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param contract
	 * @param files
	 * @param contractType
	 * @return
	 *
	 */
	@Override
	public Map<String,String> createNewContract(Contract contract, List<FileInfo> files,String contractType) {
		Assert.notNull(contract,"contract must be not null");
		Assert.notNull(contract.getMember(),"member must be not null");
		Assert.notNull(contract.getMember().getId(),"memberId must be not null");
		//校检快递经理只签零担合同信息
		if (ExpressAuthDeptUtil.isPointManager(ContextUtil.getCurrentUserRoleIds())) {
			ContractValidator.checkPointManagerSignLtt(contract);
		}
		//验证合同是否能做新签操作
		String memberId = contract.getMember().getId();
		//本部门客户验证
		Member member = alterMemberManager.getMemberById(memberId);
		if(member == null){
			throw new ContractException(ContractExceptionType.Data_Error);
		}
		//(校验规则变化 --如果是快递点部 则校验是否有对应关系)
		List<Department> departs = baseDataManager
				.getAuthorityBusinessDept(ContextUtil
						.getCurrentUserDept().getStandardCode());
		if (!ContextUtil.getCurrentUserDeptId().equals(member.getDeptId())
				&& !ExpressAuthDeptUtil.getAuthDept(
						departs)
						.contains(member.getDeptId())) {
			throw new ContractException(ContractExceptionType.Not_Member_Dept);
		}
		List<Contract> contracts = contractService.getContractsByMemberId(memberId);

		if(null==contracts||contracts.size() == 0){
			//新增合同
			return createContract(contract,files,contractType);
		}else{
			ContractValidator.validateCreateContract(contracts,
					ContractHandleType.CHANGESIGN.getHandleType());
            //若合同有效 检测合同的日期
	        Contract result = ContractValidator.checkAllowCreateContract_Date(contracts,contract.getContractBeginDate(),contract.getContractendDate());
	        if(null!=result){
	        	 throw new ContractException(ContractExceptionType.CREATE_DATA_NOT_EQUALS_ENDDATE);
	        }
			//改签合同
			contract.setFileInfoList(files);
			return changeContract(contract,contractType);
		}
	}
	/**
	 * 
	 * <p>
	 * Description:创建合同<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param contractId
	 * @param workflowId
	 * @return
	 *
	 */
	@Override
	public ContractExaminView createContractAddExaminView(String contractId,
			long workflowId) {
		ContractExaminView view = new ContractExaminView();

		List<ExamineRecord> examineRecordList = examineRecordService
				.getExamineRecordByWorkflowId(workflowId);
		Contract contract = contractService.getAllContractInfoById(contractId);
		view.setContractView(ContractUtil.createContractView(contract));
		view.setExamineRecordList(examineRecordList);
		view.setCurrentExaminer(examineRecordService
				.getCurrentPeople(workflowId));
		return view;
	}

	/**
	 * 
	 * <p>
	 * Description:税务信息是否更新<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param contract
	 * @param databaseCon
	 * @return
	 * boolean
	 */
	public boolean isContractTaxChange(Contract contract,Contract databaseCon){
		return ContractValidator.isContractTaxChange(contract, databaseCon);
	}
	/**
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.customer.server.manager.IContractManager#updateContract
	 * (com.deppon.crm.module.customer.shared.domain.Contract)
	 * 修改合同
	 *@see com.deppon.crm.module.customer.server.manager.UpdateContractTest
	 */
	@Override
	public Map<String,String> updateContract(Contract contract,String contractType) {
		// 数据库原合同信息
		Contract databaseCon = contractService.getAllContractInfoById(contract.getId());
		String databaseConId = databaseCon.getId();
		String beforeContractNum=databaseCon.getContractNum();
		//校验合同业务逻辑校验
		ContractValidator.validataContractUpdate(contract, databaseCon);
		if (Constant.CONTRACT_STATUS_UNEFFECT.equals(contract.getContractStatus())) {
			List<Contract> contracts = contractService.searchContractAllInfoByCustId(databaseCon.getMember().getId());
			if (CollectionUtils.isEmpty(contracts)) {
				throw new ContractException(ContractExceptionType.ContractDataError);
			}
			for (int i = 0; i < contracts.size(); i++) {
				if (!ContractValidator.isWaitEffectContract(contracts.get(i))
						&& Constant.CONTRACT_STATUS_EFFECT.equals(contracts
								.get(i).getContractStatus())) {
					databaseCon=contracts.get(i);
				}
			}
		}
		// 计算结算限额是否满足
		boolean isStatified = checkAmountByCustId(contract,databaseCon.getMember()
				.getId(), contract.getArrearaMount());
		// 工作流號
		String workflowId = null;
		// 获得logId
		String logId = contractService.getConOperaLogSequence();
		// 完善合同操作附件
		produceFileInfos(contract.getFileInfoList(), logId,
				Constant.FileSourceType_ContractOperatorLog);
		contract.setOldContract(databaseCon);
		// 获得合同id
		String contractId = contractService.getContractId();
		// 获取合同编码
		String contractNum = getContractNum(contractId);
		contract.setContractNum(contractNum);
//		
//		/**
//		 * 判断税务信息是否修改 传到前台做校验
//		 */
//		boolean updFlag = ContractValidator.isContractTaxChange(contract, databaseCon);
		/**合同修改 @chenaichun
		 * 校验发票标记是否需要走工作流 (true为修改)
		 */
		boolean flag = isUpdContractTaxNeedOAWorkFlow(contract,databaseCon);
		/**
		 * 判断发票标记是否修改 传到前台做校验
		 */
		// 生成OA工作流 isStatified
		Map<String,String> map = new HashMap<String,String>();
		map.put("isContractTaxUpd", String.valueOf(flag));
		// 判断是否需要OA工作流
		if (!ContractUtil.isNewNotNeedNotOAWorkFlow(contract)||flag) {
			/**
			 * @description OA中的超系统额度是true，表示超了
			 * 而我们ture表示满足结算额度
			 */
			map.put("isStatified", String.valueOf(isStatified));
			contract.setContractStatus(Constant.CONTRACT_STATUS_INPROCESS);
		}else{
			Date newerContractBeginDate = new Date();
			//设置旧合同的税务时间
			setContractTaxDate(databaseCon, null,new Date(newerContractBeginDate.getTime() - 1000),null);
			contract.setContractStatus(Constant.CONTRACT_STATUS_EFFECT);
			contract.setContractBeginDate(newerContractBeginDate);
			databaseCon.setContractendDate(new Date(newerContractBeginDate.getTime() - 1000));
			databaseCon.setContractStatus(Constant.CONTRACT_STATUS_UNEFFECT);
			// 设置新合同状态和生效时间
			// 查询出原合同的合同部门
			List<ContractDept> depts = databaseCon.getContractDepartList();//ConcontractService
			contractService.updateContract(databaseCon);
			contract.setId(contractId);
			// 设置合同部门的合同为修改和的合同
			saveApproveConDept(depts,databaseCon,contract);
			
		}
		String oldLogId = contractService.getConOperaLogSequence();
		/**
		 * 为了记录修改合同旧合同的id
		 */
		databaseCon.setId(databaseConId);
		// 原合同产生工作流
		ContractOperatorLog oldContractLog = produceOpeLogs(databaseCon,
				oldLogId, workflowId, ContractHandleType.UPDATE.getHandleType(),contract.getContractStatus(),new Date())
				.get(0);
		//若合同状态时优先，这表示没有走工作流，这直接设置工作流审批状态为空
		if (null != oldContractLog
				&& Constant.CONTRACT_STATUS_EFFECT.equals(contract
						.getContractStatus())) {
			oldContractLog.setApprovalState(null);
		}
		// 保存原合同工作流
		//配合工作流迁移，合同操作日志不通过反写
//		contractService.createContractOpeLog(oldContractLog);
		// 产生新合同，新合同id
		contract.setId(contractId);
		// 产生操作日志
		List<ContractOperatorLog> logs=produceOpeLogs(contract, logId,
				workflowId, ContractHandleType.UPDATE.getHandleType(),contract.getContractStatus(),new Date(System.currentTimeMillis()+1000));
		logs.add(oldContractLog);
				contract.setContractWorkflowList(logs); 
		// 优惠信息
		if (null != contract.getPreferential()) {
			contract.getPreferential().setContractId(contractId);
		}
		/**
		 * 合同修改
		 * 添加税务标记 @chenaichun
		 * 插入两条税务标记信息
		 */
		//新合同contract和旧合同databaseCon
		contract.setContractTaxList(produceTwoContractTax(databaseCon,contract));
		
		
		// 如果从ERP迁移的数据，可能没有合同部门，则根据合同的所属部门，创建合同部门
		if (!cheakContractHasBelongDept(databaseCon)) {
			List<ContractDept> depts = produceContractDept(databaseCon, true);
			contract.setContractDepartList(depts);
		}
		contract.setBeforeContractNum(beforeContractNum);
		/**
		 * 合同是否有快递信息，决定工作流流向
		 */
		ContractApplyType type = contract.hasExpressInfo() ? ContractApplyType.EX_UPDATE
				: ContractApplyType.UPDATE;
		//设置价格版本
		setContractPriceVersionDate(contract, databaseCon,ContractApplyType.UPDATE);
		workflowId = saveNewOrUpdateContract(contract,
				type, map,
				contract.getContractWorkflowList(), logs, contractType);
		//返回到前台的map
		Map<String,String> jsMap = new HashMap<String,String>();
		jsMap.put("workflowId", workflowId);
		jsMap.put("updFlag", String.valueOf(flag));
		return jsMap;
	}
	
	/**
	 * @description 根据时间和id得到合同编码.
	 * @author 潘光均
	 * @version 0.1 2012-9-3
	 * @param
	 * @date 2012-9-3
	 * @return StringBuffer
	 * @update 2012-9-3 下午4:22:29
	 */
	private String getContractNum(String contractId) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String str = format.format(date);
		StringBuffer contractNum = new StringBuffer();
		contractNum.append(str);
		if (contractId.length() > 4) {
			contractNum.append(contractId.substring(contractId.length() - 4,
					contractId.length()));
		} else {
			contractNum.append(StringUtils.leftPad(contractId, 4, '0'));
		}
		return contractNum.toString();
	}

	/**
	 * 
	 * @description 校验结算额度. true:结款额度小于最大发货量的2倍，反之则为false -------ture
	 *              ,不用提示，false提示
	 * @author 潘光均
	 * @version 0.1 2012-9-3
	 * @param
	 * @date 2012-9-3
	 * @return void
	 * @update 2012-9-3 下午2:17:57
	 */
	@Override
	public Boolean checkAmountByCustId(Contract contract, String custId,
			Double amount) {
		List<Double> moneys = contractService.getLatelyDeliverMoney(custId, 3);
		double maxMoney = 0d;

		if (null != moneys && 0 < moneys.size()) {
			maxMoney = ContractUtil.getMaxAmount(moneys);
		}
		List<Double> exMoneys = contractService.getExLatelyDeliverMoney(custId,
				3);
		double exMaxMoney = 0;
		if (null != exMoneys && 0 < exMoneys.size()) {
			exMaxMoney = ContractUtil.getMaxAmount(exMoneys);
		}
		if (Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract.getPayWay())
				&& !Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract
						.getExPayWay())) {
			// 最近两个月连续发货金额其中有小于3000元的，条件返回false
			return 2 * maxMoney >= amount;
		} else if (!Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract
				.getPayWay())
				&& Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract
						.getExPayWay())) {
			return 2 * exMaxMoney >= amount;
		} else if (Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract
				.getPayWay())
				&& Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract
						.getExPayWay())) {
			return 2 * (exMaxMoney + maxMoney) >= amount;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * <p>
	 * Description:删除时效合同<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param contractId
	 *
	 */
	@Override
	public void deleteUneffectContract(String contractId) {
		Assert.notNull(contractId, "cancel contract id con't null !");
		// 取出合同信息
		Contract contract = contractService.getAllContractInfoById(contractId);
		if (null == contract) {
			throw new ContractException(ContractExceptionType.ContractIsNull);
		}
		// 校验是否可以删除
		ContractValidator.canDelete(contract);
		// 删除无效合同
		contractService.deleteUneffectContractById(contractId);
		// 删除工作流信息
	}
	/**
	 * 
	 * @description 批量删除无效合同.  
	 * @author 潘光均
	 * @version 0.1 2012-9-10
	 * @param 
	 *@date 2012-9-10
	 * @return void
	 * @update 2012-9-10 下午2:38:49
	 */
	@Override
	public void batchDeleteUneffectContract(List<String> contractIds){
		Assert.notNull(contractIds,"delete id can not null !");
		Assert.isTrue(0<contractIds.size(),"has not delete one !");
		for (String contractId : contractIds) {
			// 取出合同信息
			Contract contract = contractService.getAllContractInfoById(contractId);
			if (null == contract) {
				throw new ContractException(ContractExceptionType.ContractIsNull);
			}
			// 校验是否可以删除
			ContractValidator.canDelete(contract);
		}
		contractService.batchDeleteUneffectContract(contractIds);
	}
	/**
	 * @description 判断合同是有合同归属部门.
	 * @author patern
	 * @version 0.1 2012-8-14
	 * @param Contract
	 * @date 2012-8-14
	 * @return boolean
	 * @update 2012-8-14 下午3:50:21
	 */
	private boolean cheakContractHasBelongDept(Contract contract) {
		Assert.notNull(contract, "contract mustn't null !");
		if (null == contract.getContractDepartList()
				|| 0 >= contract.getContractDepartList().size()) {
			return false;
		}
		for (ContractDept dept : contract.getContractDepartList()) {
			if (dept.isDept()) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @description 月结限额校验.  
	 * @author 潘光均
	 * @version 0.1 2012-9-13
	 * @param 
	 *@date 2012-9-13
	 * @return boolean
	 * @update 2012-9-13 下午3:35:26
	 */
	@SuppressWarnings("unused")
	private boolean checkArrearaMount(Contract contract, String custId) {
		//零担月结额度校验
		if (Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract.getPayWay())) {
			Double arrearaMount = contract.getArrearaMount();
			String arreara = contractService.getArrearaMountByCustId(custId);
			return checkMoney(arreara, arrearaMount);
		}
		//快递月结额度校验
		if (Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract.getExPayWay())) {
			Double exArrearaMount = contract.getArrearaMount();
			String exArreara = contractService.getArrearaMountByCustId(custId);
			return checkMoney(exArreara, exArrearaMount);
		}
		return false;
	}
	/**
	 * 
	 * <p>
	 * Description:校验金额是否满足<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param arreara
	 * @param arrearaMount
	 * @return
	 * boolean
	 */
	private boolean checkMoney(String arreara, Double arrearaMount) {
		String[] moneys = arreara.replace('\\', ',').split(",");
		if (moneys.length==0) {
			return false;
		}
		//最近两个月连续发货金额其中有小于3000元的，条件返回false
		if (Double.valueOf(moneys[1]) < 3000 || Double.valueOf(moneys[2]) < 3000) {
			return false;
		//arrearaMount 小于等于 最近发货三个月发货金额的最大的两倍
		}
		Double max = 0d;

		for (String money : moneys) {
			if(max < Double.valueOf(money)) 
				max = Double.valueOf(money) ;
		}
		return arrearaMount<=2*max;
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.manager.IContractManager#searchUneffectContractIn30Days(int)
	 */
	@Override
	public List<Contract> searchUneffectContractIn30Days() {
		return contractService.searchUneffectContractIn30Days(30);
	}
	/**
	 * 
	 * <p>
	 * Description:查询所有待生效合同<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param date
	 * @return
	 *
	 */
	@Override
	public List<Contract> searchAllWaitEffectContractByTime(Date date){
		return contractService.searchAllWaitEffectContractByTime(date);
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.manager.IContractManager#updateContractDebtDaysById(string,int)
	 */
	@Override
	public void updateContractDebtDaysById(String contractId, int debtDays) {
		// 参数不能为空
		if (ValidateUtil.objectIsEmpty(contractId)
				|| ValidateUtil.objectIsEmpty(debtDays) || debtDays <= 0) {
			throw new ContractException(
					ContractExceptionType.CONTRACT_DATA_ERROR);
		}
		Contract contract = contractService.getAllContractInfoById(contractId);
		if (ValidateUtil.objectIsEmpty(contract)) {
			throw new ContractException(ContractExceptionType.CONTRACT_DATA_ERROR);
		}
		// 检验是否能修改合同月结天数
		ContractValidator.checkContractCanOperateDebtDays(contract);

		Contract newContract = new Contract();
		newContract.setId(contract.getId());
		newContract.setDebtDays(debtDays);
		newContract.setUseableArrearAmount(contract.getUseableArrearAmount());
		newContract.setArrearaMount(contract.getArrearaMount());
		// 生效合同
		if (!ContractValidator.isWaitEffectContract(contract)) {
			String custNum = contract.getMember().getCustNumber();
			// 生效合同 首先判断 是否发过货物 是否产生了最早发货账龄日期
			ContractDebtDay contractDebtDay = contractService.getContractDebtByCustNum(custNum);
			// 当中间表合同欠款天数里面没有值时 说明没有发过货 则直接修改  不需要同步
			if (ValidateUtil.objectIsEmpty(contractDebtDay)
					|| ValidateUtil.objectIsEmpty(contractDebtDay.getLongestDebtDate())) {
				contractService.updateContractDebtDayById(newContract);
			} else {
				this.updateEffectContractDebtDay(contractDebtDay, newContract, debtDays);
			}
		} else {
			// 待生效合同 也直接修改 不需要同步
			contractService.updateContractDebtDayById(newContract);
		}

	}
	/**
	 * 
	 * <p>
	 * Description:查询合同月结天数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @return
	 *
	 */
	@Override
	public List<ContractMonthendDay> searchAllContractDebtDays() {
		return contractService.searchCommonContractMonthendDay(Constant.EFFECT);
	}
	/**
	 * 
	 * <p>
	 * Description:通用权限。通过合同月结天数名字  查询合同月结天数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param contractDebtDaysName
	 * @return
	 *
	 */
	@Override
	public ContractMonthendDay searchContractDebtDayByName(String contractDebtDaysName) {
		if (ValidateUtil.objectIsEmpty(contractDebtDaysName)) {
			throw new ContractException(ContractExceptionType.CONTRACT_DATA_ERROR);
		}
		return contractService.searchContractMonthendDayByName(contractDebtDaysName);
	}
	/**
	 * 
	 * <p>
	 * Description:提供给定时器
	 * 特殊权限修改后 特殊权限 除了合同为无效 的全部都要修改。
	 * 通用权限修改 此处需要判断是否可以将月结天数变小，
	 * 因为变小后 有效合同的欠款天数 > 合同月结天数 有效合同的可使用额度可能会清零<<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 *
	 */
	@Override
	public void updateCommonContractDebtDay() {
		List<ContractMonthendDay> conDays = contractService.searchCommonContractMonthendDay(Constant.UNEFFECT);
		if (conDays ==null || conDays.size() <=0) {
			return ;
		}else if (conDays !=null && conDays.size() == 1) {
			int debtDay = 0;
			int oldDebtDay = 0;
			oldDebtDay = conDays.get(0).getDefaultDebtDays();
			conDays = contractService.searchCommonContractMonthendDay(Constant.EFFECT);
			debtDay = conDays.get(0).getDefaultDebtDays();
			
			//查询有业务操作的 同类型合同的月结天数 除了无效的都去修改
			List<Contract> contracts = contractService.getSameTypeContractByDebtDay(oldDebtDay);
			if (CollectionUtils.isNotEmpty(contracts)) {
				//客户编码
				String custNum = null; 
				//合同欠款天数
				ContractDebtDay contractDebtDay = null;
				for (Contract contract : contracts) {
					custNum = contract.getMember().getCustNumber();
					// 去合同欠款天数 合同是否欠款
					contractDebtDay = contractService.getContractDebtByCustNum(custNum);
					this.updateEffectContractDebtDay(contractDebtDay,contract,debtDay);
					CustomerInfoLog.commit();
				}			
			}
			// 没有发货 或者是待生效的合同 直接修改。不需要同步
			contractService.updateOperationContractMonthDay(debtDay,oldDebtDay);
			//删除无效的
			contractService.deleteCommonContractDebtDay(Constant.UNEFFECT);
		}else {
			return ;
		}
	}
	/**
	 * 
	 * <p>
	 * Description:根据月结天数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param id
	 * @param debtDay
	 *
	 */
	@Override
	public void updateCommonContractMonthEndDayById(String id,int debtDay) {
		//参数进行检查
		if (ValidateUtil.objectIsEmpty(id) || ValidateUtil.objectIsEmpty(debtDay)) {
			throw new ContractException(ContractExceptionType.CONTRACT_DATA_ERROR);
		}
		
		ContractMonthendDay contractMonthendDay = contractService.getContractMonthendDayById(id);
		if (ValidateUtil.objectIsEmpty(contractMonthendDay)) {
			throw new ContractException(ContractExceptionType.CONTRACT_DATA_ERROR);
		}
		//先删除无效的
		contractService.deleteCommonContractDebtDay(Constant.UNEFFECT);
		contractMonthendDay.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		contractMonthendDay.setStatus(Constant.UNEFFECT);
		//先作废 通用管理的合同月结天数
		contractService.updateContractMonthEnd(contractMonthendDay);
		
		//新增一条有效的
		contractMonthendDay = new ContractMonthendDay();
		contractMonthendDay.setContractDebtDayName(Constant.CONTRACTDEBT_DAY);
		contractMonthendDay.setDefaultDebtDays(debtDay);
		contractMonthendDay.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		contractMonthendDay.setStatus(Constant.EFFECT);
		contractMonthendDay.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		contractMonthendDay.setModifyDate(new Date());
		contractService.saveCommonContractDebtDay(contractMonthendDay);
	}
	/**
	 * 
	 * <p>
	 * Description:检验当前时间是否在晚上八点到凌晨的八点<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 *
	 */
	@Override
	public void checkCurrentTime() {
		// 检验当前时间是否在晚上八点到凌晨的八点
		if (!ContractValidator.checkCurrentTime()) {
			throw new ContractException(
					ContractExceptionType.HANDLE_CONTRACTDEBTDAYS_TIMEERROR);
		}
	}
	
	/**
	 * <p>
	 * Description:有效合同 或者 审批中合同的 修改 合同月结天数 和合同欠款天数<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-10
	 * void
	 */
	private  void updateEffectContractDebtDay(ContractDebtDay contractDebtDay,Contract contract, int debtDay){
		// 说明已经还款
		if (Constant.USERAMOUNT_DEFAULT == contractDebtDay.getUsedAmount()) {
			//合同欠款天数
			contractDebtDay.setRemainDays(0);
			contract.setUseableArrearAmount(contract.getArrearaMount());
		} else {
			// 得到当前时间 至发货日期的还款时间的差值
			long day = ContractValidator.returnDebtDayDifference(contractDebtDay.getLongestDebtDate());
			// 如果欠款天数 大于 合同月结天数 则合同的可使用额度变为0
			if (ContractValidator.checkDebtDays(day, debtDay)) {
				contract.setUseableArrearAmount(0d);
				// 修改中间表的剩余欠款天数为0
				contractDebtDay.setRemainDays(0);
			} else {
				contract.setUseableArrearAmount(contract.getArrearaMount());
				// 欠款天数 小于 合同月结天数 修改剩余天数的值 以确定定时器正常维护剩余天数字段
				contractDebtDay.setRemainDays((int) (debtDay - day));
			}
		}
		//合同月结天数
		contract.setDebtDays(debtDay);
		// 修改合同本身的月结天数
		contractService.updateContractBaseInfo(contract);
		// 设置合同欠款天数最后修改人
		contractDebtDay.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		// 修改合同欠款天数
		contractService.updateContractDebtDay(contractDebtDay);
	}
	/**
	 * 
	 * <p>
	 * Description:月结天数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 *
	 */
	@Override
	public void contractDebtDaysManager() {
		// 找到剩余欠款天数为一天的
		List<ContractDebtDay> list = contractService.getContractDebtsForDay(Constant.CONTRACTDEBTDAY_FOR_ONE);
		String custNum = null;
		List<Contract> contracts = null;
		// 将剩余一天的剩余欠款天数 变味0 可使用额度变为0 此时需要同步合同
		if (!ValidateUtil.objectIsEmpty(list)) {
			for (ContractDebtDay debtDay : list) {
				debtDay.setRemainDays(0);
				debtDay.setModifyUser(Constant.SYSTEM_ID);
				// 修改中间表
				contractService.updateContractDebtDay(debtDay);
				// 客户编码
				custNum = debtDay.getCustNum();
				if (!StringUtils.isEmpty(custNum)) {
					contracts = contractService.getContractsBasicInfoByCustNum(custNum);
					if (ValidateUtil.objectIsEmpty(contracts)) {
						continue;
					} else {
						for (Contract contract : contracts) {
							// 无效的。和不是月结的不用修改
							
							if (!(Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract.getPayWay())
									&&Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract.getExPayWay()))) {
								continue;
							}else if (Constant.CONTRACT_STATUS_UNEFFECT.equals(contract.getContractStatus())) {
								continue;
							}else if (Constant.CONTRACT_STATUS_EFFECT.equals(contract.getContractStatus()) &&
									contract.getContractBeginDate().after(new Date())) {
								continue;
							}
							contract.setUseableArrearAmount(0d);
							//此时需要同步合同
							contractService.updateContractBaseInfo(contract);
							CustomerInfoLog.commit();
						}
					}
				}else {
					continue;
				}
			}
		}
		// 将所有中间表所有 剩余天数 减 -1
		contractService.updateAllContractDebt(0);
	}
	/**
	 * 
	 * <p>
	 * Description:保存合同月结天数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param contractDebtDay
	 *
	 */
	@Override
	public void saveContractDebt(ContractDebtDay contractDebtDay) {
		// 判空操作
		ContractExceptionType reason = null;
		if (ValidateUtil.objectIsEmpty(contractDebtDay)) {
			reason = ContractExceptionType.CONTRACT_DATA_ERROR;
		} else if (ValidateUtil.objectIsEmpty(contractDebtDay.getCustNum())) {
			reason = ContractExceptionType.CONTRACT_DATA_ERROR;
		}
		if (!ValidateUtil.objectIsEmpty(reason)) {
			throw new ContractException(reason);
		}
		// 客户所有合同
		List<Contract> contracts = contractService.getContractsBasicInfoByCustNum(contractDebtDay.getCustNum());
		// 剩余可欠款天数
		int debtDay = 0;
		Contract contract = new Contract();
		for (Contract contractInfo : contracts) {
			// 月结有效合同
			if (Constant.CONTRACT_STATUS_EFFECT.equals(contractInfo.getContractStatus())
					&& (Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contractInfo.getPayWay())
							||Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contractInfo.getExPayWay()))
					&& contractInfo.getContractBeginDate().before(new Date())) {
				debtDay = contractInfo.getDebtDays();
				contract = contractInfo;
				break;
			}
		}
		if (StringUtils.isEmpty(contract.getId())) {
			log.error("this Customer has no effect Contract(Month_End)");
			return ;
		}
		// 如果没有有效的月结合同信息，则取默认的月结天数
		if (debtDay == 0) {
			ContractMonthendDay contractMonthendDay = contractService.searchContractMonthendDayByName(Constant.CONTRACTDEBT_DAY);
			debtDay = contractMonthendDay != null ? contractMonthendDay.getDefaultDebtDays() : 0;
		}
		// 说明已经还款
		if (Constant.USERAMOUNT_DEFAULT == contractDebtDay.getUsedAmount()) {
			// 合同欠款天数
			contractDebtDay.setRemainDays(0);
			if (!ValidateUtil.objectIsEmpty(contract.getId())) {
				contract.setUseableArrearAmount(contract.getArrearaMount());
				contractService.updateContractBaseInfo(contract);
				CustomerInfoLog.commit();
			}
			
		} else {
			// 得到当前时间 至发货日期的还款时间的差值
			long day = ContractValidator.returnDebtDayDifference(contractDebtDay.getLongestDebtDate());
			// 如果欠款天数 大于 合同月结天数 则合同的可使用额度变为0
			if (ContractValidator.checkDebtDays(day, debtDay)) {
				if (!ValidateUtil.objectIsEmpty(contract.getId()) && contract.getUseableArrearAmount()!= 0) {
					contract.setUseableArrearAmount(0d);
					contractService.updateContract(contract);
					CustomerInfoLog.commit();
				}
			} else {
				// 欠款天数 小于 合同月结天数 修改剩余天数的值 以确定定时器正常维护剩余天数字段 
				contractDebtDay.setRemainDays((int) (debtDay - day));
				// 欠款天数 小于 合同月结天数 恢复合同可使用额度为 月结欠款额度 
				contract.setUseableArrearAmount(contract.getArrearaMount());
				contractService.updateContract(contract);
				CustomerInfoLog.commit();
			}
		}

		// 中间表合同欠款天数
		ContractDebtDay newContractDebtDay = contractService.getContractDebtByCustNum(contractDebtDay.getCustNum());
		if (ValidateUtil.objectIsEmpty(newContractDebtDay)) {
			// 插入操作
			contractService.saveContractDebt(contractDebtDay);
		} else {
			contractDebtDay.setModifyUser(Constant.SYSTEM_ID);
			contractDebtDay.setId(newContractDebtDay.getId());
			// 修改操作
			contractService.updateContractDebtDay(contractDebtDay);
		}
		//将中间表的剩余天数减去1 和为1的将剩余天数变为0
//		this.contractDebtDaysManager();
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.manager.IContractManager#updateMonthSendRate()
	 */
	@Override
	public void updateMonthSendRate() {
		//查询条件
		ContractCondition condition = new ContractCondition();
		condition.setContractStatus(Constant.CONTRACT_STATUS_UNEFFECT);
		condition.setPrefrentialType(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND);
		condition.setExPrefrentialType(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE);
		//起始条数
		int start =0;
		//每次查询数量
		int limit =500;
		
		//零担折扣信息
		PrefrentialDeal deal = this.getEffectPrefrentialDeal(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND);
		
		//快递折扣信息
		PrefrentialDeal exDeal = this.getEffectPrefrentialDeal(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE);
		
		//查询有效的月发月送合同
		List<ContractDto> contracts = contractService.queryContractWithMoney(condition,start,limit);
		while (CollectionUtils.isNotEmpty(contracts)){
			for (int i = 0; i < contracts.size(); i++) {
				if (Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(contracts.get(i).getPreferentialType())) {
					//获取客户应该享受的折扣
					double rate = this.getMonthSendRate(deal,contracts.get(i).getMonthSums());
					Preferential preferential = contracts.get(i).getPreferential();
					Preferential updatePreferential = new Preferential();
					updatePreferential.setChargeRebate(rate);
					
					//更新折扣信息
					if (null==preferential) {
						geneNewPreferential(contracts.get(i), updatePreferential,
								Constant.CONTRACT_TYPE_LINGDAN);
						createPreferentialOtherRate(contracts.get(i),updatePreferential);
						contractService.createPreferential(updatePreferential);
					}else{
						updatePreferential.setId(preferential.getId());
						geneNewPreferential(contracts.get(i), updatePreferential,
								Constant.CONTRACT_TYPE_LINGDAN);
						contractService.updatePreferential(preferential,updatePreferential);
					}
				}
				
				if (Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE.equals(contracts.get(i).getExPreferentialType())) {
					//快递折扣
					double exRate = this.getMonthSendRate(exDeal, contracts.get(i).getExMonthSums());
					Preferential exPreferential = contracts.get(i).getExPreferential();
					Preferential updateExPreferential = new Preferential();
					updateExPreferential.setChargeRebate(exRate);
					//更新快递折扣
					if (null == exPreferential) {
						geneNewPreferential(contracts.get(i), updateExPreferential,
								Constant.CONTRACT_TYPE_EXPRESS);
						createPreferentialOtherRate(contracts.get(i),updateExPreferential);
						contractService.createPreferential(updateExPreferential);
					}else{
						updateExPreferential.setId(exPreferential.getId());
						geneNewPreferential(contracts.get(i), updateExPreferential,
								Constant.CONTRACT_TYPE_EXPRESS);
						contractService.updatePreferential(exPreferential,updateExPreferential);
					}
				}
				CustomerInfoLog.commit();
			}
			start+=500;
			contracts = contractService.queryContractWithMoney(condition,start,limit); 
		}
	}

	/**
	 * @description 完善折扣出运费折扣外其他折扣信息.  
	 * @author 潘光均
	 * @version 0.1 2013-3-27
	 * @param 
	 *@date 2013-3-27
	 * @return void
	 * @update 2013-3-27 下午4:52:43
	 */
	private void createPreferentialOtherRate(Contract contract, Preferential upatePreferential) {
		upatePreferential.setContractId(contract.getId());
		upatePreferential.setAgentgathRate(1d);
		upatePreferential.setDeliveryPriceRate(1d);
		upatePreferential.setInsuredPriceRate(1d);
//		upatePreferential.setReceivePriceRate(1d);
	}

	/**
	 * @description 产生创建的折扣对象.  
	 * @author 潘光均
	 * @version 0.1 2013-3-22
	 * @param 
	 *@date 2013-3-22
	 * @return void
	 * @update 2013-3-22 上午9:18:20
	 */
	private void geneNewPreferential(Contract contract,Preferential upatePreferential,String preferType) {
		upatePreferential.setStatus(Constant.CONTRACT_STATUS_EFFECT);
		upatePreferential.setCreateDate(new Date());
		upatePreferential.setBeginTime(new Date());
		upatePreferential.setModifyDate(new Date());
		upatePreferential.setContractId(contract.getId());
		upatePreferential.setEndTime(contract.getContractendDate());
		upatePreferential.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		upatePreferential.setPreferType(preferType);
	}
	/**
	 * 
	* @Title: createNewContractTaxObj
	* @Description: 创建税务标记的对象
	* @author chenaichun 
	* @param @param contract
	* @param @param upatePreferential
	* @param @param preferType    设定文件
	* @date 2013-11-16 下午3:03:28
	* @return void    返回类型
	* @throws
	* @update 2013-11-16 下午3:03:28
	 */
	private void createNewContractTaxObj(ContractTax contractTax) {
		contractTax.setCreateDate(new Date());
		contractTax.setModifyDate(new Date());
		contractTax.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		contractTax.setCreateUser(ContextUtil.getCreateOrModifyUserId());
	}
	/**
	 * @description 查询有效的月发月送优惠方案.  
	 * @author 潘光均
	 * @version 0.1 2013-3-12
	 * @param 
	 *@date 2013-3-12
	 * @return PrefrentialDeal
	 * @update 2013-3-12 下午2:12:40
	 */
	private PrefrentialDeal getEffectPrefrentialDeal(String preType) {
		//封装查询优惠方案的条件
		PrefrentialDeal dealCondition = new PrefrentialDeal();
		dealCondition.setStatus(Constant.CONTRACT_STATUS_EFFECT);
		dealCondition.setPreferType(preType);
		//查询有效的月发月送优惠方案
		List<PrefrentialDeal> deals = prefrentialDealManager.searchEffectPrefrentialDeal(dealCondition);
		if (null==deals) {
			log.warn("未找到有效的折扣方案");
			return null;
		}else if( 0 == deals.size()){
			log.warn("未找到有效的折扣方案");
			return null;
		}else if(1 == deals.size()){
			return deals.get(0);
		}else{
			log.warn("存在多套有效的折扣方案");
			return deals.get(0);
		}
	}

	/**
	 * @description 根据客户近三个月的发货金额计算客户月发月送合同的折扣.  
	 * @author 潘光均
	 * @version 0.1 2013-3-12
	 * @param prefrentialDeal:折扣方案
	 * @param contractDto:包含客户近三个月发货金额的合同信息
	 *@date 2013-3-12
	 * @return double
	 * @update 2013-3-12 上午11:00:36
	 */
	private double getMonthSendRate(PrefrentialDeal prefrentialDeal,
			List<CustMonthSum> monthSums) {
		if (null == prefrentialDeal) {
			log.warn("未找到有效的折扣方案信息");
			return 1;
		}
		if(null == prefrentialDeal.getDealItems()){
			log.warn("未找到相应折扣条目未空");
			return 1;
		}else if(0 == prefrentialDeal.getDealItems().size()){
			log.warn("未找到相应折扣条目没有信息");
			return 1;
		}
		double money = getMaxAmount(monthSums);
		for (int i = 0; i < prefrentialDeal.getDealItems().size(); i++) {
			if (money>=prefrentialDeal.getDealItems().get(i).getMinAmount()&&money<=prefrentialDeal.getDealItems().get(i).getMaxAmount()) {
				return prefrentialDeal.getDealItems().get(i).getRate();
			}
		}
		return 1;
	}

	/**
	 * @description 获取最金额.  
	 * @author 潘光均
	 * @version 0.1 2013-3-12
	 * @param 
	 *@date 2013-3-12
	 * @return double
	 * @update 2013-3-12 下午2:07:55
	 */
	private double getMaxAmount(List<CustMonthSum> monthSums) {
		List<Double> amounts = new ArrayList<Double>();
		if (CollectionUtils.isNotEmpty(amounts)) {
			for (int i = 0; i < monthSums.size(); i++) {
				amounts.add(monthSums.get(i).getAmount());
			}
		}
		double money = 0;
		if (CollectionUtils.isNotEmpty(amounts)) {
			 money = Collections.max(amounts);
		}
		return money;
	}

	/**
	 * @description 修改特殊客户的月发月送合同运费折扣.  
	 * @author 潘光均
	 * @version 0.1 2013-3-13
	 * @param Contract：合同信息
	 *@date 2013-3-13
	 * @return void
	 * @update 2013-3-13 下午1:42:00
	 */
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.manager.IContractManager#updateMonthSendRate(com.deppon.crm.module.customer.shared.domain.Contract)
	 */
	@Override
	public void updateMonthSendRate(Contract con) {
		//数据校验
		if (con == null) {
			throw new ContractException(ContractExceptionType.Data_Error);
		}
		//获取合同信息
		Contract contract = contractService.getAllContractInfoById(con.getId());
		
		//校验是否可以修改运费折扣
		ContractValidator.validataModifyMonthSendRate(contract);
		if (null != contract
				&& null != contract.getPreferential()
				&& null != con
				&& null != con.getPreferential()
				&& !ContractUtil.compareDouble(contract.getPreferential()
						.getChargeRebate(), con.getPreferential()
						.getChargeRebate())) {
			//获取合同折扣信息
			Preferential preferential = con.getPreferential();
			if (StringUtils.isEmpty(preferential.getId())) {
				geneNewPreferential(contract, preferential,Constant.CONTRACT_TYPE_LINGDAN);
				createPreferentialOtherRate(contract,preferential);
				contractService.createPreferential(preferential);
			}else{
				//修改合同信息
				geneNewPreferential(con, preferential,Constant.CONTRACT_TYPE_LINGDAN);
				contractService.updatePreferential(contract.getPreferential(),preferential);
			}
		}
		if (null != contract
				&& null != contract.getExPreferential()
				&& null != con
				&& null != con.getExPreferential()
				&& !ContractUtil.compareDouble(contract.getExPreferential()
						.getChargeRebate(), con.getExPreferential()
						.getChargeRebate())) {
			//获取合同折扣信息
			String preId = null;
			if (null!=contract&&null!=contract.getExPreferential()) {
				preId = contract.getExPreferential().getId();
			}
			Preferential preferential = con.getExPreferential();
			preferential.setId(preId);
			if (StringUtils.isEmpty(preferential.getId())) {
				geneNewPreferential(contract, preferential,Constant.CONTRACT_TYPE_EXPRESS);
				createPreferentialOtherRate(contract,preferential);
				contractService.createPreferential(preferential);
			}else{
				//修改合同信息
				geneNewPreferential(con, preferential,Constant.CONTRACT_TYPE_EXPRESS);
				contractService.updatePreferential(contract.getExPreferential(),preferential);
			}
		}
	}
	
	/**
	 * @description 查看修改月发月送折扣信息的合同信息.  
	 * @author 潘光均
	 * @version 0.1 2013-3-13
	 * @param 
	 *@date 2013-3-13
	 * @return Contract
	 * @update 2013-3-13 下午4:14:45
	 */	
	public Contract getUpdateMonthSendInfo(String contractId) {
		// 校验合同状态
		Assert.notNull(contractId, "cancel contract id con't null !");
		Contract contract = contractService.getAllContractInfoById(contractId);
		// 设置最近三个月份发货金额
		contract.setArrAmount(contractService.getArrearaMountByCustId(contract
				.getMember().getId()));
		// 校验合同是否可以修改
		ContractValidator.canOperate(contract,ContractHandleType.MODIFYMONTHSENDRATE.getHandleType());
		return contract;
	}
	/**
	 * 
	 * <p>
	 * Description:查询合同操作日志<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月16日
	 * @param begin
	 * @param end
	 * @return
	 *
	 */
	@Override
	public List<ContractOperatorLog> queryContractOperatorLogsForDate(
			Date begin, Date end) {
		return contractService.queryContractOperatorLogsForDate(begin, end);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.manager.IContractManager#searchInitContract(com.deppon.crm.module.customer.shared.domain.ContractCondition, int, int)
	 */
	@Override
	public List<Contract> searchInitContract(
			ContractCondition contractCondition, int start, int limit) {
		contractCondition.setDeptId(ContextUtil.getCurrentUserDeptId());
		contractCondition.setContractStatus(Constant.CONTRACT_STATUS_EFFECT);
		return contractService.searchInitContract(contractCondition,start,limit);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.manager.IContractManager#countInitContract(com.deppon.crm.module.customer.shared.domain.ContractCondition)
	 */
	@Override
	public int countInitContract(ContractCondition contractCondition) {
		contractCondition.setDeptId(ContextUtil.getCurrentUserDeptId());
		contractCondition.setContractStatus(Constant.CONTRACT_STATUS_EFFECT);
		return contractService.countInitContract(contractCondition);
	}
	/**
	 * 
	 * <p>
	 * Description:检验新建合同和原合同失效日期是否一致<br />
	 *  不一致返回原合同，一致返回null
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-14
	 * @param contract
	 * @return
	 * Contract
	 */
	@Override
	public Contract checkCreateContractDate(ContractDetailView contract) {

		Contract resultContract = null;
        ContractCondition condition = new ContractCondition();
        condition.setCustNumber(contract.getCustId());
        List<Contract> contracts = contractService.searchContract(condition, 0, Integer.MAX_VALUE);

		if(contracts.size()>0){
			resultContract = ContractValidator.checkAllowAcquireCreateDate(contracts, contract.getContractBeginDate(),contract.getContractendDate());
		}
		
		return resultContract;
	}
		/**
	 * 
	 * @Title: updateDuningDept
	 *  <p>
	 * @Description: 修改合同的催款部门
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-5-8
	 * @return void
	 * @throws
	 */
	@Override
	public void modifyDuningDept(String dunningDeptCode, Boolean ifForeignGoods,
			String contractId) {
		//校验传入的数据是否完整
		if (StringUtils.isEmpty(contractId)||ifForeignGoods == null 
				||(ifForeignGoods && StringUtils.isEmpty(dunningDeptCode))) {
			throw new ContractException(ContractExceptionType.DUNINGDEPT_DATA_ERROR);
		}
		//获取原合同信息
		Contract contract = contractService.getAllContractInfoById(contractId);
		if (ValidateUtil.objectIsEmpty(contract)) {
			throw new ContractException(ContractExceptionType.DUNINGDEPT_DATA_ERROR);
		}
		//校验合同是否为已经修改以及是否为有效合同
		ContractValidator.validateModifyDunningDept(contract, ifForeignGoods, dunningDeptCode);
		//执行修改操作
		contractService.modifyDuningDept(dunningDeptCode, ifForeignGoods, contractId);
	}
 
	@Override
	public void modifyPriceVersionDate(Date priceVersionDate,Date exPriceVersionDate,String contractId) {
		if (StringUtils.isEmpty(contractId)
				|| ((ValidateUtil.objectIsEmpty(priceVersionDate)))
				&& ValidateUtil.objectIsEmpty(exPriceVersionDate)) {
			throw new ContractException(ContractExceptionType.DUNINGDEPT_DATA_ERROR);
		}
		//获取原合同信息
		Contract contract = contractService.getAllContractInfoById(contractId);
		if (ValidateUtil.objectIsEmpty(contract)) {
			throw new ContractException(
					ContractExceptionType.DUNINGDEPT_DATA_ERROR);
		}
		if (null != contract.getPriceVersionDate()
				&& priceVersionDate.equals(contract.getPriceVersionDate())
				&& null != contract.getExPriceVersionDate()
				&& exPriceVersionDate.equals(contract.getExPriceVersionDate())) {
			throw new ContractException(ContractExceptionType.NO_CHANGE_ERROR);
		}
		contractService.modifyPriceVersionDate(priceVersionDate,exPriceVersionDate,contractId);
	}

	@Override
	public void saveCustInfoForArreaAmoutMessage() {
		List<Map<String, String>> list = contractService.getCustInfoForArreaAmoutMessage();
		if (CollectionUtils.isNotEmpty(list)) {
			String deptId = null;
			String custNumber = null;
			Message message = null;
			Date date = new Date();
			List<Message> messages = new ArrayList<Message>();
			for (Map<String, String> map : list) {
				deptId = String.valueOf(map.get("DEPART"));
				custNumber = (String)map.get("CUST");
				message = new Message();
				message.setTaskcount(1);
				message.setTasktype(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_CUSTMER_MESSAGE);
				message.setDeptId(Integer.parseInt(deptId));
				message.setIshaveinfo("客户编码:"+custNumber+",可用结算额度不足20%,请及时还款或者申请结算额度!");
				message.setCreateDate(date);
				messages.add(message);
			}
			if (CollectionUtils.isNotEmpty(messages)) {
				messageManager.addMessageList(messages);
			}else {
				return ;
			}
		}else {
			log.warn("there hava no data to save");
			return ;
		}
	}
	public IBpsWorkflowManager getBpsWorkflowManager() {
		return bpsWorkflowManager;
	}

	public void setBpsWorkflowManager(IBpsWorkflowManager bpsWorkflowManager) {
		this.bpsWorkflowManager = bpsWorkflowManager;
	}

	
	public void setContractApplyOperate(IContractApplyOperate contractApplyOperate) {
		this.contractApplyOperate = contractApplyOperate;
	}

	/**
	 * @param contractWorkflowService : set the property contractWorkflowService.
	 */
	public void setContractWorkflowService(
			IContractWorkflowService contractWorkflowService) {
		this.contractWorkflowService = contractWorkflowService;
	}
	/**
	 *@author chenaichun
	 * @date 2014年5月5日 下午3:02:06 
	 * @param repeatedCustManager the repeatedCustManager to set
	 */
	public void setRepeatedCustManager(IRepeatedCustManager repeatedCustManager) {
		this.repeatedCustManager = repeatedCustManager;
	}

}

