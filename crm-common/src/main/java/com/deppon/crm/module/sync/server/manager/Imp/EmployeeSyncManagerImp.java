package com.deppon.crm.module.sync.server.manager.Imp;

import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.sync.server.manager.ISyncManager;
import com.deppon.crm.module.sync.shared.SyncException;

public class EmployeeSyncManagerImp  implements ISyncManager{
	private IEmployeeService employeeService;
	public static final String INSERT="1";
	//修改
	public static final String UPDATE="2";
	//删除
	public static final String DELETE="3";
	//返聘
	public static final String REUSED="4";
	public static final String AREALEADER="营业区区域经理";
	public static final String BIGAREALEADER="大区总经理";
	@Override
	public int check(Map<String,String> entity) {
		String changeType=entity.get(CHANGETYPE);
		String empCode=entity.get(MAINCODE);
		String position=entity.get(POSITION);
		String orgId=entity.get(ORGID);
		String oldPosition="";
		String oldOrgId="";
		Employee emp= employeeService.getEmpByCode(empCode);
		if(StringUtils.isEmpty(empCode)||StringUtils.isEmpty(changeType)){
			throw new SyncException("the map is illegal");
		}
		if(changeType.equals(DELETE)){
			changeType=DELETEDES;
			if(emp==null){
				return SUCCESS;
			}
		}else{
			if(emp==null){
				  changeType=INSERTDES;
			}else {
				    changeType=UPDATEDES;	
					oldPosition=emp.getPosition();
					oldOrgId=emp.getDeptId().getId();
			}
		}
		if(oldPosition.equals(position)){
			position=null;
		}
		if(oldOrgId.equals(orgId)){
			orgId=null;
		}
		employeeService.syncEmp(empCode, changeType, position, orgId);
		return SUCCESS;
	}
	public IEmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}
