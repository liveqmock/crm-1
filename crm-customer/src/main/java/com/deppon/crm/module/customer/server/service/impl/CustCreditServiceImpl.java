package com.deppon.crm.module.customer.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.server.dao.ICustCreditDao;
import com.deppon.crm.module.customer.server.service.ICustCreditService;
import com.deppon.crm.module.customer.shared.domain.CustCredit;
import com.deppon.crm.module.customer.shared.domain.CustCreditConfig;

/**
 * <p>
 * Description: 客户信用service<br/>
 * </p>
 * @title CustCreditServiceImpl
 * @package com.deppon.crm.module.customer.server.service.impl
 * @author andy
 * @version 1.0
 * @date 2014-03-07
 *
 */
public class CustCreditServiceImpl implements ICustCreditService {

	private ICustCreditDao custCreditDao;
	
	
	public ICustCreditDao getCustCreditDao() {
		return custCreditDao;
	}


	public void setCustCreditDao(ICustCreditDao custCreditDao) {
		this.custCreditDao = custCreditDao;
	}



	/**
	 * 
	 * <p>
	 * 定时恢复散客、固客的临欠额度<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param beginDate 3个月前1号
	 * @param endDate   上个月最后一天
	 * @return void
	 * 
	 */
	@Override
	public void getDeliverMoneyByCondtion(
			Date beginDate, 
			Date endDate) {
		custCreditDao.getDeliverMoneyByCondtion(beginDate, endDate);
	}


	/**
	 * 
	 * <p>
	 * 根据客户id，查询客户信用信息<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param custId
	 * @return CustCredit
	 * 
	 */
	@Override
	public CustCredit getCustCreditByCustId(String custId) {
		return custCreditDao.getCustCreditByCustId(custId);
	}

	/**
	 * 
	 * <p>
	 * 更新信用较差客户配置信息<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param config
	 * @return void
	 * 
	 */
	@Override
	public void updateCustCreditConfig(CustCreditConfig config) {
		custCreditDao.updateCustCreditConfig(config);
	}

	/**
	 * 
	 * <p>
	 * 获取信用较差客户配置信息<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param
	 * @return CustCreditConfig
	 * 
	 */
	@Override
	public CustCreditConfig getCustCreditConfig() {
		return custCreditDao.getCustCreditConfig();
	}


	/**
	 * 
	 * <p>
	 * 查询客户未还款信息
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return List<CustCredit>
	 * 
	 */
	@Override
	public List<CustCredit> getCustBadCreditList() {
		return custCreditDao.getCustBadCreditList();
	}


	/**
	 * 
	 * <p>
	 * 标记信用较差客户
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param list
	 * @return void
	 * 
	 */
	@Override
	public void updateCustBadCredit(List<CustCredit> list) {
		custCreditDao.updateCustBadCredit(list);
	}
	
	/**
	 * 
	 * <p>
	 * 标记信用较差客户(存储过程)
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return void
	 * 
	 */
	@Override
	public void updateCustBadCredit() {
		custCreditDao.updateCustBadCredit();
	}

	/**
	 * 
	 * <p>
	 * 获取信用较差客户列表
	 * 
	 * 1.临欠超过 N 天未还临欠客户
	 * 2.按照月结合同规定时间后 N 天未还款的月结客户
	 * 3.逾期 N 天未还款的客户
	 * 4.最长一笔临欠达到 N  天（含）的时欠款客户
	 * 5.距离规定结款日期前   N   天未还款的月结客户
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return List<CustCredit>
	 * 
	 */
	@Override
	public List<CustCredit> getCustBadCreditExcelList() {
		return custCreditDao.getCustBadCreditExcelList();
	}

	/**
	 * 
	 * <p>
	 * 记录信用较差客户列表
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param custCredit
	 * @param isCommit 是否批量提交
	 * @return void
	 * 
	 */
	@Override
	public void insertCustBadCreditReport(CustCredit custCredit, boolean isCommit) {
		custCreditDao.insertCustBadCreditReport(custCredit, isCommit);
	}
	
	/**
	 * 
	 * <p>
	 * 记录信用较差客户列表(存储过程)
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return void
	 * 
	 */
	@Override
	public void insertCustBadCreditReport() {
		custCreditDao.insertCustBadCreditReport();
	}
	
	/**
	 * 
	 * <p>
	 * 生成信用较差客户待办 (存储过程)
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @return void
	 * 
	 */
	@Override
	public void generationCustCreditToDo() {
		custCreditDao.generationCustCreditToDo();
	}

	/**
	 * 
	 * <p>
	 * 更新信用预警信息（信用预警次数、最后一次信用预警时间）
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param custId
	 * @param isCommit 是否批量提交
	 * @return void
	 * 
	 */
	@Override
	public void updateEarlyWarnInfo(String custId, boolean isCommit) {
		custCreditDao.updateEarlyWarnInfo(custId, isCommit);
	}

	/**
	 * 
	 * <p>
	 * 根据条件查询信用较差客户列表
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param custCredit
	 * @return List<CustCredit>
	 * 
	 */
	@Override
	public List<CustCredit> getCustCreditListByCondition(CustCredit custCredit) {
		return custCreditDao.getCustCreditListByCondition(custCredit);
	}

	/**
	 * 
	 * <p>
	 * 获取信用较差客户报表中的经营本部（distinct）
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return List<Map<String, String>>
	 * 
	 */
	@Override
	public List<Map<String, String>> getManagementDeptList() {
		return custCreditDao.getManagementDeptList();
	}

	/**
	 * 
	 * <p>
	 * 获取信用较差客户报表中的事业部（distinct）
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return List<Map<String, String>>
	 * 
	 */
	@Override
	public List<Map<String, String>> getBusDeptList() {
		return custCreditDao.getBusDeptList();
	}

	/**
	 * 
	 * <p>
	 * 获取信用较差客户报表中的大区（distinct）
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return List<Map<String, String>>
	 * 
	 */
	@Override
	public List<Map<String, String>> getBigAreaDeptList() {
		return custCreditDao.getBigAreaDeptList();
	}

	/**
	 * 
	 * <p>
	 * 获取信用较差客户报表中的小区（distinct）
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return List<Map<String, String>>
	 * 
	 */
	@Override
	public List<Map<String, String>> getAreaDeptList() {
		return custCreditDao.getAreaDeptList();
	}

	/**
	 * 
	 * <p>
	 * 获取信用较差客户报表中的营业部（distinct）
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return List<Map<String, String>>
	 * 
	 */
	@Override
	public List<Map<String, String>> getResponsibilityDeptList() {
		return custCreditDao.getResponsibilityDeptList();
	}

	/**
	 * 
	 * <p>
	 * 信用较差客户报表查询<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param map
	 * @param start
	 * @param limit
	 * @return List<CustCredit>
	 * 
	 */
	@Override
	public List<CustCredit> getCustCreditListByConditions(Map<String, String> map, int start,int limit) {
		return custCreditDao.getCustCreditListByConditions(map, start, limit);
	}
	
	/**
	 * 
	 * <p>
	 * 信用较差客户报表查询<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param map
	 * @return int
	 * 
	 */
	@Override
	public int getCustCreditCountByConditions(Map<String, String> map) {
		return custCreditDao.getCustCreditCountByConditions(map);
	}
	
	/**
	 * 
	 * <p>
	 * 信用较差客户报表查询<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param map
	 * @param start
	 * @param limit
	 * @return List<CustCredit>
	 * 
	 */
	@Override
	public List<CustCredit> getCustCreditListByOtherConditions(Map<String, String> map, int start,int limit) {
		return custCreditDao.getCustCreditListByOtherConditions(map, start, limit);
	}
	
	/**
	 * 
	 * <p>
	 * 信用较差客户报表查询(查询部门为“点部”)<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param map
	 * @param start
	 * @param limit
	 * @return List<CustCredit>
	 * 
	 */
	@Override
	public List<CustCredit> getCustCreditListByMinistryConditions(Map<String, String> map, int start,int limit) {
		return custCreditDao.getCustCreditListByMinistryConditions(map, start, limit);
	}
	
	/**
	 * 
	 * <p>
	 * 信用较差客户报表查询<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param map
	 * @return int
	 * 
	 */
	@Override
	public int getCustCreditCountByOtherConditions(Map<String, String> map) {
		return custCreditDao.getCustCreditCountByOtherConditions(map);
	}
	
	/**
	 * 
	 * <p>
	 * 信用较差客户报表查询(查询部门为“点部”)<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param map
	 * @return int
	 * 
	 */
	@Override
	public int getCustCreditCountByMinistryConditions(Map<String, String> map) {
		return custCreditDao.getCustCreditCountByMinistryConditions(map);
	}

	/**
	 * 
	 * <p>
	 * 根据快递分部查询信用较差客户列表<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param map
	 * @return List<CustCredit>
	 * 
	 */
	@Override
	public List<CustCredit> getCustCreditListByBranch(Map<String, String> map) {
		return custCreditDao.getCustCreditListByBranch(map);
	}
	
	/**
	 * 
	 * <p>
	 * 根据快递点部查询信用较差客户列表<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param map
	 * @return List<CustCredit>
	 * 
	 */
	@Override
	public List<CustCredit> getCustCreditListByTheMinistry(Map<String, String> map) {
		return custCreditDao.getCustCreditListByTheMinistry(map);
	}
	
	/**
	 * 
	 * <p>
	 * 查询权限校验：XX快递点部数据对应权限为XX快递点部经理<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param map
	 * @return int
	 * 
	 */
	@Override
	public int checkIsViewByexpressMinistry(Map<String, String> map) {
		return custCreditDao.checkIsViewByexpressMinistry(map);
	}
	
	/**
	 * 
	 * <p>
	 * 查询权限校验：XX快递分部数据对应权限为XX分部高级经理、XX分部统计组<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param map
	 * @return int
	 * 
	 */
	@Override
	public int checkIsViewByexpressBranch(Map<String, String> map) {
		return custCreditDao.checkIsViewByexpressBranch(map);
	}

}
