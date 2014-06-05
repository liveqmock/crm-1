package com.deppon.crm.module.customer.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.jfree.util.Log;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;

import com.deppon.crm.module.customer.server.dao.ICustCreditDao;
import com.deppon.crm.module.customer.shared.domain.CustCredit;
import com.deppon.crm.module.customer.shared.domain.CustCreditConfig;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * <p>
 * Description: 客户信用dao<br/>
 * </p>
 * @title CustCreditDaoImpl
 * @package com.deppon.crm.module.customer.server.dao.impl
 * @author andy
 * @version 1.0
 * @date 2014-03-07
 *
 */
@SuppressWarnings("unchecked")
public class CustCreditDaoImpl extends iBatis3DaoImpl implements ICustCreditDao {
	
	
	private final static String NAMESPACE_CONTRACT = "com.deppon.crm.module.customer.shared.domain.CustCredit.";
	private final static String GET_DELIVER_MONEY_BY_CONDTION = "getDeliverMoneyByCondtion";
	private final static String GET_CUSTCREDIT_BY_CUSTID = "getCustCreditByCustId";
	private final static String UPDATE_CUSTCREDIT_CONFIG = "updateCustCreditConfig";
	private final static String GET_CUSTCREDIT_CONFIG = "getCustCreditConfig";
	private final static String GET_CUST_BAD_CREDIT_LIST = "getCustBadCreditList";
	private final static String UPDATE_CUST_BAD_CREDIT = "updateCustBadCredit";
	private final static String UPDATE_CUST_CREDIT = "updateCustCredit";
	private final static String UPDATE_CUST_BAD_CREDIT_PROC = "updateCustBadCreditProc";
	private final static String GET_CUST_BAD_CREDIT_EXCEL_LIST = "getCustBadCreditExcelList";
	private final static String INSERT_CUST_BAD_CREDIT_REPORT = "insertCustBadCreditReport";
	private final static String INSERT_CUST_BAD_CREDIT_REPORT_PROC = "insertCustBadCreditReportProc";
	private final static String UPDATE_EARLY_WARN_INFO = "updateEarlyWarnInfo";
	private final static String GET_CUST_CREDIT_LIST_BY_CONDITION = "getCustCreditListByCondition";
	private final static String GET_MANAGEMENTDEPT_LIST = "getManagementDeptList";
	private final static String GET_BUSDEPT_LIST = "getBusDeptList";
	private final static String GET_BIGAREADEPT_LIST = "getBigAreaDeptList";
	private final static String GET_AREADEPT_LIST = "getAreaDeptList";
	private final static String GET_RESPONSIBILITYDEPT_LIST = "getResponsibilityDeptList";
	private final static String GET_CUSTCREDIT_LIST_BY_CONDITIONS = "getCustCreditListByConditions";
	private final static String GET_CUSTCREDIT_COUNT_BY_CONDITIONS = "getCustCreditCountByConditions";
	private final static String GET_CUSTCREDIT_LIST_BY_OTHER_CONDITIONS = "getCustCreditListByOtherConditions";
	private final static String GET_CUSTCREDIT_LIST_BY_MINISTRY_CONDITIONS = "getCustCreditListByMinistryConditions";
	private final static String GET_CUSTCREDIT_COUNT_BY_OTHER_CONDITIONS = "getCustCreditCountByOtherConditions";
	private final static String GET_CUSTCREDIT_COUNT_BY_MINISTRY_CONDITIONS = "getCustCreditCountByMinistryConditions";
	private final static String GET_CUSTCREDIT_LIST_BYBRANCH = "getCustCreditListByBranch";
	private final static String GENERATION_CUSTCREDIT_TODO = "generationCustCreditToDo";
	private final static String GET_CUSTCREDIT_LIST_BYMINISTRY = "getCustCreditListByMinistry";
	private final static String CHECK_ISVIEW_BY_EXPRESS_MINISTRY = "checkIsViewByexpressMinistry";
	private final static String CHECK_ISVIEW_BY_EXPRESS_BRANCH = "checkIsViewByexpressBranch";

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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + GET_DELIVER_MONEY_BY_CONDTION, map);
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
		List<CustCredit> list = this.getSqlSession().selectList(
				NAMESPACE_CONTRACT + GET_CUSTCREDIT_BY_CUSTID, custId);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
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
		this.getSqlSession().update(NAMESPACE_CONTRACT + UPDATE_CUSTCREDIT_CONFIG, config);
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
		return (CustCreditConfig) this.getSqlSession().selectOne(NAMESPACE_CONTRACT + GET_CUSTCREDIT_CONFIG);
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
		List<CustCredit> creditList = this.getSqlSession().selectList(NAMESPACE_CONTRACT + GET_CUST_BAD_CREDIT_LIST);
		return creditList;
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
		this.getSqlSession().update(NAMESPACE_CONTRACT + UPDATE_CUST_CREDIT);
		this.getSqlSession().update(NAMESPACE_CONTRACT + UPDATE_CUST_BAD_CREDIT, list); 
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
		this.getSqlSession().selectList(NAMESPACE_CONTRACT + UPDATE_CUST_BAD_CREDIT_PROC); 
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
		List<CustCredit> creditList = this.getSqlSession().
					selectList(NAMESPACE_CONTRACT + GET_CUST_BAD_CREDIT_EXCEL_LIST);
		return creditList;
	}
	
	/**
	 * 
	 * @description 批量操作时调用此方法，为了事务统一.
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 操作类型
	 * @date 2014-3-7
	 * @return SqlSession
	 * 
	 */
	private SqlSession getSqlSession(ExecutorType executorType) {
		SqlSession sqlSession = SqlSessionUtils.getSqlSession(
				((SqlSessionTemplate) getSqlSession()).getSqlSessionFactory(),
				executorType, null);
		return sqlSession;
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
		SqlSession session = this.getSqlSession(ExecutorType.BATCH);
		try {
			this.getSqlSession().insert(NAMESPACE_CONTRACT + INSERT_CUST_BAD_CREDIT_REPORT, custCredit);
			if(isCommit) {
				session.commit();
				session.clearCache();
			}
		} catch (Exception e) {
			session.rollback();
			Log.info("客户信用批量提交异常", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
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
		this.getSqlSession().selectList(NAMESPACE_CONTRACT + INSERT_CUST_BAD_CREDIT_REPORT_PROC);
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
		this.getSqlSession().selectList(NAMESPACE_CONTRACT + GENERATION_CUSTCREDIT_TODO);
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
		SqlSession session = this.getSqlSession(ExecutorType.BATCH);
		try {
			this.getSqlSession().insert(NAMESPACE_CONTRACT + UPDATE_EARLY_WARN_INFO, custId);
			if(isCommit) {
				session.commit();
				session.clearCache();
			}
		} catch (Exception e) {
			session.rollback();
			Log.info("信用预警批量更新异常", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
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
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT + GET_CUST_CREDIT_LIST_BY_CONDITION, custCredit);
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
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT + GET_MANAGEMENTDEPT_LIST);
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
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT + GET_BUSDEPT_LIST);
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
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT + GET_BIGAREADEPT_LIST);
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
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT + GET_AREADEPT_LIST);
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
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT + GET_RESPONSIBILITYDEPT_LIST);
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
		if(limit != -1) {
			RowBounds rb = new RowBounds(start,limit);
			return this.getSqlSession().selectList(NAMESPACE_CONTRACT + GET_CUSTCREDIT_LIST_BY_CONDITIONS, map, rb);
		}
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT + GET_CUSTCREDIT_LIST_BY_CONDITIONS, map);
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
		return (Integer)this.getSqlSession().selectOne(NAMESPACE_CONTRACT + GET_CUSTCREDIT_COUNT_BY_CONDITIONS, map);
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
		RowBounds rb = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT + GET_CUSTCREDIT_LIST_BY_OTHER_CONDITIONS, map, rb);
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
		RowBounds rb = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT + GET_CUSTCREDIT_LIST_BY_MINISTRY_CONDITIONS, map, rb);
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
		return (Integer)this.getSqlSession().selectOne(NAMESPACE_CONTRACT + GET_CUSTCREDIT_COUNT_BY_OTHER_CONDITIONS, map);
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
		return (Integer)this.getSqlSession().selectOne(NAMESPACE_CONTRACT + GET_CUSTCREDIT_COUNT_BY_MINISTRY_CONDITIONS, map);
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
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT + GET_CUSTCREDIT_LIST_BYBRANCH, map);
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
		return this.getSqlSession().selectList(NAMESPACE_CONTRACT + GET_CUSTCREDIT_LIST_BYMINISTRY, map);
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
		return (Integer)this.getSqlSession().selectOne(NAMESPACE_CONTRACT + CHECK_ISVIEW_BY_EXPRESS_MINISTRY, map);
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
		return (Integer)this.getSqlSession().selectOne(NAMESPACE_CONTRACT + CHECK_ISVIEW_BY_EXPRESS_BRANCH, map);
	}

}
