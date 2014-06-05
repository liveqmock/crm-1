package com.deppon.crm.module.uums.server.manager.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.sync.server.manager.ISyncManager;
import com.deppon.crm.module.sync.shared.SyncException;
import com.deppon.crm.module.uums.server.manager.ISyncUumsDataManager;
import com.deppon.crm.module.uums.server.service.IEmployeeService;
import com.deppon.crm.module.uums.server.service.IOrgService;
import com.deppon.crm.module.uums.shared.domain.BaseUUEntity;
import com.deppon.crm.module.uums.shared.domain.EmployeeInfo;
import com.deppon.crm.module.uums.shared.domain.OrgInfo;
/**
 * 
 * @description CRM同步uums 员工数据实现类
 * @author zzw
 * @version 0.1 2013-11-25
 */
public class SyncUumsEmployeeInfoManager implements ISyncUumsDataManager {
	private IEmployeeService employeeService;
	private IOrgService orgService;
	private ISyncManager empSync;
	//插入
	public static final String INSERT="1";
	//修改
	public static final String UPDATE="2";
	//删除
	public static final String DELETE="3";
	//返聘
	public static final String REUSED="4";
	public IEmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public IOrgService getOrgService() {
		return orgService;
	}
	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}
	
	public ISyncManager getEmpSync() {
		return empSync;
	}
	public void setEmpSync(ISyncManager empSync) {
		this.empSync = empSync;
	}
	/**
	 * 员工数据新增
	 * @param BaseUUEntity 
	 * 
	 */
	public int insert(BaseUUEntity entity) {
		EmployeeInfo employee=(EmployeeInfo)entity;
		int res=employeeService.insert(employee);
		return res;
	}
	/**
	 * 员工数据更新
	 * @param BaseUUEntity 
	 * 
	 */
	public int update(BaseUUEntity entity) {
		String empCode=entity.getTheMainCode();
		EmployeeInfo employee=(EmployeeInfo)entity;
	    int res=employeeService.updateByCode(employee, empCode);
		return res;
	}
	/**
	 * 员工数据删除
	 * @param BaseUUEntity 
	 * 
	 */
	public int delete(BaseUUEntity entity) {
		String empCode=entity.getTheMainCode();
		String status=((EmployeeInfo)entity).getStatus();
		Map<String,String> m=new HashMap<String,String>();
		//status userCode
		m.put("userCode", empCode);
		m.put("status", status);
		int res=employeeService.deleteByCode(m);
		return res;

	}
	/**
	 * 数据校验	
	 * @param BaseUUEntity 
	 * 
	 */
	@Transactional
	public int check(BaseUUEntity entity) {
		String empCode=entity.getTheMainCode();
		String changeType=entity.getChangeType();
		EmployeeInfo employee=(EmployeeInfo)entity;
		String status=employee.getStatus();
		String position=employee.getPosition();
		String orgCode=employee.getDeptBenchmarkCode();
		OrgInfo dep=orgService.searchByCode(orgCode);
		String orgId="";
		if(dep!=null){
			orgId=dep.getTheMainId();
		}else{
				throw new SyncException("the department code "+orgCode+" is not exsits");
		}
		EmployeeInfo employeeInfo=employeeService.searchByCode(empCode);
		int res=0;
		if(employeeInfo!=null){
			res=1;
		}
		if(INSERT.equals(changeType)||UPDATE.equals(changeType)){
			if(res==0){
				res=employeeService.insert(employee);
			}else{
				//存在即更新
				res=employeeService.updateByCode(employee, empCode);
			}
		}else if(DELETE.equals(changeType)){
			if(res>0){
				Map<String,String> m=new HashMap<String,String>();
				//status userCode
				m.put("employeeCode", empCode);
				m.put("status", status);
				res=employeeService.deleteByCode(m);
			}else{
				return SUCCESS;
			}
		}else if(REUSED.equals(changeType)){
			if(res>0){
				//存在即更新
				res=employeeService.reuseByCode((EmployeeInfo)entity);
			}else{
				//不存在则插入
				res=employeeService.insert(employee);
			}
		}else{
			throw new SyncException("the changType"+changeType+"is NOT defined");
		}
		Map<String,String> tmp=new HashMap<String,String>();
		tmp.put(ISyncManager.CHANGETYPE, changeType);
		tmp.put(ISyncManager.MAINCODE, empCode);
		tmp.put(ISyncManager.POSITION, position);
		tmp.put(ISyncManager.ORGID, orgId);
		empSync.check(tmp);
		return SUCCESS;
	}

}
