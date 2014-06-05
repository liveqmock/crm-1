package com.deppon.crm.module.marketing.server.dao;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.AssDeptPriCondition;
import com.deppon.crm.module.marketing.shared.domain.AssessDept;
import com.deppon.crm.module.marketing.shared.domain.AssessDeptPrincipal;
import com.deppon.crm.module.marketing.shared.domain.AuthAllSalCondition;
import com.deppon.crm.module.marketing.shared.domain.CustDevAssess;
import com.deppon.crm.module.marketing.shared.domain.CustMatAssess;
import com.deppon.crm.module.marketing.shared.domain.SearchAssessCondition;
import com.deppon.crm.module.organization.shared.domain.Department;

/**   
 * <p>
 * Description:营销效果评估报表Dao接口<br />
 * </p>
 * @title MarketAssessManager.java
 * @package com.deppon.crm.module.marketing.server.dao
 * @author ZhouYuan
 * @version 2013-01-17
 */
public interface IMarketAssessDao {
	
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
	public void createDevMarketReport(Date date);
	/**   
	 * <p>
	 * Description:查询部门列表<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param List<String> 部门ID
	 * @return List<AssessDept> 部门列表
	 */
	public List<AssessDept> searchDepts(List<String> depts);
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
	public void createMatMarketReport(Date date);
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
	public List<CustDevAssess> searchCustDevByAuth(SearchAssessCondition devCondition,int start,int limit);
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
	public List<CustDevAssess> searchAmountCustDevByAuth(SearchAssessCondition devCondition);

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
	public List<CustMatAssess> searchCustMatByAuth(SearchAssessCondition devCondition,int start,int limit);
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
	public List<CustMatAssess> searchAmountCustMatByAuth(SearchAssessCondition devCondition);
	/**
	 * 
	 * <p>
	 * 查询组织架构-所有经营本部<br/>
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-01-23
	 */
	public List<Department> searchmanagerDept(String deptName);
	/**
	 * 
	 * <p>
	 * 根据部门parentid查询子级部门<br/>
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-01-23
	 */
	public List<Department> searchDeptsByParentId(String parentId);
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
	public int countCustDevByAuth(SearchAssessCondition devCondition);
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
	public List<CustDevAssess> searchCustDevAllSalDept(AuthAllSalCondition allCondition);
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
	public int countCustMatByAuth(SearchAssessCondition devCondition);
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
	public List<CustMatAssess> searchCustMatAllSalDept(AuthAllSalCondition allCondition);
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
	public List<CustDevAssess> searchCustDevByAuthForOutput(SearchAssessCondition devCondition);
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
	public List<CustMatAssess> searchCustMatByAuthForOutput(SearchAssessCondition devCondition);
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
	public List<AssessDeptPrincipal> searchDeptPrincipal(AssDeptPriCondition adpc);
}
