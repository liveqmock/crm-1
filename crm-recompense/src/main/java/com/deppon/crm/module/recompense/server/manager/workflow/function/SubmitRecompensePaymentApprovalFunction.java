package com.deppon.crm.module.recompense.server.manager.workflow.function;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.impl.BankProvinceCityManagerImpl;
import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.crm.module.common.shared.domain.AccountBranch;
import com.deppon.crm.module.common.shared.domain.BankCity;
import com.deppon.crm.module.common.shared.domain.BankProvince;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.server.manager.RecompenseValidator;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.OnlineApply;
import com.deppon.crm.module.recompense.shared.domain.Payment;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.exception.RecompenseException;
import com.deppon.crm.module.recompense.shared.exception.RecompenseExceptionType;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.shared.util.string.StringUtil;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;

/**
 * 
 * <p>
 * Description:提交理赔付款单<br />
 * </p>
 * 
 * @title SubmitRecompensePaymentApprovalFunction.java
 * @package com.deppon.crm.module.recompense.server.manager.workflow.function
 * @author roy
 * @version 0.1 2013-1-21
 */
public class SubmitRecompensePaymentApprovalFunction implements
		FunctionProvider {
	// 理赔service
	private RecompenseService recompenseService;
	// 理赔manager
	private RecompenseManager recompenseManager;
	// 注入部门service
	private IDepartmentService departmentService;
	private BankProvinceCityManagerImpl bankProvinceCityManager;

	/**
	 * @return departmentService : return the property departmentService.
	 */
	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	/**
	 * @param departmentService
	 *            : set the property departmentService.
	 */
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	private DecimalFormat df = new DecimalFormat("#.00");

	public RecompenseManager getRecompenseManager() {
		return recompenseManager;
	}

	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}

	public RecompenseService getRecompenseService() {
		return recompenseService;
	}

	public void setRecompenseService(RecompenseService recompenseService) {
		this.recompenseService = recompenseService;
	}

	@Override
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		RecompenseApplication app = (RecompenseApplication) transientVars
				.get(Constants.RECOMPENSE_APPLICATION);
		Payment payment = app.getPayment();
		app = recompenseService.getRecompenseApplicationById(app.getId());
		User user = (User) transientVars.get(Constants.RECOMPENSE_CURRENT_USER);
		String applyManCode = user.getEmpCode().getEmpCode();
		String[] paymentDetail = transPaymentDetail(app, applyManCode);
		String[] deptChargeDetail = transDeptChargeDetail(app);
		/*
		 * recompenseService.submitRecompensePaymentApproval(app, paymentDetail,
		 * deptChargeDetail);
		 */
		// 提交付款申请
		submitPayment(payment, app, user);

	}

	/**
	 * 
	 * <p>
	 * Description:封装数组<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param app
	 * @param applyManCode
	 * @return String[]
	 */
	private String[] transPaymentDetail(RecompenseApplication app,
			String applyManCode) {
		// 理赔类型，部门id(标杆编码)，客户id(客户编码)，金额，单号，创建人工号
		Department dept = recompenseManager.getDepartmentByDeptId(app
				.getReportDept());
		String reportDept = dept.getStandardCode();
		Department cashDept = recompenseManager.getDepartmentByDeptId(app
				.getCashierDept());
		String cashStandardCode=cashDept.getStandardCode();
		
		
		String recompenseType = "20";
		if (Constants.ONLINE_TYPE.equals(app.getRecompenseMethod())) {
			recompenseType = "30";
		}
		String customerNum = app.getWaybill().getLeaveCustomerId();
		if ("2".equals(app.getClaimParty())) {
			customerNum = app.getWaybill().getArriveCustomerId();
		}
		if (customerNum == null) {
			customerNum = "";
		}
		String realAmount = null;
		if (app.getRealAmount() != null) {
			realAmount = df.format(app.getRealAmount());
		}
		String waybillNum = app.getWaybill().getWaybillNumber();
		String[] paymentDetail = { recompenseType, cashStandardCode, customerNum,
				realAmount, waybillNum, applyManCode };
		return paymentDetail;
	}

	/**
	 * 
	 * <p>
	 * Description:封装入部门费用<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param app
	 * @return String[]
	 */
	private String[] transDeptChargeDetail(RecompenseApplication app) {
		// 单号，部门编号，承担金额
		String waybillNum = app.getWaybill().getWaybillNumber();
		List<DeptCharge> deptChargeList = app.getDeptChargeList();
		if (deptChargeList != null && deptChargeList.size() > 0) {
			String[] detail = new String[deptChargeList.size() * 3];
			for (int i = 0; i < deptChargeList.size(); i++) {
				DeptCharge deptCharge = deptChargeList.get(i);
				Department dept = recompenseManager
						.getDepartmentByDeptId(deptCharge.getDeptId());
				detail[i * 3 + 0] = waybillNum;
				detail[i * 3 + 1] = dept.getStandardCode();
				detail[i * 3 + 2] = df.format(deptCharge.getAmount());
			}
			return detail;
		} else {
			return new String[0];
		}
	}

	/**
	 * 
	 * <p>
	 * Description:提交付款申请<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-4
	 * @param payment
	 *            void
	 */
	public void submitPayment(Payment payment,
			RecompenseApplication recompense, User user) {

		List<Payment> casherPayments = recompenseService
				.queryPaymentByEmployeeNum(user.getEmpCode().getEmpCode());
		if (RecompenseValidator.checkRecompense(recompense, payment)
				&& RecompenseValidator.checkCasherPaymet(payment,
						casherPayments)) {
			// 根据当前登陆用户查询该收银员在费控中的账号列表
			// 当客户领款方式为汇款或者先冲帐后汇款时，校验当前提交的账号是否为收银员账号，如若匹配上抛出异常

			// 封装属性
			payment.setApplyTime(new Date());
			payment.setCreateTime(new Date());
			payment.setCreateUserId(user.getEmpCode().getId());
			payment.setPaymentStatus(Constants.STATUS_IN_PAYMENT);
			BankProvince bankProvice = null;
			BankCity bankCity = null;
			AccountBank accountBank = null;
			AccountBranch accountBranch = null;
			// 查询省市银行支行
			if (recompense.getRecompenseMethod().equals(Constants.ONLINE_TYPE)) {
				OnlineApply onlineApply = recompenseService
						.getOnlineApplyByRecompenseId(recompense.getId());
				//如果省市支行CODE为空就根据名字查询  否则根据code查询
				if(StringUtil.isEmpty(onlineApply.getBankCode())){
					accountBank = getBankProvinceCityManager()
							.getAccountBankByName(
									payment.getBank().getName().trim());
				}else{
					accountBank = getBankProvinceCityManager()
							.getAccountBankByCode(onlineApply.getBankCode());
				}
				if(StringUtil.isEmpty(onlineApply.getBranchCode())){
					accountBranch = getBankProvinceCityManager()
							.getAccountBranchByName(
									payment.getBranch().getName().trim());
				}else{
					accountBranch = getBankProvinceCityManager()
							.getAccountBranchByCode(onlineApply.getBranchCode());
				}
				if(StringUtil.isEmpty(onlineApply.getProvinceCode())){
					bankProvice = getBankProvinceCityManager()
							.getBankProvinceByName(
									payment.getBankProvice().getName().trim());
				}else{
					bankProvice = getBankProvinceCityManager()
							.getBankProvinceByCode(
									onlineApply.getProvinceCode());
				}
				if(StringUtil.isEmpty(onlineApply.getCityCode())){
					bankCity = getBankProvinceCityManager().getBankCityByName(
							payment.getBankCity().getName().trim());
				}else{
					bankCity = getBankProvinceCityManager().getBankCityByCode(
							onlineApply.getCityCode());
				}
				if (null == bankProvice || null == bankCity
						|| null == accountBank || null == accountBranch) {
					RecompenseException re = new RecompenseException(
							RecompenseExceptionType.ERRORCUSTOMBANKINFO);
					throw new GeneralException(re.getErrorCode(),
							re.getMessage(), re, new Object[] {}) {
					};
				}
			} else {
				bankProvice = getBankProvinceCityManager().getBankProvinceById(
						payment.getBankProvice().getId());
				bankCity = getBankProvinceCityManager().getBankCityById(
						payment.getBankCity().getId());
				accountBank = getBankProvinceCityManager().getAccountBankById(
						payment.getBank().getId());
				accountBranch = getBankProvinceCityManager()
						.getAccountBranchById(payment.getBranch().getId());
			}
			payment.setBankCity(bankCity);
			payment.setBankProvice(bankProvice);
			payment.setBank(accountBank);
			payment.setBranch(accountBranch);
			// 保存付款单
			Payment newPayment = recompenseService.savePayment(payment);
			// 在主表中更新当前理赔使用的付款单Id
			recompenseService.updatePaymentIdByRecompenseNum(
					recompense.getRecompenseNum(), newPayment.getId());
			// 转换为code 用于系统交换
			newPayment.setBank(accountBank);
			newPayment.setBranch(accountBranch);
			newPayment.setBankProvice(bankProvice);
			newPayment.setBankCity(bankCity);
			recompense.setPayment(newPayment);
			// 转换入部门信息
			List<DeptCharge> deptCharges = recompense.getDeptChargeList();
			List<DeptCharge> newDeptCharges = new ArrayList<DeptCharge>();
			for (int i = 0; i < deptCharges.size(); i++) {
				DeptCharge deptCharge = deptCharges.get(i);
				String deptCode = departmentService.getDepartmentById(
						deptCharge.getDeptId()).getStandardCode();
				deptCharge.setDeptCode(deptCode);
				newDeptCharges.add(deptCharge);
			}
			recompense.setDeptChargeList(newDeptCharges);

			Department dept = recompenseManager
					.getDepartmentByDeptId(recompense.getCashierDept());
			String cashierDept = dept.getStandardCode();
			recompense.setReportDept(cashierDept);
			if (recompense.getRecompenseType().equals(Constants.UNBILLED)) {
				// 当时未开单理赔想费控提交付款单

				recompenseService.submitPaymentToFIN(recompense, user);
			} else {

				boolean flag = recompenseService.submitPaymentToFOSS(
						recompense, user);
				if (!flag) {
					RecompenseException re = new RecompenseException(
							RecompenseExceptionType.SUBMITPAYMENTFAIL);
					throw new GeneralException(re.getErrorCode(),
							re.getMessage(), re, new Object[] {}) {
					};
				}
			}

		}

	}

	/**
	 * <p>
	 * Description:bankProvinceCityManager<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-24
	 */
	public BankProvinceCityManagerImpl getBankProvinceCityManager() {
		return bankProvinceCityManager;
	}

	/**
	 * <p>
	 * Description:bankProvinceCityManager<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-24
	 */
	public void setBankProvinceCityManager(
			BankProvinceCityManagerImpl bankProvinceCityManager) {
		this.bankProvinceCityManager = bankProvinceCityManager;
	}

}
