package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * 
 * <p>
 * 数据权限action<br />
 * </p>
 * @title DataAuthAction.java
 * @package com.deppon.crm.module.customer.server.action 
 * @author bxj
 * @version 0.2 2012-7-12
 */
public class DataAuthAction extends AbstractAction{
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -4065064868712212020L;

	//基础数据管理manager
	private IBaseDataManager baseDataManager;

	private String deptName;
	
	private String functionName;
	
	private int total;
	
	private int start;
	
	private int limit;
	
	//合同签署公司@chenaichun
	private String signCompany;
	//合同签署公司集合
	private List<Map<String,String>> signCompanyList = new ArrayList<Map<String,String>>();
	
	//当前登录用户所属部门集合
	private List<Map<String,String>> currentUserDeptList = new ArrayList<Map<String,String>>();

	/***
	 * 
	 * <p>
	 * Description:获取当前登录用户所属部门集合信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-2-2
	 * @return
	 * String
	 */
	public String searchMyDataAuth(){
		List<Department> list = null;
		if(ValidateUtil.objectIsEmpty(deptName)){
			list = baseDataManager.getMyAuthDataDeptsByFunction(functionName, start, limit);
			total = baseDataManager.countMyAuthDataDeptsByFunction(functionName);
		}else{
			list = baseDataManager.getMyAuthDataDeptsByFunction(functionName,deptName, start, limit);
			total = baseDataManager.countMyAuthDataDeptsByFunction(functionName,deptName);
		}
		for(Department department:list){
			Map<String,String> map = new HashMap<String,String>();
			map.put("deptId", department.getId());
			map.put("deptName", department.getDeptName());
			currentUserDeptList.add(map);
		}
		return "success";
	}
	
	/**
	 * Description:查询全公司部门<br />
	 * @version 0.1 2013-11
	 * @return String
	 */
	public String searchAllDepartments() {
		List<Department> list = null;
		if(StringUtils.isEmpty(deptName)) {
			list = baseDataManager.getAllDepartments(null, start, limit);
			total = baseDataManager.countAllDepartments(deptName);
		}else {
			list = baseDataManager.getAllDepartments(deptName, start, limit);
			total = baseDataManager.countAllDepartments(deptName);
		}
		for(Department department:list){
			Map<String,String> map = new HashMap<String,String>();
			map.put("deptCode", department.getStandardCode());
			map.put("deptName", department.getDeptName());
			currentUserDeptList.add(map);
		}
		return "success";
	}
	
	/**
	 * Description:查询全部合同签署公司<br />
	 * @version 0.1 2013-5-8
	 * @return String
	 */
	public String searchAllSignCompany() {
		List<String> list = null;
		if(StringUtils.isEmpty(signCompany)) {
			list = baseDataManager.getAllSignCompanys(null, start, limit);
			total = baseDataManager.countAllSignCompanys(signCompany);
		}else {
			list = baseDataManager.getAllSignCompanys(signCompany, start, limit);
			total = baseDataManager.countAllSignCompanys(signCompany);
		}
		for(String company:list){
			Map<String,String> map = new HashMap<String,String>();
			map.put("signCompany", company);
			signCompanyList.add(map);
		}
		return "success";
	}
	
	public void setBaseDataManager(IBaseDataManager baseDataManager) {
		this.baseDataManager = baseDataManager;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public String getFunctionName() {
		return functionName;
	}


	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public int getStart() {
		return start;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public int getLimit() {
		return limit;
	}


	public void setLimit(int limit) {
		this.limit = limit;
	}


	public List<Map<String, String>> getCurrentUserDeptList() {
		return currentUserDeptList;
	}


	public void setCurrentUserDeptList(List<Map<String, String>> currentUserDeptList) {
		this.currentUserDeptList = currentUserDeptList;
	}

	/**
	 *@author chenaichun
	 * @date 2013-11-28 下午12:18:24 
	 *@return the signCompany
	 */
	public String getSignCompany() {
		return signCompany;
	}

	/**
	 *@author chenaichun
	 * @date 2013-11-28 下午12:18:24 
	 * @param signCompany the signCompany to set
	 */
	public void setSignCompany(String signCompany) {
		this.signCompany = signCompany;
	}

	/**
	 *@author chenaichun
	 * @date 2013-11-28 下午12:18:24 
	 *@return the signCompanyList
	 */
	public List<Map<String, String>> getSignCompanyList() {
		return signCompanyList;
	}

	/**
	 *@author chenaichun
	 * @date 2013-11-28 下午12:18:24 
	 * @param signCompanyList the signCompanyList to set
	 */
	public void setSignCompanyList(List<Map<String, String>> signCompanyList) {
		this.signCompanyList = signCompanyList;
	}
	
	
	
}
