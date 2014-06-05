package com.deppon.crm.module.marketing.server.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.shared.domain.AssessAuthority;
import com.deppon.crm.module.marketing.shared.domain.AuthAllSalCondition;
import com.deppon.crm.module.marketing.shared.domain.CustDevAssess;
import com.deppon.crm.module.marketing.shared.domain.CustMatAssess;
import com.deppon.crm.module.marketing.shared.domain.SearchAssessCondition;
import com.deppon.crm.module.organization.shared.domain.Department;

/**   
 * <p>
 * Description:营销效果评估报表Manager接口<br />
 * </p>
 * @title IMarketAssessManager.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author ZhouYuan
 * @version 2013-01-17
 */
public interface IMarketAssessManager{
	
	/**
	 * 
	 * <p>
	 * 每天生成营销报表<br/>
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-01-21
	 */
	public void autoCreateMarketReport();
	/**
	 * 
	 * <p>
	 * 封装查询条件<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustDevAssess>
	 */
	public SearchAssessCondition strucSearchAssConditon(User user,List<String> deptIds,Date date);
	/**
	 * 
	 * <p>
	 * 根据用户权限生成权限实体<br/>
	 * </p>
	 * @param user 登录用户
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 */
	public AssessAuthority createAssessAuth(User user);
	/**
	 * 
	 * <p>
	 * 根据权限查询客户开发表<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustDevAssess>
	 */
	public Map<String,Object> searchCustDevByAuth(
			List<String> deptIds,Date date,User user,String potenSource,String custCategory, int start, int limit);
	
	/**
	 * 
	 * <p>
	 * 查询客户开发表总计<br/>
	 * </p>
	 * 
	 * @author zhangdeng
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustDevAssess>
	 */
	public CustDevAssess searchSumCustDevByAuth(
			List<String> deptIds,Date date,User user,String potenSource,String custCategory);
	
	/**
	 * 
	 * <p>
	 * 根据权限查询客户开发表-导出当前页面数据<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustDevAssess>
	 */
	public List<CustDevAssess> searchCustDevByAuthForOutput(
			List<String> deptIds, Date date, User user,String potenSource,String custCategory);
	/**
	 * 
	 * <p>
	 * 根据权限查询客户维护表<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustMatAssess>
	 */
	public Map<String,Object> searchCustMatByAuth(
			List<String> deptIds,Date date,User user, int start, int limit);
	
	
	/**
	 * 
	 * <p>
	 * 查询客户维护表总计<br/>
	 * </p>
	 * 
	 * @author zhangdeng
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustMatAssess>
	 */
	public CustMatAssess searchSumCustMatByAuth(
			List<String> deptIds,Date date,User user);
	
	/**
	 * 
	 * <p>
	 * 根据权限查询客户维护表--用于导出当前页面数据<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param SearchAssessCondition 查询条件 
	 * @return List<CustMatAssess>
	 */
	public List<CustMatAssess> searchCustMatByAuthForOutput(
			List<String> deptIds,Date date,User user);
	/**
	 * 
	 * <p>
	 * 查询组织架构-所有经营本部<br/>
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-01-23
	 */
	public List<Department> searchmanagerDept();
	/** 
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
	 * 根据权限创建导出权限所有数据的查询条件<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param user用户 
	 * @return AuthAllSalCondition
	 */
	public AuthAllSalCondition createAuthAllCondition(User user,Date date);
	/**
	 * 
	 * <p>
	 * 根据查询条件导出客户开发表所有数据<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param user用户 
	 * @void
	 */
	public boolean outputDevAssessExcelByPage(String fileName,String filePath,List<String> deptIds,Date date,User user,String potenSource,String custCategory);
	/**
	 * 
	 * <p>
	 * 根据查询条件导出客户维护表所有数据<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param user用户 
	 * @void
	 */
	public boolean outputMatAssessExcelByPage(String fileName,String filePath,List<String> deptIds,Date date,User user);
	/**
	 * 
	 * <p>
	 * 导出客户开发表权限所有数据<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param user用户 
	 * @void
	 */
	public boolean outputDevAssessExcelByAuth(String fileName,String filePath,Date date,User user,String potenSource,String custCategory);
	/**
	 * 
	 * <p>
	 * 导出客户维护表权限所有数据<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-01-22
	 * @param user用户 
	 * @void
	 */
	public boolean outputMatAssessExcelByAuth(String fileName,String filePath,Date date,User user);
}
