package com.deppon.crm.module.marketing.server.service.impl;


import java.util.List;


import java.util.Date;
import java.util.List;


import com.deppon.crm.module.marketing.server.dao.impl.MarketAssessDaoImpl;
import com.deppon.crm.module.marketing.server.service.IMarketAssessService;

import com.deppon.crm.module.marketing.shared.domain.AssDeptPriCondition;
import com.deppon.crm.module.marketing.shared.domain.AssessDept;
import com.deppon.crm.module.marketing.shared.domain.AssessDeptPrincipal;
import com.deppon.crm.module.marketing.shared.domain.AuthAllSalCondition;
import com.deppon.crm.module.marketing.shared.domain.CustMatAssess;
import com.deppon.crm.module.marketing.shared.domain.SearchAssessCondition;

import com.deppon.crm.module.marketing.shared.domain.CustDevAssess;
import com.deppon.crm.module.organization.shared.domain.Department;

/**   
 * <p>
 * Description:营销效果评估报表Service接口<br />
 * </p>
 * @title MarketAssessManager.java
 * @package com.deppon.crm.module.marketing.server.service.impl
 * @author ZhouYuan
 * @version 2013-01-17
 */
public class MarketAssessServiceImpl implements IMarketAssessService{
	private MarketAssessDaoImpl marketAssessDaoImpl;

	public void setMarketAssessDaoImpl(MarketAssessDaoImpl marketAssessDaoImpl) {
		this.marketAssessDaoImpl = marketAssessDaoImpl;
	}

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
	public List<AssessDept> searchDepts(List<String> depts) {
		return marketAssessDaoImpl.searchDepts(depts);
	}

	/**
	 * 
	 * <p>
	 * 每天生成营销报表-维护<br/>
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-01-21
	 * @param endDate 
	 * @param beginDate 
	 */
	@Override
	public void autoCreateDevMarketReport(Date date) {
		 marketAssessDaoImpl.createDevMarketReport(date);
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
	 */
	@Override
	public void autoCreateMatMarketReport(Date date) {
		marketAssessDaoImpl.createMatMarketReport(date);
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
	public List<CustDevAssess> searchCustDevByAuth(
			SearchAssessCondition devCondition, int start, int limit) {
		
		return marketAssessDaoImpl.searchCustDevByAuth(devCondition, start, limit);
	}
	/**
	 * 
	 * <p>
	 * 根据查询条件查询开发表-总计<br/>
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
		return marketAssessDaoImpl.searchAmountCustDevByAuth(devCondition);
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
	@Override
	public List<Department> searchmanagerDept(String deptName) {
		return marketAssessDaoImpl.searchmanagerDept(deptName);
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
	@Override
	public List<Department> searchDeptsByParentId(String parentId) {
		return marketAssessDaoImpl.searchDeptsByParentId(parentId);
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
	public List<CustMatAssess> searchCustMatByAuth(SearchAssessCondition devCondition,int start,int limit){
		return marketAssessDaoImpl.searchCustMatByAuth(devCondition, start, limit);
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
	public List<CustMatAssess> searchAmountCustMatByAuth(SearchAssessCondition devCondition){
		return marketAssessDaoImpl.searchAmountCustMatByAuth(devCondition);
	}
	/**
	 * 
	 * <p>
	 * 根据查询条件查询客开发表数据量<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustDevAssess>
	 */
	@Override
	public int countCustDevByAuth(SearchAssessCondition devCondition){
		return marketAssessDaoImpl.countCustDevByAuth(devCondition);
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
	public List<CustDevAssess> searchCustDevAllSalDept(AuthAllSalCondition allCondition){
		return marketAssessDaoImpl.searchCustDevAllSalDept(allCondition);
	}
	/**
	 * 
	 * <p>
	 * 根据查询条件查询客户维护数据量<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustMatAssess>
	 */
	@Override
	public int countCustMatByAuth(SearchAssessCondition devCondition) {
		return marketAssessDaoImpl.countCustMatByAuth(devCondition);
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
		return marketAssessDaoImpl.searchCustMatAllSalDept(allCondition);
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
		return marketAssessDaoImpl.searchCustDevByAuthForOutput(devCondition);
	}
	/**
	 * 
	 * <p>
	 * 根据查询条件查询客户维护表--用于导出当前页面数据<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustMatAssess>
	 */
	@Override
	public List<CustMatAssess> searchCustMatByAuthForOutput(SearchAssessCondition devCondition){
		return marketAssessDaoImpl.searchCustMatByAuthForOutput(devCondition);
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
		return marketAssessDaoImpl.searchDeptPrincipal(adpc);
	}
	
	
}
