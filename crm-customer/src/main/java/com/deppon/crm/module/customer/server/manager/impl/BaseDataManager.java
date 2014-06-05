package com.deppon.crm.module.customer.server.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jfree.util.Log;

import com.deppon.crm.module.authorization.server.service.IAuthorizeService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager;
import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.server.service.IBaseDataService;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
/**
 * 
 * <p>
 * Description:基础数据管理<br />
 * </p>
 * @title BaseDataManager.java
 * @package com.deppon.crm.module.customer.server.manager.impl 
 * @author 106138
 * @version 0.1 2014年4月17日
 */
public class BaseDataManager implements IBaseDataManager{

	protected final static String MemberFunction = "MemberFunction";
	//疑似重复客户
	protected final static String FunctionRepeatCust = "FunctionRepeatCust";
	//散客
	protected final static String ScatterCustomerFunction = "ScatterCustomerFunction";
	//合同
	protected final static String ContractFunction = "ContractFunction";
	//潜客
	protected final static String PotentialCustomerFunction = "PotentialCustomerFunction";
	//部门service
	private IDepartmentService departmentService;
	//员工
	private IEmployeeService employeeService;
	//基础数据
	private IBaseDataService baseDataService;
	//权限
	private IAuthorizeService authorizeService;
	//部门manager
	private ILadingstationDepartmentManager departmentManager;
	/**
	 * 疑似重复客户
	 */
	private IRepeatedCustManager repeatedCustManager;
	/**
	 * 
	 * <p>
	 * Description:获得事业部总裁<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 * @param childDept
	 * @return
	 * Department
	 */
	public Department getParentBusinessDivision(Department childDept) {
		String departSeq = childDept.getDeptSeq();
		String[] parentIds = departSeq.split("\\.");
		for (String parentId : parentIds) {
			if(ValidateUtil.objectIsEmpty(parentId)){
				continue;
			}
			Employee employee = createQueryEmployee(parentId);
			List<Employee> employeeList = employeeService.queryAll(employee);
			if(isHasEndJobNameEmployee(employeeList,"事业部总裁")){
				return departmentService.queryById(parentId);
			}
		}
		return null;
	}

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
	@Override
	public Department getBusinessDivesionOfficeMarketingGroup(Department childDept){
		Department businessDivesion = getParentBusinessDivision(childDept);
		if(businessDivesion == null) {
			return null;
		}
		List<Department> departments = departmentService.queryAllChildDeptByDeptId(businessDivesion.getId());
		for (Department department : departments) {
			if(department.getDeptName().contains("事业部营销管理组")){
				return department;
			}
		}
		return null;
	}

	/**
	 *
	 * <p>
	 * 是否拥有jobName等于<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-14
	 * @param employeeList
	 * @param jobName
	 * @return
	 * boolean
	 */
	private boolean isHasEndJobNameEmployee(List<Employee> employeeList,
			String jobName) {
		for (Employee employee : employeeList) {
			if(employee.getPosition() != null &&  employee.getPosition().endsWith(jobName)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * <p>
	 * Description:创建查询使用的员工对象<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 * @param parentId
	 * @return
	 * Employee
	 */
	private Employee createQueryEmployee(String parentId) {
		Employee employee = new Employee();
		Department parentDepart = new Department();
		parentDepart.setId(parentId);
		employee.setDeptId(parentDepart);
		return employee;
	}
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
	@Override
	public Department getCustRelationsDepartment() {
		//客户管理组 id 180773
		return departmentService.queryById(Constant.MarketingId);
	}

	/**
	 * <p>
	 * Description:departmentService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	/**
	 * <p>
	 * Description:departmentService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * <p>
	 * Description:employeeService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	/**
	 * <p>
	 * Description:employeeService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * <p>
	 * Description:baseDataService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public IBaseDataService getBaseDataService() {
		return baseDataService;
	}

	/**
	 * <p>
	 * Description:baseDataService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public void setBaseDataService(IBaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	/**
	 * <p>
	 * Description:authorizeService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public IAuthorizeService getAuthorizeService() {
		return authorizeService;
	}

	/**
	 * <p>
	 * Description:authorizeService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public void setAuthorizeService(IAuthorizeService authorizeService) {
		this.authorizeService = authorizeService;
	}

	/**
	 * <p>
	 * Description:departmentManager<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public ILadingstationDepartmentManager getDepartmentManager() {
		return departmentManager;
	}

	/**
	 * <p>
	 * Description:departmentManager<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public void setDepartmentManager(ILadingstationDepartmentManager departmentManager) {
		this.departmentManager = departmentManager;
	}

	/**
	 * <p>
	 * Description:repeatedCustManager<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public IRepeatedCustManager getRepeatedCustManager() {
		return repeatedCustManager;
	}

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
	@Override
	public Department getRegionDepartment(String deptId) {
		return getParentDepartment(deptId,"大区");
	}

	private Department getParentDepartment(String deptId,String endName){
		Department childDept = departmentService.queryById(deptId);
		String departSeq = childDept.getDeptSeq();
		String[] parentIds = departSeq.split("\\.");
		for (String parentId : parentIds) {
			if(ValidateUtil.objectIsEmpty(parentId)){
				continue;
			}
			Department parentDept = departmentService.queryById(parentId);
			if(parentDept.getDeptName().endsWith(endName)){
				return parentDept;
			}
		}
		return null;
	}

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
	@Override
	public Department getNeighborhoodDepartment(String deptId) {
		return getParentDepartment(deptId,"营业区");

	}
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
	@Override
	public Department getCauseDepartment(String deptId) {
		return getParentDepartment(deptId,"事业部");

	}
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
	@Override
	public Department getDeptById(String id){
		return departmentService.queryById(id);
	}

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
	@Override
	public Department getDeptByStandardCode(String standardCode){
		return departmentService.getDeptByStandardCode(standardCode);
	}

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
	@Override
	public List<Map<String, String>> getAccountBank() {
		return baseDataService.getAccountBank();
	}
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
	@Override
	public List<Map<String, String>> getBankCityByBankProvinceId(
			String bankprovince) {
		return baseDataService.getBankCityByBankProvinceId(bankprovince);
	}

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
	@Override
	public List<Map<String, String>> getBankProvince() {
		return baseDataService.getBankProvince();
	}
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
	@Override
	public List<Department> getMyAuthDataDeptsByFunction(String functionName,int start,int limit){
		//根据功能走不同的权限
		List<Department> list = null;

		if(MemberFunction.equals(functionName)){
			list = getMemberAuthData(start,limit);
		} else if (ScatterCustomerFunction.equals(functionName)) {
			//加载散客查询界面中的所属部门
			list = getScatterCustomerAuthData(start, limit);
		} else if (ContractFunction.equals(functionName)) {
			//快递经理对应营业部权限
			list = baseDataService.searchAuthorityBusinessDeptByName(null, start, limit);
		}else if(PotentialCustomerFunction.equals(functionName)){
			list=getPotentialCustomerAuthData(start,limit);
		}else if (FunctionRepeatCust.equals(functionName)){
			//疑似重复所属部门查询
			list = repeatedCustManager.searchRepeatCustDeptList(null, start, limit);
		}
		if ((null==list||0>=list.size())&&validateNotExpressPosition()) {
			list = getNorMalAuthData(start,limit);
		}
		return list;
	}


	/**
	 *
	 * <p>
	 * 加载潜客查询中的所属部门，考虑到快递岗位情况<br />
	 * </p>
	 * @author 吴根斌
	 * @version 0.2 2014-2-17
	 * @param functionName
	 * @return
	 * List<Department>
	 */
	private List<Department> getPotentialCustomerAuthData(int start, int limit){
		String deptId = ContextUtil.getCurrentUserDeptId();
		String deptName = ContextUtil.getCurrentUserDept().getDeptName();
		User user = ContextUtil.getCurrentUser();
		String position = user.getEmpCode().getPosition();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		List<Department> executeDeplist = new ArrayList<Department>();
		if (StringUtils.isNotEmpty(Constant.EXPRESS_POSITIONS.get(position))
				|| Constant.EXPRESS_MARKETING_GROUP_STANDARDCODE
						.equals(deptCode)) {
			executeDeplist = authorizeService.getAllDeptMappedExpress(
					user.getId(), deptName, deptCode, start, limit);
		}
		return executeDeplist;
	}


	/**
	 *
	 * <p>
	 * 加载散客查询中的所属部门，考虑到快递岗位情况<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-11
	 * @param functionName
	 * @return
	 * List<Department>
	 */
	private List<Department> getScatterCustomerAuthData(int start, int limit){
		String deptId = ContextUtil.getCurrentUserDeptId();
		String deptName = ContextUtil.getCurrentUserDept().getDeptName();
		User user = ContextUtil.getCurrentUser();
		String position = user.getEmpCode().getPosition();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		List<Department> executeDeplist = new ArrayList<Department>();
		if (StringUtils.isNotEmpty(Constant.EXPRESS_POSITIONS.get(position))
				|| Constant.EXPRESS_MARKETING_GROUP_STANDARDCODE
						.equals(deptCode)) {
			executeDeplist = authorizeService.getAllDeptMappedExpress(
					user.getId(), deptName, deptCode, start, limit);
		} else {
			executeDeplist = authorizeService
					.getWaybillRightsDepartmentsByDeptId(deptId, start, limit);
		}
		return executeDeplist;
	}

	private List<Department> getMemberAuthData(int start, int limit){
		String deptId = getMyAuthDeptId();
		String deptName = ContextUtil.getCurrentUserDept().getDeptName();
		User user = ContextUtil.getCurrentUser();
		String position = user.getEmpCode().getPosition();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		List<Department> executeDeplist = new ArrayList<Department>();
		if (StringUtils.isNotEmpty(Constant.EXPRESS_POSITIONS.get(position))
				|| Constant.EXPRESS_MARKETING_GROUP_STANDARDCODE
						.equals(deptCode)) {
			executeDeplist = authorizeService.getAllDeptMappedExpress(
					user.getId(), deptName, deptCode, start, limit);
		} else{
			executeDeplist=authorizeService.getAllleafByParentId(deptId, start, limit);
		}
		return executeDeplist;
	}

	private List<Department> getNorMalAuthData(int start, int limit){
		String userId = ContextUtil.getCurrentUser().getId();
		return authorizeService.getAllDepartmentByUserId(userId, start, limit);
	}

	private String getMyAuthDeptId() {
		Set<String> roles = ContextUtil.getRoles();
			if(roles.contains(Constant.Cause)){
				return getCurrentUserDeptId();
			}
			if(roles.contains(Constant.Neighborhood)){
				return getCurrentUserDeptId();
			}
			if(roles.contains(Constant.Region)){
				return getCurrentUserDeptId();
			}
				//全公司的权限 是不是 就等于 营运副总裁 的全部权限
			if(roles.contains(Constant.Marketing)){
				//营运副总裁的部门id 为265
				//"464252,464253,464254"
				return Constant.CUSTOMER_ALLAUTHORIZE_DEPARTMENT;
			}

			if(roles.contains(Constant.BizManager)){
				return getCurrentUserDeptId();
			}
		return ContextUtil.getCurrentUserDept().getId();
	}

	@SuppressWarnings("unused")
	private String getCurrentUserParentDeptId() {
		Department department = ContextUtil.getCurrentUserDept();
		String seq = department.getDeptSeq();
		String[] ids = seq.split("\\.");
		//最后的第二位 是父部门id
		if(ids.length < 2){
			Log.error(department.getId()+"has error seq :" +seq);
			return null;
		}
		String parentId = ids[ids.length-2];
		return parentId;
	}

	private String getCurrentUserDeptId(){
		Department department = ContextUtil.getCurrentUserDept();
		return department.getId();
	}
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
	@Override
	public List<Department> getMyAuthDataDeptsByFunction(String functionName,
			String deptName, int start, int limit) {
		//根据功能走不同的权限
		List<Department> list = null;

		if(MemberFunction.equals(functionName)){
			list = getMemberAuthData(deptName,start,limit);
		} else if (ScatterCustomerFunction.equals(functionName)) {
			//散客新增。开单组权限
			list = getScatterCustomerAuthData(deptName, start, limit);
		} else if (ContractFunction.equals(functionName)) {
			/**
			 * @description:增加快递经理合同界面营业部对应权限
			 */
			list = baseDataService.searchAuthorityBusinessDeptByName(deptName, start, limit);
		} else if(PotentialCustomerFunction.equals(functionName)){
			list=getPotentialCustomerAuthData(deptName,start,limit);
		} else if (FunctionRepeatCust.equals(functionName)){
			//疑似重复所属部门查询
			list = repeatedCustManager.searchRepeatCustDeptList(deptName, start, limit);
		}
		if (null==list||0>=list.size()) {
			if (validateNotExpressPosition()){
				list = getNorMalAuthData(deptName,start,limit);
		    }
		}
		return list;
	}


	private boolean validateNotExpressPosition(){
		User user = ContextUtil.getCurrentUser();
		String position = user.getEmpCode().getPosition();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		return !(StringUtils.isNotEmpty(Constant.EXPRESS_POSITIONS.get(position))
				|| Constant.EXPRESS_MARKETING_GROUP_STANDARDCODE
				.equals(deptCode));
	}


	private List<Department> getPotentialCustomerAuthData(String deptName, int start, int limit) {
		User user = ContextUtil.getCurrentUser();
		String position = user.getEmpCode().getPosition();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		List<Department> executeDeplist = new ArrayList<Department>();
		if (StringUtils.isNotEmpty(Constant.EXPRESS_POSITIONS.get(position))
				|| Constant.EXPRESS_MARKETING_GROUP_STANDARDCODE
						.equals(deptCode)) {
			executeDeplist = authorizeService.getAllDeptMappedExpress(
					user.getId(), deptName, deptCode, start, limit);
		}
		return executeDeplist;
	}


	private List<Department> getScatterCustomerAuthData(String deptName, int start, int limit) {
		String deptId = ContextUtil.getCurrentUserDeptId();
		User user = ContextUtil.getCurrentUser();
		String position = user.getEmpCode().getPosition();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		List<Department> executeDeplist = new ArrayList<Department>();
		if (StringUtils.isNotEmpty(Constant.EXPRESS_POSITIONS.get(position))
				|| Constant.EXPRESS_MARKETING_GROUP_STANDARDCODE
						.equals(deptCode)) {
			executeDeplist = authorizeService.getAllDeptMappedExpress(
					user.getId(), deptName, deptCode, start, limit);
		} else {
			executeDeplist = authorizeService
					.getWaybillRightsDepartmentsByDeptName(deptId, deptName,
							start, limit);
		}
		return executeDeplist;
	}

	private List<Department> getNorMalAuthData(String deptName, int start,
			int limit) {
		String userId = ContextUtil.getCurrentUser().getId();
		return authorizeService.getAllDepartmentByUserIdAndLikeDeptName(userId, deptName, start, limit);
	}

	private List<Department> getMemberAuthData(String deptName, int start,
			int limit) {
		String deptId = getMyAuthDeptId();
		User user = ContextUtil.getCurrentUser();
		String position = user.getEmpCode().getPosition();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		List<Department> executeDeplist = new ArrayList<Department>();
		if (StringUtils.isNotEmpty(Constant.EXPRESS_POSITIONS.get(position))
				|| Constant.EXPRESS_MARKETING_GROUP_STANDARDCODE
						.equals(deptCode)) {
			executeDeplist = authorizeService.getAllDeptMappedExpress(
					user.getId(), deptName, deptCode, start, limit);
		} else {
			executeDeplist = authorizeService
					.getAllleafByParentIdAndLikeDeptName(deptId, deptName,
							start, limit);
		}
		return executeDeplist;
	}
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
	@Override
	public int countMyAuthDataDeptsByFunction(String functionName,
			String deptName) {
		//根据功能走不同的权限
		int total = 0;

		if(MemberFunction.equals(functionName)){
			total = countMemberAuthData(deptName);
		} else if (ScatterCustomerFunction.equals(functionName)) {
			//返回权限总数--散客新增。开单组权限
			total = countScatterCustomerAuthData(deptName);
			if (0 == total&&validateNotExpressPosition()) {
				total = countNorMalAuthData(deptName);
			}
		} else if (ContractFunction.equals(functionName)) {
			//返回权限总数--散客新增。开单组权限
			total = baseDataService.countAuthorityBusinessDeptByName(deptName);
			if (0 == total&&validateNotExpressPosition()) {
				total = countNorMalAuthData(deptName);
			}
		}else if(PotentialCustomerFunction.equals(functionName)){
			total=countPotentialCustomerAuthData(deptName);
			if (0 == total&&validateNotExpressPosition()) {
				total = countNorMalAuthData();
			}
		} else if (FunctionRepeatCust.equals(functionName)) {
			//疑似重复客户- 所属部门
			total = repeatedCustManager.searchRepeatCustDeptCount(deptName);
		} else{
			if (validateNotExpressPosition()){
				total = countNorMalAuthData(deptName);
		    }
		}

		return total;

	}

	private int countPotentialCustomerAuthData(String deptName) {
		User user = ContextUtil.getCurrentUser();
		String position = user.getEmpCode().getPosition();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		int total=0;
		if (StringUtils.isNotEmpty(Constant.EXPRESS_POSITIONS.get(position))
				|| Constant.EXPRESS_MARKETING_GROUP_STANDARDCODE
						.equals(deptCode)) {
			total = authorizeService.countAllDeptMappedExpress(user.getId(), deptName,deptCode);
		}
		return total;
	}

	private int countScatterCustomerAuthData(String deptName) {
		String deptId = ContextUtil.getCurrentUserDeptId();
		User user = ContextUtil.getCurrentUser();
		String position = user.getEmpCode().getPosition();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		int total=0;
		if (StringUtils.isNotEmpty(Constant.EXPRESS_POSITIONS.get(position))
				|| Constant.EXPRESS_MARKETING_GROUP_STANDARDCODE
						.equals(deptCode)) {
			total = authorizeService.countAllDeptMappedExpress(user.getId(), deptName,deptCode);
		} else {
			total=authorizeService.countAllDepartmentsByDeptName(deptId,deptName);
		}
		return total;
	}

	private int countNorMalAuthData(String deptName) {
		String userId = ContextUtil.getCurrentUser().getId();
		return authorizeService.countAllDepartmentByUserIdAndLikeDeptName(userId, deptName);
	}

	private int countMemberAuthData(String deptName) {
		String deptId = getMyAuthDeptId();
		User user = ContextUtil.getCurrentUser();
		String position = user.getEmpCode().getPosition();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		int total=0;
		if (StringUtils.isNotEmpty(Constant.EXPRESS_POSITIONS.get(position))
				|| Constant.EXPRESS_MARKETING_GROUP_STANDARDCODE
						.equals(deptCode)) {
			total = authorizeService.countAllDeptMappedExpress(user.getId(), deptName,deptCode);
		}else{
			total=authorizeService.countAllleafByParentIdAndLikeDeptName(deptId, deptName);
		}
		return total;
	}

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
	@Override
	public int countMyAuthDataDeptsByFunction(String functionName) {
		//根据功能走不同的权限
		int total = 0;
		if(MemberFunction.equals(functionName)){
			total = countMemberAuthData();
		} else if (ScatterCustomerFunction.equals(functionName)) {
			//返回权限总数--散客新增。开单组权限
			total = countScatterCustomerAuthData();
			if (0 == total&&validateNotExpressPosition()) {
				total = countNorMalAuthData();
			}
		}else if(PotentialCustomerFunction.equals(functionName)){
			total=countPotentialCustomerAuthData();
			if (0 == total&&validateNotExpressPosition()) {
				total = countNorMalAuthData();
			}
		}else if (FunctionRepeatCust.equals(functionName)) {
			//疑似重复客户- 所属部门
			total = repeatedCustManager.searchRepeatCustDeptCount(null);
		}else{
			if(validateNotExpressPosition()){
				total = countNorMalAuthData();
			}
		}
		return total;
	}

	/**
	 *
	 * <p>
	 * 统计总数<br />
	 * </p>
	 * @author 吴根斌
	 * @version 0.2 2014-2-17
	 * @param functionName
	 * @return
	 * int
	 */
	private int countScatterCustomerAuthData() {
		String deptName = ContextUtil.getCurrentUserDept().getDeptName();
		String deptId = ContextUtil.getCurrentUserDeptId();
		User user = ContextUtil.getCurrentUser();
		String position = user.getEmpCode().getPosition();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		int total=0;
		if (StringUtils.isNotEmpty(Constant.EXPRESS_POSITIONS.get(position))
				|| Constant.EXPRESS_MARKETING_GROUP_STANDARDCODE
						.equals(deptCode)) {
			total = authorizeService.countAllDeptMappedExpress(user.getId(), deptName,deptCode);
		} else {
			total=authorizeService.countAllDepartmentsByDeptId(deptId);;
		}
		return total;
	}
	/**
	 * 
	 * <p>
	 * Description:统计潜客权限部门条数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 * @return
	 * int
	 */
	private int countPotentialCustomerAuthData() {
		String deptName = ContextUtil.getCurrentUserDept().getDeptName();
		User user = ContextUtil.getCurrentUser();
		String position = user.getEmpCode().getPosition();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		int total=0;
		if (StringUtils.isNotEmpty(Constant.EXPRESS_POSITIONS.get(position))
				|| Constant.EXPRESS_MARKETING_GROUP_STANDARDCODE
						.equals(deptCode)) {
			total = authorizeService.countAllDeptMappedExpress(user.getId(), deptName,deptCode);
		}
		return total;
	}

	private int countNorMalAuthData() {
		String userId = ContextUtil.getCurrentUser().getId();
		return authorizeService.countAllDepartmentByUserId(userId);
	}
	// 根据部门ID获取散客数据总数
	private int countMemberAuthData() {
		String deptId = getMyAuthDeptId();
		String deptName = ContextUtil.getCurrentUserDept().getDeptName();
		User user = ContextUtil.getCurrentUser();
		String position = user.getEmpCode().getPosition();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		int total=0;
		if (StringUtils.isNotEmpty(Constant.EXPRESS_POSITIONS.get(position))
				|| Constant.EXPRESS_MARKETING_GROUP_STANDARDCODE
						.equals(deptCode)) {
			total = authorizeService.countAllDeptMappedExpress(user.getId(), deptName,deptCode);
		}else{
			total=authorizeService.countAllleafByParentId(deptId);
		}
		return total;
	}
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
	@Override
	public List<String> getDataAuthorityDepts(String dept) {
		// 获得本人的数据权限
		String deptId = getMyAuthDeptId();
		if (Constant.CUSTOMER_ALLAUTHORIZE_DEPARTMENT.equals(deptId)) {
			List<String> returnDeptIds = new ArrayList<String>();
			returnDeptIds.add(dept);
			return returnDeptIds;
		}
		List<Department> list = departmentService.queryAllChildDeptByDeptId(deptId);
		// 拥有的数据权限
		List<String> deptIds = new ArrayList<String>();
		for (Department department : list) {
			deptIds.add(department.getId());
		}
		// 根据快递职位判断部门数据权限
		User user = ContextUtil.getCurrentUser();
		String position = user.getEmpCode().getPosition();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		List<Department> authDeplist = new ArrayList<Department>();
		if (StringUtils.isNotEmpty(Constant.EXPRESS_POSITIONS.get(position))
				|| Constant.EXPRESS_MARKETING_GROUP_STANDARDCODE
						.equals(deptCode)) {
			authDeplist = authorizeService.getAllDeptMappedExpress(
					user.getId(), null, deptCode, 0, 10000);
		}
        for(Department authDept : authDeplist){
        	String depId=authDept.getId();
        	deptIds.add(depId);
        }



		// 业务规则的特殊处理-----当前台传过来的部门为空时，默认前台查询的部门为数据权限部门，故为空权限是合法的
		if (!ValidateUtil.objectIsEmpty(dept)) {
			// 验证是否具有数据权限
			if (!deptIds.contains(dept)) {
				throw new MemberException(MemberExceptionType.NoAuthority);
			}else{
				//只有一个的话，返回其本身
				List<String> returnDeptIds = new ArrayList<String>();
				returnDeptIds.add(dept);
				return returnDeptIds;
			}
		}else{
			//TODO 待完善
			//当部门的长度超过1000时，认为他拥有全公司的全部权限
			if(deptIds.size() > 1000){
				return new ArrayList<String>();
			}else{
				return deptIds;
			}
		}
	}
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
	@Override
	public List<String> hasDataAuthorityDepts(String dept) {
		// 获得本人的数据权限
		List<String> deptIds = ContextUtil.getCurrentUserDataAuthorityDept();
		User user = ContextUtil.getCurrentUser();
		String position = user.getEmpCode().getPosition();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		List<Department> authDeplist = new ArrayList<Department>();
		if (StringUtils.isNotEmpty(Constant.EXPRESS_POSITIONS.get(position))
				|| Constant.EXPRESS_MARKETING_GROUP_STANDARDCODE
						.equals(deptCode)) {
			authDeplist = authorizeService.getAllDeptMappedExpress(
					user.getId(), null, deptCode, 0, 10000);
		}
        for(Department authDept : authDeplist){
        	String depId=authDept.getId();
        	deptIds.add(depId);
        }
		if (!ValidateUtil.objectIsEmpty(dept)) {
			if (!deptIds.contains(dept)) {
				throw new MemberException(MemberExceptionType.NoAuthority);
			} else {
				// 只有一个的话，返回其本身
				List<String> returnDeptIds = new ArrayList<String>();
				returnDeptIds.add(dept);
				return returnDeptIds;
			}
		}else{
			// 业务规则的特殊处理-----当前台传过来的部门为空时，默认前台查询的部门为数据权限部门，故为空权限是合法的
			//再次和需求确认 如果前台传过来的部门为空时  不允许查询 抛出异常
			throw new MemberException(MemberExceptionType.NoAuthority);
		}

	}

	/**
	 * <p>
	 * Description:true表示是香港，false表示不是香港<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-1-21
	 * @return
	 * boolean
	 */
	@Override
	public boolean isHongKongOrMainLand() {
		boolean flag = false;
		String deptCityLocation = departmentManager.getDeptCityLocation(
				ContextUtil.getCurrentUserDeptId());
		if (Constant.ISHONGKONG_STRING.equals(deptCityLocation)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public String getDeptCityLocation(String deptId) {
		return departmentManager.getDeptCityLocation(deptId);
	}
	@Override
	public List<Department> getAllDepartments(String deptName,int start, int limit) {
		return authorizeService.getAllDepartments(deptName,start,limit);
	}
	@Override
	public List<String> getAllSignCompanys(String companyName,int start, int limit) {
		return authorizeService.getAllSignCompanys(companyName,start,limit);
	}

	@Override
	public int countAllDepartments(String deptName) {
		return authorizeService.countAllDepartments(deptName);
	}
	@Override
	public int countAllSignCompanys(String companyName) {
		return authorizeService.countAllSignCompanys(companyName);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.manager.IBaseDataManager#getAuthorityBusinessDept(java.lang.String)
	 */
	@Override
	public List<Department> getAuthorityBusinessDept(String standardCode) {
		return baseDataService.searchAuthorityBusinessDept(standardCode);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.manager.IBaseDataManager#getPointDepartment(java.lang.String)
	 */
	@Override
	public List<Department> getPointDepartment(String standardCode) {
		return baseDataService.searchPointDepartment(standardCode);
	}

	/**
	 * @param repeatedCustManager the repeatedCustManager to set
	 */
	public void setRepeatedCustManager(IRepeatedCustManager repeatedCustManager) {
		this.repeatedCustManager = repeatedCustManager;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.manager.IBaseDataManager#getCustManageDepartment()
	 */
	@Override
	public Department getCustManageDepartment() {
		//客户管理组 id 180773
		return departmentService.queryById(Constant.CustomerManage);
	}
    
}
