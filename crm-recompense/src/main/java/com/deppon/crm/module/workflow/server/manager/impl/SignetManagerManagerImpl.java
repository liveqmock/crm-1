package com.deppon.crm.module.workflow.server.manager.impl;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.workflow.server.manager.ISignetManagerManager;
import com.deppon.crm.module.workflow.server.service.ISignetManagerService;
import com.deppon.crm.module.workflow.shared.domain.SignetManager;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * 
 * <p>
 * Description:印章管理员设置管理类<br />
 * </p>
 * @title SignetManagerManagerImpl.java
 * @package com.deppon.crm.module.workflow.server.manager.impl 
 * @author liuHuan
 * @version 0.1 2013-7-31
 */
public class SignetManagerManagerImpl implements ISignetManagerManager {

	
	private ISignetManagerService signetManagerService;
	
	private IDepartmentService departmentService;
	
	
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setSignetManagerService(ISignetManagerService signetManagerService) {
		this.signetManagerService = signetManagerService;
	}

	/**
	 * 
	 * <p>
	 * Description:列表查询印章管理员信息<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-7-31
	 * @param empId
	 * @return
	 * List<SignetManager>
	 */
	@Override
	public List<SignetManager> findSignetManager(Integer empId,int start,int limit) {
		return signetManagerService.findSignetManager(empId,start,limit);
	}

	/**
	 * 
	 * <p>
	 * Description:添加印章管理员<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-1
	 * @param obj
	 * @return
	 * int 0 成功 1已存在印章管理员 2已存在部门
	 */
	@Override
	public int addSignetManager(SignetManager obj) {
		if(findIfExistEmp(obj.getEmpId())){
			return 1;
		}else if(findIfExistDep(obj.getDeptId())){
			return 2;
		}
		//获取当前用户
		User user = (User) UserContext.getCurrentUser();
		//记录修改人工号
		obj.setCreateUser(user.getEmpCode().getEmpCode());
		//记录修改时间
		obj.setCreateDate(new Date());
		signetManagerService.addSignetManager(obj);
		return 0;
	}


	/**
	 * 
	 * <p>
	 * Description:删除印章管理员<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-1
	 * @param id
	 * void
	 */
	@Override
	public void deleteSignetManagerById(Integer id) {
		signetManagerService.deleteSignetManagerById(id);		
	}
	
	/**
	 * 
	 * <p>
	 * Description:事业部与枢纽中心<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-5
	 * @return
	 * List<Department>
	 */
	@Override
	public List<Department> getBizAndHubCenterDeptList() {
		return departmentService.getBizAndHubCenterDeptList();
	}

   /**
	 * 
	 * <p>
	 * Description:查询印章管理员是否已存在<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-5
	 * @param emp
	 * @return
	 * int
	 */
	@Override
	public boolean findIfExistEmp(Integer emp) {
		return signetManagerService.findEmpCount(emp)>0;
	}

	/**
	 * 
	 * <p>
	 * Description:列表查询印章管理员条数<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-7-31
	 * @param empId
	 * @return
	 * long
	 */
	@Override
	public long findSignetManagerCount(Integer empId) {
		return signetManagerService.findSignetManagerCount(empId);
	}

	/**
	 * 
	 * <p>
	 * Description:查询所属区域印章管理员<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2013-8-14
	 * @param deptId
	 * @return
	 * SignetManager
	 */
	@Override
	public SignetManager getSignetManagerByDeptId(String deptId) {
		return signetManagerService.getSignetManagerByDeptId(deptId);
	}

	/**
   	 * 
   	 * <p>
   	 * Description:查询部门是否已存在<br />
   	 * </p>
   	 * @author liuHuan
   	 * @version 0.1 2013-8-5
   	 * @param deptId
   	 * @return
   	 * boolean true 存在 false 不存在
   	 */
	@Override
	public boolean findIfExistDep(Integer deptId) {
		return signetManagerService.findDepCount(deptId)>0;
	}

}
