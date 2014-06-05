package com.deppon.crm.module.marketing.server.dao.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.dao.IMarketAssessDao;
import com.deppon.crm.module.marketing.shared.domain.AssDeptPriCondition;
import com.deppon.crm.module.marketing.shared.domain.AssessDept;
import com.deppon.crm.module.marketing.shared.domain.AssessDeptPrincipal;
import com.deppon.crm.module.marketing.shared.domain.AuthAllSalCondition;
import com.deppon.crm.module.marketing.shared.domain.CustDevAssess;
import com.deppon.crm.module.marketing.shared.domain.CustMatAssess;
import com.deppon.crm.module.marketing.shared.domain.SearchAssessCondition;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**   
 * <p>
 * Description:营销效果评估报表Dao实现<br />
 * </p>
 * @title MarketAssessManager.java
 * @package com.deppon.crm.module.marketing.server.dao.impl
 * @author ZhouYuan
 * @version 2013-01-17
 */
public class MarketAssessDaoImpl extends iBatis3DaoImpl implements IMarketAssessDao{
	//营销评估命名空间
	private static final String NAME_MARKETASSESS="com.deppon.crm.module.marketing.shared.domain.MarketAssess.";
	//查询部门列表
	private static final String SEARCHDEPTS="searchDepts";
	// 开发日表生成
	private static final String SEARCHDEVMARKETREPORT = "searchDevMarketReport";
	// 维护日表生成
	private static final String SEARCHMATMARKETREPORT = "searchMatMarketReport";
	//根据权限查询客户维护表
	private static final String SEARCHCUSTDEVBYAUTH= "searchCustDevByAuth";
	//根据权限查询客户维护表-总计
	private static final String SEARAMOUNTCHCUSTDEVBYAUTH= "searchAmountCustDevByAuth";
	//根据权限查询客户开发表
	private static final String SEARCHCUSTMATBYAUTH= "searchCustMatByAuth";
	//根据权限查询客户开发表-总计
	private static final String SEARAMOUNTCHCUSTMATBYAUTH= "searchAmountCustMatByAuth";
	//根据名称查询所有经营本部
	private static final String SEARCHMANAGERDEPT = "searchManagerDept";
	// 
	private static final String SEARCHDEPTSBYPARENTID = "searchDeptsByParentId";
	//根据权限查询客户维护表查询数据量
	private static final String COUNTCUSTDEVBYAUTH = "countCustDevByAuth";
	//根据权限导出权限下所有数据
	private static final String SEARCHCUSTDEVALLSALDEPT = "searchCustDevAllSalDept";
	//根据权限查询客户维护表查询数据量
	private static final String COUNTCUSTMATBYAUTH = "countCustMatByAuth";
	//根据权限导出客户维护表中权限下所有数据
	private static final String SEARCHCUSTMATALLSALDEPT = "searchCustMatAllSalDept";
	//查询该用户部门负责人
	private static final String SEARCHDEPTPRINCIPAL="searchDeptPrincipal";
	/**   
	 * <p>
	 * Description:查询部门列表<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param List<String> 部门ID
	 * @return List<AssessDept> 部门列表
	 */
	@Override
	public List<AssessDept> searchDepts(List<String> depts){
		return getSqlSession().selectList(NAME_MARKETASSESS+SEARCHDEPTS,depts);
	}

	/**
	 * 
	 * <p>
	 * 每天生成营销报表-开发<br/>
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-01-21
	 * @param endDate 
	 * @param beginDate 
	 * @return int
	 */
	@Override
	public void createDevMarketReport(Date date) {
		Map<String,Date> map = new HashMap<String,Date>();
		map.put("date", date);
		getSqlSession().selectOne(NAME_MARKETASSESS+SEARCHDEVMARKETREPORT,map);
		
	}
	/**
	 * 
	 * <p>
	 * 每天生成营销报表-维护<br/>
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-01-22
	 * @param endDate 
	 * @param beginDate 
	 * @return int
	 */
	@Override
	public void createMatMarketReport(Date date) {
		Map<String,Date> map = new HashMap<String,Date>();
		map.put("date", date);
		getSqlSession().selectOne(NAME_MARKETASSESS+SEARCHMATMARKETREPORT,map);
	}
	/**
	 * 
	 * <p>
	 * 根据查询条件查询客户开发表<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustDevAssess>
	 */
	@Override
	public List<CustDevAssess> searchCustDevByAuth(SearchAssessCondition devCondition,int start,int limit){
		RowBounds rb = new RowBounds(start,limit);
		return getSqlSession().selectList(NAME_MARKETASSESS+SEARCHCUSTDEVBYAUTH,devCondition,rb);
	}
	/**
	 * 
	 * <p>
	 * 根据查询条件查询客户开发表-总计<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustDevAssess>
	 */
	@Override
	public List<CustDevAssess> searchAmountCustDevByAuth(
			SearchAssessCondition devCondition) {
		return getSqlSession().selectList(NAME_MARKETASSESS+SEARAMOUNTCHCUSTDEVBYAUTH,devCondition);
	}
	/**
	 * 
	 * <p>
	 * 根据查询条件查询客户维护表<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-23
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustMatAssess>
	 */
	@Override
	public List<CustMatAssess> searchCustMatByAuth(
			SearchAssessCondition devCondition, int start, int limit) {
		RowBounds rb = new RowBounds(start,limit);
		return getSqlSession().selectList(NAME_MARKETASSESS+SEARCHCUSTMATBYAUTH,devCondition,rb);
	}
	/**
	 * 
	 * <p>
	 * 根据查询条件查询客户维护表-总计<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-23
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustMatAssess>
	 */
	@Override
	public List<CustMatAssess> searchAmountCustMatByAuth(
			SearchAssessCondition devCondition) {
		return getSqlSession().selectList(NAME_MARKETASSESS+SEARAMOUNTCHCUSTMATBYAUTH,devCondition);
	}
	/**
	 * 
	 * <p>
	 * 查询组织架构-所有经营本部<br/>
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-01-23
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> searchmanagerDept(String deptName) {
		return getSqlSession().selectList(NAME_MARKETASSESS+SEARCHMANAGERDEPT,deptName);
	}
	/**
	 * 
	 * <p>
	 * 根据部门parentid查询子级部门<br/>
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-01-23
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> searchDeptsByParentId(String parentId){
		return getSqlSession().selectList(NAME_MARKETASSESS+SEARCHDEPTSBYPARENTID,parentId);
		
	}
	/**
	 * 
	 * <p>
	 * 根据查询条件查询客户开发表数据量<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustDevAssess>
	 */
	@Override
	public int countCustDevByAuth(
			SearchAssessCondition devCondition) {
		return (Integer)getSqlSession().selectOne(NAME_MARKETASSESS+COUNTCUSTDEVBYAUTH,devCondition);
	}
	/**
	 * 
	 * <p>
	 * 查询权限下的所有客户开发信息<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustDevAssess>
	 */
	@Override
	public List<CustDevAssess> searchCustDevAllSalDept(
			AuthAllSalCondition allCondition) {
		return getSqlSession().selectList(NAME_MARKETASSESS+SEARCHCUSTDEVALLSALDEPT,allCondition);
	}
	/**
	 * 
	 * <p>
	 * 根据查询条件查询客户维护表数据量<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustMatAssess>
	 */
	@Override
	public int countCustMatByAuth(
			SearchAssessCondition devCondition) {
		return (Integer)getSqlSession().selectOne(NAME_MARKETASSESS+COUNTCUSTMATBYAUTH,devCondition);
	}
	/**
	 * 
	 * <p>
	 * 查询权限下的所有客户维护信息<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustMatAssess>
	 */
	@Override
	public List<CustMatAssess> searchCustMatAllSalDept(
			AuthAllSalCondition allCondition) {
		return getSqlSession().selectList(NAME_MARKETASSESS+SEARCHCUSTMATALLSALDEPT,allCondition);
	}
	/**
	 * 
	 * <p>
	 * 根据查询条件查询客户开发表-导出当前页面数据<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustDevAssess>
	 */
	@Override
	public List<CustDevAssess> searchCustDevByAuthForOutput(SearchAssessCondition devCondition){
		return getSqlSession().selectList(NAME_MARKETASSESS+SEARCHCUSTDEVBYAUTH,devCondition);
	}
	/**
	 * 
	 * <p>
	 * 根据查询条件查询客户表--用于导出当前页面数据<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustMatAssess>
	 */
	@Override
	public List<CustMatAssess> searchCustMatByAuthForOutput(SearchAssessCondition devCondition){
		return getSqlSession().selectList(NAME_MARKETASSESS+SEARCHCUSTMATBYAUTH,devCondition);
	}
	/**
	 * 
	 * <p>
	 * 查询该用户的部门负责人<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param AssDeptPriCondition 查询条件 
	 * @return List<AssessDeptPrincipal>
	 */
	@Override
	public List<AssessDeptPrincipal> searchDeptPrincipal(AssDeptPriCondition adpc){
		return getSqlSession().selectList(NAME_MARKETASSESS+SEARCHDEPTPRINCIPAL,adpc);
	}
}
