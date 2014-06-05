package com.deppon.crm.module.workflow.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.workflow.server.dao.ISignetManagerDao;
import com.deppon.crm.module.workflow.shared.domain.SignetManager;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 * <p>
 * Description:印章管理员设置Dao<br />
 * </p>
 * @title SignetManagerDaoImpl.java
 * @package com.deppon.crm.module.workflow.server.dao.impl 
 * @author Administrator
 * @version 0.1 2013-7-31
 */
public class SignetManagerDaoImpl  extends iBatis3DaoImpl implements ISignetManagerDao {

	//命名空间
	private static final String NAMESPACE = "com.deppon.crm.module.workflow.server.dao.impl.SignetManagerDaoImpl.";

	//查询印章管理员
	public static final String FIND_SIGNETMANAGER = "findSignetManager";
	
	//查询印章管理员条数
	public static final String FIND_SIGNETMANAGERCOUNT = "findSignetManagerCount";
	
	//添加印章管事员
	public static final String ADD_SIGNETMANAGER = "addSignetManager";
	
	//删除印章管事员
	public static final String DELETE_SIGNETMANAGERBYID = "deleteSignetManagerById";
	
	//查询印章管理员记录数
	public static final String FIND_EMPCOUNT = "findEmpCount";
	
	//查询部门记录数
	public static final String FIND_DEPCOUNT = "findDepCount";
	
	//查询所属区域印章管理员
	public static final String GETSIGNETMANAGERBYDEPTID = "getSignetManagerByDeptId";
	
	/**
	 * 
	 * <p>
	 * Description:列表查询印章管理员信息<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-7-31
	 * @param empId
	 * @return List<SignetManager>
	 * List<SignetManager>
	 */
	@Override
	public List<SignetManager> findSignetManager(Integer empId,int start,int limit) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("empId", empId);
		RowBounds bounds = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAMESPACE+FIND_SIGNETMANAGER,map,bounds);
	}

	
	/**
	 * 
	 * <p>
	 * Description:列表查询印章管理员条数<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-7-31
	 * @param userId
	 * @return
	 * long
	 */
	@Override
	public long findSignetManagerCount(Integer empId) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("empId", empId);
		return (Long)this.getSqlSession().selectOne(NAMESPACE+FIND_SIGNETMANAGERCOUNT,map);
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
		this.getSqlSession().insert(NAMESPACE+ADD_SIGNETMANAGER,obj);		
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
		this.getSqlSession().delete(NAMESPACE+DELETE_SIGNETMANAGERBYID,id);
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
		return (Integer)this.getSqlSession().selectOne(NAMESPACE+FIND_EMPCOUNT,emp);
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
		return (SignetManager)this.getSqlSession().selectOne(NAMESPACE + GETSIGNETMANAGERBYDEPTID, deptId);
	}


	 /**
     * 
     * <p>
     * Description:根据部门id查询部门记录数<br />
     * </p>
     * @author liuHuan
     * @version 0.1 2013-8-22
     * @param deptId
     * @return
     * int
     */
	@Override
	public int findDepCount(Integer deptId) {
		return (Integer)this.getSqlSession().selectOne(NAMESPACE+FIND_DEPCOUNT,deptId);
	}


	
	
	

}
