/**   
 * @title ContractValidate.java
 * @package com.deppon.crm.module.customer.server.manager
 * @description what to do
 * @author 潘光均
 * @update 2012-6-28 下午3:28:59
 * @version V1.0   
 */
package com.deppon.crm.module.customer.server.manager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.customer.server.utils.Assert;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ContractUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractDept;
import com.deppon.crm.module.customer.shared.domain.ContractHandleType;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;
import com.deppon.crm.module.customer.shared.domain.ContractTax;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;
import com.deppon.crm.module.organization.shared.domain.Department;

/**
 * @description 合同校验类
 * @author 潘光均
 * @version 0.1 2012-6-28
 * @date 2012-6-28
 */

public class ContractValidator {
	/**
	 * 
	 * @校验当前客户是否已存在有效的合同
	 * @author 赵斌
	 * @2012-5-4
	 * @return
	 */
	public static boolean canBeCreate(List<Contract> list) {
		Assert.notNull(list, "cutomer id must not null");
		for (Contract contract : list) {
			// 以前的业务在sql里面，现在需要添加新的业务规则。
			// 当合同有效(或者有合同处于审批中)并且合同没有到期
			if ((Constant.CONTRACT_STATUS_EFFECT.equals(contract
					.getContractStatus()) || Constant.CONTRACT_STATUS_INPROCESS
					.equals(contract.getContractStatus()))
					&& new Date().before(contract.getContractendDate())) {
				throw new ContractException(
						ContractExceptionType.ContractCustIsError);
			}
		}
		return true;
	}

	/**
	 * 
	 * @判断是否营业部、派送部、派送中心
	 * @author 赵斌
	 * @2012-4-26
	 * @return
	 */
	public static boolean isBusinessDept(Department dept) {
		if (null == dept) {
			throw new ContractException(ContractExceptionType.NotIsBusinessDept);
		}
		String deptName = dept.getDeptName();
		boolean b = deptName.indexOf("营业部") > 0;
		boolean d = deptName.indexOf("派送部") > 0;
		boolean c = deptName.indexOf("派送中心") > 0;
		boolean f = deptName.indexOf("试") > 0;
		if (b || d || c || f) {
			return true;
		} else {
			throw new ContractException(ContractExceptionType.NotIsBusinessDept);
		}
	}

	/**
	 * 
	 * @查看工作流审批状态 若还未审批则不能进行下一步操作
	 * @author 赵斌
	 * @2012-4-28
	 * @return
	 */
	public static boolean canOperate(Contract contract, String handleType) {
		if(null==contract){
			throw new ContractException(
					ContractExceptionType.CANNOTFINDCONTRACT);
			
		}
		if (ContractHandleType.UPDATE.getHandleType().equals(handleType)||
				ContractHandleType.CHANGESIGN.getHandleType().equals(handleType)) {
			validateStatusForUpdateOrChangSign(contract);
		} else {
			 validateStatusForOther(contract);
		}
		if (ContractHandleType.MODIFYMONTHSENDRATE.getHandleType().equals(handleType)) {
			checkPreferentialType(contract);
			checkWaitEffectContract(contract);
		}
		// 判断是否存在审批中的工作流.
		isExistWorkflow(contract,handleType);
		return true;
	}

	/**
	 * @description 校验优惠类型.如果不是月发月送则不允许修改  
	 * @author 潘光均
	 * @version 0.1 2013-3-15
	 * @param 
	 *@date 2013-3-15
	 * @return void
	 * @update 2013-3-15 下午3:04:02
	 */
	private static void checkPreferentialType(Contract contract) {
		if (!Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(contract
				.getPreferentialType())
				&& !Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE
						.equals(contract.getExPreferentialType())) {
			throw new ContractException(
					ContractExceptionType.NOTCANMODIFYMONTHSENDCONTRACT);
		}
	}

	/**
	 * @description 校验除改签和修改之外的合同状态是否符合.  
	 * @author 潘光均
	 * @version 0.1 2013-3-13
	 * @param 
	 *@date 2013-3-13
	 * @return void
	 * @update 2013-3-13 下午4:53:10
	 */
	private static void validateStatusForOther(Contract contract) {
		if (!Constant.CONTRACT_STATUS_EFFECT.equals(contract
				.getContractStatus())) {
			throw new ContractException(
					ContractExceptionType.ContractWaitEffectCannotOperate);
		}
	}

	/**
	 * @description 合同修改或者改签校验合同状态是否是生效或者无效
	 * @author 潘光均
	 * @version 0.1 2013-3-13
	 * @param 
	 *@date 2013-3-13
	 * @return void
	 * @update 2013-3-13 下午4:51:57
	 */
	private static void validateStatusForUpdateOrChangSign(Contract contract) {
		if (!Constant.CONTRACT_STATUS_EFFECT.equals(contract
				.getContractStatus())
				&& !Constant.CONTRACT_STATUS_UNEFFECT.equals(contract
						.getContractStatus())) {
			throw new ContractException(
					ContractExceptionType.ContractWaitEffectCannotOperate);
		}
	}

	/**
	 * @description 校验合同是否未待生效
	 * @author 潘光均
	 * @version 0.1 2013-3-13
	 * @param 
	 *@date 2013-3-13
	 * @return void
	 * @update 2013-3-13 下午4:47:29
	 */
	private static void checkWaitEffectContract(Contract contract) {
		if (isWaitEffectContract(contract)) {
			throw new ContractException(
					ContractExceptionType.ContractWaitEffectCannotOperate);
		}
	}

	/**
	 * @description 判断是否存在审批中的工作流.
	 * @author 潘光均
	 * @version 0.1 2012-9-7
	 * @param
	 * @date 2012-9-7
	 * @return void
	 * @update 2012-9-7 下午8:29:44
	 */
	private static boolean isExistWorkflow(Contract contract, String handleType) {
		List<ContractOperatorLog> list = contract.getContractWorkflowList();
		for (ContractOperatorLog dept : list) {
			String approvalState = dept.getApprovalState();
			if (Constant.CONTRACT_APPROVING.equals(approvalState)) {
				throw new ContractException(
						ContractExceptionType.ContractStateIsError);
			}
		}
		// 合同是审批中不能进行任何操作
		if(Constant.CONTRACT_STATUS_INPROCESS.equals(contract
				.getContractStatus())){
			throw new ContractException(
					ContractExceptionType.ContractStateIsError);
		}
		// 工作流退回的合同进行修改操作
		if ((ContractHandleType.UPDATE.getHandleType().equals(handleType)||
				ContractHandleType.CHANGESIGN.getHandleType().equals(handleType))
				&& Constant.CONTRACT_STATUS_UNEFFECT.equals(contract
						.getContractStatus())) {
			// 最新的合同工作流审批结果是不同意,工作流操作类型是 新签或修改 才可以执行操作
			ContractOperatorLog operatorLog = getNewestWorkflow(list);
			String conHandleType = operatorLog.getOperatorType();
			if (!Constant.CONTRACT_DISAGREE.equals(operatorLog
					.getApprovalState())) {
				throw new ContractException(
						ContractExceptionType.ContractCannotOperate);
			}
			if (!(ContractHandleType.INSERT.getHandleType().equals(conHandleType)
					|| ContractHandleType.UPDATE.getHandleType().equals(conHandleType)
					|| ContractHandleType.CHANGESIGN.getHandleType().equals(conHandleType))) {
				throw new ContractException(
						ContractExceptionType.ContractCannotOperate);
			}
		}
		return true;
	}
	// 最新的合同工作流审批结果是不同意,工作流操作类型是 新签或修改 才可以执行操作
	private static ContractOperatorLog getNewestWorkflow(List<ContractOperatorLog> list){
		ContractOperatorLog operatorLog = list.size() > 0 ? list.get(0) : new ContractOperatorLog();
		for (ContractOperatorLog log : list) {
			if (null==operatorLog.getModifyDate()) {
				operatorLog.setModifyDate(operatorLog.getCreateDate());
			}
			//操作日志最后更新时间为空，则把创建时间赋予更新时间
			if (null==log.getModifyDate()) {
				log.setModifyDate(log.getCreateDate());
			}
			if(operatorLog.getModifyDate().before(log.getModifyDate())){
				operatorLog = log;
			}
		}
		return operatorLog;
	}
	/**
	 * 
	 * @description 判断合同是否是待生效合同.
	 * @author 潘光均
	 * @version 0.1 2012-9-4
	 * @param
	 * @date 2012-9-4
	 * @return boolean
	 * @update 2012-9-4 下午3:07:21
	 */
	public static boolean isWaitEffectContract(Contract contract) {
		Assert.notNull(contract, "contract can't null ！");
		return Constant.CONTRACT_STATUS_EFFECT.equals(contract
				.getContractStatus())
				&& contract.getContractBeginDate().after(new Date());
	}

	/**
	 * @description 判断待生效合同是否可以进行操作
	 * @author 潘光均
	 * @version 0.1 2012-7-3
	 * @param
	 * @return
	 * @date 2012-7-3
	 * @return void
	 * @update 2012-7-3 上午11:23:02
	 */
	public static boolean isWaitEffectCanOperate(Contract contract, String handleType) {
		Assert.notNull(contract, "contract can't null ！");
		// 合同状态是有效，且生效时间在当前时间之后 即为待生效合同
		// 合同只能进行绑定和解绑操作
		if (!(handleType.equals(ContractHandleType.BINDING.getHandleType())
				|| handleType.equals(ContractHandleType.UNBINDING
						.getHandleType())
				|| handleType.equals(ContractHandleType.CHANGEBELONGDEPT
						.getHandleType()) || handleType
					.equals(ContractHandleType.OBSOLETE.getHandleType()))) {
			throw new ContractException(
					ContractExceptionType.ContractWaitEffectCannotOperate);
		}
		isExistWorkflow(contract,handleType);
		return true;
	}

	/**
	 * @description 判断一个部门是不是合同的绑定部门.
	 * @author 潘光均
	 * @version 0.1 2012-7-3
	 * @param
	 * @return
	 * @date 2012-7-3
	 * @return void
	 * @update 2012-7-3 上午11:23:02
	 */
	public static boolean isBondingDept(List<ContractDept> depts, String deptId,List<ContractDept> deptsTarget) {
		if (null != depts && 0 < depts.size()) {
			for (int i = 0; i < depts.size(); i++) {
				if (depts.get(i).getContractDept().getId().equals(deptId)
						&&depts.get(i).isState()) {
					//判断合同绑定部门版本是否已经改变，改变则抛出异常提示数据已经改变
					if(!(depts.get(i).getVersionNumber().equals(deptVersionNum(depts,deptId).getVersionNumber()))){
						throw new ContractException(ContractExceptionType.OwnerDeptIsError);
					}
					return true;
				}
			}
		}
		throw new ContractException(ContractExceptionType.DeptIsNotBondingDept);
	}
	public static ContractDept deptVersionNum(List<ContractDept> depts, String deptId) {
		if (null != depts && 0 < depts.size()) {
			for (int i = 0; i < depts.size(); i++) {
				if (depts.get(i).getContractDept().getId().equals(deptId)
						&&depts.get(i).isState()) {
					return depts.get(i);
				}
			}
		}
		return new ContractDept();
	}
	/**
	 * 
	 * @description 校验需要绑定的部门是否是合同的绑定部门.
	 * @author 潘光均
	 * @version 0.1 2012-7-5
	 * @param
	 * @date 2012-7-5
	 * @return void
	 * @update 2012-7-5 下午4:09:45
	 */
	public static void chackDeptHasBound(List<ContractDept> depts, String deptId) {
		if (null != depts && 0 < depts.size()) {
			for (int i = 0; i < depts.size(); i++) {
				if (depts.get(i).getContractDept().getId().equals(deptId)
						&&depts.get(i).isState()) {
					throw new ContractException(
							ContractExceptionType.DEPTHASHBOUNDED);
				}
			}
		}
	}
    /**
     * 
     * <p>
     * Description:验证合同到期日期必须为选择日期月份的最后一天
     * 验证合同的协议联系人是否为空
     * <br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-4-7
     * @param contract
     * void
     */
	public static void validateContractForm(Contract contract) {
		//合同到期时间,必须为选择日期月份的最后一天
			Date endDate = contract.getContractendDate();
			Date lastMonthDay = getLastMonthDay(endDate,true);
			if (!endDate.equals(lastMonthDay)) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				throw new ContractException(
						ContractExceptionType.MONTH_END_MUST_LASTDAY,
						new Object[] { format.format(endDate) });
			}
		//验证合同的协议联系人是否为空
		validateLinkManForContract(contract);
	}

	/**
	 * 
	 * <p>
	 * 得到月份最后一天<br />
	 * 
	 * </p>
	 * @param date 变更日期  
	 * isNow 若为true则返回本月最后一天的日期 
	 *       若为false则返回上月的最后一天的日期
	 * @author bxj
	 * @version 0.2 2012-8-4
	 * @return Date
	 */
	private static Date getLastMonthDay(Date date,boolean isNow) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if(isNow){
		   calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		}
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}

	/**
	 * @description 校验新旧合同起始时间.
	 * @author 潘光均
	 * @version 0.1 2012-8-9
	 * @param
	 * @date 2012-8-9
	 * @return void
	 * @update 2012-8-9 下午2:09:19
	 */
	public static void checkContractValidateTime(Date date, Date date2) {
		Assert.notNull(date, "new contract data must null !");
		Assert.notNull(date2, "old contract data must null !");
		if (date.before(date2)) {
			throw new ContractException(
					ContractExceptionType.NEWCONTRACT_BEGINDATE_MUST_AFTER_OLDCONTRACT);
		}
	}

	/**
	 * @description 校验合同否归属部门.
	 * @author 潘光均
	 * @version 0.1 2012-8-23
	 * @param handType 
	 * @param expressDepts 
	 * @param
	 * @date 2012-8-23
	 * @return void
	 * @update 2012-8-23 下午2:34:54
	 */
	public static void checkIsOwnerDept(Contract contract,
			String handType, List<String> expressDepts) {
		Assert.notNull(contract, "contract can't null !");
		Assert.notNull(contract.getDept(), "contract dept  can't null !");
		//当前登录人所在部门
		String deptId = ContextUtil.getCurrentUserDeptId();
		//当前登录部门的标杆编码
		String standardCode=ContextUtil.getCurrentUserDept().getStandardCode();
		
		String ownerDeptId = contract.getDept().getId();
		// 应收账款管理组修改所有合同月结天数
		if (ContractHandleType.UPDATEMONTHENDDAY.getHandleType().equals(
				handType)) {
			if (!standardCode.equals(Constant.DEBT_STARDCODE)) {
				throw new ContractException(ContractExceptionType.DeptIsError);
			}
			// 新签要判断是是点部对应的营业部，还是归属部门的新签
		}else if (ContractHandleType.INSERT.getHandleType().equals(handType)) {
			if (!expressDepts.contains(ownerDeptId)&&!deptId.equals(ownerDeptId)) {
				throw new ContractException(ContractExceptionType.DeptIsError);
			}
		}else{
		// 解绑部门的id为空或者解绑部门是归属部门则不让解绑
			if (deptId == null || !deptId.equals(ownerDeptId)) {
				throw new ContractException(ContractExceptionType.DeptIsError);
			}
		}
	}

	/**
	 * 
	 * <p>
	 * 检查新签操作的历史合同状态，看起是否能进行新签合同<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-9-4
	 * @param contracts
	 *            void
	 * @param member
	 */
	public static void validateCreateContract(List<Contract> contracts,
			String handleType) {
		if (contracts == null || contracts.size() == 0){
			return;
		}
		checkCustHasWaitEffectCont(handleType, contracts);
		for (Contract contract : contracts) {
			// 校验合同是否有未审批的工作流
			isExistWorkflow(contract, handleType);
		}
	}

	/**
	 * @description 校验合同中是否有审批中合同.  
	 * @author 潘光均
	 * @version 0.1 2012-10-16
	 * @param 
	 *@date 2012-10-16
	 * @return void
	 * @update 2012-10-16 下午4:55:26
	 */
	public static void checkCustHasWaitEffectCont(String handleType,
			List<Contract> contracts) {
		if (ContractHandleType.INSERT.getHandleType().equals(handleType)
				|| ContractHandleType.CHANGESIGN.getHandleType().equals(
						handleType)) {
			// 存在待生效的合同不让新签
			if (null != contracts && 0 < contracts.size()) {
				for (int i = 0; i < contracts.size(); i++) {
					Contract contract = contracts.get(i);
					if (isWaitEffectContract(contract)) {
						throw new ContractException(
								ContractExceptionType.HAS_WAIT_EFFECT_CONTRACT,
								new Object[] { contract.getContractNum() });
					}
				}
			}
		}
	}

	/**
	 * @description 校验合同修改数据完整性.
	 * @author 潘光均
	 * @version 0.1 2012-9-3
	 * @param Contract
	 * @date 2012-9-3
	 * @return boolean
	 * @update 2012-9-3 下午3:26:44
	 */
	public static boolean checkContratUpdateComplete(Contract contract) {
		// 优惠折扣有不为空的时候
		//代收货款费率必须大于0小于等于1
		if (0>=contract.getExPreferential().getAgentgathRate()||1<contract.getExPreferential().getAgentgathRate()) {
			return false;
		}
		//运费折扣率必须大于0小于等于1
		if (0>=contract.getExPreferential().getChargeRebate()||1<contract.getExPreferential().getChargeRebate()) {
			return false;
		}
		//报价费率必须大于0小于等于1
		if (0>=contract.getExPreferential().getInsuredPriceRate()||1<contract.getExPreferential().getInsuredPriceRate()) {
			return false;
		}
		
		if (0>=contract.getPreferential().getAgentgathRate()||1<contract.getPreferential().getAgentgathRate()) {
			return false;
		}
		//运费折扣率必须大于0小于等于1
		if (0>=contract.getPreferential().getChargeRebate()||1<contract.getPreferential().getChargeRebate()) {
			return false;
		}
		//报价费率必须大于0小于等于1
		if (0>=contract.getPreferential().getInsuredPriceRate()||1<contract.getPreferential().getInsuredPriceRate()) {
			return false;
		}
//		接货费不为空的时候-----接货费率必须大于=0小于等于99999.999 
		if (contract.getPreferential().getReceivePriceRate() != null&&
				(0>contract.getPreferential().getReceivePriceRate()||99999.999<contract.getPreferential().getReceivePriceRate())) {
			return false;
		}
		//报价费率必须大于0小于等于1
		if (0>=contract.getPreferential().getDeliveryPriceRate()||1<contract.getPreferential().getDeliveryPriceRate()) {
			return false;
		}
		if (Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND.equals(contract.getPreferentialType())) {
			if (Constant.CONTRACT_PREFERENTIALTYPE_PRICE_REBATE.equals(contract
					.getExPreferentialType())
					&& null == contract.getExPreferential().getChargeRebate()) {
				return false;
			}
		}
		if (Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE.equals(contract
				.getExPreferentialType())) {
			if (Constant.CONTRACT_PREFERENTIALTYPE_PRICE_REBATE.equals(contract
					.getPreferentialType())
					&& null == contract.getPreferential().getChargeRebate()
					&& null == contract.getPreferential().getAgentgathRate()
					&& null == contract.getPreferential().getInsuredPriceRate()) {
				return false;
			}
		}
		if (Constant.CONTRACT_PAYWAY_NOT_MONTH_END.equals(contract.getPayWay())
				&& Constant.CONTRACT_PAYWAY_NOT_MONTH_END.equals(contract
						.getExPayWay())
				&& Constant.CONTRACT_PREFERENTIALTYPE_NOT_PREFERENTIAL
						.equals(contract.getPreferentialType())
				&& Constant.CONTRACT_PREFERENTIALTYPE_NOT_PREFERENTIAL
						.equals(contract.getExPreferentialType())) {
			return false;
		}
		if (contract.getPayWay() == null
				&& contract.getPreferentialType() == null
				&& contract.getExPayWay() == null
				&& contract.getExPreferentialType() == null){
			return false;
		}
		if (contract.getPayWay() == null
				&& contract.getPreferentialType() == null
				&& Constant.CONTRACT_PAYWAY_NOT_MONTH_END.equals(contract
						.getExPayWay())
				&& Constant.CONTRACT_PREFERENTIALTYPE_NOT_PREFERENTIAL
						.equals(contract.getExPreferentialType())){
			return false;
		}
		if (Constant.CONTRACT_PAYWAY_NOT_MONTH_END.equals(contract.getPayWay())
				&& Constant.CONTRACT_PAYWAY_NOT_MONTH_END.equals(contract
						.getExPayWay()) && contract.getExPayWay() == null
				&& contract.getExPreferentialType() == null){
			return false;
		}
		return true;
	}

	/**
	 * @description 校验合同是否可以删除.
	 * @author 潘光均
	 * @version 0.1 2012-9-4
	 * @param
	 * @date 2012-9-4
	 * @return void
	 * @update 2012-9-4 下午3:59:01
	 */
	public static boolean canDelete(Contract contract) {
		//校验非归属部门不能操作
		isBelongDept(contract);
		// 合同状态不是有效时，不能进行操作
		if (!Constant.CONTRACT_STATUS_UNEFFECT.equals(contract
				.getContractStatus())) {
			throw new ContractException(
					ContractExceptionType.onlyCanDeleteUneffectContract);
		}
		List<ContractOperatorLog> list = contract.getContractWorkflowList();
		ContractOperatorLog log = new ContractOperatorLog();
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);    //得到下一个月
		Date minDate=new Date(cal.getTimeInMillis());
		for (ContractOperatorLog oplog : list) {
			/**
			 * update
			 * @description: 如果无效合同有审批中的工作流则不让删除
			 * @author 潘光均
			 * @date 2012-10-25
			 */
			if (Constant.CONTRACT_APPROVING.equals(oplog.getApprovalState())) {
				throw new ContractException(
						ContractExceptionType.ContractStateIsError);
			}
			if (minDate.after(oplog.getCreateDate())) {
				minDate = oplog.getCreateDate();
				log = oplog;
			}
		}
		/**
		 * @INSERT
		 * @description 如果是迁移的数据开始是没有工作流信息的，如果对该合同进行一次修改未同意，再对这个合同进行一次修改，
		 *              那么这个客户的第一条工作了信息就是不同意,所以删除只能删除上线以后产生的数据
		 * @author 潘光均
		 * @date 2012-10-25
		 */
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.YEAR, 2012);
		ca.set(Calendar.MONTH, 6);
		ca.set(Calendar.DATE, 28);
		ca.set(Calendar.HOUR, 8);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		if (ca.getTime().after(contract.getCreateDate())) {
			throw new ContractException(
					ContractExceptionType.ContractStateIsError);
		}
		//合同状态为无效，第一条操作日志：操作类型（insert：新签，changeSign：新签，update修改）
		//且审批结果是不同意 该合同可以删除
		if (!(Constant.CONTRACT_DISAGREE.equals(log.getApprovalState()) 
				&& (ContractHandleType.INSERT.getHandleType().equals(log.getOperatorType())
				|| ContractHandleType.CHANGESIGN.getHandleType().equals(log.getOperatorType()) 
				|| ContractHandleType.UPDATE.getHandleType().equals(log.getOperatorType())))){
			throw new ContractException(ContractExceptionType.onlyCanDeleteUneffectContract);
		}
		return true;
	}

	/**
	 * 
	 * @description 校验合同是否满足创建的条件.
	 * @author 潘光均
	 * @version 0.1 2012-6-28
	 * @param
	 * @date 2012-6-28
	 * @return void
	 * @update 2012-6-28 下午3:46:42
	 */
	public static void validateCreateContract(Contract contract,List<Contract> contracts,List<Double> moneyList) {
		//合同时间进行校验
		cheackContractDate(contract);
		
		// 优惠类型为月发月送时，合同到期时间
		validateContractForm(contract);

		// 校验当前客户是否存在有效合同
		canBeCreate(contracts);

		// 校验部门是不是营业部、派送部、派送中心
		isBusinessDept(contract.getDept());
		//校验用户近3月发货金额
		checkAllowCreateContract_DeliverMoney(contract,moneyList);
		
		//验证合同的协议联系人是否为空
		validateLinkManForContract(contract);
	}
	
	/***
	 * 
	 * <p>
	 * Description:验证客户发货金额<br />
	 * 客户最近3月没有过货或者最近连续2月发货金额超过3000 
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-13
	 * @param contract
	 * @return
	 * boolean 当且仅当用户最近3月发货金额为0或者最近3月中连续2月发货金额超过3000 返回true
	 *      否则抛出不满足创建月结合同条件
	 */
	private static boolean checkAllowCreateContract_DeliverMoney(Contract contract,List<Double> moneyList) {
		
		// 如果月结合同  则用户三月发货金额全部为零或则是有两月连续超过3000元
		if (Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract.getPayWay())) {
			if ((moneyList.get(0)==0&&moneyList.get(1)==0&&moneyList.get(2)==0)
				||(moneyList.get(1) >= 3000d
				   &&(moneyList.get(0)>=3000d
				     ||moneyList.get(2)>=3000d))	
					) {// 新客户或者是连续两月发货金额超过3000 允许签订 
				return true;
			}
			// 客户不符合创建月结合同的条件
			throw new ContractException(ContractExceptionType.CUSTOMER_NOT_ALLOW_CREATE_CONTRRACT);
			}
		return true;
	}

	/**
	 * @description 合同更新业务逻辑校验.
	 * @author 潘光均
	 * @version 0.1 2012-9-13
	 * @param
	 * @date 2012-9-13
	 * @return void
	 * @update 2012-9-13 上午9:51:58
	 */
	public static void validataContractUpdate(Contract contract, Contract databaseCon) {
		// 校验合同是否可以操作--合同状态是否满足，
		canOperate(databaseCon, ContractHandleType.UPDATE.getHandleType());
		// 合同信息修改包括修改优惠折扣和修改结算限额，不能同时为空
		if(!checkContratUpdateComplete(contract)||!isContractChange(contract,databaseCon)){
			throw new ContractException(ContractExceptionType.CONTRACT_INFO_INCOMPLETE);
		}
		// 添加税务信息后 修改 附件校验
		if(null == contract.getContractTaxList() || contract.getContractTaxList().size()!=1){
			throw new ContractException(ContractExceptionType.CONTRACT_DATA_ERROR);
		}
		if(null == contract.getContractTaxList().get(0).getInvoiceType()
				||"".equals(contract.getContractTaxList().get(0).getInvoiceType())){
			throw new ContractException(ContractExceptionType.CONTRACT_CONTRACTTAX_NULL);
		}
		// 如果税务信息为11%运输专票 那么附件有三个 否则为两个
		if(contract.getContractTaxList().get(0).getInvoiceType().equals("INVOICE_TYPE_01")){
			if(3 > contract.getFileInfoList().size()){
				throw new ContractException(ContractExceptionType.CONTRACT_FILE_IS_INCOMPLETE);
			}
		} 
		else if(2 > contract.getFileInfoList().size()){
			throw new ContractException(ContractExceptionType.CONTRACT_FILE_IS_INCOMPLETE);
		}
	}
	/**
	 * 
	* @Title: isContractTaxChange
	* @Description: 判断税务信息是否修改（true 说明修改了）
	* @author chenaichun 
	* @param @param contract
	* @param @param databaseCon
	* @param @return    设定文件
	* @date 2013-11-29 下午8:41:22
	* @return boolean    返回类型
	* @throws
	* @update 2013-11-29 下午8:41:22
	 */
	public static boolean isContractTaxChange(Contract contract, Contract databaseCon){
		
		if(ValidateUtil.objectIsEmpty(contract)){
			throw new ContractException(ContractExceptionType.CONTRACT_DATA_ERROR);
		}
		if(ValidateUtil.objectIsEmpty(databaseCon)){
			throw new ContractException(ContractExceptionType.CONTRACT_DATA_ERROR);
		}
		else{
			ContractTax oldContractTax = ContractUtil.changeListToContractTax(databaseCon);
			ContractTax newerContractTax = ContractUtil.changeListToContractTax(contract);
			if(ValidateUtil.objectIsEmpty(oldContractTax)
					||ValidateUtil.objectIsEmpty(newerContractTax)){
				throw new ContractException(ContractExceptionType.CONTRACT_CONTRACTTAX_NULL);
			}
			if(!ContractUtil.compare(oldContractTax.getSignCompany(),
					newerContractTax.getSignCompany())){
				return true;
			}
			if(!ContractUtil.compare(oldContractTax.getInvoiceType(),
					newerContractTax.getInvoiceType())){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	* @Title: isUpdContractTaxChange
	* @Description: 判断发票标记（01-》02 ，02-》01）是否修改
	* @author chenaichun 
	* @param @param contract
	* @param @param databaseCon
	* @param @return    设定文件
	* @date 2013-12-16 下午4:48:37
	* @return boolean    返回类型
	* @throws
	* @update 2013-12-16 下午4:48:37
	 */
   public static boolean isUpdContractTaxChange(Contract contract, Contract databaseCon){
		
		if(ValidateUtil.objectIsEmpty(contract)){
			throw new ContractException(ContractExceptionType.CONTRACT_DATA_ERROR);
		}
		if(ValidateUtil.objectIsEmpty(databaseCon)){
			throw new ContractException(ContractExceptionType.CONTRACT_DATA_ERROR);
		}
		else{
			ContractTax oldContractTax = ContractUtil.changeListToContractTax(databaseCon);
			ContractTax newerContractTax = ContractUtil.changeListToContractTax(contract);
			if(ValidateUtil.objectIsEmpty(oldContractTax)
					||ValidateUtil.objectIsEmpty(newerContractTax)){
				throw new ContractException(ContractExceptionType.CONTRACT_CONTRACTTAX_NULL);
			}
			if(!ContractUtil.compare(oldContractTax.getInvoiceType(),
					newerContractTax.getInvoiceType())){
				return true;
			}
		}
		
		return false;
	}
	/**
	 * @description 判断合同数据是否未修改.  
	 * @author 潘光均
	 * @version 0.1 2012-10-18
	 * @param 
	 *@date 2012-10-18
	 * @return boolean
	 * @update 2012-10-18 下午1:15:36
	 */
	private static boolean isContractChange(Contract contract, Contract databaseCon) {
		if (ValidateUtil.objectIsEmpty(contract) || ValidateUtil.objectIsEmpty(databaseCon)) {
			return false;
		}
		if (!ContractUtil.compare(contract.getPayWay(),databaseCon.getPayWay())) {
			return true;
		}
		if (null != contract.getArrearaMount()
				&& !contract.getArrearaMount().equals(
						databaseCon.getArrearaMount())) {
			return true;
		}
		
		if (!ContractUtil.compare(contract.getPreferentialType(),
				databaseCon.getPreferentialType())) {
			return true;
		}
		/**
		 * @description:增加快递修改条款
		 * @author pgj
		 * @date 2013-09-25
		 */
		if (!ContractUtil.compare(contract.getExPreferentialType(),
				databaseCon.getExPreferentialType())) {
			return true;
		}
		
		return !contract.getPreferential().equals(databaseCon.getPreferential())
				||!contract.getExPreferential().equals(databaseCon.getExPreferential())
				||isContractTaxChange(contract,databaseCon);
	}

	/**
	 * @description 校验当前登录部门是否是合同归属部门.
	 * @author 潘光均
	 * @version 0.1 2012-9-13
	 * @param
	 * @date 2012-9-13
	 * @return void
	 * @update 2012-9-13 上午9:59:02
	 */
	public static void isBelongDept(Contract contract) {
		// 得到合用所属部门Id
		String contractDeptId = contract.getDept().getId();
		// 得到当前用户所属部门Id
		String deptId = ContextUtil.getCurrentUserDeptId();
		// 判断用户身份，若当前用户部门为此合同归属部门
		if (!deptId.equals(contractDeptId)) {
			throw new ContractException(ContractExceptionType.DeptIsError);
		}
	}
	/**
	 * 
	 * @description 校验合同的起止生效时间是否正确.
	 * @author 潘光均
	 * @version 0.1 2012-9-13
	 * @param Contract
	 * @date 2012-9-13
	 * @return void
	 * @update 2012-9-13 下午1:49:16
	 */
	public static void cheackContractDate(Contract contract) {

		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.HOUR, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.MILLISECOND, 0);
		//起始生效时间不能晚于结束时间
		if (contract.getContractBeginDate()
				.after(contract.getContractendDate())) {
			throw new ContractException(
					ContractExceptionType.CREATE_DATA_CANNOT_BEFORE_ENDDATE);
		}
	}

	/**
	 * @description 校验客户是否有审批中的合同.  
	 * @author 潘光均
	 * @version 0.1 2012-10-11
	 * @param 
	 *@date 2012-10-11
	 * @return void
	 * @update 2012-10-11 下午6:30:35
	 */
	public static void checkCustHasInproContract(List<Contract> contracts) {
		if (null!=contracts&&0<contracts.size()) {
			for (int i = 0; i < contracts.size(); i++) {
				if (Constant.CONTRACT_STATUS_INPROCESS.equals(contracts.get(i).getContractStatus())) {
					throw new ContractException(ContractExceptionType.CUSTOMER_HAS_INPROCESS_CONTRACT);
				}
			}
		}
	}
	/**
	 * 
	* @Title: checkContactOnlyOneTax
	* @Description: 校验税务信息只有一条
	* @author chenaichun 
	* @param @param contractTaxList    设定文件
	* @date 2013-11-26 上午10:19:45
	* @return void    返回类型
	* @throws
	* @update 2013-11-26 上午10:19:45
	 */
	public static void checkContactOnlyOneTax(List<ContractTax> contractTaxList){
		if(null == contractTaxList ||contractTaxList.size() != 1){
			throw new ContractException(ContractExceptionType.CONTRACT_CONTRACTTAX_NULL);
		}
		if(null == contractTaxList.get(0).getSignCompany() || null == contractTaxList.get(0).getInvoiceType()){
			throw new ContractException(ContractExceptionType.CONTRACT_CONTRACTTAX_NULL);
		}
	}
	/**
	 * <p>
	 * Description:检验当前时间是否在晚上八点到凌晨的八点<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-8
	 * @return
	 * boolean
	 */
	public static boolean checkCurrentTime(){
		boolean flag = false;
		Calendar calendar = Calendar.getInstance();
		int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
		//检验当前时间是否在晚上八点到凌晨的八点
		if ( (20 <= nowHour && nowHour < 24) || (0 <= nowHour &&
				nowHour <= 8)) {
			flag = true;
		}
		return flag;
	}
	/**
	 * <p>
	 * Description:检验是否能修改合同月结天数<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-9
	 * @param contract
	 * void
	 */
	public static void checkContractCanOperateDebtDays(Contract contract) {
		if (ValidateUtil.objectIsEmpty(contract)) {
			throw new ContractException(ContractExceptionType.ContractIsNull);
		}
		
		//无效的和审批中不能修改
		if (Constant.CONTRACT_STATUS_UNEFFECT.equals(contract.getContractStatus()) ||
				Constant.CONTRACT_STATUS_INPROCESS.equals(contract.getContractStatus())	) {
			throw new ContractException(ContractExceptionType.ContractCannotOperate);
		}
		//不是月结合同不让修改
		if (!Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract.getPayWay())
				&& !Constant.CONTRACT_PAYWAY_NOT_MONTH.equals(contract
						.getExPayWay())) {
			throw new ContractException(ContractExceptionType.ContractIsNotMonthEnd);
		}
	}
	/**
	 * <p>
	 * Description:返回当前时间--至最早账龄日期的 差值 （天数）<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-9
	 * @return
	 * boolean
	 */
	public static long returnDebtDayDifference(Date longestDebtDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(longestDebtDate);
		long debtDateTimeMillis = calendar.getTimeInMillis();
		long now = System.currentTimeMillis();
		long minus = (now - debtDateTimeMillis)/(1000*60*60*24);
		if (minus >= 0 && minus < 1) {
			minus = 1;
		}
		return minus;
	}
	
	/**
	 * <p>
	 * Description:检验当前欠款天数 是否 大于 合同的月结天数<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-9
	 * @return
	 * boolean
	 */
	public static boolean checkDebtDays(long debtDays, int monthEndDay) {
		boolean flag = false;
		if (debtDays > monthEndDay) {
			flag = true;
		}
		return flag;
	}

	/**
	 * @description 校验合同是否可以修改运费折扣.  
	 * @author 潘光均
	 * @version 0.1 2013-3-13
	 * @param 
	 *@date 2013-3-13
	 * @return void
	 * @update 2013-3-13 下午1:57:59
	 */
	public static void validataModifyMonthSendRate(Contract contract) {
		if (null == contract) {
			throw new ContractException(ContractExceptionType.Data_Error);
		}
		if (!Constant.CONTRACT_STATUS_EFFECT.equals(contract.getContractStatus())) {
			throw new ContractException(ContractExceptionType.ContractCannotOperate);
		}
		isExistWorkflow(contract, ContractHandleType.MODIFYMONTHSENDRATE.getHandleType());
	}

	/**
	 * 
	 * <p>
	 * Description:验证合同起始日期<br />
	 * 前置条件是 原合同是有效的 才会调用此合同信息
	 *  如果新合同起始日期小于原合同的失效日期 ，且新合同的失效日期和原合同的失效日期不相同，则返回原合同
	 *  否则返回null
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-13
	 * @param oldContract
	 * @param contract
	 * void
	 */
	public static Contract checkAllowCreateContract_Date(List<Contract> contractList,
			Date contractBeginDate, Date contractendDate ) {
		Assert.notNull(contractBeginDate, "合同生效日期不能为空!");
		Assert.notNull(contractendDate, "合同失效日期不能为空!");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (Contract oldContract : contractList) {
			// 转换日期为年月日格式，去除时间
			try {
				if (null != oldContract.getContractendDate()) {
					oldContract.setContractendDate(format.parse(format
							.format(oldContract.getContractendDate())));
				}
				contractendDate = format.parse(format.format(contractendDate));
			} catch (Exception e) {
			}// 日期格式错误，不做处理

			if (Constant.CONTRACT_STATUS_EFFECT.equals(oldContract
					.getContractStatus())
					&& oldContract.getContractBeginDate().before(new Date())
					&& contractBeginDate.before(oldContract
							.getContractendDate())
				   ) {
			
				if(getLastMonthDay(oldContract.getContractendDate(),true).equals(oldContract.getContractendDate())){
					if(!oldContract.getContractendDate().equals(contractendDate)){
						return oldContract; 
					}
			    //// 如果合同有效且合同结束时间不是月份最后一天的 将合同失效时间该为上月最后一天
				}else if(!getLastMonthDay(oldContract.getContractendDate(), false).equals(contractendDate)){
		            return oldContract;
				}	
				
			}
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:请求合同失效日期<br />
	 * 前置条件是 原合同是有效的 才会调用此合同信息
	 *  如果新合同起始日期小于原合同的失效日期 ，
	 *     则新合同的失效日期必须为原合同的失效日期（为月份最后一天）
	 *      或原合同失效日期上月的最后一天（原合同失效日期不是月份最后一天）
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-13
	 * @param oldContract
	 * @param contract
	 * void
	 */
	 public static Contract checkAllowAcquireCreateDate(List<Contract> contractList,
			Date contractBeginDate, Date contractendDate) {
		Assert.notNull(contractBeginDate, "合同生效日期不能为空!");
		Assert.notNull(contractendDate, "合同失效日期不能为空!");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (Contract oldContract : contractList) {
			// 转换日期为年月日格式，去除时间
			try {
				if (null != oldContract.getContractendDate()) {
					oldContract.setContractendDate(format.parse(format
							.format(oldContract.getContractendDate())));
				}
				contractendDate = format.parse(format.format(contractendDate));
			} catch (Exception e) {
			}// 日期格式错误，不做处理

			if (Constant.CONTRACT_STATUS_EFFECT.equals(oldContract
					.getContractStatus())
					&& oldContract.getContractBeginDate().before(new Date())
					&& contractBeginDate.before(oldContract
							.getContractendDate())
				    ) {
                     
				if(getLastMonthDay(oldContract.getContractendDate(),true).equals(oldContract.getContractendDate())){
					if(!oldContract.getContractendDate().equals(contractendDate)){
						return oldContract; 
					}
				}else{// 如果合同有效且合同结束时间不是月份最后一天的 将合同失效时间该为上月最后一天
					oldContract.setContractendDate(getLastMonthDay(
							oldContract.getContractendDate(), false));
		            return oldContract;
				}	
			}
		}
		return null;
	}

	
	

	/**
	 * <p>
	 * Description:校验当前操作人的子公司 跟合同的子公司是否 相同,或者 合同的子公司是德邦股份有限公司......<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-3-13
	 * @param contract 合同
	 * @return
	 * boolean
	 */
	public static void checkContractSubject(Contract contract,String employeeSubjectName,ContractHandleType handleType) {
		ContractExceptionType reason = null;
		if (ValidateUtil.objectIsEmpty(contract)) {
			//合同为空
			reason = ContractExceptionType.ContractDataError;
		} else if (StringUtils.isEmpty(contract.getContractSubject())) {
			//子公司为空
			reason = ContractExceptionType.CONTRACTSUBJECT_IS_NULL;
		} else if (Constant.DEPPON_COMPANY.equals(contract.getContractSubject())) {
			//合同的所属子公司为德邦股份有限公司  可以全国任意绑定
			reason = null;
		} else if (!employeeSubjectName.equals(contract.getContractSubject())) {
			if (Constant.BOUNDING_CONTRACT.equals(handleType.getHandleType())) {
				//不同子公司不能绑定
				reason =ContractExceptionType.CONTRACTSUBJECTS_IS_DIFFERENTCOMPANIES;
			}
			//不同子公司不能归属部门变跟
			if (Constant.CHANGEBELONGDEPT_CONTRACT.equals(handleType.getHandleType())) {
				reason = ContractExceptionType.CONTRACTSUBJECTS_IS_DIFFERENTCOMPANIES_FOR_CHANGEDEPT;
			}
		}
		if (!ValidateUtil.objectIsEmpty(reason)) {
			throw new ContractException(reason);
		}
	}
	/**
	 * 
	 * @Title: validateModifyDunningDept
	 *  <p>
	 * @Description: 校验合同是否有效、催款部门已经修改
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-5-9
	 * @return boolean
	 * @throws
	 */
	public static boolean validateModifyDunningDept(Contract contract,Boolean ifForeignGoods,String dunningDeptCode){
		if (!contract.getContractStatus().equals(Constant.CONTRACT_STATUS_EFFECT)) {
			throw new ContractException(ContractExceptionType.ContractCannotOperate);
		}
		
		if ((!contract.getIfForeignGoods()&&!ifForeignGoods)
				||(contract.getIfForeignGoods()&&ifForeignGoods
						&&contract.getDunningDeptCode().equals(dunningDeptCode))) {
			throw new ContractException(ContractExceptionType.NO_CHANGE_ERROR);
		}
		return true;
	}
	
	/**
	 * @Description:验证合同的协议联系人是否为空<br />
	 * @author CoCo
	 * @version 0.1 2013-6-14
	 * @param contract
	 *  void
	 */
	public static void validateLinkManForContract(Contract contract) {
		ContractExceptionType reason = null;
		if (ValidateUtil.objectIsEmpty(contract)) {
			//合同为空
			reason = ContractExceptionType.Data_Error;
		}else if (StringUtils.isEmpty(contract.getContactId())) {
			//协议联系人ID为空
			reason = ContractExceptionType.CUSTLINKMAN_IS_NULL_FOR_CONTRACT;
		}else if (StringUtils.isEmpty(contract.getLinkManName())) {
			//协议联系人名字为空
			reason = ContractExceptionType.CUSTLINKMAN_IS_NULL_FOR_CONTRACT;
		}else if (StringUtils.isEmpty(contract.getLinkManMobile()) 
				&& StringUtils.isEmpty(contract.getLinkManPhone())) {
			//协议联系人的手机并且号码为空
			reason = ContractExceptionType.CUSTLINKMAN_IS_NULL_FOR_CONTRACT;
		}
		if (!ValidateUtil.objectIsEmpty(reason)) {
			throw new ContractException(reason);
		}
	}

	/**
	 * <p>
	 * Description:是否点部对应营业部<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-27
	 * @param authDept
	 * @param id
	 * void
	 */
	public static void isExpressAuthDept(List<String> authDept, String id) {
		if (!authDept.contains(id)) {
			throw new ContractException(ContractExceptionType.DeptIsError);
		}
	}

	/**
	 * <p>
	 * Description:合同中是否只有零担信息<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-27
	 * @param contract
	 * void
	 */
	public static void checkPointManagerSignLtt(Contract contract) {
		if (null == contract.getExPayWay()
				&& null == contract.getExPreferentialType()
				&& null==contract.getExPreferential()) {
			throw new ContractException(ContractExceptionType.CANNOTONLYSIGNLTT);
		}
	}
}
