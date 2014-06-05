/**
 * @description
 * @author 赵斌
 * @2012-3-28
 * @return
 */
package com.deppon.crm.module.customer.server.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.deppon.crm.module.customer.server.dao.IAlterMemberDao;
import com.deppon.crm.module.customer.server.dao.IContractDao;
import com.deppon.crm.module.customer.server.service.IContractService;
import com.deppon.crm.module.customer.server.utils.Assert;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ContractUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractCondition;
import com.deppon.crm.module.customer.shared.domain.ContractDebtDay;
import com.deppon.crm.module.customer.shared.domain.ContractDept;
import com.deppon.crm.module.customer.shared.domain.ContractDto;
import com.deppon.crm.module.customer.shared.domain.ContractMonthendDay;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;
import com.deppon.crm.module.customer.shared.domain.ContractTax;
import com.deppon.crm.module.customer.shared.domain.CustMonthSum;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.Preferential;

/**
 * @author 赵斌
 * 
 */
public class ContractService implements IContractService {

	public IContractDao contractDao;

	public IContractDao getContractDao() {
		return contractDao;
	}

	public void setContractDao(IContractDao contractDao) {
		this.contractDao = contractDao;
	}

	public IAlterMemberDao alterMemberDao;

	public IAlterMemberDao getAlterMemberDao() {
		return alterMemberDao;
	}

	public void setAlterMemberDao(IAlterMemberDao alterMemberDao) {
		this.alterMemberDao = alterMemberDao;
	}

	/**
	 * 
	 * @合同创建
	 * @author 赵斌
	 * @2012-3-28
	 * @return Contract
	 * @param contract
	 *            合同
	 */
	@Override
	public Contract createContract(Contract contract) {
		return contractDao.createContract(contract);
	}

	/**
	 * @合同对应部门创建
	 * @author 赵斌
	 * @2012-3-30
	 * @return
	 */
	@Override
	public ContractDept createContractDept(ContractDept contractDept) {
		return contractDao.createContractDept(contractDept);
	}

	/**
	* <p>Title: createContractTax</p>
	* <p>Description: 合同税务标记创建
	* @author chenaichun 
	* @param contractTax
	* @return
	* @see com.deppon.crm.module.customer.server.service.IContractService#createContractTax(com.deppon.crm.module.customer.shared.domain.ContractTax)
	*/
	@Override
	public boolean createContractTax(ContractTax contractTax) {
		
		return contractDao.createContractTax(contractTax);
	}

	
	

	/**(非 Javadoc)
	* <p>Title: updateContractTax</p>
	* <p>Description: </p>
	* @author chenaichun 
	* @param contractTax
	* @return
	* @see com.deppon.crm.module.customer.server.service.IContractService#updateContractTax(com.deppon.crm.module.customer.shared.domain.ContractTax)
	*/
	
	
	@Override
	public boolean updateContractTax(ContractTax contractTax) {
		// TODO Auto-generated method stub
		return contractDao.updateContractTax(contractTax);
	}

	
	

	/**(非 Javadoc)
	* <p>Title: deleteContractTax</p>
	* <p>Description: </p>
	* @author chenaichun 
	* @param id
	* @return
	* @see com.deppon.crm.module.customer.server.service.IContractService#deleteContractTax(java.lang.String)
	*/
	@Override
	public boolean deleteContractTax(String id) {
		return contractDao.deleteContractTax(id);
	}

	/**
	 * @合同优惠信息创建
	 * @author 赵斌
	 * @2012-4-10
	 * @return
	 */
	@Override
	public Preferential createPreferential(Preferential preferential) {
		return contractDao.createPreferential(preferential);
	}
	/**
	 * @description
	 * @author 赵斌
	 * @2012-4-16
	 * @return
	 */
	@Override
	public Contract getContract(String id) {
		return contractDao.getContract(id);
	}


	/**
	 * @description
	 * @author 赵斌
	 * @2012-4-17
	 * @return
	 */
	@Override
	public ContractDept getContractDeptByDeptId(String contractId, String deptId) {
		return contractDao.getContractDeptByDeptId(contractId, deptId);
	}



	/**
	 * 
	 * @修改合同部门
	 * @author 赵斌
	 * @2012-4-17
	 * @param ContractDept
	 *            合同部门
	 * @return boolean
	 */
	@Override
	public boolean updateContractDept(ContractDept contractDept) {
		return contractDao.updateContractDept(contractDept);
	}

	/**
	 * 
	 * @修改合同信息
	 * @author 赵斌
	 * @2012-4-12
	 * @param Contract
	 *            合同
	 * @return boolean
	 */
	@Override
	public boolean updateContract(Contract contract) {
		//更新合同零担折扣
		if (null!=contract && null != contract.getPreferential()) {
			Preferential preferential = contract.getPreferential();
			preferential.setModifyDate(new Date());
			preferential.setModifyUser(ContextUtil.getCreateOrModifyUserId());
			preferential.setEndTime(contract.getContractendDate());
			contractDao.updatePreferential(preferential);
		}
		//更新合同快递折扣
		if (null!=contract && null != contract.getExPreferential()) {
			Preferential preferential = contract.getExPreferential();
			preferential.setModifyDate(new Date());
			preferential.setModifyUser(ContextUtil.getCreateOrModifyUserId());
			preferential.setEndTime(contract.getContractendDate());
			contractDao.updatePreferential(preferential);
		}
		
		return contractDao.updateContract(contract);
	}


	/**
	 * 
	 * @根据查询条件返回合同集合
	 * @author 赵斌
	 * @2012-4-18
	 * @return List
	 * @param condition
	 *            合同查询条件
	 * @param start
	 *            数据开始下标
	 * @param limit
	 *            获取数据长度
	 */
	@Override
	public List<Contract> searchContract(ContractCondition condition,
			int start, int limit) {
		return contractDao.searchContract(condition, start, limit);
	}

	/**
	 * 
	 * @查询出满足条件的合同总数
	 * @author 赵斌
	 * @2012-4-19
	 * @return
	 */
	@Override
	public int countContract(ContractCondition condition) {
		return contractDao.countContract(condition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.service.IContractService#
	 * getContractsByMemberId(java.lang.String)
	 */
	@Override
	public List<Contract> getContractsByMemberId(String memberId) {
		Assert.notEmpty(memberId, "memberId must not null");
		ContractCondition condition = new ContractCondition();
		condition.setCustId(memberId);
		return contractDao.queryContract_360ByCondition(condition);
	}
	@Override
	public List<Contract> getContractsByCustNumber(String custNumber) {
		Assert.notEmpty(custNumber, "memberId must not null");
		ContractCondition condition = new ContractCondition();
		condition.setCustNumber(custNumber);
		return contractDao.queryContract_360ByCondition(condition);
	}
	
	@Override
	public List<Contract> queryContractWithDeptById(String memberId) {
		Assert.notEmpty(memberId, "memberId must not null");
		ContractCondition condition = new ContractCondition();
		condition.setCustId(memberId);
		return contractDao.queryContractWithDeptByCondition(condition);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.service.IContractService#
	 * getContractDetailById(java.lang.String)
	 */
	@Override
	public Contract getContractDetailById(String id) {
		Assert.notEmpty(id,"id must not null");
		ContractCondition condition = new ContractCondition();
		condition.setContractId(id);
		List<Contract> list = contractDao.queryContract_360ByCondition(condition);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	/**
	 * 
	 * @查询当前部门是否可以做到达
	 * @author 赵斌
	 * @2012-4-27
	 * @return String
	 * @param deptId
	 *            部门Id
	 */
	@Override
	public String getArrive(String deptId) {
		return contractDao.getArrive(deptId);
	}

	/**
	 * 
	 * @查询当前部门是否可以做出发
	 * @author 赵斌
	 * @2012-4-27
	 * @return String
	 * @param deptId
	 *            部门Id
	 */
	@Override
	public String getLeave(String deptId) {
		return contractDao.getLeave(deptId);
	}

	/**
	 * 
	 * @根据会员ID查询会员信息
	 * @author 赵斌
	 * @2012-5-2
	 * @return Member
	 * @param id
	 *            会员Id
	 */
	@Override
	public Member getMember(String id) {
		return alterMemberDao.getMember(id);
	}
	@Override
	public List<Double> getLatelyDeliverMoney(String custId, int mouth){
		//起始时间
		Date beginDate = getBeginDate(mouth);
		//结束时间
		Date endDate = getEndDate();
		// 添加最近几个月的年月
		List<Double> moneys = this.getDeliverMoneyByCondtion(beginDate,
				endDate, custId);
		if (null == moneys || 0>= moneys.size()) {
			for (int i = 0; i < mouth; i++) {
				moneys.add(0D);
			}
		}
		return moneys;
	}

	/**
	 * @description 获取查询客户发货最后一天.  
	 * @author 潘光均
	 * @version 0.1 2012-9-14
	 * @param 
	 *@date 2012-9-14
	 * @return Date
	 * @update 2012-9-14 下午5:16:50
	 */
	private Date getEndDate() {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, 0);
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.MILLISECOND, 0);
		return ca.getTime();
	}
	/**
	 * @description 获取客户发货开时间.  
	 * @author 潘光均
	 * @version 0.1 2012-9-14
	 * @param 
	 *@date 2012-9-14
	 * @return Date
	 * @update 2012-9-14 下午5:14:41
	 */
	private Date getBeginDate(int month) {
		Calendar ca = Calendar.getInstance(Locale.CHINA);
		ca.set(Calendar.MONTH, ca.get(Calendar.MONDAY)-month);
		ca.set(Calendar.DATE, 1);
		ca.set(Calendar.HOUR, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.MILLISECOND, 0);
		return ca.getTime();
	}
	
	@Override
	public List<Double> getDeliverMoneyByCondtion(Date beginDate,
			Date endDate, String custId) {
		List<Double> moneys = new ArrayList<Double>();
		List<Map<String,String>> livers = contractDao.getDeliverMoneyByCondtion(beginDate, endDate, custId);
		for (Map<String, String> map : livers) {
			Object monery =  map.get(Constant.deliverMoney);
			moneys.add(monery==null?0:Double.parseDouble(monery.toString()));
		}
		if (moneys.size()==0) {
			moneys.add(0d);
			moneys.add(0d);
			moneys.add(0d);
		}if (moneys.size()==1) {
			moneys.add(0d);
			moneys.add(0d);
		}if (moneys.size()==2) {
			moneys.add(0d);
		}
		return moneys;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#getContractId()
	 */
	@Override
	public String getContractId() {
		return contractDao.getSeqIdByTableName("Contract");
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#getConOperaLogSequence()
	 */
	@Override
	public String getConOperaLogSequence() {
		return contractDao.getSeqIdByTableName4Log("ContractOperatorLog");
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#createContractOpeLog(com.deppon.crm.module.customer.shared.domain.ContractOperatorLog)
	 */
	@Override
	public void createContractOpeLog(ContractOperatorLog log) {
			contractDao.createOperatorLog(log);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#searchOperaLogByWorkflowId(java.lang.String)
	 */
	@Override
	public List<ContractOperatorLog> searchOperaLogByWorkflowId(String wkid) {
		ContractOperatorLog log = new ContractOperatorLog();
		log.setWorkflowId(wkid);
		return contractDao.searchContractOperatorLogs(log);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#updateContractOperaLog(com.deppon.crm.module.customer.shared.domain.ContractOperatorLog)
	 */
	@Override
	public void updateContractOperaLog(ContractOperatorLog modifyLog) {
		contractDao.updateContractOperaLog(modifyLog);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#getAllContractInfoById(java.lang.String)
	 */
	@Override
	public Contract getAllContractInfoById(String contractId) {
		return contractDao.getAllContractInfoById(contractId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#queryContractByCondition(com.deppon.crm.module.customer.shared.domain.ContractCondition)
	 */
	@Override
	public List<Contract> queryContractByCondition(ContractCondition condition) {
		return contractDao.searchContract(condition, 0, Integer.MAX_VALUE);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#removeContractDept(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean removeContractDept(String contractId, String deptId) {
		return contractDao.deleteContractDept(contractId,deptId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#getContractOperatorLogByContractId(java.lang.String)
	 */
	@Override
	public List<ContractOperatorLog> getContractOperatorLogByContractId(
			String id) {
		return contractDao.getContractOperatorLogByContractId(id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#queryContractDeptByContractId(java.lang.String)
	 */
	@Override
	public List<ContractDept> queryContractDeptByContractId(String id) {
		return contractDao.queryContractDeptByContractId(id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#cancelTimeOutContract(java.lang.String, java.util.Date)
	 */
	@Override
	public void cancelTimeOutContract(String status, Date endDate) {
		contractDao.cancelTimeOutContract(status, endDate);
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#cancelTimeOutContract(java.lang.String, java.util.Date)
	 */
	@Override
	public void effectContract(String status, Date endDate) {
		contractDao.effectContract(status, endDate);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#deleteUneffectContractById(java.lang.String)
	 */
	@Override
	public void deleteUneffectContractById(String id) {
		contractDao.deleteContractById(id);
		contractDao.deletePreferential(id);
//		contractDao.insertDeletedContractId(id);
	}

	@Override
	public String getArrearaMountByCustId(String custId){
		// 添加最近三个月
		Date beginDate = getBeginDate(3);
		Date endDate = getEndDate();
		List<Map<String,String>> livers = contractDao.getDeliverMoneyByCondtion(beginDate, endDate, custId);
		return ContractUtil.getArrearaMount(livers);

	}
	public String getArrearaMountByCustIdForK(String custId){
		// 添加最近三个月
		Date beginDate = getBeginDate(3);
		Date endDate = getEndDate();
		List<Map<String,String>> livers = contractDao.getDeliverMoneyByCondtion(beginDate, endDate, custId);
		return ContractUtil.getArrearaMount(livers);

	}
	
	@Override
	public String getExArrearaMountByCustId(String custId){
		// 添加最近三个月
		Date beginDate = getBeginDate(3);
		Date endDate = getEndDate();
		List<Map<String,String>> livers = contractDao.getExDeliverMoneyByCondtion(beginDate, endDate, custId);
		return ContractUtil.getArrearaMount(livers);

	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#batchDeleteUneffectContract(java.util.List)
	 */
	@Override
	public void batchDeleteUneffectContract(List<String> contractIds) {
		contractDao.batchDeleteUneffectContract(contractIds);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#searchUneffectContractIn30Days(int)
	 */
	@Override
	public List<Contract> searchUneffectContractIn30Days(int day) {
		return contractDao.queryUneffectContractIn30Days(day);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#getWaitEffectContractByContractId(java.lang.String)
	 */
	@Override
	public List<Contract> getWaitEffectContractByContractId(String id) {
		return contractDao.queryWaitEffectContractByContractId(id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#searchAllWaitEffectContractByTime(java.util.Date)
	 */
	@Override
	public List<Contract> searchAllWaitEffectContractByTime(Date date) {
		return contractDao.queryAllWaitEffectContractByTime(date);
	}

	@Override
	public void updateContract4Wait(Contract modifyContract) {
		contractDao.updateContract4Wait(modifyContract);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#searchContractAllInfoByCustId(java.lang.String)
	 */
	@Override
	public List<Contract> searchContractAllInfoByCustId(String custId) {
		return contractDao.queryContractAllInfoByCustId(custId);
	}
	
	@Override
	public List<ContractMonthendDay> searchCommonContractMonthendDay(String status) {
		return contractDao.searchContractMonthendDays(status);
	}

	@Override
	public ContractMonthendDay searchContractMonthendDayByName(String contractMonthEndDayName) {
		return contractDao.searchContractMonthendDayByName(contractMonthEndDayName);
	}
	
	@Override
	public void updateContractMonthEnd(ContractMonthendDay contractDebtDay) {
		contractDao.updateContractMonthEnd(contractDebtDay);
	}
	
	@Override
	public ContractMonthendDay getContractMonthendDayById(String id) {
		return contractDao.getContractMonthendDayById(id);
	}

	@Override
	public ContractDebtDay getContractDebtDayById(String id) {
		return contractDao.getContractDebtDayById(id);
	}

	@Override
	public ContractDebtDay getContractDebtByCustNum(String custNum) {
		return contractDao.getContractDebtByCustNum(custNum);
	}

	@Override
	public void updateContractDebtDay(ContractDebtDay contractDebtDay) {
		contractDao.updateContractDebtDay(contractDebtDay);
	}
	
	@Override
	public void updateContractDebtDayById(Contract contract){
		contractDao.updateContractDebtDayById(contract);
	}

	@Override
	public List<Contract> getSameTypeContractByDebtDay(int debtDay) {
		return contractDao.getSameTypeContractByDebtDay(debtDay);
	}
	
	@Override
	public List<ContractDebtDay> getContractDebtsForDay(int day) {
		return contractDao.getContractDebtsForDay(day);
	}

	@Override
	public void updateAllContractDebt(int day) {
		contractDao.updateAllContractDebt(day);
	}

	@Override
	public void saveContractDebt(ContractDebtDay contractDebtDay) {
		contractDao.saveContractDebt(contractDebtDay);
	}
	
	@Override
	public List<Contract> getContractsBasicInfoByCustNum(String custNum) {
		return contractDao.getContractsBasicInfoByCustNum(custNum);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#queryContractWithMoney(com.deppon.crm.module.customer.shared.domain.ContractCondition, int, int)
	 */
	@Override
	public List<ContractDto> queryContractWithMoney(ContractCondition condition,
			int start, int limit) {
		//设置统计客户发货的起始时间
		condition.setCustBeginDate(this.getBeginDate(3));
		//设置统计客户发货的结束时间
		condition.setCustEndDate(this.getEndDate());
		return contractDao.queryEffectMonthSendContract(condition,start,limit);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#updatePreferential(com.deppon.crm.module.customer.shared.domain.Preferential)
	 */
	@Override
	public void updatePreferential(Preferential beforePreferential,Preferential afterPreferential) {
//		double chargeRebate = afterPreferential.getChargeRebate();
		this.copyPrefefentialProperties(afterPreferential, beforePreferential);
		//保存新折扣的属性
//		afterPreferential.setChargeRebate(chargeRebate);
		contractDao.createPreferential(afterPreferential);
		
		//更新原折扣
		beforePreferential.setModifyDate(new Date());
		beforePreferential.setEndTime(new Date(afterPreferential.getBeginTime().getTime()-1000));
		beforePreferential.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		beforePreferential.setStatus(Constant.CONTRACT_STATUS_UNEFFECT);
		contractDao.updatePreferential(beforePreferential);
	}

	/**
	 * @description 复制折扣属性.  
	 * @author 潘光均
	 * @version 0.1 2013-3-19
	 * @param 
	 *@date 2013-3-19
	 * @return void
	 * @update 2013-3-19 上午11:45:12
	 */
	private void copyPrefefentialProperties(Preferential afterPreferential,
			Preferential beforePreferential) {
		afterPreferential.setContractId(beforePreferential.getContractId());
		afterPreferential.setAgentgathRate(beforePreferential.getAgentgathRate());
//		afterPreferential.setChargeRebate(beforePreferential.getChargeRebate());
		afterPreferential.setDeliveryPriceRate(beforePreferential.getDeliveryPriceRate());
		afterPreferential.setInsuredPriceRate(beforePreferential.getInsuredPriceRate());
		afterPreferential.setReceivePriceRate(beforePreferential.getReceivePriceRate());
		afterPreferential.setEndTime(beforePreferential.getEndTime());
		afterPreferential.setPreferType(beforePreferential.getPreferType());
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#queryCustMonthSumsByCustId(java.lang.String)
	 */
	@Override
	public List<CustMonthSum> queryCustMonthSumsByCustId(String id) {
		return contractDao.getCustMonthSums(id,this.getBeginDate(3),this.getEndDate());
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#updatePreferential(com.deppon.crm.module.customer.shared.domain.Preferential)
	 */
	@Override
	public void updatePreferential(Preferential preferential) {
		contractDao.updatePreferential(preferential);
	}
	@Override
	public List<ContractOperatorLog> queryContractOperatorLogsForDate(
			Date begin, Date end) {
		return contractDao.queryContractOperatorLogsForDate(begin, end);
	}

	@Override
	public List<ContractOperatorLog> searchContractOperatorLogs(
			ContractOperatorLog log) {
		return contractDao.searchContractOperatorLogs(log);
	}

	@Override
	public List<Contract> searchInitContract(
			ContractCondition contractCondition, int start, int limit) {
		return contractDao.searchInitContract(contractCondition, start, limit);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#countInitContract(com.deppon.crm.module.customer.shared.domain.ContractCondition)
	 */
	@Override
	public int countInitContract(ContractCondition contractCondition) {
		return contractDao.countInitContract(contractCondition);
	}
	@Override
	public void updateOperationContractMonthDay(int newday,int oldDay) {
		contractDao.updateOperationContractMonthDay(newday,oldDay);
	}

	@Override
	public void deleteCommonContractDebtDay(String status) {
		contractDao.deleteCommonContractDebtDay(status);
	}

	@Override
	public void saveCommonContractDebtDay(
			ContractMonthendDay contractMonthendDay) {
		contractDao.saveCommonContractDebtDay(contractMonthendDay);
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
		contractDao.updateDuningDept(dunningDeptCode, ifForeignGoods, contractId);
	}

	@Override 
	public void modifyPriceVersionDate(Date priceVersionDate,Date exPriceVersionDate, String contractId) {
		contractDao.modifyPriceVersionDate(priceVersionDate,exPriceVersionDate, contractId);
	}

	@Override
	public List<Map<String, String>> getCustInfoForArreaAmoutMessage() {
		return contractDao.getCustInfoForArreaAmoutMessage();
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#queryExCustMonthSumsByCustId(java.lang.String)
	 */
	@Override
	public List<CustMonthSum> queryExCustMonthSumsByCustId(String custId) {
		return contractDao.getExCustMonthSumsByCustId(custId,this.getBeginDate(3),this.getEndDate());
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IContractService#getExLatelyDeliverMoney(java.lang.String, int)
	 */
	@Override
	public List<Double> getExLatelyDeliverMoney(String custId, int month) {
		//起始时间
		Date beginDate = getBeginDate(month);
		//结束时间
		Date endDate = getEndDate();
		// 添加最近几个月的年月
		List<Double> moneys = this.getExDeliverMoneyByCondtion(beginDate,
				endDate, custId);
		if (null == moneys || 0>= moneys.size()) {
			for (int i = 0; i < month; i++) {
				moneys.add(0D);
			}
		}
		return moneys;
	}

	/**
	 * <p>
	 * Description:获取近三月发货金额<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-27
	 * @param beginDate
	 * @param endDate
	 * @param custId
	 * @return
	 * List<Double>
	 */
	private List<Double> getExDeliverMoneyByCondtion(Date beginDate,
			Date endDate, String custId) {
		List<Double> moneys = new ArrayList<Double>();
		List<Map<String,String>> livers = contractDao.getExDeliverMoneyByCondtion(beginDate, endDate, custId);
		for (Map<String, String> map : livers) {
			Object monery =  map.get(Constant.deliverMoney);
			moneys.add(monery==null?0:Double.parseDouble(monery.toString()));
		}
		return moneys;

	}

	@Override
	public boolean updateContractBaseInfo(Contract contract) {
		return contractDao.updateContract(contract);
	}
}
