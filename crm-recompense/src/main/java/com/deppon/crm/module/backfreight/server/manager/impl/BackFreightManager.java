package com.deppon.crm.module.backfreight.server.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.backfreight.server.manager.IBackFreightManager;
import com.deppon.crm.module.backfreight.server.service.IBackFreightService;
import com.deppon.crm.module.backfreight.server.util.BackFreightConstant;
import com.deppon.crm.module.backfreight.shared.domain.BackFreight;
import com.deppon.crm.module.backfreight.shared.domain.BackFreightSearchCondition;
import com.deppon.crm.module.backfreight.shared.exception.BackFreightException;
import com.deppon.crm.module.backfreight.shared.exception.BackFreightExceptionType;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.crm.module.common.server.manager.ISpecialDayManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.server.utils.DateUtil;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.servicerecovery.server.manager.IServiceRecoveryManager;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.Waybill;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * 
 * @description：退运费manager接口实现类
 * @author 华龙
 * @version 1.0
 * @date 2012-10-29下午2:19:13
 */
public class BackFreightManager implements IBackFreightManager {
	// 退运费service
	private IBackFreightService backFreightService;
	// 文件
	private IFileManager fileManager;
	// 理赔
	private RecompenseManager recompenseManager;
	// 服务补救
	private IServiceRecoveryManager serviceRecoveryManager;
	// 员工
	private IEmployeeService employeeService;
	// 工作日
	private ISpecialDayManager specialDayManager;
	// 固定客户
	private IMemberManager memberManager;
	// 部门
	private IDepartmentService departmentService;

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @return IDepartmentService
	 */
	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @param departmentService
	 *            void
	 */
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @return IMemberManager
	 */
	public IMemberManager getMemberManager() {
		return memberManager;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @return IMemberManager
	 */
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @return IMemberManager
	 */
	public RecompenseManager getRecompenseManager() {
		return recompenseManager;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @return IMemberManager
	 */
	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @return IMemberManager
	 */
	public IServiceRecoveryManager getServiceRecoveryManager() {
		return serviceRecoveryManager;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @return IMemberManager
	 */
	public void setServiceRecoveryManager(
			IServiceRecoveryManager serviceRecoveryManager) {
		this.serviceRecoveryManager = serviceRecoveryManager;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @return IMemberManager
	 */
	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @return IMemberManager
	 */
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @return IMemberManager
	 */
	public ISpecialDayManager getSpecialDayManager() {
		return specialDayManager;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @return IMemberManager
	 */
	public void setSpecialDayManager(ISpecialDayManager specialDayManager) {
		this.specialDayManager = specialDayManager;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @return IMemberManager
	 */
	public IFileManager getFileManager() {
		return fileManager;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @return IMemberManager
	 */
	public void setFileManager(IFileManager fileManager) {
		this.fileManager = fileManager;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @return IMemberManager
	 */
	public IBackFreightService getBackFreightService() {
		return backFreightService;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @return IMemberManager
	 */
	public void setBackFreightService(IBackFreightService backFreightService) {
		this.backFreightService = backFreightService;
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
		Waybill waybill = serviceRecoveryManager
				.findWaybillByNumForBackFreight(waybillNumber);
		// 工作日计算,出库日期加1开始算
		int days = 0;
		if (null != waybill && null != waybill.getSignedDate()) {
			Date startDate = DateUtil.getDateMidnight(waybill.getSignedDate(),
					1);
			days = specialDayManager.getActualWorkdays(startDate, new Date());
		}
		if (BackFreightValidator.checkWaybill(waybill, days, user)
				&& checkAllServiceInvalid(waybillNumber)) {
			// 封装客户信息
			String standardCode = user.getEmpCode().getDeptId()
					.getStandardCode();
			// 如果当前登录部门的标杆编码与运单上的出发部门一致的话就带出出发部门客户名字和编码
			if (standardCode.equals(waybill.getSenderDeptStandardCode())) {
				waybill.setCustomerNum(waybill.getSenderCustomerNum());
				waybill.setContactName(waybill.getSenderContactName());
			} else {
				// 带出到达
				waybill.setCustomerNum(waybill.getConsigneeCustomerNum());
				waybill.setContactName(waybill.getConsigneeContactName());
			}
			// 根据客户编码查询客户信息
			Member member = memberManager.getMemberByCustNumber(waybill
					.getCustomerNum());
			// 判断是否查询出客户
			if (null != member) {
				// 客户名称
				waybill.setCustomerName(member.getCustName());
				// 客户等级
				waybill.setCustomerLevel(member.getDegree());
			}
			// 配置部门信息 根据配载部门标杆编码查询
			Department stowageDept = departmentService
					.getDeptByStandardCode(waybill.getStowageDeptStandardCode());
			// 配置部门
			if (null != stowageDept) {
				waybill.setStowageDept(stowageDept.getId());
				waybill.setStowageDeptName(stowageDept.getDeptName());
			}
			// 子公司查询接口 调用接口进行查询
			String subsidiary = serviceRecoveryManager
					.getSubsidiaryByDeptStandardCode(standardCode);
			// 为子公司设值
			waybill.setSubsidiary(subsidiary);
			return waybill;
		}
		return null;
	}

	/**
	 * 
	 * @description ：保存退运费
	 * @author 华龙
	 * @version 1.0
	 * @param :BackFreight BackFreight：退运费实体
	 * @date 2012-10-29 下午2:36:02
	 * @return void
	 */
	@Transactional
	@Override
	public void submitBackFreight(BackFreight backFreight, User user) {
		// 校验运单是否可以进行退运费
		if (BackFreightValidator.checkBackFreightExist(backFreight)
				&& checkAllServiceInvalid(backFreight.getWaybillNumber())
				&& BackFreightValidator.checkBackFreightValid(backFreight)) {
			// 可以退运费的运单保存
			// 得到用户部门id
			String userDeptId = user.getEmpCode().getDeptId().getId();
			// 创建人
			backFreight.setCreateUser(user.getEmpCode().getId());
			// 创建世界
			backFreight.setCreateDate(new Date());
			// 申请人
			backFreight.setApplicant(user.getEmpCode().getId());
			// 申请部门
			backFreight.setApplyDept(user.getEmpCode().getDeptId().getId());
			// 申请时间
			backFreight.setApplyTime(new Date());
			// 申请状态
			backFreight
					.setApplyStatus(BackFreightConstant.BACK_FREIGHT_STATUS_WAIT_ACCEPT);
			// 职务
			backFreight.setPosition(user.getEmpCode().getPosition());
			// 客户不存在生成散客

			// 查询运单
			FossWaybillInfo waybillInfo = recompenseManager
					.getFossWaybillInfo(backFreight.getWaybillNumber());
			String customerNum = null;
			// 封装客户信息
			String standardCode = user.getEmpCode().getDeptId()
					.getStandardCode();
			if (standardCode.equals(waybillInfo.getDepartureDeptNumber())) {
				// Department leaveDept = departmentService
				// .getDeptByStandardCode(waybillInfo
				// .getDepartureDeptNumber());
				customerNum = memberManager.getNumberForRecompense(
						waybillInfo.getSenderMobile(),
						waybillInfo.getSenderPhone(), waybillInfo.getSender(),
						userDeptId, backFreight.getCustomerNum(),waybillInfo,BackFreightConstant.LEAVE);
			} else {
				// Department arriveDept = departmentService
				// .getDeptByStandardCode(waybillInfo
				// .getLadingStationNumber());
				customerNum = memberManager.getNumberForRecompense(
						waybillInfo.getConsigneeMobile(),
						waybillInfo.getConsigneePhone(),
						waybillInfo.getConsignee(), userDeptId,
						backFreight.getCustomerNum(),waybillInfo,BackFreightConstant.ARRIVE);
			}
			// 设置客户编码
			backFreight.setCustomerNum(customerNum);

			// 执行更新操作
			backFreight = backFreightService.addBackFreight(backFreight);
			// 保存附件
			List<FileInfo> fileInfoList = backFreight.getFileInfoList();
			for (FileInfo fileInfo : fileInfoList) {
				fileInfo.setCreateUser(user.getEmpCode().getId());
				fileInfo.setCreateDate(new Date());
				fileInfo.setSourceId(backFreight.getId());
				fileInfo.setSourceType(BackFreightConstant.BACK_FREIGHT_SOURCE_TYPE);
				fileManager.saveFileInfo(fileInfo);
			}
			// 封装传递到接口的参数
			String financeDept = departmentService.getDepartmentById(
					backFreight.getFinanceDept()).getStandardCode();
			backFreight.setFinanceDeptCode(financeDept);
			Employee e = employeeService.getEmpById(backFreight.getApplicant());
			backFreight.setEmpCode(e.getEmpCode());
			backFreight.setApplicantName(e.getEmpName());
			backFreight.setPosition(e.getPosition());

			String applyDept = departmentService.getDepartmentById(
					backFreight.getApplyDept()).getStandardCode();
			backFreight.setApplyDept(applyDept);
			// 配载部门标杆编码
			String stowageDeptCode = departmentService.getDepartmentById(
					backFreight.getStowageDept()).getStandardCode();
			backFreight.setStowageDept(stowageDeptCode);
			// 调用OA接口
			String oaWorkflowNum = backFreightService
					.submitBackFreightWorkflow(backFreight);
			// 如果有返回工作流号 则申请成功，退运费申请成功，调用接劳务费校验接口
			if (oaWorkflowNum != null && !"".equals(oaWorkflowNum)) {
				backFreightService.ServiceChargeStatusUpdate(
						backFreight.getWaybillNumber(), false);
			}
			// 根据退运费状态
			backFreightService.updateBackFreightWorkflowNum(
					backFreight.getId(), oaWorkflowNum);

		}

	}

	/**
	 * 
	 * @Description: 查询其他补偿服务是否存在
	 * @author 华龙
	 * @return
	 * @return boolean
	 * @date 2012-11-12 下午3:28:02
	 * @version V1.0
	 */
	private boolean checkAllServiceInvalid(String waybillNumber) {
		// 退运费
		BackFreight backfreight = backFreightService
				.findValidBackFreightByNum(waybillNumber);
		// 服务补救
		ServiceRecovery serviceRecovery = serviceRecoveryManager
				.findValidServiceRecoveryByNum(waybillNumber);
		// 理赔
		RecompenseApplication recompense = recompenseManager
				.getRecompenseApplicationByVoucherNo(waybillNumber);
		// 时效延误差错
		Boolean delayAccident = backFreightService
				.findDelayAccidentByNum(waybillNumber);
		// 如果理赔单的状态为免赔，则不进行判断
		if(recompense!=null){
			if (Constants.STATUS_EXEMPTED.equals(recompense.getStatus())) {
				recompense = null;
			}
		}
		// 坏账
		Boolean badDebt = backFreightService.findBadDebtByNum(waybillNumber);
		// 进行校验
		return BackFreightValidator.checkAllServiceInvalid(backfreight,
				serviceRecovery, recompense, delayAccident, badDebt);
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
	public boolean returnBackFreightStatus(String oaWorkflowNum,
			String employeeNum, boolean verifyStatus, Date verifyTime,
			String verifyDesc) {
		// TODO 回调的参数需与接口确认
		BackFreight backFreight = backFreightService
				.getBackFreightByOaWorkFlowNum(oaWorkflowNum);
		// TODO 更新需要修改的实体参数
		if (BackFreightValidator.checkBackFreightExist(backFreight)
				&& BackFreightValidator.checkApplyStatus(backFreight)) {
			if (verifyStatus) {
				// 同意操作
				backFreight
						.setApplyStatus(BackFreightConstant.BACK_FREIGHT_STATUS_ACCEPTED);
			} else {
				// 不同意
				backFreight
						.setApplyStatus(BackFreightConstant.BACK_FREIGHT_STATUS_NOT_ACCEPT);
			}
			String employeeId = getEmployeeIdByNum(employeeNum);
			backFreight.setVerifier(employeeId);
			backFreight.setVerifyTime(new Date());
			// TODO 更新为新方法
			backFreightService.updateBackFreight(backFreight);
			// TODO 比较传入状态，确认是否调用FOSS

			Employee e = employeeService.getEmpById(backFreight.getApplicant());
			backFreight.setApplicant(e.getEmpCode());
			String applyDeptCode = departmentService.getDepartmentById(
					backFreight.getApplyDept()).getStandardCode();
			backFreight.setApplyDept(applyDeptCode);
			// 劳务费校验
			if (verifyStatus) {
				backFreightService.ServiceChargeStatusUpdate(
						backFreight.getWaybillNumber(), false);
			boolean flag=	backFreightService.submitBackFreightPayment(backFreight);
				if(!flag){
					BackFreightException backFreightException = new BackFreightException(
							BackFreightExceptionType.BACK_FREIGHT_PAYFAIl_EXCEPTION);
					throw new GeneralException(backFreightException.getErrorCode(),
							backFreightException.getMessage(), backFreightException,
							new Object[] {}) {
					};
					
				}
			} else {
				backFreightService.ServiceChargeStatusUpdate(
						backFreight.getWaybillNumber(), true);

			}
			return true;
		} else {
			BackFreightException backFreightException = new BackFreightException(
					BackFreightExceptionType.BACK_FREIGHT_OA_RETURN_EXCEPTION);
			throw new GeneralException(backFreightException.getErrorCode(),
					backFreightException.getMessage(), backFreightException,
					new Object[] {}) {
			};
		}

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
		// 查询员工列表
		List<Employee> employeeList = employeeService.queryAll(searchEmployee,
				Integer.MAX_VALUE, 0);
		if (null != employeeList && employeeList.size() > 0) {
			employeeId = employeeList.get(0).getId();
		}
		return employeeId;
	}

	/**
	 * 
	 * @description ：根据条件查询退运费列表
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreightSearchCondition
	 *            condition : 退运费搜索条件
	 * @date 2012-10-29 下午2:41:42
	 * @return List<BackFreight> 退运费列表
	 */
	@Override
	public List<BackFreight> searchBackFreightByCondition(
			BackFreightSearchCondition condition) {
		// 校验查询条件
		if (BackFreightValidator.checkBackFreightSearchCondition(condition)) {
			BackFreightSearchCondition searchcondition = transBackFreightByCondition(condition);
			return backFreightService
					.searchBackFreightByCondition(searchcondition);
		}
		return null;
	}

	/**
	 * 
	 * @description ：转换查询条件
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreightSearchCondition
	 *            condition : 退运费搜索条件
	 * @date 2012-10-29 下午2:41:57
	 * @return BackFreightSearchCondition 退运费搜索条件
	 */
	@Override
	public BackFreightSearchCondition transBackFreightByCondition(
			BackFreightSearchCondition condition) {
		// 如果运单号不为空,去掉其他搜索条件
		if (condition.getWaybillNumbers() != null
				&& condition.getWaybillNumbers().size() > 0
				&& !(condition.getWaybillNumbers().size() == 1 && ""
						.equals(condition.getWaybillNumbers().get(0)))) {
			// 当根据运单号查询时忽略其他条件
			BackFreightSearchCondition br = new BackFreightSearchCondition();
			br.setWaybillNumbers(condition.getWaybillNumbers());
			br.setLimit(condition.getLimit());
			br.setStart(condition.getStart());
			br.setStartDate(null);
			br.setEndDate(null);
			return br;
		}
		condition.setWaybillNumbers(null);
		return condition;
	}

	/**
	 * 
	 * @description ：根据条件统计总条数
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreightSearchCondition
	 *            condition : 退运费搜索条件
	 * @date 2012-10-29 下午2:47:47
	 * @return int 总条数
	 */
	@Override
	public int countBackFreightByCondition(BackFreightSearchCondition condition) {
		if (BackFreightValidator.checkBackFreightSearchCondition(condition)) {
			BackFreightSearchCondition searchcondition = transBackFreightByCondition(condition);
			return backFreightService
					.countBackFreightByCondition(searchcondition);
		}
		return 0;
	}

	/**
	 * 
	 * @description ：根据条件导出退运费
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreightSearchCondition
	 *            condition :退运费搜索条件
	 * @date 2012-10-29 下午2:42:06
	 * @return List<BackFreight> 退运费列表
	 */
	@Override
	public List<BackFreight> exportBackFreightByCondition(
			BackFreightSearchCondition condition) {
		if (BackFreightValidator.checkBackFreightSearchCondition(condition)) {
			BackFreightSearchCondition searchcondition = transBackFreightByCondition(condition);
			return backFreightService
					.exportBackFreightByCondition(searchcondition);
		}
		return null;

	}

	/**
	 * 
	 * @description ：根据ID查询退运费
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            BackFreightId :退运费Id
	 * @date 2012-10-29 下午2:42:14
	 * @return BackFreight 退运费实体
	 */
	@Override
	public BackFreight getBackFreightById(String backFreightId) {
		if (null != backFreightId && !"".equals(backFreightId)) {
			BackFreight backFreight = backFreightService
					.getBackFreightById(backFreightId);
			FileInfo fileInfo = new FileInfo();
			fileInfo.setSourceId(backFreightId);
			fileInfo.setSourceType(BackFreightConstant.BACK_FREIGHT_SOURCE_TYPE);
			List<FileInfo> fileInfoList = fileManager
					.searchFileInfoByCondition(fileInfo, 0, Integer.MAX_VALUE);
			backFreight.setFileInfoList(fileInfoList);
			return backFreight;
		}
		return null;

	}

	/**
	 * 
	 * @Description: 查询有效的退运费
	 * @author huangzhanming
	 * @param waybillNumber
	 * @return
	 * @return BackFreight
	 * @date 2012-11-9 下午3:34:46
	 * @version V1.0
	 */
	@Override
	public BackFreight findValidBackFreightByNum(String waybillNumber) {
		if (null != waybillNumber && !"".equals(waybillNumber)) {
			return backFreightService.findValidBackFreightByNum(waybillNumber);
		}
		return null;

	}

}
