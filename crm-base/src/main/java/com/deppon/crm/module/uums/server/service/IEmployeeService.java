package com.deppon.crm.module.uums.server.service;

import java.util.Map;

import com.deppon.crm.module.uums.shared.domain.EmployeeInfo;
public interface IEmployeeService {
	/**
	 * 员工数据新增
	 * @param BaseUUEntity 
	 * 
	 */
	public int insert(EmployeeInfo entity);
	/**
	 * 员工数据更新
	 * @param BaseUUEntity 
	 * 
	 */
	public int updateById(EmployeeInfo entity,String employeeID);
	public int updateByCode(EmployeeInfo entity,String employeeCode);
	/**
	 * 员工数据删除
	 * @param BaseUUEntity 
	 * 
	 */
	public int deleteById(String employeeID) ;
	public int deleteByCode(Map<String,String> empMap) ;
	/**
	 * 数据校验	
	 * @param BaseUUEntity 
	 * 
	 */
	public EmployeeInfo searchById(String employeeID) ;
	public EmployeeInfo searchByCode(String employeeCode) ;
	//返聘
	public int reuseByCode(EmployeeInfo entity) ;
	public int reuseById(String employeeId) ;

}
