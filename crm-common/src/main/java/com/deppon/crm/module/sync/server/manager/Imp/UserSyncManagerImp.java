package com.deppon.crm.module.sync.server.manager.Imp;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.sync.server.manager.ISyncManager;
import com.deppon.crm.module.sync.shared.SyncException;
public class UserSyncManagerImp implements ISyncManager{
	private IEmployeeService employeeService;
	private IUserService userService;
	//插入
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
		String changeType = entity.get(CHANGETYPE);
		String empCode=entity.get(MAINCODE);
		String position="";
		String orgId="";
		Employee emp=employeeService.getEmpByCode(empCode);
		if(StringUtils.isEmpty(empCode)||StringUtils.isEmpty(changeType)){
			throw new SyncException("the map is illegal");
		}
		if(emp==null){
			throw new SyncException("not find the employee "+empCode);
		}
		User user=userService.selectByCode(empCode);
		if(changeType.equals(DELETE)){
			changeType=DELETEDES;
			if(user==null)
			{
				return SUCCESS;
			}
		}else{
			if(user==null){
				changeType=INSERTDES;
				position=emp.getPosition();
				orgId=emp.getDeptId().getId();
			}else {
				changeType=UPDATEDES;
				position=null;
				orgId=null;
			}
		}
		userService.syncUser(empCode, changeType, position, orgId);
		return SUCCESS;
	}
	
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public IEmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
}
