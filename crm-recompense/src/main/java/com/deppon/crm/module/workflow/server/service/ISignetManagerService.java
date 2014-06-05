package com.deppon.crm.module.workflow.server.service;

import java.util.List;

import com.deppon.crm.module.workflow.shared.domain.SignetManager;

/**
 * 
 * <p>
 * Description:印章管理员设置服务接口<br />
 * </p>
 * @title ISignetManagerService.java
 * @package com.deppon.crm.module.workflow.server.service 
 * @author liuHuan
 * @version 0.1 2013-7-31
 */
public interface ISignetManagerService {
	
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
	 * void
	 */
	void addSignetManager(SignetManager obj);
	
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
	 * Description:查询印章管理员个数<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-5
	 * @param emp
	 * @return
	 * int
	 */
	int findEmpCount(Integer emp);
	
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
	 * Description:查询部门记录数<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-5
	 * @param emp
	 * @return
	 * int
	 */
	int findDepCount(Integer deptId);

}
