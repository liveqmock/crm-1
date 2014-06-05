package com.deppon.crm.module.customer.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.organization.shared.domain.Department;

public interface IBaseDataManager {
	/**
	 * 
	 * <p>
	 * 得到客户关系开发部<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-14
	 * @return
	 * Department
	 */
	public Department getCustRelationsDepartment();
	/**
	 * 
	 * <p>
	 * 获得营业部对应的事业部办公室下面的营销管理组<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-16
	 * @param businessDivesion
	 * @return
	 * Department
	 */
	public Department getBusinessDivesionOfficeMarketingGroup(Department childDept);
	/**
	 * 
	 * <p>
	 * 得到部门对应大区<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-28
	 * @param currentUserDept
	 * @return
	 * String
	 */
	public Department getRegionDepartment(String deptId);
	/**
	 * 
	 * <p>
	 * 得到部门对应小区<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-28
	 * @param deptId
	 * @return
	 * String
	 */
	public Department getNeighborhoodDepartment(String deptId);
	/**
	 * 
	 * <p>
	 * 得到部门对应的事业部<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-28
	 * @param dept
	 * @return
	 * String
	 */
	public Department getCauseDepartment(String deptId);
	
	/**
	 * 
	 * <p>通过部门ID，获得部门信息<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-5-29
	 * @param id
	 * @return
	 * Department
	 */
	public Department getDeptById(String id);
	
	/**
	 * 
	 * <p>通过部门标杆编码，获得部门信息<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-5-29
	 * @param standardCode
	 * @return
	 * Department
	 */
	public Department getDeptByStandardCode(String standardCode);
	/**
	 * 
	 * <p>
	 * 通过开户省份查询开户城市信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-11
	 * @param bankprovince
	 * @return
	 * List<Map<String,String>>
	 */
	public List<Map<String,String>> getBankCityByBankProvinceId(String bankprovince);
	/**
	 * 
	 * <p>
	 * 得到开户支行信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-11
	 * @return
	 * List<Map<String,String>>
	 */
	public List<Map<String,String>> getBankProvince();
	/**
	 * 
	 * <p>
	 * 得到开户银行信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-11
	 * @return
	 * List<Map<String,String>>
	 */
	public List<Map<String,String>> getAccountBank();
	/**
	 * 
	 * <p>
	 * 根据对应的功能得到我的数据权限<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-11
	 * @param functionName
	 * @return
	 * List<Department>
	 */
	public List<Department> getMyAuthDataDeptsByFunction(String functionName,int start,int limit);
	
	/**
	 * 
	 * <p>
	 * 分页查询--根据对应的功能得到我的数据权限里面部门名称模块匹配的数据<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-11
	 * @param functionName
	 * @return
	 * List<Department>
	 */
	public List<Department> getMyAuthDataDeptsByFunction(String functionName,String deptName,int start,int limit);
	/**
	 * 
	 * <p>
	 * 统计总数--根据对应的功能得到我的数据权限里面部门名称模块匹配的数据<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-12
	 * @param functionName
	 * @param deptName
	 * @return
	 * int
	 */
	public int countMyAuthDataDeptsByFunction(String functionName,String deptName);
	/**
	 * 
	 * <p>
	 * 统计总数--根据对应的功能得到我的数据权限<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-12
	 * @param functionName
	 * @return
	 * int
	 */
	public int countMyAuthDataDeptsByFunction(String functionName);
	/**
	 * 
	 * <p>
	 * 客户模块特殊的---数据权限验证<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-17
	 * @param dept
	 * @return
	 * List<String>
	 */
	public List<String> getDataAuthorityDepts(String dept);
	/**
	 * 
	 * <p>
	 * 客户模块特殊的---数据权限验证，权限采用DPAP可分配权限<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-7-17
	 * @param dept
	 * @return
	 * List<String>
	 */
	public List<String> hasDataAuthorityDepts(String dept);

	/**
	 * <p>
	 * Description:true表示是香港，false表示不是香港<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-21
	 * @return
	 * boolean
	 */
	public boolean isHongKongOrMainLand();
	
	/**
	 * <p>
	 * Description:通过部门Id得到部门所在的城市  0表示大陆，1表示香港<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-21
	 * @return
	 * String
	 */
	public String getDeptCityLocation(String deptId);
		/**
	 * Description:查询公司所有部门<br />
	 * @version 0.1 2013-5-9
	 * @param start
	 * @param limit
	 * @return List<Department>
	 */
	public List<Department> getAllDepartments(String deptName,int start,int limit);
	/**
	 * Description:查询公司所有部门<br />
	 * @version 0.1 2013-5-9
	 * @param start
	 * @param limit
	 * @return List<Department>
	 */
	public List<String> getAllSignCompanys(String companyName,int start,int limit);
	
	/**
	 * Description:查询公司所有部门<br />
	 * @version 0.1 2013-5-9
	 * @param start
	 * @param limit
	 * @return List<Department>
	 */
	int countAllDepartments(String deptName);
	/**
	 * 
	 * @Title: countAllDepartments
	 *  <p>
	 * @Description: count所有合同签署公司<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-11-16
	 * @return int
	 * @throws
	 */
	int countAllSignCompanys(String deptName);
	/**
	 * <p>
	 * Description:获取营业部经理拥有的营业部权限<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-20
	 * @param standardCode
	 * @return
	 * List<String>
	 */
	public List<Department> getAuthorityBusinessDept(String standardCode);
	/**
	 * <p>
	 * Description:根据营业部标杆编码查询点部<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-24
	 * @param standardCode
	 * @return
	 * List<Department>
	 */
	public List<Department> getPointDepartment(String standardCode);
	/**
	 * <p>
	 * Description:获取客户管理组部门<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-5-13
	 * @return
	 * Department
	 */
	public Department getCustManageDepartment();
}
