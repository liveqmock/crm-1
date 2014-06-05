/**
 * @description
 * @author 赵斌
 * @2012-3-28
 * @return
 */
package com.deppon.crm.module.customer.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.client.sync.domain.CustTransationOperation;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation.CustomerTableName;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation.OperationFlg;
import com.deppon.crm.module.customer.server.dao.IContractDao;
import com.deppon.crm.module.customer.server.utils.Assert;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.CustomerLogUtil;
import com.deppon.crm.module.customer.server.utils.SqlUtil;
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
import com.deppon.crm.module.customer.shared.domain.Preferential;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * @author 赵斌
 * 
 */
public class ContractDao extends iBatis3DaoImpl implements IContractDao {

	private final static String NAMESPACE_CONTRACT = "com.deppon.crm.module.customer.shared.domain.Contract.";
	//修改合同催款部门
	private final static String UPDATE_DUNINGDEPT = "updateDuningDept";
	
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
		// 当前用户
		contract.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		int count = this.getSqlSession().insert(NAMESPACE_CONTRACT + "createContract",
				contract);
		//仅仅同步有效的合同
		if (Constant.CONTRACT_STATUS_EFFECT
				.equals(contract.getContractStatus())
				&& new Date().after(contract.getContractBeginDate())
				&& new Date().before(contract.getContractendDate())) {
			List<CustTransationOperation> list = CustomerLogUtil
					.createCustTranList(CustomerTableName.T_CUST_CONTRACT,
							OperationFlg.I, contract.getId());
			writeCustomerLog(list);
		}
		return 0 == count?null:contract;
	}

	/**
	 * @新增合同部门
	 * @author 赵斌
	 * @2012-3-30
	 * @return
	 */
	@Override
	public ContractDept createContractDept(ContractDept contractDept) {
		int count = this.getSqlSession().insert(NAMESPACE_CONTRACT + "createContractDept",
				contractDept);

		if (new Date().after(contractDept.getBoundTime())&&new Date().before(contractDept.getRemoveTime())) {
			List<CustTransationOperation> list = CustomerLogUtil
					.createCustTranList(CustomerTableName.T_CUST_CONTRACTDEPT,
							OperationFlg.I, contractDept.getId());
			writeCustomerLog(list);
		}
		return  0 == count?null:contractDept;
	}

	/**
	 * @删除合同部门
	 * @author 赵斌
	 * @2012-3-30
	 * @return
	 */
	@Override
	public void deleteContractDept(String id) {
		this.getSqlSession().delete(NAMESPACE_CONTRACT + "deleteContractDept",
				id);
		List<CustTransationOperation> list = CustomerLogUtil
				.createCustTranList(CustomerTableName.T_CUST_CONTRACTDEPT,
						OperationFlg.D, id);
		writeCustomerLog(list);
	}

	/**
	* <p>Title: createContractTax</p>
	* <p>Description: 插入税务信息</p>
	* @author chenaichun 
	* @param contractTax
	* @return
	* @see com.deppon.crm.module.customer.server.dao.IContractDao#createContractTax(com.deppon.crm.module.customer.shared.domain.ContractTax)
	*/
	@Override
	public boolean createContractTax(ContractTax contractTax) {
		int count = this.getSqlSession().insert(NAMESPACE_CONTRACT + "insertContractTax",contractTax);
		List<CustTransationOperation> list = CustomerLogUtil
				.createCustTranList(CustomerTableName.T_CUST_CONTRACTTAX,
						OperationFlg.I, contractTax.getId());
		writeCustomerLog(list);
		return count > 0?true:false;
	}

	/**(非 Javadoc)
	* <p>Title: updateContractTax</p>
	* <p>Description: 修改合同税务标记信息</p>
	* @author chenaichun 
	* @param contractTax
	* @return
	* @see com.deppon.crm.module.customer.server.dao.IContractDao#updateContractTax(com.deppon.crm.module.customer.shared.domain.ContractTax)
	*/
	@Override
	public boolean updateContractTax(ContractTax contractTax) {
		// TODO Auto-generated method stub
		contractTax.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		int result = this.getSqlSession().update(
				NAMESPACE_CONTRACT + "updateContractTax", contractTax);

			List<CustTransationOperation> list = CustomerLogUtil
					.createCustTranList(CustomerTableName.T_CUST_CONTRACTTAX,
							OperationFlg.U, contractTax.getId());
			writeCustomerLog(list);
		return result > 0 ? true : false;
	}

	

	/**(非 Javadoc)
	* <p>Title: deleteContractTax</p>
	* <p>Description:删除税务标记信息 </p>
	* @author chenaichun 
	* @param id
	* @return
	* @see com.deppon.crm.module.customer.server.dao.IContractDao#deleteContractTax(java.lang.String)
	*/
	@Override
	public boolean deleteContractTax(String id) {
		int result = this.getSqlSession().delete(NAMESPACE_CONTRACT + "deleteContractTax",id);
		List<CustTransationOperation> list = CustomerLogUtil
		.createCustTranList(CustomerTableName.T_CUST_CONTRACTTAX,
				OperationFlg.D, id);
      writeCustomerLog(list);
		return result > 0? true:false;
	}

	/**
	 * @创建合同优惠信息
	 * @author 赵斌
	 * @2012-4-10
	 * @return Preferential
	 */
	@Override
	public Preferential createPreferential(Preferential preferential) {
		Preferential preferentialBak = preferential.clone();
		if (1d==preferentialBak.getChargeRebate()) {
			preferentialBak.setChargeRebate(null);
		}
		if (1d==preferentialBak.getAgentgathRate()) {
			preferentialBak.setAgentgathRate(null);
		}
		if (1d==preferentialBak.getDeliveryPriceRate()) {
			preferentialBak.setDeliveryPriceRate(null);
		}
		if (1d==preferentialBak.getInsuredPriceRate()) {
			preferentialBak.setInsuredPriceRate(null);
		}
//		if (1d==preferentialBak.getReceivePriceRate()) {
//			preferentialBak.setReceivePriceRate(null);
//		}
		this.getSqlSession().insert(NAMESPACE_CONTRACT + "insertPreferential",
				preferentialBak);
		List<CustTransationOperation> list = CustomerLogUtil
				.createCustTranList(CustomerTableName.T_CUST_PREFERENTIAL,
						OperationFlg.I, preferentialBak.getId());
		writeCustomerLog(list);
		preferential.setId(preferentialBak.getId());
		return preferentialBak;
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
		contract.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		int result = this.getSqlSession().update(
				NAMESPACE_CONTRACT + "updateContract", contract);

			List<CustTransationOperation> list = CustomerLogUtil
					.createCustTranList(CustomerTableName.T_CUST_CONTRACT,
							OperationFlg.U, contract.getId());
			writeCustomerLog(list);
		return result > 0 ? true : false;
	}

	/**
	 * 
	 * @修改合同部门
	 * @author 赵斌
	 * @2012-4-12
	 * @param ContractDept
	 *            合同部门
	 * @return boolean
	 */
	@Override
	public boolean updateContractDept(ContractDept contractDept) {
		int result = this.getSqlSession().update(
				NAMESPACE_CONTRACT + "updateContractDept", contractDept);

		List<CustTransationOperation> list = CustomerLogUtil
				.createCustTranList(CustomerTableName.T_CUST_CONTRACTDEPT,
						OperationFlg.U, contractDept.getId());
		writeCustomerLog(list);

		return result > 0 ? true : false;
	}

	/**
	 * 根据ID查询出合同主体信息
	 * 
	 * @description
	 * @author 赵斌
	 * @2012-4-16
	 * @return
	 */
	@Override
	public Contract getContract(String id) {
		return (Contract) this.getSqlSession().selectOne(
				NAMESPACE_CONTRACT + "selectContractBaseInfoById", id);
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
	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> searchContract(ContractCondition condition,
			int start, int limit) {
		RowBounds rowBounds = new RowBounds(start, limit);
		condition.setCustCompany(SqlUtil.getLikeField(condition.getCustCompany()));
		condition.setContactName(SqlUtil.getLikeField(condition.getContactName()));
		return (List<Contract>) this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "queryContractByCondition", condition,
				rowBounds);
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
		return Integer.parseInt(this
				.getSqlSession()
				.selectOne(
						NAMESPACE_CONTRACT + "queryContractCountByCondition",
						condition).toString());
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
		return (String) this.getSqlSession().selectOne(
				NAMESPACE_CONTRACT + "queryIsArrive", deptId);
	}
	
	/**
	 * 
	 * @根据合同部门id查询合同信息
	 * @author 潘光均斌
	 * @2012-9-07
	 * @return String
	 * @param deptIds
	 *            部门Id集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> queryContractByContractDeptIds(List<String> deptIds) {
		return getSqlSession().selectList(
				NAMESPACE_CONTRACT + "queryContractByContractDeptId", deptIds);
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
		return (String) this.getSqlSession().selectOne(
				NAMESPACE_CONTRACT + "queryIsLeave", deptId);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getDeliverMoneyByCondtion(
			Date beginDate,Date endDate, String custId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		map.put("custId", custId);
		return this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "getDeliverMoneyByCondtion", map);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getExDeliverMoneyByCondtion(
			Date beginDate,Date endDate, String custId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		map.put("custId", custId);
		return this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "getExDeliverMoneyByCondtion", map);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	private void writeCustomerLog(List<CustTransationOperation> list) {
		CustomerLogUtil.writeCustomerLog(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.customer.server.dao.IContractDao#createOperatorLog
	 * (com.deppon.crm.module.customer.shared.domain.ContractOperatorLog)
	 */
	@Override
	public void createOperatorLog(ContractOperatorLog log) {
		Assert.notNull(log, "contractOperatorLog must not null");
		Assert.notNull(log.getId(), "contractOperatorLog id must not null");
		this.getSqlSession().insert(
				NAMESPACE_CONTRACT + "insertContractOperatorLog", log);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public String getSeqIdByTableName(String tableName) {
		Assert.notEmpty(tableName, "contractOperatorLog must not emp");

		Map<String, String> map = new HashMap<String, String>();
		map.put("tableName", tableName);
		map.put("date", String.valueOf(System.nanoTime()));
		return (String) this.getSqlSession().selectOne(
				NAMESPACE_CONTRACT + "getSeqIdByTableName", map);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public String getSeqIdByTableName4Log(String tableName) {
		Assert.notEmpty(tableName, "contractOperatorLog must not emp");

		Map<String, String> map = new HashMap<String, String>();
		map.put("tableName", tableName);
		map.put("date", String.valueOf(System.nanoTime()));
		return (String) this.getSqlSession().selectOne(
				NAMESPACE_CONTRACT + "getSeqIdByTableName4Log", map);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.dao.IContractDao#
	 * getConOperaLogByWorkflowId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractOperatorLog> searchContractOperatorLogs(
			ContractOperatorLog log) {
		return this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "searchContractOperatorLogs", log);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void updateContractOperaLog(ContractOperatorLog modifyLog) {
		this.getSqlSession().update(
				NAMESPACE_CONTRACT + "updateContractOperatorLog", modifyLog);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public Contract getAllContractInfoById(String contractId) {
		return (Contract) this.getSqlSession().selectOne(
				NAMESPACE_CONTRACT + "selectContractAllById", contractId);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractDept> searchContractDeptByContractId(String contractid) {
		return this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "searchContractDeptByContractId",contractid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.customer.server.dao.IContractDao#deleteContractDept
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteContractDept(String contractId, String deptId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("contractId", contractId);
		map.put("deptId", deptId);

		List<String> idList = this.getSqlSession().selectList(
				NAMESPACE_CONTRACT
						+ "queryContractDeptIdsByContractIdAndDeptId", map);
		List<CustTransationOperation> list = CustomerLogUtil
				.createCustTranList(CustomerTableName.T_CUST_CONTRACTDEPT,
						OperationFlg.D, idList);
		writeCustomerLog(list);

		return getSqlSession().delete(
				NAMESPACE_CONTRACT + "deleteContractDeptByIds", map) > 0;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.dao.IContractDao#
	 * getContractOperatorLogByContractId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractOperatorLog> getContractOperatorLogByContractId(
			String contractId) {
		return this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "searchContractOperatorLogByContractId",
				contractId);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> queryContract_360ByCondition(
			ContractCondition condition) {
		return this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "queryContract_360ByCondition", condition);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> queryContractWithDeptByCondition(
			ContractCondition condition) {
		return this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "queryContractWithDeptByCondition", condition);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ContractDept getContractDeptByDeptId(String contractId, String deptId) {
		Map map = new HashMap();
		map.put("contractId", contractId);
		map.put("deptId", deptId);
		return (ContractDept) this.getSqlSession().selectOne(
				NAMESPACE_CONTRACT + "getContractDeptByDeptId", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.customer.server.dao.IContractDao#
	 * queryContractDeptByContractId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractDept> queryContractDeptByContractId(String id) {
		return this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "searchContractDeptByContractId", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.customer.server.dao.IContractDao#cancelTimeOutContract
	 * (java.lang.String, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void cancelTimeOutContract(String status, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("endDate", endDate);
		List<String> idList = this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "getcancelTimeOutContractIds", map);
		List<CustTransationOperation> list = CustomerLogUtil
				.createCustTranList(CustomerTableName.T_CUST_CONTRACT,
						OperationFlg.U, idList);
		writeCustomerLog(list);
		this.getSqlSession().update(
				NAMESPACE_CONTRACT + "cancelTimeOutContract", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.customer.server.dao.IContractDao#cancelTimeOutContract
	 * (java.lang.String, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void effectContract(String status, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("endDate", endDate);
		//需要同步的合同id
		List<String> idList = this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "getWaitEffectContractIds", map);
		if (null != idList && 0 < idList.size()) {
			List<CustTransationOperation> list = CustomerLogUtil
					.createCustTranList(CustomerTableName.T_CUST_CONTRACT,
							OperationFlg.I, idList);
			//需要同步的合同部门id
			List<String> contractDeptList = this.getSqlSession().selectList(
					NAMESPACE_CONTRACT + "getContractDeptByEffectContractIds", idList);
			list.addAll( CustomerLogUtil
					.createCustTranList(CustomerTableName.T_CUST_CONTRACTDEPT,
							OperationFlg.I, contractDeptList));
			
			writeCustomerLog(list);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.customer.server.dao.IContractDao#deleteContractById
	 * (java.lang.String)
	 */
	@Override
	public void deleteContractById(String id) {
		this.getSqlSession().delete(NAMESPACE_CONTRACT + "deleteContractById",
				id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContractDao#
	 * insertDeletedContractId(java.lang.String)
	 */
	@Override
	public void insertDeletedContractId(String contractId) {
		this.getSqlSession().insert(
				NAMESPACE_CONTRACT + "insertDeletedContractId", contractId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContractDao#batchDeleteUneffectContract(java.util.List)
	 */
	@Override
	public void batchDeleteUneffectContract(List<String> contractIds) {
		this.getSqlSession().insert(
				NAMESPACE_CONTRACT + "batchDeletedContract", contractIds);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContractDao#queryUneffectContractIn30Days(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> queryUneffectContractIn30Days(int day) {
		return this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "queryUneffectContractIn30Days",day);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContractDao#queryWaitEffectContractByContractId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> queryWaitEffectContractByContractId(String id) {
		return this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "queryWaitEffectContractByContractId",id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContractDao#queryAllWaitEffectContractByTime(java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> queryAllWaitEffectContractByTime(Date date) {
		return this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "queryAllWaitEffectContractByTime",date);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void updateContract4Wait(Contract modifyContract) {
		modifyContract.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		this.getSqlSession().update(
				NAMESPACE_CONTRACT + "updateContract4Wait", modifyContract);

		List<CustTransationOperation> list = CustomerLogUtil
				.createCustTranList(CustomerTableName.T_CUST_CONTRACT,
						OperationFlg.U, modifyContract.getId());
		writeCustomerLog(list);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContractDao#queryContractAllInfoByCustId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> queryContractAllInfoByCustId(String custId) {
		return this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "queryContractAllInfoByCustId",custId);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractMonthendDay> searchContractMonthendDays(String status) {
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT + "searchContractMonthendDays",status);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public ContractMonthendDay searchContractMonthendDayByName(String contractMonthEndDayName) {
		return (ContractMonthendDay) this.getSqlSession().selectOne(NAMESPACE_CONTRACT + "searchContractMonthendDayByName", contractMonthEndDayName);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void updateContractMonthEnd(ContractMonthendDay contractMonthEnd) {
		this.getSqlSession().update(NAMESPACE_CONTRACT + "updateContractMonthEnd",contractMonthEnd);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public ContractMonthendDay getContractMonthendDayById(String id) {
		return (ContractMonthendDay) this.getSqlSession().selectOne(NAMESPACE_CONTRACT + "getContractMonthendDayById", id);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public ContractDebtDay getContractDebtDayById(String id) {
		return (ContractDebtDay) this.getSqlSession().selectOne(NAMESPACE_CONTRACT + "getContractDebtDayById", id);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public ContractDebtDay getContractDebtByCustNum(String custNum) {
		return (ContractDebtDay) this.getSqlSession().selectOne(NAMESPACE_CONTRACT + "getContractDebtByCustNum", custNum);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void updateContractDebtDay(ContractDebtDay contractDebtDay) {
		this.getSqlSession().update(NAMESPACE_CONTRACT + "updateContractDebtDay", contractDebtDay);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void updateContractDebtDayById(Contract contract) {
		this.getSqlSession().update(NAMESPACE_CONTRACT + "updateContractDebtDayById",contract);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> getSameTypeContractByDebtDay(int debtDay) {
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT + "getContractByDebtDay", debtDay);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractDebtDay> getContractDebtsForDay(int day) {
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT + "getContractDebtsForOneDay", day);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void updateAllContractDebt(int day) {
		this.getSqlSession().update(NAMESPACE_CONTRACT + "updateAllContractDebt",day);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void saveContractDebt(ContractDebtDay contractDebtDay) {
		this.getSqlSession().insert(NAMESPACE_CONTRACT + "saveContractDebt", contractDebtDay);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> getContractsBasicInfoByCustNum(String custNum) {
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT + "getContractsBasicInfoByCustNum", custNum);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContractDao#queryEffectMonthSendContract(com.deppon.crm.module.customer.shared.domain.ContractCondition, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractDto> queryEffectMonthSendContract(
			ContractCondition condition, int start, int limit) {
		RowBounds rowBounds = new RowBounds(start, limit);
		return (List<ContractDto>) this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "queryEffectMonthSendContract", condition,
				rowBounds);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContractDao#updatePreferential(com.deppon.crm.module.customer.shared.domain.Preferential)
	 */
	@Override
	public void updatePreferential(Preferential preferential) {
		if (1d==preferential.getChargeRebate()) {
			preferential.setChargeRebate(null);
		}
		if (1d==preferential.getAgentgathRate()) {
			preferential.setAgentgathRate(null);
		}
		if (1d==preferential.getDeliveryPriceRate()) {
			preferential.setDeliveryPriceRate(null);
		}
		if (1d==preferential.getInsuredPriceRate()) {
			preferential.setInsuredPriceRate(null);
		}
//		if (1d==preferential.getReceivePriceRate()) {
//			preferential.setReceivePriceRate(null);
//		}
		this.getSqlSession().update(NAMESPACE_CONTRACT+"updatePreferential", preferential);
		List<CustTransationOperation> list = CustomerLogUtil
				.createCustTranList(CustomerTableName.T_CUST_PREFERENTIAL,
						OperationFlg.U, preferential.getId());
		writeCustomerLog(list);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContractDao#getCustMonthSums(java.lang.String, java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CustMonthSum> getCustMonthSums(String id, Date beginDate,
			Date endDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("custId", id);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT+"queryCustMonthAmountByCustId", map);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContractDao#deletePreferential(java.lang.String)
	 */
	@Override
	public void deletePreferential(String contractId) {
		this.getSqlSession().delete(NAMESPACE_CONTRACT+"deletePreferentialByContractId", contractId);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractOperatorLog> queryContractOperatorLogsForDate(
			Date begin, Date end) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", begin);
		map.put("end", end);
		return this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + "queryContractOperatorLogsForDate",map);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> searchInitContract(
			ContractCondition contractCondition, int start, int limit) {
		RowBounds rowBounds = new RowBounds(start, limit);
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT + "searchInitContract",contractCondition,rowBounds);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContractDao#countInitContract(com.deppon.crm.module.customer.shared.domain.ContractCondition)
	 */
	@Override
	public int countInitContract(ContractCondition contractCondition) {
		return  (Integer) this.getSqlSession().selectOne(NAMESPACE_CONTRACT + "countInitContract", contractCondition);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void updateOperationContractMonthDay(int newday,int oldDay) {
		Map map = new HashMap();
		map.put("newday", newday);
		map.put("oldDay", oldDay);
		this.getSqlSession().update(NAMESPACE_CONTRACT + "updateOperationContractMonthDay",map);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void deleteCommonContractDebtDay(String status) {
		this.getSqlSession().delete(NAMESPACE_CONTRACT + "deleteCommonContractDebtDay",status);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@Override
	public void saveCommonContractDebtDay(
			ContractMonthendDay contractMonthendDay) {
		this.getSqlSession().insert(NAMESPACE_CONTRACT + "saveCommonContractDebtDay",contractMonthendDay);
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
	@SuppressWarnings("unchecked")
	@Override
	public void updateDuningDept(String dunningDeptCode, Boolean ifForeignGoods,
			String contractId) {
		@SuppressWarnings("rawtypes")
		Map map = new HashMap();
		map.put("contractId", contractId);
		map.put("ifForeignGoods", ifForeignGoods);
		map.put("dunningDeptCode", dunningDeptCode);
		this.getSqlSession().update(NAMESPACE_CONTRACT+UPDATE_DUNINGDEPT, map);
		List<CustTransationOperation> list = CustomerLogUtil
				.createCustTranList(CustomerTableName.T_CUST_CONTRACT,
						OperationFlg.U, contractId);
		writeCustomerLog(list);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override 
	public void modifyPriceVersionDate(Date priceVersionDate, Date exPriceVersionDate,String contractId) {
		@SuppressWarnings("rawtypes")
		Map map = new HashMap();
		map.put("contractId", contractId);
		map.put("priceVersionDate", priceVersionDate);
		map.put("exPriceVersionDate", exPriceVersionDate);
		this.getSqlSession().update(NAMESPACE_CONTRACT+"modifyPriceVersionDate", map);
		List<CustTransationOperation> list = CustomerLogUtil
				.createCustTranList(CustomerTableName.T_CUST_CONTRACT,
						OperationFlg.U, contractId);
		writeCustomerLog(list);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author majia
	 * @version 0.1 2013-11-28
	 * @param 
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getCustInfoForArreaAmoutMessage() {
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT+"getCustInfoForArreaAmoutMessage");
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IContractDao#getExCustMonthSumsByCustId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CustMonthSum> getExCustMonthSumsByCustId(String custId, Date beginDate,
			Date endDate){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("custId", custId);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT+"queryExCustMonthAmountByCustId", map);
	}
}
