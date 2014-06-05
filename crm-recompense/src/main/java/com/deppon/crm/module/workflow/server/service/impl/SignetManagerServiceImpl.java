package com.deppon.crm.module.workflow.server.service.impl;

import java.util.List;

import com.deppon.crm.module.workflow.server.dao.ISignetManagerDao;
import com.deppon.crm.module.workflow.server.service.ISignetManagerService;
import com.deppon.crm.module.workflow.shared.domain.SignetManager;

/**
 * 
 * <p>
 * Description:印章管理员设置服务类<br />
 * </p>
 * @title SignetManagerServiceImpl.java
 * @package com.deppon.crm.module.workflow.server.service.impl 
 * @author liuHuan
 * @version 0.1 2013-7-31
 */
public class SignetManagerServiceImpl implements ISignetManagerService {

	
	private ISignetManagerDao signetManagerDao;
	
	
	
	public void setSignetManagerDao(ISignetManagerDao signetManagerDao) {
		this.signetManagerDao = signetManagerDao;
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
		return signetManagerDao.findSignetManager(empId,start,limit);
	}


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
	@Override
	public void addSignetManager(SignetManager obj) {
		if(obj!=null){
			signetManagerDao.addSignetManager(obj);
		}
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
		signetManagerDao.deleteSignetManagerById(id);		
	}


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
	@Override
	public int findEmpCount(Integer emp) {
		return signetManagerDao.findEmpCount(emp);
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
		return signetManagerDao.findSignetManagerCount(empId);
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
		return signetManagerDao.getSignetManagerByDeptId(deptId);
	}


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
	@Override
	public int findDepCount(Integer deptId) {
		return signetManagerDao.findDepCount(deptId);
	}

}
