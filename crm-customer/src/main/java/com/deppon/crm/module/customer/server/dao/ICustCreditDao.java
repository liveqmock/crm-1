package com.deppon.crm.module.customer.server.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.shared.domain.CustCredit;
import com.deppon.crm.module.customer.shared.domain.CustCreditConfig;

/**
 * <p>
 * Description: 客户信用dao<br/>
 * </p>
 * @title ICustCreditDao
 * @package com.deppon.crm.module.customer.server.dao
 * @author andy
 * @version 1.0
 * @date 2014-03-07
 *
 */
public interface ICustCreditDao {

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
	void getDeliverMoneyByCondtion(
			Date beginDate, 
			Date endDate);
	
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
	CustCredit getCustCreditByCustId(String custId);
	
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
	void updateCustCreditConfig(CustCreditConfig config);
	
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
	CustCreditConfig getCustCreditConfig();
	
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
	List<CustCredit> getCustBadCreditList();
	
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
	void updateCustBadCredit(List<CustCredit> list);
	
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
	void updateCustBadCredit();
	
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
	List<CustCredit> getCustBadCreditExcelList();
	
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
	void insertCustBadCreditReport(CustCredit custCredit, boolean isCommit);
	
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
	void insertCustBadCreditReport();
	
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
	void generationCustCreditToDo();
	
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
	void updateEarlyWarnInfo(String custId, boolean isCommit);
	
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
	List<CustCredit> getCustCreditListByCondition(CustCredit custCredit);
	
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
	List<Map<String, String>> getManagementDeptList();
	
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
	List<Map<String, String>> getBusDeptList();
	
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
	List<Map<String, String>> getBigAreaDeptList();
	
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
	List<Map<String, String>> getAreaDeptList();
	
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
	List<Map<String, String>> getResponsibilityDeptList();
	
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
	List<CustCredit> getCustCreditListByConditions(Map<String, String> map, int start,int limit);
	
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
	int getCustCreditCountByConditions(Map<String, String> map);
	
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
	List<CustCredit> getCustCreditListByOtherConditions(Map<String, String> map, int start,int limit);
	
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
	List<CustCredit> getCustCreditListByMinistryConditions(Map<String, String> map, int start,int limit);
	
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
	int getCustCreditCountByOtherConditions(Map<String, String> map);
	
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
	int getCustCreditCountByMinistryConditions(Map<String, String> map);
	
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
	List<CustCredit> getCustCreditListByBranch(Map<String, String> map);
	
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
	List<CustCredit> getCustCreditListByTheMinistry(Map<String, String> map);
	
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
	int checkIsViewByexpressMinistry(Map<String, String> map);
	
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
	int checkIsViewByexpressBranch(Map<String, String> map);
}
