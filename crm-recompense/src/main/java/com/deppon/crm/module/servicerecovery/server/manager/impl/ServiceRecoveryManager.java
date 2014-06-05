package com.deppon.crm.module.servicerecovery.server.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.bpmsapi.module.server.api.exception.BPMSException;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.backfreight.server.manager.IBackFreightManager;
import com.deppon.crm.module.backfreight.server.util.BackFreightConstant;
import com.deppon.crm.module.backfreight.shared.domain.BackFreight;
import com.deppon.crm.module.bps.server.manager.IBpsWorkflowManager;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.crm.module.common.server.manager.ISpecialDayManager;
import com.deppon.crm.module.common.server.manager.impl.ExpressPointBusinessDeptManager;
import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.ExpressPointBusinessDept;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.server.manager.impl.BaseDataManager;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.frameworkimpl.server.util.EncryptUtil;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.server.utils.DateUtil;
import com.deppon.crm.module.recompense.shared.domain.OAWorkflow;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.exception.RecompenseException;
import com.deppon.crm.module.recompense.shared.exception.RecompenseExceptionType;
import com.deppon.crm.module.servicerecovery.server.manager.IServiceRecoveryManager;
import com.deppon.crm.module.servicerecovery.server.service.IServiceRecoveryService;
import com.deppon.crm.module.servicerecovery.server.util.ServiceRecoveryConstant;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecoverySearchCondition;
import com.deppon.crm.module.servicerecovery.shared.domain.Waybill;
import com.deppon.crm.module.servicerecovery.shared.exception.ServiceRecoveryException;
import com.deppon.crm.module.servicerecovery.shared.exception.ServiceRecoveryExceptionType;
import com.deppon.crm.module.workflow.server.util.NormalClaimUtil;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.sso.util.StringUtil;

/**
 * 
 * @description：服务补救Manager接口实现类
 * @author 华龙
 * @version 1.0
 * @date 2012-10-29下午2:15:10
 */
public class ServiceRecoveryManager implements IServiceRecoveryManager {

	private IServiceRecoveryService serviceRecoveryService;
	private RecompenseManager recompenseManager;
	private IBackFreightManager backFreightManager;
	private IFileManager fileManager;
	private IEmployeeService employeeService;
	private ISpecialDayManager specialDayManager;
	private IAlterMemberManager alterMemberManager;
	private ExpressPointBusinessDeptManager expressPointBusinessDeptManager;
	// bps工作流
	private IBpsWorkflowManager bpsWorkflowManager;
	private BaseDataManager baseDataManager;
	private IMemberManager memberManager;
	public ExpressPointBusinessDeptManager getExpressPointBusinessDeptManager() {
		return expressPointBusinessDeptManager;
	}

	public void setExpressPointBusinessDeptManager(
			ExpressPointBusinessDeptManager expressPointBusinessDeptManager) {
		this.expressPointBusinessDeptManager = expressPointBusinessDeptManager;
	}


	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	private IDepartmentService departmentService;

	public ISpecialDayManager getSpecialDayManager() {
		return specialDayManager;
	}

	public void setSpecialDayManager(ISpecialDayManager specialDayManager) {
		this.specialDayManager = specialDayManager;
	}

	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public IBackFreightManager getBackFreightManager() {
		return backFreightManager;
	}

	public void setBackFreightManager(IBackFreightManager backFreightManager) {
		this.backFreightManager = backFreightManager;
	}

	public IServiceRecoveryService getServiceRecoveryService() {
		return serviceRecoveryService;
	}

	public void setServiceRecoveryService(
			IServiceRecoveryService serviceRecoveryService) {
		this.serviceRecoveryService = serviceRecoveryService;
	}

	public IFileManager getFileManager() {
		return fileManager;
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

	public IBpsWorkflowManager getBpsWorkflowManager() {
		return bpsWorkflowManager;
	}

	public void setBpsWorkflowManager(IBpsWorkflowManager bpsWorkflowManager) {
		this.bpsWorkflowManager = bpsWorkflowManager;
	}


	/**
	 * 
	 * @description ：为退运费提高根据运单号查询运单实体
	 * @author 华龙
	 * @version 1.0
	 * @param :String waybillNumber:运单号
	 * @date 2012-10-29 下午2:35:52
	 * @return Waybill 运单号
	 */
	public Waybill findWaybillByNumForBackFreight(String waybillNumber) {
		return serviceRecoveryService.findWaybillByNum(waybillNumber);
	}

	/**
	 * 
	 * @description ：根据运单号查询运单实体
	 * @author 华龙
	 * @version 1.0
	 * @param :String waybillNumber:运单号
	 * @date 2012-10-29 下午2:35:52
	 * @return Waybill 运单号
	 */
	@Override
	public Waybill findWaybillByNum(String waybillNumber, User user) {
		Waybill waybill = serviceRecoveryService
				.findWaybillByNum(waybillNumber);
		//运输类性质换成运输方式
		if(waybill.getTranType() != null){
			if(waybill.getTranType().equals(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_AIR)){
				waybill.setTranType(ServiceRecoveryConstant.SERVICE_RECOVERY_WAYBILL_TRANSTYPE_AIR_DESC);
			}else if(waybill.getTranType().equals(BackFreightConstant.BACK_FREIGHT_TRAN_TYPE_PACKAGE)){
				waybill.setTranType(ServiceRecoveryConstant.SERVICE_RECOVERY_WAYBILL_TRANSTYPE_PACKAGE_DESC);
			}else{
				waybill.setTranType(ServiceRecoveryConstant.SERVICE_RECOVERY_WAYBILL_TRANSTYPE_CAR_DESC);
			}
		}
		// 工作日计算,出库日期加1开始算
		int days = 0;
		if (null != waybill && null != waybill.getSignedDate()) {
			Date startDate = DateUtil.getDateMidnight(waybill.getSignedDate(),
					1);
			days = specialDayManager.getValieWorkdays(startDate, new Date(),
					"LEGAL_HOLIDAYS") + 1;
		}
		Department sendExpressDepartment = null;
		Department arriveExpressDepartment = null;
		Employee sendPrincipal = null;
		Employee arrivePrincipal = null;
		if (waybill.getSenderDeptStandardCode() != null
				&&!"".equals(waybill.getSenderDeptStandardCode())) {
			ExpressPointBusinessDept sendEPBS = expressPointBusinessDeptManager
					.getExpressPointBusinessDeptByDeptCode(waybill
							.getSenderDeptStandardCode());
			if (sendEPBS != null) {
				sendExpressDepartment = departmentService
						.getDeptByStandardCode(sendEPBS.getExpressPointCode());
				if(sendExpressDepartment != null&&
						sendExpressDepartment.getPrincipal() != null
							&&!"".equals(sendExpressDepartment.getPrincipal())){
					sendPrincipal = employeeService.
							getEmpByCode(sendExpressDepartment.getPrincipal());
				}
			}
		}
		if (waybill.getConsigneeDeptStandardCode() != null) {
			ExpressPointBusinessDept arriveEPBS = expressPointBusinessDeptManager
					.getExpressPointBusinessDeptByDeptCode(waybill
							.getConsigneeDeptStandardCode());
			if (arriveEPBS != null) {
				arriveExpressDepartment = departmentService
						.getDeptByStandardCode(arriveEPBS.getExpressPointCode());
				if(arriveExpressDepartment != null
						&&arriveExpressDepartment.getPrincipal() != null
							&&!"".equals(arriveExpressDepartment.getPrincipal())){
					arrivePrincipal = employeeService.
							getEmpByCode(arriveExpressDepartment.getPrincipal());
				}
			}
		}
		String party = ServiceRecoveryValidator.checkWaybill(waybill, days, user,sendExpressDepartment,
				arriveExpressDepartment,sendPrincipal,arrivePrincipal);
		if(ServiceRecoveryConstant.SERVICE_RECOVERY_WAYBILL_TRANSTYPE_PACKAGE_DESC
				.equals(waybill.getTranType())&&sendExpressDepartment!=null
				&&arriveExpressDepartment!=null&&sendExpressDepartment.getId()
				.equals(arriveExpressDepartment.getId())){
			party = "NOTSURE";
		}
		if (checkAllServiceInvalid(waybillNumber)) {
			if (StringUtil.notNull(waybill.getSenderCustomerNum())) {
				MemberResult sender = getAlterMemberManager()
						.queryBasicMemberByCustNum(
								waybill.getSenderCustomerNum());
				if (sender != null) {
					waybill.setSenderCustomerName(sender.getCustName());
					waybill.setSenderCustomerLevel(sender.getCustGrade());
				}
			}
			if (StringUtil.notNull(waybill.getConsigneeCustomerNum())) {
				MemberResult consignee = getAlterMemberManager()
						.queryBasicMemberByCustNum(
								waybill.getConsigneeCustomerNum());
				if (consignee != null) {
					waybill.setConsigneeCustomerName(consignee.getCustName());
					waybill.setConsigneeCustomerLevel(consignee.getCustGrade());
				}
			}
			// 子公司查询接口
			String standardCode = user.getEmpCode().getDeptId()
					.getStandardCode();
			String subsidiary = serviceRecoveryService
					.getSubsidiaryByDeptStandardCode(standardCode);
			waybill.setSubsidiary(subsidiary);
			waybill.setParty(party);
			return waybill;
		}
		return null;
	}

	/**
	 * 
	 * @Description: 查询其他补偿服务是否存在
	 * @author huangzhanming
	 * @return
	 * @return boolean
	 * @date 2012-11-12 下午3:28:02
	 * @version V1.0
	 */
	private boolean checkAllServiceInvalid(String waybillNumber) {
		BackFreight backfreight = backFreightManager
				.findValidBackFreightByNum(waybillNumber);
		ServiceRecovery serviceRecovery = serviceRecoveryService
				.findValidServiceRecoveryByNum(waybillNumber);
		RecompenseApplication recompense = recompenseManager
				.getRecompenseApplicationByVoucherNo(waybillNumber);
		// 如果理赔单的状态为免赔，则不进行判断
		if (recompense != null) {
			if (Constants.STATUS_EXEMPTED.equals(recompense.getStatus())) {
				recompense = null;
			}
		}
		Boolean isDelayAccident = serviceRecoveryService
				.findDelayAccidentByNum(waybillNumber);
		return ServiceRecoveryValidator.checkAllServiceInvalid(backfreight,
				serviceRecovery, recompense, isDelayAccident);
	}

	/**
	 * 
	 * @description ：保存服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param :ServiceRecovery serviceRecovery：服务补救实体
	 * @date 2012-10-29 下午2:36:02
	 * @return void
	 */
	@Override
	@Transactional
	public void submitServiceRecovery(ServiceRecovery serviceRecovery, User user) {
		try{
			// 校验运单是否可以进行服务补救
			if (ServiceRecoveryValidator.checkServiceRecoveryExist(serviceRecovery)
					&& checkAllServiceInvalid(serviceRecovery.getWaybillNumber())
					&& ServiceRecoveryValidator
							.checkServiceRecoveryValid(serviceRecovery)) {
				// 可以服务补救的运单保存
				String userDeptId = user.getEmpCode().getDeptId().getId();
				serviceRecovery.setCreateUser(user.getEmpCode().getId());
				serviceRecovery.setCreateDate(new Date());
				serviceRecovery.setApplicant(user.getEmpCode().getId());
				serviceRecovery.setApplyDept(user.getEmpCode().getDeptId().getId());
				serviceRecovery.setApplyTime(new Date());
				serviceRecovery.setPosition(user.getEmpCode().getPosition());
				serviceRecovery
						.setApplyStatus(ServiceRecoveryConstant.SERVICE_RECOVERY_STATUS_WAIT_ACCEPT);
				// 客户不存在生成散客
				FossWaybillInfo waybillInfo = recompenseManager
						.getFossWaybillInfo(serviceRecovery.getWaybillNumber());
				String customerNum = null;
				if (ServiceRecoveryConstant.SERVICE_RECOVERY_CUSTOMER_TYPE_SHIPPER
						.equals(serviceRecovery.getCustomerType())) {
					// Department leaveDept = departmentService
					// .getDeptByStandardCode(waybillInfo
					// .getDepartureDeptNumber());
					customerNum = memberManager.getNumberForRecompense(
							waybillInfo.getSenderMobile(),
							waybillInfo.getSenderPhone(), waybillInfo.getSender(),
							userDeptId, serviceRecovery.getCustomerNum(),waybillInfo,BackFreightConstant.LEAVE);
				} else {
					// Department arriveDept = departmentService
					// .getDeptByStandardCode(waybillInfo
					// .getLadingStationNumber());
					customerNum = memberManager.getNumberForRecompense(
							waybillInfo.getConsigneeMobile(),
							waybillInfo.getConsigneePhone(),
							waybillInfo.getConsignee(), userDeptId,
							serviceRecovery.getCustomerNum(),waybillInfo,BackFreightConstant.LEAVE);
				}
				serviceRecovery.setCustomerNum(customerNum);
				if(ServiceRecoveryConstant.SERVICE_RECOVERY_WAYBILL_TRANSTYPE.equals(
						waybillInfo.getTranType())&&"NOTSURE".equals(serviceRecovery.getParty())){
					serviceRecovery.setParty(serviceRecovery.getCustomerType());
				}
				serviceRecovery = serviceRecoveryService
						.addServiceRecovery(serviceRecovery);
				List<FileInfo> fileInfoList = serviceRecovery.getFileInfoList();
				for (FileInfo fileInfo : fileInfoList) {
					fileInfo.setCreateUser(user.getEmpCode().getId());
					fileInfo.setCreateDate(new Date());
					fileInfo.setSourceId(serviceRecovery.getId());
					fileInfo.setSourceType(ServiceRecoveryConstant.SERVICE_RECOVERY_SOURCE_TYPE);
					fileManager.saveFileInfo(fileInfo);
				}
				// 根据id获取财务部标杆编码
				String financeDeptCode = departmentService.getDepartmentById(
						serviceRecovery.getFinanceDept()).getStandardCode();
				serviceRecovery.setFinanceDeptCode(financeDeptCode);
				// 根据id获取申请人的工号，职务，及申请人姓名
				Employee e = employeeService.getEmpById(serviceRecovery
						.getApplicant());
				serviceRecovery.setEmpCode(e.getEmpCode());
				serviceRecovery.setApplicantName(e.getEmpName());
				serviceRecovery.setPosition(e.getPosition());
				// 根据id获取经手人的工号，名字
				Employee e1 = employeeService.getEmpById(serviceRecovery
						.getOperator());
				serviceRecovery.setOperatorCode(e1.getEmpCode());
				serviceRecovery.setOperatorName(e.getEmpName());
				String reduction = DataDictionaryUtil.getCodeDesc(
						DataHeadTypeEnum.SERVICERECOVERY_REDUCTION_TYPE,
						serviceRecovery.getReductionType());
				serviceRecovery.setReductionType(reduction);
				/*String oaWorkflowNum = serviceRecoveryService
						.submitServiceRecoveryWorkflow(serviceRecovery);*/
	
				Map<String, String> map = bpsWorkflowManager.createWorkflow(NormalClaimUtil.SERVICE_PROCESS_DEFINITION_NAME,
						NormalClaimUtil.SERVICE_PROCESS_INSTANCE_NORMALCLAIM,
						NormalClaimUtil.SERVICE_PROCESS_DESCRIPTION,
						"");
				String oaWorkflowNum = map.get("processId");
				String workflowNo = map.get("bizCode");
	
				serviceRecoveryService.updateServiceRecoveryWorkflowNum(
						serviceRecovery.getId(), oaWorkflowNum, workflowNo);
	
			}
		}catch (BPMSException e) {
			e.printStackTrace();
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);

			throw new GeneralException(e.getMsg(), e.getMsg(), re,
					new Object[] {}) {

			};
		} catch (Exception e) {
			e.printStackTrace();
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * @description ：根据申请状态和工作留号查询审批状态
	 * @author 华龙
	 * @version 1.0
	 * @param :String applyStatus：申请状态 String oaWorkflowNum： 工作流号
	 * @date 2012-10-29 下午2:36:11
	 * @return String 状态 同意或者拒绝
	 */
	@Override
	@Transactional
	public boolean returnServiceRecoveryStatus(String oaWorkflowNum,
			String employeeNum, boolean verifyStatus, Date verifyTime,
			String verifyDesc) {
		ServiceRecovery serviceRecovery = serviceRecoveryService
				.getServiceRecoveryByOaWorkFlowNum(oaWorkflowNum);
		if (ServiceRecoveryValidator.checkServiceRecoveryExist(serviceRecovery)
				&& ServiceRecoveryValidator.checkApplyStatus(serviceRecovery)) {
			if (verifyStatus) {
				serviceRecovery
						.setApplyStatus(ServiceRecoveryConstant.SERVICE_RECOVERY_STATUS_ACCEPTED);
			} else {
				serviceRecovery
						.setApplyStatus(ServiceRecoveryConstant.SERVICE_RECOVERY_STATUS_NOT_ACCEPT);
			}
			String employeeId = getEmployeeIdByNum(employeeNum);
			serviceRecovery.setVerifier(employeeId);
			serviceRecovery.setVerifyTime(new Date());

			serviceRecoveryService.updateServiceRecovery(serviceRecovery);
			// 传到foss的员工号
			Employee e = employeeService.getEmpById(serviceRecovery
					.getApplicant());
			serviceRecovery.setApplicant(e.getEmpCode());
			String applyDeptCode = null;
			if(ServiceRecoveryConstant.SERVICE_RECOVERY_WAYBILL_TRANSTYPE_PACKAGE_DESC
					.equals(serviceRecovery.getTranType())){
				FossWaybillInfo waybillInfo = recompenseManager
						.getFossWaybillInfo(serviceRecovery.getWaybillNumber());
				if("SHIPPER".equals(serviceRecovery.getParty())){
					applyDeptCode = waybillInfo.getReceiveDeptNumber();
				}else if("CONSIGNEE".equals(serviceRecovery.getParty())){
					applyDeptCode = waybillInfo.getLadingStationNumber();
				}else{
					applyDeptCode = departmentService.getDepartmentById(
							serviceRecovery.getApplyDept()).getStandardCode();
				}
			}else{
				applyDeptCode = departmentService.getDepartmentById(
						serviceRecovery.getApplyDept()).getStandardCode();
			}
			serviceRecovery.setApplyDept(applyDeptCode);
			if (verifyStatus) {
				boolean flag = serviceRecoveryService
						.submitServiceRecoveryPayment(serviceRecovery);
				if (!flag) {
					ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
							ServiceRecoveryExceptionType.SERVICE_RECOVERY_APPLYPAYMENT_ERROR_EXCEPTION);
					throw new GeneralException(
							serviceRecoveryException.getErrorCode(),
							serviceRecoveryException.getMessage(),
							serviceRecoveryException, new Object[] {}) {
					};

				}
			}

		} else {
			ServiceRecoveryException serviceRecoveryException = new ServiceRecoveryException(
					ServiceRecoveryExceptionType.SERVICERECOVERY_OA_RETURN_EXCEPTION);
			throw new GeneralException(serviceRecoveryException.getErrorCode(),
					serviceRecoveryException.getMessage(),
					serviceRecoveryException, new Object[] {}) {
			};

		}
		return true;
	}

	/**
	 * 
	 * @Description: 封装查询员工Id的查询方法
	 * @author huangzhanming
	 * @param employeeNum
	 * @return
	 * @return String
	 * @date 2012-11-13 上午8:55:42
	 * @version V1.0
	 */
	private String getEmployeeIdByNum(String employeeNum) {
		String employeeId = null;
		Employee searchEmployee = new Employee();
		searchEmployee.setEmpCode(employeeNum);
		List<Employee> employeeList = employeeService.queryAll(searchEmployee,
				Integer.MAX_VALUE, 0);
		if (null != employeeList && employeeList.size() > 0) {
			employeeId = employeeList.get(0).getId();
		}
		return employeeId;
	}

	/**
	 * 
	 * @description ：根据条件查询服务补救列表
	 * @author 华龙
	 * @version 1.0
	 * @param ServiceRecoverySearchCondition
	 *            condition : 服务补救搜索条件
	 * @date 2012-10-29 下午2:41:42
	 * @return List<ServiceRecovery> 服务补救列表
	 */
	@Override
	public List<ServiceRecovery> searchServiceRecoveryByCondition(
			ServiceRecoverySearchCondition condition) {
		if (ServiceRecoveryValidator.checkServiceRecoveryCondition(condition)) {
			ServiceRecoverySearchCondition searchcondition = transServiceRecoveryByCondition(condition);
			List<ServiceRecovery> list = serviceRecoveryService
					.searchServiceRecoveryByCondition(searchcondition);
			for(ServiceRecovery sr : list){
				sr.setWorkflowNumEnc(EncryptUtil.encrypt(sr.getOaWorkflowNum(),NormalClaimUtil.WORKFLOW_DESC_KEY));
			}
			return list;
		}
		return null;

	}

	/**
	 * 
	 * @description ：转换查询条件
	 * @author 华龙
	 * @version 1.0
	 * @param ServiceRecoverySearchCondition
	 *            condition : 服务补救搜索条件
	 * @date 2012-10-29 下午2:41:57
	 * @return ServiceRecoverySearchCondition 服务补救搜索条件
	 */
	@Override
	public ServiceRecoverySearchCondition transServiceRecoveryByCondition(
			ServiceRecoverySearchCondition condition) {
		// 如果运单号不为空,去掉其他搜索条件
		if (condition.getWaybillNumbers() != null
				&& condition.getWaybillNumbers().size() > 0
				&& !(condition.getWaybillNumbers().size() == 1 && ""
						.equals(condition.getWaybillNumbers().get(0)))) {
			ServiceRecoverySearchCondition sr = new ServiceRecoverySearchCondition();
			sr.setWaybillNumbers(condition.getWaybillNumbers());
			sr.setLimit(condition.getLimit());
			sr.setStart(condition.getStart());
			sr.setStartDate(null);
			sr.setEndDate(null);
			return sr;
		}
		condition.setWaybillNumbers(null);
		return condition;
	}

	/**
	 * 
	 * @description ：根据条件统计总条数
	 * @author 华龙
	 * @version 1.0
	 * @param ServiceRecoverySearchCondition
	 *            condition : 服务补救搜索条件
	 * @date 2012-10-29 下午2:47:47
	 * @return int 总条数
	 */
	@Override
	public int countServiceRecoveryByCondition(
			ServiceRecoverySearchCondition condition) {
		if (ServiceRecoveryValidator.checkServiceRecoveryCondition(condition)) {
			ServiceRecoverySearchCondition searchcondition = transServiceRecoveryByCondition(condition);
			return serviceRecoveryService
					.countServiceRecoveryByCondition(searchcondition);
		}
		return 0;
	}

	/**
	 * 
	 * @description ：根据条件导出服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param ServiceRecoverySearchCondition
	 *            condition :服务补救搜索条件
	 * @date 2012-10-29 下午2:42:06
	 * @return List<ServiceRecovery> 服务补救列表
	 */
	@Override
	public List<ServiceRecovery> exportServiceRecoveryByCondition(
			ServiceRecoverySearchCondition condition) {
		if (ServiceRecoveryValidator.checkServiceRecoveryCondition(condition)) {
			ServiceRecoverySearchCondition searchcondition = transServiceRecoveryByCondition(condition);
			return serviceRecoveryService
					.exportServiceRecoveryByCondition(searchcondition);
		}
		return null;
	}

	/**
	 * 
	 * @description ：根据ID查询服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            serviceRecoveryId :服务补救Id
	 * @date 2012-10-29 下午2:42:14
	 * @return ServiceRecovery 服务补救实体
	 */
	@Override
	public ServiceRecovery getServiceRecoveryById(String serviceRecoveryId) {
		if (null != serviceRecoveryId) {
			ServiceRecovery serviceRecovery = serviceRecoveryService
					.findServiceRecoveryById(serviceRecoveryId);
			FileInfo fileInfo = new FileInfo();
			fileInfo.setSourceType(ServiceRecoveryConstant.SERVICE_RECOVERY_SOURCE_TYPE);
			fileInfo.setSourceId(serviceRecoveryId);
			List<FileInfo> fileInfoList = fileManager
					.searchFileInfoByCondition(fileInfo, 0, Integer.MAX_VALUE);
			serviceRecovery.setFileInfoList(fileInfoList);
			return serviceRecovery;
		}
		return null;
	}

	/**
	 * 
	 * @Description: 查询有效的服务补救
	 * @author huangzhanming
	 * @param waybillNumber
	 * @return
	 * @return ServiceRecovery
	 * @date 2012-11-9 下午3:33:09
	 * @version V1.0
	 */
	@Override
	public ServiceRecovery findValidServiceRecoveryByNum(String waybillNumber) {
		return serviceRecoveryService
				.findValidServiceRecoveryByNum(waybillNumber);
	}

	/**
	 * 
	 * @Description: 通过部门标杆编码查询子公司
	 * @author huangzhanming
	 * @param standardCode
	 * @return
	 * @return String
	 * @date 2012-11-15 下午1:41:33
	 * @version V1.0
	 */
	@Override
	public String getSubsidiaryByDeptStandardCode(String standardCode) {
		return serviceRecoveryService
				.getSubsidiaryByDeptStandardCode(standardCode);
	}

	public IAlterMemberManager getAlterMemberManager() {
		return alterMemberManager;
	}

	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}
	public void setBaseDataManager(BaseDataManager baseDataManager) {
		this.baseDataManager = baseDataManager;
	}
	public BaseDataManager getBaseDataManager() {
		return baseDataManager;
	}
	@Override
	public List<Department> searchMyDataAuth(String type,int start,int limit){
		return baseDataManager.getMyAuthDataDeptsByFunction(type,start,limit);
	}
	private Department getApplyEmployee(String standCode){
		Employee principal = null;
		Department dept = null;
		if (standCode != null) {
			ExpressPointBusinessDept epbs = expressPointBusinessDeptManager
					.getExpressPointBusinessDeptByDeptCode(standCode);
			Department expressDepartment = null;
			if (epbs != null) {
				expressDepartment = departmentService
						.getDeptByStandardCode(epbs.getExpressPointCode());
				if(expressDepartment != null
						&&expressDepartment.getPrincipal() != null
							&&!"".equals(expressDepartment.getPrincipal())){
					principal = employeeService.
							getEmpByCode(expressDepartment.getPrincipal());
				}
				if("点部经理".equals(principal.getPosition())){
					dept = expressDepartment;
				}
			}
		}
		return dept;
	}

	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
}
