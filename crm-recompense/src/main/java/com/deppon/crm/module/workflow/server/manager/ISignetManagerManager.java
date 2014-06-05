package com.deppon.crm.module.workflow.server.manager;

import java.util.List;

import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.workflow.shared.domain.SignetManager;

/**
 * 
 * <p>
 * Description:印章管理员设置管理接口<br />
 * </p>
 * @title ISignetManagerManager.java
 * @package com.deppon.crm.module.workflow.server.manager 
 * @author liuHuan
 * @version 0.1 2013-7-31
 */
public interface ISignetManagerManager {
	
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
	List<SignetManager> findSignetManager(Integer empId,int start,int limit);
	
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
	long findSignetManagerCount(Integer empId);
	

	/**
	 * 
	 * <p>
	 * Description:添加印章管理员<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-1
	 * @param obj
	 * @return
	 * int 0 成功 1已存在
	 */
	int addSignetManager(SignetManager obj);
	

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
	void deleteSignetManagerById(Integer id); 
	
	
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
    List<Department> getBizAndHubCenterDeptList();
    
    /**
	 * 
	 * <p>
	 * Description:查询印章管理员是否已存在<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-5
	 * @param emp
	 * @return
	 * boolean true 存在 false 不存在
	 */
    boolean findIfExistEmp(Integer emp);
    
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
    SignetManager getSignetManagerByDeptId(String deptId);
    
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
    boolean findIfExistDep(Integer deptId);

}
